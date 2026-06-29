package org.mockito.exceptions.verification;

import org.mockito.exceptions.base.MockitoAssertionError;

/* loaded from: classes3.dex */
public class NeverWantedButInvoked extends MockitoAssertionError {
    private static final long serialVersionUID = 1;

    public NeverWantedButInvoked(String str) {
        super(str);
    }
}
