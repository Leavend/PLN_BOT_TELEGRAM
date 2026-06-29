package androidx.test.espresso.base;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.test.espresso.NoActivityResumedException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.core.internal.deps.guava.collect.ImmutableList;
import androidx.test.espresso.core.internal.deps.guava.collect.UnmodifiableIterator;

/* loaded from: classes5.dex */
final class ConfigurationSynchronizationUtils {
    private static final ImmutableList<Integer> ORIENTATION_WAIT_TIMES = ImmutableList.of((Object) 10, (Object) 50, (Object) 100, (Object) Integer.valueOf(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION), (Object) 500, (Object) 2000);
    private static final String TAG = "ConfigurationSynchronizationUtils";

    private ConfigurationSynchronizationUtils() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void waitForConfigurationChangesOnActivity(Activity activity, UiController uiController, Context context) {
        int i;
        if (activity.isInMultiWindowMode() || (i = context.getResources().getConfiguration().orientation) == activity.getResources().getConfiguration().orientation) {
            return;
        }
        UnmodifiableIterator it = ORIENTATION_WAIT_TIMES.iterator();
        while (it.hasNext()) {
            long jIntValue = ((Integer) it.next()).intValue();
            Log.w(TAG, "Activity's orientation does not match the application's - waiting: " + jIntValue + "ms for orientation to update.");
            uiController.loopMainThreadForAtLeast(jIntValue);
            if (i == activity.getResources().getConfiguration().orientation) {
                return;
            }
        }
        throw new NoActivityResumedException("Timed out waiting for Activity's orientation to update.");
    }
}
