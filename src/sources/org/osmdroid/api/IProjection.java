package org.osmdroid.api;

import android.graphics.Point;

/* loaded from: classes3.dex */
public interface IProjection {
    IGeoPoint fromPixels(int i, int i2);

    IGeoPoint getNorthEast();

    IGeoPoint getSouthWest();

    float metersToEquatorPixels(float f);

    Point toPixels(IGeoPoint iGeoPoint, Point point);
}
