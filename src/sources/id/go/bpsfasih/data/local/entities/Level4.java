package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B7\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\nJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0006HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0006HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000e¨\u0006&"}, d2 = {"Lid/go/bpsfasih/data/local/entities/Level4;", "Ljava/io/Serializable;", "()V", "level5", "Lid/go/bpsfasih/data/local/entities/Level5;", "fullCode", "", "code", "name", DownloadModel.ID, "(Lid/go/bpsfasih/data/local/entities/Level5;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "setCode", "(Ljava/lang/String;)V", "getFullCode", "setFullCode", "getId", "setId", "getLevel5", "()Lid/go/bpsfasih/data/local/entities/Level5;", "setLevel5", "(Lid/go/bpsfasih/data/local/entities/Level5;)V", "getName", "setName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class Level4 implements Serializable {
    public static final int $stable = 8;
    private String code;
    private String fullCode;
    private String id;
    private Level5 level5;
    private String name;

    public static /* synthetic */ Level4 copy$default(Level4 level4, Level5 level5, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            level5 = level4.level5;
        }
        if ((i & 2) != 0) {
            str = level4.fullCode;
        }
        String str5 = str;
        if ((i & 4) != 0) {
            str2 = level4.code;
        }
        String str6 = str2;
        if ((i & 8) != 0) {
            str3 = level4.name;
        }
        String str7 = str3;
        if ((i & 16) != 0) {
            str4 = level4.id;
        }
        return level4.copy(level5, str5, str6, str7, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final Level5 getLevel5() {
        return this.level5;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFullCode() {
        return this.fullCode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    /* renamed from: component4, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component5, reason: from getter */
    public final String getId() {
        return this.id;
    }

    public final Level4 copy(Level5 level5, String fullCode, String code, String name, String id2) {
        return new Level4(level5, fullCode, code, name, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Level4)) {
            return false;
        }
        Level4 level4 = (Level4) other;
        return Intrinsics.areEqual(this.level5, level4.level5) && Intrinsics.areEqual(this.fullCode, level4.fullCode) && Intrinsics.areEqual(this.code, level4.code) && Intrinsics.areEqual(this.name, level4.name) && Intrinsics.areEqual(this.id, level4.id);
    }

    public int hashCode() {
        Level5 level5 = this.level5;
        int iHashCode = (level5 == null ? 0 : level5.hashCode()) * 31;
        String str = this.fullCode;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.code;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.name;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.id;
        return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "Level4(level5=" + this.level5 + ", fullCode=" + this.fullCode + ", code=" + this.code + ", name=" + this.name + ", id=" + this.id + ")";
    }

    public Level4(Level5 level5, String str, String str2, String str3, String str4) {
        this.level5 = level5;
        this.fullCode = str;
        this.code = str2;
        this.name = str3;
        this.id = str4;
    }

    public final Level5 getLevel5() {
        return this.level5;
    }

    public final void setLevel5(Level5 level5) {
        this.level5 = level5;
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

    public Level4() {
        this(null, null, null, null, null);
    }
}
