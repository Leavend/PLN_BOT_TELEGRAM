package id.go.bpsfasih.ui.formGear;

import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.models.UserRole;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.note.CallBackListener;
import id.go.bpsfasih.note.NoteDialog;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: FormGearActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$12$1", f = "FormGearActivity.kt", i = {}, l = {483}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class FormGearActivity$onCreate$12$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FormGearActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FormGearActivity$onCreate$12$1(FormGearActivity formGearActivity, Continuation<? super FormGearActivity$onCreate$12$1> continuation) {
        super(2, continuation);
        this.this$0 = formGearActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FormGearActivity$onCreate$12$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FormGearActivity$onCreate$12$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        boolean z = true;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AssignmentRepository assignmentRepository = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
            String mAssignmentId = this.this$0.getMAssignmentId();
            Intrinsics.checkNotNull(mAssignmentId);
            this.label = 1;
            obj = assignmentRepository.getAssignmentOnly(mAssignmentId, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        final FormGearActivity formGearActivity = this.this$0;
        AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
        CallBackListener callBackListener = new CallBackListener() { // from class: id.go.bpsfasih.ui.formGear.FormGearActivity$onCreate$12$1$1$dialogFragment$1
            @Override // id.go.bpsfasih.note.CallBackListener
            public void onDataReceived(String data) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FormGearActivity$onCreate$12$1$1$dialogFragment$1$onDataReceived$1$1(data, formGearActivity, null), 2, null);
                Toast.makeText(formGearActivity.getApplicationContext(), "Catatan tersimpan", 0).show();
            }
        };
        Intrinsics.checkNotNull(assignmentEntity);
        String strValueOf = String.valueOf(assignmentEntity.getNote());
        UserRole role = formGearActivity.getRole();
        Boolean boolIsPencacah = role != null ? role.isPencacah() : null;
        Intrinsics.checkNotNull(boolIsPencacah);
        NoteDialog noteDialog = new NoteDialog(callBackListener, strValueOf, boolIsPencacah.booleanValue());
        FragmentManager supportFragmentManager = formGearActivity.getSupportFragmentManager();
        String note = assignmentEntity.getNote();
        if (note != null && note.length() != 0) {
            z = false;
        }
        noteDialog.show(supportFragmentManager, (z || Intrinsics.areEqual(assignmentEntity.getNote(), "null")) ? "" : assignmentEntity.getNote());
        return Unit.INSTANCE;
    }
}
