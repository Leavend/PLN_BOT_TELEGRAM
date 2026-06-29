package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.dao.TarikSampleDAO;
import id.go.bpsfasih.data.local.entities.TarikSampleEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: TarikSampleRepository.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/data/local/repository/TarikSampleRepository;", "", "tarikSampleDAO", "Lid/go/bpsfasih/data/local/dao/TarikSampleDAO;", "(Lid/go/bpsfasih/data/local/dao/TarikSampleDAO;)V", "getSampling", "Lid/go/bpsfasih/data/local/entities/TarikSampleEntity;", "surveyPeriodId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSamplingByTarget", "surveyPeriodTargetId", "insertData", "", "data", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampleRepository {
    public static final int $stable = 8;
    private final TarikSampleDAO tarikSampleDAO;

    public TarikSampleRepository(TarikSampleDAO tarikSampleDAO) {
        Intrinsics.checkNotNullParameter(tarikSampleDAO, "tarikSampleDAO");
        this.tarikSampleDAO = tarikSampleDAO;
    }

    public final void insertData(final TarikSampleEntity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.TarikSampleRepository$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                TarikSampleRepository.insertData$lambda$0(this.f$0, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertData$lambda$0(TarikSampleRepository this$0, TarikSampleEntity data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        this$0.tarikSampleDAO.insert(data);
    }

    /* compiled from: TarikSampleRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/TarikSampleEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.TarikSampleRepository$getSampling$2", f = "TarikSampleRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.TarikSampleRepository$getSampling$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TarikSampleEntity>, Object> {
        final /* synthetic */ String $surveyPeriodId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$surveyPeriodId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TarikSampleRepository.this.new AnonymousClass2(this.$surveyPeriodId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TarikSampleEntity> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return TarikSampleRepository.this.tarikSampleDAO.getSamplingBySurveyPeriodId(this.$surveyPeriodId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSampling(String str, Continuation<? super TarikSampleEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    /* compiled from: TarikSampleRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/TarikSampleEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.TarikSampleRepository$getSamplingByTarget$2", f = "TarikSampleRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.TarikSampleRepository$getSamplingByTarget$2, reason: invalid class name and case insensitive filesystem */
    static final class C07372 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TarikSampleEntity>, Object> {
        final /* synthetic */ String $surveyPeriodTargetId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07372(String str, Continuation<? super C07372> continuation) {
            super(2, continuation);
            this.$surveyPeriodTargetId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TarikSampleRepository.this.new C07372(this.$surveyPeriodTargetId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TarikSampleEntity> continuation) {
            return ((C07372) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return TarikSampleRepository.this.tarikSampleDAO.getSamplingBySurveyPeriodeTargetId(this.$surveyPeriodTargetId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getSamplingByTarget(String str, Continuation<? super TarikSampleEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07372(str, null), continuation);
    }
}
