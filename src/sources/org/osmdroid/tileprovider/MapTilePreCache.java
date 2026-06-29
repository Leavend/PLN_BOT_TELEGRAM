package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.tileprovider.modules.MapTileDownloader;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.util.GarbageCollector;
import org.osmdroid.util.MapTileArea;
import org.osmdroid.util.MapTileAreaList;

/* loaded from: classes3.dex */
public class MapTilePreCache {
    private final MapTileCache mCache;
    private Iterator<Long> mTileIndices;
    private final List<MapTileModuleProviderBase> mProviders = new ArrayList();
    private final MapTileAreaList mTileAreas = new MapTileAreaList();
    private final GarbageCollector mGC = new GarbageCollector(new Runnable() { // from class: org.osmdroid.tileprovider.MapTilePreCache.1
        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                long next = MapTilePreCache.this.next();
                if (next == -1) {
                    return;
                } else {
                    MapTilePreCache.this.search(next);
                }
            }
        }
    });

    public MapTilePreCache(MapTileCache mapTileCache) {
        this.mCache = mapTileCache;
    }

    public void addProvider(MapTileModuleProviderBase mapTileModuleProviderBase) {
        this.mProviders.add(mapTileModuleProviderBase);
    }

    public void fill() {
        if (this.mGC.isRunning()) {
            return;
        }
        refresh();
        this.mGC.gc();
    }

    private void refresh() {
        MapTileArea mapTileArea;
        synchronized (this.mTileAreas) {
            int i = 0;
            for (MapTileArea mapTileArea2 : this.mCache.getAdditionalMapTileList().getList()) {
                if (i < this.mTileAreas.getList().size()) {
                    mapTileArea = this.mTileAreas.getList().get(i);
                } else {
                    mapTileArea = new MapTileArea();
                    this.mTileAreas.getList().add(mapTileArea);
                }
                mapTileArea.set(mapTileArea2);
                i++;
            }
            while (i < this.mTileAreas.getList().size()) {
                this.mTileAreas.getList().remove(this.mTileAreas.getList().size() - 1);
            }
            this.mTileIndices = this.mTileAreas.iterator();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long next() {
        long jLongValue;
        do {
            synchronized (this.mTileAreas) {
                if (!this.mTileIndices.hasNext()) {
                    return -1L;
                }
                jLongValue = this.mTileIndices.next().longValue();
            }
        } while (this.mCache.getMapTile(jLongValue) != null);
        return jLongValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void search(long j) {
        for (MapTileModuleProviderBase mapTileModuleProviderBase : this.mProviders) {
            if (mapTileModuleProviderBase instanceof MapTileDownloader) {
                ITileSource tileSource = ((MapTileDownloader) mapTileModuleProviderBase).getTileSource();
                if (!(tileSource instanceof OnlineTileSourceBase) || ((OnlineTileSourceBase) tileSource).getTileSourcePolicy().acceptsPreventive()) {
                }
            }
            Drawable drawableLoadTileIfReachable = mapTileModuleProviderBase.getTileLoader().loadTileIfReachable(j);
            if (drawableLoadTileIfReachable != null) {
                this.mCache.putTile(j, drawableLoadTileIfReachable);
                return;
            }
        }
    }
}
