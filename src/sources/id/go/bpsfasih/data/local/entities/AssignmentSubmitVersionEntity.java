package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentSubmitVersionEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u001d\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0018"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentSubmitVersionEntity;", "", "()V", "assignmentId", "", "submitVersionCode", "", "(Ljava/lang/String;I)V", "getAssignmentId", "()Ljava/lang/String;", "setAssignmentId", "(Ljava/lang/String;)V", "getSubmitVersionCode", "()I", "setSubmitVersionCode", "(I)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentSubmitVersionEntity {
    public static final int $stable = 8;
    private String assignmentId;
    private int submitVersionCode;

    public static /* synthetic */ AssignmentSubmitVersionEntity copy$default(AssignmentSubmitVersionEntity assignmentSubmitVersionEntity, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = assignmentSubmitVersionEntity.assignmentId;
        }
        if ((i2 & 2) != 0) {
            i = assignmentSubmitVersionEntity.submitVersionCode;
        }
        return assignmentSubmitVersionEntity.copy(str, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSubmitVersionCode() {
        return this.submitVersionCode;
    }

    public final AssignmentSubmitVersionEntity copy(String assignmentId, int submitVersionCode) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        return new AssignmentSubmitVersionEntity(assignmentId, submitVersionCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentSubmitVersionEntity)) {
            return false;
        }
        AssignmentSubmitVersionEntity assignmentSubmitVersionEntity = (AssignmentSubmitVersionEntity) other;
        return Intrinsics.areEqual(this.assignmentId, assignmentSubmitVersionEntity.assignmentId) && this.submitVersionCode == assignmentSubmitVersionEntity.submitVersionCode;
    }

    public int hashCode() {
        return (this.assignmentId.hashCode() * 31) + Integer.hashCode(this.submitVersionCode);
    }

    public String toString() {
        return "AssignmentSubmitVersionEntity(assignmentId=" + this.assignmentId + ", submitVersionCode=" + this.submitVersionCode + ")";
    }

    public AssignmentSubmitVersionEntity(String assignmentId, int i) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.assignmentId = assignmentId;
        this.submitVersionCode = i;
    }

    public /* synthetic */ AssignmentSubmitVersionEntity(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0 : i);
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final void setAssignmentId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.assignmentId = str;
    }

    public final int getSubmitVersionCode() {
        return this.submitVersionCode;
    }

    public final void setSubmitVersionCode(int i) {
        this.submitVersionCode = i;
    }

    public AssignmentSubmitVersionEntity() {
        this("", 0);
    }
}
