package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.Log;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;

/* loaded from: classes3.dex */
public abstract class NonAcceleratedOverlay extends Overlay {
    private Bitmap mBackingBitmap;
    private Canvas mBackingCanvas;
    private final Matrix mBackingMatrix;
    private final Matrix mCanvasIdentityMatrix;

    public boolean isUsingBackingBitmap() {
        return true;
    }

    protected abstract void onDraw(Canvas canvas, MapView mapView, boolean z);

    @Deprecated
    public NonAcceleratedOverlay(Context context) {
        super(context);
        this.mBackingMatrix = new Matrix();
        this.mCanvasIdentityMatrix = new Matrix();
    }

    public NonAcceleratedOverlay() {
        this.mBackingMatrix = new Matrix();
        this.mCanvasIdentityMatrix = new Matrix();
    }

    protected void onDraw(Canvas canvas, Canvas canvas2, MapView mapView, boolean z) {
        onDraw(canvas, mapView, z);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        this.mBackingBitmap = null;
        this.mBackingCanvas = null;
        super.onDetach(mapView);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public final void draw(Canvas canvas, MapView mapView, boolean z) {
        if (isUsingBackingBitmap() && canvas.isHardwareAccelerated()) {
            if (z || canvas.getWidth() == 0 || canvas.getHeight() == 0) {
                return;
            }
            Bitmap bitmap = this.mBackingBitmap;
            if (bitmap == null || bitmap.getWidth() != canvas.getWidth() || this.mBackingBitmap.getHeight() != canvas.getHeight()) {
                this.mBackingBitmap = null;
                this.mBackingCanvas = null;
                try {
                    this.mBackingBitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                    this.mBackingCanvas = new Canvas(this.mBackingBitmap);
                } catch (OutOfMemoryError unused) {
                    Log.e(IMapView.LOGTAG, "OutOfMemoryError creating backing bitmap in NonAcceleratedOverlay.");
                    System.gc();
                    return;
                }
            }
            this.mBackingCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            canvas.getMatrix(this.mBackingMatrix);
            this.mBackingCanvas.setMatrix(this.mBackingMatrix);
            onDraw(this.mBackingCanvas, canvas, mapView, z);
            canvas.save();
            canvas.getMatrix(this.mCanvasIdentityMatrix);
            Matrix matrix = this.mCanvasIdentityMatrix;
            matrix.invert(matrix);
            canvas.concat(this.mCanvasIdentityMatrix);
            canvas.drawBitmap(this.mBackingBitmap, 0.0f, 0.0f, (Paint) null);
            canvas.restore();
            return;
        }
        onDraw(canvas, canvas, mapView, z);
    }
}
