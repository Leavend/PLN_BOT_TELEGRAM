package org.mockito.internal.junit;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.internal.progress.MockingProgressImpl;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.junit.VerificationCollector;
import org.mockito.verification.VerificationMode;
import org.mockito.verification.VerificationStrategy;

/* loaded from: classes3.dex */
public class VerificationCollectorImpl implements VerificationCollector {
    private StringBuilder builder;
    private int numberOfFailures;

    public VerificationCollectorImpl() {
        resetBuilder();
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement statement, Description description) {
        return new Statement() { // from class: org.mockito.internal.junit.VerificationCollectorImpl.1
            @Override // org.junit.runners.model.Statement
            public void evaluate() throws Throwable {
                try {
                    VerificationCollectorImpl.this.assertLazily();
                    statement.evaluate();
                    VerificationCollectorImpl.this.collectAndReport();
                } finally {
                    ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(MockingProgressImpl.getDefaultVerificationStrategy());
                }
            }
        };
    }

    @Override // org.mockito.junit.VerificationCollector
    public void collectAndReport() throws MockitoAssertionError {
        ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(MockingProgressImpl.getDefaultVerificationStrategy());
        if (this.numberOfFailures <= 0) {
            return;
        }
        String string = this.builder.toString();
        resetBuilder();
        throw new MockitoAssertionError(string);
    }

    @Override // org.mockito.junit.VerificationCollector
    public VerificationCollector assertLazily() {
        ThreadSafeMockingProgress.mockingProgress().setVerificationStrategy(new VerificationStrategy() { // from class: org.mockito.internal.junit.VerificationCollectorImpl.2
            @Override // org.mockito.verification.VerificationStrategy
            public VerificationMode maybeVerifyLazily(VerificationMode verificationMode) {
                return new VerificationWrapper(verificationMode);
            }
        });
        return this;
    }

    private void resetBuilder() {
        this.builder = new StringBuilder().append("There were multiple verification failures:");
        this.numberOfFailures = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void append(String str) {
        this.numberOfFailures++;
        this.builder.append('\n').append(this.numberOfFailures).append(". ").append(str.substring(1, str.length()));
    }

    private class VerificationWrapper implements VerificationMode {
        private final VerificationMode delegate;

        private VerificationWrapper(VerificationMode verificationMode) {
            this.delegate = verificationMode;
        }

        @Override // org.mockito.verification.VerificationMode
        public void verify(VerificationData verificationData) {
            try {
                this.delegate.verify(verificationData);
            } catch (MockitoAssertionError e) {
                VerificationCollectorImpl.this.append(e.getMessage());
            }
        }

        @Override // org.mockito.verification.VerificationMode
        public VerificationMode description(String str) {
            throw new IllegalStateException("Should not fail in this mode");
        }
    }
}
