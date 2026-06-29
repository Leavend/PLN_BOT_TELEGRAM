package id.go.bpsfasih.utils.services;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.maps.model.LatLng;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.datasource.LocationTrackingLocalDataSource;
import id.go.bpsfasih.data.repository.LocationTrackingRepositoryImpl;
import id.go.bpsfasih.data.repository.TrackingRepositoryImpl;
import id.go.bpsfasih.domain.usecase.SyncLocationTrackingUseCase;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__BuildersKt;

/* compiled from: NeverEndingService.kt */
@Metadata(d1 = {"\u0000U\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b*\u0001\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0014\u0010\u0012\u001a\u00020\u00042\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0014H\u0002J\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\"\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\b\u0010 \u001a\u00020\u0011H\u0003J\b\u0010!\u001a\u00020\u0011H\u0002J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010#\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lid/go/bpsfasih/utils/services/NeverEndingService;", "Landroid/app/Service;", "()V", "isUploadingTracking", "", "latLng", "Lcom/google/android/gms/maps/model/LatLng;", "locationReceiver", "id/go/bpsfasih/utils/services/NeverEndingService$locationReceiver$1", "Lid/go/bpsfasih/utils/services/NeverEndingService$locationReceiver$1;", "manager", "Landroid/app/NotificationManager;", "timer", "Ljava/util/Timer;", "timerTask", "Ljava/util/TimerTask;", "getLocation", "", "isMyServiceRunning", "serviceClass", "Ljava/lang/Class;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "runServiceLocation", "startMyOwnForeground", "startTimer", "stopTimerTask", "updateToServer", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NeverEndingService extends Service {
    public static final int $stable = 8;
    private volatile boolean isUploadingTracking;
    private LatLng latLng;
    private final NeverEndingService$locationReceiver$1 locationReceiver = new NeverEndingService$locationReceiver$1(this);
    private NotificationManager manager;
    private Timer timer;
    private TimerTask timerTask;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Log.d("logger", "Some component want to bind with the service");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        Log.d("good", "good");
        if (Build.VERSION.SDK_INT > 26) {
            startMyOwnForeground();
        } else {
            startForeground(1, new Notification());
        }
        if (!isMyServiceRunning(LocationService.class)) {
            runServiceLocation();
        }
        getLocation();
    }

    private final void getLocation() {
        LocalBroadcastManager.getInstance(this).registerReceiver(this.locationReceiver, new IntentFilter("GPSLocationUpdates"));
    }

    private final boolean isMyServiceRunning(Class<?> serviceClass) {
        Object systemService = getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            Intrinsics.checkNotNullExpressionValue(runningServiceInfo, "manager.getRunningServices(Integer.MAX_VALUE)");
            if (Intrinsics.areEqual(serviceClass.getName(), runningServiceInfo.service.getClassName())) {
                Log.i("isMyServiceRunning?", "true");
                return true;
            }
        }
        Log.i("isMyServiceRunning?", "false");
        return false;
    }

    private final void runServiceLocation() {
        startService(new Intent(this, (Class<?>) LocationService.class));
    }

    private final void startMyOwnForeground() {
        NotificationChannel notificationChannel = new NotificationChannel("example.permanence", "Background Service", 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        this.manager = notificationManager;
        NotificationManager notificationManager2 = null;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manager");
            notificationManager = null;
        }
        notificationManager.createNotificationChannel(notificationChannel);
        NeverEndingService neverEndingService = this;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(neverEndingService, "example.permanence");
        Notification notificationBuild = builder.setOngoing(true).setSmallIcon(R.mipmap.ic_bps).setColor(ContextCompat.getColor(neverEndingService, R.color.blue_completed)).setContentTitle("App is running in background").setPriority(1).setCategory(NotificationCompat.CATEGORY_SERVICE).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "notificationBuilder.setO…ICE)\n            .build()");
        NotificationManager notificationManager3 = this.manager;
        if (notificationManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manager");
        } else {
            notificationManager2 = notificationManager3;
        }
        notificationManager2.notify(9, builder.build());
        startForeground(9, notificationBuild);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTimer();
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.d("Duar", "location service will be restart");
        Intent intent = new Intent();
        intent.setAction("restartservice");
        intent.setClass(this, NeverEndingReceiver.class);
        sendBroadcast(intent);
        stopTimerTask();
        NotificationManager notificationManager = this.manager;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manager");
            notificationManager = null;
        }
        notificationManager.cancelAll();
    }

    private final void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() { // from class: id.go.bpsfasih.utils.services.NeverEndingService.startTimer.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
            }
        };
        this.timerTask = timerTask;
        Timer timer = this.timer;
        if (timer != null) {
            timer.schedule(timerTask, 1000L, 1000L);
        }
    }

    private final void stopTimerTask() {
        Timer timer = this.timer;
        if (timer != null) {
            if (timer != null) {
                timer.cancel();
            }
            this.timer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateToServer() {
        if (this.isUploadingTracking) {
            return;
        }
        new Thread(new Runnable() { // from class: id.go.bpsfasih.utils.services.NeverEndingService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NeverEndingService.updateToServer$lambda$0(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateToServer$lambda$0(NeverEndingService this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            try {
                this$0.isUploadingTracking = true;
                int iIntValue = ((Number) BuildersKt__BuildersKt.runBlocking$default(null, new NeverEndingService$updateToServer$1$syncedCount$1(new SyncLocationTrackingUseCase(new LocationTrackingRepositoryImpl(new LocationTrackingLocalDataSource(SurveyRoomDatabase.INSTANCE.getDatabase(this$0).locationTrackingDAO())), new TrackingRepositoryImpl(null, 1, null), new NeverEndingService$updateToServer$1$syncLocationTrackingUseCase$1(null)), null), 1, null)).intValue();
                if (iIntValue > 0) {
                    Log.d(CommonCons.REMOTE_CONFIG_LIVE_TRACKING, "Upload batch points: " + iIntValue);
                }
            } catch (Exception e) {
                Log.e(CommonCons.REMOTE_CONFIG_LIVE_TRACKING, "Failed to upload tracking batch", e);
            }
        } finally {
            this$0.isUploadingTracking = false;
        }
    }
}
