package androidx.test.internal.runner.listener;

import android.util.Log;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public class LogRunListener extends RunListener {
    private static final String TAG = "TestRunner";

    @Override // org.junit.runner.notification.RunListener
    public void testRunStarted(Description description) throws Exception {
        Log.i(TAG, String.format("run started: %d tests", Integer.valueOf(description.testCount())));
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunFinished(Result result) throws Exception {
        Log.i(TAG, String.format("run finished: %d tests, %d failed, %d ignored", Integer.valueOf(result.getRunCount()), Integer.valueOf(result.getFailureCount()), Integer.valueOf(result.getIgnoreCount())));
    }

    @Override // org.junit.runner.notification.RunListener
    public void testStarted(Description description) throws Exception {
        Log.i(TAG, "started: " + description.getDisplayName());
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFinished(Description description) throws Exception {
        Log.i(TAG, "finished: " + description.getDisplayName());
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) throws Exception {
        Log.e(TAG, "failed: " + failure.getDescription().getDisplayName());
        Log.e(TAG, "----- begin exception -----");
        Log.e(TAG, failure.getTrace());
        Log.e(TAG, "----- end exception -----");
    }

    @Override // org.junit.runner.notification.RunListener
    public void testAssumptionFailure(Failure failure) {
        Log.e(TAG, "assumption failed: " + failure.getDescription().getDisplayName());
        Log.e(TAG, "----- begin exception -----");
        Log.e(TAG, failure.getTrace());
        Log.e(TAG, "----- end exception -----");
    }

    @Override // org.junit.runner.notification.RunListener
    public void testIgnored(Description description) throws Exception {
        Log.i(TAG, "ignored: " + description.getDisplayName());
    }
}
