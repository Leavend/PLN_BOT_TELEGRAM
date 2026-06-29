package org.osmdroid.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class MapTileAreaList implements MapTileContainer, IterableWithSize<Long> {
    private final List<MapTileArea> mList = new ArrayList();

    public List<MapTileArea> getList() {
        return this.mList;
    }

    @Override // org.osmdroid.util.IterableWithSize
    public int size() {
        Iterator<MapTileArea> it = this.mList.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().size();
        }
        return size;
    }

    @Override // java.lang.Iterable
    public Iterator<Long> iterator() {
        return new Iterator<Long>() { // from class: org.osmdroid.util.MapTileAreaList.1
            private Iterator<Long> mCurrent;
            private int mIndex;

            @Override // java.util.Iterator
            public boolean hasNext() {
                Iterator<Long> current = getCurrent();
                return current != null && current.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public Long next() {
                long jLongValue = getCurrent().next().longValue();
                if (!getCurrent().hasNext()) {
                    this.mCurrent = null;
                }
                return Long.valueOf(jLongValue);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            private Iterator<Long> getCurrent() {
                Iterator<Long> it = this.mCurrent;
                if (it != null) {
                    return it;
                }
                if (this.mIndex >= MapTileAreaList.this.mList.size()) {
                    return null;
                }
                List list = MapTileAreaList.this.mList;
                int i = this.mIndex;
                this.mIndex = i + 1;
                Iterator<Long> it2 = ((MapTileArea) list.get(i)).iterator();
                this.mCurrent = it2;
                return it2;
            }
        };
    }

    @Override // org.osmdroid.util.MapTileContainer
    public boolean contains(long j) {
        Iterator<MapTileArea> it = this.mList.iterator();
        while (it.hasNext()) {
            if (it.next().contains(j)) {
                return true;
            }
        }
        return false;
    }
}
