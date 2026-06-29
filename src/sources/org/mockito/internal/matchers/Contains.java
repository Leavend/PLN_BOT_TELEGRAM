package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class Contains implements ArgumentMatcher<String>, Serializable {
    private final String substring;

    public Contains(String str) {
        this.substring = str;
    }

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(String str) {
        return str != null && str.contains(this.substring);
    }

    public String toString() {
        return "contains(\"" + this.substring + "\")";
    }
}
