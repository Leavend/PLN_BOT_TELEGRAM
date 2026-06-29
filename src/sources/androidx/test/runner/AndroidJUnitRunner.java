package androidx.test.runner;

import android.app.Instrumentation;
import android.os.Bundle;
import android.os.Debug;
import android.os.StrictMode;
import android.util.Log;
import androidx.test.internal.events.client.TestEventClient;
import androidx.test.internal.events.client.TestEventClientArgs;
import androidx.test.internal.events.client.TestEventClientConnectListener;
import androidx.test.internal.events.client.TestEventServiceConnection;
import androidx.test.internal.platform.reflect.ReflectionException;
import androidx.test.internal.platform.reflect.ReflectiveMethod;
import androidx.test.internal.runner.ClassPathScanner;
import androidx.test.internal.runner.RunnerArgs;
import androidx.test.internal.runner.TestExecutor;
import androidx.test.internal.runner.TestRequestBuilder;
import androidx.test.internal.runner.listener.ActivityFinisherRunListener;
import androidx.test.internal.runner.listener.CoverageListener;
import androidx.test.internal.runner.listener.DelayInjector;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import androidx.test.internal.runner.listener.LogRunListener;
import androidx.test.internal.runner.listener.SuiteAssignmentPrinter;
import androidx.test.internal.runner.listener.TraceRunListener;
import androidx.test.orchestrator.callback.OrchestratorV1Connection;
import androidx.test.platform.io.FileTestStorage;
import androidx.test.platform.io.PlatformTestStorageRegistry;
import androidx.test.runner.MonitoringInstrumentation;
import androidx.test.runner.lifecycle.ApplicationLifecycleCallback;
import androidx.test.runner.lifecycle.ApplicationLifecycleMonitorRegistry;
import androidx.test.runner.screenshot.Screenshot;
import androidx.test.services.storage.TestStorage;
import androidx.tracing.Trace;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.WeakHashMap;
import org.junit.runner.Request;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public class AndroidJUnitRunner extends MonitoringInstrumentation implements TestEventClientConnectListener {
    private static final String LOG_TAG = "AndroidJUnitRunner";
    private Bundle arguments;
    private RunnerArgs runnerArgs;
    private final InstrumentationResultPrinter instrumentationResultPrinter = new InstrumentationResultPrinter();
    private TestEventClient testEventClient = TestEventClient.NO_OP_CLIENT;
    private final Set<Throwable> appExceptionsHandled = Collections.newSetFromMap(new WeakHashMap());

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public void onCreate(Bundle arguments) {
        Trace.beginSection("AndroidJUnitRunner#onCreate");
        try {
            super.onCreate(arguments);
            this.arguments = arguments;
            parseRunnerArgs(arguments);
            Log.i("AndroidJUnitRunner", "onCreate " + arguments.toString());
            if (waitForDebugger(this.runnerArgs)) {
                Log.i("AndroidJUnitRunner", "Waiting for debugger to connect...");
                Debug.waitForDebugger();
                Log.i("AndroidJUnitRunner", "Debugger connected.");
            }
            Iterator<ApplicationLifecycleCallback> it = this.runnerArgs.appListeners.iterator();
            while (it.hasNext()) {
                ApplicationLifecycleMonitorRegistry.getInstance().addLifecycleCallback(it.next());
            }
            addScreenCaptureProcessors(this.runnerArgs);
            if (shouldWaitForOrchestratorService()) {
                Log.v("AndroidJUnitRunner", "Waiting to connect to the Orchestrator service...");
            } else {
                start();
            }
        } finally {
            Trace.endSection();
        }
    }

    private boolean shouldWaitForOrchestratorService() {
        TestEventClient testEventClientConnect = TestEventClient.connect(getContext(), this, TestEventClientArgs.builder().setConnectionFactory(new TestEventClientArgs.ConnectionFactory() { // from class: androidx.test.runner.AndroidJUnitRunner$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.events.client.TestEventClientArgs.ConnectionFactory
            public final TestEventServiceConnection create(TestEventClientConnectListener testEventClientConnectListener) {
                return new OrchestratorV1Connection(testEventClientConnectListener);
            }
        }).setOrchestratorService(this.runnerArgs.orchestratorService).setPrimaryInstProcess(isPrimaryInstrProcess(this.runnerArgs.targetProcess)).setTestDiscoveryRequested(this.runnerArgs.listTestsForOrchestrator).setTestRunEventsRequested(!this.runnerArgs.listTestsForOrchestrator).setTestDiscoveryService(this.runnerArgs.testDiscoveryService).setTestRunEventService(this.runnerArgs.testRunEventsService).setTestPlatformMigration(this.runnerArgs.testPlatformMigration).build());
        this.testEventClient = testEventClientConnect;
        return testEventClientConnect.isOrchestrationServiceEnabled();
    }

    private boolean waitForDebugger(RunnerArgs arguments) {
        return arguments.debug && !arguments.listTestsForOrchestrator;
    }

    @Deprecated
    public void onOrchestratorConnect() {
        onTestEventClientConnect();
    }

    @Override // androidx.test.internal.events.client.TestEventClientConnectListener
    public void onTestEventClientConnect() {
        this.testEventClient.setConnectedToOrchestrator(true);
        start();
    }

    private void parseRunnerArgs(Bundle arguments) {
        this.runnerArgs = new RunnerArgs.Builder().fromManifest(this).fromBundle(this, arguments).build();
    }

    private Bundle getArguments() {
        return this.arguments;
    }

    InstrumentationResultPrinter getInstrumentationResultPrinter() {
        return this.instrumentationResultPrinter;
    }

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public void onStart() {
        Log.d("AndroidJUnitRunner", "onStart is called.");
        Trace.beginSection("AndroidJUnitRunner#onStart");
        Bundle bundle = new Bundle();
        try {
            setJsBridgeClassName("androidx.test.espresso.web.bridge.JavaScriptBridge");
            super.onStart();
            Request requestBuildRequest = buildRequest(this.runnerArgs, getArguments());
            if (this.runnerArgs.remoteMethod != null) {
                try {
                    new ReflectiveMethod(this.runnerArgs.remoteMethod.testClassName, this.runnerArgs.remoteMethod.methodName, new Class[0]).invokeStatic(new Object[0]);
                } catch (ReflectionException e) {
                    Log.e("AndroidJUnitRunner", String.format("Reflective call to remote method %s#%s failed", this.runnerArgs.remoteMethod.testClassName, this.runnerArgs.remoteMethod.methodName), e);
                }
            }
            if (!isPrimaryInstrProcess(this.runnerArgs.targetProcess)) {
                Log.i("AndroidJUnitRunner", "Runner is idle...");
                return;
            }
            registerTestStorage(this.runnerArgs);
            try {
                TestExecutor.Builder builder = new TestExecutor.Builder(this);
                addListeners(this.runnerArgs, builder);
                bundle = builder.build().execute(requestBuildRequest);
            } catch (Throwable th) {
                Log.e("AndroidJUnitRunner", "Fatal exception when running tests", th);
                onException(this, th);
            }
            Trace.endSection();
            finish(-1, bundle);
        } finally {
            Trace.endSection();
        }
    }

    final void addListeners(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.newRunListenerMode) {
            addListenersNewOrder(args, builder);
        } else {
            addListenersLegacyOrder(args, builder);
        }
    }

    private void addListenersLegacyOrder(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.logOnly) {
            builder.addRunListener(getInstrumentationResultPrinter());
        } else if (args.suiteAssignment) {
            builder.addRunListener(new SuiteAssignmentPrinter());
        } else {
            builder.addRunListener(new LogRunListener());
            RunListener runListener = this.testEventClient.getRunListener();
            if (runListener != null) {
                builder.addRunListener(runListener);
            } else {
                builder.addRunListener(getInstrumentationResultPrinter());
            }
            if (shouldWaitForActivitiesToComplete()) {
                builder.addRunListener(new ActivityFinisherRunListener(this, new MonitoringInstrumentation.ActivityFinisher(), new Runnable() { // from class: androidx.test.runner.AndroidJUnitRunner.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AndroidJUnitRunner.this.waitForActivitiesToComplete();
                    }
                }));
            }
            addDelayListener(args, builder);
            addCoverageListener(args, builder);
            builder.addRunListener(new TraceRunListener());
        }
        addListenersFromClasspath(builder);
        addListenersFromArg(args, builder);
    }

    private void addListenersNewOrder(RunnerArgs args, TestExecutor.Builder builder) {
        addListenersFromClasspath(builder);
        addListenersFromArg(args, builder);
        if (args.logOnly) {
            builder.addRunListener(getInstrumentationResultPrinter());
            return;
        }
        if (args.suiteAssignment) {
            builder.addRunListener(new SuiteAssignmentPrinter());
            return;
        }
        builder.addRunListener(new LogRunListener());
        addDelayListener(args, builder);
        addCoverageListener(args, builder);
        RunListener runListener = this.testEventClient.getRunListener();
        if (runListener != null) {
            builder.addRunListener(runListener);
        } else {
            builder.addRunListener(getInstrumentationResultPrinter());
        }
        if (shouldWaitForActivitiesToComplete()) {
            builder.addRunListener(new ActivityFinisherRunListener(this, new MonitoringInstrumentation.ActivityFinisher(), new Runnable() { // from class: androidx.test.runner.AndroidJUnitRunner.2
                @Override // java.lang.Runnable
                public void run() {
                    AndroidJUnitRunner.this.waitForActivitiesToComplete();
                }
            }));
        }
        builder.addRunListener(new TraceRunListener());
    }

    private void addScreenCaptureProcessors(RunnerArgs args) {
        Screenshot.addScreenCaptureProcessors(new HashSet(args.screenCaptureProcessors));
    }

    private void addCoverageListener(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.codeCoverage) {
            builder.addRunListener(new CoverageListener(args.codeCoveragePath, PlatformTestStorageRegistry.getInstance()));
        }
    }

    private void addDelayListener(RunnerArgs args, TestExecutor.Builder builder) {
        if (args.delayInMillis > 0) {
            builder.addRunListener(new DelayInjector(args.delayInMillis));
        } else {
            boolean z = args.logOnly;
        }
    }

    private static void addListenersFromClasspath(TestExecutor.Builder builder) {
        Iterator it = ServiceLoader.load(RunListener.class).iterator();
        while (it.hasNext()) {
            builder.addRunListener((RunListener) it.next());
        }
    }

    private void addListenersFromArg(RunnerArgs args, TestExecutor.Builder builder) {
        Iterator<RunListener> it = args.listeners.iterator();
        while (it.hasNext()) {
            builder.addRunListener(it.next());
        }
    }

    @Override // androidx.test.runner.MonitoringInstrumentation, android.app.Instrumentation
    public boolean onException(Object obj, Throwable e) {
        Throwable thUnwrapException = unwrapException(e);
        if (this.appExceptionsHandled.contains(thUnwrapException)) {
            Log.d("AndroidJUnitRunner", String.format("We've already handled this exception %s. Ignoring.", thUnwrapException.getClass().getName()));
            return false;
        }
        Log.w("AndroidJUnitRunner", "An unhandled exception was thrown by the app.", e);
        this.appExceptionsHandled.add(thUnwrapException);
        InstrumentationResultPrinter instrumentationResultPrinter = getInstrumentationResultPrinter();
        if (instrumentationResultPrinter != null) {
            if (instrumentationResultPrinter.getInstrumentation() == null) {
                instrumentationResultPrinter.setInstrumentation(this);
            }
            StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
            try {
                instrumentationResultPrinter.reportProcessCrash(e);
            } finally {
                StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
            }
        }
        if (this.testEventClient != null) {
            Log.d("AndroidJUnitRunner", "Reporting the crash to an event service.");
            this.testEventClient.reportProcessCrash(e);
        }
        Log.i("AndroidJUnitRunner", "Bringing down the entire Instrumentation process.");
        return super.onException(obj, e);
    }

    @Override // android.app.Instrumentation
    public void sendStatus(int resultCode, Bundle results) {
        Trace.beginSection("sendStatus");
        try {
            super.sendStatus(resultCode, results);
        } finally {
            Trace.endSection();
        }
    }

    Request buildRequest(RunnerArgs runnerArgs, Bundle bundleArgs) {
        TestRequestBuilder testRequestBuilderCreateTestRequestBuilder = createTestRequestBuilder(this, bundleArgs);
        testRequestBuilderCreateTestRequestBuilder.addPathsToScan(runnerArgs.classpathToScan);
        if (runnerArgs.classpathToScan.isEmpty()) {
            testRequestBuilderCreateTestRequestBuilder.addPathsToScan(ClassPathScanner.getDefaultClasspaths(this));
        }
        testRequestBuilderCreateTestRequestBuilder.addFromRunnerArgs(runnerArgs);
        return testRequestBuilderCreateTestRequestBuilder.build();
    }

    TestRequestBuilder createTestRequestBuilder(Instrumentation instr, Bundle arguments) {
        return new TestRequestBuilder(instr, arguments);
    }

    private void registerTestStorage(RunnerArgs runnerArgs) {
        if (runnerArgs.useTestStorageService) {
            Log.d("AndroidJUnitRunner", "Use the test storage service for managing file I/O.");
            PlatformTestStorageRegistry.registerInstance(new TestStorage());
        } else {
            Log.d("AndroidJUnitRunner", "Use the raw file system for managing file I/O.");
            PlatformTestStorageRegistry.registerInstance(new FileTestStorage());
        }
    }
}
