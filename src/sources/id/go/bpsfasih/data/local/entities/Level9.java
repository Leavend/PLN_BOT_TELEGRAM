package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B7\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\nJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0004HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0004HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006&"}, d2 = {"Lid/go/bpsfasih/data/local/entities/Level9;", "Ljava/io/Serializable;", "()V", "fullCode", "", "code", "level10", "Lid/go/bpsfasih/data/local/entities/Level10;", "name", DownloadModel.ID, "(Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/data/local/entities/Level10;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getFullCode", "setFullCode", "getId", "setId", "getLevel10", "()Lid/go/bpsfasih/data/local/entities/Level10;", "setLevel10", "(Lid/go/bpsfasih/data/local/entities/Level10;)V", "getName", "setName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Level9 implements Serializable {
    public static final int $stable = 8;
    private String code;
    private String fullCode;
    private String id;
    private Level10 level10;
    private String name;

    public static /* synthetic */ Level9 copy$default(Level9 level9, String str, String str2, Level10 level10, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = level9.fullCode;
        }
        if ((i & 2) != 0) {
            str2 = level9.code;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            level10 = level9.level10;
        }
        Level10 level102 = level10;
        if ((i & 8) != 0) {
            str3 = level9.name;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            str4 = level9.id;
        }
        return level9.copy(str, str5, level102, str6, str4);
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
    public final Level10 getLevel10() {
        return this.level10;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Level9 copy(String fullCode, String code, Level10 level10, String name, String id2) {
        return new Level9(fullCode, code, level10, name, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Level9)) {
            return false;
        }
        Level9 level9 = (Level9) other;
        return Intrinsics.areEqual(this.fullCode, level9.fullCode) && Intrinsics.areEqual(this.code, level9.code) && Intrinsics.areEqual(this.level10, level9.level10) && Intrinsics.areEqual(this.name, level9.name) && Intrinsics.areEqual(this.id, level9.id);
    }

    public int hashCode() {
        String str = this.fullCode;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.code;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Level10 level10 = this.level10;
        int iHashCode3 = (iHashCode2 + (level10 == null ? 0 : level10.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.id;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "Level9(fullCode=" + this.fullCode + ", code=" + this.code + ", level10=" + this.level10 + ", name=" + this.name + ", id=" + this.id + ")";
    }

    public Level9(String str, String str2, Level10 level10, String str3, String str4) {
        this.fullCode = str;
        this.code = str2;
        this.level10 = level10;
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

    public final Level10 getLevel10() {
        return this.level10;
    }

    public final void setLevel10(Level10 level10) {
        this.level10 = level10;
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

    public Level9() {
        this(null, null, null, null, null);
    }
}
