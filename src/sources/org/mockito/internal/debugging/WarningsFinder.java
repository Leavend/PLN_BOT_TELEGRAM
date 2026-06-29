package org.mockito.internal.debugging;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;

/* loaded from: classes3.dex */
public class WarningsFinder {
    private final List<InvocationMatcher> baseAllInvocations;
    private final List<Invocation> baseUnusedStubs;

    public WarningsFinder(List<Invocation> list, List<InvocationMatcher> list2) {
        this.baseUnusedStubs = list;
        this.baseAllInvocations = list2;
    }

    public void find(FindingsListener findingsListener) {
        LinkedList linkedList = new LinkedList(this.baseUnusedStubs);
        LinkedList linkedList2 = new LinkedList(this.baseAllInvocations);
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            Invocation invocation = (Invocation) it.next();
            Iterator it2 = linkedList2.iterator();
            while (it2.hasNext()) {
                InvocationMatcher invocationMatcher = (InvocationMatcher) it2.next();
                if (invocationMatcher.hasSimilarMethod(invocation)) {
                    findingsListener.foundStubCalledWithDifferentArgs(invocation, invocationMatcher);
                    it.remove();
                    it2.remove();
                }
            }
        }
        Iterator it3 = linkedList.iterator();
        while (it3.hasNext()) {
            findingsListener.foundUnusedStub((Invocation) it3.next());
        }
        Iterator it4 = linkedList2.iterator();
        while (it4.hasNext()) {
            findingsListener.foundUnstubbed((InvocationMatcher) it4.next());
        }
    }
}
