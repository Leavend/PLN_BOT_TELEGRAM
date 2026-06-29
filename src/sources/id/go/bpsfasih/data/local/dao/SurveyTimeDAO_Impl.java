package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import id.go.bpsfasih.data.local.entities.SurveyTimeEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class SurveyTimeDAO_Impl implements SurveyTimeDAO {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<SurveyTimeEntity> __deletionAdapterOfSurveyTimeEntity;
    private final EntityInsertionAdapter<SurveyTimeEntity> __insertionAdapterOfSurveyTimeEntity;
    private final EntityInsertionAdapter<SurveyTimeEntity> __insertionAdapterOfSurveyTimeEntity_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusSurveyTimeByAssignment;
    private final EntityDeletionOrUpdateAdapter<SurveyTimeEntity> __updateAdapterOfSurveyTimeEntity;

    public SurveyTimeDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSurveyTimeEntity = new EntityInsertionAdapter<SurveyTimeEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `data_survey_time` (`survey_time_id`,`user_id`,`survey_time_assignment_id`,`status`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SurveyTimeEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindLong(1, value.getId().intValue());
                }
                if (value.getUser_id() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUser_id());
                }
                if (value.getAssignment_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAssignment_id());
                }
                stmt.bindLong(4, value.getStatus());
            }
        };
        this.__insertionAdapterOfSurveyTimeEntity_1 = new EntityInsertionAdapter<SurveyTimeEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR IGNORE INTO `data_survey_time` (`survey_time_id`,`user_id`,`survey_time_assignment_id`,`status`) VALUES (?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SurveyTimeEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindLong(1, value.getId().intValue());
                }
                if (value.getUser_id() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUser_id());
                }
                if (value.getAssignment_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAssignment_id());
                }
                stmt.bindLong(4, value.getStatus());
            }
        };
        this.__deletionAdapterOfSurveyTimeEntity = new EntityDeletionOrUpdateAdapter<SurveyTimeEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `data_survey_time` WHERE `survey_time_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SurveyTimeEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindLong(1, value.getId().intValue());
                }
            }
        };
        this.__updateAdapterOfSurveyTimeEntity = new EntityDeletionOrUpdateAdapter<SurveyTimeEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.4
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `data_survey_time` SET `survey_time_id` = ?,`user_id` = ?,`survey_time_assignment_id` = ?,`status` = ? WHERE `survey_time_id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, SurveyTimeEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindLong(1, value.getId().intValue());
                }
                if (value.getUser_id() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUser_id());
                }
                if (value.getAssignment_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getAssignment_id());
                }
                stmt.bindLong(4, value.getStatus());
                if (value.getId() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindLong(5, value.getId().intValue());
                }
            }
        };
        this.__preparedStmtOfUpdateStatusSurveyTimeByAssignment = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE data_survey_time SET status = ? WHERE survey_time_assignment_id = ? AND user_id = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM data_survey_time WHERE user_id = ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public void insert(final SurveyTimeEntity Survey) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSurveyTimeEntity.insert((EntityInsertionAdapter<SurveyTimeEntity>) Survey);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public void insertAll(final List<SurveyTimeEntity> listItems) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSurveyTimeEntity_1.insert(listItems);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public void deleteItem(final SurveyTimeEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfSurveyTimeEntity.handle(item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public void updateItem(final SurveyTimeEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfSurveyTimeEntity.handle(item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public void updateStatusSurveyTimeByAssignment(final int status, final String asmId, final String userId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateStatusSurveyTimeByAssignment.acquire();
        supportSQLiteStatementAcquire.bindLong(1, status);
        if (asmId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, asmId);
        }
        if (userId == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, userId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStatusSurveyTimeByAssignment.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
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

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public SurveyTimeEntity getSurveyTime(long j, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM data_survey_time WHERE survey_time_assignment_id = ? AND user_id = ?", 2);
        roomSQLiteQueryAcquire.bindLong(1, j);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SurveyTimeEntity surveyTimeEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_time_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_time_assignment_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            if (cursorQuery.moveToFirst()) {
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow));
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                surveyTimeEntity = new SurveyTimeEntity(numValueOf, string2, string, cursorQuery.getInt(columnIndexOrThrow4));
            }
            return surveyTimeEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.SurveyTimeDAO
    public SurveyTimeEntity getSurveyTimeByAssignment(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM data_survey_time WHERE survey_time_assignment_id = ? AND user_id = ?", 2);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        if (str2 == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        SurveyTimeEntity surveyTimeEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_time_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_time_assignment_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            if (cursorQuery.moveToFirst()) {
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow));
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                surveyTimeEntity = new SurveyTimeEntity(numValueOf, string2, string, cursorQuery.getInt(columnIndexOrThrow4));
            }
            return surveyTimeEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
