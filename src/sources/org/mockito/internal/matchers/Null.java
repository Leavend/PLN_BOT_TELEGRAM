package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class Null implements ArgumentMatcher<Object>, Serializable {
    public static final Null NULL = new Null();

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Object obj) {
        return obj == null;
    }

    public String toString() {
        return "isNull()";
    }

    private Null() {
    }
}
