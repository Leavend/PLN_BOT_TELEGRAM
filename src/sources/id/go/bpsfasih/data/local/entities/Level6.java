package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B7\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\nJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006&"}, d2 = {"Lid/go/bpsfasih/data/local/entities/Level6;", "Ljava/io/Serializable;", "()V", "fullCode", "", "code", "level7", "Lid/go/bpsfasih/data/local/entities/Level7;", "name", DownloadModel.ID, "(Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Level7;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getFullCode", "setFullCode", "getId", "setId", "getLevel7", "()Lid/go/bpsfasih/data/local/entities/Level7;", "setLevel7", "(Lid/go/bpsfasih/data/local/entities/Level7;)V", "getName", "setName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Level6 implements Serializable {
    public static final int $stable = 8;
    private String code;
    private String fullCode;
    private String id;
    private Level7 level7;
    private String name;

    public static /* synthetic */ Level6 copy$default(Level6 level6, String str, String str2, Level7 level7, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = level6.fullCode;
        }
        if ((i & 2) != 0) {
            str2 = level6.code;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            level7 = level6.level7;
        }
        Level7 level72 = level7;
        if ((i & 8) != 0) {
            str3 = level6.name;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = level6.id;
        }
        return level6.copy(str, str5, level72, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFullCode() {
        return this.fullCode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component3, reason: from getter */
    public final Level7 getLevel7() {
        return this.level7;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Level6 copy(String fullCode, String code, Level7 level7, String name, String id2) {
        return new Level6(fullCode, code, level7, name, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Level6)) {
            return false;
        }
        Level6 level6 = (Level6) other;
        return Intrinsics.areEqual(this.fullCode, level6.fullCode) && Intrinsics.areEqual(this.code, level6.code) && Intrinsics.areEqual(this.level7, level6.level7) && Intrinsics.areEqual(this.name, level6.name) && Intrinsics.areEqual(this.id, level6.id);
    }

    public int hashCode() {
        String str = this.fullCode;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.code;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Level7 level7 = this.level7;
        int iHashCode3 = (iHashCode2 + (level7 == null ? 0 : level7.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.id;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "Level6(fullCode=" + this.fullCode + ", code=" + this.code + ", level7=" + this.level7 + ", name=" + this.name + ", id=" + this.id + ")";
    }

    public Level6(String str, String str2, Level7 level7, String str3, String str4) {
        this.fullCode = str;
        this.code = str2;
        this.level7 = level7;
        this.name = str3;
        this.id = str4;
    }

    public final String getFullCode() {
        return this.fullCode;
    }

    public final void setFullCode(String str) {
        this.fullCode = str;
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final Level7 getLevel7() {
        return this.level7;
    }

    public final void setLevel7(Level7 level7) {
        this.level7 = level7;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        this.id = str;
    }

    public Level6() {
        this(null, null, null, null, null);
    }
}
