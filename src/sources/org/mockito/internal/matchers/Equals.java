package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.text.ValuePrinter;

/* loaded from: classes3.dex */
public class Equals implements ArgumentMatcher<Object>, ContainsExtraTypeInfo, Serializable {
    private final Object wanted;

    public int hashCode() {
        return 1;
    }

    public Equals(Object obj) {
        this.wanted = obj;
    }

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Object obj) {
        return Equality.areEqual(this.wanted, obj);
    }

    public String toString() {
        return describe(this.wanted);
    }

    private String describe(Object obj) {
        return ValuePrinter.print(obj);
    }

    protected final Object getWanted() {
        return this.wanted;
    }

    public boolean equals(Object obj) {
        if (obj == null || !getClass().equals(obj.getClass())) {
            return false;
        }
        Equals equals = (Equals) obj;
        Object obj2 = this.wanted;
        return (obj2 == null && equals.wanted == null) || (obj2 != null && obj2.equals(equals.wanted));
    }

    @Override // org.mockito.internal.matchers.ContainsExtraTypeInfo
    public String toStringWithType() {
        return "(" + this.wanted.getClass().getSimpleName() + ") " + describe(this.wanted);
    }

    @Override // org.mockito.internal.matchers.ContainsExtraTypeInfo
    public boolean typeMatches(Object obj) {
        return (this.wanted == null || obj == null || obj.getClass() != this.wanted.getClass()) ? false : true;
    }
}
