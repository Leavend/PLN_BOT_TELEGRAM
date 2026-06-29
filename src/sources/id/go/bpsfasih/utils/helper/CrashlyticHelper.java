package id.go.bpsfasih.utils.helper;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashlyticHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/CrashlyticHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CrashlyticHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirebaseCrashlytics crashlytics;

    /* compiled from: CrashlyticHelper.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u0012\u0010\n\u001a\u00020\b2\n\u0010\u000b\u001a\u00060\fj\u0002`\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/utils/helper/CrashlyticHelper$Companion;", "", "()V", "crashlytics", "Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "getCrashlytics", "()Lcom/google/firebase/crashlytics/FirebaseCrashlytics;", "clearUserId", "", "init", "sendException", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FirebaseCrashlytics getCrashlytics() {
            return CrashlyticHelper.crashlytics;
        }

        public final void init() {
            getCrashlytics().setUserId(FasihApp.INSTANCE.getSession().getUserId());
            FirebaseCrashlytics crashlytics = getCrashlytics();
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL());
            if (sessionString == null) {
                sessionString = "-";
            }
            crashlytics.setCustomKey("email", sessionString);
        }

        public final void clearUserId() {
            getCrashlytics().setUserId("");
            getCrashlytics().setCustomKey("email", "");
        }

        public final void sendException(Exception e) {
            Intrinsics.checkNotNullParameter(e, "e");
            getCrashlytics().recordException(e);
        }
    }

    static {
        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
        Intrinsics.checkNotNullExpressionValue(firebaseCrashlytics, "getInstance()");
        crashlytics = firebaseCrashlytics;
    }
}
