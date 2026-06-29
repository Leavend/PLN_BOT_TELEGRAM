package androidx.test.espresso.core.internal.deps.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public abstract class BaseStub extends Binder implements IInterface {
    private static TransactionInterceptor globalInterceptor;

    protected BaseStub(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        return false;
    }

    protected void enforceNoDataAvail(Parcel parcel) {
        TransactionInterceptor transactionInterceptor = globalInterceptor;
        if (transactionInterceptor != null) {
            transactionInterceptor.enforceNoDataAvail(parcel);
        } else {
            Codecs.enforceNoDataAvail(parcel);
        }
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (routeToSuperOrEnforceInterface(i, parcel, parcel2, i2)) {
            return true;
        }
        TransactionInterceptor transactionInterceptor = globalInterceptor;
        return transactionInterceptor == null ? dispatchTransaction(i, parcel, parcel2, i2) : transactionInterceptor.interceptTransaction(this, i, parcel, parcel2, i2);
    }

    private boolean routeToSuperOrEnforceInterface(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i > 16777215) {
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface(getInterfaceDescriptor());
        return false;
    }

    static synchronized void installTransactionInterceptorPackagePrivate(TransactionInterceptor transactionInterceptor) {
        try {
            if (transactionInterceptor == null) {
                throw new IllegalArgumentException("null interceptor");
            }
            if (globalInterceptor != null) {
                throw new IllegalStateException("Duplicate TransactionInterceptor installation.");
            }
            globalInterceptor = transactionInterceptor;
        } catch (Throwable th) {
            throw th;
        }
    }
}
