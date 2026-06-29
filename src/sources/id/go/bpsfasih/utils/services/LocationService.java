package id.go.bpsfasih.utils.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.data.CommonCons;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: LocationService.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001:\u00017B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\"2\b\u0010*\u001a\u0004\u0018\u00010\"J\u001c\u0010+\u001a\u00020(2\b\u0010,\u001a\u0004\u0018\u00010\u00042\b\u0010-\u001a\u0004\u0018\u00010\u0004H\u0002J\u0014\u0010.\u001a\u0004\u0018\u00010/2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u00100\u001a\u000201H\u0016J\b\u00102\u001a\u000201H\u0016J\u0018\u00103\u001a\u0002012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u00104\u001a\u00020\bH\u0016J\"\u00105\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u00106\u001a\u00020\b2\u0006\u00104\u001a\u00020\bH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00060\u0016R\u00020\u0000X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00068"}, d2 = {"Lid/go/bpsfasih/utils/services/LocationService;", "Landroid/app/Service;", "()V", "BROADCAST_ACTION", "", "getBROADCAST_ACTION", "()Ljava/lang/String;", "TWO_MINUTES", "", "distance", "", "getDistance", "()F", "setDistance", "(F)V", "intent", "Landroid/content/Intent;", "getIntent", "()Landroid/content/Intent;", "setIntent", "(Landroid/content/Intent;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lid/go/bpsfasih/utils/services/LocationService$MyLocationListener;", "getListener", "()Lid/go/bpsfasih/utils/services/LocationService$MyLocationListener;", "setListener", "(Lid/go/bpsfasih/utils/services/LocationService$MyLocationListener;)V", "locationManager", "Landroid/location/LocationManager;", "getLocationManager", "()Landroid/location/LocationManager;", "setLocationManager", "(Landroid/location/LocationManager;)V", "previousBestLocation", "Landroid/location/Location;", "getPreviousBestLocation", "()Landroid/location/Location;", "setPreviousBestLocation", "(Landroid/location/Location;)V", "isBetterLocation", "", FirebaseAnalytics.Param.LOCATION, "currentBestLocation", "isSameProvider", "provider1", "provider2", "onBind", "Landroid/os/IBinder;", "onCreate", "", "onDestroy", "onStart", "startId", "onStartCommand", "flags", "MyLocationListener", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationService extends Service {
    public static final int $stable = 8;
    private final String BROADCAST_ACTION = "Hello World";
    private final int TWO_MINUTES = 120000;
    private float distance = CommonCons.INSTANCE.getDISTANCE_LIVE_TRACKING();
    public Intent intent;
    public MyLocationListener listener;
    private LocationManager locationManager;
    private Location previousBestLocation;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    public final String getBROADCAST_ACTION() {
        return this.BROADCAST_ACTION;
    }

    public final LocationManager getLocationManager() {
        return this.locationManager;
    }

    public final void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public final MyLocationListener getListener() {
        MyLocationListener myLocationListener = this.listener;
        if (myLocationListener != null) {
            return myLocationListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    public final void setListener(MyLocationListener myLocationListener) {
        Intrinsics.checkNotNullParameter(myLocationListener, "<set-?>");
        this.listener = myLocationListener;
    }

    public final Location getPreviousBestLocation() {
        return this.previousBestLocation;
    }

    public final void setPreviousBestLocation(Location location) {
        this.previousBestLocation = location;
    }

    public final float getDistance() {
        return this.distance;
    }

    public final void setDistance(float f) {
        this.distance = f;
    }

    public final Intent getIntent() {
        Intent intent = this.intent;
        if (intent != null) {
            return intent;
        }
        Intrinsics.throwUninitializedPropertyAccessException("intent");
        return null;
    }

    public final void setIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "<set-?>");
        this.intent = intent;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        setIntent(new Intent(this.BROADCAST_ACTION));
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            this.distance = intent.getFloatExtra("Distance", CommonCons.INSTANCE.getDISTANCE_LIVE_TRACKING());
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override // android.app.Service
    public void onStart(Intent intent, int startId) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        setListener(new MyLocationListener());
        Log.d("masuk", "masuk Service");
        Object systemService = getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
        LocationService locationService = this;
        if (ContextCompat.checkSelfPermission(locationService, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(locationService, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
            LocationManager locationManager = this.locationManager;
            if (locationManager != null) {
                locationManager.requestLocationUpdates("network", 0L, this.distance, getListener());
            }
            LocationManager locationManager2 = this.locationManager;
            if (locationManager2 != null) {
                locationManager2.requestLocationUpdates("gps", 0L, this.distance, getListener());
            }
        }
    }

    public final boolean isBetterLocation(Location location, Location currentBestLocation) {
        Intrinsics.checkNotNullParameter(location, "location");
        if (currentBestLocation == null) {
            return true;
        }
        long time = location.getTime() - currentBestLocation.getTime();
        int i = this.TWO_MINUTES;
        boolean z = time > ((long) i);
        boolean z2 = time < ((long) (-i));
        boolean z3 = time > 0;
        if (z) {
            return true;
        }
        if (z2) {
            return false;
        }
        int accuracy = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean z4 = accuracy > 0;
        boolean z5 = accuracy < 0;
        boolean z6 = accuracy > 200;
        boolean zIsSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());
        if (z5) {
            return true;
        }
        if (!z3 || z4) {
            return z3 && !z6 && zIsSameProvider;
        }
        return true;
    }

    private final boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return Intrinsics.areEqual(provider1, provider2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.v("STOP_SERVICE", "DONE");
        LocationManager locationManager = this.locationManager;
        if (locationManager != null) {
            locationManager.removeUpdates(getListener());
        }
    }

    /* compiled from: LocationService.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000f\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/utils/services/LocationService$MyLocationListener;", "Landroid/location/LocationListener;", "(Lid/go/bpsfasih/utils/services/LocationService;)V", "countDistance", "", "locNow", "Landroid/location/Location;", "locBefore", "onLocationChanged", "", "loc", "onProviderDisabled", DatabaseFileArchive.COLUMN_PROVIDER, "", "onProviderEnabled", "onStatusChanged", NotificationCompat.CATEGORY_STATUS, "", "extras", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public final class MyLocationListener implements LocationListener {
        @Override // android.location.LocationListener
        public void onProviderDisabled(String provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(extras, "extras");
        }

        public MyLocationListener() {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location loc) {
            Intrinsics.checkNotNullParameter(loc, "loc");
            Intent intent = new Intent(LocationService.this.getApplicationContext(), (Class<?>) LocationReceiver.class);
            Intent intent2 = new Intent("GPSLocationUpdates");
            Log.i("*****", "Location changed");
            LocationService locationService = LocationService.this;
            if (locationService.isBetterLocation(loc, locationService.getPreviousBestLocation())) {
                float fCountDistance = LocationService.this.getPreviousBestLocation() != null ? countDistance(loc, LocationService.this.getPreviousBestLocation()) : 0.0f;
                Location location = loc;
                intent.putExtra("Location", location);
                intent.putExtra("Distance", fCountDistance);
                intent.putExtra("Latitude", loc.getLatitude());
                intent.putExtra("Longitude", loc.getLongitude());
                intent.putExtra("Provider", loc.getProvider());
                intent.putExtra("Accuracy", loc.getAccuracy());
                LocationService.this.sendBroadcast(intent);
                intent2.putExtra("Distance", fCountDistance);
                intent2.putExtra("Latitude", loc.getLatitude());
                intent2.putExtra("Longitude", loc.getLongitude());
                intent2.putExtra("Provider", loc.getProvider());
                intent2.putExtra("Accuracy", loc.getAccuracy());
                intent2.putExtra("Location", location);
                LocalBroadcastManager.getInstance(LocationService.this.getApplicationContext()).sendBroadcast(intent2);
                LocationService.this.setPreviousBestLocation(loc);
            }
        }

        public final float countDistance(Location locNow, Location locBefore) {
            Intrinsics.checkNotNullParameter(locNow, "locNow");
            if (locBefore != null) {
                return locNow.distanceTo(locBefore);
            }
            return 0.0f;
        }
    }
}
