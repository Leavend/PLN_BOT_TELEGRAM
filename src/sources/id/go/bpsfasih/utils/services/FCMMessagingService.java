package id.go.bpsfasih.utils.services;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.utils.helper.DeviceHelper;
import id.go.bpsfasih.utils.helper.NotificationHelper;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.bouncycastle.i18n.MessageBundle;

/* compiled from: FCMMessagingService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u0003J\u001e\u0010\b\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\r"}, d2 = {"Lid/go/bpsfasih/utils/services/FCMMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "categoryChat", "", "data", "", "", "categoryRejectAssignment", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FCMMessagingService extends FirebaseMessagingService {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
        super.onMessageReceived(remoteMessage);
        Log.d("FOUR", "onMessageReceived: mendapat pesan baru " + remoteMessage.getData());
        Map<String, String> data = remoteMessage.getData();
        Intrinsics.checkNotNullExpressionValue(data, "remoteMessage.data");
        String str = data.get("category");
        if (Intrinsics.areEqual(str, "chat")) {
            categoryChat(data);
            return;
        }
        if (Intrinsics.areEqual(str, "reject")) {
            categoryRejectAssignment(data);
            return;
        }
        Companion companion = INSTANCE;
        FCMMessagingService fCMMessagingService = this;
        String str2 = data.get(MessageBundle.TITLE_ENTRY);
        if (str2 == null) {
            str2 = "FASIH";
        }
        String str3 = data.get("message");
        if (str3 == null) {
            str3 = "Ada notifikasi di FASIH";
        }
        companion.showGeneralNotification(fCMMessagingService, str2, str3, new Random().nextInt(10000000));
    }

    private final void categoryChat(Map<String, String> data) {
        String str = data.get("message");
        String str2 = null;
        List listSplit$default = str != null ? StringsKt.split$default((CharSequence) str, new String[]{"#*&#@~!#^&%*^"}, false, 0, 6, (Object) null) : null;
        Set<String> sessionList = FasihApp.INSTANCE.getSession().getSessionList(CommonCons.INSTANCE.getSESSION_LIST_GROUP(), new HashSet());
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME());
        if ((listSplit$default != null ? Integer.valueOf(listSplit$default.size()) : null) != null) {
            str2 = (String) listSplit$default.get(0);
        }
        if (Intrinsics.areEqual(data.get("senderName"), sessionString) && Intrinsics.areEqual(str2, DeviceHelper.INSTANCE.getDEVICE_ID())) {
            return;
        }
        if (sessionList == null || sessionList.contains(String.valueOf(data.get("groupId")))) {
            new SimpleDateFormat("EEE HH:mm d MMM, yyyy").format(new Date());
            if (!Intrinsics.areEqual(data.get("senderName"), sessionString) || Intrinsics.areEqual(str2, DeviceHelper.INSTANCE.getDEVICE_ID())) {
                NotificationHelper notificationHelper = NotificationHelper.INSTANCE;
                FCMMessagingService fCMMessagingService = this;
                String str3 = data.get(MessageBundle.TITLE_ENTRY);
                if (str3 == null) {
                    str3 = "Notification Title";
                }
                String str4 = data.get("body");
                if (str4 == null) {
                    str4 = "Notification Body";
                }
                notificationHelper.displayNotificationDismissable(fCMMessagingService, str3, str4, 1);
            }
        }
    }

    private final void categoryRejectAssignment(Map<String, String> data) {
        String str;
        if (data == null || (str = data.get("assignmentId")) == null) {
            return;
        }
        Companion companion = INSTANCE;
        FCMMessagingService fCMMessagingService = this;
        String str2 = data.get(MessageBundle.TITLE_ENTRY);
        if (str2 == null) {
            str2 = "Notification Title";
        }
        String str3 = str2;
        String str4 = data.get("message");
        if (str4 == null) {
            str4 = "Notification Body";
        }
        companion.downloadAssignment(fCMMessagingService, str3, str4, str, new Random().nextInt(10000000));
    }

    /* compiled from: FCMMessagingService.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ&\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/utils/services/FCMMessagingService$Companion;", "", "()V", "downloadAssignment", "", "context", "Landroid/content/Context;", MessageBundle.TITLE_ENTRY, "", "body", "assignmentId", "notificationId", "", "showGeneralNotification", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void showGeneralNotification(Context context, String title, String body, int notificationId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(body, "body");
            NotificationHelper.INSTANCE.displayNotificationDismissable(context, title, body, notificationId);
        }

        public final void downloadAssignment(Context context, String title, String body, String assignmentId, int notificationId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(body, "body");
            Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new FCMMessagingService$Companion$downloadAssignment$1(assignmentId, context, title, body, notificationId, null), 2, null);
        }
    }
}
