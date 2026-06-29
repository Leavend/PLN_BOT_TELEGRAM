package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllSurveyModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\bE\b\u0087\b\u0018\u00002\u00020\u0001B±\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0014J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010-J\u0010\u0010;\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010<\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010=\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010B\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010C\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010-J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016J\u0010\u0010G\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0016JÂ\u0001\u0010H\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010IJ\u0013\u0010J\u001a\u00020\u00072\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020\tHÖ\u0001J\t\u0010M\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001c\u0010\u0016\"\u0004\b\u001d\u0010\u0018R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u001e\u0010\u0016\"\u0004\b\u001f\u0010\u0018R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001e\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\f\u0010\u0016\"\u0004\b(\u0010\u0018R\u001e\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\r\u0010\u0016\"\u0004\b)\u0010\u0018R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010!\"\u0004\b+\u0010#R\u001e\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001e\u0010\u000e\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010!\"\u0004\b4\u0010#R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010!\"\u0004\b6\u0010#R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010!\"\u0004\b8\u0010#¨\u0006N"}, d2 = {"Lid/go/bpsfasih/data/local/models/UserRole;", "", DownloadModel.ID, "", "name", "surveyId", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "", "sequence", "", "description", "surveyRoleGroupId", "isApproving", "isPencacah", "smallestAreaOrder", "canChangeMode", "canDrawSample", "canEdit", "createdAt", "updatedAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getActive", "()Ljava/lang/Boolean;", "setActive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCanChangeMode", "setCanChangeMode", "getCanDrawSample", "setCanDrawSample", "getCanEdit", "setCanEdit", "getCreatedAt", "()Ljava/lang/String;", "setCreatedAt", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getId", "setId", "setApproving", "setPencacah", "getName", "setName", "getSequence", "()Ljava/lang/Integer;", "setSequence", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSmallestAreaOrder", "setSmallestAreaOrder", "getSurveyId", "setSurveyId", "getSurveyRoleGroupId", "setSurveyRoleGroupId", "getUpdatedAt", "setUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/data/local/models/UserRole;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UserRole {
    public static final int $stable = 8;
    private Boolean active;
    private Boolean canChangeMode;
    private Boolean canDrawSample;
    private Boolean canEdit;
    private String createdAt;
    private String description;
    private String id;
    private Boolean isApproving;
    private Boolean isPencacah;
    private String name;
    private Integer sequence;
    private Integer smallestAreaOrder;
    private String surveyId;
    private String surveyRoleGroupId;
    private String updatedAt;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    /* renamed from: component11, reason: from getter */
    public final Boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    /* renamed from: component12, reason: from getter */
    public final Boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    /* renamed from: component13, reason: from getter */
    public final Boolean getCanEdit() {
        return this.canEdit;
    }

    /* renamed from: component14, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component15, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component4, reason: from getter */
    public final Boolean getActive() {
        return this.active;
    }

    /* renamed from: component5, reason: from getter */
    public final Integer getSequence() {
        return this.sequence;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSurveyRoleGroupId() {
        return this.surveyRoleGroupId;
    }

    /* renamed from: component8, reason: from getter */
    public final Boolean getIsApproving() {
        return this.isApproving;
    }

    /* renamed from: component9, reason: from getter */
    public final Boolean getIsPencacah() {
        return this.isPencacah;
    }

    public final UserRole copy(String id2, String name, String surveyId, Boolean active, Integer sequence, String description, String surveyRoleGroupId, Boolean isApproving, Boolean isPencacah, Integer smallestAreaOrder, Boolean canChangeMode, Boolean canDrawSample, Boolean canEdit, String createdAt, String updatedAt) {
        return new UserRole(id2, name, surveyId, active, sequence, description, surveyRoleGroupId, isApproving, isPencacah, smallestAreaOrder, canChangeMode, canDrawSample, canEdit, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserRole)) {
            return false;
        }
        UserRole userRole = (UserRole) other;
        return Intrinsics.areEqual(this.id, userRole.id) && Intrinsics.areEqual(this.name, userRole.name) && Intrinsics.areEqual(this.surveyId, userRole.surveyId) && Intrinsics.areEqual(this.active, userRole.active) && Intrinsics.areEqual(this.sequence, userRole.sequence) && Intrinsics.areEqual(this.description, userRole.description) && Intrinsics.areEqual(this.surveyRoleGroupId, userRole.surveyRoleGroupId) && Intrinsics.areEqual(this.isApproving, userRole.isApproving) && Intrinsics.areEqual(this.isPencacah, userRole.isPencacah) && Intrinsics.areEqual(this.smallestAreaOrder, userRole.smallestAreaOrder) && Intrinsics.areEqual(this.canChangeMode, userRole.canChangeMode) && Intrinsics.areEqual(this.canDrawSample, userRole.canDrawSample) && Intrinsics.areEqual(this.canEdit, userRole.canEdit) && Intrinsics.areEqual(this.createdAt, userRole.createdAt) && Intrinsics.areEqual(this.updatedAt, userRole.updatedAt);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveyId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.active;
        int iHashCode4 = (iHashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num = this.sequence;
        int iHashCode5 = (iHashCode4 + (num == null ? 0 : num.hashCode())) * 31;
        String str4 = this.description;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.surveyRoleGroupId;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool2 = this.isApproving;
        int iHashCode8 = (iHashCode7 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.isPencacah;
        int iHashCode9 = (iHashCode8 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Integer num2 = this.smallestAreaOrder;
        int iHashCode10 = (iHashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Boolean bool4 = this.canChangeMode;
        int iHashCode11 = (iHashCode10 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.canDrawSample;
        int iHashCode12 = (iHashCode11 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Boolean bool6 = this.canEdit;
        int iHashCode13 = (iHashCode12 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        String str6 = this.createdAt;
        int iHashCode14 = (iHashCode13 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.updatedAt;
        return iHashCode14 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "UserRole(id=" + this.id + ", name=" + this.name + ", surveyId=" + this.surveyId + ", active=" + this.active + ", sequence=" + this.sequence + ", description=" + this.description + ", surveyRoleGroupId=" + this.surveyRoleGroupId + ", isApproving=" + this.isApproving + ", isPencacah=" + this.isPencacah + ", smallestAreaOrder=" + this.smallestAreaOrder + ", canChangeMode=" + this.canChangeMode + ", canDrawSample=" + this.canDrawSample + ", canEdit=" + this.canEdit + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
    }

    public UserRole(String str, String str2, String str3, Boolean bool, Integer num, String str4, String str5, Boolean bool2, Boolean bool3, Integer num2, Boolean bool4, Boolean bool5, Boolean bool6, String str6, String str7) {
        this.id = str;
        this.name = str2;
        this.surveyId = str3;
        this.active = bool;
        this.sequence = num;
        this.description = str4;
        this.surveyRoleGroupId = str5;
        this.isApproving = bool2;
        this.isPencacah = bool3;
        this.smallestAreaOrder = num2;
        this.canChangeMode = bool4;
        this.canDrawSample = bool5;
        this.canEdit = bool6;
        this.createdAt = str6;
        this.updatedAt = str7;
    }

    public /* synthetic */ UserRole(String str, String str2, String str3, Boolean bool, Integer num, String str4, String str5, Boolean bool2, Boolean bool3, Integer num2, Boolean bool4, Boolean bool5, Boolean bool6, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, bool, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : str4, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : bool2, (i & 256) != 0 ? null : bool3, (i & 512) != 0 ? null : num2, (i & 1024) != 0 ? null : bool4, (i & 2048) != 0 ? null : bool5, (i & 4096) != 0 ? null : bool6, (i & 8192) != 0 ? null : str6, (i & 16384) != 0 ? null : str7);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final void setActive(Boolean bool) {
        this.active = bool;
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

    public final Boolean isApproving() {
        return this.isApproving;
    }

    public final void setApproving(Boolean bool) {
        this.isApproving = bool;
    }

    public final Boolean isPencacah() {
        return this.isPencacah;
    }

    public final void setPencacah(Boolean bool) {
        this.isPencacah = bool;
    }

    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    public final void setSmallestAreaOrder(Integer num) {
        this.smallestAreaOrder = num;
    }

    public final Boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    public final void setCanChangeMode(Boolean bool) {
        this.canChangeMode = bool;
    }

    public final Boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    public final void setCanDrawSample(Boolean bool) {
        this.canDrawSample = bool;
    }

    public final Boolean getCanEdit() {
        return this.canEdit;
    }

    public final void setCanEdit(Boolean bool) {
        this.canEdit = bool;
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
}
