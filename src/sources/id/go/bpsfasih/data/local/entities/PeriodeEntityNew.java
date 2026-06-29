package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.models.UserRole;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PeriodeEntityNew.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b>\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 Q2\u00020\u0001:\u0001QB×\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0010\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u0012\u0010\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000f\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u0018J\t\u00109\u001a\u00020\u0003HÆ\u0003J\u0013\u0010:\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010>\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0003J\u0013\u0010?\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000fHÆ\u0003J\u0011\u0010@\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000fHÆ\u0003J\u0010\u0010A\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001fJ\t\u0010B\u001a\u00020\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0007HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010I\u001a\u0004\u0018\u00010\rHÆ\u0003¢\u0006\u0002\u0010\u001fJð\u0001\u0010J\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0012\b\u0002\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f2\u0012\b\u0002\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000f2\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\rHÆ\u0001¢\u0006\u0002\u0010KJ\u0013\u0010L\u001a\u00020\r2\b\u0010M\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010N\u001a\u00020OHÖ\u0001J\t\u0010P\u001a\u00020\u0003HÖ\u0001R\u0018\u0010\t\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001a\"\u0004\b\u001d\u0010\u001eR\u0015\u0010\u0017\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u0017\u0010\u001fR\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR$\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R \u0010\u0011\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001a\"\u0004\b'\u0010\u001eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001aR\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010 \u001a\u0004\b)\u0010\u001fR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001a\"\u0004\b+\u0010\u001eR$\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010#\"\u0004\b-\u0010%R\u0019\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0019\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000f¢\u0006\b\n\u0000\u001a\u0004\b/\u0010#R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001aR\u001e\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u001a\"\u0004\b6\u0010\u001eR \u0010\b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001a\"\u0004\b8\u0010\u001e¨\u0006R"}, d2 = {"Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "", "primaryId", "", DownloadModel.ID, "name", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "userIdPeriode", "createdDate", "startDate", "endDate", "pencacah", "", "role", "", "Lid/go/bpsfasih/data/local/models/UserRole;", "lookUpId", "surveyPeriodeRoleUserId", "jsMethod", "sampleTargetSurveyPeriodeId", "listSmallestRegionFullCode", "sampleTargetSurveyPeriodeIdCsv", "isActive", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/SurveyEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;)V", "getCreatedDate", "()Ljava/lang/String;", "getEndDate", "getId", "setId", "(Ljava/lang/String;)V", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getJsMethod", "getListSmallestRegionFullCode", "()Ljava/util/List;", "setListSmallestRegionFullCode", "(Ljava/util/List;)V", "getLookUpId", "setLookUpId", "getName", "getPencacah", "getPrimaryId", "setPrimaryId", "getRole", "setRole", "getSampleTargetSurveyPeriodeId", "getSampleTargetSurveyPeriodeIdCsv", "getStartDate", "getSurvey", "()Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "setSurvey", "(Lid/go/bpsfasih/data/local/entities/SurveyEntity;)V", "getSurveyPeriodeRoleUserId", "setSurveyPeriodeRoleUserId", "getUserIdPeriode", "setUserIdPeriode", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/SurveyEntity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/lang/Boolean;)Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "equals", "other", "hashCode", "", "toString", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class PeriodeEntityNew {
    private final String createdDate;
    private final String endDate;
    private String id;
    private final Boolean isActive;
    private final String jsMethod;
    private List<String> listSmallestRegionFullCode;
    private String lookUpId;
    private final String name;
    private final Boolean pencacah;
    private String primaryId;
    private List<UserRole> role;
    private final List<String> sampleTargetSurveyPeriodeId;
    private final List<String> sampleTargetSurveyPeriodeIdCsv;
    private final String startDate;
    private SurveyEntity survey;
    private String surveyPeriodeRoleUserId;

    @SerializedName("userId")
    private String userIdPeriode;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* renamed from: component1, reason: from getter */
    public final String getPrimaryId() {
        return this.primaryId;
    }

    public final List<UserRole> component10() {
        return this.role;
    }

    /* renamed from: component11, reason: from getter */
    public final String getLookUpId() {
        return this.lookUpId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSurveyPeriodeRoleUserId() {
        return this.surveyPeriodeRoleUserId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getJsMethod() {
        return this.jsMethod;
    }

    public final List<String> component14() {
        return this.sampleTargetSurveyPeriodeId;
    }

    public final List<String> component15() {
        return this.listSmallestRegionFullCode;
    }

    public final List<String> component16() {
        return this.sampleTargetSurveyPeriodeIdCsv;
    }

    /* renamed from: component17, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUserIdPeriode() {
        return this.userIdPeriode;
    }

    /* renamed from: component6, reason: from getter */
    public final String getCreatedDate() {
        return this.createdDate;
    }

    /* renamed from: component7, reason: from getter */
    public final String getStartDate() {
        return this.startDate;
    }

    /* renamed from: component8, reason: from getter */
    public final String getEndDate() {
        return this.endDate;
    }

    /* renamed from: component9, reason: from getter */
    public final Boolean getPencacah() {
        return this.pencacah;
    }

    public final PeriodeEntityNew copy(String primaryId, String id2, String name, SurveyEntity survey, String userIdPeriode, String createdDate, String startDate, String endDate, Boolean pencacah, List<UserRole> role, String lookUpId, String surveyPeriodeRoleUserId, String jsMethod, List<String> sampleTargetSurveyPeriodeId, List<String> listSmallestRegionFullCode, List<String> sampleTargetSurveyPeriodeIdCsv, Boolean isActive) {
        Intrinsics.checkNotNullParameter(primaryId, "primaryId");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(survey, "survey");
        return new PeriodeEntityNew(primaryId, id2, name, survey, userIdPeriode, createdDate, startDate, endDate, pencacah, role, lookUpId, surveyPeriodeRoleUserId, jsMethod, sampleTargetSurveyPeriodeId, listSmallestRegionFullCode, sampleTargetSurveyPeriodeIdCsv, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PeriodeEntityNew)) {
            return false;
        }
        PeriodeEntityNew periodeEntityNew = (PeriodeEntityNew) other;
        return Intrinsics.areEqual(this.primaryId, periodeEntityNew.primaryId) && Intrinsics.areEqual(this.id, periodeEntityNew.id) && Intrinsics.areEqual(this.name, periodeEntityNew.name) && Intrinsics.areEqual(this.survey, periodeEntityNew.survey) && Intrinsics.areEqual(this.userIdPeriode, periodeEntityNew.userIdPeriode) && Intrinsics.areEqual(this.createdDate, periodeEntityNew.createdDate) && Intrinsics.areEqual(this.startDate, periodeEntityNew.startDate) && Intrinsics.areEqual(this.endDate, periodeEntityNew.endDate) && Intrinsics.areEqual(this.pencacah, periodeEntityNew.pencacah) && Intrinsics.areEqual(this.role, periodeEntityNew.role) && Intrinsics.areEqual(this.lookUpId, periodeEntityNew.lookUpId) && Intrinsics.areEqual(this.surveyPeriodeRoleUserId, periodeEntityNew.surveyPeriodeRoleUserId) && Intrinsics.areEqual(this.jsMethod, periodeEntityNew.jsMethod) && Intrinsics.areEqual(this.sampleTargetSurveyPeriodeId, periodeEntityNew.sampleTargetSurveyPeriodeId) && Intrinsics.areEqual(this.listSmallestRegionFullCode, periodeEntityNew.listSmallestRegionFullCode) && Intrinsics.areEqual(this.sampleTargetSurveyPeriodeIdCsv, periodeEntityNew.sampleTargetSurveyPeriodeIdCsv) && Intrinsics.areEqual(this.isActive, periodeEntityNew.isActive);
    }

    public int hashCode() {
        int iHashCode = ((this.primaryId.hashCode() * 31) + this.id.hashCode()) * 31;
        String str = this.name;
        int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.survey.hashCode()) * 31;
        String str2 = this.userIdPeriode;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.createdDate;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.startDate;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.endDate;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool = this.pencacah;
        int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        List<UserRole> list = this.role;
        int iHashCode8 = (iHashCode7 + (list == null ? 0 : list.hashCode())) * 31;
        String str6 = this.lookUpId;
        int iHashCode9 = (iHashCode8 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.surveyPeriodeRoleUserId;
        int iHashCode10 = (iHashCode9 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.jsMethod;
        int iHashCode11 = (iHashCode10 + (str8 == null ? 0 : str8.hashCode())) * 31;
        List<String> list2 = this.sampleTargetSurveyPeriodeId;
        int iHashCode12 = (iHashCode11 + (list2 == null ? 0 : list2.hashCode())) * 31;
        List<String> list3 = this.listSmallestRegionFullCode;
        int iHashCode13 = (iHashCode12 + (list3 == null ? 0 : list3.hashCode())) * 31;
        List<String> list4 = this.sampleTargetSurveyPeriodeIdCsv;
        int iHashCode14 = (iHashCode13 + (list4 == null ? 0 : list4.hashCode())) * 31;
        Boolean bool2 = this.isActive;
        return iHashCode14 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "PeriodeEntityNew(primaryId=" + this.primaryId + ", id=" + this.id + ", name=" + this.name + ", survey=" + this.survey + ", userIdPeriode=" + this.userIdPeriode + ", createdDate=" + this.createdDate + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", pencacah=" + this.pencacah + ", role=" + this.role + ", lookUpId=" + this.lookUpId + ", surveyPeriodeRoleUserId=" + this.surveyPeriodeRoleUserId + ", jsMethod=" + this.jsMethod + ", sampleTargetSurveyPeriodeId=" + this.sampleTargetSurveyPeriodeId + ", listSmallestRegionFullCode=" + this.listSmallestRegionFullCode + ", sampleTargetSurveyPeriodeIdCsv=" + this.sampleTargetSurveyPeriodeIdCsv + ", isActive=" + this.isActive + ")";
    }

    public PeriodeEntityNew(String primaryId, String id2, String str, SurveyEntity survey, String str2, String str3, String str4, String str5, Boolean bool, List<UserRole> list, String str6, String str7, String str8, List<String> list2, List<String> list3, List<String> list4, Boolean bool2) {
        Intrinsics.checkNotNullParameter(primaryId, "primaryId");
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(survey, "survey");
        this.primaryId = primaryId;
        this.id = id2;
        this.name = str;
        this.survey = survey;
        this.userIdPeriode = str2;
        this.createdDate = str3;
        this.startDate = str4;
        this.endDate = str5;
        this.pencacah = bool;
        this.role = list;
        this.lookUpId = str6;
        this.surveyPeriodeRoleUserId = str7;
        this.jsMethod = str8;
        this.sampleTargetSurveyPeriodeId = list2;
        this.listSmallestRegionFullCode = list3;
        this.sampleTargetSurveyPeriodeIdCsv = list4;
        this.isActive = bool2;
    }

    public final String getPrimaryId() {
        return this.primaryId;
    }

    public final void setPrimaryId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.primaryId = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final String getName() {
        return this.name;
    }

    public final SurveyEntity getSurvey() {
        return this.survey;
    }

    public final void setSurvey(SurveyEntity surveyEntity) {
        Intrinsics.checkNotNullParameter(surveyEntity, "<set-?>");
        this.survey = surveyEntity;
    }

    public final String getUserIdPeriode() {
        return this.userIdPeriode;
    }

    public final void setUserIdPeriode(String str) {
        this.userIdPeriode = str;
    }

    public final String getCreatedDate() {
        return this.createdDate;
    }

    public final String getStartDate() {
        return this.startDate;
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public /* synthetic */ PeriodeEntityNew(String str, String str2, String str3, SurveyEntity surveyEntity, String str4, String str5, String str6, String str7, Boolean bool, List list, String str8, String str9, String str10, List list2, List list3, List list4, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, surveyEntity, (i & 16) != 0 ? "" : str4, str5, str6, str7, (i & 256) != 0 ? true : bool, list, (i & 1024) != 0 ? null : str8, str9, (i & 4096) != 0 ? null : str10, (i & 8192) != 0 ? null : list2, list3, (i & 32768) != 0 ? null : list4, bool2);
    }

    public final Boolean getPencacah() {
        return this.pencacah;
    }

    public final List<UserRole> getRole() {
        return this.role;
    }

    public final void setRole(List<UserRole> list) {
        this.role = list;
    }

    public final String getLookUpId() {
        return this.lookUpId;
    }

    public final void setLookUpId(String str) {
        this.lookUpId = str;
    }

    public final String getSurveyPeriodeRoleUserId() {
        return this.surveyPeriodeRoleUserId;
    }

    public final void setSurveyPeriodeRoleUserId(String str) {
        this.surveyPeriodeRoleUserId = str;
    }

    public final String getJsMethod() {
        return this.jsMethod;
    }

    public final List<String> getSampleTargetSurveyPeriodeId() {
        return this.sampleTargetSurveyPeriodeId;
    }

    public final List<String> getListSmallestRegionFullCode() {
        return this.listSmallestRegionFullCode;
    }

    public final void setListSmallestRegionFullCode(List<String> list) {
        this.listSmallestRegionFullCode = list;
    }

    public final List<String> getSampleTargetSurveyPeriodeIdCsv() {
        return this.sampleTargetSurveyPeriodeIdCsv;
    }

    public final Boolean isActive() {
        return this.isActive;
    }

    /* compiled from: PeriodeEntityNew.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew$Companion;", "", "()V", "mapIdToPeriode", "", "Lid/go/bpsfasih/data/local/entities/PeriodeEntityNew;", "periodes", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<PeriodeEntityNew> mapIdToPeriode(List<PeriodeEntityNew> periodes) {
            Intrinsics.checkNotNullParameter(periodes, "periodes");
            for (PeriodeEntityNew periodeEntityNew : periodes) {
                periodeEntityNew.setPrimaryId(periodeEntityNew.getId() + File.separator + periodeEntityNew.getUserIdPeriode());
            }
            return periodes;
        }
    }
}
