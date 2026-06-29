package org.mockito.internal.util.collections;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class Sets {
    public static Set<Object> newMockSafeHashSet(Iterable<Object> iterable) {
        return HashCodeAndEqualsSafeSet.of(iterable);
    }

    public static Set<Object> newMockSafeHashSet(Object... objArr) {
        return HashCodeAndEqualsSafeSet.of(objArr);
    }

    public static <T> Set<T> newSet(T... tArr) {
        if (tArr == null) {
            throw new IllegalArgumentException("Expected an array of elements (or empty array) but received a null.");
        }
        return new LinkedHashSet(Arrays.asList(tArr));
    }
}
