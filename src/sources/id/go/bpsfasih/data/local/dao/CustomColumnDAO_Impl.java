package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.CustomColumnEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class CustomColumnDAO_Impl implements CustomColumnDAO {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<CustomColumnEntity> __deletionAdapterOfCustomColumnEntity;
    private final EntityInsertionAdapter<CustomColumnEntity> __insertionAdapterOfCustomColumnEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfUpdateByIdPrimary;
    private final EntityDeletionOrUpdateAdapter<CustomColumnEntity> __updateAdapterOfCustomColumnEntity;

    public CustomColumnDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfCustomColumnEntity = new EntityInsertionAdapter<CustomColumnEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `custom_column_entity` (`idPrimary`,`id`,`userId`,`data1`,`data2`,`data3`,`data4`,`data5`,`data6`,`data7`,`data8`,`data9`,`data10`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CustomColumnEntity customColumnEntity) {
                if (customColumnEntity.getIdPrimary() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, customColumnEntity.getIdPrimary());
                }
                if (customColumnEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, customColumnEntity.getId());
                }
                if (customColumnEntity.getUserId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, customColumnEntity.getUserId());
                }
                supportSQLiteStatement.bindLong(4, customColumnEntity.getData1() ? 1L : 0L);
                supportSQLiteStatement.bindLong(5, customColumnEntity.getData2() ? 1L : 0L);
                supportSQLiteStatement.bindLong(6, customColumnEntity.getData3() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, customColumnEntity.getData4() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, customColumnEntity.getData5() ? 1L : 0L);
                supportSQLiteStatement.bindLong(9, customColumnEntity.getData6() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, customColumnEntity.getData7() ? 1L : 0L);
                supportSQLiteStatement.bindLong(11, customColumnEntity.getData8() ? 1L : 0L);
                supportSQLiteStatement.bindLong(12, customColumnEntity.getData9() ? 1L : 0L);
                supportSQLiteStatement.bindLong(13, customColumnEntity.getData10() ? 1L : 0L);
            }
        };
        this.__deletionAdapterOfCustomColumnEntity = new EntityDeletionOrUpdateAdapter<CustomColumnEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `custom_column_entity` WHERE `idPrimary` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, CustomColumnEntity value) {
                if (value.getIdPrimary() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getIdPrimary());
                }
            }
        };
        this.__updateAdapterOfCustomColumnEntity = new EntityDeletionOrUpdateAdapter<CustomColumnEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `custom_column_entity` SET `idPrimary` = ?,`id` = ?,`userId` = ?,`data1` = ?,`data2` = ?,`data3` = ?,`data4` = ?,`data5` = ?,`data6` = ?,`data7` = ?,`data8` = ?,`data9` = ?,`data10` = ? WHERE `idPrimary` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CustomColumnEntity customColumnEntity) {
                if (customColumnEntity.getIdPrimary() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, customColumnEntity.getIdPrimary());
                }
                if (customColumnEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, customColumnEntity.getId());
                }
                if (customColumnEntity.getUserId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, customColumnEntity.getUserId());
                }
                supportSQLiteStatement.bindLong(4, customColumnEntity.getData1() ? 1L : 0L);
                supportSQLiteStatement.bindLong(5, customColumnEntity.getData2() ? 1L : 0L);
                supportSQLiteStatement.bindLong(6, customColumnEntity.getData3() ? 1L : 0L);
                supportSQLiteStatement.bindLong(7, customColumnEntity.getData4() ? 1L : 0L);
                supportSQLiteStatement.bindLong(8, customColumnEntity.getData5() ? 1L : 0L);
                supportSQLiteStatement.bindLong(9, customColumnEntity.getData6() ? 1L : 0L);
                supportSQLiteStatement.bindLong(10, customColumnEntity.getData7() ? 1L : 0L);
                supportSQLiteStatement.bindLong(11, customColumnEntity.getData8() ? 1L : 0L);
                supportSQLiteStatement.bindLong(12, customColumnEntity.getData9() ? 1L : 0L);
                supportSQLiteStatement.bindLong(13, customColumnEntity.getData10() ? 1L : 0L);
                if (customColumnEntity.getIdPrimary() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, customColumnEntity.getIdPrimary());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM custom_column_entity WHERE userId = ?";
            }
        };
        this.__preparedStmtOfUpdateByIdPrimary = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE custom_column_entity SET data1 =?, data2 =?, data3 =?, data4 =?, data5 =?, data6 =?, data7 =?, data8 =?, data9 =?, data10 =? WHERE idPrimary = ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public void insert(final CustomColumnEntity customColumnEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCustomColumnEntity.insert((EntityInsertionAdapter<CustomColumnEntity>) customColumnEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public void deleteItem(final CustomColumnEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCustomColumnEntity.handle(item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public void update(final CustomColumnEntity customColumnEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfCustomColumnEntity.handle(customColumnEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public void deleteAll(final String userId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        if (userId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, userId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public void updateByIdPrimary(String str, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateByIdPrimary.acquire();
        supportSQLiteStatementAcquire.bindLong(1, z ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(2, z2 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(3, z3 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(4, z4 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(5, z5 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(6, z6 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(7, z7 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(8, z8 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(9, z9 ? 1L : 0L);
        supportSQLiteStatementAcquire.bindLong(10, z10 ? 1L : 0L);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(11);
        } else {
            supportSQLiteStatementAcquire.bindString(11, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateByIdPrimary.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.CustomColumnDAO
    public CustomColumnEntity getByIdPrimary(final String idPrimary) {
        CustomColumnEntity customColumnEntity;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM custom_column_entity WHERE idPrimary = ? LIMIT 1", 1);
        if (idPrimary == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, idPrimary);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "idPrimary");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data1");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data2");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data3");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data4");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data5");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data6");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data7");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data8");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data9");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "data10");
            if (cursorQuery.moveToFirst()) {
                customColumnEntity = new CustomColumnEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.getInt(columnIndexOrThrow4) != 0, cursorQuery.getInt(columnIndexOrThrow5) != 0, cursorQuery.getInt(columnIndexOrThrow6) != 0, cursorQuery.getInt(columnIndexOrThrow7) != 0, cursorQuery.getInt(columnIndexOrThrow8) != 0, cursorQuery.getInt(columnIndexOrThrow9) != 0, cursorQuery.getInt(columnIndexOrThrow10) != 0, cursorQuery.getInt(columnIndexOrThrow11) != 0, cursorQuery.getInt(columnIndexOrThrow12) != 0, cursorQuery.getInt(columnIndexOrThrow13) != 0);
            } else {
                customColumnEntity = null;
            }
            return customColumnEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
