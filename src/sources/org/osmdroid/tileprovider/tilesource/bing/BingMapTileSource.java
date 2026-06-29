package org.osmdroid.tileprovider.tilesource.bing;

import android.content.Context;
import android.util.Log;
import java.util.Locale;
import net.lingala.zip4j.util.InternalZipConstants;
import org.osmdroid.api.IMapView;
import org.osmdroid.tileprovider.tilesource.IStyledTileSource;
import org.osmdroid.tileprovider.tilesource.QuadTreeTileSource;
import org.osmdroid.tileprovider.util.ManifestUtil;

/* loaded from: classes3.dex */
public class BingMapTileSource extends QuadTreeTileSource implements IStyledTileSource<String> {
    private static final String BASE_URL_PATTERN = "https://dev.virtualearth.net/REST/V1/Imagery/Metadata/%s?mapVersion=v1&output=json&uriScheme=https&key=%s";
    private static final String BING_KEY = "BING_KEY";
    private static final String FILENAME_ENDING = ".jpeg";
    public static final String IMAGERYSET_AERIAL = "Aerial";
    public static final String IMAGERYSET_AERIALWITHLABELS = "AerialWithLabels";
    public static final String IMAGERYSET_ROAD = "Road";
    private static String mBingMapKey = "";
    private String mBaseUrl;
    private ImageryMetaDataResource mImageryData;
    private String mLocale;
    private String mStyle;
    private String mUrl;

    public BingMapTileSource(String str) {
        super("BingMaps", 0, 19, 256, FILENAME_ENDING, null);
        this.mStyle = IMAGERYSET_ROAD;
        this.mImageryData = ImageryMetaDataResource.getDefaultInstance();
        this.mLocale = str;
        if (str == null) {
            this.mLocale = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
        }
    }

    public static void retrieveBingKey(Context context) {
        mBingMapKey = ManifestUtil.retrieveKey(context, BING_KEY);
    }

    public static String getBingKey() {
        return mBingMapKey;
    }

    public static void setBingKey(String str) {
        mBingMapKey = str;
    }

    @Override // org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
    public String getBaseUrl() {
        if (!this.mImageryData.m_isInitialised) {
            initMetaData();
        }
        return this.mBaseUrl;
    }

    @Override // org.osmdroid.tileprovider.tilesource.QuadTreeTileSource, org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
    public String getTileURLString(long j) {
        if (!this.mImageryData.m_isInitialised) {
            initMetaData();
        }
        return String.format(this.mUrl, quadTree(j));
    }

    @Override // org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase, org.osmdroid.tileprovider.tilesource.ITileSource
    public int getMinimumZoomLevel() {
        return this.mImageryData.m_zoomMin;
    }

    @Override // org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase, org.osmdroid.tileprovider.tilesource.ITileSource
    public int getMaximumZoomLevel() {
        return this.mImageryData.m_zoomMax;
    }

    @Override // org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase, org.osmdroid.tileprovider.tilesource.ITileSource
    public int getTileSizePixels() {
        return this.mImageryData.m_imageHeight;
    }

    @Override // org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase
    public String pathBase() {
        return this.mName + this.mStyle;
    }

    @Override // org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase, org.osmdroid.tileprovider.tilesource.ITileSource
    public String getCopyrightNotice() {
        return this.mImageryData.copyright;
    }

    @Override // org.osmdroid.tileprovider.tilesource.IStyledTileSource
    public void setStyle(String str) {
        if (!str.equals(this.mStyle)) {
            synchronized (this.mStyle) {
                this.mUrl = null;
                this.mBaseUrl = null;
                this.mImageryData.m_isInitialised = false;
            }
        }
        this.mStyle = str;
        this.mName = pathBase();
    }

    @Override // org.osmdroid.tileprovider.tilesource.IStyledTileSource
    public String getStyle() {
        return this.mStyle;
    }

    public ImageryMetaDataResource initMetaData() {
        ImageryMetaDataResource metaData;
        if (!this.mImageryData.m_isInitialised) {
            synchronized (this) {
                if (!this.mImageryData.m_isInitialised && (metaData = getMetaData()) != null) {
                    this.mImageryData = metaData;
                    updateBaseUrl();
                }
            }
        }
        return this.mImageryData;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0186 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0190 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:119:0x019a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private org.osmdroid.tileprovider.tilesource.bing.ImageryMetaDataResource getMetaData() throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 432
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.tilesource.bing.BingMapTileSource.getMetaData():org.osmdroid.tileprovider.tilesource.bing.ImageryMetaDataResource");
    }

    protected void updateBaseUrl() {
        Log.d(IMapView.LOGTAG, "updateBaseUrl");
        String subDomain = this.mImageryData.getSubDomain();
        int iLastIndexOf = this.mImageryData.m_imageUrl.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR);
        if (iLastIndexOf > 0) {
            this.mBaseUrl = this.mImageryData.m_imageUrl.substring(0, iLastIndexOf);
        } else {
            this.mBaseUrl = this.mImageryData.m_imageUrl;
        }
        this.mUrl = this.mImageryData.m_imageUrl;
        if (subDomain != null) {
            this.mBaseUrl = String.format(this.mBaseUrl, subDomain);
            this.mUrl = String.format(this.mUrl, subDomain, "%s", this.mLocale);
        }
        Log.d(IMapView.LOGTAG, "updated url = " + this.mUrl);
        Log.d(IMapView.LOGTAG, "end updateBaseUrl");
    }
}
