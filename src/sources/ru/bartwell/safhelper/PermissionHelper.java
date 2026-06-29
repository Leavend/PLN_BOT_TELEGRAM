package ru.bartwell.safhelper;

import android.content.ContentResolver;
import android.content.Context;
import android.content.UriPermission;
import android.provider.DocumentsContract;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.http.cookie.ClientCookie;

/* compiled from: PermissionHelper.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0003J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00152\u0006\u0010\f\u001a\u00020\rH\u0003J$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0003R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lru/bartwell/safhelper/PermissionHelper;", "", "context", "Landroid/content/Context;", ClientCookie.PATH_ATTR, "", "(Landroid/content/Context;Ljava/lang/String;)V", "basePermittedPath", "getBasePermittedPath", "()Ljava/lang/String;", "setBasePermittedPath", "(Ljava/lang/String;)V", "permission", "Landroid/content/UriPermission;", "getPermission", "()Landroid/content/UriPermission;", "setPermission", "(Landroid/content/UriPermission;)V", "getSortedPermissions", "", "getStorageIdAndPath", "Lkotlin/Pair;", "storagePath", "safhelper_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes3.dex */
public final class PermissionHelper {
    private String basePermittedPath;
    private UriPermission permission;

    public PermissionHelper(Context context, String path) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(path, "path");
        for (UriPermission uriPermission : getSortedPermissions(context)) {
            String strFindExternalStoragePath = Utils.INSTANCE.findExternalStoragePath(context, path);
            if (strFindExternalStoragePath != null) {
                Pair<String, String> storageIdAndPath = getStorageIdAndPath(uriPermission);
                String strComponent1 = storageIdAndPath.component1();
                String strComponent2 = storageIdAndPath.component2();
                Pair<String, String> storageIdAndPath2 = getStorageIdAndPath(strFindExternalStoragePath, path);
                String strComponent12 = storageIdAndPath2.component1();
                String strComponent22 = storageIdAndPath2.component2();
                if (!(strComponent1.length() == 0) && Intrinsics.areEqual(strComponent1, strComponent12) && StringsKt.startsWith$default(strComponent22, strComponent2, false, 2, (Object) null)) {
                    this.permission = uriPermission;
                    this.basePermittedPath = Utils.INSTANCE.normalizePath(strFindExternalStoragePath + File.separator + strComponent2);
                    return;
                }
            }
        }
    }

    public final UriPermission getPermission() {
        return this.permission;
    }

    public final void setPermission(UriPermission uriPermission) {
        this.permission = uriPermission;
    }

    public final String getBasePermittedPath() {
        return this.basePermittedPath;
    }

    public final void setBasePermittedPath(String str) {
        this.basePermittedPath = str;
    }

    private final List<UriPermission> getSortedPermissions(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        Intrinsics.checkExpressionValueIsNotNull(contentResolver, "context.contentResolver");
        List<UriPermission> persistedUriPermissions = contentResolver.getPersistedUriPermissions();
        Intrinsics.checkExpressionValueIsNotNull(persistedUriPermissions, "context.contentResolver.persistedUriPermissions");
        return CollectionsKt.sortedWith(persistedUriPermissions, new Comparator<UriPermission>() { // from class: ru.bartwell.safhelper.PermissionHelper.getSortedPermissions.1
            @Override // java.util.Comparator
            public final int compare(UriPermission o1, UriPermission o2) {
                Intrinsics.checkExpressionValueIsNotNull(o1, "o1");
                int length = o1.getUri().toString().length();
                Intrinsics.checkExpressionValueIsNotNull(o2, "o2");
                return Intrinsics.compare(length, o2.getUri().toString().length());
            }
        });
    }

    private final Pair<String, String> getStorageIdAndPath(String storagePath, String path) {
        List<String> listSplitPathToParts = Utils.INSTANCE.splitPathToParts(storagePath);
        if (listSplitPathToParts.isEmpty()) {
            return new Pair<>("", "");
        }
        return new Pair<>(listSplitPathToParts.get(listSplitPathToParts.size() - 1), Utils.INSTANCE.getRelativePath(storagePath, path));
    }

    private final Pair<String, String> getStorageIdAndPath(UriPermission permission) {
        String treeDocumentId = DocumentsContract.getTreeDocumentId(permission.getUri());
        Intrinsics.checkExpressionValueIsNotNull(treeDocumentId, "DocumentsContract.getTre…ocumentId(permission.uri)");
        List listSplit$default = StringsKt.split$default((CharSequence) treeDocumentId, new String[]{":"}, false, 0, 6, (Object) null);
        return new Pair<>(listSplit$default.get(0), File.separator + ((String) listSplit$default.get(1)));
    }
}
