package androidx.recyclerview.selection;

import android.util.Log;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class OperationMonitor {
    private static final String TAG = "OperationMonitor";
    private final List<OnChangeListener> mListeners = new ArrayList();
    private final Resettable mResettable = new Resettable() { // from class: androidx.recyclerview.selection.OperationMonitor.1
        @Override // androidx.recyclerview.selection.Resettable
        public boolean isResetRequired() {
            return OperationMonitor.this.isResetRequired();
        }

        @Override // androidx.recyclerview.selection.Resettable
        public void reset() {
            OperationMonitor.this.reset();
        }
    };
    private int mNumOps = 0;

    public interface OnChangeListener {
        void onChanged();
    }

    synchronized void start() {
        int i = this.mNumOps + 1;
        this.mNumOps = i;
        if (i == 1) {
            notifyStateChanged();
        }
    }

    synchronized void stop() {
        int i = this.mNumOps;
        if (i == 0) {
            return;
        }
        int i2 = i - 1;
        this.mNumOps = i2;
        if (i2 == 0) {
            notifyStateChanged();
        }
    }

    synchronized void reset() {
        if (this.mNumOps > 0) {
            Log.w(TAG, "Resetting OperationMonitor with " + this.mNumOps + " active operations.");
        }
        this.mNumOps = 0;
        notifyStateChanged();
    }

    synchronized boolean isResetRequired() {
        return isStarted();
    }

    public synchronized boolean isStarted() {
        return this.mNumOps > 0;
    }

    public void addListener(OnChangeListener onChangeListener) {
        Preconditions.checkArgument(onChangeListener != null);
        this.mListeners.add(onChangeListener);
    }

    public void removeListener(OnChangeListener onChangeListener) {
        Preconditions.checkArgument(onChangeListener != null);
        this.mListeners.remove(onChangeListener);
    }

    void checkStarted(boolean z) {
        if (z) {
            Preconditions.checkState(this.mNumOps > 0);
        } else {
            Preconditions.checkState(this.mNumOps == 0);
        }
    }

    private void notifyStateChanged() {
        Iterator<OnChangeListener> it = this.mListeners.iterator();
        while (it.hasNext()) {
            it.next().onChanged();
        }
    }

    Resettable asResettable() {
        return this.mResettable;
    }
}
