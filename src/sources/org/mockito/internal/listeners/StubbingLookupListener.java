package org.mockito.internal.listeners;

import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public interface StubbingLookupListener {
    void onStubbingLookup(Invocation invocation, MatchableInvocation matchableInvocation);
}
