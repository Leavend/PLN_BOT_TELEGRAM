package org.mockito;

import org.mockito.verification.VerificationMode;

/* loaded from: classes3.dex */
public interface InOrder {
    <T> T verify(T t);

    <T> T verify(T t, VerificationMode verificationMode);

    void verifyNoMoreInteractions();
}
