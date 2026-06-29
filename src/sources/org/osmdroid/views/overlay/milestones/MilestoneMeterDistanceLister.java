package org.osmdroid.views.overlay.milestones;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.osmdroid.util.Distance;

/* loaded from: classes3.dex */
public class MilestoneMeterDistanceLister extends MilestoneLister {
    private double mDistance;
    private int mIndex;
    private final double[] mMilestoneMeters;
    private int mMilestoneMetersIndex;
    private final double mNbMetersRecurrence;
    private double mNeededForNext;
    private double mSideEffectLastEpsilon;
    private boolean mSideEffectLastFlag;
    private double mSideEffectLastOrientation;
    private long mSideEffectLastX;
    private long mSideEffectLastY;

    public MilestoneMeterDistanceLister(double d) {
        this.mSideEffectLastEpsilon = 1.0E-5d;
        this.mNbMetersRecurrence = d;
        this.mMilestoneMeters = null;
    }

    public MilestoneMeterDistanceLister(double[] dArr) {
        this.mSideEffectLastEpsilon = 1.0E-5d;
        this.mNbMetersRecurrence = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mMilestoneMeters = dArr;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void init() {
        super.init();
        this.mDistance = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mIndex = 0;
        if (this.mMilestoneMeters != null) {
            this.mMilestoneMetersIndex = 0;
        }
        this.mNeededForNext = getNewNeededForNext();
        this.mSideEffectLastFlag = false;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister
    protected void add(long j, long j2, long j3, long j4) {
        long j5 = j3;
        this.mSideEffectLastFlag = false;
        if (this.mNeededForNext == -1.0d) {
            return;
        }
        int i = this.mIndex + 1;
        this.mIndex = i;
        double distance = getDistance(i);
        if (distance == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return;
        }
        double d = j;
        double d2 = j2;
        double dSqrt = Math.sqrt(Distance.getSquaredDistanceToPoint(d, d2, j5, j4)) / distance;
        double orientation = getOrientation(j, j2, j3, j4);
        while (true) {
            double d3 = this.mNeededForNext;
            if (distance < d3) {
                this.mDistance += distance;
                this.mNeededForNext = d3 - distance;
                this.mSideEffectLastFlag = true;
                this.mSideEffectLastX = j5;
                this.mSideEffectLastY = j4;
                this.mSideEffectLastOrientation = orientation;
                return;
            }
            this.mDistance += d3;
            double d4 = distance - d3;
            double d5 = 0.017453292519943295d * orientation;
            double dCos = d + (d3 * Math.cos(d5) * dSqrt);
            double dSin = d2 + (this.mNeededForNext * Math.sin(d5) * dSqrt);
            d2 = dSin;
            add((long) dCos, (long) dSin, orientation);
            double newNeededForNext = getNewNeededForNext();
            this.mNeededForNext = newNeededForNext;
            if (newNeededForNext == -1.0d) {
                return;
            }
            distance = d4;
            d = dCos;
            j5 = j3;
        }
    }

    private double getNewNeededForNext() {
        double[] dArr = this.mMilestoneMeters;
        if (dArr == null) {
            return this.mNbMetersRecurrence;
        }
        int i = this.mMilestoneMetersIndex;
        if (i >= dArr.length) {
            return -1.0d;
        }
        double d = i == 0 ? 0.0d : dArr[i - 1];
        this.mMilestoneMetersIndex = i + 1;
        double d2 = dArr[i] - d;
        if (d2 >= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return d2;
        }
        throw new IllegalArgumentException();
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneLister, org.osmdroid.util.PointAccepter
    public void end() {
        if (this.mSideEffectLastFlag && this.mNeededForNext < this.mSideEffectLastEpsilon) {
            add(this.mSideEffectLastX, this.mSideEffectLastY, this.mSideEffectLastOrientation);
        }
        super.end();
    }

    public void setSideEffectLastEpsilon(double d) {
        this.mSideEffectLastEpsilon = d;
    }

    private void add(long j, long j2, double d) {
        add(new MilestoneStep(j, j2, d, Double.valueOf(this.mDistance)));
    }
}
