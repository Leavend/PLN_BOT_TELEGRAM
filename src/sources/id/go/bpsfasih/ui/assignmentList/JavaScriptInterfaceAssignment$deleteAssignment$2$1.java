package id.go.bpsfasih.ui.assignmentList;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: JavaScriptInterfaceAssignment.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$deleteAssignment$2$1", f = "JavaScriptInterfaceAssignment.kt", i = {}, l = {361}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class JavaScriptInterfaceAssignment$deleteAssignment$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $assignmentId;
    int label;
    final /* synthetic */ JavaScriptInterfaceAssignment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JavaScriptInterfaceAssignment$deleteAssignment$2$1(JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, String str, Continuation<? super JavaScriptInterfaceAssignment$deleteAssignment$2$1> continuation) {
        super(2, continuation);
        this.this$0 = javaScriptInterfaceAssignment;
        this.$assignmentId = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new JavaScriptInterfaceAssignment$deleteAssignment$2$1(this.this$0, this.$assignmentId, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((JavaScriptInterfaceAssignment$deleteAssignment$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0069  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L17
            if (r1 != r2) goto Lf
            kotlin.ResultKt.throwOnFailure(r5)
            goto L2e
        Lf:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L17:
            kotlin.ResultKt.throwOnFailure(r5)
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r5 = r4.this$0
            id.go.bpsfasih.data.local.repository.AssignmentRepository r5 = r5.getAssignmentRepo()
            java.lang.String r1 = r4.$assignmentId
            r3 = r4
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4.label = r2
            java.lang.Object r5 = r5.getAssignmentByIdOne(r1, r3)
            if (r5 != r0) goto L2e
            return r0
        L2e:
            id.go.bpsfasih.data.local.entities.AssignmentEntity r5 = (id.go.bpsfasih.data.local.entities.AssignmentEntity) r5
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            boolean r0 = r5.isNew()
            if (r0 == 0) goto L69
            java.lang.Integer r0 = r5.getAssignmentStatusId()
            id.go.bpsfasih.data.CommonCons r1 = id.go.bpsfasih.data.CommonCons.INSTANCE
            int r1 = r1.getASSIGNMENT_STATUS_OPEN()
            if (r0 != 0) goto L46
            goto L69
        L46:
            int r0 = r0.intValue()
            if (r0 != r1) goto L69
            boolean r0 = r5.getPendingStatus()
            if (r0 != 0) goto L69
            id.go.bpsfasih.utils.dbHelper.DataSurvey$Assignment$Companion r0 = id.go.bpsfasih.utils.dbHelper.DataSurvey.Assignment.INSTANCE
            id.go.bpsfasih.data.local.repository.AssignmentRepository r0 = r0.getAssignmentRepository()
            java.lang.String r5 = r5.getId()
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$deleteAssignment$2$1$1 r1 = new id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$deleteAssignment$2$1$1
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r2 = r4.this$0
            r1.<init>()
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            r0.delete(r5, r1)
            goto L7c
        L69:
            id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment r5 = r4.this$0
            id.go.bpsfasih.ui.assignmentList.AssignmentListActivity r5 = r5.getActivity()
            android.content.Context r5 = (android.content.Context) r5
            java.lang.String r0 = "Assigment tidak dapat dihapus."
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r5 = android.widget.Toast.makeText(r5, r0, r2)
            r5.show()
        L7c:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$deleteAssignment$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
