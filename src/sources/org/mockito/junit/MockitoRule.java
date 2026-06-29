package org.mockito.junit;

import org.junit.rules.MethodRule;
import org.mockito.Incubating;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public interface MockitoRule extends MethodRule {
    MockitoRule silent();

    @Incubating
    MockitoRule strictness(Strictness strictness);
}
