package org.osmdroid.views.overlay.milestones;

import android.graphics.Canvas;
import java.util.Iterator;
import org.osmdroid.util.PointAccepter;

/* loaded from: classes3.dex */
public class MilestoneManager implements PointAccepter {
    private final MilestoneDisplayer mDisplayer;
    private final MilestoneLister mLister;

    public MilestoneManager(MilestoneLister milestoneLister, MilestoneDisplayer milestoneDisplayer) {
        this.mLister = milestoneLister;
        this.mDisplayer = milestoneDisplayer;
    }

    public void draw(Canvas canvas) {
        this.mDisplayer.drawBegin(canvas);
        Iterator<MilestoneStep> it = this.mLister.getMilestones().iterator();
        while (it.hasNext()) {
            this.mDisplayer.draw(canvas, it.next());
        }
        this.mDisplayer.drawEnd(canvas);
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mLister.init();
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        this.mLister.add(j, j2);
    }

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
        this.mLister.end();
    }

    public void setDistances(double[] dArr) {
        this.mLister.setDistances(dArr);
    }
}
