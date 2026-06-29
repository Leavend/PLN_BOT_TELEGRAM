package id.go.bpsfasih.ui.assignmentList;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nambimobile.widgets.efab.ExpandableFab;
import com.nambimobile.widgets.efab.FabOption;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.data.local.repository.SamplingRegionRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.localserver.Server;
import id.go.bpsfasih.databinding.ActivityAssignmentlistBinding;
import id.go.bpsfasih.ui.assignmentList.AssignmentListActivity;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import id.go.bpsfasih.ui.petaSebaranAssignment.PetaSebaranAssignmentActivity;
import id.go.bpsfasih.ui.tariksampel.TarikSampelActivity;
import id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity;
import id.go.bpsfasih.ui.uploadactivity.UploadActivity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import io.github.hyuwah.draggableviewlib.DraggableUtils;
import io.github.hyuwah.draggableviewlib.DraggableView;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AssignmentListActivity.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0005¢\u0006\u0002\u0010\u0002J(\u0010)\u001a\u00020\u00182\u0006\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u00182\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002J\u0010\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020\u0018H\u0002J\b\u00103\u001a\u000204H\u0002J\r\u00105\u001a\u000204H\u0001¢\u0006\u0002\b6J\u0012\u00107\u001a\u0002042\b\u00108\u001a\u0004\u0018\u000109H\u0015J\b\u0010:\u001a\u000204H\u0014J\u001c\u0010;\u001a\u0002042\b\u0010<\u001a\u0004\u0018\u00010\u00182\b\u0010=\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010>\u001a\u000204H\u0002J\b\u0010?\u001a\u000204H\u0002J\u0016\u0010@\u001a\u0002042\u0006\u0010*\u001a\u00020\u00182\u0006\u0010+\u001a\u00020\u0018R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020(0'X\u0082.¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "assignmentAdded", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "assignmentUpdateListingViewModel", "Lid/go/bpsfasih/ui/assignmentList/AssignmentUpdateListingViewModel;", "binding", "Lid/go/bpsfasih/databinding/ActivityAssignmentlistBinding;", "isTarikSampleDone", "", "()Ljava/lang/Boolean;", "setTarikSampleDone", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "jsi", "Lid/go/bpsfasih/ui/assignmentList/JavaScriptInterfaceAssignment;", "getJsi$app_release", "()Lid/go/bpsfasih/ui/assignmentList/JavaScriptInterfaceAssignment;", "setJsi$app_release", "(Lid/go/bpsfasih/ui/assignmentList/JavaScriptInterfaceAssignment;)V", "mCanAddSample", "mIsPencacah", "mPeriodId", "", "mRegionFullCode", "mRegionId", "mRegionName", "mSurveyId", "mTemplateId", "periodeResultQuery", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "getPeriodeResultQuery", "()Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "setPeriodeResultQuery", "(Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;)V", "samplingRegionOffline", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "someDraggableView", "Lio/github/hyuwah/draggableviewlib/DraggableView;", "Lcom/nambimobile/widgets/efab/ExpandableFab;", "buildAssignmentInfoMessage", "assignmentId", "surveyId", "localSubmitVersionCode", "", "serverSubmitVersionCode", "formatFileSize", "size", "", "getSafeRegionName", "initServer", "", "loadContent", "loadContent$app_release", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "sampling", "periodeId", "fullcode", "setChangeModeAppBar", "setDefaultAppBar", "showDialogAksi", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentListActivity extends BaseClassActivityNew {
    private AssignmentEntity assignmentAdded;
    private AssignmentUpdateListingViewModel assignmentUpdateListingViewModel;
    private ActivityAssignmentlistBinding binding;
    private JavaScriptInterfaceAssignment jsi;
    private boolean mCanAddSample;
    private Boolean mIsPencacah;
    private String mPeriodId;
    private String mRegionFullCode;
    private String mRegionId;
    private String mRegionName;
    private String mSurveyId;
    private String mTemplateId;
    private PeriodeEntityNew periodeResultQuery;
    private SamplingRegionEntity samplingRegionOffline;
    private DraggableView<ExpandableFab> someDraggableView;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private Boolean isTarikSampleDone = true;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$13$lambda$10(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$13$lambda$12(View view) {
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

    /* renamed from: isTarikSampleDone, reason: from getter */
    public final Boolean getIsTarikSampleDone() {
        return this.isTarikSampleDone;
    }

    public final void setTarikSampleDone(Boolean bool) {
        this.isTarikSampleDone = bool;
    }

    public final PeriodeEntityNew getPeriodeResultQuery() {
        return this.periodeResultQuery;
    }

    public final void setPeriodeResultQuery(PeriodeEntityNew periodeEntityNew) {
        this.periodeResultQuery = periodeEntityNew;
    }

    /* compiled from: AssignmentListActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Ja\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\b2\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lid/go/bpsfasih/ui/assignmentList/AssignmentListActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "surveyId", "", "periodId", "regionId", "regionFullCode", "regionName", "templateId", "isPencacah", "", "canAddSample", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Z)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String surveyId, String periodId, String regionId, String regionFullCode, String regionName, String templateId, Boolean isPencacah, boolean canAddSample) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent intent = new Intent(context, (Class<?>) AssignmentListActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), surveyId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), periodId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_ID(), regionId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE(), regionFullCode);
            intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_NAME(), regionName);
            intent.putExtra(CommonCons.INSTANCE.getTEMPLATE_ID(), templateId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_IS_PENCACAH(), isPencacah);
            intent.putExtra(CommonCons.INSTANCE.getKEY_CAN_ADD_SAMPLE(), canAddSample);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    /* renamed from: getJsi$app_release, reason: from getter */
    public final JavaScriptInterfaceAssignment getJsi() {
        return this.jsi;
    }

    public final void setJsi$app_release(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment) {
        this.jsi = javaScriptInterfaceAssignment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSafeRegionName() {
        String str = this.mRegionName;
        String str2 = null;
        if (str != null) {
            if (!(!StringsKt.isBlank(str))) {
                str = null;
            }
            if (str != null) {
                return str;
            }
        }
        String str3 = this.mRegionFullCode;
        if (str3 != null && (!StringsKt.isBlank(str3))) {
            str2 = str3;
        }
        return str2 == null ? "Wilayah" : str2;
    }

    private final String buildAssignmentInfoMessage(String assignmentId, String surveyId, int localSubmitVersionCode, int serverSubmitVersionCode) {
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String str = this.mPeriodId;
        if (str == null) {
            str = "";
        }
        File file = new File(companion.pathAnswerAssignment(surveyId, str, assignmentId), "data.json");
        return "Version Code lokal: " + localSubmitVersionCode + "\nVersion Code server: " + serverSubmitVersionCode + "\nUkuran data : " + (file.exists() ? formatFileSize(file.length()) : "Tidak ada");
    }

    private final String formatFileSize(long size) {
        if (size >= 1024) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format(Locale.US, "%.1f KB", Arrays.copyOf(new Object[]{Double.valueOf(size / 1024.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
            return str;
        }
        return size + " B";
    }

    /* JADX WARN: Type inference failed for: r2v14, types: [T, id.go.bpsfasih.data.local.repository.PeriodeRepository] */
    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws InterruptedException {
        super.onCreate(savedInstanceState);
        initServer();
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.activity_assignmentlist);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l….activity_assignmentlist)");
        this.binding = (ActivityAssignmentlistBinding) contentView;
        this.mSurveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        this.mPeriodId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        this.mRegionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_ID());
        this.mRegionFullCode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE());
        this.mRegionName = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_NAME());
        this.mTemplateId = (String) BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(null), 1, null);
        this.mIsPencacah = Boolean.valueOf(getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_IS_PENCACAH(), false));
        this.mCanAddSample = getIntent().getBooleanExtra(CommonCons.INSTANCE.getKEY_CAN_ADD_SAMPLE(), false);
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("unduh_change_mode")) {
            try {
                SurveyEntity surveyEntity = (SurveyEntity) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentListActivity$onCreate$survey$1(this, null), 1, null);
                if (surveyEntity.getSurveyModes() != null) {
                    List<String> surveyModes = surveyEntity.getSurveyModes();
                    Integer numValueOf = surveyModes != null ? Integer.valueOf(surveyModes.size()) : null;
                    Intrinsics.checkNotNull(numValueOf);
                    if (numValueOf.intValue() > 1) {
                        setChangeModeAppBar();
                    } else {
                        setDefaultAppBar();
                    }
                } else {
                    setDefaultAppBar();
                }
            } catch (Exception unused) {
                setDefaultAppBar();
            }
        } else {
            setDefaultAppBar();
        }
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        String str = this.mSurveyId;
        Intrinsics.checkNotNull(str);
        String str2 = this.mPeriodId;
        Intrinsics.checkNotNull(str2);
        String str3 = this.mRegionId;
        Intrinsics.checkNotNull(str3);
        String str4 = this.mTemplateId;
        Intrinsics.checkNotNull(str4);
        this.assignmentUpdateListingViewModel = (AssignmentUpdateListingViewModel) new ViewModelProvider(this, new UpdateListingAssignmentViewModelFactory(application, str, str2, str3, str4, this, this.mCanAddSample)).get(AssignmentUpdateListingViewModel.class);
        ActivityAssignmentlistBinding activityAssignmentlistBinding = this.binding;
        if (activityAssignmentlistBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAssignmentlistBinding = null;
        }
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel = null;
        }
        activityAssignmentlistBinding.setViewModel(assignmentUpdateListingViewModel);
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel2 = this.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel2 = null;
        }
        AssignmentListActivity assignmentListActivity = this;
        assignmentUpdateListingViewModel2.getAssignmentAdded().observe(assignmentListActivity, new Observer<AssignmentEntity>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.onCreate.2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(AssignmentEntity it) {
                Intrinsics.checkNotNullParameter(it, "it");
                final AssignmentListActivity assignmentListActivity2 = AssignmentListActivity.this;
                assignmentListActivity2.assignmentAdded = it;
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = assignmentListActivity2.assignmentUpdateListingViewModel;
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = null;
                if (assignmentUpdateListingViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                    assignmentUpdateListingViewModel3 = null;
                }
                AssignmentEntity assignmentEntity = assignmentListActivity2.assignmentAdded;
                Intrinsics.checkNotNull(assignmentEntity);
                assignmentUpdateListingViewModel3.openAssignment(assignmentEntity);
                AssignmentUpdateListingViewModel assignmentUpdateListingViewModel5 = assignmentListActivity2.assignmentUpdateListingViewModel;
                if (assignmentUpdateListingViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                } else {
                    assignmentUpdateListingViewModel4 = assignmentUpdateListingViewModel5;
                }
                assignmentUpdateListingViewModel4.isCanOpen().observe(assignmentListActivity2, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$onCreate$2$onChanged$1$1
                    @Override // androidx.lifecycle.Observer
                    public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                        onChanged(bool.booleanValue());
                    }

                    public final void onChanged(boolean z) {
                        FormGearActivity.Companion companion = FormGearActivity.Companion;
                        Context applicationContext = assignmentListActivity2.getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                        String str5 = assignmentListActivity2.mTemplateId;
                        Boolean bool = assignmentListActivity2.mIsPencacah;
                        String str6 = assignmentListActivity2.mPeriodId;
                        AssignmentEntity assignmentEntity2 = assignmentListActivity2.assignmentAdded;
                        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel6 = assignmentListActivity2.assignmentUpdateListingViewModel;
                        if (assignmentUpdateListingViewModel6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                            assignmentUpdateListingViewModel6 = null;
                        }
                        companion.startActivity(applicationContext, str5, bool, str6, assignmentEntity2, false, false, true, assignmentUpdateListingViewModel6.getUserLatLng().getValue(), true);
                    }
                });
            }
        });
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel3 = this.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel3 = null;
        }
        assignmentUpdateListingViewModel3.isAssignmentDeleted().observe(assignmentListActivity, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.onCreate.3
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                onChanged(bool.booleanValue());
            }

            public final void onChanged(boolean z) {
                if (z) {
                    AssignmentListActivity.this.loadContent$app_release();
                    AssignmentUpdateListingViewModel assignmentUpdateListingViewModel4 = AssignmentListActivity.this.assignmentUpdateListingViewModel;
                    if (assignmentUpdateListingViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                        assignmentUpdateListingViewModel4 = null;
                    }
                    assignmentUpdateListingViewModel4.setDeleteAssignment();
                }
            }
        });
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = DataSurvey.Periode.INSTANCE.getPeriodeRepository();
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass4(objectRef, null), 1, null);
        this.someDraggableView = new DraggableView.Builder((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab)).build();
        this.someDraggableView = DraggableUtils.setupDraggable((ExpandableFab) _$_findCachedViewById(R.id.expendable_fab)).build();
    }

    /* compiled from: AssignmentListActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$onCreate$1", f = "AssignmentListActivity.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$onCreate$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentListActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                TemplateValidationRepository templateValidationRepository = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
                String str = AssignmentListActivity.this.mSurveyId;
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

    /* compiled from: AssignmentListActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$onCreate$4", f = "AssignmentListActivity.kt", i = {}, l = {223}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$onCreate$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<PeriodeRepository> $periodeRepo;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass4(Ref.ObjectRef<PeriodeRepository> objectRef, Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
            this.$periodeRepo = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentListActivity.this.new AnonymousClass4(this.$periodeRepo, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            AssignmentListActivity assignmentListActivity;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                AssignmentListActivity assignmentListActivity2 = AssignmentListActivity.this;
                PeriodeRepository periodeRepository = this.$periodeRepo.element;
                String str = AssignmentListActivity.this.mPeriodId;
                Intrinsics.checkNotNull(str);
                this.L$0 = assignmentListActivity2;
                this.label = 1;
                Object periodeById = periodeRepository.getPeriodeById(str, this);
                if (periodeById == coroutine_suspended) {
                    return coroutine_suspended;
                }
                assignmentListActivity = assignmentListActivity2;
                obj = periodeById;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                assignmentListActivity = (AssignmentListActivity) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            assignmentListActivity.setPeriodeResultQuery((PeriodeEntityNew) obj);
            return Unit.INSTANCE;
        }
    }

    private final void setDefaultAppBar() {
        setAppBar(0, "Daftar Assignment", Integer.valueOf(R.menu.menu_assignment), new MenuItem.OnMenuItemClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda3
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return AssignmentListActivity.setDefaultAppBar$lambda$2(this.f$0, menuItem);
            }
        }, this.mPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setDefaultAppBar$lambda$2(AssignmentListActivity this$0, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        if (itemId == R.id.daftarUpload) {
            this$0.startActivity(new Intent(this$0, (Class<?>) UploadActivity.class));
            return true;
        }
        if (itemId == R.id.customColumn) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this$0.assignmentUpdateListingViewModel;
            if (assignmentUpdateListingViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                assignmentUpdateListingViewModel = null;
            }
            assignmentUpdateListingViewModel.customColumn();
            return true;
        }
        if (itemId == R.id.mapsAssigntment) {
            Intent intent = new Intent(this$0, (Class<?>) PetaSebaranAssignmentActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_ID(), this$0.mRegionId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), this$0.mSurveyId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), this$0.mPeriodId);
            this$0.startActivity(intent);
            return true;
        }
        if (itemId == R.id.tarikSampel) {
            this$0.sampling(this$0.mPeriodId, this$0.mRegionFullCode);
            return true;
        }
        if (itemId != R.id.refresh) {
            return true;
        }
        this$0.finish();
        this$0.startActivity(this$0.getIntent());
        return true;
    }

    private final void sampling(String periodeId, String fullcode) {
        Log.d("<<<", periodeId);
        Log.d("<<<", fullcode);
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C08271(periodeId, fullcode, null), 3, null);
    }

    /* compiled from: AssignmentListActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$sampling$1", f = "AssignmentListActivity.kt", i = {}, l = {269}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$sampling$1, reason: invalid class name and case insensitive filesystem */
    static final class C08271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $fullcode;
        final /* synthetic */ String $periodeId;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C08271(String str, String str2, Continuation<? super C08271> continuation) {
            super(2, continuation);
            this.$periodeId = str;
            this.$fullcode = str2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$0(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentListActivity.this.new C08271(this.$periodeId, this.$fullcode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C08271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object objWithContext;
            AssignmentListActivity assignmentListActivity;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AssignmentListActivity assignmentListActivity2 = AssignmentListActivity.this;
                    this.L$0 = assignmentListActivity2;
                    this.label = 1;
                    objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C01731(this.$periodeId, this.$fullcode, null), this);
                    if (objWithContext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    assignmentListActivity = assignmentListActivity2;
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    assignmentListActivity = (AssignmentListActivity) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    objWithContext = obj;
                }
                assignmentListActivity.samplingRegionOffline = (SamplingRegionEntity) objWithContext;
                SamplingRegionEntity samplingRegionEntity = AssignmentListActivity.this.samplingRegionOffline;
                if (StringsKt.equals$default(samplingRegionEntity != null ? samplingRegionEntity.getMode() : null, CommonCons.INSTANCE.getKEY_OFFLINE_MODE(), false, 2, null)) {
                    Intent intent = new Intent(AssignmentListActivity.this, (Class<?>) TarikSampelActivity.class);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE(), AssignmentListActivity.this.mRegionFullCode);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_ID(), AssignmentListActivity.this.mRegionId);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), AssignmentListActivity.this.mSurveyId);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), AssignmentListActivity.this.mPeriodId);
                    String key_sampling_region_id = CommonCons.INSTANCE.getKEY_SAMPLING_REGION_ID();
                    SamplingRegionEntity samplingRegionEntity2 = AssignmentListActivity.this.samplingRegionOffline;
                    intent.putExtra(key_sampling_region_id, samplingRegionEntity2 != null ? samplingRegionEntity2.getId() : null);
                    intent.putExtra(CommonCons.INSTANCE.getKEY_MODE(), CommonCons.INSTANCE.getKEY_OFFLINE_MODE());
                    AssignmentListActivity.this.startActivity(intent);
                } else if (Intrinsics.areEqual((Boolean) BuildersKt__BuildersKt.runBlocking$default(null, new AssignmentListActivity$sampling$1$canExecute$1(AssignmentListActivity.this, null), 1, null), Boxing.boxBoolean(true))) {
                    Intent intent2 = new Intent(AssignmentListActivity.this, (Class<?>) TarikSampelActivity.class);
                    intent2.putExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE(), AssignmentListActivity.this.mRegionFullCode);
                    intent2.putExtra(CommonCons.INSTANCE.getKEY_REGION_ID(), AssignmentListActivity.this.mRegionId);
                    intent2.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), AssignmentListActivity.this.mPeriodId);
                    intent2.putExtra(CommonCons.INSTANCE.getKEY_MODE(), CommonCons.INSTANCE.getKEY_ONLINE_MODE());
                    AssignmentListActivity.this.startActivity(intent2);
                } else {
                    AssignmentListActivity.this.showAlertDialogColor("Gagal", Boxing.boxInt(R.color.error30), "Anda tidak memiliki wewenang untuk melakukan tarik sampel", Boxing.boxInt(R.color.error30), null, "Tutup", Boxing.boxInt(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$sampling$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AssignmentListActivity.C08271.invokeSuspend$lambda$0(view);
                        }
                    }, null, null, null, Boxing.boxInt(R.color.error30), true);
                }
            } catch (Exception e) {
                Log.e(">>>", "Error fetching sampling region: " + e.getMessage(), e);
            }
            return Unit.INSTANCE;
        }

        /* compiled from: AssignmentListActivity.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$sampling$1$1", f = "AssignmentListActivity.kt", i = {}, l = {270}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$sampling$1$1, reason: invalid class name and collision with other inner class name */
        static final class C01731 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SamplingRegionEntity>, Object> {
            final /* synthetic */ String $fullcode;
            final /* synthetic */ String $periodeId;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01731(String str, String str2, Continuation<? super C01731> continuation) {
                super(2, continuation);
                this.$periodeId = str;
                this.$fullcode = str2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01731(this.$periodeId, this.$fullcode, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SamplingRegionEntity> continuation) {
                return ((C01731) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SamplingRegionRepository samplingRegionRepository = DataSurvey.SamplingRegion.INSTANCE.getSamplingRegionRepository();
                    String str = this.$periodeId;
                    Intrinsics.checkNotNull(str);
                    String str2 = this.$fullcode;
                    Intrinsics.checkNotNull(str2);
                    this.label = 1;
                    obj = samplingRegionRepository.getSamplingRegion(str, str2, this);
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

    private final void setChangeModeAppBar() {
        setAppBar(0, "Daftar Assignment", Integer.valueOf(R.menu.menu_assignment_change_mode), new MenuItem.OnMenuItemClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda2
            @Override // android.view.MenuItem.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return AssignmentListActivity.setChangeModeAppBar$lambda$3(this.f$0, menuItem);
            }
        }, this.mPeriodId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setChangeModeAppBar$lambda$3(AssignmentListActivity this$0, MenuItem it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        int itemId = it.getItemId();
        if (itemId == R.id.daftarUpload) {
            this$0.startActivity(new Intent(this$0, (Class<?>) UploadActivity.class));
            return true;
        }
        if (itemId == R.id.customColumn) {
            AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this$0.assignmentUpdateListingViewModel;
            if (assignmentUpdateListingViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
                assignmentUpdateListingViewModel = null;
            }
            assignmentUpdateListingViewModel.customColumn();
            return true;
        }
        if (itemId == R.id.mapsAssigntment) {
            Intent intent = new Intent(this$0, (Class<?>) PetaSebaranAssignmentActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_REGION_ID(), this$0.mRegionId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID(), this$0.mSurveyId);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), this$0.mPeriodId);
            this$0.startActivity(intent);
            return true;
        }
        if (itemId == R.id.unduhAssigntmentChangeMode) {
            UnduhChangeModeActivity.Companion companion = UnduhChangeModeActivity.INSTANCE;
            AssignmentListActivity assignmentListActivity = this$0;
            String str = this$0.mPeriodId;
            Intrinsics.checkNotNull(str);
            companion.startActivity(assignmentListActivity, str);
            return true;
        }
        if (itemId == R.id.tarikSampel) {
            Intent intent2 = new Intent(this$0, (Class<?>) TarikSampelActivity.class);
            intent2.putExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE(), this$0.mRegionFullCode);
            intent2.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), this$0.mPeriodId);
            this$0.startActivity(intent2);
            return true;
        }
        if (itemId != R.id.refresh) {
            return true;
        }
        this$0.finish();
        this$0.startActivity(this$0.getIntent());
        return true;
    }

    private final void initServer() {
        Server.HttpServerThread httpServerThread = new Server.HttpServerThread();
        if (httpServerThread.isAlive()) {
            return;
        }
        Log.d(InstrumentationResultPrinter.REPORT_KEY_NAME_TEST, "serverThread ");
        httpServerThread.start();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        loadContent$app_release();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, java.lang.Boolean] */
    public final void loadContent$app_release() {
        showProgressBar();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = false;
        ((FabOption) _$_findCachedViewById(R.id.fab_colorInfo)).setFabOptionEnabled(true);
        ((FabOption) _$_findCachedViewById(R.id.fab_filterByStatus)).setFabOptionEnabled(true);
        ((FabOption) _$_findCachedViewById(R.id.fab_sorting)).setFabOptionEnabled(true);
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel = null;
        }
        assignmentUpdateListingViewModel.getAssignmentRegion(this.mPeriodId, this.mRegionId, new AssignmentListActivity$loadContent$1(this, objectRef));
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void showDialogAksi(final java.lang.String r17, final java.lang.String r18) {
        /*
            Method dump skipped, instructions count: 456
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.showDialogAksi(java.lang.String, java.lang.String):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$4(BottomSheetDialog dialogAksi, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        dialogAksi.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$5(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, String surveyId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        Intrinsics.checkNotNullParameter(surveyId, "$surveyId");
        dialogAksi.dismiss();
        JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0.jsi;
        if (javaScriptInterfaceAssignment != null) {
            javaScriptInterfaceAssignment.openAssignment(assignmentId, surveyId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$6(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        dialogAksi.dismiss();
        JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0.jsi;
        if (javaScriptInterfaceAssignment != null) {
            javaScriptInterfaceAssignment.downloadAssignmentData(assignmentId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$7(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        dialogAksi.dismiss();
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this$0.assignmentUpdateListingViewModel;
        if (assignmentUpdateListingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assignmentUpdateListingViewModel");
            assignmentUpdateListingViewModel = null;
        }
        assignmentUpdateListingViewModel.assignAssignment(assignmentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$8(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        dialogAksi.dismiss();
        JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0.jsi;
        if (javaScriptInterfaceAssignment != null) {
            javaScriptInterfaceAssignment.restoreAssignment(assignmentId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$9(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        dialogAksi.dismiss();
        JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0.jsi;
        if (javaScriptInterfaceAssignment != null) {
            javaScriptInterfaceAssignment.changeModeAssignment(assignmentId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void showDialogAksi$lambda$13(com.google.android.material.bottomsheet.BottomSheetDialog r18, final id.go.bpsfasih.data.local.entities.AssignmentEntity r19, final id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r20, final java.lang.String r21, java.lang.String r22, int r23, android.view.View r24) {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            java.lang.String r4 = "$dialogAksi"
            r5 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
            java.lang.String r4 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "$assignmentId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            java.lang.String r4 = "$surveyId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r4)
            r18.dismiss()
            id.go.bpsfasih.utils.helper.RemoteConfigHelper$Companion r4 = id.go.bpsfasih.utils.helper.RemoteConfigHelper.INSTANCE
            java.lang.String r5 = "reset_submit_version_code"
            boolean r4 = r4.getFeaturesRemoteConfigIsShow(r5)
            r5 = 0
            if (r4 == 0) goto L42
            r4 = 1
            if (r0 == 0) goto L3e
            java.lang.Integer r6 = r19.getAssignmentStatusId()
            if (r6 != 0) goto L36
            goto L3e
        L36:
            int r6 = r6.intValue()
            if (r6 != 0) goto L3e
            r6 = r4
            goto L3f
        L3e:
            r6 = r5
        L3f:
            if (r6 == 0) goto L42
            goto L43
        L42:
            r4 = r5
        L43:
            if (r0 == 0) goto L49
            int r5 = r19.getSubmitVersionCode()
        L49:
            r6 = r23
            java.lang.String r8 = r1.buildAssignmentInfoMessage(r2, r3, r6, r5)
            if (r4 == 0) goto L6f
            r6 = r1
            id.go.bpsfasih.BaseClassActivityNew r6 = (id.go.bpsfasih.BaseClassActivityNew) r6
            java.lang.String r7 = "Info Assignment"
            r9 = 0
            java.lang.String r10 = "Tutup"
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda11 r11 = new id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda11
            r11.<init>()
            java.lang.String r12 = "Samakan Version Code"
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda12 r13 = new id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda12
            r13.<init>()
            r14 = 0
            r15 = 0
            r16 = 384(0x180, float:5.38E-43)
            r17 = 0
            id.go.bpsfasih.BaseClassActivityNew.showAlertDialog$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
            goto L87
        L6f:
            r6 = r1
            id.go.bpsfasih.BaseClassActivityNew r6 = (id.go.bpsfasih.BaseClassActivityNew) r6
            java.lang.String r7 = "Info Assignment"
            r9 = 0
            java.lang.String r10 = "Tutup"
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda1 r11 = new id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$$ExternalSyntheticLambda1
            r11.<init>()
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 384(0x180, float:5.38E-43)
            r17 = 0
            id.go.bpsfasih.BaseClassActivityNew.showAlertDialog$default(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity.showDialogAksi$lambda$13(com.google.android.material.bottomsheet.BottomSheetDialog, id.go.bpsfasih.data.local.entities.AssignmentEntity, id.go.bpsfasih.ui.assignmentList.AssignmentListActivity, java.lang.String, java.lang.String, int, android.view.View):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$13$lambda$11(AssignmentListActivity this$0, String assignmentId, AssignmentEntity assignmentEntity, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this$0), null, null, new AssignmentListActivity$showDialogAksi$7$2$1(assignmentId, assignmentEntity, this$0, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogAksi$lambda$14(BottomSheetDialog dialogAksi, AssignmentListActivity this$0, String assignmentId, View view) {
        Intrinsics.checkNotNullParameter(dialogAksi, "$dialogAksi");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        dialogAksi.dismiss();
        JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this$0.jsi;
        if (javaScriptInterfaceAssignment != null) {
            javaScriptInterfaceAssignment.deleteAssignment(assignmentId);
        }
    }
}
