package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import id.go.bpsfasih.data.local.entities.AssignmentSubmitVersionEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class AssignmentSubmitVersionDAO_Impl implements AssignmentSubmitVersionDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<AssignmentSubmitVersionEntity> __insertionAdapterOfAssignmentSubmitVersionEntity;

    public AssignmentSubmitVersionDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfAssignmentSubmitVersionEntity = new EntityInsertionAdapter<AssignmentSubmitVersionEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `assignment_submit_version` (`assignment_id`,`submit_version_code`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, AssignmentSubmitVersionEntity value) {
                if (value.getAssignmentId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getAssignmentId());
                }
                stmt.bindLong(2, value.getSubmitVersionCode());
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO
    public void insert(final AssignmentSubmitVersionEntity item) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfAssignmentSubmitVersionEntity.insert((EntityInsertionAdapter<AssignmentSubmitVersionEntity>) item);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO
    public AssignmentSubmitVersionEntity getByAssignmentId(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM assignment_submit_version WHERE assignment_id = ? LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        AssignmentSubmitVersionEntity assignmentSubmitVersionEntity = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "submit_version_code");
            if (cursorQuery.moveToFirst()) {
                if (!cursorQuery.isNull(columnIndexOrThrow)) {
                    string = cursorQuery.getString(columnIndexOrThrow);
                }
                assignmentSubmitVersionEntity = new AssignmentSubmitVersionEntity(string, cursorQuery.getInt(columnIndexOrThrow2));
            }
            return assignmentSubmitVersionEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
