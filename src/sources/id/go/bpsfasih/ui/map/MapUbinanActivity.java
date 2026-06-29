package id.go.bpsfasih.ui.map;

import android.R;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.data.CommonCons;
import id.ipd.mapipd.model.ItemLoc;
import id.ipd.mapipd.ui.MarkerPlotMapActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapUbinanActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001bH\u0002J\u0012\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0010\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u0010H\u0016J\u0006\u0010\"\u001a\u00020\u0018J\u0006\u0010#\u001a\u00020\u0018J\u0006\u0010$\u001a\u00020\u0018J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006'"}, d2 = {"Lid/go/bpsfasih/ui/map/MapUbinanActivity;", "Lid/ipd/mapipd/ui/MarkerPlotMapActivity;", "()V", "fabMapType", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "latitude", "", "getLatitude", "()Ljava/lang/Double;", "setLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "longitude", "getLongitude", "setLongitude", "myGoogleMap", "Lcom/google/android/gms/maps/GoogleMap;", "viewModel", "Lid/go/bpsfasih/ui/map/MapViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/map/MapViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/map/MapViewModel;)V", "addCustomShadow", "", "addMapTypeButton", "dpToPx", "", "dp", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onMapReady", "googleMap", "setObserver", "showRoadMap", "showSatelliteMap", "toggleMapType", "updateFabIcon", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MapUbinanActivity extends MarkerPlotMapActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FloatingActionButton fabMapType;
    private Double latitude;
    private Double longitude;
    private GoogleMap myGoogleMap;
    public MapViewModel viewModel;

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

    public MapUbinanActivity() {
        Double dValueOf = Double.valueOf(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        this.latitude = dValueOf;
        this.longitude = dValueOf;
    }

    public final MapViewModel getViewModel() {
        MapViewModel mapViewModel = this.viewModel;
        if (mapViewModel != null) {
            return mapViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(MapViewModel mapViewModel) {
        Intrinsics.checkNotNullParameter(mapViewModel, "<set-?>");
        this.viewModel = mapViewModel;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(Double d) {
        this.latitude = d;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(Double d) {
        this.longitude = d;
    }

    @Override // id.ipd.mapipd.ui.MarkerPlotMapActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.latitude = Double.valueOf(getIntent().getDoubleExtra(CommonCons.INSTANCE.getKEY_LATITUDE(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        this.longitude = Double.valueOf(getIntent().getDoubleExtra(CommonCons.INSTANCE.getKEY_LONGITUDE(), FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        setObserver();
    }

    @Override // id.ipd.mapipd.ui.MarkerPlotMapActivity, com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(GoogleMap googleMap) {
        Intrinsics.checkNotNullParameter(googleMap, "googleMap");
        super.onMapReady(googleMap);
        this.myGoogleMap = googleMap;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
            googleMap = null;
        }
        googleMap.setMapType(1);
        addMapTypeButton();
    }

    private final void addMapTypeButton() {
        MapUbinanActivity mapUbinanActivity = this;
        this.fabMapType = new FloatingActionButton(mapUbinanActivity);
        updateFabIcon();
        FloatingActionButton floatingActionButton = this.fabMapType;
        FloatingActionButton floatingActionButton2 = null;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton = null;
        }
        floatingActionButton.setSize(0);
        FloatingActionButton floatingActionButton3 = this.fabMapType;
        if (floatingActionButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton3 = null;
        }
        floatingActionButton3.setBackgroundTintList(ContextCompat.getColorStateList(mapUbinanActivity, R.color.white));
        FloatingActionButton floatingActionButton4 = this.fabMapType;
        if (floatingActionButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton4 = null;
        }
        floatingActionButton4.setImageTintList(ContextCompat.getColorStateList(mapUbinanActivity, R.color.black));
        FloatingActionButton floatingActionButton5 = this.fabMapType;
        if (floatingActionButton5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton5 = null;
        }
        floatingActionButton5.setElevation(dpToPx(8));
        FloatingActionButton floatingActionButton6 = this.fabMapType;
        if (floatingActionButton6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton6 = null;
        }
        floatingActionButton6.setRippleColor(ContextCompat.getColor(mapUbinanActivity, R.color.darker_gray));
        FloatingActionButton floatingActionButton7 = this.fabMapType;
        if (floatingActionButton7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton7 = null;
        }
        floatingActionButton7.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.map.MapUbinanActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MapUbinanActivity.addMapTypeButton$lambda$1(this.f$0, view);
            }
        });
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(dpToPx(56), dpToPx(56));
        layoutParams.gravity = 8388661;
        layoutParams.topMargin = dpToPx(80);
        layoutParams.setMarginEnd(dpToPx(16));
        addCustomShadow();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.content);
        FloatingActionButton floatingActionButton8 = this.fabMapType;
        if (floatingActionButton8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
        } else {
            floatingActionButton2 = floatingActionButton8;
        }
        frameLayout.addView(floatingActionButton2, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addMapTypeButton$lambda$1(final MapUbinanActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FloatingActionButton floatingActionButton = this$0.fabMapType;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton = null;
        }
        floatingActionButton.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100L).withEndAction(new Runnable() { // from class: id.go.bpsfasih.ui.map.MapUbinanActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MapUbinanActivity.addMapTypeButton$lambda$1$lambda$0(this.f$0);
            }
        }).start();
        this$0.toggleMapType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addMapTypeButton$lambda$1$lambda$0(MapUbinanActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FloatingActionButton floatingActionButton = this$0.fabMapType;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton = null;
        }
        floatingActionButton.animate().scaleX(1.0f).scaleY(1.0f).setDuration(100L).start();
    }

    private final void updateFabIcon() {
        GoogleMap googleMap = this.myGoogleMap;
        FloatingActionButton floatingActionButton = null;
        if (googleMap == null) {
            Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
            googleMap = null;
        }
        int i = googleMap.getMapType() == 2 ? R.drawable.ic_dialog_map : R.drawable.ic_menu_gallery;
        FloatingActionButton floatingActionButton2 = this.fabMapType;
        if (floatingActionButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
        } else {
            floatingActionButton = floatingActionButton2;
        }
        floatingActionButton.setImageResource(i);
    }

    private final void addCustomShadow() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        MapUbinanActivity mapUbinanActivity = this;
        gradientDrawable.setColor(ContextCompat.getColor(mapUbinanActivity, R.color.white));
        gradientDrawable.setStroke(dpToPx(1), ContextCompat.getColor(mapUbinanActivity, R.color.darker_gray));
        FloatingActionButton floatingActionButton = this.fabMapType;
        if (floatingActionButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fabMapType");
            floatingActionButton = null;
        }
        floatingActionButton.setBackground(gradientDrawable);
    }

    private final void toggleMapType() {
        GoogleMap googleMap = this.myGoogleMap;
        if (googleMap != null) {
            GoogleMap googleMap2 = null;
            if (googleMap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
                googleMap = null;
            }
            if (googleMap.getMapType() == 1) {
                GoogleMap googleMap3 = this.myGoogleMap;
                if (googleMap3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
                } else {
                    googleMap2 = googleMap3;
                }
                googleMap2.setMapType(2);
            } else {
                GoogleMap googleMap4 = this.myGoogleMap;
                if (googleMap4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
                } else {
                    googleMap2 = googleMap4;
                }
                googleMap2.setMapType(1);
            }
            updateFabIcon();
        }
    }

    private final int dpToPx(int dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    public final void showRoadMap() {
        GoogleMap googleMap = this.myGoogleMap;
        if (googleMap != null) {
            if (googleMap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
                googleMap = null;
            }
            googleMap.setMapType(1);
            updateFabIcon();
        }
    }

    public final void showSatelliteMap() {
        GoogleMap googleMap = this.myGoogleMap;
        if (googleMap != null) {
            if (googleMap == null) {
                Intrinsics.throwUninitializedPropertyAccessException("myGoogleMap");
                googleMap = null;
            }
            googleMap.setMapType(2);
            updateFabIcon();
        }
    }

    public final void setObserver() {
        if (!Intrinsics.areEqual(this.latitude, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) && !Intrinsics.areEqual(this.longitude, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
            Double d = this.latitude;
            Intrinsics.checkNotNull(d);
            double dDoubleValue = d.doubleValue();
            Double d2 = this.longitude;
            Intrinsics.checkNotNull(d2);
            MarkerPlotMapActivity.initDataSegmen$default(this, CollectionsKt.listOf(new ItemLoc("1", new LatLng(dDoubleValue, d2.doubleValue()), "Lokasi", "Titik tengah radius KSA")), null, null, null, null, 30, null);
            return;
        }
        MarkerPlotMapActivity.initData$default(this, null, null, null, null, null, 30, null);
    }
}
