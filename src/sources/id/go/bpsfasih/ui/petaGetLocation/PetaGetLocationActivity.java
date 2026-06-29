package id.go.bpsfasih.ui.petaGetLocation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.domain.models.LocationHistory;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.ipd.mapipd.ui.CurrentLocationMapActivity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: PetaGetLocationActivity.kt */
@Metadata(d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\b\b\u0007\u0018\u0000 92\u00020\u0001:\u00019B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\u0016\u0010'\u001a\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0002J\u0010\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u0010H\u0002J\u0010\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0015H\u0002J\u0010\u0010/\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u0015H\u0002J\b\u00100\u001a\u00020\u001bH\u0002J\u0012\u00101\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\u0010\u00104\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u00105\u001a\u00020\u001bH\u0014J\b\u00106\u001a\u00020\u001bH\u0014J\u0010\u00107\u001a\u00020\u001b2\u0006\u0010,\u001a\u00020\u0010H\u0002J\b\u00108\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0018\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001dR\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001dR\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001dR\u0010\u0010 \u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lid/go/bpsfasih/ui/petaGetLocation/PetaGetLocationActivity;", "Lid/ipd/mapipd/ui/CurrentLocationMapActivity;", "()V", "fakeGpsCheckHandler", "Landroid/os/Handler;", "fakeGpsCheckInterval", "", "fakeGpsCheckRunnable", "id/go/bpsfasih/ui/petaGetLocation/PetaGetLocationActivity$fakeGpsCheckRunnable$1", "Lid/go/bpsfasih/ui/petaGetLocation/PetaGetLocationActivity$fakeGpsCheckRunnable$1;", "fusedLocationClient", "Lcom/google/android/gms/location/FusedLocationProviderClient;", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "historyMarkers", "", "Lcom/google/android/gms/maps/model/Marker;", "isFakeGpsAlertShown", "", "mCurrLocationMarker", "mLastLocation", "Landroid/location/Location;", "mLocationCallback", "Lcom/google/android/gms/location/LocationCallback;", "onLocationSelected", "Lkotlin/Function3;", "", "", "selectedAccuracy", "Ljava/lang/Double;", "selectedLatitude", "selectedLongitude", "selectedMarker", "showCurrentLocation", "targetAssignmentId", "", "targetDataKey", "checkForFakeGps", "clearHistoryMarkers", "displayLocationHistory", "histories", "", "Lid/go/bpsfasih/domain/models/LocationHistory;", "getMarkerAccuracy", "marker", "isLocationFromMockProvicer", FirebaseAnalytics.Param.LOCATION, "isLocationFromMockProvider", "loadLocationHistory", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "onPause", "onResume", "showLocationConfirmationDialog", "updateView", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PetaGetLocationActivity extends CurrentLocationMapActivity {
    public static final String EXTRA_ASSIGNMENT_ID = "extra_assignment_id";
    public static final String EXTRA_DATA_KEY = "extra_data_key";
    public static final String EXTRA_SHOW_CURRENT_LOCATION = "extra_show_current_location";
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap googleMap;
    private boolean isFakeGpsAlertShown;
    private Marker mCurrLocationMarker;
    private Location mLastLocation;
    private Function3<? super Double, ? super Double, ? super Double, Unit> onLocationSelected;
    private Double selectedAccuracy;
    private Double selectedLatitude;
    private Double selectedLongitude;
    private Marker selectedMarker;
    private String targetAssignmentId;
    private String targetDataKey;
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final List<Marker> historyMarkers = new ArrayList();
    private boolean showCurrentLocation = true;
    private final Handler fakeGpsCheckHandler = new Handler(Looper.getMainLooper());
    private final long fakeGpsCheckInterval = 3000;
    private LocationCallback mLocationCallback = new LocationCallback() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$mLocationCallback$1
        @Override // com.google.android.gms.location.LocationCallback
        public void onLocationResult(LocationResult locationResult) {
            Intrinsics.checkNotNullParameter(locationResult, "locationResult");
            List<Location> locations = locationResult.getLocations();
            Intrinsics.checkNotNullExpressionValue(locations, "locationResult.locations");
            if (!locations.isEmpty()) {
                Location location = (Location) CollectionsKt.last((List) locations);
                PetaGetLocationActivity petaGetLocationActivity = this.this$0;
                Intrinsics.checkNotNullExpressionValue(location, "location");
                if (petaGetLocationActivity.isLocationFromMockProvicer(location)) {
                    return;
                }
                this.this$0.mLastLocation = location;
                if (this.this$0.showCurrentLocation) {
                    Marker marker = this.this$0.mCurrLocationMarker;
                    if (marker != null) {
                        marker.remove();
                    }
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    MarkerOptions markerOptionsTitle = new MarkerOptions().position(latLng).title("Current Position");
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf(location.getAccuracy())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    MarkerOptions markerOptionsIcon = markerOptionsTitle.snippet("Lokasi saat ini\nAccuracy: " + str + " meter").icon(BitmapDescriptorFactory.defaultMarker(0.0f));
                    Intrinsics.checkNotNullExpressionValue(markerOptionsIcon, "MarkerOptions()\n        …scriptorFactory.HUE_RED))");
                    PetaGetLocationActivity petaGetLocationActivity2 = this.this$0;
                    GoogleMap googleMap = petaGetLocationActivity2.googleMap;
                    GoogleMap googleMap2 = null;
                    if (googleMap == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                        googleMap = null;
                    }
                    petaGetLocationActivity2.mCurrLocationMarker = googleMap.addMarker(markerOptionsIcon);
                    GoogleMap googleMap3 = this.this$0.googleMap;
                    if (googleMap3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                    } else {
                        googleMap2 = googleMap3;
                    }
                    googleMap2.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
                }
                this.this$0.updateView();
            }
        }
    };
    private final PetaGetLocationActivity$fakeGpsCheckRunnable$1 fakeGpsCheckRunnable = new Runnable() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$fakeGpsCheckRunnable$1
        @Override // java.lang.Runnable
        public void run() {
            this.this$0.checkForFakeGps();
            if (this.this$0.isFinishing() || this.this$0.isFakeGpsAlertShown) {
                return;
            }
            this.this$0.fakeGpsCheckHandler.postDelayed(this, this.this$0.fakeGpsCheckInterval);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateView() {
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // id.ipd.mapipd.ui.CurrentLocationMapActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient((Activity) this);
        Intrinsics.checkNotNullExpressionValue(fusedLocationProviderClient, "getFusedLocationProviderClient(this)");
        this.fusedLocationClient = fusedLocationProviderClient;
        this.targetAssignmentId = getIntent().getStringExtra(EXTRA_ASSIGNMENT_ID);
        this.targetDataKey = getIntent().getStringExtra(EXTRA_DATA_KEY);
        this.showCurrentLocation = getIntent().getBooleanExtra(EXTRA_SHOW_CURRENT_LOCATION, true);
        Log.d(">>> assignment_id", this.targetAssignmentId);
        Log.d(">>> data_key", this.targetDataKey);
        checkForFakeGps();
        if (this.showCurrentLocation) {
            init("Ambil Lokasi", new Function3<Double, Double, Float, Unit>() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity.onCreate.1
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2, Float f) {
                    invoke2(d, d2, f);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Double d, Double d2, Float f) {
                    Double d3 = PetaGetLocationActivity.this.selectedLatitude;
                    double dDoubleValue = d3 != null ? d3.doubleValue() : d != null ? d.doubleValue() : 0.0d;
                    Double d4 = PetaGetLocationActivity.this.selectedLongitude;
                    double dDoubleValue2 = d4 != null ? d4.doubleValue() : d2 != null ? d2.doubleValue() : 0.0d;
                    Object obj = PetaGetLocationActivity.this.selectedAccuracy;
                    Object objValueOf = f;
                    if (obj != null) {
                        objValueOf = obj;
                    } else if (f == null) {
                        objValueOf = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
                    }
                    Intent intent = new Intent();
                    intent.putExtra(CommonCons.INSTANCE.getKEY_LATITUDE(), dDoubleValue);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), dDoubleValue2);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_ACCURACY(), (Serializable) objValueOf);
                    PetaGetLocationActivity.this.setResult(-1, intent);
                    PetaGetLocationActivity.this.finish();
                }
            });
            this.onLocationSelected = new Function3<Double, Double, Double, Unit>() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity.onCreate.2
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2, Double d3) {
                    invoke2(d, d2, d3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Double d, Double d2, Double d3) {
                    Intent intent = new Intent();
                    String key_latitude = CommonCons.INSTANCE.getKEY_LATITUDE();
                    double dDoubleValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    intent.putExtra(key_latitude, d != null ? d.doubleValue() : 0.0d);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), d2 != null ? d2.doubleValue() : 0.0d);
                    String key_accuracy = CommonCons.INSTANCE.getKEY_ACCURACY();
                    if (d3 != null) {
                        dDoubleValue = d3.doubleValue();
                    }
                    intent.putExtra(key_accuracy, dDoubleValue);
                    PetaGetLocationActivity.this.setResult(-1, intent);
                    PetaGetLocationActivity.this.finish();
                }
            };
        } else {
            init(null, null);
        }
    }

    @Override // id.ipd.mapipd.ui.CurrentLocationMapActivity, com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        super.onMapReady(googleMap);
        this.googleMap = googleMap;
        UiSettings uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
        googleMap.setMinZoomPreference(2.0f);
        googleMap.setMaxZoomPreference(20.0f);
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda3
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public final boolean onMarkerClick(Marker marker) {
                return PetaGetLocationActivity.onMapReady$lambda$1(this.f$0, marker);
            }
        });
        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda4
            @Override // com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener
            public final void onInfoWindowClick(Marker marker) {
                PetaGetLocationActivity.onMapReady$lambda$2(this.f$0, marker);
            }
        });
        loadLocationHistory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onMapReady$lambda$1(PetaGetLocationActivity this$0, Marker marker) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(marker, "marker");
        this$0.selectedMarker = marker;
        LatLng position = marker.getPosition();
        Intrinsics.checkNotNullExpressionValue(position, "marker.position");
        this$0.selectedLatitude = Double.valueOf(position.latitude);
        this$0.selectedLongitude = Double.valueOf(position.longitude);
        this$0.selectedAccuracy = Double.valueOf(this$0.getMarkerAccuracy(marker));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onMapReady$lambda$2(PetaGetLocationActivity this$0, Marker marker) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(marker, "marker");
        LatLng position = marker.getPosition();
        Intrinsics.checkNotNullExpressionValue(position, "marker.position");
        this$0.selectedLatitude = Double.valueOf(position.latitude);
        this$0.selectedLongitude = Double.valueOf(position.longitude);
        this$0.selectedAccuracy = Double.valueOf(this$0.getMarkerAccuracy(marker));
        this$0.showLocationConfirmationDialog(marker);
    }

    private final double getMarkerAccuracy(Marker marker) {
        ArrayList arrayList;
        if (Intrinsics.areEqual(marker, this.mCurrLocationMarker)) {
            return this.mLastLocation != null ? r9.getAccuracy() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        if (!this.historyMarkers.contains(marker)) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        int iIndexOf = this.historyMarkers.indexOf(marker);
        try {
            LocationHistory[] locationHistoryAsObject = FileHelper.INSTANCE.readLocationHistoryAsObject(Directory.INSTANCE.getABSOLUTEPATHENV() + "location_histories.json");
            if (locationHistoryAsObject == null || (arrayList = ArraysKt.toMutableList(locationHistoryAsObject)) == null) {
                arrayList = new ArrayList();
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                LocationHistory locationHistory = (LocationHistory) obj;
                if ((this.targetAssignmentId == null || Intrinsics.areEqual(locationHistory.getAssignment_id(), this.targetAssignmentId)) && (this.targetDataKey == null || Intrinsics.areEqual(locationHistory.getData_key(), this.targetDataKey))) {
                    arrayList2.add(obj);
                }
            }
            List listSortedWith = CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$getMarkerAccuracy$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Long longOrNull = StringsKt.toLongOrNull(((LocationHistory) t).getTimestamp());
                    Long lValueOf = Long.valueOf(longOrNull != null ? longOrNull.longValue() : 0L);
                    Long longOrNull2 = StringsKt.toLongOrNull(((LocationHistory) t2).getTimestamp());
                    return ComparisonsKt.compareValues(lValueOf, Long.valueOf(longOrNull2 != null ? longOrNull2.longValue() : 0L));
                }
            });
            return iIndexOf < listSortedWith.size() ? ((LocationHistory) listSortedWith.get(iIndexOf)).getAccuracy() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        } catch (Exception e) {
            e.printStackTrace();
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
    }

    private final void showLocationConfirmationDialog(final Marker marker) {
        LatLng position = marker.getPosition();
        Intrinsics.checkNotNullExpressionValue(position, "marker.position");
        AlertDialog.Builder title = new AlertDialog.Builder(this).setTitle("Konfirmasi Lokasi");
        String title2 = marker.getTitle();
        String snippet = marker.getSnippet();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(position.latitude)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String str2 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(position.longitude)}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        title.setMessage("Apakah Anda yakin memilih lokasi ini?\n\nTitle: " + title2 + "\nSnippet: " + snippet + "\nLat: " + str + "\nLng: " + str2).setPositiveButton("Ambil Lokasi", new DialogInterface.OnClickListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PetaGetLocationActivity.showLocationConfirmationDialog$lambda$5(marker, dialogInterface, i);
            }
        }).setNegativeButton("Batal", new DialogInterface.OnClickListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda6
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PetaGetLocationActivity.showLocationConfirmationDialog$lambda$6(this.f$0, marker, dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLocationConfirmationDialog$lambda$5(Marker marker, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(marker, "$marker");
        marker.hideInfoWindow();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showLocationConfirmationDialog$lambda$6(PetaGetLocationActivity this$0, Marker marker, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(marker, "$marker");
        dialogInterface.dismiss();
        this$0.selectedLatitude = null;
        this$0.selectedLongitude = null;
        this$0.selectedAccuracy = null;
        marker.hideInfoWindow();
    }

    private final void loadLocationHistory() {
        ArrayList arrayList;
        try {
            LocationHistory[] locationHistoryAsObject = FileHelper.INSTANCE.readLocationHistoryAsObject(Directory.INSTANCE.getABSOLUTEPATHENV() + "location_histories.json");
            if (locationHistoryAsObject == null || (arrayList = ArraysKt.toMutableList(locationHistoryAsObject)) == null) {
                arrayList = new ArrayList();
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (true) {
                boolean z = true;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                LocationHistory locationHistory = (LocationHistory) next;
                if ((this.targetAssignmentId != null && !Intrinsics.areEqual(locationHistory.getAssignment_id(), this.targetAssignmentId)) || (this.targetDataKey != null && !Intrinsics.areEqual(locationHistory.getData_key(), this.targetDataKey))) {
                    z = false;
                }
                if (z) {
                    arrayList2.add(next);
                }
            }
            List<LocationHistory> listSortedWith = CollectionsKt.sortedWith(arrayList2, new Comparator() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$loadLocationHistory$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    Long longOrNull = StringsKt.toLongOrNull(((LocationHistory) t).getTimestamp());
                    Long lValueOf = Long.valueOf(longOrNull != null ? longOrNull.longValue() : 0L);
                    Long longOrNull2 = StringsKt.toLongOrNull(((LocationHistory) t2).getTimestamp());
                    return ComparisonsKt.compareValues(lValueOf, Long.valueOf(longOrNull2 != null ? longOrNull2.longValue() : 0L));
                }
            });
            if (!listSortedWith.isEmpty()) {
                displayLocationHistory(listSortedWith);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void displayLocationHistory(final List<LocationHistory> histories) {
        GoogleMap googleMap;
        String timestamp;
        if (this.googleMap == null) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    PetaGetLocationActivity.displayLocationHistory$lambda$10(this.f$0, histories);
                }
            }, 500L);
            return;
        }
        clearHistoryMarkers();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        Iterator<T> it = histories.iterator();
        int i = 0;
        while (true) {
            googleMap = null;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            LocationHistory locationHistory = (LocationHistory) next;
            LatLng latLng = new LatLng(locationHistory.getLatitude(), locationHistory.getLongitude());
            builder.include(latLng);
            try {
                timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(new Date(Long.parseLong(locationHistory.getTimestamp())));
            } catch (Exception unused) {
                timestamp = locationHistory.getTimestamp();
            }
            locationHistory.getAccuracy();
            if (locationHistory.getAccuracy() > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(locationHistory.getAccuracy())}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                String str2 = "\nAccuracy: " + str + " meter";
            }
            MarkerOptions markerOptionsIcon = new MarkerOptions().position(latLng).title("Riwayat ke-" + i2).snippet(String.valueOf(timestamp)).icon(BitmapDescriptorFactory.defaultMarker(60.0f));
            Intrinsics.checkNotNullExpressionValue(markerOptionsIcon, "MarkerOptions()\n        …iptorFactory.HUE_YELLOW))");
            GoogleMap googleMap2 = this.googleMap;
            if (googleMap2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("googleMap");
            } else {
                googleMap = googleMap2;
            }
            Marker markerAddMarker = googleMap.addMarker(markerOptionsIcon);
            if (markerAddMarker != null) {
                this.historyMarkers.add(markerAddMarker);
            }
            i = i2;
        }
        if (!histories.isEmpty()) {
            try {
                LatLngBounds latLngBoundsBuild = builder.build();
                Intrinsics.checkNotNullExpressionValue(latLngBoundsBuild, "bounds.build()");
                GoogleMap googleMap3 = this.googleMap;
                if (googleMap3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                    googleMap3 = null;
                }
                googleMap3.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBoundsBuild, 100));
            } catch (Exception unused2) {
                LocationHistory locationHistory2 = (LocationHistory) CollectionsKt.first((List) histories);
                LatLng latLng2 = new LatLng(locationHistory2.getLatitude(), locationHistory2.getLongitude());
                GoogleMap googleMap4 = this.googleMap;
                if (googleMap4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("googleMap");
                } else {
                    googleMap = googleMap4;
                }
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, 15.0f));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void displayLocationHistory$lambda$10(PetaGetLocationActivity this$0, List histories) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(histories, "$histories");
        this$0.displayLocationHistory(histories);
    }

    private final void clearHistoryMarkers() {
        Iterator<T> it = this.historyMarkers.iterator();
        while (it.hasNext()) {
            ((Marker) it.next()).remove();
        }
        this.historyMarkers.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLocationFromMockProvicer(Location location) {
        if (Build.VERSION.SDK_INT >= 31) {
            return location.isMock();
        }
        return location.isFromMockProvider();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkForFakeGps() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0) {
            return;
        }
        FusedLocationProviderClient fusedLocationProviderClient = this.fusedLocationClient;
        if (fusedLocationProviderClient == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fusedLocationClient");
            fusedLocationProviderClient = null;
        }
        Task<Location> currentLocation = fusedLocationProviderClient.getCurrentLocation(100, (CancellationToken) null);
        final Function1<Location, Unit> function1 = new Function1<Location, Unit>() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity.checkForFakeGps.1
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
                if (location == null || !PetaGetLocationActivity.this.isLocationFromMockProvider(location)) {
                    return;
                }
                Toast.makeText(PetaGetLocationActivity.this.getApplicationContext(), "Anda terdeteksi menggunakan Fake GPS", 1).show();
                PetaGetLocationActivity.this.finish();
            }
        };
        currentLocation.addOnSuccessListener(new OnSuccessListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                PetaGetLocationActivity.checkForFakeGps$lambda$14(function1, obj);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: id.go.bpsfasih.ui.petaGetLocation.PetaGetLocationActivity$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                PetaGetLocationActivity.checkForFakeGps$lambda$15(exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkForFakeGps$lambda$14(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkForFakeGps$lambda$15(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        Log.e("FakeGPS", "Error getting location: " + e.getMessage());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isLocationFromMockProvider(Location location) {
        if (Build.VERSION.SDK_INT >= 31) {
            return location.isMock();
        }
        return location.isFromMockProvider();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.fakeGpsCheckHandler.post(this.fakeGpsCheckRunnable);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.fakeGpsCheckHandler.removeCallbacks(this.fakeGpsCheckRunnable);
    }
}
