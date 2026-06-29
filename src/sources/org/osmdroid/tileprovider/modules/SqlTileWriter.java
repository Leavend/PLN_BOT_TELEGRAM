package org.osmdroid.tileprovider.modules;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.ExpirableBitmapDrawable;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.Counters;
import org.osmdroid.tileprovider.util.StreamUtils;
import org.osmdroid.util.GarbageCollector;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.SplashScreenable;

/* loaded from: classes3.dex */
public class SqlTileWriter implements IFilesystemCache, SplashScreenable {
    public static final String COLUMN_EXPIRES = "expires";
    public static final String COLUMN_EXPIRES_INDEX = "expires_index";
    public static final String DATABASE_FILENAME = "cache.db";
    private static boolean cleanOnStartup = true;
    protected static File db_file = null;
    static boolean hasInited = false;
    protected static SQLiteDatabase mDb = null;
    private static final String primaryKey = "key=? and provider=?";
    private final GarbageCollector garbageCollector;
    protected long lastSizeCheck = 0;
    private static final Object mLock = new Object();
    private static final String[] queryColumns = {DatabaseFileArchive.COLUMN_TILE, "expires"};
    private static final String[] expireQueryColumn = {"expires"};

    public static long getIndex(long j, long j2, long j3) {
        int i = (int) j3;
        return (((j3 << i) + j) << i) + j2;
    }

    public static String getPrimaryKey() {
        return primaryKey;
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public void onDetach() {
    }

    public static void setCleanupOnStart(boolean z) {
        cleanOnStartup = z;
    }

    public SqlTileWriter() {
        GarbageCollector garbageCollector = new GarbageCollector(new Runnable() { // from class: org.osmdroid.tileprovider.modules.SqlTileWriter.1
            @Override // java.lang.Runnable
            public void run() throws InterruptedException, SQLException {
                SqlTileWriter.this.runCleanupOperation();
            }
        });
        this.garbageCollector = garbageCollector;
        getDb();
        if (hasInited) {
            return;
        }
        hasInited = true;
        if (cleanOnStartup) {
            garbageCollector.gc();
        }
    }

    public void runCleanupOperation() throws InterruptedException, SQLException {
        SQLiteDatabase db = getDb();
        if (db == null || !db.isOpen()) {
            if (Configuration.getInstance().isDebugMode()) {
                Log.d(IMapView.LOGTAG, "Finished init thread, aborted due to null database reference");
            }
        } else {
            createIndex(db);
            long length = db_file.length();
            if (length <= Configuration.getInstance().getTileFileSystemCacheMaxBytes()) {
                return;
            }
            runCleanupOperation(length - Configuration.getInstance().getTileFileSystemCacheTrimBytes(), Configuration.getInstance().getTileGCBulkSize(), Configuration.getInstance().getTileGCBulkPauseInMillis(), true);
        }
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean saveFile(ITileSource iTileSource, long j, InputStream inputStream, Long l) throws Throwable {
        SQLiteDatabase db = getDb();
        if (db == null || !db.isOpen()) {
            Log.d(IMapView.LOGTAG, "Unable to store cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j) + ", database not available.");
            Counters.fileCacheSaveErrors++;
            return false;
        }
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            try {
                try {
                    ContentValues contentValues = new ContentValues();
                    long index = getIndex(j);
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
                        } catch (SQLiteFullException e) {
                            e = e;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            Log.e(IMapView.LOGTAG, "SQLiteFullException while saving tile.", e);
                            this.garbageCollector.gc();
                            catchException(e);
                            byteArrayOutputStream.close();
                            return false;
                        } catch (Exception e2) {
                            e = e2;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            Log.e(IMapView.LOGTAG, "Unable to store cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j) + " db is " + (db == null ? "null" : "not null"), e);
                            Counters.fileCacheSaveErrors++;
                            catchException(e);
                            byteArrayOutputStream.close();
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            byteArrayOutputStream = byteArrayOutputStream2;
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException unused) {
                            }
                            throw th;
                        }
                    }
                    byte[] byteArray = byteArrayOutputStream2.toByteArray();
                    contentValues.put(DatabaseFileArchive.COLUMN_KEY, Long.valueOf(index));
                    contentValues.put(DatabaseFileArchive.COLUMN_TILE, byteArray);
                    if (l != null) {
                        contentValues.put("expires", l);
                    }
                    db.replaceOrThrow("tiles", null, contentValues);
                    if (Configuration.getInstance().isDebugMode()) {
                        Log.d(IMapView.LOGTAG, "tile inserted " + iTileSource.name() + MapTileIndex.toString(j));
                    }
                    if (System.currentTimeMillis() > this.lastSizeCheck + Configuration.getInstance().getTileGCFrequencyInMillis()) {
                        this.lastSizeCheck = System.currentTimeMillis();
                        this.garbageCollector.gc();
                    }
                    byteArrayOutputStream2.close();
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (SQLiteFullException e3) {
                e = e3;
            } catch (Exception e4) {
                e = e4;
            }
        } catch (IOException unused2) {
        }
        return false;
    }

    public boolean exists(String str, long j) {
        return 1 == getRowCount(primaryKey, getPrimaryKeyParameters(getIndex(j), str));
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean exists(ITileSource iTileSource, long j) {
        return exists(iTileSource.name(), j);
    }

    public boolean purgeCache() {
        SQLiteDatabase db = getDb();
        if (db == null || !db.isOpen()) {
            return false;
        }
        try {
            db.delete("tiles", null, null);
            return true;
        } catch (Exception e) {
            Log.w(IMapView.LOGTAG, "Error purging the db", e);
            catchException(e);
            return false;
        }
    }

    public boolean purgeCache(String str) {
        SQLiteDatabase db = getDb();
        if (db == null || !db.isOpen()) {
            return false;
        }
        try {
            db.delete("tiles", "provider = ?", new String[]{str});
            return true;
        } catch (Exception e) {
            Log.w(IMapView.LOGTAG, "Error purging the db", e);
            catchException(e);
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x024f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int[] importFromFileCache(boolean r28) throws java.lang.NumberFormatException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 828
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.modules.SqlTileWriter.importFromFileCache(boolean):int[]");
    }

    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public boolean remove(ITileSource iTileSource, long j) {
        SQLiteDatabase db = getDb();
        if (db == null || !db.isOpen()) {
            Log.d(IMapView.LOGTAG, "Unable to delete cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j) + ", database not available.");
            Counters.fileCacheSaveErrors++;
            return false;
        }
        try {
            db.delete("tiles", primaryKey, getPrimaryKeyParameters(getIndex(j), iTileSource));
            return true;
        } catch (Exception e) {
            Log.e(IMapView.LOGTAG, "Unable to delete cached tile from " + iTileSource.name() + " " + MapTileIndex.toString(j) + " db is " + (db == null ? "null" : "not null"), e);
            Counters.fileCacheSaveErrors++;
            catchException(e);
            return false;
        }
    }

    public long getRowCount(String str) {
        if (str == null) {
            return getRowCount(null, null);
        }
        return getRowCount("provider=?", new String[]{str});
    }

    protected long getRowCount(String str, String[] strArr) {
        SQLiteDatabase db;
        Cursor cursorRawQuery = null;
        try {
            try {
                db = getDb();
            } catch (Exception e) {
                catchException(e);
                if (cursorRawQuery != null) {
                }
            }
            if (db != null && db.isOpen()) {
                cursorRawQuery = db.rawQuery("select count(*) from tiles" + (str == null ? "" : " where " + str), strArr);
                if (!cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                    return -1L;
                }
                long j = cursorRawQuery.getLong(0);
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
                return j;
            }
            return -1L;
        } catch (Throwable th) {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
            throw th;
        }
    }

    public long getRowCount(String str, int i, Collection<Rect> collection, Collection<Rect> collection2) {
        return getRowCount(((Object) getWhereClause(i, collection, collection2)) + (str != null ? " and provider=?" : ""), str != null ? new String[]{str} : null);
    }

    public long getSize() {
        return db_file.length();
    }

    public long getFirstExpiry() {
        SQLiteDatabase db = getDb();
        if (db != null && db.isOpen()) {
            try {
                Cursor cursorRawQuery = db.rawQuery("select min(expires) from tiles", null);
                cursorRawQuery.moveToFirst();
                long j = cursorRawQuery.getLong(0);
                cursorRawQuery.close();
                return j;
            } catch (Exception e) {
                Log.e(IMapView.LOGTAG, "Unable to query for oldest tile", e);
                catchException(e);
            }
        }
        return 0L;
    }

    protected static String extractXFromKeyInSQL(int i) {
        return "((key>>" + i + ")%" + (1 << i) + ")";
    }

    protected static String extractYFromKeyInSQL(int i) {
        return "(key%" + (1 << i) + ")";
    }

    public static long getIndex(long j) {
        return getIndex(MapTileIndex.getX(j), MapTileIndex.getY(j), MapTileIndex.getZoom(j));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0039 A[PHI: r3
      0x0039: PHI (r3v4 android.database.Cursor) = (r3v3 android.database.Cursor), (r3v6 android.database.Cursor) binds: [B:19:0x0037, B:10:0x0024] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Long getExpirationTimestamp(org.osmdroid.tileprovider.tilesource.ITileSource r3, long r4) throws java.lang.Throwable {
        /*
            r2 = this;
            r0 = 0
            long r4 = getIndex(r4)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.lang.String[] r3 = getPrimaryKeyParameters(r4, r3)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.lang.String[] r4 = org.osmdroid.tileprovider.modules.SqlTileWriter.expireQueryColumn     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            android.database.Cursor r3 = r2.getTileCursor(r3, r4)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3d
            if (r4 == 0) goto L24
            r4 = 0
            long r4 = r3.getLong(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3d
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L3d
            if (r3 == 0) goto L23
            r3.close()
        L23:
            return r4
        L24:
            if (r3 == 0) goto L3c
            goto L39
        L27:
            r4 = move-exception
            goto L2d
        L29:
            r4 = move-exception
            goto L3f
        L2b:
            r4 = move-exception
            r3 = r0
        L2d:
            java.lang.String r5 = "OsmDroid"
            java.lang.String r1 = "error getting expiration date from the tile cache"
            android.util.Log.e(r5, r1, r4)     // Catch: java.lang.Throwable -> L3d
            r2.catchException(r4)     // Catch: java.lang.Throwable -> L3d
            if (r3 == 0) goto L3c
        L39:
            r3.close()
        L3c:
            return r0
        L3d:
            r4 = move-exception
            r0 = r3
        L3f:
            if (r0 == 0) goto L44
            r0.close()
        L44:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.modules.SqlTileWriter.getExpirationTimestamp(org.osmdroid.tileprovider.tilesource.ITileSource, long):java.lang.Long");
    }

    public static String[] getPrimaryKeyParameters(long j, ITileSource iTileSource) {
        return getPrimaryKeyParameters(j, iTileSource.name());
    }

    public static String[] getPrimaryKeyParameters(long j, String str) {
        return new String[]{String.valueOf(j), str};
    }

    public Cursor getTileCursor(String[] strArr, String[] strArr2) {
        return getDb().query("tiles", strArr2, primaryKey, strArr, null, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [org.osmdroid.tileprovider.tilesource.ITileSource] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.ByteArrayInputStream, java.io.Closeable, java.io.InputStream] */
    @Override // org.osmdroid.tileprovider.modules.IFilesystemCache
    public Drawable loadTile(ITileSource iTileSource, long j) throws Exception {
        Cursor tileCursor;
        long j2;
        byte[] blob;
        Cursor cursor = null;
        try {
            try {
                tileCursor = getTileCursor(getPrimaryKeyParameters(getIndex(j), (ITileSource) iTileSource), queryColumns);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            boolean z = true;
            if (tileCursor.moveToFirst()) {
                blob = tileCursor.getBlob(0);
                j2 = tileCursor.getLong(1);
            } else {
                j2 = 0;
                blob = null;
            }
            if (blob == null) {
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "SqlCache - Tile doesn't exist: " + iTileSource.name() + MapTileIndex.toString(j));
                }
                if (tileCursor != null) {
                    tileCursor.close();
                }
                return null;
            }
            if (tileCursor != null) {
                tileCursor.close();
            }
            try {
                ?? byteArrayInputStream = new ByteArrayInputStream(blob);
                try {
                    Drawable drawable = iTileSource.getDrawable(byteArrayInputStream);
                    if (j2 >= System.currentTimeMillis()) {
                        z = false;
                    }
                    if (z && drawable != null) {
                        if (Configuration.getInstance().isDebugMode()) {
                            Log.d(IMapView.LOGTAG, "Tile expired: " + iTileSource.name() + MapTileIndex.toString(j));
                        }
                        ExpirableBitmapDrawable.setState(drawable, -2);
                    }
                    StreamUtils.closeStream(byteArrayInputStream);
                    return drawable;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = byteArrayInputStream;
                    if (cursor != null) {
                        StreamUtils.closeStream(cursor);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Exception e2) {
            e = e2;
            catchException(e);
            throw e;
        } catch (Throwable th4) {
            th = th4;
            cursor = tileCursor;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void runCleanupOperation(long j, int i, long j2, boolean z) throws InterruptedException {
        boolean z2;
        String str;
        StringBuilder sb = new StringBuilder();
        SQLiteDatabase db = getDb();
        long j3 = j;
        boolean z3 = true;
        while (j3 > 0) {
            if (z3) {
                z2 = false;
            } else {
                if (j2 > 0) {
                    try {
                        Thread.sleep(j2);
                    } catch (InterruptedException unused) {
                    }
                }
                z2 = z3;
            }
            try {
                String str2 = "";
                Cursor cursorRawQuery = db.rawQuery("SELECT key,LENGTH(HEX(tile))/2 FROM tiles WHERE expires IS NOT NULL " + (z ? "" : "AND expires < " + System.currentTimeMillis() + " ") + "ORDER BY expires ASC LIMIT " + i, null);
                cursorRawQuery.moveToFirst();
                sb.setLength(0);
                sb.append("key in (");
                String str3 = "";
                while (true) {
                    str = str2;
                    if (cursorRawQuery.isAfterLast()) {
                        break;
                    }
                    long j4 = cursorRawQuery.getLong(0);
                    long j5 = cursorRawQuery.getLong(1);
                    cursorRawQuery.moveToNext();
                    sb.append(str3).append(j4);
                    j3 -= j5;
                    str3 = ",";
                    if (j3 <= 0) {
                        break;
                    } else {
                        str2 = str;
                    }
                }
                cursorRawQuery.close();
                if (str.equals(str3)) {
                    return;
                }
                sb.append(')');
                try {
                    db.delete("tiles", sb.toString(), null);
                } catch (SQLiteFullException e) {
                    Log.e(IMapView.LOGTAG, "SQLiteFullException while cleanup.", e);
                    catchException(e);
                } catch (Exception e2) {
                    catchException(e2);
                    return;
                }
                z3 = z2;
            } catch (Exception e3) {
                catchException(e3);
                return;
            }
        }
    }

    protected SQLiteDatabase getDb() {
        SQLiteDatabase sQLiteDatabase = mDb;
        if (sQLiteDatabase != null) {
            return sQLiteDatabase;
        }
        synchronized (mLock) {
            Configuration.getInstance().getOsmdroidTileCache().mkdirs();
            File file = new File(Configuration.getInstance().getOsmdroidTileCache().getAbsolutePath() + File.separator + DATABASE_FILENAME);
            db_file = file;
            if (mDb == null) {
                try {
                    SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                    mDb = sQLiteDatabaseOpenOrCreateDatabase;
                    sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS tiles (key INTEGER , provider TEXT, tile BLOB, expires INTEGER, PRIMARY KEY (key, provider));");
                } catch (Exception e) {
                    Log.e(IMapView.LOGTAG, "Unable to start the sqlite tile writer. Check external storage availability.", e);
                    catchException(e);
                    return null;
                }
            }
        }
        return mDb;
    }

    public void refreshDb() {
        synchronized (mLock) {
            SQLiteDatabase sQLiteDatabase = mDb;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
                mDb = null;
            }
        }
    }

    protected void catchException(Exception exc) {
        if (!(exc instanceof SQLiteException) || isFunctionalException((SQLiteException) exc)) {
            return;
        }
        refreshDb();
    }

    public static boolean isFunctionalException(SQLiteException sQLiteException) {
        String simpleName = sQLiteException.getClass().getSimpleName();
        simpleName.hashCode();
        switch (simpleName) {
            case "SQLiteFullException":
            case "SQLiteBindOrColumnIndexOutOfRangeException":
            case "SQLiteTableLockedException":
            case "SQLiteMisuseException":
            case "SQLiteBlobTooBigException":
            case "SQLiteConstraintException":
            case "SQLiteDatatypeMismatchException":
                return true;
            default:
                return false;
        }
    }

    private void createIndex(SQLiteDatabase sQLiteDatabase) throws SQLException {
        sQLiteDatabase.execSQL("CREATE INDEX IF NOT EXISTS expires_index ON tiles (expires);");
    }

    @Override // org.osmdroid.util.SplashScreenable
    public void runDuringSplashScreen() throws SQLException {
        createIndex(getDb());
    }

    protected StringBuilder getWhereClause(int i, Rect rect) {
        long j = (1 << (i + 1)) - 1;
        long j2 = i;
        long index = getIndex(0L, 0L, j2);
        long index2 = getIndex(j, j, j2);
        String strExtractXFromKeyInSQL = extractXFromKeyInSQL(i);
        String strExtractYFromKeyInSQL = extractYFromKeyInSQL(i);
        StringBuilder sb = new StringBuilder();
        sb.append("(key between ");
        sb.append(index).append(" and ").append(index2);
        if (rect != null) {
            sb.append(" and ");
            if (rect.left == rect.right) {
                sb.append(strExtractXFromKeyInSQL).append("=").append(rect.left);
            } else {
                sb.append("(").append(strExtractXFromKeyInSQL).append(">=").append(rect.left).append(rect.left < rect.right ? " and " : " or ").append(strExtractXFromKeyInSQL).append("<=").append(rect.right).append(")");
            }
            sb.append(" and ");
            if (rect.top == rect.bottom) {
                sb.append(strExtractYFromKeyInSQL).append("=").append(rect.top);
            } else {
                sb.append("(").append(strExtractYFromKeyInSQL).append(">=").append(rect.top).append(rect.top >= rect.bottom ? " or " : " and ").append(strExtractYFromKeyInSQL).append("<=").append(rect.bottom).append(")");
            }
        }
        sb.append(')');
        return sb;
    }

    protected StringBuilder getWhereClause(int i, Collection<Rect> collection, Collection<Rect> collection2) {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        sb.append((CharSequence) getWhereClause(i, null));
        String str = "";
        if (collection != null && collection.size() > 0) {
            sb.append(" and (");
            Iterator<Rect> it = collection.iterator();
            String str2 = "";
            while (it.hasNext()) {
                sb.append(str2).append('(').append((CharSequence) getWhereClause(i, it.next())).append(')');
                str2 = " or ";
            }
            sb.append(")");
        }
        if (collection2 != null && collection2.size() > 0) {
            sb.append(" and not(");
            Iterator<Rect> it2 = collection2.iterator();
            while (it2.hasNext()) {
                sb.append(str).append('(').append((CharSequence) getWhereClause(i, it2.next())).append(')');
                str = " or ";
            }
            sb.append(")");
        }
        sb.append(')');
        return sb;
    }

    public long delete(String str, int i, Collection<Rect> collection, Collection<Rect> collection2) {
        try {
            SQLiteDatabase db = getDb();
            if (db != null && db.isOpen()) {
                return db.delete("tiles", ((Object) getWhereClause(i, collection, collection2)) + (str != null ? " and provider=?" : ""), str != null ? new String[]{str} : null);
            }
            return -1L;
        } catch (Exception e) {
            catchException(e);
            return 0L;
        }
    }
}
