package org.osmdroid.tileprovider.modules;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.StreamUtils;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class SqliteArchiveTileWriter implements IFilesystemCache {
    static boolean hasInited = false;
    private static final String[] queryColumns = {DatabaseFileArchive.COLUMN_TILE};
    final File db_file;
    final SQLiteDatabase mDatabase;
    final int questimate = 8000;

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public Long getExpirationTimestamp(ITileSource iTileSource, long j) {
        return null;
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean remove(ITileSource iTileSource, long j) {
        return false;
    }

    public SqliteArchiveTileWriter(String str) throws Exception {
        File file = new File(str);
        this.db_file = file;
        try {
            SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file.getAbsolutePath(), (SQLiteDatabase.CursorFactory) null);
            this.mDatabase = sQLiteDatabaseOpenOrCreateDatabase;
            try {
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS tiles (key INTEGER , provider TEXT, tile BLOB, PRIMARY KEY (key, provider));");
            } catch (Throwable th) {
                th.printStackTrace();
                Log.d(IMapView.LOGTAG, "error setting db schema, it probably exists already", th);
            }
        } catch (Exception e) {
            throw new Exception("Trouble creating database file at " + str, e);
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean saveFile(ITileSource iTileSource, long j, InputStream inputStream, Long l) throws IOException {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        boolean z = false;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            Log.d(IMapView.LOGTAG, "Skipping SqlArchiveTileWriter saveFile, database is closed");
            return false;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                ContentValues contentValues = new ContentValues();
                long index = SqlTileWriter.getIndex(j);
                contentValues.put(DatabaseFileArchive.COLUMN_PROVIDER, iTileSource.name());
                byte[] bArr = new byte[512];
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                while (true) {
                    try {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream2.write(bArr, 0, i);
                    } catch (Throwable th) {
                        th = th;
                        byteArrayOutputStream = byteArrayOutputStream2;
                        try {
                            Log.e(IMapView.LOGTAG, "Unable to store cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j), th);
                            byteArrayOutputStream.close();
                            return z;
                        } catch (Throwable th2) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException unused) {
                            }
                            throw th2;
                        }
                    }
                }
                byte[] byteArray = byteArrayOutputStream2.toByteArray();
                contentValues.put(DatabaseFileArchive.COLUMN_KEY, Long.valueOf(index));
                contentValues.put(DatabaseFileArchive.COLUMN_TILE, byteArray);
                this.mDatabase.insert("tiles", null, contentValues);
                z = true;
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "tile inserted " + iTileSource.name() + MapTileIndex.toString(j));
                }
                byteArrayOutputStream2.close();
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (IOException unused2) {
        }
        return z;
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean exists(ITileSource iTileSource, long j) {
        try {
            Cursor tileCursor = getTileCursor(SqlTileWriter.getPrimaryKeyParameters(SqlTileWriter.getIndex(j), iTileSource));
            boolean z = tileCursor.getCount() != 0;
            tileCursor.close();
            return z;
        } catch (Throwable th) {
            Log.e(IMapView.LOGTAG, "Unable to store cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j), th);
            return false;
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public void onDetach() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public Cursor getTileCursor(String[] strArr) {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            Log.w(IMapView.LOGTAG, "Skipping SqlArchiveTileWriter getTileCursor, database is closed");
            return null;
        }
        return this.mDatabase.query("tiles", queryColumns, SqlTileWriter.getPrimaryKey(), strArr, null, null, null);
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public Drawable loadTile(ITileSource iTileSource, long j) throws Exception {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        ByteArrayInputStream byteArrayInputStream = null;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            Log.w(IMapView.LOGTAG, "Skipping SqlArchiveTileWriter loadTile, database is closed");
            return null;
        }
        try {
            Cursor tileCursor = getTileCursor(SqlTileWriter.getPrimaryKeyParameters(SqlTileWriter.getIndex(j), iTileSource));
            if (tileCursor == null) {
                return null;
            }
            byte[] blob = tileCursor.moveToFirst() ? tileCursor.getBlob(tileCursor.getColumnIndex(DatabaseFileArchive.COLUMN_TILE)) : null;
            tileCursor.close();
            if (blob == null) {
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "SqlCache - Tile doesn't exist: " + iTileSource.name() + MapTileIndex.toString(j));
                }
                return null;
            }
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(blob);
            try {
                Drawable drawable = iTileSource.getDrawable(byteArrayInputStream2);
                StreamUtils.closeStream(byteArrayInputStream2);
                return drawable;
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = byteArrayInputStream2;
                if (byteArrayInputStream != null) {
                    StreamUtils.closeStream(byteArrayInputStream);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
