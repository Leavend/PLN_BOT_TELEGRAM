package org.osmdroid.util;

/* loaded from: classes3.dex */
public class PointL {
    public long x;
    public long y;

    public PointL() {
    }

    public PointL(long j, long j2) {
        this.x = j;
        this.y = j2;
    }

    public PointL(PointL pointL) {
        set(pointL);
    }

    public void set(PointL pointL) {
        this.x = pointL.x;
        this.y = pointL.y;
    }

    public void set(long j, long j2) {
        this.x = j;
        this.y = j2;
    }

    public final void offset(long j, long j2) {
        this.x += j;
        this.y += j2;
    }

    public String toString() {
        return "PointL(" + this.x + ", " + this.y + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointL)) {
            return false;
        }
        PointL pointL = (PointL) obj;
        return this.x == pointL.x && this.y == pointL.y;
    }
}
