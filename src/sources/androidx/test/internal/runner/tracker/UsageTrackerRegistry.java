package androidx.test.internal.runner.tracker;

import androidx.test.internal.runner.tracker.UsageTracker;
import androidx.test.internal.util.Checks;

@Deprecated
/* loaded from: classes5.dex */
public final class UsageTrackerRegistry {
    private static volatile UsageTracker instance = new UsageTracker.NoOpUsageTracker();

    @Deprecated
    public interface AxtVersions {
        public static final String ESPRESSO_VERSION = "na";
        public static final String RUNNER_VERSION = "na";
        public static final String SERVICES_VERSION = "na";
    }

    public static void registerInstance(UsageTracker tracker) {
        instance = (UsageTracker) Checks.checkNotNull(tracker);
    }

    public static UsageTracker getInstance() {
        return instance;
    }

    private UsageTrackerRegistry() {
    }
}
