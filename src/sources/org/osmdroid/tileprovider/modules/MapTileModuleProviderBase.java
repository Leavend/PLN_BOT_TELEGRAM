package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.ExpirableBitmapDrawable;
import org.osmdroid.tileprovider.MapTileRequestState;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public abstract class MapTileModuleProviderBase {
    private final ExecutorService mExecutor;
    protected final LinkedHashMap<Long, MapTileRequestState> mPending;
    protected final Object mQueueLockObject = new Object();
    protected final HashMap<Long, MapTileRequestState> mWorking;

    public abstract int getMaximumZoomLevel();

    public abstract int getMinimumZoomLevel();

    protected abstract String getName();

    protected abstract String getThreadGroupName();

    public abstract TileLoader getTileLoader();

    public abstract boolean getUsesDataConnection();

    public abstract void setTileSource(ITileSource iTileSource);

    public boolean isTileReachable(long j) {
        int zoom = MapTileIndex.getZoom(j);
        return zoom >= getMinimumZoomLevel() && zoom <= getMaximumZoomLevel();
    }

    public MapTileModuleProviderBase(int i, final int i2) {
        if (i2 < i) {
            Log.w(IMapView.LOGTAG, "The pending queue size is smaller than the thread pool size. Automatically reducing the thread pool size.");
            i = i2;
        }
        this.mExecutor = Executors.newFixedThreadPool(i, new ConfigurablePriorityThreadFactory(5, getThreadGroupName()));
        this.mWorking = new HashMap<>();
        this.mPending = new LinkedHashMap<Long, MapTileRequestState>(i2 + 2, 0.1f, true) { // from class: org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.1
            private static final long serialVersionUID = 6455337315681858866L;

            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<Long, MapTileRequestState> entry) {
                MapTileRequestState mapTileRequestState;
                if (size() <= i2) {
                    return false;
                }
                Iterator<Long> it = MapTileModuleProviderBase.this.mPending.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    long jLongValue = it.next().longValue();
                    if (!MapTileModuleProviderBase.this.mWorking.containsKey(Long.valueOf(jLongValue)) && (mapTileRequestState = MapTileModuleProviderBase.this.mPending.get(Long.valueOf(jLongValue))) != null) {
                        MapTileModuleProviderBase.this.removeTileFromQueues(jLongValue);
                        mapTileRequestState.getCallback().mapTileRequestFailedExceedsMaxQueueSize(mapTileRequestState);
                        break;
                    }
                }
                return false;
            }
        };
    }

    public void loadMapTileAsync(MapTileRequestState mapTileRequestState) {
        if (this.mExecutor.isShutdown()) {
            return;
        }
        synchronized (this.mQueueLockObject) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "MapTileModuleProviderBase.loadMaptileAsync() on provider: " + getName() + " for tile: " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
                if (this.mPending.containsKey(Long.valueOf(mapTileRequestState.getMapTile()))) {
                    Log.d(IMapView.LOGTAG, "MapTileModuleProviderBase.loadMaptileAsync() tile already exists in request queue for modular provider. Moving to front of queue.");
                } else {
                    Log.d(IMapView.LOGTAG, "MapTileModuleProviderBase.loadMaptileAsync() adding tile to request queue for modular provider.");
                }
            }
            this.mPending.put(Long.valueOf(mapTileRequestState.getMapTile()), mapTileRequestState);
        }
        try {
            this.mExecutor.execute(getTileLoader());
        } catch (RejectedExecutionException e) {
            Log.w(IMapView.LOGTAG, "RejectedExecutionException", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearQueue() {
        synchronized (this.mQueueLockObject) {
            this.mPending.clear();
            this.mWorking.clear();
        }
    }

    public void detach() {
        clearQueue();
        this.mExecutor.shutdown();
    }

    protected void removeTileFromQueues(long j) {
        synchronized (this.mQueueLockObject) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "MapTileModuleProviderBase.removeTileFromQueues() on provider: " + getName() + " for tile: " + MapTileIndex.toString(j));
            }
            this.mPending.remove(Long.valueOf(j));
            this.mWorking.remove(Long.valueOf(j));
        }
    }

    public abstract class TileLoader implements Runnable {
        public abstract Drawable loadTile(long j) throws CantContinueException;

        protected void onTileLoaderInit() {
        }

        protected void onTileLoaderShutdown() {
        }

        public TileLoader() {
        }

        public Drawable loadTileIfReachable(long j) throws CantContinueException {
            if (MapTileModuleProviderBase.this.isTileReachable(j)) {
                return loadTile(j);
            }
            return null;
        }

        @Deprecated
        protected Drawable loadTile(MapTileRequestState mapTileRequestState) throws CantContinueException {
            return loadTileIfReachable(mapTileRequestState.getMapTile());
        }

        protected MapTileRequestState nextTile() {
            MapTileRequestState mapTileRequestState;
            synchronized (MapTileModuleProviderBase.this.mQueueLockObject) {
                Long l = null;
                for (Long l2 : MapTileModuleProviderBase.this.mPending.keySet()) {
                    if (!MapTileModuleProviderBase.this.mWorking.containsKey(l2)) {
                        if (Configuration.getInstance().isDebugTileProviders()) {
                            Log.d(IMapView.LOGTAG, "TileLoader.nextTile() on provider: " + MapTileModuleProviderBase.this.getName() + " found tile in working queue: " + MapTileIndex.toString(l2.longValue()));
                        }
                        l = l2;
                    }
                }
                if (l != null) {
                    if (Configuration.getInstance().isDebugTileProviders()) {
                        Log.d(IMapView.LOGTAG, "TileLoader.nextTile() on provider: " + MapTileModuleProviderBase.this.getName() + " adding tile to working queue: " + l);
                    }
                    MapTileModuleProviderBase.this.mWorking.put(l, MapTileModuleProviderBase.this.mPending.get(l));
                }
                mapTileRequestState = l != null ? MapTileModuleProviderBase.this.mPending.get(l) : null;
            }
            return mapTileRequestState;
        }

        protected void tileLoaded(MapTileRequestState mapTileRequestState, Drawable drawable) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "TileLoader.tileLoaded() on provider: " + MapTileModuleProviderBase.this.getName() + " with tile: " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
            }
            MapTileModuleProviderBase.this.removeTileFromQueues(mapTileRequestState.getMapTile());
            ExpirableBitmapDrawable.setState(drawable, -1);
            mapTileRequestState.getCallback().mapTileRequestCompleted(mapTileRequestState, drawable);
        }

        protected void tileLoadedExpired(MapTileRequestState mapTileRequestState, Drawable drawable) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "TileLoader.tileLoadedExpired() on provider: " + MapTileModuleProviderBase.this.getName() + " with tile: " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
            }
            MapTileModuleProviderBase.this.removeTileFromQueues(mapTileRequestState.getMapTile());
            ExpirableBitmapDrawable.setState(drawable, -2);
            mapTileRequestState.getCallback().mapTileRequestExpiredTile(mapTileRequestState, drawable);
        }

        protected void tileLoadedScaled(MapTileRequestState mapTileRequestState, Drawable drawable) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "TileLoader.tileLoadedScaled() on provider: " + MapTileModuleProviderBase.this.getName() + " with tile: " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
            }
            MapTileModuleProviderBase.this.removeTileFromQueues(mapTileRequestState.getMapTile());
            ExpirableBitmapDrawable.setState(drawable, -3);
            mapTileRequestState.getCallback().mapTileRequestExpiredTile(mapTileRequestState, drawable);
        }

        protected void tileLoadedFailed(MapTileRequestState mapTileRequestState) {
            if (Configuration.getInstance().isDebugTileProviders()) {
                Log.d(IMapView.LOGTAG, "TileLoader.tileLoadedFailed() on provider: " + MapTileModuleProviderBase.this.getName() + " with tile: " + MapTileIndex.toString(mapTileRequestState.getMapTile()));
            }
            MapTileModuleProviderBase.this.removeTileFromQueues(mapTileRequestState.getMapTile());
            mapTileRequestState.getCallback().mapTileRequestFailed(mapTileRequestState);
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x00a0 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x009b A[SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void run() {
            /*
                r6 = this;
                r6.onTileLoaderInit()
            L3:
                org.osmdroid.tileprovider.MapTileRequestState r0 = r6.nextTile()
                if (r0 == 0) goto Lbd
                org.osmdroid.config.IConfigurationProvider r1 = org.osmdroid.config.Configuration.getInstance()
                boolean r1 = r1.isDebugTileProviders()
                java.lang.String r2 = "OsmDroid"
                if (r1 == 0) goto L53
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "TileLoader.run() processing next tile: "
                r1.<init>(r3)
                long r3 = r0.getMapTile()
                java.lang.String r3 = org.osmdroid.util.MapTileIndex.toString(r3)
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r3 = ", pending:"
                java.lang.StringBuilder r1 = r1.append(r3)
                org.osmdroid.tileprovider.modules.MapTileModuleProviderBase r3 = org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.this
                java.util.LinkedHashMap<java.lang.Long, org.osmdroid.tileprovider.MapTileRequestState> r3 = r3.mPending
                int r3 = r3.size()
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r3 = ", working:"
                java.lang.StringBuilder r1 = r1.append(r3)
                org.osmdroid.tileprovider.modules.MapTileModuleProviderBase r3 = org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.this
                java.util.HashMap<java.lang.Long, org.osmdroid.tileprovider.MapTileRequestState> r3 = r3.mWorking
                int r3 = r3.size()
                java.lang.StringBuilder r1 = r1.append(r3)
                java.lang.String r1 = r1.toString()
                android.util.Log.d(r2, r1)
            L53:
                long r3 = r0.getMapTile()     // Catch: java.lang.Throwable -> L5c org.osmdroid.tileprovider.modules.CantContinueException -> L78
                android.graphics.drawable.Drawable r1 = r6.loadTileIfReachable(r3)     // Catch: java.lang.Throwable -> L5c org.osmdroid.tileprovider.modules.CantContinueException -> L78
                goto L99
            L5c:
                r1 = move-exception
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Error downloading tile: "
                r3.<init>(r4)
                long r4 = r0.getMapTile()
                java.lang.String r4 = org.osmdroid.util.MapTileIndex.toString(r4)
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                android.util.Log.i(r2, r3, r1)
                goto L98
            L78:
                r1 = move-exception
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Tile loader can't continue: "
                r3.<init>(r4)
                long r4 = r0.getMapTile()
                java.lang.String r4 = org.osmdroid.util.MapTileIndex.toString(r4)
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                android.util.Log.i(r2, r3, r1)
                org.osmdroid.tileprovider.modules.MapTileModuleProviderBase r1 = org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.this
                org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.access$000(r1)
            L98:
                r1 = 0
            L99:
                if (r1 != 0) goto La0
                r6.tileLoadedFailed(r0)
                goto L3
            La0:
                int r2 = org.osmdroid.tileprovider.ExpirableBitmapDrawable.getState(r1)
                r3 = -2
                if (r2 != r3) goto Lac
                r6.tileLoadedExpired(r0, r1)
                goto L3
            Lac:
                int r2 = org.osmdroid.tileprovider.ExpirableBitmapDrawable.getState(r1)
                r3 = -3
                if (r2 != r3) goto Lb8
                r6.tileLoadedScaled(r0, r1)
                goto L3
            Lb8:
                r6.tileLoaded(r0, r1)
                goto L3
            Lbd:
                r6.onTileLoaderShutdown()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.TileLoader.run():void");
        }
    }
}
