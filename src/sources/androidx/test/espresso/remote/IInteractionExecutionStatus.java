package androidx.test.espresso.remote;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.test.espresso.core.internal.deps.aidl.BaseProxy;
import androidx.test.espresso.core.internal.deps.aidl.BaseStub;
import androidx.test.espresso.core.internal.deps.aidl.Codecs;

/* loaded from: classes5.dex */
public interface IInteractionExecutionStatus extends IInterface {

    public static abstract class Stub extends BaseStub implements IInteractionExecutionStatus {
        private static final String DESCRIPTOR = "androidx.test.espresso.remote.IInteractionExecutionStatus";
        static final int TRANSACTION_canExecute = 1;

        public static class Proxy extends BaseProxy implements IInteractionExecutionStatus {
            Proxy(IBinder iBinder) {
                super(iBinder, Stub.DESCRIPTOR);
            }

            @Override // androidx.test.espresso.remote.IInteractionExecutionStatus
            public boolean canExecute() throws RemoteException {
                Parcel parcelTransactAndReadException = transactAndReadException(1, obtainAndWriteInterfaceToken());
                boolean zCreateBoolean = Codecs.createBoolean(parcelTransactAndReadException);
                parcelTransactAndReadException.recycle();
                return zCreateBoolean;
            }
        }

        public Stub() {
            super(DESCRIPTOR);
        }

        @Override // androidx.test.espresso.core.internal.deps.aidl.BaseStub
        protected boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                return false;
            }
            boolean zCanExecute = canExecute();
            parcel2.writeNoException();
            Codecs.writeBoolean(parcel2, zCanExecute);
            return true;
        }

        public static IInteractionExecutionStatus asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface instanceof IInteractionExecutionStatus) {
                return (IInteractionExecutionStatus) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }
    }

    boolean canExecute() throws RemoteException;
}
