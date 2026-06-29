package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeriodeUpdateModel.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Be\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0014J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jn\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020\u00062\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\f\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0005\u0010\u0014R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006*"}, d2 = {"Lid/go/bpsfasih/data/local/models/PeriodeUpdateEntity;", "", "endDate", "", "surveyId", "isActive", "", "updatedAt", "Lid/go/bpsfasih/data/local/models/UpdatedAt;", "name", "createdAt", "Lid/go/bpsfasih/data/local/models/CreatedAt;", DownloadModel.ID, "startDate", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lid/go/bpsfasih/data/local/models/UpdatedAt;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/CreatedAt;Ljava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Lid/go/bpsfasih/data/local/models/CreatedAt;", "getEndDate", "()Ljava/lang/String;", "getId", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "getStartDate", "getSurveyId", "getUpdatedAt", "()Lid/go/bpsfasih/data/local/models/UpdatedAt;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Lid/go/bpsfasih/data/local/models/UpdatedAt;Ljava/lang/String;Lid/go/bpsfasih/data/local/models/CreatedAt;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/data/local/models/PeriodeUpdateEntity;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class PeriodeUpdateEntity {
    public static final int $stable = 0;

    @SerializedName("created_at")
    private final CreatedAt createdAt;

    @SerializedName(FirebaseAnalytics.Param.END_DATE)
    private final String endDate;

    @SerializedName("_id")
    private final String id;

    @SerializedName("is_active")
    private final Boolean isActive;

    @SerializedName("name")
    private final String name;

    @SerializedName(FirebaseAnalytics.Param.START_DATE)
    private final String startDate;

    @SerializedName("survey_id")
    private final String surveyId;

    @SerializedName("updated_at")
    private final UpdatedAt updatedAt;

    public PeriodeUpdateEntity() {
        this(null, null, null, null, null, null, null, null, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getEndDate() {
        return this.endDate;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    /* renamed from: component4, reason: from getter */
    public final UpdatedAt getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component6, reason: from getter */
    public final CreatedAt getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component7, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component8, reason: from getter */
    public final String getStartDate() {
        return this.startDate;
    }

    public final PeriodeUpdateEntity copy(String endDate, String surveyId, Boolean isActive, UpdatedAt updatedAt, String name, CreatedAt createdAt, String id2, String startDate) {
        return new PeriodeUpdateEntity(endDate, surveyId, isActive, updatedAt, name, createdAt, id2, startDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PeriodeUpdateEntity)) {
            return false;
        }
        PeriodeUpdateEntity periodeUpdateEntity = (PeriodeUpdateEntity) other;
        return Intrinsics.areEqual(this.endDate, periodeUpdateEntity.endDate) && Intrinsics.areEqual(this.surveyId, periodeUpdateEntity.surveyId) && Intrinsics.areEqual(this.isActive, periodeUpdateEntity.isActive) && Intrinsics.areEqual(this.updatedAt, periodeUpdateEntity.updatedAt) && Intrinsics.areEqual(this.name, periodeUpdateEntity.name) && Intrinsics.areEqual(this.createdAt, periodeUpdateEntity.createdAt) && Intrinsics.areEqual(this.id, periodeUpdateEntity.id) && Intrinsics.areEqual(this.startDate, periodeUpdateEntity.startDate);
    }

    public int hashCode() {
        String str = this.endDate;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.surveyId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isActive;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        UpdatedAt updatedAt = this.updatedAt;
        int iHashCode4 = (iHashCode3 + (updatedAt == null ? 0 : updatedAt.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        CreatedAt createdAt = this.createdAt;
        int iHashCode6 = (iHashCode5 + (createdAt == null ? 0 : createdAt.hashCode())) * 31;
        String str4 = this.id;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.startDate;
        return iHashCode7 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "PeriodeUpdateEntity(endDate=" + this.endDate + ", surveyId=" + this.surveyId + ", isActive=" + this.isActive + ", updatedAt=" + this.updatedAt + ", name=" + this.name + ", createdAt=" + this.createdAt + ", id=" + this.id + ", startDate=" + this.startDate + ")";
    }

    public PeriodeUpdateEntity(String str, String str2, Boolean bool, UpdatedAt updatedAt, String str3, CreatedAt createdAt, String str4, String str5) {
        this.endDate = str;
        this.surveyId = str2;
        this.isActive = bool;
        this.updatedAt = updatedAt;
        this.name = str3;
        this.createdAt = createdAt;
        this.id = str4;
        this.startDate = str5;
    }

    public /* synthetic */ PeriodeUpdateEntity(String str, String str2, Boolean bool, UpdatedAt updatedAt, String str3, CreatedAt createdAt, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : bool, (i & 8) != 0 ? null : updatedAt, (i & 16) != 0 ? null : str3, (i & 32) != 0 ? null : createdAt, (i & 64) != 0 ? null : str4, (i & 128) == 0 ? str5 : null);
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final Boolean isActive() {
        return this.isActive;
    }

    public final UpdatedAt getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getName() {
        return this.name;
    }

    public final CreatedAt getCreatedAt() {
        return this.createdAt;
    }

    public final String getId() {
        return this.id;
    }

    public final String getStartDate() {
        return this.startDate;
    }
}
