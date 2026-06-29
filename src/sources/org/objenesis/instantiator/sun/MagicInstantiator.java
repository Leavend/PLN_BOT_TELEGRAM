package org.objenesis.instantiator.sun;

import com.google.common.base.Ascii;
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
public class MagicInstantiator<T> implements ObjectInstantiator<T> {
    private static final byte[] CONSTRUCTOR_CODE;
    private static final int CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_INTERFACE = 9;
    private static final int INDEX_CLASS_OBJECT = 14;
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_CLASS_TYPE = 17;
    private static final int INDEX_METHODREF_OBJECT_CONSTRUCTOR = 13;
    private static final int INDEX_NAMEANDTYPE_DEFAULT_CONSTRUCTOR = 16;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_INSTANTIATOR_CLASS = 7;
    private static final int INDEX_UTF8_INTERFACE = 10;
    private static final int INDEX_UTF8_NEWINSTANCE_DESC = 12;
    private static final int INDEX_UTF8_NEWINSTANCE_NAME = 11;
    private static final int INDEX_UTF8_OBJECT = 15;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final int INDEX_UTF8_TYPE = 18;
    private static final byte[] NEWINSTANCE_CODE;
    private static final int NEWINSTANCE_CODE_ATTRIBUTE_LENGTH;
    private ObjectInstantiator<T> instantiator;
    private static final String MAGIC_ACCESSOR = getMagicClass();
    private static int CONSTANT_POOL_COUNT = 19;

    static {
        byte[] bArr = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_invokespecial, 0, Ascii.CR, ClassDefinitionUtils.OPS_return};
        CONSTRUCTOR_CODE = bArr;
        CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH = bArr.length + 12;
        byte[] bArr2 = {ClassDefinitionUtils.OPS_new, 0, 17, ClassDefinitionUtils.OPS_dup, ClassDefinitionUtils.OPS_invokespecial, 0, Ascii.CR, ClassDefinitionUtils.OPS_areturn};
        NEWINSTANCE_CODE = bArr2;
        NEWINSTANCE_CODE_ATTRIBUTE_LENGTH = bArr2.length + 12;
    }

    public MagicInstantiator(Class<T> cls) {
        this.instantiator = newInstantiatorOf(cls);
    }

    public ObjectInstantiator<T> getInstantiator() {
        return this.instantiator;
    }

    private <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) throws Throwable {
        String str = getClass().getName() + "$$$" + cls.getSimpleName();
        Class existingClass = ClassDefinitionUtils.getExistingClass(getClass().getClassLoader(), str);
        if (existingClass == null) {
            try {
                existingClass = ClassDefinitionUtils.defineClass(str, writeExtendingClass(cls, str), getClass().getClassLoader());
            } catch (Exception e) {
                throw new ObjenesisException(e);
            }
        }
        try {
            return (ObjectInstantiator) existingClass.newInstance();
        } catch (IllegalAccessException e2) {
            throw new ObjenesisException(e2);
        } catch (InstantiationException e3) {
            throw new ObjenesisException(e3);
        }
    }

    private byte[] writeExtendingClass(Class<?> cls, String str) throws Throwable {
        DataOutputStream dataOutputStream;
        String strClassNameToInternalClassName = ClassDefinitionUtils.classNameToInternalClassName(str);
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
            dataOutputStream.writeUTF("L" + strClassNameToInternalClassName + ";");
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(strClassNameToInternalClassName);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(MAGIC_ACCESSOR);
            dataOutputStream.writeByte(7);
            dataOutputStream.writeShort(10);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(ObjectInstantiator.class.getName().replace('.', '/'));
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF("newInstance");
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF("()Ljava/lang/Object;");
            dataOutputStream.writeByte(10);
            dataOutputStream.writeShort(14);
            dataOutputStream.writeShort(16);
            dataOutputStream.writeByte(7);
            dataOutputStream.writeShort(15);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF("java/lang/Object");
            dataOutputStream.writeByte(12);
            dataOutputStream.writeShort(3);
            dataOutputStream.writeShort(4);
            dataOutputStream.writeByte(7);
            dataOutputStream.writeShort(18);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeUTF(ClassDefinitionUtils.classNameToInternalClassName(cls.getName()));
            dataOutputStream.writeShort(49);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(2);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(9);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(2);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(3);
            dataOutputStream.writeShort(4);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(5);
            dataOutputStream.writeInt(CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(1);
            byte[] bArr = CONSTRUCTOR_CODE;
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(11);
            dataOutputStream.writeShort(12);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(5);
            dataOutputStream.writeInt(NEWINSTANCE_CODE_ATTRIBUTE_LENGTH);
            dataOutputStream.writeShort(2);
            dataOutputStream.writeShort(1);
            byte[] bArr2 = NEWINSTANCE_CODE;
            dataOutputStream.writeInt(bArr2.length);
            dataOutputStream.write(bArr2);
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

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        return this.instantiator.newInstance();
    }

    private static String getMagicClass() throws ClassNotFoundException {
        try {
            Class.forName("sun.reflect.MagicAccessorImpl", false, MagicInstantiator.class.getClassLoader());
            return "sun/reflect/MagicAccessorImpl";
        } catch (ClassNotFoundException unused) {
            return "jdk/internal/reflect/MagicAccessorImpl";
        }
    }
}
