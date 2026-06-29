package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.database.core.ServerValues;
import com.kdownloader.database.DownloadModel;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationTrackingEntity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b%\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u007f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0013HÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\fHÆ\u0003J\t\u00103\u001a\u00020\fHÆ\u0003J\u0096\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u0013HÆ\u0001¢\u0006\u0002\u00105J\u0013\u00106\u001a\u00020\u00132\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u000209HÖ\u0001J\t\u0010:\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0016\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0012\u001a\u00020\u00138\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u001eR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0016\u0010\r\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0016\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0019R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019¨\u0006;"}, d2 = {"Lid/go/bpsfasih/data/local/entities/LocationTrackingEntity;", "", DownloadModel.ID, "", "publicId", "", "userId", "assignmentId", "surveyPeriodeId", "activity", "session", "latitude", "", "longitude", ServerValues.NAME_OP_TIMESTAMP, "accuracy", "", "date", "isSynced", "", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDJLjava/lang/Float;Ljava/lang/String;Z)V", "getAccuracy", "()Ljava/lang/Float;", "Ljava/lang/Float;", "getActivity", "()Ljava/lang/String;", "getAssignmentId", "getDate", "getId", "()J", "()Z", "getLatitude", "()D", "getLongitude", "getPublicId", "getSession", "getSurveyPeriodeId", "getTimestamp", "getUserId", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDJLjava/lang/Float;Ljava/lang/String;Z)Lid/go/bpsfasih/data/local/entities/LocationTrackingEntity;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LocationTrackingEntity {
    public static final int $stable = 0;
    private final Float accuracy;
    private final String activity;
    private final String assignmentId;
    private final String date;
    private final long id;
    private final boolean isSynced;
    private final double latitude;
    private final double longitude;
    private final String publicId;
    private final String session;
    private final String surveyPeriodeId;
    private final long timestamp;
    private final String userId;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component11, reason: from getter */
    public final Float getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getIsSynced() {
        return this.isSynced;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPublicId() {
        return this.publicId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getActivity() {
        return this.activity;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSession() {
        return this.session;
    }

    /* renamed from: component8, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component9, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    public final LocationTrackingEntity copy(long id2, String publicId, String userId, String assignmentId, String surveyPeriodeId, String activity, String session, double latitude, double longitude, long timestamp, Float accuracy, String date, boolean isSynced) {
        Intrinsics.checkNotNullParameter(publicId, "publicId");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(date, "date");
        return new LocationTrackingEntity(id2, publicId, userId, assignmentId, surveyPeriodeId, activity, session, latitude, longitude, timestamp, accuracy, date, isSynced);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationTrackingEntity)) {
            return false;
        }
        LocationTrackingEntity locationTrackingEntity = (LocationTrackingEntity) other;
        return this.id == locationTrackingEntity.id && Intrinsics.areEqual(this.publicId, locationTrackingEntity.publicId) && Intrinsics.areEqual(this.userId, locationTrackingEntity.userId) && Intrinsics.areEqual(this.assignmentId, locationTrackingEntity.assignmentId) && Intrinsics.areEqual(this.surveyPeriodeId, locationTrackingEntity.surveyPeriodeId) && Intrinsics.areEqual(this.activity, locationTrackingEntity.activity) && Intrinsics.areEqual(this.session, locationTrackingEntity.session) && Double.compare(this.latitude, locationTrackingEntity.latitude) == 0 && Double.compare(this.longitude, locationTrackingEntity.longitude) == 0 && this.timestamp == locationTrackingEntity.timestamp && Intrinsics.areEqual((Object) this.accuracy, (Object) locationTrackingEntity.accuracy) && Intrinsics.areEqual(this.date, locationTrackingEntity.date) && this.isSynced == locationTrackingEntity.isSynced;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((Long.hashCode(this.id) * 31) + this.publicId.hashCode()) * 31) + this.userId.hashCode()) * 31;
        String str = this.assignmentId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.surveyPeriodeId;
        int iHashCode3 = (((((((((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.activity.hashCode()) * 31) + this.session.hashCode()) * 31) + Double.hashCode(this.latitude)) * 31) + Double.hashCode(this.longitude)) * 31) + Long.hashCode(this.timestamp)) * 31;
        Float f = this.accuracy;
        int iHashCode4 = (((iHashCode3 + (f != null ? f.hashCode() : 0)) * 31) + this.date.hashCode()) * 31;
        boolean z = this.isSynced;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode4 + i;
    }

    public String toString() {
        return "LocationTrackingEntity(id=" + this.id + ", publicId=" + this.publicId + ", userId=" + this.userId + ", assignmentId=" + this.assignmentId + ", surveyPeriodeId=" + this.surveyPeriodeId + ", activity=" + this.activity + ", session=" + this.session + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", timestamp=" + this.timestamp + ", accuracy=" + this.accuracy + ", date=" + this.date + ", isSynced=" + this.isSynced + ")";
    }

    public LocationTrackingEntity(long j, String publicId, String userId, String str, String str2, String activity, String session, double d, double d2, long j2, Float f, String date, boolean z) {
        Intrinsics.checkNotNullParameter(publicId, "publicId");
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(session, "session");
        Intrinsics.checkNotNullParameter(date, "date");
        this.id = j;
        this.publicId = publicId;
        this.userId = userId;
        this.assignmentId = str;
        this.surveyPeriodeId = str2;
        this.activity = activity;
        this.session = session;
        this.latitude = d;
        this.longitude = d2;
        this.timestamp = j2;
        this.accuracy = f;
        this.date = date;
        this.isSynced = z;
    }

    public final long getId() {
        return this.id;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ LocationTrackingEntity(long j, String str, String str2, String str3, String str4, String str5, String str6, double d, double d2, long j2, Float f, String str7, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        String str8;
        long j3 = (i & 1) != 0 ? 0L : j;
        if ((i & 2) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            str8 = string;
        } else {
            str8 = str;
        }
        this(j3, str8, str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : str4, str5, (i & 64) != 0 ? "" : str6, d, d2, j2, f, str7, (i & 4096) != 0 ? false : z);
    }

    public final String getPublicId() {
        return this.publicId;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final String getSurveyPeriodeId() {
        return this.surveyPeriodeId;
    }

    public final String getActivity() {
        return this.activity;
    }

    public final String getSession() {
        return this.session;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final Float getAccuracy() {
        return this.accuracy;
    }

    public final String getDate() {
        return this.date;
    }

    public final boolean isSynced() {
        return this.isSynced;
    }
}
