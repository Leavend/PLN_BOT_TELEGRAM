package org.osmdroid.views.overlay.milestones;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.osmdroid.util.Distance;

/* loaded from: classes3.dex */
public class MilestoneMeterDistanceSliceLister extends MilestoneLister {
    private double mDistance;
    private int mIndex;
    private double mNbMetersEnd;
    private double mNbMetersStart;
    private Step mStep;

    private enum Step {
        STEP_INIT,
        STEP_STARTED,
        STEP_ENDED
    }

    public void setMeterDistanceSlice(double d, double d2) {
        this.mNbMetersStart = d;
        this.mNbMetersEnd = d2;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void init() {
        super.init();
        this.mDistance = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mIndex = 0;
        this.mStep = Step.STEP_INIT;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister
    protected void add(long j, long j2, long j3, long j4) {
        double dSin;
        if (this.mStep == Step.STEP_ENDED) {
            return;
        }
        int i = this.mIndex + 1;
        this.mIndex = i;
        double distance = getDistance(i);
        if (distance == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        double dCos = j;
        double d = j2;
        double dSqrt = Math.sqrt(Distance.getSquaredDistanceToPoint(dCos, d, j3, j4)) / distance;
        double orientation = getOrientation(j, j2, j3, j4);
        if (this.mStep == Step.STEP_INIT) {
            double d2 = this.mNbMetersStart;
            double d3 = this.mDistance;
            double d4 = d2 - d3;
            if (d4 > distance) {
                this.mDistance = d3 + distance;
                return;
            }
            this.mStep = Step.STEP_STARTED;
            this.mDistance += d4;
            double d5 = distance - d4;
            double d6 = orientation * 0.017453292519943295d;
            dCos += Math.cos(d6) * d4 * dSqrt;
            dSin = d + (d4 * Math.sin(d6) * dSqrt);
            add(new MilestoneStep((long) dCos, (long) dSin, orientation, null));
            if (this.mNbMetersStart == this.mNbMetersEnd) {
                this.mStep = Step.STEP_ENDED;
                return;
            }
            distance = d5;
        } else {
            dSin = d;
        }
        if (this.mStep == Step.STEP_STARTED) {
            double d7 = this.mNbMetersEnd;
            double d8 = this.mDistance;
            double d9 = d7 - d8;
            if (d9 > distance) {
                this.mDistance = d8 + distance;
                add(new MilestoneStep(j3, j4, orientation, null));
            } else {
                this.mStep = Step.STEP_ENDED;
                double d10 = orientation * 0.017453292519943295d;
                add(new MilestoneStep((long) (dCos + (Math.cos(d10) * d9 * dSqrt)), (long) (dSin + (d9 * Math.sin(d10) * dSqrt)), orientation, null));
            }
        }
    }
}
