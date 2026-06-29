package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Encryption.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0018\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J<\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\n\"\u0004\b\u000e\u0010\fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u0004\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\n\"\u0004\b\u0014\u0010\f¨\u0006 "}, d2 = {"Lid/go/bpsfasih/domain/models/Encryption;", "", "assignmentId", "", "isEnscrypt", "", "createdAt", "updatedAt", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getAssignmentId", "()Ljava/lang/String;", "setAssignmentId", "(Ljava/lang/String;)V", "getCreatedAt", "setCreatedAt", "()Ljava/lang/Boolean;", "setEnscrypt", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "getUpdatedAt", "setUpdatedAt", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lid/go/bpsfasih/domain/models/Encryption;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Encryption {
    public static final int $stable = 8;
    private String assignmentId;
    private String createdAt;
    private Boolean isEnscrypt;
    private String updatedAt;

    public static /* synthetic */ Encryption copy$default(Encryption encryption, String str, Boolean bool, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = encryption.assignmentId;
        }
        if ((i & 2) != 0) {
            bool = encryption.isEnscrypt;
        }
        if ((i & 4) != 0) {
            str2 = encryption.createdAt;
        }
        if ((i & 8) != 0) {
            str3 = encryption.updatedAt;
        }
        return encryption.copy(str, bool, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAssignmentId() {
        return this.assignmentId;
    }

    /* renamed from: component2, reason: from getter */
    public final Boolean getIsEnscrypt() {
        return this.isEnscrypt;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCreatedAt() {
        return this.createdAt;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final Encryption copy(String assignmentId, Boolean isEnscrypt, String createdAt, String updatedAt) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        return new Encryption(assignmentId, isEnscrypt, createdAt, updatedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Encryption)) {
            return false;
        }
        Encryption encryption = (Encryption) other;
        return Intrinsics.areEqual(this.assignmentId, encryption.assignmentId) && Intrinsics.areEqual(this.isEnscrypt, encryption.isEnscrypt) && Intrinsics.areEqual(this.createdAt, encryption.createdAt) && Intrinsics.areEqual(this.updatedAt, encryption.updatedAt);
    }

    public int hashCode() {
        int iHashCode = this.assignmentId.hashCode() * 31;
        Boolean bool = this.isEnscrypt;
        int iHashCode2 = (iHashCode + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.createdAt;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.updatedAt;
        return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "Encryption(assignmentId=" + this.assignmentId + ", isEnscrypt=" + this.isEnscrypt + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
    }

    public Encryption(String assignmentId, Boolean bool, String str, String str2) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.assignmentId = assignmentId;
        this.isEnscrypt = bool;
        this.createdAt = str;
        this.updatedAt = str2;
    }

    public final String getAssignmentId() {
        return this.assignmentId;
    }

    public final void setAssignmentId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.assignmentId = str;
    }

    public /* synthetic */ Encryption(String str, Boolean bool, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : bool, (i & 4) != 0 ? "" : str2, (i & 8) != 0 ? "" : str3);
    }

    public final Boolean isEnscrypt() {
        return this.isEnscrypt;
    }

    public final void setEnscrypt(Boolean bool) {
        this.isEnscrypt = bool;
    }

    public final String getCreatedAt() {
        return this.createdAt;
    }

    public final void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public final String getUpdatedAt() {
        return this.updatedAt;
    }

    public final void setUpdatedAt(String str) {
        this.updatedAt = str;
    }
}
