package ru.bartwell.safhelper;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.apache.http.cookie.ClientCookie;

/* compiled from: Utils.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lru/bartwell/safhelper/Utils;", "", "()V", "Companion", "safhelper_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes3.dex */
public final class Utils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String TAG = "SafHelper";

    /* compiled from: Utils.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0007J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0003J\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\f2\u0006\u0010\b\u001a\u00020\u0004J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/bartwell/safhelper/Utils$Companion;", "", "()V", "TAG", "", "findExternalStoragePath", "context", "Landroid/content/Context;", ClientCookie.PATH_ATTR, "getExternalStoragePaths", "", "getPathAndNameFromPath", "Lkotlin/Pair;", "getRelativePath", "storagePath", "normalizePath", "splitPathToParts", "safhelper_release"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String normalizePath(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            String absolutePath = new File(path).getAbsolutePath();
            Intrinsics.checkExpressionValueIsNotNull(absolutePath, "File(path).absolutePath");
            return absolutePath;
        }

        public final Pair<String, String> getPathAndNameFromPath(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            List<String> listSplitPathToParts = splitPathToParts(path);
            if (listSplitPathToParts.size() < 2) {
                return new Pair<>(File.separator, listSplitPathToParts.get(listSplitPathToParts.size() - 1));
            }
            return new Pair<>(File.separator + TextUtils.join(File.separator, CollectionsKt.dropLast(listSplitPathToParts, 1)), listSplitPathToParts.get(listSplitPathToParts.size() - 1));
        }

        public final List<String> splitPathToParts(String path) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            String str = path;
            String str2 = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str2, "File.separator");
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{str2}, false, 0, 6, (Object) null);
            ArrayList arrayList = new ArrayList();
            for (Object obj : listSplit$default) {
                if (!TextUtils.isEmpty((String) obj)) {
                    arrayList.add(obj);
                }
            }
            return arrayList;
        }

        public final String findExternalStoragePath(Context context, String path) {
            Object obj;
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(path, "path");
            Iterator<T> it = getExternalStoragePaths(context).iterator();
            while (true) {
                obj = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (StringsKt.startsWith$default(path, (String) next, false, 2, (Object) null)) {
                    obj = next;
                    break;
                }
            }
            return (String) obj;
        }

        private final List<String> getExternalStoragePaths(Context context) {
            ArrayList arrayList = new ArrayList();
            File[] externalCacheDirs = context.getExternalCacheDirs();
            Intrinsics.checkExpressionValueIsNotNull(externalCacheDirs, "context.externalCacheDirs");
            ArrayList<File> arrayList2 = new ArrayList();
            for (File file : externalCacheDirs) {
                if (Environment.isExternalStorageRemovable(file)) {
                    arrayList2.add(file);
                }
            }
            for (File it : arrayList2) {
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                String path = it.getPath();
                Intrinsics.checkExpressionValueIsNotNull(path, "it.path");
                arrayList.add((String) StringsKt.split$default((CharSequence) path, new String[]{"/Android"}, false, 0, 6, (Object) null).get(0));
            }
            Log.d(Utils.TAG, "getExternalStoragePaths: " + arrayList);
            return arrayList;
        }

        public final String getRelativePath(String storagePath, String path) {
            Intrinsics.checkParameterIsNotNull(storagePath, "storagePath");
            Intrinsics.checkParameterIsNotNull(path, "path");
            if (path.length() <= storagePath.length()) {
                return "";
            }
            String strSubstring = path.substring(storagePath.length());
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
    }
}
