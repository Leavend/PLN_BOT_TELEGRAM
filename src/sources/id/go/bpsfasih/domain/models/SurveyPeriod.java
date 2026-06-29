package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Survey.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u007f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\u000eHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\fHÆ\u0003J\u0091\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u0010.\u001a\u00020\f2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001b¨\u00063"}, d2 = {"Lid/go/bpsfasih/domain/models/SurveyPeriod;", "", DownloadModel.ID, "", "name", "surveyId", "parentId", "userId", "createdDate", "startDate", "endDate", "isActive", "", "userRoles", "", "Lid/go/bpsfasih/domain/models/UserRole;", "smallestRegionCodes", "surveyPeriodRoleUserId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Ljava/util/List;Ljava/lang/String;)V", "getCreatedDate", "()Ljava/lang/String;", "getEndDate", "getId", "()Z", "getName", "getParentId", "getSmallestRegionCodes", "()Ljava/util/List;", "getStartDate", "getSurveyId", "getSurveyPeriodRoleUserId", "getUserId", "getUserRoles", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class SurveyPeriod {
    public static final int $stable = 8;
    private final String createdDate;
    private final String endDate;
    private final String id;
    private final boolean isActive;
    private final String name;
    private final String parentId;
    private final List<String> smallestRegionCodes;
    private final String startDate;
    private final String surveyId;
    private final String surveyPeriodRoleUserId;
    private final String userId;
    private final List<UserRole> userRoles;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final List<UserRole> component10() {
        return this.userRoles;
    }

    public final List<String> component11() {
        return this.smallestRegionCodes;
    }

    /* renamed from: component12, reason: from getter */
    public final String getSurveyPeriodRoleUserId() {
        return this.surveyPeriodRoleUserId;
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
    public final boolean getIsActive() {
        return this.isActive;
    }

    public final SurveyPeriod copy(String id2, String name, String surveyId, String parentId, String userId, String createdDate, String startDate, String endDate, boolean isActive, List<UserRole> userRoles, List<String> smallestRegionCodes, String surveyPeriodRoleUserId) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(userRoles, "userRoles");
        Intrinsics.checkNotNullParameter(smallestRegionCodes, "smallestRegionCodes");
        Intrinsics.checkNotNullParameter(surveyPeriodRoleUserId, "surveyPeriodRoleUserId");
        return new SurveyPeriod(id2, name, surveyId, parentId, userId, createdDate, startDate, endDate, isActive, userRoles, smallestRegionCodes, surveyPeriodRoleUserId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SurveyPeriod)) {
            return false;
        }
        SurveyPeriod surveyPeriod = (SurveyPeriod) other;
        return Intrinsics.areEqual(this.id, surveyPeriod.id) && Intrinsics.areEqual(this.name, surveyPeriod.name) && Intrinsics.areEqual(this.surveyId, surveyPeriod.surveyId) && Intrinsics.areEqual(this.parentId, surveyPeriod.parentId) && Intrinsics.areEqual(this.userId, surveyPeriod.userId) && Intrinsics.areEqual(this.createdDate, surveyPeriod.createdDate) && Intrinsics.areEqual(this.startDate, surveyPeriod.startDate) && Intrinsics.areEqual(this.endDate, surveyPeriod.endDate) && this.isActive == surveyPeriod.isActive && Intrinsics.areEqual(this.userRoles, surveyPeriod.userRoles) && Intrinsics.areEqual(this.smallestRegionCodes, surveyPeriod.smallestRegionCodes) && Intrinsics.areEqual(this.surveyPeriodRoleUserId, surveyPeriod.surveyPeriodRoleUserId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.surveyId.hashCode()) * 31;
        String str = this.parentId;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.userId;
        int iHashCode3 = (((((((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.createdDate.hashCode()) * 31) + this.startDate.hashCode()) * 31) + this.endDate.hashCode()) * 31;
        boolean z = this.isActive;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return ((((((iHashCode3 + i) * 31) + this.userRoles.hashCode()) * 31) + this.smallestRegionCodes.hashCode()) * 31) + this.surveyPeriodRoleUserId.hashCode();
    }

    public String toString() {
        return "SurveyPeriod(id=" + this.id + ", name=" + this.name + ", surveyId=" + this.surveyId + ", parentId=" + this.parentId + ", userId=" + this.userId + ", createdDate=" + this.createdDate + ", startDate=" + this.startDate + ", endDate=" + this.endDate + ", isActive=" + this.isActive + ", userRoles=" + this.userRoles + ", smallestRegionCodes=" + this.smallestRegionCodes + ", surveyPeriodRoleUserId=" + this.surveyPeriodRoleUserId + ")";
    }

    public SurveyPeriod(String id2, String name, String surveyId, String str, String str2, String createdDate, String startDate, String endDate, boolean z, List<UserRole> userRoles, List<String> smallestRegionCodes, String surveyPeriodRoleUserId) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(createdDate, "createdDate");
        Intrinsics.checkNotNullParameter(startDate, "startDate");
        Intrinsics.checkNotNullParameter(endDate, "endDate");
        Intrinsics.checkNotNullParameter(userRoles, "userRoles");
        Intrinsics.checkNotNullParameter(smallestRegionCodes, "smallestRegionCodes");
        Intrinsics.checkNotNullParameter(surveyPeriodRoleUserId, "surveyPeriodRoleUserId");
        this.id = id2;
        this.name = name;
        this.surveyId = surveyId;
        this.parentId = str;
        this.userId = str2;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = z;
        this.userRoles = userRoles;
        this.smallestRegionCodes = smallestRegionCodes;
        this.surveyPeriodRoleUserId = surveyPeriodRoleUserId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final String getParentId() {
        return this.parentId;
    }

    public final String getUserId() {
        return this.userId;
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

    public final boolean isActive() {
        return this.isActive;
    }

    public /* synthetic */ SurveyPeriod(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, List list, List list2, String str9, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, str6, str7, str8, (i & 256) != 0 ? false : z, (i & 512) != 0 ? CollectionsKt.emptyList() : list, (i & 1024) != 0 ? CollectionsKt.emptyList() : list2, str9);
    }

    public final List<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public final List<String> getSmallestRegionCodes() {
        return this.smallestRegionCodes;
    }

    public final String getSurveyPeriodRoleUserId() {
        return this.surveyPeriodRoleUserId;
    }
}
