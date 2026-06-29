package org.objenesis.instantiator;

import java.io.Serializable;

/* loaded from: classes3.dex */
public class SerializationInstantiatorHelper {
    public static <T> Class<? super T> getNonSerializableSuperClass(Class<T> cls) {
        while (Serializable.class.isAssignableFrom(cls)) {
            cls = (Class<? super T>) cls.getSuperclass();
            if (cls == null) {
                throw new Error("Bad class hierarchy: No non-serializable parents");
            }
        }
        return (Class<? super T>) cls;
    }
}
