package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
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

/* compiled from: FormGearActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1", f = "FormGearActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $data;
    int label;
    final /* synthetic */ FormGearActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1(String str, FormGearActivity formGearActivity, Continuation<? super FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1> continuation) {
        super(2, continuation);
        this.$data = str;
        this.this$0 = formGearActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1(this.$data, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
        String str = this.$data;
        Intrinsics.checkNotNull(str);
        String mAssignmentId = this.this$0.getMAssignmentId();
        Intrinsics.checkNotNull(mAssignmentId);
        assignmentRepository.updateNote(str, mAssignmentId);
        return Unit.INSTANCE;
    }
}
