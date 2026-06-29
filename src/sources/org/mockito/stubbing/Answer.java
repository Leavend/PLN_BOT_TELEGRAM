package org.mockito.stubbing;

import org.mockito.invocation.InvocationOnMock;

/* loaded from: classes3.dex */
public interface Answer<T> {
    T answer(InvocationOnMock invocationOnMock) throws Throwable;
}
