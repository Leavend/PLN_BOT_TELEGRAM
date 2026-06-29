package org.mockito.internal.verification;

import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationsFinder;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.invocation.Invocation;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class NoMoreInteractions implements VerificationMode, VerificationInOrderMode {
    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) {
        Invocation invocationFindFirstUnverified = InvocationsFinder.findFirstUnverified(verificationData.getAllInvocations());
        if (invocationFindFirstUnverified != null) {
            throw Reporter.noMoreInteractionsWanted(invocationFindFirstUnverified, verificationData.getAllInvocations());
        }
    }

    @Override // org.mockito.internal.verification.api.VerificationInOrderMode
    public void verifyInOrder(VerificationDataInOrder verificationDataInOrder) {
        Invocation invocationFindFirstUnverifiedInOrder = InvocationsFinder.findFirstUnverifiedInOrder(verificationDataInOrder.getOrderingContext(), verificationDataInOrder.getAllInvocations());
        if (invocationFindFirstUnverifiedInOrder != null) {
            throw Reporter.noMoreInteractionsWantedInOrder(invocationFindFirstUnverifiedInOrder);
        }
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
