package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentRegionResponse.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/AssignmentRegionResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentRegionResponse extends BaseResponse {
    public static final int $stable = 8;
    private final List<AssignmentRegionEntity> data;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AssignmentRegionResponse copy$default(AssignmentRegionResponse assignmentRegionResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = assignmentRegionResponse.data;
        }
        return assignmentRegionResponse.copy(list);
    }

    public final List<AssignmentRegionEntity> component1() {
        return this.data;
    }

    public final AssignmentRegionResponse copy(List<AssignmentRegionEntity> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new AssignmentRegionResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AssignmentRegionResponse) && Intrinsics.areEqual(this.data, ((AssignmentRegionResponse) other).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "AssignmentRegionResponse(data=" + this.data + ")";
    }

    public final List<AssignmentRegionEntity> getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssignmentRegionResponse(List<AssignmentRegionEntity> data) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
}
