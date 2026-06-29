package id.go.bpsfasih.ui.formGear;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.utils.helper.MessageDigestHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataFormArchiveHelper.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\"\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011J\"\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0011J\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lid/go/bpsfasih/ui/formGear/DataFormArchiveHelper;", "", "()V", "CHECKSUM_FILE_NAME", "", "DATA_FILE_NAME", "MEDIA_DIRECTORY_NAME", "NOTE_FILE_NAME", "REFERENCE_FILE_NAME", "compressReferenceFile", "", "stagingDirectory", "Ljava/io/File;", "createArchive", "request", "Lid/go/bpsfasih/ui/formGear/DataFormArchiveRequest;", "onStartCompressing", "Lkotlin/Function0;", "createArchiveWithChecksum", "Lid/go/bpsfasih/ui/formGear/DataFormArchiveResult;", "ensureDirectoryExists", "directory", "removeExcludedFiles", "replaceDataFileIfNeeded", "overrideDataJson", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataFormArchiveHelper {
    private static final String CHECKSUM_FILE_NAME = "checksum.md5";
    private static final String DATA_FILE_NAME = "data";
    public static final DataFormArchiveHelper INSTANCE = new DataFormArchiveHelper();
    private static final String MEDIA_DIRECTORY_NAME = "media";
    private static final String NOTE_FILE_NAME = "note";
    private static final String REFERENCE_FILE_NAME = "reference";

    private DataFormArchiveHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DataFormArchiveResult createArchiveWithChecksum$default(DataFormArchiveHelper dataFormArchiveHelper, DataFormArchiveRequest dataFormArchiveRequest, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        return dataFormArchiveHelper.createArchiveWithChecksum(dataFormArchiveRequest, function0);
    }

    public final DataFormArchiveResult createArchiveWithChecksum(DataFormArchiveRequest request, Function0<Unit> onStartCompressing) throws Throwable {
        Intrinsics.checkNotNullParameter(request, "request");
        Log.d(">>> create 7z", "start");
        ensureDirectoryExists(request.getTempDirectory());
        ensureDirectoryExists(request.getSourceDirectory());
        File file = new File(request.getSourceDirectory(), CHECKSUM_FILE_NAME);
        FilesKt.writeText$default(file, "This file will contain MD5", null, 2, null);
        File fileCreateArchive = createArchive(request, onStartCompressing);
        if (fileCreateArchive == null) {
            return null;
        }
        String md5 = MessageDigestHelper.INSTANCE.getMD5(fileCreateArchive);
        if (md5 == null) {
            md5 = "";
        }
        FilesKt.writeText$default(file, md5, null, 2, null);
        return new DataFormArchiveResult(fileCreateArchive, md5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ File createArchive$default(DataFormArchiveHelper dataFormArchiveHelper, DataFormArchiveRequest dataFormArchiveRequest, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            function0 = null;
        }
        return dataFormArchiveHelper.createArchive(dataFormArchiveRequest, function0);
    }

    public final File createArchive(DataFormArchiveRequest request, Function0<Unit> onStartCompressing) {
        Intrinsics.checkNotNullParameter(request, "request");
        ensureDirectoryExists(request.getTempDirectory());
        ensureDirectoryExists(request.getSourceDirectory());
        if (onStartCompressing != null) {
            try {
                onStartCompressing.invoke();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        File file = new File(request.getTempDirectory(), request.getAssignmentId());
        File file2 = new File(request.getTempDirectory(), request.getAssignmentId() + ".7z");
        if (!file.exists()) {
            file.mkdirs();
        }
        if (file2.exists()) {
            FilesKt.deleteRecursively(file2);
        }
        FilesKt.copyRecursively$default(request.getSourceDirectory(), file, true, null, 4, null);
        replaceDataFileIfNeeded(file, request.getOverrideDataJson());
        compressReferenceFile(file);
        removeExcludedFiles(file);
        ZipHelper.Companion companion = ZipHelper.INSTANCE;
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "stagingDirectory.path");
        String path2 = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "stagingDirectory.path");
        companion.zip7File(path, path2);
        FilesKt.deleteRecursively(file);
        return file2;
    }

    private final void ensureDirectoryExists(File directory) {
        if (directory.exists()) {
            return;
        }
        directory.mkdirs();
    }

    private final void replaceDataFileIfNeeded(File stagingDirectory, String overrideDataJson) {
        if (overrideDataJson == null) {
            return;
        }
        FilesKt.writeText$default(new File(stagingDirectory, "data" + CommonCons.INSTANCE.getEXTENSION_JSON()), overrideDataJson, null, 2, null);
    }

    private final void compressReferenceFile(File stagingDirectory) {
        File file = new File(stagingDirectory, "reference" + CommonCons.INSTANCE.getEXTENSION_JSON());
        ZipHelper.Companion companion = ZipHelper.INSTANCE;
        String path = file.getPath();
        Intrinsics.checkNotNullExpressionValue(path, "referenceFile.path");
        String path2 = new File(stagingDirectory, "reference").getPath();
        Intrinsics.checkNotNullExpressionValue(path2, "File(stagingDirectory, REFERENCE_FILE_NAME).path");
        companion.zipFile(path, path2);
        file.delete();
    }

    private final void removeExcludedFiles(File stagingDirectory) {
        FilesKt.deleteRecursively(new File(stagingDirectory, "media"));
        new File(stagingDirectory, "note" + CommonCons.INSTANCE.getEXTENSION_TXT()).delete();
    }
}
