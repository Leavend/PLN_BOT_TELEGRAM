package org.mockito.stubbing;

import org.mockito.NotExtensible;
import org.mockito.invocation.Invocation;

@NotExtensible
/* loaded from: classes3.dex */
public interface Stubbing extends Answer {
    Invocation getInvocation();

    boolean wasUsed();
}
