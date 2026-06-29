package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Province.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0087\b\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010#\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0015J\t\u0010$\u001a\u00020\nHÆ\u0003JH\u0010%\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001¢\u0006\u0002\u0010&J\u0013\u0010'\u001a\u00020\n2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\u0003HÖ\u0001J\t\u0010*\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR \u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0011\"\u0004\b\u001f\u0010\u0013¨\u0006+"}, d2 = {"Lid/go/bpsfasih/domain/models/Province;", "", "provinceId", "", "provinceName", "", "provinceCode", "provinceDateCreated", "", "provinceActive", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Z)V", "getProvinceActive", "()Z", "setProvinceActive", "(Z)V", "getProvinceCode", "()Ljava/lang/String;", "setProvinceCode", "(Ljava/lang/String;)V", "getProvinceDateCreated", "()Ljava/lang/Long;", "setProvinceDateCreated", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getProvinceId", "()Ljava/lang/Integer;", "setProvinceId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getProvinceName", "setProvinceName", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Z)Lid/go/bpsfasih/domain/models/Province;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Province {
    public static final int $stable = 8;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean provinceActive;

    @SerializedName("code")
    private String provinceCode;

    @SerializedName("dateCreated")
    private Long provinceDateCreated;

    @SerializedName(DownloadModel.ID)
    private Integer provinceId;

    @SerializedName("name")
    private String provinceName;

    public static /* synthetic */ Province copy$default(Province province, Integer num, String str, String str2, Long l, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = province.provinceId;
        }
        if ((i & 2) != 0) {
            str = province.provinceName;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            str2 = province.provinceCode;
        }
        String str4 = str2;
        if ((i & 8) != 0) {
            l = province.provinceDateCreated;
        }
        Long l2 = l;
        if ((i & 16) != 0) {
            z = province.provinceActive;
        }
        return province.copy(num, str3, str4, l2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getProvinceId() {
        return this.provinceId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getProvinceName() {
        return this.provinceName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getProvinceCode() {
        return this.provinceCode;
    }

    /* renamed from: component4, reason: from getter */
    public final Long getProvinceDateCreated() {
        return this.provinceDateCreated;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getProvinceActive() {
        return this.provinceActive;
    }

    public final Province copy(Integer provinceId, String provinceName, String provinceCode, Long provinceDateCreated, boolean provinceActive) {
        return new Province(provinceId, provinceName, provinceCode, provinceDateCreated, provinceActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Province)) {
            return false;
        }
        Province province = (Province) other;
        return Intrinsics.areEqual(this.provinceId, province.provinceId) && Intrinsics.areEqual(this.provinceName, province.provinceName) && Intrinsics.areEqual(this.provinceCode, province.provinceCode) && Intrinsics.areEqual(this.provinceDateCreated, province.provinceDateCreated) && this.provinceActive == province.provinceActive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.provinceId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.provinceName;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.provinceCode;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Long l = this.provinceDateCreated;
        int iHashCode4 = (iHashCode3 + (l != null ? l.hashCode() : 0)) * 31;
        boolean z = this.provinceActive;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode4 + i;
    }

    public String toString() {
        return "Province(provinceId=" + this.provinceId + ", provinceName=" + this.provinceName + ", provinceCode=" + this.provinceCode + ", provinceDateCreated=" + this.provinceDateCreated + ", provinceActive=" + this.provinceActive + ")";
    }

    public Province(Integer num, String str, String str2, Long l, boolean z) {
        this.provinceId = num;
        this.provinceName = str;
        this.provinceCode = str2;
        this.provinceDateCreated = l;
        this.provinceActive = z;
    }

    public final Integer getProvinceId() {
        return this.provinceId;
    }

    public final void setProvinceId(Integer num) {
        this.provinceId = num;
    }

    public final String getProvinceName() {
        return this.provinceName;
    }

    public final void setProvinceName(String str) {
        this.provinceName = str;
    }

    public final String getProvinceCode() {
        return this.provinceCode;
    }

    public final void setProvinceCode(String str) {
        this.provinceCode = str;
    }

    public final Long getProvinceDateCreated() {
        return this.provinceDateCreated;
    }

    public final void setProvinceDateCreated(Long l) {
        this.provinceDateCreated = l;
    }

    public final boolean getProvinceActive() {
        return this.provinceActive;
    }

    public final void setProvinceActive(boolean z) {
        this.provinceActive = z;
    }
}
