package androidx.test.runner.internal.deps.aidl;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface TransactionInterceptor {
    void enforceNoDataAvail(Parcel parcel);

    boolean interceptTransaction(BaseStub stub, int code, Parcel data, Parcel reply, int flags) throws RemoteException;
}
