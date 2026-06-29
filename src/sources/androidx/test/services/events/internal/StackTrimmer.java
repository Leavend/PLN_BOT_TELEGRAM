package androidx.test.services.events.internal;

import android.util.Log;
import org.junit.runner.notification.Failure;

/* loaded from: classes5.dex */
public final class StackTrimmer {
    static final int MAX_TRACE_SIZE = 65536;
    private static final String TAG = "StackTrimmer";

    private StackTrimmer() {
    }

    public static String getTrimmedStackTrace(Failure failure) {
        String trimmedStackTrace = Throwables.getTrimmedStackTrace(failure.getException());
        if (trimmedStackTrace.length() <= 65536) {
            return trimmedStackTrace;
        }
        Log.w(TAG, String.format("Stack trace too long, trimmed to first %s characters.", 65536));
        return trimmedStackTrace.substring(0, 65536) + "\n";
    }

    public static String getTrimmedMessage(Failure failure) {
        String message = failure.getMessage();
        if (message == null || message.length() <= 65536) {
            return message;
        }
        Log.w(TAG, String.format("Message too long, trimmed to first %s characters.", 65536));
        return message.substring(0, 65536) + "\n";
    }
}
