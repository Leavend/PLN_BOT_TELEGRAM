package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentAnswerResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/AssignmentAnswerResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentAnswerResponse extends BaseResponse {
    public static final int $stable = 0;
    private final String data;

    public static /* synthetic */ AssignmentAnswerResponse copy$default(AssignmentAnswerResponse assignmentAnswerResponse, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = assignmentAnswerResponse.data;
        }
        return assignmentAnswerResponse.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getData() {
        return this.data;
    }

    public final AssignmentAnswerResponse copy(String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new AssignmentAnswerResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof AssignmentAnswerResponse) && Intrinsics.areEqual(this.data, ((AssignmentAnswerResponse) other).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "AssignmentAnswerResponse(data=" + this.data + ")";
    }

    public final String getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssignmentAnswerResponse(String data) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
}
