package org.osmdroid.views.overlay.milestones;

import org.osmdroid.util.Distance;

/* loaded from: classes3.dex */
public class MilestoneMiddleLister extends MilestoneLister {
    private final double mMinimumSquaredPixelDistance;

    public MilestoneMiddleLister(double d) {
        this.mMinimumSquaredPixelDistance = d * d;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister
    protected void add(long j, long j2, long j3, long j4) {
        if (Distance.getSquaredDistanceToPoint(j, j2, j3, j4) <= this.mMinimumSquaredPixelDistance) {
            return;
        }
        add(new MilestoneStep((j + j3) / 2, (j2 + j4) / 2, getOrientation(j, j2, j3, j4)));
    }
}
