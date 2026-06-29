package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import id.go.bpsfasih.data.local.entities.TemplateValidationEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class TemplateValidationDAO_Impl implements TemplateValidationDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<TemplateValidationEntity> __insertionAdapterOfTemplateValidationEntity;
    private final SharedSQLiteStatement __preparedStmtOfUpdateFormEngineInfo;

    public TemplateValidationDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTemplateValidationEntity = new EntityInsertionAdapter<TemplateValidationEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.TemplateValidationDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `data_template_validation` (`survey_id`,`userId`,`template_id`,`template_version`,`validasi_version`,`form_engine_id`,`form_engine_brand_name`) VALUES (?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, TemplateValidationEntity value) {
                if (value.getSurvey_id() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getSurvey_id());
                }
                if (value.getUserId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUserId());
                }
                if (value.getTemplate_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getTemplate_id());
                }
                if (value.getTemplate_version() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTemplate_version());
                }
                if (value.getValidasi_version() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getValidasi_version());
                }
                stmt.bindLong(6, value.getFormEngineId());
                if (value.getFormEngineBrandName() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getFormEngineBrandName());
                }
            }
        };
        this.__preparedStmtOfUpdateFormEngineInfo = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.TemplateValidationDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE data_template_validation SET form_engine_id = ?, form_engine_brand_name = ? WHERE survey_id = ? AND userId = ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.TemplateValidationDAO
    public void insert(final TemplateValidationEntity templateValidationEntity) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTemplateValidationEntity.insert((EntityInsertionAdapter<TemplateValidationEntity>) templateValidationEntity);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.TemplateValidationDAO
    public void updateFormEngineInfo(final int formEngineId, final String formEngineBrandName, final String surveyId, final String userId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfUpdateFormEngineInfo.acquire();
        supportSQLiteStatementAcquire.bindLong(1, formEngineId);
        if (formEngineBrandName == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, formEngineBrandName);
        }
        if (surveyId == null) {
            supportSQLiteStatementAcquire.bindNull(3);
        } else {
            supportSQLiteStatementAcquire.bindString(3, surveyId);
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
            this.__preparedStmtOfUpdateFormEngineInfo.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.TemplateValidationDAO
    public TemplateValidationEntity getBySurveyId(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM data_template_validation WHERE survey_id = ? AND userId= ?", 2);
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
        TemplateValidationEntity templateValidationEntity = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "userId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "template_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "template_version");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "validasi_version");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "form_engine_id");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "form_engine_brand_name");
            if (cursorQuery.moveToFirst()) {
                templateValidationEntity = new TemplateValidationEntity(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.getInt(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
            }
            return templateValidationEntity;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
