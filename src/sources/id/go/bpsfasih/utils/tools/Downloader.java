package id.go.bpsfasih.utils.tools;

import android.net.Uri;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.messaging.Constants;
import com.kdownloader.KDownloader;
import com.kdownloader.internal.DownloadRequest;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.LookupsList;
import id.go.bpsfasih.data.local.entities.SurveyEntity;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.domain.models.AssignmentBeforeSaveModel;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import java.io.File;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;

/* compiled from: Downloader.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/tools/Downloader;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Downloader {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String BASE_URL = new Config().BASE_URL();

    /* compiled from: Downloader.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002Jh\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r28\u0010\u000e\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00060\u000fJV\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u000426\u0010\u000e\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u00060\u000fJT\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042<\b\u0002\u0010\u001b\u001a6\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001a\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u000fJX\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u000428\u0010\u001b\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00060\u000fJF\u0010 \u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u000426\u0010!\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00060\u000fJH\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020$28\u0010\u001b\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0004¢\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u00060\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lid/go/bpsfasih/utils/tools/Downloader$Companion;", "", "()V", Config.BASE_URL, "", "downloadFileAssignment", "", "survey", "Lid/go/bpsfasih/data/local/entities/SurveyEntity;", "periodeId", ClientCookie.PATH_ATTR, "fileName", "surveyTypeIsUpdateListing", "", "downloadCallback", "Lkotlin/Function2;", "Lid/go/bpsfasih/domain/models/AssignmentBeforeSaveModel;", "Lkotlin/ParameterName;", "name", "result", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "downloadFileAssignmentNotif", "url", "userId", "destinationFile", "downloadTemplateValidationNotif", "templateId", "callback", "requestAnswer", StorageChooser.DIRECTORY_CHOOSER, "message", NotificationCompat.CATEGORY_ERROR, "requestFormgear", "myCallback", "requestLookup", "lookup", "Lid/go/bpsfasih/data/local/entities/LookupsList;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void downloadFileAssignment(final SurveyEntity survey, final String periodeId, String path, String fileName, final boolean surveyTypeIsUpdateListing, final Function2<? super AssignmentBeforeSaveModel, ? super Boolean, Unit> downloadCallback) {
            Intrinsics.checkNotNullParameter(survey, "survey");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(downloadCallback, "downloadCallback");
            String userId = FasihApp.INSTANCE.getSession().getUserId();
            final File file = new File(FileHelper.INSTANCE.extractAssignmentTemporary(userId), fileName);
            if (file.exists()) {
                FilesKt.deleteRecursively(file);
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(Downloader.BASE_URL + path, FileHelper.INSTANCE.extractAssignmentTemporary(userId), fileName);
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$downloadFileAssignment$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Log.d("Download_File_Assign", error);
                    downloadCallback.invoke(null, true);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    downloadCallback.invoke(new AssignmentBeforeSaveModel(survey, periodeId, file.getPath(), Boolean.valueOf(surveyTypeIsUpdateListing)), false);
                }
            });
        }

        public final void downloadFileAssignmentNotif(String url, String fileName, String userId, final Function2<? super String, ? super Boolean, Unit> downloadCallback) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(userId, "userId");
            Intrinsics.checkNotNullParameter(downloadCallback, "downloadCallback");
            final File file = new File(FileHelper.INSTANCE.extractAssignmentTemporary(userId), fileName);
            if (file.exists()) {
                FilesKt.deleteRecursively(file);
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(url, FileHelper.INSTANCE.extractAssignmentTemporary(userId), fileName);
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$downloadFileAssignmentNotif$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Log.d("Download_File_Assign", error);
                    downloadCallback.invoke(error, true);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    Log.d("Download_File_Assign", " Sukses");
                    Function2 function2 = downloadCallback;
                    String path = file.getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "destinationFile.path");
                    function2.invoke(path, false);
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void downloadTemplateValidationNotif$default(Companion companion, String str, String str2, Function2 function2, int i, Object obj) {
            if ((i & 4) != 0) {
                function2 = null;
            }
            companion.downloadTemplateValidationNotif(str, str2, function2);
        }

        public final void downloadTemplateValidationNotif(String url, final String templateId, final Function2<? super String, ? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(templateId, "templateId");
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHTEMPLATEDSIGNER());
            if (!file.exists()) {
                file.mkdirs();
            }
            Uri.parse(url);
            File file2 = new File(Directory.INSTANCE.getABSOLUTEPATHTEMPLATEDSIGNER() + templateId + File.separator);
            if (file2.exists()) {
                FilesKt.deleteRecursively(file2);
                file2.delete();
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(url, Directory.INSTANCE.getABSOLUTEPATHTEMPLATEDSIGNER() + templateId, templateId + CommonCons.INSTANCE.getEXTENSION_ZIP());
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$downloadTemplateValidationNotif$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    Function2 function2 = callback;
                    if (function2 != null) {
                        function2.invoke(templateId, true);
                    }
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    Function2 function2 = callback;
                    if (function2 != null) {
                        function2.invoke(templateId, false);
                    }
                }
            });
        }

        public final void requestFormgear(String url, final Function2<? super Boolean, ? super String, Unit> myCallback) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(myCallback, "myCallback");
            if (url.length() == 0) {
                myCallback.invoke(false, "Gagal mendapatkan link unduh Form Gear. Harap keluar/kill dari aplikasi Fasih dan coba ganti koneksi internet Anda. Setelah itu ulangi Unduh Form Gear.");
                return;
            }
            File file = new File(Directory.INSTANCE.getFORMENGINE_FILE_TEMP());
            if (file.exists()) {
                FilesKt.deleteRecursively(file);
                file.delete();
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(url, Directory.INSTANCE.getABSOLUTEPATH(), Directory.INSTANCE.getFORMENGINE_FILE_TEMP() + CommonCons.INSTANCE.getEXTENSION_ZIP());
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$requestFormgear$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    myCallback.invoke(false, error);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    ZipHelper.INSTANCE.unZip(CommonCons.INSTANCE.getABSOLUTE_PATH(), Directory.INSTANCE.getFORMENGINE_FILE_TEMP() + CommonCons.INSTANCE.getEXTENSION_ZIP(), true);
                    myCallback.invoke(true, "Sukses mengunduh Form Gear");
                }
            });
        }

        public final void requestLookup(LookupsList lookup, final Function2<? super Boolean, ? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(lookup, "lookup");
            Intrinsics.checkNotNullParameter(callback, "callback");
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHLOOKUP());
            if (!file.exists()) {
                file.mkdirs();
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(Downloader.BASE_URL + "/mobile/lookup/api//v1/collections/" + lookup.getId() + "/download-zip-v2?version=" + lookup.getVersion(), Directory.INSTANCE.getABSOLUTEPATHLOOKUP() + lookup.getId(), lookup.getVersion() + CommonCons.INSTANCE.getEXTENSION_ZIP());
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$requestLookup$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    callback.invoke(false, null);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    callback.invoke(true, error);
                }
            });
        }

        public final void requestAnswer(String url, String dir, String fileName, final Function2<? super String, ? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(dir, "dir");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(callback, "callback");
            File file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(dir + InternalZipConstants.ZIP_FILE_SEPARATOR + fileName);
            if (file2.exists()) {
                FilesKt.deleteRecursively(file2);
            }
            KDownloader kDownloaderCreate$default = KDownloader.Companion.create$default(KDownloader.INSTANCE, FasihApp.INSTANCE.getContext(), null, 2, null);
            DownloadRequest.Builder builderNewRequestBuilder = kDownloaderCreate$default.newRequestBuilder(url, dir, fileName);
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_AUTH());
            Intrinsics.checkNotNull(sessionString);
            kDownloaderCreate$default.enqueue(builderNewRequestBuilder.headers(MapsKt.hashMapOf(TuplesKt.to("Authorization", CollectionsKt.listOf(sessionString)))).build(), new DownloadRequest.Listener() { // from class: id.go.bpsfasih.utils.tools.Downloader$Companion$requestAnswer$$inlined$enqueue$1
                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onPause() {
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onStart() {
                    Log.d("Download_File_Assign", "downloadFileAssignment: start");
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onProgress(int value) {
                    Log.d("Download_File_Assign", "Waiting " + value);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onCompleted() {
                    callback.invoke("Sukses", false);
                }

                @Override // com.kdownloader.internal.DownloadRequest.Listener
                public void onError(String error) {
                    Intrinsics.checkNotNullParameter(error, "error");
                    callback.invoke(error, true);
                }
            });
        }
    }
}
