package org.osmdroid.views.overlay.milestones;

import android.graphics.Canvas;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes3.dex */
public abstract class MilestoneDisplayer {
    private final boolean mFollowTrajectory;
    private final double mInitialOrientation;

    protected abstract void draw(Canvas canvas, Object obj);

    public void drawBegin(Canvas canvas) {
    }

    public void drawEnd(Canvas canvas) {
    }

    public MilestoneDisplayer(double d, boolean z) {
        this.mInitialOrientation = d;
        this.mFollowTrajectory = z;
    }

    public void draw(Canvas canvas, MilestoneStep milestoneStep) {
        double d = this.mInitialOrientation;
        double orientation = this.mFollowTrajectory ? milestoneStep.getOrientation() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        canvas.save();
        canvas.rotate((float) (d + orientation), milestoneStep.getX(), milestoneStep.getY());
        canvas.translate(milestoneStep.getX(), milestoneStep.getY());
        draw(canvas, milestoneStep.getObject());
        canvas.restore();
    }
}
