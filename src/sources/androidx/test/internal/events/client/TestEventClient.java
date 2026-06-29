package androidx.test.internal.events.client;

import android.content.Context;
import android.util.Log;
import androidx.test.internal.util.Checks;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.junit.runner.notification.RunListener;

/* loaded from: classes5.dex */
public final class TestEventClient {
    public static final TestEventClient NO_OP_CLIENT = new TestEventClient();
    private static final String TAG = "TestEventClient";
    private static TestEventServiceConnection defaultConn;
    private final AtomicBoolean isConnectedToOrchestrator;
    private final OrchestratedInstrumentationListener notificationRunListener;
    private final TestDiscoveryListener testDiscoveryListener;
    private final TestPlatformListener testPlatformListener;

    private TestEventClient() {
        this.isConnectedToOrchestrator = new AtomicBoolean(false);
        this.testDiscoveryListener = null;
        this.notificationRunListener = null;
        this.testPlatformListener = null;
    }

    private TestEventClient(TestDiscoveryListener testDiscoveryListener) {
        this.isConnectedToOrchestrator = new AtomicBoolean(false);
        Checks.checkNotNull(testDiscoveryListener, "testDiscovery cannot be null");
        this.testDiscoveryListener = testDiscoveryListener;
        this.notificationRunListener = null;
        this.testPlatformListener = null;
    }

    private TestEventClient(OrchestratedInstrumentationListener runListener) {
        this.isConnectedToOrchestrator = new AtomicBoolean(false);
        Checks.checkNotNull(runListener, "runListener cannot be null");
        this.testDiscoveryListener = null;
        this.notificationRunListener = runListener;
        this.testPlatformListener = null;
    }

    private TestEventClient(TestPlatformListener runListener) {
        this.isConnectedToOrchestrator = new AtomicBoolean(false);
        Checks.checkNotNull(runListener, "runListener cannot be null");
        this.testDiscoveryListener = null;
        this.notificationRunListener = null;
        this.testPlatformListener = runListener;
    }

    public static TestEventClient connect(Context context, TestEventClientConnectListener listener, TestEventClientArgs args) {
        Checks.checkNotNull(context, "context parameter cannot be null!");
        Checks.checkNotNull(listener, "listener parameter cannot be null!");
        Checks.checkNotNull(args, "args parameter cannot be null!");
        if (!args.isOrchestrated) {
            return NO_OP_CLIENT;
        }
        if (!args.isPrimaryInstrProcess) {
            Log.w(TAG, "Orchestration requested, but this isn't the primary instrumentation");
            return NO_OP_CLIENT;
        }
        TestEventServiceConnection connection = defaultConn;
        if (connection == null) {
            connection = getConnection(listener, args);
        }
        TestEventClient testEventClient = NO_OP_CLIENT;
        if (args.isTestDiscoveryRequested) {
            Log.v(TAG, "Test discovery events requested");
            testEventClient = new TestEventClient(new TestDiscoveryListener((TestDiscoveryEventService) connection));
        } else if (args.isTestRunEventsRequested) {
            Log.v(TAG, "Test run events requested");
            if (args.testPlatformMigration) {
                testEventClient = new TestEventClient(new TestPlatformListener((TestPlatformEventService) connection));
            } else {
                testEventClient = new TestEventClient(new OrchestratedInstrumentationListener((TestRunEventService) connection));
            }
        }
        connection.connect(context);
        return testEventClient;
    }

    public boolean isOrchestrationServiceEnabled() {
        return isTestDiscoveryEnabled() || isTestRunEventsEnabled();
    }

    private boolean isTestDiscoveryEnabled() {
        return this.testDiscoveryListener != null;
    }

    private boolean isTestRunEventsEnabled() {
        return (this.notificationRunListener == null && this.testPlatformListener == null) ? false : true;
    }

    public RunListener getRunListener() {
        if (isTestDiscoveryEnabled()) {
            return this.testDiscoveryListener;
        }
        if (!isTestRunEventsEnabled()) {
            return null;
        }
        OrchestratedInstrumentationListener orchestratedInstrumentationListener = this.notificationRunListener;
        return orchestratedInstrumentationListener != null ? orchestratedInstrumentationListener : this.testPlatformListener;
    }

    private static TestEventServiceConnection getConnection(TestEventClientConnectListener listener, TestEventClientArgs args) {
        if (args.orchestratorVersion == 1) {
            if (args.connectionFactory != null) {
                return args.connectionFactory.create(listener);
            }
            throw new IllegalArgumentException("Orchestrator v1 connectionFactory must be provided by TestEventClientArgs.Builder#setConnectionFactory()");
        }
        if (args.orchestratorVersion == 2) {
            if (args.isTestDiscoveryRequested) {
                return new TestDiscoveryEventServiceConnection((String) Checks.checkNotNull(args.testDiscoveryService), listener);
            }
            if (args.isTestRunEventsRequested) {
                if (args.testPlatformMigration) {
                    return new TestPlatformEventServiceConnection((String) Checks.checkNotNull(args.testRunEventService), listener);
                }
                return new TestRunEventServiceConnection((String) Checks.checkNotNull(args.testRunEventService), listener);
            }
        }
        throw new IllegalArgumentException("TestEventClientArgs misconfiguration - can't determine which service connection to use.");
    }

    public boolean reportProcessCrash(Throwable t) {
        return reportProcessCrash(t, TimeUnit.SECONDS.toMillis(20L));
    }

    public boolean reportProcessCrash(Throwable t, long timeoutMillis) {
        if (!this.isConnectedToOrchestrator.get()) {
            Log.w(TAG, "Process crashed before connection to orchestrator");
            return false;
        }
        if (isTestRunEventsEnabled()) {
            if (this.notificationRunListener != null) {
                Log.d(TAG, "Reporting process crashed to orchestration test run event service.");
                return this.notificationRunListener.reportProcessCrash(t, timeoutMillis);
            }
            if (this.testPlatformListener != null) {
                Log.d(TAG, "Reporting process crash to platform test event service.");
                return this.testPlatformListener.reportProcessCrash(t);
            }
        } else if (isTestDiscoveryEnabled()) {
            Log.d(TAG, "Reporting process crash to platform test discovery service.");
            return this.testDiscoveryListener.reportProcessCrash(t);
        }
        return false;
    }

    public static void setOrchestratorConnection(TestEventServiceConnection conn) {
        defaultConn = (TestEventServiceConnection) Checks.checkNotNull(conn);
    }

    public void setConnectedToOrchestrator(boolean b) {
        this.isConnectedToOrchestrator.set(b);
    }
}
