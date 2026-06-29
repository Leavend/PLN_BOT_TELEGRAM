package id.go.bpsfasih.data.local.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO;
import id.go.bpsfasih.data.local.entities.AssignmentSubmitVersionEntity;
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

/* compiled from: AssignmentSubmitVersionRepository.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;", "(Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;)V", "getByAssignmentId", "Lid/go/bpsfasih/data/local/entities/AssignmentSubmitVersionEntity;", "assignmentId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "submitVersionCode", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertSync", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentSubmitVersionRepository {
    public static final int $stable = 8;
    private final AssignmentSubmitVersionDAO dao;

    public AssignmentSubmitVersionRepository(AssignmentSubmitVersionDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    /* compiled from: AssignmentSubmitVersionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentSubmitVersionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository$getByAssignmentId$2", f = "AssignmentSubmitVersionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository$getByAssignmentId$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentSubmitVersionEntity>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentSubmitVersionRepository.this.new AnonymousClass2(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentSubmitVersionEntity> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentSubmitVersionRepository.this.dao.getByAssignmentId(this.$assignmentId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getByAssignmentId(String str, Continuation<? super AssignmentSubmitVersionEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, null), continuation);
    }

    /* compiled from: AssignmentSubmitVersionRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository$upsert$2", f = "AssignmentSubmitVersionRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository$upsert$2, reason: invalid class name and case insensitive filesystem */
    static final class C07282 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ int $submitVersionCode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07282(String str, int i, Continuation<? super C07282> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.$submitVersionCode = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentSubmitVersionRepository.this.new C07282(this.$assignmentId, this.$submitVersionCode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C07282) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            AssignmentSubmitVersionRepository.this.upsertSync(this.$assignmentId, this.$submitVersionCode);
            return Unit.INSTANCE;
        }
    }

    public final Object upsert(String str, int i, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new C07282(str, i, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    public final void upsertSync(String assignmentId, int submitVersionCode) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.dao.insert(new AssignmentSubmitVersionEntity(assignmentId, submitVersionCode));
    }
}
