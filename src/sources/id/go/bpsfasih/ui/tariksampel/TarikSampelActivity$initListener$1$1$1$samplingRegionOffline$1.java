package id.go.bpsfasih.ui.tariksampel;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import id.go.bpsfasih.data.local.repository.SamplingRegionRepository;
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

/* compiled from: TarikSampelActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/SamplingRegionEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1", f = "TarikSampelActivity.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SamplingRegionEntity>, Object> {
    int label;
    final /* synthetic */ TarikSampelActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1(TarikSampelActivity tarikSampelActivity, Continuation<? super TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1> continuation) {
        super(2, continuation);
        this.this$0 = tarikSampelActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SamplingRegionEntity> continuation) {
        return ((TarikSampelActivity$initListener$1$1$1$samplingRegionOffline$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SamplingRegionRepository samplingRegionRepository = DataSurvey.SamplingRegion.INSTANCE.getSamplingRegionRepository();
            String samplingRegionId = this.this$0.getSamplingRegionId();
            Intrinsics.checkNotNull(samplingRegionId);
            this.label = 1;
            obj = samplingRegionRepository.getSamplingRegionById(samplingRegionId, this);
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
