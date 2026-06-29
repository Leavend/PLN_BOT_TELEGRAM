package id.go.bpsfasih.data.local.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.domain.models.Regency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Disctrict.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0002\u0010\u0016J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\fHÆ\u0003JT\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fHÆ\u0001¢\u0006\u0002\u0010\"J\u0013\u0010#\u001a\u00020\u00032\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\tHÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\n\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006'"}, d2 = {"Lid/go/bpsfasih/data/local/models/Disctrict;", "", "disc_Active", "", "disc_Code", "", "disc_DateCreated", "", "disc_Id", "", "disc_Name", "regency", "Lid/go/bpsfasih/domain/models/Regency;", "(ZLjava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Regency;)V", "getDisc_Active", "()Z", "getDisc_Code", "()Ljava/lang/String;", "getDisc_DateCreated", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDisc_Id", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDisc_Name", "getRegency", "()Lid/go/bpsfasih/domain/models/Regency;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(ZLjava/lang/String;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Lid/go/bpsfasih/domain/models/Regency;)Lid/go/bpsfasih/data/local/models/Disctrict;", "equals", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Disctrict {
    public static final int $stable = 8;

    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
    private final boolean disc_Active;

    @SerializedName("code")
    private final String disc_Code;

    @SerializedName("dateCreated")
    private final Long disc_DateCreated;

    @SerializedName(DownloadModel.ID)
    private final Integer disc_Id;

    @SerializedName("name")
    private final String disc_Name;
    private final Regency regency;

    public static /* synthetic */ Disctrict copy$default(Disctrict disctrict, boolean z, String str, Long l, Integer num, String str2, Regency regency, int i, Object obj) {
        if ((i & 1) != 0) {
            z = disctrict.disc_Active;
        }
        if ((i & 2) != 0) {
            str = disctrict.disc_Code;
        }
        String str3 = str;
        if ((i & 4) != 0) {
            l = disctrict.disc_DateCreated;
        }
        Long l2 = l;
        if ((i & 8) != 0) {
            num = disctrict.disc_Id;
        }
        Integer num2 = num;
        if ((i & 16) != 0) {
            str2 = disctrict.disc_Name;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            regency = disctrict.regency;
        }
        return disctrict.copy(z, str3, l2, num2, str4, regency);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getDisc_Active() {
        return this.disc_Active;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisc_Code() {
        return this.disc_Code;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getDisc_DateCreated() {
        return this.disc_DateCreated;
    }

    /* renamed from: component4, reason: from getter */
    public final Integer getDisc_Id() {
        return this.disc_Id;
    }

    /* renamed from: component5, reason: from getter */
    public final String getDisc_Name() {
        return this.disc_Name;
    }

    /* renamed from: component6, reason: from getter */
    public final Regency getRegency() {
        return this.regency;
    }

    public final Disctrict copy(boolean disc_Active, String disc_Code, Long disc_DateCreated, Integer disc_Id, String disc_Name, Regency regency) {
        return new Disctrict(disc_Active, disc_Code, disc_DateCreated, disc_Id, disc_Name, regency);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Disctrict)) {
            return false;
        }
        Disctrict disctrict = (Disctrict) other;
        return this.disc_Active == disctrict.disc_Active && Intrinsics.areEqual(this.disc_Code, disctrict.disc_Code) && Intrinsics.areEqual(this.disc_DateCreated, disctrict.disc_DateCreated) && Intrinsics.areEqual(this.disc_Id, disctrict.disc_Id) && Intrinsics.areEqual(this.disc_Name, disctrict.disc_Name) && Intrinsics.areEqual(this.regency, disctrict.regency);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    public int hashCode() {
        boolean z = this.disc_Active;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.disc_Code;
        int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
        Long l = this.disc_DateCreated;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        Integer num = this.disc_Id;
        int iHashCode3 = (iHashCode2 + (num == null ? 0 : num.hashCode())) * 31;
        String str2 = this.disc_Name;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Regency regency = this.regency;
        return iHashCode4 + (regency != null ? regency.hashCode() : 0);
    }

    public String toString() {
        return "Disctrict(disc_Active=" + this.disc_Active + ", disc_Code=" + this.disc_Code + ", disc_DateCreated=" + this.disc_DateCreated + ", disc_Id=" + this.disc_Id + ", disc_Name=" + this.disc_Name + ", regency=" + this.regency + ")";
    }

    public Disctrict(boolean z, String str, Long l, Integer num, String str2, Regency regency) {
        this.disc_Active = z;
        this.disc_Code = str;
        this.disc_DateCreated = l;
        this.disc_Id = num;
        this.disc_Name = str2;
        this.regency = regency;
    }

    public final boolean getDisc_Active() {
        return this.disc_Active;
    }

    public final String getDisc_Code() {
        return this.disc_Code;
    }

    public final Long getDisc_DateCreated() {
        return this.disc_DateCreated;
    }

    public final Integer getDisc_Id() {
        return this.disc_Id;
    }

    public final String getDisc_Name() {
        return this.disc_Name;
    }

    public final Regency getRegency() {
        return this.regency;
    }
}
