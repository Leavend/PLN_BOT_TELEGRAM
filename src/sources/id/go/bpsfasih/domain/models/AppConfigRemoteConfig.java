package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppConfigRemoteConfig.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/domain/models/AppConfigRemoteConfig;", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/domain/models/AppConfigRemoteConfig$AppConfigRemoteConfigItem;", "Lkotlin/collections/ArrayList;", "()V", "AppConfigRemoteConfigItem", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AppConfigRemoteConfig extends ArrayList<AppConfigRemoteConfigItem> {
    public static final int $stable = 0;

    /* compiled from: AppConfigRemoteConfig.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\bHÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003JU\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\rR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000e¨\u0006\""}, d2 = {"Lid/go/bpsfasih/domain/models/AppConfigRemoteConfig$AppConfigRemoteConfigItem;", "", "is_default", "", "link_download", "", "message", "users", "", "versionCode", "versionName", "is_force", "(ZLjava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "()Z", "()Ljava/lang/String;", "getLink_download", "getMessage", "getUsers", "()Ljava/util/List;", "getVersionCode", "getVersionName", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class AppConfigRemoteConfigItem {
        public static final int $stable = 8;
        private final boolean is_default;
        private final String is_force;
        private final String link_download;
        private final String message;
        private final List<String> users;
        private final String versionCode;
        private final String versionName;

        public static /* synthetic */ AppConfigRemoteConfigItem copy$default(AppConfigRemoteConfigItem appConfigRemoteConfigItem, boolean z, String str, String str2, List list, String str3, String str4, String str5, int i, Object obj) {
            if ((i & 1) != 0) {
                z = appConfigRemoteConfigItem.is_default;
            }
            if ((i & 2) != 0) {
                str = appConfigRemoteConfigItem.link_download;
            }
            String str6 = str;
            if ((i & 4) != 0) {
                str2 = appConfigRemoteConfigItem.message;
            }
            String str7 = str2;
            if ((i & 8) != 0) {
                list = appConfigRemoteConfigItem.users;
            }
            List list2 = list;
            if ((i & 16) != 0) {
                str3 = appConfigRemoteConfigItem.versionCode;
            }
            String str8 = str3;
            if ((i & 32) != 0) {
                str4 = appConfigRemoteConfigItem.versionName;
            }
            String str9 = str4;
            if ((i & 64) != 0) {
                str5 = appConfigRemoteConfigItem.is_force;
            }
            return appConfigRemoteConfigItem.copy(z, str6, str7, list2, str8, str9, str5);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIs_default() {
            return this.is_default;
        }

        /* renamed from: component2, reason: from getter */
        public final String getLink_download() {
            return this.link_download;
        }

        /* renamed from: component3, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        public final List<String> component4() {
            return this.users;
        }

        /* renamed from: component5, reason: from getter */
        public final String getVersionCode() {
            return this.versionCode;
        }

        /* renamed from: component6, reason: from getter */
        public final String getVersionName() {
            return this.versionName;
        }

        /* renamed from: component7, reason: from getter */
        public final String getIs_force() {
            return this.is_force;
        }

        public final AppConfigRemoteConfigItem copy(boolean is_default, String link_download, String message, List<String> users, String versionCode, String versionName, String is_force) {
            Intrinsics.checkNotNullParameter(link_download, "link_download");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(users, "users");
            Intrinsics.checkNotNullParameter(versionCode, "versionCode");
            Intrinsics.checkNotNullParameter(versionName, "versionName");
            Intrinsics.checkNotNullParameter(is_force, "is_force");
            return new AppConfigRemoteConfigItem(is_default, link_download, message, users, versionCode, versionName, is_force);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AppConfigRemoteConfigItem)) {
                return false;
            }
            AppConfigRemoteConfigItem appConfigRemoteConfigItem = (AppConfigRemoteConfigItem) other;
            return this.is_default == appConfigRemoteConfigItem.is_default && Intrinsics.areEqual(this.link_download, appConfigRemoteConfigItem.link_download) && Intrinsics.areEqual(this.message, appConfigRemoteConfigItem.message) && Intrinsics.areEqual(this.users, appConfigRemoteConfigItem.users) && Intrinsics.areEqual(this.versionCode, appConfigRemoteConfigItem.versionCode) && Intrinsics.areEqual(this.versionName, appConfigRemoteConfigItem.versionName) && Intrinsics.areEqual(this.is_force, appConfigRemoteConfigItem.is_force);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v14 */
        /* JADX WARN: Type inference failed for: r0v15 */
        public int hashCode() {
            boolean z = this.is_default;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (((((((((((r0 * 31) + this.link_download.hashCode()) * 31) + this.message.hashCode()) * 31) + this.users.hashCode()) * 31) + this.versionCode.hashCode()) * 31) + this.versionName.hashCode()) * 31) + this.is_force.hashCode();
        }

        public String toString() {
            return "AppConfigRemoteConfigItem(is_default=" + this.is_default + ", link_download=" + this.link_download + ", message=" + this.message + ", users=" + this.users + ", versionCode=" + this.versionCode + ", versionName=" + this.versionName + ", is_force=" + this.is_force + ")";
        }

        public AppConfigRemoteConfigItem(boolean z, String link_download, String message, List<String> users, String versionCode, String versionName, String is_force) {
            Intrinsics.checkNotNullParameter(link_download, "link_download");
            Intrinsics.checkNotNullParameter(message, "message");
            Intrinsics.checkNotNullParameter(users, "users");
            Intrinsics.checkNotNullParameter(versionCode, "versionCode");
            Intrinsics.checkNotNullParameter(versionName, "versionName");
            Intrinsics.checkNotNullParameter(is_force, "is_force");
            this.is_default = z;
            this.link_download = link_download;
            this.message = message;
            this.users = users;
            this.versionCode = versionCode;
            this.versionName = versionName;
            this.is_force = is_force;
        }

        public final boolean is_default() {
            return this.is_default;
        }

        public final String getLink_download() {
            return this.link_download;
        }

        public final String getMessage() {
            return this.message;
        }

        public final List<String> getUsers() {
            return this.users;
        }

        public final String getVersionCode() {
            return this.versionCode;
        }

        public final String getVersionName() {
            return this.versionName;
        }

        public final String is_force() {
            return this.is_force;
        }
    }

    public /* bridge */ boolean contains(AppConfigRemoteConfigItem appConfigRemoteConfigItem) {
        return super.contains((Object) appConfigRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof AppConfigRemoteConfigItem) {
            return contains((AppConfigRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(AppConfigRemoteConfigItem appConfigRemoteConfigItem) {
        return super.indexOf((Object) appConfigRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof AppConfigRemoteConfigItem) {
            return indexOf((AppConfigRemoteConfigItem) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(AppConfigRemoteConfigItem appConfigRemoteConfigItem) {
        return super.lastIndexOf((Object) appConfigRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof AppConfigRemoteConfigItem) {
            return lastIndexOf((AppConfigRemoteConfigItem) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ AppConfigRemoteConfigItem remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ boolean remove(AppConfigRemoteConfigItem appConfigRemoteConfigItem) {
        return super.remove((Object) appConfigRemoteConfigItem);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof AppConfigRemoteConfigItem) {
            return remove((AppConfigRemoteConfigItem) obj);
        }
        return false;
    }

    public /* bridge */ AppConfigRemoteConfigItem removeAt(int i) {
        return (AppConfigRemoteConfigItem) super.remove(i);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
