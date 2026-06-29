package id.go.bpsfasih.utils;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl;
import id.go.bpsfasih.data.repository.TrackingRepositoryImpl;
import id.go.bpsfasih.domain.usecase.CheckTrackingStatusUseCase;
import id.go.bpsfasih.domain.usecase.GetLocationsByAssignmentUseCase;
import id.go.bpsfasih.domain.usecase.GetTodayLocationsUseCase;
import id.go.bpsfasih.domain.usecase.SaveLocationUseCase;
import id.go.bpsfasih.domain.usecase.StartTrackingUseCase;
import id.go.bpsfasih.domain.usecase.StopTrackingUseCase;
import id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase;
import id.go.bpsfasih.presentation.locationtracking.StartTrackingDialogFragment;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveTrackingHelper.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aJ\b\u0010\u001b\u001a\u00020\u001aH\u0002J\u000e\u0010\u001c\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\nJ\u000e\u0010 \u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\"\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0019\u001a\u00020\u001a2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u001aJ\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lid/go/bpsfasih/utils/LiveTrackingHelper;", "", "()V", "checkTrackingStatusUseCase", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase;", "getLocationsByAssignmentUseCase", "Lid/go/bpsfasih/domain/usecase/GetLocationsByAssignmentUseCase;", "getTodayLocationsUseCase", "Lid/go/bpsfasih/domain/usecase/GetTodayLocationsUseCase;", "isInitialized", "", "saveLocationUseCase", "Lid/go/bpsfasih/domain/usecase/SaveLocationUseCase;", "startTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/StartTrackingUseCase;", "stopTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/StopTrackingUseCase;", "syncLocationTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/SyncLocationTrackingUseCase;", "trackingPreferences", "Lid/go/bpsfasih/utils/TrackingPreferences;", "checkAndShowDialog", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "userId", "", "getCurrentDate", "initialize", "context", "Landroid/content/Context;", "isTracking", "resumeTracking", "showTrackingDialog", "startTracking", "assignmentId", "stopTracking", "stopTrackingPermanent", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LiveTrackingHelper {
    private static CheckTrackingStatusUseCase checkTrackingStatusUseCase;
    private static GetLocationsByAssignmentUseCase getLocationsByAssignmentUseCase;
    private static GetTodayLocationsUseCase getTodayLocationsUseCase;
    private static boolean isInitialized;
    private static SaveLocationUseCase saveLocationUseCase;
    private static StartTrackingUseCase startTrackingUseCase;
    private static StopTrackingUseCase stopTrackingUseCase;
    private static SyncLocationTrackingUseCase syncLocationTrackingUseCase;
    private static TrackingPreferences trackingPreferences;
    public static final LiveTrackingHelper INSTANCE = new LiveTrackingHelper();
    public static final int $stable = 8;

    private LiveTrackingHelper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void initialize(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isInitialized) {
            return;
        }
        LocationTrackingRepositoryImpl locationTrackingRepositoryImpl = new LocationTrackingRepositoryImpl(new LocationTrackingLocalDataSource(SurveyRoomDatabase.INSTANCE.getDatabase(context).locationTrackingDAO()));
        TrackingPreferences trackingPreferences2 = null;
        TrackingRepositoryImpl trackingRepositoryImpl = new TrackingRepositoryImpl(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
        trackingPreferences = new TrackingPreferences();
        TrackingPreferences trackingPreferences3 = trackingPreferences;
        if (trackingPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
            trackingPreferences3 = null;
        }
        startTrackingUseCase = new StartTrackingUseCase(trackingPreferences3);
        TrackingPreferences trackingPreferences4 = trackingPreferences;
        if (trackingPreferences4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
            trackingPreferences4 = null;
        }
        stopTrackingUseCase = new StopTrackingUseCase(trackingPreferences4);
        LocationTrackingRepositoryImpl locationTrackingRepositoryImpl2 = locationTrackingRepositoryImpl;
        saveLocationUseCase = new SaveLocationUseCase(locationTrackingRepositoryImpl2);
        syncLocationTrackingUseCase = new SyncLocationTrackingUseCase(locationTrackingRepositoryImpl2, trackingRepositoryImpl, new AnonymousClass1(null));
        TrackingPreferences trackingPreferences5 = trackingPreferences;
        if (trackingPreferences5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
            trackingPreferences5 = null;
        }
        checkTrackingStatusUseCase = new CheckTrackingStatusUseCase(trackingPreferences5);
        getTodayLocationsUseCase = new GetTodayLocationsUseCase(locationTrackingRepositoryImpl2);
        getLocationsByAssignmentUseCase = new GetLocationsByAssignmentUseCase(locationTrackingRepositoryImpl2);
        LocationTrackingManager locationTrackingManager = LocationTrackingManager.INSTANCE;
        SaveLocationUseCase saveLocationUseCase2 = saveLocationUseCase;
        if (saveLocationUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveLocationUseCase");
            saveLocationUseCase2 = null;
        }
        CheckTrackingStatusUseCase checkTrackingStatusUseCase2 = checkTrackingStatusUseCase;
        if (checkTrackingStatusUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkTrackingStatusUseCase");
            checkTrackingStatusUseCase2 = null;
        }
        SyncLocationTrackingUseCase syncLocationTrackingUseCase2 = syncLocationTrackingUseCase;
        if (syncLocationTrackingUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("syncLocationTrackingUseCase");
            syncLocationTrackingUseCase2 = null;
        }
        TrackingPreferences trackingPreferences6 = trackingPreferences;
        if (trackingPreferences6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
        } else {
            trackingPreferences2 = trackingPreferences6;
        }
        locationTrackingManager.initialize(saveLocationUseCase2, checkTrackingStatusUseCase2, syncLocationTrackingUseCase2, trackingPreferences2);
        isInitialized = true;
    }

    /* compiled from: LiveTrackingHelper.kt */
    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\u008a@"}, d2 = {"<anonymous>", "", "assignmentId"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.LiveTrackingHelper$initialize$1", f = "LiveTrackingHelper.kt", i = {}, l = {63}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.LiveTrackingHelper$initialize$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<String, Continuation<? super String>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(String str, Continuation<? super String> continuation) {
            return ((AnonymousClass1) create(str, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                String str = (String) this.L$0;
                String str2 = str;
                if (str2 == null || str2.length() == 0) {
                    return null;
                }
                this.label = 1;
                obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentOnly(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            AssignmentEntity assignmentEntity = (AssignmentEntity) obj;
            if (assignmentEntity != null) {
                return assignmentEntity.getPeriodeNotPrimary();
            }
            return null;
        }
    }

    public final void checkAndShowDialog(FragmentActivity activity, String userId) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(userId, "userId");
        if (!isInitialized) {
            initialize(activity);
        }
        String currentDate = getCurrentDate();
        CheckTrackingStatusUseCase checkTrackingStatusUseCase2 = checkTrackingStatusUseCase;
        if (checkTrackingStatusUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkTrackingStatusUseCase");
            checkTrackingStatusUseCase2 = null;
        }
        if (checkTrackingStatusUseCase2.invoke(activity, currentDate) instanceof CheckTrackingStatusUseCase.TrackingStatus.Active) {
            return;
        }
        showTrackingDialog(activity, userId);
    }

    private final void showTrackingDialog(final FragmentActivity activity, final String userId) {
        StartTrackingDialogFragment startTrackingDialogFragmentNewInstance = StartTrackingDialogFragment.INSTANCE.newInstance();
        startTrackingDialogFragmentNewInstance.setOnStartTrackingListener(new Function0<Unit>() { // from class: id.go.bpsfasih.utils.LiveTrackingHelper.showTrackingDialog.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                LiveTrackingHelper.startTracking$default(LiveTrackingHelper.INSTANCE, activity, userId, null, 4, null);
            }
        });
        startTrackingDialogFragmentNewInstance.show(activity.getSupportFragmentManager(), StartTrackingDialogFragment.TAG);
    }

    public static /* synthetic */ void startTracking$default(LiveTrackingHelper liveTrackingHelper, Context context, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        liveTrackingHelper.startTracking(context, str, str2);
    }

    public final void startTracking(Context context, String userId, String assignmentId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userId, "userId");
        if (!isInitialized) {
            initialize(context);
        }
        if (!LocationHelper.INSTANCE.hasLocationPermission(context) || !GpsStatusHelper.INSTANCE.isGpsEnabled(context)) {
            stopTrackingPermanent(context);
            return;
        }
        String currentDate = getCurrentDate();
        StartTrackingUseCase startTrackingUseCase2 = startTrackingUseCase;
        TrackingPreferences trackingPreferences2 = null;
        if (startTrackingUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("startTrackingUseCase");
            startTrackingUseCase2 = null;
        }
        if (Result.m6859isSuccessimpl(startTrackingUseCase2.m6678invoke0E7RQCE(context, userId, currentDate))) {
            if (assignmentId != null) {
                TrackingPreferences trackingPreferences3 = trackingPreferences;
                if (trackingPreferences3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
                } else {
                    trackingPreferences2 = trackingPreferences3;
                }
                trackingPreferences2.setTrackingAssignmentId(context, assignmentId);
            }
            LocationTrackingManager.INSTANCE.startTracking(context, currentDate, assignmentId);
        }
    }

    public final void resumeTracking(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!isInitialized) {
            initialize(context);
        }
        if (!LocationHelper.INSTANCE.hasLocationPermission(context) || !GpsStatusHelper.INSTANCE.isGpsEnabled(context)) {
            stopTrackingPermanent(context);
            return;
        }
        String currentDate = getCurrentDate();
        TrackingPreferences trackingPreferences2 = trackingPreferences;
        if (trackingPreferences2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
            trackingPreferences2 = null;
        }
        LocationTrackingManager.INSTANCE.resumeIfNeeded(context, currentDate, trackingPreferences2.getTrackingAssignmentId(context));
    }

    public final void stopTracking() {
        if (isInitialized) {
            LocationTrackingManager.INSTANCE.stopTracking();
        }
    }

    public final void stopTrackingPermanent(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!isInitialized) {
            initialize(context);
        }
        StopTrackingUseCase stopTrackingUseCase2 = stopTrackingUseCase;
        if (stopTrackingUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopTrackingUseCase");
            stopTrackingUseCase2 = null;
        }
        stopTrackingUseCase2.m6679invokeIoAF18A(context);
        LocationTrackingManager.INSTANCE.stopTracking();
    }

    public final boolean isTracking() {
        if (isInitialized) {
            return LocationTrackingManager.INSTANCE.isTracking();
        }
        return false;
    }

    private final String getCurrentDate() {
        String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Intrinsics.checkNotNullExpressionValue(str, "dateFormat.format(Date())");
        return str;
    }
}
