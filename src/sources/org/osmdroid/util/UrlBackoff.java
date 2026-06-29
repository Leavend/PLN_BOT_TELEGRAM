package org.osmdroid.util;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class UrlBackoff {
    private static final long[] mExponentialBackoffDurationInMillisDefault = {5000, 15000, 60000, 120000, 300000};
    private long[] mExponentialBackoffDurationInMillis = mExponentialBackoffDurationInMillisDefault;
    private final Map<String, Delay> mDelays = new HashMap();

    public void next(String str) {
        Delay delay;
        synchronized (this.mDelays) {
            delay = this.mDelays.get(str);
        }
        if (delay == null) {
            Delay delay2 = new Delay(this.mExponentialBackoffDurationInMillis);
            synchronized (this.mDelays) {
                this.mDelays.put(str, delay2);
            }
            return;
        }
        delay.next();
    }

    public Delay remove(String str) {
        Delay delayRemove;
        synchronized (this.mDelays) {
            delayRemove = this.mDelays.remove(str);
        }
        return delayRemove;
    }

    public boolean shouldWait(String str) {
        Delay delay;
        synchronized (this.mDelays) {
            delay = this.mDelays.get(str);
        }
        return delay != null && delay.shouldWait();
    }

    public void clear() {
        synchronized (this.mDelays) {
            this.mDelays.clear();
        }
    }

    public void setExponentialBackoffDurationInMillis(long[] jArr) {
        this.mExponentialBackoffDurationInMillis = jArr;
    }
}
