package id.go.bpsfasih.utils;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.usecase.CheckTrackingStatusUseCase;
import id.go.bpsfasih.domain.usecase.SaveLocationUseCase;
import id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: LocationTrackingManager.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0019\u001a\u00020\u0004J\u0006\u0010\u001a\u001a\u00020\u0004J&\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020\u001cH\u0002J\u0010\u0010 \u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"H\u0002J\"\u0010#\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0007J*\u0010&\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u00072\b\u0010%\u001a\u0004\u0018\u00010\u0007H\u0002J\"\u0010)\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u00072\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0007J\u0006\u0010*\u001a\u00020\u001cJ\b\u0010+\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082.¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lid/go/bpsfasih/utils/LocationTrackingManager;", "", "()V", "DEFAULT_SYNC_INTERVAL_MS", "", "DEFAULT_TRACKING_INTERVAL_MS", "TAG", "", "checkTrackingStatusUseCase", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "currentActivityName", "currentSession", "saveLocationUseCase", "Lid/go/bpsfasih/domain/usecase/SaveLocationUseCase;", "syncIntervalMs", "syncJob", "Lkotlinx/coroutines/Job;", "syncLocationTrackingUseCase", "Lid/go/bpsfasih/domain/usecase/SyncLocationTrackingUseCase;", "trackingIntervalMs", "trackingJob", "trackingPreferences", "Lid/go/bpsfasih/utils/TrackingPreferences;", "getSaveIntervalSeconds", "getSyncIntervalSeconds", "initialize", "", "isTracking", "", "refreshIntervalsFromRemoteConfig", "resolveActivityName", "context", "Landroid/content/Context;", "resumeIfNeeded", "currentDate", "assignmentId", "saveLocation", "userId", "date", "startTracking", "stopTracking", "syncUnsyncedLocations", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LocationTrackingManager {
    private static final String TAG = "LocationTrackingManager";
    private static CheckTrackingStatusUseCase checkTrackingStatusUseCase;
    private static SaveLocationUseCase saveLocationUseCase;
    private static Job syncJob;
    private static SyncLocationTrackingUseCase syncLocationTrackingUseCase;
    private static Job trackingJob;
    private static TrackingPreferences trackingPreferences;
    public static final LocationTrackingManager INSTANCE = new LocationTrackingManager();
    private static final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
    private static volatile String currentActivityName = "ENTRY";
    private static volatile String currentSession = "";
    private static final long DEFAULT_TRACKING_INTERVAL_MS = 180000;
    private static volatile long trackingIntervalMs = DEFAULT_TRACKING_INTERVAL_MS;
    private static final long DEFAULT_SYNC_INTERVAL_MS = 360000;
    private static volatile long syncIntervalMs = DEFAULT_SYNC_INTERVAL_MS;
    public static final int $stable = 8;

    private LocationTrackingManager() {
    }

    public final void initialize(SaveLocationUseCase saveLocationUseCase2, CheckTrackingStatusUseCase checkTrackingStatusUseCase2, SyncLocationTrackingUseCase syncLocationTrackingUseCase2, TrackingPreferences trackingPreferences2) {
        Intrinsics.checkNotNullParameter(saveLocationUseCase2, "saveLocationUseCase");
        Intrinsics.checkNotNullParameter(checkTrackingStatusUseCase2, "checkTrackingStatusUseCase");
        Intrinsics.checkNotNullParameter(syncLocationTrackingUseCase2, "syncLocationTrackingUseCase");
        Intrinsics.checkNotNullParameter(trackingPreferences2, "trackingPreferences");
        saveLocationUseCase = saveLocationUseCase2;
        checkTrackingStatusUseCase = checkTrackingStatusUseCase2;
        syncLocationTrackingUseCase = syncLocationTrackingUseCase2;
        trackingPreferences = trackingPreferences2;
    }

    public static /* synthetic */ void startTracking$default(LocationTrackingManager locationTrackingManager, Context context, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        locationTrackingManager.startTracking(context, str, str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void startTracking(android.content.Context r18, java.lang.String r19, java.lang.String r20) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.LocationTrackingManager.startTracking(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* compiled from: LocationTrackingManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.LocationTrackingManager$startTracking$2", f = "LocationTrackingManager.kt", i = {}, l = {96}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.LocationTrackingManager$startTracking$2, reason: invalid class name and case insensitive filesystem */
    static final class C09232 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ Context $context;
        final /* synthetic */ String $currentDate;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C09232(Context context, String str, String str2, String str3, Continuation<? super C09232> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$userId = str;
            this.$currentDate = str2;
            this.$assignmentId = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09232(this.$context, this.$userId, this.$currentDate, this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09232) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x0066  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0029 -> B:12:0x002c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 1
                if (r1 == 0) goto L18
                if (r1 != r2) goto L10
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
                goto L2c
            L10:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L18:
                kotlin.ResultKt.throwOnFailure(r9)
                r9 = r8
            L1c:
                long r3 = id.go.bpsfasih.utils.LocationTrackingManager.access$getTrackingIntervalMs$p()
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r9.label = r2
                java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r1 != r0) goto L2c
                return r0
            L2c:
                id.go.bpsfasih.utils.GpsStatusHelper r1 = id.go.bpsfasih.utils.GpsStatusHelper.INSTANCE
                android.content.Context r3 = r9.$context
                boolean r1 = r1.isGpsEnabled(r3)
                java.lang.String r3 = "LocationTrackingManager"
                r4 = 0
                r5 = 0
                java.lang.String r6 = "trackingPreferences"
                if (r1 != 0) goto L66
                id.go.bpsfasih.utils.TrackingPreferences r0 = id.go.bpsfasih.utils.LocationTrackingManager.access$getTrackingPreferences$p()
                if (r0 != 0) goto L46
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                r0 = r5
            L46:
                android.content.Context r1 = r9.$context
                r0.setTrackingActive(r1, r4)
                id.go.bpsfasih.utils.TrackingPreferences r0 = id.go.bpsfasih.utils.LocationTrackingManager.access$getTrackingPreferences$p()
                if (r0 != 0) goto L55
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                goto L56
            L55:
                r5 = r0
            L56:
                android.content.Context r9 = r9.$context
                r5.endTrackingSession(r9)
                id.go.bpsfasih.utils.LocationTrackingManager r9 = id.go.bpsfasih.utils.LocationTrackingManager.INSTANCE
                r9.stopTracking()
                java.lang.String r9 = "Tracking stopped because GPS is disabled"
                android.util.Log.d(r3, r9)
                goto L99
            L66:
                id.go.bpsfasih.utils.LocationHelper r1 = id.go.bpsfasih.utils.LocationHelper.INSTANCE
                android.content.Context r7 = r9.$context
                boolean r1 = r1.hasLocationPermission(r7)
                if (r1 != 0) goto L9c
                id.go.bpsfasih.utils.TrackingPreferences r0 = id.go.bpsfasih.utils.LocationTrackingManager.access$getTrackingPreferences$p()
                if (r0 != 0) goto L7a
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                r0 = r5
            L7a:
                android.content.Context r1 = r9.$context
                r0.setTrackingActive(r1, r4)
                id.go.bpsfasih.utils.TrackingPreferences r0 = id.go.bpsfasih.utils.LocationTrackingManager.access$getTrackingPreferences$p()
                if (r0 != 0) goto L89
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
                goto L8a
            L89:
                r5 = r0
            L8a:
                android.content.Context r9 = r9.$context
                r5.endTrackingSession(r9)
                id.go.bpsfasih.utils.LocationTrackingManager r9 = id.go.bpsfasih.utils.LocationTrackingManager.INSTANCE
                r9.stopTracking()
                java.lang.String r9 = "Tracking stopped because location permission is unavailable"
                android.util.Log.d(r3, r9)
            L99:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            L9c:
                id.go.bpsfasih.utils.LocationTrackingManager r1 = id.go.bpsfasih.utils.LocationTrackingManager.INSTANCE
                android.content.Context r3 = r9.$context
                java.lang.String r4 = r9.$userId
                java.lang.String r5 = r9.$currentDate
                java.lang.String r6 = r9.$assignmentId
                id.go.bpsfasih.utils.LocationTrackingManager.access$saveLocation(r1, r3, r4, r5, r6)
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.LocationTrackingManager.C09232.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* compiled from: LocationTrackingManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.LocationTrackingManager$startTracking$3", f = "LocationTrackingManager.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.LocationTrackingManager$startTracking$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        /* JADX WARN: Removed duplicated region for block: B:11:0x002b A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0029 -> B:12:0x002c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                r2 = 1
                if (r1 == 0) goto L18
                if (r1 != r2) goto L10
                kotlin.ResultKt.throwOnFailure(r6)
                r6 = r5
                goto L2c
            L10:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L18:
                kotlin.ResultKt.throwOnFailure(r6)
                r6 = r5
            L1c:
                long r3 = id.go.bpsfasih.utils.LocationTrackingManager.access$getSyncIntervalMs$p()
                r1 = r6
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r6.label = r2
                java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r1 != r0) goto L2c
                return r0
            L2c:
                id.go.bpsfasih.utils.LocationTrackingManager r1 = id.go.bpsfasih.utils.LocationTrackingManager.INSTANCE
                id.go.bpsfasih.utils.LocationTrackingManager.access$syncUnsyncedLocations(r1)
                goto L1c
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.LocationTrackingManager.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final void stopTracking() {
        Job job = trackingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        trackingJob = null;
        Job job2 = syncJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        syncJob = null;
        Log.d(TAG, "Tracking stopped");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveLocation(final Context context, final String userId, final String date, final String assignmentId) {
        if (!LocationHelper.INSTANCE.hasLocationPermission(context)) {
            TrackingPreferences trackingPreferences2 = trackingPreferences;
            TrackingPreferences trackingPreferences3 = null;
            if (trackingPreferences2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
                trackingPreferences2 = null;
            }
            trackingPreferences2.setTrackingActive(context, false);
            TrackingPreferences trackingPreferences4 = trackingPreferences;
            if (trackingPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
            } else {
                trackingPreferences3 = trackingPreferences4;
            }
            trackingPreferences3.endTrackingSession(context);
            stopTracking();
            Log.d(TAG, "Tracking stopped before saving because location permission is unavailable");
            return;
        }
        LocationHelper.INSTANCE.getCurrentLocation(context, new Function3<Double, Double, Float, Unit>() { // from class: id.go.bpsfasih.utils.LocationTrackingManager.saveLocation.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Double d, Double d2, Float f) {
                invoke(d.doubleValue(), d2.doubleValue(), f);
                return Unit.INSTANCE;
            }

            /* compiled from: LocationTrackingManager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
            @DebugMetadata(c = "id.go.bpsfasih.utils.LocationTrackingManager$saveLocation$1$1", f = "LocationTrackingManager.kt", i = {}, l = {148}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: id.go.bpsfasih.utils.LocationTrackingManager$saveLocation$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02561 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Float $accuracy;
                final /* synthetic */ String $assignmentId;
                final /* synthetic */ String $date;
                final /* synthetic */ double $lat;
                final /* synthetic */ double $lng;
                final /* synthetic */ String $userId;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02561(String str, String str2, double d, double d2, Float f, String str3, Continuation<? super C02561> continuation) {
                    super(2, continuation);
                    this.$userId = str;
                    this.$assignmentId = str2;
                    this.$lat = d;
                    this.$lng = d2;
                    this.$accuracy = f;
                    this.$date = str3;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02561(this.$userId, this.$assignmentId, this.$lat, this.$lng, this.$accuracy, this.$date, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02561) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object value;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        SaveLocationUseCase saveLocationUseCase = LocationTrackingManager.saveLocationUseCase;
                        if (saveLocationUseCase == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("saveLocationUseCase");
                            saveLocationUseCase = null;
                        }
                        this.label = 1;
                        Object objM6677invoketZkwj4A = saveLocationUseCase.m6677invoketZkwj4A(this.$userId, this.$assignmentId, this.$lat, this.$lng, this.$accuracy, this.$date, LocationTrackingManager.currentActivityName, LocationTrackingManager.currentSession, this);
                        if (objM6677invoketZkwj4A == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        value = objM6677invoketZkwj4A;
                    } else {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        value = ((Result) obj).getValue();
                    }
                    if (Result.m6859isSuccessimpl(value)) {
                        double d = this.$lat;
                        double d2 = this.$lng;
                        String str = this.$assignmentId;
                        if (str == null) {
                            str = "null";
                        }
                        Log.d(LocationTrackingManager.TAG, "Location saved: " + d + ", " + d2 + " (assignment: " + str + ", session: " + LocationTrackingManager.currentSession + ")");
                    } else {
                        Log.e(LocationTrackingManager.TAG, "Failed to save location", Result.m6855exceptionOrNullimpl(value));
                    }
                    return Unit.INSTANCE;
                }
            }

            public final void invoke(double d, double d2, Float f) {
                BuildersKt__Builders_commonKt.launch$default(LocationTrackingManager.coroutineScope, null, null, new C02561(userId, assignmentId, d, d2, f, date, null), 3, null);
            }
        }, new Function1<Exception, Unit>() { // from class: id.go.bpsfasih.utils.LocationTrackingManager.saveLocation.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception exception) {
                Intrinsics.checkNotNullParameter(exception, "exception");
                if (exception instanceof SecurityException) {
                    TrackingPreferences trackingPreferences5 = LocationTrackingManager.trackingPreferences;
                    TrackingPreferences trackingPreferences6 = null;
                    if (trackingPreferences5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
                        trackingPreferences5 = null;
                    }
                    trackingPreferences5.setTrackingActive(context, false);
                    TrackingPreferences trackingPreferences7 = LocationTrackingManager.trackingPreferences;
                    if (trackingPreferences7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("trackingPreferences");
                    } else {
                        trackingPreferences6 = trackingPreferences7;
                    }
                    trackingPreferences6.endTrackingSession(context);
                    LocationTrackingManager.INSTANCE.stopTracking();
                    Log.d(LocationTrackingManager.TAG, "Tracking stopped because location permission was revoked");
                }
                Log.e(LocationTrackingManager.TAG, "Failed to get location", exception);
            }
        });
    }

    public static /* synthetic */ void resumeIfNeeded$default(LocationTrackingManager locationTrackingManager, Context context, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = null;
        }
        locationTrackingManager.resumeIfNeeded(context, str, str2);
    }

    public final void resumeIfNeeded(Context context, String currentDate, String assignmentId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        CheckTrackingStatusUseCase checkTrackingStatusUseCase2 = checkTrackingStatusUseCase;
        if (checkTrackingStatusUseCase2 == null) {
            Log.e(TAG, "LocationTrackingManager not initialized");
            return;
        }
        if (checkTrackingStatusUseCase2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("checkTrackingStatusUseCase");
            checkTrackingStatusUseCase2 = null;
        }
        if (checkTrackingStatusUseCase2.invoke(context, currentDate) instanceof CheckTrackingStatusUseCase.TrackingStatus.Active) {
            startTracking(context, currentDate, assignmentId);
        }
    }

    public final boolean isTracking() {
        Job job = trackingJob;
        return job != null && job.isActive();
    }

    public final long getSaveIntervalSeconds() {
        refreshIntervalsFromRemoteConfig();
        return trackingIntervalMs / 1000;
    }

    public final long getSyncIntervalSeconds() {
        refreshIntervalsFromRemoteConfig();
        return syncIntervalMs / 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncUnsyncedLocations() {
        if (syncLocationTrackingUseCase == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C09242(null), 3, null);
    }

    /* compiled from: LocationTrackingManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.LocationTrackingManager$syncUnsyncedLocations$2", f = "LocationTrackingManager.kt", i = {}, l = {210}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.LocationTrackingManager$syncUnsyncedLocations$2, reason: invalid class name and case insensitive filesystem */
    static final class C09242 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C09242(Continuation<? super C09242> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09242(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09242) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM6680invokegIAlus$default;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SyncLocationTrackingUseCase syncLocationTrackingUseCase = LocationTrackingManager.syncLocationTrackingUseCase;
                if (syncLocationTrackingUseCase == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("syncLocationTrackingUseCase");
                    syncLocationTrackingUseCase = null;
                }
                this.label = 1;
                objM6680invokegIAlus$default = SyncLocationTrackingUseCase.m6680invokegIAlus$default(syncLocationTrackingUseCase, 0, this, 1, null);
                if (objM6680invokegIAlus$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objM6680invokegIAlus$default = ((Result) obj).getValue();
            }
            if (Result.m6859isSuccessimpl(objM6680invokegIAlus$default)) {
                Integer numBoxInt = Boxing.boxInt(0);
                if (Result.m6858isFailureimpl(objM6680invokegIAlus$default)) {
                    objM6680invokegIAlus$default = numBoxInt;
                }
                int iIntValue = ((Number) objM6680invokegIAlus$default).intValue();
                if (iIntValue > 0) {
                    Log.d(LocationTrackingManager.TAG, "Synced " + iIntValue + " tracking points to server");
                }
            } else {
                Log.e(LocationTrackingManager.TAG, "Failed to sync tracking points", Result.m6855exceptionOrNullimpl(objM6680invokegIAlus$default));
            }
            return Unit.INSTANCE;
        }
    }

    private final String resolveActivityName(Context context) {
        String simpleName = context.getClass().getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "simpleName");
        if (!StringsKt.isBlank(simpleName)) {
            return simpleName;
        }
        String name = context.getClass().getName();
        Intrinsics.checkNotNullExpressionValue(name, "context::class.java.name");
        String strSubstringAfterLast$default = StringsKt.substringAfterLast$default(name, '.', (String) null, 2, (Object) null);
        if (StringsKt.isBlank(strSubstringAfterLast$default)) {
            strSubstringAfterLast$default = "ENTRY";
        }
        return strSubstringAfterLast$default;
    }

    private final void refreshIntervalsFromRemoteConfig() {
        RemoteConfigHelper.LiveTrackingIntervalConfig liveTrackingIntervalConfig = RemoteConfigHelper.INSTANCE.getLiveTrackingIntervalConfig(180L, 360L);
        trackingIntervalMs = liveTrackingIntervalConfig.getSaveDurationSeconds() * 1000;
        syncIntervalMs = liveTrackingIntervalConfig.getSendDurationSeconds() * 1000;
        Log.d(TAG, "Using " + trackingIntervalMs + " ms save interval and " + syncIntervalMs + " ms send interval from live_tracking");
    }
}
