package org.osmdroid.views.overlay.mylocation;

import android.location.Location;

/* loaded from: classes3.dex */
public interface IMyLocationConsumer {
    void onLocationChanged(Location location, IMyLocationProvider iMyLocationProvider);
}
