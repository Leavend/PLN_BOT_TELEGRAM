package androidx.test.internal.events.client;

import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.FailureInfo;
import androidx.test.services.events.ParcelableConverter;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.TestEventException;
import androidx.test.services.events.run.TestAssumptionFailureEvent;
import androidx.test.services.events.run.TestFailureEvent;
import androidx.test.services.events.run.TestFinishedEvent;
import androidx.test.services.events.run.TestIgnoredEvent;
import androidx.test.services.events.run.TestRunFinishedEvent;
import androidx.test.services.events.run.TestRunStartedEvent;
import androidx.test.services.events.run.TestStartedEvent;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public final class OrchestratedInstrumentationListener extends RunListener {
    private static final String TAG = "OrchestrationListener";
    private final TestRunEventService notificationService;
    private final AtomicBoolean isTestFailed = new AtomicBoolean(false);
    private Description description = Description.EMPTY;

    public OrchestratedInstrumentationListener(TestRunEventService notificationService) {
        Checks.checkNotNull(notificationService, "notificationService cannot be null");
        this.notificationService = notificationService;
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunStarted(Description description) {
        try {
            this.notificationService.send(new TestRunStartedEvent(ParcelableConverter.getTestCaseFromDescription(description)));
        } catch (TestEventException e) {
            Log.e(TAG, "Unable to send TestRunStartedEvent to Orchestrator", e);
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunFinished(Result result) {
        List<FailureInfo> listEmptyList = Collections.emptyList();
        try {
            listEmptyList = ParcelableConverter.getFailuresFromList(result.getFailures());
        } catch (TestEventException e) {
            Log.w(TAG, "Failure event doesn't contain a test case", e);
        }
        try {
            this.notificationService.send(new TestRunFinishedEvent(result.getRunCount(), result.getIgnoreCount(), result.getRunTime(), listEmptyList));
        } catch (TestEventException e2) {
            Log.e(TAG, "Unable to send TestRunFinishedEvent to Orchestrator", e2);
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testStarted(Description description) {
        this.description = description;
        if (!JUnitValidator.validateDescription(description)) {
            Log.w(TAG, "testStarted: JUnit reported " + description.getClassName() + "#" + description.getMethodName() + "; discarding as bogus.");
        } else {
            try {
                this.notificationService.send(new TestStartedEvent(ParcelableConverter.getTestCaseFromDescription(description)));
            } catch (TestEventException e) {
                Log.e(TAG, "Unable to send TestStartedEvent to Orchestrator", e);
            }
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFinished(Description description) {
        if (!JUnitValidator.validateDescription(description)) {
            Log.w(TAG, "testFinished: JUnit reported " + description.getClassName() + "#" + description.getMethodName() + "; discarding as bogus.");
        } else {
            try {
                this.notificationService.send(new TestFinishedEvent(ParcelableConverter.getTestCaseFromDescription(description)));
            } catch (TestEventException e) {
                Log.e(TAG, "Unable to send TestFinishedEvent to Orchestrator", e);
            }
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) {
        TestFailureEvent testFailureEventFromCachedDescription;
        if (this.isTestFailed.compareAndSet(false, true)) {
            Description description = failure.getDescription();
            if (!JUnitValidator.validateDescription(description)) {
                Log.w(TAG, "testFailure: JUnit reported " + description.getClassName() + "#" + description.getMethodName() + "; discarding as bogus.");
                return;
            }
            try {
                testFailureEventFromCachedDescription = new TestFailureEvent(ParcelableConverter.getTestCaseFromDescription(failure.getDescription()), ParcelableConverter.getFailure(failure));
            } catch (TestEventException e) {
                Log.d(TAG, "Unable to determine test case from failure [" + String.valueOf(failure) + "]", e);
                testFailureEventFromCachedDescription = getTestFailureEventFromCachedDescription(failure);
                if (testFailureEventFromCachedDescription == null) {
                    return;
                }
            }
            try {
                this.notificationService.send(testFailureEventFromCachedDescription);
            } catch (TestEventException e2) {
                throw new IllegalStateException("Unable to send TestFailureEvent, terminating", e2);
            }
        }
    }

    private TestFailureEvent getTestFailureEventFromCachedDescription(Failure failure) {
        Checks.checkNotNull(failure, "failure cannot be null");
        try {
            TestCaseInfo testCaseFromDescription = ParcelableConverter.getTestCaseFromDescription(this.description);
            return new TestFailureEvent(testCaseFromDescription, new FailureInfo(failure.getMessage(), failure.getTestHeader(), failure.getTrace(), testCaseFromDescription));
        } catch (TestEventException e) {
            Log.e(TAG, "Unable to determine test case from description [" + String.valueOf(this.description) + "]", e);
            return null;
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testAssumptionFailure(Failure failure) {
        try {
            this.notificationService.send(new TestAssumptionFailureEvent(ParcelableConverter.getTestCaseFromDescription(failure.getDescription()), ParcelableConverter.getFailure(failure)));
        } catch (TestEventException e) {
            Log.e(TAG, "Unable to send TestAssumptionFailureEvent to Orchestrator", e);
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testIgnored(Description description) {
        try {
            TestCaseInfo testCaseFromDescription = ParcelableConverter.getTestCaseFromDescription(description);
            Log.i(TAG, "TestIgnoredEvent(" + description.getDisplayName() + "): " + description.getClassName() + "#" + description.getMethodName() + " = " + testCaseFromDescription.getClassAndMethodName());
            this.notificationService.send(new TestIgnoredEvent(testCaseFromDescription));
        } catch (TestEventException e) {
            Log.e(TAG, "Unable to send TestIgnoredEvent to Orchestrator", e);
        }
    }

    public boolean reportProcessCrash(Throwable t, long timeoutMillis) {
        if (this.isTestFailed.get()) {
            return false;
        }
        Log.i(TAG, "No test failure has been reported. Report the process crash.");
        reportProcessCrash(t);
        return true;
    }

    private void reportProcessCrash(Throwable t) {
        testFailure(new Failure(this.description, t));
        testFinished(this.description);
    }
}
