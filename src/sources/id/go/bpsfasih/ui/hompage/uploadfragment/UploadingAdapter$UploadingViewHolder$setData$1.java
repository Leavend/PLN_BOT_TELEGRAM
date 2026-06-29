package id.go.bpsfasih.ui.hompage.uploadfragment;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UploadingAdapter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$UploadingViewHolder$setData$1", f = "UploadingAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class UploadingAdapter$UploadingViewHolder$setData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AssignmentUploadEntity $data;
    int label;
    final /* synthetic */ UploadingAdapter.UploadingViewHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UploadingAdapter$UploadingViewHolder$setData$1(AssignmentUploadEntity assignmentUploadEntity, UploadingAdapter.UploadingViewHolder uploadingViewHolder, Continuation<? super UploadingAdapter$UploadingViewHolder$setData$1> continuation) {
        super(2, continuation);
        this.$data = assignmentUploadEntity;
        this.this$0 = uploadingViewHolder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UploadingAdapter$UploadingViewHolder$setData$1(this.$data, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UploadingAdapter$UploadingViewHolder$setData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x017c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            Method dump skipped, instructions count: 762
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.hompage.uploadfragment.UploadingAdapter$UploadingViewHolder$setData$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
