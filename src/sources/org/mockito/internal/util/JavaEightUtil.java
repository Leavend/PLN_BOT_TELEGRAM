package org.mockito.internal.util;

import org.mockito.internal.creation.instance.InstantiationException;

/* loaded from: classes3.dex */
public final class JavaEightUtil {
    private static Object emptyOptional;
    private static Object emptyOptionalDouble;
    private static Object emptyOptionalInt;
    private static Object emptyOptionalLong;

    private JavaEightUtil() {
    }

    public static Object emptyOptional() {
        Object obj = emptyOptional;
        if (obj != null) {
            return obj;
        }
        Object objInvokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.Optional", "empty");
        emptyOptional = objInvokeNullaryFactoryMethod;
        return objInvokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalDouble() {
        Object obj = emptyOptionalDouble;
        if (obj != null) {
            return obj;
        }
        Object objInvokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalDouble", "empty");
        emptyOptionalDouble = objInvokeNullaryFactoryMethod;
        return objInvokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalInt() {
        Object obj = emptyOptionalInt;
        if (obj != null) {
            return obj;
        }
        Object objInvokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalInt", "empty");
        emptyOptionalInt = objInvokeNullaryFactoryMethod;
        return objInvokeNullaryFactoryMethod;
    }

    public static Object emptyOptionalLong() {
        Object obj = emptyOptionalLong;
        if (obj != null) {
            return obj;
        }
        Object objInvokeNullaryFactoryMethod = invokeNullaryFactoryMethod("java.util.OptionalLong", "empty");
        emptyOptionalLong = objInvokeNullaryFactoryMethod;
        return objInvokeNullaryFactoryMethod;
    }

    public static Object emptyStream() {
        return invokeNullaryFactoryMethod("java.util.stream.Stream", "empty");
    }

    public static Object emptyDoubleStream() {
        return invokeNullaryFactoryMethod("java.util.stream.DoubleStream", "empty");
    }

    public static Object emptyIntStream() {
        return invokeNullaryFactoryMethod("java.util.stream.IntStream", "empty");
    }

    public static Object emptyLongStream() {
        return invokeNullaryFactoryMethod("java.util.stream.LongStream", "empty");
    }

    private static Object invokeNullaryFactoryMethod(String str, String str2) {
        try {
            return Class.forName(str).getMethod(str2, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            throw new InstantiationException(String.format("Could not create %s#%s(): %s", str, str2, e), e);
        }
    }
}
