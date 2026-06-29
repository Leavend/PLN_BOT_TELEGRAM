package androidx.test.internal.platform.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import androidx.test.platform.app.InstrumentationRegistry;

/* loaded from: classes5.dex */
public interface ActivityInvoker {
    void finishActivity(Activity activity);

    Instrumentation.ActivityResult getActivityResult();

    Intent getIntentForActivity(Class<? extends Activity> activityClass);

    void pauseActivity(Activity activity);

    void recreateActivity(Activity activity);

    void resumeActivity(Activity activity);

    void startActivity(Intent intent);

    void startActivity(Intent intent, Bundle activityOptions);

    void startActivityForResult(Intent intent);

    void startActivityForResult(Intent intent, Bundle activityOptions);

    void stopActivity(Activity activity);

    /* renamed from: androidx.test.internal.platform.app.ActivityInvoker$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static Intent $default$getIntentForActivity(ActivityInvoker _this, Class activityClass) {
            Intent intentMakeMainActivity = Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getTargetContext(), (Class<?>) activityClass));
            return InstrumentationRegistry.getInstrumentation().getTargetContext().getPackageManager().resolveActivity(intentMakeMainActivity, 0) != null ? intentMakeMainActivity : Intent.makeMainActivity(new ComponentName(InstrumentationRegistry.getInstrumentation().getContext(), (Class<?>) activityClass));
        }
    }
}
