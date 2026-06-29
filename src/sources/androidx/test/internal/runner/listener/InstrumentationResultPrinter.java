package androidx.test.internal.runner.listener;

import android.os.Bundle;
import android.util.Log;
import androidx.test.services.events.internal.StackTrimmer;
import com.google.firebase.database.DatabaseError;
import com.kdownloader.database.DownloadModel;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.internal.TextListener;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/* loaded from: classes5.dex */
public class InstrumentationResultPrinter extends InstrumentationRunListener {
    public static final String REPORT_KEY_NAME_CLASS = "class";
    public static final String REPORT_KEY_NAME_TEST = "test";
    public static final String REPORT_KEY_NUM_CURRENT = "current";
    public static final String REPORT_KEY_NUM_TOTAL = "numtests";
    public static final String REPORT_KEY_STACK = "stack";
    public static final String REPORT_VALUE_ID = "AndroidJUnitRunner";
    public static final int REPORT_VALUE_RESULT_ASSUMPTION_FAILURE = -4;

    @Deprecated
    public static final int REPORT_VALUE_RESULT_ERROR = -1;
    public static final int REPORT_VALUE_RESULT_FAILURE = -2;
    public static final int REPORT_VALUE_RESULT_IGNORED = -3;
    public static final int REPORT_VALUE_RESULT_OK = 0;
    public static final int REPORT_VALUE_RESULT_START = 1;
    private static final String TAG = "InstrumentationResultPrinter";
    private final Bundle resultTemplate;
    Bundle testResult;
    private final AtomicInteger testNum = new AtomicInteger(0);
    private Description description = Description.EMPTY;
    private int testResultCode = DatabaseError.UNKNOWN_ERROR;
    private String testClass = null;

    public InstrumentationResultPrinter() {
        Bundle bundle = new Bundle();
        this.resultTemplate = bundle;
        this.testResult = new Bundle(bundle);
    }

    @Override // org.junit.runner.notification.RunListener
    public void testRunStarted(Description description) throws Exception {
        this.resultTemplate.putString(DownloadModel.ID, REPORT_VALUE_ID);
        this.resultTemplate.putInt(REPORT_KEY_NUM_TOTAL, description.testCount());
    }

    @Override // org.junit.runner.notification.RunListener
    public void testStarted(Description description) throws Exception {
        this.testNum.incrementAndGet();
        this.description = description;
        String className = description.getClassName();
        String methodName = description.getMethodName();
        Bundle bundle = new Bundle(this.resultTemplate);
        this.testResult = bundle;
        bundle.putString(REPORT_KEY_NAME_CLASS, className);
        this.testResult.putString(REPORT_KEY_NAME_TEST, methodName);
        this.testResult.putInt(REPORT_KEY_NUM_CURRENT, this.testNum.get());
        if (className != null && !className.equals(this.testClass)) {
            this.testResult.putString("stream", String.format("\n%s:", className));
            this.testClass = className;
        } else {
            this.testResult.putString("stream", "");
        }
        sendStatus(1, this.testResult);
        this.testResultCode = 0;
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFinished(Description description) throws Exception {
        if (this.testResultCode == 0) {
            this.testResult.putString("stream", ".");
        }
        sendStatus(this.testResultCode, this.testResult);
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFailure(Failure failure) throws Exception {
        boolean z;
        if (isAnyTestStarted()) {
            z = false;
        } else {
            testStarted(failure.getDescription());
            z = true;
        }
        this.testResultCode = -2;
        reportFailure(failure);
        if (z) {
            testFinished(failure.getDescription());
        }
    }

    @Override // org.junit.runner.notification.RunListener
    public void testAssumptionFailure(Failure failure) {
        this.testResultCode = -4;
        this.testResult.putString(REPORT_KEY_STACK, failure.getTrace());
    }

    private void reportFailure(Failure failure) {
        String trimmedStackTrace = StackTrimmer.getTrimmedStackTrace(failure);
        this.testResult.putString(REPORT_KEY_STACK, trimmedStackTrace);
        this.testResult.putString("stream", String.format("\nError in %s:\n%s", failure.getDescription().getDisplayName(), trimmedStackTrace));
    }

    @Override // org.junit.runner.notification.RunListener
    public void testIgnored(Description description) throws Exception {
        testStarted(description);
        this.testResultCode = -3;
        testFinished(description);
    }

    public void reportProcessCrash(Throwable t) {
        try {
            this.testResultCode = -2;
            Failure failure = new Failure(this.description, t);
            this.testResult.putString(REPORT_KEY_STACK, failure.getTrace());
            this.testResult.putString("stream", String.format((isAnyTestStarted() ? "\nProcess crashed while executing " + this.description.getDisplayName() : "\nProcess crashed before executing the test(s)") + ":\n%s", failure.getTrace()));
            testFinished(this.description);
        } catch (Exception e) {
            Description description = this.description;
            if (description == null) {
                Log.e(TAG, "Failed to initialize test before process crash", e);
            } else {
                Log.e(TAG, "Failed to mark test " + description.getDisplayName() + " as finished after process crash", e);
            }
        }
    }

    @Override // androidx.test.internal.runner.listener.InstrumentationRunListener
    public void instrumentationRunFinished(PrintStream streamResult, Bundle resultBundle, Result junitResults) {
        new TextListener(streamResult).testRunFinished(junitResults);
    }

    private boolean isAnyTestStarted() {
        return this.testNum.get() > 0;
    }
}
