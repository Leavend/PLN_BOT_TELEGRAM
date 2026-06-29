package androidx.test.espresso;

import android.os.Looper;
import android.util.Log;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public final class IdlingRegistry {
    private static final String TAG = "IdlingRegistry";
    private static final IdlingRegistry instance = new IdlingRegistry();
    private final Set<IdlingResource> resources = Collections.synchronizedSet(new HashSet());
    private final Set<Looper> loopers = Collections.synchronizedSet(new HashSet());

    IdlingRegistry() {
    }

    public static IdlingRegistry getInstance() {
        return instance;
    }

    public boolean register(IdlingResource... idlingResources) {
        if (idlingResources == null) {
            throw new NullPointerException("idlingResources cannot be null!");
        }
        Log.d(TAG, "Registering idling resources: " + Arrays.toString(idlingResources));
        return this.resources.addAll(Arrays.asList(idlingResources));
    }

    public boolean unregister(IdlingResource... idlingResources) {
        if (idlingResources == null) {
            throw new NullPointerException("idlingResources cannot be null!");
        }
        Log.d(TAG, "Unregistering idling resources: " + Arrays.toString(idlingResources));
        return this.resources.removeAll(Arrays.asList(idlingResources));
    }

    public void registerLooperAsIdlingResource(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("looper cannot be null!");
        }
        if (Looper.getMainLooper() == looper) {
            throw new IllegalArgumentException("Not intended for use with main looper!");
        }
        this.loopers.add(looper);
    }

    public boolean unregisterLooperAsIdlingResource(Looper looper) {
        if (looper == null) {
            throw new NullPointerException("looper cannot be null!");
        }
        return this.loopers.remove(looper);
    }

    public Collection<IdlingResource> getResources() {
        HashSet hashSet;
        new HashSet();
        synchronized (this.resources) {
            hashSet = new HashSet(this.resources);
        }
        return hashSet;
    }

    public Collection<Looper> getLoopers() {
        HashSet hashSet;
        new HashSet();
        synchronized (this.loopers) {
            hashSet = new HashSet(this.loopers);
        }
        return hashSet;
    }
}
