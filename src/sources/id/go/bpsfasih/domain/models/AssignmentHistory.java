package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentHistory.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J<\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\n¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/domain/models/AssignmentHistory;", "", "assignmentId", "", "remark", "", "userId", "statusAlias", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getAssignmentId", "()Ljava/lang/String;", "getRemark", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getStatusAlias", "getUserId", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/domain/models/AssignmentHistory;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentHistory {
    public static final int $stable = 0;
    private final String assignmentId;
    private final Integer remark;
    private final String statusAlias;
    private final String userId;

    public static /* synthetic */ AssignmentHistory copy$default(AssignmentHistory assignmentHistory, String str, Integer num, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assignmentHistory.assignmentId;
        }
        if ((i & 2) != 0) {
            num = assignmentHistory.remark;
        }
        if ((i & 4) != 0) {
            str2 = assignmentHistory.userId;
        }
        if ((i & 8) != 0) {
            str3 = assignmentHistory.statusAlias;
        }
        return assignmentHistory.copy(str, num, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getRemark() {
        return this.remark;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getStatusAlias() {
        return this.statusAlias;
    }

    public final AssignmentHistory copy(String assignmentId, Integer remark, String userId, String statusAlias) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        return new AssignmentHistory(assignmentId, remark, userId, statusAlias);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentHistory)) {
            return false;
        }
        AssignmentHistory assignmentHistory = (AssignmentHistory) other;
        return Intrinsics.areEqual(this.assignmentId, assignmentHistory.assignmentId) && Intrinsics.areEqual(this.remark, assignmentHistory.remark) && Intrinsics.areEqual(this.userId, assignmentHistory.userId) && Intrinsics.areEqual(this.statusAlias, assignmentHistory.statusAlias);
    }

    public int hashCode() {
        int iHashCode = this.assignmentId.hashCode() * 31;
        Integer num = this.remark;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str = this.userId;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.statusAlias;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentHistory(assignmentId=" + this.assignmentId + ", remark=" + this.remark + ", userId=" + this.userId + ", statusAlias=" + this.statusAlias + ")";
    }

    public AssignmentHistory(String assignmentId, Integer num, String str, String str2) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.assignmentId = assignmentId;
        this.remark = num;
        this.userId = str;
        this.statusAlias = str2;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final Integer getRemark() {
        return this.remark;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getStatusAlias() {
        return this.statusAlias;
    }
}
