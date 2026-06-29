package id.ipd.mapipd.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import id.ipd.mapipd.R;
import id.ipd.mapipd.databinding.ActivityMapIpdCurrentLocationBinding;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CurrentLocationMapActivity.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010!\u001a\u00020\u0010H\u0002J<\u0010\"\u001a\u00020\u00102\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2(\b\u0002\u0010\f\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rJ\b\u0010#\u001a\u00020\u0010H\u0002J\b\u0010$\u001a\u00020\u0010H\u0002J\u0012\u0010%\u001a\u00020\u00102\b\u0010&\u001a\u0004\u0018\u00010'H\u0014J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020\u0016H\u0016J+\u0010*\u001a\u00020\u00102\u0006\u0010+\u001a\u00020\u00052\f\u0010,\u001a\b\u0012\u0004\u0012\u00020 0-2\u0006\u0010.\u001a\u00020/H\u0016¢\u0006\u0002\u00100J\b\u00101\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR.\u0010\f\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lid/ipd/mapipd/ui/CurrentLocationMapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "()V", "REQUEST_CODE", "", "binding", "Lid/ipd/mapipd/databinding/ActivityMapIpdCurrentLocationBinding;", "getBinding", "()Lid/ipd/mapipd/databinding/ActivityMapIpdCurrentLocationBinding;", "binding$delegate", "Lkotlin/Lazy;", "listenerActionButton", "Lkotlin/Function3;", "", "", "", "mCurrLocationMarker", "Lcom/google/android/gms/maps/model/Marker;", "mFusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "mGoogleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mLastLocation", "Landroid/location/Location;", "mLocationCallback", "Lcom/google/android/gms/location/LocationCallback;", "mLocationRequest", "Lcom/google/android/gms/location/LocationRequest;", "mapFragment", "Lcom/google/android/gms/maps/SupportMapFragment;", "textActionButton", "", "checkLocationPermission", "init", "initListener", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "updateView", "mapipd_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class CurrentLocationMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Function3<? super Double, ? super Double, ? super Float, Unit> listenerActionButton;
    private Marker mCurrLocationMarker;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mGoogleMap;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private SupportMapFragment mapFragment;
    private final int REQUEST_CODE = 3300;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<ActivityMapIpdCurrentLocationBinding>() { // from class: id.ipd.mapipd.ui.CurrentLocationMapActivity$binding$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityMapIpdCurrentLocationBinding invoke() {
            return ActivityMapIpdCurrentLocationBinding.inflate(this.this$0.getLayoutInflater());
        }
    });
    private LocationCallback mLocationCallback = new LocationCallback() { // from class: id.ipd.mapipd.ui.CurrentLocationMapActivity$mLocationCallback$1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            Marker marker;
            Intrinsics.checkNotNullParameter(locationResult, "locationResult");
            List<Location> locations = locationResult.getLocations();
            Intrinsics.checkNotNullExpressionValue(locations, "locationResult.locations");
            if (!locations.isEmpty()) {
                Location location = (Location) CollectionsKt.last((List) locations);
                this.this$0.mLastLocation = location;
                if (this.this$0.mCurrLocationMarker != null && (marker = this.this$0.mCurrLocationMarker) != null) {
                    marker.remove();
                }
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Current Position");
                GoogleMap googleMap = this.this$0.mGoogleMap;
                GoogleMap googleMap2 = null;
                if (googleMap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                    googleMap = null;
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 16.0f));
                CurrentLocationMapActivity currentLocationMapActivity = this.this$0;
                GoogleMap googleMap3 = currentLocationMapActivity.mGoogleMap;
                if (googleMap3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                } else {
                    googleMap2 = googleMap3;
                }
                currentLocationMapActivity.mCurrLocationMarker = googleMap2.addMarker(markerOptions);
                this.this$0.updateView();
            }
        }
    };
    private String textActionButton = "";

    private final ActivityMapIpdCurrentLocationBinding getBinding() {
        return (ActivityMapIpdCurrentLocationBinding) this.binding.getValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void init$default(CurrentLocationMapActivity currentLocationMapActivity, String str, Function3 function3, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: init");
        }
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            function3 = null;
        }
        currentLocationMapActivity.init(str, function3);
    }

    public final void init(String textActionButton, Function3<? super Double, ? super Double, ? super Float, Unit> listenerActionButton) {
        this.listenerActionButton = listenerActionButton;
        if (textActionButton == null) {
            textActionButton = "";
        }
        this.textActionButton = textActionButton;
        initView();
        initListener();
    }

    private final void initListener() {
        Unit unit;
        final Function3<? super Double, ? super Double, ? super Float, Unit> function3 = this.listenerActionButton;
        if (function3 != null) {
            getBinding().actionB.setText(this.textActionButton);
            getBinding().actionB.setOnClickListener(new View.OnClickListener() { // from class: id.ipd.mapipd.ui.CurrentLocationMapActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CurrentLocationMapActivity.initListener$lambda$1$lambda$0(function3, this, view);
                }
            });
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            getBinding().actionB.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1$lambda$0(Function3 listener, CurrentLocationMapActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Location location = this$0.mLastLocation;
        Double dValueOf = location != null ? Double.valueOf(location.getLatitude()) : null;
        Location location2 = this$0.mLastLocation;
        Double dValueOf2 = location2 != null ? Double.valueOf(location2.getLongitude()) : null;
        Location location3 = this$0.mLastLocation;
        listener.invoke(dValueOf, dValueOf2, location3 != null ? Float.valueOf(location3.getAccuracy()) : null);
    }

    private final void initView() {
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.mFusedLocationClient = fusedLocationProviderClient;
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.map);
        Intrinsics.checkNotNull(fragmentFindFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        SupportMapFragment supportMapFragment = (SupportMapFragment) fragmentFindFragmentById;
        this.mapFragment = supportMapFragment;
        if (supportMapFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mapFragment");
            supportMapFragment = null;
        }
        supportMapFragment.getMapAsync(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateView() {
        TextView textView = getBinding().latTv;
        Location location = this.mLastLocation;
        textView.setText(String.valueOf(location != null ? Double.valueOf(location.getLatitude()) : null));
        TextView textView2 = getBinding().longTv;
        Location location2 = this.mLastLocation;
        textView2.setText(String.valueOf(location2 != null ? Double.valueOf(location2.getLongitude()) : null));
        TextView textView3 = getBinding().accTv;
        Location location3 = this.mLastLocation;
        textView3.setText(String.valueOf(location3 != null ? Float.valueOf(location3.getAccuracy()) : null));
    }

    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        this.mGoogleMap = googleMap;
        LocationRequest locationRequest = null;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap = null;
        }
        googleMap.setPadding(10, 10, 10, 10);
        GoogleMap googleMap2 = this.mGoogleMap;
        if (googleMap2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap2 = null;
        }
        googleMap2.setMapType(2);
        LocationRequest locationRequestBuild = new LocationRequest.Builder(100, 10000L).build();
        Intrinsics.checkNotNullExpressionValue(locationRequestBuild, "Builder(Priority.PRIORIT…000)\n            .build()");
        this.mLocationRequest = locationRequestBuild;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                fusedLocationProviderClient = null;
            }
            LocationRequest locationRequest2 = this.mLocationRequest;
            if (locationRequest2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLocationRequest");
            } else {
                locationRequest = locationRequest2;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.mLocationCallback, Looper.myLooper());
            return;
        }
        checkLocationPermission();
    }

    private final void checkLocationPermission() {
        CurrentLocationMapActivity currentLocationMapActivity = this;
        if (ActivityCompat.checkSelfPermission(currentLocationMapActivity, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            CurrentLocationMapActivity currentLocationMapActivity2 = this;
            if (ActivityCompat.shouldShowRequestPermissionRationale(currentLocationMapActivity2, "android.permission.ACCESS_FINE_LOCATION")) {
                new AlertDialog.Builder(currentLocationMapActivity).setTitle("Location Permission Needed").setMessage("This app needs the Location permission, please accept to use location functionality").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: id.ipd.mapipd.ui.CurrentLocationMapActivity$$ExternalSyntheticLambda1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        CurrentLocationMapActivity.checkLocationPermission$lambda$3(this.f$0, dialogInterface, i);
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(currentLocationMapActivity2, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this.REQUEST_CODE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$3(CurrentLocationMapActivity this$0, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this$0.REQUEST_CODE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.REQUEST_CODE) {
            if ((!(grantResults.length == 0)) && grantResults[0] == 0) {
                if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
                    FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
                    LocationRequest locationRequest = null;
                    if (fusedLocationProviderClient == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                        fusedLocationProviderClient = null;
                    }
                    LocationRequest locationRequest2 = this.mLocationRequest;
                    if (locationRequest2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mLocationRequest");
                    } else {
                        locationRequest = locationRequest2;
                    }
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.mLocationCallback, Looper.myLooper());
                    return;
                }
                return;
            }
            Toast.makeText(this, "permission denied", 1).show();
        }
    }
}
