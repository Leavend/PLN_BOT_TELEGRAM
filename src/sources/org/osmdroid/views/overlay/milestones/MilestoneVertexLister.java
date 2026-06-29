package org.osmdroid.views.overlay.milestones;

/* loaded from: classes3.dex */
public class MilestoneVertexLister extends MilestoneLister {
    private int mIndex;
    private double mLatestOrientation;
    private long mLatestX;
    private long mLatestY;

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void init() {
        super.init();
        this.mIndex = 0;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister
    protected void add(long j, long j2, long j3, long j4) {
        this.mLatestOrientation = getOrientation(j, j2, j3, j4);
        int i = this.mIndex;
        this.mIndex = i + 1;
        innerAdd(j, j2, i);
        this.mLatestX = j3;
        this.mLatestY = j4;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void end() {
        super.end();
        innerAdd(this.mLatestX, this.mLatestY, -this.mIndex);
    }

    private void innerAdd(long j, long j2, int i) {
        add(new MilestoneStep(j, j2, this.mLatestOrientation, Integer.valueOf(i)));
    }
}
