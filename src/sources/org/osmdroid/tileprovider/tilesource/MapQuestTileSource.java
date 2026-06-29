package org.osmdroid.tileprovider.tilesource;

import android.content.Context;
import android.content.pm.PackageManager;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.tileprovider.util.ManifestUtil;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class MapQuestTileSource extends OnlineTileSourceBase {
    private static final String ACCESS_TOKEN = "MAPQUEST_ACCESS_TOKEN";
    private static final String MAPBOX_MAPID = "MAPQUEST_MAPID";
    private static final String[] mapBoxBaseUrl = {"http://api.tiles.mapbox.com/v4/"};
    private String accessToken;
    private String mapBoxMapId;

    public MapQuestTileSource(Context context) throws PackageManager.NameNotFoundException {
        super("MapQuest", 1, 19, 256, ".png", mapBoxBaseUrl, "MapQuest");
        this.mapBoxMapId = "mapquest.streets-mb";
        retrieveAccessToken(context);
        retrieveMapBoxMapId(context);
        this.mName = "MapQuest" + this.mapBoxMapId;
    }

    public MapQuestTileSource(String str, String str2) {
        super("MapQuest" + str, 1, 19, 256, ".png", mapBoxBaseUrl, "MapQuest");
        this.accessToken = str2;
        this.mapBoxMapId = str;
    }

    public MapQuestTileSource(String str, int i, int i2, int i3, String str2) {
        super(str, i, i2, i3, str2, mapBoxBaseUrl, "MapQuest");
        this.mapBoxMapId = "mapquest.streets-mb";
    }

    public MapQuestTileSource(String str, int i, int i2, int i3, String str2, String str3, String str4) {
        super(str + str3, i, i2, i3, str2, new String[]{str4}, "MapQuest");
        this.mapBoxMapId = str3;
    }

    public final void retrieveMapBoxMapId(Context context) throws PackageManager.NameNotFoundException {
        String strRetrieveKey = ManifestUtil.retrieveKey(context, MAPBOX_MAPID);
        if (strRetrieveKey == null || strRetrieveKey.length() <= 0) {
            return;
        }
        this.mapBoxMapId = strRetrieveKey;
    }

    public final void retrieveAccessToken(Context context) {
        this.accessToken = ManifestUtil.retrieveKey(context, ACCESS_TOKEN);
    }

    public void setMapboxMapid(String str) {
        this.mapBoxMapId = str;
    }

    public String getMapBoxMapId() {
        return this.mapBoxMapId;
    }

    @Override // org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
    public String getTileURLString(long j) {
        return getBaseUrl() + getMapBoxMapId() + InternalZipConstants.ZIP_FILE_SEPARATOR + MapTileIndex.getZoom(j) + InternalZipConstants.ZIP_FILE_SEPARATOR + MapTileIndex.getX(j) + InternalZipConstants.ZIP_FILE_SEPARATOR + MapTileIndex.getY(j) + ".png?access_token=" + getAccessToken();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }
}
