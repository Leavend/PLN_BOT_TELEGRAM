package id.go.bpsfasih;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.analytics.ktx.AnalyticsKt;
import com.google.firebase.ktx.Firebase;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.dao.AssignmentBlokSensusDAO;
import id.go.bpsfasih.data.local.dao.AssignmentSubmitVersionDAO;
import id.go.bpsfasih.data.local.dao.CustomColumnDAO;
import id.go.bpsfasih.data.local.dao.PeriodeDAONew;
import id.go.bpsfasih.data.local.dao.SurveyDAONew;
import id.go.bpsfasih.data.local.dao.TemplateValidationDAO;
import id.go.bpsfasih.data.local.dao.regionDao.AssignmentRegionDAO;
import id.go.bpsfasih.data.local.databaseNotRoom.Database;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.remoteconfig.RemoteConfig;
import id.go.bpsfasih.remoteconfig.module.RemoteConfigModule;
import id.go.bpsfasih.utils.LiveTrackingHelper;
import id.go.bpsfasih.utils.Session;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import io.reactivex.disposables.CompositeDisposable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: FasihApp.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/FasihApp;", "Landroidx/multidex/MultiDexApplication;", "()V", "firebaseAnalytics", "Lcom/google/firebase/analytics/FirebaseAnalytics;", "startedActivities", "", "attachBaseContext", "", "base", "Landroid/content/Context;", "onCreate", "resumeTrackingOnForegroundActivity", "activity", "Landroid/app/Activity;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FasihApp extends MultiDexApplication {
    public static AssignmentBlokSensusDAO assignmentBlokSensusDAO;
    public static AssigmentDAO assignmentDao;
    public static AssignmentRegionDAO assignmentRegionDAO;
    public static AssignmentSubmitVersionDAO assignmentSubmitVersionDAO;
    private static Context context;
    public static CustomColumnDAO customColumnDAO;
    private static Database mDb;
    private static Session mSession;
    public static PeriodeDAONew periodeDao;
    public static SurveyDAONew surveyDao;
    public static TemplateValidationDAO templateValidationDAO;
    private FirebaseAnalytics firebaseAnalytics;
    private int startedActivities;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final CompositeDisposable myCompositeDisposable = new CompositeDisposable();

    /* compiled from: FasihApp.kt */
    @Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010=\u001a\u00020\u001cJ\u0006\u0010>\u001a\u00020$J\u0006\u0010?\u001a\u00020&J\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u001cJ\u0006\u0010C\u001a\u00020AR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u001c8\u0002@\u0002X\u0083.¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0011\u0010'\u001a\u00020(¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u000202X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u000208X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<¨\u0006D"}, d2 = {"Lid/go/bpsfasih/FasihApp$Companion;", "", "()V", "assignmentBlokSensusDAO", "Lid/go/bpsfasih/data/local/dao/AssignmentBlokSensusDAO;", "getAssignmentBlokSensusDAO", "()Lid/go/bpsfasih/data/local/dao/AssignmentBlokSensusDAO;", "setAssignmentBlokSensusDAO", "(Lid/go/bpsfasih/data/local/dao/AssignmentBlokSensusDAO;)V", "assignmentDao", "Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "getAssignmentDao", "()Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "setAssignmentDao", "(Lid/go/bpsfasih/data/local/dao/AssigmentDAO;)V", "assignmentRegionDAO", "Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;", "getAssignmentRegionDAO", "()Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;", "setAssignmentRegionDAO", "(Lid/go/bpsfasih/data/local/dao/regionDao/AssignmentRegionDAO;)V", "assignmentSubmitVersionDAO", "Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;", "getAssignmentSubmitVersionDAO", "()Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;", "setAssignmentSubmitVersionDAO", "(Lid/go/bpsfasih/data/local/dao/AssignmentSubmitVersionDAO;)V", "context", "Landroid/content/Context;", "customColumnDAO", "Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "getCustomColumnDAO", "()Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;", "setCustomColumnDAO", "(Lid/go/bpsfasih/data/local/dao/CustomColumnDAO;)V", "mDb", "Lid/go/bpsfasih/data/local/databaseNotRoom/Database;", "mSession", "Lid/go/bpsfasih/utils/Session;", "myCompositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getMyCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "periodeDao", "Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;", "getPeriodeDao", "()Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;", "setPeriodeDao", "(Lid/go/bpsfasih/data/local/dao/PeriodeDAONew;)V", "surveyDao", "Lid/go/bpsfasih/data/local/dao/SurveyDAONew;", "getSurveyDao", "()Lid/go/bpsfasih/data/local/dao/SurveyDAONew;", "setSurveyDao", "(Lid/go/bpsfasih/data/local/dao/SurveyDAONew;)V", "templateValidationDAO", "Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;", "getTemplateValidationDAO", "()Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;", "setTemplateValidationDAO", "(Lid/go/bpsfasih/data/local/dao/TemplateValidationDAO;)V", "getContext", "getDatabase", "getSession", "setContext", "", "con", "setDao", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CompositeDisposable getMyCompositeDisposable() {
            return FasihApp.myCompositeDisposable;
        }

        public final SurveyDAONew getSurveyDao() {
            SurveyDAONew surveyDAONew = FasihApp.surveyDao;
            if (surveyDAONew != null) {
                return surveyDAONew;
            }
            Intrinsics.throwUninitializedPropertyAccessException("surveyDao");
            return null;
        }

        public final void setSurveyDao(SurveyDAONew surveyDAONew) {
            Intrinsics.checkNotNullParameter(surveyDAONew, "<set-?>");
            FasihApp.surveyDao = surveyDAONew;
        }

        public final PeriodeDAONew getPeriodeDao() {
            PeriodeDAONew periodeDAONew = FasihApp.periodeDao;
            if (periodeDAONew != null) {
                return periodeDAONew;
            }
            Intrinsics.throwUninitializedPropertyAccessException("periodeDao");
            return null;
        }

        public final void setPeriodeDao(PeriodeDAONew periodeDAONew) {
            Intrinsics.checkNotNullParameter(periodeDAONew, "<set-?>");
            FasihApp.periodeDao = periodeDAONew;
        }

        public final AssigmentDAO getAssignmentDao() {
            AssigmentDAO assigmentDAO = FasihApp.assignmentDao;
            if (assigmentDAO != null) {
                return assigmentDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("assignmentDao");
            return null;
        }

        public final void setAssignmentDao(AssigmentDAO assigmentDAO) {
            Intrinsics.checkNotNullParameter(assigmentDAO, "<set-?>");
            FasihApp.assignmentDao = assigmentDAO;
        }

        public final AssignmentBlokSensusDAO getAssignmentBlokSensusDAO() {
            AssignmentBlokSensusDAO assignmentBlokSensusDAO = FasihApp.assignmentBlokSensusDAO;
            if (assignmentBlokSensusDAO != null) {
                return assignmentBlokSensusDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("assignmentBlokSensusDAO");
            return null;
        }

        public final void setAssignmentBlokSensusDAO(AssignmentBlokSensusDAO assignmentBlokSensusDAO) {
            Intrinsics.checkNotNullParameter(assignmentBlokSensusDAO, "<set-?>");
            FasihApp.assignmentBlokSensusDAO = assignmentBlokSensusDAO;
        }

        public final AssignmentRegionDAO getAssignmentRegionDAO() {
            AssignmentRegionDAO assignmentRegionDAO = FasihApp.assignmentRegionDAO;
            if (assignmentRegionDAO != null) {
                return assignmentRegionDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("assignmentRegionDAO");
            return null;
        }

        public final void setAssignmentRegionDAO(AssignmentRegionDAO assignmentRegionDAO) {
            Intrinsics.checkNotNullParameter(assignmentRegionDAO, "<set-?>");
            FasihApp.assignmentRegionDAO = assignmentRegionDAO;
        }

        public final TemplateValidationDAO getTemplateValidationDAO() {
            TemplateValidationDAO templateValidationDAO = FasihApp.templateValidationDAO;
            if (templateValidationDAO != null) {
                return templateValidationDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("templateValidationDAO");
            return null;
        }

        public final void setTemplateValidationDAO(TemplateValidationDAO templateValidationDAO) {
            Intrinsics.checkNotNullParameter(templateValidationDAO, "<set-?>");
            FasihApp.templateValidationDAO = templateValidationDAO;
        }

        public final CustomColumnDAO getCustomColumnDAO() {
            CustomColumnDAO customColumnDAO = FasihApp.customColumnDAO;
            if (customColumnDAO != null) {
                return customColumnDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("customColumnDAO");
            return null;
        }

        public final void setCustomColumnDAO(CustomColumnDAO customColumnDAO) {
            Intrinsics.checkNotNullParameter(customColumnDAO, "<set-?>");
            FasihApp.customColumnDAO = customColumnDAO;
        }

        public final AssignmentSubmitVersionDAO getAssignmentSubmitVersionDAO() {
            AssignmentSubmitVersionDAO assignmentSubmitVersionDAO = FasihApp.assignmentSubmitVersionDAO;
            if (assignmentSubmitVersionDAO != null) {
                return assignmentSubmitVersionDAO;
            }
            Intrinsics.throwUninitializedPropertyAccessException("assignmentSubmitVersionDAO");
            return null;
        }

        public final void setAssignmentSubmitVersionDAO(AssignmentSubmitVersionDAO assignmentSubmitVersionDAO) {
            Intrinsics.checkNotNullParameter(assignmentSubmitVersionDAO, "<set-?>");
            FasihApp.assignmentSubmitVersionDAO = assignmentSubmitVersionDAO;
        }

        public final void setContext(Context con) {
            Intrinsics.checkNotNullParameter(con, "con");
            FasihApp.context = con;
        }

        public final synchronized Context getContext() {
            Context context;
            context = FasihApp.context;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            return context;
        }

        public final Session getSession() {
            Session session = FasihApp.mSession;
            Intrinsics.checkNotNull(session);
            return session;
        }

        public final Database getDatabase() {
            if (FasihApp.mDb == null) {
                Context context = FasihApp.context;
                if (context == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("context");
                    context = null;
                }
                FasihApp.mDb = new Database(context);
            }
            Database database = FasihApp.mDb;
            Intrinsics.checkNotNull(database);
            return database;
        }

        public final void setDao() {
            SurveyRoomDatabase database = SurveyRoomDatabase.INSTANCE.getDatabase(getContext());
            setSurveyDao(database.surveyNewDao());
            setPeriodeDao(database.periodeNewDao());
            setAssignmentDao(database.assigmentDAO());
            setAssignmentBlokSensusDAO(database.assignmetnBlokSensusDao());
            setAssignmentRegionDAO(database.assignmetnRegionDAO());
            setTemplateValidationDAO(database.templateValidasiDAO());
            setCustomColumnDAO(database.customColumnDAO());
            setAssignmentSubmitVersionDAO(database.assignmentSubmitVersionDAO());
        }
    }

    @Override // androidx.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        Intrinsics.checkNotNullParameter(base, "base");
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        FasihApp fasihApp = this;
        FirebaseApp.initializeApp(fasihApp);
        Companion companion = INSTANCE;
        companion.setContext(fasihApp);
        companion.setDao();
        mSession = new Session(fasihApp);
        this.firebaseAnalytics = AnalyticsKt.getAnalytics(Firebase.INSTANCE);
        RemoteConfigModule.INSTANCE.get().fetch(new RemoteConfig.Listener() { // from class: id.go.bpsfasih.FasihApp.onCreate.1
            @Override // id.go.bpsfasih.remoteconfig.RemoteConfig.Listener
            public void onCompleted(RemoteConfig config) {
                Intrinsics.checkNotNullParameter(config, "config");
            }

            @Override // id.go.bpsfasih.remoteconfig.RemoteConfig.Listener
            public void onError(Throwable t) {
                Intrinsics.checkNotNullParameter(t, "t");
            }
        });
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(FirebaseAnalytics.Param.LOCATION, "Location", 2);
            Object systemService = getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
        }
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: id.go.bpsfasih.FasihApp.onCreate.2
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                FasihApp.this.startedActivities++;
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                FasihApp.this.resumeTrackingOnForegroundActivity(activity);
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                FasihApp.this.startedActivities = RangesKt.coerceAtLeast(r3.startedActivities - 1, 0);
                if (FasihApp.this.startedActivities == 0) {
                    LiveTrackingHelper.INSTANCE.stopTracking();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeTrackingOnForegroundActivity(Activity activity) {
        try {
            Companion companion = INSTANCE;
            if (companion.getSession().is_login()) {
                if (!RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow(CommonCons.REMOTE_CONFIG_FEATURE_TRACKING_LOCATION)) {
                    LiveTrackingHelper.INSTANCE.stopTrackingPermanent(activity);
                    return;
                }
                String sessionString = companion.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID());
                if (sessionString == null || sessionString.length() == 0) {
                    return;
                }
                LiveTrackingHelper.INSTANCE.resumeTracking(activity);
            }
        } catch (Exception e) {
            Log.e("FasihApp", "Failed to resume tracking on activity lifecycle", e);
        }
    }
}
