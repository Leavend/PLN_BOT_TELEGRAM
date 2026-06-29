package androidx.test.runner.suites;

import androidx.test.internal.runner.ClassPathScanner;
import androidx.test.internal.runner.ErrorReportingRunner;
import androidx.test.internal.runner.TestLoader;
import androidx.test.platform.app.InstrumentationRegistry;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

@RunWith(RunnerSuite.class)
/* loaded from: classes5.dex */
public final class AndroidClasspathSuite {

    public static class RunnerSuite extends Suite {
        public RunnerSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
            super(klass, getRunnersForClasses(builder));
        }

        private static List<Runner> getRunnersForClasses(RunnerBuilder builder) {
            try {
                return TestLoader.Factory.create(null, builder, true).getRunnersFor(new ClassPathScanner(ClassPathScanner.getDefaultClasspaths(InstrumentationRegistry.getInstrumentation())).getClassPathEntries());
            } catch (IOException e) {
                return Arrays.asList(new ErrorReportingRunner(InstrumentationRegistry.getInstrumentation().getContext().getPackageName(), new RuntimeException("Failed to perform classpath scanning to determine tests to run", e)));
            }
        }
    }
}
