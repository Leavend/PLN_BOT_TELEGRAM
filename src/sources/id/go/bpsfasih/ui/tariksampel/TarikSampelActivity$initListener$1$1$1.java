package id.go.bpsfasih.ui.tariksampel;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TarikSampelActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$initListener$1$1$1", f = "TarikSampelActivity.kt", i = {1, 2, 2}, l = {78, 83, ModuleDescriptor.MODULE_VERSION}, m = "invokeSuspend", n = {"sampling", "sampling", "surveyPeriode"}, s = {"L$0", "L$0", "L$1"})
/* loaded from: classes2.dex */
final class TarikSampelActivity$initListener$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ TarikSampelActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TarikSampelActivity$initListener$1$1$1(TarikSampelActivity tarikSampelActivity, Continuation<? super TarikSampelActivity$initListener$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = tarikSampelActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$2(View view) {
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TarikSampelActivity$initListener$1$1$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TarikSampelActivity$initListener$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0093 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0140 A[Catch: Exception -> 0x0182, TRY_LEAVE, TryCatch #0 {Exception -> 0x0182, blocks: (B:8:0x001c, B:28:0x0096, B:32:0x009d, B:34:0x00ab, B:35:0x011d, B:36:0x0140, B:13:0x002d, B:24:0x0073, B:14:0x0031, B:20:0x0053, B:17:0x0038), top: B:40:0x000c }] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 389
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.tariksampel.TarikSampelActivity$initListener$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
