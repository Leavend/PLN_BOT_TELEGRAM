package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseResponse.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/local/models/AssignmentSubmitS3PostResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "(Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;)V", "getData", "()Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentSubmitS3PostResponse extends id.go.bpsfasih.data.remote.dto.BaseResponse {
    public static final int $stable = 8;
    private final BaseResponseDataUpload data;

    public final BaseResponseDataUpload getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AssignmentSubmitS3PostResponse(BaseResponseDataUpload data) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
}
