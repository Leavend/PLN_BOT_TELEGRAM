package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UrlsRemoteConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/domain/models/UrlsRemoteConfig;", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/domain/models/UrlsRemoteConfig$UrlRemoteConfigItem;", "Lkotlin/collections/ArrayList;", "()V", "UrlRemoteConfigItem", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UrlsRemoteConfig extends ArrayList<UrlRemoteConfigItem> {
    public static final int $stable = 0;

    /* compiled from: UrlsRemoteConfig.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/domain/models/UrlsRemoteConfig$UrlRemoteConfigItem;", "", DownloadModel.ID, "", "name", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getName", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class UrlRemoteConfigItem {
        public static final int $stable = 0;
        private final String id;
        private final String name;
        private final String url;

        public static /* synthetic */ UrlRemoteConfigItem copy$default(UrlRemoteConfigItem urlRemoteConfigItem, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = urlRemoteConfigItem.id;
            }
            if ((i & 2) != 0) {
                str2 = urlRemoteConfigItem.name;
            }
            if ((i & 4) != 0) {
                str3 = urlRemoteConfigItem.url;
            }
            return urlRemoteConfigItem.copy(str, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component3, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        public final UrlRemoteConfigItem copy(String id2, String name, String url) {
            Intrinsics.checkNotNullParameter(id2, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            return new UrlRemoteConfigItem(id2, name, url);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UrlRemoteConfigItem)) {
                return false;
            }
            UrlRemoteConfigItem urlRemoteConfigItem = (UrlRemoteConfigItem) other;
            return Intrinsics.areEqual(this.id, urlRemoteConfigItem.id) && Intrinsics.areEqual(this.name, urlRemoteConfigItem.name) && Intrinsics.areEqual(this.url, urlRemoteConfigItem.url);
        }

        public int hashCode() {
            return (((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.url.hashCode();
        }

        public String toString() {
            return "UrlRemoteConfigItem(id=" + this.id + ", name=" + this.name + ", url=" + this.url + ")";
        }

        public UrlRemoteConfigItem(String id2, String name, String url) {
            Intrinsics.checkNotNullParameter(id2, "id");
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(url, "url");
            this.id = id2;
            this.name = name;
            this.url = url;
        }

        public final String getId() {
            return this.id;
        }

        public final String getName() {
            return this.name;
        }

        public final String getUrl() {
            return this.url;
        }
    }

    public /* bridge */ boolean contains(UrlRemoteConfigItem urlRemoteConfigItem) {
        return super.contains((Object) urlRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof UrlRemoteConfigItem) {
            return contains((UrlRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(UrlRemoteConfigItem urlRemoteConfigItem) {
        return super.indexOf((Object) urlRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof UrlRemoteConfigItem) {
            return indexOf((UrlRemoteConfigItem) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(UrlRemoteConfigItem urlRemoteConfigItem) {
        return super.lastIndexOf((Object) urlRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof UrlRemoteConfigItem) {
            return lastIndexOf((UrlRemoteConfigItem) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ UrlRemoteConfigItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(UrlRemoteConfigItem urlRemoteConfigItem) {
        return super.remove((Object) urlRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof UrlRemoteConfigItem) {
            return remove((UrlRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ UrlRemoteConfigItem removeAt(int i) {
        return (UrlRemoteConfigItem) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
