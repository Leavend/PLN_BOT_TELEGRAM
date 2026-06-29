package id.go.bpsfasih.data.local.databaseRoom;

import android.database.SQLException;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.google.firebase.database.core.ServerValues;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.dao.AssigmentDAO_Impl;
import id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO;
import id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO_Impl;
import id.go.bpsfasih.data.local.dao.AssignmentInboxDAO;
import id.go.bpsfasih.data.local.dao.AssignmentInboxDAO_Impl;
import id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO;
import id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO_Impl;
import id.go.bpsfasih.data.local.dao.AssignmentUploadDAO;
import id.go.bpsfasih.data.local.dao.AssignmentUploadDAO_Impl;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO_Impl;
import id.go.bpsfasih.data.local.dao.CustomDataTemplateDAO;
import id.go.bpsfasih.data.local.dao.CustomDataTemplateDAO_Impl;
import id.go.bpsfasih.data.local.dao.FieldOfficerDAO;
import id.go.bpsfasih.data.local.dao.FieldOfficerDAO_Impl;
import id.go.bpsfasih.data.local.dao.LocationTrackingDAO;
import id.go.bpsfasih.data.local.dao.LocationTrackingDAO_Impl;
import id.go.bpsfasih.data.local.dao.PeriodeDAONew;
import id.go.bpsfasih.data.local.dao.PeriodeDAONew_Impl;
import id.go.bpsfasih.data.local.dao.SamplingRegionDAO;
import id.go.bpsfasih.data.local.dao.SamplingRegionDAO_Impl;
import id.go.bpsfasih.data.local.dao.SurveyDAONew;
import id.go.bpsfasih.data.local.dao.SurveyDAONew_Impl;
import id.go.bpsfasih.data.local.dao.SurveyTimeDAO;
import id.go.bpsfasih.data.local.dao.SurveyTimeDAO_Impl;
import id.go.bpsfasih.data.local.dao.TarikSampleDAO;
import id.go.bpsfasih.data.local.dao.TarikSampleDAO_Impl;
import id.go.bpsfasih.data.local.dao.TemplateValidationDAO;
import id.go.bpsfasih.data.local.dao.TemplateValidationDAO_Impl;
import id.go.bpsfasih.data.local.dao.UserDAO;
import id.go.bpsfasih.data.local.dao.UserDAO_Impl;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO_Impl;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentUpdateListingDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentUpdateListingDAO_Impl;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentsDao;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentsDao_Impl;
import id.go.bpsfasih.data.local.dao.regionDao.NewAssignmentsDAO;
import id.go.bpsfasih.data.local.dao.regionDao.NewAssignmentsDAO_Impl;
import id.go.bpsfasih.data.local.dao.regionDao.UpdateListingDAO;
import id.go.bpsfasih.data.local.dao.regionDao.UpdateListingDAO_Impl;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.cookie.ClientCookie;

/* loaded from: classes2.dex */
public final class SurveyRoomDatabase_Impl extends SurveyRoomDatabase {
    private volatile AssigmentDAO _assigmentDAO;
    private volatile AssignmentBlokSensusDAO _assignmentBlokSensusDAO;
    private volatile AssignmentInboxDAO _assignmentInboxDAO;
    private volatile AssignmentRegionDAO _assignmentRegionDAO;
    private volatile AssignmentSubmitVersionDAO _assignmentSubmitVersionDAO;
    private volatile AssignmentUpdateListingDAO _assignmentUpdateListingDAO;
    private volatile AssignmentUploadDAO _assignmentUploadDAO;
    private volatile AssignmentsDao _assignmentsDao;
    private volatile CustomColumnDAO _customColumnDAO;
    private volatile CustomDataTemplateDAO _customDataTemplateDAO;
    private volatile FieldOfficerDAO _fieldOfficerDAO;
    private volatile LocationTrackingDAO _locationTrackingDAO;
    private volatile NewAssignmentsDAO _newAssignmentsDAO;
    private volatile PeriodeDAONew _periodeDAONew;
    private volatile SamplingRegionDAO _samplingRegionDAO;
    private volatile SurveyDAONew _surveyDAONew;
    private volatile SurveyTimeDAO _surveyTimeDAO;
    private volatile TarikSampleDAO _tarikSampleDAO;
    private volatile TemplateValidationDAO _templateValidationDAO;
    private volatile UpdateListingDAO _updateListingDAO;
    private volatile UserDAO _userDAO;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(27) { // from class: id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) throws SQLException {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_assignment_entity` (`assignment_id_primary` TEXT NOT NULL, `periodeId` TEXT NOT NULL, `periodeNotPrimary` TEXT NOT NULL, `assignment_id` TEXT NOT NULL, `userIdAssignment` TEXT, `preDefinedData` TEXT, `assignmentStatusId` INTEGER, `cawiToken` TEXT, `cawiPin` TEXT, `tarikSample` INTEGER NOT NULL, `isNew` INTEGER NOT NULL, `otherStatus` INTEGER NOT NULL, `pendingStatus` INTEGER NOT NULL, `parentUser` TEXT, `surveyId` TEXT NOT NULL, `surveyIdNotPrimary` TEXT NOT NULL, `provinceId` TEXT, `provinceName` TEXT, `provinceCode` TEXT, `regencyId` TEXT, `regencyName` TEXT, `regencyCode` TEXT, `districtId` TEXT, `districtName` TEXT, `districtFullcode` TEXT, `villageId` TEXT, `villageName` TEXT, `villageFullcode` TEXT, `blokSensusId` TEXT, `blokSensusFullCode` TEXT, `data1` TEXT, `data2` TEXT, `data3` TEXT, `data4` TEXT, `data5` TEXT, `sum_error` INTEGER, `sum_remark` INTEGER, `sum_clean` INTEGER, `latitude` REAL, `longitude` REAL, `strata` TEXT, `currentUserId` TEXT, `currentUserUsername` TEXT, `currentUserFullname` TEXT, `currentUserSurveyRoleId` TEXT, `currentUserSurveyRoleName` TEXT, `currentUserSurveyRoleIsPencacah` INTEGER, `currentUserSurveyRoleCanPullSample` INTEGER, `offlineSend` INTEGER NOT NULL, `responsibility` TEXT, `assignmentResponsibility` TEXT, `assignmentHistories` TEXT, `is_inside_blok_sensus` INTEGER NOT NULL, `latitude_if_outside` REAL, `longitude_if_outside` REAL, `keterangan_validasi` TEXT, `isDone` INTEGER, `secondary` INTEGER, `copyFromId` TEXT, `original` INTEGER NOT NULL, `paradata` TEXT, `data6` TEXT, `data7` TEXT, `data8` TEXT, `data9` TEXT, `data10` TEXT, `mode` TEXT, `comment` TEXT, `basePathComment` TEXT, `assignmentStatusAlias` TEXT, `note` TEXT, `is_encrypt` INTEGER NOT NULL DEFAULT 0, `basePath` TEXT, `data_downloaded_at` TEXT, `submitVersionCode` INTEGER NOT NULL DEFAULT 0, `data_bangunanSensus` TEXT, `data_sls_number` TEXT, `data_address` TEXT, `data_householdCode` TEXT, `data_rutaId` TEXT, `data_name` TEXT, `data_bangunanFisik` TEXT, `data_isLiveIn` INTEGER, `data_address_number` TEXT, `data_strata` TEXT, `data_prop` TEXT, `data_kab_reg` TEXT, `data_kec_dist` TEXT, `data_desa_village` TEXT, `data_blok` TEXT, `data_cawi_identifier` TEXT, `data_cawi_pin` TEXT, `data_provinsi` TEXT, `data_kabupatenKota` TEXT, `data_kecamatan` TEXT, `data_data` TEXT, `data_desa` TEXT, `data_alamat` TEXT, `data_namaDusun` TEXT, `data_rtRw` TEXT, `data_nomorKk` TEXT, `data_namaKepalaKeluarga` TEXT, `data_latitude` TEXT, `data_longitude` TEXT, `regionMetadata_levelCount` INTEGER, `regionMetadata_smallestRegionLevel` TEXT, `regionMetadata_groupName` TEXT, `regionMetadata_level` TEXT, `regionMetadata_id` TEXT, `region_dateCreated` INTEGER, `region_groupId` TEXT, `region_active` INTEGER, `region_id` TEXT, `region_versionCode` REAL, `region_level1_fullCode` TEXT, `region_level1_code` TEXT, `region_level1_name` TEXT, `region_level1_id` TEXT, `region_level1_level2_fullCode` TEXT, `region_level1_level2_code` TEXT, `region_level1_level2_name` TEXT, `region_level1_level2_id` TEXT, `region_level1_level2_level3_fullCode` TEXT, `region_level1_level2_level3_code` TEXT, `region_level1_level2_level3_name` TEXT, `region_level1_level2_level3_id` TEXT, `region_level1_level2_level3_level4_fullCode` TEXT, `region_level1_level2_level3_level4_code` TEXT, `region_level1_level2_level3_level4_name` TEXT, `region_level1_level2_level3_level4_id` TEXT, `region_level1_level2_level3_level4_level5_fullCode` TEXT, `region_level1_level2_level3_level4_level5_code` TEXT, `region_level1_level2_level3_level4_level5_name` TEXT, `region_level1_level2_level3_level4_level5_id` TEXT, `region_level1_level2_level3_level4_level5_level6_fullCode` TEXT, `region_level1_level2_level3_level4_level5_level6_code` TEXT, `region_level1_level2_level3_level4_level5_level6_name` TEXT, `region_level1_level2_level3_level4_level5_level6_id` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_fullCode` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_code` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_name` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_id` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_fullCode` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_code` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_name` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_id` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_fullCode` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_code` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_name` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_id` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_fullCode` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_code` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_name` TEXT, `region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_id` TEXT, PRIMARY KEY(`assignment_id_primary`, `periodeId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `user_table` (`id` INTEGER NOT NULL, `username` TEXT NOT NULL, `fullname` TEXT NOT NULL, `email` TEXT, `dateCreatedUser` INTEGER, `tempFlag` INTEGER NOT NULL, `activeUser` INTEGER NOT NULL, `bpsUsers` INTEGER NOT NULL, `password` TEXT NOT NULL, `user__roleId` INTEGER, `user__roleName` TEXT, `user__roleType` INTEGER, `user__roleActive` INTEGER NOT NULL, `user__regCode` TEXT, `user__regDateCreated` INTEGER, `user__regName` TEXT, `user__regActive` INTEGER, `user__regId` INTEGER, `user__reg_provinceId` INTEGER, `user__reg_provinceName` TEXT, `user__reg_provinceCode` TEXT, `user__reg_provinceDateCreated` INTEGER, `user__reg_provinceActive` INTEGER, `user__provinceId` INTEGER, `user__provinceName` TEXT, `user__provinceCode` TEXT, `user__provinceDateCreated` INTEGER, `user__provinceActive` INTEGER NOT NULL, `user__nonBpsUserId` INTEGER, `user__password` TEXT, `eselon2` TEXT, `eselon3` TEXT, `bpsUserId` INTEGER, `jabatan` TEXT, `kodeJabatan` TEXT, `kodeOrg` TEXT, `nipBaru` TEXT, `nipLama` TEXT, `org` TEXT, `passwordBpsUser` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_user_table_id` ON `user_table` (`id`)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_survey_time` (`survey_time_id` INTEGER PRIMARY KEY AUTOINCREMENT, `user_id` TEXT, `survey_time_assignment_id` TEXT, `status` INTEGER NOT NULL)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `field_officer` (`id` TEXT NOT NULL, `nik` TEXT, `username` TEXT, `email` TEXT, `firstName` TEXT, `lastName` TEXT, `fullname` TEXT, `pob` TEXT, `dob` TEXT, `gender` TEXT, `religionId` TEXT, `phoneNumber` TEXT, `phoneNumber2` TEXT, `regencyId` TEXT, `regencyName` TEXT, `postalCode` TEXT, `address` TEXT, `maritalStatusId` TEXT, `recentEducationId` TEXT, `roleName` TEXT, `provinceId` TEXT, `provinceName` TEXT NOT NULL, `isSynced` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_survey_new_table` (`survey_id_primary` TEXT NOT NULL, `survey_id` TEXT NOT NULL, `survey_active` INTEGER NOT NULL, `areaType` INTEGER NOT NULL, `canAddSample` INTEGER NOT NULL, `cawiTokenAutoGenerated` INTEGER NOT NULL, `createdDate` TEXT NOT NULL, `geoAccuracy` INTEGER NOT NULL, `geoLiveTracking` INTEGER NOT NULL, `geoRadius` INTEGER NOT NULL, `imageServiceType` INTEGER NOT NULL, `imageServiceUrl` TEXT, `survey_name` TEXT NOT NULL, `panelType` INTEGER NOT NULL, `surveyParentId` TEXT, `surveySampleMethodId` TEXT, `surveySampleTypeId` TEXT, `surveyStepTypeId` TEXT NOT NULL, `surveyTemplateId` TEXT, `surveyTemplateListingId` TEXT, `updateListingType` INTEGER NOT NULL, `survey_user_id` TEXT, `isSelected` INTEGER NOT NULL, `tarikSampleExclusive` INTEGER, `engineIcs2` INTEGER NOT NULL, `isMultiPencacah` INTEGER, `regionGroupId` TEXT, `backgroundRecord` INTEGER, `smallestRegionType` INTEGER, `templateLookup` TEXT, `surveyModes` TEXT, `is_pin` INTEGER NOT NULL, `survey__id` TEXT, `survey__name` TEXT, `survey__userId` TEXT, `survey__primaryCount` INTEGER, `survey__criteriaWhere` TEXT, `survey__criteriaOrderBy` TEXT, `survey__method` TEXT, `survey__methodType` INTEGER, `survey__countType` INTEGER, `survey__criteriaWhereMobile` TEXT, `survey__criteriaOrderByMobile` TEXT, `survey__dateCreated` INTEGER, PRIMARY KEY(`survey_id_primary`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_table_periode_new` (`periode_id_primary` TEXT NOT NULL, `periode_id` TEXT NOT NULL, `periode_name` TEXT, `userIdPeriode` TEXT, `periode_created_date` TEXT, `periode_start_date` TEXT, `endDate` TEXT, `pencacah` INTEGER, `role` TEXT, `periode_lookup_id` TEXT, `surveyPeriodeRoleUserId` TEXT, `jsMethod` TEXT, `sampleTargetSurveyPeriodeId` TEXT, `listSmallestRegionFullCode` TEXT, `sampleTargetSurveyPeriodeIdCsv` TEXT, `isActive` INTEGER, `survey_survey_id_primary` TEXT NOT NULL, `survey_survey_id` TEXT NOT NULL, `survey_survey_active` INTEGER NOT NULL, `survey_areaType` INTEGER NOT NULL, `survey_canAddSample` INTEGER NOT NULL, `survey_cawiTokenAutoGenerated` INTEGER NOT NULL, `survey_createdDate` TEXT NOT NULL, `survey_geoAccuracy` INTEGER NOT NULL, `survey_geoLiveTracking` INTEGER NOT NULL, `survey_geoRadius` INTEGER NOT NULL, `survey_imageServiceType` INTEGER NOT NULL, `survey_imageServiceUrl` TEXT, `survey_survey_name` TEXT NOT NULL, `survey_panelType` INTEGER NOT NULL, `survey_surveyParentId` TEXT, `survey_surveySampleMethodId` TEXT, `survey_surveySampleTypeId` TEXT, `survey_surveyStepTypeId` TEXT NOT NULL, `survey_surveyTemplateId` TEXT, `survey_surveyTemplateListingId` TEXT, `survey_updateListingType` INTEGER NOT NULL, `survey_survey_user_id` TEXT, `survey_isSelected` INTEGER NOT NULL, `survey_tarikSampleExclusive` INTEGER, `survey_engineIcs2` INTEGER NOT NULL, `survey_isMultiPencacah` INTEGER, `survey_regionGroupId` TEXT, `survey_backgroundRecord` INTEGER, `survey_smallestRegionType` INTEGER, `survey_templateLookup` TEXT, `survey_surveyModes` TEXT, `survey_is_pin` INTEGER NOT NULL, `survey_survey__id` TEXT, `survey_survey__name` TEXT, `survey_survey__userId` TEXT, `survey_survey__primaryCount` INTEGER, `survey_survey__criteriaWhere` TEXT, `survey_survey__criteriaOrderBy` TEXT, `survey_survey__method` TEXT, `survey_survey__methodType` INTEGER, `survey_survey__countType` INTEGER, `survey_survey__criteriaWhereMobile` TEXT, `survey_survey__criteriaOrderByMobile` TEXT, `survey_survey__dateCreated` INTEGER, PRIMARY KEY(`periode_id_primary`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_custom_template` (`idPrimary` TEXT NOT NULL, `id` TEXT NOT NULL, `userId` TEXT NOT NULL, `data1_dataKey` TEXT, `data1_caption` TEXT, `data1_visible` INTEGER, `data1_answer` TEXT, `data1_principal` TEXT, `data1_columnName` TEXT, `data2_dataKey` TEXT, `data2_caption` TEXT, `data2_visible` INTEGER, `data2_answer` TEXT, `data2_principal` TEXT, `data2_columnName` TEXT, `data3_dataKey` TEXT, `data3_caption` TEXT, `data3_visible` INTEGER, `data3_answer` TEXT, `data3_principal` TEXT, `data3_columnName` TEXT, `data4_dataKey` TEXT, `data4_caption` TEXT, `data4_visible` INTEGER, `data4_answer` TEXT, `data4_principal` TEXT, `data4_columnName` TEXT, `data5_dataKey` TEXT, `data5_caption` TEXT, `data5_visible` INTEGER, `data5_answer` TEXT, `data5_principal` TEXT, `data5_columnName` TEXT, `data6_dataKey` TEXT, `data6_caption` TEXT, `data6_visible` INTEGER, `data6_answer` TEXT, `data6_principal` TEXT, `data6_columnName` TEXT, `data7_dataKey` TEXT, `data7_caption` TEXT, `data7_visible` INTEGER, `data7_answer` TEXT, `data7_principal` TEXT, `data7_columnName` TEXT, `data8_dataKey` TEXT, `data8_caption` TEXT, `data8_visible` INTEGER, `data8_answer` TEXT, `data8_principal` TEXT, `data8_columnName` TEXT, `data9_dataKey` TEXT, `data9_caption` TEXT, `data9_visible` INTEGER, `data9_answer` TEXT, `data9_principal` TEXT, `data9_columnName` TEXT, `data10_dataKey` TEXT, `data10_caption` TEXT, `data10_visible` INTEGER, `data10_answer` TEXT, `data10_principal` TEXT, `data10_columnName` TEXT, PRIMARY KEY(`idPrimary`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `assignment_blok_sensus` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `blokSensusId` TEXT, `surveyPeriodeId` TEXT, `doneListing` INTEGER, `doneTarikSample` INTEGER, `doneTarikSampleOffline` INTEGER, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `assignment_region` (`_id` TEXT NOT NULL, `userId` TEXT NOT NULL, `region_id` TEXT, `region_group_id` TEXT, `smallest_region_full_code` TEXT, `survey_period_id` TEXT, `done_listing` INTEGER, `done_tarik_sample` INTEGER, `done_tarik_sample_offline` INTEGER, `state_data_table` TEXT, `region` TEXT, `regionMetadata` TEXT, PRIMARY KEY(`_id`, `userId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `data_template_validation` (`survey_id` TEXT NOT NULL, `userId` TEXT NOT NULL, `template_id` TEXT NOT NULL, `template_version` TEXT, `validasi_version` TEXT, `form_engine_id` INTEGER NOT NULL, `form_engine_brand_name` TEXT, PRIMARY KEY(`survey_id`, `userId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `custom_column_entity` (`idPrimary` TEXT NOT NULL, `id` TEXT NOT NULL, `userId` TEXT NOT NULL, `data1` INTEGER NOT NULL, `data2` INTEGER NOT NULL, `data3` INTEGER NOT NULL, `data4` INTEGER NOT NULL, `data5` INTEGER NOT NULL, `data6` INTEGER NOT NULL, `data7` INTEGER NOT NULL, `data8` INTEGER NOT NULL, `data9` INTEGER NOT NULL, `data10` INTEGER NOT NULL, PRIMARY KEY(`idPrimary`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sampling_offline` (`id` TEXT NOT NULL, `survey_periode_source_id` TEXT NOT NULL, `survey_periode_target_id` TEXT NOT NULL, `script_id` TEXT NOT NULL, `script_sampling` TEXT, `source_schema` TEXT, `target_schema` TEXT, `source` TEXT, `target` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `sampling_region` (`id` TEXT NOT NULL, `survey_periode_id` TEXT NOT NULL, `fullcode` TEXT NOT NULL, `mode` TEXT NOT NULL, `status` TEXT, `is_done` INTEGER, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `assignment_uploads` (`id` TEXT NOT NULL, `userId` TEXT NOT NULL, `templateId` TEXT, `data1` TEXT, `data2` TEXT, `data3` TEXT, `data4` TEXT, `data5` TEXT, `data6` TEXT, `data7` TEXT, `data8` TEXT, `data9` TEXT, `data10` TEXT, `labelData1` TEXT, `labelData2` TEXT, `labelData3` TEXT, `labelData4` TEXT, `labelData5` TEXT, `labelData6` TEXT, `labelData7` TEXT, `labelData8` TEXT, `labelData9` TEXT, `labelData10` TEXT, `is_upload_successful` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `assignment_submit_version` (`assignment_id` TEXT NOT NULL, `submit_version_code` INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(`assignment_id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `location_tracking` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `public_id` TEXT NOT NULL, `user_id` TEXT NOT NULL, `assignment_id` TEXT, `survey_periode_id` TEXT, `activity` TEXT NOT NULL, `session` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `timestamp` INTEGER NOT NULL, `accuracy` REAL, `date` TEXT NOT NULL, `is_synced` INTEGER NOT NULL)");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_location_tracking_user_id_date` ON `location_tracking` (`user_id`, `date`)");
                _db.execSQL("CREATE INDEX IF NOT EXISTS `index_location_tracking_assignment_id` ON `location_tracking` (`assignment_id`)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '9290568fe3f53b561f31c138d2a6ae6f')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) throws SQLException {
                _db.execSQL("DROP TABLE IF EXISTS `data_assignment_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `user_table`");
                _db.execSQL("DROP TABLE IF EXISTS `data_survey_time`");
                _db.execSQL("DROP TABLE IF EXISTS `field_officer`");
                _db.execSQL("DROP TABLE IF EXISTS `data_survey_new_table`");
                _db.execSQL("DROP TABLE IF EXISTS `data_table_periode_new`");
                _db.execSQL("DROP TABLE IF EXISTS `data_custom_template`");
                _db.execSQL("DROP TABLE IF EXISTS `assignment_blok_sensus`");
                _db.execSQL("DROP TABLE IF EXISTS `assignment_region`");
                _db.execSQL("DROP TABLE IF EXISTS `data_template_validation`");
                _db.execSQL("DROP TABLE IF EXISTS `custom_column_entity`");
                _db.execSQL("DROP TABLE IF EXISTS `sampling_offline`");
                _db.execSQL("DROP TABLE IF EXISTS `sampling_region`");
                _db.execSQL("DROP TABLE IF EXISTS `assignment_uploads`");
                _db.execSQL("DROP TABLE IF EXISTS `assignment_submit_version`");
                _db.execSQL("DROP TABLE IF EXISTS `location_tracking`");
                if (SurveyRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = SurveyRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) SurveyRoomDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (SurveyRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = SurveyRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) SurveyRoomDatabase_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                SurveyRoomDatabase_Impl.this.mDatabase = _db;
                SurveyRoomDatabase_Impl.this.internalInitInvalidationTracker(_db);
                if (SurveyRoomDatabase_Impl.this.mCallbacks != null) {
                    int size = SurveyRoomDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) SurveyRoomDatabase_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) throws SQLException {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(154);
                map.put("assignment_id_primary", new TableInfo.Column("assignment_id_primary", "TEXT", true, 1, null, 1));
                map.put("periodeId", new TableInfo.Column("periodeId", "TEXT", true, 2, null, 1));
                map.put("periodeNotPrimary", new TableInfo.Column("periodeNotPrimary", "TEXT", true, 0, null, 1));
                map.put("assignment_id", new TableInfo.Column("assignment_id", "TEXT", true, 0, null, 1));
                map.put("userIdAssignment", new TableInfo.Column("userIdAssignment", "TEXT", false, 0, null, 1));
                map.put("preDefinedData", new TableInfo.Column("preDefinedData", "TEXT", false, 0, null, 1));
                map.put("assignmentStatusId", new TableInfo.Column("assignmentStatusId", "INTEGER", false, 0, null, 1));
                map.put("cawiToken", new TableInfo.Column("cawiToken", "TEXT", false, 0, null, 1));
                map.put("cawiPin", new TableInfo.Column("cawiPin", "TEXT", false, 0, null, 1));
                map.put("tarikSample", new TableInfo.Column("tarikSample", "INTEGER", true, 0, null, 1));
                map.put("isNew", new TableInfo.Column("isNew", "INTEGER", true, 0, null, 1));
                map.put("otherStatus", new TableInfo.Column("otherStatus", "INTEGER", true, 0, null, 1));
                map.put("pendingStatus", new TableInfo.Column("pendingStatus", "INTEGER", true, 0, null, 1));
                map.put("parentUser", new TableInfo.Column("parentUser", "TEXT", false, 0, null, 1));
                map.put("surveyId", new TableInfo.Column("surveyId", "TEXT", true, 0, null, 1));
                map.put("surveyIdNotPrimary", new TableInfo.Column("surveyIdNotPrimary", "TEXT", true, 0, null, 1));
                map.put("provinceId", new TableInfo.Column("provinceId", "TEXT", false, 0, null, 1));
                map.put("provinceName", new TableInfo.Column("provinceName", "TEXT", false, 0, null, 1));
                map.put("provinceCode", new TableInfo.Column("provinceCode", "TEXT", false, 0, null, 1));
                map.put("regencyId", new TableInfo.Column("regencyId", "TEXT", false, 0, null, 1));
                map.put("regencyName", new TableInfo.Column("regencyName", "TEXT", false, 0, null, 1));
                map.put("regencyCode", new TableInfo.Column("regencyCode", "TEXT", false, 0, null, 1));
                map.put("districtId", new TableInfo.Column("districtId", "TEXT", false, 0, null, 1));
                map.put("districtName", new TableInfo.Column("districtName", "TEXT", false, 0, null, 1));
                map.put("districtFullcode", new TableInfo.Column("districtFullcode", "TEXT", false, 0, null, 1));
                map.put("villageId", new TableInfo.Column("villageId", "TEXT", false, 0, null, 1));
                map.put("villageName", new TableInfo.Column("villageName", "TEXT", false, 0, null, 1));
                map.put("villageFullcode", new TableInfo.Column("villageFullcode", "TEXT", false, 0, null, 1));
                map.put("blokSensusId", new TableInfo.Column("blokSensusId", "TEXT", false, 0, null, 1));
                map.put("blokSensusFullCode", new TableInfo.Column("blokSensusFullCode", "TEXT", false, 0, null, 1));
                map.put("data1", new TableInfo.Column("data1", "TEXT", false, 0, null, 1));
                map.put("data2", new TableInfo.Column("data2", "TEXT", false, 0, null, 1));
                map.put("data3", new TableInfo.Column("data3", "TEXT", false, 0, null, 1));
                map.put("data4", new TableInfo.Column("data4", "TEXT", false, 0, null, 1));
                map.put("data5", new TableInfo.Column("data5", "TEXT", false, 0, null, 1));
                map.put("sum_error", new TableInfo.Column("sum_error", "INTEGER", false, 0, null, 1));
                map.put("sum_remark", new TableInfo.Column("sum_remark", "INTEGER", false, 0, null, 1));
                map.put("sum_clean", new TableInfo.Column("sum_clean", "INTEGER", false, 0, null, 1));
                map.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, 1));
                map.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, 1));
                map.put("strata", new TableInfo.Column("strata", "TEXT", false, 0, null, 1));
                map.put("currentUserId", new TableInfo.Column("currentUserId", "TEXT", false, 0, null, 1));
                map.put("currentUserUsername", new TableInfo.Column("currentUserUsername", "TEXT", false, 0, null, 1));
                map.put("currentUserFullname", new TableInfo.Column("currentUserFullname", "TEXT", false, 0, null, 1));
                map.put("currentUserSurveyRoleId", new TableInfo.Column("currentUserSurveyRoleId", "TEXT", false, 0, null, 1));
                map.put("currentUserSurveyRoleName", new TableInfo.Column("currentUserSurveyRoleName", "TEXT", false, 0, null, 1));
                map.put("currentUserSurveyRoleIsPencacah", new TableInfo.Column("currentUserSurveyRoleIsPencacah", "INTEGER", false, 0, null, 1));
                map.put("currentUserSurveyRoleCanPullSample", new TableInfo.Column("currentUserSurveyRoleCanPullSample", "INTEGER", false, 0, null, 1));
                map.put("offlineSend", new TableInfo.Column("offlineSend", "INTEGER", true, 0, null, 1));
                map.put("responsibility", new TableInfo.Column("responsibility", "TEXT", false, 0, null, 1));
                map.put("assignmentResponsibility", new TableInfo.Column("assignmentResponsibility", "TEXT", false, 0, null, 1));
                map.put("assignmentHistories", new TableInfo.Column("assignmentHistories", "TEXT", false, 0, null, 1));
                map.put("is_inside_blok_sensus", new TableInfo.Column("is_inside_blok_sensus", "INTEGER", true, 0, null, 1));
                map.put("latitude_if_outside", new TableInfo.Column("latitude_if_outside", "REAL", false, 0, null, 1));
                map.put("longitude_if_outside", new TableInfo.Column("longitude_if_outside", "REAL", false, 0, null, 1));
                map.put("keterangan_validasi", new TableInfo.Column("keterangan_validasi", "TEXT", false, 0, null, 1));
                map.put("isDone", new TableInfo.Column("isDone", "INTEGER", false, 0, null, 1));
                map.put("secondary", new TableInfo.Column("secondary", "INTEGER", false, 0, null, 1));
                map.put("copyFromId", new TableInfo.Column("copyFromId", "TEXT", false, 0, null, 1));
                map.put("original", new TableInfo.Column("original", "INTEGER", true, 0, null, 1));
                map.put("paradata", new TableInfo.Column("paradata", "TEXT", false, 0, null, 1));
                map.put("data6", new TableInfo.Column("data6", "TEXT", false, 0, null, 1));
                map.put("data7", new TableInfo.Column("data7", "TEXT", false, 0, null, 1));
                map.put("data8", new TableInfo.Column("data8", "TEXT", false, 0, null, 1));
                map.put("data9", new TableInfo.Column("data9", "TEXT", false, 0, null, 1));
                map.put("data10", new TableInfo.Column("data10", "TEXT", false, 0, null, 1));
                map.put("mode", new TableInfo.Column("mode", "TEXT", false, 0, null, 1));
                map.put(ClientCookie.COMMENT_ATTR, new TableInfo.Column(ClientCookie.COMMENT_ATTR, "TEXT", false, 0, null, 1));
                map.put("basePathComment", new TableInfo.Column("basePathComment", "TEXT", false, 0, null, 1));
                map.put("assignmentStatusAlias", new TableInfo.Column("assignmentStatusAlias", "TEXT", false, 0, null, 1));
                map.put(FormGearJavascriptInterface.NOTE_FILE, new TableInfo.Column(FormGearJavascriptInterface.NOTE_FILE, "TEXT", false, 0, null, 1));
                map.put("is_encrypt", new TableInfo.Column("is_encrypt", "INTEGER", true, 0, "0", 1));
                map.put("basePath", new TableInfo.Column("basePath", "TEXT", false, 0, null, 1));
                map.put("data_downloaded_at", new TableInfo.Column("data_downloaded_at", "TEXT", false, 0, null, 1));
                map.put("submitVersionCode", new TableInfo.Column("submitVersionCode", "INTEGER", true, 0, "0", 1));
                map.put("data_bangunanSensus", new TableInfo.Column("data_bangunanSensus", "TEXT", false, 0, null, 1));
                map.put("data_sls_number", new TableInfo.Column("data_sls_number", "TEXT", false, 0, null, 1));
                map.put("data_address", new TableInfo.Column("data_address", "TEXT", false, 0, null, 1));
                map.put("data_householdCode", new TableInfo.Column("data_householdCode", "TEXT", false, 0, null, 1));
                map.put("data_rutaId", new TableInfo.Column("data_rutaId", "TEXT", false, 0, null, 1));
                map.put("data_name", new TableInfo.Column("data_name", "TEXT", false, 0, null, 1));
                map.put("data_bangunanFisik", new TableInfo.Column("data_bangunanFisik", "TEXT", false, 0, null, 1));
                map.put("data_isLiveIn", new TableInfo.Column("data_isLiveIn", "INTEGER", false, 0, null, 1));
                map.put("data_address_number", new TableInfo.Column("data_address_number", "TEXT", false, 0, null, 1));
                map.put("data_strata", new TableInfo.Column("data_strata", "TEXT", false, 0, null, 1));
                map.put("data_prop", new TableInfo.Column("data_prop", "TEXT", false, 0, null, 1));
                map.put("data_kab_reg", new TableInfo.Column("data_kab_reg", "TEXT", false, 0, null, 1));
                map.put("data_kec_dist", new TableInfo.Column("data_kec_dist", "TEXT", false, 0, null, 1));
                map.put("data_desa_village", new TableInfo.Column("data_desa_village", "TEXT", false, 0, null, 1));
                map.put("data_blok", new TableInfo.Column("data_blok", "TEXT", false, 0, null, 1));
                map.put("data_cawi_identifier", new TableInfo.Column("data_cawi_identifier", "TEXT", false, 0, null, 1));
                map.put("data_cawi_pin", new TableInfo.Column("data_cawi_pin", "TEXT", false, 0, null, 1));
                map.put("data_provinsi", new TableInfo.Column("data_provinsi", "TEXT", false, 0, null, 1));
                map.put("data_kabupatenKota", new TableInfo.Column("data_kabupatenKota", "TEXT", false, 0, null, 1));
                map.put("data_kecamatan", new TableInfo.Column("data_kecamatan", "TEXT", false, 0, null, 1));
                map.put("data_data", new TableInfo.Column("data_data", "TEXT", false, 0, null, 1));
                map.put("data_desa", new TableInfo.Column("data_desa", "TEXT", false, 0, null, 1));
                map.put("data_alamat", new TableInfo.Column("data_alamat", "TEXT", false, 0, null, 1));
                map.put("data_namaDusun", new TableInfo.Column("data_namaDusun", "TEXT", false, 0, null, 1));
                map.put("data_rtRw", new TableInfo.Column("data_rtRw", "TEXT", false, 0, null, 1));
                map.put("data_nomorKk", new TableInfo.Column("data_nomorKk", "TEXT", false, 0, null, 1));
                map.put("data_namaKepalaKeluarga", new TableInfo.Column("data_namaKepalaKeluarga", "TEXT", false, 0, null, 1));
                map.put("data_latitude", new TableInfo.Column("data_latitude", "TEXT", false, 0, null, 1));
                map.put("data_longitude", new TableInfo.Column("data_longitude", "TEXT", false, 0, null, 1));
                map.put("regionMetadata_levelCount", new TableInfo.Column("regionMetadata_levelCount", "INTEGER", false, 0, null, 1));
                map.put("regionMetadata_smallestRegionLevel", new TableInfo.Column("regionMetadata_smallestRegionLevel", "TEXT", false, 0, null, 1));
                map.put("regionMetadata_groupName", new TableInfo.Column("regionMetadata_groupName", "TEXT", false, 0, null, 1));
                map.put("regionMetadata_level", new TableInfo.Column("regionMetadata_level", "TEXT", false, 0, null, 1));
                map.put("regionMetadata_id", new TableInfo.Column("regionMetadata_id", "TEXT", false, 0, null, 1));
                map.put("region_dateCreated", new TableInfo.Column("region_dateCreated", "INTEGER", false, 0, null, 1));
                map.put("region_groupId", new TableInfo.Column("region_groupId", "TEXT", false, 0, null, 1));
                map.put("region_active", new TableInfo.Column("region_active", "INTEGER", false, 0, null, 1));
                map.put("region_id", new TableInfo.Column("region_id", "TEXT", false, 0, null, 1));
                map.put("region_versionCode", new TableInfo.Column("region_versionCode", "REAL", false, 0, null, 1));
                map.put("region_level1_fullCode", new TableInfo.Column("region_level1_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_code", new TableInfo.Column("region_level1_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_name", new TableInfo.Column("region_level1_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_id", new TableInfo.Column("region_level1_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_fullCode", new TableInfo.Column("region_level1_level2_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_code", new TableInfo.Column("region_level1_level2_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_name", new TableInfo.Column("region_level1_level2_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_id", new TableInfo.Column("region_level1_level2_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_fullCode", new TableInfo.Column("region_level1_level2_level3_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_code", new TableInfo.Column("region_level1_level2_level3_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_name", new TableInfo.Column("region_level1_level2_level3_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_id", new TableInfo.Column("region_level1_level2_level3_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_code", new TableInfo.Column("region_level1_level2_level3_level4_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_name", new TableInfo.Column("region_level1_level2_level3_level4_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_id", new TableInfo.Column("region_level1_level2_level3_level4_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_id", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_fullCode", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_fullCode", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_code", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_code", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_name", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_name", "TEXT", false, 0, null, 1));
                map.put("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_id", new TableInfo.Column("region_level1_level2_level3_level4_level5_level6_level7_level8_level9_level10_id", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("data_assignment_entity", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "data_assignment_entity");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_assignment_entity(id.go.bpsfasih.data.local.entities.AssignmentEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(40);
                map2.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "INTEGER", true, 1, null, 1));
                map2.put(HintConstants.AUTOFILL_HINT_USERNAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_USERNAME, "TEXT", true, 0, null, 1));
                map2.put("fullname", new TableInfo.Column("fullname", "TEXT", true, 0, null, 1));
                map2.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, 1));
                map2.put("dateCreatedUser", new TableInfo.Column("dateCreatedUser", "INTEGER", false, 0, null, 1));
                map2.put("tempFlag", new TableInfo.Column("tempFlag", "INTEGER", true, 0, null, 1));
                map2.put("activeUser", new TableInfo.Column("activeUser", "INTEGER", true, 0, null, 1));
                map2.put("bpsUsers", new TableInfo.Column("bpsUsers", "INTEGER", true, 0, null, 1));
                map2.put(HintConstants.AUTOFILL_HINT_PASSWORD, new TableInfo.Column(HintConstants.AUTOFILL_HINT_PASSWORD, "TEXT", true, 0, null, 1));
                map2.put("user__roleId", new TableInfo.Column("user__roleId", "INTEGER", false, 0, null, 1));
                map2.put("user__roleName", new TableInfo.Column("user__roleName", "TEXT", false, 0, null, 1));
                map2.put("user__roleType", new TableInfo.Column("user__roleType", "INTEGER", false, 0, null, 1));
                map2.put("user__roleActive", new TableInfo.Column("user__roleActive", "INTEGER", true, 0, null, 1));
                map2.put("user__regCode", new TableInfo.Column("user__regCode", "TEXT", false, 0, null, 1));
                map2.put("user__regDateCreated", new TableInfo.Column("user__regDateCreated", "INTEGER", false, 0, null, 1));
                map2.put("user__regName", new TableInfo.Column("user__regName", "TEXT", false, 0, null, 1));
                map2.put("user__regActive", new TableInfo.Column("user__regActive", "INTEGER", false, 0, null, 1));
                map2.put("user__regId", new TableInfo.Column("user__regId", "INTEGER", false, 0, null, 1));
                map2.put("user__reg_provinceId", new TableInfo.Column("user__reg_provinceId", "INTEGER", false, 0, null, 1));
                map2.put("user__reg_provinceName", new TableInfo.Column("user__reg_provinceName", "TEXT", false, 0, null, 1));
                map2.put("user__reg_provinceCode", new TableInfo.Column("user__reg_provinceCode", "TEXT", false, 0, null, 1));
                map2.put("user__reg_provinceDateCreated", new TableInfo.Column("user__reg_provinceDateCreated", "INTEGER", false, 0, null, 1));
                map2.put("user__reg_provinceActive", new TableInfo.Column("user__reg_provinceActive", "INTEGER", false, 0, null, 1));
                map2.put("user__provinceId", new TableInfo.Column("user__provinceId", "INTEGER", false, 0, null, 1));
                map2.put("user__provinceName", new TableInfo.Column("user__provinceName", "TEXT", false, 0, null, 1));
                map2.put("user__provinceCode", new TableInfo.Column("user__provinceCode", "TEXT", false, 0, null, 1));
                map2.put("user__provinceDateCreated", new TableInfo.Column("user__provinceDateCreated", "INTEGER", false, 0, null, 1));
                map2.put("user__provinceActive", new TableInfo.Column("user__provinceActive", "INTEGER", true, 0, null, 1));
                map2.put("user__nonBpsUserId", new TableInfo.Column("user__nonBpsUserId", "INTEGER", false, 0, null, 1));
                map2.put("user__password", new TableInfo.Column("user__password", "TEXT", false, 0, null, 1));
                map2.put("eselon2", new TableInfo.Column("eselon2", "TEXT", false, 0, null, 1));
                map2.put("eselon3", new TableInfo.Column("eselon3", "TEXT", false, 0, null, 1));
                map2.put("bpsUserId", new TableInfo.Column("bpsUserId", "INTEGER", false, 0, null, 1));
                map2.put("jabatan", new TableInfo.Column("jabatan", "TEXT", false, 0, null, 1));
                map2.put("kodeJabatan", new TableInfo.Column("kodeJabatan", "TEXT", false, 0, null, 1));
                map2.put("kodeOrg", new TableInfo.Column("kodeOrg", "TEXT", false, 0, null, 1));
                map2.put("nipBaru", new TableInfo.Column("nipBaru", "TEXT", false, 0, null, 1));
                map2.put("nipLama", new TableInfo.Column("nipLama", "TEXT", false, 0, null, 1));
                map2.put("org", new TableInfo.Column("org", "TEXT", false, 0, null, 1));
                map2.put("passwordBpsUser", new TableInfo.Column("passwordBpsUser", "TEXT", false, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_user_table_id", false, Arrays.asList(DownloadModel.ID), Arrays.asList("ASC")));
                TableInfo tableInfo3 = new TableInfo("user_table", map2, hashSet, hashSet2);
                TableInfo tableInfo4 = TableInfo.read(_db, "user_table");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "user_table(id.go.bpsfasih.data.local.entities.UserEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(4);
                map3.put("survey_time_id", new TableInfo.Column("survey_time_id", "INTEGER", false, 1, null, 1));
                map3.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0, null, 1));
                map3.put("survey_time_assignment_id", new TableInfo.Column("survey_time_assignment_id", "TEXT", false, 0, null, 1));
                map3.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("data_survey_time", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(_db, "data_survey_time");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_survey_time(id.go.bpsfasih.data.local.entities.SurveyTimeEntity).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(23);
                map4.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 1, null, 1));
                map4.put("nik", new TableInfo.Column("nik", "TEXT", false, 0, null, 1));
                map4.put(HintConstants.AUTOFILL_HINT_USERNAME, new TableInfo.Column(HintConstants.AUTOFILL_HINT_USERNAME, "TEXT", false, 0, null, 1));
                map4.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, 1));
                map4.put("firstName", new TableInfo.Column("firstName", "TEXT", false, 0, null, 1));
                map4.put("lastName", new TableInfo.Column("lastName", "TEXT", false, 0, null, 1));
                map4.put("fullname", new TableInfo.Column("fullname", "TEXT", false, 0, null, 1));
                map4.put("pob", new TableInfo.Column("pob", "TEXT", false, 0, null, 1));
                map4.put("dob", new TableInfo.Column("dob", "TEXT", false, 0, null, 1));
                map4.put(HintConstants.AUTOFILL_HINT_GENDER, new TableInfo.Column(HintConstants.AUTOFILL_HINT_GENDER, "TEXT", false, 0, null, 1));
                map4.put("religionId", new TableInfo.Column("religionId", "TEXT", false, 0, null, 1));
                map4.put(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, new TableInfo.Column(HintConstants.AUTOFILL_HINT_PHONE_NUMBER, "TEXT", false, 0, null, 1));
                map4.put("phoneNumber2", new TableInfo.Column("phoneNumber2", "TEXT", false, 0, null, 1));
                map4.put("regencyId", new TableInfo.Column("regencyId", "TEXT", false, 0, null, 1));
                map4.put("regencyName", new TableInfo.Column("regencyName", "TEXT", false, 0, null, 1));
                map4.put(HintConstants.AUTOFILL_HINT_POSTAL_CODE, new TableInfo.Column(HintConstants.AUTOFILL_HINT_POSTAL_CODE, "TEXT", false, 0, null, 1));
                map4.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, 1));
                map4.put("maritalStatusId", new TableInfo.Column("maritalStatusId", "TEXT", false, 0, null, 1));
                map4.put("recentEducationId", new TableInfo.Column("recentEducationId", "TEXT", false, 0, null, 1));
                map4.put("roleName", new TableInfo.Column("roleName", "TEXT", false, 0, null, 1));
                map4.put("provinceId", new TableInfo.Column("provinceId", "TEXT", false, 0, null, 1));
                map4.put("provinceName", new TableInfo.Column("provinceName", "TEXT", true, 0, null, 1));
                map4.put("isSynced", new TableInfo.Column("isSynced", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("field_officer", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(_db, "field_officer");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "field_officer(id.go.bpsfasih.data.local.entities.FieldOfficerEntity).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(44);
                map5.put("survey_id_primary", new TableInfo.Column("survey_id_primary", "TEXT", true, 1, null, 1));
                map5.put("survey_id", new TableInfo.Column("survey_id", "TEXT", true, 0, null, 1));
                map5.put("survey_active", new TableInfo.Column("survey_active", "INTEGER", true, 0, null, 1));
                map5.put("areaType", new TableInfo.Column("areaType", "INTEGER", true, 0, null, 1));
                map5.put("canAddSample", new TableInfo.Column("canAddSample", "INTEGER", true, 0, null, 1));
                map5.put("cawiTokenAutoGenerated", new TableInfo.Column("cawiTokenAutoGenerated", "INTEGER", true, 0, null, 1));
                map5.put("createdDate", new TableInfo.Column("createdDate", "TEXT", true, 0, null, 1));
                map5.put("geoAccuracy", new TableInfo.Column("geoAccuracy", "INTEGER", true, 0, null, 1));
                map5.put("geoLiveTracking", new TableInfo.Column("geoLiveTracking", "INTEGER", true, 0, null, 1));
                map5.put("geoRadius", new TableInfo.Column("geoRadius", "INTEGER", true, 0, null, 1));
                map5.put("imageServiceType", new TableInfo.Column("imageServiceType", "INTEGER", true, 0, null, 1));
                map5.put("imageServiceUrl", new TableInfo.Column("imageServiceUrl", "TEXT", false, 0, null, 1));
                map5.put("survey_name", new TableInfo.Column("survey_name", "TEXT", true, 0, null, 1));
                map5.put("panelType", new TableInfo.Column("panelType", "INTEGER", true, 0, null, 1));
                map5.put("surveyParentId", new TableInfo.Column("surveyParentId", "TEXT", false, 0, null, 1));
                map5.put("surveySampleMethodId", new TableInfo.Column("surveySampleMethodId", "TEXT", false, 0, null, 1));
                map5.put("surveySampleTypeId", new TableInfo.Column("surveySampleTypeId", "TEXT", false, 0, null, 1));
                map5.put("surveyStepTypeId", new TableInfo.Column("surveyStepTypeId", "TEXT", true, 0, null, 1));
                map5.put("surveyTemplateId", new TableInfo.Column("surveyTemplateId", "TEXT", false, 0, null, 1));
                map5.put("surveyTemplateListingId", new TableInfo.Column("surveyTemplateListingId", "TEXT", false, 0, null, 1));
                map5.put("updateListingType", new TableInfo.Column("updateListingType", "INTEGER", true, 0, null, 1));
                map5.put("survey_user_id", new TableInfo.Column("survey_user_id", "TEXT", false, 0, null, 1));
                map5.put("isSelected", new TableInfo.Column("isSelected", "INTEGER", true, 0, null, 1));
                map5.put("tarikSampleExclusive", new TableInfo.Column("tarikSampleExclusive", "INTEGER", false, 0, null, 1));
                map5.put("engineIcs2", new TableInfo.Column("engineIcs2", "INTEGER", true, 0, null, 1));
                map5.put("isMultiPencacah", new TableInfo.Column("isMultiPencacah", "INTEGER", false, 0, null, 1));
                map5.put("regionGroupId", new TableInfo.Column("regionGroupId", "TEXT", false, 0, null, 1));
                map5.put("backgroundRecord", new TableInfo.Column("backgroundRecord", "INTEGER", false, 0, null, 1));
                map5.put("smallestRegionType", new TableInfo.Column("smallestRegionType", "INTEGER", false, 0, null, 1));
                map5.put("templateLookup", new TableInfo.Column("templateLookup", "TEXT", false, 0, null, 1));
                map5.put("surveyModes", new TableInfo.Column("surveyModes", "TEXT", false, 0, null, 1));
                map5.put("is_pin", new TableInfo.Column("is_pin", "INTEGER", true, 0, null, 1));
                map5.put("survey__id", new TableInfo.Column("survey__id", "TEXT", false, 0, null, 1));
                map5.put("survey__name", new TableInfo.Column("survey__name", "TEXT", false, 0, null, 1));
                map5.put("survey__userId", new TableInfo.Column("survey__userId", "TEXT", false, 0, null, 1));
                map5.put("survey__primaryCount", new TableInfo.Column("survey__primaryCount", "INTEGER", false, 0, null, 1));
                map5.put("survey__criteriaWhere", new TableInfo.Column("survey__criteriaWhere", "TEXT", false, 0, null, 1));
                map5.put("survey__criteriaOrderBy", new TableInfo.Column("survey__criteriaOrderBy", "TEXT", false, 0, null, 1));
                map5.put("survey__method", new TableInfo.Column("survey__method", "TEXT", false, 0, null, 1));
                map5.put("survey__methodType", new TableInfo.Column("survey__methodType", "INTEGER", false, 0, null, 1));
                map5.put("survey__countType", new TableInfo.Column("survey__countType", "INTEGER", false, 0, null, 1));
                map5.put("survey__criteriaWhereMobile", new TableInfo.Column("survey__criteriaWhereMobile", "TEXT", false, 0, null, 1));
                map5.put("survey__criteriaOrderByMobile", new TableInfo.Column("survey__criteriaOrderByMobile", "TEXT", false, 0, null, 1));
                map5.put("survey__dateCreated", new TableInfo.Column("survey__dateCreated", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo9 = new TableInfo("data_survey_new_table", map5, new HashSet(0), new HashSet(0));
                TableInfo tableInfo10 = TableInfo.read(_db, "data_survey_new_table");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_survey_new_table(id.go.bpsfasih.data.local.entities.SurveyEntity).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                HashMap map6 = new HashMap(60);
                map6.put("periode_id_primary", new TableInfo.Column("periode_id_primary", "TEXT", true, 1, null, 1));
                map6.put("periode_id", new TableInfo.Column("periode_id", "TEXT", true, 0, null, 1));
                map6.put("periode_name", new TableInfo.Column("periode_name", "TEXT", false, 0, null, 1));
                map6.put("userIdPeriode", new TableInfo.Column("userIdPeriode", "TEXT", false, 0, null, 1));
                map6.put("periode_created_date", new TableInfo.Column("periode_created_date", "TEXT", false, 0, null, 1));
                map6.put("periode_start_date", new TableInfo.Column("periode_start_date", "TEXT", false, 0, null, 1));
                map6.put("endDate", new TableInfo.Column("endDate", "TEXT", false, 0, null, 1));
                map6.put("pencacah", new TableInfo.Column("pencacah", "INTEGER", false, 0, null, 1));
                map6.put("role", new TableInfo.Column("role", "TEXT", false, 0, null, 1));
                map6.put("periode_lookup_id", new TableInfo.Column("periode_lookup_id", "TEXT", false, 0, null, 1));
                map6.put("surveyPeriodeRoleUserId", new TableInfo.Column("surveyPeriodeRoleUserId", "TEXT", false, 0, null, 1));
                map6.put("jsMethod", new TableInfo.Column("jsMethod", "TEXT", false, 0, null, 1));
                map6.put("sampleTargetSurveyPeriodeId", new TableInfo.Column("sampleTargetSurveyPeriodeId", "TEXT", false, 0, null, 1));
                map6.put("listSmallestRegionFullCode", new TableInfo.Column("listSmallestRegionFullCode", "TEXT", false, 0, null, 1));
                map6.put("sampleTargetSurveyPeriodeIdCsv", new TableInfo.Column("sampleTargetSurveyPeriodeIdCsv", "TEXT", false, 0, null, 1));
                map6.put("isActive", new TableInfo.Column("isActive", "INTEGER", false, 0, null, 1));
                map6.put("survey_survey_id_primary", new TableInfo.Column("survey_survey_id_primary", "TEXT", true, 0, null, 1));
                map6.put("survey_survey_id", new TableInfo.Column("survey_survey_id", "TEXT", true, 0, null, 1));
                map6.put("survey_survey_active", new TableInfo.Column("survey_survey_active", "INTEGER", true, 0, null, 1));
                map6.put("survey_areaType", new TableInfo.Column("survey_areaType", "INTEGER", true, 0, null, 1));
                map6.put("survey_canAddSample", new TableInfo.Column("survey_canAddSample", "INTEGER", true, 0, null, 1));
                map6.put("survey_cawiTokenAutoGenerated", new TableInfo.Column("survey_cawiTokenAutoGenerated", "INTEGER", true, 0, null, 1));
                map6.put("survey_createdDate", new TableInfo.Column("survey_createdDate", "TEXT", true, 0, null, 1));
                map6.put("survey_geoAccuracy", new TableInfo.Column("survey_geoAccuracy", "INTEGER", true, 0, null, 1));
                map6.put("survey_geoLiveTracking", new TableInfo.Column("survey_geoLiveTracking", "INTEGER", true, 0, null, 1));
                map6.put("survey_geoRadius", new TableInfo.Column("survey_geoRadius", "INTEGER", true, 0, null, 1));
                map6.put("survey_imageServiceType", new TableInfo.Column("survey_imageServiceType", "INTEGER", true, 0, null, 1));
                map6.put("survey_imageServiceUrl", new TableInfo.Column("survey_imageServiceUrl", "TEXT", false, 0, null, 1));
                map6.put("survey_survey_name", new TableInfo.Column("survey_survey_name", "TEXT", true, 0, null, 1));
                map6.put("survey_panelType", new TableInfo.Column("survey_panelType", "INTEGER", true, 0, null, 1));
                map6.put("survey_surveyParentId", new TableInfo.Column("survey_surveyParentId", "TEXT", false, 0, null, 1));
                map6.put("survey_surveySampleMethodId", new TableInfo.Column("survey_surveySampleMethodId", "TEXT", false, 0, null, 1));
                map6.put("survey_surveySampleTypeId", new TableInfo.Column("survey_surveySampleTypeId", "TEXT", false, 0, null, 1));
                map6.put("survey_surveyStepTypeId", new TableInfo.Column("survey_surveyStepTypeId", "TEXT", true, 0, null, 1));
                map6.put("survey_surveyTemplateId", new TableInfo.Column("survey_surveyTemplateId", "TEXT", false, 0, null, 1));
                map6.put("survey_surveyTemplateListingId", new TableInfo.Column("survey_surveyTemplateListingId", "TEXT", false, 0, null, 1));
                map6.put("survey_updateListingType", new TableInfo.Column("survey_updateListingType", "INTEGER", true, 0, null, 1));
                map6.put("survey_survey_user_id", new TableInfo.Column("survey_survey_user_id", "TEXT", false, 0, null, 1));
                map6.put("survey_isSelected", new TableInfo.Column("survey_isSelected", "INTEGER", true, 0, null, 1));
                map6.put("survey_tarikSampleExclusive", new TableInfo.Column("survey_tarikSampleExclusive", "INTEGER", false, 0, null, 1));
                map6.put("survey_engineIcs2", new TableInfo.Column("survey_engineIcs2", "INTEGER", true, 0, null, 1));
                map6.put("survey_isMultiPencacah", new TableInfo.Column("survey_isMultiPencacah", "INTEGER", false, 0, null, 1));
                map6.put("survey_regionGroupId", new TableInfo.Column("survey_regionGroupId", "TEXT", false, 0, null, 1));
                map6.put("survey_backgroundRecord", new TableInfo.Column("survey_backgroundRecord", "INTEGER", false, 0, null, 1));
                map6.put("survey_smallestRegionType", new TableInfo.Column("survey_smallestRegionType", "INTEGER", false, 0, null, 1));
                map6.put("survey_templateLookup", new TableInfo.Column("survey_templateLookup", "TEXT", false, 0, null, 1));
                map6.put("survey_surveyModes", new TableInfo.Column("survey_surveyModes", "TEXT", false, 0, null, 1));
                map6.put("survey_is_pin", new TableInfo.Column("survey_is_pin", "INTEGER", true, 0, null, 1));
                map6.put("survey_survey__id", new TableInfo.Column("survey_survey__id", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__name", new TableInfo.Column("survey_survey__name", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__userId", new TableInfo.Column("survey_survey__userId", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__primaryCount", new TableInfo.Column("survey_survey__primaryCount", "INTEGER", false, 0, null, 1));
                map6.put("survey_survey__criteriaWhere", new TableInfo.Column("survey_survey__criteriaWhere", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__criteriaOrderBy", new TableInfo.Column("survey_survey__criteriaOrderBy", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__method", new TableInfo.Column("survey_survey__method", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__methodType", new TableInfo.Column("survey_survey__methodType", "INTEGER", false, 0, null, 1));
                map6.put("survey_survey__countType", new TableInfo.Column("survey_survey__countType", "INTEGER", false, 0, null, 1));
                map6.put("survey_survey__criteriaWhereMobile", new TableInfo.Column("survey_survey__criteriaWhereMobile", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__criteriaOrderByMobile", new TableInfo.Column("survey_survey__criteriaOrderByMobile", "TEXT", false, 0, null, 1));
                map6.put("survey_survey__dateCreated", new TableInfo.Column("survey_survey__dateCreated", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo11 = new TableInfo("data_table_periode_new", map6, new HashSet(0), new HashSet(0));
                TableInfo tableInfo12 = TableInfo.read(_db, "data_table_periode_new");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_table_periode_new(id.go.bpsfasih.data.local.entities.PeriodeEntityNew).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                HashMap map7 = new HashMap(63);
                map7.put("idPrimary", new TableInfo.Column("idPrimary", "TEXT", true, 1, null, 1));
                map7.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 0, null, 1));
                map7.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, 1));
                map7.put("data1_dataKey", new TableInfo.Column("data1_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data1_caption", new TableInfo.Column("data1_caption", "TEXT", false, 0, null, 1));
                map7.put("data1_visible", new TableInfo.Column("data1_visible", "INTEGER", false, 0, null, 1));
                map7.put("data1_answer", new TableInfo.Column("data1_answer", "TEXT", false, 0, null, 1));
                map7.put("data1_principal", new TableInfo.Column("data1_principal", "TEXT", false, 0, null, 1));
                map7.put("data1_columnName", new TableInfo.Column("data1_columnName", "TEXT", false, 0, null, 1));
                map7.put("data2_dataKey", new TableInfo.Column("data2_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data2_caption", new TableInfo.Column("data2_caption", "TEXT", false, 0, null, 1));
                map7.put("data2_visible", new TableInfo.Column("data2_visible", "INTEGER", false, 0, null, 1));
                map7.put("data2_answer", new TableInfo.Column("data2_answer", "TEXT", false, 0, null, 1));
                map7.put("data2_principal", new TableInfo.Column("data2_principal", "TEXT", false, 0, null, 1));
                map7.put("data2_columnName", new TableInfo.Column("data2_columnName", "TEXT", false, 0, null, 1));
                map7.put("data3_dataKey", new TableInfo.Column("data3_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data3_caption", new TableInfo.Column("data3_caption", "TEXT", false, 0, null, 1));
                map7.put("data3_visible", new TableInfo.Column("data3_visible", "INTEGER", false, 0, null, 1));
                map7.put("data3_answer", new TableInfo.Column("data3_answer", "TEXT", false, 0, null, 1));
                map7.put("data3_principal", new TableInfo.Column("data3_principal", "TEXT", false, 0, null, 1));
                map7.put("data3_columnName", new TableInfo.Column("data3_columnName", "TEXT", false, 0, null, 1));
                map7.put("data4_dataKey", new TableInfo.Column("data4_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data4_caption", new TableInfo.Column("data4_caption", "TEXT", false, 0, null, 1));
                map7.put("data4_visible", new TableInfo.Column("data4_visible", "INTEGER", false, 0, null, 1));
                map7.put("data4_answer", new TableInfo.Column("data4_answer", "TEXT", false, 0, null, 1));
                map7.put("data4_principal", new TableInfo.Column("data4_principal", "TEXT", false, 0, null, 1));
                map7.put("data4_columnName", new TableInfo.Column("data4_columnName", "TEXT", false, 0, null, 1));
                map7.put("data5_dataKey", new TableInfo.Column("data5_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data5_caption", new TableInfo.Column("data5_caption", "TEXT", false, 0, null, 1));
                map7.put("data5_visible", new TableInfo.Column("data5_visible", "INTEGER", false, 0, null, 1));
                map7.put("data5_answer", new TableInfo.Column("data5_answer", "TEXT", false, 0, null, 1));
                map7.put("data5_principal", new TableInfo.Column("data5_principal", "TEXT", false, 0, null, 1));
                map7.put("data5_columnName", new TableInfo.Column("data5_columnName", "TEXT", false, 0, null, 1));
                map7.put("data6_dataKey", new TableInfo.Column("data6_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data6_caption", new TableInfo.Column("data6_caption", "TEXT", false, 0, null, 1));
                map7.put("data6_visible", new TableInfo.Column("data6_visible", "INTEGER", false, 0, null, 1));
                map7.put("data6_answer", new TableInfo.Column("data6_answer", "TEXT", false, 0, null, 1));
                map7.put("data6_principal", new TableInfo.Column("data6_principal", "TEXT", false, 0, null, 1));
                map7.put("data6_columnName", new TableInfo.Column("data6_columnName", "TEXT", false, 0, null, 1));
                map7.put("data7_dataKey", new TableInfo.Column("data7_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data7_caption", new TableInfo.Column("data7_caption", "TEXT", false, 0, null, 1));
                map7.put("data7_visible", new TableInfo.Column("data7_visible", "INTEGER", false, 0, null, 1));
                map7.put("data7_answer", new TableInfo.Column("data7_answer", "TEXT", false, 0, null, 1));
                map7.put("data7_principal", new TableInfo.Column("data7_principal", "TEXT", false, 0, null, 1));
                map7.put("data7_columnName", new TableInfo.Column("data7_columnName", "TEXT", false, 0, null, 1));
                map7.put("data8_dataKey", new TableInfo.Column("data8_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data8_caption", new TableInfo.Column("data8_caption", "TEXT", false, 0, null, 1));
                map7.put("data8_visible", new TableInfo.Column("data8_visible", "INTEGER", false, 0, null, 1));
                map7.put("data8_answer", new TableInfo.Column("data8_answer", "TEXT", false, 0, null, 1));
                map7.put("data8_principal", new TableInfo.Column("data8_principal", "TEXT", false, 0, null, 1));
                map7.put("data8_columnName", new TableInfo.Column("data8_columnName", "TEXT", false, 0, null, 1));
                map7.put("data9_dataKey", new TableInfo.Column("data9_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data9_caption", new TableInfo.Column("data9_caption", "TEXT", false, 0, null, 1));
                map7.put("data9_visible", new TableInfo.Column("data9_visible", "INTEGER", false, 0, null, 1));
                map7.put("data9_answer", new TableInfo.Column("data9_answer", "TEXT", false, 0, null, 1));
                map7.put("data9_principal", new TableInfo.Column("data9_principal", "TEXT", false, 0, null, 1));
                map7.put("data9_columnName", new TableInfo.Column("data9_columnName", "TEXT", false, 0, null, 1));
                map7.put("data10_dataKey", new TableInfo.Column("data10_dataKey", "TEXT", false, 0, null, 1));
                map7.put("data10_caption", new TableInfo.Column("data10_caption", "TEXT", false, 0, null, 1));
                map7.put("data10_visible", new TableInfo.Column("data10_visible", "INTEGER", false, 0, null, 1));
                map7.put("data10_answer", new TableInfo.Column("data10_answer", "TEXT", false, 0, null, 1));
                map7.put("data10_principal", new TableInfo.Column("data10_principal", "TEXT", false, 0, null, 1));
                map7.put("data10_columnName", new TableInfo.Column("data10_columnName", "TEXT", false, 0, null, 1));
                TableInfo tableInfo13 = new TableInfo("data_custom_template", map7, new HashSet(0), new HashSet(0));
                TableInfo tableInfo14 = TableInfo.read(_db, "data_custom_template");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_custom_template(id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                HashMap map8 = new HashMap(7);
                map8.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 1, null, 1));
                map8.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, 1));
                map8.put("blokSensusId", new TableInfo.Column("blokSensusId", "TEXT", false, 0, null, 1));
                map8.put("surveyPeriodeId", new TableInfo.Column("surveyPeriodeId", "TEXT", false, 0, null, 1));
                map8.put("doneListing", new TableInfo.Column("doneListing", "INTEGER", false, 0, null, 1));
                map8.put("doneTarikSample", new TableInfo.Column("doneTarikSample", "INTEGER", false, 0, null, 1));
                map8.put("doneTarikSampleOffline", new TableInfo.Column("doneTarikSampleOffline", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo15 = new TableInfo("assignment_blok_sensus", map8, new HashSet(0), new HashSet(0));
                TableInfo tableInfo16 = TableInfo.read(_db, "assignment_blok_sensus");
                if (!tableInfo15.equals(tableInfo16)) {
                    return new RoomOpenHelper.ValidationResult(false, "assignment_blok_sensus(id.go.bpsfasih.data.local.entities.AssignmentBlokSensus).\n Expected:\n" + tableInfo15 + "\n Found:\n" + tableInfo16);
                }
                HashMap map9 = new HashMap(12);
                map9.put("_id", new TableInfo.Column("_id", "TEXT", true, 1, null, 1));
                map9.put("userId", new TableInfo.Column("userId", "TEXT", true, 2, null, 1));
                map9.put("region_id", new TableInfo.Column("region_id", "TEXT", false, 0, null, 1));
                map9.put("region_group_id", new TableInfo.Column("region_group_id", "TEXT", false, 0, null, 1));
                map9.put("smallest_region_full_code", new TableInfo.Column("smallest_region_full_code", "TEXT", false, 0, null, 1));
                map9.put("survey_period_id", new TableInfo.Column("survey_period_id", "TEXT", false, 0, null, 1));
                map9.put("done_listing", new TableInfo.Column("done_listing", "INTEGER", false, 0, null, 1));
                map9.put("done_tarik_sample", new TableInfo.Column("done_tarik_sample", "INTEGER", false, 0, null, 1));
                map9.put("done_tarik_sample_offline", new TableInfo.Column("done_tarik_sample_offline", "INTEGER", false, 0, null, 1));
                map9.put("state_data_table", new TableInfo.Column("state_data_table", "TEXT", false, 0, null, 1));
                map9.put("region", new TableInfo.Column("region", "TEXT", false, 0, null, 1));
                map9.put("regionMetadata", new TableInfo.Column("regionMetadata", "TEXT", false, 0, null, 1));
                TableInfo tableInfo17 = new TableInfo("assignment_region", map9, new HashSet(0), new HashSet(0));
                TableInfo tableInfo18 = TableInfo.read(_db, "assignment_region");
                if (!tableInfo17.equals(tableInfo18)) {
                    return new RoomOpenHelper.ValidationResult(false, "assignment_region(id.go.bpsfasih.data.local.entities.AssignmentRegionEntity).\n Expected:\n" + tableInfo17 + "\n Found:\n" + tableInfo18);
                }
                HashMap map10 = new HashMap(7);
                map10.put("survey_id", new TableInfo.Column("survey_id", "TEXT", true, 1, null, 1));
                map10.put("userId", new TableInfo.Column("userId", "TEXT", true, 2, null, 1));
                map10.put("template_id", new TableInfo.Column("template_id", "TEXT", true, 0, null, 1));
                map10.put("template_version", new TableInfo.Column("template_version", "TEXT", false, 0, null, 1));
                map10.put("validasi_version", new TableInfo.Column("validasi_version", "TEXT", false, 0, null, 1));
                map10.put("form_engine_id", new TableInfo.Column("form_engine_id", "INTEGER", true, 0, null, 1));
                map10.put("form_engine_brand_name", new TableInfo.Column("form_engine_brand_name", "TEXT", false, 0, null, 1));
                TableInfo tableInfo19 = new TableInfo("data_template_validation", map10, new HashSet(0), new HashSet(0));
                TableInfo tableInfo20 = TableInfo.read(_db, "data_template_validation");
                if (!tableInfo19.equals(tableInfo20)) {
                    return new RoomOpenHelper.ValidationResult(false, "data_template_validation(id.go.bpsfasih.data.local.entities.TemplateValidationEntity).\n Expected:\n" + tableInfo19 + "\n Found:\n" + tableInfo20);
                }
                HashMap map11 = new HashMap(13);
                map11.put("idPrimary", new TableInfo.Column("idPrimary", "TEXT", true, 1, null, 1));
                map11.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 0, null, 1));
                map11.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, 1));
                map11.put("data1", new TableInfo.Column("data1", "INTEGER", true, 0, null, 1));
                map11.put("data2", new TableInfo.Column("data2", "INTEGER", true, 0, null, 1));
                map11.put("data3", new TableInfo.Column("data3", "INTEGER", true, 0, null, 1));
                map11.put("data4", new TableInfo.Column("data4", "INTEGER", true, 0, null, 1));
                map11.put("data5", new TableInfo.Column("data5", "INTEGER", true, 0, null, 1));
                map11.put("data6", new TableInfo.Column("data6", "INTEGER", true, 0, null, 1));
                map11.put("data7", new TableInfo.Column("data7", "INTEGER", true, 0, null, 1));
                map11.put("data8", new TableInfo.Column("data8", "INTEGER", true, 0, null, 1));
                map11.put("data9", new TableInfo.Column("data9", "INTEGER", true, 0, null, 1));
                map11.put("data10", new TableInfo.Column("data10", "INTEGER", true, 0, null, 1));
                TableInfo tableInfo21 = new TableInfo("custom_column_entity", map11, new HashSet(0), new HashSet(0));
                TableInfo tableInfo22 = TableInfo.read(_db, "custom_column_entity");
                if (!tableInfo21.equals(tableInfo22)) {
                    return new RoomOpenHelper.ValidationResult(false, "custom_column_entity(id.go.bpsfasih.data.local.entities.CustomColumnEntity).\n Expected:\n" + tableInfo21 + "\n Found:\n" + tableInfo22);
                }
                HashMap map12 = new HashMap(9);
                map12.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 1, null, 1));
                map12.put("survey_periode_source_id", new TableInfo.Column("survey_periode_source_id", "TEXT", true, 0, null, 1));
                map12.put("survey_periode_target_id", new TableInfo.Column("survey_periode_target_id", "TEXT", true, 0, null, 1));
                map12.put("script_id", new TableInfo.Column("script_id", "TEXT", true, 0, null, 1));
                map12.put("script_sampling", new TableInfo.Column("script_sampling", "TEXT", false, 0, null, 1));
                map12.put("source_schema", new TableInfo.Column("source_schema", "TEXT", false, 0, null, 1));
                map12.put("target_schema", new TableInfo.Column("target_schema", "TEXT", false, 0, null, 1));
                map12.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, 1));
                map12.put(TypedValues.AttributesType.S_TARGET, new TableInfo.Column(TypedValues.AttributesType.S_TARGET, "TEXT", false, 0, null, 1));
                TableInfo tableInfo23 = new TableInfo("sampling_offline", map12, new HashSet(0), new HashSet(0));
                TableInfo tableInfo24 = TableInfo.read(_db, "sampling_offline");
                if (!tableInfo23.equals(tableInfo24)) {
                    return new RoomOpenHelper.ValidationResult(false, "sampling_offline(id.go.bpsfasih.data.local.entities.TarikSampleEntity).\n Expected:\n" + tableInfo23 + "\n Found:\n" + tableInfo24);
                }
                HashMap map13 = new HashMap(6);
                map13.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 1, null, 1));
                map13.put("survey_periode_id", new TableInfo.Column("survey_periode_id", "TEXT", true, 0, null, 1));
                map13.put("fullcode", new TableInfo.Column("fullcode", "TEXT", true, 0, null, 1));
                map13.put("mode", new TableInfo.Column("mode", "TEXT", true, 0, null, 1));
                map13.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "TEXT", false, 0, null, 1));
                map13.put("is_done", new TableInfo.Column("is_done", "INTEGER", false, 0, null, 1));
                TableInfo tableInfo25 = new TableInfo("sampling_region", map13, new HashSet(0), new HashSet(0));
                TableInfo tableInfo26 = TableInfo.read(_db, "sampling_region");
                if (!tableInfo25.equals(tableInfo26)) {
                    return new RoomOpenHelper.ValidationResult(false, "sampling_region(id.go.bpsfasih.data.local.entities.SamplingRegionEntity).\n Expected:\n" + tableInfo25 + "\n Found:\n" + tableInfo26);
                }
                HashMap map14 = new HashMap(24);
                map14.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "TEXT", true, 1, null, 1));
                map14.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, 1));
                map14.put("templateId", new TableInfo.Column("templateId", "TEXT", false, 0, null, 1));
                map14.put("data1", new TableInfo.Column("data1", "TEXT", false, 0, null, 1));
                map14.put("data2", new TableInfo.Column("data2", "TEXT", false, 0, null, 1));
                map14.put("data3", new TableInfo.Column("data3", "TEXT", false, 0, null, 1));
                map14.put("data4", new TableInfo.Column("data4", "TEXT", false, 0, null, 1));
                map14.put("data5", new TableInfo.Column("data5", "TEXT", false, 0, null, 1));
                map14.put("data6", new TableInfo.Column("data6", "TEXT", false, 0, null, 1));
                map14.put("data7", new TableInfo.Column("data7", "TEXT", false, 0, null, 1));
                map14.put("data8", new TableInfo.Column("data8", "TEXT", false, 0, null, 1));
                map14.put("data9", new TableInfo.Column("data9", "TEXT", false, 0, null, 1));
                map14.put("data10", new TableInfo.Column("data10", "TEXT", false, 0, null, 1));
                map14.put("labelData1", new TableInfo.Column("labelData1", "TEXT", false, 0, null, 1));
                map14.put("labelData2", new TableInfo.Column("labelData2", "TEXT", false, 0, null, 1));
                map14.put("labelData3", new TableInfo.Column("labelData3", "TEXT", false, 0, null, 1));
                map14.put("labelData4", new TableInfo.Column("labelData4", "TEXT", false, 0, null, 1));
                map14.put("labelData5", new TableInfo.Column("labelData5", "TEXT", false, 0, null, 1));
                map14.put("labelData6", new TableInfo.Column("labelData6", "TEXT", false, 0, null, 1));
                map14.put("labelData7", new TableInfo.Column("labelData7", "TEXT", false, 0, null, 1));
                map14.put("labelData8", new TableInfo.Column("labelData8", "TEXT", false, 0, null, 1));
                map14.put("labelData9", new TableInfo.Column("labelData9", "TEXT", false, 0, null, 1));
                map14.put("labelData10", new TableInfo.Column("labelData10", "TEXT", false, 0, null, 1));
                map14.put("is_upload_successful", new TableInfo.Column("is_upload_successful", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo27 = new TableInfo("assignment_uploads", map14, new HashSet(0), new HashSet(0));
                TableInfo tableInfo28 = TableInfo.read(_db, "assignment_uploads");
                if (!tableInfo27.equals(tableInfo28)) {
                    return new RoomOpenHelper.ValidationResult(false, "assignment_uploads(id.go.bpsfasih.data.local.entities.AssignmentUploadEntity).\n Expected:\n" + tableInfo27 + "\n Found:\n" + tableInfo28);
                }
                HashMap map15 = new HashMap(2);
                map15.put("assignment_id", new TableInfo.Column("assignment_id", "TEXT", true, 1, null, 1));
                map15.put("submit_version_code", new TableInfo.Column("submit_version_code", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo29 = new TableInfo("assignment_submit_version", map15, new HashSet(0), new HashSet(0));
                TableInfo tableInfo30 = TableInfo.read(_db, "assignment_submit_version");
                if (!tableInfo29.equals(tableInfo30)) {
                    return new RoomOpenHelper.ValidationResult(false, "assignment_submit_version(id.go.bpsfasih.data.local.entities.AssignmentSubmitVersionEntity).\n Expected:\n" + tableInfo29 + "\n Found:\n" + tableInfo30);
                }
                HashMap map16 = new HashMap(13);
                map16.put(DownloadModel.ID, new TableInfo.Column(DownloadModel.ID, "INTEGER", true, 1, null, 1));
                map16.put("public_id", new TableInfo.Column("public_id", "TEXT", true, 0, null, 1));
                map16.put("user_id", new TableInfo.Column("user_id", "TEXT", true, 0, null, 1));
                map16.put("assignment_id", new TableInfo.Column("assignment_id", "TEXT", false, 0, null, 1));
                map16.put("survey_periode_id", new TableInfo.Column("survey_periode_id", "TEXT", false, 0, null, 1));
                map16.put("activity", new TableInfo.Column("activity", "TEXT", true, 0, null, 1));
                map16.put("session", new TableInfo.Column("session", "TEXT", true, 0, null, 1));
                map16.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, 1));
                map16.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, 1));
                map16.put(ServerValues.NAME_OP_TIMESTAMP, new TableInfo.Column(ServerValues.NAME_OP_TIMESTAMP, "INTEGER", true, 0, null, 1));
                map16.put("accuracy", new TableInfo.Column("accuracy", "REAL", false, 0, null, 1));
                map16.put("date", new TableInfo.Column("date", "TEXT", true, 0, null, 1));
                map16.put("is_synced", new TableInfo.Column("is_synced", "INTEGER", true, 0, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(2);
                hashSet4.add(new TableInfo.Index("index_location_tracking_user_id_date", false, Arrays.asList("user_id", "date"), Arrays.asList("ASC", "ASC")));
                hashSet4.add(new TableInfo.Index("index_location_tracking_assignment_id", false, Arrays.asList("assignment_id"), Arrays.asList("ASC")));
                TableInfo tableInfo31 = new TableInfo("location_tracking", map16, hashSet3, hashSet4);
                TableInfo tableInfo32 = TableInfo.read(_db, "location_tracking");
                if (!tableInfo31.equals(tableInfo32)) {
                    return new RoomOpenHelper.ValidationResult(false, "location_tracking(id.go.bpsfasih.data.local.entities.LocationTrackingEntity).\n Expected:\n" + tableInfo31 + "\n Found:\n" + tableInfo32);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "9290568fe3f53b561f31c138d2a6ae6f", "99cd167a3cd3ce867e5ec0d7f8939b27")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "data_assignment_entity", "user_table", "data_survey_time", "field_officer", "data_survey_new_table", "data_table_periode_new", "data_custom_template", "assignment_blok_sensus", "assignment_region", "data_template_validation", "custom_column_entity", "sampling_offline", "sampling_region", "assignment_uploads", "assignment_submit_version", "location_tracking");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() throws SQLException {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `data_assignment_entity`");
            writableDatabase.execSQL("DELETE FROM `user_table`");
            writableDatabase.execSQL("DELETE FROM `data_survey_time`");
            writableDatabase.execSQL("DELETE FROM `field_officer`");
            writableDatabase.execSQL("DELETE FROM `data_survey_new_table`");
            writableDatabase.execSQL("DELETE FROM `data_table_periode_new`");
            writableDatabase.execSQL("DELETE FROM `data_custom_template`");
            writableDatabase.execSQL("DELETE FROM `assignment_blok_sensus`");
            writableDatabase.execSQL("DELETE FROM `assignment_region`");
            writableDatabase.execSQL("DELETE FROM `data_template_validation`");
            writableDatabase.execSQL("DELETE FROM `custom_column_entity`");
            writableDatabase.execSQL("DELETE FROM `sampling_offline`");
            writableDatabase.execSQL("DELETE FROM `sampling_region`");
            writableDatabase.execSQL("DELETE FROM `assignment_uploads`");
            writableDatabase.execSQL("DELETE FROM `assignment_submit_version`");
            writableDatabase.execSQL("DELETE FROM `location_tracking`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(AssigmentDAO.class, AssigmentDAO_Impl.getRequiredConverters());
        map.put(UserDAO.class, UserDAO_Impl.getRequiredConverters());
        map.put(SurveyTimeDAO.class, SurveyTimeDAO_Impl.getRequiredConverters());
        map.put(AssignmentUploadDAO.class, AssignmentUploadDAO_Impl.getRequiredConverters());
        map.put(NewAssignmentsDAO.class, NewAssignmentsDAO_Impl.getRequiredConverters());
        map.put(AssignmentUpdateListingDAO.class, AssignmentUpdateListingDAO_Impl.getRequiredConverters());
        map.put(AssignmentsDao.class, AssignmentsDao_Impl.getRequiredConverters());
        map.put(UpdateListingDAO.class, UpdateListingDAO_Impl.getRequiredConverters());
        map.put(AssignmentInboxDAO.class, AssignmentInboxDAO_Impl.getRequiredConverters());
        map.put(SurveyDAONew.class, SurveyDAONew_Impl.getRequiredConverters());
        map.put(PeriodeDAONew.class, PeriodeDAONew_Impl.getRequiredConverters());
        map.put(FieldOfficerDAO.class, FieldOfficerDAO_Impl.getRequiredConverters());
        map.put(CustomDataTemplateDAO.class, CustomDataTemplateDAO_Impl.getRequiredConverters());
        map.put(AssignmentBlokSensusDAO.class, AssignmentBlokSensusDAO_Impl.getRequiredConverters());
        map.put(AssignmentRegionDAO.class, AssignmentRegionDAO_Impl.getRequiredConverters());
        map.put(TemplateValidationDAO.class, TemplateValidationDAO_Impl.getRequiredConverters());
        map.put(CustomColumnDAO.class, CustomColumnDAO_Impl.getRequiredConverters());
        map.put(AssignmentSubmitVersionDAO.class, AssignmentSubmitVersionDAO_Impl.getRequiredConverters());
        map.put(TarikSampleDAO.class, TarikSampleDAO_Impl.getRequiredConverters());
        map.put(SamplingRegionDAO.class, SamplingRegionDAO_Impl.getRequiredConverters());
        map.put(LocationTrackingDAO.class, LocationTrackingDAO_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssigmentDAO assigmentDAO() {
        AssigmentDAO assigmentDAO;
        if (this._assigmentDAO != null) {
            return this._assigmentDAO;
        }
        synchronized (this) {
            if (this._assigmentDAO == null) {
                this._assigmentDAO = new AssigmentDAO_Impl(this);
            }
            assigmentDAO = this._assigmentDAO;
        }
        return assigmentDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public UserDAO userDAO() {
        UserDAO userDAO;
        if (this._userDAO != null) {
            return this._userDAO;
        }
        synchronized (this) {
            if (this._userDAO == null) {
                this._userDAO = new UserDAO_Impl(this);
            }
            userDAO = this._userDAO;
        }
        return userDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public SurveyTimeDAO surveyTimeDao() {
        SurveyTimeDAO surveyTimeDAO;
        if (this._surveyTimeDAO != null) {
            return this._surveyTimeDAO;
        }
        synchronized (this) {
            if (this._surveyTimeDAO == null) {
                this._surveyTimeDAO = new SurveyTimeDAO_Impl(this);
            }
            surveyTimeDAO = this._surveyTimeDAO;
        }
        return surveyTimeDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentUploadDAO assignmentUploadDao() {
        AssignmentUploadDAO assignmentUploadDAO;
        if (this._assignmentUploadDAO != null) {
            return this._assignmentUploadDAO;
        }
        synchronized (this) {
            if (this._assignmentUploadDAO == null) {
                this._assignmentUploadDAO = new AssignmentUploadDAO_Impl(this);
            }
            assignmentUploadDAO = this._assignmentUploadDAO;
        }
        return assignmentUploadDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public NewAssignmentsDAO newAssignmentsDao() {
        NewAssignmentsDAO newAssignmentsDAO;
        if (this._newAssignmentsDAO != null) {
            return this._newAssignmentsDAO;
        }
        synchronized (this) {
            if (this._newAssignmentsDAO == null) {
                this._newAssignmentsDAO = new NewAssignmentsDAO_Impl(this);
            }
            newAssignmentsDAO = this._newAssignmentsDAO;
        }
        return newAssignmentsDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentUpdateListingDAO assignmentUpdateListingDao() {
        AssignmentUpdateListingDAO assignmentUpdateListingDAO;
        if (this._assignmentUpdateListingDAO != null) {
            return this._assignmentUpdateListingDAO;
        }
        synchronized (this) {
            if (this._assignmentUpdateListingDAO == null) {
                this._assignmentUpdateListingDAO = new AssignmentUpdateListingDAO_Impl(this);
            }
            assignmentUpdateListingDAO = this._assignmentUpdateListingDAO;
        }
        return assignmentUpdateListingDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentsDao assignmentsDao() {
        AssignmentsDao assignmentsDao;
        if (this._assignmentsDao != null) {
            return this._assignmentsDao;
        }
        synchronized (this) {
            if (this._assignmentsDao == null) {
                this._assignmentsDao = new AssignmentsDao_Impl(this);
            }
            assignmentsDao = this._assignmentsDao;
        }
        return assignmentsDao;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public UpdateListingDAO updateListingDao() {
        UpdateListingDAO updateListingDAO;
        if (this._updateListingDAO != null) {
            return this._updateListingDAO;
        }
        synchronized (this) {
            if (this._updateListingDAO == null) {
                this._updateListingDAO = new UpdateListingDAO_Impl(this);
            }
            updateListingDAO = this._updateListingDAO;
        }
        return updateListingDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentInboxDAO assignmentInboxDao() {
        AssignmentInboxDAO assignmentInboxDAO;
        if (this._assignmentInboxDAO != null) {
            return this._assignmentInboxDAO;
        }
        synchronized (this) {
            if (this._assignmentInboxDAO == null) {
                this._assignmentInboxDAO = new AssignmentInboxDAO_Impl(this);
            }
            assignmentInboxDAO = this._assignmentInboxDAO;
        }
        return assignmentInboxDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public SurveyDAONew surveyNewDao() {
        SurveyDAONew surveyDAONew;
        if (this._surveyDAONew != null) {
            return this._surveyDAONew;
        }
        synchronized (this) {
            if (this._surveyDAONew == null) {
                this._surveyDAONew = new SurveyDAONew_Impl(this);
            }
            surveyDAONew = this._surveyDAONew;
        }
        return surveyDAONew;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public PeriodeDAONew periodeNewDao() {
        PeriodeDAONew periodeDAONew;
        if (this._periodeDAONew != null) {
            return this._periodeDAONew;
        }
        synchronized (this) {
            if (this._periodeDAONew == null) {
                this._periodeDAONew = new PeriodeDAONew_Impl(this);
            }
            periodeDAONew = this._periodeDAONew;
        }
        return periodeDAONew;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public FieldOfficerDAO fieldOfficerDAO() {
        FieldOfficerDAO fieldOfficerDAO;
        if (this._fieldOfficerDAO != null) {
            return this._fieldOfficerDAO;
        }
        synchronized (this) {
            if (this._fieldOfficerDAO == null) {
                this._fieldOfficerDAO = new FieldOfficerDAO_Impl(this);
            }
            fieldOfficerDAO = this._fieldOfficerDAO;
        }
        return fieldOfficerDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public CustomDataTemplateDAO CustomDataTemplateDAO() {
        CustomDataTemplateDAO customDataTemplateDAO;
        if (this._customDataTemplateDAO != null) {
            return this._customDataTemplateDAO;
        }
        synchronized (this) {
            if (this._customDataTemplateDAO == null) {
                this._customDataTemplateDAO = new CustomDataTemplateDAO_Impl(this);
            }
            customDataTemplateDAO = this._customDataTemplateDAO;
        }
        return customDataTemplateDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentBlokSensusDAO assignmetnBlokSensusDao() {
        AssignmentBlokSensusDAO assignmentBlokSensusDAO;
        if (this._assignmentBlokSensusDAO != null) {
            return this._assignmentBlokSensusDAO;
        }
        synchronized (this) {
            if (this._assignmentBlokSensusDAO == null) {
                this._assignmentBlokSensusDAO = new AssignmentBlokSensusDAO_Impl(this);
            }
            assignmentBlokSensusDAO = this._assignmentBlokSensusDAO;
        }
        return assignmentBlokSensusDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentRegionDAO assignmetnRegionDAO() {
        AssignmentRegionDAO assignmentRegionDAO;
        if (this._assignmentRegionDAO != null) {
            return this._assignmentRegionDAO;
        }
        synchronized (this) {
            if (this._assignmentRegionDAO == null) {
                this._assignmentRegionDAO = new AssignmentRegionDAO_Impl(this);
            }
            assignmentRegionDAO = this._assignmentRegionDAO;
        }
        return assignmentRegionDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public TemplateValidationDAO templateValidasiDAO() {
        TemplateValidationDAO templateValidationDAO;
        if (this._templateValidationDAO != null) {
            return this._templateValidationDAO;
        }
        synchronized (this) {
            if (this._templateValidationDAO == null) {
                this._templateValidationDAO = new TemplateValidationDAO_Impl(this);
            }
            templateValidationDAO = this._templateValidationDAO;
        }
        return templateValidationDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public CustomColumnDAO customColumnDAO() {
        CustomColumnDAO customColumnDAO;
        if (this._customColumnDAO != null) {
            return this._customColumnDAO;
        }
        synchronized (this) {
            if (this._customColumnDAO == null) {
                this._customColumnDAO = new CustomColumnDAO_Impl(this);
            }
            customColumnDAO = this._customColumnDAO;
        }
        return customColumnDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public AssignmentSubmitVersionDAO assignmentSubmitVersionDAO() {
        AssignmentSubmitVersionDAO assignmentSubmitVersionDAO;
        if (this._assignmentSubmitVersionDAO != null) {
            return this._assignmentSubmitVersionDAO;
        }
        synchronized (this) {
            if (this._assignmentSubmitVersionDAO == null) {
                this._assignmentSubmitVersionDAO = new AssignmentSubmitVersionDAO_Impl(this);
            }
            assignmentSubmitVersionDAO = this._assignmentSubmitVersionDAO;
        }
        return assignmentSubmitVersionDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public TarikSampleDAO tarikSampleDAO() {
        TarikSampleDAO tarikSampleDAO;
        if (this._tarikSampleDAO != null) {
            return this._tarikSampleDAO;
        }
        synchronized (this) {
            if (this._tarikSampleDAO == null) {
                this._tarikSampleDAO = new TarikSampleDAO_Impl(this);
            }
            tarikSampleDAO = this._tarikSampleDAO;
        }
        return tarikSampleDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public SamplingRegionDAO samplingRegionDAO() {
        SamplingRegionDAO samplingRegionDAO;
        if (this._samplingRegionDAO != null) {
            return this._samplingRegionDAO;
        }
        synchronized (this) {
            if (this._samplingRegionDAO == null) {
                this._samplingRegionDAO = new SamplingRegionDAO_Impl(this);
            }
            samplingRegionDAO = this._samplingRegionDAO;
        }
        return samplingRegionDAO;
    }

    @Override // id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase
    public LocationTrackingDAO locationTrackingDAO() {
        LocationTrackingDAO locationTrackingDAO;
        if (this._locationTrackingDAO != null) {
            return this._locationTrackingDAO;
        }
        synchronized (this) {
            if (this._locationTrackingDAO == null) {
                this._locationTrackingDAO = new LocationTrackingDAO_Impl(this);
            }
            locationTrackingDAO = this._locationTrackingDAO;
        }
        return locationTrackingDAO;
    }
}
