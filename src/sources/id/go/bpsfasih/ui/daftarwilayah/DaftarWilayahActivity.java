package id.go.bpsfasih.ui.daftarwilayah;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import id.go.bpsfasih.data.local.entities.TarikSampleEntity;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.models.ContentItem;
import id.go.bpsfasih.data.local.models.SamplingRegionModelItem;
import id.go.bpsfasih.data.local.models.Script;
import id.go.bpsfasih.data.local.models.Source;
import id.go.bpsfasih.data.local.models.Target;
import id.go.bpsfasih.data.local.models.TarikSample;
import id.go.bpsfasih.data.local.models.TarikSampleConfig;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import id.go.bpsfasih.data.local.repository.SamplingRegionRepository;
import id.go.bpsfasih.data.local.repository.TarikSampleRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.remote.dto.AssignmentRegionResponse;
import id.go.bpsfasih.data.remote.dto.RegionMetadataResponse;
import id.go.bpsfasih.data.remote.dto.SamplingRegionResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleConfigResponse;
import id.go.bpsfasih.data.remote.dto.TarikSampleResponse;
import id.go.bpsfasih.data.repository.RegionRepositoryImpl;
import id.go.bpsfasih.data.repository.TarikSampelRepositoryImpl;
import id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity;
import id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.SsoHelper;
import id.go.bpsfasih.utils.sync.dbProcess.DPAssignmentRegion;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: DaftarWilayahActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020'H\u0002J\b\u0010)\u001a\u00020'H\u0002J\u000e\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00100\tH\u0002J\u0010\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020\u0010H\u0002J\u0006\u0010-\u001a\u00020'J\b\u0010.\u001a\u00020'H\u0002J\b\u0010/\u001a\u00020'H\u0002J\u0012\u00100\u001a\u00020'2\b\u00101\u001a\u0004\u0018\u000102H\u0014J\b\u00103\u001a\u00020'H\u0014J\b\u00104\u001a\u00020'H\u0014J\b\u00105\u001a\u00020\u0004H\u0016J\b\u00106\u001a\u00020'H\u0002J\u0006\u00107\u001a\u00020'R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0016R(\u0010\u0017\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR(\u0010\u001f\u001a\u0010\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u0016\u0010\"\u001a\n\u0012\u0004\u0012\u00020#\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082.¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "hasPromptedRegionMetadata", "", "isDownloadingRegionSupportData", "isPencacah", "Ljava/lang/Boolean;", "listAssignment", "", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "mAdapter", "Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahAdapter;", "mCanAddSample", "mPanelType", "mPeriodeId", "", "mPeriodeIdOnly", "mSurveyId", "mTemplateId", "mViewLevelId", "", "Ljava/lang/Long;", "resultIntentFromSyncAssignment", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getResultIntentFromSyncAssignment", "()Landroidx/activity/result/ActivityResultLauncher;", "setResultIntentFromSyncAssignment", "(Landroidx/activity/result/ActivityResultLauncher;)V", "resultLauncher", "getResultLauncher", "setResultLauncher", "samplingRegionOffline", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "updateListingViewModel", "Lid/go/bpsfasih/ui/daftarwilayah/UpdateListingViewModel;", "checkRegionSupportDataState", "", "checkTarikSample", "downloadRegionSupportData", "getFullcode", "handleRegionSupportDownloadError", "message", "initListener", "initObserve", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStop", "onSupportNavigateUp", "setDefaultAppBar", "showDialogSyncAssignment", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DaftarWilayahActivity extends BaseClassActivityNew {
    private boolean hasPromptedRegionMetadata;
    private boolean isDownloadingRegionSupportData;
    private Boolean isPencacah;
    private List<AssignmentRegionWilayahPojo> listAssignment;
    private DaftarWilayahAdapter mAdapter;
    private boolean mCanAddSample;
    private boolean mPanelType;
    private ActivityResultLauncher<Intent> resultIntentFromSyncAssignment;
    private ActivityResultLauncher<Intent> resultLauncher;
    private List<SamplingRegionEntity> samplingRegionOffline;
    private UpdateListingViewModel updateListingViewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String mPeriodeId = "";
    private String mPeriodeIdOnly = "";
    private String mSurveyId = "";
    private Long mViewLevelId = 0L;
    private String mTemplateId = "";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void downloadRegionSupportData$lambda$11(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogSyncAssignment$lambda$8$lambda$7(View view) {
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

    public DaftarWilayahActivity() {
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$resultLauncher$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    UpdateListingViewModel updateListingViewModel = this.this$0.updateListingViewModel;
                    if (updateListingViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
                        updateListingViewModel = null;
                    }
                    updateListingViewModel.requestFlagNotification();
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…)\n            }\n        }");
        this.resultLauncher = activityResultLauncherRegisterForActivityResult;
        ActivityResultLauncher<Intent> activityResultLauncherRegisterForActivityResult2 = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new DaftarWilayahActivity$resultIntentFromSyncAssignment$1(this));
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult2, "registerForActivityResul…)\n            }\n        }");
        this.resultIntentFromSyncAssignment = activityResultLauncherRegisterForActivityResult2;
    }

    public static final /* synthetic */ void access$downloadRegionSupportData(DaftarWilayahActivity daftarWilayahActivity) {
        daftarWilayahActivity.downloadRegionSupportData();
    }

    public final ActivityResultLauncher<Intent> getResultLauncher() {
        return this.resultLauncher;
    }

    public final void setResultLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.resultLauncher = activityResultLauncher;
    }

    public final ActivityResultLauncher<Intent> getResultIntentFromSyncAssignment() {
        return this.resultIntentFromSyncAssignment;
    }

    public final void setResultIntentFromSyncAssignment(ActivityResultLauncher<Intent> activityResultLauncher) {
        Intrinsics.checkNotNullParameter(activityResultLauncher, "<set-?>");
        this.resultIntentFromSyncAssignment = activityResultLauncher;
    }

    /* compiled from: DaftarWilayahActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JO\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\b¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/daftarwilayah/DaftarWilayahActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "periodeId", "", "periodeIdOnly", "templateId", "isPencacah", "", "panelType", "canAddSample", "surveyId", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;ZZLjava/lang/String;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String periodeId, String periodeIdOnly, String templateId, Boolean isPencacah, boolean panelType, boolean canAddSample, String surveyId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(periodeIdOnly, "periodeIdOnly");
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intent intent = new Intent(context, (Class<?>) DaftarWilayahActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getFRAME_CATEGORY_ID(), 12);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), periodeId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID_ONLY(), periodeIdOnly);
            intent.putExtra(CommonCons.INSTANCE.getKEY_VIEW_LEVEL_ID(), 1);
            intent.putExtra(CommonCons.INSTANCE.getTEMPLATE_ID(), templateId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_CAN_ADD_SAMPLE(), canAddSample);
            intent.setFlags(268435456);
            intent.putExtra(CommonCons.INSTANCE.getKEY_IS_PENCACAH(), isPencacah);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PANEL_TYPE(), panelType);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), surveyId);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_wilayah);
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Daftar Wilayah", null, null, null, 24, null);
        initView();
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C08581(null), 3, null);
        initListener();
        setDefaultAppBar();
        if (Network.INSTANCE.isOnline(this)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                checkTarikSample();
            } else {
                SsoHelper.INSTANCE.requestRefreshToken(this, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.onCreate.2
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
                            DaftarWilayahActivity.this.checkTarikSample();
                        } else {
                            DaftarWilayahActivity.this.hideProgressBar();
                            DaftarWilayahActivity.this.showAlertDialogKodeVerifikasiLogout();
                        }
                    }
                });
            }
        }
    }

    /* compiled from: DaftarWilayahActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$onCreate$1", f = "DaftarWilayahActivity.kt", i = {}, l = {155}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$onCreate$1, reason: invalid class name and case insensitive filesystem */
    static final class C08581 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C08581(Continuation<? super C08581> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DaftarWilayahActivity.this.new C08581(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08581) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            DaftarWilayahActivity daftarWilayahActivity;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DaftarWilayahActivity daftarWilayahActivity2 = DaftarWilayahActivity.this;
                    this.L$0 = daftarWilayahActivity2;
                    this.label = 1;
                    Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C02021(DaftarWilayahActivity.this, null), this);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    daftarWilayahActivity = daftarWilayahActivity2;
                    obj = objWithContext;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    daftarWilayahActivity = (DaftarWilayahActivity) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                daftarWilayahActivity.samplingRegionOffline = (List) obj;
                DaftarWilayahActivity.this.initObserve();
                DaftarWilayahActivity.this.checkRegionSupportDataState();
            } catch (Exception e) {
                Log.e(">>>", "Error fetching sampling region: " + e.getMessage(), e);
            }
            return Unit.INSTANCE;
        }

        /* compiled from: DaftarWilayahActivity.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$onCreate$1$1", f = "DaftarWilayahActivity.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$onCreate$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02021 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SamplingRegionEntity>>, Object> {
            int label;
            final /* synthetic */ DaftarWilayahActivity this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02021(DaftarWilayahActivity daftarWilayahActivity, Continuation<? super C02021> continuation) {
                super(2, continuation);
                this.this$0 = daftarWilayahActivity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02021(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SamplingRegionEntity>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super List<SamplingRegionEntity>>) continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SamplingRegionEntity>> continuation) {
                return ((C02021) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SamplingRegionRepository samplingRegionRepository = DataSurvey.SamplingRegion.INSTANCE.getSamplingRegionRepository();
                    String str = this.this$0.mPeriodeIdOnly;
                    Intrinsics.checkNotNull(str);
                    this.label = 1;
                    obj = samplingRegionRepository.getSamplingRegion(str, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }
    }

    private final void initView() {
        UpdateListingViewModel updateListingViewModel;
        Bundle extras;
        Bundle extras2;
        Bundle extras3;
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).startShimmerAnimation();
        Intent intent = getIntent();
        if ((intent != null ? intent.getExtras() : null) != null) {
            Intent intent2 = getIntent();
            this.mPeriodeId = (intent2 == null || (extras3 = intent2.getExtras()) == null) ? null : extras3.getString(CommonCons.INSTANCE.getKEY_PERIODE_ID());
            Intent intent3 = getIntent();
            this.mPeriodeIdOnly = (intent3 == null || (extras2 = intent3.getExtras()) == null) ? null : extras2.getString(CommonCons.INSTANCE.getKEY_PERIODE_ID_ONLY());
            this.mTemplateId = getIntent().getStringExtra(CommonCons.INSTANCE.getTEMPLATE_ID());
            this.isPencacah = Boolean.valueOf(getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_IS_PENCACAH(), false));
            this.mPanelType = getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_PANEL_TYPE(), false);
            this.mCanAddSample = getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_CAN_ADD_SAMPLE(), false);
            Intent intent4 = getIntent();
            this.mSurveyId = (intent4 == null || (extras = intent4.getExtras()) == null) ? null : extras.getString(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        }
        this.mTemplateId = (String) BuildersKt__BuildersKt.runBlocking$default(null, new C08571(null), 1, null);
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        this.updateListingViewModel = (UpdateListingViewModel) ViewModelProviders.of(this, new UpdateListingViewModelFactory(application, this, this.mViewLevelId, this.mPeriodeId, this.mPeriodeIdOnly, this.mTemplateId)).get(UpdateListingViewModel.class);
        DaftarWilayahActivity daftarWilayahActivity = this;
        String str = this.mTemplateId;
        Boolean bool = this.isPencacah;
        boolean z = this.mCanAddSample;
        String str2 = this.mSurveyId;
        UpdateListingViewModel updateListingViewModel2 = this.updateListingViewModel;
        if (updateListingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel = null;
        } else {
            updateListingViewModel = updateListingViewModel2;
        }
        this.mAdapter = new DaftarWilayahAdapter(daftarWilayahActivity, str, bool, z, str2, updateListingViewModel);
        ((RecyclerView) _$_findCachedViewById(R.id.update_listing_recycler)).setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(R.id.update_listing_recycler)).setLayoutManager(new LinearLayoutManager(daftarWilayahActivity));
    }

    /* compiled from: DaftarWilayahActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$initView$1", f = "DaftarWilayahActivity.kt", i = {}, l = {202}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$initView$1, reason: invalid class name and case insensitive filesystem */
    static final class C08571 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        C08571(Continuation<? super C08571> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DaftarWilayahActivity.this.new C08571(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C08571) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TemplateValidationRepository templateValidationRepository = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
                String str = DaftarWilayahActivity.this.mSurveyId;
                Intrinsics.checkNotNull(str);
                this.label = 1;
                obj = templateValidationRepository.getDataBySurveyId(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) obj;
            if (templateValidationEntity != null) {
                return templateValidationEntity.getTemplate_id();
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initObserve() {
        UpdateListingViewModel updateListingViewModel = this.updateListingViewModel;
        UpdateListingViewModel updateListingViewModel2 = null;
        if (updateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel = null;
        }
        DaftarWilayahActivity daftarWilayahActivity = this;
        updateListingViewModel.getShowProgressBar().observe(daftarWilayahActivity, new DaftarWilayahActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.initObserve.1
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
                    ((ProgressBar) DaftarWilayahActivity.this._$_findCachedViewById(R.id.progressBar)).setVisibility(0);
                } else {
                    ((ProgressBar) DaftarWilayahActivity.this._$_findCachedViewById(R.id.progressBar)).setVisibility(8);
                }
            }
        }));
        UpdateListingViewModel updateListingViewModel3 = this.updateListingViewModel;
        if (updateListingViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel3 = null;
        }
        updateListingViewModel3.getShowProgressBarDialog().observe(daftarWilayahActivity, new DaftarWilayahActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.initObserve.2
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
                    DaftarWilayahActivity.this.showProgressBar();
                } else {
                    DaftarWilayahActivity.this.hideProgressBar();
                }
            }
        }));
        UpdateListingViewModel updateListingViewModel4 = this.updateListingViewModel;
        if (updateListingViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
        } else {
            updateListingViewModel2 = updateListingViewModel4;
        }
        LiveData<List<AssignmentRegionWilayahPojo>> assignmentUpdateListing = updateListingViewModel2.getAssignmentUpdateListing();
        if (assignmentUpdateListing != null) {
            assignmentUpdateListing.observe(daftarWilayahActivity, new Observer<List<? extends AssignmentRegionWilayahPojo>>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.initObserve.3
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(List<? extends AssignmentRegionWilayahPojo> list) {
                    onChanged2((List<AssignmentRegionWilayahPojo>) list);
                }

                /* renamed from: onChanged, reason: avoid collision after fix types in other method */
                public final void onChanged2(List<AssignmentRegionWilayahPojo> list) {
                    if (list != null) {
                        DaftarWilayahActivity daftarWilayahActivity2 = DaftarWilayahActivity.this;
                        ((ShimmerFrameLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).setVisibility(8);
                        ((ShimmerFrameLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).stopShimmerAnimation();
                        daftarWilayahActivity2.listAssignment = list;
                        if (list.size() > 0) {
                            ((ConstraintLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.emptyData_cl)).setVisibility(8);
                            ((ConstraintLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.notEmptyData_cl)).setVisibility(0);
                            DaftarWilayahAdapter daftarWilayahAdapter = daftarWilayahActivity2.mAdapter;
                            if (daftarWilayahAdapter != null) {
                                daftarWilayahAdapter.setData$app_release(list, daftarWilayahActivity2.samplingRegionOffline);
                                return;
                            }
                            return;
                        }
                        ((ConstraintLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.emptyData_cl)).setVisibility(0);
                        ((ConstraintLayout) daftarWilayahActivity2._$_findCachedViewById(R.id.notEmptyData_cl)).setVisibility(8);
                    }
                }
            });
        }
    }

    public final void initListener() {
        ((LinearLayout) _$_findCachedViewById(R.id.sort_l)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahActivity.initListener$lambda$1(this.f$0, view);
            }
        });
        ((SearchView) _$_findCachedViewById(R.id.search_et)).setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.initListener.2
            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String query) {
                Intrinsics.checkNotNullParameter(query, "query");
                UpdateListingViewModel updateListingViewModel = DaftarWilayahActivity.this.updateListingViewModel;
                if (updateListingViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
                    updateListingViewModel = null;
                }
                List<AssignmentRegionWilayahPojo> listFilter = updateListingViewModel.filter(query);
                Intrinsics.checkNotNull(listFilter);
                DaftarWilayahAdapter daftarWilayahAdapter = DaftarWilayahActivity.this.mAdapter;
                Intrinsics.checkNotNull(daftarWilayahAdapter);
                daftarWilayahAdapter.setData$app_release(listFilter, DaftarWilayahActivity.this.samplingRegionOffline);
                return true;
            }

            @Override // androidx.appcompat.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String newText) {
                Intrinsics.checkNotNullParameter(newText, "newText");
                newText.length();
                return true;
            }
        });
        ((Button) _$_findCachedViewById(R.id.syncNotEmptyData_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahActivity.initListener$lambda$2(this.f$0, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.syncEmptyData_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahActivity.initListener$lambda$3(this.f$0, view);
            }
        });
        if (Network.INSTANCE.isOnline(this)) {
            if (SsoHelper.INSTANCE.checkExpSession()) {
                checkTarikSample();
            } else {
                SsoHelper.INSTANCE.requestRefreshToken(this, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.initListener.5
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
                            DaftarWilayahActivity.this.checkTarikSample();
                        } else {
                            DaftarWilayahActivity.this.hideProgressBar();
                            DaftarWilayahActivity.this.showAlertDialogKodeVerifikasiLogout();
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this$0);
        bottomSheetDialog.setContentView(R.layout.bottom_dialog_sort_wilayah);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_buttomDialog)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DaftarWilayahActivity.initListener$lambda$1$lambda$0(bottomSheetDialog, view2);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1$lambda$0(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showDialogSyncAssignment();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateListingViewModel updateListingViewModel = this$0.updateListingViewModel;
        if (updateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel = null;
        }
        updateListingViewModel.sync();
    }

    public final void showDialogSyncAssignment() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_sync_assingment);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setCancelable(true);
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahActivity.showDialogSyncAssignment$lambda$4(bottomSheetDialog, view);
            }
        });
        ((Button) bottomSheetDialog.findViewById(R.id.checkNotifAssignment_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DaftarWilayahActivity.showDialogSyncAssignment$lambda$5(bottomSheetDialog, this, view);
            }
        });
        long sessionLong = FasihApp.INSTANCE.getSession().getSessionLong(CommonCons.TIMESTAMP_AVAIABLE_SYNC_ALL_ASS, 0L);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis < sessionLong) {
            ((Button) bottomSheetDialog.findViewById(R.id.syncAllAssignment_b)).setEnabled(false);
            ((TextView) bottomSheetDialog.findViewById(R.id.avaiableSyncAllAssignment_tv)).setText("Sync seluruh assignment dapat diakses " + TimeUnit.MILLISECONDS.toMinutes(sessionLong - jCurrentTimeMillis) + " menit lagi");
            ((TextView) bottomSheetDialog.findViewById(R.id.avaiableSyncAllAssignment_tv)).setVisibility(0);
        } else {
            ((Button) bottomSheetDialog.findViewById(R.id.syncAllAssignment_b)).setVisibility(0);
            ((TextView) bottomSheetDialog.findViewById(R.id.avaiableSyncAllAssignment_tv)).setVisibility(8);
            ((Button) bottomSheetDialog.findViewById(R.id.syncAllAssignment_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DaftarWilayahActivity.showDialogSyncAssignment$lambda$8(bottomSheetDialog, this, view);
                }
            });
        }
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogSyncAssignment$lambda$4(BottomSheetDialog dialogSyncAssignment, View view) {
        Intrinsics.checkNotNullParameter(dialogSyncAssignment, "$dialogSyncAssignment");
        dialogSyncAssignment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogSyncAssignment$lambda$5(BottomSheetDialog dialogSyncAssignment, DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialogSyncAssignment, "$dialogSyncAssignment");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogSyncAssignment.dismiss();
        UpdateListingViewModel updateListingViewModel = this$0.updateListingViewModel;
        if (updateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel = null;
        }
        updateListingViewModel.checkNotificationAssignment(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogSyncAssignment$lambda$8(BottomSheetDialog dialogSyncAssignment, final DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(dialogSyncAssignment, "$dialogSyncAssignment");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialogSyncAssignment.dismiss();
        BaseClassActivityNew.showAlertDialog$default(this$0, "Peringatan", "Anda hanya dapat melakukan sync seluruh assignment setiap " + TimeUnit.MILLISECONDS.toMinutes(RemoteConfigHelper.INSTANCE.getWaitingTime(CommonCons.REMOTE_CONFIG_WAITING_TIME_SYNC_ALL_ASSIGNMENT)) + " menit sekali.", null, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DaftarWilayahActivity.showDialogSyncAssignment$lambda$8$lambda$7(view2);
            }
        }, "Sync", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                DaftarWilayahActivity.showDialogSyncAssignment$lambda$8$lambda$6(this.f$0, view2);
            }
        }, false, true, 128, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogSyncAssignment$lambda$8$lambda$6(DaftarWilayahActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UpdateListingViewModel updateListingViewModel = this$0.updateListingViewModel;
        if (updateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("updateListingViewModel");
            updateListingViewModel = null;
        }
        updateListingViewModel.sync();
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).setVisibility(8);
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).stopShimmerAnimation();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_blok_sensus)).startShimmerAnimation();
    }

    private final void setDefaultAppBar() {
        BaseClassActivityNew.setAppBar$default(this, 0, "Daftar Wilayah", Integer.valueOf(R.menu.menu_daftar_wilayah), new MenuItem.OnMenuItemClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda0
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return DaftarWilayahActivity.setDefaultAppBar$lambda$9(this.f$0, menuItem);
            }
        }, null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setDefaultAppBar$lambda$9(DaftarWilayahActivity this$0, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        if (itemId == R.id.cekTarikSampleOffline) {
            this$0.checkTarikSample();
            return true;
        }
        if (itemId != R.id.mini_dashboard) {
            return true;
        }
        Intent intent = new Intent(this$0, (Class<?>) MiniDashboardActivity.class);
        intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), this$0.mPeriodeId);
        this$0.startActivity(intent);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkTarikSample() {
        TarikSampelRepositoryImpl tarikSampelRepositoryImpl = new TarikSampelRepositoryImpl();
        String str = this.mPeriodeIdOnly;
        Intrinsics.checkNotNull(str);
        tarikSampelRepositoryImpl.getTarikSample(str, new Function1<TarikSampleResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.checkTarikSample.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TarikSampleResponse tarikSampleResponse) {
                invoke2(tarikSampleResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final TarikSampleResponse tarikSampleResponse) {
                TarikSample data;
                List<ContentItem> content;
                Boolean success;
                if (!((tarikSampleResponse == null || (success = tarikSampleResponse.getSuccess()) == null) ? false : success.booleanValue()) || tarikSampleResponse == null || (data = tarikSampleResponse.getData()) == null || (content = data.getContent()) == null) {
                    return;
                }
                final DaftarWilayahActivity daftarWilayahActivity = DaftarWilayahActivity.this;
                for (final ContentItem contentItem : content) {
                    TarikSampelRepositoryImpl tarikSampelRepositoryImpl2 = new TarikSampelRepositoryImpl();
                    String id2 = contentItem != null ? contentItem.getId() : null;
                    Intrinsics.checkNotNull(id2);
                    tarikSampelRepositoryImpl2.getTarikSampleConfig(id2, new Function1<TarikSampleConfigResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkTarikSample$1$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(TarikSampleConfigResponse tarikSampleConfigResponse) {
                            invoke2(tarikSampleConfigResponse);
                            return Unit.INSTANCE;
                        }

                        /* compiled from: DaftarWilayahActivity.kt */
                        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                        @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkTarikSample$1$1$1$1", f = "DaftarWilayahActivity.kt", i = {0, 0}, l = {432}, m = "invokeSuspend", n = {"$this$launch", "fullCodes"}, s = {"L$0", "L$1"})
                        /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkTarikSample$1$1$1$1, reason: invalid class name */
                        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                            final /* synthetic */ TarikSampleConfigResponse $config;
                            final /* synthetic */ ContentItem $it;
                            private /* synthetic */ Object L$0;
                            Object L$1;
                            int label;
                            final /* synthetic */ DaftarWilayahActivity this$0;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            AnonymousClass1(ContentItem contentItem, TarikSampleConfigResponse tarikSampleConfigResponse, DaftarWilayahActivity daftarWilayahActivity, Continuation<? super AnonymousClass1> continuation) {
                                super(2, continuation);
                                this.$it = contentItem;
                                this.$config = tarikSampleConfigResponse;
                                this.this$0 = daftarWilayahActivity;
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$it, this.$config, this.this$0, continuation);
                                anonymousClass1.L$0 = obj;
                                return anonymousClass1;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object obj) throws Throwable {
                                final CoroutineScope coroutineScope;
                                String str;
                                Object objWithContext;
                                List list;
                                TarikSampleConfig data;
                                TarikSampleConfig data2;
                                TarikSampleConfig data3;
                                Exception e;
                                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                int i = this.label;
                                if (i == 0) {
                                    ResultKt.throwOnFailure(obj);
                                    coroutineScope = (CoroutineScope) this.L$0;
                                    TarikSampleRepository tarikSampleRepository = DataSurvey.TarikSample.INSTANCE.getTarikSampleRepository();
                                    String id2 = this.$it.getId();
                                    Source source = this.$it.getSource();
                                    String id3 = source != null ? source.getId() : null;
                                    Intrinsics.checkNotNull(id3);
                                    Target target = this.$it.getTarget();
                                    String id4 = target != null ? target.getId() : null;
                                    Intrinsics.checkNotNull(id4);
                                    Script script = this.$it.getScript();
                                    String id5 = script != null ? script.getId() : null;
                                    Intrinsics.checkNotNull(id5);
                                    TarikSampleConfigResponse tarikSampleConfigResponse = this.$config;
                                    String script2 = (tarikSampleConfigResponse == null || (data3 = tarikSampleConfigResponse.getData()) == null) ? null : data3.getScript();
                                    Gson gson = new Gson();
                                    TarikSampleConfigResponse tarikSampleConfigResponse2 = this.$config;
                                    String json = gson.toJson((tarikSampleConfigResponse2 == null || (data2 = tarikSampleConfigResponse2.getData()) == null) ? null : data2.getSourceSchema());
                                    Gson gson2 = new Gson();
                                    TarikSampleConfigResponse tarikSampleConfigResponse3 = this.$config;
                                    str = ">>>>";
                                    tarikSampleRepository.insertData(new TarikSampleEntity(id2, id3, id4, id5, script2, json, gson2.toJson((tarikSampleConfigResponse3 == null || (data = tarikSampleConfigResponse3.getData()) == null) ? null : data.getTargetSchema()), new Gson().toJson(this.$it.getSource()).toString(), new Gson().toJson(this.$it.getTarget()).toString()));
                                    List fullcode = this.this$0.getFullcode();
                                    if (!fullcode.isEmpty()) {
                                        try {
                                            this.L$0 = coroutineScope;
                                            this.L$1 = fullcode;
                                            this.label = 1;
                                            objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new DaftarWilayahActivity$checkTarikSample$1$1$1$1$sampling$1(this.this$0, null), this);
                                            if (objWithContext == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                            list = fullcode;
                                        } catch (Exception e2) {
                                            e = e2;
                                            Log.e(str, "Error fetching sampling data: " + e.getMessage());
                                            return Unit.INSTANCE;
                                        }
                                    } else {
                                        Log.d(str, "No fullCodes available.");
                                        return Unit.INSTANCE;
                                    }
                                } else {
                                    if (i != 1) {
                                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                    }
                                    list = (List) this.L$1;
                                    coroutineScope = (CoroutineScope) this.L$0;
                                    try {
                                        ResultKt.throwOnFailure(obj);
                                        objWithContext = obj;
                                        str = ">>>>";
                                    } catch (Exception e3) {
                                        e = e3;
                                        str = ">>>>";
                                        Log.e(str, "Error fetching sampling data: " + e.getMessage());
                                        return Unit.INSTANCE;
                                    }
                                }
                                TarikSampelRepositoryImpl tarikSampelRepositoryImpl = new TarikSampelRepositoryImpl();
                                String id6 = ((TarikSampleEntity) objWithContext).getId();
                                String[] strArr = (String[]) list.toArray(new String[0]);
                                String[] strArr2 = (String[]) Arrays.copyOf(strArr, strArr.length);
                                final DaftarWilayahActivity daftarWilayahActivity = this.this$0;
                                tarikSampelRepositoryImpl.getRegion(id6, strArr2, new Function1<SamplingRegionResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.checkTarikSample.1.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(SamplingRegionResponse samplingRegionResponse) {
                                        invoke2(samplingRegionResponse);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(SamplingRegionResponse samplingRegionResponse) {
                                        if (samplingRegionResponse != null) {
                                            DaftarWilayahActivity daftarWilayahActivity2 = daftarWilayahActivity;
                                            if (!Intrinsics.areEqual((Object) samplingRegionResponse.getSuccess(), (Object) true)) {
                                                Log.d(">>>>", "Sampling request failed.");
                                                return;
                                            }
                                            Iterator<T> it = samplingRegionResponse.getData().iterator();
                                            while (it.hasNext()) {
                                                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1((SamplingRegionModelItem) it.next(), daftarWilayahActivity2, null), 3, null);
                                            }
                                            daftarWilayahActivity2.initObserve();
                                            return;
                                        }
                                        Log.d(">>>>", "Sampling response is null.");
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(TarikSampleConfigResponse tarikSampleConfigResponse) {
                            TarikSampleResponse tarikSampleResponse2 = tarikSampleResponse;
                            Boolean success2 = tarikSampleResponse2 != null ? tarikSampleResponse2.getSuccess() : null;
                            Intrinsics.checkNotNull(success2);
                            if (success2.booleanValue()) {
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(daftarWilayahActivity), null, null, new AnonymousClass1(contentItem, tarikSampleConfigResponse, daftarWilayahActivity, null), 3, null);
                            }
                        }
                    });
                }
            }
        });
        Toast.makeText(getApplicationContext(), "Sukses memperbarui data", 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> getFullcode() {
        ArrayList arrayList = new ArrayList();
        List<AssignmentRegionWilayahPojo> list = this.listAssignment;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                String smallest_region_full_code = ((AssignmentRegionWilayahPojo) it.next()).getAssignmentRegion().getSmallest_region_full_code();
                if (smallest_region_full_code != null) {
                    arrayList.add(smallest_region_full_code);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkRegionSupportDataState() {
        String str = this.mPeriodeIdOnly;
        if (str == null || this.hasPromptedRegionMetadata || this.isDownloadingRegionSupportData) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass1(str, null), 3, null);
    }

    /* compiled from: DaftarWilayahActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkRegionSupportDataState$1", f = "DaftarWilayahActivity.kt", i = {2, 3, 3}, l = {494, 500, 504, 509}, m = "invokeSuspend", n = {"assignmentRegionCount", "assignmentRegionCount", "missingRegionCount"}, s = {"I$0", "I$0", "I$1"})
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkRegionSupportDataState$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $periodeId;
        int I$0;
        int I$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$periodeId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DaftarWilayahActivity.this.new AnonymousClass1(this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x009b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:27:0x009c  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00c3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00c4  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00d5  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00d8  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 261
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void downloadRegionSupportData() {
        final String str = this.mPeriodeIdOnly;
        if (str == null || this.isDownloadingRegionSupportData) {
            return;
        }
        if (!Network.INSTANCE.isOnline(this)) {
            int i = R.color.error30;
            int i2 = R.color.error30;
            showAlertDialogColor("Gagal", Integer.valueOf(i), "Tidak dapat mengunduh data wilayah. Periksa koneksi internet lalu coba lagi.", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DaftarWilayahActivity.downloadRegionSupportData$lambda$11(view);
                }
            }, null, null, null, Integer.valueOf(R.color.error30), true);
            this.hasPromptedRegionMetadata = false;
            return;
        }
        this.isDownloadingRegionSupportData = true;
        showProgressBar();
        new RegionRepositoryImpl().getAssignmentRegion(str, new Function1<AssignmentRegionResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.downloadRegionSupportData.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentRegionResponse assignmentRegionResponse) {
                invoke2(assignmentRegionResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentRegionResponse assignmentRegionResponse) {
                if (assignmentRegionResponse == null) {
                    DaftarWilayahActivity.this.handleRegionSupportDownloadError("Data wilayah gagal diunduh.");
                    return;
                }
                boolean z = true;
                if (!Intrinsics.areEqual((Object) assignmentRegionResponse.getSuccess(), (Object) true)) {
                    DaftarWilayahActivity daftarWilayahActivity = DaftarWilayahActivity.this;
                    String message = assignmentRegionResponse.getMessage();
                    daftarWilayahActivity.handleRegionSupportDownloadError(message != null ? message : "Data wilayah gagal diunduh.");
                    return;
                }
                List<AssignmentRegionEntity> data = assignmentRegionResponse.getData();
                if (data != null && !data.isEmpty()) {
                    z = false;
                }
                if (z) {
                    DaftarWilayahActivity.this.handleRegionSupportDownloadError("Data wilayah tidak tersedia untuk periode ini.");
                    return;
                }
                List<AssignmentRegionEntity> listMapIdToAssignmentRegion = AssignmentRegionEntity.INSTANCE.mapIdToAssignmentRegion(assignmentRegionResponse.getData());
                String str2 = str;
                final DaftarWilayahActivity daftarWilayahActivity2 = DaftarWilayahActivity.this;
                final String str3 = str;
                new DPAssignmentRegion(str2, listMapIdToAssignmentRegion, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.downloadRegionSupportData.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z2) {
                        if (!z2) {
                            daftarWilayahActivity2.handleRegionSupportDownloadError("Data wilayah gagal disimpan.");
                            return;
                        }
                        RegionRepositoryImpl regionRepositoryImpl = new RegionRepositoryImpl();
                        String str4 = str3;
                        final DaftarWilayahActivity daftarWilayahActivity3 = daftarWilayahActivity2;
                        final String str5 = str3;
                        regionRepositoryImpl.getRegionMetadata(str4, new Function1<RegionMetadataResponse, Unit>() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity.downloadRegionSupportData.2.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* compiled from: DaftarWilayahActivity.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                            @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$downloadRegionSupportData$2$1$1$1", f = "DaftarWilayahActivity.kt", i = {}, l = {591}, m = "invokeSuspend", n = {}, s = {})
                            /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$downloadRegionSupportData$2$1$1$1, reason: invalid class name and collision with other inner class name */
                            static final class C02001 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                final /* synthetic */ RegionMetadataResponse $metadataResult;
                                final /* synthetic */ String $periodeId;
                                int label;
                                final /* synthetic */ DaftarWilayahActivity this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                C02001(RegionMetadataResponse regionMetadataResponse, DaftarWilayahActivity daftarWilayahActivity, String str, Continuation<? super C02001> continuation) {
                                    super(2, continuation);
                                    this.$metadataResult = regionMetadataResponse;
                                    this.this$0 = daftarWilayahActivity;
                                    this.$periodeId = str;
                                }

                                /* JADX INFO: Access modifiers changed from: private */
                                public static final void invokeSuspend$lambda$0(View view) {
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                    return new C02001(this.$metadataResult, this.this$0, this.$periodeId, continuation);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                    return ((C02001) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object obj) {
                                    String message;
                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    int i = this.label;
                                    if (i == 0) {
                                        ResultKt.throwOnFailure(obj);
                                        RegionMetadataResponse regionMetadataResponse = this.$metadataResult;
                                        if ((regionMetadataResponse != null ? Intrinsics.areEqual(regionMetadataResponse.getSuccess(), Boxing.boxBoolean(true)) : false) && this.$metadataResult.getData() != null) {
                                            this.label = 1;
                                            if (BuildersKt.withContext(Dispatchers.getIO(), new C02011(this.$periodeId, this.$metadataResult, null), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            DaftarWilayahActivity daftarWilayahActivity = this.this$0;
                                            RegionMetadataResponse regionMetadataResponse2 = this.$metadataResult;
                                            if (regionMetadataResponse2 == null || (message = regionMetadataResponse2.getMessage()) == null) {
                                                message = "Metadata wilayah gagal diunduh.";
                                            }
                                            daftarWilayahActivity.handleRegionSupportDownloadError(message);
                                            return Unit.INSTANCE;
                                        }
                                    } else {
                                        if (i != 1) {
                                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                        }
                                        ResultKt.throwOnFailure(obj);
                                    }
                                    this.this$0.hideProgressBar();
                                    this.this$0.isDownloadingRegionSupportData = false;
                                    this.this$0.showAlertDialogColor("Sukses", Boxing.boxInt(R.color.success30), "Data wilayah berhasil diunduh. Daftar wilayah sekarang siap digunakan.", Boxing.boxInt(R.color.success30), null, "Lanjut", Boxing.boxInt(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$downloadRegionSupportData$2$1$1$1$$ExternalSyntheticLambda0
                                        @Override // android.view.View.OnClickListener
                                        public final void onClick(View view) {
                                            DaftarWilayahActivity.AnonymousClass2.AnonymousClass1.C01991.C02001.invokeSuspend$lambda$0(view);
                                        }
                                    }, null, null, null, Boxing.boxInt(R.color.success30), true);
                                    return Unit.INSTANCE;
                                }

                                /* compiled from: DaftarWilayahActivity.kt */
                                @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
                                @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$downloadRegionSupportData$2$1$1$1$1", f = "DaftarWilayahActivity.kt", i = {}, l = {593}, m = "invokeSuspend", n = {}, s = {})
                                /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$downloadRegionSupportData$2$1$1$1$1, reason: invalid class name and collision with other inner class name */
                                static final class C02011 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                    final /* synthetic */ RegionMetadataResponse $metadataResult;
                                    final /* synthetic */ String $periodeId;
                                    int label;

                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    C02011(String str, RegionMetadataResponse regionMetadataResponse, Continuation<? super C02011> continuation) {
                                        super(2, continuation);
                                        this.$periodeId = str;
                                        this.$metadataResult = regionMetadataResponse;
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                        return new C02011(this.$periodeId, this.$metadataResult, continuation);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                        return ((C02011) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                    }

                                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                    public final Object invokeSuspend(Object obj) {
                                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                        int i = this.label;
                                        if (i == 0) {
                                            ResultKt.throwOnFailure(obj);
                                            this.label = 1;
                                            if (DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().updateRegionMetadataByPeriode(this.$periodeId, this.$metadataResult.getData(), this) == coroutine_suspended) {
                                                return coroutine_suspended;
                                            }
                                        } else {
                                            if (i != 1) {
                                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                            }
                                            ResultKt.throwOnFailure(obj);
                                        }
                                        return Unit.INSTANCE;
                                    }
                                }
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(RegionMetadataResponse regionMetadataResponse) {
                                invoke2(regionMetadataResponse);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(RegionMetadataResponse regionMetadataResponse) {
                                BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(daftarWilayahActivity3), null, null, new C02001(regionMetadataResponse, daftarWilayahActivity3, str5, null), 3, null);
                            }
                        });
                    }
                });
            }
        });
    }

    /* compiled from: DaftarWilayahActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$handleRegionSupportDownloadError$1", f = "DaftarWilayahActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$handleRegionSupportDownloadError$1, reason: invalid class name and case insensitive filesystem */
    static final class C08531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $message;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08531(String str, Continuation<? super C08531> continuation) {
            super(2, continuation);
            this.$message = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DaftarWilayahActivity.this.new C08531(this.$message, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DaftarWilayahActivity.this.hideProgressBar();
            DaftarWilayahActivity.this.isDownloadingRegionSupportData = false;
            DaftarWilayahActivity.this.hasPromptedRegionMetadata = false;
            int i = R.color.error30;
            int i2 = R.color.error30;
            final DaftarWilayahActivity daftarWilayahActivity = DaftarWilayahActivity.this;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$handleRegionSupportDownloadError$1$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DaftarWilayahActivity.access$downloadRegionSupportData(daftarWilayahActivity);
                }
            };
            int i3 = R.drawable.layout_button_primary;
            final DaftarWilayahActivity daftarWilayahActivity2 = DaftarWilayahActivity.this;
            DaftarWilayahActivity.this.showAlertDialogColor("Gagal", Boxing.boxInt(i), this.$message, Boxing.boxInt(i2), null, "Unduh Ulang", Boxing.boxInt(i3), onClickListener, "Kembali", Boxing.boxInt(R.drawable.layout_button_secondary), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$handleRegionSupportDownloadError$1$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    daftarWilayahActivity2.finish();
                }
            }, null, false);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleRegionSupportDownloadError(String message) {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C08531(message, null), 3, null);
    }
}
