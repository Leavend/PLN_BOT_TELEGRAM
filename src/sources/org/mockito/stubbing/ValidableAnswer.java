package org.mockito.stubbing;

import org.mockito.Incubating;
import org.mockito.invocation.InvocationOnMock;

@Incubating
/* loaded from: classes3.dex */
public interface ValidableAnswer {
    void validateFor(InvocationOnMock invocationOnMock);
}
