package androidx.test.espresso.core.internal.deps.guava.collect;

import java.lang.reflect.Array;

/* loaded from: classes5.dex */
public final class ObjectArrays {
    static Object[] checkElementsNotNull(Object... objArr) {
        checkElementsNotNull(objArr, objArr.length);
        return objArr;
    }

    public static Object[] newArray(Class cls, int i) {
        return (Object[]) Array.newInstance((Class<?>) cls, i);
    }

    static Object checkElementNotNull(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("at index " + i);
    }

    public static Object[] newArray(Object[] objArr, int i) {
        return Platform.newArray(objArr, i);
    }

    static Object[] checkElementsNotNull(Object[] objArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            checkElementNotNull(objArr[i2], i2);
        }
        return objArr;
    }
}
