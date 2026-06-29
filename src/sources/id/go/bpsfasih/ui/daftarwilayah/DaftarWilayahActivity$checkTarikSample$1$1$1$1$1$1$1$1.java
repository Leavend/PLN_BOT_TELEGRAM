package id.go.bpsfasih.ui.daftarwilayah;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.SamplingRegionEntity;
import id.go.bpsfasih.data.local.models.SamplingRegionModelItem;
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

/* compiled from: DaftarWilayahActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1", f = "DaftarWilayahActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SamplingRegionModelItem $it;
    int label;
    final /* synthetic */ DaftarWilayahActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1(SamplingRegionModelItem samplingRegionModelItem, DaftarWilayahActivity daftarWilayahActivity, Continuation<? super DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1> continuation) {
        super(2, continuation);
        this.$it = samplingRegionModelItem;
        this.this$0 = daftarWilayahActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DaftarWilayahActivity$checkTarikSample$1$1$1$1$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SamplingRegionRepository samplingRegionRepository = DataSurvey.SamplingRegion.INSTANCE.getSamplingRegionRepository();
        SamplingRegionModelItem samplingRegionModelItem = this.$it;
        String id2 = samplingRegionModelItem != null ? samplingRegionModelItem.getId() : null;
        Intrinsics.checkNotNull(id2);
        String str = this.this$0.mPeriodeIdOnly;
        Intrinsics.checkNotNull(str);
        SamplingRegionModelItem samplingRegionModelItem2 = this.$it;
        String fullCode = samplingRegionModelItem2 != null ? samplingRegionModelItem2.getFullCode() : null;
        Intrinsics.checkNotNull(fullCode);
        SamplingRegionModelItem samplingRegionModelItem3 = this.$it;
        String mode = samplingRegionModelItem3 != null ? samplingRegionModelItem3.getMode() : null;
        Intrinsics.checkNotNull(mode);
        SamplingRegionModelItem samplingRegionModelItem4 = this.$it;
        String status = samplingRegionModelItem4 != null ? samplingRegionModelItem4.getStatus() : null;
        Intrinsics.checkNotNull(status);
        samplingRegionRepository.insertData(new SamplingRegionEntity(id2, str, fullCode, mode, status, null, 32, null));
        return Unit.INSTANCE;
    }
}
