package id.go.bpsfasih.ui.survey;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.test.internal.runner.listener.InstrumentationResultPrinter;
import com.google.android.gms.actions.SearchIntents;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.entities.TemplateLookupList;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.ui.periode.PeriodeActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SurveyViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\nJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000eJ$\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010%\u001a\u00020\u0005H\u0002J\u000e\u0010&\u001a\u00020!2\u0006\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020!2\u0006\u0010%\u001a\u00020\u0005R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006*"}, d2 = {"Lid/go/bpsfasih/ui/survey/SurveyViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "surveyId", "", "updateListing", "", "isPencacah", "surveyParentId", "(Landroid/app/Application;Ljava/lang/String;ZZLjava/lang/String;)V", "allSurvey", "Landroidx/lifecycle/MediatorLiveData;", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getAllSurvey", "()Landroidx/lifecycle/MediatorLiveData;", "originalSurveys", "Landroidx/lifecycle/LiveData;", "receiveFile", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "getReceiveFile", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "requesting", "getRequesting", "searchQuery", "Landroidx/lifecycle/MutableLiveData;", "templateId", "getTemplateId", "()Ljava/lang/String;", "setTemplateId", "(Ljava/lang/String;)V", "checkPeriode", "", InstrumentationResultPrinter.REPORT_KEY_NUM_CURRENT, "filterSurveys", "surveys", SearchIntents.EXTRA_QUERY, "goToPeriode", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", FirebaseAnalytics.Event.SEARCH, "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final MediatorLiveData<List<SurveyPojo>> allSurvey;
    private final LiveData<List<SurveyPojo>> originalSurveys;
    private final SingleLiveEvent<Boolean> receiveFile;
    private final SingleLiveEvent<Boolean> requesting;
    private final MutableLiveData<String> searchQuery;
    private String templateId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SurveyViewModel(Application application, String str, boolean z, boolean z2, String str2) {
        LiveData surveys;
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.requesting = new SingleLiveEvent<>();
        this.templateId = "";
        this.receiveFile = new SingleLiveEvent<>();
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>("");
        this.searchQuery = mutableLiveData;
        MediatorLiveData<List<SurveyPojo>> mediatorLiveData = new MediatorLiveData<>();
        this.allSurvey = mediatorLiveData;
        String str3 = str2;
        if (str3 == null || str3.length() == 0) {
            DataSurvey.Survey.INSTANCE.getSurveyRepo().getAllSurvey();
            surveys = DataSurvey.Survey.INSTANCE.getSurveyRepo().getSurveys();
        } else {
            DataSurvey.Survey.INSTANCE.getSurveyRepo().getSurveyByParentId(str2);
            surveys = DataSurvey.Survey.INSTANCE.getSurveyRepo().getSurveys();
        }
        this.originalSurveys = surveys;
        mediatorLiveData.addSource(surveys, new SurveyViewModel$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends SurveyPojo>, Unit>() { // from class: id.go.bpsfasih.ui.survey.SurveyViewModel.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends SurveyPojo> list) {
                invoke2((List<SurveyPojo>) list);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<SurveyPojo> surveys2) {
                MediatorLiveData<List<SurveyPojo>> allSurvey = SurveyViewModel.this.getAllSurvey();
                SurveyViewModel surveyViewModel = SurveyViewModel.this;
                Intrinsics.checkNotNullExpressionValue(surveys2, "surveys");
                String str4 = (String) SurveyViewModel.this.searchQuery.getValue();
                if (str4 == null) {
                    str4 = "";
                }
                allSurvey.setValue(surveyViewModel.filterSurveys(surveys2, str4));
            }
        }));
        mediatorLiveData.addSource(mutableLiveData, new SurveyViewModel$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.survey.SurveyViewModel.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str4) {
                invoke2(str4);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String query) {
                MediatorLiveData<List<SurveyPojo>> allSurvey = SurveyViewModel.this.getAllSurvey();
                SurveyViewModel surveyViewModel = SurveyViewModel.this;
                List listEmptyList = (List) surveyViewModel.originalSurveys.getValue();
                if (listEmptyList == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                Intrinsics.checkNotNullExpressionValue(query, "query");
                allSurvey.setValue(surveyViewModel.filterSurveys(listEmptyList, query));
            }
        }));
    }

    public final SingleLiveEvent<Boolean> getRequesting() {
        return this.requesting;
    }

    public final String getTemplateId() {
        return this.templateId;
    }

    public final void setTemplateId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.templateId = str;
    }

    public final SingleLiveEvent<Boolean> getReceiveFile() {
        return this.receiveFile;
    }

    public final MediatorLiveData<List<SurveyPojo>> getAllSurvey() {
        return this.allSurvey;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<SurveyPojo> filterSurveys(List<SurveyPojo> surveys, String query) {
        String str = query;
        if (str.length() == 0) {
            return surveys;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : surveys) {
            SurveyPojo surveyPojo = (SurveyPojo) obj;
            if (StringsKt.contains((CharSequence) surveyPojo.getSurvey().getName(), (CharSequence) str, true) || StringsKt.contains((CharSequence) surveyPojo.getSurvey().getId(), (CharSequence) str, true)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public final void search(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        this.searchQuery.setValue(query);
    }

    /* compiled from: SurveyViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.survey.SurveyViewModel$checkPeriode$1", f = "SurveyViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.survey.SurveyViewModel$checkPeriode$1, reason: invalid class name and case insensitive filesystem */
    static final class C09181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SurveyPojo $current;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09181(SurveyPojo surveyPojo, Continuation<? super C09181> continuation) {
            super(2, continuation);
            this.$current = surveyPojo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SurveyViewModel.this.new C09181(this.$current, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            SurveyViewModel.this.goToPeriode(this.$current.getSurvey());
            return Unit.INSTANCE;
        }
    }

    public final void checkPeriode(SurveyPojo current) {
        Intrinsics.checkNotNullParameter(current, "current");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C09181(current, null), 3, null);
    }

    public final void goToPeriode(SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        List<TemplateLookupList> templateLookup = survey.getTemplateLookup();
        Unit unit = null;
        if (templateLookup != null) {
            if (templateLookup.size() > 0) {
                PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), (String) BuildersKt__BuildersKt.runBlocking$default(null, new SurveyViewModel$goToPeriode$1$templateId$1(survey, null), 1, null), survey.getName());
            } else {
                PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), survey.getSurveyTemplateId(), survey.getName());
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), survey.getSurveyTemplateId(), survey.getName());
        }
    }
}
