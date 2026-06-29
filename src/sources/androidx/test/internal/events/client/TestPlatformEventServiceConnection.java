package androidx.test.internal.events.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.test.internal.events.client.TestEventServiceConnectionBase;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.platform.ITestPlatformEvent;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes5.dex */
public class TestPlatformEventServiceConnection extends TestEventServiceConnectionBase<ITestPlatformEvent> implements TestPlatformEventService {
    TestPlatformEventServiceConnection(String serviceName, TestEventClientConnectListener listener) {
        super(serviceName, new TestEventServiceConnectionBase.ServiceFromBinder() { // from class: androidx.test.internal.events.client.TestPlatformEventServiceConnection$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.events.client.TestEventServiceConnectionBase.ServiceFromBinder
            public final IInterface asInterface(IBinder iBinder) {
                return ITestPlatformEvent.Stub.asInterface(iBinder);
            }
        }, listener);
    }

    @Override // androidx.test.internal.events.client.TestPlatformEventService
    public void send(TestPlatformEvent testPlatformEvent) throws TestEventClientException {
        Checks.checkNotNull(testPlatformEvent, "testPlatformEvent cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Can't send test platform event, service not connected");
        }
        try {
            ((ITestPlatformEvent) this.service).send(testPlatformEvent);
        } catch (RemoteException e) {
            throw new TestEventClientException("Failed to send test platform event", e);
        }
    }
}
