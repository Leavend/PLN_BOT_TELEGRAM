package ru.bartwell.safhelper;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.UriPermission;
import android.net.Uri;
import android.util.Log;
import androidx.documentfile.provider.DocumentFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.http.cookie.ClientCookie;
import ru.bartwell.safhelper.Utils;

/* compiled from: SafHelper.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0005H\u0003J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0003J\u0006\u0010\u0019\u001a\u00020\u0017J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\u0005H\u0002J\u0006\u0010\u001b\u001a\u00020\u0017J\u0010\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0007J\u0010\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0005H\u0007J(\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020\u00072\b\u0010#\u001a\u0004\u0018\u00010$J\u000e\u0010%\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lru/bartwell/safhelper/SafHelper;", "", "context", "Landroid/content/Context;", "basePath", "", "intentRequestCode", "", "(Landroid/content/Context;Ljava/lang/String;I)V", "basePermittedPath", "permission", "Landroid/content/UriPermission;", "checkAndGetRelativePath", ClientCookie.PATH_ATTR, "createFile", "Ljava/io/OutputStream;", "filePath", "getDocumentFileFromPath", "Landroidx/documentfile/provider/DocumentFile;", "relativePath", "init", "", "internalMkdir", "", "dirPath", "isApplicable", "isPathOnManagedStorage", "isPermissionGranted", "mkdir", "mkdirs", "onActivityResult", "activity", "Landroid/app/Activity;", "requestCode", "resultCode", "resultData", "Landroid/content/Intent;", "requestPermissions", "safhelper_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes3.dex */
public final class SafHelper {
    private final String basePath;
    private String basePermittedPath;
    private final Context context;
    private final int intentRequestCode;
    private UriPermission permission;

    public SafHelper(Context context, String basePath, int i) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(basePath, "basePath");
        this.context = context;
        this.basePath = basePath;
        this.intentRequestCode = i;
        Log.d(Utils.TAG, "New instance");
        init();
    }

    public /* synthetic */ SafHelper(Context context, String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, str, (i2 & 4) != 0 ? 10001 : i);
    }

    private final void init() {
        Log.d(Utils.TAG, "Is SAF available: " + SafHelperKt.IS_SAF_AVAILABLE);
        if (SafHelperKt.IS_SAF_AVAILABLE) {
            PermissionHelper permissionHelper = new PermissionHelper(this.context, Utils.INSTANCE.normalizePath(this.basePath));
            this.basePermittedPath = permissionHelper.getBasePermittedPath();
            Log.d(Utils.TAG, "Base permitted path: " + this.basePermittedPath);
            this.permission = permissionHelper.getPermission();
            StringBuilder sb = new StringBuilder("Permission: ");
            UriPermission uriPermission = this.permission;
            StringBuilder sbAppend = sb.append(uriPermission != null ? uriPermission.getUri() : null).append(" (");
            UriPermission uriPermission2 = this.permission;
            Log.d(Utils.TAG, sbAppend.append(uriPermission2 != null ? Boolean.valueOf(uriPermission2.isWritePermission()) : null).append(')').toString());
        }
    }

    public final void requestPermissions(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Log.d(Utils.TAG, "requestPermissions");
        if (SafHelperKt.IS_SAF_AVAILABLE) {
            activity.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), this.intentRequestCode);
        }
    }

    public final boolean onActivityResult(Activity activity, int requestCode, int resultCode, Intent resultData) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Log.d(Utils.TAG, "onActivityResult(" + requestCode + ',' + resultCode + ')');
        if (requestCode != this.intentRequestCode) {
            return false;
        }
        if (resultCode != -1 || !SafHelperKt.IS_SAF_AVAILABLE) {
            return true;
        }
        Uri data = resultData != null ? resultData.getData() : null;
        Log.d(Utils.TAG, "onActivityResult: " + data);
        activity.grantUriPermission(activity.getPackageName(), data, 3);
        ContentResolver contentResolver = activity.getContentResolver();
        if (data == null) {
            Intrinsics.throwNpe();
        }
        contentResolver.takePersistableUriPermission(data, 3);
        init();
        return true;
    }

    public final boolean isPermissionGranted() {
        UriPermission uriPermission;
        if (SafHelperKt.IS_SAF_AVAILABLE && (uriPermission = this.permission) != null) {
            if (uriPermission == null) {
                Intrinsics.throwNpe();
            }
            if (uriPermission.isWritePermission()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isApplicable() {
        return SafHelperKt.IS_SAF_AVAILABLE && Utils.INSTANCE.findExternalStoragePath(this.context, this.basePath) != null;
    }

    private final boolean isPathOnManagedStorage(String path) {
        String str = this.basePermittedPath;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        return StringsKt.startsWith$default(path, str, false, 2, (Object) null);
    }

    public final boolean mkdirs(String dirPath) {
        Intrinsics.checkParameterIsNotNull(dirPath, "dirPath");
        Log.d(Utils.TAG, "mkdirs(" + dirPath + ')');
        String strCheckAndGetRelativePath = checkAndGetRelativePath(dirPath);
        Log.d(Utils.TAG, "mkdirs: relative path = " + strCheckAndGetRelativePath);
        if (strCheckAndGetRelativePath.length() == 0) {
            Log.d(Utils.TAG, "mkdirs: path is a root of the storage, exit");
            return true;
        }
        String str = "";
        for (String str2 : Utils.INSTANCE.splitPathToParts(strCheckAndGetRelativePath)) {
            if (!(str.length() == 0)) {
                str = str + File.separator;
            }
            str = str + str2;
            if (!internalMkdir(str)) {
                return false;
            }
        }
        return true;
    }

    private final String checkAndGetRelativePath(String path) {
        Log.d(Utils.TAG, "checkAndGetRelativePath(" + path + ')');
        if (!isApplicable()) {
            throw new UnsupportedOperationException("Can be done with SAF. You should check path with isApplicable() before call it");
        }
        String strNormalizePath = Utils.INSTANCE.normalizePath(path);
        Log.d(Utils.TAG, "checkAndGetRelativePath: normalized path = " + strNormalizePath);
        if (!isPathOnManagedStorage(strNormalizePath)) {
            throw new UnsupportedOperationException("Path is outside the managed storage. Path should starts with " + this.basePermittedPath);
        }
        Utils.Companion companion = Utils.INSTANCE;
        String str = this.basePermittedPath;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String relativePath = companion.getRelativePath(str, strNormalizePath);
        Log.d(Utils.TAG, "checkAndGetRelativePath: relative path = " + relativePath);
        return relativePath;
    }

    private final boolean internalMkdir(String dirPath) throws FileNotFoundException {
        Log.d(Utils.TAG, "internalMkdir(" + dirPath + ')');
        Pair<String, String> pathAndNameFromPath = Utils.INSTANCE.getPathAndNameFromPath(dirPath);
        String strComponent1 = pathAndNameFromPath.component1();
        String strComponent2 = pathAndNameFromPath.component2();
        DocumentFile documentFileFromPath = getDocumentFileFromPath(strComponent1);
        DocumentFile documentFileFindFile = documentFileFromPath.findFile(strComponent2);
        if (documentFileFindFile == null || !documentFileFindFile.exists()) {
            return documentFileFromPath.createDirectory(strComponent2) != null;
        }
        Log.d(Utils.TAG, "internalMkdir: directory already exists");
        return documentFileFindFile.isDirectory();
    }

    public final boolean mkdir(String dirPath) {
        Intrinsics.checkParameterIsNotNull(dirPath, "dirPath");
        Log.d(Utils.TAG, "mkdir(" + dirPath + ')');
        String strCheckAndGetRelativePath = checkAndGetRelativePath(dirPath);
        if (strCheckAndGetRelativePath.length() == 0) {
            Log.d(Utils.TAG, "mkdir: path is a root of the storage, exit");
            return true;
        }
        return internalMkdir(strCheckAndGetRelativePath);
    }

    public final OutputStream createFile(String filePath) throws FileNotFoundException {
        Uri uri;
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Log.d(Utils.TAG, "createFile(" + filePath + ')');
        String strCheckAndGetRelativePath = checkAndGetRelativePath(filePath);
        if (strCheckAndGetRelativePath.length() == 0) {
            throw new UnsupportedOperationException("File name is not specified");
        }
        Pair<String, String> pathAndNameFromPath = Utils.INSTANCE.getPathAndNameFromPath(strCheckAndGetRelativePath);
        String strComponent1 = pathAndNameFromPath.component1();
        String strComponent2 = pathAndNameFromPath.component2();
        DocumentFile documentFileFromPath = getDocumentFileFromPath(strComponent1);
        DocumentFile documentFileFindFile = documentFileFromPath.findFile(strComponent2);
        if (documentFileFindFile == null || !documentFileFindFile.exists()) {
            uri = null;
        } else if (documentFileFindFile.isFile()) {
            Log.d(Utils.TAG, "createFile: file already exists");
            uri = documentFileFindFile.getUri();
        } else {
            throw new UnsupportedOperationException("" + strComponent2 + " already exists and not a file (cannot override it)");
        }
        if (uri == null) {
            Log.d(Utils.TAG, "createFile: file not exists, create new");
            DocumentFile documentFileCreateFile = documentFileFromPath.createFile(null, strComponent2);
            Intrinsics.checkExpressionValueIsNotNull(documentFileCreateFile, "documentFile.createFile(null, name)");
            uri = documentFileCreateFile.getUri();
        }
        Log.d(Utils.TAG, "createFile: Write data");
        OutputStream outputStreamOpenOutputStream = this.context.getContentResolver().openOutputStream(uri);
        Intrinsics.checkExpressionValueIsNotNull(outputStreamOpenOutputStream, "context.contentResolver.openOutputStream(uri)");
        return outputStreamOpenOutputStream;
    }

    private final DocumentFile getDocumentFileFromPath(String relativePath) throws FileNotFoundException {
        Log.d(Utils.TAG, "getDocumentFileFromPath(" + relativePath + ')');
        List<String> listSplitPathToParts = Utils.INSTANCE.splitPathToParts(relativePath);
        Log.d(Utils.TAG, "getDocumentFileFromPath: parts=" + listSplitPathToParts);
        Context context = this.context;
        UriPermission uriPermission = this.permission;
        DocumentFile document = DocumentFile.fromTreeUri(context, uriPermission != null ? uriPermission.getUri() : null);
        for (String str : listSplitPathToParts) {
            document = document.findFile(str);
            if (document == null) {
                throw new FileNotFoundException("" + str + " doesn't exists");
            }
        }
        Log.d(Utils.TAG, "getDocumentFileFromPath: success");
        Intrinsics.checkExpressionValueIsNotNull(document, "document");
        return document;
    }
}
