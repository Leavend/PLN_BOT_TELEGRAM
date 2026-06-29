package id.go.bpsfasih.utils.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import id.go.bpsfasih.R;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: NotificationHelper.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0003J&\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/utils/helper/NotificationHelper;", "", "()V", "baseNotification", "", "context", "Landroid/content/Context;", MessageBundle.TITLE_ENTRY, "", "body", "ongoing", "", "autoCancel", "notificationId", "", "displayNotificationDismissable", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NotificationHelper {
    public static final int $stable = 0;
    public static final NotificationHelper INSTANCE = new NotificationHelper();

    private NotificationHelper() {
    }

    public final void displayNotificationDismissable(Context context, String title, String body, int notificationId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(body, "body");
        baseNotification(context, title, body, false, true, notificationId);
    }

    private final void baseNotification(Context context, String title, String body, boolean ongoing, boolean autoCancel, int notificationId) {
        Intent intent = new Intent(context, (Class<?>) HomePageActivity.class);
        intent.setFlags(268468224);
        PendingIntent activity = PendingIntent.getActivity(context, 0, intent, AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Object systemService = context.getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        String string = context.getString(R.string.channel_id);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.channel_id)");
        String string2 = context.getString(R.string.channel_name);
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.channel_name)");
        String string3 = context.getString(R.string.channel_desc);
        Intrinsics.checkNotNullExpressionValue(string3, "context.getString(R.string.channel_desc)");
        Uri defaultUri = RingtoneManager.getDefaultUri(2);
        Intrinsics.checkNotNullExpressionValue(defaultUri, "getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)");
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(string, string2, 2);
            notificationChannel.setDescription(string3);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.getNotificationChannel(string).canBypassDnd();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, string);
        NotificationCompat.Builder color = builder.setSmallIcon(R.mipmap.ic_bps).setColor(ContextCompat.getColor(context, R.color.blue_completed));
        String upperCase = title.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
        NotificationCompat.Builder ongoing2 = color.setContentTitle(upperCase).setOngoing(ongoing);
        String str = body;
        ongoing2.setContentText(str).setDefaults(1).setStyle(new NotificationCompat.BigTextStyle().bigText(str)).setWhen(System.currentTimeMillis()).setAutoCancel(autoCancel).setContentIntent(activity);
        RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(2)).play();
        builder.setSound(defaultUri);
        notificationManager.notify(notificationId, builder.build());
    }
}
