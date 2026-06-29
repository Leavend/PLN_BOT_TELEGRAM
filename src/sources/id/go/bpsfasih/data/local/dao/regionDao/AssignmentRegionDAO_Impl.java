package id.go.bpsfasih.data.local.dao.regionDao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import id.go.bpsfasih.data.local.converter.TyperConverterAll;
import id.go.bpsfasih.data.local.entities.AssignmentRegionEntity;
import id.go.bpsfasih.data.local.entities.RegionMetadata;
import id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class AssignmentRegionDAO_Impl implements AssignmentRegionDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AssignmentRegionEntity> __insertionAdapterOfAssignmentRegionEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByPeriode;
    private final SharedSQLiteStatement __preparedStmtOfUpdateRegionMetadataByPeriode;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStateDataTable;
    private final SharedSQLiteStatement __preparedStmtOfUpdateStatusListingDone;
    private final TyperConverterAll __typerConverterAll = new TyperConverterAll();

    public AssignmentRegionDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAssignmentRegionEntity = new EntityInsertionAdapter<AssignmentRegionEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `assignment_region` (`_id`,`userId`,`region_id`,`region_group_id`,`smallest_region_full_code`,`survey_period_id`,`done_listing`,`done_tarik_sample`,`done_tarik_sample_offline`,`state_data_table`,`region`,`regionMetadata`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AssignmentRegionEntity assignmentRegionEntity) {
                if (assignmentRegionEntity.get_id() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, assignmentRegionEntity.get_id());
                }
                if (assignmentRegionEntity.getUserId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, assignmentRegionEntity.getUserId());
                }
                if (assignmentRegionEntity.getRegion_id() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, assignmentRegionEntity.getRegion_id());
                }
                if (assignmentRegionEntity.getRegion_group_id() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, assignmentRegionEntity.getRegion_group_id());
                }
                if (assignmentRegionEntity.getSmallest_region_full_code() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, assignmentRegionEntity.getSmallest_region_full_code());
                }
                if (assignmentRegionEntity.getSurvey_period_id() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, assignmentRegionEntity.getSurvey_period_id());
                }
                if ((assignmentRegionEntity.getDone_listing() == null ? null : Integer.valueOf(assignmentRegionEntity.getDone_listing().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, r0.intValue());
                }
                if ((assignmentRegionEntity.getDone_tarik_sample() == null ? null : Integer.valueOf(assignmentRegionEntity.getDone_tarik_sample().booleanValue() ? 1 : 0)) == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindLong(8, r0.intValue());
                }
                if ((assignmentRegionEntity.getDone_tarik_sample_offline() != null ? Integer.valueOf(assignmentRegionEntity.getDone_tarik_sample_offline().booleanValue() ? 1 : 0) : null) == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, r1.intValue());
                }
                if (assignmentRegionEntity.getStateDataTable() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, assignmentRegionEntity.getStateDataTable());
                }
                String strRegionToJson = AssignmentRegionDAO_Impl.this.__typerConverterAll.regionToJson(assignmentRegionEntity.getRegion());
                if (strRegionToJson == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, strRegionToJson);
                }
                String strRegionMetadataToJson = AssignmentRegionDAO_Impl.this.__typerConverterAll.regionMetadataToJson(assignmentRegionEntity.getRegionMetadata());
                if (strRegionMetadataToJson == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, strRegionMetadataToJson);
                }
            }
        };
        this.__preparedStmtOfUpdateRegionMetadataByPeriode = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_region SET regionMetadata = ? WHERE survey_period_id = ? AND userId = ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM assignment_region WHERE userId = ?";
            }
        };
        this.__preparedStmtOfDeleteByPeriode = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM assignment_region WHERE userId = ? AND survey_period_id = ?";
            }
        };
        this.__preparedStmtOfUpdateStatusListingDone = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_region SET done_listing = ? WHERE survey_period_id = ? AND region_id = ? AND userId= ?";
            }
        };
        this.__preparedStmtOfUpdateStateDataTable = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_region SET state_data_table = ? WHERE survey_period_id = ? AND region_id = ? AND userId= ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void insert(final AssignmentRegionEntity assignmentRegionEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentRegionEntity.insert((EntityInsertionAdapter<AssignmentRegionEntity>) assignmentRegionEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void insertAll(final List<AssignmentRegionEntity> assignmentRegionEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentRegionEntity.insert(assignmentRegionEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void updateRegionMetadataByPeriode(final String surveyPeriodeId, final String userId, final RegionMetadata regionMetadata) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateRegionMetadataByPeriode.acquire();
        String strRegionMetadataToJson = this.__typerConverterAll.regionMetadataToJson(regionMetadata);
        if (strRegionMetadataToJson == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, strRegionMetadataToJson);
        }
        if (surveyPeriodeId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, surveyPeriodeId);
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
            this.__preparedStmtOfUpdateRegionMetadataByPeriode.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
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

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void deleteByPeriode(final String userId, final String periodeId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByPeriode.acquire();
        if (userId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, userId);
        }
        if (periodeId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, periodeId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByPeriode.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void updateStatusListingDone(Boolean bool, String str, String str2, String str3) {
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
        if (str3 == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, str3);
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

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public void updateStateDataTable(final String regionId, final String surveyPeriodeId, final String userId, final String value) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateStateDataTable.acquire();
        if (value == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, value);
        }
        if (surveyPeriodeId == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, surveyPeriodeId);
        }
        if (regionId == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, regionId);
        }
        if (userId == null) {
            supportSQLiteStatementAcquire.bindNull(4);
        } else {
            supportSQLiteStatementAcquire.bindString(4, userId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateStateDataTable.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public AssignmentRegionEntity getStatusRegionBySurveyPeriodeId(final String regionId, final String surveyPeriodeId, final String userId) {
        AssignmentRegionEntity assignmentRegionEntity;
        Boolean boolValueOf;
        Boolean boolValueOf2;
        Boolean boolValueOf3;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM assignment_region WHERE region_id = ? AND survey_period_id= ? AND userId= ?", 3);
        boolean z = true;
        if (regionId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, regionId);
        }
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "region_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "region_group_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "smallest_region_full_code");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_period_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "done_listing");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "done_tarik_sample");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "done_tarik_sample_offline");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state_data_table");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "region");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "regionMetadata");
            if (cursorQuery.moveToFirst()) {
                String string = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string2 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                String string3 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                String string4 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                String string5 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                String string6 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                Integer numValueOf = cursorQuery.isNull(columnIndexOrThrow7) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow7));
                if (numValueOf == null) {
                    boolValueOf = null;
                } else {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                Integer numValueOf2 = cursorQuery.isNull(columnIndexOrThrow8) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow8));
                if (numValueOf2 == null) {
                    boolValueOf2 = null;
                } else {
                    boolValueOf2 = Boolean.valueOf(numValueOf2.intValue() != 0);
                }
                Integer numValueOf3 = cursorQuery.isNull(columnIndexOrThrow9) ? null : Integer.valueOf(cursorQuery.getInt(columnIndexOrThrow9));
                if (numValueOf3 == null) {
                    boolValueOf3 = null;
                } else {
                    if (numValueOf3.intValue() == 0) {
                        z = false;
                    }
                    boolValueOf3 = Boolean.valueOf(z);
                }
                assignmentRegionEntity = new AssignmentRegionEntity(string, string2, string3, string4, string5, string6, boolValueOf, boolValueOf2, boolValueOf3, cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), this.__typerConverterAll.jsonToRegion(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11)), this.__typerConverterAll.jsonToRegionMetadata(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12)));
            } else {
                assignmentRegionEntity = null;
            }
            return assignmentRegionEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public String getSmallRegionFullCodeBySurveyPeriodeId(final String regionId, final String surveyPeriodeId, final String userId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT smallest_region_full_code FROM assignment_region WHERE region_id = ? AND survey_period_id= ? AND userId= ?", 3);
        if (regionId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, regionId);
        }
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(3);
        } else {
            roomSQLiteQueryAcquire.bindString(3, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst() && !cursorQuery.isNull(0)) {
                string = cursorQuery.getString(0);
            }
            return string;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public LiveData<List<AssignmentRegionWilayahPojo>> getAssignmentRegionWilayahByPeriode(final String surveyPeriodeId, final String userId) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT ar.*, COALESCE(SUM(CASE WHEN a.assignmentStatusId = 0 AND IFNULL(a.pendingStatus, 0) = 0 THEN 1 ELSE 0 END), 0) AS openCount, COALESCE(SUM(CASE WHEN a.assignmentStatusId = 3 AND IFNULL(a.pendingStatus, 0) = 0 THEN 1 ELSE 0 END), 0) AS rejectCount, COALESCE(SUM(CASE WHEN a.assignmentStatusId = 1 AND IFNULL(a.pendingStatus, 0) = 0 THEN 1 ELSE 0 END), 0) AS submitCount, COALESCE(SUM(CASE WHEN IFNULL(a.pendingStatus, 0) = 1 THEN 1 ELSE 0 END), 0) AS pendingCount, COALESCE(SUM(CASE WHEN a.assignmentStatusId = 2 AND IFNULL(a.pendingStatus, 0) = 0 THEN 1 ELSE 0 END), 0) AS approvedCount FROM assignment_region ar LEFT JOIN data_assignment_entity a ON a.region_id = ar.region_id AND a.userIdAssignment = ar.userId AND a.periodeNotPrimary = ar.survey_period_id WHERE ar.survey_period_id = ? AND ar.userId = ? GROUP BY ar._id, ar.userId ORDER BY ar.smallest_region_full_code ASC", 2);
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, userId);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"assignment_region", "data_assignment_entity"}, false, new Callable<List<AssignmentRegionWilayahPojo>>() { // from class: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.7
            /* JADX WARN: Removed duplicated region for block: B:101:0x01f6  */
            /* JADX WARN: Removed duplicated region for block: B:103:0x0200 A[Catch: all -> 0x0258, TRY_LEAVE, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:107:0x021d  */
            /* JADX WARN: Removed duplicated region for block: B:108:0x021f A[Catch: all -> 0x025c, TryCatch #1 {all -> 0x025c, blocks: (B:3:0x0010, B:105:0x020d, B:109:0x0223, B:110:0x0236, B:108:0x021f), top: B:121:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x0118  */
            /* JADX WARN: Removed duplicated region for block: B:40:0x011b A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:43:0x0127  */
            /* JADX WARN: Removed duplicated region for block: B:44:0x012a A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0136  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x0139 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:51:0x0145  */
            /* JADX WARN: Removed duplicated region for block: B:52:0x0148 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:55:0x0154  */
            /* JADX WARN: Removed duplicated region for block: B:56:0x0157 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:59:0x0163  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0166 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:63:0x0172  */
            /* JADX WARN: Removed duplicated region for block: B:64:0x0175 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:67:0x0181  */
            /* JADX WARN: Removed duplicated region for block: B:68:0x0184 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:75:0x019b  */
            /* JADX WARN: Removed duplicated region for block: B:76:0x019e A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:78:0x01a8  */
            /* JADX WARN: Removed duplicated region for block: B:79:0x01ab A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:86:0x01c2  */
            /* JADX WARN: Removed duplicated region for block: B:87:0x01c5 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:89:0x01cf  */
            /* JADX WARN: Removed duplicated region for block: B:90:0x01d2 A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            /* JADX WARN: Removed duplicated region for block: B:97:0x01e7  */
            /* JADX WARN: Removed duplicated region for block: B:98:0x01ea A[Catch: all -> 0x0258, TryCatch #0 {all -> 0x0258, blocks: (B:4:0x0064, B:5:0x0087, B:7:0x008d, B:9:0x00a7, B:11:0x00ad, B:13:0x00b3, B:15:0x00b9, B:17:0x00bf, B:19:0x00c5, B:21:0x00cb, B:23:0x00d1, B:25:0x00d9, B:27:0x00e3, B:29:0x00ed, B:37:0x0112, B:41:0x0121, B:45:0x0130, B:49:0x013f, B:53:0x014e, B:57:0x015d, B:61:0x016c, B:73:0x0195, B:84:0x01bc, B:95:0x01e1, B:99:0x01f0, B:103:0x0200, B:98:0x01ea, B:90:0x01d2, B:94:0x01db, B:87:0x01c5, B:79:0x01ab, B:83:0x01b6, B:76:0x019e, B:68:0x0184, B:72:0x018f, B:64:0x0175, B:60:0x0166, B:56:0x0157, B:52:0x0148, B:48:0x0139, B:44:0x012a, B:40:0x011b), top: B:119:0x0064 }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public java.util.List<id.go.bpsfasih.data.local.pojo.AssignmentRegionWilayahPojo> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 609
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl.AnonymousClass7.call():java.util.List");
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public int getMissingRegionCount(final String surveyPeriodeId, final String userId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM assignment_region WHERE survey_period_id = ? AND userId = ? AND (region IS NULL OR region = '')", 2);
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public int getMissingRegionMetadataCount(final String surveyPeriodeId, final String userId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM assignment_region WHERE survey_period_id = ? AND userId = ? AND (regionMetadata IS NULL OR regionMetadata = '' OR regionMetadata LIKE '%\"name\":\"Level 1\"%' OR regionMetadata LIKE '%\"groupName\":null%')", 2);
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO
    public int getAssignmentRegionCountByPeriode(final String surveyPeriodeId, final String userId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM assignment_region WHERE survey_period_id = ? AND userId = ?", 2);
        if (surveyPeriodeId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, surveyPeriodeId);
        }
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            return cursorQuery.moveToFirst() ? cursorQuery.getInt(0) : 0;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
