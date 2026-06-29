package com.codekidlabs.storagechooser.utils;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes5.dex */
public class FileUtil {
    public static boolean createDirectory(String str, String str2) {
        return new File(str2 + InternalZipConstants.ZIP_FILE_SEPARATOR + str).mkdirs();
    }

    public static boolean isDir(String str) {
        return new File(str).isDirectory();
    }

    public File[] listFilesAsDir(String str) {
        return new File(str).listFiles(new FileFilter() { // from class: com.codekidlabs.storagechooser.utils.FileUtil.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
    }

    public File[] listFilesInDir(String str) {
        return new File(str).listFiles();
    }

    public String[] arrangeAscending(String[] strArr) {
        Arrays.sort(strArr);
        return strArr;
    }

    public void removeNonOperational(List<File> list) {
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i).getName();
            if (name.equals(MemoryUtil.SELF_DIR_NAME)) {
                list.remove(i);
            }
            if (name.equals(MemoryUtil.EMULATED_DIR_NAME)) {
                list.remove(i);
            }
            if (name.equals(MemoryUtil.EMULATED_DIR_KNOX)) {
                list.remove(i);
            }
            if (name.equals(MemoryUtil.SDCARD0_DIR_NAME)) {
                list.remove(i);
            }
        }
    }

    public String[] fileListToStringArray(List<String> list) {
        String[] strArr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strArr[i] = list.get(i);
        }
        return strArr;
    }
}
