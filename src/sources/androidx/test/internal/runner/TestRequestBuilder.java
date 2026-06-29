package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.test.filters.AbstractFilter;
import androidx.test.filters.CustomFilter;
import androidx.test.filters.RequiresDevice;
import androidx.test.filters.SdkSuppress;
import androidx.test.filters.Suppress;
import androidx.test.internal.runner.ClassPathScanner;
import androidx.test.internal.runner.RunnerArgs;
import androidx.test.internal.runner.TestLoader;
import androidx.test.internal.runner.filters.TestsRegExFilter;
import androidx.test.internal.util.AndroidRunnerParams;
import androidx.test.internal.util.Checks;
import androidx.tracing.Trace;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.runner.Description;
import org.junit.runner.Request;
import org.junit.runner.Runner;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.manipulation.NoTestsRemainException;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
public class TestRequestBuilder {
    static final String AMBIGUOUS_ARGUMENTS_MSG = "Ambiguous arguments: cannot provide both test package and test class(es) to run";
    static final String MISSING_ARGUMENTS_MSG = "Must provide either classes to run, or paths to scan";
    private static final String TAG = "TestRequestBuilder";
    private final Bundle argsBundle;
    private ClassLoader classLoader;
    private ClassAndMethodFilter classMethodFilter;
    private List<Class<? extends RunnerBuilder>> customRunnerBuilderClasses;
    private final DeviceBuild deviceBuild;
    private Set<String> excludedClasses;
    private Set<String> excludedPackages;
    private Filter filter;
    private boolean ignoreSuiteMethods;
    private Set<String> includedClasses;
    private Set<String> includedPackages;
    private final Instrumentation instr;
    private final List<String> pathsToScan;
    private long perTestTimeout;
    private boolean skipExecution;
    private final TestsRegExFilter testsRegExFilter;

    interface DeviceBuild {
        String getCodeName();

        String getHardware();

        int getSdkVersionInt();
    }

    private static class DeviceBuildImpl implements DeviceBuild {
        private DeviceBuildImpl() {
        }

        @Override // androidx.test.internal.runner.TestRequestBuilder.DeviceBuild
        public int getSdkVersionInt() {
            return Build.VERSION.SDK_INT;
        }

        @Override // androidx.test.internal.runner.TestRequestBuilder.DeviceBuild
        public String getHardware() {
            return Build.HARDWARE;
        }

        @Override // androidx.test.internal.runner.TestRequestBuilder.DeviceBuild
        public String getCodeName() {
            return Build.VERSION.CODENAME;
        }
    }

    private static class AnnotationInclusionFilter extends AbstractFilter {
        private final Class<? extends Annotation> annotationClass;

        AnnotationInclusionFilter(Class<? extends Annotation> annotation) {
            this.annotationClass = annotation;
        }

        @Override // androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            Class<?> testClass = description.getTestClass();
            return description.getAnnotation(this.annotationClass) != null || (testClass != null && testClass.isAnnotationPresent(this.annotationClass));
        }

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return String.format("annotation %s", this.annotationClass.getName());
        }
    }

    private static class SizeFilter extends AbstractFilter {
        private final TestSize testSize;

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return "";
        }

        SizeFilter(TestSize testSize) {
            this.testSize = testSize;
        }

        @Override // androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            if (this.testSize.testMethodIsAnnotatedWithTestSize(description)) {
                return true;
            }
            if (!this.testSize.testClassIsAnnotatedWithTestSize(description)) {
                return false;
            }
            Iterator<Annotation> it = description.getAnnotations().iterator();
            while (it.hasNext()) {
                if (TestSize.isAnyTestSize(it.next().annotationType())) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class AnnotationExclusionFilter extends AbstractFilter {
        private final Class<? extends Annotation> annotationClass;

        AnnotationExclusionFilter(Class<? extends Annotation> annotation) {
            this.annotationClass = annotation;
        }

        @Override // androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            Class<?> testClass = description.getTestClass();
            return (testClass == null || !testClass.isAnnotationPresent(this.annotationClass)) && description.getAnnotation(this.annotationClass) == null;
        }

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return String.format("not annotation %s", this.annotationClass.getName());
        }
    }

    private static class ExtendedSuite extends Suite {
        static Suite createSuite(List<Runner> runners) {
            try {
                return new ExtendedSuite(runners);
            } catch (InitializationError e) {
                throw new RuntimeException("Internal Error: " + Suite.class.getName() + "(Class<?>, List<Runner>) should never throw an InitializationError when passed a null Class", e);
            }
        }

        ExtendedSuite(List<Runner> runners) throws InitializationError {
            super((Class<?>) null, runners);
        }
    }

    private static class CustomFilters extends AbstractFilter {
        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return "skip tests annotated with custom filters if necessary";
        }

        private CustomFilters() {
        }

        @Override // androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            Iterator<Annotation> it = description.getAnnotations().iterator();
            while (it.hasNext()) {
                CustomFilter customFilter = (CustomFilter) it.next().annotationType().getAnnotation(CustomFilter.class);
                if (customFilter != null) {
                    Class<? extends AbstractFilter> clsFilterClass = customFilter.filterClass();
                    try {
                        if (!clsFilterClass.getConstructor(new Class[0]).newInstance(new Object[0]).shouldRun(description)) {
                            return false;
                        }
                    } catch (ClassCastException e) {
                        throw new IllegalArgumentException(clsFilterClass.getName() + " does not extend androidx.test.filters.AbstractFilter", e);
                    } catch (IllegalAccessException e2) {
                        e = e2;
                        throw new IllegalArgumentException("Failed to create: " + clsFilterClass.getName(), e);
                    } catch (InstantiationException e3) {
                        e = e3;
                        throw new IllegalArgumentException("Failed to create: " + clsFilterClass.getName(), e);
                    } catch (NoSuchMethodException e4) {
                        throw new IllegalArgumentException("Must have no argument constructor for class " + clsFilterClass.getName(), e4);
                    } catch (InvocationTargetException e5) {
                        e = e5;
                        throw new IllegalArgumentException("Failed to create: " + clsFilterClass.getName(), e);
                    }
                }
            }
            return true;
        }
    }

    private class SdkSuppressFilter extends AbstractFilter {
        private SdkSuppressFilter() {
        }

        @Override // androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            SdkSuppress annotationForTest = getAnnotationForTest(description);
            if (annotationForTest != null) {
                return (TestRequestBuilder.this.getDeviceSdkInt() >= annotationForTest.minSdkVersion() && TestRequestBuilder.this.getDeviceSdkInt() <= annotationForTest.maxSdkVersion()) || TestRequestBuilder.this.getDeviceCodeName().equals(annotationForTest.codeName());
            }
            return true;
        }

        private SdkSuppress getAnnotationForTest(Description description) {
            SdkSuppress sdkSuppress = (SdkSuppress) description.getAnnotation(SdkSuppress.class);
            if (sdkSuppress != null) {
                return sdkSuppress;
            }
            Class<?> testClass = description.getTestClass();
            if (testClass != null) {
                return (SdkSuppress) testClass.getAnnotation(SdkSuppress.class);
            }
            return null;
        }

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return String.format("skip tests annotated with SdkSuppress if necessary", new Object[0]);
        }
    }

    class RequiresDeviceFilter extends AnnotationExclusionFilter {
        static final String EMULATOR_HARDWARE_GCE = "gce_x86";
        static final String EMULATOR_HARDWARE_GOLDFISH = "goldfish";
        static final String EMULATOR_HARDWARE_RANCHU = "ranchu";
        private final Set<String> emulatorHardwareNames;

        RequiresDeviceFilter() {
            super(RequiresDevice.class);
            this.emulatorHardwareNames = new HashSet(Arrays.asList(EMULATOR_HARDWARE_GOLDFISH, EMULATOR_HARDWARE_RANCHU, EMULATOR_HARDWARE_GCE));
        }

        @Override // androidx.test.internal.runner.TestRequestBuilder.AnnotationExclusionFilter, androidx.test.filters.AbstractFilter
        protected boolean evaluateTest(Description description) {
            if (super.evaluateTest(description)) {
                return true;
            }
            return !this.emulatorHardwareNames.contains(TestRequestBuilder.this.getDeviceHardware());
        }

        @Override // androidx.test.internal.runner.TestRequestBuilder.AnnotationExclusionFilter, org.junit.runner.manipulation.Filter
        public String describe() {
            return String.format("skip tests annotated with RequiresDevice if necessary", new Object[0]);
        }
    }

    private static class ShardingFilter extends Filter {
        private final int numShards;
        private final int shardIndex;

        ShardingFilter(int numShards, int shardIndex) {
            this.numShards = numShards;
            this.shardIndex = shardIndex;
        }

        @Override // org.junit.runner.manipulation.Filter
        public boolean shouldRun(Description description) {
            return !description.isTest() || Math.abs(description.hashCode()) % this.numShards == this.shardIndex;
        }

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return String.format("Shard %s of %s shards", Integer.valueOf(this.shardIndex), Integer.valueOf(this.numShards));
        }
    }

    private static class LenientFilterRequest extends Request {
        private final Filter filter;
        private final Request request;

        public LenientFilterRequest(Request classRequest, Filter filter) {
            this.request = classRequest;
            this.filter = filter;
        }

        @Override // org.junit.runner.Request
        public Runner getRunner() {
            try {
                Runner runner = this.request.getRunner();
                this.filter.apply(runner);
                return runner;
            } catch (NoTestsRemainException unused) {
                return new BlankRunner();
            }
        }
    }

    private static class BlankRunner extends Runner {
        @Override // org.junit.runner.Runner
        public void run(RunNotifier notifier) {
        }

        private BlankRunner() {
        }

        @Override // org.junit.runner.Runner, org.junit.runner.Describable
        public Description getDescription() {
            return Description.createSuiteDescription("no tests found", new Annotation[0]);
        }
    }

    private static class ClassAndMethodFilter extends AbstractFilter {
        private Map<String, MethodFilter> methodFilters;

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return "Class and method filter";
        }

        private ClassAndMethodFilter() {
            this.methodFilters = new HashMap();
        }

        @Override // androidx.test.filters.AbstractFilter
        public boolean evaluateTest(Description description) {
            if (this.methodFilters.isEmpty()) {
                return true;
            }
            MethodFilter methodFilter = this.methodFilters.get(description.getClassName());
            if (methodFilter != null) {
                return methodFilter.shouldRun(description);
            }
            return true;
        }

        public void addMethod(String className, String methodName) {
            MethodFilter methodFilter = this.methodFilters.get(className);
            if (methodFilter == null) {
                methodFilter = new MethodFilter(className);
                this.methodFilters.put(className, methodFilter);
            }
            methodFilter.addInclusionMethod(methodName);
        }

        public void removeMethod(String className, String methodName) {
            MethodFilter methodFilter = this.methodFilters.get(className);
            if (methodFilter == null) {
                methodFilter = new MethodFilter(className);
                this.methodFilters.put(className, methodFilter);
            }
            methodFilter.addExclusionMethod(methodName);
        }
    }

    private static class MethodFilter extends AbstractFilter {
        private final String className;
        private Set<String> includedMethods = new HashSet();
        private Set<String> excludedMethods = new HashSet();

        public MethodFilter(String className) {
            this.className = className;
        }

        @Override // org.junit.runner.manipulation.Filter
        public String describe() {
            return "Method filter for " + this.className + " class";
        }

        @Override // androidx.test.filters.AbstractFilter
        public boolean evaluateTest(Description description) {
            String methodName = description.getMethodName();
            if (methodName == null) {
                return false;
            }
            String strStripParameterizedSuffix = stripParameterizedSuffix(methodName);
            if (this.excludedMethods.contains(methodName) || this.excludedMethods.contains(strStripParameterizedSuffix)) {
                return false;
            }
            return this.includedMethods.isEmpty() || this.includedMethods.contains(methodName) || this.includedMethods.contains(strStripParameterizedSuffix) || methodName.equals("initializationError");
        }

        private String stripParameterizedSuffix(String name) {
            return Pattern.compile(".+(\\[[0-9]+\\])$").matcher(name).matches() ? name.substring(0, name.lastIndexOf(91)) : name;
        }

        public void addInclusionMethod(String methodName) {
            this.includedMethods.add(methodName);
        }

        public void addExclusionMethod(String methodName) {
            this.excludedMethods.add(methodName);
        }
    }

    public TestRequestBuilder(Instrumentation instr, Bundle bundle) {
        this(new DeviceBuildImpl(), instr, bundle);
    }

    /* JADX WARN: Multi-variable type inference failed */
    TestRequestBuilder(DeviceBuild deviceBuild, Instrumentation instrumentation, Bundle bundle) throws ClassNotFoundException {
        this.pathsToScan = new ArrayList();
        this.includedPackages = new HashSet();
        this.excludedPackages = new HashSet();
        this.includedClasses = new HashSet();
        this.excludedClasses = new HashSet();
        this.classMethodFilter = new ClassAndMethodFilter();
        TestsRegExFilter testsRegExFilter = new TestsRegExFilter();
        this.testsRegExFilter = testsRegExFilter;
        this.filter = new AnnotationExclusionFilter(Suppress.class).intersect(new SdkSuppressFilter()).intersect(new RequiresDeviceFilter()).intersect(this.classMethodFilter).intersect(testsRegExFilter).intersect(new CustomFilters());
        this.customRunnerBuilderClasses = new ArrayList();
        this.skipExecution = false;
        this.perTestTimeout = 0L;
        this.ignoreSuiteMethods = false;
        this.deviceBuild = (DeviceBuild) Checks.checkNotNull(deviceBuild);
        this.instr = (Instrumentation) Checks.checkNotNull(instrumentation);
        this.argsBundle = (Bundle) Checks.checkNotNull(bundle);
        maybeAddLegacySuppressFilter();
    }

    private void maybeAddLegacySuppressFilter() throws ClassNotFoundException {
        try {
            this.filter = this.filter.intersect(new AnnotationExclusionFilter(Class.forName("android.test.suitebuilder.annotation.Suppress")));
        } catch (ClassNotFoundException unused) {
        }
    }

    public TestRequestBuilder addPathsToScan(Iterable<String> paths) {
        Iterator<String> it = paths.iterator();
        while (it.hasNext()) {
            addPathToScan(it.next());
        }
        return this;
    }

    public TestRequestBuilder addPathToScan(String path) {
        this.pathsToScan.add(path);
        return this;
    }

    public TestRequestBuilder setClassLoader(ClassLoader loader) {
        this.classLoader = loader;
        return this;
    }

    public TestRequestBuilder ignoreSuiteMethods(boolean ignoreSuiteMethods) {
        this.ignoreSuiteMethods = ignoreSuiteMethods;
        return this;
    }

    public TestRequestBuilder addTestClass(String className) {
        this.includedClasses.add(className);
        return this;
    }

    public TestRequestBuilder removeTestClass(String className) {
        this.excludedClasses.add(className);
        return this;
    }

    public TestRequestBuilder addTestMethod(String testClassName, String testMethodName) {
        this.includedClasses.add(testClassName);
        this.classMethodFilter.addMethod(testClassName, testMethodName);
        return this;
    }

    public TestRequestBuilder removeTestMethod(String testClassName, String testMethodName) {
        this.classMethodFilter.removeMethod(testClassName, testMethodName);
        return this;
    }

    public TestRequestBuilder addTestPackage(String testPackage) {
        this.includedPackages.add(testPackage);
        return this;
    }

    public TestRequestBuilder removeTestPackage(String testPackage) {
        this.excludedPackages.add(testPackage);
        return this;
    }

    public TestRequestBuilder setTestsRegExFilter(String testsRegex) {
        this.testsRegExFilter.setPattern(testsRegex);
        return this;
    }

    public TestRequestBuilder addTestSizeFilter(TestSize forTestSize) {
        if (!TestSize.NONE.equals(forTestSize)) {
            addFilter(new SizeFilter(forTestSize));
        } else {
            Log.e(TAG, String.format("Unrecognized test size '%s'", forTestSize.getSizeQualifierName()));
        }
        return this;
    }

    public TestRequestBuilder addAnnotationInclusionFilter(String annotation) {
        Class<? extends Annotation> clsLoadAnnotationClass = loadAnnotationClass(annotation);
        if (clsLoadAnnotationClass != null) {
            addFilter(new AnnotationInclusionFilter(clsLoadAnnotationClass));
        }
        return this;
    }

    public TestRequestBuilder addAnnotationExclusionFilter(String notAnnotation) {
        Class<? extends Annotation> clsLoadAnnotationClass = loadAnnotationClass(notAnnotation);
        if (clsLoadAnnotationClass != null) {
            addFilter(new AnnotationExclusionFilter(clsLoadAnnotationClass));
        }
        return this;
    }

    public TestRequestBuilder addShardingFilter(int numShards, int shardIndex) {
        return addFilter(new ShardingFilter(numShards, shardIndex));
    }

    public TestRequestBuilder addFilter(Filter filter) {
        this.filter = this.filter.intersect(filter);
        return this;
    }

    public TestRequestBuilder addCustomRunnerBuilderClass(Class<? extends RunnerBuilder> runnerBuilderClass) {
        this.customRunnerBuilderClasses.add(runnerBuilderClass);
        return this;
    }

    public TestRequestBuilder setSkipExecution(boolean b) {
        this.skipExecution = b;
        return this;
    }

    public TestRequestBuilder setPerTestTimeout(long millis) {
        this.perTestTimeout = millis;
        return this;
    }

    public TestRequestBuilder addFromRunnerArgs(RunnerArgs runnerArgs) {
        for (RunnerArgs.TestArg testArg : runnerArgs.tests) {
            if (testArg.methodName == null) {
                addTestClass(testArg.testClassName);
            } else {
                addTestMethod(testArg.testClassName, testArg.methodName);
            }
        }
        for (RunnerArgs.TestArg testArg2 : runnerArgs.notTests) {
            if (testArg2.methodName == null) {
                removeTestClass(testArg2.testClassName);
            } else {
                removeTestMethod(testArg2.testClassName, testArg2.methodName);
            }
        }
        Iterator<String> it = runnerArgs.testPackages.iterator();
        while (it.hasNext()) {
            addTestPackage(it.next());
        }
        Iterator<String> it2 = runnerArgs.notTestPackages.iterator();
        while (it2.hasNext()) {
            removeTestPackage(it2.next());
        }
        if (runnerArgs.testSize != null) {
            addTestSizeFilter(TestSize.fromString(runnerArgs.testSize));
        }
        Iterator<String> it3 = runnerArgs.annotations.iterator();
        while (it3.hasNext()) {
            addAnnotationInclusionFilter(it3.next());
        }
        Iterator<String> it4 = runnerArgs.notAnnotations.iterator();
        while (it4.hasNext()) {
            addAnnotationExclusionFilter(it4.next());
        }
        Iterator<Filter> it5 = runnerArgs.filters.iterator();
        while (it5.hasNext()) {
            addFilter(it5.next());
        }
        if (runnerArgs.testTimeout > 0) {
            setPerTestTimeout(runnerArgs.testTimeout);
        }
        if (runnerArgs.numShards > 0 && runnerArgs.shardIndex >= 0 && runnerArgs.shardIndex < runnerArgs.numShards) {
            addShardingFilter(runnerArgs.numShards, runnerArgs.shardIndex);
        }
        if (runnerArgs.logOnly || runnerArgs.listTestsForOrchestrator) {
            setSkipExecution(true);
        }
        if (runnerArgs.classLoader != null) {
            setClassLoader(runnerArgs.classLoader);
        }
        Iterator<Class<? extends RunnerBuilder>> it6 = runnerArgs.runnerBuilderClasses.iterator();
        while (it6.hasNext()) {
            addCustomRunnerBuilderClass(it6.next());
        }
        if (runnerArgs.testsRegEx != null) {
            setTestsRegExFilter(runnerArgs.testsRegEx);
        }
        return this;
    }

    public Request build() {
        Collection<String> classNamesFromClassPath;
        Trace.beginSection("build test request");
        try {
            this.includedPackages.removeAll(this.excludedPackages);
            this.includedClasses.removeAll(this.excludedClasses);
            validate(this.includedClasses);
            boolean zIsEmpty = this.includedClasses.isEmpty();
            TestLoader testLoaderCreate = TestLoader.Factory.create(this.classLoader, getRunnerBuilder(new AndroidRunnerParams(this.instr, this.argsBundle, this.perTestTimeout, this.ignoreSuiteMethods || zIsEmpty)), zIsEmpty);
            if (zIsEmpty) {
                Log.d(TAG, "Using class path scanning to discover tests");
                classNamesFromClassPath = getClassNamesFromClassPath();
            } else {
                Log.d(TAG, String.format("Skipping class path scanning and directly running %s", this.includedClasses));
                classNamesFromClassPath = this.includedClasses;
            }
            return new LenientFilterRequest(Request.runner(ExtendedSuite.createSuite(testLoaderCreate.getRunnersFor(classNamesFromClassPath))), this.filter);
        } finally {
            Trace.endSection();
        }
    }

    private void validate(Set<String> classNames) {
        if (classNames.isEmpty() && this.pathsToScan.isEmpty()) {
            throw new IllegalArgumentException(MISSING_ARGUMENTS_MSG);
        }
    }

    private RunnerBuilder getRunnerBuilder(AndroidRunnerParams runnerParams) {
        if (this.skipExecution) {
            return new AndroidLogOnlyBuilder(runnerParams, this.customRunnerBuilderClasses);
        }
        return new AndroidRunnerBuilder(runnerParams, this.customRunnerBuilderClasses);
    }

    private Collection<String> getClassNamesFromClassPath() {
        if (this.pathsToScan.isEmpty()) {
            throw new IllegalStateException("neither test class to execute or class paths were provided");
        }
        Log.i(TAG, String.format("Scanning classpath to find tests in paths %s", this.pathsToScan));
        ClassPathScanner classPathScannerCreateClassPathScanner = createClassPathScanner(this.pathsToScan);
        ClassPathScanner.ChainedClassNameFilter chainedClassNameFilter = new ClassPathScanner.ChainedClassNameFilter();
        chainedClassNameFilter.add(new ClassPathScanner.ExternalClassNameFilter());
        for (String str : ClassPathScanner.getDefaultExcludedPackages()) {
            if (!this.includedPackages.contains(str)) {
                this.excludedPackages.add(str);
            }
        }
        if (!this.includedPackages.isEmpty()) {
            chainedClassNameFilter.add(new ClassPathScanner.InclusivePackageNamesFilter(this.includedPackages));
        }
        Iterator<String> it = this.excludedPackages.iterator();
        while (it.hasNext()) {
            chainedClassNameFilter.add(new ClassPathScanner.ExcludePackageNameFilter(it.next()));
        }
        chainedClassNameFilter.add(new ClassPathScanner.ExcludeClassNamesFilter(this.excludedClasses));
        try {
            return classPathScannerCreateClassPathScanner.getClassPathEntries(chainedClassNameFilter);
        } catch (IOException e) {
            Log.e(TAG, "Failed to scan classes", e);
            return Collections.emptyList();
        }
    }

    ClassPathScanner createClassPathScanner(List<String> classPath) {
        return new ClassPathScanner(classPath);
    }

    private Class<? extends Annotation> loadAnnotationClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassCastException unused) {
            Log.e(TAG, String.format("Class %s is not an annotation", className));
            return null;
        } catch (ClassNotFoundException unused2) {
            Log.e(TAG, String.format("Could not find annotation class: %s", className));
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getDeviceSdkInt() {
        return this.deviceBuild.getSdkVersionInt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDeviceHardware() {
        return this.deviceBuild.getHardware();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDeviceCodeName() {
        return this.deviceBuild.getCodeName();
    }
}
