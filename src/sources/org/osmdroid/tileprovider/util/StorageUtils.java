package org.osmdroid.tileprovider.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes3.dex */
public class StorageUtils {
    public static final String EXTERNAL_SD_CARD = "externalSdCard";
    public static final String SD_CARD = "sdCard";
    private static final String TAG = "StorageUtils";

    public static class StorageInfo {
        public String displayName;
        public final int display_number;
        public long freeSpace;
        public final boolean internal;
        public final String path;
        public boolean readonly;

        public StorageInfo(String str, boolean z, boolean z2, int i) {
            this.freeSpace = 0L;
            this.path = str;
            this.internal = z;
            this.display_number = i;
            this.freeSpace = new StatFs(str).getAvailableBytes();
            if (!z2) {
                this.readonly = !StorageUtils.isWritable(new File(str));
            }
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append("Internal SD card");
            } else if (i > 1) {
                sb.append("SD card ").append(i);
            } else {
                sb.append("SD card");
            }
            if (z2) {
                sb.append(" (Read only)");
            }
            this.displayName = sb.toString();
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String str) {
            this.displayName = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            StorageInfo storageInfo = (StorageInfo) obj;
            if (this.internal != storageInfo.internal || this.readonly != storageInfo.readonly || this.display_number != storageInfo.display_number || this.freeSpace != storageInfo.freeSpace) {
                return false;
            }
            String str = this.path;
            if (str == null ? storageInfo.path != null : !str.equals(storageInfo.path)) {
                return false;
            }
            String str2 = this.displayName;
            String str3 = storageInfo.displayName;
            return str2 != null ? str2.equals(str3) : str3 == null;
        }

        public int hashCode() {
            String str = this.path;
            int iHashCode = (((((((str != null ? str.hashCode() : 0) * 31) + (this.internal ? 1 : 0)) * 31) + (this.readonly ? 1 : 0)) * 31) + this.display_number) * 31;
            long j = this.freeSpace;
            int i = (iHashCode + ((int) (j ^ (j >>> 32)))) * 31;
            String str2 = this.displayName;
            return i + (str2 != null ? str2.hashCode() : 0);
        }
    }

    public static List<StorageInfo> getStorageList() {
        return getStorageList(null);
    }

    public static List<StorageInfo> getStorageList(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (context != null) {
                return getStorageListApi19(context);
            }
            return getStorageListPreApi19();
        }
        List<StorageInfo> storageListPreApi19 = getStorageListPreApi19();
        if (context != null) {
            List<StorageInfo> storageListApi19 = getStorageListApi19(context);
            storageListApi19.removeAll(storageListPreApi19);
            storageListPreApi19.addAll(storageListApi19);
        }
        return storageListPreApi19;
    }

    private static List<StorageInfo> getStorageListPreApi19() {
        boolean z;
        ArrayList arrayList = new ArrayList();
        StorageInfo primarySharedStorage = getPrimarySharedStorage();
        if (primarySharedStorage != null) {
            arrayList.add(primarySharedStorage);
        }
        arrayList.addAll(tryToFindOtherVoIdManagedStorages(primarySharedStorage != null ? primarySharedStorage.path : ""));
        for (File file : getAllWritableStorageLocations()) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (((StorageInfo) it.next()).path.equals(file.getAbsolutePath())) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                arrayList.add(new StorageInfo(file.getAbsolutePath(), false, false, -1));
            }
        }
        return arrayList;
    }

    private static List<StorageInfo> getStorageListApi19(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new StorageInfo(context.getFilesDir().getAbsolutePath(), true, false, -1));
        ArrayList arrayList2 = new ArrayList();
        for (File file : context.getExternalFilesDirs(null)) {
            if (file != null && "mounted".equals(Environment.getStorageState(file))) {
                arrayList2.add(file);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(new StorageInfo(((File) it.next()).getAbsolutePath(), false, false, -1));
        }
        return arrayList;
    }

    @Deprecated
    public static File getStorage() {
        return getStorage(null);
    }

    public static StorageInfo getBestWritableStorage() {
        return getBestWritableStorage(null);
    }

    @Deprecated
    public static File getStorage(Context context) {
        StorageInfo bestWritableStorage = getBestWritableStorage(context);
        if (bestWritableStorage != null) {
            return new File(bestWritableStorage.path);
        }
        return null;
    }

    public static StorageInfo getBestWritableStorage(Context context) {
        List<StorageInfo> storageList = getStorageList(context);
        StorageInfo storageInfo = null;
        for (int i = 0; i < storageList.size(); i++) {
            StorageInfo storageInfo2 = storageList.get(i);
            if (!storageInfo2.readonly && isWritable(new File(storageInfo2.path)) && (storageInfo == null || storageInfo.freeSpace < storageInfo2.freeSpace)) {
                storageInfo = storageInfo2;
            }
        }
        return storageInfo;
    }

    @Deprecated
    public static boolean isAvailable() {
        return isPrimarySharedStorageAvailable();
    }

    private static boolean isPrimarySharedStorageAvailable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    @Deprecated
    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR;
    }

    @Deprecated
    public static boolean isWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isWritable(File file) {
        try {
            File file2 = new File(file.getAbsolutePath() + File.separator + UUID.randomUUID().toString());
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write("hi".getBytes());
            fileOutputStream.close();
            file2.delete();
            Log.i(TAG, file.getAbsolutePath() + " is writable");
            return true;
        } catch (Throwable unused) {
            Log.i(TAG, file.getAbsolutePath() + " is NOT writable");
            return false;
        }
    }

    public static Map<String, File> getAllStorageLocations() {
        HashMap map = new HashMap(10);
        map.putAll(tryToGetMountedStoragesFromFilesystem());
        if (!map.containsValue(Environment.getExternalStorageDirectory())) {
            map.put(SD_CARD, Environment.getExternalStorageDirectory());
        }
        for (File file : tryToGetStorageFromSystemEnv()) {
            if (file.exists() && !map.containsValue(file)) {
                map.put(SD_CARD, file);
            }
        }
        return map;
    }

    private static Set<File> getAllWritableStorageLocations() {
        HashSet hashSet = new HashSet();
        for (File file : tryToGetStorageFromSystemEnv()) {
            if (isWritable(file)) {
                hashSet.add(file);
            }
        }
        if (Environment.getExternalStorageDirectory() != null) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (isWritable(externalStorageDirectory)) {
                hashSet.add(externalStorageDirectory);
            }
        }
        for (File file2 : tryToGetMountedStoragesFromFilesystem().values()) {
            if (isWritable(file2)) {
                hashSet.add(file2);
            }
        }
        return hashSet;
    }

    private static StorageInfo getPrimarySharedStorage() {
        boolean z;
        String path = "";
        try {
            if (Environment.getExternalStorageDirectory() != null) {
                path = Environment.getExternalStorageDirectory().getPath();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        boolean zIsPrimarySharedStorageAvailable = false;
        boolean zEquals = true;
        try {
            z = !Environment.isExternalStorageRemovable();
        } catch (Throwable th2) {
            th2.printStackTrace();
            z = false;
        }
        try {
            zIsPrimarySharedStorageAvailable = isPrimarySharedStorageAvailable();
        } catch (Throwable th3) {
            th3.printStackTrace();
        }
        try {
            zEquals = Environment.getExternalStorageState().equals("mounted_ro");
        } catch (Throwable th4) {
            th4.printStackTrace();
        }
        if (zIsPrimarySharedStorageAvailable) {
            return new StorageInfo(path, z, zEquals, -1);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e0 A[Catch: IOException -> 0x00eb, PHI: r3
      0x00e0: PHI (r3v4 java.io.BufferedReader) = (r3v21 java.io.BufferedReader), (r3v22 java.io.BufferedReader) binds: [B:45:0x00de, B:50:0x00e8] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x00eb, blocks: (B:33:0x00cb, B:46:0x00e0), top: B:64:0x000a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<org.osmdroid.tileprovider.util.StorageUtils.StorageInfo> tryToFindOtherVoIdManagedStorages(java.lang.String r10) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 242
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.util.StorageUtils.tryToFindOtherVoIdManagedStorages(java.lang.String):java.util.List");
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x0188: MOVE (r8 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY]), block:B:91:0x0188 */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0058 A[EXC_TOP_SPLITTER, PHI: r10
      0x0058: PHI (r10v3 java.util.Scanner) = (r10v2 java.util.Scanner), (r10v17 java.util.Scanner) binds: [B:25:0x0064, B:17:0x0056] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x00b6 A[EXC_TOP_SPLITTER, PHI: r8
      0x00b6: PHI (r8v5 java.util.Scanner) = (r8v12 java.util.Scanner), (r8v13 java.util.Scanner) binds: [B:54:0x00c1, B:47:0x00b4] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0075 A[Catch: all -> 0x00ba, Exception -> 0x00bd, TRY_LEAVE, TryCatch #2 {Exception -> 0x00bd, blocks: (B:28:0x0068, B:30:0x0075), top: B:101:0x0068 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x018b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.Map<java.lang.String, java.io.File> tryToGetMountedStoragesFromFilesystem() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.util.StorageUtils.tryToGetMountedStoragesFromFilesystem():java.util.Map");
    }

    private static Set<File> tryToGetStorageFromSystemEnv() {
        HashSet hashSet = new HashSet();
        String str = System.getenv("EXTERNAL_STORAGE");
        if (str != null) {
            hashSet.add(new File(str + File.separator));
        }
        String str2 = System.getenv("SECONDARY_STORAGE");
        if (str2 != null) {
            for (String str3 : str2.split(File.pathSeparator)) {
                hashSet.add(new File(str3 + File.separator));
            }
        }
        return hashSet;
    }
}
