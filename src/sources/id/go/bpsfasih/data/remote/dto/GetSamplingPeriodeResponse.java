package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.GetSamplingPeriodeData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GetSamplingResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/GetSamplingPeriodeResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "Lid/go/bpsfasih/data/local/entities/GetSamplingPeriodeData;", "(Lid/go/bpsfasih/data/local/entities/GetSamplingPeriodeData;)V", "getData", "()Lid/go/bpsfasih/data/local/entities/GetSamplingPeriodeData;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class GetSamplingPeriodeResponse extends BaseResponse {
    public static final int $stable = 8;
    private final GetSamplingPeriodeData data;

    public static /* synthetic */ GetSamplingPeriodeResponse copy$default(GetSamplingPeriodeResponse getSamplingPeriodeResponse, GetSamplingPeriodeData getSamplingPeriodeData, int i, Object obj) {
        if ((i & 1) != 0) {
            getSamplingPeriodeData = getSamplingPeriodeResponse.data;
        }
        return getSamplingPeriodeResponse.copy(getSamplingPeriodeData);
    }

    /* renamed from: component1, reason: from getter */
    public final GetSamplingPeriodeData getData() {
        return this.data;
    }

    public final GetSamplingPeriodeResponse copy(GetSamplingPeriodeData data) {
        Intrinsics.checkNotNullParameter(data, "data");
        return new GetSamplingPeriodeResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof GetSamplingPeriodeResponse) && Intrinsics.areEqual(this.data, ((GetSamplingPeriodeResponse) other).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "GetSamplingPeriodeResponse(data=" + this.data + ")";
    }

    public final GetSamplingPeriodeData getData() {
        return this.data;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetSamplingPeriodeResponse(GetSamplingPeriodeData data) {
        super(null, null, null, 7, null);
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
    }
}
