package org.mockito.internal.progress;

import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.LocalizedMatcher;

/* loaded from: classes3.dex */
public interface ArgumentMatcherStorage {
    List<LocalizedMatcher> pullLocalizedMatchers();

    void reportAnd();

    void reportMatcher(ArgumentMatcher<?> argumentMatcher);

    void reportNot();

    void reportOr();

    void reset();

    void validateState();
}
