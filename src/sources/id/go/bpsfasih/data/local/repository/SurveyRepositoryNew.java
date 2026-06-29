package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.SurveyDAONew;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.data.local.pojo.Sync;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: SurveyRepository.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016J\u0006\u0010\u0017\u001a\u00020\u0011J\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00070\u0006J\u0011\u0010\u001a\u001a\u00020\u001bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0006J\u0019\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0019\u0010\"\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0010\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010 J\u0019\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020 H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0006\u0010'\u001a\u00020\u0011J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00130\u0007J\u000e\u0010(\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\"\u0010)\u001a\u00020\u00112\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00130\u00072\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016J\u000e\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020 R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR&\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\n\"\u0004\b\u000f\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lid/go/bpsfasih/data/local/repository/SurveyRepositoryNew;", "", "surveyDAO", "Lid/go/bpsfasih/data/local/dao/SurveyDAONew;", "(Lid/go/bpsfasih/data/local/dao/SurveyDAONew;)V", "surveyHomePojo", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/SurveyPojo;", "getSurveyHomePojo", "()Landroidx/lifecycle/LiveData;", "setSurveyHomePojo", "(Landroidx/lifecycle/LiveData;)V", "surveys", "getSurveys", "setSurveys", "delete", "", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "deleteAll", "callback", "Lkotlin/Function0;", "getAllSurvey", "getAllSurveyBySync", "Lid/go/bpsfasih/data/local/pojo/Sync;", "getJumlahSurveys", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getJumlahSurveysLiveData", "getSurveyById", DownloadModel.ID, "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSurveyByIdPrimary", "getSurveyByParentId", "surveyParentId", "getSurveyId", "periodeId", "getSurveyPojoHome", "insert", "insertAll", "listItem", "update", "updateStatusPin", "surveyIdPrimary", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SurveyRepositoryNew {
    public static final int $stable = 8;
    private final SurveyDAONew surveyDAO;
    private LiveData<List<SurveyPojo>> surveyHomePojo;
    private LiveData<List<SurveyPojo>> surveys;

    public SurveyRepositoryNew(SurveyDAONew surveyDAO) {
        Intrinsics.checkNotNullParameter(surveyDAO, "surveyDAO");
        this.surveyDAO = surveyDAO;
        this.surveyHomePojo = surveyDAO.getAllSurveyPojo(FasihApp.INSTANCE.getSession().getUserId());
        this.surveys = surveyDAO.getAllSurvey(FasihApp.INSTANCE.getSession().getUserId());
    }

    public final LiveData<List<SurveyPojo>> getSurveyHomePojo() {
        return this.surveyHomePojo;
    }

    public final void setSurveyHomePojo(LiveData<List<SurveyPojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.surveyHomePojo = liveData;
    }

    public final LiveData<List<SurveyPojo>> getSurveys() {
        return this.surveys;
    }

    public final void setSurveys(LiveData<List<SurveyPojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.surveys = liveData;
    }

    public final void insert(final SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.insert$lambda$0(this.f$0, survey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(SurveyRepositoryNew this$0, SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(survey, "$survey");
        this$0.surveyDAO.insert(survey);
    }

    public final LiveData<List<Sync>> getAllSurveyBySync() {
        return this.surveyDAO.getAllSurveyBySync(FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void delete(final SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.delete$lambda$1(this.f$0, survey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$1(SurveyRepositoryNew this$0, SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(survey, "$survey");
        this$0.surveyDAO.deleteSurvey(survey);
    }

    public final void deleteAll(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.deleteAll$lambda$2(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$2(SurveyRepositoryNew this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.surveyDAO.deleteAll(FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    public final void update(final SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(survey, "survey");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.update$lambda$3(this.f$0, survey);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void update$lambda$3(SurveyRepositoryNew this$0, SurveyEntity survey) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(survey, "$survey");
        this$0.surveyDAO.updateSurvey(survey);
    }

    public final void insertAll(final List<SurveyEntity> listItem, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(listItem, "listItem");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.insertAll$lambda$4(this.f$0, listItem, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAll$lambda$4(SurveyRepositoryNew this$0, List listItem, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listItem, "$listItem");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.surveyDAO.insertAll(listItem);
        callback.invoke();
    }

    public final void getSurveyByParentId(String surveyParentId) {
        if (surveyParentId != null) {
            this.surveys = this.surveyDAO.getAllSurveyByParentId(surveyParentId, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final void getAllSurvey() {
        this.surveys = this.surveyDAO.getAllSurvey(FasihApp.INSTANCE.getSession().getUserId());
    }

    /* renamed from: getSurveys, reason: collision with other method in class */
    public final List<SurveyEntity> m6642getSurveys() {
        return this.surveyDAO.getSurveys(FasihApp.INSTANCE.getSession().getUserId());
    }

    /* compiled from: SurveyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getJumlahSurveys$2", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getJumlahSurveys$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SurveyRepositoryNew.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(SurveyRepositoryNew.this.surveyDAO.getJumlahSurveys(FasihApp.INSTANCE.getSession().getUserId()));
        }
    }

    public final Object getJumlahSurveys(Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(null), continuation);
    }

    public final LiveData<Integer> getJumlahSurveysLiveData() {
        return this.surveyDAO.getJumlahSurveysLiveData(FasihApp.INSTANCE.getSession().getUserId());
    }

    /* compiled from: SurveyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyId$2", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07362 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $periodeId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07362(String str, Continuation<? super C07362> continuation) {
            super(2, continuation);
            this.$periodeId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SurveyRepositoryNew.this.new C07362(this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C07362) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return SurveyRepositoryNew.this.surveyDAO.getSurveyId(this.$periodeId, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getSurveyId(String str, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07362(str, null), continuation);
    }

    /* compiled from: SurveyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyById$2", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyById$2, reason: invalid class name and case insensitive filesystem */
    static final class C07342 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SurveyEntity>, Object> {
        final /* synthetic */ String $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07342(String str, Continuation<? super C07342> continuation) {
            super(2, continuation);
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SurveyRepositoryNew.this.new C07342(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SurveyEntity> continuation) {
            return ((C07342) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return SurveyRepositoryNew.this.surveyDAO.getSurveyByIdSingle(this.$id, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getSurveyById(String str, Continuation<? super SurveyEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07342(str, null), continuation);
    }

    public final void getSurveyPojoHome() {
        this.surveyHomePojo = this.surveyDAO.getAllSurveyPojo(FasihApp.INSTANCE.getSession().getUserId());
    }

    /* compiled from: SurveyRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyByIdPrimary$2", f = "SurveyRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$getSurveyByIdPrimary$2, reason: invalid class name and case insensitive filesystem */
    static final class C07352 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SurveyEntity>, Object> {
        final /* synthetic */ String $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07352(String str, Continuation<? super C07352> continuation) {
            super(2, continuation);
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SurveyRepositoryNew.this.new C07352(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SurveyEntity> continuation) {
            return ((C07352) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return SurveyRepositoryNew.this.surveyDAO.getSurveyByIdPrimarySingle(this.$id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSurveyByIdPrimary(String str, Continuation<? super SurveyEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07352(str, null), continuation);
    }

    public final void updateStatusPin(final String surveyIdPrimary) {
        Intrinsics.checkNotNullParameter(surveyIdPrimary, "surveyIdPrimary");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.SurveyRepositoryNew$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                SurveyRepositoryNew.updateStatusPin$lambda$5(this.f$0, surveyIdPrimary);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateStatusPin$lambda$5(SurveyRepositoryNew this$0, String surveyIdPrimary) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(surveyIdPrimary, "$surveyIdPrimary");
        this$0.surveyDAO.updateStatusPin(surveyIdPrimary);
    }
}
