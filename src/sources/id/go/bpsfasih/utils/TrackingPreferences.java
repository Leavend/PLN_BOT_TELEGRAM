package id.go.bpsfasih.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TrackingPreferences.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0011J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0015\u001a\u0004\u0018\u00010\tJ\u0016\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tJ\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\t¨\u0006\u001b"}, d2 = {"Lid/go/bpsfasih/utils/TrackingPreferences;", "", "()V", "clearTracking", "", "context", "Landroid/content/Context;", "endTrackingSession", "ensureTrackingSession", "", "getPreferences", "Landroid/content/SharedPreferences;", "getTrackingAssignmentId", "getTrackingSession", "getTrackingStartDate", "getTrackingUserId", "isTrackingActive", "", "setTrackingActive", "isActive", "setTrackingAssignmentId", "assignmentId", "setTrackingStartDate", "date", "setTrackingUserId", "userId", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TrackingPreferences {
    public static final int $stable = 0;
    private static final String KEY_ASSIGNMENT_ID = "tracking_assignment_id";
    private static final String KEY_IS_ACTIVE = "is_tracking_active";
    private static final String KEY_START_DATE = "tracking_start_date";
    private static final String KEY_TRACKING_ACTIVE_SESSION = "tracking_active_session";
    private static final String KEY_USER_ID = "tracking_user_id";
    private static final String PREF_NAME = "location_tracking_prefs";

    private final SharedPreferences getPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPrefere…ME, Context.MODE_PRIVATE)");
        return sharedPreferences;
    }

    public final void setTrackingActive(Context context, boolean isActive) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().putBoolean(KEY_IS_ACTIVE, isActive).apply();
    }

    public final boolean isTrackingActive(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getBoolean(KEY_IS_ACTIVE, false);
    }

    public final void setTrackingStartDate(Context context, String date) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(date, "date");
        getPreferences(context).edit().putString(KEY_START_DATE, date).apply();
    }

    public final String getTrackingStartDate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = getPreferences(context).getString(KEY_START_DATE, "");
        return string == null ? "" : string;
    }

    public final void setTrackingUserId(Context context, String userId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(userId, "userId");
        getPreferences(context).edit().putString(KEY_USER_ID, userId).apply();
    }

    public final String getTrackingUserId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = getPreferences(context).getString(KEY_USER_ID, "");
        return string == null ? "" : string;
    }

    public final void setTrackingAssignmentId(Context context, String assignmentId) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (assignmentId != null) {
            getPreferences(context).edit().putString(KEY_ASSIGNMENT_ID, assignmentId).apply();
        } else {
            getPreferences(context).edit().remove(KEY_ASSIGNMENT_ID).apply();
        }
    }

    public final String getTrackingAssignmentId(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getString(KEY_ASSIGNMENT_ID, null);
    }

    public final String ensureTrackingSession(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences preferences = getPreferences(context);
        String string = preferences.getString(KEY_TRACKING_ACTIVE_SESSION, null);
        String str = string;
        if (!(str == null || StringsKt.isBlank(str))) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string2, "randomUUID().toString()");
        preferences.edit().putString(KEY_TRACKING_ACTIVE_SESSION, string2).apply();
        return string2;
    }

    public final String getTrackingSession(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return getPreferences(context).getString(KEY_TRACKING_ACTIVE_SESSION, null);
    }

    public final void endTrackingSession(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().remove(KEY_TRACKING_ACTIVE_SESSION).apply();
    }

    public final void clearTracking(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        getPreferences(context).edit().clear().apply();
    }
}
