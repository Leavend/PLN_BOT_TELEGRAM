package id.go.bpsfasih.data.local.pojo;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentRegionWilayahPojo.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, d2 = {"Lid/go/bpsfasih/data/local/pojo/AssignmentRegionWilayahPojo;", "", "assignmentRegion", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "openCount", "", "rejectCount", "submitCount", "pendingCount", "approvedCount", "(Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;IIIII)V", "getApprovedCount", "()I", "getAssignmentRegion", "()Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "getOpenCount", "getPendingCount", "getRejectCount", "getSubmitCount", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentRegionWilayahPojo {
    public static final int $stable = 8;
    private final int approvedCount;
    private final AssignmentRegionEntity assignmentRegion;
    private final int openCount;
    private final int pendingCount;
    private final int rejectCount;
    private final int submitCount;

    public static /* synthetic */ AssignmentRegionWilayahPojo copy$default(AssignmentRegionWilayahPojo assignmentRegionWilayahPojo, AssignmentRegionEntity assignmentRegionEntity, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
        if ((i6 & 1) != 0) {
            assignmentRegionEntity = assignmentRegionWilayahPojo.assignmentRegion;
        }
        if ((i6 & 2) != 0) {
            i = assignmentRegionWilayahPojo.openCount;
        }
        int i7 = i;
        if ((i6 & 4) != 0) {
            i2 = assignmentRegionWilayahPojo.rejectCount;
        }
        int i8 = i2;
        if ((i6 & 8) != 0) {
            i3 = assignmentRegionWilayahPojo.submitCount;
        }
        int i9 = i3;
        if ((i6 & 16) != 0) {
            i4 = assignmentRegionWilayahPojo.pendingCount;
        }
        int i10 = i4;
        if ((i6 & 32) != 0) {
            i5 = assignmentRegionWilayahPojo.approvedCount;
        }
        return assignmentRegionWilayahPojo.copy(assignmentRegionEntity, i7, i8, i9, i10, i5);
    }

    /* renamed from: component1, reason: from getter */
    public final AssignmentRegionEntity getAssignmentRegion() {
        return this.assignmentRegion;
    }

    /* renamed from: component2, reason: from getter */
    public final int getOpenCount() {
        return this.openCount;
    }

    /* renamed from: component3, reason: from getter */
    public final int getRejectCount() {
        return this.rejectCount;
    }

    /* renamed from: component4, reason: from getter */
    public final int getSubmitCount() {
        return this.submitCount;
    }

    /* renamed from: component5, reason: from getter */
    public final int getPendingCount() {
        return this.pendingCount;
    }

    /* renamed from: component6, reason: from getter */
    public final int getApprovedCount() {
        return this.approvedCount;
    }

    public final AssignmentRegionWilayahPojo copy(AssignmentRegionEntity assignmentRegion, int openCount, int rejectCount, int submitCount, int pendingCount, int approvedCount) {
        Intrinsics.checkNotNullParameter(assignmentRegion, "assignmentRegion");
        return new AssignmentRegionWilayahPojo(assignmentRegion, openCount, rejectCount, submitCount, pendingCount, approvedCount);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentRegionWilayahPojo)) {
            return false;
        }
        AssignmentRegionWilayahPojo assignmentRegionWilayahPojo = (AssignmentRegionWilayahPojo) other;
        return Intrinsics.areEqual(this.assignmentRegion, assignmentRegionWilayahPojo.assignmentRegion) && this.openCount == assignmentRegionWilayahPojo.openCount && this.rejectCount == assignmentRegionWilayahPojo.rejectCount && this.submitCount == assignmentRegionWilayahPojo.submitCount && this.pendingCount == assignmentRegionWilayahPojo.pendingCount && this.approvedCount == assignmentRegionWilayahPojo.approvedCount;
    }

    public int hashCode() {
        return (((((((((this.assignmentRegion.hashCode() * 31) + Integer.hashCode(this.openCount)) * 31) + Integer.hashCode(this.rejectCount)) * 31) + Integer.hashCode(this.submitCount)) * 31) + Integer.hashCode(this.pendingCount)) * 31) + Integer.hashCode(this.approvedCount);
    }

    public String toString() {
        return "AssignmentRegionWilayahPojo(assignmentRegion=" + this.assignmentRegion + ", openCount=" + this.openCount + ", rejectCount=" + this.rejectCount + ", submitCount=" + this.submitCount + ", pendingCount=" + this.pendingCount + ", approvedCount=" + this.approvedCount + ")";
    }

    public AssignmentRegionWilayahPojo(AssignmentRegionEntity assignmentRegion, int i, int i2, int i3, int i4, int i5) {
        Intrinsics.checkNotNullParameter(assignmentRegion, "assignmentRegion");
        this.assignmentRegion = assignmentRegion;
        this.openCount = i;
        this.rejectCount = i2;
        this.submitCount = i3;
        this.pendingCount = i4;
        this.approvedCount = i5;
    }

    public final AssignmentRegionEntity getAssignmentRegion() {
        return this.assignmentRegion;
    }

    public final int getOpenCount() {
        return this.openCount;
    }

    public final int getRejectCount() {
        return this.rejectCount;
    }

    public final int getSubmitCount() {
        return this.submitCount;
    }

    public final int getPendingCount() {
        return this.pendingCount;
    }

    public final int getApprovedCount() {
        return this.approvedCount;
    }
}
