package org.mockito.internal.verification;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.util.Timer;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public class VerificationOverTimeImpl implements VerificationMode {
    private final VerificationMode delegate;
    private final long pollingPeriodMillis;
    private final boolean returnOnSuccess;
    private final Timer timer;

    public VerificationOverTimeImpl(long j, long j2, VerificationMode verificationMode, boolean z) {
        this(j, verificationMode, z, new Timer(j2));
    }

    public VerificationOverTimeImpl(long j, VerificationMode verificationMode, boolean z, Timer timer) {
        this.pollingPeriodMillis = j;
        this.delegate = verificationMode;
        this.returnOnSuccess = z;
        this.timer = timer;
    }

    @Override // org.mockito.verification.VerificationMode
    public void verify(VerificationData verificationData) throws InterruptedException {
        this.timer.start();
        do {
            AssertionError assertionErrorHandleVerifyException = null;
            while (this.timer.isCounting()) {
                try {
                    this.delegate.verify(verificationData);
                } catch (MockitoAssertionError e) {
                    assertionErrorHandleVerifyException = handleVerifyException(e);
                } catch (AssertionError e2) {
                    assertionErrorHandleVerifyException = handleVerifyException(e2);
                }
            }
            if (assertionErrorHandleVerifyException != null) {
                throw assertionErrorHandleVerifyException;
            }
            return;
        } while (!this.returnOnSuccess);
    }

    private AssertionError handleVerifyException(AssertionError assertionError) throws InterruptedException {
        if (canRecoverFromFailure(this.delegate)) {
            sleep(this.pollingPeriodMillis);
            return assertionError;
        }
        throw assertionError;
    }

    protected boolean canRecoverFromFailure(VerificationMode verificationMode) {
        return ((verificationMode instanceof AtMost) || (verificationMode instanceof NoMoreInteractions)) ? false : true;
    }

    public VerificationOverTimeImpl copyWithVerificationMode(VerificationMode verificationMode) {
        return new VerificationOverTimeImpl(this.pollingPeriodMillis, this.timer.duration(), verificationMode, this.returnOnSuccess);
    }

    private void sleep(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            throw new RuntimeException("Thread sleep has been interrupted", e);
        }
    }

    @Override // org.mockito.verification.VerificationMode
    public VerificationMode description(String str) {
        return VerificationModeFactory.description(this, str);
    }

    public boolean isReturnOnSuccess() {
        return this.returnOnSuccess;
    }

    public long getPollingPeriodMillis() {
        return this.pollingPeriodMillis;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public VerificationMode getDelegate() {
        return this.delegate;
    }
}
