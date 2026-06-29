package androidx.test.espresso.core.internal.deps.aidl;

import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes5.dex */
public interface TransactionInterceptor {
    void enforceNoDataAvail(Parcel parcel);

    boolean interceptTransaction(BaseStub baseStub, int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException;
}
