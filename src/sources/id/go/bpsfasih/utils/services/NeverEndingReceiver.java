package id.go.bpsfasih.utils.services;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.utils.helper.ServiceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: NeverEndingReceiver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001c\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\n"}, d2 = {"Lid/go/bpsfasih/utils/services/NeverEndingReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "reDownload", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NeverEndingReceiver extends BroadcastReceiver {
    public static final int $stable = 0;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action;
        if (intent == null) {
            action = "null";
        } else {
            action = intent.getAction();
            Intrinsics.checkNotNull(action);
        }
        Log.d("wakeup", action);
        if (StringsKt.equals$default(intent != null ? intent.getAction() : null, "reject_assignment", false, 2, null)) {
            reDownload(context, intent);
            Log.d("reject_assignment", "Ok");
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            if (context != null) {
                AppJobService.INSTANCE.schedule(context);
            }
        } else if (context != null) {
            context.startService(new Intent(context, (Class<?>) AppServices.class));
        }
        if (FasihApp.INSTANCE.getSession().is_login()) {
            Log.d("never_ending_receiver", "Service tried to stop");
        } else {
            if (ServiceHelper.INSTANCE.isMyServiceRunning(NeverEndingService.class)) {
                return;
            }
            Log.d("never_ending_receiver", "location service will be stop in service");
        }
    }

    private final void reDownload(Context context, Intent intent) {
        if (intent != null) {
            intent.getStringExtra(MessageBundle.TITLE_ENTRY);
        }
        if (intent != null) {
            intent.getStringExtra("body");
        }
        String stringExtra = intent != null ? intent.getStringExtra("assignmentId") : null;
        Integer numValueOf = intent != null ? Integer.valueOf(intent.getIntExtra("notificationId", 0)) : null;
        if (context != null && stringExtra != null && numValueOf != null && numValueOf.intValue() != 0) {
            Object systemService = context.getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            ((NotificationManager) systemService).cancel(numValueOf.intValue());
            return;
        }
        Object systemService2 = context != null ? context.getSystemService("notification") : null;
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService2;
        if (numValueOf != null && numValueOf.intValue() != 0) {
            notificationManager.cancel(numValueOf.intValue());
        } else {
            notificationManager.cancelAll();
        }
    }
}
