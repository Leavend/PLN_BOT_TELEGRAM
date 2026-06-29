package org.mockito.internal.runners.util;

import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes3.dex */
public class FailureDetector extends RunListener {
    private boolean failed;

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);
        this.failed = true;
    }

    public boolean isSuccessful() {
        return !this.failed;
    }
}
