package id.go.bpsfasih.utils.helper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: FirebaseAnalitycHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/FirebaseAnalitycHelper;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FirebaseAnalitycHelper {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: FirebaseAnalitycHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\"\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n¨\u0006\u0010"}, d2 = {"Lid/go/bpsfasih/utils/helper/FirebaseAnalitycHelper$Companion;", "", "()V", "clearUser", "", "context", "Landroid/content/Context;", "init", "send", "eventName", "", "params", "Landroid/os/Bundle;", "sendFormEngineEvent", "assignmentId", "jsonParam", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void init(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            FirebaseAnalytics.getInstance(context).setUserId(FasihApp.INSTANCE.getSession().getUserId());
            FirebaseAnalytics.getInstance(context).setUserProperty("email", FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL()));
        }

        public final void clearUser(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            FirebaseAnalytics.getInstance(context).setUserId(null);
            FirebaseAnalytics.getInstance(context).setUserProperty("email", null);
        }

        public static /* synthetic */ void send$default(Companion companion, Context context, String str, Bundle bundle, int i, Object obj) {
            if ((i & 4) != 0) {
                bundle = null;
            }
            companion.send(context, str, bundle);
        }

        public final void send(Context context, String eventName, Bundle params) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(eventName, "eventName");
            FirebaseAnalytics.getInstance(context).logEvent(eventName, params);
        }

        public final void sendFormEngineEvent(Context context, String assignmentId, String jsonParam) throws JSONException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
            Intrinsics.checkNotNullParameter(jsonParam, "jsonParam");
            Bundle bundle = new Bundle();
            bundle.putString("assignment_id", assignmentId);
            bundle.putString("user_id", FasihApp.INSTANCE.getSession().getUserId());
            bundle.putString("email", FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_EMAIL()));
            bundle.putInt("mobile_version_code", 126);
            bundle.putString("mobile_version_name", BuildConfig.VERSION_NAME);
            bundle.putLong("local_timestamp", System.currentTimeMillis());
            if (jsonParam.length() > 0) {
                try {
                    JSONObject jSONObject = new JSONObject(jsonParam);
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        Object value = jSONObject.get(next);
                        if (value instanceof String) {
                            bundle.putString("custom_param_" + next, (String) value);
                        } else if (value instanceof Integer) {
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            bundle.putInt("custom_param_" + next, ((Number) value).intValue());
                        } else if (value instanceof Long) {
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            bundle.putLong("custom_param_" + next, ((Number) value).longValue());
                        } else if (value instanceof Double) {
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            bundle.putDouble("custom_param_" + next, ((Number) value).doubleValue());
                        } else if (value instanceof Boolean) {
                            Intrinsics.checkNotNullExpressionValue(value, "value");
                            bundle.putBoolean("custom_param_" + next, ((Boolean) value).booleanValue());
                        } else {
                            bundle.putString("custom_param_" + next, value.toString());
                            Log.w("AnalyticsParams", "Parameter '" + next + "' dengan tipe " + value.getClass().getSimpleName() + " dikonversi ke String.");
                        }
                    }
                } catch (Exception e) {
                    String localizedMessage = e.getLocalizedMessage();
                    if (localizedMessage == null) {
                        localizedMessage = "Unknown JSON parsing error (" + jsonParam + ")";
                    }
                    bundle.putString("error_parsing_param", localizedMessage);
                }
            }
            send(context, "event_form_engine", bundle);
        }
    }
}
