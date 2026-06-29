package org.osmdroid.tileprovider.modules;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.Set;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class MBTilesFileArchive implements IArchiveFile {
    public static final String COL_TILES_TILE_COLUMN = "tile_column";
    public static final String COL_TILES_TILE_DATA = "tile_data";
    public static final String COL_TILES_TILE_ROW = "tile_row";
    public static final String COL_TILES_ZOOM_LEVEL = "zoom_level";
    public static final String TABLE_TILES = "tiles";
    private SQLiteDatabase mDatabase;

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void setIgnoreTileSource(boolean z) {
    }

    public MBTilesFileArchive() {
    }

    private MBTilesFileArchive(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    public static MBTilesFileArchive getDatabaseFileArchive(File file) throws SQLiteException {
        return new MBTilesFileArchive(SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 17));
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void init(File file) throws Exception {
        this.mDatabase = SQLiteDatabase.openDatabase(file.getAbsolutePath(), null, 17);
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public InputStream getInputStream(ITileSource iTileSource, long j) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            Cursor cursorQuery = this.mDatabase.query("tiles", new String[]{COL_TILES_TILE_DATA}, "tile_column=? and tile_row=? and zoom_level=?", new String[]{Integer.toString(MapTileIndex.getX(j)), Double.toString((Math.pow(2.0d, MapTileIndex.getZoom(j)) - MapTileIndex.getY(j)) - 1.0d), Integer.toString(MapTileIndex.getZoom(j))}, null, null, null);
            if (cursorQuery.getCount() != 0) {
                cursorQuery.moveToFirst();
                byteArrayInputStream = new ByteArrayInputStream(cursorQuery.getBlob(0));
            } else {
                byteArrayInputStream = null;
            }
            cursorQuery.close();
        } catch (Throwable th) {
            Log.w(IMapView.LOGTAG, "Error getting db stream: " + MapTileIndex.toString(j), th);
        }
        if (byteArrayInputStream != null) {
            return byteArrayInputStream;
        }
        return null;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public Set<String> getTileSources() {
        return Collections.EMPTY_SET;
    }

    @Override // org.osmdroid.tileprovider.modules.IArchiveFile
    public void close() {
        this.mDatabase.close();
    }

    public String toString() {
        return "DatabaseFileArchive [mDatabase=" + this.mDatabase.getPath() + "]";
    }
}
