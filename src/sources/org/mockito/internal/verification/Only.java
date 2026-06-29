package org.mockito.internal.verification;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMarker;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class Only implements VerificationMode {
    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) {
        MatchableInvocation target = verificationData.getTarget();
        List<Invocation> allInvocations = verificationData.getAllInvocations();
        List<Invocation> listFindInvocations = InvocationsFinder.findInvocations(allInvocations, target);
        if (allInvocations.size() != 1 && !listFindInvocations.isEmpty()) {
            throw Reporter.noMoreInteractionsWanted(InvocationsFinder.findFirstUnverified(allInvocations), allInvocations);
        }
        if (allInvocations.size() != 1 || listFindInvocations.isEmpty()) {
            throw Reporter.wantedButNotInvoked(target);
        }
        InvocationMarker.markVerified(listFindInvocations.get(0), target);
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
