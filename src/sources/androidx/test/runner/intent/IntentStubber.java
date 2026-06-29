package androidx.test.runner.intent;

import android.app.Instrumentation;
import android.content.Intent;

/* loaded from: classes5.dex */
public interface IntentStubber {
    Instrumentation.ActivityResult getActivityResultForIntent(Intent intent);
}
