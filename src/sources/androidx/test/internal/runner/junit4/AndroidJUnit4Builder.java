package androidx.test.internal.runner.junit4;

import android.util.Log;
import androidx.test.internal.runner.EmptyTestRunner;
import androidx.test.internal.util.AndroidRunnerParams;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.runner.Runner;

/* loaded from: classes5.dex */
public class AndroidJUnit4Builder extends JUnit4Builder {
    private static final String TAG = "AndroidJUnit4Builder";
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidJUnit4Builder(AndroidRunnerParams runnerParams) {
        this.androidRunnerParams = runnerParams;
    }

    @Override // org.junit.internal.builders.JUnit4Builder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        try {
            if (!hasTestMethods(testClass)) {
                return new EmptyTestRunner(testClass);
            }
            return new AndroidJUnit4ClassRunner(testClass, this.androidRunnerParams);
        } catch (Throwable th) {
            Log.e(TAG, "Error constructing runner", th);
            throw th;
        }
    }

    private static boolean hasTestMethods(Class<?> testClass) {
        try {
            for (Method method : testClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            Log.w(TAG, String.format("%s in hasTestMethods for %s", th.toString(), testClass.getName()));
            return false;
        }
    }
}
