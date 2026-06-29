package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.TarikSampleEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class TarikSampleDAO_Impl implements TarikSampleDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<TarikSampleEntity> __insertionAdapterOfTarikSampleEntity;

    public TarikSampleDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTarikSampleEntity = new EntityInsertionAdapter<TarikSampleEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.TarikSampleDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `sampling_offline` (`id`,`survey_periode_source_id`,`survey_periode_target_id`,`script_id`,`script_sampling`,`source_schema`,`target_schema`,`source`,`target`) VALUES (?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, TarikSampleEntity value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getSurvey_periode_source_id() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getSurvey_periode_source_id());
                }
                if (value.getSurvey_periode_target_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getSurvey_periode_target_id());
                }
                if (value.getScript_id() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getScript_id());
                }
                if (value.getScript_sampling() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getScript_sampling());
                }
                if (value.getSource_schema() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getSource_schema());
                }
                if (value.getTarget_schema() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getTarget_schema());
                }
                if (value.getSource() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getSource());
                }
                if (value.getTarget() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getTarget());
                }
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.TarikSampleDAO
    public void insert(final TarikSampleEntity tarikSampleEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTarikSampleEntity.insert((EntityInsertionAdapter<TarikSampleEntity>) tarikSampleEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.TarikSampleDAO
    public TarikSampleEntity getSamplingBySurveyPeriodId(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from sampling_offline WHERE survey_periode_source_id = ? LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        TarikSampleEntity tarikSampleEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_source_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_target_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "script_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "script_sampling");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "source_schema");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "target_schema");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "source");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.AttributesType.S_TARGET);
            if (cursorQuery.moveToFirst()) {
                tarikSampleEntity = new TarikSampleEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
            }
            return tarikSampleEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.TarikSampleDAO
    public TarikSampleEntity getSamplingBySurveyPeriodeTargetId(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * from sampling_offline WHERE survey_periode_target_id = ? LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        TarikSampleEntity tarikSampleEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_source_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_target_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "script_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "script_sampling");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "source_schema");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "target_schema");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "source");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TypedValues.AttributesType.S_TARGET);
            if (cursorQuery.moveToFirst()) {
                tarikSampleEntity = new TarikSampleEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
            }
            return tarikSampleEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
