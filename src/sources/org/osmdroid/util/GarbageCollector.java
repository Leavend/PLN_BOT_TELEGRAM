package org.osmdroid.util;

import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public class GarbageCollector {
    private final Runnable mAction;
    private final AtomicBoolean mRunning = new AtomicBoolean(false);

    public GarbageCollector(Runnable runnable) {
        this.mAction = runnable;
    }

    public boolean gc() {
        if (this.mRunning.getAndSet(true)) {
            return false;
        }
        Thread thread = new Thread(new Runnable() { // from class: org.osmdroid.util.GarbageCollector.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    GarbageCollector.this.mAction.run();
                } finally {
                    GarbageCollector.this.mRunning.set(false);
                }
            }
        });
        thread.setName("GarbageCollector");
        thread.setPriority(1);
        thread.start();
        return true;
    }

    public boolean isRunning() {
        return this.mRunning.get();
    }
}
