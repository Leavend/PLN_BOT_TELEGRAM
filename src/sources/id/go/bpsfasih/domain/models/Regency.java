package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Regency.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u001c\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010 \u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0011J\u0010\u0010!\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0019JV\u0010\"\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\u00020\n2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\fHÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014¨\u0006("}, d2 = {"Lid/go/bpsfasih/domain/models/Regency;", "", "regCode", "", "regDateCreated", "", "province", "Lid/go/bpsfasih/domain/models/Province;", "regName", "regActive", "", "regId", "", "(Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "getProvince", "()Lid/go/bpsfasih/domain/models/Province;", "getRegActive", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getRegCode", "()Ljava/lang/String;", "getRegDateCreated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRegId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getRegName", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;)Lid/go/bpsfasih/domain/models/Regency;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Regency {
    public static final int $stable = 8;
    private final Province province;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private final Boolean regActive;

    @SerializedName("code")
    private final String regCode;

    @SerializedName("dateCreated")
    private final Long regDateCreated;

    @SerializedName(DownloadModel.ID)
    private final Integer regId;

    @SerializedName("name")
    private final String regName;

    public Regency() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ Regency copy$default(Regency regency, String str, Long l, Province province, String str2, Boolean bool, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = regency.regCode;
        }
        if ((i & 2) != 0) {
            l = regency.regDateCreated;
        }
        Long l2 = l;
        if ((i & 4) != 0) {
            province = regency.province;
        }
        Province province2 = province;
        if ((i & 8) != 0) {
            str2 = regency.regName;
        }
        String str3 = str2;
        if ((i & 16) != 0) {
            bool = regency.regActive;
        }
        Boolean bool2 = bool;
        if ((i & 32) != 0) {
            num = regency.regId;
        }
        return regency.copy(str, l2, province2, str3, bool2, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRegCode() {
        return this.regCode;
    }

    /* renamed from: component2, reason: from getter */
    public final Long getRegDateCreated() {
        return this.regDateCreated;
    }

    /* renamed from: component3, reason: from getter */
    public final Province getProvince() {
        return this.province;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRegName() {
        return this.regName;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getRegActive() {
        return this.regActive;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getRegId() {
        return this.regId;
    }

    public final Regency copy(String regCode, Long regDateCreated, Province province, String regName, Boolean regActive, Integer regId) {
        return new Regency(regCode, regDateCreated, province, regName, regActive, regId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Regency)) {
            return false;
        }
        Regency regency = (Regency) other;
        return Intrinsics.areEqual(this.regCode, regency.regCode) && Intrinsics.areEqual(this.regDateCreated, regency.regDateCreated) && Intrinsics.areEqual(this.province, regency.province) && Intrinsics.areEqual(this.regName, regency.regName) && Intrinsics.areEqual(this.regActive, regency.regActive) && Intrinsics.areEqual(this.regId, regency.regId);
    }

    public int hashCode() {
        String str = this.regCode;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Long l = this.regDateCreated;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Province province = this.province;
        int iHashCode3 = (iHashCode2 + (province == null ? 0 : province.hashCode())) * 31;
        String str2 = this.regName;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.regActive;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num = this.regId;
        return iHashCode5 + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "Regency(regCode=" + this.regCode + ", regDateCreated=" + this.regDateCreated + ", province=" + this.province + ", regName=" + this.regName + ", regActive=" + this.regActive + ", regId=" + this.regId + ")";
    }

    public Regency(String str, Long l, Province province, String str2, Boolean bool, Integer num) {
        this.regCode = str;
        this.regDateCreated = l;
        this.province = province;
        this.regName = str2;
        this.regActive = bool;
        this.regId = num;
    }

    public /* synthetic */ Regency(String str, Long l, Province province, String str2, Boolean bool, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : l, (i & 4) != 0 ? null : province, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? null : num);
    }

    public final String getRegCode() {
        return this.regCode;
    }

    public final Long getRegDateCreated() {
        return this.regDateCreated;
    }

    public final Province getProvince() {
        return this.province;
    }

    public final String getRegName() {
        return this.regName;
    }

    public final Boolean getRegActive() {
        return this.regActive;
    }

    public final Integer getRegId() {
        return this.regId;
    }
}
