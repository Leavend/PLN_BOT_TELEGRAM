package androidx.test.orchestrator.callback;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.test.internal.events.client.TestDiscoveryEventService;
import androidx.test.internal.events.client.TestEventClientConnectListener;
import androidx.test.internal.events.client.TestEventClientException;
import androidx.test.internal.events.client.TestEventServiceConnectionBase;
import androidx.test.internal.events.client.TestRunEventService;
import androidx.test.internal.util.Checks;
import androidx.test.orchestrator.callback.OrchestratorCallback;
import androidx.test.services.events.discovery.TestDiscoveryEvent;
import androidx.test.services.events.discovery.TestFoundEvent;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes5.dex */
public final class OrchestratorV1Connection extends TestEventServiceConnectionBase<OrchestratorCallback> implements TestRunEventService, TestDiscoveryEventService {
    private static final String ORCHESTRATOR_SERVICE = "androidx.test.orchestrator/.OrchestratorService";

    public OrchestratorV1Connection(TestEventClientConnectListener listener) {
        super(ORCHESTRATOR_SERVICE, new TestEventServiceConnectionBase.ServiceFromBinder() { // from class: androidx.test.orchestrator.callback.OrchestratorV1Connection$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.events.client.TestEventServiceConnectionBase.ServiceFromBinder
            public final IInterface asInterface(IBinder iBinder) {
                return OrchestratorCallback.Stub.asInterface(iBinder);
            }
        }, listener);
    }

    @Override // androidx.test.internal.events.client.TestRunEventService
    public void send(TestRunEvent event) throws TestEventClientException {
        Checks.checkNotNull(event, "event cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Unable to send notification, Orchestrator callback is null");
        }
        try {
            ((OrchestratorCallback) this.service).sendTestNotification(BundleConverter.getBundleFromTestRunEvent(event));
        } catch (RemoteException e) {
            throw new TestEventClientException("Unable to send test run event [" + String.valueOf(event.getClass()) + "]", e);
        }
    }

    @Override // androidx.test.internal.events.client.TestDiscoveryEventService
    public void send(TestDiscoveryEvent event) throws TestEventClientException {
        Checks.checkNotNull(event, "event cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Unable to add test, Orchestrator callback is null");
        }
        if (event instanceof TestFoundEvent) {
            String classAndMethodName = ((TestFoundEvent) event).testCase.getClassAndMethodName();
            try {
                ((OrchestratorCallback) this.service).addTest(classAndMethodName);
            } catch (RemoteException e) {
                throw new TestEventClientException("Failed to add test [" + classAndMethodName + "]", e);
            }
        }
    }
}
