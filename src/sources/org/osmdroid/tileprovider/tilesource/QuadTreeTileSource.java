package org.osmdroid.tileprovider.tilesource;

import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class QuadTreeTileSource extends OnlineTileSourceBase {
    public QuadTreeTileSource(String str, int i, int i2, int i3, String str2, String[] strArr) {
        super(str, i, i2, i3, str2, strArr);
    }

    @Override // org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase
    public String getTileURLString(long j) {
        return getBaseUrl() + quadTree(j) + this.mImageFilenameEnding;
    }

    protected String quadTree(long j) {
        StringBuilder sb = new StringBuilder();
        for (int zoom = MapTileIndex.getZoom(j); zoom > 0; zoom--) {
            int i = 1 << (zoom - 1);
            int i2 = (MapTileIndex.getX(j) & i) == 0 ? 0 : 1;
            if ((i & MapTileIndex.getY(j)) != 0) {
                i2 += 2;
            }
            sb.append("" + i2);
        }
        return sb.toString();
    }
}
