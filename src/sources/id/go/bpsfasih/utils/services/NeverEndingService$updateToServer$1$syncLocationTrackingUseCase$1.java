package id.go.bpsfasih.utils.services;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: NeverEndingService.kt */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "assignmentId"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.services.NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1", f = "NeverEndingService.kt", i = {}, l = {197}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1 extends SuspendLambda implements Function2<String, Continuation<? super String>, Object> {
    /* synthetic */ Object L$0;
    int label;

    NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1(Continuation<? super NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1 neverEndingService$updateToServer$1$syncLocationTrackingUseCase$1 = new NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1(continuation);
        neverEndingService$updateToServer$1$syncLocationTrackingUseCase$1.L$0 = obj;
        return neverEndingService$updateToServer$1$syncLocationTrackingUseCase$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(String str, Continuation<? super String> continuation) {
        return ((NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = (String) this.L$0;
            String str2 = str;
            if (str2 == null || str2.length() == 0) {
                return null;
            }
            this.label = 1;
            obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentOnly(str, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
        if (assignmentEntity != null) {
            return assignmentEntity.getPeriodeNotPrimary();
        }
        return null;
    }
}
