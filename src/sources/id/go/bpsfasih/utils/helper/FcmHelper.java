package id.go.bpsfasih.utils.helper;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import id.go.bpsfasih.utils.helper.FcmHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FcmHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/FcmHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FcmHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: FcmHelper.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00040\u0006J\u001c\u0010\b\u001a\u00020\u00042\u0014\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00040\u0006¨\u0006\n"}, d2 = {"Lid/go/bpsfasih/utils/helper/FcmHelper$Companion;", "", "()V", "deleteToken", "", "callback", "Lkotlin/Function1;", "", "getToken", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void getToken(final Function1<? super String, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener() { // from class: id.go.bpsfasih.utils.helper.FcmHelper$Companion$$ExternalSyntheticLambda1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FcmHelper.Companion.getToken$lambda$0(callback, task);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void getToken$lambda$0(Function1 callback, Task task) {
            Intrinsics.checkNotNullParameter(callback, "$callback");
            Intrinsics.checkNotNullParameter(task, "task");
            if (!task.isSuccessful()) {
                Log.w("FOUR", "Fetching FCM registration token failed", task.getException());
                return;
            }
            String str = (String) task.getResult();
            Log.d("FOUR", "Token FCM : " + str);
            callback.invoke(str);
        }

        public final void deleteToken(final Function1<? super Boolean, Unit> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            FirebaseMessaging.getInstance().deleteToken().addOnCompleteListener(new OnCompleteListener() { // from class: id.go.bpsfasih.utils.helper.FcmHelper$Companion$$ExternalSyntheticLambda0
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    FcmHelper.Companion.deleteToken$lambda$1(callback, task);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void deleteToken$lambda$1(Function1 callback, Task task) {
            Intrinsics.checkNotNullParameter(callback, "$callback");
            Intrinsics.checkNotNullParameter(task, "task");
            if (!task.isSuccessful()) {
                callback.invoke(false);
            } else {
                callback.invoke(true);
            }
        }
    }
}
