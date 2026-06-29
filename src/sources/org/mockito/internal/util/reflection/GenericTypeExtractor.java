package org.mockito.internal.util.reflection;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes3.dex */
public class GenericTypeExtractor {
    public static Class<?> genericTypeOf(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        while (cls != Object.class) {
            if (cls.getSuperclass() == cls2) {
                return extractGeneric(cls.getGenericSuperclass());
            }
            Type typeFindGenericInterface = findGenericInterface(cls, cls3);
            if (typeFindGenericInterface != null) {
                return extractGeneric(typeFindGenericInterface);
            }
            cls = cls.getSuperclass();
        }
        return Object.class;
    }

    private static Type findGenericInterface(Class<?> cls, Class<?> cls2) {
        for (int i = 0; i < cls.getInterfaces().length; i++) {
            Class<?> cls3 = cls.getInterfaces()[i];
            if (cls3 == cls2) {
                return cls.getGenericInterfaces()[0];
            }
            Type typeFindGenericInterface = findGenericInterface(cls3, cls2);
            if (typeFindGenericInterface != null) {
                return typeFindGenericInterface;
            }
        }
        return null;
    }

    private static Class<?> extractGeneric(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length <= 0) {
                return Object.class;
            }
            Type type2 = actualTypeArguments[0];
            if (type2 instanceof Class) {
                return (Class) type2;
            }
            return Object.class;
        }
        return Object.class;
    }
}
