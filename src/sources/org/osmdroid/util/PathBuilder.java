package org.osmdroid.util;

import android.graphics.Path;

/* loaded from: classes3.dex */
public class PathBuilder implements PointAccepter {
    private boolean mFirst;
    private final PointL mLatestPoint = new PointL();
    private final Path mPath;

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
    }

    public PathBuilder(Path path) {
        this.mPath = path;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mFirst = true;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        if (this.mFirst) {
            this.mFirst = false;
            this.mPath.moveTo(j, j2);
            this.mLatestPoint.set(j, j2);
        } else {
            if (this.mLatestPoint.x == j && this.mLatestPoint.y == j2) {
                return;
            }
            this.mPath.lineTo(j, j2);
            this.mLatestPoint.set(j, j2);
        }
    }
}
