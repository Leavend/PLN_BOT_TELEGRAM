package org.mockito.internal.invocation;

import java.util.Iterator;
import java.util.List;
import org.mockito.internal.verification.api.InOrderContext;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class InvocationMarker {
    private InvocationMarker() {
    }

    public static void markVerified(List<Invocation> list, MatchableInvocation matchableInvocation) {
        Iterator<Invocation> it = list.iterator();
        while (it.hasNext()) {
            markVerified(it.next(), matchableInvocation);
        }
    }

    public static void markVerified(Invocation invocation, MatchableInvocation matchableInvocation) {
        invocation.markVerified();
        matchableInvocation.captureArgumentsFrom(invocation);
    }

    public static void markVerifiedInOrder(List<Invocation> list, MatchableInvocation matchableInvocation, InOrderContext inOrderContext) {
        markVerified(list, matchableInvocation);
        Iterator<Invocation> it = list.iterator();
        while (it.hasNext()) {
            inOrderContext.markVerified(it.next());
        }
    }
}
