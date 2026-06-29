package id.go.bpsfasih.domain.models;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BpsUser.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\"\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0086\u0001\u0010&\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010+\u001a\u00020\u0006HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u0018\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013¨\u0006-"}, d2 = {"Lid/go/bpsfasih/domain/models/BpsUser;", "", "eselon2", "", "eselon3", "bpsUserId", "", "jabatan", "kodeJabatan", "kodeOrg", "nipBaru", "nipLama", "org", "passwordBpsUser", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBpsUserId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getEselon2", "()Ljava/lang/String;", "getEselon3", "getJabatan", "getKodeJabatan", "getKodeOrg", "getNipBaru", "getNipLama", "getOrg", "getPasswordBpsUser", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/domain/models/BpsUser;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class BpsUser {
    public static final int $stable = 0;

    @SerializedName(DownloadModel.ID)
    private final Integer bpsUserId;
    private final String eselon2;
    private final String eselon3;
    private final String jabatan;
    private final String kodeJabatan;
    private final String kodeOrg;
    private final String nipBaru;
    private final String nipLama;
    private final String org;

    @SerializedName(HintConstants.AUTOFILL_HINT_PASSWORD)
    private final String passwordBpsUser;

    /* renamed from: component1, reason: from getter */
    public final String getEselon2() {
        return this.eselon2;
    }

    /* renamed from: component10, reason: from getter */
    public final String getPasswordBpsUser() {
        return this.passwordBpsUser;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEselon3() {
        return this.eselon3;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getBpsUserId() {
        return this.bpsUserId;
    }

    /* renamed from: component4, reason: from getter */
    public final String getJabatan() {
        return this.jabatan;
    }

    /* renamed from: component5, reason: from getter */
    public final String getKodeJabatan() {
        return this.kodeJabatan;
    }

    /* renamed from: component6, reason: from getter */
    public final String getKodeOrg() {
        return this.kodeOrg;
    }

    /* renamed from: component7, reason: from getter */
    public final String getNipBaru() {
        return this.nipBaru;
    }

    /* renamed from: component8, reason: from getter */
    public final String getNipLama() {
        return this.nipLama;
    }

    /* renamed from: component9, reason: from getter */
    public final String getOrg() {
        return this.org;
    }

    public final BpsUser copy(String eselon2, String eselon3, Integer bpsUserId, String jabatan, String kodeJabatan, String kodeOrg, String nipBaru, String nipLama, String org2, String passwordBpsUser) {
        return new BpsUser(eselon2, eselon3, bpsUserId, jabatan, kodeJabatan, kodeOrg, nipBaru, nipLama, org2, passwordBpsUser);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BpsUser)) {
            return false;
        }
        BpsUser bpsUser = (BpsUser) other;
        return Intrinsics.areEqual(this.eselon2, bpsUser.eselon2) && Intrinsics.areEqual(this.eselon3, bpsUser.eselon3) && Intrinsics.areEqual(this.bpsUserId, bpsUser.bpsUserId) && Intrinsics.areEqual(this.jabatan, bpsUser.jabatan) && Intrinsics.areEqual(this.kodeJabatan, bpsUser.kodeJabatan) && Intrinsics.areEqual(this.kodeOrg, bpsUser.kodeOrg) && Intrinsics.areEqual(this.nipBaru, bpsUser.nipBaru) && Intrinsics.areEqual(this.nipLama, bpsUser.nipLama) && Intrinsics.areEqual(this.org, bpsUser.org) && Intrinsics.areEqual(this.passwordBpsUser, bpsUser.passwordBpsUser);
    }

    public int hashCode() {
        String str = this.eselon2;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.eselon3;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.bpsUserId;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str3 = this.jabatan;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.kodeJabatan;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.kodeOrg;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.nipBaru;
        int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.nipLama;
        int iHashCode8 = (iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.org;
        int iHashCode9 = (iHashCode8 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.passwordBpsUser;
        return iHashCode9 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "BpsUser(eselon2=" + this.eselon2 + ", eselon3=" + this.eselon3 + ", bpsUserId=" + this.bpsUserId + ", jabatan=" + this.jabatan + ", kodeJabatan=" + this.kodeJabatan + ", kodeOrg=" + this.kodeOrg + ", nipBaru=" + this.nipBaru + ", nipLama=" + this.nipLama + ", org=" + this.org + ", passwordBpsUser=" + this.passwordBpsUser + ")";
    }

    public BpsUser(String str, String str2, Integer num, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.eselon2 = str;
        this.eselon3 = str2;
        this.bpsUserId = num;
        this.jabatan = str3;
        this.kodeJabatan = str4;
        this.kodeOrg = str5;
        this.nipBaru = str6;
        this.nipLama = str7;
        this.org = str8;
        this.passwordBpsUser = str9;
    }

    public final String getEselon2() {
        return this.eselon2;
    }

    public final String getEselon3() {
        return this.eselon3;
    }

    public final Integer getBpsUserId() {
        return this.bpsUserId;
    }

    public final String getJabatan() {
        return this.jabatan;
    }

    public final String getKodeJabatan() {
        return this.kodeJabatan;
    }

    public final String getKodeOrg() {
        return this.kodeOrg;
    }

    public final String getNipBaru() {
        return this.nipBaru;
    }

    public final String getNipLama() {
        return this.nipLama;
    }

    public final String getOrg() {
        return this.org;
    }

    public final String getPasswordBpsUser() {
        return this.passwordBpsUser;
    }
}
