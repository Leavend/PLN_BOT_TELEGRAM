package org.osmdroid.util;

import android.location.Location;
import android.location.LocationManager;
import org.osmdroid.config.Configuration;

/* loaded from: classes3.dex */
public class LocationUtils {
    private LocationUtils() {
    }

    public static Location getLastKnownLocation(LocationManager locationManager) {
        if (locationManager == null) {
            return null;
        }
        Location lastKnownLocation = getLastKnownLocation(locationManager, "gps");
        Location lastKnownLocation2 = getLastKnownLocation(locationManager, "network");
        return lastKnownLocation == null ? lastKnownLocation2 : (lastKnownLocation2 != null && lastKnownLocation2.getTime() > lastKnownLocation.getTime() + Configuration.getInstance().getGpsWaitTime()) ? lastKnownLocation2 : lastKnownLocation;
    }

    private static Location getLastKnownLocation(LocationManager locationManager, String str) {
        try {
            if (locationManager.isProviderEnabled(str)) {
                return locationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }
}
