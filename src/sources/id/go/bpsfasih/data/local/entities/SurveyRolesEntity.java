package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SurveyRolesEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\bL\b\u0087\b\u0018\u00002\u00020\u0001BÝ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u0018J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010J\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010K\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010L\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010M\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010N\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010O\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u00105J\u000b\u0010P\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010Q\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010R\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010S\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u00105J\u0010\u0010T\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u0010U\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jæ\u0001\u0010W\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010XJ\u0013\u0010Y\u001a\u00020\t2\b\u0010Z\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010[\u001a\u00020\u0005HÖ\u0001J\t\u0010\\\u001a\u00020\u0003HÖ\u0001R\"\u0010\u0014\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\"\u0010\u0015\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\"\u0010\u0013\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b \u0010\u001a\"\u0004\b!\u0010\u001cR\"\u0010\u0016\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\"\u0010\u001a\"\u0004\b#\u0010\u001cR\"\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b$\u0010\u001a\"\u0004\b%\u0010\u001cR\"\u0010\u0012\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b&\u0010\u001a\"\u0004\b'\u0010\u001cR\"\u0010\u0017\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b(\u0010\u001a\"\u0004\b)\u0010\u001cR \u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010+\"\u0004\b/\u0010-R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R\"\u0010\b\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\b\u0010\u001a\"\u0004\b2\u0010\u001cR\"\u0010\f\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\f\u0010\u001a\"\u0004\b3\u0010\u001cR\"\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00108\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\"\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u00108\u001a\u0004\b9\u00105\"\u0004\b:\u00107R \u0010\u000f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010+\"\u0004\b<\u0010-R \u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R \u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010+\"\u0004\bB\u0010-R \u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010+\"\u0004\bD\u0010-¨\u0006]"}, d2 = {"Lid/go/bpsfasih/data/local/entities/SurveyRolesEntity;", "", DownloadModel.ID, "", "sequence", "", "description", "surveyRoleGroupId", "isApproving", "", "smallestAreaOrder", "canDrawSample", "isPencacah", "createdAt", "updatedAt", "surveyId", "surveyRoleGroup", "Lid/go/bpsfasih/data/local/entities/SurveyRoleGroup;", "canEdit", "canChangeMode", "adminTicket", "canAccessTicket", "canDeleteSample", "canTransferSample", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/SurveyRoleGroup;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "getAdminTicket", "()Ljava/lang/Boolean;", "setAdminTicket", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCanAccessTicket", "setCanAccessTicket", "getCanChangeMode", "setCanChangeMode", "getCanDeleteSample", "setCanDeleteSample", "getCanDrawSample", "setCanDrawSample", "getCanEdit", "setCanEdit", "getCanTransferSample", "setCanTransferSample", "getCreatedAt", "()Ljava/lang/String;", "setCreatedAt", "(Ljava/lang/String;)V", "getDescription", "setDescription", "getId", "setId", "setApproving", "setPencacah", "getSequence", "()Ljava/lang/Integer;", "setSequence", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getSmallestAreaOrder", "setSmallestAreaOrder", "getSurveyId", "setSurveyId", "getSurveyRoleGroup", "()Lid/go/bpsfasih/data/local/entities/SurveyRoleGroup;", "setSurveyRoleGroup", "(Lid/go/bpsfasih/data/local/entities/SurveyRoleGroup;)V", "getSurveyRoleGroupId", "setSurveyRoleGroupId", "getUpdatedAt", "setUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/SurveyRoleGroup;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;)Lid/go/bpsfasih/data/local/entities/SurveyRolesEntity;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyRolesEntity {
    public static final int $stable = 8;

    @SerializedName("adminTicket")
    private Boolean adminTicket;

    @SerializedName("canAccessTicket")
    private Boolean canAccessTicket;

    @SerializedName("canChangeMode")
    private Boolean canChangeMode;

    @SerializedName("canDeleteSample")
    private Boolean canDeleteSample;

    @SerializedName("canDrawSample")
    private Boolean canDrawSample;

    @SerializedName("canEdit")
    private Boolean canEdit;

    @SerializedName("canTransferSample")
    private Boolean canTransferSample;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("description")
    private String description;

    @SerializedName(DownloadModel.ID)
    private String id;

    @SerializedName("isApproving")
    private Boolean isApproving;

    @SerializedName("isPencacah")
    private Boolean isPencacah;

    @SerializedName("sequence")
    private Integer sequence;

    @SerializedName("smallestAreaOrder")
    private Integer smallestAreaOrder;

    @SerializedName("surveyId")
    private String surveyId;

    @SerializedName("surveyRoleGroup")
    private SurveyRoleGroup surveyRoleGroup;

    @SerializedName("surveyRoleGroupId")
    private String surveyRoleGroupId;

    @SerializedName("updatedAt")
    private String updatedAt;

    public SurveyRolesEntity() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 262143, null);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component11, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component12, reason: from getter */
    public final SurveyRoleGroup getSurveyRoleGroup() {
        return this.surveyRoleGroup;
    }

    /* renamed from: component13, reason: from getter */
    public final Boolean getCanEdit() {
        return this.canEdit;
    }

    /* renamed from: component14, reason: from getter */
    public final Boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    /* renamed from: component15, reason: from getter */
    public final Boolean getAdminTicket() {
        return this.adminTicket;
    }

    /* renamed from: component16, reason: from getter */
    public final Boolean getCanAccessTicket() {
        return this.canAccessTicket;
    }

    /* renamed from: component17, reason: from getter */
    public final Boolean getCanDeleteSample() {
        return this.canDeleteSample;
    }

    /* renamed from: component18, reason: from getter */
    public final Boolean getCanTransferSample() {
        return this.canTransferSample;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getSequence() {
        return this.sequence;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDescription() {
        return this.description;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSurveyRoleGroupId() {
        return this.surveyRoleGroupId;
    }

    /* renamed from: component5, reason: from getter */
    public final Boolean getIsApproving() {
        return this.isApproving;
    }

    /* renamed from: component6, reason: from getter */
    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    /* renamed from: component7, reason: from getter */
    public final Boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    /* renamed from: component8, reason: from getter */
    public final Boolean getIsPencacah() {
        return this.isPencacah;
    }

    /* renamed from: component9, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final SurveyRolesEntity copy(String id2, Integer sequence, String description, String surveyRoleGroupId, Boolean isApproving, Integer smallestAreaOrder, Boolean canDrawSample, Boolean isPencacah, String createdAt, String updatedAt, String surveyId, SurveyRoleGroup surveyRoleGroup, Boolean canEdit, Boolean canChangeMode, Boolean adminTicket, Boolean canAccessTicket, Boolean canDeleteSample, Boolean canTransferSample) {
        return new SurveyRolesEntity(id2, sequence, description, surveyRoleGroupId, isApproving, smallestAreaOrder, canDrawSample, isPencacah, createdAt, updatedAt, surveyId, surveyRoleGroup, canEdit, canChangeMode, adminTicket, canAccessTicket, canDeleteSample, canTransferSample);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyRolesEntity)) {
            return false;
        }
        SurveyRolesEntity surveyRolesEntity = (SurveyRolesEntity) other;
        return Intrinsics.areEqual(this.id, surveyRolesEntity.id) && Intrinsics.areEqual(this.sequence, surveyRolesEntity.sequence) && Intrinsics.areEqual(this.description, surveyRolesEntity.description) && Intrinsics.areEqual(this.surveyRoleGroupId, surveyRolesEntity.surveyRoleGroupId) && Intrinsics.areEqual(this.isApproving, surveyRolesEntity.isApproving) && Intrinsics.areEqual(this.smallestAreaOrder, surveyRolesEntity.smallestAreaOrder) && Intrinsics.areEqual(this.canDrawSample, surveyRolesEntity.canDrawSample) && Intrinsics.areEqual(this.isPencacah, surveyRolesEntity.isPencacah) && Intrinsics.areEqual(this.createdAt, surveyRolesEntity.createdAt) && Intrinsics.areEqual(this.updatedAt, surveyRolesEntity.updatedAt) && Intrinsics.areEqual(this.surveyId, surveyRolesEntity.surveyId) && Intrinsics.areEqual(this.surveyRoleGroup, surveyRolesEntity.surveyRoleGroup) && Intrinsics.areEqual(this.canEdit, surveyRolesEntity.canEdit) && Intrinsics.areEqual(this.canChangeMode, surveyRolesEntity.canChangeMode) && Intrinsics.areEqual(this.adminTicket, surveyRolesEntity.adminTicket) && Intrinsics.areEqual(this.canAccessTicket, surveyRolesEntity.canAccessTicket) && Intrinsics.areEqual(this.canDeleteSample, surveyRolesEntity.canDeleteSample) && Intrinsics.areEqual(this.canTransferSample, surveyRolesEntity.canTransferSample);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.sequence;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.description;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveyRoleGroupId;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.isApproving;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        Integer num2 = this.smallestAreaOrder;
        int iHashCode6 = (iHashCode5 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Boolean bool2 = this.canDrawSample;
        int iHashCode7 = (iHashCode6 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.isPencacah;
        int iHashCode8 = (iHashCode7 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        String str4 = this.createdAt;
        int iHashCode9 = (iHashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.updatedAt;
        int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.surveyId;
        int iHashCode11 = (iHashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
        SurveyRoleGroup surveyRoleGroup = this.surveyRoleGroup;
        int iHashCode12 = (iHashCode11 + (surveyRoleGroup == null ? 0 : surveyRoleGroup.hashCode())) * 31;
        Boolean bool4 = this.canEdit;
        int iHashCode13 = (iHashCode12 + (bool4 == null ? 0 : bool4.hashCode())) * 31;
        Boolean bool5 = this.canChangeMode;
        int iHashCode14 = (iHashCode13 + (bool5 == null ? 0 : bool5.hashCode())) * 31;
        Boolean bool6 = this.adminTicket;
        int iHashCode15 = (iHashCode14 + (bool6 == null ? 0 : bool6.hashCode())) * 31;
        Boolean bool7 = this.canAccessTicket;
        int iHashCode16 = (iHashCode15 + (bool7 == null ? 0 : bool7.hashCode())) * 31;
        Boolean bool8 = this.canDeleteSample;
        int iHashCode17 = (iHashCode16 + (bool8 == null ? 0 : bool8.hashCode())) * 31;
        Boolean bool9 = this.canTransferSample;
        return iHashCode17 + (bool9 != null ? bool9.hashCode() : 0);
    }

    public String toString() {
        return "SurveyRolesEntity(id=" + this.id + ", sequence=" + this.sequence + ", description=" + this.description + ", surveyRoleGroupId=" + this.surveyRoleGroupId + ", isApproving=" + this.isApproving + ", smallestAreaOrder=" + this.smallestAreaOrder + ", canDrawSample=" + this.canDrawSample + ", isPencacah=" + this.isPencacah + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", surveyId=" + this.surveyId + ", surveyRoleGroup=" + this.surveyRoleGroup + ", canEdit=" + this.canEdit + ", canChangeMode=" + this.canChangeMode + ", adminTicket=" + this.adminTicket + ", canAccessTicket=" + this.canAccessTicket + ", canDeleteSample=" + this.canDeleteSample + ", canTransferSample=" + this.canTransferSample + ")";
    }

    public SurveyRolesEntity(String str, Integer num, String str2, String str3, Boolean bool, Integer num2, Boolean bool2, Boolean bool3, String str4, String str5, String str6, SurveyRoleGroup surveyRoleGroup, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9) {
        this.id = str;
        this.sequence = num;
        this.description = str2;
        this.surveyRoleGroupId = str3;
        this.isApproving = bool;
        this.smallestAreaOrder = num2;
        this.canDrawSample = bool2;
        this.isPencacah = bool3;
        this.createdAt = str4;
        this.updatedAt = str5;
        this.surveyId = str6;
        this.surveyRoleGroup = surveyRoleGroup;
        this.canEdit = bool4;
        this.canChangeMode = bool5;
        this.adminTicket = bool6;
        this.canAccessTicket = bool7;
        this.canDeleteSample = bool8;
        this.canTransferSample = bool9;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
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

    public final Integer getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    public final void setSmallestAreaOrder(Integer num) {
        this.smallestAreaOrder = num;
    }

    public final Boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    public final void setCanDrawSample(Boolean bool) {
        this.canDrawSample = bool;
    }

    public final Boolean isPencacah() {
        return this.isPencacah;
    }

    public final void setPencacah(Boolean bool) {
        this.isPencacah = bool;
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

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public /* synthetic */ SurveyRolesEntity(String str, Integer num, String str2, String str3, Boolean bool, Integer num2, Boolean bool2, Boolean bool3, String str4, String str5, String str6, SurveyRoleGroup surveyRoleGroup, Boolean bool4, Boolean bool5, Boolean bool6, Boolean bool7, Boolean bool8, Boolean bool9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? null : str3, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? null : num2, (i & 64) != 0 ? null : bool2, (i & 128) != 0 ? null : bool3, (i & 256) != 0 ? null : str4, (i & 512) != 0 ? null : str5, (i & 1024) != 0 ? null : str6, (i & 2048) != 0 ? new SurveyRoleGroup(null, null, null, null, 15, null) : surveyRoleGroup, (i & 4096) != 0 ? null : bool4, (i & 8192) != 0 ? null : bool5, (i & 16384) != 0 ? null : bool6, (i & 32768) != 0 ? null : bool7, (i & 65536) != 0 ? null : bool8, (i & 131072) != 0 ? null : bool9);
    }

    public final SurveyRoleGroup getSurveyRoleGroup() {
        return this.surveyRoleGroup;
    }

    public final void setSurveyRoleGroup(SurveyRoleGroup surveyRoleGroup) {
        this.surveyRoleGroup = surveyRoleGroup;
    }

    public final Boolean getCanEdit() {
        return this.canEdit;
    }

    public final void setCanEdit(Boolean bool) {
        this.canEdit = bool;
    }

    public final Boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    public final void setCanChangeMode(Boolean bool) {
        this.canChangeMode = bool;
    }

    public final Boolean getAdminTicket() {
        return this.adminTicket;
    }

    public final void setAdminTicket(Boolean bool) {
        this.adminTicket = bool;
    }

    public final Boolean getCanAccessTicket() {
        return this.canAccessTicket;
    }

    public final void setCanAccessTicket(Boolean bool) {
        this.canAccessTicket = bool;
    }

    public final Boolean getCanDeleteSample() {
        return this.canDeleteSample;
    }

    public final void setCanDeleteSample(Boolean bool) {
        this.canDeleteSample = bool;
    }

    public final Boolean getCanTransferSample() {
        return this.canTransferSample;
    }

    public final void setCanTransferSample(Boolean bool) {
        this.canTransferSample = bool;
    }
}
