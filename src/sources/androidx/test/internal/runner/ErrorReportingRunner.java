package androidx.test.internal.runner;

import java.lang.annotation.Annotation;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

/* loaded from: classes5.dex */
public class ErrorReportingRunner extends Runner {
    private final Throwable cause;
    private final String className;

    public ErrorReportingRunner(String className, Throwable e) {
        this.className = className;
        this.cause = e;
    }

    @Override // org.junit.runner.Runner, org.junit.runner.Describable
    public Description getDescription() {
        Description descriptionCreateSuiteDescription = Description.createSuiteDescription(this.className, new Annotation[0]);
        descriptionCreateSuiteDescription.addChild(describeCause());
        return descriptionCreateSuiteDescription;
    }

    private Description describeCause() {
        return Description.createTestDescription(this.className, "initializationError", new Annotation[0]);
    }

    @Override // org.junit.runner.Runner
    public void run(RunNotifier notifier) throws StoppedByUserException {
        Description descriptionDescribeCause = describeCause();
        notifier.fireTestStarted(descriptionDescribeCause);
        notifier.fireTestFailure(new Failure(descriptionDescribeCause, this.cause));
        notifier.fireTestFinished(descriptionDescribeCause);
    }
}
