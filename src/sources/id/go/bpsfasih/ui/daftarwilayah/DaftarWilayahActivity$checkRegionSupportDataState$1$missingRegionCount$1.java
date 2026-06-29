package id.go.bpsfasih.ui.daftarwilayah;

import androidx.constraintlayout.widget.ConstraintLayout;
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

/* compiled from: DaftarWilayahActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.daftarwilayah.DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1", f = "DaftarWilayahActivity.kt", i = {}, l = {506}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    final /* synthetic */ String $periodeId;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1(String str, Continuation<? super DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1> continuation) {
        super(2, continuation);
        this.$periodeId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1(this.$periodeId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((DaftarWilayahActivity$checkRegionSupportDataState$1$missingRegionCount$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DataSurvey.AssignmentRegion.INSTANCE.getAssignmentRegionRepository().getMissingRegionCount(this.$periodeId, this);
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
