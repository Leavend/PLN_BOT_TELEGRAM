package org.mockito.verification;

import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.Timer;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.internal.verification.VerificationOverTimeImpl;
import org.mockito.internal.verification.VerificationWrapper;

/* loaded from: classes3.dex */
public class Timeout extends VerificationWrapper<VerificationOverTimeImpl> implements VerificationWithTimeout {
    public Timeout(long j, VerificationMode verificationMode) {
        this(10L, j, verificationMode);
    }

    Timeout(long j, long j2, VerificationMode verificationMode) {
        this(new VerificationOverTimeImpl(j, j2, verificationMode, true));
    }

    Timeout(long j, VerificationMode verificationMode, Timer timer) {
        this(new VerificationOverTimeImpl(j, verificationMode, true, timer));
    }

    Timeout(VerificationOverTimeImpl verificationOverTimeImpl) {
        super(verificationOverTimeImpl);
    }

    @Override // org.mockito.internal.verification.VerificationWrapper
    protected VerificationMode copySelfWithNewVerificationMode(VerificationMode verificationMode) {
        return new Timeout(((VerificationOverTimeImpl) this.wrappedVerification).copyWithVerificationMode(verificationMode));
    }

    @Override // org.mockito.internal.verification.VerificationWrapper
    public VerificationMode atMost(int i) {
        throw Reporter.atMostAndNeverShouldNotBeUsedWithTimeout();
    }

    @Override // org.mockito.internal.verification.VerificationWrapper
    public VerificationMode never() {
        throw Reporter.atMostAndNeverShouldNotBeUsedWithTimeout();
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }
}
