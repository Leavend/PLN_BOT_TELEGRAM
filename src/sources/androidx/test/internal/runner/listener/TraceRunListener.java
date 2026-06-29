package androidx.test.internal.runner.listener;

import android.util.Log;
import androidx.tracing.Trace;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public class TraceRunListener extends RunListener {
    private Thread startedThread = null;

    @Override // org.junit.runner.notification.RunListener
    public void testStarted(Description description) throws Exception {
        this.startedThread = Thread.currentThread();
        Trace.beginSection((description.getTestClass() != null ? description.getTestClass().getSimpleName() : "None") + "#" + (description.getMethodName() != null ? description.getMethodName() : "None"));
    }

    @Override // org.junit.runner.notification.RunListener
    public void testFinished(Description description) throws Exception {
        if (Thread.currentThread().equals(this.startedThread)) {
            Trace.endSection();
        } else {
            Log.e("TraceRunListener", "testFinished called on different thread than testStarted");
        }
        this.startedThread = null;
    }
}
