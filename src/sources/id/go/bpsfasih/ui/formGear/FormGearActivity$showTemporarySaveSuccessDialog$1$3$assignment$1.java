package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.pojo.Sync;
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
import kotlinx.coroutines.CoroutineScope;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: FormGearActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1", f = "FormGearActivity.kt", i = {}, l = {1194}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
    int label;
    final /* synthetic */ FormGearActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1(FormGearActivity formGearActivity, Continuation<? super FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1> continuation) {
        super(2, continuation);
        this.this$0 = formGearActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
        return ((FormGearActivity$showTemporarySaveSuccessDialog$1$3$assignment$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
            String mAssignmentId = this.this$0.getMAssignmentId();
            String userId = FasihApp.INSTANCE.getSession().getUserId();
            this.label = 1;
            obj = assignmentRepository.getAssignmentByIdPrimary(mAssignmentId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return ((Sync) obj).getAssignment();
    }
}
