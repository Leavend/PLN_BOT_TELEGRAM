package id.go.bpsfasih.ui.assignmentList;

import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository;
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

/* compiled from: AssignmentListActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$showDialogAksi$7$2$1", f = "AssignmentListActivity.kt", i = {}, l = {618}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes2.dex */
final class AssignmentListActivity$showDialogAksi$7$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AssignmentEntity $assignment;
    final /* synthetic */ String $assignmentId;
    int label;
    final /* synthetic */ AssignmentListActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AssignmentListActivity$showDialogAksi$7$2$1(String str, AssignmentEntity assignmentEntity, AssignmentListActivity assignmentListActivity, Continuation<? super AssignmentListActivity$showDialogAksi$7$2$1> continuation) {
        super(2, continuation);
        this.$assignmentId = str;
        this.$assignment = assignmentEntity;
        this.this$0 = assignmentListActivity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(View view) {
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AssignmentListActivity$showDialogAksi$7$2$1(this.$assignmentId, this.$assignment, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AssignmentListActivity$showDialogAksi$7$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AssignmentSubmitVersionRepository assignmentSubmitVersionRepository = DataSurvey.AssignmentSubmitVersion.INSTANCE.getAssignmentSubmitVersionRepository();
            String str = this.$assignmentId;
            AssignmentEntity assignmentEntity = this.$assignment;
            int submitVersionCode = assignmentEntity != null ? assignmentEntity.getSubmitVersionCode() : 0;
            this.label = 1;
            if (assignmentSubmitVersionRepository.upsert(str, submitVersionCode, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        BaseClassActivityNew.showAlertDialog$default(this.this$0, "Berhasil", "Version code assignment berhasil disamakan.", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.AssignmentListActivity$showDialogAksi$7$2$1$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AssignmentListActivity$showDialogAksi$7$2$1.invokeSuspend$lambda$0(view);
            }
        }, null, null, false, false, 384, null);
        return Unit.INSTANCE;
    }
}
