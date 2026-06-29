package id.go.bpsfasih.domain.usecase;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: SyncLocationTrackingUseCase.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase", f = "SyncLocationTrackingUseCase.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 4, 4}, l = {102, 22, 30, 31, 39}, m = "invoke-gIAlu-s", n = {"this", "$this$withLock_u24default$iv", "batchSize", "this", "$this$withLock_u24default$iv", "batchSize", "this", "$this$withLock_u24default$iv", "unsynced", "destination$iv$iv", "this", "$this$withLock_u24default$iv", "unsynced", "$this$withLock_u24default$iv", "unsynced"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* loaded from: classes2.dex */
final class SyncLocationTrackingUseCase$invoke$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SyncLocationTrackingUseCase this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncLocationTrackingUseCase$invoke$1(SyncLocationTrackingUseCase syncLocationTrackingUseCase, Continuation<? super SyncLocationTrackingUseCase$invoke$1> continuation) {
        super(continuation);
        this.this$0 = syncLocationTrackingUseCase;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objM6681invokegIAlus = this.this$0.m6681invokegIAlus(0, this);
        return objM6681invokegIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objM6681invokegIAlus : Result.m6851boximpl(objM6681invokegIAlus);
    }
}
