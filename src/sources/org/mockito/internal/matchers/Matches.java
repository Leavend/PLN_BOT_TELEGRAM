package org.mockito.internal.matchers;

import java.io.Serializable;
import java.util.regex.Pattern;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class Matches implements ArgumentMatcher<Object>, Serializable {
    private final Pattern pattern;

    public Matches(String str) {
        this(Pattern.compile(str));
    }

    public Matches(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Object obj) {
        return (obj instanceof String) && this.pattern.matcher((String) obj).matches();
    }

    public String toString() {
        return "matches(\"" + this.pattern.pattern().replaceAll("\\\\", "\\\\\\\\") + "\")";
    }
}
