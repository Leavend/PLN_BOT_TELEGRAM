package androidx.test.internal.runner;

import android.util.Log;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
class DirectTestLoader extends TestLoader {
    private static final String LOG_TAG = "DirectTestLoader";
    private final ClassLoader classLoader;
    private final RunnerBuilder runnerBuilder;

    DirectTestLoader(ClassLoader classLoader, RunnerBuilder runnerBuilder) {
        this.classLoader = classLoader;
        this.runnerBuilder = runnerBuilder;
    }

    @Override // androidx.test.internal.runner.TestLoader
    protected Runner doCreateRunner(String className) throws ClassNotFoundException {
        try {
            return this.runnerBuilder.safeRunnerForClass(Class.forName(className, false, this.classLoader));
        } catch (ClassNotFoundException | LinkageError e) {
            String str = String.format("Failed loading specified test class '%s'", className);
            Log.e(LOG_TAG, str, e);
            return new ErrorReportingRunner(className, new RuntimeException(str, e));
        }
    }
}
