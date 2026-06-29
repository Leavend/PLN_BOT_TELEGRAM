package id.go.bpsfasih.utils.helper;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.hzy.lib7z.IExtractCallback;
import com.hzy.lib7z.Z7Extractor;
import com.hzy.libp7zip.P7ZipApi;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.utils.CrashHandler;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONException;

/* compiled from: ZipHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/ZipHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ZipHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: ZipHelper.kt */
    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J9\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00040\tJ\u001e\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006JV\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u001626\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00040\u0017J \u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J'\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010#J\u0019\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00060%2\u0006\u0010&\u001a\u00020'¢\u0006\u0002\u0010(JN\u0010)\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u000626\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00040\u0017Je\u0010*\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010\u00062S\u0010+\u001aO\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(-\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(.\u0012\u001b\u0012\u0019\u0012\u0004\u0012\u00020\u0006\u0018\u00010/¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(0\u0012\u0004\u0012\u00020\u00040,J\"\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020\u001b2\b\b\u0002\u00103\u001a\u0002042\b\b\u0002\u00105\u001a\u000206J.\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020\nJ\u0016\u0010<\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u0006JN\u0010>\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'26\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00040\u0017J\u0016\u0010?\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u0006¨\u0006@"}, d2 = {"Lid/go/bpsfasih/utils/helper/ZipHelper$Companion;", "", "()V", "extractZip", "", "inpath", "", "outpath", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", FirebaseAnalytics.Param.SUCCESS, "getCompressCmd", "filePath", "outPath", "type", "protectedZip", "inputPath", "outputPathFolderZip", "passwordChars", "", "Lkotlin/Function2;", "message", "recursivelyAddZipEntries", "folder", "Ljava/io/File;", "basePath", "out", "Ljava/util/zip/ZipOutputStream;", "unZip", ClientCookie.PATH_ATTR, "zipName", "remove", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Z", "unZipBPS", "", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;)[Ljava/lang/String;", "unZipLookup", "unzipAssigment", "uzipCallback", "Lkotlin/Function3;", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "result", "", "assignment", "waitForFileNonZeroSize", StorageChooser.FILE_PICKER, "maxAttempts", "", "delayMillis", "", "zip", "globalRemark", "toPath", "isSaveAnswerToDraft", "isUpdateStatusAssignment", "zip7File", "outputPath", "zipBPS", "zipFile", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void unzipAssigment(String path, Function3<? super Boolean, ? super String, ? super List<String>, Unit> uzipCallback) throws IOException {
            File file;
            String str = path;
            Intrinsics.checkNotNullParameter(uzipCallback, "uzipCallback");
            File file2 = new File(str);
            ArrayList arrayList = new ArrayList();
            if (!file2.exists()) {
                return;
            }
            File file3 = new File(FileHelper.INSTANCE.extractAssignmentTemporary(FasihApp.INSTANCE.getSession().getUserId()));
            if (!file3.exists()) {
                new File(String.valueOf(path));
                file3.mkdir();
            }
            try {
                String name = "";
                FileInputStream fileInputStream = new FileInputStream(String.valueOf(path));
                ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
                FileOutputStream zipInputStream2 = new ZipInputStream(fileInputStream);
                try {
                    ZipInputStream zipInputStream3 = zipInputStream2;
                    ZipEntry nextEntry = zipInputStream3.getNextEntry();
                    while (nextEntry != null) {
                        name = nextEntry.getName();
                        Intrinsics.checkNotNullExpressionValue(name, "ze.name");
                        if (nextEntry.isDirectory()) {
                            File file4 = new File(str + InternalZipConstants.ZIP_FILE_SEPARATOR + nextEntry.getName());
                            if (!file4.exists() && !file4.isDirectory()) {
                                file2.mkdirs();
                            }
                            file = file2;
                        } else {
                            file = file2;
                            zipInputStream2 = new FileOutputStream(new File(file3.getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR + nextEntry.getName()));
                            try {
                                FileOutputStream fileOutputStream = zipInputStream2;
                                byte[] bArr = new byte[8192];
                                for (int i = zipInputStream3.read(bArr); i != -1; i = zipInputStream3.read(bArr)) {
                                    fileOutputStream.write(bArr, 0, i);
                                }
                                zipInputStream3.closeEntry();
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(zipInputStream2, null);
                                String name2 = nextEntry.getName();
                                Intrinsics.checkNotNullExpressionValue(name2, "ze.name");
                                arrayList.add(name2);
                            } finally {
                            }
                        }
                        nextEntry = zipInputStream3.getNextEntry();
                        str = path;
                        file2 = file;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(zipInputStream2, null);
                    zipInputStream.close();
                    fileInputStream.close();
                    uzipCallback.invoke(false, name, arrayList);
                } finally {
                }
            } catch (IOException e) {
                e.printStackTrace();
                uzipCallback.invoke(true, e.toString(), null);
            }
        }

        public static /* synthetic */ boolean unZip$default(Companion companion, String str, String str2, Boolean bool, int i, Object obj) {
            if ((i & 4) != 0) {
                bool = false;
            }
            return companion.unZip(str, str2, bool);
        }

        public final boolean unZip(String path, String zipName, Boolean remove) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(zipName, "zipName");
            File file = new File(path + InternalZipConstants.ZIP_FILE_SEPARATOR + zipName);
            if (!file.exists()) {
                return false;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileOutputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    try {
                        ZipInputStream zipInputStream = new ZipInputStream(bufferedInputStream);
                        try {
                            ZipInputStream zipInputStream2 = zipInputStream;
                            byte[] bArr = new byte[8192];
                            for (ZipEntry nextEntry = zipInputStream2.getNextEntry(); nextEntry != null; nextEntry = zipInputStream2.getNextEntry()) {
                                File file2 = new File(path + InternalZipConstants.ZIP_FILE_SEPARATOR + nextEntry.getName());
                                if (nextEntry.isDirectory()) {
                                    if (!file2.exists()) {
                                        file2.mkdirs();
                                    }
                                } else {
                                    File parentFile = file2.getParentFile();
                                    if (parentFile != null) {
                                        Intrinsics.checkNotNullExpressionValue(parentFile, "parentFile");
                                        if (!parentFile.exists()) {
                                            parentFile.mkdirs();
                                        }
                                    }
                                    bufferedInputStream = new FileOutputStream(file2);
                                    try {
                                        FileOutputStream fileOutputStream = bufferedInputStream;
                                        while (true) {
                                            int i = zipInputStream2.read(bArr);
                                            if (i == -1) {
                                                break;
                                            }
                                            fileOutputStream.write(bArr, 0, i);
                                        }
                                        Unit unit = Unit.INSTANCE;
                                        CloseableKt.closeFinally(bufferedInputStream, null);
                                    } finally {
                                    }
                                }
                                zipInputStream2.closeEntry();
                            }
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(zipInputStream, null);
                            Unit unit3 = Unit.INSTANCE;
                            CloseableKt.closeFinally(bufferedInputStream, null);
                            Unit unit4 = Unit.INSTANCE;
                            CloseableKt.closeFinally(fileInputStream, null);
                            if (Intrinsics.areEqual((Object) remove, (Object) true) && file.exists()) {
                                file.delete();
                            }
                            return true;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                }
            } catch (IOException e) {
                Log.e("UNZIP_ERROR", "Error unzip " + zipName + " : " + e.getMessage(), e);
                return false;
            }
        }

        public final void unZipLookup(String path, String zipName, Function2<? super Boolean, ? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(zipName, "zipName");
            Intrinsics.checkNotNullParameter(callback, "callback");
            File file = new File(path + InternalZipConstants.ZIP_FILE_SEPARATOR + zipName);
            File file2 = new File(path + InternalZipConstants.ZIP_FILE_SEPARATOR + StringsKt.substringBefore$default(zipName, CommonCons.INSTANCE.getEXTENSION_ZIP(), (String) null, 2, (Object) null) + CommonCons.INSTANCE.getEXTENSION_JSON());
            if (!waitForFileNonZeroSize$default(this, file, 0, 0L, 6, null)) {
                callback.invoke(false, "File ZIP tidak ditemukan atau ukurannya 0 setelah menunggu.");
                return;
            }
            try {
                FileOutputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
                try {
                    ZipInputStream zipInputStream2 = zipInputStream;
                    ZipEntry nextEntry = zipInputStream2.getNextEntry();
                    if (nextEntry != null && !nextEntry.isDirectory()) {
                        zipInputStream = new FileOutputStream(file2);
                        try {
                            FileOutputStream fileOutputStream = zipInputStream;
                            byte[] bArr = new byte[8192];
                            for (int i = zipInputStream2.read(bArr); i != -1; i = zipInputStream2.read(bArr)) {
                                fileOutputStream.write(bArr, 0, i);
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(zipInputStream, null);
                            zipInputStream2.closeEntry();
                        } finally {
                        }
                    } else if (nextEntry == null) {
                        Log.w("UnzipLookup", "File ZIP '" + zipName + "' kosong.");
                        callback.invoke(false, "File ZIP kosong");
                        CloseableKt.closeFinally(zipInputStream, null);
                        return;
                    } else if (nextEntry.isDirectory()) {
                        Log.w("UnzipLookup", "Entry pertama dalam ZIP '" + zipName + "' adalah direktori, bukan file.");
                        callback.invoke(false, "Entry dalam ZIP adalah direktori");
                        CloseableKt.closeFinally(zipInputStream, null);
                        return;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(zipInputStream, null);
                    if (file2.exists()) {
                        callback.invoke(true, "Sukses");
                        return;
                    }
                    Log.e("UnzipLookup", "File jsonLookup '" + file2.getAbsolutePath() + "' tidak ditemukan setelah proses unzip seharusnya selesai.");
                    callback.invoke(false, "File json tidak ditemukan setelah unzip");
                } finally {
                }
            } catch (IOException e) {
                CrashHandler.INSTANCE.sendExeption(e);
                e.printStackTrace();
                Log.e("UnzipLookup", "IOException saat unzip: " + e.getMessage(), e);
                String message = e.getMessage();
                if (message == null) {
                    message = e.toString();
                }
                callback.invoke(false, message);
            }
        }

        public static /* synthetic */ boolean waitForFileNonZeroSize$default(Companion companion, File file, int i, long j, int i2, Object obj) {
            if ((i2 & 2) != 0) {
                i = 10;
            }
            if ((i2 & 4) != 0) {
                j = 1000;
            }
            return companion.waitForFileNonZeroSize(file, i, j);
        }

        public final boolean waitForFileNonZeroSize(File file, int maxAttempts, long delayMillis) throws InterruptedException {
            Intrinsics.checkNotNullParameter(file, "file");
            if (1 <= maxAttempts) {
                int i = 1;
                while (true) {
                    Log.d("WaitForFile", "Attempt " + i + ": Checking size of " + file.getAbsolutePath());
                    if (file.exists() && file.length() > 0) {
                        Log.i("WaitForFile", "File " + file.getName() + " found with size " + file.length());
                        return true;
                    }
                    try {
                        Thread.sleep(delayMillis);
                        if (i == maxAttempts) {
                            break;
                        }
                        i++;
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        Log.w("WaitForFile", "Wait interrupted for " + file.getName(), e);
                        return false;
                    }
                }
            }
            Log.w("WaitForFile", "File " + file.getName() + " did not reach non-zero size after " + maxAttempts + " attempts.");
            return false;
        }

        public final void zip(String globalRemark, String path, String toPath, boolean isSaveAnswerToDraft, boolean isUpdateStatusAssignment) throws JSONException, IOException {
            Intrinsics.checkNotNullParameter(globalRemark, "globalRemark");
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(toPath, "toPath");
            File file = new File(toPath, new File(path).getName() + ".zip");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            String path2 = file.getPath();
            File file2 = new File(path);
            String[] list = file2.list();
            if (list != null) {
                FileInputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(path2)));
                try {
                    ZipOutputStream zipOutputStream2 = zipOutputStream;
                    for (String str : list) {
                        String str2 = file2.getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR + str;
                        zipOutputStream = new FileInputStream(str2);
                        try {
                            FileInputStream fileInputStream = zipOutputStream;
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                            try {
                                String strSubstring = str2.substring(StringsKt.lastIndexOf$default((CharSequence) str2, InternalZipConstants.ZIP_FILE_SEPARATOR, 0, false, 6, (Object) null));
                                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                                zipOutputStream2.putNextEntry(new ZipEntry(strSubstring));
                                ByteStreamsKt.copyTo(bufferedInputStream, zipOutputStream2, 1024);
                                fileInputStream.close();
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(bufferedInputStream, null);
                                Unit unit2 = Unit.INSTANCE;
                                CloseableKt.closeFinally(zipOutputStream, null);
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    CloseableKt.closeFinally(bufferedInputStream, th);
                                    throw th2;
                                }
                            }
                        } finally {
                        }
                    }
                    zipOutputStream2.close();
                    Unit unit3 = Unit.INSTANCE;
                    CloseableKt.closeFinally(zipOutputStream, null);
                } catch (Throwable th3) {
                    try {
                        throw th3;
                    } finally {
                    }
                }
            }
        }

        public final void extractZip(String inpath, String outpath) {
            Intrinsics.checkNotNullParameter(inpath, "inpath");
            Intrinsics.checkNotNullParameter(outpath, "outpath");
            Z7Extractor.extractFile(inpath, outpath, new IExtractCallback() { // from class: id.go.bpsfasih.utils.helper.ZipHelper$Companion$extractZip$1
                @Override // com.hzy.lib7z.IExtractCallback
                public void onSucceed() {
                    Log.d("extractzip", "exctract success");
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onGetFileNum(int fileNum) {
                    Log.d("getfilenum", String.valueOf(fileNum));
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onProgress(String name, long size) {
                    Log.d("onprogress", name + " " + size);
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onError(int errorCode, String message) {
                    Log.d("onerror", message + " " + errorCode);
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onStart() {
                    Log.d("onstart", "onstart");
                }
            });
        }

        public final void extractZip(String inpath, String outpath, final Function1<? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(inpath, "inpath");
            Intrinsics.checkNotNullParameter(outpath, "outpath");
            Intrinsics.checkNotNullParameter(callback, "callback");
            Z7Extractor.extractFile(inpath, outpath, new IExtractCallback() { // from class: id.go.bpsfasih.utils.helper.ZipHelper$Companion$extractZip$2
                @Override // com.hzy.lib7z.IExtractCallback
                public void onSucceed() {
                    Log.d("extractzip", "exctract success");
                    callback.invoke(true);
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onGetFileNum(int fileNum) {
                    Log.d("getfilenum", String.valueOf(fileNum));
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onProgress(String name, long size) {
                    Log.d("onprogress", name + " " + size);
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onError(int errorCode, String message) {
                    Log.d("onerror", message + " " + errorCode);
                    callback.invoke(false);
                }

                @Override // com.hzy.lib7z.IExtractCallback
                public void onStart() {
                    Log.d("onstart", "onstart");
                }
            });
        }

        public final void zipFile(String inputPath, String outputPath) {
            Intrinsics.checkNotNullParameter(inputPath, "inputPath");
            Intrinsics.checkNotNullParameter(outputPath, "outputPath");
            P7ZipApi.executeCommand(getCompressCmd(inputPath, outputPath + ".zip", "zip"));
        }

        public final void zip7File(String inputPath, String outputPath) {
            Intrinsics.checkNotNullParameter(inputPath, "inputPath");
            Intrinsics.checkNotNullParameter(outputPath, "outputPath");
            P7ZipApi.executeCommand(getCompressCmd(inputPath, outputPath + ".7z", "7z"));
        }

        public final String getCompressCmd(String filePath, String outPath, String type) {
            Intrinsics.checkNotNullParameter(filePath, "filePath");
            Intrinsics.checkNotNullParameter(outPath, "outPath");
            Intrinsics.checkNotNullParameter(type, "type");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("7z a -t%s '%s' '%s'", Arrays.copyOf(new Object[]{type, outPath, filePath}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            return str;
        }

        public final void protectedZip(String inputPath, String outputPathFolderZip, char[] passwordChars, Function2<? super Boolean, ? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(inputPath, "inputPath");
            Intrinsics.checkNotNullParameter(outputPathFolderZip, "outputPathFolderZip");
            Intrinsics.checkNotNullParameter(passwordChars, "passwordChars");
            Intrinsics.checkNotNullParameter(callback, "callback");
            File file = new File(inputPath);
            File file2 = new File(outputPathFolderZip, file.getName() + ".zip");
            try {
                if (!file.exists()) {
                    Log.e("ZipCreation", "File input tidak ditemukan: " + file.getAbsolutePath());
                    callback.invoke(false, "File input tidak ditemukan");
                }
                ZipFile zipFile = new ZipFile(file2, passwordChars);
                ZipParameters zipParameters = new ZipParameters();
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
                zipFile.addFolder(file, zipParameters);
                FilesKt.deleteRecursively(file);
                callback.invoke(true, "Sukses membuat zip terproteksi kata sandi");
            } catch (Exception e) {
                CrashlyticHelper.INSTANCE.sendException(e);
                Log.e("ZipCreation", "Error membuat ZIP terproteksi kata sandi: " + e.getMessage(), e);
                callback.invoke(false, "Error membuat ZIP terproteksi kata sandi");
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0080  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void zipBPS(java.lang.String r6, android.net.Uri r7, kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.String, kotlin.Unit> r8) throws java.lang.Throwable {
            /*
                r5 = this;
                java.lang.String r0 = "Error : "
                java.lang.String r1 = "folder"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
                java.lang.String r1 = "uri"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r1)
                java.lang.String r1 = "callback"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)
                java.io.File r1 = new java.io.File
                r1.<init>(r6)
                r6 = 0
                java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                id.go.bpsfasih.FasihApp$Companion r4 = id.go.bpsfasih.FasihApp.INSTANCE     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                android.content.Context r4 = r4.getContext()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                android.content.ContentResolver r4 = r4.getContentResolver()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                java.io.OutputStream r7 = r4.openOutputStream(r7)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                r3.<init>(r7)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                r2.<init>(r3)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L51
                java.lang.String r6 = r1.getAbsolutePath()     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L7d
                java.lang.String r7 = "folderToZip.absolutePath"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L7d
                r5.recursivelyAddZipEntries(r1, r6, r2)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L7d
                r6 = 1
                java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L7d
                java.lang.String r7 = "Sukses melakukan backup folder BPS"
                r8.invoke(r6, r7)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L7d
            L47:
                r2.close()
                goto L7c
            L4b:
                r6 = move-exception
                goto L54
            L4d:
                r7 = move-exception
                r2 = r6
                r6 = r7
                goto L7e
            L51:
                r7 = move-exception
                r2 = r6
                r6 = r7
            L54:
                java.lang.String r7 = "ZIP Err"
                java.lang.String r1 = r6.getMessage()     // Catch: java.lang.Throwable -> L7d
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Throwable -> L7d
                android.util.Log.e(r7, r1)     // Catch: java.lang.Throwable -> L7d
                r7 = 0
                java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)     // Catch: java.lang.Throwable -> L7d
                java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L7d
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L7d
                r1.<init>(r0)     // Catch: java.lang.Throwable -> L7d
                java.lang.StringBuilder r6 = r1.append(r6)     // Catch: java.lang.Throwable -> L7d
                java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L7d
                r8.invoke(r7, r6)     // Catch: java.lang.Throwable -> L7d
                if (r2 == 0) goto L7c
                goto L47
            L7c:
                return
            L7d:
                r6 = move-exception
            L7e:
                if (r2 == 0) goto L83
                r2.close()
            L83:
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.helper.ZipHelper.Companion.zipBPS(java.lang.String, android.net.Uri, kotlin.jvm.functions.Function2):void");
        }

        /* JADX WARN: Code restructure failed: missing block: B:26:0x00ba, code lost:
        
            r2 = 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.String[] unZipBPS(android.net.Uri r24) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 822
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.helper.ZipHelper.Companion.unZipBPS(android.net.Uri):java.lang.String[]");
        }

        private final void recursivelyAddZipEntries(File folder, String basePath, ZipOutputStream out) {
            File[] fileArrListFiles = folder.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            for (File file : fileArrListFiles) {
                if (file.isDirectory()) {
                    Intrinsics.checkNotNullExpressionValue(file, "file");
                    recursivelyAddZipEntries(file, basePath, out);
                } else {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                    try {
                        String path = file.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "file.path");
                        String strSubstring = path.substring(basePath.length());
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                        out.putNextEntry(new ZipEntry(strSubstring));
                        ByteStreamsKt.copyTo(bufferedInputStream, out, 1024);
                        CloseableKt.closeFinally(bufferedInputStream2, null);
                    } finally {
                    }
                }
            }
        }
    }
}
