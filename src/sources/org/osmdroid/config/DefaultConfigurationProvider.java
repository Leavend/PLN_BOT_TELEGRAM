package org.osmdroid.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.net.Proxy;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.constants.OpenStreetMapTileProviderConstants;
import org.osmdroid.tileprovider.modules.SqlTileWriter;
import org.osmdroid.tileprovider.util.StorageUtils;

/* loaded from: classes3.dex */
public class DefaultConfigurationProvider implements IConfigurationProvider {
    public static final String DEFAULT_USER_AGENT = "osmdroid";
    private String mNormalizedUserAgent;
    protected File osmdroidBasePath;
    protected File osmdroidTileCache;
    protected long gpsWaitTime = 20000;
    protected boolean debugMode = false;
    protected boolean debugMapView = false;
    protected boolean debugTileProviders = false;
    protected boolean debugMapTileDownloader = false;
    protected boolean isMapViewHardwareAccelerated = true;
    protected String userAgentValue = DEFAULT_USER_AGENT;
    protected String userAgentHttpHeader = "User-Agent";
    private final Map<String, String> mAdditionalHttpRequestProperties = new HashMap();
    protected short cacheMapTileCount = 9;
    protected short tileDownloadThreads = 2;
    protected short tileFileSystemThreads = 8;
    protected short tileDownloadMaxQueueSize = 40;
    protected short tileFileSystemMaxQueueSize = 40;
    protected long tileFileSystemCacheMaxBytes = 629145600;
    protected long tileFileSystemCacheTrimBytes = 524288000;
    protected SimpleDateFormat httpHeaderDateTimeFormat = new SimpleDateFormat(OpenStreetMapTileProviderConstants.HTTP_EXPIRES_HEADER_FORMAT, Locale.US);
    protected long expirationAdder = 0;
    protected Long expirationOverride = null;
    protected Proxy httpProxy = null;
    protected int animationSpeedDefault = 1000;
    protected int animationSpeedShort = 500;
    protected boolean mapViewRecycler = true;
    protected short cacheTileOvershoot = 0;
    protected long mTileGCFrequencyInMillis = 300000;
    protected int mTileGCBulkSize = 20;
    protected long mTileGCBulkPauseInMillis = 500;
    protected boolean mTileDownloaderFollowRedirects = true;
    protected boolean enforceTileSystemBounds = false;

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getGpsWaitTime() {
        return this.gpsWaitTime;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setGpsWaitTime(long j) {
        this.gpsWaitTime = j;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isDebugMode() {
        return this.debugMode;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setDebugMode(boolean z) {
        this.debugMode = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isDebugMapView() {
        return this.debugMapView;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setDebugMapView(boolean z) {
        this.debugMapView = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isDebugTileProviders() {
        return this.debugTileProviders;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setDebugTileProviders(boolean z) {
        this.debugTileProviders = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isDebugMapTileDownloader() {
        return this.debugMapTileDownloader;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setDebugMapTileDownloader(boolean z) {
        this.debugMapTileDownloader = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isMapViewHardwareAccelerated() {
        return this.isMapViewHardwareAccelerated;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setMapViewHardwareAccelerated(boolean z) {
        this.isMapViewHardwareAccelerated = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public String getUserAgentValue() {
        return this.userAgentValue;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setUserAgentValue(String str) {
        this.userAgentValue = str;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public Map<String, String> getAdditionalHttpRequestProperties() {
        return this.mAdditionalHttpRequestProperties;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getCacheMapTileCount() {
        return this.cacheMapTileCount;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setCacheMapTileCount(short s) {
        this.cacheMapTileCount = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getTileDownloadThreads() {
        return this.tileDownloadThreads;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileDownloadThreads(short s) {
        this.tileDownloadThreads = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getTileFileSystemThreads() {
        return this.tileFileSystemThreads;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileFileSystemThreads(short s) {
        this.tileFileSystemThreads = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getTileDownloadMaxQueueSize() {
        return this.tileDownloadMaxQueueSize;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileDownloadMaxQueueSize(short s) {
        this.tileDownloadMaxQueueSize = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getTileFileSystemMaxQueueSize() {
        return this.tileFileSystemMaxQueueSize;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileFileSystemMaxQueueSize(short s) {
        this.tileFileSystemMaxQueueSize = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getTileFileSystemCacheMaxBytes() {
        return this.tileFileSystemCacheMaxBytes;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileFileSystemCacheMaxBytes(long j) {
        this.tileFileSystemCacheMaxBytes = j;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getTileFileSystemCacheTrimBytes() {
        return this.tileFileSystemCacheTrimBytes;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileFileSystemCacheTrimBytes(long j) {
        this.tileFileSystemCacheTrimBytes = j;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public SimpleDateFormat getHttpHeaderDateTimeFormat() {
        return this.httpHeaderDateTimeFormat;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setHttpHeaderDateTimeFormat(SimpleDateFormat simpleDateFormat) {
        this.httpHeaderDateTimeFormat = simpleDateFormat;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public Proxy getHttpProxy() {
        return this.httpProxy;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setHttpProxy(Proxy proxy) {
        this.httpProxy = proxy;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public File getOsmdroidBasePath() {
        return getOsmdroidBasePath(null);
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public File getOsmdroidBasePath(Context context) {
        try {
            if (this.osmdroidBasePath == null) {
                StorageUtils.StorageInfo bestWritableStorage = StorageUtils.getBestWritableStorage(context);
                if (bestWritableStorage != null) {
                    File file = new File(bestWritableStorage.path, DEFAULT_USER_AGENT);
                    this.osmdroidBasePath = file;
                    file.mkdirs();
                } else if (!new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), DEFAULT_USER_AGENT).mkdirs()) {
                    Log.e(IMapView.LOGTAG, "Directory not created");
                }
            }
        } catch (Exception e) {
            Log.d(IMapView.LOGTAG, "Unable to create base path at " + this.osmdroidBasePath, e);
        }
        if (this.osmdroidBasePath == null && context != null) {
            this.osmdroidBasePath = context.getFilesDir();
        }
        return this.osmdroidBasePath;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setOsmdroidBasePath(File file) {
        this.osmdroidBasePath = file;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public File getOsmdroidTileCache() {
        return getOsmdroidTileCache(null);
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public File getOsmdroidTileCache(Context context) {
        if (this.osmdroidTileCache == null) {
            this.osmdroidTileCache = new File(getOsmdroidBasePath(context), "tiles");
        }
        try {
            this.osmdroidTileCache.mkdirs();
        } catch (Exception e) {
            Log.d(IMapView.LOGTAG, "Unable to create tile cache path at " + this.osmdroidTileCache, e);
        }
        return this.osmdroidTileCache;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setOsmdroidTileCache(File file) {
        this.osmdroidTileCache = file;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public String getUserAgentHttpHeader() {
        return this.userAgentHttpHeader;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setUserAgentHttpHeader(String str) {
        this.userAgentHttpHeader = str;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void load(Context context, SharedPreferences sharedPreferences) {
        this.mNormalizedUserAgent = computeNormalizedUserAgent(context);
        String string = sharedPreferences.getString("osmdroid.basePath", null);
        if (string == null || !new File(string).exists()) {
            File osmdroidBasePath = getOsmdroidBasePath(context);
            File osmdroidTileCache = getOsmdroidTileCache(context);
            if (!osmdroidBasePath.exists() || !StorageUtils.isWritable(osmdroidBasePath)) {
                osmdroidBasePath = new File(context.getFilesDir(), DEFAULT_USER_AGENT);
                osmdroidTileCache = new File(osmdroidBasePath, "tiles");
                osmdroidTileCache.mkdirs();
            }
            SharedPreferences.Editor editorEdit = sharedPreferences.edit();
            editorEdit.putString("osmdroid.basePath", osmdroidBasePath.getAbsolutePath());
            editorEdit.putString("osmdroid.cachePath", osmdroidTileCache.getAbsolutePath());
            commit(editorEdit);
            setOsmdroidBasePath(osmdroidBasePath);
            setOsmdroidTileCache(osmdroidTileCache);
            setUserAgentValue(context.getPackageName());
            save(context, sharedPreferences);
        } else {
            setOsmdroidBasePath(new File(sharedPreferences.getString("osmdroid.basePath", getOsmdroidBasePath(context).getAbsolutePath())));
            setOsmdroidTileCache(new File(sharedPreferences.getString("osmdroid.cachePath", getOsmdroidTileCache(context).getAbsolutePath())));
            setDebugMode(sharedPreferences.getBoolean("osmdroid.DebugMode", this.debugMode));
            setDebugMapTileDownloader(sharedPreferences.getBoolean("osmdroid.DebugDownloading", this.debugMapTileDownloader));
            setDebugMapView(sharedPreferences.getBoolean("osmdroid.DebugMapView", this.debugMapView));
            setDebugTileProviders(sharedPreferences.getBoolean("osmdroid.DebugTileProvider", this.debugTileProviders));
            setMapViewHardwareAccelerated(sharedPreferences.getBoolean("osmdroid.HardwareAcceleration", this.isMapViewHardwareAccelerated));
            setUserAgentValue(sharedPreferences.getString("osmdroid.userAgentValue", context.getPackageName()));
            load(sharedPreferences, this.mAdditionalHttpRequestProperties, "osmdroid.additionalHttpRequestProperty.");
            setGpsWaitTime(sharedPreferences.getLong("osmdroid.gpsWaitTime", this.gpsWaitTime));
            setTileDownloadThreads((short) sharedPreferences.getInt("osmdroid.tileDownloadThreads", this.tileDownloadThreads));
            setTileFileSystemThreads((short) sharedPreferences.getInt("osmdroid.tileFileSystemThreads", this.tileFileSystemThreads));
            setTileDownloadMaxQueueSize((short) sharedPreferences.getInt("osmdroid.tileDownloadMaxQueueSize", this.tileDownloadMaxQueueSize));
            setTileFileSystemMaxQueueSize((short) sharedPreferences.getInt("osmdroid.tileFileSystemMaxQueueSize", this.tileFileSystemMaxQueueSize));
            setExpirationExtendedDuration(sharedPreferences.getLong("osmdroid.ExpirationExtendedDuration", this.expirationAdder));
            setMapViewRecyclerFriendly(sharedPreferences.getBoolean("osmdroid.mapViewRecycler", this.mapViewRecycler));
            setAnimationSpeedDefault(sharedPreferences.getInt("osmdroid.ZoomSpeedDefault", this.animationSpeedDefault));
            setAnimationSpeedShort(sharedPreferences.getInt("osmdroid.animationSpeedShort", this.animationSpeedShort));
            setCacheMapTileOvershoot((short) sharedPreferences.getInt("osmdroid.cacheTileOvershoot", this.cacheTileOvershoot));
            setMapTileDownloaderFollowRedirects(sharedPreferences.getBoolean("osmdroid.TileDownloaderFollowRedirects", this.mTileDownloaderFollowRedirects));
            setEnforceTileSystemBounds(sharedPreferences.getBoolean("osmdroid.enforceTileSystemBounds", false));
            if (sharedPreferences.contains("osmdroid.ExpirationOverride")) {
                Long lValueOf = Long.valueOf(sharedPreferences.getLong("osmdroid.ExpirationOverride", -1L));
                this.expirationOverride = lValueOf;
                if (lValueOf != null && lValueOf.longValue() == -1) {
                    this.expirationOverride = null;
                }
            }
        }
        File file = new File(getOsmdroidTileCache().getAbsolutePath() + File.separator + SqlTileWriter.DATABASE_FILENAME);
        long freeSpace = getOsmdroidTileCache().getFreeSpace() + (file.exists() ? file.length() : 0L);
        if (getTileFileSystemCacheMaxBytes() > freeSpace) {
            double d = freeSpace;
            setTileFileSystemCacheMaxBytes((long) (0.95d * d));
            setTileFileSystemCacheTrimBytes((long) (d * 0.9d));
        }
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void save(Context context, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        editorEdit.putString("osmdroid.basePath", getOsmdroidBasePath().getAbsolutePath());
        editorEdit.putString("osmdroid.cachePath", getOsmdroidTileCache().getAbsolutePath());
        editorEdit.putBoolean("osmdroid.DebugMode", isDebugMode());
        editorEdit.putBoolean("osmdroid.DebugDownloading", isDebugMapTileDownloader());
        editorEdit.putBoolean("osmdroid.DebugMapView", isDebugMapView());
        editorEdit.putBoolean("osmdroid.DebugTileProvider", isDebugTileProviders());
        editorEdit.putBoolean("osmdroid.HardwareAcceleration", isMapViewHardwareAccelerated());
        editorEdit.putBoolean("osmdroid.TileDownloaderFollowRedirects", isMapTileDownloaderFollowRedirects());
        editorEdit.putString("osmdroid.userAgentValue", getUserAgentValue());
        save(sharedPreferences, editorEdit, this.mAdditionalHttpRequestProperties, "osmdroid.additionalHttpRequestProperty.");
        editorEdit.putLong("osmdroid.gpsWaitTime", this.gpsWaitTime);
        editorEdit.putInt("osmdroid.cacheMapTileCount", this.cacheMapTileCount);
        editorEdit.putInt("osmdroid.tileDownloadThreads", this.tileDownloadThreads);
        editorEdit.putInt("osmdroid.tileFileSystemThreads", this.tileFileSystemThreads);
        editorEdit.putInt("osmdroid.tileDownloadMaxQueueSize", this.tileDownloadMaxQueueSize);
        editorEdit.putInt("osmdroid.tileFileSystemMaxQueueSize", this.tileFileSystemMaxQueueSize);
        editorEdit.putLong("osmdroid.ExpirationExtendedDuration", this.expirationAdder);
        Long l = this.expirationOverride;
        if (l != null) {
            editorEdit.putLong("osmdroid.ExpirationOverride", l.longValue());
        }
        editorEdit.putInt("osmdroid.ZoomSpeedDefault", this.animationSpeedDefault);
        editorEdit.putInt("osmdroid.animationSpeedShort", this.animationSpeedShort);
        editorEdit.putBoolean("osmdroid.mapViewRecycler", this.mapViewRecycler);
        editorEdit.putInt("osmdroid.cacheTileOvershoot", this.cacheTileOvershoot);
        editorEdit.putBoolean("osmdroid.enforceTileSystemBounds", this.enforceTileSystemBounds);
        commit(editorEdit);
    }

    private static void load(SharedPreferences sharedPreferences, Map<String, String> map, String str) {
        if (str == null || map == null) {
            return;
        }
        map.clear();
        for (String str2 : sharedPreferences.getAll().keySet()) {
            if (str2 != null && str2.startsWith(str)) {
                map.put(str2.substring(str.length()), sharedPreferences.getString(str2, null));
            }
        }
    }

    private static void save(SharedPreferences sharedPreferences, SharedPreferences.Editor editor, Map<String, String> map, String str) {
        for (String str2 : sharedPreferences.getAll().keySet()) {
            if (str2.startsWith(str)) {
                editor.remove(str2);
            }
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            editor.putString(str + entry.getKey(), entry.getValue());
        }
    }

    private static void commit(SharedPreferences.Editor editor) {
        editor.apply();
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getExpirationExtendedDuration() {
        return this.expirationAdder;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setExpirationExtendedDuration(long j) {
        if (j < 0) {
            this.expirationAdder = 0L;
        } else {
            this.expirationAdder = j;
        }
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setExpirationOverrideDuration(Long l) {
        this.expirationOverride = l;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public Long getExpirationOverrideDuration() {
        return this.expirationOverride;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setAnimationSpeedDefault(int i) {
        this.animationSpeedDefault = i;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public int getAnimationSpeedDefault() {
        return this.animationSpeedDefault;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setAnimationSpeedShort(int i) {
        this.animationSpeedShort = i;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public int getAnimationSpeedShort() {
        return this.animationSpeedShort;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isMapViewRecyclerFriendly() {
        return this.mapViewRecycler;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setMapViewRecyclerFriendly(boolean z) {
        this.mapViewRecycler = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setCacheMapTileOvershoot(short s) {
        this.cacheTileOvershoot = s;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public short getCacheMapTileOvershoot() {
        return this.cacheTileOvershoot;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getTileGCFrequencyInMillis() {
        return this.mTileGCFrequencyInMillis;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileGCFrequencyInMillis(long j) {
        this.mTileGCFrequencyInMillis = j;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public int getTileGCBulkSize() {
        return this.mTileGCBulkSize;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileGCBulkSize(int i) {
        this.mTileGCBulkSize = i;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public long getTileGCBulkPauseInMillis() {
        return this.mTileGCBulkPauseInMillis;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setTileGCBulkPauseInMillis(long j) {
        this.mTileGCBulkPauseInMillis = j;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setMapTileDownloaderFollowRedirects(boolean z) {
        this.mTileDownloaderFollowRedirects = z;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isMapTileDownloaderFollowRedirects() {
        return this.mTileDownloaderFollowRedirects;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public String getNormalizedUserAgent() {
        return this.mNormalizedUserAgent;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public boolean isEnforceTileSystemBounds() {
        return this.enforceTileSystemBounds;
    }

    @Override // org.osmdroid.config.IConfigurationProvider
    public void setEnforceTileSystemBounds(boolean z) {
        this.enforceTileSystemBounds = z;
    }

    private String computeNormalizedUserAgent(Context context) {
        if (context == null) {
            return null;
        }
        String packageName = context.getPackageName();
        try {
            return packageName + InternalZipConstants.ZIP_FILE_SEPARATOR + context.getPackageManager().getPackageInfo(packageName, 128).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return packageName;
        }
    }
}
