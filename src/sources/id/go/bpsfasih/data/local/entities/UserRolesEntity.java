package id.go.bpsfasih.data.local.entities;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserRolesEntity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b;\b\u0087\b\u0018\u00002\u00020\u0001B»\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010)J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010?\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010)J\u0010\u0010@\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0019\u0010D\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\tHÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003JÄ\u0001\u0010I\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0018\b\u0002\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÆ\u0001¢\u0006\u0002\u0010JJ\u0013\u0010K\u001a\u00020\u00142\b\u0010L\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010M\u001a\u00020\u000fHÖ\u0001J\t\u0010N\u001a\u00020\u0003HÖ\u0001R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0010\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R \u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\"\u0010\u0013\u001a\u0004\u0018\u00010\u00148\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u0013\u0010 \"\u0004\b!\u0010\"R.\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\u00030\bj\b\u0012\u0004\u0012\u00020\u0003`\t8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\"\u0010\u000e\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010\u0012\u001a\u0004\u0018\u00010\u000f8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010,\u001a\u0004\b-\u0010)\"\u0004\b.\u0010+R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0017\"\u0004\b2\u0010\u0019R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0017\"\u0004\b4\u0010\u0019R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0017\"\u0004\b6\u0010\u0019R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0017\"\u0004\b8\u0010\u0019R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0017\"\u0004\b:\u0010\u0019¨\u0006O"}, d2 = {"Lid/go/bpsfasih/data/local/entities/UserRolesEntity;", "", DownloadModel.ID, "", "userId", "surveyRoleId", "surveyPeriodId", "regionId", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "createdAt", "updatedAt", HintConstants.AUTOFILL_HINT_USERNAME, "fullname", "sequence", "", "description", "surveyRoleGroupId", "smallestAreaOrder", "isPencacah", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "getCreatedAt", "()Ljava/lang/String;", "setCreatedAt", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getFullname", "setFullname", "getId", "setId", "()Ljava/lang/Boolean;", "setPencacah", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getRegionId", "()Ljava/util/ArrayList;", "setRegionId", "(Ljava/util/ArrayList;)V", "getSequence", "()Ljava/lang/Integer;", "setSequence", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSmallestAreaOrder", "setSmallestAreaOrder", "getSurveyPeriodId", "setSurveyPeriodId", "getSurveyRoleGroupId", "setSurveyRoleGroupId", "getSurveyRoleId", "setSurveyRoleId", "getUpdatedAt", "setUpdatedAt", "getUserId", "setUserId", "getUsername", "setUsername", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;)Lid/go/bpsfasih/data/local/entities/UserRolesEntity;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UserRolesEntity {
    public static final int $stable = 8;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("description")
    private String description;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName(DownloadModel.ID)
    private String id;

    @SerializedName("isPencacah")
    private Boolean isPencacah;

    @SerializedName("regionId")
    private ArrayList<String> regionId;

    @SerializedName("sequence")
    private Integer sequence;

    @SerializedName("smallestAreaOrder")
    private Integer smallestAreaOrder;

    @SerializedName("surveyPeriodId")
    private String surveyPeriodId;

    @SerializedName("surveyRoleGroupId")
    private String surveyRoleGroupId;

    @SerializedName("surveyRoleId")
    private String surveyRoleId;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("userId")
    private String userId;

    @SerializedName(HintConstants.AUTOFILL_HINT_USERNAME)
    private String username;

    public UserRolesEntity() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, 16383, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getSequence() {
        return this.sequence;
    }

    /* renamed from: component11, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSurveyRoleGroupId() {
        return this.surveyRoleGroupId;
    }

    /* renamed from: component13, reason: from getter */
    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    /* renamed from: component14, reason: from getter */
    public final Boolean getIsPencacah() {
        return this.isPencacah;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUserId() {
        return this.userId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSurveyRoleId() {
        return this.surveyRoleId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final ArrayList<String> component5() {
        return this.regionId;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component7, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component8, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* renamed from: component9, reason: from getter */
    public final String getFullname() {
        return this.fullname;
    }

    public final UserRolesEntity copy(String id2, String userId, String surveyRoleId, String surveyPeriodId, ArrayList<String> regionId, String createdAt, String updatedAt, String username, String fullname, Integer sequence, String description, String surveyRoleGroupId, Integer smallestAreaOrder, Boolean isPencacah) {
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        return new UserRolesEntity(id2, userId, surveyRoleId, surveyPeriodId, regionId, createdAt, updatedAt, username, fullname, sequence, description, surveyRoleGroupId, smallestAreaOrder, isPencacah);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserRolesEntity)) {
            return false;
        }
        UserRolesEntity userRolesEntity = (UserRolesEntity) other;
        return Intrinsics.areEqual(this.id, userRolesEntity.id) && Intrinsics.areEqual(this.userId, userRolesEntity.userId) && Intrinsics.areEqual(this.surveyRoleId, userRolesEntity.surveyRoleId) && Intrinsics.areEqual(this.surveyPeriodId, userRolesEntity.surveyPeriodId) && Intrinsics.areEqual(this.regionId, userRolesEntity.regionId) && Intrinsics.areEqual(this.createdAt, userRolesEntity.createdAt) && Intrinsics.areEqual(this.updatedAt, userRolesEntity.updatedAt) && Intrinsics.areEqual(this.username, userRolesEntity.username) && Intrinsics.areEqual(this.fullname, userRolesEntity.fullname) && Intrinsics.areEqual(this.sequence, userRolesEntity.sequence) && Intrinsics.areEqual(this.description, userRolesEntity.description) && Intrinsics.areEqual(this.surveyRoleGroupId, userRolesEntity.surveyRoleGroupId) && Intrinsics.areEqual(this.smallestAreaOrder, userRolesEntity.smallestAreaOrder) && Intrinsics.areEqual(this.isPencacah, userRolesEntity.isPencacah);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.userId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveyRoleId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.surveyPeriodId;
        int iHashCode4 = (((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.regionId.hashCode()) * 31;
        String str5 = this.createdAt;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.updatedAt;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.username;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.fullname;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Integer num = this.sequence;
        int iHashCode9 = (iHashCode8 + (num == null ? 0 : num.hashCode())) * 31;
        String str9 = this.description;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.surveyRoleGroupId;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        Integer num2 = this.smallestAreaOrder;
        int iHashCode12 = (iHashCode11 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Boolean bool = this.isPencacah;
        return iHashCode12 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "UserRolesEntity(id=" + this.id + ", userId=" + this.userId + ", surveyRoleId=" + this.surveyRoleId + ", surveyPeriodId=" + this.surveyPeriodId + ", regionId=" + this.regionId + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", username=" + this.username + ", fullname=" + this.fullname + ", sequence=" + this.sequence + ", description=" + this.description + ", surveyRoleGroupId=" + this.surveyRoleGroupId + ", smallestAreaOrder=" + this.smallestAreaOrder + ", isPencacah=" + this.isPencacah + ")";
    }

    public UserRolesEntity(String str, String str2, String str3, String str4, ArrayList<String> regionId, String str5, String str6, String str7, String str8, Integer num, String str9, String str10, Integer num2, Boolean bool) {
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        this.id = str;
        this.userId = str2;
        this.surveyRoleId = str3;
        this.surveyPeriodId = str4;
        this.regionId = regionId;
        this.createdAt = str5;
        this.updatedAt = str6;
        this.username = str7;
        this.fullname = str8;
        this.sequence = num;
        this.description = str9;
        this.surveyRoleGroupId = str10;
        this.smallestAreaOrder = num2;
        this.isPencacah = bool;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final String getSurveyRoleId() {
        return this.surveyRoleId;
    }

    public final void setSurveyRoleId(String str) {
        this.surveyRoleId = str;
    }

    public final String getSurveyPeriodId() {
        return this.surveyPeriodId;
    }

    public final void setSurveyPeriodId(String str) {
        this.surveyPeriodId = str;
    }

    public /* synthetic */ UserRolesEntity(String str, String str2, String str3, String str4, ArrayList arrayList, String str5, String str6, String str7, String str8, Integer num, String str9, String str10, Integer num2, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? new ArrayList() : arrayList, (i & 32) != 0 ? null : str5, (i & 64) != 0 ? null : str6, (i & 128) != 0 ? null : str7, (i & 256) != 0 ? null : str8, (i & 512) != 0 ? null : num, (i & 1024) != 0 ? null : str9, (i & 2048) != 0 ? null : str10, (i & 4096) != 0 ? null : num2, (i & 8192) == 0 ? bool : null);
    }

    public final ArrayList<String> getRegionId() {
        return this.regionId;
    }

    public final void setRegionId(ArrayList<String> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.regionId = arrayList;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(String str) {
        this.username = str;
    }

    public final String getFullname() {
        return this.fullname;
    }

    public final void setFullname(String str) {
        this.fullname = str;
    }

    public final Integer getSequence() {
        return this.sequence;
    }

    public final void setSequence(Integer num) {
        this.sequence = num;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void setDescription(String str) {
        this.description = str;
    }

    public final String getSurveyRoleGroupId() {
        return this.surveyRoleGroupId;
    }

    public final void setSurveyRoleGroupId(String str) {
        this.surveyRoleGroupId = str;
    }

    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    public final void setSmallestAreaOrder(Integer num) {
        this.smallestAreaOrder = num;
    }

    public final Boolean isPencacah() {
        return this.isPencacah;
    }

    public final void setPencacah(Boolean bool) {
        this.isPencacah = bool;
    }
}
