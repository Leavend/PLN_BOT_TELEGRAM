package androidx.test.internal.runner;

import android.app.Instrumentation;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.io.PlatformTestStorageRegistry;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;
import androidx.test.runner.screenshot.ScreenCaptureProcessor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.lingala.zip4j.util.InternalZipConstants;
import org.junit.runner.manipulation.Filter;
import org.junit.runner.notification.RunListener;
import org.junit.runners.model.RunnerBuilder;

/* loaded from: classes5.dex */
public class RunnerArgs {
    static final String ARGUMENT_ANNOTATION = "annotation";
    static final String ARGUMENT_APP_LISTENER = "appListener";
    static final String ARGUMENT_CLASSPATH_TO_SCAN = "classpathToScan";
    static final String ARGUMENT_CLASS_LOADER = "classLoader";
    static final String ARGUMENT_COVERAGE = "coverage";
    static final String ARGUMENT_COVERAGE_PATH = "coverageFile";
    static final String ARGUMENT_DEBUG = "debug";
    static final String ARGUMENT_DELAY_IN_MILLIS = "delay_msec";
    static final String ARGUMENT_DISABLE_ANALYTICS = "disableAnalytics";
    static final String ARGUMENT_FILTER = "filter";
    static final String ARGUMENT_LISTENER = "listener";
    static final String ARGUMENT_LIST_TESTS_FOR_ORCHESTRATOR = "listTestsForOrchestrator";
    static final String ARGUMENT_LOG_ONLY = "log";
    static final String ARGUMENT_NOT_ANNOTATION = "notAnnotation";
    static final String ARGUMENT_NOT_TEST_CLASS = "notClass";
    static final String ARGUMENT_NOT_TEST_FILE = "notTestFile";
    static final String ARGUMENT_NOT_TEST_PACKAGE = "notPackage";
    static final String ARGUMENT_NUM_SHARDS = "numShards";
    static final String ARGUMENT_ORCHESTRATOR_DISCOVERY_SERVICE = "testDiscoveryService";
    static final String ARGUMENT_ORCHESTRATOR_RUN_EVENTS_SERVICE = "testRunEventsService";
    static final String ARGUMENT_ORCHESTRATOR_SERVICE = "orchestratorService";
    static final String ARGUMENT_REMOTE_INIT_METHOD = "remoteMethod";
    static final String ARGUMENT_RUNNER_BUILDER = "runnerBuilder";
    static final String ARGUMENT_RUN_LISTENER_NEW_ORDER = "newRunListenerMode";
    static final String ARGUMENT_SCREENSHOT_PROCESSORS = "screenCaptureProcessors";
    static final String ARGUMENT_SHARD_INDEX = "shardIndex";
    static final String ARGUMENT_SHELL_EXEC_BINDER_KEY = "shellExecBinderKey";
    static final String ARGUMENT_SUITE_ASSIGNMENT = "suiteAssignment";
    static final String ARGUMENT_TARGET_PROCESS = "targetProcess";
    static final String ARGUMENT_TESTS_REGEX = "tests_regex";
    static final String ARGUMENT_TEST_CLASS = "class";
    static final String ARGUMENT_TEST_FILE = "testFile";
    static final String ARGUMENT_TEST_PACKAGE = "package";
    static final String ARGUMENT_TEST_PLATFORM_MIGRATION = "temporary_testPlatformMigration";
    static final String ARGUMENT_TEST_SIZE = "size";
    static final String ARGUMENT_TIMEOUT = "timeout_msec";
    static final String ARGUMENT_USE_TEST_STORAGE_SERVICE = "useTestStorageService";
    private static final String CLASSPATH_SEPARATOR = ":";
    private static final String CLASS_SEPARATOR = ",";
    private static final String LOG_TAG = "RunnerArgs";
    private static final char METHOD_SEPARATOR = '#';
    public final List<String> annotations;
    public final List<ApplicationLifecycleCallback> appListeners;
    public final ClassLoader classLoader;
    public final Set<String> classpathToScan;
    public final boolean codeCoverage;
    public final String codeCoveragePath;
    public final boolean debug;
    public final int delayInMillis;
    public final boolean disableAnalytics;
    public final List<Filter> filters;
    public final boolean listTestsForOrchestrator;
    public final List<RunListener> listeners;
    public final boolean logOnly;
    public final boolean newRunListenerMode;
    public final List<String> notAnnotations;
    public final List<String> notTestPackages;
    public final List<TestArg> notTests;
    public final int numShards;
    public final String orchestratorService;
    public final TestArg remoteMethod;
    public final List<Class<? extends RunnerBuilder>> runnerBuilderClasses;
    public final List<ScreenCaptureProcessor> screenCaptureProcessors;
    public final int shardIndex;
    public final String shellExecBinderKey;
    public final boolean suiteAssignment;
    public final String targetProcess;
    public final String testDiscoveryService;
    public final List<String> testPackages;
    public final boolean testPlatformMigration;
    public final String testRunEventsService;
    public final String testSize;
    public final long testTimeout;
    public final List<TestArg> tests;
    public final String testsRegEx;
    public final boolean useTestStorageService;

    public static class TestArg {
        public final String methodName;
        public final String testClassName;

        TestArg(String className, String methodName) {
            this.testClassName = className;
            this.methodName = methodName;
        }

        TestArg(String className) {
            this(className, null);
        }

        public String toString() {
            String str = this.methodName;
            if (str == null) {
                return this.testClassName;
            }
            return this.testClassName + "#" + str;
        }
    }

    private static final class TestFileArgs {
        private final List<String> packages;
        private final List<TestArg> tests;

        private TestFileArgs() {
            this.tests = new ArrayList();
            this.packages = new ArrayList();
        }
    }

    private RunnerArgs(Builder builder) {
        this.debug = builder.debug;
        this.suiteAssignment = builder.suiteAssignment;
        this.codeCoverage = builder.codeCoverage;
        this.codeCoveragePath = builder.codeCoveragePath;
        this.delayInMillis = builder.delayInMillis;
        this.logOnly = builder.logOnly;
        this.testPackages = builder.testPackages;
        this.notTestPackages = builder.notTestPackages;
        this.testSize = builder.testSize;
        this.annotations = Collections.unmodifiableList(builder.annotations);
        this.notAnnotations = Collections.unmodifiableList(builder.notAnnotations);
        this.testTimeout = builder.testTimeout;
        this.listeners = Collections.unmodifiableList(builder.listeners);
        this.filters = Collections.unmodifiableList(builder.filters);
        this.runnerBuilderClasses = Collections.unmodifiableList(builder.runnerBuilderClasses);
        this.tests = Collections.unmodifiableList(builder.tests);
        this.notTests = Collections.unmodifiableList(builder.notTests);
        this.numShards = builder.numShards;
        this.shardIndex = builder.shardIndex;
        this.disableAnalytics = builder.disableAnalytics;
        this.appListeners = Collections.unmodifiableList(builder.appListeners);
        this.classLoader = builder.classLoader;
        this.classpathToScan = builder.classpathToScan;
        this.remoteMethod = builder.remoteMethod;
        this.orchestratorService = builder.orchestratorService;
        this.listTestsForOrchestrator = builder.listTestsForOrchestrator;
        this.testDiscoveryService = builder.testDiscoveryService;
        this.testRunEventsService = builder.testRunEventsService;
        this.useTestStorageService = builder.useTestStorageService;
        this.screenCaptureProcessors = Collections.unmodifiableList(builder.screenCaptureProcessors);
        this.targetProcess = builder.targetProcess;
        this.shellExecBinderKey = builder.shellExecBinderKey;
        this.newRunListenerMode = builder.newRunListenerMode;
        this.testsRegEx = builder.testsRegEx;
        this.testPlatformMigration = builder.testPlatformMigration;
    }

    public static class Builder {
        private final List<String> annotations;
        private List<ApplicationLifecycleCallback> appListeners;
        private ClassLoader classLoader;
        private Set<String> classpathToScan;
        private boolean codeCoverage;
        private String codeCoveragePath;
        private boolean debug;
        private int delayInMillis;
        private boolean disableAnalytics;
        private List<Filter> filters;
        private boolean listTestsForOrchestrator;
        private List<RunListener> listeners;
        private boolean logOnly;
        private boolean newRunListenerMode;
        private final List<String> notAnnotations;
        private List<String> notTestPackages;
        private List<TestArg> notTests;
        private int numShards;
        private String orchestratorService;
        private TestArg remoteMethod;
        private List<Class<? extends RunnerBuilder>> runnerBuilderClasses;
        private List<ScreenCaptureProcessor> screenCaptureProcessors;
        private int shardIndex;
        public String shellExecBinderKey;
        private boolean suiteAssignment;
        private String targetProcess;
        private String testDiscoveryService;
        private List<String> testPackages;
        private boolean testPlatformMigration;
        private String testRunEventsService;
        private String testSize;
        private final PlatformTestStorage testStorage;
        private long testTimeout;
        private List<TestArg> tests;
        private String testsRegEx;
        private boolean useTestStorageService;

        public Builder() {
            this(PlatformTestStorageRegistry.getInstance());
        }

        Builder(PlatformTestStorage testStorage) {
            this.debug = false;
            this.suiteAssignment = false;
            this.codeCoverage = false;
            this.codeCoveragePath = null;
            this.delayInMillis = -1;
            this.logOnly = false;
            this.testPackages = new ArrayList();
            this.notTestPackages = new ArrayList();
            this.testSize = null;
            this.annotations = new ArrayList();
            this.notAnnotations = new ArrayList();
            this.testTimeout = -1L;
            this.listeners = new ArrayList();
            this.filters = new ArrayList();
            this.runnerBuilderClasses = new ArrayList();
            this.tests = new ArrayList();
            this.notTests = new ArrayList();
            this.numShards = 0;
            this.shardIndex = 0;
            this.disableAnalytics = false;
            this.appListeners = new ArrayList();
            this.classLoader = null;
            this.classpathToScan = new HashSet();
            this.remoteMethod = null;
            this.orchestratorService = null;
            this.listTestsForOrchestrator = false;
            this.testDiscoveryService = null;
            this.testRunEventsService = null;
            this.useTestStorageService = false;
            this.targetProcess = null;
            this.screenCaptureProcessors = new ArrayList();
            this.newRunListenerMode = false;
            this.testsRegEx = null;
            this.testPlatformMigration = false;
            this.testStorage = testStorage;
        }

        public Builder fromBundle(Instrumentation instr, Bundle bundle) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
            this.debug = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_DEBUG));
            this.useTestStorageService = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_USE_TEST_STORAGE_SERVICE));
            this.delayInMillis = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_DELAY_IN_MILLIS), RunnerArgs.ARGUMENT_DELAY_IN_MILLIS);
            this.tests.addAll(parseTestClasses(bundle.getString("class")));
            this.notTests.addAll(parseTestClasses(bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_CLASS)));
            this.testPackages.addAll(parseTestPackages(bundle.getString(RunnerArgs.ARGUMENT_TEST_PACKAGE)));
            this.notTestPackages.addAll(parseTestPackages(bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_PACKAGE)));
            TestFileArgs testFile = parseTestFile(instr, this.useTestStorageService, bundle.getString(RunnerArgs.ARGUMENT_TEST_FILE));
            this.tests.addAll(testFile.tests);
            this.testPackages.addAll(testFile.packages);
            TestFileArgs testFile2 = parseTestFile(instr, this.useTestStorageService, bundle.getString(RunnerArgs.ARGUMENT_NOT_TEST_FILE));
            this.notTests.addAll(testFile2.tests);
            this.notTestPackages.addAll(testFile2.packages);
            this.listeners.addAll(parseLoadAndInstantiateClasses(bundle.getString("listener"), RunListener.class, null));
            this.filters.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_FILTER), Filter.class, bundle));
            this.runnerBuilderClasses.addAll(parseAndLoadClasses(bundle.getString(RunnerArgs.ARGUMENT_RUNNER_BUILDER), RunnerBuilder.class));
            this.testSize = bundle.getString(RunnerArgs.ARGUMENT_TEST_SIZE);
            this.annotations.addAll(parseStrings(bundle.getString(RunnerArgs.ARGUMENT_ANNOTATION)));
            this.notAnnotations.addAll(parseStrings(bundle.getString(RunnerArgs.ARGUMENT_NOT_ANNOTATION)));
            this.testTimeout = parseUnsignedLong(bundle.getString(RunnerArgs.ARGUMENT_TIMEOUT), RunnerArgs.ARGUMENT_TIMEOUT);
            this.numShards = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_NUM_SHARDS), RunnerArgs.ARGUMENT_NUM_SHARDS);
            this.shardIndex = parseUnsignedInt(bundle.get(RunnerArgs.ARGUMENT_SHARD_INDEX), RunnerArgs.ARGUMENT_SHARD_INDEX);
            this.logOnly = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_LOG_ONLY));
            this.disableAnalytics = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_DISABLE_ANALYTICS));
            this.appListeners.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_APP_LISTENER), ApplicationLifecycleCallback.class, null));
            this.codeCoverage = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_COVERAGE));
            this.codeCoveragePath = bundle.getString(RunnerArgs.ARGUMENT_COVERAGE_PATH);
            this.suiteAssignment = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_SUITE_ASSIGNMENT));
            this.classLoader = (ClassLoader) parseLoadAndInstantiateClass(bundle.getString(RunnerArgs.ARGUMENT_CLASS_LOADER), ClassLoader.class);
            this.classpathToScan = parseClasspath(bundle.getString(RunnerArgs.ARGUMENT_CLASSPATH_TO_SCAN));
            if (bundle.containsKey(RunnerArgs.ARGUMENT_REMOTE_INIT_METHOD)) {
                this.remoteMethod = parseTestClass(bundle.getString(RunnerArgs.ARGUMENT_REMOTE_INIT_METHOD));
            }
            this.orchestratorService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_SERVICE);
            this.listTestsForOrchestrator = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_LIST_TESTS_FOR_ORCHESTRATOR));
            this.testDiscoveryService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_DISCOVERY_SERVICE);
            this.testRunEventsService = bundle.getString(RunnerArgs.ARGUMENT_ORCHESTRATOR_RUN_EVENTS_SERVICE);
            this.targetProcess = bundle.getString(RunnerArgs.ARGUMENT_TARGET_PROCESS);
            this.screenCaptureProcessors.addAll(parseLoadAndInstantiateClasses(bundle.getString(RunnerArgs.ARGUMENT_SCREENSHOT_PROCESSORS), ScreenCaptureProcessor.class, null));
            this.shellExecBinderKey = bundle.getString(RunnerArgs.ARGUMENT_SHELL_EXEC_BINDER_KEY);
            this.newRunListenerMode = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_RUN_LISTENER_NEW_ORDER));
            this.testsRegEx = bundle.getString(RunnerArgs.ARGUMENT_TESTS_REGEX);
            this.testPlatformMigration = parseBoolean(bundle.getString(RunnerArgs.ARGUMENT_TEST_PLATFORM_MIGRATION));
            return this;
        }

        private TestFileArgs parseTestFile(Instrumentation instr, boolean useStorageService, String filePath) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
            if (filePath == null) {
                return new TestFileArgs();
            }
            if (useStorageService) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.testStorage.openInputFile(filePath.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) ? filePath.substring(1) : filePath)));
                    try {
                        TestFileArgs fromFileStream = parseFromFileStream(bufferedReader);
                        bufferedReader.close();
                        return fromFileStream;
                    } finally {
                    }
                } catch (IOException e) {
                    Log.w(RunnerArgs.LOG_TAG, String.format("Could not read test file from TestStorage %s. Attempting to read from local file system", filePath), e);
                }
            }
            try {
                BufferedReader bufferedReaderOpenFile = openFile(instr, filePath);
                try {
                    TestFileArgs fromFileStream2 = parseFromFileStream(bufferedReaderOpenFile);
                    if (bufferedReaderOpenFile != null) {
                        bufferedReaderOpenFile.close();
                    }
                    return fromFileStream2;
                } finally {
                }
            } catch (IOException e2) {
                throw new IllegalArgumentException("Could not read test file " + filePath, e2);
            }
        }

        public Builder fromManifest(Instrumentation instr) {
            try {
                Bundle bundle = instr.getContext().getPackageManager().getInstrumentationInfo(instr.getComponentName(), 128).metaData;
                return bundle == null ? this : fromBundle(instr, bundle);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.wtf(RunnerArgs.LOG_TAG, String.format("Could not find component %s", instr.getComponentName()));
                return this;
            }
        }

        private static List<String> parseStrings(String value) {
            if (value == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(value.split(RunnerArgs.CLASS_SEPARATOR));
        }

        private static boolean parseBoolean(String booleanValue) {
            return booleanValue != null && Boolean.parseBoolean(booleanValue);
        }

        private static int parseUnsignedInt(Object value, String name) throws NumberFormatException {
            if (value == null) {
                return -1;
            }
            int i = Integer.parseInt(value.toString());
            if (i >= 0) {
                return i;
            }
            throw new NumberFormatException(name + " can not be negative");
        }

        private static long parseUnsignedLong(Object value, String name) throws NumberFormatException {
            if (value == null) {
                return -1L;
            }
            long j = Long.parseLong(value.toString());
            if (j >= 0) {
                return j;
            }
            throw new NumberFormatException(name + " can not be negative");
        }

        private static List<String> parseTestPackages(String packagesArg) {
            ArrayList arrayList = new ArrayList();
            if (packagesArg != null) {
                for (String str : packagesArg.split(RunnerArgs.CLASS_SEPARATOR)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        }

        private List<TestArg> parseTestClasses(String classesArg) {
            return ClassesArgTokenizer.parse(classesArg);
        }

        private static Set<String> parseClasspath(String classpath) {
            if (classpath == null || classpath.isEmpty()) {
                return new HashSet();
            }
            return new HashSet(Arrays.asList(classpath.split(RunnerArgs.CLASSPATH_SEPARATOR, -1)));
        }

        private static TestArg parseTestClass(String testClassName) {
            if (TextUtils.isEmpty(testClassName)) {
                return null;
            }
            int iIndexOf = testClassName.indexOf(35);
            if (iIndexOf > 0) {
                return new TestArg(testClassName.substring(0, iIndexOf), testClassName.substring(iIndexOf + 1));
            }
            return new TestArg(testClassName);
        }

        private TestFileArgs parseFromFileStream(BufferedReader reader) throws IOException {
            TestFileArgs testFileArgs = new TestFileArgs();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    return testFileArgs;
                }
                if (isClassOrMethod(line)) {
                    testFileArgs.tests.add(parseTestClass(line));
                } else {
                    testFileArgs.packages.addAll(parseTestPackages(line));
                }
            }
        }

        private BufferedReader openFile(Instrumentation instr, String filePath) throws IOException {
            Reader fileReader;
            if (Build.VERSION.SDK_INT >= 26 && instr.getContext().getPackageManager().isInstantApp()) {
                fileReader = new InputStreamReader(new ParcelFileDescriptor.AutoCloseInputStream(instr.getUiAutomation().executeShellCommand("cat " + filePath)));
            } else {
                fileReader = new FileReader(new File(filePath));
            }
            return new BufferedReader(fileReader);
        }

        static boolean isClassOrMethod(String line) {
            for (int i = 0; i < line.length(); i++) {
                char cCharAt = line.charAt(i);
                if (cCharAt == '#' || Character.isUpperCase(cCharAt)) {
                    return true;
                }
            }
            return false;
        }

        private <T> List<T> parseLoadAndInstantiateClasses(String classString, Class<T> type, Bundle bundle) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            ArrayList arrayList = new ArrayList();
            if (classString != null) {
                for (String str : classString.split(RunnerArgs.CLASS_SEPARATOR)) {
                    loadClassByNameInstantiateAndAdd(arrayList, str, type, bundle);
                }
            }
            return arrayList;
        }

        private <T> T parseLoadAndInstantiateClass(String classString, Class<T> type) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            List<T> loadAndInstantiateClasses = parseLoadAndInstantiateClasses(classString, type, null);
            if (loadAndInstantiateClasses.isEmpty()) {
                return null;
            }
            if (loadAndInstantiateClasses.size() > 1) {
                throw new IllegalArgumentException(String.format("Expected 1 class loader, %d given", Integer.valueOf(loadAndInstantiateClasses.size())));
            }
            return loadAndInstantiateClasses.get(0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private <T> void loadClassByNameInstantiateAndAdd(List<T> objects, String className, Class<T> type, Bundle bundle) throws NoSuchMethodException, ClassNotFoundException, SecurityException {
            Object[] objArr;
            Constructor<?> constructor;
            if (className == null || className.length() == 0) {
                return;
            }
            try {
                try {
                    Class<?> cls = Class.forName(className);
                    try {
                        constructor = cls.getConstructor(new Class[0]);
                        objArr = new Object[0];
                    } catch (NoSuchMethodException e) {
                        if (bundle != null) {
                            try {
                                objArr = new Object[]{bundle};
                                constructor = cls.getConstructor(Bundle.class);
                            } catch (NoSuchMethodException e2) {
                                e2.initCause(e);
                                throw e2;
                            }
                        } else {
                            throw e;
                        }
                    }
                    constructor.setAccessible(true);
                    objects.add(constructor.newInstance(objArr));
                } catch (NoSuchMethodException unused) {
                    throw new IllegalArgumentException("Must have no argument constructor for class " + className);
                }
            } catch (ClassCastException unused2) {
                throw new IllegalArgumentException(className + " does not extend " + type.getName());
            } catch (ClassNotFoundException unused3) {
                throw new IllegalArgumentException("Could not find extra class " + className);
            } catch (IllegalAccessException e3) {
                throw new IllegalArgumentException("Failed to create listener: " + className, e3);
            } catch (InstantiationException e4) {
                throw new IllegalArgumentException("Failed to create: " + className, e4);
            } catch (InvocationTargetException e5) {
                throw new IllegalArgumentException("Failed to create: " + className, e5);
            }
        }

        private <T> List<Class<? extends T>> parseAndLoadClasses(String classString, Class<T> type) throws ClassNotFoundException {
            ArrayList arrayList = new ArrayList();
            if (classString != null) {
                for (String str : classString.split(RunnerArgs.CLASS_SEPARATOR)) {
                    loadClassByNameAndAdd(arrayList, str, type);
                }
            }
            return arrayList;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private <T> void loadClassByNameAndAdd(List<Class<? extends T>> classes, String className, Class<T> type) throws ClassNotFoundException {
            if (className == null || className.length() == 0) {
                return;
            }
            try {
                Class<?> cls = Class.forName(className);
                if (!type.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException(className + " does not extend " + type.getName());
                }
                classes.add(cls);
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException(className + " does not extend " + type.getName());
            } catch (ClassNotFoundException unused2) {
                throw new IllegalArgumentException("Could not find extra class " + className);
            }
        }

        public RunnerArgs build() {
            return new RunnerArgs(this);
        }
    }
}
