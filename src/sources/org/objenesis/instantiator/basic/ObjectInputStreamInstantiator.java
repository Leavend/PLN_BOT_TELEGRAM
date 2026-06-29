package org.objenesis.instantiator.basic;

import androidx.compose.material3.MenuKt;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

@Instantiator(Typology.SERIALIZATION)
/* loaded from: classes3.dex */
public class ObjectInputStreamInstantiator<T> implements ObjectInstantiator<T> {
    private ObjectInputStream inputStream;

    private static class MockStream extends InputStream {
        private static byte[] HEADER;
        private static final int[] NEXT = {1, 2, 2};
        private static byte[] REPEATING_DATA;
        private final byte[] FIRST_DATA;
        private byte[][] buffers;
        private int pointer = 0;
        private int sequence = 0;
        private byte[] data = HEADER;

        @Override // java.io.InputStream
        public int available() throws IOException {
            return Integer.MAX_VALUE;
        }

        static {
            initialize();
        }

        private static void initialize() throws IOException {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeShort(-21267);
                dataOutputStream.writeShort(5);
                HEADER = byteArrayOutputStream.toByteArray();
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream2 = new DataOutputStream(byteArrayOutputStream2);
                dataOutputStream2.writeByte(115);
                dataOutputStream2.writeByte(113);
                dataOutputStream2.writeInt(8257536);
                REPEATING_DATA = byteArrayOutputStream2.toByteArray();
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
        }

        public MockStream(Class<?> cls) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeByte(115);
                dataOutputStream.writeByte(114);
                dataOutputStream.writeUTF(cls.getName());
                dataOutputStream.writeLong(ObjectStreamClass.lookup(cls).getSerialVersionUID());
                dataOutputStream.writeByte(2);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeByte(MenuKt.InTransitionDuration);
                dataOutputStream.writeByte(112);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                this.FIRST_DATA = byteArray;
                this.buffers = new byte[][]{HEADER, byteArray, REPEATING_DATA};
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
        }

        private void advanceBuffer() {
            this.pointer = 0;
            int i = NEXT[this.sequence];
            this.sequence = i;
            this.data = this.buffers[i];
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            byte[] bArr = this.data;
            int i = this.pointer;
            int i2 = i + 1;
            this.pointer = i2;
            byte b = bArr[i];
            if (i2 >= bArr.length) {
                advanceBuffer();
            }
            return b;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int length = this.data.length - this.pointer;
            int i3 = i2;
            while (length <= i3) {
                System.arraycopy(this.data, this.pointer, bArr, i, length);
                i += length;
                i3 -= length;
                advanceBuffer();
                length = this.data.length - this.pointer;
            }
            if (i3 > 0) {
                System.arraycopy(this.data, this.pointer, bArr, i, i3);
                this.pointer += i3;
            }
            return i2;
        }
    }

    public ObjectInputStreamInstantiator(Class<T> cls) {
        if (Serializable.class.isAssignableFrom(cls)) {
            try {
                this.inputStream = new ObjectInputStream(new MockStream(cls));
                return;
            } catch (IOException e) {
                throw new Error("IOException: " + e.getMessage());
            }
        }
        throw new ObjenesisException(new NotSerializableException(cls + " not serializable"));
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) this.inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new Error("ClassNotFoundException: " + e.getMessage());
        } catch (Exception e2) {
            throw new ObjenesisException(e2);
        }
    }
}
