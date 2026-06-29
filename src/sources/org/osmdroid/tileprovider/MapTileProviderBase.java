package org.osmdroid.tileprovider;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.IFilesystemCache;
import org.osmdroid.tileprovider.modules.MapTileApproximater;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.PointL;
import org.osmdroid.util.RectL;
import org.osmdroid.util.TileLooper;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public abstract class MapTileProviderBase implements IMapTileProviderCallback {
    public static final int MAPTILE_FAIL_ID = 1;
    public static final int MAPTILE_SUCCESS_ID = 0;
    private static int sApproximationBackgroundColor = -3355444;
    protected final MapTileCache mTileCache;
    protected Drawable mTileNotFoundImage;
    private final Collection<Handler> mTileRequestCompleteHandlers;
    private ITileSource mTileSource;
    protected boolean mUseDataConnection;

    public abstract Drawable getMapTile(long j);

    public abstract int getMaximumZoomLevel();

    public abstract int getMinimumZoomLevel();

    public abstract long getQueueSize();

    public abstract IFilesystemCache getTileWriter();

    public void detach() {
        clearTileCache();
        if (this.mTileNotFoundImage != null && (this.mTileNotFoundImage instanceof ReusableBitmapDrawable)) {
            BitmapPool.getInstance().returnDrawableToPool((ReusableBitmapDrawable) this.mTileNotFoundImage);
        }
        this.mTileNotFoundImage = null;
        clearTileCache();
    }

    public static void setApproximationBackgroundColor(int i) {
        sApproximationBackgroundColor = i;
    }

    public void setTileSource(ITileSource iTileSource) {
        this.mTileSource = iTileSource;
        clearTileCache();
    }

    public ITileSource getTileSource() {
        return this.mTileSource;
    }

    public MapTileCache createTileCache() {
        return new MapTileCache();
    }

    public MapTileProviderBase(ITileSource iTileSource) {
        this(iTileSource, null);
    }

    public MapTileProviderBase(ITileSource iTileSource, Handler handler) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.mTileRequestCompleteHandlers = linkedHashSet;
        this.mUseDataConnection = true;
        this.mTileNotFoundImage = null;
        this.mTileCache = createTileCache();
        linkedHashSet.add(handler);
        this.mTileSource = iTileSource;
    }

    public void setTileLoadFailureImage(Drawable drawable) {
        this.mTileNotFoundImage = drawable;
    }

    @Override // org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestCompleted(MapTileRequestState mapTileRequestState, Drawable drawable) {
        putTileIntoCache(mapTileRequestState.getMapTile(), drawable, -1);
        sendMessage(0);
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.d(IMapView.LOGTAG, "MapTileProviderBase.mapTileRequestCompleted(): " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
        }
    }

    @Override // org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestFailed(MapTileRequestState mapTileRequestState) {
        if (this.mTileNotFoundImage != null) {
            putTileIntoCache(mapTileRequestState.getMapTile(), this.mTileNotFoundImage, -4);
            sendMessage(0);
        } else {
            sendMessage(1);
        }
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.d(IMapView.LOGTAG, "MapTileProviderBase.mapTileRequestFailed(): " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
        }
    }

    @Override // org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestFailedExceedsMaxQueueSize(MapTileRequestState mapTileRequestState) {
        mapTileRequestFailed(mapTileRequestState);
    }

    @Override // org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestExpiredTile(MapTileRequestState mapTileRequestState, Drawable drawable) {
        putTileIntoCache(mapTileRequestState.getMapTile(), drawable, ExpirableBitmapDrawable.getState(drawable));
        sendMessage(0);
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.d(IMapView.LOGTAG, "MapTileProviderBase.mapTileRequestExpiredTile(): " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
        }
    }

    protected void putTileIntoCache(long j, Drawable drawable, int i) {
        if (drawable == null) {
            return;
        }
        Drawable mapTile = this.mTileCache.getMapTile(j);
        if (mapTile == null || ExpirableBitmapDrawable.getState(mapTile) <= i) {
            ExpirableBitmapDrawable.setState(drawable, i);
            this.mTileCache.putTile(j, drawable);
        }
    }

    @Deprecated
    protected void putExpiredTileIntoCache(MapTileRequestState mapTileRequestState, Drawable drawable) {
        putTileIntoCache(mapTileRequestState.getMapTile(), drawable, -2);
    }

    @Deprecated
    public void setTileRequestCompleteHandler(Handler handler) {
        this.mTileRequestCompleteHandlers.clear();
        this.mTileRequestCompleteHandlers.add(handler);
    }

    public Collection<Handler> getTileRequestCompleteHandlers() {
        return this.mTileRequestCompleteHandlers;
    }

    public void ensureCapacity(int i) {
        this.mTileCache.ensureCapacity(i);
    }

    public MapTileCache getTileCache() {
        return this.mTileCache;
    }

    public void clearTileCache() {
        this.mTileCache.clear();
    }

    @Override // org.osmdroid.tileprovider.IMapTileProviderCallback
    public boolean useDataConnection() {
        return this.mUseDataConnection;
    }

    public void setUseDataConnection(boolean z) {
        this.mUseDataConnection = z;
    }

    public void rescaleCache(Projection projection, double d, double d2, Rect rect) {
        ScaleTileLooper zoomOutTileLooper;
        if (TileSystem.getInputTileZoomLevel(d) == TileSystem.getInputTileZoomLevel(d2)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.i(IMapView.LOGTAG, "rescale tile cache from " + d2 + " to " + d);
        }
        PointL mercatorPixels = projection.toMercatorPixels(rect.left, rect.top, null);
        PointL mercatorPixels2 = projection.toMercatorPixels(rect.right, rect.bottom, null);
        RectL rectL = new RectL(mercatorPixels.x, mercatorPixels.y, mercatorPixels2.x, mercatorPixels2.y);
        if (d > d2) {
            zoomOutTileLooper = new ZoomInTileLooper();
        } else {
            zoomOutTileLooper = new ZoomOutTileLooper();
        }
        zoomOutTileLooper.loop(d, rectL, d2, getTileSource().getTileSizePixels());
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.i(IMapView.LOGTAG, "Finished rescale in " + (jCurrentTimeMillis2 - jCurrentTimeMillis) + "ms");
        }
    }

    private abstract class ScaleTileLooper extends TileLooper {
        private boolean isWorth;
        protected Paint mDebugPaint;
        protected Rect mDestRect;
        protected int mDiff;
        protected final HashMap<Long, Bitmap> mNewTiles;
        protected int mOldTileZoomLevel;
        protected Rect mSrcRect;
        protected int mTileSize;
        protected int mTileSize_2;

        protected abstract void computeTile(long j, int i, int i2);

        private ScaleTileLooper() {
            this.mNewTiles = new HashMap<>();
        }

        public void loop(double d, RectL rectL, double d2, int i) {
            this.mSrcRect = new Rect();
            this.mDestRect = new Rect();
            this.mDebugPaint = new Paint();
            this.mOldTileZoomLevel = TileSystem.getInputTileZoomLevel(d2);
            this.mTileSize = i;
            loop(d, rectL);
        }

        @Override // org.osmdroid.util.TileLooper
        public void initialiseLoop() {
            super.initialiseLoop();
            int iAbs = Math.abs(this.mTileZoomLevel - this.mOldTileZoomLevel);
            this.mDiff = iAbs;
            this.mTileSize_2 = this.mTileSize >> iAbs;
            this.isWorth = iAbs != 0;
        }

        @Override // org.osmdroid.util.TileLooper
        public void handleTile(long j, int i, int i2) {
            if (this.isWorth && MapTileProviderBase.this.getMapTile(j) == null) {
                try {
                    computeTile(j, i, i2);
                } catch (OutOfMemoryError unused) {
                    Log.e(IMapView.LOGTAG, "OutOfMemoryError rescaling cache");
                }
            }
        }

        @Override // org.osmdroid.util.TileLooper
        public void finaliseLoop() {
            while (!this.mNewTiles.isEmpty()) {
                long jLongValue = this.mNewTiles.keySet().iterator().next().longValue();
                putScaledTileIntoCache(jLongValue, this.mNewTiles.remove(Long.valueOf(jLongValue)));
            }
        }

        protected void putScaledTileIntoCache(long j, Bitmap bitmap) {
            MapTileProviderBase.this.putTileIntoCache(j, new ReusableBitmapDrawable(bitmap), -3);
            if (Configuration.getInstance().isDebugMode()) {
                Log.d(IMapView.LOGTAG, "Created scaled tile: " + MapTileIndex.toString(j));
                this.mDebugPaint.setTextSize(40.0f);
                new Canvas(bitmap).drawText("scaled", 50.0f, 50.0f, this.mDebugPaint);
            }
        }
    }

    private class ZoomInTileLooper extends ScaleTileLooper {
        private ZoomInTileLooper() {
            super();
        }

        @Override // org.osmdroid.tileprovider.MapTileProviderBase.ScaleTileLooper
        public void computeTile(long j, int i, int i2) {
            Bitmap bitmapApproximateTileFromLowerZoom;
            Drawable mapTile = MapTileProviderBase.this.mTileCache.getMapTile(MapTileIndex.getTileIndex(this.mOldTileZoomLevel, MapTileIndex.getX(j) >> this.mDiff, MapTileIndex.getY(j) >> this.mDiff));
            if (!(mapTile instanceof BitmapDrawable) || (bitmapApproximateTileFromLowerZoom = MapTileApproximater.approximateTileFromLowerZoom((BitmapDrawable) mapTile, j, this.mDiff)) == null) {
                return;
            }
            this.mNewTiles.put(Long.valueOf(j), bitmapApproximateTileFromLowerZoom);
        }
    }

    private class ZoomOutTileLooper extends ScaleTileLooper {
        private static final int MAX_ZOOM_OUT_DIFF = 4;

        private ZoomOutTileLooper() {
            super();
        }

        @Override // org.osmdroid.tileprovider.MapTileProviderBase.ScaleTileLooper
        protected void computeTile(long j, int i, int i2) {
            Bitmap bitmap;
            if (this.mDiff >= 4) {
                return;
            }
            int x = MapTileIndex.getX(j) << this.mDiff;
            int y = MapTileIndex.getY(j) << this.mDiff;
            int i3 = 1 << this.mDiff;
            Bitmap tileBitmap = null;
            Canvas canvas = null;
            for (int i4 = 0; i4 < i3; i4++) {
                for (int i5 = 0; i5 < i3; i5++) {
                    Drawable mapTile = MapTileProviderBase.this.mTileCache.getMapTile(MapTileIndex.getTileIndex(this.mOldTileZoomLevel, x + i4, y + i5));
                    if ((mapTile instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) mapTile).getBitmap()) != null) {
                        if (tileBitmap == null) {
                            tileBitmap = MapTileApproximater.getTileBitmap(this.mTileSize);
                            canvas = new Canvas(tileBitmap);
                            canvas.drawColor(MapTileProviderBase.sApproximationBackgroundColor);
                        }
                        this.mDestRect.set(this.mTileSize_2 * i4, this.mTileSize_2 * i5, (i4 + 1) * this.mTileSize_2, (i5 + 1) * this.mTileSize_2);
                        canvas.drawBitmap(bitmap, (Rect) null, this.mDestRect, (Paint) null);
                    }
                }
            }
            if (tileBitmap != null) {
                this.mNewTiles.put(Long.valueOf(j), tileBitmap);
            }
        }
    }

    public void expireInMemoryCache(long j) {
        Drawable mapTile = this.mTileCache.getMapTile(j);
        if (mapTile != null) {
            ExpirableBitmapDrawable.setState(mapTile, -2);
        }
    }

    private void sendMessage(int i) {
        for (int i2 = 0; i2 < 3 && !sendMessageFailFast(i); i2++) {
        }
    }

    private boolean sendMessageFailFast(int i) {
        for (Handler handler : this.mTileRequestCompleteHandlers) {
            try {
                if (handler != null) {
                    handler.sendEmptyMessage(i);
                }
            } catch (ConcurrentModificationException unused) {
                return false;
            }
        }
        return true;
    }
}
