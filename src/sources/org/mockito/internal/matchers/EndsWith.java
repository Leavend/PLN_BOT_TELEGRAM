package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class EndsWith implements ArgumentMatcher<String>, Serializable {
    private final String suffix;

    public EndsWith(String str) {
        this.suffix = str;
    }

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(String str) {
        return str != null && str.endsWith(this.suffix);
    }

    public String toString() {
        return "endsWith(\"" + this.suffix + "\")";
    }
}
