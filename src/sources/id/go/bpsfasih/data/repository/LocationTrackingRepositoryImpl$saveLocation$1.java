package id.go.bpsfasih.data.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: LocationTrackingRepositoryImpl.kt */
@Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl", f = "LocationTrackingRepositoryImpl.kt", i = {}, l = {17}, m = "saveLocation-gIAlu-s", n = {}, s = {})
/* loaded from: classes2.dex */
final class LocationTrackingRepositoryImpl$saveLocation$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ LocationTrackingRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocationTrackingRepositoryImpl$saveLocation$1(LocationTrackingRepositoryImpl locationTrackingRepositoryImpl, Continuation<? super LocationTrackingRepositoryImpl$saveLocation$1> continuation) {
        super(continuation);
        this.this$0 = locationTrackingRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object objMo6659saveLocationgIAlus = this.this$0.mo6659saveLocationgIAlus(null, this);
        return objMo6659saveLocationgIAlus == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objMo6659saveLocationgIAlus : Result.m6851boximpl(objMo6659saveLocationgIAlus);
    }
}
