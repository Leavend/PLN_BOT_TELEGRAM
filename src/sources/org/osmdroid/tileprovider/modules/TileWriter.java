package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.ExpirableBitmapDrawable;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.Counters;
import org.osmdroid.tileprovider.util.StreamUtils;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class TileWriter implements IFilesystemCache {
    static boolean hasInited = false;
    private static long mUsedCacheSpace;
    Thread initThread;
    private long mMaximumCachedFileAge;

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public Long getExpirationTimestamp(ITileSource iTileSource, long j) {
        return null;
    }

    public TileWriter() {
        this.initThread = null;
        if (hasInited) {
            return;
        }
        hasInited = true;
        Thread thread = new Thread() { // from class: org.osmdroid.tileprovider.modules.TileWriter.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                long unused = TileWriter.mUsedCacheSpace = 0L;
                TileWriter.this.calculateDirectorySize(Configuration.getInstance().getOsmdroidTileCache());
                if (TileWriter.mUsedCacheSpace > Configuration.getInstance().getTileFileSystemCacheMaxBytes()) {
                    TileWriter.this.cutCurrentCache();
                }
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "Finished init thread");
                }
            }
        };
        this.initThread = thread;
        thread.setName("TileWriter#init");
        this.initThread.setPriority(1);
        this.initThread.start();
    }

    public static long getUsedCacheSpace() {
        return mUsedCacheSpace;
    }

    public void setMaximumCachedFileAge(long j) {
        this.mMaximumCachedFileAge = j;
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean saveFile(ITileSource iTileSource, long j, InputStream inputStream, Long l) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        File file = getFile(iTileSource, j);
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.d(IMapView.LOGTAG, "TileWrite " + file.getAbsolutePath());
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !createFolderAndCheckIfExists(parentFile)) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file.getPath()), 8192);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException unused) {
        }
        try {
            long jCopy = mUsedCacheSpace + StreamUtils.copy(inputStream, bufferedOutputStream);
            mUsedCacheSpace = jCopy;
            if (jCopy > Configuration.getInstance().getTileFileSystemCacheMaxBytes()) {
                cutCurrentCache();
            }
            StreamUtils.closeStream(bufferedOutputStream);
            return true;
        } catch (IOException unused2) {
            bufferedOutputStream2 = bufferedOutputStream;
            Counters.fileCacheSaveErrors++;
            if (bufferedOutputStream2 != null) {
                StreamUtils.closeStream(bufferedOutputStream2);
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                StreamUtils.closeStream(bufferedOutputStream2);
            }
            throw th;
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public void onDetach() {
        Thread thread = this.initThread;
        if (thread != null) {
            try {
                thread.interrupt();
            } catch (Throwable unused) {
            }
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean remove(ITileSource iTileSource, long j) {
        File file = getFile(iTileSource, j);
        if (!file.exists()) {
            return false;
        }
        try {
            return file.delete();
        } catch (Exception e) {
            Log.i(IMapView.LOGTAG, "Unable to delete cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j), e);
            return false;
        }
    }

    public File getFile(ITileSource iTileSource, long j) {
        return new File(Configuration.getInstance().getOsmdroidTileCache(), iTileSource.getTileRelativeFilenameString(j) + OpenStreetMapTileProviderConstants.TILE_PATH_EXTENSION);
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean exists(ITileSource iTileSource, long j) {
        return getFile(iTileSource, j).exists();
    }

    private boolean createFolderAndCheckIfExists(File file) throws InterruptedException {
        if (file.mkdirs()) {
            return true;
        }
        if (Configuration.getInstance().isDebugMode()) {
            Log.d(IMapView.LOGTAG, "Failed to create " + file + " - wait and check again");
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
        }
        if (file.exists()) {
            if (Configuration.getInstance().isDebugMode()) {
                Log.d(IMapView.LOGTAG, "Seems like another thread created " + file);
            }
            return true;
        }
        if (!Configuration.getInstance().isDebugMode()) {
            return false;
        }
        Log.d(IMapView.LOGTAG, "File still doesn't exist: " + file);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateDirectorySize(File file) {
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    mUsedCacheSpace += file2.length();
                }
                if (file2.isDirectory() && !isSymbolicDirectoryLink(file, file2)) {
                    calculateDirectorySize(file2);
                }
            }
        }
    }

    private boolean isSymbolicDirectoryLink(File file, File file2) {
        try {
            return !file.getCanonicalPath().equals(file2.getCanonicalFile().getParent());
        } catch (IOException | NoSuchElementException unused) {
            return true;
        }
    }

    private List<File> getDirectoryFileList(File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile()) {
                    arrayList.add(file2);
                }
                if (file2.isDirectory()) {
                    arrayList.addAll(getDirectoryFileList(file2));
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cutCurrentCache() {
        synchronized (Configuration.getInstance().getOsmdroidTileCache()) {
            if (mUsedCacheSpace > Configuration.getInstance().getTileFileSystemCacheTrimBytes()) {
                Log.d(IMapView.LOGTAG, "Trimming tile cache from " + mUsedCacheSpace + " to " + Configuration.getInstance().getTileFileSystemCacheTrimBytes());
                File[] fileArr = (File[]) getDirectoryFileList(Configuration.getInstance().getOsmdroidTileCache()).toArray(new File[0]);
                Arrays.sort(fileArr, new Comparator<File>() { // from class: org.osmdroid.tileprovider.modules.TileWriter.2
                    @Override // java.util.Comparator
                    public int compare(File file, File file2) {
                        return Long.valueOf(file.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
                    }
                });
                for (File file : fileArr) {
                    if (mUsedCacheSpace <= Configuration.getInstance().getTileFileSystemCacheTrimBytes()) {
                        break;
                    }
                    long length = file.length();
                    if (file.delete()) {
                        if (Configuration.getInstance().isDebugTileProviders()) {
                            Log.d(IMapView.LOGTAG, "Cache trim deleting " + file.getAbsolutePath());
                        }
                        mUsedCacheSpace -= length;
                    }
                }
                Log.d(IMapView.LOGTAG, "Finished trimming tile cache");
            }
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public Drawable loadTile(ITileSource iTileSource, long j) throws Exception {
        File file = getFile(iTileSource, j);
        if (!file.exists()) {
            return null;
        }
        Drawable drawable = iTileSource.getDrawable(file.getPath());
        if ((file.lastModified() < System.currentTimeMillis() - this.mMaximumCachedFileAge) && drawable != null) {
            if (Configuration.getInstance().isDebugMode()) {
                Log.d(IMapView.LOGTAG, "Tile expired: " + MapTileIndex.toString(j));
            }
            ExpirableBitmapDrawable.setState(drawable, -2);
        }
        return drawable;
    }
}
