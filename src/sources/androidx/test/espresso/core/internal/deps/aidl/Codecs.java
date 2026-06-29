package androidx.test.espresso.core.internal.deps.aidl;

import android.os.BadParcelableException;
import android.os.Parcel;

/* loaded from: classes5.dex */
public class Codecs {
    private static final ClassLoader CLASS_LOADER = Codecs.class.getClassLoader();

    private Codecs() {
    }

    public static boolean createBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void enforceNoDataAvail(Parcel parcel) {
        int iDataAvail = parcel.dataAvail();
        if (iDataAvail > 0) {
            throw new BadParcelableException("Parcel data not fully consumed, unread size: " + iDataAvail);
        }
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }
}
