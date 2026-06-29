package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import androidx.test.internal.runner.listener.InstrumentationRunListener;
import androidx.test.internal.util.Checks;
import androidx.tracing.Trace;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public final class TestExecutor {
    private static final String LOG_TAG = "TestExecutor";
    private final Instrumentation instr;
    private final List<RunListener> listeners;

    private TestExecutor(Builder builder) {
        this.listeners = (List) Checks.checkNotNull(builder.listeners);
        this.instr = builder.instr;
    }

    public Bundle execute(Request request) throws UnsupportedEncodingException {
        Trace.beginSection("execute tests");
        try {
            return execute(new JUnitCore(), request);
        } finally {
            Trace.endSection();
        }
    }

    Bundle execute(JUnitCore junitRunner, Request request) throws IllegalAccessException, IllegalArgumentException, UnsupportedEncodingException, InvocationTargetException {
        Bundle bundle = new Bundle();
        setUpListeners(junitRunner);
        Result resultRun = junitRunner.run(request);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        try {
            reportRunEnded(this.listeners, printStream, bundle, resultRun);
            printStream.close();
            bundle.putString("stream", String.format("\n%s", byteArrayOutputStream.toString("UTF_8")));
            return bundle;
        } catch (Throwable th) {
            try {
                printStream.close();
            } catch (Throwable th2) {
                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
            }
            throw th;
        }
    }

    private void setUpListeners(JUnitCore testRunner) {
        for (RunListener runListener : this.listeners) {
            Log.d(LOG_TAG, "Adding listener " + runListener.getClass().getName());
            testRunner.addListener(runListener);
            if (runListener instanceof InstrumentationRunListener) {
                ((InstrumentationRunListener) runListener).setInstrumentation(this.instr);
            }
        }
    }

    private void reportRunEnded(List<RunListener> listeners, PrintStream summaryWriter, Bundle resultBundle, Result jUnitResults) {
        for (RunListener runListener : listeners) {
            if (runListener instanceof InstrumentationRunListener) {
                ((InstrumentationRunListener) runListener).instrumentationRunFinished(summaryWriter, resultBundle, jUnitResults);
            }
        }
    }

    public static class Builder {
        private final Instrumentation instr;
        private final List<RunListener> listeners = new ArrayList();

        public Builder(Instrumentation instr) {
            this.instr = instr;
        }

        public Builder addRunListener(RunListener listener) {
            this.listeners.add(listener);
            return this;
        }

        public TestExecutor build() {
            return new TestExecutor(this);
        }
    }
}
