package org.osmdroid.api;

/* loaded from: classes3.dex */
public interface IGeoPoint {
    double getLatitude();

    @Deprecated
    int getLatitudeE6();

    double getLongitude();

    @Deprecated
    int getLongitudeE6();
}
