package org.osmdroid.views.overlay.gestures;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

/* loaded from: classes3.dex */
public class OneFingerZoomOverlay extends Overlay {
    private boolean mIsDoubleClick = false;
    private float mLastY;

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onDoubleTap(MotionEvent motionEvent, MapView mapView) {
        return true;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onDoubleTapEvent(MotionEvent motionEvent, MapView mapView) {
        if (motionEvent.getAction() == 0) {
            this.mIsDoubleClick = true;
        } else if (motionEvent.getAction() == 1) {
            this.mIsDoubleClick = false;
        }
        return super.onDoubleTapEvent(motionEvent, mapView);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
        if (this.mIsDoubleClick) {
            VelocityTracker velocityTrackerObtain = VelocityTracker.obtain();
            velocityTrackerObtain.addMovement(motionEvent);
            velocityTrackerObtain.computeCurrentVelocity(100, 1000.0f);
            float fAbs = Math.abs(velocityTrackerObtain.getYVelocity()) / 1000.0f;
            if (this.mLastY > motionEvent.getY()) {
                mapView.getController().setZoom(mapView.getZoomLevelDouble() - fAbs);
            } else {
                mapView.getController().setZoom(mapView.getZoomLevelDouble() + fAbs);
            }
            this.mLastY = motionEvent.getY();
            velocityTrackerObtain.recycle();
        }
        return super.onTouchEvent(motionEvent, mapView);
    }
}
