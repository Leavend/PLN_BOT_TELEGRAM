package org.mockito.internal.creation;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class SuspendMethod {
    private static final String KOTLIN_CONTINUATION = "kotlin.coroutines.experimental.Continuation";

    public static Class<?>[] trimSuspendParameterTypes(Class<?>[] clsArr) {
        int length = clsArr.length;
        if (length <= 0) {
            return clsArr;
        }
        int i = length - 1;
        return clsArr[i].getName().equals(KOTLIN_CONTINUATION) ? (Class[]) Arrays.copyOf(clsArr, i) : clsArr;
    }
}
