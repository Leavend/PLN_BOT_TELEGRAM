package org.mockito.internal.matchers;

import java.io.Serializable;
import java.lang.Comparable;

/* loaded from: classes3.dex */
public class GreaterOrEqual<T extends Comparable<T>> extends CompareTo<T> implements Serializable {
    @Override // org.mockito.internal.matchers.CompareTo
    protected String getName() {
        return "geq";
    }

    @Override // org.mockito.internal.matchers.CompareTo
    protected boolean matchResult(int i) {
        return i >= 0;
    }

    public GreaterOrEqual(T t) {
        super(t);
    }
}
