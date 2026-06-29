package org.osmdroid.views.overlay.compass;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import org.osmdroid.library.R;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.IOverlayMenuProvider;
import org.osmdroid.views.overlay.Overlay;

/* loaded from: classes3.dex */
public class CompassOverlay extends Overlay implements IOverlayMenuProvider, IOrientationConsumer {
    public static final int MENU_COMPASS = getSafeMenuId();
    private float mAzimuth;
    private float mAzimuthOffset;
    private float mAzimuthPrecision;
    private float mCompassCenterX;
    private float mCompassCenterY;
    protected Bitmap mCompassFrameBitmap;
    protected final float mCompassFrameCenterX;
    protected final float mCompassFrameCenterY;
    private final Matrix mCompassMatrix;
    private final float mCompassRadius;
    protected Bitmap mCompassRoseBitmap;
    protected final float mCompassRoseCenterX;
    protected final float mCompassRoseCenterY;
    private final Display mDisplay;
    private boolean mInCenter;
    private boolean mIsCompassEnabled;
    protected long mLastRender;
    private int mLastRenderLag;
    protected MapView mMapView;
    private int mMode;
    private boolean mOptionsMenuEnabled;
    public IOrientationProvider mOrientationProvider;
    protected final float mScale;
    private Paint sSmoothPaint;
    private boolean wasEnabledOnPause;

    public CompassOverlay(Context context, MapView mapView) {
        this(context, new InternalCompassOrientationProvider(context), mapView);
    }

    public CompassOverlay(Context context, IOrientationProvider iOrientationProvider, MapView mapView) throws RuntimeException {
        this.sSmoothPaint = new Paint(2);
        this.mCompassMatrix = new Matrix();
        this.wasEnabledOnPause = false;
        this.mMode = 1;
        this.mAzimuth = Float.NaN;
        this.mAzimuthOffset = 0.0f;
        this.mInCenter = false;
        this.mCompassCenterX = 35.0f;
        this.mCompassCenterY = 35.0f;
        this.mCompassRadius = 20.0f;
        this.mLastRender = 0L;
        this.mOptionsMenuEnabled = true;
        this.mLastRenderLag = 500;
        this.mAzimuthPrecision = 0.0f;
        this.mScale = context.getResources().getDisplayMetrics().density;
        this.mMapView = mapView;
        this.mDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        createCompassFramePicture();
        if (this.mMode > 0) {
            createCompassRosePicture();
        } else {
            createPointerPicture();
        }
        this.mCompassFrameCenterX = (this.mCompassFrameBitmap.getWidth() / 2.0f) - 0.5f;
        this.mCompassFrameCenterY = (this.mCompassFrameBitmap.getHeight() / 2.0f) - 0.5f;
        this.mCompassRoseCenterX = (this.mCompassRoseBitmap.getWidth() / 2.0f) - 0.5f;
        this.mCompassRoseCenterY = (this.mCompassRoseBitmap.getHeight() / 2.0f) - 0.5f;
        setOrientationProvider(iOrientationProvider);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onPause() {
        this.wasEnabledOnPause = this.mIsCompassEnabled;
        IOrientationProvider iOrientationProvider = this.mOrientationProvider;
        if (iOrientationProvider != null) {
            iOrientationProvider.stopOrientationProvider();
        }
        super.onPause();
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onResume() {
        super.onResume();
        if (this.wasEnabledOnPause) {
            enableCompass();
        }
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        this.mMapView = null;
        this.sSmoothPaint = null;
        disableCompass();
        this.mOrientationProvider = null;
        this.mCompassFrameBitmap.recycle();
        this.mCompassRoseBitmap.recycle();
        super.onDetach(mapView);
    }

    public void setLastRenderLag(int i) {
        this.mLastRenderLag = i;
    }

    public void setAzimuthPrecision(float f) {
        this.mAzimuthPrecision = f;
    }

    private void invalidateCompass() {
        int iCeil;
        int iCeil2;
        int iCeil3;
        int iCeil4;
        if (this.mLastRender + this.mLastRenderLag > System.currentTimeMillis()) {
            return;
        }
        this.mLastRender = System.currentTimeMillis();
        Rect screenRect = this.mMapView.getProjection().getScreenRect();
        if (this.mInCenter) {
            iCeil = screenRect.left + ((int) Math.ceil(screenRect.exactCenterX() - this.mCompassFrameCenterX));
            iCeil2 = screenRect.top + ((int) Math.ceil(screenRect.exactCenterY() - this.mCompassFrameCenterY));
            iCeil3 = screenRect.left + ((int) Math.ceil(screenRect.exactCenterX() + this.mCompassFrameCenterX));
            iCeil4 = screenRect.top + ((int) Math.ceil(screenRect.exactCenterY() + this.mCompassFrameCenterY));
        } else {
            iCeil = screenRect.left + ((int) Math.ceil((this.mCompassCenterX * this.mScale) - this.mCompassFrameCenterX));
            iCeil2 = screenRect.top + ((int) Math.ceil((this.mCompassCenterY * this.mScale) - this.mCompassFrameCenterY));
            iCeil3 = screenRect.left + ((int) Math.ceil((this.mCompassCenterX * this.mScale) + this.mCompassFrameCenterX));
            iCeil4 = ((int) Math.ceil((this.mCompassCenterY * this.mScale) + this.mCompassFrameCenterY)) + screenRect.top;
        }
        this.mMapView.postInvalidateMapCoordinates(iCeil - 2, iCeil2 - 2, iCeil3 + 2, iCeil4 + 2);
    }

    public void setCompassCenter(float f, float f2) {
        this.mCompassCenterX = f;
        this.mCompassCenterY = f2;
    }

    public void setCompassInCenter(boolean z) {
        this.mInCenter = z;
    }

    public boolean isCompassInCenter() {
        return this.mInCenter;
    }

    public void setAzimuthOffset(float f) {
        this.mAzimuthOffset = f;
    }

    public float getAzimuthOffset() {
        return this.mAzimuthOffset;
    }

    public IOrientationProvider getOrientationProvider() {
        return this.mOrientationProvider;
    }

    public void setOrientationProvider(IOrientationProvider iOrientationProvider) throws RuntimeException {
        if (iOrientationProvider == null) {
            throw new RuntimeException("You must pass an IOrientationProvider to setOrientationProvider()");
        }
        if (isCompassEnabled()) {
            this.mOrientationProvider.stopOrientationProvider();
        }
        this.mOrientationProvider = iOrientationProvider;
    }

    protected void drawCompass(Canvas canvas, float f, Rect rect) {
        float fExactCenterX;
        float fExactCenterY;
        Projection projection = this.mMapView.getProjection();
        if (this.mInCenter) {
            Rect screenRect = projection.getScreenRect();
            fExactCenterX = screenRect.exactCenterX();
            fExactCenterY = screenRect.exactCenterY();
        } else {
            float f2 = this.mCompassCenterX;
            float f3 = this.mScale;
            float f4 = f2 * f3;
            float f5 = f3 * this.mCompassCenterY;
            fExactCenterX = f4;
            fExactCenterY = f5;
        }
        this.mCompassMatrix.setTranslate(-this.mCompassFrameCenterX, -this.mCompassFrameCenterY);
        this.mCompassMatrix.postTranslate(fExactCenterX, fExactCenterY);
        projection.save(canvas, false, true);
        canvas.concat(this.mCompassMatrix);
        canvas.drawBitmap(this.mCompassFrameBitmap, 0.0f, 0.0f, this.sSmoothPaint);
        projection.restore(canvas, true);
        this.mCompassMatrix.setRotate(-f, this.mCompassRoseCenterX, this.mCompassRoseCenterY);
        this.mCompassMatrix.postTranslate(-this.mCompassRoseCenterX, -this.mCompassRoseCenterY);
        this.mCompassMatrix.postTranslate(fExactCenterX, fExactCenterY);
        projection.save(canvas, false, true);
        canvas.concat(this.mCompassMatrix);
        canvas.drawBitmap(this.mCompassRoseBitmap, 0.0f, 0.0f, this.sSmoothPaint);
        projection.restore(canvas, true);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        if (!isCompassEnabled() || Float.isNaN(this.mAzimuth)) {
            return;
        }
        drawCompass(canvas, this.mMode * (this.mAzimuth + this.mAzimuthOffset + getDisplayOrientation()), projection.getScreenRect());
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public void setOptionsMenuEnabled(boolean z) {
        this.mOptionsMenuEnabled = z;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean isOptionsMenuEnabled() {
        return this.mOptionsMenuEnabled;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onCreateOptionsMenu(Menu menu, int i, MapView mapView) {
        menu.add(0, MENU_COMPASS + i, 0, mapView.getContext().getResources().getString(R.string.compass)).setIcon(mapView.getContext().getResources().getDrawable(R.drawable.ic_menu_compass)).setCheckable(true);
        return true;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onPrepareOptionsMenu(Menu menu, int i, MapView mapView) {
        menu.findItem(MENU_COMPASS + i).setChecked(isCompassEnabled());
        return false;
    }

    @Override // org.osmdroid.views.overlay.IOverlayMenuProvider
    public boolean onOptionsItemSelected(MenuItem menuItem, int i, MapView mapView) {
        if (menuItem.getItemId() - i != MENU_COMPASS) {
            return false;
        }
        if (isCompassEnabled()) {
            disableCompass();
            return true;
        }
        enableCompass();
        return true;
    }

    @Override // org.osmdroid.views.overlay.compass.IOrientationConsumer
    public void onOrientationChanged(float f, IOrientationProvider iOrientationProvider) {
        if (Float.isNaN(this.mAzimuth) || Math.abs(this.mAzimuth - f) >= this.mAzimuthPrecision) {
            this.mAzimuth = f;
            invalidateCompass();
        }
    }

    public boolean enableCompass(IOrientationProvider iOrientationProvider) throws RuntimeException {
        setOrientationProvider(iOrientationProvider);
        boolean zStartOrientationProvider = this.mOrientationProvider.startOrientationProvider(this);
        this.mIsCompassEnabled = zStartOrientationProvider;
        if (this.mMapView != null) {
            invalidateCompass();
        }
        return zStartOrientationProvider;
    }

    public boolean enableCompass() {
        return enableCompass(this.mOrientationProvider);
    }

    public void disableCompass() {
        this.mIsCompassEnabled = false;
        IOrientationProvider iOrientationProvider = this.mOrientationProvider;
        if (iOrientationProvider != null) {
            iOrientationProvider.stopOrientationProvider();
        }
        this.mAzimuth = Float.NaN;
        if (this.mMapView != null) {
            invalidateCompass();
        }
    }

    public boolean isCompassEnabled() {
        return this.mIsCompassEnabled;
    }

    public float getOrientation() {
        return this.mAzimuth;
    }

    public void setPointerMode(boolean z) {
        if (z) {
            this.mMode = -1;
            createPointerPicture();
        } else {
            this.mMode = 1;
            createCompassRosePicture();
        }
    }

    public boolean isPointerMode() {
        return this.mMode < 0;
    }

    private Point calculatePointOnCircle(float f, float f2, float f3, float f4) {
        double radians = Math.toRadians((-f4) + 90.0f);
        double d = f3;
        return new Point(((int) f) + ((int) (Math.cos(radians) * d)), ((int) f2) - ((int) (d * Math.sin(radians))));
    }

    private void drawTriangle(Canvas canvas, float f, float f2, float f3, float f4, Paint paint) {
        canvas.save();
        Point pointCalculatePointOnCircle = calculatePointOnCircle(f, f2, f3, f4);
        canvas.rotate(f4, pointCalculatePointOnCircle.x, pointCalculatePointOnCircle.y);
        Path path = new Path();
        path.moveTo(pointCalculatePointOnCircle.x - (this.mScale * 2.0f), pointCalculatePointOnCircle.y);
        path.lineTo(pointCalculatePointOnCircle.x + (this.mScale * 2.0f), pointCalculatePointOnCircle.y);
        path.lineTo(pointCalculatePointOnCircle.x, pointCalculatePointOnCircle.y - (this.mScale * 5.0f));
        path.close();
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    private int getDisplayOrientation() {
        int rotation = this.mDisplay.getRotation();
        if (rotation == 1) {
            return 90;
        }
        if (rotation != 2) {
            return rotation != 3 ? 0 : 270;
        }
        return 180;
    }

    private void createCompassFramePicture() {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(200);
        Paint paint2 = new Paint();
        paint2.setColor(-7829368);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2.0f);
        paint2.setAlpha(200);
        int i = (int) (this.mScale * 50.0f);
        int i2 = i / 2;
        Bitmap bitmap = this.mCompassFrameBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mCompassFrameBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mCompassFrameBitmap);
        float f = i2;
        canvas.drawCircle(f, f, this.mScale * 20.0f, paint);
        canvas.drawCircle(f, f, this.mScale * 20.0f, paint2);
        drawTriangle(canvas, f, f, this.mScale * 20.0f, 0.0f, paint2);
        drawTriangle(canvas, f, f, this.mScale * 20.0f, 90.0f, paint2);
        drawTriangle(canvas, f, f, this.mScale * 20.0f, 180.0f, paint2);
        drawTriangle(canvas, f, f, this.mScale * 20.0f, 270.0f, paint2);
    }

    private void createCompassRosePicture() {
        Paint paint = new Paint();
        paint.setColor(-6291456);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(220);
        Paint paint2 = new Paint();
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAlpha(220);
        Paint paint3 = new Paint();
        paint3.setColor(-1);
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setAlpha(220);
        int i = (int) (this.mScale * 50.0f);
        int i2 = i / 2;
        Bitmap bitmap = this.mCompassRoseBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mCompassRoseBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mCompassRoseBitmap);
        Path path = new Path();
        float f = i2;
        path.moveTo(f, f - (this.mScale * 17.0f));
        path.lineTo((this.mScale * 4.0f) + f, f);
        path.lineTo(f - (this.mScale * 4.0f), f);
        path.lineTo(f, f - (this.mScale * 17.0f));
        path.close();
        canvas.drawPath(path, paint);
        Path path2 = new Path();
        path2.moveTo(f, (this.mScale * 17.0f) + f);
        path2.lineTo((this.mScale * 4.0f) + f, f);
        path2.lineTo(f - (this.mScale * 4.0f), f);
        path2.lineTo(f, (this.mScale * 17.0f) + f);
        path2.close();
        canvas.drawPath(path2, paint2);
        canvas.drawCircle(f, f, 2.0f, paint3);
    }

    private void createPointerPicture() {
        Paint paint = new Paint();
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(220);
        Paint paint2 = new Paint();
        paint2.setColor(-1);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAlpha(220);
        int i = (int) (this.mScale * 50.0f);
        int i2 = i / 2;
        Bitmap bitmap = this.mCompassRoseBitmap;
        if (bitmap != null) {
            bitmap.recycle();
        }
        this.mCompassRoseBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mCompassRoseBitmap);
        Path path = new Path();
        float f = i2;
        path.moveTo(f, f - (this.mScale * 17.0f));
        float f2 = this.mScale;
        path.lineTo((f2 * 4.0f) + f, (f2 * 17.0f) + f);
        path.lineTo(f, (this.mScale * 8.5f) + f);
        float f3 = this.mScale;
        path.lineTo(f - (4.0f * f3), (f3 * 17.0f) + f);
        path.lineTo(f, f - (this.mScale * 17.0f));
        path.close();
        canvas.drawPath(path, paint);
        canvas.drawCircle(f, f, 2.0f, paint2);
    }
}
