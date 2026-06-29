package org.hamcrest.text;

import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/* loaded from: classes3.dex */
public class StringContainsInOrder extends TypeSafeMatcher<String> {
    private final Iterable<String> substrings;

    public StringContainsInOrder(Iterable<String> iterable) {
        this.substrings = iterable;
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        Iterator<String> it = this.substrings.iterator();
        int iIndexOf = 0;
        while (it.hasNext()) {
            iIndexOf = str.indexOf(it.next(), iIndexOf);
            if (iIndexOf == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was \"").appendText(str).appendText("\"");
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string containing ").appendValueList("", ", ", "", this.substrings).appendText(" in order");
    }

    @Factory
    public static Matcher<String> stringContainsInOrder(Iterable<String> iterable) {
        return new StringContainsInOrder(iterable);
    }
}
