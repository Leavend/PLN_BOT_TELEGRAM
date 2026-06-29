package id.go.bpsfasih.ui.hompage.beranda_fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.entities.TemplateLookupList;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel;
import id.go.bpsfasih.ui.periode.PeriodeActivity;
import id.go.bpsfasih.ui.survey.SurveyActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.sync.reqDownload.RDSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: HomeFragmentViewModel.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010%2\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020)2\u0006\u0010*\u001a\u00020.J\u0006\u0010/\u001a\u00020)J\u0006\u00100\u001a\u00020)J\u0006\u00101\u001a\u00020)R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u0013\u0010\u001f\u001a\u0004\u0018\u00010 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R&\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b'\u0010\u0018¨\u00062"}, d2 = {"Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentViewModel;", "Landroidx/lifecycle/ViewModel;", "activity", "Lid/go/bpsfasih/ui/hompage/HomePageActivity;", "(Lid/go/bpsfasih/ui/hompage/HomePageActivity;)V", "getActivity", "()Lid/go/bpsfasih/ui/hompage/HomePageActivity;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "emailIsClicked", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getEmailIsClicked", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setEmailIsClicked", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "jumlahAssignment", "Landroidx/lifecycle/LiveData;", "", "getJumlahAssignment", "()Landroidx/lifecycle/LiveData;", "setJumlahAssignment", "(Landroidx/lifecycle/LiveData;)V", "jumlahPeriode", "getJumlahPeriode", "setJumlahPeriode", "jumlahSurvey", "getJumlahSurvey", "setJumlahSurvey", "surveyExist", "Landroidx/databinding/ObservableBoolean;", "getSurveyExist", "()Landroidx/databinding/ObservableBoolean;", "surveysLiveData", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getSurveysLiveData", "setSurveysLiveData", "checkSurvey", "", "survey", "fragment", "Landroidx/fragment/app/Fragment;", "goToPeriode", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "syncSurvei", "upload", "viewAll", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class HomeFragmentViewModel extends ViewModel {
    public static final int $stable = 8;
    private final HomePageActivity activity;
    private final Context context;
    private SingleLiveEvent<Boolean> emailIsClicked;
    private LiveData<Integer> jumlahAssignment;
    private LiveData<Integer> jumlahPeriode;
    private LiveData<Integer> jumlahSurvey;
    private final ObservableBoolean surveyExist;
    private LiveData<List<SurveyPojo>> surveysLiveData;

    public HomeFragmentViewModel(HomePageActivity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.context = FasihApp.INSTANCE.getContext();
        this.surveyExist = new ObservableBoolean(false);
        DataSurvey.Survey.INSTANCE.getSurveyRepo().getSurveyPojoHome();
        this.surveysLiveData = DataSurvey.Survey.INSTANCE.getSurveyRepo().getSurveyHomePojo();
        this.jumlahSurvey = DataSurvey.Survey.INSTANCE.getSurveyRepo().getJumlahSurveysLiveData();
        this.jumlahPeriode = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getJumlahPeriode();
        this.jumlahAssignment = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getJumlahAssignmentLiveData();
        this.emailIsClicked = new SingleLiveEvent<>();
        RemoteConfigHelper.INSTANCE.setImageCompressResolution();
    }

    public final HomePageActivity getActivity() {
        return this.activity;
    }

    public final Context getContext() {
        return this.context;
    }

    public final SingleLiveEvent<Boolean> getEmailIsClicked() {
        return this.emailIsClicked;
    }

    public final void setEmailIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.emailIsClicked = singleLiveEvent;
    }

    public final LiveData<List<SurveyPojo>> getSurveysLiveData() {
        return this.surveysLiveData;
    }

    public final void setSurveysLiveData(LiveData<List<SurveyPojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.surveysLiveData = liveData;
    }

    public final LiveData<Integer> getJumlahSurvey() {
        return this.jumlahSurvey;
    }

    public final void setJumlahSurvey(LiveData<Integer> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.jumlahSurvey = liveData;
    }

    public final LiveData<Integer> getJumlahPeriode() {
        return this.jumlahPeriode;
    }

    public final void setJumlahPeriode(LiveData<Integer> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.jumlahPeriode = liveData;
    }

    public final LiveData<Integer> getJumlahAssignment() {
        return this.jumlahAssignment;
    }

    public final void setJumlahAssignment(LiveData<Integer> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.jumlahAssignment = liveData;
    }

    public final ObservableBoolean getSurveyExist() {
        return this.surveyExist;
    }

    public final void viewAll() {
        SurveyActivity.INSTANCE.startActivity(this.context);
    }

    public final void upload() {
        Toast.makeText(FasihApp.INSTANCE.getContext(), "uld", 0).show();
    }

    /* compiled from: HomeFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "surveySync", "", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel$syncSurvei$1, reason: invalid class name and case insensitive filesystem */
    static final class C08971 extends Lambda implements Function1<List<? extends SurveyEntity>, Unit> {
        C08971() {
            super(1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2$lambda$1(View view) {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends SurveyEntity> list) {
            invoke2((List<SurveyEntity>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(List<SurveyEntity> list) {
            Unit unit;
            if (list != null) {
                DataSurvey.Survey.INSTANCE.getSurveyRepo().insertAll(list, new HomeFragmentViewModel$syncSurvei$1$1$1(HomeFragmentViewModel.this));
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            if (unit == null) {
                HomeFragmentViewModel.this.getActivity().showAlertDialog("Gagal", "Terdapat kesalahan ketika mendownload daftar survei", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel$syncSurvei$1$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HomeFragmentViewModel.C08971.invoke$lambda$2$lambda$1(view);
                    }
                }, null, null, true, true);
            }
            HomeFragmentViewModel.this.getActivity().hideProgressBar();
        }
    }

    public final void syncSurvei() {
        this.activity.showProgressBar();
        new RDSurvey(new C08971());
    }

    public final void goToPeriode(SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        List<TemplateLookupList> templateLookup = survey.getTemplateLookup();
        Unit unit = null;
        if (templateLookup != null) {
            if (templateLookup.size() > 0) {
                PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), (String) BuildersKt__BuildersKt.runBlocking$default(null, new HomeFragmentViewModel$goToPeriode$1$templateId$1(survey, null), 1, null), survey.getName());
            } else {
                PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), survey.getSurveyTemplateId(), survey.getName());
            }
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            PeriodeActivity.INSTANCE.startActivity(FasihApp.INSTANCE.getContext(), survey.getUpdateListingType(), survey.getPanelType(), survey.getIdPrimary(), survey.getSurveyTemplateId(), survey.getName());
        }
    }

    /* compiled from: HomeFragmentViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel$checkSurvey$1", f = "HomeFragmentViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragmentViewModel$checkSurvey$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SurveyPojo $survey;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SurveyPojo surveyPojo, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$survey = surveyPojo;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return HomeFragmentViewModel.this.new AnonymousClass1(this.$survey, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            SurveyEntity survey;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            HomeFragmentViewModel.this.getActivity().showProgressBar();
            SurveyPojo surveyPojo = this.$survey;
            if (surveyPojo != null && (survey = surveyPojo.getSurvey()) != null) {
                HomeFragmentViewModel.this.goToPeriode(survey);
            }
            HomeFragmentViewModel.this.getActivity().hideProgressBar();
            return Unit.INSTANCE;
        }
    }

    public final void checkSurvey(SurveyPojo survey, Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(survey, null), 2, null);
    }
}
