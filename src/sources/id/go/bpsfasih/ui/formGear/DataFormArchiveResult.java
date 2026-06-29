package id.go.bpsfasih.ui.formGear;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataFormArchiveHelper.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/ui/formGear/DataFormArchiveResult;", "", "archiveFile", "Ljava/io/File;", "md5Checksum", "", "(Ljava/io/File;Ljava/lang/String;)V", "getArchiveFile", "()Ljava/io/File;", "getMd5Checksum", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DataFormArchiveResult {
    private final File archiveFile;
    private final String md5Checksum;

    public static /* synthetic */ DataFormArchiveResult copy$default(DataFormArchiveResult dataFormArchiveResult, File file, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            file = dataFormArchiveResult.archiveFile;
        }
        if ((i & 2) != 0) {
            str = dataFormArchiveResult.md5Checksum;
        }
        return dataFormArchiveResult.copy(file, str);
    }

    /* renamed from: component1, reason: from getter */
    public final File getArchiveFile() {
        return this.archiveFile;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMd5Checksum() {
        return this.md5Checksum;
    }

    public final DataFormArchiveResult copy(File archiveFile, String md5Checksum) {
        Intrinsics.checkNotNullParameter(archiveFile, "archiveFile");
        Intrinsics.checkNotNullParameter(md5Checksum, "md5Checksum");
        return new DataFormArchiveResult(archiveFile, md5Checksum);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataFormArchiveResult)) {
            return false;
        }
        DataFormArchiveResult dataFormArchiveResult = (DataFormArchiveResult) other;
        return Intrinsics.areEqual(this.archiveFile, dataFormArchiveResult.archiveFile) && Intrinsics.areEqual(this.md5Checksum, dataFormArchiveResult.md5Checksum);
    }

    public int hashCode() {
        return (this.archiveFile.hashCode() * 31) + this.md5Checksum.hashCode();
    }

    public String toString() {
        return "DataFormArchiveResult(archiveFile=" + this.archiveFile + ", md5Checksum=" + this.md5Checksum + ")";
    }

    public DataFormArchiveResult(File archiveFile, String md5Checksum) {
        Intrinsics.checkNotNullParameter(archiveFile, "archiveFile");
        Intrinsics.checkNotNullParameter(md5Checksum, "md5Checksum");
        this.archiveFile = archiveFile;
        this.md5Checksum = md5Checksum;
    }

    public final File getArchiveFile() {
        return this.archiveFile;
    }

    public final String getMd5Checksum() {
        return this.md5Checksum;
    }
}
