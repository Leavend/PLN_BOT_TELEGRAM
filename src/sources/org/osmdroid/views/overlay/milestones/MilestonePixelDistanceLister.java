package org.osmdroid.views.overlay.milestones;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.osmdroid.util.Distance;

/* loaded from: classes3.dex */
public class MilestonePixelDistanceLister extends MilestoneLister {
    private double mDistance;
    private final double mNbPixelsInit;
    private final double mNbPixelsRecurrence;

    public MilestonePixelDistanceLister(double d, double d2) {
        this.mNbPixelsInit = d;
        this.mNbPixelsRecurrence = d2;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void init() {
        super.init();
        this.mDistance = this.mNbPixelsRecurrence - this.mNbPixelsInit;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister
    protected void add(long j, long j2, long j3, long j4) {
        double dCos = j;
        double d = j2;
        double dSqrt = Math.sqrt(Distance.getSquaredDistanceToPoint(dCos, d, j3, j4));
        if (dSqrt == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        double orientation = getOrientation(j, j2, j3, j4);
        double dSin = d;
        while (true) {
            double dFloor = Math.floor(this.mDistance / this.mNbPixelsRecurrence);
            double d2 = this.mNbPixelsRecurrence;
            double d3 = (dFloor * d2) + d2;
            double d4 = this.mDistance;
            double d5 = d3 - d4;
            if (dSqrt < d5) {
                this.mDistance = d4 + dSqrt;
                return;
            }
            this.mDistance = d4 + d5;
            double d6 = 0.017453292519943295d * orientation;
            dCos += Math.cos(d6) * d5;
            dSin += d5 * Math.sin(d6);
            add(new MilestoneStep((long) dCos, (long) dSin, orientation, Double.valueOf(this.mDistance)));
            dSqrt -= d5;
        }
    }
}
