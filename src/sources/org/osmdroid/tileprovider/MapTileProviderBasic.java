package org.osmdroid.tileprovider;

import android.content.Context;
import org.osmdroid.tileprovider.modules.IFilesystemCache;
import org.osmdroid.tileprovider.modules.INetworkAvailablityCheck;
import org.osmdroid.tileprovider.modules.MapTileApproximater;
import org.osmdroid.tileprovider.modules.MapTileAssetsProvider;
import org.osmdroid.tileprovider.modules.MapTileDownloader;
import org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider;
import org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase;
import org.osmdroid.tileprovider.modules.MapTileFilesystemProvider;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.modules.MapTileSqlCacheProvider;
import org.osmdroid.tileprovider.modules.NetworkAvailabliltyCheck;
import org.osmdroid.tileprovider.modules.SqlTileWriter;
import org.osmdroid.tileprovider.modules.TileWriter;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.MapTileAreaBorderComputer;
import org.osmdroid.util.MapTileAreaZoomComputer;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class MapTileProviderBasic extends MapTileProviderArray implements IMapTileProviderCallback {
    private final MapTileApproximater mApproximationProvider;
    private final MapTileDownloader mDownloaderProvider;
    private final INetworkAvailablityCheck mNetworkAvailabilityCheck;
    protected IFilesystemCache tileWriter;

    public MapTileProviderBasic(Context context) {
        this(context, TileSourceFactory.DEFAULT_TILE_SOURCE);
    }

    public MapTileProviderBasic(Context context, ITileSource iTileSource) {
        this(context, iTileSource, null);
    }

    public MapTileProviderBasic(Context context, ITileSource iTileSource, IFilesystemCache iFilesystemCache) {
        this(new SimpleRegisterReceiver(context), new NetworkAvailabliltyCheck(context), iTileSource, context, iFilesystemCache);
    }

    public MapTileProviderBasic(IRegisterReceiver iRegisterReceiver, INetworkAvailablityCheck iNetworkAvailablityCheck, ITileSource iTileSource, Context context, IFilesystemCache iFilesystemCache) {
        super(iTileSource, iRegisterReceiver);
        this.mNetworkAvailabilityCheck = iNetworkAvailablityCheck;
        if (iFilesystemCache != null) {
            this.tileWriter = iFilesystemCache;
        } else {
            this.tileWriter = new SqlTileWriter();
        }
        MapTileFileStorageProviderBase mapTileFileStorageProviderBaseCreateAssetsProvider = createAssetsProvider(iRegisterReceiver, iTileSource, context);
        this.mTileProviderList.add(mapTileFileStorageProviderBaseCreateAssetsProvider);
        MapTileFileStorageProviderBase mapTileFileStorageProviderBase = getMapTileFileStorageProviderBase(iRegisterReceiver, iTileSource, this.tileWriter);
        this.mTileProviderList.add(mapTileFileStorageProviderBase);
        MapTileFileStorageProviderBase mapTileFileStorageProviderBaseCreateArchiveProvider = createArchiveProvider(iRegisterReceiver, iTileSource);
        this.mTileProviderList.add(mapTileFileStorageProviderBaseCreateArchiveProvider);
        MapTileApproximater mapTileApproximaterCreateApproximater = createApproximater(mapTileFileStorageProviderBaseCreateAssetsProvider, mapTileFileStorageProviderBase, mapTileFileStorageProviderBaseCreateArchiveProvider);
        this.mApproximationProvider = mapTileApproximaterCreateApproximater;
        this.mTileProviderList.add(mapTileApproximaterCreateApproximater);
        MapTileDownloader mapTileDownloaderCreateDownloaderProvider = createDownloaderProvider(iNetworkAvailablityCheck, iTileSource);
        this.mDownloaderProvider = mapTileDownloaderCreateDownloaderProvider;
        this.mTileProviderList.add(mapTileDownloaderCreateDownloaderProvider);
        getTileCache().getProtectedTileComputers().add(new MapTileAreaZoomComputer(-1));
        getTileCache().getProtectedTileComputers().add(new MapTileAreaBorderComputer(1));
        getTileCache().setAutoEnsureCapacity(false);
        getTileCache().setStressedMemory(false);
        getTileCache().getPreCache().addProvider(mapTileFileStorageProviderBaseCreateAssetsProvider);
        getTileCache().getPreCache().addProvider(mapTileFileStorageProviderBase);
        getTileCache().getPreCache().addProvider(mapTileFileStorageProviderBaseCreateArchiveProvider);
        getTileCache().getPreCache().addProvider(mapTileDownloaderCreateDownloaderProvider);
        getTileCache().getProtectedTileContainers().add(this);
        setOfflineFirst(true);
    }

    protected MapTileApproximater createApproximater(MapTileFileStorageProviderBase mapTileFileStorageProviderBase, MapTileFileStorageProviderBase mapTileFileStorageProviderBase2, MapTileFileStorageProviderBase mapTileFileStorageProviderBase3) {
        MapTileApproximater mapTileApproximater = new MapTileApproximater();
        mapTileApproximater.addProvider(mapTileFileStorageProviderBase);
        mapTileApproximater.addProvider(mapTileFileStorageProviderBase2);
        mapTileApproximater.addProvider(mapTileFileStorageProviderBase3);
        return mapTileApproximater;
    }

    protected MapTileFileStorageProviderBase createArchiveProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource) {
        return new MapTileFileArchiveProvider(iRegisterReceiver, iTileSource);
    }

    protected MapTileFileStorageProviderBase createAssetsProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource, Context context) {
        return new MapTileAssetsProvider(iRegisterReceiver, context.getAssets(), iTileSource);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderArray, org.osmdroid.tileprovider.MapTileProviderBase
    public IFilesystemCache getTileWriter() {
        return this.tileWriter;
    }

    protected MapTileDownloader createDownloaderProvider(INetworkAvailablityCheck iNetworkAvailablityCheck, ITileSource iTileSource) {
        return new MapTileDownloader(iTileSource, this.tileWriter, iNetworkAvailablityCheck);
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderArray, org.osmdroid.tileprovider.MapTileProviderBase
    public void detach() {
        IFilesystemCache iFilesystemCache = this.tileWriter;
        if (iFilesystemCache != null) {
            iFilesystemCache.onDetach();
        }
        this.tileWriter = null;
        super.detach();
    }

    @Override // org.osmdroid.tileprovider.MapTileProviderArray
    protected boolean isDowngradedMode(long j) {
        int zoom;
        INetworkAvailablityCheck iNetworkAvailablityCheck = this.mNetworkAvailabilityCheck;
        if ((iNetworkAvailablityCheck != null && !iNetworkAvailablityCheck.getNetworkAvailable()) || !useDataConnection()) {
            return true;
        }
        int i = -1;
        int i2 = -1;
        for (MapTileModuleProviderBase mapTileModuleProviderBase : this.mTileProviderList) {
            if (mapTileModuleProviderBase.getUsesDataConnection()) {
                int minimumZoomLevel = mapTileModuleProviderBase.getMinimumZoomLevel();
                if (i == -1 || i > minimumZoomLevel) {
                    i = minimumZoomLevel;
                }
                int maximumZoomLevel = mapTileModuleProviderBase.getMaximumZoomLevel();
                if (i2 == -1 || i2 < maximumZoomLevel) {
                    i2 = maximumZoomLevel;
                }
            }
        }
        return i == -1 || i2 == -1 || (zoom = MapTileIndex.getZoom(j)) < i || zoom > i2;
    }

    public static MapTileFileStorageProviderBase getMapTileFileStorageProviderBase(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource, IFilesystemCache iFilesystemCache) {
        if (iFilesystemCache instanceof TileWriter) {
            return new MapTileFilesystemProvider(iRegisterReceiver, iTileSource);
        }
        return new MapTileSqlCacheProvider(iRegisterReceiver, iTileSource);
    }

    public boolean setOfflineFirst(boolean z) {
        int i = -1;
        int i2 = -1;
        int i3 = 0;
        for (MapTileModuleProviderBase mapTileModuleProviderBase : this.mTileProviderList) {
            if (i == -1 && mapTileModuleProviderBase == this.mDownloaderProvider) {
                i = i3;
            }
            if (i2 == -1 && mapTileModuleProviderBase == this.mApproximationProvider) {
                i2 = i3;
            }
            i3++;
        }
        if (i == -1 || i2 == -1) {
            return false;
        }
        if (i2 < i && z) {
            return true;
        }
        if (i2 > i && !z) {
            return true;
        }
        this.mTileProviderList.set(i, this.mApproximationProvider);
        this.mTileProviderList.set(i2, this.mDownloaderProvider);
        return true;
    }
}
