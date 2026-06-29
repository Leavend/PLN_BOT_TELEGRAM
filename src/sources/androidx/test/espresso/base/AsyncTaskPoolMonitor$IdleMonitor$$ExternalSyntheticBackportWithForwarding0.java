package androidx.test.espresso.base;

import androidx.arch.core.executor.TaskExecutorWithFakeMainThread$1$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class AsyncTaskPoolMonitor$IdleMonitor$$ExternalSyntheticBackportWithForwarding0 {
    public static /* synthetic */ boolean m(AtomicReference atomicReference, Object obj, Object obj2) {
        while (!TaskExecutorWithFakeMainThread$1$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, obj, obj2)) {
            if (atomicReference.get() != obj) {
                return false;
            }
        }
        return true;
    }
}
