package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002BA\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u0012\b\u0002\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\fJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0002\u0010\u0018J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0013\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0006HÆ\u0003JR\u0010#\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0012\b\u0002\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010$J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(HÖ\u0003J\t\u0010)\u001a\u00020\u0004HÖ\u0001J\t\u0010*\u001a\u00020\u0006HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000e\"\u0004\b\u0012\u0010\u0010R$\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001b\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000e\"\u0004\b\u001d\u0010\u0010¨\u0006+"}, d2 = {"Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "Ljava/io/Serializable;", "()V", "levelCount", "", "smallestRegionLevel", "", "groupName", FirebaseAnalytics.Param.LEVEL, "", "Lid/go/bpsfasih/data/local/entities/LevelRegionMetadata;", DownloadModel.ID, "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)V", "getGroupName", "()Ljava/lang/String;", "setGroupName", "(Ljava/lang/String;)V", "getId", "setId", "getLevel", "()Ljava/util/List;", "setLevel", "(Ljava/util/List;)V", "getLevelCount", "()Ljava/lang/Integer;", "setLevelCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSmallestRegionLevel", "setSmallestRegionLevel", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "equals", "", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class RegionMetadata implements Serializable {
    public static final int $stable = 8;
    private String groupName;
    private String id;
    private List<LevelRegionMetadata> level;
    private Integer levelCount;
    private String smallestRegionLevel;

    public static /* synthetic */ RegionMetadata copy$default(RegionMetadata regionMetadata, Integer num, String str, String str2, List list, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            num = regionMetadata.levelCount;
        }
        if ((i & 2) != 0) {
            str = regionMetadata.smallestRegionLevel;
        }
        String str4 = str;
        if ((i & 4) != 0) {
            str2 = regionMetadata.groupName;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            list = regionMetadata.level;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            str3 = regionMetadata.id;
        }
        return regionMetadata.copy(num, str4, str5, list2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getLevelCount() {
        return this.levelCount;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSmallestRegionLevel() {
        return this.smallestRegionLevel;
    }

    /* renamed from: component3, reason: from getter */
    public final String getGroupName() {
        return this.groupName;
    }

    public final List<LevelRegionMetadata> component4() {
        return this.level;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final RegionMetadata copy(Integer levelCount, String smallestRegionLevel, String groupName, List<LevelRegionMetadata> level, String id2) {
        return new RegionMetadata(levelCount, smallestRegionLevel, groupName, level, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RegionMetadata)) {
            return false;
        }
        RegionMetadata regionMetadata = (RegionMetadata) other;
        return Intrinsics.areEqual(this.levelCount, regionMetadata.levelCount) && Intrinsics.areEqual(this.smallestRegionLevel, regionMetadata.smallestRegionLevel) && Intrinsics.areEqual(this.groupName, regionMetadata.groupName) && Intrinsics.areEqual(this.level, regionMetadata.level) && Intrinsics.areEqual(this.id, regionMetadata.id);
    }

    public int hashCode() {
        Integer num = this.levelCount;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.smallestRegionLevel;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.groupName;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        List<LevelRegionMetadata> list = this.level;
        int iHashCode4 = (iHashCode3 + (list == null ? 0 : list.hashCode())) * 31;
        String str3 = this.id;
        return iHashCode4 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "RegionMetadata(levelCount=" + this.levelCount + ", smallestRegionLevel=" + this.smallestRegionLevel + ", groupName=" + this.groupName + ", level=" + this.level + ", id=" + this.id + ")";
    }

    public RegionMetadata(Integer num, String str, String str2, List<LevelRegionMetadata> list, String str3) {
        this.levelCount = num;
        this.smallestRegionLevel = str;
        this.groupName = str2;
        this.level = list;
        this.id = str3;
    }

    public final Integer getLevelCount() {
        return this.levelCount;
    }

    public final void setLevelCount(Integer num) {
        this.levelCount = num;
    }

    public final String getSmallestRegionLevel() {
        return this.smallestRegionLevel;
    }

    public final void setSmallestRegionLevel(String str) {
        this.smallestRegionLevel = str;
    }

    public final String getGroupName() {
        return this.groupName;
    }

    public final void setGroupName(String str) {
        this.groupName = str;
    }

    public /* synthetic */ RegionMetadata(Integer num, String str, String str2, ArrayList arrayList, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, str2, (i & 8) != 0 ? new ArrayList() : arrayList, str3);
    }

    public final List<LevelRegionMetadata> getLevel() {
        return this.level;
    }

    public final void setLevel(List<LevelRegionMetadata> list) {
        this.level = list;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public RegionMetadata() {
        this(null, null, null, null, null);
    }
}
