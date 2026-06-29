package org.osmdroid.views.drawing;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Looper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.TileStates;
import org.osmdroid.util.RectL;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.TilesOverlay;

/* loaded from: classes3.dex */
public class MapSnapshot implements Runnable {
    public static final int INCLUDE_FLAGS_ALL = 15;
    public static final int INCLUDE_FLAG_EXPIRED = 2;
    public static final int INCLUDE_FLAG_NOTFOUND = 8;
    public static final int INCLUDE_FLAG_SCALED = 4;
    public static final int INCLUDE_FLAG_UPTODATE = 1;
    private boolean mAlreadyFinished;
    private Bitmap mBitmap;
    private boolean mCurrentlyRunning;
    private MapSnapshotHandler mHandler;
    private final int mIncludeFlags;
    private boolean mIsDetached;
    private MapSnapshotable mMapSnapshotable;
    private boolean mOneMoreTime;
    private List<Overlay> mOverlays;
    private Projection mProjection;
    private Status mStatus;
    private MapTileProviderBase mTileProvider;
    private TilesOverlay mTilesOverlay;
    private final RectL mViewPort;

    public interface MapSnapshotable {
        void callback(MapSnapshot mapSnapshot);
    }

    public enum Status {
        NOTHING,
        STARTED,
        TILES_OK,
        PAINTING,
        CANVAS_OK
    }

    public static boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public MapSnapshot(MapSnapshotable mapSnapshotable, int i, MapView mapView) {
        this(mapSnapshotable, i, mapView.getTileProvider(), mapView.getOverlays(), mapView.getProjection());
    }

    public MapSnapshot(MapSnapshotable mapSnapshotable, int i, MapTileProviderBase mapTileProviderBase, List<Overlay> list, Projection projection) {
        RectL rectL = new RectL();
        this.mViewPort = rectL;
        this.mStatus = Status.NOTHING;
        this.mMapSnapshotable = mapSnapshotable;
        this.mIncludeFlags = i;
        this.mTileProvider = mapTileProviderBase;
        this.mOverlays = list;
        this.mProjection = projection;
        projection.getMercatorViewPort(rectL);
        TilesOverlay tilesOverlay = new TilesOverlay(this.mTileProvider, null);
        this.mTilesOverlay = tilesOverlay;
        tilesOverlay.setHorizontalWrapEnabled(this.mProjection.isHorizontalWrapEnabled());
        this.mTilesOverlay.setVerticalWrapEnabled(this.mProjection.isVerticalWrapEnabled());
        this.mHandler = new MapSnapshotHandler(this);
        this.mTileProvider.getTileRequestCompleteHandlers().add(this.mHandler);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mStatus = Status.STARTED;
        refreshASAP();
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public boolean save(File file) {
        return save(this.mBitmap, file);
    }

    public void onDetach() {
        this.mIsDetached = true;
        this.mProjection = null;
        this.mTileProvider.getTileRequestCompleteHandlers().remove(this.mHandler);
        this.mTileProvider.detach();
        this.mTileProvider = null;
        this.mHandler.destroy();
        this.mHandler = null;
        this.mMapSnapshotable = null;
        this.mTilesOverlay = null;
        this.mOverlays = null;
        this.mBitmap = null;
    }

    private void draw() {
        this.mBitmap = Bitmap.createBitmap(this.mProjection.getWidth(), this.mProjection.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mBitmap);
        this.mProjection.save(canvas, true, false);
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        Projection projection = this.mProjection;
        tilesOverlay.drawTiles(canvas, projection, projection.getZoomLevel(), this.mViewPort);
        List<Overlay> list = this.mOverlays;
        if (list != null) {
            for (Overlay overlay : list) {
                if (overlay != null && overlay.isEnabled()) {
                    overlay.draw(canvas, this.mProjection);
                }
            }
        }
        this.mProjection.restore(canvas, false);
    }

    private void refresh() {
        if (refreshCheckStart()) {
            TileStates tileStates = this.mTilesOverlay.getTileStates();
            do {
                TilesOverlay tilesOverlay = this.mTilesOverlay;
                Projection projection = this.mProjection;
                tilesOverlay.drawTiles(null, projection, projection.getZoomLevel(), this.mViewPort);
                int i = this.mIncludeFlags;
                boolean z = true;
                if (i != 0 && i != 15) {
                    if ((i & 1) == 0 && tileStates.getUpToDate() != 0) {
                        z = false;
                    }
                    if (z && (this.mIncludeFlags & 2) == 0 && tileStates.getExpired() != 0) {
                        z = false;
                    }
                    if (z && (this.mIncludeFlags & 4) == 0 && tileStates.getScaled() != 0) {
                        z = false;
                    }
                    if (z && (this.mIncludeFlags & 8) == 0 && tileStates.getNotFound() != 0) {
                        z = false;
                    }
                }
                if (z) {
                    if (this.mStatus == Status.CANVAS_OK || this.mStatus == Status.PAINTING || !refreshCheckFinish()) {
                        return;
                    }
                    this.mStatus = Status.PAINTING;
                    if (this.mIsDetached) {
                        return;
                    }
                    draw();
                    this.mStatus = Status.CANVAS_OK;
                    MapSnapshotable mapSnapshotable = this.mMapSnapshotable;
                    if (mapSnapshotable != null) {
                        mapSnapshotable.callback(this);
                    }
                }
            } while (refreshCheckEnd());
        }
    }

    private synchronized boolean refreshCheckStart() {
        if (this.mIsDetached) {
            return false;
        }
        if (this.mAlreadyFinished) {
            return false;
        }
        if (!this.mOneMoreTime) {
            return false;
        }
        if (this.mCurrentlyRunning) {
            return false;
        }
        this.mOneMoreTime = false;
        this.mCurrentlyRunning = true;
        return true;
    }

    private synchronized boolean refreshCheckEnd() {
        if (this.mIsDetached) {
            return false;
        }
        if (this.mAlreadyFinished) {
            return false;
        }
        if (!this.mOneMoreTime) {
            this.mCurrentlyRunning = false;
            return false;
        }
        this.mOneMoreTime = false;
        return true;
    }

    private synchronized boolean refreshCheckFinish() {
        boolean z;
        z = !this.mAlreadyFinished;
        this.mAlreadyFinished = true;
        return z;
    }

    private synchronized boolean refreshAgain() {
        this.mOneMoreTime = true;
        return true ^ this.mCurrentlyRunning;
    }

    public void refreshASAP() {
        if (refreshAgain()) {
            refresh();
        }
    }

    private static boolean save(Bitmap bitmap, File file) throws Throwable {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            try {
                fileOutputStream.close();
                return true;
            } catch (IOException e2) {
                e2.printStackTrace();
                return true;
            }
        } catch (Exception e3) {
            e = e3;
            fileOutputStream2 = fileOutputStream;
            e.printStackTrace();
            if (fileOutputStream2 == null) {
                return false;
            }
            try {
                fileOutputStream2.close();
                return false;
            } catch (IOException e4) {
                e4.printStackTrace();
                return false;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }
}
