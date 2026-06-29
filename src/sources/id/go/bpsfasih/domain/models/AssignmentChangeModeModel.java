package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentChangeModeModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/domain/models/AssignmentChangeModeModel;", "", "assignment_id", "", "(Ljava/lang/String;)V", "getAssignment_id", "()Ljava/lang/String;", "setAssignment_id", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentChangeModeModel {
    public static final int $stable = 8;
    private String assignment_id;

    public static /* synthetic */ AssignmentChangeModeModel copy$default(AssignmentChangeModeModel assignmentChangeModeModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assignmentChangeModeModel.assignment_id;
        }
        return assignmentChangeModeModel.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAssignment_id() {
        return this.assignment_id;
    }

    public final AssignmentChangeModeModel copy(String assignment_id) {
        return new AssignmentChangeModeModel(assignment_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AssignmentChangeModeModel) && Intrinsics.areEqual(this.assignment_id, ((AssignmentChangeModeModel) other).assignment_id);
    }

    public int hashCode() {
        String str = this.assignment_id;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public String toString() {
        return "AssignmentChangeModeModel(assignment_id=" + this.assignment_id + ")";
    }

    public AssignmentChangeModeModel(String str) {
        this.assignment_id = str;
    }

    public final String getAssignment_id() {
        return this.assignment_id;
    }

    public final void setAssignment_id(String str) {
        this.assignment_id = str;
    }
}
