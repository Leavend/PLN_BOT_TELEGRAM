package org.osmdroid.util;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ListPointAccepter implements PointAccepter {
    private boolean mFirst;
    private final boolean mRemoveConsecutiveDuplicates;
    private final List<Long> mList = new ArrayList();
    private final PointL mLatestPoint = new PointL();

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
    }

    public ListPointAccepter(boolean z) {
        this.mRemoveConsecutiveDuplicates = z;
    }

    public List<Long> getList() {
        return this.mList;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mList.clear();
        this.mFirst = true;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        if (!this.mRemoveConsecutiveDuplicates) {
            this.mList.add(Long.valueOf(j));
            this.mList.add(Long.valueOf(j2));
            return;
        }
        if (this.mFirst) {
            this.mFirst = false;
            this.mList.add(Long.valueOf(j));
            this.mList.add(Long.valueOf(j2));
            this.mLatestPoint.set(j, j2);
            return;
        }
        if (this.mLatestPoint.x == j && this.mLatestPoint.y == j2) {
            return;
        }
        this.mList.add(Long.valueOf(j));
        this.mList.add(Long.valueOf(j2));
        this.mLatestPoint.set(j, j2);
    }
}
