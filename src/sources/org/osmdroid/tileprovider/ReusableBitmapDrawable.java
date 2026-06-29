package org.osmdroid.tileprovider;

import android.graphics.Bitmap;

/* loaded from: classes3.dex */
public class ReusableBitmapDrawable extends ExpirableBitmapDrawable {
    private boolean mBitmapRecycled;
    private int mUsageRefCount;

    public ReusableBitmapDrawable(Bitmap bitmap) {
        super(bitmap);
        this.mBitmapRecycled = false;
        this.mUsageRefCount = 0;
    }

    public void beginUsingDrawable() {
        synchronized (this) {
            this.mUsageRefCount++;
        }
    }

    public void finishUsingDrawable() {
        synchronized (this) {
            int i = this.mUsageRefCount - 1;
            this.mUsageRefCount = i;
            if (i < 0) {
                throw new IllegalStateException("Unbalanced endUsingDrawable() called.");
            }
        }
    }

    public Bitmap tryRecycle() {
        synchronized (this) {
            if (this.mUsageRefCount != 0) {
                return null;
            }
            this.mBitmapRecycled = true;
            return getBitmap();
        }
    }

    public boolean isBitmapValid() {
        boolean z;
        synchronized (this) {
            z = !this.mBitmapRecycled;
        }
        return z;
    }
}
