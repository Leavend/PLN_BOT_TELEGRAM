package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Village.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001e\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000eJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010 \u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\"\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010#\u001a\u0004\u0018\u00010\u0005HÆ\u0003J`\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010%J\u0013\u0010&\u001a\u00020\u00032\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\fHÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0018\u0010\r\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006*"}, d2 = {"Lid/go/bpsfasih/data/local/models/Village;", "", "villageActive", "", "villageCode", "", "villageDateCreated", "", "disctrict", "Lid/go/bpsfasih/data/local/models/Disctrict;", "villageFullCode", "villageId", "", "villageName", "(ZLjava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/data/local/models/Disctrict;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)V", "getDisctrict", "()Lid/go/bpsfasih/data/local/models/Disctrict;", "getVillageActive", "()Z", "getVillageCode", "()Ljava/lang/String;", "getVillageDateCreated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getVillageFullCode", "getVillageId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getVillageName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(ZLjava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/data/local/models/Disctrict;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Lid/go/bpsfasih/data/local/models/Village;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Village {
    public static final int $stable = 8;
    private final Disctrict disctrict;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private final boolean villageActive;

    @SerializedName("code")
    private final String villageCode;

    @SerializedName("dateCreated")
    private final Long villageDateCreated;

    @SerializedName("fullCode")
    private final String villageFullCode;

    @SerializedName(DownloadModel.ID)
    private final Integer villageId;

    @SerializedName("name")
    private final String villageName;

    public static /* synthetic */ Village copy$default(Village village, boolean z, String str, Long l, Disctrict disctrict, String str2, Integer num, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = village.villageActive;
        }
        if ((i & 2) != 0) {
            str = village.villageCode;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            l = village.villageDateCreated;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            disctrict = village.disctrict;
        }
        Disctrict disctrict2 = disctrict;
        if ((i & 16) != 0) {
            str2 = village.villageFullCode;
        }
        String str5 = str2;
        if ((i & 32) != 0) {
            num = village.villageId;
        }
        Integer num2 = num;
        if ((i & 64) != 0) {
            str3 = village.villageName;
        }
        return village.copy(z, str4, l2, disctrict2, str5, num2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getVillageActive() {
        return this.villageActive;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVillageCode() {
        return this.villageCode;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getVillageDateCreated() {
        return this.villageDateCreated;
    }

    /* renamed from: component4, reason: from getter */
    public final Disctrict getDisctrict() {
        return this.disctrict;
    }

    /* renamed from: component5, reason: from getter */
    public final String getVillageFullCode() {
        return this.villageFullCode;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getVillageId() {
        return this.villageId;
    }

    /* renamed from: component7, reason: from getter */
    public final String getVillageName() {
        return this.villageName;
    }

    public final Village copy(boolean villageActive, String villageCode, Long villageDateCreated, Disctrict disctrict, String villageFullCode, Integer villageId, String villageName) {
        return new Village(villageActive, villageCode, villageDateCreated, disctrict, villageFullCode, villageId, villageName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Village)) {
            return false;
        }
        Village village = (Village) other;
        return this.villageActive == village.villageActive && Intrinsics.areEqual(this.villageCode, village.villageCode) && Intrinsics.areEqual(this.villageDateCreated, village.villageDateCreated) && Intrinsics.areEqual(this.disctrict, village.disctrict) && Intrinsics.areEqual(this.villageFullCode, village.villageFullCode) && Intrinsics.areEqual(this.villageId, village.villageId) && Intrinsics.areEqual(this.villageName, village.villageName);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    public int hashCode() {
        boolean z = this.villageActive;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.villageCode;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.villageDateCreated;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Disctrict disctrict = this.disctrict;
        int iHashCode3 = (iHashCode2 + (disctrict == null ? 0 : disctrict.hashCode())) * 31;
        String str2 = this.villageFullCode;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.villageId;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.villageName;
        return iHashCode5 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "Village(villageActive=" + this.villageActive + ", villageCode=" + this.villageCode + ", villageDateCreated=" + this.villageDateCreated + ", disctrict=" + this.disctrict + ", villageFullCode=" + this.villageFullCode + ", villageId=" + this.villageId + ", villageName=" + this.villageName + ")";
    }

    public Village(boolean z, String str, Long l, Disctrict disctrict, String str2, Integer num, String str3) {
        this.villageActive = z;
        this.villageCode = str;
        this.villageDateCreated = l;
        this.disctrict = disctrict;
        this.villageFullCode = str2;
        this.villageId = num;
        this.villageName = str3;
    }

    public final boolean getVillageActive() {
        return this.villageActive;
    }

    public final String getVillageCode() {
        return this.villageCode;
    }

    public final Long getVillageDateCreated() {
        return this.villageDateCreated;
    }

    public final Disctrict getDisctrict() {
        return this.disctrict;
    }

    public final String getVillageFullCode() {
        return this.villageFullCode;
    }

    public final Integer getVillageId() {
        return this.villageId;
    }

    public final String getVillageName() {
        return this.villageName;
    }
}
