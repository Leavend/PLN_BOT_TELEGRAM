package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckNotificationAssignmentResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentModel;", "(Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentModel;)V", "getData", "()Lid/go/bpsfasih/data/remote/dto/CheckNotificationAssignmentModel;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class CheckNotificationAssignmentResponse extends BaseResponse {
    public static final int $stable = 8;
    private final CheckNotificationAssignmentModel data;

    /* JADX WARN: Multi-variable type inference failed */
    public CheckNotificationAssignmentResponse() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ CheckNotificationAssignmentResponse copy$default(CheckNotificationAssignmentResponse checkNotificationAssignmentResponse, CheckNotificationAssignmentModel checkNotificationAssignmentModel, int i, Object obj) {
        if ((i & 1) != 0) {
            checkNotificationAssignmentModel = checkNotificationAssignmentResponse.data;
        }
        return checkNotificationAssignmentResponse.copy(checkNotificationAssignmentModel);
    }

    /* renamed from: component1, reason: from getter */
    public final CheckNotificationAssignmentModel getData() {
        return this.data;
    }

    public final CheckNotificationAssignmentResponse copy(CheckNotificationAssignmentModel data) {
        return new CheckNotificationAssignmentResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof CheckNotificationAssignmentResponse) && Intrinsics.areEqual(this.data, ((CheckNotificationAssignmentResponse) other).data);
    }

    public int hashCode() {
        CheckNotificationAssignmentModel checkNotificationAssignmentModel = this.data;
        if (checkNotificationAssignmentModel == null) {
            return 0;
        }
        return checkNotificationAssignmentModel.hashCode();
    }

    public String toString() {
        return "CheckNotificationAssignmentResponse(data=" + this.data + ")";
    }

    public /* synthetic */ CheckNotificationAssignmentResponse(CheckNotificationAssignmentModel checkNotificationAssignmentModel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : checkNotificationAssignmentModel);
    }

    public final CheckNotificationAssignmentModel getData() {
        return this.data;
    }

    public CheckNotificationAssignmentResponse(CheckNotificationAssignmentModel checkNotificationAssignmentModel) {
        super(null, null, null, 7, null);
        this.data = checkNotificationAssignmentModel;
    }
}
