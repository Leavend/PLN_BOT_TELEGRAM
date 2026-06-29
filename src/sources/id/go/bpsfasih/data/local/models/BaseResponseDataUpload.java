package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: BaseResponse.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0017\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "", FirebaseAnalytics.Param.SUCCESS, "", "errorCode", "", "message", "", "data", "Lid/go/bpsfasih/data/local/models/AssignmentReturnData;", "(Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/AssignmentReturnData;)V", "getData", "()Lid/go/bpsfasih/data/local/models/AssignmentReturnData;", "getErrorCode", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getSuccess", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class BaseResponseDataUpload {
    public static final int $stable = 8;
    private final AssignmentReturnData data;
    private final Integer errorCode;
    private final String message;
    private final Boolean success;

    public BaseResponseDataUpload() {
        this(null, null, null, null, 15, null);
    }

    public BaseResponseDataUpload(Boolean bool, Integer num, String str, AssignmentReturnData assignmentReturnData) {
        this.success = bool;
        this.errorCode = num;
        this.message = str;
        this.data = assignmentReturnData;
    }

    public /* synthetic */ BaseResponseDataUpload(Boolean bool, Integer num, String str, AssignmentReturnData assignmentReturnData, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : bool, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : assignmentReturnData);
    }

    public final Boolean getSuccess() {
        return this.success;
    }

    public final Integer getErrorCode() {
        return this.errorCode;
    }

    public final String getMessage() {
        return this.message;
    }

    public final AssignmentReturnData getData() {
        return this.data;
    }
}
