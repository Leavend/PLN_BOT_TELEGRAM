package org.osmdroid.views.overlay.milestones;

/* loaded from: classes3.dex */
public class MilestoneStep {
    private final Object mObject;
    private final double mOrientation;
    private final long mX;
    private final long mY;

    public MilestoneStep(long j, long j2, double d, Object obj) {
        this.mX = j;
        this.mY = j2;
        this.mOrientation = d;
        this.mObject = obj;
    }

    public MilestoneStep(long j, long j2, double d) {
        this(j, j2, d, null);
    }

    public long getX() {
        return this.mX;
    }

    public long getY() {
        return this.mY;
    }

    public double getOrientation() {
        return this.mOrientation;
    }

    public Object getObject() {
        return this.mObject;
    }

    public String toString() {
        return getClass().getSimpleName() + ":" + this.mX + "," + this.mY + "," + this.mOrientation + "," + this.mObject;
    }
}
