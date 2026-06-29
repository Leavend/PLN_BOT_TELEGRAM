package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.PeriodeDAONew;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
import id.go.bpsfasih.data.local.pojo.PeriodePojo;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: PeriodeRepository.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012J\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\bJ\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00160\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ%\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00160\t2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00102\b\b\u0002\u0010\u0004\u001a\u00020\u0005J\u001b\u0010!\u001a\u0004\u0018\u00010\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\u000e\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020\u0016J\"\u0010%\u001a\u00020\u00102\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012J<\u0010'\u001a\u00020\u00102\b\u0010\"\u001a\u0004\u0018\u00010\u00052\b\u0010(\u001a\u0004\u0018\u00010\u00052\b\u0010)\u001a\u0004\u0018\u00010\u00052\b\u0010*\u001a\u0004\u0018\u00010\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lid/go/bpsfasih/data/local/repository/PeriodeRepository;", "", "periodeDAONew", "Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;", "surveyId", "", "(Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;Ljava/lang/String;)V", "periodePojoBySurvey", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/PeriodePojo;", "getPeriodePojoBySurvey", "()Landroidx/lifecycle/LiveData;", "setPeriodePojoBySurvey", "(Landroidx/lifecycle/LiveData;)V", "deleteAll", "", "callback", "Lkotlin/Function0;", "getJumlahPeriode", "", "getPeriodeById", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", DownloadModel.ID, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPeriodeByPrimaryId", "periodePrimaryId", "getPeriodeByUserId", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPeriodeList", "surveyIds", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPeriodePojo", "getSurveyRoleUserId", "periodeId", "insert", "periode", "insertAll", "listItem", "updatePeriode", "name", "startDate", "endDate", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PeriodeRepository {
    public static final int $stable = 8;
    private final PeriodeDAONew periodeDAONew;
    private LiveData<List<PeriodePojo>> periodePojoBySurvey;

    public PeriodeRepository(PeriodeDAONew periodeDAONew, String surveyId) {
        Intrinsics.checkNotNullParameter(periodeDAONew, "periodeDAONew");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        this.periodeDAONew = periodeDAONew;
        this.periodePojoBySurvey = periodeDAONew.getPeriodePojoBySurvey(surveyId, FasihApp.INSTANCE.getSession().getUserId());
    }

    public /* synthetic */ PeriodeRepository(PeriodeDAONew periodeDAONew, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(periodeDAONew, (i & 2) != 0 ? "" : str);
    }

    public final LiveData<List<PeriodePojo>> getPeriodePojoBySurvey() {
        return this.periodePojoBySurvey;
    }

    public final void setPeriodePojoBySurvey(LiveData<List<PeriodePojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.periodePojoBySurvey = liveData;
    }

    public final void insert(final PeriodeEntityNew periode) {
        Intrinsics.checkNotNullParameter(periode, "periode");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.PeriodeRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                PeriodeRepository.insert$lambda$0(this.f$0, periode);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(PeriodeRepository this$0, PeriodeEntityNew periode) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(periode, "$periode");
        this$0.periodeDAONew.insert(periode);
    }

    /* compiled from: PeriodeRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeByUserId$2", f = "PeriodeRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeByUserId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07302 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PeriodeEntityNew>>, Object> {
        int label;

        C07302(Continuation<? super C07302> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeRepository.this.new C07302(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PeriodeEntityNew>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PeriodeEntityNew>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PeriodeEntityNew>> continuation) {
            return ((C07302) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PeriodeRepository.this.periodeDAONew.getPeriodeByUserId(FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getPeriodeByUserId(Continuation<? super List<PeriodeEntityNew>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07302(null), continuation);
    }

    /* compiled from: PeriodeRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeById$2", f = "PeriodeRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeById$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
        final /* synthetic */ String $id;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$id = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeRepository.this.new AnonymousClass2(this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PeriodeRepository.this.periodeDAONew.getPeriodeById(this.$id);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getPeriodeById(String str, Continuation<? super PeriodeEntityNew> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    /* compiled from: PeriodeRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeByPrimaryId$2", f = "PeriodeRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeByPrimaryId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07292 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
        final /* synthetic */ String $periodePrimaryId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07292(String str, Continuation<? super C07292> continuation) {
            super(2, continuation);
            this.$periodePrimaryId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeRepository.this.new C07292(this.$periodePrimaryId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
            return ((C07292) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return PeriodeRepository.this.periodeDAONew.getPeriodeByPrimaryId(this.$periodePrimaryId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getPeriodeByPrimaryId(String str, Continuation<? super PeriodeEntityNew> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07292(str, null), continuation);
    }

    public static /* synthetic */ void getPeriodePojo$default(PeriodeRepository periodeRepository, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        periodeRepository.getPeriodePojo(str);
    }

    public final void getPeriodePojo(String surveyId) {
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        this.periodePojoBySurvey = this.periodeDAONew.getPeriodePojoBySurvey(surveyId, FasihApp.INSTANCE.getSession().getUserId());
    }

    /* compiled from: PeriodeRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.PeriodeRepository$getSurveyRoleUserId$2", f = "PeriodeRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.PeriodeRepository$getSurveyRoleUserId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07322 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $periodeId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07322(String str, Continuation<? super C07322> continuation) {
            super(2, continuation);
            this.$periodeId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeRepository.this.new C07322(this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C07322) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PeriodeRepository.this.periodeDAONew.getSurveyRoleUserId(this.$periodeId, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getSurveyRoleUserId(String str, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07322(str, null), continuation);
    }

    /* compiled from: PeriodeRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeList$2", f = "PeriodeRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.PeriodeRepository$getPeriodeList$2, reason: invalid class name and case insensitive filesystem */
    static final class C07312 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PeriodeEntityNew>>, Object> {
        final /* synthetic */ List<String> $surveyIds;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07312(List<String> list, Continuation<? super C07312> continuation) {
            super(2, continuation);
            this.$surveyIds = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PeriodeRepository.this.new C07312(this.$surveyIds, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PeriodeEntityNew>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<PeriodeEntityNew>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PeriodeEntityNew>> continuation) {
            return ((C07312) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return PeriodeRepository.this.periodeDAONew.getPeriodeBySurveysId(this.$surveyIds, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getPeriodeList(List<String> list, Continuation<? super List<PeriodeEntityNew>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07312(list, null), continuation);
    }

    public final LiveData<Integer> getJumlahPeriode() {
        return this.periodeDAONew.getJumlahPeriode(FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void insertAll(final List<PeriodeEntityNew> listItem, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(listItem, "listItem");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.PeriodeRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PeriodeRepository.insertAll$lambda$1(this.f$0, listItem, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAll$lambda$1(PeriodeRepository this$0, List listItem, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listItem, "$listItem");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.periodeDAONew.insertAll(listItem);
        callback.invoke();
    }

    public final void deleteAll(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.PeriodeRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PeriodeRepository.deleteAll$lambda$2(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$2(PeriodeRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.periodeDAONew.deleteAll(FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    public final void updatePeriode(final String periodeId, final String name, final String startDate, final String endDate, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.PeriodeRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                PeriodeRepository.updatePeriode$lambda$3(this.f$0, periodeId, name, startDate, endDate, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updatePeriode$lambda$3(PeriodeRepository this$0, String str, String str2, String str3, String str4, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.periodeDAONew.updatePeriode(str, str2, str3, str4);
        callback.invoke();
    }
}
