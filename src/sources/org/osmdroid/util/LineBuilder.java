package org.osmdroid.util;

/* loaded from: classes3.dex */
public abstract class LineBuilder implements PointAccepter {
    private int mIndex;
    private final float[] mLines;

    public abstract void flush();

    public LineBuilder(int i) {
        this.mLines = new float[i];
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mIndex = 0;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        float[] fArr = this.mLines;
        int i = this.mIndex;
        int i2 = i + 1;
        fArr[i] = j;
        int i3 = i2 + 1;
        this.mIndex = i3;
        fArr[i2] = j2;
        if (i3 >= fArr.length) {
            innerFlush();
        }
    }

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
        innerFlush();
    }

    public float[] getLines() {
        return this.mLines;
    }

    public int getSize() {
        return this.mIndex;
    }

    private void innerFlush() {
        if (this.mIndex > 0) {
            flush();
        }
        this.mIndex = 0;
    }
}
