package androidx.test.internal.events.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.test.internal.events.client.TestEventServiceConnectionBase;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.discovery.ITestDiscoveryEvent;
import androidx.test.services.events.discovery.TestDiscoveryEvent;

/* loaded from: classes5.dex */
public class TestDiscoveryEventServiceConnection extends TestEventServiceConnectionBase<ITestDiscoveryEvent> implements TestDiscoveryEventService {
    TestDiscoveryEventServiceConnection(String serviceName, TestEventClientConnectListener listener) {
        super(serviceName, new TestEventServiceConnectionBase.ServiceFromBinder() { // from class: androidx.test.internal.events.client.TestDiscoveryEventServiceConnection$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.events.client.TestEventServiceConnectionBase.ServiceFromBinder
            public final IInterface asInterface(IBinder iBinder) {
                return ITestDiscoveryEvent.Stub.asInterface(iBinder);
            }
        }, listener);
    }

    @Override // androidx.test.internal.events.client.TestDiscoveryEventService
    public void send(TestDiscoveryEvent testDiscoveryEvent) throws TestEventClientException {
        Checks.checkNotNull(testDiscoveryEvent, "testDiscoveryEvent cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Can't add test, service not connected");
        }
        try {
            ((ITestDiscoveryEvent) this.service).send(testDiscoveryEvent);
        } catch (RemoteException e) {
            throw new TestEventClientException("Failed to send test case", e);
        }
    }
}
