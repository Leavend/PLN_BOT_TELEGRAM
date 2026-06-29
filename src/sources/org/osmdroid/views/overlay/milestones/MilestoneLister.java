package org.osmdroid.views.overlay.milestones;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.List;
import org.osmdroid.util.PointAccepter;
import org.osmdroid.util.PointL;

/* loaded from: classes3.dex */
public abstract class MilestoneLister implements PointAccepter {
    private double[] mDistances;
    private boolean mFirst;
    private final List<MilestoneStep> mMilestones = new ArrayList();
    private final PointL mLatestPoint = new PointL();

    protected abstract void add(long j, long j2, long j3, long j4);

    @Override // org.osmdroid.util.PointAccepter
    public void end() {
    }

    public List<MilestoneStep> getMilestones() {
        return this.mMilestones;
    }

    public void setDistances(double[] dArr) {
        this.mDistances = dArr;
    }

    protected double getDistance(int i) {
        return this.mDistances[i];
    }

    @Override // org.osmdroid.util.PointAccepter
    public void init() {
        this.mMilestones.clear();
        this.mFirst = true;
    }

    @Override // org.osmdroid.util.PointAccepter
    public void add(long j, long j2) {
        if (this.mFirst) {
            this.mFirst = false;
            this.mLatestPoint.set(j, j2);
        } else {
            add(this.mLatestPoint.x, this.mLatestPoint.y, j, j2);
            this.mLatestPoint.set(j, j2);
        }
    }

    protected void add(MilestoneStep milestoneStep) {
        this.mMilestones.add(milestoneStep);
    }

    public static double getOrientation(long j, long j2, long j3, long j4) {
        if (j == j3) {
            return j2 == j4 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : j2 > j4 ? -90.0d : 90.0d;
        }
        return (Math.atan((j4 - j2) / (j3 - j)) * 57.29577951308232d) + ((j3 > j ? 1 : (j3 == j ? 0 : -1)) < 0 ? 180 : 0);
    }
}
