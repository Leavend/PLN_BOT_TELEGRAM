package androidx.test.espresso.core.internal.deps.guava.util.concurrent;

import sun.misc.Unsafe;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class AbstractFuture$UnsafeAtomicHelper$$ExternalSyntheticBackportWithForwarding1 {
    public static /* synthetic */ boolean m(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
