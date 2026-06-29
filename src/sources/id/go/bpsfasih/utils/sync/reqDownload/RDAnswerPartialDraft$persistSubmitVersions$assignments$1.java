package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import java.util.List;
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
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$persistSubmitVersions$assignments$1", f = "RDAnswerPartialDraft.kt", i = {}, l = {269}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class RDAnswerPartialDraft$persistSubmitVersions$assignments$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentEntity>>, Object> {
    final /* synthetic */ List<String> $assignmentIds;
    int label;
    final /* synthetic */ RDAnswerPartialDraft this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RDAnswerPartialDraft$persistSubmitVersions$assignments$1(RDAnswerPartialDraft rDAnswerPartialDraft, List<String> list, Continuation<? super RDAnswerPartialDraft$persistSubmitVersions$assignments$1> continuation) {
        super(2, continuation);
        this.this$0 = rDAnswerPartialDraft;
        this.$assignmentIds = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RDAnswerPartialDraft$persistSubmitVersions$assignments$1(this.this$0, this.$assignmentIds, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentEntity>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<AssignmentEntity>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentEntity>> continuation) {
        return ((RDAnswerPartialDraft$persistSubmitVersions$assignments$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = this.this$0.assignmentaRepo.getDetailAssignmentById(this.$assignmentIds, this);
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
