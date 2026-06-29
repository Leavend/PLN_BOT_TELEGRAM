package org.osmdroid.tileprovider.modules;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class ZipFileArchive implements IArchiveFile {
    private boolean mIgnoreTileSource = false;
    protected ZipFile mZipFile;

    public ZipFileArchive() {
    }

    private ZipFileArchive(ZipFile zipFile) {
        this.mZipFile = zipFile;
    }

    public static ZipFileArchive getZipFileArchive(File file) throws IOException {
        return new ZipFileArchive(new ZipFile(file));
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void setIgnoreTileSource(boolean z) {
        this.mIgnoreTileSource = z;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void init(File file) throws Exception {
        this.mZipFile = new ZipFile(file);
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public InputStream getInputStream(ITileSource iTileSource, long j) {
        try {
            if (!this.mIgnoreTileSource) {
                ZipEntry entry = this.mZipFile.getEntry(iTileSource.getTileRelativeFilenameString(j));
                if (entry != null) {
                    return this.mZipFile.getInputStream(entry);
                }
                return null;
            }
            Enumeration<? extends ZipEntry> enumerationEntries = this.mZipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (name.contains(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    ZipEntry entry2 = this.mZipFile.getEntry(getTileRelativeFilenameString(j, name.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[0]));
                    if (entry2 != null) {
                        return this.mZipFile.getInputStream(entry2);
                    }
                }
            }
            return null;
        } catch (IOException e) {
            Log.w(IMapView.LOGTAG, "Error getting zip stream: " + MapTileIndex.toString(j), e);
            return null;
        }
    }

    private String getTileRelativeFilenameString(long j, String str) {
        return str + '/' + MapTileIndex.getZoom(j) + '/' + MapTileIndex.getX(j) + '/' + MapTileIndex.getY(j) + ".png";
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public Set<String> getTileSources() {
        HashSet hashSet = new HashSet();
        try {
            Enumeration<? extends ZipEntry> enumerationEntries = this.mZipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (name.contains(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    hashSet.add(name.split(InternalZipConstants.ZIP_FILE_SEPARATOR)[0]);
                }
            }
        } catch (Exception e) {
            Log.w(IMapView.LOGTAG, "Error getting tile sources: ", e);
        }
        return hashSet;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void close() throws IOException {
        try {
            this.mZipFile.close();
        } catch (IOException unused) {
        }
    }

    public String toString() {
        return "ZipFileArchive [mZipFile=" + this.mZipFile.getName() + "]";
    }
}
