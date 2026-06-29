package org.mockito.internal.junit;

import java.util.LinkedList;
import java.util.List;
import org.mockito.Mockito;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.listeners.StubbingLookupListener;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
class DefaultStubbingLookupListener implements StubbingLookupListener {
    private Strictness currentStrictness;
    private boolean mismatchesReported;

    DefaultStubbingLookupListener(Strictness strictness) {
        this.currentStrictness = strictness;
    }

    @Override // org.mockito.internal.listeners.StubbingLookupListener
    public void onStubbingLookup(Invocation invocation, MatchableInvocation matchableInvocation) {
        if (this.currentStrictness != Strictness.STRICT_STUBS) {
            return;
        }
        if (matchableInvocation == null) {
            List<Invocation> listPotentialArgMismatches = potentialArgMismatches(invocation);
            if (listPotentialArgMismatches.isEmpty()) {
                return;
            }
            this.mismatchesReported = true;
            Reporter.potentialStubbingProblem(invocation, listPotentialArgMismatches);
            return;
        }
        invocation.markVerified();
    }

    private static List<Invocation> potentialArgMismatches(Invocation invocation) {
        LinkedList linkedList = new LinkedList();
        for (Stubbing stubbing : Mockito.mockingDetails(invocation.getMock()).getStubbings()) {
            if (!stubbing.wasUsed() && stubbing.getInvocation().getMethod().getName().equals(invocation.getMethod().getName())) {
                linkedList.add(stubbing.getInvocation());
            }
        }
        return linkedList;
    }

    void setCurrentStrictness(Strictness strictness) {
        this.currentStrictness = strictness;
    }

    boolean isMismatchesReported() {
        return this.mismatchesReported;
    }
}
