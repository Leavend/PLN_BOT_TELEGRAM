package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListingMethod.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b3\b\u0087\b\u0018\u00002\u00020\u0001Bq\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0010\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\t\u00104\u001a\u00020\u0010HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u00107\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010;\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010\"J\u0010\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0090\u0001\u0010=\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0010HÆ\u0001¢\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020\u00102\b\u0010@\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010A\u001a\u00020\u0003HÖ\u0001J\t\u0010B\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0018\"\u0004\b\u001c\u0010\u001aR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b&\u0010\u0013\"\u0004\b'\u0010\u0015R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0018\"\u0004\b+\u0010\u001aR\u001e\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b,\u0010\u0013\"\u0004\b-\u0010\u0015R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u0010\u0015R\u001e\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b0\u0010\u0013\"\u0004\b1\u0010\u0015¨\u0006C"}, d2 = {"Lid/go/bpsfasih/data/local/entities/ListingMethod;", "", "listingMethodId", "", "listingMethodName", "", "primaryCount", "criteriaWhere", "criteriaOrderBy", FirebaseAnalytics.Param.METHOD, "userId", "listingMethodDateCreated", "", "countType", "methodType", "listingMethodActive", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getCountType", "()Ljava/lang/Integer;", "setCountType", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCriteriaOrderBy", "()Ljava/lang/String;", "setCriteriaOrderBy", "(Ljava/lang/String;)V", "getCriteriaWhere", "setCriteriaWhere", "getListingMethodActive", "()Z", "setListingMethodActive", "(Z)V", "getListingMethodDateCreated", "()Ljava/lang/Long;", "setListingMethodDateCreated", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getListingMethodId", "setListingMethodId", "getListingMethodName", "setListingMethodName", "getMethod", "setMethod", "getMethodType", "setMethodType", "getPrimaryCount", "setPrimaryCount", "getUserId", "setUserId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/Integer;Z)Lid/go/bpsfasih/data/local/entities/ListingMethod;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class ListingMethod {
    public static final int $stable = 8;
    private Integer countType;
    private String criteriaOrderBy;
    private String criteriaWhere;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean listingMethodActive;

    @SerializedName("dateCreated")
    private Long listingMethodDateCreated;

    @SerializedName(DownloadModel.ID)
    private Integer listingMethodId;

    @SerializedName("name")
    private String listingMethodName;
    private String method;
    private Integer methodType;
    private Integer primaryCount;
    private Integer userId;

    /* renamed from: component1, reason: from getter */
    public final Integer getListingMethodId() {
        return this.listingMethodId;
    }

    /* renamed from: component10, reason: from getter */
    public final Integer getMethodType() {
        return this.methodType;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getListingMethodActive() {
        return this.listingMethodActive;
    }

    /* renamed from: component2, reason: from getter */
    public final String getListingMethodName() {
        return this.listingMethodName;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getPrimaryCount() {
        return this.primaryCount;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCriteriaWhere() {
        return this.criteriaWhere;
    }

    /* renamed from: component5, reason: from getter */
    public final String getCriteriaOrderBy() {
        return this.criteriaOrderBy;
    }

    /* renamed from: component6, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component7, reason: from getter */
    public final Integer getUserId() {
        return this.userId;
    }

    /* renamed from: component8, reason: from getter */
    public final Long getListingMethodDateCreated() {
        return this.listingMethodDateCreated;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getCountType() {
        return this.countType;
    }

    public final ListingMethod copy(Integer listingMethodId, String listingMethodName, Integer primaryCount, String criteriaWhere, String criteriaOrderBy, String method, Integer userId, Long listingMethodDateCreated, Integer countType, Integer methodType, boolean listingMethodActive) {
        return new ListingMethod(listingMethodId, listingMethodName, primaryCount, criteriaWhere, criteriaOrderBy, method, userId, listingMethodDateCreated, countType, methodType, listingMethodActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ListingMethod)) {
            return false;
        }
        ListingMethod listingMethod = (ListingMethod) other;
        return Intrinsics.areEqual(this.listingMethodId, listingMethod.listingMethodId) && Intrinsics.areEqual(this.listingMethodName, listingMethod.listingMethodName) && Intrinsics.areEqual(this.primaryCount, listingMethod.primaryCount) && Intrinsics.areEqual(this.criteriaWhere, listingMethod.criteriaWhere) && Intrinsics.areEqual(this.criteriaOrderBy, listingMethod.criteriaOrderBy) && Intrinsics.areEqual(this.method, listingMethod.method) && Intrinsics.areEqual(this.userId, listingMethod.userId) && Intrinsics.areEqual(this.listingMethodDateCreated, listingMethod.listingMethodDateCreated) && Intrinsics.areEqual(this.countType, listingMethod.countType) && Intrinsics.areEqual(this.methodType, listingMethod.methodType) && this.listingMethodActive == listingMethod.listingMethodActive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.listingMethodId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.listingMethodName;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num2 = this.primaryCount;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str2 = this.criteriaWhere;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.criteriaOrderBy;
        int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.method;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num3 = this.userId;
        int iHashCode7 = (iHashCode6 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Long l = this.listingMethodDateCreated;
        int iHashCode8 = (iHashCode7 + (l == null ? 0 : l.hashCode())) * 31;
        Integer num4 = this.countType;
        int iHashCode9 = (iHashCode8 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.methodType;
        int iHashCode10 = (iHashCode9 + (num5 != null ? num5.hashCode() : 0)) * 31;
        boolean z = this.listingMethodActive;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode10 + i;
    }

    public String toString() {
        return "ListingMethod(listingMethodId=" + this.listingMethodId + ", listingMethodName=" + this.listingMethodName + ", primaryCount=" + this.primaryCount + ", criteriaWhere=" + this.criteriaWhere + ", criteriaOrderBy=" + this.criteriaOrderBy + ", method=" + this.method + ", userId=" + this.userId + ", listingMethodDateCreated=" + this.listingMethodDateCreated + ", countType=" + this.countType + ", methodType=" + this.methodType + ", listingMethodActive=" + this.listingMethodActive + ")";
    }

    public ListingMethod(Integer num, String str, Integer num2, String str2, String str3, String str4, Integer num3, Long l, Integer num4, Integer num5, boolean z) {
        this.listingMethodId = num;
        this.listingMethodName = str;
        this.primaryCount = num2;
        this.criteriaWhere = str2;
        this.criteriaOrderBy = str3;
        this.method = str4;
        this.userId = num3;
        this.listingMethodDateCreated = l;
        this.countType = num4;
        this.methodType = num5;
        this.listingMethodActive = z;
    }

    public final Integer getListingMethodId() {
        return this.listingMethodId;
    }

    public final void setListingMethodId(Integer num) {
        this.listingMethodId = num;
    }

    public final String getListingMethodName() {
        return this.listingMethodName;
    }

    public final void setListingMethodName(String str) {
        this.listingMethodName = str;
    }

    public final Integer getPrimaryCount() {
        return this.primaryCount;
    }

    public final void setPrimaryCount(Integer num) {
        this.primaryCount = num;
    }

    public final String getCriteriaWhere() {
        return this.criteriaWhere;
    }

    public final void setCriteriaWhere(String str) {
        this.criteriaWhere = str;
    }

    public final String getCriteriaOrderBy() {
        return this.criteriaOrderBy;
    }

    public final void setCriteriaOrderBy(String str) {
        this.criteriaOrderBy = str;
    }

    public final String getMethod() {
        return this.method;
    }

    public final void setMethod(String str) {
        this.method = str;
    }

    public final Integer getUserId() {
        return this.userId;
    }

    public final void setUserId(Integer num) {
        this.userId = num;
    }

    public final Long getListingMethodDateCreated() {
        return this.listingMethodDateCreated;
    }

    public final void setListingMethodDateCreated(Long l) {
        this.listingMethodDateCreated = l;
    }

    public final Integer getCountType() {
        return this.countType;
    }

    public final void setCountType(Integer num) {
        this.countType = num;
    }

    public final Integer getMethodType() {
        return this.methodType;
    }

    public final void setMethodType(Integer num) {
        this.methodType = num;
    }

    public final boolean getListingMethodActive() {
        return this.listingMethodActive;
    }

    public final void setListingMethodActive(boolean z) {
        this.listingMethodActive = z;
    }
}
