package androidx.test.runner.suites;

import androidx.test.internal.runner.ClassPathScanner;
import androidx.test.internal.runner.ErrorReportingRunner;
import androidx.test.internal.runner.TestLoader;
import androidx.test.platform.app.InstrumentationRegistry;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.junit.runner.Runner;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
public final class PackagePrefixClasspathSuite extends Suite {
    public PackagePrefixClasspathSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, getRunnersForClasses(klass, builder));
    }

    private static List<Runner> getRunnersForClasses(Class<?> klass, RunnerBuilder builder) {
        try {
            ClassPathScanner.ChainedClassNameFilter chainedClassNameFilter = new ClassPathScanner.ChainedClassNameFilter();
            chainedClassNameFilter.add(new ClassPathScanner.InclusivePackageNamesFilter(Arrays.asList(klass.getPackage().getName())));
            chainedClassNameFilter.add(new ClassPathScanner.ExternalClassNameFilter());
            Set<String> classPathEntries = new ClassPathScanner(ClassPathScanner.getDefaultClasspaths(InstrumentationRegistry.getInstrumentation())).getClassPathEntries(chainedClassNameFilter);
            classPathEntries.remove(klass.getName());
            return TestLoader.Factory.create(null, builder, true).getRunnersFor(classPathEntries);
        } catch (IOException e) {
            return Arrays.asList(new ErrorReportingRunner(InstrumentationRegistry.getInstrumentation().getContext().getPackageName(), new RuntimeException("Failed to perform classpath scanning to determine tests to run", e)));
        }
    }
}
