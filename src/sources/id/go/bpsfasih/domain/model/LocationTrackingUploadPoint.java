package id.go.bpsfasih.domain.model;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationTrackingUploadPoint.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0006HÆ\u0003J\t\u0010!\u001a\u00020\tHÆ\u0003J\t\u0010\"\u001a\u00020\u000bHÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jg\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u0006-"}, d2 = {"Lid/go/bpsfasih/domain/model/LocationTrackingUploadPoint;", "", DownloadModel.ID, "", "sessionId", "latitude", "", "longitude", "accuracy", "", "ts", "", "activity", "assignmentId", "surveyPeriodId", "(Ljava/lang/String;Ljava/lang/String;DDFJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccuracy", "()F", "getActivity", "()Ljava/lang/String;", "getAssignmentId", "getId", "getLatitude", "()D", "getLongitude", "getSessionId", "getSurveyPeriodId", "getTs", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LocationTrackingUploadPoint {
    public static final int $stable = 0;
    private final float accuracy;
    private final String activity;
    private final String assignmentId;
    private final String id;
    private final double latitude;
    private final double longitude;
    private final String sessionId;
    private final String surveyPeriodId;
    private final long ts;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSessionId() {
        return this.sessionId;
    }

    /* renamed from: component3, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component4, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component5, reason: from getter */
    public final float getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component6, reason: from getter */
    public final long getTs() {
        return this.ts;
    }

    /* renamed from: component7, reason: from getter */
    public final String getActivity() {
        return this.activity;
    }

    /* renamed from: component8, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final LocationTrackingUploadPoint copy(String id2, String sessionId, double latitude, double longitude, float accuracy, long ts, String activity, String assignmentId, String surveyPeriodId) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        return new LocationTrackingUploadPoint(id2, sessionId, latitude, longitude, accuracy, ts, activity, assignmentId, surveyPeriodId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationTrackingUploadPoint)) {
            return false;
        }
        LocationTrackingUploadPoint locationTrackingUploadPoint = (LocationTrackingUploadPoint) other;
        return Intrinsics.areEqual(this.id, locationTrackingUploadPoint.id) && Intrinsics.areEqual(this.sessionId, locationTrackingUploadPoint.sessionId) && Double.compare(this.latitude, locationTrackingUploadPoint.latitude) == 0 && Double.compare(this.longitude, locationTrackingUploadPoint.longitude) == 0 && Float.compare(this.accuracy, locationTrackingUploadPoint.accuracy) == 0 && this.ts == locationTrackingUploadPoint.ts && Intrinsics.areEqual(this.activity, locationTrackingUploadPoint.activity) && Intrinsics.areEqual(this.assignmentId, locationTrackingUploadPoint.assignmentId) && Intrinsics.areEqual(this.surveyPeriodId, locationTrackingUploadPoint.surveyPeriodId);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.id.hashCode() * 31) + this.sessionId.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + Float.hashCode(this.accuracy)) * 31) + Long.hashCode(this.ts)) * 31) + this.activity.hashCode()) * 31;
        String str = this.assignmentId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.surveyPeriodId;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "LocationTrackingUploadPoint(id=" + this.id + ", sessionId=" + this.sessionId + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", accuracy=" + this.accuracy + ", ts=" + this.ts + ", activity=" + this.activity + ", assignmentId=" + this.assignmentId + ", surveyPeriodId=" + this.surveyPeriodId + ")";
    }

    public LocationTrackingUploadPoint(String id2, String sessionId, double d, double d2, float f, long j, String activity, String str, String str2) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.id = id2;
        this.sessionId = sessionId;
        this.latitude = d;
        this.longitude = d2;
        this.accuracy = f;
        this.ts = j;
        this.activity = activity;
        this.assignmentId = str;
        this.surveyPeriodId = str2;
    }

    public final String getId() {
        return this.id;
    }

    public final String getSessionId() {
        return this.sessionId;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final float getAccuracy() {
        return this.accuracy;
    }

    public final long getTs() {
        return this.ts;
    }

    public final String getActivity() {
        return this.activity;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }
}
