package org.osmdroid.util;

import android.graphics.Rect;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class MapTileArea implements MapTileContainer, IterableWithSize<Long> {
    private int mHeight;
    private int mLeft;
    private int mMapTileUpperBound;
    private int mTop;
    private int mWidth;
    private int mZoom;

    public MapTileArea set(int i, int i2, int i3, int i4, int i5) {
        this.mZoom = i;
        this.mMapTileUpperBound = 1 << i;
        this.mWidth = computeSize(i2, i4);
        this.mHeight = computeSize(i3, i5);
        this.mLeft = cleanValue(i2);
        this.mTop = cleanValue(i3);
        return this;
    }

    public MapTileArea set(int i, Rect rect) {
        return set(i, rect.left, rect.top, rect.right, rect.bottom);
    }

    public MapTileArea set(MapTileArea mapTileArea) {
        if (mapTileArea.size() == 0) {
            return reset();
        }
        return set(mapTileArea.mZoom, mapTileArea.mLeft, mapTileArea.mTop, mapTileArea.getRight(), mapTileArea.getBottom());
    }

    public MapTileArea reset() {
        this.mWidth = 0;
        return this;
    }

    public int getZoom() {
        return this.mZoom;
    }

    public int getLeft() {
        return this.mLeft;
    }

    public int getTop() {
        return this.mTop;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getRight() {
        return (this.mLeft + this.mWidth) % this.mMapTileUpperBound;
    }

    public int getBottom() {
        return (this.mTop + this.mHeight) % this.mMapTileUpperBound;
    }

    @Override // org.osmdroid.util.IterableWithSize
    public int size() {
        return this.mWidth * this.mHeight;
    }

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return new Iterator<Long>() { // from class: org.osmdroid.util.MapTileArea.1
            private int mIndex;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.mIndex < MapTileArea.this.size();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Long next() {
                if (!hasNext()) {
                    return null;
                }
                int i = MapTileArea.this.mLeft + (this.mIndex % MapTileArea.this.mWidth);
                int i2 = MapTileArea.this.mTop + (this.mIndex / MapTileArea.this.mWidth);
                this.mIndex++;
                while (i >= MapTileArea.this.mMapTileUpperBound) {
                    i -= MapTileArea.this.mMapTileUpperBound;
                }
                while (i2 >= MapTileArea.this.mMapTileUpperBound) {
                    i2 -= MapTileArea.this.mMapTileUpperBound;
                }
                return Long.valueOf(MapTileIndex.getTileIndex(MapTileArea.this.mZoom, i, i2));
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // org.osmdroid.util.MapTileContainer
    public boolean contains(long j) {
        if (MapTileIndex.getZoom(j) == this.mZoom && contains(MapTileIndex.getX(j), this.mLeft, this.mWidth)) {
            return contains(MapTileIndex.getY(j), this.mTop, this.mHeight);
        }
        return false;
    }

    private boolean contains(int i, int i2, int i3) {
        while (i < i2) {
            i += this.mMapTileUpperBound;
        }
        return i < i2 + i3;
    }

    private int cleanValue(int i) {
        while (i < 0) {
            i += this.mMapTileUpperBound;
        }
        while (true) {
            int i2 = this.mMapTileUpperBound;
            if (i < i2) {
                return i;
            }
            i -= i2;
        }
    }

    private int computeSize(int i, int i2) {
        while (i > i2) {
            i2 += this.mMapTileUpperBound;
        }
        return Math.min(this.mMapTileUpperBound, (i2 - i) + 1);
    }

    public String toString() {
        return this.mWidth == 0 ? "MapTileArea:empty" : "MapTileArea:zoom=" + this.mZoom + ",left=" + this.mLeft + ",top=" + this.mTop + ",width=" + this.mWidth + ",height=" + this.mHeight;
    }
}
