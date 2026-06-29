package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.models.ImageCompressResolutionRemoteConfig;
import id.go.bpsfasih.domain.models.LocationHistory;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.Directory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import okhttp3.HttpUrl;
import org.apache.http.cookie.ClientCookie;

/* compiled from: FileHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/FileHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FileHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TEMPLATE_PATH = Directory.INSTANCE.getABSOLUTEPATHTEMPLATEDSIGNER();

    /* compiled from: FileHelper.kt */
    @Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JR\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u000428\u0010\n\u001a4\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0015\u0012\u0013\u0018\u00010\u0010¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00060\u000bJ/\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\f¢\u0006\u0002\u0010\u0018JN\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u000426\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001c\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00060\u000bJF\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000426\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00060\u000bJ\b\u0010!\u001a\u00020\u0010H\u0002J\u000e\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004J\u0006\u0010$\u001a\u00020\u0004J\u000e\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0004JF\u0010'\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u000426\u0010\u001b\u001a2\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00060\u000bJ\u0018\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002J\u001e\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004J\u0016\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004J\u001e\u0010/\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u0004J\u000e\u00101\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004J\u0006\u00102\u001a\u00020\u0004J\u000e\u00103\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004J\u0006\u00104\u001a\u00020\u0004J\u0012\u00105\u001a\u00020\u00042\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0004J\u0016\u00107\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0004J\u001e\u00108\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u00109\u001a\u00020\u0004J\u0012\u0010:\u001a\u00020\u00042\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0004J\u0012\u0010;\u001a\u00020\u00042\n\b\u0002\u00106\u001a\u0004\u0018\u00010\u0004J\u001c\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00102\n\u0010>\u001a\u00060?j\u0002`@H\u0002J\u0010\u0010A\u001a\u0004\u0018\u00010\u00042\u0006\u0010.\u001a\u00020\u0004J\u0012\u0010B\u001a\u00060?j\u0002`@2\u0006\u0010C\u001a\u00020\u0004J\u0012\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040F0EJ\u001b\u0010G\u001a\n\u0012\u0004\u0012\u00020I\u0018\u00010H2\u0006\u0010J\u001a\u00020\u0004¢\u0006\u0002\u0010KJ \u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00102\u0006\u0010N\u001a\u00020\u00102\u0006\u0010O\u001a\u00020\u0010H\u0002JU\u0010P\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0017\u001a\u00020\f2\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060R¢\u0006\u0002\u0010SJc\u0010T\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010U\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010Q\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060RH\u0002¢\u0006\u0002\u0010VJ4\u0010W\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010U\u001a\u00020\fH\u0002J\u0016\u0010X\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u00042\u0006\u0010Y\u001a\u00020\u0004J\u001e\u0010Z\u001a\u00020\u00062\u0006\u0010C\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u0004J\u0018\u0010[\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00102\u0006\u0010\\\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lid/go/bpsfasih/utils/helper/FileHelper$Companion;", "", "()V", "TEMPLATE_PATH", "", "compressImage", "", "context", "Landroid/content/Context;", "uri", "callBack", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isDone", "Ljava/io/File;", FirebaseAnalytics.Param.DESTINATION, "copyDataAssignmentResult", "sourceFile", "timestampNow", "", "fileName", "isEncrypt", "(Ljava/io/File;Ljava/lang/Long;Ljava/lang/String;Z)V", "copyFolder", "origin", "callback", FirebaseAnalytics.Param.SUCCESS, "message", "deleteListDownloaded", "assignmentIdPrimary", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "externalStorage", "extractAssignmentTemporary", "userID", "getAbsolutePathAnswer", "getAbsolutePathUserForAssignment", "filename", "insertListDownloaded", "moveItem", "sources", "moveToAnswer", "source", "pathAnswer", "surveyId", "periodeId", "pathAnswerAssignment", "assignmentId", "pathDataSort", "pathExternalStorage", "pathFolderUiAssignment", "pathQuestionnaire", "pathQuestionnaireQ", "templateId", "pathTempAnswer", "pathTempAnswerZip", "zipName", "pathTemplate", "pathValidation", "read", StorageChooser.FILE_PICKER, "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "readDataSort", "readFile", ClientCookie.PATH_ATTR, "readListDownloaded", "Landroidx/lifecycle/LiveData;", "", "readLocationHistoryAsObject", "", "Lid/go/bpsfasih/domain/models/LocationHistory;", "answerPath", "(Ljava/lang/String;)[Lid/go/bpsfasih/domain/models/LocationHistory;", "replaceFileSafely", "targetFile", "tempFile", "previousFile", "saveAssignmentFile", "wrappedDataKey", "Lkotlin/Function1;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;)V", "saveFile", "isBackup", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/Long;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "write", "writeDataSort", "param", "writeFile", "writeUtf8", "data", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String pathExternalStorage() {
            externalStorage();
            return ContextCompat.getExternalFilesDirs(FasihApp.INSTANCE.getContext(), null)[0].getAbsolutePath() + File.separator + CommonCons.INSTANCE.getAPPLICATION_STORAGE();
        }

        private final File externalStorage() {
            File file = new File(ContextCompat.getExternalFilesDirs(FasihApp.INSTANCE.getContext(), null)[0].getAbsolutePath() + File.separator + CommonCons.INSTANCE.getAPPLICATION_STORAGE());
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }

        public final StringBuilder readFile(String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            File file = new File(path);
            StringBuilder sb = new StringBuilder();
            if (file.exists()) {
                read(file, sb);
            }
            return sb;
        }

        private final synchronized void read(File file, StringBuilder sb) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
                char[] cArr = new char[1024];
                int i = bufferedReader.read(cArr);
                while (i > 0) {
                    String str = new String(cArr, 0, i);
                    sb.append(str);
                    Log.d("read", str);
                    i = bufferedReader.read(cArr);
                    if (i == -1) {
                        break;
                    }
                }
            } catch (IOException e) {
                CrashHandler.INSTANCE.sendExeption(e);
                e.printStackTrace();
            }
        }

        public final void saveAssignmentFile(String path, String fileName, String sources, Long timestampNow, boolean isEncrypt, String wrappedDataKey, final Function1<? super Boolean, Unit> callback) throws IOException {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(sources, "sources");
            Intrinsics.checkNotNullParameter(callback, "callback");
            saveFile(path, fileName, sources, isEncrypt, true, timestampNow, wrappedDataKey, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.helper.FileHelper$Companion$saveAssignmentFile$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    callback.invoke(Boolean.valueOf(z));
                }
            });
        }

        public final void writeFile(String path, String fileName, String sources) throws IOException {
            Intrinsics.checkNotNullParameter(path, "path");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(sources, "sources");
            write(path, fileName, sources, false, true);
        }

        public static /* synthetic */ void copyDataAssignmentResult$default(Companion companion, File file, Long l, String str, boolean z, int i, Object obj) {
            if ((i & 8) != 0) {
                z = false;
            }
            companion.copyDataAssignmentResult(file, l, str, z);
        }

        public final void copyDataAssignmentResult(File sourceFile, Long timestampNow, String fileName, boolean isEncrypt) {
            Intrinsics.checkNotNullParameter(sourceFile, "sourceFile");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            if (sourceFile.exists()) {
                String str = Directory.INSTANCE.getABSOLUTEPATHBACKUP() + new File(sourceFile.getParent()).getName();
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, String.valueOf(timestampNow));
                if (!file2.exists()) {
                    file2.mkdirs();
                }
                FilesKt.copyTo$default(sourceFile, new File(file2, fileName), true, 0, 4, null);
                String[] list = new File(str).list();
                if (list == null) {
                    return;
                }
                ArraysKt.sortDescending(list);
                int i = 0;
                for (String str2 : list) {
                    i++;
                    if (i > 3) {
                        FilesKt.deleteRecursively(new File(str + File.separator + str2));
                    }
                }
            }
        }

        public final String pathQuestionnaire() {
            return pathExternalStorage() + File.separator + CommonCons.INSTANCE.getWEB_FOLDER() + File.separator + CommonCons.INSTANCE.getWEB_INDEX();
        }

        public static /* synthetic */ String pathQuestionnaireQ$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "sensus";
            }
            return companion.pathQuestionnaireQ(str);
        }

        public final String pathQuestionnaireQ(String templateId) {
            return FileHelper.TEMPLATE_PATH + templateId + File.separator + templateId + ".json";
        }

        public static /* synthetic */ String pathTemplate$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "sensus";
            }
            return companion.pathTemplate(str);
        }

        public final String pathTemplate(String templateId) {
            return FileHelper.TEMPLATE_PATH + templateId + File.separator + templateId + "_template.json";
        }

        public static /* synthetic */ String pathValidation$default(Companion companion, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "sensus";
            }
            return companion.pathValidation(str);
        }

        public final String pathValidation(String templateId) {
            return FileHelper.TEMPLATE_PATH + templateId + File.separator + templateId + "_validation.json";
        }

        public final String pathDataSort(String periodeId) {
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            return CommonCons.INSTANCE.getABSOLUTE_PATH() + "/env/ui_assignment/" + periodeId + "/sort.json";
        }

        public final String pathFolderUiAssignment(String periodeId) {
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            return CommonCons.INSTANCE.getABSOLUTE_PATH() + "/env/ui_assignment/" + periodeId;
        }

        public final String extractAssignmentTemporary(String userID) {
            Intrinsics.checkNotNullParameter(userID, "userID");
            String str = pathExternalStorage() + File.separator + userID + File.separator + CommonCons.INSTANCE.getASSIGNMENT_FOLDER_TEMP() + File.separator;
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return str;
        }

        public final String getAbsolutePathAnswer() {
            return CommonCons.INSTANCE.getABSOLUTE_PATH() + FasihApp.INSTANCE.getSession().getUserId() + File.separator + CommonCons.INSTANCE.getPATH_ANSWER() + File.separator;
        }

        public final String pathAnswerAssignment(String surveyId, String periodeId, String assignmentId) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
            return getAbsolutePathAnswer() + surveyId + File.separator + periodeId + File.separator + assignmentId;
        }

        public final String pathTempAnswer(String surveyId, String periodeId) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            return getAbsolutePathAnswer() + surveyId + File.separator + periodeId + File.separator + "temp";
        }

        public final String pathAnswer(String surveyId, String periodeId) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            return getAbsolutePathAnswer() + surveyId + File.separator + periodeId;
        }

        public final String pathTempAnswerZip(String surveyId, String periodeId, String zipName) {
            Intrinsics.checkNotNullParameter(surveyId, "surveyId");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(zipName, "zipName");
            return getAbsolutePathAnswer() + surveyId + File.separator + periodeId + File.separator + "temp" + File.separator + zipName;
        }

        public final String getAbsolutePathUserForAssignment(String filename) {
            Intrinsics.checkNotNullParameter(filename, "filename");
            String str = Directory.INSTANCE.getABSOLUTEPATH() + FasihApp.INSTANCE.getSession().getUserId() + "/usersAssignment/";
            if (!new File(str).exists()) {
                new File(str).mkdirs();
            }
            return str + filename;
        }

        public final void moveToAnswer(String source, String destination, String fileName) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(destination, "destination");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            File file = new File(source);
            File file2 = new File(destination + File.separator + FormGearJavascriptInterface.MEDIA_FILE);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            moveItem(file, new File(file2, fileName));
        }

        private final void moveItem(File sources, File destination) {
            if (sources.exists()) {
                FilesKt.copyTo(sources, destination, true, 1024);
                sources.delete();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void saveFile(String path, String fileName, String sources, boolean isEncrypt, boolean isBackup, Long timestampNow, String wrappedDataKey, Function1<? super Boolean, Unit> callback) throws IOException {
            String strEncrypt;
            if (isEncrypt) {
                try {
                    strEncrypt = AssignmentEncryptionHelper.INSTANCE.encrypt(sources, wrappedDataKey);
                } catch (Exception unused) {
                    callback.invoke(false);
                }
            } else {
                strEncrypt = sources;
            }
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(path, fileName);
            File file3 = new File(path, fileName + ".tmp");
            File file4 = new File(path, fileName + ".previous");
            if (isBackup && !fileName.equals("reference.json")) {
                copyDataAssignmentResult$default(this, file2, timestampNow, fileName, false, 8, null);
            }
            writeUtf8(file3, strEncrypt);
            replaceFileSafely(file2, file3, file4);
            callback.invoke(Boolean.valueOf(file2.exists() && file2.length() > 0));
        }

        private final void writeUtf8(File file, String data) throws IOException {
            OutputStreamWriter fileOutputStream = new FileOutputStream(file);
            try {
                FileOutputStream fileOutputStream2 = fileOutputStream;
                fileOutputStream = new OutputStreamWriter(fileOutputStream2, StandardCharsets.UTF_8);
                try {
                    OutputStreamWriter outputStreamWriter = fileOutputStream;
                    outputStreamWriter.write(data);
                    outputStreamWriter.flush();
                    fileOutputStream2.getFD().sync();
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileOutputStream, null);
                } finally {
                }
            } finally {
            }
        }

        private final void replaceFileSafely(File targetFile, File tempFile, File previousFile) throws IOException {
            if (previousFile.exists()) {
                previousFile.delete();
            }
            if (targetFile.exists() && !targetFile.renameTo(previousFile)) {
                throw new IOException("Failed to preserve previous file: " + targetFile.getName());
            }
            if (!tempFile.renameTo(targetFile)) {
                if (previousFile.exists()) {
                    previousFile.renameTo(targetFile);
                }
                throw new IOException("Failed to replace file: " + targetFile.getName());
            }
            if (previousFile.exists()) {
                previousFile.delete();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void write(String path, String fileName, String sources, boolean isEncrypt, boolean isBackup) throws IOException {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(path, fileName);
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(sources);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.flush();
            fileOutputStream.close();
            if (isEncrypt) {
                Log.d("encrypt", String.valueOf(isEncrypt));
            }
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r7v1, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r8v1, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r8v7, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r9v4, types: [T, java.lang.Integer] */
        /* JADX WARN: Type inference failed for: r9v5, types: [T, java.lang.Integer] */
        public final void compressImage(Context context, String uri, Function2<? super Boolean, ? super File, Unit> callBack) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(callBack, "callBack");
            if (uri != null) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = 450;
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = 600;
                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                objectRef3.element = 75;
                try {
                    ImageCompressResolutionRemoteConfig imageCompressResolutionRemoteConfig = (ImageCompressResolutionRemoteConfig) new Gson().fromJson(FasihApp.INSTANCE.getSession().getImageCompressResolution(), ImageCompressResolutionRemoteConfig.class);
                    objectRef.element = imageCompressResolutionRemoteConfig.getWidth();
                    objectRef2.element = imageCompressResolutionRemoteConfig.getHeight();
                    objectRef3.element = imageCompressResolutionRemoteConfig.getQuality();
                } catch (Exception unused) {
                    callBack.invoke(false, null);
                }
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new FileHelper$Companion$compressImage$1(context, new File(uri), callBack, objectRef, objectRef2, objectRef3, null), 3, null);
                return;
            }
            callBack.invoke(false, null);
        }

        public final LiveData<List<String>> readListDownloaded() {
            MutableLiveData mutableLiveData = new MutableLiveData();
            try {
                File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + CommonCons.INSTANCE.getLIST_UNDUH_CHANGE_MODE_DOWNLOAD());
                if (file2.exists()) {
                    String[] listJson = (String[]) new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), String[].class);
                    Intrinsics.checkNotNullExpressionValue(listJson, "listJson");
                    mutableLiveData.setValue(ArraysKt.toList(listJson));
                }
            } catch (Exception unused) {
            }
            return mutableLiveData;
        }

        public final void insertListDownloaded(String assignmentIdPrimary, Function2<? super Boolean, ? super String, Unit> callback) throws IOException {
            Intrinsics.checkNotNullParameter(assignmentIdPrimary, "assignmentIdPrimary");
            Intrinsics.checkNotNullParameter(callback, "callback");
            try {
                File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + CommonCons.INSTANCE.getLIST_UNDUH_CHANGE_MODE_DOWNLOAD());
                if (!file2.exists()) {
                    try {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file2);
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                            outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                            outputStreamWriter.flush();
                            outputStreamWriter.close();
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            CrashHandler.INSTANCE.sendExeption(e);
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    file2.createNewFile();
                }
                Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) String[].class);
                Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…rray<String>::class.java)");
                List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
                mutableList.add(assignmentIdPrimary);
                String jsonString = new Gson().toJson(mutableList);
                Companion companion = FileHelper.INSTANCE;
                String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
                String list_unduh_change_mode_download = CommonCons.INSTANCE.getLIST_UNDUH_CHANGE_MODE_DOWNLOAD();
                Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
                companion.writeFile(absolutepathenv, list_unduh_change_mode_download, jsonString);
                callback.invoke(false, "Sukses menambah assignment id");
            } catch (Exception e3) {
                callback.invoke(true, String.valueOf(e3.getMessage()));
            }
        }

        public final void deleteListDownloaded(String assignmentIdPrimary, Function2<? super Boolean, ? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(assignmentIdPrimary, "assignmentIdPrimary");
            Intrinsics.checkNotNullParameter(callback, "callback");
            try {
                File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + CommonCons.INSTANCE.getLIST_UNDUH_CHANGE_MODE_DOWNLOAD());
                if (file2.exists()) {
                    Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) String[].class);
                    Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…rray<String>::class.java)");
                    List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
                    mutableList.remove(assignmentIdPrimary);
                    String jsonString = new Gson().toJson(mutableList);
                    String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
                    String list_unduh_change_mode_download = CommonCons.INSTANCE.getLIST_UNDUH_CHANGE_MODE_DOWNLOAD();
                    Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
                    writeFile(absolutepathenv, list_unduh_change_mode_download, jsonString);
                }
                callback.invoke(false, "Sukses menambah assignment id");
            } catch (Exception e) {
                callback.invoke(true, String.valueOf(e.getMessage()));
            }
        }

        public final LocationHistory[] readLocationHistoryAsObject(String answerPath) throws IOException {
            Intrinsics.checkNotNullParameter(answerPath, "answerPath");
            try {
                File file = new File(answerPath);
                if (!file.exists()) {
                    try {
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                            outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                            outputStreamWriter.flush();
                            outputStreamWriter.close();
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException e) {
                            CrashHandler.INSTANCE.sendExeption(e);
                            e.printStackTrace();
                        }
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    file.createNewFile();
                }
                return (LocationHistory[]) new Gson().fromJson(FilesKt.readText$default(file, null, 1, null), LocationHistory[].class);
            } catch (Exception unused) {
                return null;
            }
        }

        public final String readDataSort(String periodeId) {
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            try {
                Reader inputStreamReader = new InputStreamReader(new FileInputStream(new File(pathDataSort(periodeId))), Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String text = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    return text;
                } finally {
                }
            } catch (Exception unused) {
                return null;
            }
        }

        public final void writeDataSort(String periodeId, String param) throws IOException {
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intrinsics.checkNotNullParameter(param, "param");
            try {
                File file = new File(pathFolderUiAssignment(periodeId));
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(pathDataSort(periodeId));
                if (!file2.exists()) {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.write("{}");
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        CrashHandler.INSTANCE.sendExeption(e2);
                        e2.printStackTrace();
                    }
                    file2.createNewFile();
                }
                writeFile(pathFolderUiAssignment(periodeId), "sort.json", param);
            } catch (Exception unused) {
            }
        }

        public final void copyFolder(String destination, String origin, Function2<? super Boolean, ? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            Intrinsics.checkNotNullParameter(origin, "origin");
            Intrinsics.checkNotNullParameter(callback, "callback");
            File file = new File(destination);
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            File file2 = new File(origin);
            if (!file2.exists()) {
                callback.invoke(true, "Folder tidak ditemukan.");
                return;
            }
            try {
                FilesKt.copyRecursively$default(file2, new File(file, file2.getName()), true, null, 4, null);
                callback.invoke(true, "Folder berhasil disalin");
            } catch (IOException e) {
                Log.e("CopyFolderError", "Error menyalin folder : " + e.getMessage(), e);
                callback.invoke(false, "Error menyalin folder");
            }
        }
    }
}
