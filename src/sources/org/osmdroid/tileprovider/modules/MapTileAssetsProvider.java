package org.osmdroid.tileprovider.modules;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.TileSystem;

/* loaded from: classes3.dex */
public class MapTileAssetsProvider extends MapTileFileStorageProviderBase {
    private final AssetManager mAssets;
    private final AtomicReference<ITileSource> mTileSource;

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getName() {
        return "Assets Cache Provider";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getThreadGroupName() {
        return "assets";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public boolean getUsesDataConnection() {
        return false;
    }

    public MapTileAssetsProvider(IRegisterReceiver iRegisterReceiver, AssetManager assetManager) {
        this(iRegisterReceiver, assetManager, TileSourceFactory.DEFAULT_TILE_SOURCE);
    }

    public MapTileAssetsProvider(IRegisterReceiver iRegisterReceiver, AssetManager assetManager, ITileSource iTileSource) {
        this(iRegisterReceiver, assetManager, iTileSource, Configuration.getInstance().getTileDownloadThreads(), Configuration.getInstance().getTileDownloadMaxQueueSize());
    }

    public MapTileAssetsProvider(IRegisterReceiver iRegisterReceiver, AssetManager assetManager, ITileSource iTileSource, int i, int i2) {
        super(iRegisterReceiver, i, i2);
        this.mTileSource = new AtomicReference<>();
        setTileSource(iTileSource);
        this.mAssets = assetManager;
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public TileLoader getTileLoader() {
        return new TileLoader(this.mAssets);
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

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void setTileSource(ITileSource iTileSource) {
        this.mTileSource.set(iTileSource);
    }

    protected class TileLoader extends MapTileModuleProviderBase.TileLoader {
        private AssetManager mAssets;

        public TileLoader(AssetManager assetManager) {
            super();
            this.mAssets = assetManager;
        }

        @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.TileLoader
        public Drawable loadTile(long j) throws CantContinueException {
            ITileSource iTileSource = (ITileSource) MapTileAssetsProvider.this.mTileSource.get();
            if (iTileSource == null) {
                return null;
            }
            try {
                return iTileSource.getDrawable(this.mAssets.open(iTileSource.getTileRelativeFilenameString(j)));
            } catch (IOException unused) {
                return null;
            } catch (BitmapTileSourceBase.LowMemoryException e) {
                throw new CantContinueException(e);
            }
        }
    }
}
