package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: TrackingRepository.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.data.repository.TrackingRepositoryImpl", f = "TrackingRepository.kt", i = {}, l = {20}, m = "createLiveTracking-gIAlu-s", n = {}, s = {})
/* loaded from: classes2.dex */
final class TrackingRepositoryImpl$createLiveTracking$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TrackingRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TrackingRepositoryImpl$createLiveTracking$1(TrackingRepositoryImpl trackingRepositoryImpl, Continuation<? super TrackingRepositoryImpl$createLiveTracking$1> continuation) {
        super(continuation);
        this.this$0 = trackingRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo6675createLiveTrackinggIAlus = this.this$0.mo6675createLiveTrackinggIAlus(null, this);
        return objMo6675createLiveTrackinggIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo6675createLiveTrackinggIAlus : Result.m6851boximpl(objMo6675createLiveTrackinggIAlus);
    }
}
