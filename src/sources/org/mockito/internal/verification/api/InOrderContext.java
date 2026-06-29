package org.mockito.internal.verification.api;

import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public interface InOrderContext {
    boolean isVerified(Invocation invocation);

    void markVerified(Invocation invocation);
}
