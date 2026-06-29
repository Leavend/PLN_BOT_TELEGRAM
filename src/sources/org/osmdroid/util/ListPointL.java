package org.osmdroid.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class ListPointL implements Iterable<PointL> {
    private final List<PointL> mList = new ArrayList();
    private int mSize;

    public void clear() {
        this.mSize = 0;
    }

    public int size() {
        return this.mSize;
    }

    public PointL get(int i) {
        return this.mList.get(i);
    }

    public void add(long j, long j2) {
        PointL pointL;
        if (this.mSize >= this.mList.size()) {
            pointL = new PointL();
            this.mList.add(pointL);
        } else {
            pointL = this.mList.get(this.mSize);
        }
        this.mSize++;
        pointL.set(j, j2);
    }

    @Override // java.lang.Iterable
    public Iterator<PointL> iterator() {
        return new Iterator<PointL>() { // from class: org.osmdroid.util.ListPointL.1
            private int mIndex;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.mIndex < ListPointL.this.mSize;
            }

            @Override // java.util.Iterator
            public PointL next() {
                ListPointL listPointL = ListPointL.this;
                int i = this.mIndex;
                this.mIndex = i + 1;
                return listPointL.get(i);
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
