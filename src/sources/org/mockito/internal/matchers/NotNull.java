package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class NotNull implements ArgumentMatcher<Object>, Serializable {
    public static final NotNull NOT_NULL = new NotNull();

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Object obj) {
        return obj != null;
    }

    public String toString() {
        return "notNull()";
    }

    private NotNull() {
    }
}
