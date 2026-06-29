package com.codekidlabs.storagechooser.utils;

import android.os.StatFs;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes5.dex */
public class MemoryUtil {
    public static final String CONTAINER = "container";
    public static final String EMULATED_DIR_KNOX = "knox-emulated";
    public static final String EMULATED_DIR_NAME = "emulated";
    private static final String ERROR = "error";
    public static final String SDCARD0_DIR_NAME = "sdcard0";
    public static final String SELF_DIR_NAME = "self";

    public boolean isExternalStoragePresent() {
        return getStorageListSize() != 1;
    }

    public int getStorageListSize() {
        File file = new File("/storage");
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, file.listFiles());
        for (int i = 0; i < arrayList.size(); i++) {
            String name = ((File) arrayList.get(i)).getName();
            if (name.equals(SELF_DIR_NAME) || name.equals(EMULATED_DIR_KNOX) || name.equals(EMULATED_DIR_KNOX) || name.equals(SDCARD0_DIR_NAME) || name.equals(CONTAINER)) {
                arrayList.remove(i);
            }
        }
        return arrayList.size();
    }

    public long getAvailableMemorySize(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public long getTotalMemorySize(String str) {
        StatFs statFs = new StatFs(new File(str).getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    public String formatSize(long j) {
        String str;
        if (j >= 1024) {
            j /= 1024;
            if (j >= 1024) {
                j /= 1024;
                if (j >= 1024) {
                    j /= 1024;
                    str = DiskUtil.IN_GB;
                } else {
                    str = DiskUtil.IN_MB;
                }
            } else {
                str = DiskUtil.IN_KB;
            }
        } else {
            str = null;
        }
        StringBuilder sb = new StringBuilder(Long.toString(j));
        for (int length = sb.length() - 3; length > 0; length -= 3) {
            sb.insert(length, ',');
        }
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public long suffixedSize(long j, String str) {
        str.hashCode();
        switch (str) {
            case "GiB":
                return (long) (j / Math.pow(1024.0d, 3.0d));
            case "KiB":
                return j / 1024;
            case "MiB":
                return (long) (j / Math.pow(1024.0d, 2.0d));
            default:
                return 0L;
        }
    }
}
