package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FormGearActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$11$1", f = "FormGearActivity.kt", i = {}, l = {419}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormGearActivity$onCreate$11$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FormGearActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearActivity$onCreate$11$1(FormGearActivity formGearActivity, Continuation<? super FormGearActivity$onCreate$11$1> continuation) {
        super(2, continuation);
        this.this$0 = formGearActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormGearActivity$onCreate$11$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormGearActivity$onCreate$11$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d1  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.String r0 = "/note.txt"
            java.lang.String r1 = ""
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r8.label
            r4 = 1
            if (r3 == 0) goto L1b
            if (r3 != r4) goto L13
            kotlin.ResultKt.throwOnFailure(r9)
            goto L39
        L13:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L1b:
            kotlin.ResultKt.throwOnFailure(r9)
            id.go.bpsfasih.utils.dbHelper.DataSurvey$Assignment$Companion r9 = id.go.bpsfasih.utils.dbHelper.DataSurvey.Assignment.INSTANCE
            id.go.bpsfasih.data.local.repository.AssignmentRepository r9 = r9.getAssignmentRepository()
            id.go.bpsfasih.ui.formGear.FormGearActivity r3 = r8.this$0
            java.lang.String r3 = r3.getMAssignmentId()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r5 = r8
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r8.label = r4
            java.lang.Object r9 = r9.getAssignmentOnly(r3, r5)
            if (r9 != r2) goto L39
            return r2
        L39:
            id.go.bpsfasih.ui.formGear.FormGearActivity r2 = r8.this$0
            id.go.bpsfasih.data.local.entities.AssignmentEntity r9 = (id.go.bpsfasih.data.local.entities.AssignmentEntity) r9
            r3 = 0
            java.io.File r5 = new java.io.File     // Catch: java.io.IOException -> Lae
            java.lang.String r6 = r2.getAnswerPath()     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lae
            r7.<init>()     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r6 = r7.append(r6)     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r6 = r6.append(r0)     // Catch: java.io.IOException -> Lae
            java.lang.String r6 = r6.toString()     // Catch: java.io.IOException -> Lae
            r5.<init>(r6)     // Catch: java.io.IOException -> Lae
            boolean r5 = r5.exists()     // Catch: java.io.IOException -> Lae
            if (r5 == 0) goto L83
            id.go.bpsfasih.utils.helper.FileHelper$Companion r9 = id.go.bpsfasih.utils.helper.FileHelper.INSTANCE     // Catch: java.io.IOException -> Lae
            java.lang.String r4 = r2.getAnswerPath()     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.IOException -> Lae
            r5.<init>()     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r0 = r4.append(r0)     // Catch: java.io.IOException -> Lae
            java.lang.String r0 = r0.toString()     // Catch: java.io.IOException -> Lae
            java.lang.StringBuilder r9 = r9.readFile(r0)     // Catch: java.io.IOException -> Lae
            java.lang.String r9 = r9.toString()     // Catch: java.io.IOException -> Lae
            java.lang.String r0 = "FileHelper.readFile(answ…              .toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r0)     // Catch: java.io.IOException -> Lae
            goto Lac
        L83:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch: java.io.IOException -> Lae
            java.lang.String r0 = r9.getNote()     // Catch: java.io.IOException -> Lae
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch: java.io.IOException -> Lae
            if (r0 == 0) goto L96
            int r0 = r0.length()     // Catch: java.io.IOException -> Lae
            if (r0 != 0) goto L95
            goto L96
        L95:
            r4 = r3
        L96:
            if (r4 != 0) goto Lbd
            java.lang.String r0 = r9.getNote()     // Catch: java.io.IOException -> Lae
            java.lang.String r4 = "null"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)     // Catch: java.io.IOException -> Lae
            if (r0 == 0) goto La5
            goto Lbd
        La5:
            java.lang.String r9 = r9.getNote()     // Catch: java.io.IOException -> Lae
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch: java.io.IOException -> Lae
        Lac:
            r1 = r9
            goto Lbd
        Lae:
            android.content.Context r9 = r2.getApplicationContext()
            java.lang.String r0 = "Ada kesalahan membaca file"
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            android.widget.Toast r9 = android.widget.Toast.makeText(r9, r0, r3)
            r9.show()
        Lbd:
            id.go.bpsfasih.note.NoteDialog r9 = new id.go.bpsfasih.note.NoteDialog
            id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$11$1$1$dialogFragment$1 r0 = new id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$11$1$1$dialogFragment$1
            r0.<init>()
            id.go.bpsfasih.note.CallBackListener r0 = (id.go.bpsfasih.note.CallBackListener) r0
            id.go.bpsfasih.data.local.models.UserRole r3 = r2.getRole()
            if (r3 == 0) goto Ld1
            java.lang.Boolean r3 = r3.isPencacah()
            goto Ld2
        Ld1:
            r3 = 0
        Ld2:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r3 = r3.booleanValue()
            r9.<init>(r0, r1, r3)
            androidx.fragment.app.FragmentManager r0 = r2.getSupportFragmentManager()
            r9.show(r0, r1)
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$11$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
