package org.osmdroid.views.overlay.mylocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.HashSet;
import java.util.Set;
import org.osmdroid.api.IMapView;
import org.osmdroid.util.NetworkLocationIgnorer;

/* loaded from: classes3.dex */
public class GpsMyLocationProvider implements IMyLocationProvider, LocationListener {
    private final Set<String> locationSources;
    private Location mLocation;
    private LocationManager mLocationManager;
    private IMyLocationConsumer mMyLocationConsumer;
    private long mLocationUpdateMinTime = 0;
    private float mLocationUpdateMinDistance = 0.0f;
    private NetworkLocationIgnorer mIgnorer = new NetworkLocationIgnorer();

    @Override // android.location.LocationListener
    public void onProviderDisabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onProviderEnabled(String str) {
    }

    @Override // android.location.LocationListener
    public void onStatusChanged(String str, int i, Bundle bundle) {
    }

    public GpsMyLocationProvider(Context context) {
        HashSet hashSet = new HashSet();
        this.locationSources = hashSet;
        this.mLocationManager = (LocationManager) context.getSystemService(FirebaseAnalytics.Param.LOCATION);
        hashSet.add("gps");
        hashSet.add("network");
    }

    public void clearLocationSources() {
        this.locationSources.clear();
    }

    public void addLocationSource(String str) {
        this.locationSources.add(str);
    }

    public Set<String> getLocationSources() {
        return this.locationSources;
    }

    public long getLocationUpdateMinTime() {
        return this.mLocationUpdateMinTime;
    }

    public void setLocationUpdateMinTime(long j) {
        this.mLocationUpdateMinTime = j;
    }

    public float getLocationUpdateMinDistance() {
        return this.mLocationUpdateMinDistance;
    }

    public void setLocationUpdateMinDistance(float f) {
        this.mLocationUpdateMinDistance = f;
    }

    @Override // org.osmdroid.views.overlay.mylocation.IMyLocationProvider
    public boolean startLocationProvider(IMyLocationConsumer iMyLocationConsumer) {
        this.mMyLocationConsumer = iMyLocationConsumer;
        boolean z = false;
        for (String str : this.mLocationManager.getProviders(true)) {
            if (this.locationSources.contains(str)) {
                try {
                    this.mLocationManager.requestLocationUpdates(str, this.mLocationUpdateMinTime, this.mLocationUpdateMinDistance, this);
                    z = true;
                } catch (Throwable th) {
                    Log.e(IMapView.LOGTAG, "Unable to attach listener for location provider " + str + " check permissions?", th);
                }
            }
        }
        return z;
    }

    @Override // org.osmdroid.views.overlay.mylocation.IMyLocationProvider
    public void stopLocationProvider() {
        this.mMyLocationConsumer = null;
        LocationManager locationManager = this.mLocationManager;
        if (locationManager != null) {
            try {
                locationManager.removeUpdates(this);
            } catch (Throwable th) {
                Log.w(IMapView.LOGTAG, "Unable to deattach location listener", th);
            }
        }
    }

    @Override // org.osmdroid.views.overlay.mylocation.IMyLocationProvider
    public Location getLastKnownLocation() {
        return this.mLocation;
    }

    @Override // org.osmdroid.views.overlay.mylocation.IMyLocationProvider
    public void destroy() {
        stopLocationProvider();
        this.mLocation = null;
        this.mLocationManager = null;
        this.mMyLocationConsumer = null;
        this.mIgnorer = null;
    }

    @Override // android.location.LocationListener
    public void onLocationChanged(Location location) {
        if (this.mIgnorer == null) {
            Log.w(IMapView.LOGTAG, "GpsMyLocation provider, mIgnore is null, unexpected. Location update will be ignored");
            return;
        }
        if (location == null || location.getProvider() == null || this.mIgnorer.shouldIgnore(location.getProvider(), System.currentTimeMillis())) {
            return;
        }
        this.mLocation = location;
        IMyLocationConsumer iMyLocationConsumer = this.mMyLocationConsumer;
        if (iMyLocationConsumer == null || location == null) {
            return;
        }
        iMyLocationConsumer.onLocationChanged(location, this);
    }
}
