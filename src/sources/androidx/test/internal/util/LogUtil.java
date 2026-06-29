package androidx.test.internal.util;

import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import androidx.test.internal.util.ProcSummary;
import com.codekidlabs.storagechooser.utils.MemoryUtil;

/* loaded from: classes5.dex */
public final class LogUtil {
    private static volatile String myProcName;

    interface Supplier<T> {
        T get();
    }

    static /* synthetic */ String lambda$logDebug$0(String str) {
        return str;
    }

    public static void logDebug(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier() { // from class: androidx.test.internal.util.LogUtil$$ExternalSyntheticLambda1
            @Override // androidx.test.internal.util.LogUtil.Supplier
            public final Object get() {
                return LogUtil.lambda$logDebug$0(message);
            }
        }, args);
    }

    private static void logDebug(String tag, Supplier<String> msgSupplier, Object... args) {
        if (isLoggable(tag, 3)) {
            Log.d(tag, String.format(msgSupplier.get(), args));
        }
    }

    static /* synthetic */ String lambda$logDebugWithProcess$1(String str) {
        return str + " in " + procName();
    }

    public static void logDebugWithProcess(String tag, final String message, Object... args) {
        logDebug(tag, (Supplier<String>) new Supplier() { // from class: androidx.test.internal.util.LogUtil$$ExternalSyntheticLambda0
            @Override // androidx.test.internal.util.LogUtil.Supplier
            public final Object get() {
                return LogUtil.lambda$logDebugWithProcess$1(message);
            }
        }, args);
    }

    private static final String procName() {
        String str;
        String str2 = myProcName;
        if (str2 != null) {
            return str2;
        }
        try {
            str = ProcSummary.summarize(MemoryUtil.SELF_DIR_NAME).cmdline;
        } catch (ProcSummary.SummaryException unused) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return (str.length() <= 64 || !str.contains("-classpath")) ? str : "robolectric";
    }

    private static boolean isLoggable(String tag, final int level) {
        if (tag.length() > 23) {
            tag = tag.substring(0, 22);
        }
        return Log.isLoggable(tag, level);
    }
}
