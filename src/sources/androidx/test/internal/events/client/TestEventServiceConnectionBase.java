package androidx.test.internal.events.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.test.internal.util.Checks;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes5.dex */
public class TestEventServiceConnectionBase<T extends IInterface> implements TestEventServiceConnection {
    private static final String TAG = "ConnectionBase";
    private final TestEventClientConnectListener listener;
    private final ServiceFromBinder<T> serviceFromBinder;
    private final String serviceName;
    private final String servicePackageName;
    public T service = null;
    private final ServiceConnection connection = new ServiceConnection() { // from class: androidx.test.internal.events.client.TestEventServiceConnectionBase.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TestEventServiceConnectionBase testEventServiceConnectionBase = TestEventServiceConnectionBase.this;
            testEventServiceConnectionBase.service = (T) testEventServiceConnectionBase.serviceFromBinder.asInterface(iBinder);
            Log.d(TestEventServiceConnectionBase.TAG, "Connected to " + TestEventServiceConnectionBase.this.serviceName);
            TestEventServiceConnectionBase.this.listener.onTestEventClientConnect();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            TestEventServiceConnectionBase.this.service = null;
            Log.d(TestEventServiceConnectionBase.TAG, "Disconnected from " + TestEventServiceConnectionBase.this.serviceName);
        }
    };

    public interface ServiceFromBinder<T extends IInterface> {
        T asInterface(IBinder binder);
    }

    public TestEventServiceConnectionBase(String serviceName, ServiceFromBinder<T> serviceFromBinder, TestEventClientConnectListener listener) {
        this.serviceName = (String) Checks.checkNotNull(getServiceNameOnly(serviceName), "serviceName cannot be null");
        this.servicePackageName = (String) Checks.checkNotNull(getServicePackage(serviceName), "servicePackageName cannot be null");
        this.listener = (TestEventClientConnectListener) Checks.checkNotNull(listener, "listener cannot be null");
        this.serviceFromBinder = (ServiceFromBinder) Checks.checkNotNull(serviceFromBinder, "serviceFromBinder cannot be null");
    }

    @Override // androidx.test.internal.events.client.TestEventServiceConnection
    public void connect(Context context) {
        Intent intent = new Intent(this.serviceName);
        intent.setPackage(this.servicePackageName);
        if (context.bindService(intent, this.connection, 1)) {
            return;
        }
        throw new IllegalStateException("Cannot connect to " + this.serviceName);
    }

    static String getServiceNameOnly(String serviceName) {
        String[] strArrSplit = serviceName.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        if (strArrSplit.length == 2) {
            if (!strArrSplit[1].startsWith(".")) {
                return strArrSplit[1];
            }
            return strArrSplit[0] + strArrSplit[1];
        }
        if (strArrSplit.length == 1) {
            return strArrSplit[0];
        }
        throw new IllegalArgumentException("Invalid serviceName [" + serviceName + "]");
    }

    static String getServicePackage(String serviceName) {
        String[] strArrSplit = serviceName.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        if (strArrSplit.length >= 2) {
            return strArrSplit[0];
        }
        return null;
    }
}
