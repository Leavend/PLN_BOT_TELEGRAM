package org.osmdroid.tileprovider.modules;

import android.util.Log;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.tileprovider.modules.MapTileModuleProviderBase;
import org.osmdroid.tileprovider.tilesource.ITileSource;
import org.osmdroid.util.MapTileIndex;
import org.osmdroid.util.TileSystem;

/* loaded from: classes3.dex */
public class MapTileFileArchiveProvider extends MapTileFileStorageProviderBase {
    private final boolean ignoreTileSource;
    private final ArrayList<IArchiveFile> mArchiveFiles;
    private final boolean mSpecificArchivesProvided;
    private final AtomicReference<ITileSource> mTileSource;

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getName() {
        return "File Archive Provider";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    protected String getThreadGroupName() {
        return "filearchive";
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public boolean getUsesDataConnection() {
        return false;
    }

    public MapTileFileArchiveProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource, IArchiveFile[] iArchiveFileArr) {
        this(iRegisterReceiver, iTileSource, iArchiveFileArr, false);
    }

    public MapTileFileArchiveProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource, IArchiveFile[] iArchiveFileArr, boolean z) throws IllegalAccessException, InstantiationException {
        super(iRegisterReceiver, Configuration.getInstance().getTileFileSystemThreads(), Configuration.getInstance().getTileFileSystemMaxQueueSize());
        this.mArchiveFiles = new ArrayList<>();
        this.mTileSource = new AtomicReference<>();
        this.ignoreTileSource = z;
        setTileSource(iTileSource);
        if (iArchiveFileArr == null) {
            this.mSpecificArchivesProvided = false;
            findArchiveFiles();
            return;
        }
        this.mSpecificArchivesProvided = true;
        for (int length = iArchiveFileArr.length - 1; length >= 0; length--) {
            this.mArchiveFiles.add(iArchiveFileArr[length]);
        }
    }

    public MapTileFileArchiveProvider(IRegisterReceiver iRegisterReceiver, ITileSource iTileSource) {
        this(iRegisterReceiver, iTileSource, null);
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
    protected void onMediaMounted() throws IllegalAccessException, InstantiationException {
        if (this.mSpecificArchivesProvided) {
            return;
        }
        findArchiveFiles();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase
    protected void onMediaUnmounted() throws IllegalAccessException, InstantiationException {
        if (this.mSpecificArchivesProvided) {
            return;
        }
        findArchiveFiles();
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void setTileSource(ITileSource iTileSource) {
        this.mTileSource.set(iTileSource);
    }

    @Override // org.osmdroid.tileprovider.modules.MapTileFileStorageProviderBase, org.osmdroid.tileprovider.modules.MapTileModuleProviderBase
    public void detach() {
        clearArcives();
        super.detach();
    }

    private void clearArcives() {
        while (!this.mArchiveFiles.isEmpty()) {
            IArchiveFile iArchiveFile = this.mArchiveFiles.get(0);
            if (iArchiveFile != null) {
                iArchiveFile.close();
            }
            this.mArchiveFiles.remove(0);
        }
    }

    private void findArchiveFiles() throws IllegalAccessException, InstantiationException {
        File[] fileArrListFiles;
        clearArcives();
        File osmdroidBasePath = Configuration.getInstance().getOsmdroidBasePath();
        if (osmdroidBasePath == null || (fileArrListFiles = osmdroidBasePath.listFiles()) == null) {
            return;
        }
        for (File file : fileArrListFiles) {
            IArchiveFile archiveFile = ArchiveFileFactory.getArchiveFile(file);
            if (archiveFile != null) {
                archiveFile.setIgnoreTileSource(this.ignoreTileSource);
                this.mArchiveFiles.add(archiveFile);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized InputStream getInputStream(long j, ITileSource iTileSource) {
        InputStream inputStream;
        Iterator<IArchiveFile> it = this.mArchiveFiles.iterator();
        while (it.hasNext()) {
            IArchiveFile next = it.next();
            if (next != null && (inputStream = next.getInputStream(iTileSource, j)) != null) {
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "Found tile " + MapTileIndex.toString(j) + " in " + next);
                }
                return inputStream;
            }
        }
        return null;
    }

    protected class TileLoader extends MapTileModuleProviderBase.TileLoader {
        protected TileLoader() {
            super();
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x0063 A[DONT_GENERATE, PHI: r2 r4
          0x0063: PHI (r2v3 java.io.InputStream) = (r2v2 java.io.InputStream), (r2v5 java.io.InputStream) binds: [B:22:0x006e, B:17:0x0061] A[DONT_GENERATE, DONT_INLINE]
          0x0063: PHI (r4v3 android.graphics.drawable.Drawable) = (r4v0 android.graphics.drawable.Drawable), (r4v6 android.graphics.drawable.Drawable) binds: [B:22:0x006e, B:17:0x0061] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // org.osmdroid.tileprovider.modules.MapTileModuleProviderBase.TileLoader
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public android.graphics.drawable.Drawable loadTile(long r7) throws java.io.IOException {
            /*
                r6 = this;
                java.lang.String r0 = "OsmDroid"
                java.lang.String r1 = "Use tile from archive: "
                java.lang.String r2 = "Archives - Tile doesn't exist: "
                org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider r3 = org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider.this
                java.util.concurrent.atomic.AtomicReference r3 = org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider.access$000(r3)
                java.lang.Object r3 = r3.get()
                org.osmdroid.tileprovider.tilesource.ITileSource r3 = (org.osmdroid.tileprovider.tilesource.ITileSource) r3
                r4 = 0
                if (r3 != 0) goto L16
                return r4
            L16:
                org.osmdroid.config.IConfigurationProvider r5 = org.osmdroid.config.Configuration.getInstance()     // Catch: java.lang.Throwable -> L67
                boolean r5 = r5.isDebugMode()     // Catch: java.lang.Throwable -> L67
                if (r5 == 0) goto L34
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67
                r5.<init>(r2)     // Catch: java.lang.Throwable -> L67
                java.lang.String r2 = org.osmdroid.util.MapTileIndex.toString(r7)     // Catch: java.lang.Throwable -> L67
                java.lang.StringBuilder r2 = r5.append(r2)     // Catch: java.lang.Throwable -> L67
                java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L67
                android.util.Log.d(r0, r2)     // Catch: java.lang.Throwable -> L67
            L34:
                org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider r2 = org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider.this     // Catch: java.lang.Throwable -> L67
                java.io.InputStream r2 = org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider.access$100(r2, r7, r3)     // Catch: java.lang.Throwable -> L67
                if (r2 == 0) goto L61
                org.osmdroid.config.IConfigurationProvider r5 = org.osmdroid.config.Configuration.getInstance()     // Catch: java.lang.Throwable -> L5f
                boolean r5 = r5.isDebugMode()     // Catch: java.lang.Throwable -> L5f
                if (r5 == 0) goto L5a
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L5f
                r5.<init>(r1)     // Catch: java.lang.Throwable -> L5f
                java.lang.String r7 = org.osmdroid.util.MapTileIndex.toString(r7)     // Catch: java.lang.Throwable -> L5f
                java.lang.StringBuilder r7 = r5.append(r7)     // Catch: java.lang.Throwable -> L5f
                java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L5f
                android.util.Log.d(r0, r7)     // Catch: java.lang.Throwable -> L5f
            L5a:
                android.graphics.drawable.Drawable r4 = r3.getDrawable(r2)     // Catch: java.lang.Throwable -> L5f
                goto L61
            L5f:
                r7 = move-exception
                goto L69
            L61:
                if (r2 == 0) goto L71
            L63:
                org.osmdroid.tileprovider.util.StreamUtils.closeStream(r2)
                goto L71
            L67:
                r7 = move-exception
                r2 = r4
            L69:
                java.lang.String r8 = "Error loading tile"
                android.util.Log.e(r0, r8, r7)     // Catch: java.lang.Throwable -> L72
                if (r2 == 0) goto L71
                goto L63
            L71:
                return r4
            L72:
                r7 = move-exception
                if (r2 == 0) goto L78
                org.osmdroid.tileprovider.util.StreamUtils.closeStream(r2)
            L78:
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider.TileLoader.loadTile(long):android.graphics.drawable.Drawable");
        }
    }
}
