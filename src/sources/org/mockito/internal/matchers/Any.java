package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class Any implements ArgumentMatcher<Object>, VarargMatcher, Serializable {
    public static final Any ANY = new Any();

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Object obj) {
        return true;
    }

    public String toString() {
        return "<any>";
    }

    private Any() {
    }
}
