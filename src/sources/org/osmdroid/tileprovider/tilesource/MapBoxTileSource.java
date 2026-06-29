package org.osmdroid.tileprovider.tilesource;

import android.content.Context;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.tileprovider.util.ManifestUtil;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class MapBoxTileSource extends OnlineTileSourceBase {
    private static final String ACCESS_TOKEN = "MAPBOX_ACCESS_TOKEN";
    private static final String MAPBOX_MAPID = "MAPBOX_MAPID";
    private static final String[] mapBoxBaseUrl = {"https://api.mapbox.com/v4/"};
    private String accessToken;
    private String highDPI;
    private String mapBoxMapId;

    public MapBoxTileSource() {
        super("mapbox", 1, 19, 256, ".png", mapBoxBaseUrl);
        this.mapBoxMapId = "";
        this.highDPI = "";
    }

    public MapBoxTileSource(Context context) {
        super("mapbox", 1, 19, 256, ".png", mapBoxBaseUrl);
        this.mapBoxMapId = "";
        this.highDPI = "";
        retrieveAccessToken(context);
        retrieveMapBoxMapId(context);
        this.mName = "mapbox" + this.mapBoxMapId;
    }

    public MapBoxTileSource(String str, String str2) {
        super("mapbox", 1, 19, 256, ".png", mapBoxBaseUrl);
        this.highDPI = "";
        this.accessToken = str2;
        this.mapBoxMapId = str;
        this.mName = "mapbox" + this.mapBoxMapId;
    }

    public MapBoxTileSource(String str, int i, int i2, int i3, String str2) {
        super(str, i, i2, i3, str2, mapBoxBaseUrl);
        this.mapBoxMapId = "";
        this.highDPI = "";
    }

    public MapBoxTileSource(String str, int i, int i2, int i3, String str2, String str3, String str4) {
        super(str, i, i2, i3, str2, new String[]{str4});
        this.mapBoxMapId = "";
        this.highDPI = "";
    }

    public final void retrieveMapBoxMapId(Context context) {
        this.mapBoxMapId = ManifestUtil.retrieveKey(context, MAPBOX_MAPID);
    }

    public final void retrieveAccessToken(Context context) {
        this.accessToken = ManifestUtil.retrieveKey(context, ACCESS_TOKEN);
    }

    public void setMapboxMapid(String str) {
        this.mapBoxMapId = str;
        this.mName = "mapbox" + this.mapBoxMapId;
    }

    public String getMapBoxMapId() {
        return this.mapBoxMapId;
    }

    @Override // org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
    public String getTileURLString(long j) {
        StringBuilder sb = new StringBuilder(getBaseUrl());
        sb.append(getMapBoxMapId());
        sb.append(InternalZipConstants.ZIP_FILE_SEPARATOR);
        sb.append(MapTileIndex.getZoom(j));
        sb.append(InternalZipConstants.ZIP_FILE_SEPARATOR);
        sb.append(MapTileIndex.getX(j));
        sb.append(InternalZipConstants.ZIP_FILE_SEPARATOR);
        sb.append(MapTileIndex.getY(j));
        sb.append(this.highDPI);
        sb.append(this.mImageFilenameEnding);
        sb.append("?access_token=").append(getAccessToken());
        return sb.toString();
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void enableHighDPI(boolean z) {
        if (z) {
            this.highDPI = "@2x";
        } else {
            this.highDPI = "";
        }
    }
}
