package org.hamcrest;

/* loaded from: classes3.dex */
public class JavaLangMatcherAssert {
    private JavaLangMatcherAssert() {
    }

    public static <T> boolean that(T t, Matcher<? super T> matcher) {
        return matcher.matches(t);
    }
}
