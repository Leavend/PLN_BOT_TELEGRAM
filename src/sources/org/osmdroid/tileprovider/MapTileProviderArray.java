package org.osmdroid.tileprovider;

import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.osmdroid.tileprovider.modules.IFilesystemCache;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileContainer;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.TileSystem;

/* loaded from: classes3.dex */
public class MapTileProviderArray extends MapTileProviderBase implements MapTileContainer {
    private static final int WORKING_STATUS_FOUND = 1;
    private static final int WORKING_STATUS_STARTED = 0;
    private IRegisterReceiver mRegisterReceiver;
    protected final List<MapTileModuleProviderBase> mTileProviderList;
    private final Map<Long, Integer> mWorking;

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public IFilesystemCache getTileWriter() {
        return null;
    }

    @Deprecated
    protected boolean isDowngradedMode() {
        return false;
    }

    protected boolean isDowngradedMode(long j) {
        return false;
    }

    protected MapTileProviderArray(ITileSource iTileSource, IRegisterReceiver iRegisterReceiver) {
        this(iTileSource, iRegisterReceiver, new MapTileModuleProviderBase[0]);
    }

    public MapTileProviderArray(ITileSource iTileSource, IRegisterReceiver iRegisterReceiver, MapTileModuleProviderBase[] mapTileModuleProviderBaseArr) {
        super(iTileSource);
        this.mWorking = new HashMap();
        this.mRegisterReceiver = iRegisterReceiver;
        ArrayList arrayList = new ArrayList();
        this.mTileProviderList = arrayList;
        Collections.addAll(arrayList, mapTileModuleProviderBaseArr);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public void detach() {
        synchronized (this.mTileProviderList) {
            Iterator<MapTileModuleProviderBase> it = this.mTileProviderList.iterator();
            while (it.hasNext()) {
                it.next().detach();
            }
        }
        synchronized (this.mWorking) {
            this.mWorking.clear();
        }
        IRegisterReceiver iRegisterReceiver = this.mRegisterReceiver;
        if (iRegisterReceiver != null) {
            iRegisterReceiver.destroy();
            this.mRegisterReceiver = null;
        }
        super.detach();
    }

    @Override // org.osmdroid.util.MapTileContainer
    public boolean contains(long j) {
        boolean zContainsKey;
        synchronized (this.mWorking) {
            zContainsKey = this.mWorking.containsKey(Long.valueOf(j));
        }
        return zContainsKey;
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public Drawable getMapTile(long j) {
        Drawable mapTile = this.mTileCache.getMapTile(j);
        if (mapTile != null && (ExpirableBitmapDrawable.getState(mapTile) == -1 || isDowngradedMode(j))) {
            return mapTile;
        }
        synchronized (this.mWorking) {
            if (this.mWorking.containsKey(Long.valueOf(j))) {
                return mapTile;
            }
            this.mWorking.put(Long.valueOf(j), 0);
            runAsyncNextProvider(new MapTileRequestState(j, this.mTileProviderList, this));
            return mapTile;
        }
    }

    private void remove(long j) {
        synchronized (this.mWorking) {
            this.mWorking.remove(Long.valueOf(j));
        }
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase, org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestCompleted(MapTileRequestState mapTileRequestState, Drawable drawable) {
        super.mapTileRequestCompleted(mapTileRequestState, drawable);
        remove(mapTileRequestState.getMapTile());
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase, org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestFailed(MapTileRequestState mapTileRequestState) {
        runAsyncNextProvider(mapTileRequestState);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase, org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestFailedExceedsMaxQueueSize(MapTileRequestState mapTileRequestState) {
        super.mapTileRequestFailed(mapTileRequestState);
        remove(mapTileRequestState.getMapTile());
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase, org.osmdroid.tileprovider.IMapTileProviderCallback
    public void mapTileRequestExpiredTile(MapTileRequestState mapTileRequestState, Drawable drawable) {
        super.mapTileRequestExpiredTile(mapTileRequestState, drawable);
        synchronized (this.mWorking) {
            this.mWorking.put(Long.valueOf(mapTileRequestState.getMapTile()), 1);
        }
        runAsyncNextProvider(mapTileRequestState);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public long getQueueSize() {
        long size;
        synchronized (this.mWorking) {
            size = this.mWorking.size();
        }
        return size;
    }

    protected MapTileModuleProviderBase findNextAppropriateProvider(MapTileRequestState mapTileRequestState) {
        MapTileModuleProviderBase nextProvider;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (true) {
            nextProvider = mapTileRequestState.getNextProvider();
            if (nextProvider != null) {
                boolean z4 = true;
                z = !getProviderExists(nextProvider);
                boolean z5 = !useDataConnection() && nextProvider.getUsesDataConnection();
                int zoom = MapTileIndex.getZoom(mapTileRequestState.getMapTile());
                if (zoom <= nextProvider.getMaximumZoomLevel() && zoom >= nextProvider.getMinimumZoomLevel()) {
                    z4 = false;
                }
                boolean z6 = z5;
                z3 = z4;
                z2 = z6;
            }
            if (nextProvider == null || (!z && !z2 && !z3)) {
                break;
            }
        }
        return nextProvider;
    }

    private void runAsyncNextProvider(MapTileRequestState mapTileRequestState) {
        Integer num;
        MapTileModuleProviderBase mapTileModuleProviderBaseFindNextAppropriateProvider = findNextAppropriateProvider(mapTileRequestState);
        if (mapTileModuleProviderBaseFindNextAppropriateProvider != null) {
            mapTileModuleProviderBaseFindNextAppropriateProvider.loadMapTileAsync(mapTileRequestState);
            return;
        }
        synchronized (this.mWorking) {
            num = this.mWorking.get(Long.valueOf(mapTileRequestState.getMapTile()));
        }
        if (num != null && num.intValue() == 0) {
            super.mapTileRequestFailed(mapTileRequestState);
        }
        remove(mapTileRequestState.getMapTile());
    }

    public boolean getProviderExists(MapTileModuleProviderBase mapTileModuleProviderBase) {
        return this.mTileProviderList.contains(mapTileModuleProviderBase);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public int getMinimumZoomLevel() {
        int maximumZoomLevel = TileSystem.getMaximumZoomLevel();
        synchronized (this.mTileProviderList) {
            for (MapTileModuleProviderBase mapTileModuleProviderBase : this.mTileProviderList) {
                if (mapTileModuleProviderBase.getMinimumZoomLevel() < maximumZoomLevel) {
                    maximumZoomLevel = mapTileModuleProviderBase.getMinimumZoomLevel();
                }
            }
        }
        return maximumZoomLevel;
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public int getMaximumZoomLevel() {
        int maximumZoomLevel;
        synchronized (this.mTileProviderList) {
            maximumZoomLevel = 0;
            for (MapTileModuleProviderBase mapTileModuleProviderBase : this.mTileProviderList) {
                if (mapTileModuleProviderBase.getMaximumZoomLevel() > maximumZoomLevel) {
                    maximumZoomLevel = mapTileModuleProviderBase.getMaximumZoomLevel();
                }
            }
        }
        return maximumZoomLevel;
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderBase
    public void setTileSource(ITileSource iTileSource) {
        super.setTileSource(iTileSource);
        synchronized (this.mTileProviderList) {
            Iterator<MapTileModuleProviderBase> it = this.mTileProviderList.iterator();
            while (it.hasNext()) {
                it.next().setTileSource(iTileSource);
                clearTileCache();
            }
        }
    }
}
