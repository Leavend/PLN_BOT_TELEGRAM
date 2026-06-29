package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Role.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\bHÆ\u0003J<\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020\b2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\u0003HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0017\u0010\u000f\"\u0004\b\u0018\u0010\u0011¨\u0006#"}, d2 = {"Lid/go/bpsfasih/domain/models/Role;", "", "roleId", "", "roleName", "", "roleType", "roleActive", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Z)V", "getRoleActive", "()Z", "setRoleActive", "(Z)V", "getRoleId", "()Ljava/lang/Integer;", "setRoleId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getRoleName", "()Ljava/lang/String;", "setRoleName", "(Ljava/lang/String;)V", "getRoleType", "setRoleType", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Z)Lid/go/bpsfasih/domain/models/Role;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Role {
    public static final int $stable = 8;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean roleActive;

    @SerializedName(DownloadModel.ID)
    private Integer roleId;

    @SerializedName("name")
    private String roleName;
    private Integer roleType;

    public static /* synthetic */ Role copy$default(Role role, Integer num, String str, Integer num2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = role.roleId;
        }
        if ((i & 2) != 0) {
            str = role.roleName;
        }
        if ((i & 4) != 0) {
            num2 = role.roleType;
        }
        if ((i & 8) != 0) {
            z = role.roleActive;
        }
        return role.copy(num, str, num2, z);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getRoleId() {
        return this.roleId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getRoleType() {
        return this.roleType;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getRoleActive() {
        return this.roleActive;
    }

    public final Role copy(Integer roleId, String roleName, Integer roleType, boolean roleActive) {
        return new Role(roleId, roleName, roleType, roleActive);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Role)) {
            return false;
        }
        Role role = (Role) other;
        return Intrinsics.areEqual(this.roleId, role.roleId) && Intrinsics.areEqual(this.roleName, role.roleName) && Intrinsics.areEqual(this.roleType, role.roleType) && this.roleActive == role.roleActive;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.roleId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.roleName;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num2 = this.roleType;
        int iHashCode3 = (iHashCode2 + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z = this.roleActive;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode3 + i;
    }

    public String toString() {
        return "Role(roleId=" + this.roleId + ", roleName=" + this.roleName + ", roleType=" + this.roleType + ", roleActive=" + this.roleActive + ")";
    }

    public Role(Integer num, String str, Integer num2, boolean z) {
        this.roleId = num;
        this.roleName = str;
        this.roleType = num2;
        this.roleActive = z;
    }

    public final Integer getRoleId() {
        return this.roleId;
    }

    public final void setRoleId(Integer num) {
        this.roleId = num;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final void setRoleName(String str) {
        this.roleName = str;
    }

    public final Integer getRoleType() {
        return this.roleType;
    }

    public final void setRoleType(Integer num) {
        this.roleType = num;
    }

    public final boolean getRoleActive() {
        return this.roleActive;
    }

    public final void setRoleActive(boolean z) {
        this.roleActive = z;
    }
}
