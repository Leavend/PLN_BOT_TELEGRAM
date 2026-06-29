package id.go.bpsfasih.ui.periode;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.pojo.PeriodePojo;
import id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.SsoHelper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: PeriodeActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001b2\u00020\u00012\u00020\u0002:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0003J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u0006\u0010\u0011\u001a\u00020\u000fJ\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u000fH\u0002J\u0012\u0010\u0014\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\fH\u0016J\b\u0010\u0019\u001a\u00020\u000fH\u0014J\b\u0010\u001a\u001a\u00020\u000fH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/ui/periode/PeriodeActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "Lid/go/bpsfasih/ui/periode/PeriodeAdapterCallback;", "()V", "mAdapter", "Lid/go/bpsfasih/ui/periode/PeriodeAdapter;", "mPeriodeViewModel", "Lid/go/bpsfasih/ui/periode/PeriodeViewModel;", "mSurveyTypeIsPanel", "", "mSurveyTypeIsUpdate", "mTemplateId", "", "msSurveyId", "checkFormEngine", "", "checkTemplateValidasi", "initListener", "initObserve", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClicked", "periodeId", "onResume", "onStop", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PeriodeActivity extends BaseClassActivityNew implements PeriodeAdapterCallback {
    private PeriodeAdapter mAdapter;
    private PeriodeViewModel mPeriodeViewModel;
    private boolean mSurveyTypeIsPanel;
    private boolean mSurveyTypeIsUpdate;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String msSurveyId = "";
    private String mTemplateId = "";

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

    /* compiled from: PeriodeActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u000b¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/ui/periode/PeriodeActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "updateListingType", "", "panelType", "surveyId", "", "templateId", "surveyName", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, boolean updateListingType, boolean panelType, String surveyId, String templateId, String surveyName) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(surveyName, "surveyName");
            Intent intent = new Intent(context, (Class<?>) PeriodeActivity.class);
            intent.putExtra(CommonCons.SURVEY_TYPE_IS_UPDATE, updateListingType);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), surveyId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PANEL_TYPE(), panelType);
            intent.putExtra(CommonCons.INSTANCE.getTEMPLATE_ID(), templateId);
            intent.putExtra(CommonCons.INSTANCE.getSURVEY_NAME(), surveyName);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras;
        Bundle extras2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periode);
        setStatusBarColor();
        Intent intent = getIntent();
        Boolean boolValueOf = null;
        if ((intent != null ? intent.getExtras() : null) != null) {
            Intent intent2 = getIntent();
            this.msSurveyId = (intent2 == null || (extras2 = intent2.getExtras()) == null) ? null : extras2.getString(CommonCons.INSTANCE.getKEY_SURVEY_ID(), "");
            Intent intent3 = getIntent();
            if (intent3 != null && (extras = intent3.getExtras()) != null) {
                boolValueOf = Boolean.valueOf(extras.getBoolean(CommonCons.SURVEY_TYPE_IS_UPDATE, false));
            }
            Intrinsics.checkNotNull(boolValueOf);
            this.mSurveyTypeIsUpdate = boolValueOf.booleanValue();
            this.mSurveyTypeIsPanel = getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_PANEL_TYPE(), false);
            this.mTemplateId = getIntent().getStringExtra(CommonCons.INSTANCE.getTEMPLATE_ID());
            ((TextView) _$_findCachedViewById(R.id.deskripsi_tv)).setText("Silakan pilih periode pada " + getIntent().getStringExtra(CommonCons.INSTANCE.getSURVEY_NAME()));
        }
        BaseClassActivityNew.setAppBar$default(this, 0, "Periode", Integer.valueOf(R.menu.menu_periode), new MenuItem.OnMenuItemClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$$ExternalSyntheticLambda4
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return PeriodeActivity.onCreate$lambda$0(this.f$0, menuItem);
            }
        }, null, 16, null);
        initView();
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        this.mPeriodeViewModel = (PeriodeViewModel) new ViewModelProvider(this, new MyViewModelFactory(application, this.msSurveyId, this)).get(PeriodeViewModel.class);
        initObserve();
        initListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$0(final PeriodeActivity this$0, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        if (itemId == R.id.syncTemplateValidasi) {
            PeriodeActivity periodeActivity = this$0;
            if (Network.INSTANCE.isOnline(periodeActivity)) {
                if (SsoHelper.INSTANCE.checkExpSession()) {
                    PeriodeViewModel periodeViewModel = this$0.mPeriodeViewModel;
                    if (periodeViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                        periodeViewModel = null;
                    }
                    periodeViewModel.requestTemplateValidation();
                } else {
                    SsoHelper.INSTANCE.requestRefreshToken(this$0, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$onCreate$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                            invoke(bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z) {
                            if (z) {
                                PeriodeViewModel periodeViewModel2 = this.this$0.mPeriodeViewModel;
                                if (periodeViewModel2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                                    periodeViewModel2 = null;
                                }
                                periodeViewModel2.requestTemplateValidation();
                                return;
                            }
                            this.this$0.hideProgressBar();
                            this.this$0.showAlertDialogKodeVerifikasiLogout();
                        }
                    });
                }
            } else {
                Toast.makeText(periodeActivity, "Pastikan smartphone anda memiliki koneksi", 1).show();
                this$0.finish();
            }
        } else if (itemId == R.id.syncFormEngine) {
            Intent intent = new Intent(this$0, (Class<?>) DownloadFormEngineActivity.class);
            String key_survey_id = CommonCons.INSTANCE.getKEY_SURVEY_ID();
            String str = this$0.msSurveyId;
            Intrinsics.checkNotNull(str);
            intent.putExtra(key_survey_id, (String) CollectionsKt.first(StringsKt.split$default((CharSequence) str, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null)));
            this$0.startActivity(intent);
        }
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        List<PeriodePojo> value;
        super.onResume();
        PeriodeViewModel periodeViewModel = this.mPeriodeViewModel;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        LiveData<List<PeriodePojo>> periodBySurvey = periodeViewModel.getPeriodBySurvey();
        if (periodBySurvey == null || (value = periodBySurvey.getValue()) == null || value.size() <= 0) {
            return;
        }
        checkTemplateValidasi();
    }

    private final void initView() {
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_periode)).startShimmerAnimation();
        PeriodeActivity periodeActivity = this;
        this.mAdapter = new PeriodeAdapter(periodeActivity, this.mSurveyTypeIsUpdate, this.mTemplateId, this.mSurveyTypeIsPanel, this);
        ((RecyclerView) _$_findCachedViewById(R.id.periodeRecycler)).setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(R.id.periodeRecycler)).setLayoutManager(new LinearLayoutManager(periodeActivity));
    }

    public final void initListener() {
        ((Button) _$_findCachedViewById(R.id.syncNotEmptyData_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeriodeActivity.initListener$lambda$2(this.f$0, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.syncEmptyData_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PeriodeActivity.initListener$lambda$3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(PeriodeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PeriodeViewModel periodeViewModel = this$0.mPeriodeViewModel;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        periodeViewModel.syncPeriode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(PeriodeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PeriodeViewModel periodeViewModel = this$0.mPeriodeViewModel;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        periodeViewModel.syncPeriode();
    }

    private final void initObserve() {
        PeriodeViewModel periodeViewModel = this.mPeriodeViewModel;
        PeriodeViewModel periodeViewModel2 = null;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        PeriodeActivity periodeActivity = this;
        periodeViewModel.getShowProgressBar().observe(periodeActivity, new PeriodeActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity.initObserve.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    ((ProgressBar) PeriodeActivity.this._$_findCachedViewById(R.id.progressBar)).setVisibility(8);
                } else {
                    ((ProgressBar) PeriodeActivity.this._$_findCachedViewById(R.id.progressBar)).setVisibility(8);
                }
            }
        }));
        PeriodeViewModel periodeViewModel3 = this.mPeriodeViewModel;
        if (periodeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel3 = null;
        }
        periodeViewModel3.getShowProgressBarDialog().observe(periodeActivity, new PeriodeActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity.initObserve.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    PeriodeActivity.this.showProgressBar();
                } else {
                    PeriodeActivity.this.hideProgressBar();
                }
            }
        }));
        PeriodeViewModel periodeViewModel4 = this.mPeriodeViewModel;
        if (periodeViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
        } else {
            periodeViewModel2 = periodeViewModel4;
        }
        LiveData<List<PeriodePojo>> periodBySurvey = periodeViewModel2.getPeriodBySurvey();
        if (periodBySurvey != null) {
            periodBySurvey.observe(periodeActivity, new Observer<List<? extends PeriodePojo>>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity.initObserve.3
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(List<? extends PeriodePojo> list) {
                    onChanged2((List<PeriodePojo>) list);
                }

                /* renamed from: onChanged, reason: avoid collision after fix types in other method */
                public final void onChanged2(List<PeriodePojo> list) {
                    if (list != null) {
                        PeriodeActivity periodeActivity2 = PeriodeActivity.this;
                        if (list.size() > 0) {
                            ((ShimmerFrameLayout) periodeActivity2._$_findCachedViewById(R.id.shimmer_view_container_periode)).setVisibility(8);
                            ((ShimmerFrameLayout) periodeActivity2._$_findCachedViewById(R.id.shimmer_view_container_periode)).stopShimmerAnimation();
                            ((ConstraintLayout) periodeActivity2._$_findCachedViewById(R.id.notEmptyData_cl)).setVisibility(0);
                            ((ConstraintLayout) periodeActivity2._$_findCachedViewById(R.id.emptyData_cl)).setVisibility(8);
                            PeriodeAdapter periodeAdapter = periodeActivity2.mAdapter;
                            Intrinsics.checkNotNull(periodeAdapter);
                            periodeAdapter.setPeriode$app_release(list);
                            periodeActivity2.checkTemplateValidasi();
                            return;
                        }
                        ((ShimmerFrameLayout) periodeActivity2._$_findCachedViewById(R.id.shimmer_view_container_periode)).setVisibility(8);
                        ((ShimmerFrameLayout) periodeActivity2._$_findCachedViewById(R.id.shimmer_view_container_periode)).stopShimmerAnimation();
                        ((ConstraintLayout) periodeActivity2._$_findCachedViewById(R.id.notEmptyData_cl)).setVisibility(8);
                        ((ConstraintLayout) periodeActivity2._$_findCachedViewById(R.id.emptyData_cl)).setVisibility(0);
                    }
                }
            });
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_periode)).stopShimmerAnimation();
    }

    @Override // id.go.bpsfasih.ui.periode.PeriodeAdapterCallback
    public void onItemClicked(final String periodeId) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        PeriodeActivity periodeActivity = this;
        if (Network.INSTANCE.isOnline(periodeActivity)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                PeriodeViewModel periodeViewModel = this.mPeriodeViewModel;
                if (periodeViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                    periodeViewModel = null;
                }
                periodeViewModel.requestPeriodeUpdate(periodeId);
                return;
            }
            SsoHelper.INSTANCE.requestRefreshToken(this, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity.onItemClicked.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        PeriodeViewModel periodeViewModel2 = PeriodeActivity.this.mPeriodeViewModel;
                        if (periodeViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                            periodeViewModel2 = null;
                        }
                        periodeViewModel2.requestPeriodeUpdate(periodeId);
                        return;
                    }
                    PeriodeActivity.this.hideProgressBar();
                    PeriodeActivity.this.showAlertDialogKodeVerifikasiLogout();
                }
            });
            return;
        }
        Toast.makeText(periodeActivity, "Pastikan smartphone anda memiliki koneksi", 1).show();
        finish();
    }

    public final void checkTemplateValidasi() {
        PeriodeViewModel periodeViewModel = this.mPeriodeViewModel;
        PeriodeViewModel periodeViewModel2 = null;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        periodeViewModel.m6814getTemplateId();
        PeriodeViewModel periodeViewModel3 = this.mPeriodeViewModel;
        if (periodeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel3 = null;
        }
        if (periodeViewModel3.getDataTemplateValidationVersion() == null) {
            showAlertDialog("Unduh Template dan Validasi", "Anda belum memiliki file kuesioner, silahkan melakukan unduh template dan validasi.", null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PeriodeActivity.checkTemplateValidasi$lambda$4(this.f$0, view);
                }
            }, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PeriodeActivity.checkTemplateValidasi$lambda$5(this.f$0, view);
                }
            }, false, false);
            return;
        }
        PeriodeViewModel periodeViewModel4 = this.mPeriodeViewModel;
        if (periodeViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
        } else {
            periodeViewModel2 = periodeViewModel4;
        }
        periodeViewModel2.checkVersionTemplateValidation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkTemplateValidasi$lambda$4(final PeriodeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        PeriodeActivity periodeActivity = this$0;
        if (Network.INSTANCE.isOnline(periodeActivity)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                PeriodeViewModel periodeViewModel = this$0.mPeriodeViewModel;
                if (periodeViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                    periodeViewModel = null;
                }
                periodeViewModel.requestTemplateValidation();
                return;
            }
            SsoHelper.INSTANCE.requestRefreshToken(this$0, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeActivity$checkTemplateValidasi$1$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        PeriodeViewModel periodeViewModel2 = this.this$0.mPeriodeViewModel;
                        if (periodeViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
                            periodeViewModel2 = null;
                        }
                        periodeViewModel2.requestTemplateValidation();
                        return;
                    }
                    this.this$0.hideProgressBar();
                    this.this$0.showAlertDialogKodeVerifikasiLogout();
                }
            });
            return;
        }
        Toast.makeText(periodeActivity, "Pastikan smartphone anda memiliki koneksi", 1).show();
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkTemplateValidasi$lambda$5(PeriodeActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    public final void checkFormEngine() {
        PeriodeViewModel periodeViewModel = this.mPeriodeViewModel;
        if (periodeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mPeriodeViewModel");
            periodeViewModel = null;
        }
        periodeViewModel.m6813getFormEngineId();
    }
}
