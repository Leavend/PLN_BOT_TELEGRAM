package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import id.go.bpsfasih.data.local.models.RecapDataModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecapResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\tJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J>\u0010\u0016\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0017J\u0013\u0010\u0018\u001a\u00020\u00052\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0007HÖ\u0001R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/RecapResponse;", "", "data", "Lid/go/bpsfasih/data/local/models/RecapDataModel;", FirebaseAnalytics.Param.SUCCESS, "", "message", "", "httpStatus", "(Lid/go/bpsfasih/data/local/models/RecapDataModel;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getData", "()Lid/go/bpsfasih/data/local/models/RecapDataModel;", "getHttpStatus", "()Ljava/lang/String;", "getMessage", "getSuccess", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "copy", "(Lid/go/bpsfasih/data/local/models/RecapDataModel;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/data/remote/dto/RecapResponse;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class RecapResponse {
    public static final int $stable = 8;

    @SerializedName("data")
    private final RecapDataModel data;

    @SerializedName("httpStatus")
    private final String httpStatus;

    @SerializedName("message")
    private final String message;

    @SerializedName(FirebaseAnalytics.Param.SUCCESS)
    private final Boolean success;

    public RecapResponse() {
        this(null, null, null, null, 15, null);
    }

    public static /* synthetic */ RecapResponse copy$default(RecapResponse recapResponse, RecapDataModel recapDataModel, Boolean bool, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            recapDataModel = recapResponse.data;
        }
        if ((i & 2) != 0) {
            bool = recapResponse.success;
        }
        if ((i & 4) != 0) {
            str = recapResponse.message;
        }
        if ((i & 8) != 0) {
            str2 = recapResponse.httpStatus;
        }
        return recapResponse.copy(recapDataModel, bool, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final RecapDataModel getData() {
        return this.data;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component4, reason: from getter */
    public final String getHttpStatus() {
        return this.httpStatus;
    }

    public final RecapResponse copy(RecapDataModel data, Boolean success, String message, String httpStatus) {
        return new RecapResponse(data, success, message, httpStatus);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RecapResponse)) {
            return false;
        }
        RecapResponse recapResponse = (RecapResponse) other;
        return Intrinsics.areEqual(this.data, recapResponse.data) && Intrinsics.areEqual(this.success, recapResponse.success) && Intrinsics.areEqual(this.message, recapResponse.message) && Intrinsics.areEqual(this.httpStatus, recapResponse.httpStatus);
    }

    public int hashCode() {
        RecapDataModel recapDataModel = this.data;
        int iHashCode = (recapDataModel == null ? 0 : recapDataModel.hashCode()) * 31;
        Boolean bool = this.success;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.message;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.httpStatus;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "RecapResponse(data=" + this.data + ", success=" + this.success + ", message=" + this.message + ", httpStatus=" + this.httpStatus + ")";
    }

    public RecapResponse(RecapDataModel recapDataModel, Boolean bool, String str, String str2) {
        this.data = recapDataModel;
        this.success = bool;
        this.message = str;
        this.httpStatus = str2;
    }

    public /* synthetic */ RecapResponse(RecapDataModel recapDataModel, Boolean bool, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : recapDataModel, (i & 2) != 0 ? null : bool, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : str2);
    }

    public final RecapDataModel getData() {
        return this.data;
    }

    public final Boolean getSuccess() {
        return this.success;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getHttpStatus() {
        return this.httpStatus;
    }
}
