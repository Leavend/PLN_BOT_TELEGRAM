package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.AssignmentBlokSensus;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class AssignmentBlokSensusDAO_Impl implements AssignmentBlokSensusDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AssignmentBlokSensus> __insertionAdapterOfAssignmentBlokSensus;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfSetTarikSampleOffline;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusListingDone;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusTarikSample;

    public AssignmentBlokSensusDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAssignmentBlokSensus = new EntityInsertionAdapter<AssignmentBlokSensus>(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `assignment_blok_sensus` (`id`,`userId`,`blokSensusId`,`surveyPeriodeId`,`doneListing`,`doneTarikSample`,`doneTarikSampleOffline`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AssignmentBlokSensus assignmentBlokSensus) {
                if (assignmentBlokSensus.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, assignmentBlokSensus.getId());
                }
                if (assignmentBlokSensus.getUserId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, assignmentBlokSensus.getUserId());
                }
                if (assignmentBlokSensus.getBlokSensusId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, assignmentBlokSensus.getBlokSensusId());
                }
                if (assignmentBlokSensus.getSurveyPeriodeId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, assignmentBlokSensus.getSurveyPeriodeId());
                }
                if ((assignmentBlokSensus.getDoneListing() == null ? null : Integer.valueOf(assignmentBlokSensus.getDoneListing().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindLong(5, r0.intValue());
                }
                if ((assignmentBlokSensus.getDoneTarikSample() == null ? null : Integer.valueOf(assignmentBlokSensus.getDoneTarikSample().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindLong(6, r0.intValue());
                }
                if ((assignmentBlokSensus.getDoneTarikSampleOffline() != null ? Integer.valueOf(assignmentBlokSensus.getDoneTarikSampleOffline().booleanValue() ? 1 : 0) : null) == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, r1.intValue());
                }
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM assignment_blok_sensus WHERE userId = ?";
            }
        };
        this.__preparedStmtOfUpdateStatusListingDone = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_blok_sensus SET doneListing = ? WHERE surveyPeriodeId = UPPER(?) AND blokSensusId = UPPER(?)";
            }
        };
        this.__preparedStmtOfUpdateStatusTarikSample = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_blok_sensus SET doneTarikSample = ? WHERE surveyPeriodeId = UPPER(?) AND blokSensusId = UPPER(?)";
            }
        };
        this.__preparedStmtOfSetTarikSampleOffline = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_blok_sensus SET doneTarikSampleOffline = ? WHERE surveyPeriodeId = UPPER(?) AND blokSensusId = UPPER(?)";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
    public void insert(final AssignmentBlokSensus assignmentBlokSensus) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentBlokSensus.insert((EntityInsertionAdapter<AssignmentBlokSensus>) assignmentBlokSensus);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
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

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
    public void updateStatusListingDone(Boolean bool, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateStatusListingDone.acquire();
        if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindLong(1, r5.intValue());
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str2);
        }
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStatusListingDone.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
    public void updateStatusTarikSample(Boolean bool, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateStatusTarikSample.acquire();
        if ((bool == null ? null : Integer.valueOf(bool.booleanValue() ? 1 : 0)) == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindLong(1, r5.intValue());
        }
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str2);
        }
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStatusTarikSample.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
    public void setTarikSampleOffline(boolean z, String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSetTarikSampleOffline.acquire();
        supportSQLiteStatementAcquire.bindLong(1, z ? 1L : 0L);
        if (str2 == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str2);
        }
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetTarikSampleOffline.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO
    public AssignmentBlokSensus getStatusBSBySurveyPeriodeId(String str, String str2) {
        Boolean boolValueOf;
        Boolean boolValueOf2;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM assignment_blok_sensus WHERE blokSensusId = UPPER(?) AND surveyPeriodeId= UPPER(?)", 2);
        boolean z = true;
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
        AssignmentBlokSensus assignmentBlokSensus = null;
        Boolean boolValueOf3 = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "blokSensusId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "surveyPeriodeId");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "doneListing");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "doneTarikSample");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "doneTarikSampleOffline");
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string3 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string4 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow5) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow5));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                Integer numValueOf2 = cursorQuery.isNull(columnIndexOrThrow6) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow6));
                if (numValueOf2 == null) {
                    boolValueOf2 = null;
                } else {
                    boolValueOf2 = Boolean.valueOf(numValueOf2.intValue() != 0);
                }
                Integer numValueOf3 = cursorQuery.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow7));
                if (numValueOf3 != null) {
                    if (numValueOf3.intValue() == 0) {
                        z = false;
                    }
                    boolValueOf3 = Boolean.valueOf(z);
                }
                assignmentBlokSensus = new AssignmentBlokSensus(string, string2, string3, string4, boolValueOf, boolValueOf2, boolValueOf3);
            }
            return assignmentBlokSensus;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
