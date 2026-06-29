package org.osmdroid.tileprovider.modules;

import android.util.Log;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.osmdroid.api.IMapView;

/* loaded from: classes3.dex */
public class ArchiveFileFactory {
    static Map<String, Class<? extends IArchiveFile>> extensionMap;

    static {
        HashMap map = new HashMap();
        extensionMap = map;
        map.put("zip", ZipFileArchive.class);
        extensionMap.put("sqlite", DatabaseFileArchive.class);
        extensionMap.put("mbtiles", MBTilesFileArchive.class);
        extensionMap.put("gemf", GEMFFileArchive.class);
    }

    public static boolean isFileExtensionRegistered(String str) {
        return extensionMap.containsKey(str);
    }

    public static void registerArchiveFileProvider(Class<? extends IArchiveFile> cls, String str) {
        extensionMap.put(str, cls);
    }

    public static IArchiveFile getArchiveFile(File file) throws IllegalAccessException, InstantiationException {
        String name = file.getName();
        if (name.contains(".")) {
            try {
                name = name.substring(name.lastIndexOf(".") + 1);
            } catch (Exception unused) {
            }
        }
        Class<? extends IArchiveFile> cls = extensionMap.get(name.toLowerCase());
        if (cls == null) {
            return null;
        }
        try {
            IArchiveFile iArchiveFileNewInstance = cls.newInstance();
            iArchiveFileNewInstance.init(file);
            return iArchiveFileNewInstance;
        } catch (IllegalAccessException e) {
            Log.e(IMapView.LOGTAG, "Error initializing archive file provider " + file.getAbsolutePath(), e);
            return null;
        } catch (InstantiationException e2) {
            Log.e(IMapView.LOGTAG, "Error initializing archive file provider " + file.getAbsolutePath(), e2);
            return null;
        } catch (Exception e3) {
            Log.e(IMapView.LOGTAG, "Error opening archive file " + file.getAbsolutePath(), e3);
            return null;
        }
    }

    public static Set<String> getRegisteredExtensions() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(extensionMap.keySet());
        return hashSet;
    }
}
