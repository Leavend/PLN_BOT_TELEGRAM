package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.firebase.database.core.ServerValues;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationHistory.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J]\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006&"}, d2 = {"Lid/go/bpsfasih/domain/models/LocationHistory;", "", DownloadModel.ID, "", "assignment_id", "data_key", "latitude", "", "longitude", "accuracy", ServerValues.NAME_OP_TIMESTAMP, NotificationCompat.CATEGORY_STATUS, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDDLjava/lang/String;Ljava/lang/String;)V", "getAccuracy", "()D", "getAssignment_id", "()Ljava/lang/String;", "getData_key", "getId", "getLatitude", "getLongitude", "getStatus", "getTimestamp", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LocationHistory {
    public static final int $stable = 0;
    private final double accuracy;
    private final String assignment_id;
    private final String data_key;
    private final String id;
    private final double latitude;
    private final double longitude;
    private final String status;
    private final String timestamp;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getAssignment_id() {
        return this.assignment_id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getData_key() {
        return this.data_key;
    }

    /* renamed from: component4, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component5, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component6, reason: from getter */
    public final double getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component7, reason: from getter */
    public final String getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component8, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    public final LocationHistory copy(String id2, String assignment_id, String data_key, double latitude, double longitude, double accuracy, String timestamp, String status) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(status, "status");
        return new LocationHistory(id2, assignment_id, data_key, latitude, longitude, accuracy, timestamp, status);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationHistory)) {
            return false;
        }
        LocationHistory locationHistory = (LocationHistory) other;
        return Intrinsics.areEqual(this.id, locationHistory.id) && Intrinsics.areEqual(this.assignment_id, locationHistory.assignment_id) && Intrinsics.areEqual(this.data_key, locationHistory.data_key) && Double.compare(this.latitude, locationHistory.latitude) == 0 && Double.compare(this.longitude, locationHistory.longitude) == 0 && Double.compare(this.accuracy, locationHistory.accuracy) == 0 && Intrinsics.areEqual(this.timestamp, locationHistory.timestamp) && Intrinsics.areEqual(this.status, locationHistory.status);
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.assignment_id;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.data_key;
        return ((((((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + Double.hashCode(this.accuracy)) * 31) + this.timestamp.hashCode()) * 31) + this.status.hashCode();
    }

    public String toString() {
        return "LocationHistory(id=" + this.id + ", assignment_id=" + this.assignment_id + ", data_key=" + this.data_key + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", accuracy=" + this.accuracy + ", timestamp=" + this.timestamp + ", status=" + this.status + ")";
    }

    public LocationHistory(String id2, String str, String str2, double d, double d2, double d3, String timestamp, String status) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(timestamp, "timestamp");
        Intrinsics.checkNotNullParameter(status, "status");
        this.id = id2;
        this.assignment_id = str;
        this.data_key = str2;
        this.latitude = d;
        this.longitude = d2;
        this.accuracy = d3;
        this.timestamp = timestamp;
        this.status = status;
    }

    public final String getId() {
        return this.id;
    }

    public final String getAssignment_id() {
        return this.assignment_id;
    }

    public final String getData_key() {
        return this.data_key;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final double getAccuracy() {
        return this.accuracy;
    }

    public final String getTimestamp() {
        return this.timestamp;
    }

    public final String getStatus() {
        return this.status;
    }
}
