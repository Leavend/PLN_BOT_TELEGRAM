package id.go.bpsfasih.ui.assignmentList;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: JavaScriptInterfaceAssignment.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "isExist", "", "time", "", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
final class JavaScriptInterfaceAssignment$showRestore$dialog$2$1 extends Lambda implements Function2<Boolean, String, Unit> {
    final /* synthetic */ AssignmentEntity $assignment;
    final /* synthetic */ DialogInterface $dialog;
    final /* synthetic */ Ref.ObjectRef<String[]> $listBackupAssignmentLabel;
    final /* synthetic */ File[] $listBackupAssignmentOrigin;
    final /* synthetic */ JavaScriptInterfaceAssignment this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    JavaScriptInterfaceAssignment$showRestore$dialog$2$1(Ref.ObjectRef<String[]> objectRef, JavaScriptInterfaceAssignment javaScriptInterfaceAssignment, File[] fileArr, AssignmentEntity assignmentEntity, DialogInterface dialogInterface) {
        super(2);
        this.$listBackupAssignmentLabel = objectRef;
        this.this$0 = javaScriptInterfaceAssignment;
        this.$listBackupAssignmentOrigin = fileArr;
        this.$assignment = assignmentEntity;
        this.$dialog = dialogInterface;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
        invoke(bool.booleanValue(), str);
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z, String str) {
        String str2 = "Anda akan merestore ke file " + this.$listBackupAssignmentLabel.element[this.this$0.getIndexPathFileBackup()] + " ?";
        AssignmentListActivity activity = this.this$0.getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.assignmentList.AssignmentListActivity");
        AssignmentListActivity assignmentListActivity = activity;
        final JavaScriptInterfaceAssignment javaScriptInterfaceAssignment = this.this$0;
        final File[] fileArr = this.$listBackupAssignmentOrigin;
        final AssignmentEntity assignmentEntity = this.$assignment;
        final DialogInterface dialogInterface = this.$dialog;
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$showRestore$dialog$2$1$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                JavaScriptInterfaceAssignment$showRestore$dialog$2$1.invoke$lambda$0(javaScriptInterfaceAssignment, fileArr, assignmentEntity, dialogInterface, view);
            }
        };
        final DialogInterface dialogInterface2 = this.$dialog;
        BaseClassActivityNew.showAlertDialog$default(assignmentListActivity, "Konfirmasi Restore", str2, null, "Ya", onClickListener, "Tidak", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$showRestore$dialog$2$1$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dialogInterface2.dismiss();
            }
        }, true, false, 256, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$0(final JavaScriptInterfaceAssignment this$0, File[] listBackupAssignmentOrigin, AssignmentEntity assignment, final DialogInterface dialogInterface, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listBackupAssignmentOrigin, "$listBackupAssignmentOrigin");
        Intrinsics.checkNotNullParameter(assignment, "$assignment");
        AssignmentUpdateListingViewModel assignmentUpdateListingViewModel = this$0.model;
        Intrinsics.checkNotNull(assignmentUpdateListingViewModel);
        assignmentUpdateListingViewModel.restore(listBackupAssignmentOrigin[this$0.getIndexPathFileBackup()].getPath(), assignment.getSurveyIdNotPrimary(), assignment.getPeriodeNotPrimary(), assignment.getId(), new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.assignmentList.JavaScriptInterfaceAssignment$showRestore$dialog$2$1$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String str) {
                if (z) {
                    Toast.makeText(this$0.getActivity(), "Restore sukses", 1).show();
                    dialogInterface.dismiss();
                } else {
                    Intrinsics.checkNotNull(str);
                    Log.d("hanaapt-error", str);
                    Toast.makeText(this$0.getActivity(), "Error : " + str, 1).show();
                    dialogInterface.dismiss();
                }
            }
        });
    }
}
