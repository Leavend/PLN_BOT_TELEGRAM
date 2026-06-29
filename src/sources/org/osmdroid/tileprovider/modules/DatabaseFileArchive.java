package org.osmdroid.tileprovider.modules;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class DatabaseFileArchive implements IArchiveFile {
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_PROVIDER = "provider";
    public static final String TABLE = "tiles";
    private SQLiteDatabase mDatabase;
    private boolean mIgnoreTileSource = false;
    public static final String COLUMN_TILE = "tile";
    static final String[] tile_column = {COLUMN_TILE};

    public DatabaseFileArchive() {
    }

    private DatabaseFileArchive(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    public static DatabaseFileArchive getDatabaseFileArchive(File file) throws SQLiteException {
        return new DatabaseFileArchive(SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 0));
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void setIgnoreTileSource(boolean z) {
        this.mIgnoreTileSource = z;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public Set<String> getTileSources() {
        HashSet hashSet = new HashSet();
        try {
            Cursor cursorRawQuery = this.mDatabase.rawQuery("SELECT distinct provider FROM tiles", null);
            while (cursorRawQuery.moveToNext()) {
                hashSet.add(cursorRawQuery.getString(0));
            }
            cursorRawQuery.close();
        } catch (Exception e) {
            Log.w(IMapView.LOGTAG, "Error getting tile sources: ", e);
        }
        return hashSet;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void init(File file) throws Exception {
        this.mDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 17);
    }

    public byte[] getImage(ITileSource iTileSource, long j) {
        Cursor cursorQuery;
        byte[] blob;
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "Skipping DatabaseFileArchive lookup, database is closed");
            }
            return null;
        }
        try {
            String[] strArr = {COLUMN_TILE};
            long x = MapTileIndex.getX(j);
            long y = MapTileIndex.getY(j);
            long zoom = MapTileIndex.getZoom(j);
            int i = (int) zoom;
            long j2 = (((zoom << i) + x) << i) + y;
            if (!this.mIgnoreTileSource) {
                cursorQuery = this.mDatabase.query("tiles", strArr, "key = " + j2 + " and provider = ?", new String[]{iTileSource.name()}, null, null, null);
            } else {
                cursorQuery = this.mDatabase.query("tiles", strArr, "key = " + j2, null, null, null, null);
            }
            if (cursorQuery.getCount() != 0) {
                cursorQuery.moveToFirst();
                blob = cursorQuery.getBlob(0);
            } else {
                blob = null;
            }
            cursorQuery.close();
        } catch (Throwable th) {
            Log.w(IMapView.LOGTAG, "Error getting db stream: " + MapTileIndex.toString(j), th);
        }
        if (blob != null) {
            return blob;
        }
        return null;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public InputStream getInputStream(ITileSource iTileSource, long j) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byte[] image = getImage(iTileSource, j);
            byteArrayInputStream = image != null ? new ByteArrayInputStream(image) : null;
        } catch (Throwable th) {
            Log.w(IMapView.LOGTAG, "Error getting db stream: " + MapTileIndex.toString(j), th);
        }
        if (byteArrayInputStream != null) {
            return byteArrayInputStream;
        }
        return null;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void close() {
        this.mDatabase.close();
    }

    public String toString() {
        return "DatabaseFileArchive [mDatabase=" + this.mDatabase.getPath() + "]";
    }
}
