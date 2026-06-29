package id.go.bpsfasih.ui.tarikSampelOffline;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.PeriodeEntityNew;
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

/* compiled from: TarikSampelOfflineActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$periode$1", f = "TarikSampelOfflineActivity.kt", i = {}, l = {176}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class TarikSampelOfflineActivity$getPrincipalTarget$periode$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PeriodeEntityNew>, Object> {
    final /* synthetic */ String $periodeTarget;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TarikSampelOfflineActivity$getPrincipalTarget$periode$1(String str, Continuation<? super TarikSampelOfflineActivity$getPrincipalTarget$periode$1> continuation) {
        super(2, continuation);
        this.$periodeTarget = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TarikSampelOfflineActivity$getPrincipalTarget$periode$1(this.$periodeTarget, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PeriodeEntityNew> continuation) {
        return ((TarikSampelOfflineActivity$getPrincipalTarget$periode$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DataSurvey.Periode.INSTANCE.getPeriodeRepository().getPeriodeById(this.$periodeTarget, this);
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
