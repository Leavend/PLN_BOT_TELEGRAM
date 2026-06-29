package org.mockito.internal.matchers.text;

import java.lang.reflect.Method;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.util.StringUtil;

/* loaded from: classes3.dex */
class MatcherToString {
    MatcherToString() {
    }

    static String toString(ArgumentMatcher<?> argumentMatcher) throws SecurityException {
        for (Class<?> superclass = argumentMatcher.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            for (Method method : superclass.getDeclaredMethods()) {
                if (ObjectMethodsGuru.isToStringMethod(method)) {
                    return argumentMatcher.toString();
                }
            }
        }
        return StringUtil.decamelizeMatcher(argumentMatcher.getClass().getSimpleName());
    }
}
