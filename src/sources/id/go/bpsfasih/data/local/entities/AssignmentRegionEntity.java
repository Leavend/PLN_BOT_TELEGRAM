package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import id.go.bpsfasih.FasihApp;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentRegionEntity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b,\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 A2\u00020\u0001:\u0001AB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u007f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013J\t\u0010.\u001a\u00020\u0004HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u00102\u001a\u00020\u0004HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00108\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u0010\u00109\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010\u0017J\u009a\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÆ\u0001¢\u0006\u0002\u0010;J\u0013\u0010<\u001a\u00020\u000b2\b\u0010=\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010>\u001a\u00020?HÖ\u0001J\t\u0010@\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0019\u0010\u0017R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u001a\u0010\u0017R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0015R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0015R\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0015R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0015\"\u0004\b)\u0010*R \u0010+\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0015\"\u0004\b-\u0010*¨\u0006B"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "", "()V", "_id", "", "userId", "region_id", "region_group_id", "smallest_region_full_code", "survey_period_id", "done_listing", "", "done_tarik_sample", "done_tarik_sample_offline", "stateDataTable", "region", "Lid/go/bpsfasih/data/local/entities/Region;", "regionMetadata", "Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Region;Lid/go/bpsfasih/data/local/entities/RegionMetadata;)V", "get_id", "()Ljava/lang/String;", "getDone_listing", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getDone_tarik_sample", "getDone_tarik_sample_offline", "getRegion", "()Lid/go/bpsfasih/data/local/entities/Region;", "setRegion", "(Lid/go/bpsfasih/data/local/entities/Region;)V", "getRegionMetadata", "()Lid/go/bpsfasih/data/local/entities/RegionMetadata;", "setRegionMetadata", "(Lid/go/bpsfasih/data/local/entities/RegionMetadata;)V", "getRegion_group_id", "getRegion_id", "getSmallest_region_full_code", "getStateDataTable", "getSurvey_period_id", "getUserId", "setUserId", "(Ljava/lang/String;)V", "wrappedDatakey", "getWrappedDatakey", "setWrappedDatakey", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Region;Lid/go/bpsfasih/data/local/entities/RegionMetadata;)Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "equals", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class AssignmentRegionEntity {
    private final String _id;
    private final Boolean done_listing;
    private final Boolean done_tarik_sample;
    private final Boolean done_tarik_sample_offline;
    private Region region;
    private RegionMetadata regionMetadata;
    private final String region_group_id;
    private final String region_id;
    private final String smallest_region_full_code;
    private final String stateDataTable;
    private final String survey_period_id;
    private String userId;

    @SerializedName("wrappedDatakey")
    private String wrappedDatakey;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String get_id() {
        return this._id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getStateDataTable() {
        return this.stateDataTable;
    }

    /* renamed from: component11, reason: from getter */
    public final Region getRegion() {
        return this.region;
    }

    /* renamed from: component12, reason: from getter */
    public final RegionMetadata getRegionMetadata() {
        return this.regionMetadata;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRegion_id() {
        return this.region_id;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRegion_group_id() {
        return this.region_group_id;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSmallest_region_full_code() {
        return this.smallest_region_full_code;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSurvey_period_id() {
        return this.survey_period_id;
    }

    /* renamed from: component7, reason: from getter */
    public final Boolean getDone_listing() {
        return this.done_listing;
    }

    /* renamed from: component8, reason: from getter */
    public final Boolean getDone_tarik_sample() {
        return this.done_tarik_sample;
    }

    /* renamed from: component9, reason: from getter */
    public final Boolean getDone_tarik_sample_offline() {
        return this.done_tarik_sample_offline;
    }

    public final AssignmentRegionEntity copy(String _id, String userId, String region_id, String region_group_id, String smallest_region_full_code, String survey_period_id, Boolean done_listing, Boolean done_tarik_sample, Boolean done_tarik_sample_offline, String stateDataTable, Region region, RegionMetadata regionMetadata) {
        Intrinsics.checkNotNullParameter(_id, "_id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new AssignmentRegionEntity(_id, userId, region_id, region_group_id, smallest_region_full_code, survey_period_id, done_listing, done_tarik_sample, done_tarik_sample_offline, stateDataTable, region, regionMetadata);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AssignmentRegionEntity)) {
            return false;
        }
        AssignmentRegionEntity assignmentRegionEntity = (AssignmentRegionEntity) other;
        return Intrinsics.areEqual(this._id, assignmentRegionEntity._id) && Intrinsics.areEqual(this.userId, assignmentRegionEntity.userId) && Intrinsics.areEqual(this.region_id, assignmentRegionEntity.region_id) && Intrinsics.areEqual(this.region_group_id, assignmentRegionEntity.region_group_id) && Intrinsics.areEqual(this.smallest_region_full_code, assignmentRegionEntity.smallest_region_full_code) && Intrinsics.areEqual(this.survey_period_id, assignmentRegionEntity.survey_period_id) && Intrinsics.areEqual(this.done_listing, assignmentRegionEntity.done_listing) && Intrinsics.areEqual(this.done_tarik_sample, assignmentRegionEntity.done_tarik_sample) && Intrinsics.areEqual(this.done_tarik_sample_offline, assignmentRegionEntity.done_tarik_sample_offline) && Intrinsics.areEqual(this.stateDataTable, assignmentRegionEntity.stateDataTable) && Intrinsics.areEqual(this.region, assignmentRegionEntity.region) && Intrinsics.areEqual(this.regionMetadata, assignmentRegionEntity.regionMetadata);
    }

    public int hashCode() {
        int iHashCode = ((this._id.hashCode() * 31) + this.userId.hashCode()) * 31;
        String str = this.region_id;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.region_group_id;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.smallest_region_full_code;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.survey_period_id;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Boolean bool = this.done_listing;
        int iHashCode6 = (iHashCode5 + (bool == null ? 0 : bool.hashCode())) * 31;
        Boolean bool2 = this.done_tarik_sample;
        int iHashCode7 = (iHashCode6 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.done_tarik_sample_offline;
        int iHashCode8 = (iHashCode7 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        String str5 = this.stateDataTable;
        int iHashCode9 = (iHashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Region region = this.region;
        int iHashCode10 = (iHashCode9 + (region == null ? 0 : region.hashCode())) * 31;
        RegionMetadata regionMetadata = this.regionMetadata;
        return iHashCode10 + (regionMetadata != null ? regionMetadata.hashCode() : 0);
    }

    public String toString() {
        return "AssignmentRegionEntity(_id=" + this._id + ", userId=" + this.userId + ", region_id=" + this.region_id + ", region_group_id=" + this.region_group_id + ", smallest_region_full_code=" + this.smallest_region_full_code + ", survey_period_id=" + this.survey_period_id + ", done_listing=" + this.done_listing + ", done_tarik_sample=" + this.done_tarik_sample + ", done_tarik_sample_offline=" + this.done_tarik_sample_offline + ", stateDataTable=" + this.stateDataTable + ", region=" + this.region + ", regionMetadata=" + this.regionMetadata + ")";
    }

    public AssignmentRegionEntity(String _id, String userId, String str, String str2, String str3, String str4, Boolean bool, Boolean bool2, Boolean bool3, String str5, Region region, RegionMetadata regionMetadata) {
        Intrinsics.checkNotNullParameter(_id, "_id");
        Intrinsics.checkNotNullParameter(userId, "userId");
        this._id = _id;
        this.userId = userId;
        this.region_id = str;
        this.region_group_id = str2;
        this.smallest_region_full_code = str3;
        this.survey_period_id = str4;
        this.done_listing = bool;
        this.done_tarik_sample = bool2;
        this.done_tarik_sample_offline = bool3;
        this.stateDataTable = str5;
        this.region = region;
        this.regionMetadata = regionMetadata;
    }

    public final String get_id() {
        return this._id;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userId = str;
    }

    public final String getRegion_id() {
        return this.region_id;
    }

    public final String getRegion_group_id() {
        return this.region_group_id;
    }

    public final String getSmallest_region_full_code() {
        return this.smallest_region_full_code;
    }

    public final String getSurvey_period_id() {
        return this.survey_period_id;
    }

    public final Boolean getDone_listing() {
        return this.done_listing;
    }

    public final Boolean getDone_tarik_sample() {
        return this.done_tarik_sample;
    }

    public /* synthetic */ AssignmentRegionEntity(String str, String str2, String str3, String str4, String str5, String str6, Boolean bool, Boolean bool2, Boolean bool3, String str7, Region region, RegionMetadata regionMetadata, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, bool, bool2, (i & 256) != 0 ? false : bool3, str7, (i & 1024) != 0 ? null : region, (i & 2048) != 0 ? null : regionMetadata);
    }

    public final Boolean getDone_tarik_sample_offline() {
        return this.done_tarik_sample_offline;
    }

    public final String getStateDataTable() {
        return this.stateDataTable;
    }

    public final Region getRegion() {
        return this.region;
    }

    public final void setRegion(Region region) {
        this.region = region;
    }

    public final RegionMetadata getRegionMetadata() {
        return this.regionMetadata;
    }

    public final void setRegionMetadata(RegionMetadata regionMetadata) {
        this.regionMetadata = regionMetadata;
    }

    public final String getWrappedDatakey() {
        return this.wrappedDatakey;
    }

    public final void setWrappedDatakey(String str) {
        this.wrappedDatakey = str;
    }

    public AssignmentRegionEntity() {
        this("", "", "", "", "", "", false, false, false, null, null, null);
    }

    /* compiled from: AssignmentRegionEntity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity$Companion;", "", "()V", "mapIdToAssignmentRegion", "", "Lid/go/bpsfasih/data/local/entities/AssignmentRegionEntity;", "assignmentRegionEntity", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<AssignmentRegionEntity> mapIdToAssignmentRegion(List<AssignmentRegionEntity> assignmentRegionEntity) {
            Intrinsics.checkNotNullParameter(assignmentRegionEntity, "assignmentRegionEntity");
            Iterator<T> it = assignmentRegionEntity.iterator();
            while (it.hasNext()) {
                ((AssignmentRegionEntity) it.next()).setUserId(FasihApp.INSTANCE.getSession().getUserId().toString());
            }
            return assignmentRegionEntity;
        }
    }
}
