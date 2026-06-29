package org.osmdroid.tileprovider.tilesource;

/* loaded from: classes3.dex */
public class FileBasedTileSource extends XYTileSource {
    public FileBasedTileSource(String str, int i, int i2, int i3, String str2, String[] strArr) {
        super(str, i, i2, i3, str2, strArr);
    }

    public static ITileSource getSource(String str) {
        if (str.contains(".")) {
            str = str.substring(0, str.indexOf("."));
        }
        return new FileBasedTileSource(str, 0, 18, 256, ".png", new String[]{"http://localhost"});
    }
}
