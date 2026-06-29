package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;

/* loaded from: classes3.dex */
public class TooManyActualInvocations extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public TooManyActualInvocations(String str) {
        super(str);
    }
}
