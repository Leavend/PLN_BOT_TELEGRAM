package org.osmdroid;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/* loaded from: classes3.dex */
public class LocationListenerProxy implements LocationListener {
    private LocationListener mListener = null;
    private final LocationManager mLocationManager;

    public LocationListenerProxy(LocationManager locationManager) {
        this.mLocationManager = locationManager;
    }

    public boolean startListening(LocationListener locationListener, long j, float f) {
        this.mListener = locationListener;
        boolean z = false;
        for (String str : this.mLocationManager.getProviders(true)) {
            if ("gps".equals(str) || "network".equals(str)) {
                this.mLocationManager.requestLocationUpdates(str, j, f, this);
                z = true;
            }
        }
        return z;
    }

    public void stopListening() {
        this.mListener = null;
        this.mLocationManager.removeUpdates(this);
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        LocationListener locationListener = this.mListener;
        if (locationListener != null) {
            locationListener.onLocationChanged(location);
        }
    }

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
        LocationListener locationListener = this.mListener;
        if (locationListener != null) {
            locationListener.onProviderDisabled(str);
        }
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
        LocationListener locationListener = this.mListener;
        if (locationListener != null) {
            locationListener.onProviderEnabled(str);
        }
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
        LocationListener locationListener = this.mListener;
        if (locationListener != null) {
            locationListener.onStatusChanged(str, i, bundle);
        }
    }
}
