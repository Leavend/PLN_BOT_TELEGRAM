package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.util.concurrent.atomic.AtomicReference;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.util.Counters;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.TileSystem;

/* loaded from: classes3.dex */
public class MapTileSqlCacheProvider extends MapTileFileStorageProviderBase {
    private static final String[] columns = {DatabaseFileArchive.COLUMN_TILE, "expires"};
    private final AtomicReference<ITileSource> mTileSource;
    private SqlTileWriter mWriter;

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getName() {
        return "SQL Cache Archive Provider";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getThreadGroupName() {
        return "sqlcache";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public boolean getUsesDataConnection() {
        return false;
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase
    protected void onMediaMounted() {
    }

    @Deprecated
    public MapTileSqlCacheProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource, long j) {
        this(iRegisterReceiver, iTileSource);
    }

    public MapTileSqlCacheProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource) {
        super(iRegisterReceiver, Configuration.getInstance().getTileFileSystemThreads(), Configuration.getInstance().getTileFileSystemMaxQueueSize());
        this.mTileSource = new AtomicReference<>();
        setTileSource(iTileSource);
        this.mWriter = new SqlTileWriter();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public TileLoader getTileLoader() {
        return new TileLoader();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public int getMinimumZoomLevel() {
        ITileSource iTileSource = this.mTileSource.get();
        if (iTileSource != null) {
            return iTileSource.getMinimumZoomLevel();
        }
        return 0;
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public int getMaximumZoomLevel() {
        ITileSource iTileSource = this.mTileSource.get();
        if (iTileSource != null) {
            return iTileSource.getMaximumZoomLevel();
        }
        return TileSystem.getMaximumZoomLevel();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase
    protected void onMediaUnmounted() {
        SqlTileWriter sqlTileWriter = this.mWriter;
        if (sqlTileWriter != null) {
            sqlTileWriter.onDetach();
        }
        this.mWriter = new SqlTileWriter();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void setTileSource(ITileSource iTileSource) {
        this.mTileSource.set(iTileSource);
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase, org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void detach() {
        SqlTileWriter sqlTileWriter = this.mWriter;
        if (sqlTileWriter != null) {
            sqlTileWriter.onDetach();
        }
        this.mWriter = null;
        super.detach();
    }

    public boolean hasTile(long j) {
        ITileSource iTileSource = this.mTileSource.get();
        return (iTileSource == null || this.mWriter.getExpirationTimestamp(iTileSource, j) == null) ? false : true;
    }

    protected class TileLoader extends MapTileModuleProviderBase.TileLoader {
        protected TileLoader() {
            super();
        }

        @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.TileLoader
        public Drawable loadTile(long j) throws CantContinueException {
            ITileSource iTileSource = (ITileSource) MapTileSqlCacheProvider.this.mTileSource.get();
            if (iTileSource == null) {
                return null;
            }
            if (MapTileSqlCacheProvider.this.mWriter != null) {
                try {
                    Drawable drawableLoadTile = MapTileSqlCacheProvider.this.mWriter.loadTile(iTileSource, j);
                    if (drawableLoadTile == null) {
                        Counters.fileCacheMiss++;
                    } else {
                        Counters.fileCacheHit++;
                    }
                    return drawableLoadTile;
                } catch (BitmapTileSourceBase.LowMemoryException e) {
                    Log.w(IMapView.LOGTAG, "LowMemoryException downloading MapTile: " + MapTileIndex.toString(j) + " : " + e);
                    Counters.fileCacheOOM++;
                    throw new CantContinueException(e);
                } catch (Throwable th) {
                    Log.e(IMapView.LOGTAG, "Error loading tile", th);
                    return null;
                }
            }
            Log.d(IMapView.LOGTAG, "TileLoader failed to load tile due to mWriter being null (map shutdown?)");
            return null;
        }
    }
}
