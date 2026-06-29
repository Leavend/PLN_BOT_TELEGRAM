package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WaitingTimeRemoteConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/domain/models/WaitingTimeRemoteConfig;", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/domain/models/WaitingTimeRemoteConfig$Item;", "Lkotlin/collections/ArrayList;", "()V", "Item", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class WaitingTimeRemoteConfig extends ArrayList<Item> {
    public static final int $stable = 0;

    /* compiled from: WaitingTimeRemoteConfig.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/domain/models/WaitingTimeRemoteConfig$Item;", "", "name", "", "unit", "time", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getName", "()Ljava/lang/String;", "getTime", "()J", "getUnit", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class Item {
        public static final int $stable = 0;
        private final String name;
        private final long time;
        private final String unit;

        public static /* synthetic */ Item copy$default(Item item, String str, String str2, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                str = item.name;
            }
            if ((i & 2) != 0) {
                str2 = item.unit;
            }
            if ((i & 4) != 0) {
                j = item.time;
            }
            return item.copy(str, str2, j);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final String getUnit() {
            return this.unit;
        }

        /* renamed from: component3, reason: from getter */
        public final long getTime() {
            return this.time;
        }

        public final Item copy(String name, String unit, long time) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(unit, "unit");
            return new Item(name, unit, time);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Item)) {
                return false;
            }
            Item item = (Item) other;
            return Intrinsics.areEqual(this.name, item.name) && Intrinsics.areEqual(this.unit, item.unit) && this.time == item.time;
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.unit.hashCode()) * 31) + Long.hashCode(this.time);
        }

        public String toString() {
            return "Item(name=" + this.name + ", unit=" + this.unit + ", time=" + this.time + ")";
        }

        public Item(String name, String unit, long j) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(unit, "unit");
            this.name = name;
            this.unit = unit;
            this.time = j;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUnit() {
            return this.unit;
        }

        public final long getTime() {
            return this.time;
        }
    }

    public /* bridge */ boolean contains(Item item) {
        return super.contains((Object) item);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Item) {
            return contains((Item) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(Item item) {
        return super.indexOf((Object) item);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof Item) {
            return indexOf((Item) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(Item item) {
        return super.lastIndexOf((Object) item);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof Item) {
            return lastIndexOf((Item) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ Item remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(Item item) {
        return super.remove((Object) item);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof Item) {
            return remove((Item) obj);
        }
        return false;
    }

    public /* bridge */ Item removeAt(int i) {
        return (Item) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
