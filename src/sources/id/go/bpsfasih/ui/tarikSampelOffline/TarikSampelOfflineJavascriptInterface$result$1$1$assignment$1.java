package id.go.bpsfasih.ui.tarikSampelOffline;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentOfflineSamplingResult;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TarikSampelOfflineJavascriptInterface.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1", f = "TarikSampelOfflineJavascriptInterface.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
    final /* synthetic */ AssignmentOfflineSamplingResult $item;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1(AssignmentOfflineSamplingResult assignmentOfflineSamplingResult, Continuation<? super TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1> continuation) {
        super(2, continuation);
        this.$item = assignmentOfflineSamplingResult;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1(this.$item, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
        return ((TarikSampelOfflineJavascriptInterface$result$1$1$assignment$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentByIdOne(this.$item.getId(), this);
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
