package androidx.test.internal.runner;

import android.util.Log;
import java.lang.reflect.Modifier;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
class ScanningTestLoader extends TestLoader {
    private static final String LOG_TAG = "ScanningTestLoader";
    private final ClassLoader classLoader;
    private final RunnerBuilder runnerBuilder;

    ScanningTestLoader(ClassLoader classLoader, RunnerBuilder runnerBuilder) {
        this.classLoader = classLoader;
        this.runnerBuilder = runnerBuilder;
    }

    @Override // androidx.test.internal.runner.TestLoader
    protected Runner doCreateRunner(String className) {
        try {
            Class<?> cls = Class.forName(className, false, this.classLoader);
            if (Modifier.isAbstract(cls.getModifiers())) {
                logDebug("Skipping abstract class %s: not a test", cls.getName());
                return null;
            }
            Runner runnerRunnerForClass = this.runnerBuilder.runnerForClass(cls);
            if (!(runnerRunnerForClass instanceof EmptyTestRunner)) {
                return runnerRunnerForClass;
            }
            logDebug("Skipping class %s: class with no test methods", cls.getName());
            return null;
        } catch (Throwable th) {
            Log.w(LOG_TAG, String.format("Could not load class: %s", className), th);
            return null;
        }
    }

    private static void logDebug(String msg, Object... objects) {
        if (Log.isLoggable(LOG_TAG, 3)) {
            Log.d(LOG_TAG, String.format(msg, objects));
        }
    }
}
