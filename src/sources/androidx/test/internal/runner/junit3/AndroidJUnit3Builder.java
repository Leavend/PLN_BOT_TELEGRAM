package androidx.test.internal.runner.junit3;

import android.util.Log;
import androidx.test.internal.runner.EmptyTestRunner;
import androidx.test.internal.util.AndroidRunnerBuilderUtil;
import androidx.test.internal.util.AndroidRunnerParams;
import org.junit.internal.builders.JUnit3Builder;
import org.junit.runner.Runner;

/* loaded from: classes5.dex */
public class AndroidJUnit3Builder extends JUnit3Builder {
    private static final String TAG = "AndroidJUnit3Builder";
    private final AndroidRunnerParams androidRunnerParams;

    public AndroidJUnit3Builder(AndroidRunnerParams runnerParams) {
        this.androidRunnerParams = runnerParams;
    }

    @Override // org.junit.internal.builders.JUnit3Builder, org.junit.runners.model.RunnerBuilder
    public Runner runnerForClass(Class<?> testClass) throws Throwable {
        try {
            if (!AndroidRunnerBuilderUtil.isJUnit3Test(testClass)) {
                return null;
            }
            if (!AndroidRunnerBuilderUtil.hasJUnit3TestMethod(testClass)) {
                return new EmptyTestRunner(testClass);
            }
            return new JUnit38ClassRunner(new AndroidTestSuite(testClass, this.androidRunnerParams));
        } catch (Throwable th) {
            Log.e(TAG, "Error constructing runner", th);
            throw th;
        }
    }
}
