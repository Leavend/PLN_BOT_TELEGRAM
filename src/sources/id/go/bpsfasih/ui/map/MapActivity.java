package id.go.bpsfasih.ui.map;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.CommonCons;
import id.ipd.mapipd.model.ItemLoc;
import id.ipd.mapipd.ui.MarkerPlotMapActivity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MapActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0006\u0010\u0019\u001a\u00020\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/ui/map/MapActivity;", "Lid/ipd/mapipd/ui/MarkerPlotMapActivity;", "()V", "periodeId", "", "getPeriodeId", "()Ljava/lang/String;", "setPeriodeId", "(Ljava/lang/String;)V", "regionId", "getRegionId", "setRegionId", "surveyId", "getSurveyId", "setSurveyId", "viewModel", "Lid/go/bpsfasih/ui/map/MapViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/map/MapViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/map/MapViewModel;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setObserver", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MapActivity extends MarkerPlotMapActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String periodeId;
    private String regionId;
    private String surveyId;
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

    public final String getRegionId() {
        return this.regionId;
    }

    public final void setRegionId(String str) {
        this.regionId = str;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final void setPeriodeId(String str) {
        this.periodeId = str;
    }

    @Override // id.ipd.mapipd.ui.MarkerPlotMapActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.regionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_ID());
        this.surveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        this.periodeId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        String str = this.regionId;
        Intrinsics.checkNotNull(str);
        String str2 = this.surveyId;
        Intrinsics.checkNotNull(str2);
        String str3 = this.periodeId;
        Intrinsics.checkNotNull(str3);
        setViewModel(new MapViewModel(str, str2, str3));
        setObserver();
    }

    public final void setObserver() {
        getViewModel().get_listLocation().observe(this, new MapActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends ItemLoc>, Unit>() { // from class: id.go.bpsfasih.ui.map.MapActivity.setObserver.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends ItemLoc> list) {
                invoke2((List<ItemLoc>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<ItemLoc> list) {
                MarkerPlotMapActivity.initData$default(MapActivity.this, list, null, null, null, null, 30, null);
            }
        }));
    }
}
