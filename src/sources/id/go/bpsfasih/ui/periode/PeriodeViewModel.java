package id.go.bpsfasih.ui.periode;

import android.app.Application;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import id.go.bpsfasih.data.local.models.PeriodeUpdateResponse;
import id.go.bpsfasih.data.local.models.TemplateValidasiVersionModel;
import id.go.bpsfasih.data.local.models.TemplateValidationResponse;
import id.go.bpsfasih.data.local.pojo.PeriodePojo;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import id.go.bpsfasih.data.repository.NotificationRepositoryImpl;
import id.go.bpsfasih.ui.periode.PeriodeViewModel;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.sync.reqDownload.RDPeriode;
import id.go.bpsfasih.utils.sync.reqDownload.RDTemplateValidationNotif;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.apache.http.cookie.ClientCookie;

/* compiled from: PeriodeViewModel.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u00102\u001a\u000203J\u000e\u00104\u001a\u0002032\u0006\u00105\u001a\u000206J\b\u00107\u001a\u0004\u0018\u000108J\u0006\u00109\u001a\u000203J\u0006\u0010\r\u001a\u000203J\u0006\u0010-\u001a\u000203J\u000e\u0010:\u001a\u0002032\u0006\u0010;\u001a\u00020\u0005J\u0006\u0010<\u001a\u000203J\u0006\u0010=\u001a\u000203J.\u0010>\u001a\u0002032\b\u0010;\u001a\u0004\u0018\u00010\u00052\b\u0010?\u001a\u0004\u0018\u00010\u00052\b\u0010@\u001a\u0004\u0018\u00010\u00052\b\u0010A\u001a\u0004\u0018\u00010\u0005R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R(\u0010\u0012\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 R\u001a\u0010$\u001a\u00020%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010+\"\u0004\b.\u0010/R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lid/go/bpsfasih/ui/periode/PeriodeViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "surveyId", "", "activity", "Lid/go/bpsfasih/ui/periode/PeriodeActivity;", "(Landroid/app/Application;Ljava/lang/String;Lid/go/bpsfasih/ui/periode/PeriodeActivity;)V", "getActivity", "()Lid/go/bpsfasih/ui/periode/PeriodeActivity;", "formEngineId", "", "getFormEngineId", "()Ljava/lang/Integer;", "setFormEngineId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "periodBySurvey", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/PeriodePojo;", "getPeriodBySurvey", "()Landroidx/lifecycle/LiveData;", "setPeriodBySurvey", "(Landroidx/lifecycle/LiveData;)V", "showProgressBar", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getShowProgressBar", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setShowProgressBar", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "showProgressBarDialog", "getShowProgressBarDialog", "setShowProgressBarDialog", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "getSurvey", "()Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "setSurvey", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;)V", "getSurveyId", "()Ljava/lang/String;", "templateId", "getTemplateId", "setTemplateId", "(Ljava/lang/String;)V", "templateValidationRepo", "Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;", "checkVersionTemplateValidation", "", "downloadTemplateValidation", "templateValidation", "Lid/go/bpsfasih/data/local/entities/TemplateValidationEntity;", "getDataTemplateValidationVersion", "Lid/go/bpsfasih/data/local/models/TemplateValidasiVersionModel;", "getFormEngineBrandName", "requestPeriodeUpdate", "periodeId", "requestTemplateValidation", "syncPeriode", "updatePeriode", "name", "startDate", "endDate", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PeriodeViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final PeriodeActivity activity;
    private Integer formEngineId;
    private LiveData<List<PeriodePojo>> periodBySurvey;
    private SingleLiveEvent<Boolean> showProgressBar;
    private SingleLiveEvent<Boolean> showProgressBarDialog;
    private SurveyEntity survey;
    private final String surveyId;
    private String templateId;
    private final TemplateValidationRepository templateValidationRepo;

    public final void getFormEngineBrandName() {
    }

    public /* synthetic */ PeriodeViewModel(Application application, String str, PeriodeActivity periodeActivity, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(application, (i & 2) != 0 ? "" : str, periodeActivity);
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final PeriodeActivity getActivity() {
        return this.activity;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PeriodeViewModel(Application application, String str, PeriodeActivity activity) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.surveyId = str;
        this.activity = activity;
        this.showProgressBar = new SingleLiveEvent<>();
        this.showProgressBarDialog = new SingleLiveEvent<>();
        this.survey = new SurveyEntity();
        this.templateValidationRepo = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
        if (str != null) {
            DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodePojo(str);
            this.periodBySurvey = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodePojoBySurvey();
            this.survey = (SurveyEntity) BuildersKt__BuildersKt.runBlocking$default(null, new PeriodeViewModel$1$1(this, null), 1, null);
        }
    }

    public final LiveData<List<PeriodePojo>> getPeriodBySurvey() {
        return this.periodBySurvey;
    }

    public final void setPeriodBySurvey(LiveData<List<PeriodePojo>> liveData) {
        this.periodBySurvey = liveData;
    }

    public final SingleLiveEvent<Boolean> getShowProgressBar() {
        return this.showProgressBar;
    }

    public final void setShowProgressBar(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.showProgressBar = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getShowProgressBarDialog() {
        return this.showProgressBarDialog;
    }

    public final void setShowProgressBarDialog(SingleLiveEvent<Boolean> singleLiveEvent) {
        Intrinsics.checkNotNullParameter(singleLiveEvent, "<set-?>");
        this.showProgressBarDialog = singleLiveEvent;
    }

    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    public final void setSurvey(SurveyEntity surveyEntity) {
        Intrinsics.checkNotNullParameter(surveyEntity, "<set-?>");
        this.survey = surveyEntity;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final void setTemplateId(String str) {
        this.templateId = str;
    }

    public final Integer getFormEngineId() {
        return this.formEngineId;
    }

    public final void setFormEngineId(Integer num) {
        this.formEngineId = num;
    }

    /* compiled from: PeriodeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.periode.PeriodeViewModel$getTemplateId$1", f = "PeriodeViewModel.kt", i = {}, l = {62}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$getTemplateId$1, reason: invalid class name and case insensitive filesystem */
    static final class C09091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        C09091(Continuation<? super C09091> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeViewModel.this.new C09091(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C09091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository().getDataBySurveyId(PeriodeViewModel.this.getSurvey().getId(), this);
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

    /* renamed from: getTemplateId, reason: collision with other method in class */
    public final void m6814getTemplateId() {
        this.templateId = (String) BuildersKt__BuildersKt.runBlocking$default(null, new C09091(null), 1, null);
    }

    /* compiled from: PeriodeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.periode.PeriodeViewModel$getFormEngineId$1", f = "PeriodeViewModel.kt", i = {}, l = {70}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$getFormEngineId$1, reason: invalid class name and case insensitive filesystem */
    static final class C09081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        C09081(Continuation<? super C09081> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeViewModel.this.new C09081(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C09081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository().getDataBySurveyId(PeriodeViewModel.this.getSurvey().getId(), this);
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
                return Boxing.boxInt(templateValidationEntity.getFormEngineId());
            }
            return null;
        }
    }

    /* renamed from: getFormEngineId, reason: collision with other method in class */
    public final void m6813getFormEngineId() {
        this.formEngineId = (Integer) BuildersKt__BuildersKt.runBlocking$default(null, new C09081(null), 1, null);
    }

    public final TemplateValidasiVersionModel getDataTemplateValidationVersion() {
        try {
            Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathTemplate(this.templateId))), Charsets.UTF_8);
            BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
            try {
                String text = TextStreamsKt.readText(bufferedReader);
                CloseableKt.closeFinally(bufferedReader, null);
                String asString = new JsonParser().parse(text).getAsJsonObject().get(ClientCookie.VERSION_ATTR).getAsString();
                try {
                    Reader inputStreamReader2 = new InputStreamReader(new FileInputStream(new File(FileHelper.INSTANCE.pathValidation(this.templateId))), Charsets.UTF_8);
                    bufferedReader = inputStreamReader2 instanceof BufferedReader ? (BufferedReader) inputStreamReader2 : new BufferedReader(inputStreamReader2, 8192);
                    try {
                        String text2 = TextStreamsKt.readText(bufferedReader);
                        CloseableKt.closeFinally(bufferedReader, null);
                        return new TemplateValidasiVersionModel(this.templateId, asString, new JsonParser().parse(text2).getAsJsonObject().get(ClientCookie.VERSION_ATTR).getAsString());
                    } finally {
                    }
                } catch (JsonParseException | IOException | IllegalStateException unused) {
                    return null;
                }
            } finally {
                try {
                    throw th;
                } finally {
                }
            }
        } catch (JsonParseException | IOException | IllegalStateException unused2) {
            return null;
        }
    }

    public final void checkVersionTemplateValidation() {
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("notif_check_version_template_validation") && Network.INSTANCE.isOnline(this.activity)) {
            this.showProgressBar.postValue(true);
            new NotificationRepositoryImpl().checkVersionTemplateValidation(this.survey.getId(), new AnonymousClass1());
        }
    }

    /* compiled from: PeriodeViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "result", "Lid/go/bpsfasih/data/local/models/TemplateValidationResponse;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$checkVersionTemplateValidation$1, reason: invalid class name */
    static final class AnonymousClass1 extends Lambda implements Function1<TemplateValidationResponse, Unit> {
        AnonymousClass1() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(TemplateValidationResponse templateValidationResponse) {
            invoke2(templateValidationResponse);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(TemplateValidationResponse templateValidationResponse) {
            if (templateValidationResponse != null ? Intrinsics.areEqual((Object) templateValidationResponse.getSuccess(), (Object) true) : false) {
                TemplateValidasiVersionModel dataTemplateValidationVersion = PeriodeViewModel.this.getDataTemplateValidationVersion();
                final TemplateValidationEntity templateValidationEntity = (TemplateValidationEntity) CollectionsKt.first((List) templateValidationResponse.getData());
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C02431(templateValidationEntity, null), 3, null);
                if (dataTemplateValidationVersion == null || !StringsKt.equals$default(dataTemplateValidationVersion.getTemplate_id(), templateValidationEntity.getTemplate_id(), false, 2, null) || !StringsKt.equals$default(dataTemplateValidationVersion.getTemplate_version(), templateValidationEntity.getTemplate_version(), false, 2, null) || !StringsKt.equals$default(dataTemplateValidationVersion.getValidasi_version(), templateValidationEntity.getValidasi_version(), false, 2, null)) {
                    PeriodeActivity activity = PeriodeViewModel.this.getActivity();
                    final PeriodeViewModel periodeViewModel = PeriodeViewModel.this;
                    BaseClassActivityNew.showAlertDialog$default(activity, "Ada Perubahan Data", "Terdapat update template dan validasi pada survei ini. Unduh perubahan data sekarang?", null, "Unduh", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$checkVersionTemplateValidation$1$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            PeriodeViewModel.AnonymousClass1.invoke$lambda$0(periodeViewModel, templateValidationEntity, view);
                        }
                    }, null, null, false, false, 128, null);
                }
            }
            PeriodeViewModel.this.getShowProgressBar().postValue(false);
        }

        /* compiled from: PeriodeViewModel.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        @DebugMetadata(c = "id.go.bpsfasih.ui.periode.PeriodeViewModel$checkVersionTemplateValidation$1$1", f = "PeriodeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$checkVersionTemplateValidation$1$1, reason: invalid class name and collision with other inner class name */
        static final class C02431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ TemplateValidationEntity $dataTemplateValidationServer;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C02431(TemplateValidationEntity templateValidationEntity, Continuation<? super C02431> continuation) {
                super(2, continuation);
                this.$dataTemplateValidationServer = templateValidationEntity;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C02431(this.$dataTemplateValidationServer, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C02431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                TemplateValidationRepository templateValidationRepository = DataSurvey.TemplateValidation.INSTANCE.getTemplateValidationRepository();
                int formEngineId = this.$dataTemplateValidationServer.getFormEngineId();
                String formEngineBrandName = this.$dataTemplateValidationServer.getFormEngineBrandName();
                Intrinsics.checkNotNull(formEngineBrandName);
                templateValidationRepository.updateData(formEngineId, formEngineBrandName, this.$dataTemplateValidationServer.getSurvey_id(), new Function0<Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel.checkVersionTemplateValidation.1.1.1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                });
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(PeriodeViewModel this$0, TemplateValidationEntity dataTemplateValidationServer, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(dataTemplateValidationServer, "$dataTemplateValidationServer");
            if (Network.INSTANCE.isOnline(this$0.getActivity())) {
                this$0.downloadTemplateValidation(TemplateValidationEntity.INSTANCE.mapIdToTemplateValidation(dataTemplateValidationServer));
            }
        }
    }

    public final void requestTemplateValidation() {
        this.showProgressBarDialog.postValue(true);
        new NotificationRepositoryImpl().checkVersionTemplateValidation(this.survey.getId(), new Function1<TemplateValidationResponse, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel.requestTemplateValidation.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(TemplateValidationResponse templateValidationResponse) {
                invoke2(templateValidationResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(TemplateValidationResponse templateValidationResponse) {
                PeriodeViewModel.this.getShowProgressBarDialog().postValue(false);
                if (templateValidationResponse != null ? Intrinsics.areEqual((Object) templateValidationResponse.getSuccess(), (Object) true) : false) {
                    PeriodeViewModel.this.downloadTemplateValidation(TemplateValidationEntity.INSTANCE.mapIdToTemplateValidation((TemplateValidationEntity) CollectionsKt.first((List) templateValidationResponse.getData())));
                } else {
                    Toast.makeText(PeriodeViewModel.this.getActivity(), "Terjadi kesalahan ketika mengunduh template dan validasi", 1).show();
                    PeriodeViewModel.this.getActivity().finish();
                }
            }
        });
    }

    public final void requestPeriodeUpdate(String periodeId) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        new NotificationRepositoryImpl().checkSurveyPeriode(periodeId, new Function1<PeriodeUpdateResponse, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel.requestPeriodeUpdate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(PeriodeUpdateResponse periodeUpdateResponse) throws InterruptedException {
                invoke2(periodeUpdateResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(PeriodeUpdateResponse periodeUpdateResponse) throws InterruptedException {
                if (periodeUpdateResponse != null ? Intrinsics.areEqual((Object) periodeUpdateResponse.getSuccess(), (Object) true) : false) {
                    PeriodeViewModel.this.updatePeriode(periodeUpdateResponse.getData().getId(), periodeUpdateResponse.getData().getName(), periodeUpdateResponse.getData().getStartDate(), periodeUpdateResponse.getData().getEndDate());
                } else {
                    Toast.makeText(PeriodeViewModel.this.getActivity(), "Terjadi kesalahan ketika mengupdate periode", 0).show();
                }
            }
        });
    }

    /* compiled from: PeriodeViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.periode.PeriodeViewModel$updatePeriode$1", f = "PeriodeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$updatePeriode$1, reason: invalid class name and case insensitive filesystem */
    static final class C09131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $endDate;
        final /* synthetic */ String $name;
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $startDate;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09131(String str, String str2, String str3, String str4, Continuation<? super C09131> continuation) {
            super(2, continuation);
            this.$periodeId = str;
            this.$name = str2;
            this.$startDate = str3;
            this.$endDate = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09131(this.$periodeId, this.$name, this.$startDate, this.$endDate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            DataSurvey.Periode.INSTANCE.getPeriodeRepository().updatePeriode(this.$periodeId, this.$name, this.$startDate, this.$endDate, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel.updatePeriode.1.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            });
            return Unit.INSTANCE;
        }
    }

    public final void updatePeriode(String periodeId, String name, String startDate, String endDate) throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new C09131(periodeId, name, startDate, endDate, null), 1, null);
        Toast.makeText(this.activity, "Sukses update periode", 0).show();
    }

    public final void downloadTemplateValidation(final TemplateValidationEntity templateValidation) {
        Intrinsics.checkNotNullParameter(templateValidation, "templateValidation");
        this.showProgressBarDialog.postValue(true);
        new RDTemplateValidationNotif(templateValidation, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel.downloadTemplateValidation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* compiled from: PeriodeViewModel.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "id.go.bpsfasih.ui.periode.PeriodeViewModel$downloadTemplateValidation$1$1", f = "PeriodeViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$downloadTemplateValidation$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ boolean $error;
                final /* synthetic */ String $message;
                final /* synthetic */ TemplateValidationEntity $templateValidation;
                int label;
                final /* synthetic */ PeriodeViewModel this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02451(boolean z, PeriodeViewModel periodeViewModel, String str, TemplateValidationEntity templateValidationEntity, Continuation<? super C02451> continuation) {
                    super(2, continuation);
                    this.$error = z;
                    this.this$0 = periodeViewModel;
                    this.$message = str;
                    this.$templateValidation = templateValidationEntity;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invokeSuspend$lambda$0(View view) {
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02451(this.$error, this.this$0, this.$message, this.$templateValidation, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    if (!this.$error) {
                        this.this$0.getShowProgressBarDialog().postValue(Boxing.boxBoolean(false));
                        PeriodeActivity activity = this.this$0.getActivity();
                        int i = R.color.success30;
                        int i2 = R.color.success30;
                        activity.showAlertDialogColor("Sukses", Boxing.boxInt(i), "Sukses memperbarui template dan validasi", Boxing.boxInt(i2), null, "Tutup", Boxing.boxInt(R.drawable.layout_button_success), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$downloadTemplateValidation$1$1$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                PeriodeViewModel.C09071.C02451.invokeSuspend$lambda$0(view);
                            }
                        }, null, null, null, Boxing.boxInt(R.color.success30), true);
                    } else {
                        this.this$0.getShowProgressBarDialog().postValue(Boxing.boxBoolean(false));
                        PeriodeActivity activity2 = this.this$0.getActivity();
                        int i3 = R.color.error30;
                        int i4 = R.color.error30;
                        final PeriodeViewModel periodeViewModel = this.this$0;
                        final TemplateValidationEntity templateValidationEntity = this.$templateValidation;
                        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$downloadTemplateValidation$1$1$$ExternalSyntheticLambda1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                periodeViewModel.downloadTemplateValidation(templateValidationEntity);
                            }
                        };
                        int i5 = R.drawable.layout_button_primary;
                        final PeriodeViewModel periodeViewModel2 = this.this$0;
                        activity2.showAlertDialogColor("Gagal", Boxing.boxInt(i3), this.$message, Boxing.boxInt(i4), null, "Unduh ulang", Boxing.boxInt(i5), onClickListener, "Tutup", Boxing.boxInt(R.drawable.layout_button_secondary), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$downloadTemplateValidation$1$1$$ExternalSyntheticLambda2
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                PeriodeViewModel.C09071.C02451.invokeSuspend$lambda$2(periodeViewModel2, view);
                            }
                        }, null, false);
                    }
                    return Unit.INSTANCE;
                }

                /* JADX INFO: Access modifiers changed from: private */
                public static final void invokeSuspend$lambda$2(PeriodeViewModel periodeViewModel, View view) {
                    periodeViewModel.getActivity().finish();
                }
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C02451(z, PeriodeViewModel.this, str, templateValidation, null), 2, null);
            }
        });
    }

    /* compiled from: PeriodeViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "listPeriode", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.periode.PeriodeViewModel$syncPeriode$1, reason: invalid class name and case insensitive filesystem */
    static final class C09121 extends Lambda implements Function1<List<PeriodeEntityNew>, Unit> {
        C09121() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<PeriodeEntityNew> list) {
            invoke2(list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<PeriodeEntityNew> list) {
            Unit unit;
            if (list != null) {
                DataSurvey.Periode.INSTANCE.getPeriodeRepository().insertAll(list, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$syncPeriode$1$1$1
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }
                });
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                PeriodeActivity activity = PeriodeViewModel.this.getActivity();
                int i = R.color.error30;
                int i2 = R.color.error30;
                activity.showAlertDialogColor("Gagal", Integer.valueOf(i), "Terdapat kesalahan ketika mendownload daftar periode", Integer.valueOf(i2), null, "Tutup", Integer.valueOf(R.drawable.layout_button_error), new View.OnClickListener() { // from class: id.go.bpsfasih.ui.periode.PeriodeViewModel$syncPeriode$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        PeriodeViewModel.C09121.invoke$lambda$2$lambda$1(view);
                    }
                }, null, null, null, Integer.valueOf(R.color.error30), true);
            }
            PeriodeViewModel.this.getActivity().hideProgressBar();
        }
    }

    public final void syncPeriode() {
        this.activity.showProgressBar();
        new RDPeriode(this.survey, new C09121());
    }
}
