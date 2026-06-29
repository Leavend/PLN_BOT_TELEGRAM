package org.mockito.junit;

import org.mockito.Incubating;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.internal.junit.VerificationCollectorImpl;
import org.mockito.internal.util.ConsoleMockitoLogger;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public class MockitoJUnit {
    public static MockitoRule rule() {
        return new JUnitRule(new ConsoleMockitoLogger(), Strictness.WARN);
    }

    @Incubating
    public static VerificationCollector collector() {
        return new VerificationCollectorImpl();
    }
}
