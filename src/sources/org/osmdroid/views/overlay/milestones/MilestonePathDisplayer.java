package org.osmdroid.views.overlay.milestones;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/* loaded from: classes3.dex */
public class MilestonePathDisplayer extends MilestoneDisplayer {
    private final Paint mPaint;
    private final Path mPath;

    public MilestonePathDisplayer(double d, boolean z, Path path, Paint paint) {
        super(d, z);
        this.mPath = path;
        this.mPaint = paint;
    }

    @Override // org.osmdroid.views.overlay.milestones.MilestoneDisplayer
    protected void draw(Canvas canvas, Object obj) {
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
