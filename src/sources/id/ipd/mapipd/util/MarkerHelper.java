package id.ipd.mapipd.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MarkerHelper.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007¨\u0006\r"}, d2 = {"Lid/ipd/mapipd/util/MarkerHelper;", "", "()V", "getDestinationPoint", "Lcom/google/android/gms/maps/model/LatLng;", "source", "brng", "", "dist", "getRectangleCorner", "", "centerLatLon", "distance", "mapipd_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MarkerHelper {
    public static final MarkerHelper INSTANCE = new MarkerHelper();

    private MarkerHelper() {
    }

    public final List<LatLng> getRectangleCorner(LatLng centerLatLon, double distance) {
        Intrinsics.checkNotNullParameter(centerLatLon, "centerLatLon");
        ArrayList arrayList = new ArrayList();
        arrayList.add(getDestinationPoint(centerLatLon, 45.0d, distance));
        arrayList.add(getDestinationPoint(centerLatLon, 135.0d, distance));
        arrayList.add(getDestinationPoint(centerLatLon, 225.0d, distance));
        arrayList.add(getDestinationPoint(centerLatLon, 315.0d, distance));
        return arrayList;
    }

    private final LatLng getDestinationPoint(LatLng source, double brng, double dist) {
        double d = dist / 6371000;
        double radians = Math.toRadians(brng);
        double radians2 = Math.toRadians(source.latitude);
        double radians3 = Math.toRadians(source.longitude);
        double dAsin = Math.asin((Math.sin(radians2) * Math.cos(d)) + (Math.cos(radians2) * Math.sin(d) * Math.cos(radians)));
        return new LatLng(Math.toDegrees(dAsin), Math.toDegrees(radians3 + Math.atan2(Math.sin(radians) * Math.sin(d) * Math.cos(radians2), Math.cos(d) - (Math.sin(radians2) * Math.sin(dAsin)))));
    }
}
