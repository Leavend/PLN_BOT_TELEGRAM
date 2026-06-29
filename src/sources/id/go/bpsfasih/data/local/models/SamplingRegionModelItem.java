package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SamplingRegionModel.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BY\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lid/go/bpsfasih/data/local/models/SamplingRegionModelItem;", "", "mode", "", "createdAt", "fullCode", DownloadModel.ID, "samplingSurveyPeriodId", NotificationCompat.CATEGORY_STATUS, "updatedAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCreatedAt", "()Ljava/lang/String;", "getFullCode", "getId", "getMode", "getSamplingSurveyPeriodId", "getStatus", "getUpdatedAt", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SamplingRegionModelItem {
    public static final int $stable = 0;

    @SerializedName("createdAt")
    private final String createdAt;

    @SerializedName("fullCode")
    private final String fullCode;

    @SerializedName("_id")
    private final String id;

    @SerializedName("mode")
    private final String mode;

    @SerializedName("samplingSurveyPeriodId")
    private final String samplingSurveyPeriodId;

    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private final String status;

    @SerializedName("updatedAt")
    private final String updatedAt;

    public SamplingRegionModelItem() {
        this(null, null, null, null, null, null, null, 127, null);
    }

    public static /* synthetic */ SamplingRegionModelItem copy$default(SamplingRegionModelItem samplingRegionModelItem, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = samplingRegionModelItem.mode;
        }
        if ((i & 2) != 0) {
            str2 = samplingRegionModelItem.createdAt;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = samplingRegionModelItem.fullCode;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = samplingRegionModelItem.id;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = samplingRegionModelItem.samplingSurveyPeriodId;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = samplingRegionModelItem.status;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = samplingRegionModelItem.updatedAt;
        }
        return samplingRegionModelItem.copy(str, str8, str9, str10, str11, str12, str7);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFullCode() {
        return this.fullCode;
    }

    /* renamed from: component4, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSamplingSurveyPeriodId() {
        return this.samplingSurveyPeriodId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component7, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final SamplingRegionModelItem copy(String mode, String createdAt, String fullCode, String id2, String samplingSurveyPeriodId, String status, String updatedAt) {
        return new SamplingRegionModelItem(mode, createdAt, fullCode, id2, samplingSurveyPeriodId, status, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SamplingRegionModelItem)) {
            return false;
        }
        SamplingRegionModelItem samplingRegionModelItem = (SamplingRegionModelItem) other;
        return Intrinsics.areEqual(this.mode, samplingRegionModelItem.mode) && Intrinsics.areEqual(this.createdAt, samplingRegionModelItem.createdAt) && Intrinsics.areEqual(this.fullCode, samplingRegionModelItem.fullCode) && Intrinsics.areEqual(this.id, samplingRegionModelItem.id) && Intrinsics.areEqual(this.samplingSurveyPeriodId, samplingRegionModelItem.samplingSurveyPeriodId) && Intrinsics.areEqual(this.status, samplingRegionModelItem.status) && Intrinsics.areEqual(this.updatedAt, samplingRegionModelItem.updatedAt);
    }

    public int hashCode() {
        String str = this.mode;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.createdAt;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.fullCode;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.id;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.samplingSurveyPeriodId;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.status;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.updatedAt;
        return iHashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "SamplingRegionModelItem(mode=" + this.mode + ", createdAt=" + this.createdAt + ", fullCode=" + this.fullCode + ", id=" + this.id + ", samplingSurveyPeriodId=" + this.samplingSurveyPeriodId + ", status=" + this.status + ", updatedAt=" + this.updatedAt + ")";
    }

    public SamplingRegionModelItem(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mode = str;
        this.createdAt = str2;
        this.fullCode = str3;
        this.id = str4;
        this.samplingSurveyPeriodId = str5;
        this.status = str6;
        this.updatedAt = str7;
    }

    public /* synthetic */ SamplingRegionModelItem(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) != 0 ? null : str7);
    }

    public final String getMode() {
        return this.mode;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getFullCode() {
        return this.fullCode;
    }

    public final String getId() {
        return this.id;
    }

    public final String getSamplingSurveyPeriodId() {
        return this.samplingSurveyPeriodId;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }
}
