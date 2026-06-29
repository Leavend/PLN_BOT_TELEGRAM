package id.go.bpsfasih.data.local.entities;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssigmentEntity.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\tJ&\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/data/local/entities/LevelRegionMetadata;", "Ljava/io/Serializable;", "()V", "name", "", DownloadModel.ID, "", "(Ljava/lang/String;Ljava/lang/Integer;)V", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Integer;)Lid/go/bpsfasih/data/local/entities/LevelRegionMetadata;", "equals", "", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LevelRegionMetadata implements Serializable {
    public static final int $stable = 8;
    private Integer id;
    private String name;

    public static /* synthetic */ LevelRegionMetadata copy$default(LevelRegionMetadata levelRegionMetadata, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = levelRegionMetadata.name;
        }
        if ((i & 2) != 0) {
            num = levelRegionMetadata.id;
        }
        return levelRegionMetadata.copy(str, num);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getId() {
        return this.id;
    }

    public final LevelRegionMetadata copy(String name, Integer id2) {
        return new LevelRegionMetadata(name, id2);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LevelRegionMetadata)) {
            return false;
        }
        LevelRegionMetadata levelRegionMetadata = (LevelRegionMetadata) other;
        return Intrinsics.areEqual(this.name, levelRegionMetadata.name) && Intrinsics.areEqual(this.id, levelRegionMetadata.id);
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.id;
        return iHashCode + (num != null ? num.hashCode() : 0);
    }

    public String toString() {
        return "LevelRegionMetadata(name=" + this.name + ", id=" + this.id + ")";
    }

    public LevelRegionMetadata(String str, Integer num) {
        this.name = str;
        this.id = num;
    }

    public /* synthetic */ LevelRegionMetadata(String str, Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : num);
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        this.name = str;
    }

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public LevelRegionMetadata() {
        this(null, null);
    }
}
