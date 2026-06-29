package org.objenesis.instantiator.basic;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

@Instantiator(Typology.STANDARD)
/* loaded from: classes3.dex */
public class ProxyingInstantiator<T> implements ObjectInstantiator<T> {
    private static final byte[] CODE;
    private static final int CODE_ATTRIBUTE_LENGTH;
    private static int CONSTANT_POOL_COUNT = 9;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_UTF8_CLASS = 7;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final String SUFFIX = "$$$Objenesis";
    private final Class<?> newType;

    static {
        byte[] bArr = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_return};
        CODE = bArr;
        CODE_ATTRIBUTE_LENGTH = bArr.length + 12;
    }

    public ProxyingInstantiator(Class<T> cls) throws Throwable {
        try {
            this.newType = ClassDefinitionUtils.defineClass(cls.getName() + SUFFIX, writeExtendingClass(cls, SUFFIX), cls.getClassLoader());
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        try {
            return (T) this.newType.newInstance();
        } catch (IllegalAccessException e) {
            throw new ObjenesisException(e);
        } catch (InstantiationException e2) {
            throw new ObjenesisException(e2);
        }
    }

    private static byte[] writeExtendingClass(Class<?> cls, String str) throws Throwable {
        DataOutputStream dataOutputStream;
        String strClassNameToInternalClassName = ClassDefinitionUtils.classNameToInternalClassName(cls.getName());
        String str2 = strClassNameToInternalClassName + str;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            dataOutputStream.write(ClassDefinitionUtils.MAGIC);
            dataOutputStream.write(ClassDefinitionUtils.VERSION);
            dataOutputStream.writeShort(CONSTANT_POOL_COUNT);
            dataOutputStream.writeByte(7);
            dataOutputStream.writeShort(7);
            dataOutputStream.writeByte(7);
            dataOutputStream.writeShort(8);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(CONSTRUCTOR_NAME);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(CONSTRUCTOR_DESC);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF("Code");
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF("L" + str2 + ";");
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(str2);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(strClassNameToInternalClassName);
            dataOutputStream.writeShort(33);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(2);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(3);
            dataOutputStream.writeShort(4);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(5);
            dataOutputStream.writeInt(CODE_ATTRIBUTE_LENGTH);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(1);
            byte[] bArr = CODE;
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            try {
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e2) {
                throw new ObjenesisException(e2);
            }
        } catch (IOException e3) {
            e = e3;
            dataOutputStream2 = dataOutputStream;
            throw new ObjenesisException(e);
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream2 = dataOutputStream;
            if (dataOutputStream2 != null) {
                try {
                    dataOutputStream2.close();
                } catch (IOException e4) {
                    throw new ObjenesisException(e4);
                }
            }
            throw th;
        }
    }
}
