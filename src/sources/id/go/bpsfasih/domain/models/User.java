package id.go.bpsfasih.domain.models;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: User.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b)\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0014¢\u0006\u0002\u0010\u0017J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0012HÆ\u0003J\t\u0010.\u001a\u00020\u0014HÆ\u0003J\t\u0010/\u001a\u00020\u0014HÆ\u0003J\t\u00100\u001a\u00020\u0014HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0005HÆ\u0003J\t\u00103\u001a\u00020\bHÆ\u0003J\t\u00104\u001a\u00020\nHÆ\u0003J\t\u00105\u001a\u00020\fHÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u001bJ\u000b\u00108\u001a\u0004\u0018\u00010\u0010HÆ\u0003J\u0098\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0014HÆ\u0001¢\u0006\u0002\u0010:J\u0013\u0010;\u001a\u00020\u00142\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020>HÖ\u0001J\t\u0010?\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0015\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\"R\u0011\u0010\u0016\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\"R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001e¨\u0006@"}, d2 = {"Lid/go/bpsfasih/domain/models/User;", "", DownloadModel.ID, "", HintConstants.AUTOFILL_HINT_USERNAME, "", "fullname", "role", "Lid/go/bpsfasih/domain/models/Role;", "regency", "Lid/go/bpsfasih/domain/models/Regency;", "province", "Lid/go/bpsfasih/domain/models/Province;", "email", "dateCreated", "nonBpsUser", "Lid/go/bpsfasih/domain/models/NonBpsUser;", "bpsUser", "Lid/go/bpsfasih/domain/models/BpsUser;", "isTemp", "", "isActive", "isBpsUser", "(JLjava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Role;Lid/go/bpsfasih/domain/models/Regency;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/NonBpsUser;Lid/go/bpsfasih/domain/models/BpsUser;ZZZ)V", "getBpsUser", "()Lid/go/bpsfasih/domain/models/BpsUser;", "getDateCreated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getEmail", "()Ljava/lang/String;", "getFullname", "getId", "()J", "()Z", "getNonBpsUser", "()Lid/go/bpsfasih/domain/models/NonBpsUser;", "getProvince", "()Lid/go/bpsfasih/domain/models/Province;", "getRegency", "()Lid/go/bpsfasih/domain/models/Regency;", "getRole", "()Lid/go/bpsfasih/domain/models/Role;", "getUsername", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Role;Lid/go/bpsfasih/domain/models/Regency;Lid/go/bpsfasih/domain/models/Province;Ljava/lang/String;Ljava/lang/Long;Lid/go/bpsfasih/domain/models/NonBpsUser;Lid/go/bpsfasih/domain/models/BpsUser;ZZZ)Lid/go/bpsfasih/domain/models/User;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class User {
    public static final int $stable = 8;
    private final BpsUser bpsUser;
    private final Long dateCreated;
    private final String email;
    private final String fullname;
    private final long id;
    private final boolean isActive;
    private final boolean isBpsUser;
    private final boolean isTemp;
    private final NonBpsUser nonBpsUser;
    private final Province province;
    private final Regency regency;
    private final Role role;
    private final String username;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final BpsUser getBpsUser() {
        return this.bpsUser;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getIsTemp() {
        return this.isTemp;
    }

    /* renamed from: component12, reason: from getter */
    public final boolean getIsActive() {
        return this.isActive;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getIsBpsUser() {
        return this.isBpsUser;
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
    public final Long getDateCreated() {
        return this.dateCreated;
    }

    /* renamed from: component9, reason: from getter */
    public final NonBpsUser getNonBpsUser() {
        return this.nonBpsUser;
    }

    public final User copy(long id2, String username, String fullname, Role role, Regency regency, Province province, String email, Long dateCreated, NonBpsUser nonBpsUser, BpsUser bpsUser, boolean isTemp, boolean isActive, boolean isBpsUser) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullname, "fullname");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(regency, "regency");
        Intrinsics.checkNotNullParameter(province, "province");
        return new User(id2, username, fullname, role, regency, province, email, dateCreated, nonBpsUser, bpsUser, isTemp, isActive, isBpsUser);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User user = (User) other;
        return this.id == user.id && Intrinsics.areEqual(this.username, user.username) && Intrinsics.areEqual(this.fullname, user.fullname) && Intrinsics.areEqual(this.role, user.role) && Intrinsics.areEqual(this.regency, user.regency) && Intrinsics.areEqual(this.province, user.province) && Intrinsics.areEqual(this.email, user.email) && Intrinsics.areEqual(this.dateCreated, user.dateCreated) && Intrinsics.areEqual(this.nonBpsUser, user.nonBpsUser) && Intrinsics.areEqual(this.bpsUser, user.bpsUser) && this.isTemp == user.isTemp && this.isActive == user.isActive && this.isBpsUser == user.isBpsUser;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((((((((((Long.hashCode(this.id) * 31) + this.username.hashCode()) * 31) + this.fullname.hashCode()) * 31) + this.role.hashCode()) * 31) + this.regency.hashCode()) * 31) + this.province.hashCode()) * 31;
        String str = this.email;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.dateCreated;
        int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
        NonBpsUser nonBpsUser = this.nonBpsUser;
        int iHashCode4 = (iHashCode3 + (nonBpsUser == null ? 0 : nonBpsUser.hashCode())) * 31;
        BpsUser bpsUser = this.bpsUser;
        int iHashCode5 = (iHashCode4 + (bpsUser != null ? bpsUser.hashCode() : 0)) * 31;
        boolean z = this.isTemp;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode5 + i) * 31;
        boolean z2 = this.isActive;
        int i3 = z2;
        if (z2 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        boolean z3 = this.isBpsUser;
        return i4 + (z3 ? 1 : z3 ? 1 : 0);
    }

    public String toString() {
        return "User(id=" + this.id + ", username=" + this.username + ", fullname=" + this.fullname + ", role=" + this.role + ", regency=" + this.regency + ", province=" + this.province + ", email=" + this.email + ", dateCreated=" + this.dateCreated + ", nonBpsUser=" + this.nonBpsUser + ", bpsUser=" + this.bpsUser + ", isTemp=" + this.isTemp + ", isActive=" + this.isActive + ", isBpsUser=" + this.isBpsUser + ")";
    }

    public User(long j, String username, String fullname, Role role, Regency regency, Province province, String str, Long l, NonBpsUser nonBpsUser, BpsUser bpsUser, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(fullname, "fullname");
        Intrinsics.checkNotNullParameter(role, "role");
        Intrinsics.checkNotNullParameter(regency, "regency");
        Intrinsics.checkNotNullParameter(province, "province");
        this.id = j;
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.regency = regency;
        this.province = province;
        this.email = str;
        this.dateCreated = l;
        this.nonBpsUser = nonBpsUser;
        this.bpsUser = bpsUser;
        this.isTemp = z;
        this.isActive = z2;
        this.isBpsUser = z3;
    }

    public final long getId() {
        return this.id;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getFullname() {
        return this.fullname;
    }

    public final Role getRole() {
        return this.role;
    }

    public final Regency getRegency() {
        return this.regency;
    }

    public final Province getProvince() {
        return this.province;
    }

    public final String getEmail() {
        return this.email;
    }

    public final Long getDateCreated() {
        return this.dateCreated;
    }

    public final NonBpsUser getNonBpsUser() {
        return this.nonBpsUser;
    }

    public final BpsUser getBpsUser() {
        return this.bpsUser;
    }

    public final boolean isTemp() {
        return this.isTemp;
    }

    public final boolean isActive() {
        return this.isActive;
    }

    public final boolean isBpsUser() {
        return this.isBpsUser;
    }
}
