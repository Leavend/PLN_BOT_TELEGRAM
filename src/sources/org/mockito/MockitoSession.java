package org.mockito;

import org.mockito.quality.Strictness;

@Incubating
/* loaded from: classes3.dex */
public interface MockitoSession {
    @Incubating
    void finishMocking();

    @Incubating
    void finishMocking(Throwable th);

    @Incubating
    void setStrictness(Strictness strictness);
}
