package org.mockito.internal.matchers;

import java.io.Serializable;
import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public class EqualsWithDelta implements ArgumentMatcher<Number>, Serializable {
    private final Number delta;
    private final Number wanted;

    public EqualsWithDelta(Number number, Number number2) {
        this.wanted = number;
        this.delta = number2;
    }

    @Override // org.mockito.ArgumentMatcher
    public boolean matches(Number number) {
        Number number2 = this.wanted;
        if ((number2 == null) ^ (number == null)) {
            return false;
        }
        if (number2 == number) {
            return true;
        }
        return number2.doubleValue() - this.delta.doubleValue() <= number.doubleValue() && number.doubleValue() <= this.wanted.doubleValue() + this.delta.doubleValue();
    }

    public String toString() {
        return "eq(" + this.wanted + ", " + this.delta + ")";
    }
}
