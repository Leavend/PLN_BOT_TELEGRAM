package id.ipd.mapipd.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.PopupWindow;
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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import id.ipd.mapipd.R;
import id.ipd.mapipd.databinding.ActivityMapIpdBinding;
import id.ipd.mapipd.model.ItemLoc;
import id.ipd.mapipd.util.MarkerHelper;
import java.util.HashMap;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MarkerPlotMapActivity.kt */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u00102\u001a\u00020\u001bH\u0002J\u000e\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020/J\u0006\u00105\u001a\u00020\u001bJP\u00106\u001a\u00020\u001b2\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00172\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\b\b\u0002\u00101\u001a\u00020/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001a2\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019JN\u00107\u001a\u00020\u001b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u00172\n\b\u0002\u0010.\u001a\u0004\u0018\u00010/2\b\b\u0002\u00101\u001a\u00020/2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u001a2\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019J\b\u00108\u001a\u00020\u001bH\u0002J\u0012\u00109\u001a\u00020\u001b2\b\u0010:\u001a\u0004\u0018\u00010;H\u0014J\u0010\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020 H\u0016J\u0010\u0010A\u001a\u00020\u00142\u0006\u0010=\u001a\u00020\u0010H\u0016J+\u0010B\u001a\u00020\u001b2\u0006\u0010C\u001a\u00020\u00072\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a0E2\u0006\u0010F\u001a\u00020GH\u0016¢\u0006\u0002\u0010HJ\u000e\u0010I\u001a\u00020\u001b2\u0006\u0010J\u001a\u00020\u0010R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fj\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020/X\u0082.¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lid/ipd/mapipd/ui/MarkerPlotMapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/google/android/gms/maps/OnMapReadyCallback;", "Lcom/google/android/gms/maps/GoogleMap$OnMarkerClickListener;", "Lcom/google/android/gms/maps/GoogleMap$OnMapClickListener;", "()V", "REQUEST_CODE", "", "binding", "Lid/ipd/mapipd/databinding/ActivityMapIpdBinding;", "getBinding", "()Lid/ipd/mapipd/databinding/ActivityMapIpdBinding;", "binding$delegate", "Lkotlin/Lazy;", "data", "Ljava/util/HashMap;", "Lcom/google/android/gms/maps/model/Marker;", "Lid/ipd/mapipd/model/ItemLoc;", "Lkotlin/collections/HashMap;", "isSubSegmen", "", "Ljava/lang/Boolean;", "listLocation", "", "listenerButton", "Lkotlin/Function1;", "", "", "mCurrLocationMarker", "mFusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "mGoogleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mHeight", "mLastLocation", "Landroid/location/Location;", "mLocationCallback", "Lcom/google/android/gms/location/LocationCallback;", "mLocationRequest", "Lcom/google/android/gms/location/LocationRequest;", "mMarker", "mPopupWindow", "Landroid/widget/PopupWindow;", "mWidth", "mapFragment", "Lcom/google/android/gms/maps/SupportMapFragment;", "markerIcon", "Landroid/graphics/Bitmap;", "textButton", "userIcon", "checkLocationPermission", "convertBitmapSize", "bitmap", "hideDialog", "initData", "initDataSegmen", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapClick", "p0", "Lcom/google/android/gms/maps/model/LatLng;", "onMapReady", "googleMap", "onMarkerClick", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "showDialog", "marker", "mapipd_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class MarkerPlotMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {
    private List<ItemLoc> listLocation;
    private Function1<? super String, Unit> listenerButton;
    private Marker mCurrLocationMarker;
    private FusedLocationProviderClient mFusedLocationClient;
    private GoogleMap mGoogleMap;
    private int mHeight;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    private Marker mMarker;
    private PopupWindow mPopupWindow;
    private int mWidth;
    private SupportMapFragment mapFragment;
    private Bitmap markerIcon;
    private String textButton;
    private Bitmap userIcon;
    private final int REQUEST_CODE = 3300;
    private HashMap<Marker, ItemLoc> data = new HashMap<>();
    private Boolean isSubSegmen = false;

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<ActivityMapIpdBinding>() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity$binding$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityMapIpdBinding invoke() {
            return ActivityMapIpdBinding.inflate(this.this$0.getLayoutInflater());
        }
    });
    private LocationCallback mLocationCallback = new LocationCallback() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity$mLocationCallback$1
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
                Bitmap bitmap = this.this$0.userIcon;
                GoogleMap googleMap = null;
                if (bitmap == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("userIcon");
                    bitmap = null;
                }
                markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                MarkerPlotMapActivity markerPlotMapActivity = this.this$0;
                GoogleMap googleMap2 = markerPlotMapActivity.mGoogleMap;
                if (googleMap2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                } else {
                    googleMap = googleMap2;
                }
                markerPlotMapActivity.mCurrLocationMarker = googleMap.addMarker(markerOptions);
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityMapIpdBinding getBinding() {
        return (ActivityMapIpdBinding) this.binding.getValue();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
    }

    public static /* synthetic */ void initData$default(MarkerPlotMapActivity markerPlotMapActivity, List list, Bitmap bitmap, Bitmap bitmap2, String str, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initData");
        }
        Bitmap bitmap3 = (i & 2) != 0 ? null : bitmap;
        if ((i & 4) != 0) {
            Drawable drawable = markerPlotMapActivity.getDrawable(R.drawable.ic_user);
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            bitmap2 = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap2, "getDrawable(R.drawable.i…as BitmapDrawable).bitmap");
        }
        markerPlotMapActivity.initData(list, bitmap3, bitmap2, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : function1);
    }

    public final void initData(List<ItemLoc> listLocation, Bitmap markerIcon, Bitmap userIcon, String textButton, Function1<? super String, Unit> listenerButton) {
        Intrinsics.checkNotNullParameter(userIcon, "userIcon");
        this.listLocation = listLocation;
        this.markerIcon = markerIcon != null ? convertBitmapSize(markerIcon) : null;
        this.userIcon = convertBitmapSize(userIcon);
        this.textButton = textButton;
        this.listenerButton = listenerButton;
        initView();
    }

    public static /* synthetic */ void initDataSegmen$default(MarkerPlotMapActivity markerPlotMapActivity, List list, Bitmap bitmap, Bitmap bitmap2, String str, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initDataSegmen");
        }
        Bitmap bitmap3 = (i & 2) != 0 ? null : bitmap;
        if ((i & 4) != 0) {
            Drawable drawable = markerPlotMapActivity.getDrawable(R.drawable.ic_user);
            Intrinsics.checkNotNull(drawable, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            bitmap2 = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkNotNullExpressionValue(bitmap2, "getDrawable(R.drawable.i…as BitmapDrawable).bitmap");
        }
        markerPlotMapActivity.initDataSegmen(list, bitmap3, bitmap2, (i & 8) != 0 ? null : str, (i & 16) != 0 ? null : function1);
    }

    public final void initDataSegmen(List<ItemLoc> listLocation, Bitmap markerIcon, Bitmap userIcon, String textButton, Function1<? super String, Unit> listenerButton) {
        Intrinsics.checkNotNullParameter(listLocation, "listLocation");
        Intrinsics.checkNotNullParameter(userIcon, "userIcon");
        this.listLocation = listLocation;
        this.markerIcon = markerIcon != null ? convertBitmapSize(markerIcon) : null;
        this.userIcon = convertBitmapSize(userIcon);
        this.textButton = textButton;
        this.listenerButton = listenerButton;
        this.isSubSegmen = true;
        initView();
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

    public final Bitmap convertBitmapSize(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmap, 100, 100, false);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(bitmap, width, height, false)");
        return bitmapCreateScaledBitmap;
    }

    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        this.mGoogleMap = googleMap;
        GoogleMap googleMap2 = null;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap = null;
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        GoogleMap googleMap3 = this.mGoogleMap;
        if (googleMap3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap3 = null;
        }
        googleMap3.setPadding(10, 10, 10, 10);
        List<ItemLoc> list = this.listLocation;
        if (list != null) {
            for (ItemLoc itemLoc : list) {
                MarkerOptions markerOptionsTitle = new MarkerOptions().position(itemLoc.getPosition()).title(itemLoc.getTitle());
                Intrinsics.checkNotNullExpressionValue(markerOptionsTitle, "MarkerOptions()\n        …         .title(it.title)");
                Bitmap bitmap = this.markerIcon;
                if (bitmap != null) {
                    Intrinsics.checkNotNull(bitmap);
                    markerOptionsTitle.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                }
                GoogleMap googleMap4 = this.mGoogleMap;
                if (googleMap4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                    googleMap4 = null;
                }
                Marker markerAddMarker = googleMap4.addMarker(markerOptionsTitle);
                HashMap<Marker, ItemLoc> map = this.data;
                Intrinsics.checkNotNull(markerAddMarker);
                map.put(markerAddMarker, itemLoc);
            }
        }
        LocationRequest locationRequestBuild = new LocationRequest.Builder(100, 1000L).build();
        Intrinsics.checkNotNullExpressionValue(locationRequestBuild, "Builder(Priority.PRIORIT…000)\n            .build()");
        this.mLocationRequest = locationRequestBuild;
        if (ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            FusedLocationProviderClient fusedLocationProviderClient = this.mFusedLocationClient;
            if (fusedLocationProviderClient == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                fusedLocationProviderClient = null;
            }
            LocationRequest locationRequest = this.mLocationRequest;
            if (locationRequest == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mLocationRequest");
                locationRequest = null;
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.mLocationCallback, Looper.myLooper());
            GoogleMap googleMap5 = this.mGoogleMap;
            if (googleMap5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                googleMap5 = null;
            }
            googleMap5.setMyLocationEnabled(true);
        } else {
            checkLocationPermission();
        }
        GoogleMap googleMap6 = this.mGoogleMap;
        if (googleMap6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap6 = null;
        }
        googleMap6.setOnMarkerClickListener(this);
        GoogleMap googleMap7 = this.mGoogleMap;
        if (googleMap7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
            googleMap7 = null;
        }
        googleMap7.setOnMapClickListener(this);
        if (Intrinsics.areEqual((Object) this.isSubSegmen, (Object) true)) {
            List<ItemLoc> list2 = this.listLocation;
            Integer numValueOf = list2 != null ? Integer.valueOf(list2.size()) : null;
            Intrinsics.checkNotNull(numValueOf);
            if (numValueOf.intValue() > 0) {
                MarkerHelper markerHelper = MarkerHelper.INSTANCE;
                List<ItemLoc> list3 = this.listLocation;
                Intrinsics.checkNotNull(list3);
                double d = list3.get(0).getPosition().latitude;
                List<ItemLoc> list4 = this.listLocation;
                Intrinsics.checkNotNull(list4);
                PolygonOptions polygonOptionsAddAll = new PolygonOptions().addAll(markerHelper.getRectangleCorner(new LatLng(d, list4.get(0).getPosition().longitude), 70.710678d));
                Intrinsics.checkNotNullExpressionValue(polygonOptionsAddAll, "PolygonOptions().addAll(polygon)");
                GoogleMap googleMap8 = this.mGoogleMap;
                if (googleMap8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                } else {
                    googleMap2 = googleMap8;
                }
                googleMap2.addPolygon(polygonOptionsAddAll);
            }
        }
    }

    private final void checkLocationPermission() {
        MarkerPlotMapActivity markerPlotMapActivity = this;
        if (ActivityCompat.checkSelfPermission(markerPlotMapActivity, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            MarkerPlotMapActivity markerPlotMapActivity2 = this;
            if (ActivityCompat.shouldShowRequestPermissionRationale(markerPlotMapActivity2, "android.permission.ACCESS_FINE_LOCATION")) {
                new AlertDialog.Builder(markerPlotMapActivity).setTitle("Location Permission Needed").setMessage("This app needs the Location permission, please accept to use location functionality").setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity$$ExternalSyntheticLambda0
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        MarkerPlotMapActivity.checkLocationPermission$lambda$1(this.f$0, dialogInterface, i);
                    }
                }).create().show();
            } else {
                ActivityCompat.requestPermissions(markerPlotMapActivity2, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, this.REQUEST_CODE);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkLocationPermission$lambda$1(MarkerPlotMapActivity this$0, DialogInterface dialogInterface, int i) {
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
                    GoogleMap googleMap = null;
                    if (fusedLocationProviderClient == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFusedLocationClient");
                        fusedLocationProviderClient = null;
                    }
                    LocationRequest locationRequest = this.mLocationRequest;
                    if (locationRequest == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mLocationRequest");
                        locationRequest = null;
                    }
                    fusedLocationProviderClient.requestLocationUpdates(locationRequest, this.mLocationCallback, Looper.myLooper());
                    GoogleMap googleMap2 = this.mGoogleMap;
                    if (googleMap2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mGoogleMap");
                    } else {
                        googleMap = googleMap2;
                    }
                    googleMap.setMyLocationEnabled(true);
                    return;
                }
                return;
            }
            Toast.makeText(this, "permission denied", 1).show();
        }
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        showDialog(p0);
        return true;
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMapClickListener
    public void onMapClick(LatLng p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        hideDialog();
    }

    public final void showDialog(Marker marker) {
        Unit unit;
        Unit unit2;
        Intrinsics.checkNotNullParameter(marker, "marker");
        final ItemLoc itemLoc = this.data.get(marker);
        if (itemLoc != null) {
            if (itemLoc.getTitle() != null) {
                getBinding().titleTv.setVisibility(0);
                getBinding().titleTv.setText(itemLoc.getTitle());
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                getBinding().titleTv.setVisibility(8);
            }
            if (itemLoc.getDesription() != null) {
                getBinding().descriptionTv.setVisibility(0);
                getBinding().descriptionTv.setText(itemLoc.getDesription());
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            if (unit2 == null) {
                getBinding().descriptionTv.setVisibility(8);
            }
            getBinding().directionB.setOnClickListener(new View.OnClickListener() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MarkerPlotMapActivity.showDialog$lambda$8$lambda$6(itemLoc, this, view);
                }
            });
            if (this.textButton != null && this.listenerButton != null) {
                getBinding().actionB.setVisibility(0);
                getBinding().actionB.setText(this.textButton);
                getBinding().actionB.setOnClickListener(new View.OnClickListener() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        MarkerPlotMapActivity.showDialog$lambda$8$lambda$7(this.f$0, itemLoc, view);
                    }
                });
            }
            getBinding().dialogCv.setVisibility(0);
            getBinding().dialogCv.setAlpha(0.0f);
            getBinding().dialogCv.setTranslationY(getBinding().dialogCv.getHeight());
            getBinding().dialogCv.animate().translationY(0.0f).alpha(1.0f).setListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$8$lambda$6(ItemLoc itemLoc, MarkerPlotMapActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("google.navigation:q=" + itemLoc.getPosition().latitude + "," + itemLoc.getPosition().longitude));
            intent.setPackage("com.google.android.apps.maps");
            this$0.startActivity(intent);
        } catch (Exception unused) {
            Toast.makeText(this$0, "Aplikasi Google Maps belum terpasang. Harap install aplikasi terlebih dahulu", 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialog$lambda$8$lambda$7(MarkerPlotMapActivity this$0, ItemLoc itemLoc, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super String, Unit> function1 = this$0.listenerButton;
        if (function1 != null) {
            function1.invoke(itemLoc.getId());
        }
    }

    public final void hideDialog() {
        getBinding().dialogCv.animate().translationY(getBinding().dialogCv.getHeight()).alpha(0.0f).setDuration(300L).setListener(new AnimatorListenerAdapter() { // from class: id.ipd.mapipd.ui.MarkerPlotMapActivity.hideDialog.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                super.onAnimationEnd(animation);
                MarkerPlotMapActivity.this.getBinding().dialogCv.setVisibility(8);
            }
        });
    }
}
