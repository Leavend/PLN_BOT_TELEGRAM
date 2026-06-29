package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Survey.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b/\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\b\b\u0002\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\u0006\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u0014J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\t\u0010&\u001a\u00020\tHÆ\u0003J\t\u0010'\u001a\u00020\tHÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0005HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\t\u00100\u001a\u00020\u0005HÆ\u0003J\t\u00101\u001a\u00020\tHÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\u009f\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u0003HÆ\u0001J\u0013\u00104\u001a\u00020\t2\b\u00105\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00106\u001a\u00020\u0005HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u000f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0016R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0016R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001fR\u0011\u0010\u0013\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001bR\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b¨\u00068"}, d2 = {"Lid/go/bpsfasih/domain/models/UserRole;", "", DownloadModel.ID, "", "sequence", "", "description", "surveyRoleGroupId", "isApproving", "", "isPencacah", "smallestAreaOrder", "canDrawSample", "canChangeMode", "isAdminTicket", "canAccessTicket", "canEdit", "createdAt", "updatedAt", "surveyId", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ZZIZZZZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCanAccessTicket", "()Z", "getCanChangeMode", "getCanDrawSample", "getCanEdit", "getCreatedAt", "()Ljava/lang/String;", "getDescription", "getId", "getSequence", "()I", "getSmallestAreaOrder", "getSurveyId", "getSurveyRoleGroupId", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UserRole {
    public static final int $stable = 0;
    private final boolean canAccessTicket;
    private final boolean canChangeMode;
    private final boolean canDrawSample;
    private final boolean canEdit;
    private final String createdAt;
    private final String description;
    private final String id;
    private final boolean isAdminTicket;
    private final boolean isApproving;
    private final boolean isPencacah;
    private final int sequence;
    private final int smallestAreaOrder;
    private final String surveyId;
    private final String surveyRoleGroupId;
    private final String updatedAt;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final boolean getIsAdminTicket() {
        return this.isAdminTicket;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getCanAccessTicket() {
        return this.canAccessTicket;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getCanEdit() {
        return this.canEdit;
    }

    /* renamed from: component13, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component14, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    /* renamed from: component15, reason: from getter */
    public final String getSurveyId() {
        return this.surveyId;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSequence() {
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
    public final boolean getIsApproving() {
        return this.isApproving;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsPencacah() {
        return this.isPencacah;
    }

    /* renamed from: component7, reason: from getter */
    public final int getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    /* renamed from: component8, reason: from getter */
    public final boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    /* renamed from: component9, reason: from getter */
    public final boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    public final UserRole copy(String id2, int sequence, String description, String surveyRoleGroupId, boolean isApproving, boolean isPencacah, int smallestAreaOrder, boolean canDrawSample, boolean canChangeMode, boolean isAdminTicket, boolean canAccessTicket, boolean canEdit, String createdAt, String updatedAt, String surveyId) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(surveyRoleGroupId, "surveyRoleGroupId");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        return new UserRole(id2, sequence, description, surveyRoleGroupId, isApproving, isPencacah, smallestAreaOrder, canDrawSample, canChangeMode, isAdminTicket, canAccessTicket, canEdit, createdAt, updatedAt, surveyId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserRole)) {
            return false;
        }
        UserRole userRole = (UserRole) other;
        return Intrinsics.areEqual(this.id, userRole.id) && this.sequence == userRole.sequence && Intrinsics.areEqual(this.description, userRole.description) && Intrinsics.areEqual(this.surveyRoleGroupId, userRole.surveyRoleGroupId) && this.isApproving == userRole.isApproving && this.isPencacah == userRole.isPencacah && this.smallestAreaOrder == userRole.smallestAreaOrder && this.canDrawSample == userRole.canDrawSample && this.canChangeMode == userRole.canChangeMode && this.isAdminTicket == userRole.isAdminTicket && this.canAccessTicket == userRole.canAccessTicket && this.canEdit == userRole.canEdit && Intrinsics.areEqual(this.createdAt, userRole.createdAt) && Intrinsics.areEqual(this.updatedAt, userRole.updatedAt) && Intrinsics.areEqual(this.surveyId, userRole.surveyId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((this.id.hashCode() * 31) + Integer.hashCode(this.sequence)) * 31) + this.description.hashCode()) * 31) + this.surveyRoleGroupId.hashCode()) * 31;
        boolean z = this.isApproving;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.isPencacah;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int iHashCode2 = (((i2 + i3) * 31) + Integer.hashCode(this.smallestAreaOrder)) * 31;
        boolean z3 = this.canDrawSample;
        int i4 = z3;
        if (z3 != 0) {
            i4 = 1;
        }
        int i5 = (iHashCode2 + i4) * 31;
        boolean z4 = this.canChangeMode;
        int i6 = z4;
        if (z4 != 0) {
            i6 = 1;
        }
        int i7 = (i5 + i6) * 31;
        boolean z5 = this.isAdminTicket;
        int i8 = z5;
        if (z5 != 0) {
            i8 = 1;
        }
        int i9 = (i7 + i8) * 31;
        boolean z6 = this.canAccessTicket;
        int i10 = z6;
        if (z6 != 0) {
            i10 = 1;
        }
        int i11 = (i9 + i10) * 31;
        boolean z7 = this.canEdit;
        return ((((((i11 + (z7 ? 1 : z7 ? 1 : 0)) * 31) + this.createdAt.hashCode()) * 31) + this.updatedAt.hashCode()) * 31) + this.surveyId.hashCode();
    }

    public String toString() {
        return "UserRole(id=" + this.id + ", sequence=" + this.sequence + ", description=" + this.description + ", surveyRoleGroupId=" + this.surveyRoleGroupId + ", isApproving=" + this.isApproving + ", isPencacah=" + this.isPencacah + ", smallestAreaOrder=" + this.smallestAreaOrder + ", canDrawSample=" + this.canDrawSample + ", canChangeMode=" + this.canChangeMode + ", isAdminTicket=" + this.isAdminTicket + ", canAccessTicket=" + this.canAccessTicket + ", canEdit=" + this.canEdit + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", surveyId=" + this.surveyId + ")";
    }

    public UserRole(String id2, int i, String description, String surveyRoleGroupId, boolean z, boolean z2, int i2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String createdAt, String updatedAt, String surveyId) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(description, "description");
        Intrinsics.checkNotNullParameter(surveyRoleGroupId, "surveyRoleGroupId");
        Intrinsics.checkNotNullParameter(createdAt, "createdAt");
        Intrinsics.checkNotNullParameter(updatedAt, "updatedAt");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        this.id = id2;
        this.sequence = i;
        this.description = description;
        this.surveyRoleGroupId = surveyRoleGroupId;
        this.isApproving = z;
        this.isPencacah = z2;
        this.smallestAreaOrder = i2;
        this.canDrawSample = z3;
        this.canChangeMode = z4;
        this.isAdminTicket = z5;
        this.canAccessTicket = z6;
        this.canEdit = z7;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.surveyId = surveyId;
    }

    public /* synthetic */ UserRole(String str, int i, String str2, String str3, boolean z, boolean z2, int i2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, String str4, String str5, String str6, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, str2, str3, (i3 & 16) != 0 ? false : z, (i3 & 32) != 0 ? false : z2, i2, (i3 & 128) != 0 ? false : z3, (i3 & 256) != 0 ? false : z4, (i3 & 512) != 0 ? false : z5, (i3 & 1024) != 0 ? false : z6, (i3 & 2048) != 0 ? false : z7, str4, str5, str6);
    }

    public final String getId() {
        return this.id;
    }

    public final int getSequence() {
        return this.sequence;
    }

    public final String getDescription() {
        return this.description;
    }

    public final String getSurveyRoleGroupId() {
        return this.surveyRoleGroupId;
    }

    public final boolean isApproving() {
        return this.isApproving;
    }

    public final boolean isPencacah() {
        return this.isPencacah;
    }

    public final int getSmallestAreaOrder() {
        return this.smallestAreaOrder;
    }

    public final boolean getCanDrawSample() {
        return this.canDrawSample;
    }

    public final boolean getCanChangeMode() {
        return this.canChangeMode;
    }

    public final boolean isAdminTicket() {
        return this.isAdminTicket;
    }

    public final boolean getCanAccessTicket() {
        return this.canAccessTicket;
    }

    public final boolean getCanEdit() {
        return this.canEdit;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }
}
