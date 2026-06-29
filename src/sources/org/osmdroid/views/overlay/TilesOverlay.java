package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;
import java.io.File;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.library.R;
import org.osmdroid.tileprovider.BitmapPool;
import org.osmdroid.tileprovider.MapTileProviderBase;
import org.osmdroid.tileprovider.ReusableBitmapDrawable;
import org.osmdroid.tileprovider.TileStates;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.RectL;
import org.osmdroid.util.TileLooper;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.drawing.MapSnapshot;

/* loaded from: classes3.dex */
public class TilesOverlay extends Overlay implements IOverlayMenuProvider {
    public static final ColorFilter INVERT_COLORS;
    static final float[] negate;
    private Context ctx;
    private ColorFilter currentColorFilter;
    private boolean horizontalWrapEnabled;
    private Rect mCanvasRect;
    protected final Paint mDebugPaint;
    private final Rect mIntersectionRect;
    private int mLoadingBackgroundColor;
    private int mLoadingLineColor;
    private BitmapDrawable mLoadingTile;
    private boolean mOptionsMenuEnabled;
    protected Projection mProjection;
    private final Rect mProtectedTiles;
    private final OverlayTileLooper mTileLooper;
    protected final MapTileProviderBase mTileProvider;
    private final Rect mTileRect;
    private final TileStates mTileStates;
    protected final RectL mViewPort;
    protected Drawable userSelectedLoadingDrawable;
    private boolean verticalWrapEnabled;
    public static final int MENU_MAP_MODE = getSafeMenuId();
    public static final int MENU_TILE_SOURCE_STARTING_ID = getSafeMenuIdSequence(TileSourceFactory.getTileSources().size());
    public static final int MENU_OFFLINE = getSafeMenuId();
    public static final int MENU_SNAPSHOT = getSafeMenuId();
    public static final int MENU_STATES = getSafeMenuId();

    static {
        float[] fArr = {-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
        negate = fArr;
        INVERT_COLORS = new ColorMatrixColorFilter(fArr);
    }

    public TilesOverlay(MapTileProviderBase mapTileProviderBase, Context context) {
        this(mapTileProviderBase, context, true, true);
    }

    public TilesOverlay(MapTileProviderBase mapTileProviderBase, Context context, boolean z, boolean z2) {
        this.userSelectedLoadingDrawable = null;
        this.mDebugPaint = new Paint();
        this.mTileRect = new Rect();
        this.mViewPort = new RectL();
        this.mOptionsMenuEnabled = true;
        this.mLoadingTile = null;
        this.mLoadingBackgroundColor = Color.rgb(216, HttpStatusCodesKt.HTTP_ALREADY_REPORTED, HttpStatusCodesKt.HTTP_ALREADY_REPORTED);
        this.mLoadingLineColor = Color.rgb(200, 192, 192);
        this.horizontalWrapEnabled = true;
        this.verticalWrapEnabled = true;
        this.currentColorFilter = null;
        this.mProtectedTiles = new Rect();
        this.mTileStates = new TileStates();
        this.mTileLooper = new OverlayTileLooper();
        this.mIntersectionRect = new Rect();
        this.ctx = context;
        if (mapTileProviderBase == null) {
            throw new IllegalArgumentException("You must pass a valid tile provider to the tiles overlay.");
        }
        this.mTileProvider = mapTileProviderBase;
        setHorizontalWrapEnabled(z);
        setVerticalWrapEnabled(z2);
    }

    public void setLoadingDrawable(Drawable drawable) {
        this.userSelectedLoadingDrawable = drawable;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        this.mTileProvider.detach();
        this.ctx = null;
        BitmapPool.getInstance().asyncRecycle(this.mLoadingTile);
        this.mLoadingTile = null;
        BitmapPool.getInstance().asyncRecycle(this.userSelectedLoadingDrawable);
        this.userSelectedLoadingDrawable = null;
    }

    public int getMinimumZoomLevel() {
        return this.mTileProvider.getMinimumZoomLevel();
    }

    public int getMaximumZoomLevel() {
        return this.mTileProvider.getMaximumZoomLevel();
    }

    public boolean useDataConnection() {
        return this.mTileProvider.useDataConnection();
    }

    public void setUseDataConnection(boolean z) {
        this.mTileProvider.setUseDataConnection(z);
    }

    public void protectDisplayedTilesForCache(Canvas canvas, Projection projection) {
        if (setViewPort(canvas, projection)) {
            TileSystem.getTileFromMercator(this.mViewPort, TileSystem.getTileSize(this.mProjection.getZoomLevel()), this.mProtectedTiles);
            this.mTileProvider.getTileCache().getMapTileArea().set(TileSystem.getInputTileZoomLevel(this.mProjection.getZoomLevel()), this.mProtectedTiles);
            this.mTileProvider.getTileCache().maintenance();
        }
    }

    protected boolean setViewPort(Canvas canvas, Projection projection) {
        setProjection(projection);
        getProjection().getMercatorViewPort(this.mViewPort);
        return true;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        if (Configuration.getInstance().isDebugTileProviders()) {
            Log.d(IMapView.LOGTAG, "onDraw");
        }
        if (setViewPort(canvas, projection)) {
            drawTiles(canvas, getProjection(), getProjection().getZoomLevel(), this.mViewPort);
        }
    }

    public void drawTiles(Canvas canvas, Projection projection, double d, RectL rectL) {
        this.mProjection = projection;
        this.mTileLooper.loop(d, rectL, canvas);
    }

    protected class OverlayTileLooper extends TileLooper {
        private Canvas mCanvas;

        public OverlayTileLooper() {
        }

        public OverlayTileLooper(boolean z, boolean z2) {
            super(z, z2);
        }

        public void loop(double d, RectL rectL, Canvas canvas) {
            this.mCanvas = canvas;
            loop(d, rectL);
        }

        @Override // org.osmdroid.util.TileLooper
        public void initialiseLoop() {
            TilesOverlay.this.mTileProvider.ensureCapacity((((this.mTiles.bottom - this.mTiles.top) + 1) * ((this.mTiles.right - this.mTiles.left) + 1)) + Configuration.getInstance().getCacheMapTileOvershoot());
            TilesOverlay.this.mTileStates.initialiseLoop();
            super.initialiseLoop();
        }

        @Override // org.osmdroid.util.TileLooper
        public void handleTile(long j, int i, int i2) {
            Drawable mapTile = TilesOverlay.this.mTileProvider.getMapTile(j);
            TilesOverlay.this.mTileStates.handleTile(mapTile);
            if (this.mCanvas == null) {
                return;
            }
            boolean z = mapTile instanceof ReusableBitmapDrawable;
            ReusableBitmapDrawable reusableBitmapDrawable = z ? (ReusableBitmapDrawable) mapTile : null;
            if (mapTile == null) {
                mapTile = TilesOverlay.this.getLoadingTile();
            }
            if (mapTile != null) {
                TilesOverlay.this.mProjection.getPixelFromTile(i, i2, TilesOverlay.this.mTileRect);
                if (z) {
                    reusableBitmapDrawable.beginUsingDrawable();
                }
                if (z) {
                    try {
                        if (!reusableBitmapDrawable.isBitmapValid()) {
                            mapTile = TilesOverlay.this.getLoadingTile();
                            z = false;
                        }
                    } finally {
                        if (z) {
                            reusableBitmapDrawable.finishUsingDrawable();
                        }
                    }
                }
                TilesOverlay tilesOverlay = TilesOverlay.this;
                tilesOverlay.onTileReadyToDraw(this.mCanvas, mapTile, tilesOverlay.mTileRect);
            }
            if (Configuration.getInstance().isDebugTileProviders()) {
                TilesOverlay.this.mProjection.getPixelFromTile(i, i2, TilesOverlay.this.mTileRect);
                this.mCanvas.drawText(MapTileIndex.toString(j), TilesOverlay.this.mTileRect.left + 1, TilesOverlay.this.mTileRect.top + TilesOverlay.this.mDebugPaint.getTextSize(), TilesOverlay.this.mDebugPaint);
                this.mCanvas.drawLine(TilesOverlay.this.mTileRect.left, TilesOverlay.this.mTileRect.top, TilesOverlay.this.mTileRect.right, TilesOverlay.this.mTileRect.top, TilesOverlay.this.mDebugPaint);
                this.mCanvas.drawLine(TilesOverlay.this.mTileRect.left, TilesOverlay.this.mTileRect.top, TilesOverlay.this.mTileRect.left, TilesOverlay.this.mTileRect.bottom, TilesOverlay.this.mDebugPaint);
            }
        }

        @Override // org.osmdroid.util.TileLooper
        public void finaliseLoop() {
            TilesOverlay.this.mTileStates.finaliseLoop();
        }
    }

    protected void setCanvasRect(Rect rect) {
        this.mCanvasRect = rect;
    }

    protected Rect getCanvasRect() {
        return this.mCanvasRect;
    }

    protected void setProjection(Projection projection) {
        this.mProjection = projection;
    }

    protected Projection getProjection() {
        return this.mProjection;
    }

    protected void onTileReadyToDraw(Canvas canvas, Drawable drawable, Rect rect) {
        drawable.setColorFilter(this.currentColorFilter);
        drawable.setBounds(rect.left, rect.top, rect.right, rect.bottom);
        Rect canvasRect = getCanvasRect();
        if (canvasRect == null) {
            drawable.draw(canvas);
        } else if (this.mIntersectionRect.setIntersect(canvas.getClipBounds(), canvasRect)) {
            canvas.save();
            canvas.clipRect(this.mIntersectionRect);
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public void setOptionsMenuEnabled(boolean z) {
        this.mOptionsMenuEnabled = z;
    }

    public boolean isOptionsMenuEnabled() {
        return this.mOptionsMenuEnabled;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onCreateOptionsMenu(Menu menu, int i, MapView mapView) {
        SubMenu icon = menu.addSubMenu(0, 0, 0, R.string.map_mode).setIcon(R.drawable.ic_menu_mapmode);
        for (int i2 = 0; i2 < TileSourceFactory.getTileSources().size(); i2++) {
            icon.add(MENU_MAP_MODE + i, MENU_TILE_SOURCE_STARTING_ID + i2 + i, 0, TileSourceFactory.getTileSources().get(i2).name());
        }
        icon.setGroupCheckable(MENU_MAP_MODE + i, true, true);
        Context context = this.ctx;
        if (context != null) {
            menu.add(0, MENU_OFFLINE + i, 0, context.getString(mapView.useDataConnection() ? R.string.set_mode_offline : R.string.set_mode_online)).setIcon(this.ctx.getResources().getDrawable(R.drawable.ic_menu_offline));
            menu.add(0, MENU_SNAPSHOT + i, 0, R.string.snapshot);
            menu.add(0, MENU_STATES + i, 0, R.string.states);
        }
        return true;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onPrepareOptionsMenu(Menu menu, int i, MapView mapView) {
        int iIndexOf = TileSourceFactory.getTileSources().indexOf(mapView.getTileProvider().getTileSource());
        if (iIndexOf >= 0) {
            menu.findItem(MENU_TILE_SOURCE_STARTING_ID + iIndexOf + i).setChecked(true);
        }
        menu.findItem(MENU_OFFLINE + i).setTitle(mapView.useDataConnection() ? R.string.set_mode_offline : R.string.set_mode_online);
        return true;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onOptionsItemSelected(MenuItem menuItem, int i, MapView mapView) {
        int itemId = menuItem.getItemId() - i;
        int i2 = MENU_TILE_SOURCE_STARTING_ID;
        if (itemId >= i2 && itemId < TileSourceFactory.getTileSources().size() + i2) {
            mapView.setTileSource(TileSourceFactory.getTileSources().get(itemId - i2));
            return true;
        }
        if (itemId == MENU_OFFLINE) {
            mapView.setUseDataConnection(!mapView.useDataConnection());
            return true;
        }
        if (itemId == MENU_STATES) {
            Toast.makeText(mapView.getContext(), this.mTileStates.toString(), 0).show();
            return true;
        }
        if (itemId != MENU_SNAPSHOT) {
            return false;
        }
        Thread thread = new Thread(new MapSnapshot(new MapSnapshot.MapSnapshotable() { // from class: org.osmdroid.views.overlay.TilesOverlay.1
            @Override // org.osmdroid.views.drawing.MapSnapshot.MapSnapshotable
            public void callback(MapSnapshot mapSnapshot) {
                if (mapSnapshot.getStatus() != MapSnapshot.Status.CANVAS_OK) {
                    return;
                }
                mapSnapshot.save(new File(Configuration.getInstance().getOsmdroidBasePath(), "snapshot.png"));
                mapSnapshot.onDetach();
            }
        }, 1, mapView));
        thread.setName("TilesOverlaySnapShotThread");
        thread.start();
        return true;
    }

    public int getLoadingBackgroundColor() {
        return this.mLoadingBackgroundColor;
    }

    public void setLoadingBackgroundColor(int i) {
        if (this.mLoadingBackgroundColor != i) {
            this.mLoadingBackgroundColor = i;
            clearLoadingTile();
        }
    }

    public int getLoadingLineColor() {
        return this.mLoadingLineColor;
    }

    public void setLoadingLineColor(int i) {
        if (this.mLoadingLineColor != i) {
            this.mLoadingLineColor = i;
            clearLoadingTile();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable getLoadingTile() {
        Drawable drawable = this.userSelectedLoadingDrawable;
        if (drawable != null) {
            return drawable;
        }
        if (this.mLoadingTile == null && this.mLoadingBackgroundColor != 0) {
            try {
                int tileSizePixels = this.mTileProvider.getTileSource() != null ? this.mTileProvider.getTileSource().getTileSizePixels() : 256;
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(tileSizePixels, tileSizePixels, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                Paint paint = new Paint();
                canvas.drawColor(this.mLoadingBackgroundColor);
                paint.setColor(this.mLoadingLineColor);
                paint.setStrokeWidth(0.0f);
                int i = tileSizePixels / 16;
                for (int i2 = 0; i2 < tileSizePixels; i2 += i) {
                    float f = i2;
                    float f2 = tileSizePixels;
                    canvas.drawLine(0.0f, f, f2, f, paint);
                    canvas.drawLine(f, 0.0f, f, f2, paint);
                }
                this.mLoadingTile = new BitmapDrawable(bitmapCreateBitmap);
            } catch (NullPointerException unused) {
                Log.e(IMapView.LOGTAG, "NullPointerException getting loading tile");
                System.gc();
            } catch (OutOfMemoryError unused2) {
                Log.e(IMapView.LOGTAG, "OutOfMemoryError getting loading tile");
                System.gc();
            }
        }
        return this.mLoadingTile;
    }

    private void clearLoadingTile() {
        BitmapDrawable bitmapDrawable = this.mLoadingTile;
        this.mLoadingTile = null;
        BitmapPool.getInstance().asyncRecycle(bitmapDrawable);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.currentColorFilter = colorFilter;
    }

    public boolean isHorizontalWrapEnabled() {
        return this.horizontalWrapEnabled;
    }

    public void setHorizontalWrapEnabled(boolean z) {
        this.horizontalWrapEnabled = z;
        this.mTileLooper.setHorizontalWrapEnabled(z);
    }

    public boolean isVerticalWrapEnabled() {
        return this.verticalWrapEnabled;
    }

    public void setVerticalWrapEnabled(boolean z) {
        this.verticalWrapEnabled = z;
        this.mTileLooper.setVerticalWrapEnabled(z);
    }

    public TileStates getTileStates() {
        return this.mTileStates;
    }
}
