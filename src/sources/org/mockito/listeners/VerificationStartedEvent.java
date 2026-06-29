package org.mockito.listeners;

import org.mockito.Incubating;

@Incubating
/* loaded from: classes3.dex */
public interface VerificationStartedEvent {
    @Incubating
    Object getMock();

    @Incubating
    void setMock(Object obj);
}
