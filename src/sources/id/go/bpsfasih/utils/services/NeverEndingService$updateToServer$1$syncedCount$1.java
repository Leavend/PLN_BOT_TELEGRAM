package id.go.bpsfasih.utils.services;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NeverEndingService.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.services.NeverEndingService$updateToServer$1$syncedCount$1", f = "NeverEndingService.kt", i = {}, l = {204}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class NeverEndingService$updateToServer$1$syncedCount$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ SyncLocationTrackingUseCase $syncLocationTrackingUseCase;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NeverEndingService$updateToServer$1$syncedCount$1(SyncLocationTrackingUseCase syncLocationTrackingUseCase, Continuation<? super NeverEndingService$updateToServer$1$syncedCount$1> continuation) {
        super(2, continuation);
        this.$syncLocationTrackingUseCase = syncLocationTrackingUseCase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NeverEndingService$updateToServer$1$syncedCount$1(this.$syncLocationTrackingUseCase, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((NeverEndingService$updateToServer$1$syncedCount$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objM6680invokegIAlus$default;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            objM6680invokegIAlus$default = SyncLocationTrackingUseCase.m6680invokegIAlus$default(this.$syncLocationTrackingUseCase, 0, this, 1, null);
            if (objM6680invokegIAlus$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            objM6680invokegIAlus$default = ((Result) obj).getValue();
        }
        return Result.m6858isFailureimpl(objM6680invokegIAlus$default) ? Boxing.boxInt(0) : objM6680invokegIAlus$default;
    }
}
