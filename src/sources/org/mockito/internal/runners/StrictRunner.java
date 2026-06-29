package org.mockito.internal.runners;

import org.junit.runner.Description;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.mockito.Mockito;
import org.mockito.exceptions.misusing.RedundantListenerException;
import org.mockito.internal.junit.UnnecessaryStubbingsReporter;
import org.mockito.internal.runners.util.FailureDetector;

/* loaded from: classes3.dex */
public class StrictRunner implements InternalRunner {
    private boolean filterRequested;
    private final InternalRunner runner;
    private final Class<?> testClass;

    public StrictRunner(InternalRunner internalRunner, Class<?> cls) {
        this.runner = internalRunner;
        this.testClass = cls;
    }

    @Override // org.mockito.internal.runners.InternalRunner
    public void run(RunNotifier runNotifier) throws RedundantListenerException {
        UnnecessaryStubbingsReporter unnecessaryStubbingsReporter = new UnnecessaryStubbingsReporter();
        FailureDetector failureDetector = new FailureDetector();
        Mockito.framework().addListener(unnecessaryStubbingsReporter);
        try {
            runNotifier.addListener(failureDetector);
            this.runner.run(runNotifier);
            Mockito.framework().removeListener(unnecessaryStubbingsReporter);
            if (this.filterRequested || !failureDetector.isSuccessful()) {
                return;
            }
            unnecessaryStubbingsReporter.validateUnusedStubs(this.testClass, runNotifier);
        } catch (Throwable th) {
            Mockito.framework().removeListener(unnecessaryStubbingsReporter);
            throw th;
        }
    }

    @Override // org.mockito.internal.runners.InternalRunner
    public Description getDescription() {
        return this.runner.getDescription();
    }

    @Override // org.junit.runner.manipulation.Filterable
    public void filter(Filter filter) throws NoTestsRemainException {
        this.filterRequested = true;
        this.runner.filter(filter);
    }
}
