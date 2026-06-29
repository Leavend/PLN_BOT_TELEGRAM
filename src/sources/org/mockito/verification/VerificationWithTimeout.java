package org.mockito.verification;

/* loaded from: classes3.dex */
public interface VerificationWithTimeout extends VerificationMode {
    VerificationMode atLeast(int i);

    VerificationMode atLeastOnce();

    VerificationMode only();

    VerificationMode times(int i);
}
