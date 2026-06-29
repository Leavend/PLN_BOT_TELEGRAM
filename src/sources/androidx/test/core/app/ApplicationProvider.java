package androidx.test.core.app;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;

/* loaded from: classes5.dex */
public final class ApplicationProvider {
    private ApplicationProvider() {
    }

    public static <T extends Context> T getApplicationContext() {
        return (T) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}
