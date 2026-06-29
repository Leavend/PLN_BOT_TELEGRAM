package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.RegionMetadata;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import id.go.bpsfasih.utils.helper.WrappedDataKeyStore;
import java.util.Iterator;
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

/* compiled from: AssignmentRegionRepository.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u001c\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0019\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\u0019\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0017J'\u0010\u001b\u001a\u0004\u0018\u00010\u00132\b\u0010\u001c\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ'\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001dJ\u001c\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u001f2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J\"\u0010\"\u001a\u00020\u000e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010J#\u0010$\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00132\b\u0010%\u001a\u0004\u0018\u00010&H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010'J-\u0010(\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010)\u001a\u00020\u0013H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006+"}, d2 = {"Lid/go/bpsfasih/data/local/repository/AssignmentRegionRepository;", "", "assignmentRegionDAO", "Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;", "(Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;)V", "assignmentRegionWilayahPojo", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "getAssignmentRegionWilayahPojo", "()Landroidx/lifecycle/LiveData;", "setAssignmentRegionWilayahPojo", "(Landroidx/lifecycle/LiveData;)V", "deleteAll", "", "callback", "Lkotlin/Function0;", "deleteByPeriode", "periodeId", "", "getAssignmentRegionCountByPeriode", "", "surveyPeriodeId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssignmentRegionWilayahByPeriode", "getMissingRegionCount", "getMissingRegionMetadataCount", "getSmallRegionFullCode", "regionId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStatusAssignmentRegion", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "insert", "assignmentRegion", "insertAll", "listItem", "updateRegionMetadataByPeriode", "regionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "(Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/RegionMetadata;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStateDataTable", "value", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentRegionRepository {
    public static final int $stable = 8;
    private final AssignmentRegionDAO assignmentRegionDAO;
    private LiveData<List<AssignmentRegionWilayahPojo>> assignmentRegionWilayahPojo;

    public AssignmentRegionRepository(AssignmentRegionDAO assignmentRegionDAO) {
        Intrinsics.checkNotNullParameter(assignmentRegionDAO, "assignmentRegionDAO");
        this.assignmentRegionDAO = assignmentRegionDAO;
        this.assignmentRegionWilayahPojo = assignmentRegionDAO.getAssignmentRegionWilayahByPeriode("", "");
    }

    public final LiveData<List<AssignmentRegionWilayahPojo>> getAssignmentRegionWilayahPojo() {
        return this.assignmentRegionWilayahPojo;
    }

    public final void setAssignmentRegionWilayahPojo(LiveData<List<AssignmentRegionWilayahPojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.assignmentRegionWilayahPojo = liveData;
    }

    public final void getAssignmentRegionWilayahByPeriode(String periodeId) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.assignmentRegionWilayahPojo = this.assignmentRegionDAO.getAssignmentRegionWilayahByPeriode(periodeId, FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void insert(final AssignmentRegionEntity assignmentRegion, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(assignmentRegion, "assignmentRegion");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRegionRepository.insert$lambda$0(assignmentRegion, this, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(AssignmentRegionEntity assignmentRegion, AssignmentRegionRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(assignmentRegion, "$assignmentRegion");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        WrappedDataKeyStore.INSTANCE.save(assignmentRegion.getSurvey_period_id(), assignmentRegion.getRegion_id(), assignmentRegion.getWrappedDatakey());
        this$0.assignmentRegionDAO.insert(assignmentRegion);
        callback.invoke();
    }

    public final void insertAll(final List<AssignmentRegionEntity> listItem, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(listItem, "listItem");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRegionRepository.insertAll$lambda$2(listItem, this, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAll$lambda$2(List listItem, AssignmentRegionRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(listItem, "$listItem");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Iterator it = listItem.iterator();
        while (it.hasNext()) {
            AssignmentRegionEntity assignmentRegionEntity = (AssignmentRegionEntity) it.next();
            WrappedDataKeyStore.INSTANCE.save(assignmentRegionEntity.getSurvey_period_id(), assignmentRegionEntity.getRegion_id(), assignmentRegionEntity.getWrappedDatakey());
        }
        this$0.assignmentRegionDAO.insertAll(listItem);
        callback.invoke();
    }

    public final void deleteAll(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRegionRepository.deleteAll$lambda$3(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$3(AssignmentRegionRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentRegionDAO.deleteAll(FasihApp.INSTANCE.getSession().getUserId());
        WrappedDataKeyStore.INSTANCE.deleteAll();
        callback.invoke();
    }

    public final void deleteByPeriode(final String periodeId, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRegionRepository.deleteByPeriode$lambda$4(this.f$0, periodeId, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteByPeriode$lambda$4(AssignmentRegionRepository this$0, String periodeId, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(periodeId, "$periodeId");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentRegionDAO.deleteByPeriode(FasihApp.INSTANCE.getSession().getUserId(), periodeId);
        WrappedDataKeyStore.INSTANCE.deleteByPeriode(periodeId);
        callback.invoke();
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getStatusAssignmentRegion$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getStatusAssignmentRegion$2, reason: invalid class name and case insensitive filesystem */
    static final class C07152 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentRegionEntity>, Object> {
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07152(String str, String str2, String str3, Continuation<? super C07152> continuation) {
            super(2, continuation);
            this.$regionId = str;
            this.$surveyPeriodeId = str2;
            this.$userId = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07152(this.$regionId, this.$surveyPeriodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentRegionEntity> continuation) {
            return ((C07152) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRegionRepository.this.assignmentRegionDAO.getStatusRegionBySurveyPeriodeId(this.$regionId, this.$surveyPeriodeId, this.$userId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getStatusAssignmentRegion(String str, String str2, Continuation<? super AssignmentRegionEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07152(str, str2, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getSmallRegionFullCode$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getSmallRegionFullCode$2, reason: invalid class name and case insensitive filesystem */
    static final class C07142 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07142(String str, String str2, String str3, Continuation<? super C07142> continuation) {
            super(2, continuation);
            this.$regionId = str;
            this.$surveyPeriodeId = str2;
            this.$userId = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07142(this.$regionId, this.$surveyPeriodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((C07142) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRegionRepository.this.assignmentRegionDAO.getSmallRegionFullCodeBySurveyPeriodeId(this.$regionId, this.$surveyPeriodeId, this.$userId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSmallRegionFullCode(String str, String str2, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07142(str, str2, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$updateStateDataTable$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$updateStateDataTable$2, reason: invalid class name and case insensitive filesystem */
    static final class C07172 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        final /* synthetic */ String $value;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07172(String str, String str2, String str3, String str4, Continuation<? super C07172> continuation) {
            super(2, continuation);
            this.$regionId = str;
            this.$surveyPeriodeId = str2;
            this.$userId = str3;
            this.$value = str4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07172(this.$regionId, this.$surveyPeriodeId, this.$userId, this.$value, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07172) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AssignmentRegionRepository.this.assignmentRegionDAO.updateStateDataTable(this.$regionId, this.$surveyPeriodeId, this.$userId, this.$value);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object updateStateDataTable(String str, String str2, String str3, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C07172(str, str2, FasihApp.INSTANCE.getSession().getUserId(), str3, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getMissingRegionMetadataCount$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getMissingRegionMetadataCount$2, reason: invalid class name and case insensitive filesystem */
    static final class C07132 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07132(String str, String str2, Continuation<? super C07132> continuation) {
            super(2, continuation);
            this.$surveyPeriodeId = str;
            this.$userId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07132(this.$surveyPeriodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C07132) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(AssignmentRegionRepository.this.assignmentRegionDAO.getMissingRegionMetadataCount(this.$surveyPeriodeId, this.$userId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getMissingRegionMetadataCount(String str, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07132(str, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getMissingRegionCount$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getMissingRegionCount$2, reason: invalid class name and case insensitive filesystem */
    static final class C07122 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07122(String str, String str2, Continuation<? super C07122> continuation) {
            super(2, continuation);
            this.$surveyPeriodeId = str;
            this.$userId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07122(this.$surveyPeriodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C07122) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(AssignmentRegionRepository.this.assignmentRegionDAO.getMissingRegionCount(this.$surveyPeriodeId, this.$userId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getMissingRegionCount(String str, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07122(str, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getAssignmentRegionCountByPeriode$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$getAssignmentRegionCountByPeriode$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, String str2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$surveyPeriodeId = str;
            this.$userId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new AnonymousClass2(this.$surveyPeriodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(AssignmentRegionRepository.this.assignmentRegionDAO.getAssignmentRegionCountByPeriode(this.$surveyPeriodeId, this.$userId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssignmentRegionCountByPeriode(String str, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    /* compiled from: AssignmentRegionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$updateRegionMetadataByPeriode$2", f = "AssignmentRegionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRegionRepository$updateRegionMetadataByPeriode$2, reason: invalid class name and case insensitive filesystem */
    static final class C07162 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ RegionMetadata $regionMetadata;
        final /* synthetic */ String $surveyPeriodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07162(String str, String str2, RegionMetadata regionMetadata, Continuation<? super C07162> continuation) {
            super(2, continuation);
            this.$surveyPeriodeId = str;
            this.$userId = str2;
            this.$regionMetadata = regionMetadata;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRegionRepository.this.new C07162(this.$surveyPeriodeId, this.$userId, this.$regionMetadata, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07162) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AssignmentRegionRepository.this.assignmentRegionDAO.updateRegionMetadataByPeriode(this.$surveyPeriodeId, this.$userId, this.$regionMetadata);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object updateRegionMetadataByPeriode(String str, RegionMetadata regionMetadata, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C07162(str, FasihApp.INSTANCE.getSession().getUserId(), regionMetadata, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }
}
