package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.database.core.ServerValues;
import kotlin.Metadata;

/* compiled from: ActionLog.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0007\u0018\u00002\u00020\u0001BU\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\b\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b \u0010\u001c\"\u0004\b!\u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0004\b#\u0010\u0012R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0004\b%\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0010\"\u0004\b'\u0010\u0012¨\u0006("}, d2 = {"Lid/go/bpsfasih/domain/models/ActionLog;", "", ServerValues.NAME_OP_TIMESTAMP, "", "action", "latitude", "", "longitude", "isMock", "", "userId", "userName", "batteryInfo", "Lid/go/bpsfasih/domain/models/BatteryInfo;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/domain/models/BatteryInfo;)V", "getAction", "()Ljava/lang/String;", "setAction", "(Ljava/lang/String;)V", "getBatteryInfo", "()Lid/go/bpsfasih/domain/models/BatteryInfo;", "setBatteryInfo", "(Lid/go/bpsfasih/domain/models/BatteryInfo;)V", "()Ljava/lang/Boolean;", "setMock", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getLatitude", "()Ljava/lang/Double;", "setLatitude", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "getLongitude", "setLongitude", "getTimestamp", "setTimestamp", "getUserId", "setUserId", "getUserName", "setUserName", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ActionLog {
    public static final int $stable = 8;
    private String action;
    private BatteryInfo batteryInfo;
    private Boolean isMock;
    private Double latitude;
    private Double longitude;
    private String timestamp;
    private String userId;
    private String userName;

    public ActionLog(String str, String str2, Double d, Double d2, Boolean bool, String str3, String str4, BatteryInfo batteryInfo) {
        this.timestamp = str;
        this.action = str2;
        this.latitude = d;
        this.longitude = d2;
        this.isMock = bool;
        this.userId = str3;
        this.userName = str4;
        this.batteryInfo = batteryInfo;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final void setTimestamp(String str) {
        this.timestamp = str;
    }

    public final String getAction() {
        return this.action;
    }

    public final void setAction(String str) {
        this.action = str;
    }

    public final Double getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(Double d) {
        this.latitude = d;
    }

    public final Double getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(Double d) {
        this.longitude = d;
    }

    /* renamed from: isMock, reason: from getter */
    public final Boolean getIsMock() {
        return this.isMock;
    }

    public final void setMock(Boolean bool) {
        this.isMock = bool;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final String getUserName() {
        return this.userName;
    }

    public final void setUserName(String str) {
        this.userName = str;
    }

    public final BatteryInfo getBatteryInfo() {
        return this.batteryInfo;
    }

    public final void setBatteryInfo(BatteryInfo batteryInfo) {
        this.batteryInfo = batteryInfo;
    }
}
