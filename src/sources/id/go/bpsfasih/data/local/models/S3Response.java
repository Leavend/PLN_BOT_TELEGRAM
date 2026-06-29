package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseResponse.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\b\u0002\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lid/go/bpsfasih/data/local/models/S3Response;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "", "Lid/go/bpsfasih/data/local/models/S3Entity;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class S3Response extends id.go.bpsfasih.data.remote.dto.BaseResponse {
    public static final int $stable = 8;
    private final List<S3Entity> data;

    /* JADX WARN: Multi-variable type inference failed */
    public S3Response() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public /* synthetic */ S3Response(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }

    public final List<S3Entity> getData() {
        return this.data;
    }

    public S3Response(List<S3Entity> list) {
        super(null, null, null, 7, null);
        this.data = list;
    }
}
