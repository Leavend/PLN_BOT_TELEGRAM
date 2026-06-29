package androidx.test.internal.events.client;

import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.ErrorInfo;
import androidx.test.services.events.ParcelableConverter;
import androidx.test.services.events.TestEventException;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.discovery.TestDiscoveryErrorEvent;
import androidx.test.services.events.discovery.TestDiscoveryFinishedEvent;
import androidx.test.services.events.discovery.TestDiscoveryStartedEvent;
import androidx.test.services.events.discovery.TestFoundEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public final class TestDiscoveryListener extends RunListener {
    private static final String TAG = "TestDiscoveryListener";
    private final AtomicBoolean discoveryStarted = new AtomicBoolean(false);
    private final TestDiscoveryEventService testDiscoveryEventService;

    public TestDiscoveryListener(TestDiscoveryEventService testDiscoveryEventService) {
        this.testDiscoveryEventService = (TestDiscoveryEventService) Checks.checkNotNull(testDiscoveryEventService, "testDiscoveryEventService can't be null");
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunStarted(Description description) {
        try {
            reportTestRunStarted();
        } catch (TestEventClientException e) {
            Log.e(TAG, "Failed to send discovery started", e);
        }
    }

    private void reportTestRunStarted() throws TestEventClientException {
        if (this.discoveryStarted.getAndSet(true)) {
            return;
        }
        this.testDiscoveryEventService.send(new TestDiscoveryStartedEvent());
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunFinished(Result result) {
        try {
            this.testDiscoveryEventService.send(new TestDiscoveryFinishedEvent());
        } catch (TestEventClientException e) {
            Log.e(TAG, "Failed to send discovery started", e);
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFinished(Description description) {
        if (!JUnitValidator.validateDescription(description)) {
            Log.d(TAG, "JUnit reported " + description.getClassName() + "#" + description.getMethodName() + "; discarding as bogus.");
        } else {
            try {
                this.testDiscoveryEventService.send(new TestFoundEvent(ParcelableConverter.getTestCaseFromDescription(description)));
            } catch (TestEventException e) {
                Log.e(TAG, "Failed to get test description", e);
            }
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) {
        try {
            reportDiscoveryError(failure);
        } catch (TestEventClientException e) {
            Log.e(TAG, "Failed to send discovery failure", e);
        }
    }

    private void reportDiscoveryError(Failure failure) throws TestEventClientException {
        this.testDiscoveryEventService.send(new TestDiscoveryErrorEvent(ErrorInfo.createFromFailure(failure), TimeStamp.now()));
    }

    public boolean reportProcessCrash(Throwable t) {
        try {
            reportTestRunStarted();
            reportDiscoveryError(new Failure(Description.EMPTY, t));
            this.testDiscoveryEventService.send(new TestDiscoveryFinishedEvent());
            return true;
        } catch (TestEventClientException e) {
            Log.e(TAG, "Failed to report process crash error", e);
            return false;
        }
    }
}
