package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseResponse.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lid/go/bpsfasih/data/local/models/BaseResponseDataStringApproval;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "", "(Ljava/lang/Integer;)V", "getData", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BaseResponseDataStringApproval extends id.go.bpsfasih.data.remote.dto.BaseResponse {
    public static final int $stable = 0;
    private final Integer data;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseResponseDataStringApproval() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ BaseResponseDataStringApproval(Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : num);
    }

    public final Integer getData() {
        return this.data;
    }

    public BaseResponseDataStringApproval(Integer num) {
        super(null, null, null, 7, null);
        this.data = num;
    }
}
