package id.go.bpsfasih.data.local.entities;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FieldOfficerEntity.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b6\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Bç\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0018\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001bJ\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010D\u001a\u00020\u0003HÆ\u0003J\t\u0010E\u001a\u00020\u001aHÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010H\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010J\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010K\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0097\u0002\u0010M\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u001aHÆ\u0001J\u0013\u0010N\u001a\u00020\u001a2\b\u0010O\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010P\u001a\u00020QHÖ\u0001J\t\u0010R\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001dR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001dR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001dR\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010$\"\u0004\b%\u0010&R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u001dR\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001dR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001dR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001dR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001dR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001dR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001dR\u0013\u0010\u0015\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u001dR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001dR\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001dR\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001dR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001d¨\u0006S"}, d2 = {"Lid/go/bpsfasih/data/local/entities/FieldOfficerEntity;", "", DownloadModel.ID, "", "nik", HintConstants.AUTOFILL_HINT_USERNAME, "email", "firstName", "lastName", "fullname", "pob", "dob", HintConstants.AUTOFILL_HINT_GENDER, "religionId", HintConstants.AUTOFILL_HINT_PHONE_NUMBER, "phoneNumber2", "regencyId", "regencyName", HintConstants.AUTOFILL_HINT_POSTAL_CODE, "address", "maritalStatusId", "recentEducationId", "roleName", "provinceId", "provinceName", "isSynced", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getAddress", "()Ljava/lang/String;", "getDob", "getEmail", "getFirstName", "getFullname", "getGender", "getId", "()Z", "setSynced", "(Z)V", "getLastName", "getMaritalStatusId", "getNik", "getPhoneNumber", "getPhoneNumber2", "getPob", "getPostalCode", "getProvinceId", "getProvinceName", "getRecentEducationId", "getRegencyId", "getRegencyName", "getReligionId", "getRoleName", "getUsername", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class FieldOfficerEntity {
    public static final int $stable = 8;
    private final String address;
    private final String dob;
    private final String email;
    private final String firstName;
    private final String fullname;
    private final String gender;
    private final String id;
    private boolean isSynced;
    private final String lastName;
    private final String maritalStatusId;
    private final String nik;
    private final String phoneNumber;
    private final String phoneNumber2;
    private final String pob;
    private final String postalCode;
    private final String provinceId;
    private final String provinceName;
    private final String recentEducationId;
    private final String regencyId;
    private final String regencyName;
    private final String religionId;
    private final String roleName;
    private final String username;

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getGender() {
        return this.gender;
    }

    /* renamed from: component11, reason: from getter */
    public final String getReligionId() {
        return this.religionId;
    }

    /* renamed from: component12, reason: from getter */
    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    /* renamed from: component13, reason: from getter */
    public final String getPhoneNumber2() {
        return this.phoneNumber2;
    }

    /* renamed from: component14, reason: from getter */
    public final String getRegencyId() {
        return this.regencyId;
    }

    /* renamed from: component15, reason: from getter */
    public final String getRegencyName() {
        return this.regencyName;
    }

    /* renamed from: component16, reason: from getter */
    public final String getPostalCode() {
        return this.postalCode;
    }

    /* renamed from: component17, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component18, reason: from getter */
    public final String getMaritalStatusId() {
        return this.maritalStatusId;
    }

    /* renamed from: component19, reason: from getter */
    public final String getRecentEducationId() {
        return this.recentEducationId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getNik() {
        return this.nik;
    }

    /* renamed from: component20, reason: from getter */
    public final String getRoleName() {
        return this.roleName;
    }

    /* renamed from: component21, reason: from getter */
    public final String getProvinceId() {
        return this.provinceId;
    }

    /* renamed from: component22, reason: from getter */
    public final String getProvinceName() {
        return this.provinceName;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getIsSynced() {
        return this.isSynced;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUsername() {
        return this.username;
    }

    /* renamed from: component4, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFirstName() {
        return this.firstName;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLastName() {
        return this.lastName;
    }

    /* renamed from: component7, reason: from getter */
    public final String getFullname() {
        return this.fullname;
    }

    /* renamed from: component8, reason: from getter */
    public final String getPob() {
        return this.pob;
    }

    /* renamed from: component9, reason: from getter */
    public final String getDob() {
        return this.dob;
    }

    public final FieldOfficerEntity copy(String id2, String nik, String username, String email, String firstName, String lastName, String fullname, String pob, String dob, String gender, String religionId, String phoneNumber, String phoneNumber2, String regencyId, String regencyName, String postalCode, String address, String maritalStatusId, String recentEducationId, String roleName, String provinceId, String provinceName, boolean isSynced) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(provinceName, "provinceName");
        return new FieldOfficerEntity(id2, nik, username, email, firstName, lastName, fullname, pob, dob, gender, religionId, phoneNumber, phoneNumber2, regencyId, regencyName, postalCode, address, maritalStatusId, recentEducationId, roleName, provinceId, provinceName, isSynced);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FieldOfficerEntity)) {
            return false;
        }
        FieldOfficerEntity fieldOfficerEntity = (FieldOfficerEntity) other;
        return Intrinsics.areEqual(this.id, fieldOfficerEntity.id) && Intrinsics.areEqual(this.nik, fieldOfficerEntity.nik) && Intrinsics.areEqual(this.username, fieldOfficerEntity.username) && Intrinsics.areEqual(this.email, fieldOfficerEntity.email) && Intrinsics.areEqual(this.firstName, fieldOfficerEntity.firstName) && Intrinsics.areEqual(this.lastName, fieldOfficerEntity.lastName) && Intrinsics.areEqual(this.fullname, fieldOfficerEntity.fullname) && Intrinsics.areEqual(this.pob, fieldOfficerEntity.pob) && Intrinsics.areEqual(this.dob, fieldOfficerEntity.dob) && Intrinsics.areEqual(this.gender, fieldOfficerEntity.gender) && Intrinsics.areEqual(this.religionId, fieldOfficerEntity.religionId) && Intrinsics.areEqual(this.phoneNumber, fieldOfficerEntity.phoneNumber) && Intrinsics.areEqual(this.phoneNumber2, fieldOfficerEntity.phoneNumber2) && Intrinsics.areEqual(this.regencyId, fieldOfficerEntity.regencyId) && Intrinsics.areEqual(this.regencyName, fieldOfficerEntity.regencyName) && Intrinsics.areEqual(this.postalCode, fieldOfficerEntity.postalCode) && Intrinsics.areEqual(this.address, fieldOfficerEntity.address) && Intrinsics.areEqual(this.maritalStatusId, fieldOfficerEntity.maritalStatusId) && Intrinsics.areEqual(this.recentEducationId, fieldOfficerEntity.recentEducationId) && Intrinsics.areEqual(this.roleName, fieldOfficerEntity.roleName) && Intrinsics.areEqual(this.provinceId, fieldOfficerEntity.provinceId) && Intrinsics.areEqual(this.provinceName, fieldOfficerEntity.provinceName) && this.isSynced == fieldOfficerEntity.isSynced;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        String str = this.nik;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.username;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.email;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.firstName;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.lastName;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.fullname;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.pob;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.dob;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.gender;
        int iHashCode10 = (iHashCode9 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.religionId;
        int iHashCode11 = (iHashCode10 + (str10 == null ? 0 : str10.hashCode())) * 31;
        String str11 = this.phoneNumber;
        int iHashCode12 = (iHashCode11 + (str11 == null ? 0 : str11.hashCode())) * 31;
        String str12 = this.phoneNumber2;
        int iHashCode13 = (iHashCode12 + (str12 == null ? 0 : str12.hashCode())) * 31;
        String str13 = this.regencyId;
        int iHashCode14 = (iHashCode13 + (str13 == null ? 0 : str13.hashCode())) * 31;
        String str14 = this.regencyName;
        int iHashCode15 = (iHashCode14 + (str14 == null ? 0 : str14.hashCode())) * 31;
        String str15 = this.postalCode;
        int iHashCode16 = (iHashCode15 + (str15 == null ? 0 : str15.hashCode())) * 31;
        String str16 = this.address;
        int iHashCode17 = (iHashCode16 + (str16 == null ? 0 : str16.hashCode())) * 31;
        String str17 = this.maritalStatusId;
        int iHashCode18 = (iHashCode17 + (str17 == null ? 0 : str17.hashCode())) * 31;
        String str18 = this.recentEducationId;
        int iHashCode19 = (iHashCode18 + (str18 == null ? 0 : str18.hashCode())) * 31;
        String str19 = this.roleName;
        int iHashCode20 = (iHashCode19 + (str19 == null ? 0 : str19.hashCode())) * 31;
        String str20 = this.provinceId;
        int iHashCode21 = (((iHashCode20 + (str20 != null ? str20.hashCode() : 0)) * 31) + this.provinceName.hashCode()) * 31;
        boolean z = this.isSynced;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return iHashCode21 + i;
    }

    public String toString() {
        return "FieldOfficerEntity(id=" + this.id + ", nik=" + this.nik + ", username=" + this.username + ", email=" + this.email + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", fullname=" + this.fullname + ", pob=" + this.pob + ", dob=" + this.dob + ", gender=" + this.gender + ", religionId=" + this.religionId + ", phoneNumber=" + this.phoneNumber + ", phoneNumber2=" + this.phoneNumber2 + ", regencyId=" + this.regencyId + ", regencyName=" + this.regencyName + ", postalCode=" + this.postalCode + ", address=" + this.address + ", maritalStatusId=" + this.maritalStatusId + ", recentEducationId=" + this.recentEducationId + ", roleName=" + this.roleName + ", provinceId=" + this.provinceId + ", provinceName=" + this.provinceName + ", isSynced=" + this.isSynced + ")";
    }

    public FieldOfficerEntity(String id2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String provinceName, boolean z) {
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(provinceName, "provinceName");
        this.id = id2;
        this.nik = str;
        this.username = str2;
        this.email = str3;
        this.firstName = str4;
        this.lastName = str5;
        this.fullname = str6;
        this.pob = str7;
        this.dob = str8;
        this.gender = str9;
        this.religionId = str10;
        this.phoneNumber = str11;
        this.phoneNumber2 = str12;
        this.regencyId = str13;
        this.regencyName = str14;
        this.postalCode = str15;
        this.address = str16;
        this.maritalStatusId = str17;
        this.recentEducationId = str18;
        this.roleName = str19;
        this.provinceId = str20;
        this.provinceName = provinceName;
        this.isSynced = z;
    }

    public /* synthetic */ FieldOfficerEntity(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, str20, str21, str22, (i & 4194304) != 0 ? false : z);
    }

    public final String getId() {
        return this.id;
    }

    public final String getNik() {
        return this.nik;
    }

    public final String getUsername() {
        return this.username;
    }

    public final String getEmail() {
        return this.email;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final String getFullname() {
        return this.fullname;
    }

    public final String getPob() {
        return this.pob;
    }

    public final String getDob() {
        return this.dob;
    }

    public final String getGender() {
        return this.gender;
    }

    public final String getReligionId() {
        return this.religionId;
    }

    public final String getPhoneNumber() {
        return this.phoneNumber;
    }

    public final String getPhoneNumber2() {
        return this.phoneNumber2;
    }

    public final String getRegencyId() {
        return this.regencyId;
    }

    public final String getRegencyName() {
        return this.regencyName;
    }

    public final String getPostalCode() {
        return this.postalCode;
    }

    public final String getAddress() {
        return this.address;
    }

    public final String getMaritalStatusId() {
        return this.maritalStatusId;
    }

    public final String getRecentEducationId() {
        return this.recentEducationId;
    }

    public final String getRoleName() {
        return this.roleName;
    }

    public final String getProvinceId() {
        return this.provinceId;
    }

    public final String getProvinceName() {
        return this.provinceName;
    }

    public final boolean isSynced() {
        return this.isSynced;
    }

    public final void setSynced(boolean z) {
        this.isSynced = z;
    }
}
