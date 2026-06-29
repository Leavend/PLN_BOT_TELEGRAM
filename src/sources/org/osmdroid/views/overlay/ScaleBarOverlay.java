package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.core.view.ViewCompat;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Locale;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.library.R;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.util.constants.GeoConstants;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public class ScaleBarOverlay extends Overlay implements GeoConstants {
    private static final Rect sTextBoundsRect = new Rect();
    private boolean adjustLength;
    protected boolean alignBottom;
    protected boolean alignRight;
    private Paint barPaint;
    protected final Path barPath;
    private Paint bgPaint;
    private boolean centred;
    private Context context;
    private double lastLatitude;
    private double lastZoomLevel;
    boolean latitudeBar;
    protected final Rect latitudeBarRect;
    boolean longitudeBar;
    protected final Rect longitudeBarRect;
    private int mMapHeight;
    private MapView mMapView;
    private int mMapWidth;
    private float maxLength;
    double minZoom;
    public int screenHeight;
    public int screenWidth;
    private Paint textPaint;
    UnitsOfMeasure unitsOfMeasure;
    int xOffset;
    public float xdpi;
    int yOffset;
    public float ydpi;

    public enum UnitsOfMeasure {
        metric,
        imperial,
        nautical
    }

    public ScaleBarOverlay(MapView mapView) {
        this(mapView, mapView.getContext(), 0, 0);
    }

    public ScaleBarOverlay(Context context, int i, int i2) {
        this(null, context, i, i2);
    }

    private ScaleBarOverlay(MapView mapView, Context context, int i, int i2) {
        this.xOffset = 10;
        this.yOffset = 10;
        this.minZoom = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.unitsOfMeasure = UnitsOfMeasure.metric;
        this.latitudeBar = true;
        this.longitudeBar = false;
        this.alignBottom = false;
        this.alignRight = false;
        this.barPath = new Path();
        this.latitudeBarRect = new Rect();
        this.longitudeBarRect = new Rect();
        this.lastZoomLevel = -1.0d;
        this.lastLatitude = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.centred = false;
        this.adjustLength = false;
        this.mMapView = mapView;
        this.context = context;
        this.mMapWidth = i;
        this.mMapHeight = i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Paint paint = new Paint();
        this.barPaint = paint;
        paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.barPaint.setAntiAlias(true);
        this.barPaint.setStyle(Paint.Style.STROKE);
        this.barPaint.setAlpha(255);
        this.barPaint.setStrokeWidth(displayMetrics.density * 2.0f);
        String str = null;
        this.bgPaint = null;
        Paint paint2 = new Paint();
        this.textPaint = paint2;
        paint2.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.textPaint.setAntiAlias(true);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setAlpha(255);
        this.textPaint.setTextSize(displayMetrics.density * 10.0f);
        this.xdpi = displayMetrics.xdpi;
        this.ydpi = displayMetrics.ydpi;
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        try {
            str = (String) Build.class.getField("MANUFACTURER").get(null);
        } catch (Exception unused) {
        }
        if ("motorola".equals(str) && "DROIDX".equals(Build.MODEL)) {
            WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
            if (windowManager != null && windowManager.getDefaultDisplay().getOrientation() > 0) {
                this.xdpi = (float) (this.screenWidth / 3.75d);
                this.ydpi = (float) (this.screenHeight / 2.1d);
            } else {
                this.xdpi = (float) (this.screenWidth / 2.1d);
                this.ydpi = (float) (this.screenHeight / 3.75d);
            }
        } else if ("motorola".equals(str) && "Droid".equals(Build.MODEL)) {
            this.xdpi = 264.0f;
            this.ydpi = 264.0f;
        }
        this.maxLength = 2.54f;
    }

    public void setMinZoom(double d) {
        this.minZoom = d;
    }

    public void setScaleBarOffset(int i, int i2) {
        this.xOffset = i;
        this.yOffset = i2;
    }

    public void setLineWidth(float f) {
        this.barPaint.setStrokeWidth(f);
    }

    public void setTextSize(float f) {
        this.textPaint.setTextSize(f);
    }

    public void setUnitsOfMeasure(UnitsOfMeasure unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
        this.lastZoomLevel = -1.0d;
    }

    public UnitsOfMeasure getUnitsOfMeasure() {
        return this.unitsOfMeasure;
    }

    public void drawLatitudeScale(boolean z) {
        this.latitudeBar = z;
        this.lastZoomLevel = -1.0d;
    }

    public void drawLongitudeScale(boolean z) {
        this.longitudeBar = z;
        this.lastZoomLevel = -1.0d;
    }

    public void setCentred(boolean z) {
        this.centred = z;
        this.alignBottom = !z;
        this.alignRight = !z;
        this.lastZoomLevel = -1.0d;
    }

    public void setAlignBottom(boolean z) {
        this.centred = false;
        this.alignBottom = z;
        this.lastZoomLevel = -1.0d;
    }

    public void setAlignRight(boolean z) {
        this.centred = false;
        this.alignRight = z;
        this.lastZoomLevel = -1.0d;
    }

    public Paint getBarPaint() {
        return this.barPaint;
    }

    public void setBarPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("pBarPaint argument cannot be null");
        }
        this.barPaint = paint;
        this.lastZoomLevel = -1.0d;
    }

    public Paint getTextPaint() {
        return this.textPaint;
    }

    public void setTextPaint(Paint paint) {
        if (paint == null) {
            throw new IllegalArgumentException("pTextPaint argument cannot be null");
        }
        this.textPaint = paint;
        this.lastZoomLevel = -1.0d;
    }

    public void setBackgroundPaint(Paint paint) {
        this.bgPaint = paint;
        this.lastZoomLevel = -1.0d;
    }

    public void setEnableAdjustLength(boolean z) {
        this.adjustLength = z;
        this.lastZoomLevel = -1.0d;
    }

    public void setMaxLength(float f) {
        this.maxLength = f;
        this.lastZoomLevel = -1.0d;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        Paint paint;
        double zoomLevel = projection.getZoomLevel();
        if (zoomLevel < this.minZoom) {
            return;
        }
        Rect intrinsicScreenRect = projection.getIntrinsicScreenRect();
        int iWidth = intrinsicScreenRect.width();
        int iHeight = intrinsicScreenRect.height();
        boolean z = (iHeight == this.screenHeight && iWidth == this.screenWidth) ? false : true;
        this.screenHeight = iHeight;
        this.screenWidth = iWidth;
        IGeoPoint iGeoPointFromPixels = projection.fromPixels(iWidth / 2, iHeight / 2, null);
        if (zoomLevel != this.lastZoomLevel || iGeoPointFromPixels.getLatitude() != this.lastLatitude || z) {
            this.lastZoomLevel = zoomLevel;
            this.lastLatitude = iGeoPointFromPixels.getLatitude();
            rebuildBarPath(projection);
        }
        int i = this.xOffset;
        int i2 = this.yOffset;
        if (this.alignBottom) {
            i2 *= -1;
        }
        if (this.alignRight) {
            i *= -1;
        }
        if (this.centred && this.latitudeBar) {
            i += (-this.latitudeBarRect.width()) / 2;
        }
        if (this.centred && this.longitudeBar) {
            i2 += (-this.longitudeBarRect.height()) / 2;
        }
        projection.save(canvas, false, true);
        canvas.translate(i, i2);
        if (this.latitudeBar && (paint = this.bgPaint) != null) {
            canvas.drawRect(this.latitudeBarRect, paint);
        }
        if (this.longitudeBar && this.bgPaint != null) {
            canvas.drawRect(this.longitudeBarRect.left, this.longitudeBarRect.top + (this.latitudeBar ? this.latitudeBarRect.height() : 0), this.longitudeBarRect.right, this.longitudeBarRect.bottom, this.bgPaint);
        }
        canvas.drawPath(this.barPath, this.barPaint);
        if (this.latitudeBar) {
            drawLatitudeText(canvas, projection);
        }
        if (this.longitudeBar) {
            drawLongitudeText(canvas, projection);
        }
        projection.restore(canvas, true);
    }

    public void disableScaleBar() {
        setEnabled(false);
    }

    public void enableScaleBar() {
        setEnabled(true);
    }

    private void drawLatitudeText(Canvas canvas, Projection projection) {
        int iHeight;
        int i = (int) (this.maxLength * ((int) (this.xdpi / 2.54d)));
        int i2 = i / 2;
        double dDistanceToAsDouble = ((GeoPoint) projection.fromPixels((this.screenWidth / 2) - i2, this.yOffset, null)).distanceToAsDouble(projection.fromPixels((this.screenWidth / 2) + i2, this.yOffset, null));
        double dAdjustScaleBarLength = this.adjustLength ? adjustScaleBarLength(dDistanceToAsDouble) : dDistanceToAsDouble;
        int i3 = (int) ((i * dAdjustScaleBarLength) / dDistanceToAsDouble);
        String strScaleBarLengthText = scaleBarLengthText(dAdjustScaleBarLength);
        Paint paint = this.textPaint;
        int length = strScaleBarLengthText.length();
        Rect rect = sTextBoundsRect;
        paint.getTextBounds(strScaleBarLengthText, 0, length, rect);
        int iHeight2 = (int) (rect.height() / 5.0d);
        float fWidth = (i3 / 2) - (rect.width() / 2);
        if (this.alignRight) {
            fWidth += this.screenWidth - i3;
        }
        if (this.alignBottom) {
            iHeight = this.screenHeight - (iHeight2 * 2);
        } else {
            iHeight = rect.height() + iHeight2;
        }
        canvas.drawText(strScaleBarLengthText, fWidth, iHeight, this.textPaint);
    }

    private void drawLongitudeText(Canvas canvas, Projection projection) {
        int iHeight;
        int i = (int) (this.maxLength * ((int) (this.ydpi / 2.54d)));
        int i2 = i / 2;
        double dDistanceToAsDouble = ((GeoPoint) projection.fromPixels(this.screenWidth / 2, (this.screenHeight / 2) - i2, null)).distanceToAsDouble(projection.fromPixels(this.screenWidth / 2, (this.screenHeight / 2) + i2, null));
        double dAdjustScaleBarLength = this.adjustLength ? adjustScaleBarLength(dDistanceToAsDouble) : dDistanceToAsDouble;
        int i3 = (int) ((i * dAdjustScaleBarLength) / dDistanceToAsDouble);
        String strScaleBarLengthText = scaleBarLengthText(dAdjustScaleBarLength);
        Paint paint = this.textPaint;
        int length = strScaleBarLengthText.length();
        Rect rect = sTextBoundsRect;
        paint.getTextBounds(strScaleBarLengthText, 0, length, rect);
        int iHeight2 = (int) (rect.height() / 5.0d);
        if (this.alignRight) {
            iHeight = this.screenWidth - (iHeight2 * 2);
        } else {
            iHeight = rect.height() + iHeight2;
        }
        float f = iHeight;
        float fWidth = (i3 / 2) + (rect.width() / 2);
        if (this.alignBottom) {
            fWidth += this.screenHeight - i3;
        }
        canvas.save();
        canvas.rotate(-90.0f, f, fWidth);
        canvas.drawText(strScaleBarLengthText, f, fWidth, this.textPaint);
        canvas.restore();
    }

    protected void rebuildBarPath(Projection projection) {
        int mapHeight;
        float f = this.maxLength;
        int i = (int) (((int) (this.xdpi / 2.54d)) * f);
        int i2 = (int) (f * ((int) (this.ydpi / 2.54d)));
        int i3 = i / 2;
        double dDistanceToAsDouble = ((GeoPoint) projection.fromPixels((this.screenWidth / 2) - i3, this.yOffset, null)).distanceToAsDouble(projection.fromPixels((this.screenWidth / 2) + i3, this.yOffset, null));
        double dAdjustScaleBarLength = this.adjustLength ? adjustScaleBarLength(dDistanceToAsDouble) : dDistanceToAsDouble;
        int i4 = (int) ((i * dAdjustScaleBarLength) / dDistanceToAsDouble);
        int i5 = i2 / 2;
        double dDistanceToAsDouble2 = ((GeoPoint) projection.fromPixels(this.screenWidth / 2, (this.screenHeight / 2) - i5, null)).distanceToAsDouble(projection.fromPixels(this.screenWidth / 2, (this.screenHeight / 2) + i5, null));
        double dAdjustScaleBarLength2 = this.adjustLength ? adjustScaleBarLength(dDistanceToAsDouble2) : dDistanceToAsDouble2;
        int i6 = (int) ((i2 * dAdjustScaleBarLength2) / dDistanceToAsDouble2);
        String strScaleBarLengthText = scaleBarLengthText(dAdjustScaleBarLength);
        Rect rect = new Rect();
        int mapWidth = 0;
        this.textPaint.getTextBounds(strScaleBarLengthText, 0, strScaleBarLengthText.length(), rect);
        int iHeight = (int) (rect.height() / 5.0d);
        String strScaleBarLengthText2 = scaleBarLengthText(dAdjustScaleBarLength2);
        Rect rect2 = new Rect();
        this.textPaint.getTextBounds(strScaleBarLengthText2, 0, strScaleBarLengthText2.length(), rect2);
        int iHeight2 = (int) (rect2.height() / 5.0d);
        int iHeight3 = rect.height();
        int iHeight4 = rect2.height();
        this.barPath.rewind();
        if (this.alignBottom) {
            iHeight *= -1;
            iHeight3 *= -1;
            mapHeight = getMapHeight();
            i6 = mapHeight - i6;
        } else {
            mapHeight = 0;
        }
        if (this.alignRight) {
            iHeight2 *= -1;
            iHeight4 *= -1;
            mapWidth = getMapWidth();
            i4 = mapWidth - i4;
        }
        if (this.latitudeBar) {
            float f2 = i4;
            int i7 = iHeight3 + mapHeight + (iHeight * 2);
            float f3 = i7;
            this.barPath.moveTo(f2, f3);
            float f4 = mapHeight;
            this.barPath.lineTo(f2, f4);
            float f5 = mapWidth;
            this.barPath.lineTo(f5, f4);
            if (!this.longitudeBar) {
                this.barPath.lineTo(f5, f3);
            }
            this.latitudeBarRect.set(mapWidth, mapHeight, i4, i7);
        }
        if (this.longitudeBar) {
            if (!this.latitudeBar) {
                float f6 = mapHeight;
                this.barPath.moveTo(mapWidth + iHeight4 + (iHeight2 * 2), f6);
                this.barPath.lineTo(mapWidth, f6);
            }
            float f7 = i6;
            this.barPath.lineTo(mapWidth, f7);
            int i8 = iHeight4 + mapWidth + (iHeight2 * 2);
            this.barPath.lineTo(i8, f7);
            this.longitudeBarRect.set(mapWidth, mapHeight, i8, i6);
        }
    }

    private double adjustScaleBarLength(double d) {
        double d2;
        double d3;
        boolean z = true;
        long j = 0;
        if (this.unitsOfMeasure == UnitsOfMeasure.imperial) {
            if (d >= 321.8688d) {
                d2 = d / 1609.344d;
                z = false;
            }
            d2 = d * 3.2808399d;
        } else {
            if (this.unitsOfMeasure == UnitsOfMeasure.nautical) {
                if (d >= 370.4d) {
                    d2 = d / 1852.0d;
                }
                d2 = d * 3.2808399d;
            } else {
                d2 = d;
            }
            z = false;
        }
        while (d2 >= 10.0d) {
            j++;
            d2 /= 10.0d;
        }
        while (true) {
            d3 = 1.0d;
            if (d2 >= 1.0d || d2 <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                break;
            }
            j--;
            d2 *= 10.0d;
        }
        if (d2 >= 2.0d) {
            d3 = 5.0d;
            if (d2 < 5.0d) {
                d3 = 2.0d;
            }
        }
        if (z) {
            d3 /= 3.2808399d;
        } else if (this.unitsOfMeasure == UnitsOfMeasure.imperial) {
            d3 *= 1609.344d;
        } else if (this.unitsOfMeasure == UnitsOfMeasure.nautical) {
            d3 *= 1852.0d;
        }
        return d3 * Math.pow(10.0d, j);
    }

    /* renamed from: org.osmdroid.views.overlay.ScaleBarOverlay$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure;

        static {
            int[] iArr = new int[UnitsOfMeasure.values().length];
            $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure = iArr;
            try {
                iArr[UnitsOfMeasure.metric.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[UnitsOfMeasure.imperial.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[UnitsOfMeasure.nautical.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    protected String scaleBarLengthText(double d) {
        int i = AnonymousClass1.$SwitchMap$org$osmdroid$views$overlay$ScaleBarOverlay$UnitsOfMeasure[this.unitsOfMeasure.ordinal()];
        if (i == 2) {
            if (d >= 8046.72d) {
                return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.statuteMile, "%.0f");
            }
            if (d >= 321.8688d) {
                return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.statuteMile, "%.1f");
            }
            return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.foot, "%.0f");
        }
        if (i == 3) {
            if (d >= 9260.0d) {
                return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.nauticalMile, "%.0f");
            }
            if (d >= 370.4d) {
                return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.nauticalMile, "%.1f");
            }
            return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.foot, "%.0f");
        }
        if (d >= 5000.0d) {
            return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.kilometer, "%.0f");
        }
        if (d >= 200.0d) {
            return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.kilometer, "%.1f");
        }
        if (d >= 20.0d) {
            return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.meter, "%.0f");
        }
        return getConvertedScaleString(d, GeoConstants.UnitOfMeasure.meter, "%.2f");
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        this.context = null;
        this.mMapView = null;
        this.barPaint = null;
        this.bgPaint = null;
        this.textPaint = null;
    }

    private String getConvertedScaleString(double d, GeoConstants.UnitOfMeasure unitOfMeasure, String str) {
        return getScaleString(this.context, String.format(Locale.getDefault(), str, Double.valueOf(d / unitOfMeasure.getConversionFactorToMeters())), unitOfMeasure);
    }

    public static String getScaleString(Context context, String str, GeoConstants.UnitOfMeasure unitOfMeasure) {
        return context.getString(R.string.format_distance_value_unit, str, context.getString(unitOfMeasure.getStringResId()));
    }

    private int getMapWidth() {
        MapView mapView = this.mMapView;
        return mapView != null ? mapView.getWidth() : this.mMapWidth;
    }

    private int getMapHeight() {
        MapView mapView = this.mMapView;
        return mapView != null ? mapView.getHeight() : this.mMapHeight;
    }
}
