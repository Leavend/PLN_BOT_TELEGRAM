package org.osmdroid.tileprovider.tilesource;

/* loaded from: classes3.dex */
public interface IStyledTileSource<T> {
    T getStyle();

    void setStyle(T t);

    void setStyle(String str);
}
