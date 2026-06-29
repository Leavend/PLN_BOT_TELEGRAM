package org.mockito.internal.runners.util;

import java.lang.reflect.Method;
import org.junit.Test;

/* loaded from: classes3.dex */
public class TestMethodsFinder {
    private TestMethodsFinder() {
    }

    public static boolean hasTestMethods(Class<?> cls) throws SecurityException {
        for (Method method : cls.getMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                return true;
            }
        }
        return false;
    }
}
