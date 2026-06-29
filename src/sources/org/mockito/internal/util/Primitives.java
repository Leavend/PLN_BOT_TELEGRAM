package org.mockito.internal.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class Primitives {
    private static final Map<Class<?>, Object> PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES;
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPES;

    static {
        HashMap map = new HashMap();
        PRIMITIVE_TYPES = map;
        HashMap map2 = new HashMap();
        PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES = map2;
        map.put(Boolean.class, Boolean.TYPE);
        map.put(Character.class, Character.TYPE);
        map.put(Byte.class, Byte.TYPE);
        map.put(Short.class, Short.TYPE);
        map.put(Integer.class, Integer.TYPE);
        map.put(Long.class, Long.TYPE);
        map.put(Float.class, Float.TYPE);
        map.put(Double.class, Double.TYPE);
        map2.put(Boolean.class, false);
        map2.put(Character.class, (char) 0);
        map2.put(Byte.class, (byte) 0);
        map2.put(Short.class, (short) 0);
        map2.put(Integer.class, 0);
        map2.put(Long.class, 0L);
        Float fValueOf = Float.valueOf(0.0f);
        map2.put(Float.class, fValueOf);
        Double dValueOf = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        map2.put(Double.class, dValueOf);
        map2.put(Boolean.TYPE, false);
        map2.put(Character.TYPE, (char) 0);
        map2.put(Byte.TYPE, (byte) 0);
        map2.put(Short.TYPE, (short) 0);
        map2.put(Integer.TYPE, 0);
        map2.put(Long.TYPE, 0L);
        map2.put(Float.TYPE, fValueOf);
        map2.put(Double.TYPE, dValueOf);
    }

    public static <T> Class<T> primitiveTypeOf(Class<T> cls) {
        return cls.isPrimitive() ? cls : (Class) PRIMITIVE_TYPES.get(cls);
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        return PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.containsKey(cls);
    }

    public static boolean isAssignableFromWrapper(Class<?> cls, Class<?> cls2) {
        if (isPrimitiveOrWrapper(cls) && isPrimitiveOrWrapper(cls2)) {
            return primitiveTypeOf(cls).isAssignableFrom(primitiveTypeOf(cls2));
        }
        return false;
    }

    public static <T> T defaultValue(Class<T> cls) {
        return (T) PRIMITIVE_OR_WRAPPER_DEFAULT_VALUES.get(cls);
    }
}
