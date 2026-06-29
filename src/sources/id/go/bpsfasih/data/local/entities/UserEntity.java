package id.go.bpsfasih.data.local.entities;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.domain.models.BpsUser;
import id.go.bpsfasih.domain.models.NonBpsUser;
import id.go.bpsfasih.domain.models.Province;
import id.go.bpsfasih.domain.models.Regency;
import id.go.bpsfasih.domain.models.Role;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UserEntity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\bF\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0005¢\u0006\u0002\u0010\u0018J\t\u0010H\u001a\u00020\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u0010J\u001a\u00020\u0014HÆ\u0003J\t\u0010K\u001a\u00020\u0014HÆ\u0003J\t\u0010L\u001a\u00020\u0014HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0005HÆ\u0003J\t\u0010O\u001a\u00020\u0005HÆ\u0003J\t\u0010P\u001a\u00020\bHÆ\u0003J\t\u0010Q\u001a\u00020\nHÆ\u0003J\t\u0010R\u001a\u00020\fHÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010$J\u000b\u0010U\u001a\u0004\u0018\u00010\u0010HÆ\u0003J¢\u0001\u0010V\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0005HÆ\u0001¢\u0006\u0002\u0010WJ\u0013\u0010X\u001a\u00020\u00142\b\u0010Y\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010Z\u001a\u00020[HÖ\u0001J\t\u0010\\\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0015\u001a\u00020\u00148\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u0011\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\u0016\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\"\u0010\u000e\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010'\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010)\"\u0004\b-\u0010+R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R \u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u001a\"\u0004\bE\u0010\u001cR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010)\"\u0004\bG\u0010+¨\u0006]"}, d2 = {"Lid/go/bpsfasih/data/local/entities/UserEntity;", "", DownloadModel.ID, "", HintConstants.AUTOFILL_HINT_USERNAME, "", "fullname", "role", "Lid/go/bpsfasih/domain/models/Role;", "regency", "Lid/go/bpsfasih/domain/models/Regency;", "province", "Lid/go/bpsfasih/domain/models/Province;", "email", "dateCreatedUser", "nonBpsUser", "Lid/go/bpsfasih/domain/models/NonBpsUser;", "bpsUser", "Lid/go/bpsfasih/domain/models/BpsUser;", "tempFlag", "", "activeUser", "bpsUsers", HintConstants.AUTOFILL_HINT_PASSWORD, "(JLjava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Role;Lid/go/bpsfasih/domain/models/Regency;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/NonBpsUser;Lid/go/bpsfasih/domain/models/BpsUser;ZZZLjava/lang/String;)V", "getActiveUser", "()Z", "setActiveUser", "(Z)V", "getBpsUser", "()Lid/go/bpsfasih/domain/models/BpsUser;", "setBpsUser", "(Lid/go/bpsfasih/domain/models/BpsUser;)V", "getBpsUsers", "setBpsUsers", "getDateCreatedUser", "()Ljava/lang/Long;", "setDateCreatedUser", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "getFullname", "setFullname", "getId", "()J", "setId", "(J)V", "getNonBpsUser", "()Lid/go/bpsfasih/domain/models/NonBpsUser;", "setNonBpsUser", "(Lid/go/bpsfasih/domain/models/NonBpsUser;)V", "getPassword", "setPassword", "getProvince", "()Lid/go/bpsfasih/domain/models/Province;", "setProvince", "(Lid/go/bpsfasih/domain/models/Province;)V", "getRegency", "()Lid/go/bpsfasih/domain/models/Regency;", "setRegency", "(Lid/go/bpsfasih/domain/models/Regency;)V", "getRole", "()Lid/go/bpsfasih/domain/models/Role;", "setRole", "(Lid/go/bpsfasih/domain/models/Role;)V", "getTempFlag", "setTempFlag", "getUsername", "setUsername", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Role;Lid/go/bpsfasih/domain/models/Regency;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/NonBpsUser;Lid/go/bpsfasih/domain/models/BpsUser;ZZZLjava/lang/String;)Lid/go/bpsfasih/data/local/entities/UserEntity;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class UserEntity {
    public static final int $stable = 8;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private boolean activeUser;
    private BpsUser bpsUser;
    private boolean bpsUsers;

    @SerializedName("dataCreated")
    private Long dateCreatedUser;
    private String email;
    private String fullname;

    @SerializedName(DownloadModel.ID)
    private long id;
    private NonBpsUser nonBpsUser;
    private String password;
    private Province province;
    private Regency regency;
    private Role role;
    private boolean tempFlag;
    private String username;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final BpsUser getBpsUser() {
        return this.bpsUser;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getTempFlag() {
        return this.tempFlag;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getActiveUser() {
        return this.activeUser;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getBpsUsers() {
        return this.bpsUsers;
    }

    /* renamed from: component14, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFullname() {
        return this.fullname;
    }

    /* renamed from: component4, reason: from getter */
    public final Role getRole() {
        return this.role;
    }

    /* renamed from: component5, reason: from getter */
    public final Regency getRegency() {
        return this.regency;
    }

    /* renamed from: component6, reason: from getter */
    public final Province getProvince() {
        return this.province;
    }

    /* renamed from: component7, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component8, reason: from getter */
    public final Long getDateCreatedUser() {
        return this.dateCreatedUser;
    }

    /* renamed from: component9, reason: from getter */
    public final NonBpsUser getNonBpsUser() {
        return this.nonBpsUser;
    }

    public final UserEntity copy(long id2, String username, String fullname, Role role, Regency regency, Province province, String email, Long dateCreatedUser, NonBpsUser nonBpsUser, BpsUser bpsUser, boolean tempFlag, boolean activeUser, boolean bpsUsers, String password) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullname, "fullname");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(regency, "regency");
        Intrinsics.checkNotNullParameter(province, "province");
        Intrinsics.checkNotNullParameter(password, "password");
        return new UserEntity(id2, username, fullname, role, regency, province, email, dateCreatedUser, nonBpsUser, bpsUser, tempFlag, activeUser, bpsUsers, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof UserEntity)) {
            return false;
        }
        UserEntity userEntity = (UserEntity) other;
        return this.id == userEntity.id && Intrinsics.areEqual(this.username, userEntity.username) && Intrinsics.areEqual(this.fullname, userEntity.fullname) && Intrinsics.areEqual(this.role, userEntity.role) && Intrinsics.areEqual(this.regency, userEntity.regency) && Intrinsics.areEqual(this.province, userEntity.province) && Intrinsics.areEqual(this.email, userEntity.email) && Intrinsics.areEqual(this.dateCreatedUser, userEntity.dateCreatedUser) && Intrinsics.areEqual(this.nonBpsUser, userEntity.nonBpsUser) && Intrinsics.areEqual(this.bpsUser, userEntity.bpsUser) && this.tempFlag == userEntity.tempFlag && this.activeUser == userEntity.activeUser && this.bpsUsers == userEntity.bpsUsers && Intrinsics.areEqual(this.password, userEntity.password);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((Long.hashCode(this.id) * 31) + this.username.hashCode()) * 31) + this.fullname.hashCode()) * 31) + this.role.hashCode()) * 31) + this.regency.hashCode()) * 31) + this.province.hashCode()) * 31;
        String str = this.email;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.dateCreatedUser;
        int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        NonBpsUser nonBpsUser = this.nonBpsUser;
        int iHashCode4 = (iHashCode3 + (nonBpsUser == null ? 0 : nonBpsUser.hashCode())) * 31;
        BpsUser bpsUser = this.bpsUser;
        int iHashCode5 = (iHashCode4 + (bpsUser != null ? bpsUser.hashCode() : 0)) * 31;
        boolean z = this.tempFlag;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode5 + i) * 31;
        boolean z2 = this.activeUser;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.bpsUsers;
        return ((i4 + (z3 ? 1 : z3 ? 1 : 0)) * 31) + this.password.hashCode();
    }

    public String toString() {
        return "UserEntity(id=" + this.id + ", username=" + this.username + ", fullname=" + this.fullname + ", role=" + this.role + ", regency=" + this.regency + ", province=" + this.province + ", email=" + this.email + ", dateCreatedUser=" + this.dateCreatedUser + ", nonBpsUser=" + this.nonBpsUser + ", bpsUser=" + this.bpsUser + ", tempFlag=" + this.tempFlag + ", activeUser=" + this.activeUser + ", bpsUsers=" + this.bpsUsers + ", password=" + this.password + ")";
    }

    public UserEntity(long j, String username, String fullname, Role role, Regency regency, Province province, String str, Long l, NonBpsUser nonBpsUser, BpsUser bpsUser, boolean z, boolean z2, boolean z3, String password) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullname, "fullname");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(regency, "regency");
        Intrinsics.checkNotNullParameter(province, "province");
        Intrinsics.checkNotNullParameter(password, "password");
        this.id = j;
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.regency = regency;
        this.province = province;
        this.email = str;
        this.dateCreatedUser = l;
        this.nonBpsUser = nonBpsUser;
        this.bpsUser = bpsUser;
        this.tempFlag = z;
        this.activeUser = z2;
        this.bpsUsers = z3;
        this.password = password;
    }

    public final long getId() {
        return this.id;
    }

    public final void setId(long j) {
        this.id = j;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.username = str;
    }

    public final String getFullname() {
        return this.fullname;
    }

    public final void setFullname(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fullname = str;
    }

    public final Role getRole() {
        return this.role;
    }

    public final void setRole(Role role) {
        Intrinsics.checkNotNullParameter(role, "<set-?>");
        this.role = role;
    }

    public final Regency getRegency() {
        return this.regency;
    }

    public final void setRegency(Regency regency) {
        Intrinsics.checkNotNullParameter(regency, "<set-?>");
        this.regency = regency;
    }

    public final Province getProvince() {
        return this.province;
    }

    public final void setProvince(Province province) {
        Intrinsics.checkNotNullParameter(province, "<set-?>");
        this.province = province;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final Long getDateCreatedUser() {
        return this.dateCreatedUser;
    }

    public final void setDateCreatedUser(Long l) {
        this.dateCreatedUser = l;
    }

    public final NonBpsUser getNonBpsUser() {
        return this.nonBpsUser;
    }

    public final void setNonBpsUser(NonBpsUser nonBpsUser) {
        this.nonBpsUser = nonBpsUser;
    }

    public final BpsUser getBpsUser() {
        return this.bpsUser;
    }

    public final void setBpsUser(BpsUser bpsUser) {
        this.bpsUser = bpsUser;
    }

    public final boolean getTempFlag() {
        return this.tempFlag;
    }

    public final void setTempFlag(boolean z) {
        this.tempFlag = z;
    }

    public final boolean getActiveUser() {
        return this.activeUser;
    }

    public final void setActiveUser(boolean z) {
        this.activeUser = z;
    }

    public final boolean getBpsUsers() {
        return this.bpsUsers;
    }

    public final void setBpsUsers(boolean z) {
        this.bpsUsers = z;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }
}
