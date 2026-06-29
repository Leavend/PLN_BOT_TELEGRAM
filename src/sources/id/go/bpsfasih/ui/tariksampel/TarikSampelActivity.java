package id.go.bpsfasih.ui.tariksampel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: TarikSampelActivity.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\u0012\u0010!\u001a\u00020\u001f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\b\u0010$\u001a\u00020\u001fH\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0019X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006%"}, d2 = {"Lid/go/bpsfasih/ui/tariksampel/TarikSampelActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "mode", "", "getMode", "()Ljava/lang/String;", "setMode", "(Ljava/lang/String;)V", "periodeId", "getPeriodeId", "setPeriodeId", "regionFullCode", "getRegionFullCode", "setRegionFullCode", "regionId", "getRegionId", "setRegionId", "samplingRegionId", "getSamplingRegionId", "setSamplingRegionId", "surveyId", "getSurveyId", "setSurveyId", "viewModel", "Lid/go/bpsfasih/ui/tariksampel/TarikSampelViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/tariksampel/TarikSampelViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/tariksampel/TarikSampelViewModel;)V", "initListener", "", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "showDialogLogout", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampelActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String mode;
    private String periodeId;
    private String regionFullCode;
    private String regionId;
    private String samplingRegionId;
    private String surveyId;
    public TarikSampelViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogLogout$lambda$5(View view) {
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
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

    public final TarikSampelViewModel getViewModel() {
        TarikSampelViewModel tarikSampelViewModel = this.viewModel;
        if (tarikSampelViewModel != null) {
            return tarikSampelViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(TarikSampelViewModel tarikSampelViewModel) {
        Intrinsics.checkNotNullParameter(tarikSampelViewModel, "<set-?>");
        this.viewModel = tarikSampelViewModel;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public final void setRegionId(String str) {
        this.regionId = str;
    }

    public final String getRegionFullCode() {
        return this.regionFullCode;
    }

    public final void setRegionFullCode(String str) {
        this.regionFullCode = str;
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

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        this.mode = str;
    }

    public final String getSamplingRegionId() {
        return this.samplingRegionId;
    }

    public final void setSamplingRegionId(String str) {
        this.samplingRegionId = str;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarik_sampel);
        BaseClassActivityNew.setAppBar$default(this, 0, "Tarik Sampel", null, null, null, 28, null);
        setStatusBarColor();
        this.regionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_ID());
        this.regionFullCode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE());
        this.surveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        this.periodeId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        this.mode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_MODE());
        this.samplingRegionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SAMPLING_REGION_ID());
        setViewModel(new TarikSampelViewModel(this));
        setStatusBarColor();
        initView();
        initListener();
    }

    private final void initView() {
        ((TextView) _$_findCachedViewById(R.id.keterangan)).setText("Anda akan melakukan tarik sampel " + this.mode + " pada wilayah " + this.regionFullCode);
        if (StringsKt.equals$default(this.mode, CommonCons.INSTANCE.getKEY_OFFLINE_MODE(), false, 2, null)) {
            ((Button) _$_findCachedViewById(R.id.tarikSampel)).setText("Tarik Sample Offline");
        } else {
            ((Button) _$_findCachedViewById(R.id.tarikSampel)).setText("Tarik Sample Online");
        }
    }

    private final void initListener() {
        ((Button) _$_findCachedViewById(R.id.tarikSampel)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TarikSampelActivity.initListener$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(final TarikSampelActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this$0);
        BaseClassActivityNew.showAlertDialogKodeVerifikasi$default(this$0, bottomSheetDialog, "Tarik Sampel", "Apakah yakin akan melakukan tarik sampel ?", "Untuk melakukan aksi berikut, tolong ketik kode verifikasi dengan benar", "Tarik Sampel", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TarikSampelActivity.initListener$lambda$3$lambda$0(bottomSheetDialog, this$0, view2);
            }
        }, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TarikSampelActivity.initListener$lambda$3$lambda$1(bottomSheetDialog, view2);
            }
        }, new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                TarikSampelActivity.initListener$lambda$3$lambda$2(bottomSheetDialog, view2);
            }
        }, false, 512, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3$lambda$0(BottomSheetDialog dialog, final TarikSampelActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        if (StringsKt.equals$default(this$0.mode, CommonCons.INSTANCE.getKEY_OFFLINE_MODE(), false, 2, null)) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new TarikSampelActivity$initListener$1$1$1(this$0, null), 3, null);
            return;
        }
        TarikSampelViewModel viewModel = this$0.getViewModel();
        String str = this$0.periodeId;
        Intrinsics.checkNotNull(str);
        viewModel.reqTarikSampel(str, new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$initListener$1$1$2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                invoke2(str2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str2) {
                TarikSampelViewModel viewModel2 = this.this$0.getViewModel();
                Intrinsics.checkNotNull(str2);
                String periodeId = this.this$0.getPeriodeId();
                Intrinsics.checkNotNull(periodeId);
                String regionFullCode = this.this$0.getRegionFullCode();
                Intrinsics.checkNotNull(regionFullCode);
                String regionId = this.this$0.getRegionId();
                Intrinsics.checkNotNull(regionId);
                viewModel2.execTariksampel(str2, periodeId, regionFullCode, regionId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3$lambda$1(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3$lambda$2(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    private final void showDialogLogout() {
        BaseClassActivityNew.showAlertDialog$default(this, "Logout", "Apakah yakin logout ?", null, "Ya", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TarikSampelActivity.showDialogLogout$lambda$4(view);
            }
        }, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TarikSampelActivity.showDialogLogout$lambda$5(view);
            }
        }, false, false, 384, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogLogout$lambda$4(View view) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarikSampelActivity$showDialogLogout$1$1(null), 3, null);
        FasihApp.INSTANCE.getSession().createSessionList(CommonCons.INSTANCE.getSESSION_LIST_GROUP(), new HashSet());
    }
}
