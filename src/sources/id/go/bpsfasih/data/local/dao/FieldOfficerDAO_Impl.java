package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import id.go.bpsfasih.data.local.entities.FieldOfficerEntity;
import java.util.Collections;
import java.util.List;

/* loaded from: classes2.dex */
public final class FieldOfficerDAO_Impl implements FieldOfficerDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<FieldOfficerEntity> __insertionAdapterOfFieldOfficerEntity;
    private final SharedSQLiteStatement __preparedStmtOfSetIsSynced;

    public FieldOfficerDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfFieldOfficerEntity = new EntityInsertionAdapter<FieldOfficerEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.FieldOfficerDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `field_officer` (`id`,`nik`,`username`,`email`,`firstName`,`lastName`,`fullname`,`pob`,`dob`,`gender`,`religionId`,`phoneNumber`,`phoneNumber2`,`regencyId`,`regencyName`,`postalCode`,`address`,`maritalStatusId`,`recentEducationId`,`roleName`,`provinceId`,`provinceName`,`isSynced`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, FieldOfficerEntity fieldOfficerEntity) {
                if (fieldOfficerEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, fieldOfficerEntity.getId());
                }
                if (fieldOfficerEntity.getNik() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, fieldOfficerEntity.getNik());
                }
                if (fieldOfficerEntity.getUsername() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, fieldOfficerEntity.getUsername());
                }
                if (fieldOfficerEntity.getEmail() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, fieldOfficerEntity.getEmail());
                }
                if (fieldOfficerEntity.getFirstName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, fieldOfficerEntity.getFirstName());
                }
                if (fieldOfficerEntity.getLastName() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, fieldOfficerEntity.getLastName());
                }
                if (fieldOfficerEntity.getFullname() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, fieldOfficerEntity.getFullname());
                }
                if (fieldOfficerEntity.getPob() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, fieldOfficerEntity.getPob());
                }
                if (fieldOfficerEntity.getDob() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, fieldOfficerEntity.getDob());
                }
                if (fieldOfficerEntity.getGender() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, fieldOfficerEntity.getGender());
                }
                if (fieldOfficerEntity.getReligionId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, fieldOfficerEntity.getReligionId());
                }
                if (fieldOfficerEntity.getPhoneNumber() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, fieldOfficerEntity.getPhoneNumber());
                }
                if (fieldOfficerEntity.getPhoneNumber2() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, fieldOfficerEntity.getPhoneNumber2());
                }
                if (fieldOfficerEntity.getRegencyId() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, fieldOfficerEntity.getRegencyId());
                }
                if (fieldOfficerEntity.getRegencyName() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, fieldOfficerEntity.getRegencyName());
                }
                if (fieldOfficerEntity.getPostalCode() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, fieldOfficerEntity.getPostalCode());
                }
                if (fieldOfficerEntity.getAddress() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, fieldOfficerEntity.getAddress());
                }
                if (fieldOfficerEntity.getMaritalStatusId() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, fieldOfficerEntity.getMaritalStatusId());
                }
                if (fieldOfficerEntity.getRecentEducationId() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, fieldOfficerEntity.getRecentEducationId());
                }
                if (fieldOfficerEntity.getRoleName() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, fieldOfficerEntity.getRoleName());
                }
                if (fieldOfficerEntity.getProvinceId() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, fieldOfficerEntity.getProvinceId());
                }
                if (fieldOfficerEntity.getProvinceName() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, fieldOfficerEntity.getProvinceName());
                }
                supportSQLiteStatement.bindLong(23, fieldOfficerEntity.isSynced() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfSetIsSynced = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.FieldOfficerDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE field_officer SET isSynced = ? WHERE id = ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.FieldOfficerDAO
    public void insert(final FieldOfficerEntity fieldOfficer) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfFieldOfficerEntity.insert((EntityInsertionAdapter<FieldOfficerEntity>) fieldOfficer);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.FieldOfficerDAO
    public void setIsSynced(boolean z, String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfSetIsSynced.acquire();
        supportSQLiteStatementAcquire.bindLong(1, z ? 1L : 0L);
        if (str == null) {
            supportSQLiteStatementAcquire.bindNull(2);
        } else {
            supportSQLiteStatementAcquire.bindString(2, str);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetIsSynced.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // id.go.bpsfasih.data.local.dao.FieldOfficerDAO
    public boolean getSyncedUser(final String userId) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select isSynced From field_officer WHERE id = ?", 1);
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, userId);
        }
        this.__db.assertNotSuspendingTransaction();
        boolean z = false;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            if (cursorQuery.moveToFirst()) {
                z = cursorQuery.getInt(0) != 0;
            }
            return z;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
