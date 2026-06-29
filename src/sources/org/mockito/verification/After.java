package org.mockito.verification;

import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.mockito.internal.verification.VerificationWrapper;

/* loaded from: classes3.dex */
public class After extends VerificationWrapper<VerificationOverTimeImpl> implements VerificationAfterDelay {
    public After(long j, VerificationMode verificationMode) {
        this(10L, j, verificationMode);
    }

    After(long j, long j2, VerificationMode verificationMode) {
        this(new VerificationOverTimeImpl(j, j2, verificationMode, false));
    }

    After(VerificationOverTimeImpl verificationOverTimeImpl) {
        super(verificationOverTimeImpl);
    }

    @Override // org.mockito.internal.verification.VerificationWrapper
    protected VerificationMode copySelfWithNewVerificationMode(VerificationMode verificationMode) {
        return new After(((VerificationOverTimeImpl) this.wrappedVerification).copyWithVerificationMode(verificationMode));
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
