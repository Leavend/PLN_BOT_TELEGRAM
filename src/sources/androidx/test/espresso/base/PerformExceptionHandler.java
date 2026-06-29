package androidx.test.espresso.base;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.view.View;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class PerformExceptionHandler extends DefaultFailureHandler.TypedFailureHandler<PerformException> {
    private final Context appContext;

    public PerformExceptionHandler(Context context, Class<PerformException> cls) {
        super(cls);
        this.appContext = (Context) Preconditions.checkNotNull(context);
    }

    private static float getAnimatorDurationScale(ContentResolver contentResolver) {
        if (isJellyBeanMR1OrHigher()) {
            return getSetting(contentResolver, "animator_duration_scale", "animator_duration_scale");
        }
        return 0.0f;
    }

    private static float getGlobalSetting(ContentResolver contentResolver, String str) {
        try {
            return Settings.Global.getFloat(contentResolver, str);
        } catch (Settings.SettingNotFoundException unused) {
            return 0.0f;
        }
    }

    private static float getSetting(ContentResolver contentResolver, String str, String str2) {
        return isJellyBeanMR1OrHigher() ? getGlobalSetting(contentResolver, str) : getSystemSetting(contentResolver, str2);
    }

    private static float getSystemSetting(ContentResolver contentResolver, String str) {
        try {
            return Settings.System.getFloat(contentResolver, str);
        } catch (Settings.SettingNotFoundException unused) {
            return 0.0f;
        }
    }

    private static float getTransitionAnimationScale(ContentResolver contentResolver) {
        return getSetting(contentResolver, "transition_animation_scale", "transition_animation_scale");
    }

    private static float getWindowAnimationScale(ContentResolver contentResolver) {
        return getSetting(contentResolver, "window_animation_scale", "window_animation_scale");
    }

    private static boolean isAnimationAndTransitionDisabled(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return isEqualToZero(getTransitionAnimationScale(contentResolver)) && isEqualToZero(getWindowAnimationScale(contentResolver)) && isEqualToZero(getAnimatorDurationScale(contentResolver));
    }

    private static boolean isEqualToZero(float f) {
        return Float.compare(Math.abs(f), 0.0f) == 0;
    }

    private static boolean isJellyBeanMR1OrHigher() {
        return true;
    }

    /* renamed from: handleSafely, reason: avoid collision after fix types in other method */
    public void handleSafely2(PerformException performException, Matcher<View> matcher) {
        StringBuilder sb = new StringBuilder();
        if (!isAnimationAndTransitionDisabled(this.appContext)) {
            sb.append("Animations or transitions are enabled on the target device.\nFor more info check: https://developer.android.com/training/testing/espresso/setup#set-up-environment\n\n");
        }
        sb.append(matcher);
        throw new PerformException.Builder().from(performException).withViewDescription(sb.toString()).build();
    }

    @Override // androidx.test.espresso.base.DefaultFailureHandler.TypedFailureHandler
    public /* bridge */ /* synthetic */ void handleSafely(PerformException performException, Matcher matcher) {
        handleSafely2(performException, (Matcher<View>) matcher);
    }
}
