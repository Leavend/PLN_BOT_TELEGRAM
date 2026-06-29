package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import java.util.Collection;
import java.util.LinkedHashSet;

/* loaded from: classes3.dex */
public class TileStates {
    private boolean mDone;
    private int mExpired;
    private int mNotFound;
    private Collection<Runnable> mRunAfters = new LinkedHashSet();
    private int mScaled;
    private int mTotal;
    private int mUpToDate;

    public Collection<Runnable> getRunAfters() {
        return this.mRunAfters;
    }

    public void initialiseLoop() {
        this.mDone = false;
        this.mTotal = 0;
        this.mUpToDate = 0;
        this.mExpired = 0;
        this.mScaled = 0;
        this.mNotFound = 0;
    }

    public void finaliseLoop() {
        this.mDone = true;
        for (Runnable runnable : this.mRunAfters) {
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    public void handleTile(Drawable drawable) {
        this.mTotal++;
        if (drawable == null) {
            this.mNotFound++;
            return;
        }
        int state = ExpirableBitmapDrawable.getState(drawable);
        if (state == -4) {
            this.mNotFound++;
            return;
        }
        if (state == -3) {
            this.mScaled++;
        } else if (state == -2) {
            this.mExpired++;
        } else {
            if (state == -1) {
                this.mUpToDate++;
                return;
            }
            throw new IllegalArgumentException("Unknown state: " + state);
        }
    }

    public boolean isDone() {
        return this.mDone;
    }

    public int getTotal() {
        return this.mTotal;
    }

    public int getUpToDate() {
        return this.mUpToDate;
    }

    public int getExpired() {
        return this.mExpired;
    }

    public int getScaled() {
        return this.mScaled;
    }

    public int getNotFound() {
        return this.mNotFound;
    }

    public String toString() {
        return this.mDone ? "TileStates: " + this.mTotal + " = " + this.mUpToDate + "(U) + " + this.mExpired + "(E) + " + this.mScaled + "(S) + " + this.mNotFound + "(N)" : "TileStates";
    }
}
