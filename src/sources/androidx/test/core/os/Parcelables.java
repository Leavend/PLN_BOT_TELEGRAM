package androidx.test.core.os;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public final class Parcelables {
    public static <T extends Parcelable> T forceParcel(T parcelable, Parcelable.Creator<T> creator) {
        Parcel parcelObtain = Parcel.obtain();
        try {
            parcelable.writeToParcel(parcelObtain, 0);
            parcelObtain.setDataPosition(0);
            return creator.createFromParcel(parcelObtain);
        } finally {
            parcelObtain.recycle();
        }
    }

    private Parcelables() {
    }
}
