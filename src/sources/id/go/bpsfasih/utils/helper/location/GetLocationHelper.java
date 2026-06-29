package id.go.bpsfasih.utils.helper.location;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.logging.LogFactory;

/* compiled from: GetLocationHelper.kt */
@Metadata(d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n*\u0001\b\b\u0007\u0018\u00002\u00020\u0001:\u0001:B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0006J5\u0010\u001a\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\rH\u0002¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010'\u001a\u00020\u0017H\u0002J\u0012\u0010(\u001a\u00020\u00172\b\u0010)\u001a\u0004\u0018\u00010*H\u0002J\b\u0010+\u001a\u00020\rH\u0002J\u0010\u0010,\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010-\u001a\u00020\r2\u0006\u0010)\u001a\u00020*H\u0002J \u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u00020&2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000201H\u0002J\b\u00103\u001a\u00020\u0017H\u0002J\b\u00104\u001a\u00020\u0017H\u0002J\b\u00105\u001a\u00020\u0017H\u0002J\b\u00106\u001a\u00020\u0017H\u0002J\b\u00107\u001a\u00020\u0017H\u0002J\b\u00108\u001a\u00020\u0017H\u0002J\b\u00109\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Lid/go/bpsfasih/utils/helper/location/GetLocationHelper;", "", "()V", "__activity", "Landroidx/appcompat/app/AppCompatActivity;", "__locCallback", "Lid/go/bpsfasih/utils/helper/location/LocCallback;", "gpsLocationListener", "id/go/bpsfasih/utils/helper/location/GetLocationHelper$gpsLocationListener$1", "Lid/go/bpsfasih/utils/helper/location/GetLocationHelper$gpsLocationListener$1;", "locationManager", "Landroid/location/LocationManager;", "locationReceived", "", "mFusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "mLocationCallback", "Lcom/google/android/gms/location/LocationCallback;", "networkStatus", "Lid/go/bpsfasih/utils/helper/location/GetLocationHelper$NetworkStatus;", "timeoutHandler", "Landroid/os/Handler;", "GetLocation", "", "activity", "locCallback", "cleanupAndCallback", FormGearActivity.LAT, "", "lng", "accuracy", "", "isMock", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Boolean;)V", "getNetworkStatus", "context", "Landroid/content/Context;", "getSignalStrength", "", "handleAirplaneMode", "handleResponse", FirebaseAnalytics.Param.LOCATION, "Landroid/location/Location;", "hasLocationPermission", "isAirplaneModeOn", "isLocationFresh", "requestFusedLocation", LogFactory.PRIORITY_KEY, "interval", "", "maxDelay", "requestGPSLocation", "requestGPSOnlyMode", "requestLocationBasedOnNetwork", "requestOnlineMode", "requestPoorSignalMode", "setupTimeoutAndStrategy", "tryLastKnownLocation", "NetworkStatus", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class GetLocationHelper {
    public static final int $stable = 8;
    private AppCompatActivity __activity;
    private LocCallback __locCallback;
    private LocationManager locationManager;
    private boolean locationReceived;
    private FusedLocationProviderClient mFusedLocationClient;
    private Handler timeoutHandler;
    private NetworkStatus networkStatus = NetworkStatus.OFFLINE;
    private LocationCallback mLocationCallback = new LocationCallback() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$mLocationCallback$1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            Intrinsics.checkNotNullParameter(locationResult, "locationResult");
            List<Location> locations = locationResult.getLocations();
            Intrinsics.checkNotNullExpressionValue(locations, "locationResult.locations");
            if (!(!locations.isEmpty()) || this.this$0.locationReceived) {
                return;
            }
            Location location = (Location) CollectionsKt.last((List) locations);
            Log.d("FASIH_Location", "Fused location received (" + this.this$0.networkStatus.name() + "): " + location.getLatitude() + ", " + location.getLongitude());
            this.this$0.handleResponse(location);
        }
    };
    private final GetLocationHelper$gpsLocationListener$1 gpsLocationListener = new LocationListener() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$gpsLocationListener$1
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
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            Intrinsics.checkNotNullParameter(location, "location");
            if (this.this$0.locationReceived) {
                return;
            }
            Log.d("FASIH_Location", "GPS location received (" + this.this$0.networkStatus.name() + "): " + location.getLatitude() + ", " + location.getLongitude());
            this.this$0.handleResponse(location);
        }
    };

    /* compiled from: GetLocationHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/utils/helper/location/GetLocationHelper$NetworkStatus;", "", "(Ljava/lang/String;I)V", "OFFLINE", "POOR_SIGNAL", "GOOD_SIGNAL", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public enum NetworkStatus {
        OFFLINE,
        POOR_SIGNAL,
        GOOD_SIGNAL
    }

    /* compiled from: GetLocationHelper.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NetworkStatus.values().length];
            try {
                iArr[NetworkStatus.GOOD_SIGNAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NetworkStatus.POOR_SIGNAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NetworkStatus.OFFLINE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void cleanupAndCallback$lambda$7() {
    }

    public final void GetLocation(AppCompatActivity activity, LocCallback locCallback) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(locCallback, "locCallback");
        this.__locCallback = locCallback;
        this.__activity = activity;
        this.locationReceived = false;
        AppCompatActivity appCompatActivity = activity;
        this.networkStatus = getNetworkStatus(appCompatActivity);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) activity);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(activity)");
        this.mFusedLocationClient = fusedLocationProviderClient;
        Object systemService = activity.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
        Log.d("FASIH_Location", "Network Status: " + this.networkStatus.name());
        if (isAirplaneModeOn(appCompatActivity)) {
            handleAirplaneMode();
        } else if (!hasLocationPermission()) {
            handleResponse(null);
        } else {
            setupTimeoutAndStrategy();
            requestLocationBasedOnNetwork();
        }
    }

    private final NetworkStatus getNetworkStatus(Context context) {
        NetworkCapabilities networkCapabilities;
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork != null && (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) != null) {
            if (!networkCapabilities.hasTransport(1) && !networkCapabilities.hasTransport(0)) {
                return NetworkStatus.OFFLINE;
            }
            if (networkCapabilities.hasTransport(0)) {
                return getSignalStrength(context) <= 1 ? NetworkStatus.POOR_SIGNAL : NetworkStatus.GOOD_SIGNAL;
            }
            return NetworkStatus.GOOD_SIGNAL;
        }
        return NetworkStatus.OFFLINE;
    }

    private final int getSignalStrength(Context context) {
        try {
            Object systemService = context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
            return 2;
        } catch (Exception e) {
            Log.w("FASIH_Location", "Cannot get signal strength", e);
            return 1;
        }
    }

    private final boolean isAirplaneModeOn(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "airplane_mode_on", 0) != 0;
    }

    private final void handleAirplaneMode() {
        Log.d("FASIH_Location", "Airplane mode detected");
        LocationManager locationManager = this.locationManager;
        if (locationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            locationManager = null;
        }
        if (locationManager.isProviderEnabled("gps")) {
            Log.d("FASIH_Location", "GPS available in airplane mode");
            Toast.makeText(this.__activity, "Mode pesawat: Menggunakan GPS saja (tanpa bantuan internet)", 1).show();
            this.networkStatus = NetworkStatus.OFFLINE;
            setupTimeoutAndStrategy();
            requestGPSOnlyMode();
            return;
        }
        Log.w("FASIH_Location", "GPS not available in airplane mode");
        Toast.makeText(this.__activity, "Mode pesawat dan GPS tidak aktif. Aktifkan GPS untuk mendapatkan lokasi.", 1).show();
        handleResponse(null);
    }

    private final void setupTimeoutAndStrategy() {
        long j;
        int i = WhenMappings.$EnumSwitchMapping$0[this.networkStatus.ordinal()];
        if (i == 1) {
            j = 15000;
        } else if (i == 2) {
            j = 30000;
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            j = 45000;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        this.timeoutHandler = handler;
        handler.postDelayed(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                GetLocationHelper.setupTimeoutAndStrategy$lambda$0(this.f$0);
            }
        }, j);
        Log.d("FASIH_Location", "Timeout set to " + (j / 1000) + "s for " + this.networkStatus.name());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupTimeoutAndStrategy$lambda$0(GetLocationHelper this$0) {
        String str;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.locationReceived) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[this$0.networkStatus.ordinal()];
        if (i == 1) {
            str = "Gagal mendapatkan lokasi dalam 15 detik. Pastikan GPS aktif.";
        } else if (i == 2) {
            str = "Sinyal lemah: GPS membutuhkan waktu lebih lama. Tunggu sebentar atau pindah ke area dengan sinyal lebih baik.";
        } else {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            }
            str = "Mode offline: GPS membutuhkan kurang lebih 1 menit. Pastikan Anda di area terbuka tanpa halangan.";
        }
        Toast.makeText(this$0.__activity, str, 1).show();
        this$0.cleanupAndCallback(null, null, null, null);
    }

    private final void requestLocationBasedOnNetwork() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.networkStatus.ordinal()];
        if (i == 1) {
            requestOnlineMode();
        } else if (i == 2) {
            requestPoorSignalMode();
        } else {
            if (i != 3) {
                return;
            }
            requestGPSOnlyMode();
        }
    }

    private final void requestOnlineMode() {
        Log.d("FASIH_Location", "Using ONLINE mode - good signal");
        tryLastKnownLocation();
        requestFusedLocation(100, 1000L, 2000L);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                GetLocationHelper.requestOnlineMode$lambda$1(this.f$0);
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestOnlineMode$lambda$1(GetLocationHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.locationReceived) {
            return;
        }
        this$0.requestGPSLocation();
    }

    private final void requestPoorSignalMode() {
        Log.d("FASIH_Location", "Using POOR SIGNAL mode - prioritize GPS");
        Toast.makeText(this.__activity, "Sinyal lemah terdeteksi. Mengutamakan GPS untuk akurasi yang lebih baik.", 1).show();
        tryLastKnownLocation();
        requestGPSLocation();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                GetLocationHelper.requestPoorSignalMode$lambda$2(this.f$0);
            }
        }, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestPoorSignalMode$lambda$2(GetLocationHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.locationReceived) {
            return;
        }
        this$0.requestFusedLocation(102, 5000L, 10000L);
    }

    private final void requestGPSOnlyMode() {
        Log.d("FASIH_Location", "Using GPS ONLY mode - no network assistance");
        AppCompatActivity appCompatActivity = this.__activity;
        Intrinsics.checkNotNull(appCompatActivity);
        Toast.makeText(this.__activity, (isAirplaneModeOn(appCompatActivity) ? "Mode pesawat: Menggunakan GPS murni" : "Mode offline: Menggunakan GPS murni").concat(". Proses mungkin memerlukan 1-2 menit."), 1).show();
        tryLastKnownLocation();
        requestGPSLocation();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                GetLocationHelper.requestGPSOnlyMode$lambda$3(this.f$0);
            }
        }, 30000L);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                GetLocationHelper.requestGPSOnlyMode$lambda$4(this.f$0);
            }
        }, 60000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestGPSOnlyMode$lambda$3(GetLocationHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.locationReceived) {
            return;
        }
        Log.d("FASIH_Location", "GPS retry attempt 1");
        this$0.requestGPSLocation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestGPSOnlyMode$lambda$4(GetLocationHelper this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.locationReceived) {
            return;
        }
        Log.d("FASIH_Location", "GPS retry attempt 2");
        this$0.requestGPSLocation();
    }

    private final void tryLastKnownLocation() {
        try {
            if (hasLocationPermission()) {
                LocationManager locationManager = this.locationManager;
                FusedLocationProviderClient fusedLocationProviderClient = null;
                if (locationManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                    locationManager = null;
                }
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null && isLocationFresh(lastKnownLocation)) {
                    Log.d("FASIH_Location", "Using GPS last known location");
                    handleResponse(lastKnownLocation);
                } else if (this.networkStatus != NetworkStatus.OFFLINE) {
                    FusedLocationProviderClient fusedLocationProviderClient2 = this.mFusedLocationClient;
                    if (fusedLocationProviderClient2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                    } else {
                        fusedLocationProviderClient = fusedLocationProviderClient2;
                    }
                    Task<Location> lastLocation = fusedLocationProviderClient.getLastLocation();
                    final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper.tryLastKnownLocation.1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Location location) {
                            invoke2(location);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(Location location) {
                            if (location == null || !GetLocationHelper.this.isLocationFresh(location) || GetLocationHelper.this.locationReceived) {
                                return;
                            }
                            Log.d("FASIH_Location", "Using Fused last known location");
                            GetLocationHelper.this.handleResponse(location);
                        }
                    };
                    lastLocation.addOnSuccessListener(new OnSuccessListener() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda2
                        @Override // com.google.android.gms.tasks.OnSuccessListener
                        public final void onSuccess(Object obj) {
                            GetLocationHelper.tryLastKnownLocation$lambda$5(function1, obj);
                        }
                    }).addOnFailureListener(new OnFailureListener() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda3
                        @Override // com.google.android.gms.tasks.OnFailureListener
                        public final void onFailure(Exception exc) {
                            GetLocationHelper.tryLastKnownLocation$lambda$6(exc);
                        }
                    });
                }
            }
        } catch (SecurityException e) {
            Log.e("FASIH_Location", "Security exception", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tryLastKnownLocation$lambda$5(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void tryLastKnownLocation$lambda$6(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.w("FASIH_Location", "Fused last known failed", e);
    }

    private final void requestFusedLocation(int priority, long interval, long maxDelay) {
        try {
            if (!hasLocationPermission() || this.networkStatus == NetworkStatus.OFFLINE) {
                return;
            }
            LocationRequest locationRequestBuild = new LocationRequest.Builder(priority, interval).setMinUpdateIntervalMillis(interval / 2).setMaxUpdateDelayMillis(maxDelay).build();
            Intrinsics.checkNotNullExpressionValue(locationRequestBuild, "Builder(priority, interv…                 .build()");
            Log.d("FASIH_Location", "Requesting Fused location with priority " + priority);
            FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                fusedLocationProviderClient = null;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequestBuild, this.mLocationCallback, Looper.getMainLooper());
        } catch (SecurityException e) {
            Log.e("FASIH_Location", "Security exception fused", e);
        }
    }

    private final void requestGPSLocation() {
        try {
            if (hasLocationPermission()) {
                LocationManager locationManager = this.locationManager;
                LocationManager locationManager2 = null;
                if (locationManager == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                    locationManager = null;
                }
                if (locationManager.isProviderEnabled("gps")) {
                    Log.d("FASIH_Location", "Requesting GPS location");
                    LocationManager locationManager3 = this.locationManager;
                    if (locationManager3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("locationManager");
                    } else {
                        locationManager2 = locationManager3;
                    }
                    locationManager2.requestLocationUpdates("gps", 1000L, 0.0f, this.gpsLocationListener, Looper.getMainLooper());
                    return;
                }
            }
            Log.w("FASIH_Location", "GPS not available");
        } catch (SecurityException e) {
            Log.e("FASIH_Location", "Security exception GPS", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLocationFresh(Location location) {
        int i;
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.networkStatus.ordinal()];
        if (i2 == 1) {
            i = 300000;
        } else if (i2 == 2) {
            i = 900000;
        } else {
            if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = 1800000;
        }
        return System.currentTimeMillis() - location.getTime() < ((long) i);
    }

    private final boolean hasLocationPermission() {
        AppCompatActivity appCompatActivity = this.__activity;
        Intrinsics.checkNotNull(appCompatActivity);
        return ContextCompat.checkSelfPermission(appCompatActivity, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleResponse(Location location) {
        float f;
        if (this.locationReceived) {
            return;
        }
        this.locationReceived = true;
        Boolean boolValueOf = null;
        Double dValueOf = location != null ? Double.valueOf(location.getLatitude()) : null;
        Double dValueOf2 = location != null ? Double.valueOf(location.getLongitude()) : null;
        Float fValueOf = location != null ? Float.valueOf(location.getAccuracy()) : null;
        if (Build.VERSION.SDK_INT >= 31 && location != null) {
            boolValueOf = Boolean.valueOf(location.isMock());
        }
        Log.d("FASIH_Location", "Final result (" + this.networkStatus.name() + ") - Lat: " + dValueOf + ", Lng: " + dValueOf2 + ", Accuracy: " + fValueOf);
        if (location != null && fValueOf != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[this.networkStatus.ordinal()];
            if (i == 1) {
                f = 20.0f;
            } else if (i == 2) {
                f = 30.0f;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                f = 50.0f;
            }
            if (fValueOf.floatValue() > f) {
                Toast.makeText(this.__activity, "Akurasi GPS: " + ((int) fValueOf.floatValue()) + "m (" + this.networkStatus.name() + "). " + (this.networkStatus == NetworkStatus.OFFLINE ? "Normal untuk mode offline." : "Coba di area lebih terbuka untuk akurasi yang lebih baik."), 0).show();
            }
        }
        cleanupAndCallback(dValueOf, dValueOf2, fValueOf, boolValueOf);
    }

    private final void cleanupAndCallback(Double lat, Double lng, Float accuracy, Boolean isMock) {
        Handler handler = this.timeoutHandler;
        if (handler != null) {
            handler.removeCallbacks(new Runnable() { // from class: id.go.bpsfasih.utils.helper.location.GetLocationHelper$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    GetLocationHelper.cleanupAndCallback$lambda$7();
                }
            });
        }
        try {
            FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
            LocationManager locationManager = null;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                fusedLocationProviderClient = null;
            }
            fusedLocationProviderClient.removeLocationUpdates(this.mLocationCallback);
            LocationManager locationManager2 = this.locationManager;
            if (locationManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("locationManager");
            } else {
                locationManager = locationManager2;
            }
            locationManager.removeUpdates(this.gpsLocationListener);
        } catch (SecurityException e) {
            Log.e("FASIH_Location", "Cleanup error", e);
        }
        LocCallback locCallback = this.__locCallback;
        if (locCallback != null) {
            locCallback.result(lat, lng, accuracy, isMock);
        }
    }
}
