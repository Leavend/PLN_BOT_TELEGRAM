package org.osmdroid.tileprovider.modules;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.BitmapPool;
import org.osmdroid.tileprovider.ExpirableBitmapDrawable;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.TileSystem;

/* loaded from: classes3.dex */
public class MapTileApproximater extends MapTileModuleProviderBase {
    private final List<MapTileModuleProviderBase> mProviders;
    private int minZoomLevel;

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getName() {
        return "Offline Tile Approximation Provider";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getThreadGroupName() {
        return "approximater";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public boolean getUsesDataConnection() {
        return false;
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    @Deprecated
    public void setTileSource(ITileSource iTileSource) {
    }

    public MapTileApproximater() {
        this(Configuration.getInstance().getTileFileSystemThreads(), Configuration.getInstance().getTileFileSystemMaxQueueSize());
    }

    public MapTileApproximater(int i, int i2) {
        super(i, i2);
        this.mProviders = new CopyOnWriteArrayList();
    }

    public void addProvider(MapTileModuleProviderBase mapTileModuleProviderBase) {
        this.mProviders.add(mapTileModuleProviderBase);
        computeZoomLevels();
    }

    private void computeZoomLevels() {
        this.minZoomLevel = 0;
        Iterator<MapTileModuleProviderBase> it = this.mProviders.iterator();
        boolean z = true;
        while (it.hasNext()) {
            int minimumZoomLevel = it.next().getMinimumZoomLevel();
            if (z) {
                this.minZoomLevel = minimumZoomLevel;
                z = false;
            } else {
                this.minZoomLevel = Math.min(this.minZoomLevel, minimumZoomLevel);
            }
        }
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public TileLoader getTileLoader() {
        return new TileLoader();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public int getMinimumZoomLevel() {
        return this.minZoomLevel;
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public int getMaximumZoomLevel() {
        return TileSystem.getMaximumZoomLevel();
    }

    protected class TileLoader extends MapTileModuleProviderBase.TileLoader {
        protected TileLoader() {
            super();
        }

        @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.TileLoader
        public Drawable loadTile(long j) {
            Bitmap bitmapApproximateTileFromLowerZoom = MapTileApproximater.this.approximateTileFromLowerZoom(j);
            if (bitmapApproximateTileFromLowerZoom == null) {
                return null;
            }
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmapApproximateTileFromLowerZoom);
            ExpirableBitmapDrawable.setState(bitmapDrawable, -3);
            return bitmapDrawable;
        }
    }

    public Bitmap approximateTileFromLowerZoom(long j) {
        for (int i = 1; MapTileIndex.getZoom(j) - i >= 0; i++) {
            Bitmap bitmapApproximateTileFromLowerZoom = approximateTileFromLowerZoom(j, i);
            if (bitmapApproximateTileFromLowerZoom != null) {
                return bitmapApproximateTileFromLowerZoom;
            }
        }
        return null;
    }

    public Bitmap approximateTileFromLowerZoom(long j, int i) {
        Iterator<MapTileModuleProviderBase> it = this.mProviders.iterator();
        while (it.hasNext()) {
            Bitmap bitmapApproximateTileFromLowerZoom = approximateTileFromLowerZoom(it.next(), j, i);
            if (bitmapApproximateTileFromLowerZoom != null) {
                return bitmapApproximateTileFromLowerZoom;
            }
        }
        return null;
    }

    public static Bitmap approximateTileFromLowerZoom(MapTileModuleProviderBase mapTileModuleProviderBase, long j, int i) {
        int zoom;
        if (i <= 0 || (zoom = MapTileIndex.getZoom(j) - i) < mapTileModuleProviderBase.getMinimumZoomLevel() || zoom > mapTileModuleProviderBase.getMaximumZoomLevel()) {
            return null;
        }
        try {
            Drawable drawableLoadTileIfReachable = mapTileModuleProviderBase.getTileLoader().loadTileIfReachable(MapTileIndex.getTileIndex(zoom, MapTileIndex.getX(j) >> i, MapTileIndex.getY(j) >> i));
            if (drawableLoadTileIfReachable instanceof BitmapDrawable) {
                return approximateTileFromLowerZoom((BitmapDrawable) drawableLoadTileIfReachable, j, i);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0033 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0034 A[Catch: all -> 0x002d, TRY_LEAVE, TryCatch #0 {all -> 0x002d, blocks: (B:13:0x0026, B:18:0x002f, B:21:0x0034), top: B:31:0x0026 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.graphics.Bitmap approximateTileFromLowerZoom(android.graphics.drawable.BitmapDrawable r10, long r11, int r13) {
        /*
            r0 = 0
            if (r13 > 0) goto L4
            return r0
        L4:
            android.graphics.Bitmap r1 = r10.getBitmap()
            int r1 = r1.getWidth()
            android.graphics.Bitmap r2 = getTileBitmap(r1)
            android.graphics.Canvas r3 = new android.graphics.Canvas
            r3.<init>(r2)
            boolean r4 = r10 instanceof org.osmdroid.tileprovider.ReusableBitmapDrawable
            if (r4 == 0) goto L1d
            r5 = r10
            org.osmdroid.tileprovider.ReusableBitmapDrawable r5 = (org.osmdroid.tileprovider.ReusableBitmapDrawable) r5
            goto L1e
        L1d:
            r5 = r0
        L1e:
            if (r4 == 0) goto L23
            r5.beginUsingDrawable()
        L23:
            r6 = 0
            if (r4 == 0) goto L2f
            boolean r7 = r5.isBitmapValid()     // Catch: java.lang.Throwable -> L2d
            if (r7 == 0) goto L58
            goto L2f
        L2d:
            r10 = move-exception
            goto L61
        L2f:
            int r7 = r1 >> r13
            if (r7 != 0) goto L34
            goto L58
        L34:
            int r8 = org.osmdroid.util.MapTileIndex.getX(r11)     // Catch: java.lang.Throwable -> L2d
            r9 = 1
            int r13 = r9 << r13
            int r8 = r8 % r13
            int r8 = r8 * r7
            int r11 = org.osmdroid.util.MapTileIndex.getY(r11)     // Catch: java.lang.Throwable -> L2d
            int r11 = r11 % r13
            int r11 = r11 * r7
            android.graphics.Rect r12 = new android.graphics.Rect     // Catch: java.lang.Throwable -> L2d
            int r13 = r8 + r7
            int r7 = r7 + r11
            r12.<init>(r8, r11, r13, r7)     // Catch: java.lang.Throwable -> L2d
            android.graphics.Rect r11 = new android.graphics.Rect     // Catch: java.lang.Throwable -> L2d
            r11.<init>(r6, r6, r1, r1)     // Catch: java.lang.Throwable -> L2d
            android.graphics.Bitmap r10 = r10.getBitmap()     // Catch: java.lang.Throwable -> L2d
            r3.drawBitmap(r10, r12, r11, r0)     // Catch: java.lang.Throwable -> L2d
            r6 = r9
        L58:
            if (r4 == 0) goto L5d
            r5.finishUsingDrawable()
        L5d:
            if (r6 != 0) goto L60
            return r0
        L60:
            return r2
        L61:
            if (r4 == 0) goto L66
            r5.finishUsingDrawable()
        L66:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.modules.MapTileApproximater.approximateTileFromLowerZoom(android.graphics.drawable.BitmapDrawable, long, int):android.graphics.Bitmap");
    }

    public static Bitmap getTileBitmap(int i) {
        Bitmap bitmapObtainSizedBitmapFromPool = BitmapPool.getInstance().obtainSizedBitmapFromPool(i, i);
        if (bitmapObtainSizedBitmapFromPool != null) {
            bitmapObtainSizedBitmapFromPool.setHasAlpha(true);
            bitmapObtainSizedBitmapFromPool.eraseColor(0);
            return bitmapObtainSizedBitmapFromPool;
        }
        return Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void detach() {
        super.detach();
        this.mProviders.clear();
    }
}
