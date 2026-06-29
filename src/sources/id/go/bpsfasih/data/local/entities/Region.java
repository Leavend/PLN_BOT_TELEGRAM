package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BA\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0015J\u000b\u0010)\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010*\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010+\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u0010\u0010-\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010$JV\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u00020\b2\b\u00101\u001a\u0004\u0018\u000102HÖ\u0003J\t\u00103\u001a\u000204HÖ\u0001J\t\u00105\u001a\u00020\u0006HÖ\u0001R\u001e\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001a\"\u0004\b\u001e\u0010\u001cR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&¨\u00066"}, d2 = {"Lid/go/bpsfasih/data/local/entities/Region;", "Ljava/io/Serializable;", "()V", "dateCreated", "", "groupId", "", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "", DownloadModel.ID, "level1", "Lid/go/bpsfasih/data/local/entities/Level1;", "versionCode", "", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Level1;Ljava/lang/Double;)V", "getActive", "()Ljava/lang/Boolean;", "setActive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getDateCreated", "()Ljava/lang/Long;", "setDateCreated", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getGroupId", "()Ljava/lang/String;", "setGroupId", "(Ljava/lang/String;)V", "getId", "setId", "getLevel1", "()Lid/go/bpsfasih/data/local/entities/Level1;", "setLevel1", "(Lid/go/bpsfasih/data/local/entities/Level1;)V", "getVersionCode", "()Ljava/lang/Double;", "setVersionCode", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Level1;Ljava/lang/Double;)Lid/go/bpsfasih/data/local/entities/Region;", "equals", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Region implements Serializable {
    public static final int $stable = 8;
    private Boolean active;
    private Long dateCreated;
    private String groupId;
    private String id;
    private Level1 level1;
    private Double versionCode;

    public static /* synthetic */ Region copy$default(Region region, Long l, String str, Boolean bool, String str2, Level1 level1, Double d, int i, Object obj) {
        if ((i & 1) != 0) {
            l = region.dateCreated;
        }
        if ((i & 2) != 0) {
            str = region.groupId;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            bool = region.active;
        }
        Boolean bool2 = bool;
        if ((i & 8) != 0) {
            str2 = region.id;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            level1 = region.level1;
        }
        Level1 level12 = level1;
        if ((i & 32) != 0) {
            d = region.versionCode;
        }
        return region.copy(l, str3, bool2, str4, level12, d);
    }

    /* renamed from: component1, reason: from getter */
    public final Long getDateCreated() {
        return this.dateCreated;
    }

    /* renamed from: component2, reason: from getter */
    public final String getGroupId() {
        return this.groupId;
    }

    /* renamed from: component3, reason: from getter */
    public final Boolean getActive() {
        return this.active;
    }

    /* renamed from: component4, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component5, reason: from getter */
    public final Level1 getLevel1() {
        return this.level1;
    }

    /* renamed from: component6, reason: from getter */
    public final Double getVersionCode() {
        return this.versionCode;
    }

    public final Region copy(Long dateCreated, String groupId, Boolean active, String id2, Level1 level1, Double versionCode) {
        return new Region(dateCreated, groupId, active, id2, level1, versionCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Region)) {
            return false;
        }
        Region region = (Region) other;
        return Intrinsics.areEqual(this.dateCreated, region.dateCreated) && Intrinsics.areEqual(this.groupId, region.groupId) && Intrinsics.areEqual(this.active, region.active) && Intrinsics.areEqual(this.id, region.id) && Intrinsics.areEqual(this.level1, region.level1) && Intrinsics.areEqual((Object) this.versionCode, (Object) region.versionCode);
    }

    public int hashCode() {
        Long l = this.dateCreated;
        int iHashCode = (l == null ? 0 : l.hashCode()) * 31;
        String str = this.groupId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Boolean bool = this.active;
        int iHashCode3 = (iHashCode2 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str2 = this.id;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Level1 level1 = this.level1;
        int iHashCode5 = (iHashCode4 + (level1 == null ? 0 : level1.hashCode())) * 31;
        Double d = this.versionCode;
        return iHashCode5 + (d != null ? d.hashCode() : 0);
    }

    public String toString() {
        return "Region(dateCreated=" + this.dateCreated + ", groupId=" + this.groupId + ", active=" + this.active + ", id=" + this.id + ", level1=" + this.level1 + ", versionCode=" + this.versionCode + ")";
    }

    public Region(Long l, String str, Boolean bool, String str2, Level1 level1, Double d) {
        this.dateCreated = l;
        this.groupId = str;
        this.active = bool;
        this.id = str2;
        this.level1 = level1;
        this.versionCode = d;
    }

    public final Long getDateCreated() {
        return this.dateCreated;
    }

    public final void setDateCreated(Long l) {
        this.dateCreated = l;
    }

    public final String getGroupId() {
        return this.groupId;
    }

    public final void setGroupId(String str) {
        this.groupId = str;
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final void setActive(Boolean bool) {
        this.active = bool;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final Level1 getLevel1() {
        return this.level1;
    }

    public final void setLevel1(Level1 level1) {
        this.level1 = level1;
    }

    public final Double getVersionCode() {
        return this.versionCode;
    }

    public final void setVersionCode(Double d) {
        this.versionCode = d;
    }

    public Region() {
        this(null, null, null, null, null, null);
    }
}
