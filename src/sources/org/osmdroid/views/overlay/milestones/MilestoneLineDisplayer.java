package org.osmdroid.views.overlay.milestones;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.osmdroid.views.overlay.LineDrawer;

/* loaded from: classes3.dex */
public class MilestoneLineDisplayer extends MilestoneDisplayer {
    private boolean mFirst;
    private final LineDrawer mLineDrawer;
    private long mPreviousX;
    private long mPreviousY;

    @Override // org.osmdroid.views.overlay.milestones.MilestoneDisplayer
    protected void draw(Canvas canvas, Object obj) {
    }

    public MilestoneLineDisplayer(Paint paint) {
        super(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, false);
        this.mFirst = true;
        LineDrawer lineDrawer = new LineDrawer(256) { // from class: org.osmdroid.views.overlay.milestones.MilestoneLineDisplayer.1
            @Override // org.osmdroid.views.overlay.LineDrawer, org.osmdroid.util.LineBuilder
            public void flush() {
                super.flush();
                MilestoneLineDisplayer.this.mFirst = true;
            }
        };
        this.mLineDrawer = lineDrawer;
        lineDrawer.setPaint(paint);
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneDisplayer
    public void drawBegin(Canvas canvas) {
        this.mLineDrawer.init();
        this.mLineDrawer.setCanvas(canvas);
        this.mFirst = true;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneDisplayer
    public void draw(Canvas canvas, MilestoneStep milestoneStep) {
        long x = milestoneStep.getX();
        long y = milestoneStep.getY();
        if (this.mFirst) {
            this.mFirst = false;
        } else {
            long j = this.mPreviousX;
            if (j != x || this.mPreviousY != y) {
                this.mLineDrawer.add(j, this.mPreviousY);
                this.mLineDrawer.add(x, y);
            }
        }
        this.mPreviousX = x;
        this.mPreviousY = y;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneDisplayer
    public void drawEnd(Canvas canvas) {
        this.mLineDrawer.end();
    }
}
