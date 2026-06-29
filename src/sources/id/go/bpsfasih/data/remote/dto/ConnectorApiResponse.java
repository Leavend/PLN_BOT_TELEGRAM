package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ConnectorApiResponse.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\bHÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R \u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/ConnectorApiResponse;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "data", "", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "httpStatus", "", "getHttpStatus", "()Ljava/lang/String;", "setHttpStatus", "(Ljava/lang/String;)V", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ConnectorApiResponse extends BaseResponse {
    public static final int $stable = 8;

    @SerializedName("data")
    private final Object data;

    @SerializedName("httpStatus")
    private String httpStatus;

    public static /* synthetic */ ConnectorApiResponse copy$default(ConnectorApiResponse connectorApiResponse, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            obj = connectorApiResponse.data;
        }
        return connectorApiResponse.copy(obj);
    }

    /* renamed from: component1, reason: from getter */
    public final Object getData() {
        return this.data;
    }

    public final ConnectorApiResponse copy(Object data) {
        return new ConnectorApiResponse(data);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ConnectorApiResponse) && Intrinsics.areEqual(this.data, ((ConnectorApiResponse) other).data);
    }

    public int hashCode() {
        Object obj = this.data;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "ConnectorApiResponse(data=" + this.data + ")";
    }

    public final Object getData() {
        return this.data;
    }

    public ConnectorApiResponse(Object obj) {
        super(null, null, null, 7, null);
        this.data = obj;
    }

    public final String getHttpStatus() {
        return this.httpStatus;
    }

    public final void setHttpStatus(String str) {
        this.httpStatus = str;
    }
}
