package id.go.bpsfasih.data.local.dao;

import android.database.Cursor;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.google.firebase.database.core.ServerValues;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.entities.LocationTrackingEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes2.dex */
public final class LocationTrackingDAO_Impl implements LocationTrackingDAO {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<LocationTrackingEntity> __insertionAdapterOfLocationTrackingEntity;
    private final SharedSQLiteStatement __preparedStmtOfDeleteOldLocations;

    public LocationTrackingDAO_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfLocationTrackingEntity = new EntityInsertionAdapter<LocationTrackingEntity>(__db) { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `location_tracking` (`id`,`public_id`,`user_id`,`assignment_id`,`survey_periode_id`,`activity`,`session`,`latitude`,`longitude`,`timestamp`,`accuracy`,`date`,`is_synced`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, LocationTrackingEntity locationTrackingEntity) {
                supportSQLiteStatement.bindLong(1, locationTrackingEntity.getId());
                if (locationTrackingEntity.getPublicId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, locationTrackingEntity.getPublicId());
                }
                if (locationTrackingEntity.getUserId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, locationTrackingEntity.getUserId());
                }
                if (locationTrackingEntity.getAssignmentId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, locationTrackingEntity.getAssignmentId());
                }
                if (locationTrackingEntity.getSurveyPeriodeId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, locationTrackingEntity.getSurveyPeriodeId());
                }
                if (locationTrackingEntity.getActivity() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, locationTrackingEntity.getActivity());
                }
                if (locationTrackingEntity.getSession() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, locationTrackingEntity.getSession());
                }
                supportSQLiteStatement.bindDouble(8, locationTrackingEntity.getLatitude());
                supportSQLiteStatement.bindDouble(9, locationTrackingEntity.getLongitude());
                supportSQLiteStatement.bindLong(10, locationTrackingEntity.getTimestamp());
                if (locationTrackingEntity.getAccuracy() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindDouble(11, locationTrackingEntity.getAccuracy().floatValue());
                }
                if (locationTrackingEntity.getDate() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, locationTrackingEntity.getDate());
                }
                supportSQLiteStatement.bindLong(13, locationTrackingEntity.isSynced() ? 1L : 0L);
            }
        };
        this.__preparedStmtOfDeleteOldLocations = new SharedSQLiteStatement(__db) { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM location_tracking WHERE date < ?";
            }
        };
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object insert(final LocationTrackingEntity location, final Continuation<? super Long> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Long>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                LocationTrackingDAO_Impl.this.__db.beginTransaction();
                try {
                    long jInsertAndReturnId = LocationTrackingDAO_Impl.this.__insertionAdapterOfLocationTrackingEntity.insertAndReturnId(location);
                    LocationTrackingDAO_Impl.this.__db.setTransactionSuccessful();
                    return Long.valueOf(jInsertAndReturnId);
                } finally {
                    LocationTrackingDAO_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object deleteOldLocations(final String date, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.4
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                SupportSQLiteStatement supportSQLiteStatementAcquire = LocationTrackingDAO_Impl.this.__preparedStmtOfDeleteOldLocations.acquire();
                String str = date;
                if (str == null) {
                    supportSQLiteStatementAcquire.bindNull(1);
                } else {
                    supportSQLiteStatementAcquire.bindString(1, str);
                }
                LocationTrackingDAO_Impl.this.__db.beginTransaction();
                try {
                    supportSQLiteStatementAcquire.executeUpdateDelete();
                    LocationTrackingDAO_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    LocationTrackingDAO_Impl.this.__db.endTransaction();
                    LocationTrackingDAO_Impl.this.__preparedStmtOfDeleteOldLocations.release(supportSQLiteStatementAcquire);
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Flow<List<LocationTrackingEntity>> getLocationsByDate(final String date) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM location_tracking WHERE date = ? ORDER BY timestamp DESC", 1);
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, date);
        }
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"location_tracking"}, new Callable<List<LocationTrackingEntity>>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.5
            @Override // java.util.concurrent.Callable
            public List<LocationTrackingEntity> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "public_id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_id");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "activity");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "session");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "latitude");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "longitude");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ServerValues.NAME_OP_TIMESTAMP);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "accuracy");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_synced");
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new LocationTrackingEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getDouble(columnIndexOrThrow8), cursorQuery.getDouble(columnIndexOrThrow9), cursorQuery.getLong(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : Float.valueOf(cursorQuery.getFloat(columnIndexOrThrow11)), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getInt(columnIndexOrThrow13) != 0));
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

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object getLocationsByUserAndDate(final String userId, final String date, final Continuation<? super List<LocationTrackingEntity>> continuation) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM location_tracking WHERE user_id = ? AND date = ? ORDER BY timestamp DESC", 2);
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, userId);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<LocationTrackingEntity>>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.6
            @Override // java.util.concurrent.Callable
            public List<LocationTrackingEntity> call() throws Exception {
                int columnIndexOrThrow;
                int columnIndexOrThrow2;
                int columnIndexOrThrow3;
                int columnIndexOrThrow4;
                int columnIndexOrThrow5;
                int columnIndexOrThrow6;
                int columnIndexOrThrow7;
                int columnIndexOrThrow8;
                int columnIndexOrThrow9;
                int columnIndexOrThrow10;
                int columnIndexOrThrow11;
                int columnIndexOrThrow12;
                int columnIndexOrThrow13;
                AnonymousClass6 anonymousClass6 = this;
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "public_id");
                    columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
                    columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
                    columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_id");
                    columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "activity");
                    columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "session");
                    columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "latitude");
                    columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "longitude");
                    columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ServerValues.NAME_OP_TIMESTAMP);
                    columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "accuracy");
                    columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date");
                    columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_synced");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new LocationTrackingEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getDouble(columnIndexOrThrow8), cursorQuery.getDouble(columnIndexOrThrow9), cursorQuery.getLong(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : Float.valueOf(cursorQuery.getFloat(columnIndexOrThrow11)), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getInt(columnIndexOrThrow13) != 0));
                    }
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    anonymousClass6 = this;
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object getLocationsByAssignment(final String assignmentId, final Continuation<? super List<LocationTrackingEntity>> continuation) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM location_tracking WHERE assignment_id = ? ORDER BY timestamp DESC", 1);
        if (assignmentId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, assignmentId);
        }
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<LocationTrackingEntity>>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.7
            @Override // java.util.concurrent.Callable
            public List<LocationTrackingEntity> call() throws Exception {
                int columnIndexOrThrow;
                int columnIndexOrThrow2;
                int columnIndexOrThrow3;
                int columnIndexOrThrow4;
                int columnIndexOrThrow5;
                int columnIndexOrThrow6;
                int columnIndexOrThrow7;
                int columnIndexOrThrow8;
                int columnIndexOrThrow9;
                int columnIndexOrThrow10;
                int columnIndexOrThrow11;
                int columnIndexOrThrow12;
                int columnIndexOrThrow13;
                AnonymousClass7 anonymousClass7 = this;
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "public_id");
                    columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
                    columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
                    columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_id");
                    columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "activity");
                    columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "session");
                    columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "latitude");
                    columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "longitude");
                    columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ServerValues.NAME_OP_TIMESTAMP);
                    columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "accuracy");
                    columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date");
                    columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_synced");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new LocationTrackingEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getDouble(columnIndexOrThrow8), cursorQuery.getDouble(columnIndexOrThrow9), cursorQuery.getLong(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : Float.valueOf(cursorQuery.getFloat(columnIndexOrThrow11)), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getInt(columnIndexOrThrow13) != 0));
                    }
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    anonymousClass7 = this;
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object getLocationsByAssignmentAndDate(final String assignmentId, final String date, final Continuation<? super List<LocationTrackingEntity>> continuation) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM location_tracking WHERE assignment_id = ? AND date = ? ORDER BY timestamp DESC", 2);
        if (assignmentId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, assignmentId);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<LocationTrackingEntity>>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.8
            @Override // java.util.concurrent.Callable
            public List<LocationTrackingEntity> call() throws Exception {
                int columnIndexOrThrow;
                int columnIndexOrThrow2;
                int columnIndexOrThrow3;
                int columnIndexOrThrow4;
                int columnIndexOrThrow5;
                int columnIndexOrThrow6;
                int columnIndexOrThrow7;
                int columnIndexOrThrow8;
                int columnIndexOrThrow9;
                int columnIndexOrThrow10;
                int columnIndexOrThrow11;
                int columnIndexOrThrow12;
                int columnIndexOrThrow13;
                AnonymousClass8 anonymousClass8 = this;
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "public_id");
                    columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
                    columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
                    columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_id");
                    columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "activity");
                    columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "session");
                    columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "latitude");
                    columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "longitude");
                    columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ServerValues.NAME_OP_TIMESTAMP);
                    columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "accuracy");
                    columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date");
                    columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_synced");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new LocationTrackingEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getDouble(columnIndexOrThrow8), cursorQuery.getDouble(columnIndexOrThrow9), cursorQuery.getLong(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : Float.valueOf(cursorQuery.getFloat(columnIndexOrThrow11)), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getInt(columnIndexOrThrow13) != 0));
                    }
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    anonymousClass8 = this;
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object getUnsyncedLocations(final Continuation<? super List<LocationTrackingEntity>> continuation) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM location_tracking WHERE is_synced = 0 ORDER BY timestamp ASC", 0);
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<List<LocationTrackingEntity>>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.9
            @Override // java.util.concurrent.Callable
            public List<LocationTrackingEntity> call() throws Exception {
                int columnIndexOrThrow;
                int columnIndexOrThrow2;
                int columnIndexOrThrow3;
                int columnIndexOrThrow4;
                int columnIndexOrThrow5;
                int columnIndexOrThrow6;
                int columnIndexOrThrow7;
                int columnIndexOrThrow8;
                int columnIndexOrThrow9;
                int columnIndexOrThrow10;
                int columnIndexOrThrow11;
                int columnIndexOrThrow12;
                int columnIndexOrThrow13;
                AnonymousClass9 anonymousClass9 = this;
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, DownloadModel.ID);
                    columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "public_id");
                    columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
                    columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "assignment_id");
                    columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "survey_periode_id");
                    columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "activity");
                    columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "session");
                    columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "latitude");
                    columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "longitude");
                    columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, ServerValues.NAME_OP_TIMESTAMP);
                    columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "accuracy");
                    columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "date");
                    columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_synced");
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        arrayList.add(new LocationTrackingEntity(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.getDouble(columnIndexOrThrow8), cursorQuery.getDouble(columnIndexOrThrow9), cursorQuery.getLong(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : Float.valueOf(cursorQuery.getFloat(columnIndexOrThrow11)), cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12), cursorQuery.getInt(columnIndexOrThrow13) != 0));
                    }
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    anonymousClass9 = this;
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                    throw th;
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object getTrackingCountToday(final String userId, final String date, final Continuation<? super Integer> continuation) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT COUNT(*) FROM location_tracking WHERE user_id = ? AND date = ?", 2);
        if (userId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, userId);
        }
        if (date == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, date);
        }
        return CoroutinesRoom.execute(this.__db, false, DBUtil.createCancellationSignal(), new Callable<Integer>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Integer call() throws Exception {
                Integer numValueOf = null;
                Cursor cursorQuery = DBUtil.query(LocationTrackingDAO_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    if (cursorQuery.moveToFirst() && !cursorQuery.isNull(0)) {
                        numValueOf = Integer.valueOf(cursorQuery.getInt(0));
                    }
                    return numValueOf;
                } finally {
                    cursorQuery.close();
                    roomSQLiteQueryAcquire.release();
                }
            }
        }, continuation);
    }

    @Override // id.go.bpsfasih.data.local.dao.LocationTrackingDAO
    public Object deleteLocationsByIds(final List<Long> ids, final Continuation<? super Unit> continuation) {
        return CoroutinesRoom.execute(this.__db, true, new Callable<Unit>() { // from class: id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl.11
            @Override // java.util.concurrent.Callable
            public Unit call() throws Exception {
                StringBuilder sbNewStringBuilder = StringUtil.newStringBuilder();
                sbNewStringBuilder.append("DELETE FROM location_tracking WHERE id IN (");
                StringUtil.appendPlaceholders(sbNewStringBuilder, ids.size());
                sbNewStringBuilder.append(")");
                SupportSQLiteStatement supportSQLiteStatementCompileStatement = LocationTrackingDAO_Impl.this.__db.compileStatement(sbNewStringBuilder.toString());
                int i = 1;
                for (Long l : ids) {
                    if (l == null) {
                        supportSQLiteStatementCompileStatement.bindNull(i);
                    } else {
                        supportSQLiteStatementCompileStatement.bindLong(i, l.longValue());
                    }
                    i++;
                }
                LocationTrackingDAO_Impl.this.__db.beginTransaction();
                try {
                    supportSQLiteStatementCompileStatement.executeUpdateDelete();
                    LocationTrackingDAO_Impl.this.__db.setTransactionSuccessful();
                    return Unit.INSTANCE;
                } finally {
                    LocationTrackingDAO_Impl.this.__db.endTransaction();
                }
            }
        }, continuation);
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
