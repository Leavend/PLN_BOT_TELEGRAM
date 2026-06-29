package com.kdownloader.database;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadModel.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 22\u00020\u0001:\u00012BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n¢\u0006\u0002\u0010\rJ\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\t\u0010)\u001a\u00020\nHÆ\u0003J\t\u0010*\u001a\u00020\nHÆ\u0003J\t\u0010+\u001a\u00020\nHÆ\u0003JY\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\nHÆ\u0001J\u0013\u0010-\u001a\u00020.2\b\u0010/\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00100\u001a\u00020\u0003HÖ\u0001J\t\u00101\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u000b\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0013\"\u0004\b\u001f\u0010\u0015R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0013\"\u0004\b!\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011¨\u00063"}, d2 = {"Lcom/kdownloader/database/DownloadModel;", "", DownloadModel.ID, "", "url", "", "eTag", "dirPath", "fileName", "totalBytes", "", "downloadedBytes", "lastModifiedAt", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJJ)V", "getDirPath", "()Ljava/lang/String;", "setDirPath", "(Ljava/lang/String;)V", "getDownloadedBytes", "()J", "setDownloadedBytes", "(J)V", "getETag", "setETag", "getFileName", "setFileName", "getId", "()I", "setId", "(I)V", "getLastModifiedAt", "setLastModifiedAt", "getTotalBytes", "setTotalBytes", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DownloadModel {
    public static final String DIR_PATH = "dir_path";
    public static final String DOWNLOADED_BYTES = "downloaded_bytes";
    public static final String ETAG = "etag";
    public static final String FILE_NAME = "file_name";
    public static final String ID = "id";
    public static final String LAST_MODIFIED_AT = "last_modified_at";
    public static final String TOTAL_BYTES = "total_bytes";
    public static final String URL = "url";
    private String dirPath;
    private long downloadedBytes;
    private String eTag;
    private String fileName;
    private int id;
    private long lastModifiedAt;
    private long totalBytes;
    private String url;

    public DownloadModel() {
        this(0, null, null, null, null, 0L, 0L, 0L, 255, null);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component3, reason: from getter */
    public final String getETag() {
        return this.eTag;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDirPath() {
        return this.dirPath;
    }

    /* renamed from: component5, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* renamed from: component6, reason: from getter */
    public final long getTotalBytes() {
        return this.totalBytes;
    }

    /* renamed from: component7, reason: from getter */
    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    /* renamed from: component8, reason: from getter */
    public final long getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    public final DownloadModel copy(int id2, String url, String eTag, String dirPath, String fileName, long totalBytes, long downloadedBytes, long lastModifiedAt) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(eTag, "eTag");
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new DownloadModel(id2, url, eTag, dirPath, fileName, totalBytes, downloadedBytes, lastModifiedAt);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloadModel)) {
            return false;
        }
        DownloadModel downloadModel = (DownloadModel) other;
        return this.id == downloadModel.id && Intrinsics.areEqual(this.url, downloadModel.url) && Intrinsics.areEqual(this.eTag, downloadModel.eTag) && Intrinsics.areEqual(this.dirPath, downloadModel.dirPath) && Intrinsics.areEqual(this.fileName, downloadModel.fileName) && this.totalBytes == downloadModel.totalBytes && this.downloadedBytes == downloadModel.downloadedBytes && this.lastModifiedAt == downloadModel.lastModifiedAt;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.id) * 31) + this.url.hashCode()) * 31) + this.eTag.hashCode()) * 31) + this.dirPath.hashCode()) * 31) + this.fileName.hashCode()) * 31) + Long.hashCode(this.totalBytes)) * 31) + Long.hashCode(this.downloadedBytes)) * 31) + Long.hashCode(this.lastModifiedAt);
    }

    public String toString() {
        return "DownloadModel(id=" + this.id + ", url=" + this.url + ", eTag=" + this.eTag + ", dirPath=" + this.dirPath + ", fileName=" + this.fileName + ", totalBytes=" + this.totalBytes + ", downloadedBytes=" + this.downloadedBytes + ", lastModifiedAt=" + this.lastModifiedAt + ')';
    }

    public DownloadModel(int i, String url, String eTag, String dirPath, String fileName, long j, long j2, long j3) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(eTag, "eTag");
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.id = i;
        this.url = url;
        this.eTag = eTag;
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.totalBytes = j;
        this.downloadedBytes = j2;
        this.lastModifiedAt = j3;
    }

    public /* synthetic */ DownloadModel(int i, String str, String str2, String str3, String str4, long j, long j2, long j3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? "" : str, (i2 & 4) != 0 ? "" : str2, (i2 & 8) != 0 ? "" : str3, (i2 & 16) == 0 ? str4 : "", (i2 & 32) != 0 ? 0L : j, (i2 & 64) != 0 ? 0L : j2, (i2 & 128) == 0 ? j3 : 0L);
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    public final String getETag() {
        return this.eTag;
    }

    public final void setETag(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.eTag = str;
    }

    public final String getDirPath() {
        return this.dirPath;
    }

    public final void setDirPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.dirPath = str;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final void setFileName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fileName = str;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public final void setDownloadedBytes(long j) {
        this.downloadedBytes = j;
    }

    public final long getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    public final void setLastModifiedAt(long j) {
        this.lastModifiedAt = j;
    }
}
