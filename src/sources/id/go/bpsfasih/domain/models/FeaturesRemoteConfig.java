package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FeaturesRemoteConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/domain/models/FeaturesRemoteConfig;", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/domain/models/FeaturesRemoteConfig$FeaturesRemoteConfigItem;", "Lkotlin/collections/ArrayList;", "()V", "FeaturesRemoteConfigItem", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FeaturesRemoteConfig extends ArrayList<FeaturesRemoteConfigItem> {
    public static final int $stable = 0;

    /* compiled from: FeaturesRemoteConfig.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\bHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lid/go/bpsfasih/domain/models/FeaturesRemoteConfig$FeaturesRemoteConfigItem;", "", DownloadModel.ID, "", "is_active", "", "name", "users", "", "(Ljava/lang/String;ZLjava/lang/String;Ljava/util/List;)V", "getId", "()Ljava/lang/String;", "()Z", "getName", "getUsers", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class FeaturesRemoteConfigItem {
        public static final int $stable = 8;
        private final String id;
        private final boolean is_active;
        private final String name;
        private final List<String> users;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FeaturesRemoteConfigItem copy$default(FeaturesRemoteConfigItem featuresRemoteConfigItem, String str, boolean z, String str2, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = featuresRemoteConfigItem.id;
            }
            if ((i & 2) != 0) {
                z = featuresRemoteConfigItem.is_active;
            }
            if ((i & 4) != 0) {
                str2 = featuresRemoteConfigItem.name;
            }
            if ((i & 8) != 0) {
                list = featuresRemoteConfigItem.users;
            }
            return featuresRemoteConfigItem.copy(str, z, str2, list);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getIs_active() {
            return this.is_active;
        }

        /* renamed from: component3, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<String> component4() {
            return this.users;
        }

        public final FeaturesRemoteConfigItem copy(String id2, boolean is_active, String name, List<String> users) {
            Intrinsics.checkNotNullParameter(id2, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(users, "users");
            return new FeaturesRemoteConfigItem(id2, is_active, name, users);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FeaturesRemoteConfigItem)) {
                return false;
            }
            FeaturesRemoteConfigItem featuresRemoteConfigItem = (FeaturesRemoteConfigItem) other;
            return Intrinsics.areEqual(this.id, featuresRemoteConfigItem.id) && this.is_active == featuresRemoteConfigItem.is_active && Intrinsics.areEqual(this.name, featuresRemoteConfigItem.name) && Intrinsics.areEqual(this.users, featuresRemoteConfigItem.users);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int iHashCode = this.id.hashCode() * 31;
            boolean z = this.is_active;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return ((((iHashCode + i) * 31) + this.name.hashCode()) * 31) + this.users.hashCode();
        }

        public String toString() {
            return "FeaturesRemoteConfigItem(id=" + this.id + ", is_active=" + this.is_active + ", name=" + this.name + ", users=" + this.users + ")";
        }

        public FeaturesRemoteConfigItem(String id2, boolean z, String name, List<String> users) {
            Intrinsics.checkNotNullParameter(id2, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(users, "users");
            this.id = id2;
            this.is_active = z;
            this.name = name;
            this.users = users;
        }

        public final String getId() {
            return this.id;
        }

        public final boolean is_active() {
            return this.is_active;
        }

        public final String getName() {
            return this.name;
        }

        public final List<String> getUsers() {
            return this.users;
        }
    }

    public /* bridge */ boolean contains(FeaturesRemoteConfigItem featuresRemoteConfigItem) {
        return super.contains((Object) featuresRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof FeaturesRemoteConfigItem) {
            return contains((FeaturesRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(FeaturesRemoteConfigItem featuresRemoteConfigItem) {
        return super.indexOf((Object) featuresRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof FeaturesRemoteConfigItem) {
            return indexOf((FeaturesRemoteConfigItem) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(FeaturesRemoteConfigItem featuresRemoteConfigItem) {
        return super.lastIndexOf((Object) featuresRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof FeaturesRemoteConfigItem) {
            return lastIndexOf((FeaturesRemoteConfigItem) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ FeaturesRemoteConfigItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(FeaturesRemoteConfigItem featuresRemoteConfigItem) {
        return super.remove((Object) featuresRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof FeaturesRemoteConfigItem) {
            return remove((FeaturesRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ FeaturesRemoteConfigItem removeAt(int i) {
        return (FeaturesRemoteConfigItem) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
