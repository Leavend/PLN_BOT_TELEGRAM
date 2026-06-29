package org.mockito.internal.invocation;

import org.mockito.ArgumentMatcher;

/* loaded from: classes3.dex */
public interface ArgumentMatcherAction {
    boolean apply(ArgumentMatcher<?> argumentMatcher, Object obj);
}
