package com.hzy.lib7z;

import android.content.res.AssetManager;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes2.dex */
public class Z7Extractor {
    public static final long DEFAULT_IN_BUF_SIZE = 16777216;
    private static final String lib7z = "un7zip";
    private static boolean mLibLoaded = false;

    public interface LibLoader {
        void loadLibrary(String str);
    }

    public static native int nExtractAsset(AssetManager assetManager, String str, String str2, IExtractCallback iExtractCallback, long j);

    public static native int nExtractFile(String str, String str2, IExtractCallback iExtractCallback, long j);

    public static native String nGetLzmaVersion();

    public static void init() {
        init(null);
    }

    public static void init(LibLoader libLoader) {
        if (mLibLoaded) {
            return;
        }
        if (libLoader != null) {
            libLoader.loadLibrary(lib7z);
        } else {
            System.loadLibrary(lib7z);
        }
        mLibLoaded = true;
    }

    public static String getLzmaVersion() {
        if (!mLibLoaded) {
            init();
        }
        return nGetLzmaVersion();
    }

    public static int extractFile(String str, String str2, IExtractCallback iExtractCallback) {
        if (!mLibLoaded) {
            init();
        }
        File file = new File(str);
        if (!TextUtils.isEmpty(str) && file.exists() && !TextUtils.isEmpty(str2) && prepareOutPath(str2)) {
            return nExtractFile(str, str2, iExtractCallback, DEFAULT_IN_BUF_SIZE);
        }
        if (iExtractCallback != null) {
            iExtractCallback.onError(999, "File Path Error!");
        }
        return 999;
    }

    public static int extractAsset(AssetManager assetManager, String str, String str2, IExtractCallback iExtractCallback) {
        if (!mLibLoaded) {
            init();
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && prepareOutPath(str2)) {
            return nExtractAsset(assetManager, str, str2, iExtractCallback, DEFAULT_IN_BUF_SIZE);
        }
        if (iExtractCallback != null) {
            iExtractCallback.onError(999, "File Path Error!");
        }
        return 999;
    }

    private static boolean prepareOutPath(String str) {
        File file = new File(str);
        if (file.exists() || !file.mkdirs()) {
            return file.exists() && file.isDirectory();
        }
        return true;
    }
}
