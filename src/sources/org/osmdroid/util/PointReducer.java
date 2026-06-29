package org.osmdroid.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class PointReducer {
    public static ArrayList<GeoPoint> reduceWithTolerance(ArrayList<GeoPoint> arrayList, double d) {
        int i;
        int i2;
        int size = arrayList.size();
        if (d <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || size < 3) {
            return arrayList;
        }
        boolean[] zArr = new boolean[size];
        int i3 = 1;
        while (true) {
            i = size - 1;
            if (i3 >= i) {
                break;
            }
            zArr[i3] = false;
            i3++;
        }
        zArr[i] = true;
        zArr[0] = true;
        douglasPeuckerReduction(arrayList, zArr, d, 0, i);
        ArrayList<GeoPoint> arrayList2 = new ArrayList<>(size);
        for (i2 = 0; i2 < size; i2++) {
            if (zArr[i2]) {
                arrayList2.add(arrayList.get(i2));
            }
        }
        return arrayList2;
    }

    private static void douglasPeuckerReduction(ArrayList<GeoPoint> arrayList, boolean[] zArr, double d, int i, int i2) {
        int i3 = i + 1;
        if (i2 <= i3) {
            return;
        }
        GeoPoint geoPoint = arrayList.get(i);
        GeoPoint geoPoint2 = arrayList.get(i2);
        double d2 = 0.0d;
        int i4 = 0;
        while (i3 < i2) {
            double dOrthogonalDistance = orthogonalDistance(arrayList.get(i3), geoPoint, geoPoint2);
            if (dOrthogonalDistance > d2) {
                i4 = i3;
                d2 = dOrthogonalDistance;
            }
            i3++;
        }
        if (d2 > d) {
            zArr[i4] = true;
            douglasPeuckerReduction(arrayList, zArr, d, i, i4);
            douglasPeuckerReduction(arrayList, zArr, d, i4, i2);
        }
    }

    public static double orthogonalDistance(GeoPoint geoPoint, GeoPoint geoPoint2, GeoPoint geoPoint3) {
        return (Math.abs(((((((geoPoint2.getLatitude() * geoPoint3.getLongitude()) + (geoPoint3.getLatitude() * geoPoint.getLongitude())) + (geoPoint.getLatitude() * geoPoint2.getLongitude())) - (geoPoint3.getLatitude() * geoPoint2.getLongitude())) - (geoPoint.getLatitude() * geoPoint3.getLongitude())) - (geoPoint2.getLatitude() * geoPoint.getLongitude())) / 2.0d) / Math.hypot(geoPoint2.getLatitude() - geoPoint3.getLatitude(), geoPoint2.getLongitude() - geoPoint3.getLongitude())) * 2.0d;
    }
}
