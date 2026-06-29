package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes2.dex */
public final class AssignmentUploadDAO_Impl implements AssignmentUploadDAO {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<AssignmentUploadEntity> __deletionAdapterOfAssignmentUploadEntity;
    private final EntityInsertionAdapter<AssignmentUploadEntity> __insertionAdapterOfAssignmentUploadEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteAll;
    private final SharedSQLiteStatement __preparedStmtOfIsUploadSuccessful;

    public AssignmentUploadDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAssignmentUploadEntity = new EntityInsertionAdapter<AssignmentUploadEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `assignment_uploads` (`id`,`userId`,`templateId`,`data1`,`data2`,`data3`,`data4`,`data5`,`data6`,`data7`,`data8`,`data9`,`data10`,`labelData1`,`labelData2`,`labelData3`,`labelData4`,`labelData5`,`labelData6`,`labelData7`,`labelData8`,`labelData9`,`labelData10`,`is_upload_successful`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, AssignmentUploadEntity assignmentUploadEntity) {
                if (assignmentUploadEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, assignmentUploadEntity.getId());
                }
                if (assignmentUploadEntity.getUserId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, assignmentUploadEntity.getUserId());
                }
                if (assignmentUploadEntity.getTemplateId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, assignmentUploadEntity.getTemplateId());
                }
                if (assignmentUploadEntity.getData1() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, assignmentUploadEntity.getData1());
                }
                if (assignmentUploadEntity.getData2() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, assignmentUploadEntity.getData2());
                }
                if (assignmentUploadEntity.getData3() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, assignmentUploadEntity.getData3());
                }
                if (assignmentUploadEntity.getData4() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, assignmentUploadEntity.getData4());
                }
                if (assignmentUploadEntity.getData5() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, assignmentUploadEntity.getData5());
                }
                if (assignmentUploadEntity.getData6() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, assignmentUploadEntity.getData6());
                }
                if (assignmentUploadEntity.getData7() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, assignmentUploadEntity.getData7());
                }
                if (assignmentUploadEntity.getData8() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, assignmentUploadEntity.getData8());
                }
                if (assignmentUploadEntity.getData9() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, assignmentUploadEntity.getData9());
                }
                if (assignmentUploadEntity.getData10() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, assignmentUploadEntity.getData10());
                }
                if (assignmentUploadEntity.getLabelData1() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, assignmentUploadEntity.getLabelData1());
                }
                if (assignmentUploadEntity.getLabelData2() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, assignmentUploadEntity.getLabelData2());
                }
                if (assignmentUploadEntity.getLabelData3() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, assignmentUploadEntity.getLabelData3());
                }
                if (assignmentUploadEntity.getLabelData4() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, assignmentUploadEntity.getLabelData4());
                }
                if (assignmentUploadEntity.getLabelData5() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, assignmentUploadEntity.getLabelData5());
                }
                if (assignmentUploadEntity.getLabelData6() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, assignmentUploadEntity.getLabelData6());
                }
                if (assignmentUploadEntity.getLabelData7() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, assignmentUploadEntity.getLabelData7());
                }
                if (assignmentUploadEntity.getLabelData8() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, assignmentUploadEntity.getLabelData8());
                }
                if (assignmentUploadEntity.getLabelData9() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, assignmentUploadEntity.getLabelData9());
                }
                if (assignmentUploadEntity.getLabelData10() == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, assignmentUploadEntity.getLabelData10());
                }
                supportSQLiteStatement.bindLong(24, assignmentUploadEntity.isUploadSuccessful() ? 1L : 0L);
            }
        };
        this.__deletionAdapterOfAssignmentUploadEntity = new EntityDeletionOrUpdateAdapter<AssignmentUploadEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `assignment_uploads` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, AssignmentUploadEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
            }
        };
        this.__preparedStmtOfIsUploadSuccessful = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE assignment_uploads SET is_upload_successful = 1 WHERE id= ?";
            }
        };
        this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM assignment_uploads";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public void insert(final AssignmentUploadEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentUploadEntity.insert((EntityInsertionAdapter<AssignmentUploadEntity>) item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public void insertAll(final List<AssignmentUploadEntity> listItems) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentUploadEntity.insert(listItems);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public void deleteItem(final AssignmentUploadEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfAssignmentUploadEntity.handle(item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public void isUploadSuccessful(final String id2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfIsUploadSuccessful.acquire();
        if (id2 == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, id2);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfIsUploadSuccessful.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public void deleteAll() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteAll.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteAll.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentUploadDAO
    public LiveData<List<AssignmentUploadEntity>> getAllItem() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from assignment_uploads ORDER BY id ASC", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"assignment_uploads"}, false, new Callable<List<AssignmentUploadEntity>>() { // from class: id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl.5
            @Override // java.util.concurrent.Callable
            public List<AssignmentUploadEntity> call() throws Exception {
                String string;
                int i;
                int i2;
                boolean z;
                Cursor cursorQuery = DBUtil.query(AssignmentUploadDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userId");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "templateId");
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData1");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData2");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData3");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData4");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData5");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData6");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData7");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData8");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData9");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "labelData10");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_upload_successful");
                    int i3 = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                        String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                        String string4 = cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3);
                        String string5 = cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4);
                        String string6 = cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5);
                        String string7 = cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6);
                        String string8 = cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7);
                        String string9 = cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8);
                        String string10 = cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9);
                        String string11 = cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10);
                        String string12 = cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11);
                        String string13 = cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12);
                        if (cursorQuery.isNull(columnIndexOrThrow13)) {
                            i = i3;
                            string = null;
                        } else {
                            string = cursorQuery.getString(columnIndexOrThrow13);
                            i = i3;
                        }
                        String string14 = cursorQuery.isNull(i) ? null : cursorQuery.getString(i);
                        int i4 = columnIndexOrThrow15;
                        int i5 = columnIndexOrThrow;
                        String string15 = cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4);
                        int i6 = columnIndexOrThrow16;
                        String string16 = cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6);
                        int i7 = columnIndexOrThrow17;
                        String string17 = cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7);
                        int i8 = columnIndexOrThrow18;
                        String string18 = cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8);
                        int i9 = columnIndexOrThrow19;
                        String string19 = cursorQuery.isNull(i9) ? null : cursorQuery.getString(i9);
                        int i10 = columnIndexOrThrow20;
                        String string20 = cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10);
                        int i11 = columnIndexOrThrow21;
                        String string21 = cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11);
                        int i12 = columnIndexOrThrow22;
                        String string22 = cursorQuery.isNull(i12) ? null : cursorQuery.getString(i12);
                        int i13 = columnIndexOrThrow23;
                        String string23 = cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13);
                        int i14 = columnIndexOrThrow24;
                        if (cursorQuery.getInt(i14) != 0) {
                            z = true;
                            i2 = i14;
                        } else {
                            i2 = i14;
                            z = false;
                        }
                        arrayList.add(new AssignmentUploadEntity(string2, string3, string4, string5, string6, string7, string8, string9, string10, string11, string12, string13, string, string14, string15, string16, string17, string18, string19, string20, string21, string22, string23, z));
                        columnIndexOrThrow = i5;
                        columnIndexOrThrow15 = i4;
                        columnIndexOrThrow16 = i6;
                        columnIndexOrThrow17 = i7;
                        columnIndexOrThrow18 = i8;
                        columnIndexOrThrow19 = i9;
                        columnIndexOrThrow20 = i10;
                        columnIndexOrThrow21 = i11;
                        columnIndexOrThrow22 = i12;
                        columnIndexOrThrow23 = i13;
                        columnIndexOrThrow24 = i2;
                        i3 = i;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
