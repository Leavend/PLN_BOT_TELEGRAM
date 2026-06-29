package org.mockito.internal.verification;

import java.util.List;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.util.ObjectMethodsGuru;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

/* loaded from: classes3.dex */
public class VerificationDataImpl implements VerificationData {
    private final InvocationContainerImpl invocations;
    private final InvocationMatcher wanted;

    public VerificationDataImpl(InvocationContainerImpl invocationContainerImpl, InvocationMatcher invocationMatcher) {
        this.invocations = invocationContainerImpl;
        this.wanted = invocationMatcher;
        assertWantedIsVerifiable();
    }

    @Override // org.mockito.internal.verification.api.VerificationData
    public List<Invocation> getAllInvocations() {
        return this.invocations.getInvocations();
    }

    @Override // org.mockito.internal.verification.api.VerificationData
    public MatchableInvocation getTarget() {
        return this.wanted;
    }

    @Override // org.mockito.internal.verification.api.VerificationData
    public InvocationMatcher getWanted() {
        return this.wanted;
    }

    private void assertWantedIsVerifiable() {
        InvocationMatcher invocationMatcher = this.wanted;
        if (invocationMatcher != null && ObjectMethodsGuru.isToStringMethod(invocationMatcher.getMethod())) {
            throw Reporter.cannotVerifyToString();
        }
    }
}
