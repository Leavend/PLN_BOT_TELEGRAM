package org.mockito.exceptions.misusing;

import org.mockito.exceptions.base.MockitoException;

/* loaded from: classes3.dex */
public class InvalidUseOfMatchersException extends MockitoException {
    private static final long serialVersionUID = 1;

    public InvalidUseOfMatchersException(String str) {
        super(str);
    }

    public InvalidUseOfMatchersException() {
        super("");
    }
}
