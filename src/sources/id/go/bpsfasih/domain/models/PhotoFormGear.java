package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PhotoFormGear.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/domain/models/PhotoFormGear;", "", "dataKey", "", "filename", "uri", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDataKey", "()Ljava/lang/String;", "getFilename", "getUri", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class PhotoFormGear {
    public static final int $stable = 0;
    private final String dataKey;
    private final String filename;
    private final String uri;

    public static /* synthetic */ PhotoFormGear copy$default(PhotoFormGear photoFormGear, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = photoFormGear.dataKey;
        }
        if ((i & 2) != 0) {
            str2 = photoFormGear.filename;
        }
        if ((i & 4) != 0) {
            str3 = photoFormGear.uri;
        }
        return photoFormGear.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDataKey() {
        return this.dataKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFilename() {
        return this.filename;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUri() {
        return this.uri;
    }

    public final PhotoFormGear copy(String dataKey, String filename, String uri) {
        Intrinsics.checkNotNullParameter(dataKey, "dataKey");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(uri, "uri");
        return new PhotoFormGear(dataKey, filename, uri);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhotoFormGear)) {
            return false;
        }
        PhotoFormGear photoFormGear = (PhotoFormGear) other;
        return Intrinsics.areEqual(this.dataKey, photoFormGear.dataKey) && Intrinsics.areEqual(this.filename, photoFormGear.filename) && Intrinsics.areEqual(this.uri, photoFormGear.uri);
    }

    public int hashCode() {
        return (((this.dataKey.hashCode() * 31) + this.filename.hashCode()) * 31) + this.uri.hashCode();
    }

    public String toString() {
        return "PhotoFormGear(dataKey=" + this.dataKey + ", filename=" + this.filename + ", uri=" + this.uri + ")";
    }

    public PhotoFormGear(String dataKey, String filename, String uri) {
        Intrinsics.checkNotNullParameter(dataKey, "dataKey");
        Intrinsics.checkNotNullParameter(filename, "filename");
        Intrinsics.checkNotNullParameter(uri, "uri");
        this.dataKey = dataKey;
        this.filename = filename;
        this.uri = uri;
    }

    public final String getDataKey() {
        return this.dataKey;
    }

    public final String getFilename() {
        return this.filename;
    }

    public final String getUri() {
        return this.uri;
    }
}
