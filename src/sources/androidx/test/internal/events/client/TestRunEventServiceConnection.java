package androidx.test.internal.events.client;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.test.internal.events.client.TestEventServiceConnectionBase;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.run.ITestRunEvent;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes5.dex */
public class TestRunEventServiceConnection extends TestEventServiceConnectionBase<ITestRunEvent> implements TestRunEventService {
    TestRunEventServiceConnection(String serviceName, TestEventClientConnectListener listener) {
        super(serviceName, new TestEventServiceConnectionBase.ServiceFromBinder() { // from class: androidx.test.internal.events.client.TestRunEventServiceConnection$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.events.client.TestEventServiceConnectionBase.ServiceFromBinder
            public final IInterface asInterface(IBinder iBinder) {
                return ITestRunEvent.Stub.asInterface(iBinder);
            }
        }, listener);
    }

    @Override // androidx.test.internal.events.client.TestRunEventService
    public void send(TestRunEvent testRunEvent) throws TestEventClientException {
        Checks.checkNotNull(testRunEvent, "testRunEvent cannot be null");
        if (this.service == 0) {
            throw new TestEventClientException("Can't send test run event, service not connected");
        }
        try {
            ((ITestRunEvent) this.service).send(testRunEvent);
        } catch (RemoteException e) {
            throw new TestEventClientException("Failed to send test run event", e);
        }
    }
}
