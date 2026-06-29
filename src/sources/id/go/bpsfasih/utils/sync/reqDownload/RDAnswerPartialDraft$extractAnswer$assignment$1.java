package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RDAnswerPartialDraft.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$extractAnswer$assignment$1", f = "RDAnswerPartialDraft.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class RDAnswerPartialDraft$extractAnswer$assignment$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
    final /* synthetic */ String $assignmentId;
    int label;
    final /* synthetic */ RDAnswerPartialDraft this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RDAnswerPartialDraft$extractAnswer$assignment$1(RDAnswerPartialDraft rDAnswerPartialDraft, String str, Continuation<? super RDAnswerPartialDraft$extractAnswer$assignment$1> continuation) {
        super(2, continuation);
        this.this$0 = rDAnswerPartialDraft;
        this.$assignmentId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RDAnswerPartialDraft$extractAnswer$assignment$1(this.this$0, this.$assignmentId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
        return ((RDAnswerPartialDraft$extractAnswer$assignment$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.assignmentaRepo.getAssignmentOnly(this.$assignmentId, this);
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
