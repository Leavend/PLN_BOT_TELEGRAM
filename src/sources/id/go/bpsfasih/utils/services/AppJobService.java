package id.go.bpsfasih.utils.services;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppJobService.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/utils/services/AppJobService;", "Landroid/app/job/JobService;", "()V", "doMyWork", "", "onStartJob", "", "params", "Landroid/app/job/JobParameters;", "onStopJob", "onTaskRemoved", "rootIntent", "Landroid/content/Intent;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AppJobService extends JobService {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int JOB_ID = 1;
    private static final int ONE_MIN = 60000;

    private final void doMyWork() {
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        return false;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        Intrinsics.checkNotNullParameter(rootIntent, "rootIntent");
        super.onTaskRemoved(rootIntent);
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters params) {
        Intrinsics.checkNotNullParameter(params, "params");
        doMyWork();
        return true;
    }

    /* compiled from: AppJobService.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lid/go/bpsfasih/utils/services/AppJobService$Companion;", "", "()V", "JOB_ID", "", "ONE_MIN", "schedule", "", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void schedule(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ComponentName componentName = new ComponentName(context, (Class<?>) AppJobService.class);
            Log.d("WakeUpService", "schedule");
            JobInfo.Builder overrideDeadline = new JobInfo.Builder(AppJobService.JOB_ID, componentName).setMinimumLatency(1000L).setOverrideDeadline(AppJobService.ONE_MIN * 1);
            Intrinsics.checkNotNull(overrideDeadline);
            overrideDeadline.setRequiresDeviceIdle(true);
            Object systemService = context.getSystemService("jobscheduler");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.job.JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) systemService;
            Object systemService2 = context.getSystemService("jobscheduler");
            Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.app.job.JobScheduler");
            if (((JobScheduler) systemService2).getPendingJob(1) == null) {
                Log.d("WakeUpService", "add schedule");
                jobScheduler.schedule(overrideDeadline.build());
            }
        }
    }
}
