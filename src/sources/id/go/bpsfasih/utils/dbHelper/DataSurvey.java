package id.go.bpsfasih.utils.dbHelper;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.AssignmentUploadDAO;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO;
import id.go.bpsfasih.data.local.dao.CustomDataTemplateDAO;
import id.go.bpsfasih.data.local.dao.SamplingRegionDAO;
import id.go.bpsfasih.data.local.dao.SurveyTimeDAO;
import id.go.bpsfasih.data.local.dao.TarikSampleDAO;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.repository.AssignmentRegionRepository;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository;
import id.go.bpsfasih.data.local.repository.AssignmentUploadRepository;
import id.go.bpsfasih.data.local.repository.CustomColumnRepository;
import id.go.bpsfasih.data.local.repository.CustomDataTemplateRepository;
import id.go.bpsfasih.data.local.repository.PeriodeRepository;
import id.go.bpsfasih.data.local.repository.SamplingRegionRepository;
import id.go.bpsfasih.data.local.repository.SurveyRepositoryNew;
import id.go.bpsfasih.data.local.repository.SurveyTimeRepository;
import id.go.bpsfasih.data.local.repository.TarikSampleRepository;
import id.go.bpsfasih.data.local.repository.TemplateValidationRepository;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataSurvey.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001:\r\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000fB\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey;", "", "()V", "Assignment", "AssignmentRegion", "AssignmentSubmitVersion", "AssignmentUpload", "CustomColumn", "CustomTemplate", "Periode", "SamplingRegion", "Survey", "SurveyTime", "TarikSample", "TemplateValidation", "Upload", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DataSurvey {
    public static final int $stable = 0;

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Survey;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Survey {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SurveyRepositoryNew surveyRepo = new SurveyRepositoryNew(FasihApp.INSTANCE.getSurveyDao());

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Survey$Companion;", "", "()V", "surveyRepo", "Lid/go/bpsfasih/data/local/repository/SurveyRepositoryNew;", "getSurveyRepo", "()Lid/go/bpsfasih/data/local/repository/SurveyRepositoryNew;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SurveyRepositoryNew getSurveyRepo() {
                return Survey.surveyRepo;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Periode;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Periode {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static PeriodeRepository periodeRepository = new PeriodeRepository(FasihApp.INSTANCE.getPeriodeDao(), 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Periode$Companion;", "", "()V", "periodeRepository", "Lid/go/bpsfasih/data/local/repository/PeriodeRepository;", "getPeriodeRepository", "()Lid/go/bpsfasih/data/local/repository/PeriodeRepository;", "setPeriodeRepository", "(Lid/go/bpsfasih/data/local/repository/PeriodeRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final PeriodeRepository getPeriodeRepository() {
                return Periode.periodeRepository;
            }

            public final void setPeriodeRepository(PeriodeRepository periodeRepository) {
                Intrinsics.checkNotNullParameter(periodeRepository, "<set-?>");
                Periode.periodeRepository = periodeRepository;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Assignment;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Assignment {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static AssignmentRepository assignmentRepository = new AssignmentRepository(FasihApp.INSTANCE.getAssignmentDao());

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Assignment$Companion;", "", "()V", "assignmentRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "getAssignmentRepository", "()Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "setAssignmentRepository", "(Lid/go/bpsfasih/data/local/repository/AssignmentRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AssignmentRepository getAssignmentRepository() {
                return Assignment.assignmentRepository;
            }

            public final void setAssignmentRepository(AssignmentRepository assignmentRepository) {
                Intrinsics.checkNotNullParameter(assignmentRepository, "<set-?>");
                Assignment.assignmentRepository = assignmentRepository;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentRegion;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AssignmentRegion {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static AssignmentRegionRepository assignmentRegionRepository = new AssignmentRegionRepository(FasihApp.INSTANCE.getAssignmentRegionDAO());

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentRegion$Companion;", "", "()V", "assignmentRegionRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentRegionRepository;", "getAssignmentRegionRepository", "()Lid/go/bpsfasih/data/local/repository/AssignmentRegionRepository;", "setAssignmentRegionRepository", "(Lid/go/bpsfasih/data/local/repository/AssignmentRegionRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AssignmentRegionRepository getAssignmentRegionRepository() {
                return AssignmentRegion.assignmentRegionRepository;
            }

            public final void setAssignmentRegionRepository(AssignmentRegionRepository assignmentRegionRepository) {
                Intrinsics.checkNotNullParameter(assignmentRegionRepository, "<set-?>");
                AssignmentRegion.assignmentRegionRepository = assignmentRegionRepository;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentSubmitVersion;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AssignmentSubmitVersion {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static AssignmentSubmitVersionRepository assignmentSubmitVersionRepository = new AssignmentSubmitVersionRepository(FasihApp.INSTANCE.getAssignmentSubmitVersionDAO());

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentSubmitVersion$Companion;", "", "()V", "assignmentSubmitVersionRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "getAssignmentSubmitVersionRepository", "()Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "setAssignmentSubmitVersionRepository", "(Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AssignmentSubmitVersionRepository getAssignmentSubmitVersionRepository() {
                return AssignmentSubmitVersion.assignmentSubmitVersionRepository;
            }

            public final void setAssignmentSubmitVersionRepository(AssignmentSubmitVersionRepository assignmentSubmitVersionRepository) {
                Intrinsics.checkNotNullParameter(assignmentSubmitVersionRepository, "<set-?>");
                AssignmentSubmitVersion.assignmentSubmitVersionRepository = assignmentSubmitVersionRepository;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$TemplateValidation;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class TemplateValidation {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static TemplateValidationRepository templateValidationRepository = new TemplateValidationRepository(FasihApp.INSTANCE.getTemplateValidationDAO());

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$TemplateValidation$Companion;", "", "()V", "templateValidationRepository", "Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;", "getTemplateValidationRepository", "()Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;", "setTemplateValidationRepository", "(Lid/go/bpsfasih/data/local/repository/TemplateValidationRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TemplateValidationRepository getTemplateValidationRepository() {
                return TemplateValidation.templateValidationRepository;
            }

            public final void setTemplateValidationRepository(TemplateValidationRepository templateValidationRepository) {
                Intrinsics.checkNotNullParameter(templateValidationRepository, "<set-?>");
                TemplateValidation.templateValidationRepository = templateValidationRepository;
            }
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$CustomTemplate;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class CustomTemplate {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static CustomDataTemplateRepository customTemplateRepo;
        private static final CustomDataTemplateDAO dao;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$CustomTemplate$Companion;", "", "()V", "customTemplateRepo", "Lid/go/bpsfasih/data/local/repository/CustomDataTemplateRepository;", "getCustomTemplateRepo", "()Lid/go/bpsfasih/data/local/repository/CustomDataTemplateRepository;", "setCustomTemplateRepo", "(Lid/go/bpsfasih/data/local/repository/CustomDataTemplateRepository;)V", "dao", "Lid/go/bpsfasih/data/local/dao/CustomDataTemplateDAO;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CustomDataTemplateRepository getCustomTemplateRepo() {
                return CustomTemplate.customTemplateRepo;
            }

            public final void setCustomTemplateRepo(CustomDataTemplateRepository customDataTemplateRepository) {
                Intrinsics.checkNotNullParameter(customDataTemplateRepository, "<set-?>");
                CustomTemplate.customTemplateRepo = customDataTemplateRepository;
            }
        }

        static {
            CustomDataTemplateDAO CustomDataTemplateDAO = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).CustomDataTemplateDAO();
            dao = CustomDataTemplateDAO;
            customTemplateRepo = new CustomDataTemplateRepository(CustomDataTemplateDAO);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$CustomColumn;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class CustomColumn {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static CustomColumnRepository customColumnRepo;
        private static final CustomColumnDAO dao;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$CustomColumn$Companion;", "", "()V", "customColumnRepo", "Lid/go/bpsfasih/data/local/repository/CustomColumnRepository;", "getCustomColumnRepo", "()Lid/go/bpsfasih/data/local/repository/CustomColumnRepository;", "setCustomColumnRepo", "(Lid/go/bpsfasih/data/local/repository/CustomColumnRepository;)V", "dao", "Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final CustomColumnRepository getCustomColumnRepo() {
                return CustomColumn.customColumnRepo;
            }

            public final void setCustomColumnRepo(CustomColumnRepository customColumnRepository) {
                Intrinsics.checkNotNullParameter(customColumnRepository, "<set-?>");
                CustomColumn.customColumnRepo = customColumnRepository;
            }
        }

        static {
            CustomColumnDAO customColumnDAO = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).customColumnDAO();
            dao = customColumnDAO;
            customColumnRepo = new CustomColumnRepository(customColumnDAO);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Upload;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Upload {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final AssignmentUploadDAO dao;
        private static AssignmentUploadRepository uploadRepository;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$Upload$Companion;", "", "()V", "dao", "Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;", "uploadRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;", "getUploadRepository", "()Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;", "setUploadRepository", "(Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AssignmentUploadRepository getUploadRepository() {
                return Upload.uploadRepository;
            }

            public final void setUploadRepository(AssignmentUploadRepository assignmentUploadRepository) {
                Intrinsics.checkNotNullParameter(assignmentUploadRepository, "<set-?>");
                Upload.uploadRepository = assignmentUploadRepository;
            }
        }

        static {
            AssignmentUploadDAO assignmentUploadDao = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).assignmentUploadDao();
            dao = assignmentUploadDao;
            uploadRepository = new AssignmentUploadRepository(assignmentUploadDao);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$SurveyTime;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SurveyTime {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SurveyTimeDAO dao;
        private static SurveyTimeRepository surveyTimeRepository;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$SurveyTime$Companion;", "", "()V", "dao", "Lid/go/bpsfasih/data/local/dao/SurveyTimeDAO;", "surveyTimeRepository", "Lid/go/bpsfasih/data/local/repository/SurveyTimeRepository;", "getSurveyTimeRepository", "()Lid/go/bpsfasih/data/local/repository/SurveyTimeRepository;", "setSurveyTimeRepository", "(Lid/go/bpsfasih/data/local/repository/SurveyTimeRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SurveyTimeRepository getSurveyTimeRepository() {
                return SurveyTime.surveyTimeRepository;
            }

            public final void setSurveyTimeRepository(SurveyTimeRepository surveyTimeRepository) {
                Intrinsics.checkNotNullParameter(surveyTimeRepository, "<set-?>");
                SurveyTime.surveyTimeRepository = surveyTimeRepository;
            }
        }

        static {
            SurveyTimeDAO surveyTimeDao = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).surveyTimeDao();
            dao = surveyTimeDao;
            surveyTimeRepository = new SurveyTimeRepository(surveyTimeDao);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$TarikSample;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class TarikSample {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final TarikSampleDAO dao;
        private static TarikSampleRepository tarikSampleRepository;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$TarikSample$Companion;", "", "()V", "dao", "Lid/go/bpsfasih/data/local/dao/TarikSampleDAO;", "tarikSampleRepository", "Lid/go/bpsfasih/data/local/repository/TarikSampleRepository;", "getTarikSampleRepository", "()Lid/go/bpsfasih/data/local/repository/TarikSampleRepository;", "setTarikSampleRepository", "(Lid/go/bpsfasih/data/local/repository/TarikSampleRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final TarikSampleRepository getTarikSampleRepository() {
                return TarikSample.tarikSampleRepository;
            }

            public final void setTarikSampleRepository(TarikSampleRepository tarikSampleRepository) {
                Intrinsics.checkNotNullParameter(tarikSampleRepository, "<set-?>");
                TarikSample.tarikSampleRepository = tarikSampleRepository;
            }
        }

        static {
            TarikSampleDAO tarikSampleDAO = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).tarikSampleDAO();
            dao = tarikSampleDAO;
            tarikSampleRepository = new TarikSampleRepository(tarikSampleDAO);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$SamplingRegion;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class SamplingRegion {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SamplingRegionDAO dao;
        private static SamplingRegionRepository samplingRegionRepository;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$SamplingRegion$Companion;", "", "()V", "dao", "Lid/go/bpsfasih/data/local/dao/SamplingRegionDAO;", "samplingRegionRepository", "Lid/go/bpsfasih/data/local/repository/SamplingRegionRepository;", "getSamplingRegionRepository", "()Lid/go/bpsfasih/data/local/repository/SamplingRegionRepository;", "setSamplingRegionRepository", "(Lid/go/bpsfasih/data/local/repository/SamplingRegionRepository;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SamplingRegionRepository getSamplingRegionRepository() {
                return SamplingRegion.samplingRegionRepository;
            }

            public final void setSamplingRegionRepository(SamplingRegionRepository samplingRegionRepository) {
                Intrinsics.checkNotNullParameter(samplingRegionRepository, "<set-?>");
                SamplingRegion.samplingRegionRepository = samplingRegionRepository;
            }
        }

        static {
            SamplingRegionDAO samplingRegionDAO = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).samplingRegionDAO();
            dao = samplingRegionDAO;
            samplingRegionRepository = new SamplingRegionRepository(samplingRegionDAO);
        }
    }

    /* compiled from: DataSurvey.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentUpload;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class AssignmentUpload {
        public static final int $stable = 0;

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static AssignmentUploadRepository assignmentUploadRepository;
        private static final AssignmentUploadDAO dao;

        /* compiled from: DataSurvey.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/utils/dbHelper/DataSurvey$AssignmentUpload$Companion;", "", "()V", "assignmentUploadRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;", "getAssignmentUploadRepository", "()Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;", "setAssignmentUploadRepository", "(Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;)V", "dao", "Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final AssignmentUploadRepository getAssignmentUploadRepository() {
                return AssignmentUpload.assignmentUploadRepository;
            }

            public final void setAssignmentUploadRepository(AssignmentUploadRepository assignmentUploadRepository) {
                Intrinsics.checkNotNullParameter(assignmentUploadRepository, "<set-?>");
                AssignmentUpload.assignmentUploadRepository = assignmentUploadRepository;
            }
        }

        static {
            AssignmentUploadDAO assignmentUploadDao = SurveyRoomDatabase.INSTANCE.getDatabase(FasihApp.INSTANCE.getContext()).assignmentUploadDao();
            dao = assignmentUploadDao;
            assignmentUploadRepository = new AssignmentUploadRepository(assignmentUploadDao);
        }
    }
}
