package id.go.bpsfasih.domain.models;

import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.annotations.SerializedName;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NonBpsUser.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\bJ\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J&\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\"\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u000b\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/domain/models/NonBpsUser;", "", "nonBpsUserId", "", HintConstants.AUTOFILL_HINT_PASSWORD, "", "(Ljava/lang/Integer;Ljava/lang/String;)V", "getNonBpsUserId", "()Ljava/lang/Integer;", "setNonBpsUserId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/lang/String;)Lid/go/bpsfasih/domain/models/NonBpsUser;", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class NonBpsUser {
    public static final int $stable = 8;

    @SerializedName(DownloadModel.ID)
    private Integer nonBpsUserId;
    private String password;

    public static /* synthetic */ NonBpsUser copy$default(NonBpsUser nonBpsUser, Integer num, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            num = nonBpsUser.nonBpsUserId;
        }
        if ((i & 2) != 0) {
            str = nonBpsUser.password;
        }
        return nonBpsUser.copy(num, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getNonBpsUserId() {
        return this.nonBpsUserId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPassword() {
        return this.password;
    }

    public final NonBpsUser copy(Integer nonBpsUserId, String password) {
        return new NonBpsUser(nonBpsUserId, password);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NonBpsUser)) {
            return false;
        }
        NonBpsUser nonBpsUser = (NonBpsUser) other;
        return Intrinsics.areEqual(this.nonBpsUserId, nonBpsUser.nonBpsUserId) && Intrinsics.areEqual(this.password, nonBpsUser.password);
    }

    public int hashCode() {
        Integer num = this.nonBpsUserId;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.password;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "NonBpsUser(nonBpsUserId=" + this.nonBpsUserId + ", password=" + this.password + ")";
    }

    public NonBpsUser(Integer num, String str) {
        this.nonBpsUserId = num;
        this.password = str;
    }

    public final Integer getNonBpsUserId() {
        return this.nonBpsUserId;
    }

    public final void setNonBpsUserId(Integer num) {
        this.nonBpsUserId = num;
    }

    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(String str) {
        this.password = str;
    }
}
