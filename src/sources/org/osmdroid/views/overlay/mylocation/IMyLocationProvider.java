package org.osmdroid.views.overlay.mylocation;

import android.location.Location;

/* loaded from: classes3.dex */
public interface IMyLocationProvider {
    void destroy();

    Location getLastKnownLocation();

    boolean startLocationProvider(IMyLocationConsumer iMyLocationConsumer);

    void stopLocationProvider();
}
