package org.osmdroid.tileprovider;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.modules.ConfigurablePriorityThreadFactory;

/* loaded from: classes3.dex */
public class BitmapPool {
    private static final BitmapPool sInstance = new BitmapPool();
    private final LinkedList<Bitmap> mPool = new LinkedList<>();
    private final ExecutorService mExecutor = Executors.newFixedThreadPool(1, new ConfigurablePriorityThreadFactory(1, getClass().getName()));

    private BitmapPool() {
    }

    public static BitmapPool getInstance() {
        return sInstance;
    }

    public void returnDrawableToPool(ReusableBitmapDrawable reusableBitmapDrawable) {
        Bitmap bitmapTryRecycle = reusableBitmapDrawable.tryRecycle();
        if (bitmapTryRecycle == null || bitmapTryRecycle.isRecycled() || !bitmapTryRecycle.isMutable() || bitmapTryRecycle.getConfig() == null) {
            if (bitmapTryRecycle != null) {
                Log.d(IMapView.LOGTAG, "Rejected bitmap from being added to BitmapPool.");
            }
        } else {
            synchronized (this.mPool) {
                this.mPool.addLast(bitmapTryRecycle);
            }
        }
    }

    @Deprecated
    public void applyReusableOptions(BitmapFactory.Options options) {
        options.inBitmap = null;
        options.inSampleSize = 1;
        options.inMutable = true;
    }

    public void applyReusableOptions(BitmapFactory.Options options, int i, int i2) {
        options.inBitmap = obtainSizedBitmapFromPool(i, i2);
        options.inSampleSize = 1;
        options.inMutable = true;
    }

    @Deprecated
    public Bitmap obtainBitmapFromPool() {
        synchronized (this.mPool) {
            if (this.mPool.isEmpty()) {
                return null;
            }
            Bitmap bitmapRemoveFirst = this.mPool.removeFirst();
            if (!bitmapRemoveFirst.isRecycled()) {
                return bitmapRemoveFirst;
            }
            return obtainBitmapFromPool();
        }
    }

    public Bitmap obtainSizedBitmapFromPool(int i, int i2) {
        synchronized (this.mPool) {
            if (this.mPool.isEmpty()) {
                return null;
            }
            Iterator<Bitmap> it = this.mPool.iterator();
            while (it.hasNext()) {
                Bitmap next = it.next();
                if (next.isRecycled()) {
                    this.mPool.remove(next);
                    return obtainSizedBitmapFromPool(i, i2);
                }
                if (next.getWidth() == i && next.getHeight() == i2) {
                    this.mPool.remove(next);
                    return next;
                }
            }
            return null;
        }
    }

    public void clearBitmapPool() {
        synchronized (sInstance.mPool) {
            while (true) {
                BitmapPool bitmapPool = sInstance;
                if (!bitmapPool.mPool.isEmpty()) {
                    bitmapPool.mPool.remove().recycle();
                }
            }
        }
    }

    public void asyncRecycle(final Drawable drawable) {
        if (drawable == null) {
            return;
        }
        this.mExecutor.execute(new Runnable() { // from class: org.osmdroid.tileprovider.BitmapPool.1
            @Override // java.lang.Runnable
            public void run() {
                BitmapPool.this.syncRecycle(drawable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void syncRecycle(Drawable drawable) {
        if (drawable != null && (drawable instanceof ReusableBitmapDrawable)) {
            returnDrawableToPool((ReusableBitmapDrawable) drawable);
        }
    }
}
