package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RDAnswerPartial.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartial$requestAnswerS3$listAssignment$1", f = "RDAnswerPartial.kt", i = {}, l = {185}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class RDAnswerPartial$requestAnswerS3$listAssignment$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentEntity>>, Object> {
    int label;
    final /* synthetic */ RDAnswerPartial this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RDAnswerPartial$requestAnswerS3$listAssignment$1(RDAnswerPartial rDAnswerPartial, Continuation<? super RDAnswerPartial$requestAnswerS3$listAssignment$1> continuation) {
        super(2, continuation);
        this.this$0 = rDAnswerPartial;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RDAnswerPartial$requestAnswerS3$listAssignment$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentEntity>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<AssignmentEntity>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentEntity>> continuation) {
        return ((RDAnswerPartial$requestAnswerS3$listAssignment$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
            List<String> listAssignmentS3 = this.this$0.getUnit().getListAssignmentS3();
            Intrinsics.checkNotNull(listAssignmentS3);
            this.label = 1;
            obj = assignmentRepository.getDetailAssignmentById(listAssignmentS3, this);
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
