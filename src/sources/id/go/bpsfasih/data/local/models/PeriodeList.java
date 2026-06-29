package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AllSurveyModel.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b<\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B³\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0010\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000e\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0015J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u00109\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eHÆ\u0003J\u0011\u0010:\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000eHÆ\u0003J\u0010\u0010>\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0017J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010F\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\u0017JØ\u0001\u0010G\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0012\b\u0002\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000e2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\u0012\b\u0002\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000e2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010HJ\u0013\u0010I\u001a\u00020\f2\b\u0010J\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010K\u001a\u00020LHÖ\u0001J\t\u0010M\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u001a\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u001c\"\u0004\b \u0010\u001eR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010\u001eR\u0015\u0010\u0014\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0014\u0010\u0017R\"\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001b\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010$R$\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010$\"\u0004\b)\u0010&R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001c\"\u0004\b+\u0010\u001eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001c\"\u0004\b-\u0010\u001eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010\u001eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u001c\"\u0004\b1\u0010\u001eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001c\"\u0004\b3\u0010\u001eR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u0010\u001eR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001c\"\u0004\b7\u0010\u001e¨\u0006N"}, d2 = {"Lid/go/bpsfasih/data/local/models/PeriodeList;", "", DownloadModel.ID, "", "name", "surveyId", "parentId", "userId", "createdDate", "startDate", "endDate", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "", "listUserRole", "", "Lid/go/bpsfasih/data/local/models/UserRole;", "listBlokSensusFullCode", "lookUpId", "surveyPeriodeRoleUserId", "listSmallestRegionFullCode", "isActive", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)V", "getActive", "()Ljava/lang/Boolean;", "setActive", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getCreatedDate", "()Ljava/lang/String;", "setCreatedDate", "(Ljava/lang/String;)V", "getEndDate", "setEndDate", "getId", "setId", "getListBlokSensusFullCode", "()Ljava/util/List;", "setListBlokSensusFullCode", "(Ljava/util/List;)V", "getListSmallestRegionFullCode", "getListUserRole", "setListUserRole", "getLookUpId", "setLookUpId", "getName", "setName", "getParentId", "setParentId", "getStartDate", "setStartDate", "getSurveyId", "setSurveyId", "getSurveyPeriodeRoleUserId", "setSurveyPeriodeRoleUserId", "getUserId", "setUserId", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Boolean;)Lid/go/bpsfasih/data/local/models/PeriodeList;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class PeriodeList {
    public static final int $stable = 8;
    private Boolean active;
    private String createdDate;
    private String endDate;
    private String id;
    private final Boolean isActive;
    private List<String> listBlokSensusFullCode;
    private final List<String> listSmallestRegionFullCode;
    private List<UserRole> listUserRole;
    private String lookUpId;
    private String name;
    private String parentId;
    private String startDate;
    private String surveyId;
    private String surveyPeriodeRoleUserId;
    private String userId;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<UserRole> component10() {
        return this.listUserRole;
    }

    public final List<String> component11() {
        return this.listBlokSensusFullCode;
    }

    /* renamed from: component12, reason: from getter */
    public final String getLookUpId() {
        return this.lookUpId;
    }

    /* renamed from: component13, reason: from getter */
    public final String getSurveyPeriodeRoleUserId() {
        return this.surveyPeriodeRoleUserId;
    }

    public final List<String> component14() {
        return this.listSmallestRegionFullCode;
    }

    /* renamed from: component15, reason: from getter */
    public final Boolean getIsActive() {
        return this.isActive;
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
    public final String getParentId() {
        return this.parentId;
    }

    /* renamed from: component5, reason: from getter */
    public final String getUserId() {
        return this.userId;
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
    public final Boolean getActive() {
        return this.active;
    }

    public final PeriodeList copy(String id2, String name, String surveyId, String parentId, String userId, String createdDate, String startDate, String endDate, Boolean active, List<UserRole> listUserRole, List<String> listBlokSensusFullCode, String lookUpId, String surveyPeriodeRoleUserId, List<String> listSmallestRegionFullCode, Boolean isActive) {
        return new PeriodeList(id2, name, surveyId, parentId, userId, createdDate, startDate, endDate, active, listUserRole, listBlokSensusFullCode, lookUpId, surveyPeriodeRoleUserId, listSmallestRegionFullCode, isActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PeriodeList)) {
            return false;
        }
        PeriodeList periodeList = (PeriodeList) other;
        return Intrinsics.areEqual(this.id, periodeList.id) && Intrinsics.areEqual(this.name, periodeList.name) && Intrinsics.areEqual(this.surveyId, periodeList.surveyId) && Intrinsics.areEqual(this.parentId, periodeList.parentId) && Intrinsics.areEqual(this.userId, periodeList.userId) && Intrinsics.areEqual(this.createdDate, periodeList.createdDate) && Intrinsics.areEqual(this.startDate, periodeList.startDate) && Intrinsics.areEqual(this.endDate, periodeList.endDate) && Intrinsics.areEqual(this.active, periodeList.active) && Intrinsics.areEqual(this.listUserRole, periodeList.listUserRole) && Intrinsics.areEqual(this.listBlokSensusFullCode, periodeList.listBlokSensusFullCode) && Intrinsics.areEqual(this.lookUpId, periodeList.lookUpId) && Intrinsics.areEqual(this.surveyPeriodeRoleUserId, periodeList.surveyPeriodeRoleUserId) && Intrinsics.areEqual(this.listSmallestRegionFullCode, periodeList.listSmallestRegionFullCode) && Intrinsics.areEqual(this.isActive, periodeList.isActive);
    }

    public int hashCode() {
        String str = this.id;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.name;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.surveyId;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.parentId;
        int iHashCode4 = (iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.userId;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.createdDate;
        int iHashCode6 = (iHashCode5 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.startDate;
        int iHashCode7 = (iHashCode6 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.endDate;
        int iHashCode8 = (iHashCode7 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Boolean bool = this.active;
        int iHashCode9 = (iHashCode8 + (bool == null ? 0 : bool.hashCode())) * 31;
        List<UserRole> list = this.listUserRole;
        int iHashCode10 = (iHashCode9 + (list == null ? 0 : list.hashCode())) * 31;
        List<String> list2 = this.listBlokSensusFullCode;
        int iHashCode11 = (iHashCode10 + (list2 == null ? 0 : list2.hashCode())) * 31;
        String str9 = this.lookUpId;
        int iHashCode12 = (iHashCode11 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.surveyPeriodeRoleUserId;
        int iHashCode13 = (iHashCode12 + (str10 == null ? 0 : str10.hashCode())) * 31;
        List<String> list3 = this.listSmallestRegionFullCode;
        int iHashCode14 = (iHashCode13 + (list3 == null ? 0 : list3.hashCode())) * 31;
        Boolean bool2 = this.isActive;
        return iHashCode14 + (bool2 != null ? bool2.hashCode() : 0);
    }

    public String toString() {
        return "PeriodeList(id=" + this.id + ", name=" + this.name + ", surveyId=" + this.surveyId + ", parentId=" + this.parentId + ", userId=" + this.userId + ", createdDate=" + this.createdDate + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", active=" + this.active + ", listUserRole=" + this.listUserRole + ", listBlokSensusFullCode=" + this.listBlokSensusFullCode + ", lookUpId=" + this.lookUpId + ", surveyPeriodeRoleUserId=" + this.surveyPeriodeRoleUserId + ", listSmallestRegionFullCode=" + this.listSmallestRegionFullCode + ", isActive=" + this.isActive + ")";
    }

    public PeriodeList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Boolean bool, List<UserRole> list, List<String> list2, String str9, String str10, List<String> list3, Boolean bool2) {
        this.id = str;
        this.name = str2;
        this.surveyId = str3;
        this.parentId = str4;
        this.userId = str5;
        this.createdDate = str6;
        this.startDate = str7;
        this.endDate = str8;
        this.active = bool;
        this.listUserRole = list;
        this.listBlokSensusFullCode = list2;
        this.lookUpId = str9;
        this.surveyPeriodeRoleUserId = str10;
        this.listSmallestRegionFullCode = list3;
        this.isActive = bool2;
    }

    public /* synthetic */ PeriodeList(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, Boolean bool, List list, List list2, String str9, String str10, List list3, Boolean bool2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, bool, list, list2, str9, str10, (i & 8192) != 0 ? null : list3, bool2);
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

    public final String getParentId() {
        return this.parentId;
    }

    public final void setParentId(String str) {
        this.parentId = str;
    }

    public final String getUserId() {
        return this.userId;
    }

    public final void setUserId(String str) {
        this.userId = str;
    }

    public final String getCreatedDate() {
        return this.createdDate;
    }

    public final void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public final String getStartDate() {
        return this.startDate;
    }

    public final void setStartDate(String str) {
        this.startDate = str;
    }

    public final String getEndDate() {
        return this.endDate;
    }

    public final void setEndDate(String str) {
        this.endDate = str;
    }

    public final Boolean getActive() {
        return this.active;
    }

    public final void setActive(Boolean bool) {
        this.active = bool;
    }

    public final List<UserRole> getListUserRole() {
        return this.listUserRole;
    }

    public final void setListUserRole(List<UserRole> list) {
        this.listUserRole = list;
    }

    public final List<String> getListBlokSensusFullCode() {
        return this.listBlokSensusFullCode;
    }

    public final void setListBlokSensusFullCode(List<String> list) {
        this.listBlokSensusFullCode = list;
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

    public final List<String> getListSmallestRegionFullCode() {
        return this.listSmallestRegionFullCode;
    }

    public final Boolean isActive() {
        return this.isActive;
    }
}
