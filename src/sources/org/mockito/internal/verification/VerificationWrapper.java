package org.mockito.internal.verification;

import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public abstract class VerificationWrapper<WrapperType extends VerificationMode> implements VerificationMode {
    protected final WrapperType wrappedVerification;

    protected abstract VerificationMode copySelfWithNewVerificationMode(VerificationMode verificationMode);

    public VerificationWrapper(WrapperType wrappertype) {
        this.wrappedVerification = wrappertype;
    }

    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) {
        this.wrappedVerification.verify(verificationData);
    }

    public VerificationMode times(int i) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.times(i));
    }

    public VerificationMode never() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atMost(0));
    }

    public VerificationMode atLeastOnce() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atLeastOnce());
    }

    public VerificationMode atLeast(int i) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atLeast(i));
    }

    public VerificationMode atMost(int i) {
        return copySelfWithNewVerificationMode(VerificationModeFactory.atMost(i));
    }

    public VerificationMode only() {
        return copySelfWithNewVerificationMode(VerificationModeFactory.only());
    }
}
