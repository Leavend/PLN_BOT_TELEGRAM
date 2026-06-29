package org.mockito.internal.verification;

import java.util.List;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.internal.verification.checkers.MissingInvocationChecker;
import org.mockito.internal.verification.checkers.NumberOfInvocationsChecker;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class Times implements VerificationInOrderMode, VerificationMode {
    final int wantedCount;

    public Times(int i) {
        if (i < 0) {
            throw new MockitoException("Negative value is not allowed here");
        }
        this.wantedCount = i;
    }

    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) {
        List<Invocation> allInvocations = verificationData.getAllInvocations();
        MatchableInvocation target = verificationData.getTarget();
        if (this.wantedCount > 0) {
            MissingInvocationChecker.checkMissingInvocation(verificationData.getAllInvocations(), verificationData.getTarget());
        }
        NumberOfInvocationsChecker.checkNumberOfInvocations(allInvocations, target, this.wantedCount);
    }

    @Override // org.mockito.internal.verification.api.VerificationInOrderMode
    public void verifyInOrder(VerificationDataInOrder verificationDataInOrder) {
        List<Invocation> allInvocations = verificationDataInOrder.getAllInvocations();
        MatchableInvocation wanted = verificationDataInOrder.getWanted();
        if (this.wantedCount > 0) {
            MissingInvocationChecker.checkMissingInvocation(allInvocations, wanted, verificationDataInOrder.getOrderingContext());
        }
        NumberOfInvocationsChecker.checkNumberOfInvocations(allInvocations, wanted, this.wantedCount, verificationDataInOrder.getOrderingContext());
    }

    public String toString() {
        return "Wanted invocations count: " + this.wantedCount;
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
