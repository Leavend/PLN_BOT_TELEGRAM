package id.go.bpsfasih.data.local.databaseRoom;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO;
import id.go.bpsfasih.data.local.dao.AssignmentInboxDAO;
import id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO;
import id.go.bpsfasih.data.local.dao.AssignmentUploadDAO;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO;
import id.go.bpsfasih.data.local.dao.CustomDataTemplateDAO;
import id.go.bpsfasih.data.local.dao.FieldOfficerDAO;
import id.go.bpsfasih.data.local.dao.LocationTrackingDAO;
import id.go.bpsfasih.data.local.dao.PeriodeDAONew;
import id.go.bpsfasih.data.local.dao.SamplingRegionDAO;
import id.go.bpsfasih.data.local.dao.SurveyDAONew;
import id.go.bpsfasih.data.local.dao.SurveyTimeDAO;
import id.go.bpsfasih.data.local.dao.TarikSampleDAO;
import id.go.bpsfasih.data.local.dao.TemplateValidationDAO;
import id.go.bpsfasih.data.local.dao.UserDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentUpdateListingDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentsDao;
import id.go.bpsfasih.data.local.dao.regionDao.NewAssignmentsDAO;
import id.go.bpsfasih.data.local.dao.regionDao.UpdateListingDAO;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: database.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020$H&J\b\u0010%\u001a\u00020&H&J\b\u0010'\u001a\u00020(H&J\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020,H&¨\u0006."}, d2 = {"Lid/go/bpsfasih/data/local/databaseRoom/SurveyRoomDatabase;", "Landroidx/room/RoomDatabase;", "()V", "CustomDataTemplateDAO", "Lid/go/bpsfasih/data/local/dao/CustomDataTemplateDAO;", "assigmentDAO", "Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "assignmentInboxDao", "Lid/go/bpsfasih/data/local/dao/AssignmentInboxDAO;", "assignmentSubmitVersionDAO", "Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;", "assignmentUpdateListingDao", "Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentUpdateListingDAO;", "assignmentUploadDao", "Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;", "assignmentsDao", "Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentsDao;", "assignmetnBlokSensusDao", "Lid/go/bpsfasih/data/local/dao/AssignmentBlokSensusDAO;", "assignmetnRegionDAO", "Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;", "customColumnDAO", "Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "fieldOfficerDAO", "Lid/go/bpsfasih/data/local/dao/FieldOfficerDAO;", "locationTrackingDAO", "Lid/go/bpsfasih/data/local/dao/LocationTrackingDAO;", "newAssignmentsDao", "Lid/go/bpsfasih/data/local/dao/regionDao/NewAssignmentsDAO;", "periodeNewDao", "Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;", "samplingRegionDAO", "Lid/go/bpsfasih/data/local/dao/SamplingRegionDAO;", "surveyNewDao", "Lid/go/bpsfasih/data/local/dao/SurveyDAONew;", "surveyTimeDao", "Lid/go/bpsfasih/data/local/dao/SurveyTimeDAO;", "tarikSampleDAO", "Lid/go/bpsfasih/data/local/dao/TarikSampleDAO;", "templateValidasiDAO", "Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;", "updateListingDao", "Lid/go/bpsfasih/data/local/dao/regionDao/UpdateListingDAO;", "userDAO", "Lid/go/bpsfasih/data/local/dao/UserDAO;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public abstract class SurveyRoomDatabase extends RoomDatabase {
    public static final int $stable = 0;
    private static volatile SurveyRoomDatabase INSTANCE;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Migration MIGRATION_1_2 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_1_2$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: tambah kolom isengineIcs2");
            database.execSQL("ALTER TABLE data_survey_new_table ADD COLUMN engineIcs2 INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final Migration MIGRATION_3_4 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_3_4$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 4");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN assignmentStatusAlias TEXT");
            database.execSQL("ALTER TABLE data_upload ADD COLUMN comment TEXT");
            database.execSQL("ALTER TABLE data_upload ADD COLUMN type INTEGER");
            database.execSQL("CREATE TABLE assignment_region ( _id TEXT NOT NULL, userId TEXT NOT NULL, region_id TEXT, region_group_id TEXT, smallest_region_full_code TEXT, survey_period_id TEXT, done_listing INTEGER, done_tarik_sample INTEGER, done_tarik_sample_offline INTEGER DEFAULT 0, PRIMARY KEY(_id, userId))");
        }
    };
    private static final Migration MIGRATION_4_5 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_4_5$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 5");
            database.execSQL("CREATE TABLE data_template_validation ( survey_id TEXT NOT NULL, userId TEXT NOT NULL, template_id TEXT NOT NULL, template_version TEXT, validasi_version TEXT, PRIMARY KEY(survey_id, userId))");
        }
    };
    private static final Migration MIGRATION_5_6 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_5_6$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 6");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN note TEXT");
            database.execSQL("ALTER TABLE data_upload ADD COLUMN note TEXT");
        }
    };
    private static final Migration MIGRATION_6_7 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_6_7$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 7");
            database.execSQL("ALTER TABLE data_user_for_assignment ADD COLUMN canChangeMode INTEGER NOT NULL default 0");
            database.execSQL("ALTER TABLE data_survey_new_table ADD COLUMN surveyModes TEXT");
            database.execSQL("ALTER TABLE data_tutorial ADD COLUMN tutorial_surveysurveyModes TEXT");
            database.execSQL("ALTER TABLE data_table_periode_new ADD COLUMN survey_surveyModes TEXT");
            database.execSQL("ALTER TABLE data_newHouseHoldSample ADD COLUMN NHS_SurveysurveyModes TEXT");
            database.execSQL("ALTER TABLE data_newHouseHoldSample ADD COLUMN NHS_Periodesurvey_surveyModes TEXT");
        }
    };
    private static final Migration MIGRATION_7_8 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_7_8$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 8");
            database.execSQL("ALTER TABLE data_upload ADD COLUMN changeModeParam TEXT");
        }
    };
    private static final Migration MIGRATION_8_9 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_8_9$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 9");
            database.execSQL("CREATE TABLE custom_column_entity ( idPrimary TEXT NOT NULL, id TEXT NOT NULL, userId TEXT NOT NULL, data1 INTEGER NOT NULL DEFAULT 1, data2 INTEGER NOT NULL DEFAULT 1, data3 INTEGER NOT NULL DEFAULT 1, data4 INTEGER NOT NULL DEFAULT 1, data5 INTEGER NOT NULL DEFAULT 1, data6 INTEGER NOT NULL DEFAULT 1, data7 INTEGER NOT NULL DEFAULT 1, data8 INTEGER NOT NULL DEFAULT 1, data9 INTEGER NOT NULL DEFAULT 1, data10 INTEGER NOT NULL DEFAULT 1, PRIMARY KEY(idPrimary))");
        }
    };
    private static final Migration MIGRATION_9_10 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_9_10$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 10");
            database.execSQL("ALTER TABLE data_template_validation ADD COLUMN form_engine_id INTEGER NOT NULL DEFAULT 1");
            database.execSQL("ALTER TABLE data_template_validation ADD COLUMN form_engine_brand_name TEXT");
        }
    };
    private static final Migration MIGRATION_10_11 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_10_11$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 11");
            database.execSQL("CREATE TABLE sampling_offline ( id TEXT NOT NULL, survey_periode_source_id TEXT NOT NULL, survey_periode_target_id TEXT NOT NULL, script_id TEXT NOT NULL, script_sampling TEXT, PRIMARY KEY(id))");
        }
    };
    private static final Migration MIGRATION_11_12 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_11_12$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 12");
            database.execSQL("CREATE TABLE sampling_region (id TEXT NOT NULL, survey_periode_id TEXT NOT NULL, fullcode TEXT NOT NULL, mode TEXT NOT NULL, status TEXT, PRIMARY KEY(id))");
        }
    };
    private static final Migration MIGRATION_12_13 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_12_13$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 13");
            database.execSQL("ALTER TABLE sampling_offline ADD COLUMN source_schema TEXT");
            database.execSQL("ALTER TABLE sampling_offline ADD COLUMN target_schema TEXT");
        }
    };
    private static final Migration MIGRATION_13_14 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_13_14$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 14");
            database.execSQL("ALTER TABLE sampling_offline ADD COLUMN source TEXT");
            database.execSQL("ALTER TABLE sampling_offline ADD COLUMN target TEXT");
        }
    };
    private static final Migration MIGRATION_14_15 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_14_15$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 15");
            database.execSQL("ALTER TABLE sampling_region ADD COLUMN is_done INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final Migration MIGRATION_15_16 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_15_16$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("FOUR", "migrate: ke versi 16");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN is_encrypt INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final Migration MIGRATION_16_17 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_16_17$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("\n                    CREATE TABLE IF NOT EXISTS assignment_uploads (\n                        id TEXT NOT NULL PRIMARY KEY,\n                        userId TEXT NOT NULL DEFAULT '',\n                        templateId TEXT,\n                        data1 TEXT,\n                        data2 TEXT,\n                        data3 TEXT,\n                        data4 TEXT,\n                        data5 TEXT,\n                        data6 TEXT,\n                        data7 TEXT,\n                        data8 TEXT,\n                        data9 TEXT,\n                        data10 TEXT,\n                        labelData1 TEXT,\n                        labelData2 TEXT,\n                        labelData3 TEXT,\n                        labelData4 TEXT,\n                        labelData5 TEXT,\n                        labelData6 TEXT,\n                        labelData7 TEXT,\n                        labelData8 TEXT,\n                        labelData9 TEXT,\n                        labelData10 TEXT,\n                        is_upload_successful INTEGER NOT NULL DEFAULT 0\n                    )\n                ");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN basePath TEXT");
            database.execSQL("DROP TABLE IF EXISTS data_upload");
        }
    };
    private static final Migration MIGRATION_17_18 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_17_18$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("DROP TABLE IF EXISTS data_chat");
            database.execSQL("DROP TABLE IF EXISTS data_chat_group");
            database.execSQL("DROP TABLE IF EXISTS data_chat_group_member");
            database.execSQL("DROP TABLE IF EXISTS data_chat_group_message");
            database.execSQL("DROP TABLE IF EXISTS data_list_chat_group");
            database.execSQL("DROP TABLE IF EXISTS data_landmark");
            database.execSQL("DROP TABLE IF EXISTS data_tutorial");
            database.execSQL("DROP TABLE IF EXISTS data_user_for_assignment");
        }
    };
    private static final Migration MIGRATION_18_19 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_18_19$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("DROP TABLE IF EXISTS data_assignmentInfo_entity");
            database.execSQL("DROP TABLE IF EXISTS data_assignment_assign_pending");
            database.execSQL("DROP TABLE IF EXISTS data_newHouseHoldSample");
            database.execSQL("DROP TABLE IF EXISTS data_tracker_user_model");
            database.execSQL("DROP TABLE IF EXISTS data_tracking_location");
            database.execSQL("DROP TABLE IF EXISTS kode_infrastruktur");
            database.execSQL("DROP TABLE IF EXISTS kode_infrastruktur_kategori");
            database.execSQL("DROP TABLE IF EXISTS list_template_design");
            database.execSQL("DROP TABLE IF EXISTS main_user");
        }
    };
    private static final Migration MIGRATION_19_20 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_19_20$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE data_survey_new_table ADD COLUMN is_pin INTEGER NOT NULL DEFAULT 0");
            database.execSQL("ALTER TABLE data_table_periode_new ADD COLUMN survey_is_pin INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final Migration MIGRATION_20_21 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_20_21$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE assignment_region ADD COLUMN state_data_table TEXT");
        }
    };
    private static final Migration MIGRATION_21_22 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_21_22$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN data_downloaded_at TEXT");
        }
    };
    private static final Migration MIGRATION_22_23 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_22_23$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE data_assignment_entity ADD COLUMN submitVersionCode INTEGER NOT NULL DEFAULT 0");
        }
    };
    private static final Migration MIGRATION_23_24 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_23_24$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("CREATE TABLE IF NOT EXISTS assignment_submit_version (assignment_id TEXT NOT NULL, submit_version_code INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(assignment_id))");
            database.execSQL("INSERT OR REPLACE INTO assignment_submit_version (assignment_id, submit_version_code) SELECT assignment_id, MAX(COALESCE(submitVersionCode, 0)) FROM data_assignment_entity WHERE assignment_id IS NOT NULL AND assignment_id != '' GROUP BY assignment_id");
        }
    };
    private static final Migration MIGRATION_24_25 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_24_25$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            Log.d("TRACKING", "migrate: Create location_tracking table");
            database.execSQL("\n                    CREATE TABLE IF NOT EXISTS location_tracking (\n                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n                        user_id TEXT NOT NULL,\n                        assignment_id TEXT,\n                        survey_periode_id TEXT,\n                        activity TEXT NOT NULL DEFAULT 'ENTRY',\n                        session TEXT NOT NULL DEFAULT '',\n                        latitude REAL NOT NULL,\n                        longitude REAL NOT NULL,\n                        timestamp INTEGER NOT NULL,\n                        accuracy REAL,\n                        date TEXT NOT NULL,\n                        is_synced INTEGER NOT NULL DEFAULT 0\n                    )\n                ");
            database.execSQL("\n                    CREATE INDEX IF NOT EXISTS index_location_tracking_user_date\n                    ON location_tracking(user_id, date)\n                ");
            database.execSQL("\n                    CREATE INDEX IF NOT EXISTS index_location_tracking_assignment\n                    ON location_tracking(assignment_id)\n                ");
        }
    };
    private static final Migration MIGRATION_25_26 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_25_26$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE assignment_region ADD COLUMN region TEXT");
            database.execSQL("ALTER TABLE assignment_region ADD COLUMN regionMetadata TEXT");
        }
    };
    private static final Migration MIGRATION_26_27 = new Migration() { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase$Companion$MIGRATION_26_27$1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) throws SQLException {
            Intrinsics.checkNotNullParameter(database, "database");
            database.execSQL("ALTER TABLE location_tracking ADD COLUMN public_id TEXT NOT NULL DEFAULT ''");
        }
    };

    public abstract CustomDataTemplateDAO CustomDataTemplateDAO();

    public abstract AssigmentDAO assigmentDAO();

    public abstract AssignmentInboxDAO assignmentInboxDao();

    public abstract AssignmentSubmitVersionDAO assignmentSubmitVersionDAO();

    public abstract AssignmentUpdateListingDAO assignmentUpdateListingDao();

    public abstract AssignmentUploadDAO assignmentUploadDao();

    public abstract AssignmentsDao assignmentsDao();

    public abstract AssignmentBlokSensusDAO assignmetnBlokSensusDao();

    public abstract AssignmentRegionDAO assignmetnRegionDAO();

    public abstract CustomColumnDAO customColumnDAO();

    public abstract FieldOfficerDAO fieldOfficerDAO();

    public abstract LocationTrackingDAO locationTrackingDAO();

    public abstract NewAssignmentsDAO newAssignmentsDao();

    public abstract PeriodeDAONew periodeNewDao();

    public abstract SamplingRegionDAO samplingRegionDAO();

    public abstract SurveyDAONew surveyNewDao();

    public abstract SurveyTimeDAO surveyTimeDao();

    public abstract TarikSampleDAO tarikSampleDAO();

    public abstract TemplateValidationDAO templateValidasiDAO();

    public abstract UpdateListingDAO updateListingDao();

    public abstract UserDAO userDAO();

    /* compiled from: database.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020;R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0011\u0010\u001b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0011\u0010\u001f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\bR\u0011\u0010%\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u0011\u0010'\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\bR\u0011\u0010)\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\bR\u0011\u0010+\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\bR\u0011\u0010-\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\bR\u0011\u0010/\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\bR\u0011\u00101\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\bR\u0011\u00103\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\bR\u0011\u00105\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\bR\u0011\u00107\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\b¨\u0006<"}, d2 = {"Lid/go/bpsfasih/data/local/databaseRoom/SurveyRoomDatabase$Companion;", "", "()V", "INSTANCE", "Lid/go/bpsfasih/data/local/databaseRoom/SurveyRoomDatabase;", "MIGRATION_10_11", "Landroidx/room/migration/Migration;", "getMIGRATION_10_11", "()Landroidx/room/migration/Migration;", "MIGRATION_11_12", "getMIGRATION_11_12", "MIGRATION_12_13", "getMIGRATION_12_13", "MIGRATION_13_14", "getMIGRATION_13_14", "MIGRATION_14_15", "getMIGRATION_14_15", "MIGRATION_15_16", "getMIGRATION_15_16", "MIGRATION_16_17", "getMIGRATION_16_17", "MIGRATION_17_18", "getMIGRATION_17_18", "MIGRATION_18_19", "getMIGRATION_18_19", "MIGRATION_19_20", "getMIGRATION_19_20", "MIGRATION_1_2", "getMIGRATION_1_2", "MIGRATION_20_21", "getMIGRATION_20_21", "MIGRATION_21_22", "getMIGRATION_21_22", "MIGRATION_22_23", "getMIGRATION_22_23", "MIGRATION_23_24", "getMIGRATION_23_24", "MIGRATION_24_25", "getMIGRATION_24_25", "MIGRATION_25_26", "getMIGRATION_25_26", "MIGRATION_26_27", "getMIGRATION_26_27", "MIGRATION_3_4", "getMIGRATION_3_4", "MIGRATION_4_5", "getMIGRATION_4_5", "MIGRATION_5_6", "getMIGRATION_5_6", "MIGRATION_6_7", "getMIGRATION_6_7", "MIGRATION_7_8", "getMIGRATION_7_8", "MIGRATION_8_9", "getMIGRATION_8_9", "MIGRATION_9_10", "getMIGRATION_9_10", "getDatabase", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Migration getMIGRATION_1_2() {
            return SurveyRoomDatabase.MIGRATION_1_2;
        }

        public final Migration getMIGRATION_3_4() {
            return SurveyRoomDatabase.MIGRATION_3_4;
        }

        public final Migration getMIGRATION_4_5() {
            return SurveyRoomDatabase.MIGRATION_4_5;
        }

        public final Migration getMIGRATION_5_6() {
            return SurveyRoomDatabase.MIGRATION_5_6;
        }

        public final Migration getMIGRATION_6_7() {
            return SurveyRoomDatabase.MIGRATION_6_7;
        }

        public final Migration getMIGRATION_7_8() {
            return SurveyRoomDatabase.MIGRATION_7_8;
        }

        public final Migration getMIGRATION_8_9() {
            return SurveyRoomDatabase.MIGRATION_8_9;
        }

        public final Migration getMIGRATION_9_10() {
            return SurveyRoomDatabase.MIGRATION_9_10;
        }

        public final Migration getMIGRATION_10_11() {
            return SurveyRoomDatabase.MIGRATION_10_11;
        }

        public final Migration getMIGRATION_11_12() {
            return SurveyRoomDatabase.MIGRATION_11_12;
        }

        public final Migration getMIGRATION_12_13() {
            return SurveyRoomDatabase.MIGRATION_12_13;
        }

        public final Migration getMIGRATION_13_14() {
            return SurveyRoomDatabase.MIGRATION_13_14;
        }

        public final Migration getMIGRATION_14_15() {
            return SurveyRoomDatabase.MIGRATION_14_15;
        }

        public final Migration getMIGRATION_15_16() {
            return SurveyRoomDatabase.MIGRATION_15_16;
        }

        public final Migration getMIGRATION_16_17() {
            return SurveyRoomDatabase.MIGRATION_16_17;
        }

        public final Migration getMIGRATION_17_18() {
            return SurveyRoomDatabase.MIGRATION_17_18;
        }

        public final Migration getMIGRATION_18_19() {
            return SurveyRoomDatabase.MIGRATION_18_19;
        }

        public final Migration getMIGRATION_19_20() {
            return SurveyRoomDatabase.MIGRATION_19_20;
        }

        public final Migration getMIGRATION_20_21() {
            return SurveyRoomDatabase.MIGRATION_20_21;
        }

        public final Migration getMIGRATION_21_22() {
            return SurveyRoomDatabase.MIGRATION_21_22;
        }

        public final Migration getMIGRATION_22_23() {
            return SurveyRoomDatabase.MIGRATION_22_23;
        }

        public final Migration getMIGRATION_23_24() {
            return SurveyRoomDatabase.MIGRATION_23_24;
        }

        public final Migration getMIGRATION_24_25() {
            return SurveyRoomDatabase.MIGRATION_24_25;
        }

        public final Migration getMIGRATION_25_26() {
            return SurveyRoomDatabase.MIGRATION_25_26;
        }

        public final Migration getMIGRATION_26_27() {
            return SurveyRoomDatabase.MIGRATION_26_27;
        }

        public final SurveyRoomDatabase getDatabase(Context context) {
            SurveyRoomDatabase surveyRoomDatabase;
            Intrinsics.checkNotNullParameter(context, "context");
            SurveyRoomDatabase surveyRoomDatabase2 = SurveyRoomDatabase.INSTANCE;
            if (surveyRoomDatabase2 != null) {
                return surveyRoomDatabase2;
            }
            synchronized (this) {
                RoomDatabase roomDatabaseBuild = Room.databaseBuilder(context.getApplicationContext(), SurveyRoomDatabase.class, "Survey_database").addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_1_2()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_3_4()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_4_5()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_5_6()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_6_7()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_7_8()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_8_9()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_9_10()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_10_11()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_11_12()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_12_13()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_13_14()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_14_15()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_15_16()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_16_17()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_17_18()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_18_19()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_19_20()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_20_21()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_21_22()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_22_23()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_23_24()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_24_25()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_25_26()).addMigrations(SurveyRoomDatabase.INSTANCE.getMIGRATION_26_27()).fallbackToDestructiveMigration().build();
                Intrinsics.checkNotNullExpressionValue(roomDatabaseBuild, "databaseBuilder(\n       …                 .build()");
                surveyRoomDatabase = (SurveyRoomDatabase) roomDatabaseBuild;
                Companion companion = SurveyRoomDatabase.INSTANCE;
                SurveyRoomDatabase.INSTANCE = surveyRoomDatabase;
            }
            return surveyRoomDatabase;
        }
    }
}
