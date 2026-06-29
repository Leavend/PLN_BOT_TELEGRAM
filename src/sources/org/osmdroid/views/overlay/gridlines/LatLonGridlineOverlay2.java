package org.osmdroid.views.overlay.gridlines;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.text.DecimalFormat;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

/* loaded from: classes3.dex */
public class LatLonGridlineOverlay2 extends Overlay {
    protected DecimalFormat mDecimalFormatter = new DecimalFormat("#.#####");
    protected float mMultiplier = 1.0f;
    protected Paint mLinePaint = new Paint();
    protected Paint mTextBackgroundPaint = new Paint();
    protected Paint mTextPaint = new Paint();
    protected GeoPoint mOptimizationGeoPoint = new GeoPoint(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    protected Point mOptimizationPoint = new Point();

    public LatLonGridlineOverlay2() {
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mTextBackgroundPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setStyle(Paint.Style.STROKE);
        this.mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        setLineColor(ViewCompat.MEASURED_STATE_MASK);
        setFontColor(-1);
        setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        setLineWidth(1.0f);
        setFontSizeDp((short) 32);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x01ae A[PHI: r32
      0x01ae: PHI (r32v6 boolean) = (r32v3 boolean), (r32v3 boolean), (r32v1 boolean) binds: [B:68:0x01f8, B:70:0x01fc, B:59:0x01ac] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // org.osmdroid.views.overlay.Overlay
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void draw(android.graphics.Canvas r49, org.osmdroid.views.Projection r50) {
        /*
            Method dump skipped, instructions count: 692
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2.draw(android.graphics.Canvas, org.osmdroid.views.Projection):void");
    }

    public void setDecimalFormatter(DecimalFormat decimalFormat) {
        this.mDecimalFormatter = decimalFormat;
    }

    public void setLineColor(int i) {
        this.mLinePaint.setColor(i);
    }

    public void setFontColor(int i) {
        this.mTextPaint.setColor(i);
    }

    public void setFontSizeDp(short s) {
        this.mTextPaint.setTextSize(s);
    }

    public void setTextStyle(Paint.Style style) {
        this.mTextPaint.setStyle(style);
    }

    public void setTextPaint(Paint paint) {
        this.mTextPaint = paint;
    }

    public Paint getTextPaint() {
        return this.mTextPaint;
    }

    public void setBackgroundColor(int i) {
        this.mTextBackgroundPaint.setColor(i);
    }

    public void setLineWidth(float f) {
        this.mLinePaint.setStrokeWidth(f);
    }

    public void setMultiplier(float f) {
        this.mMultiplier = f;
    }

    protected double getIncrementor(int i) {
        double d;
        double d2;
        switch (i) {
            case 0:
            case 1:
                d = this.mMultiplier;
                d2 = 30.0d;
                break;
            case 2:
                d = this.mMultiplier;
                d2 = 15.0d;
                break;
            case 3:
                d = this.mMultiplier;
                d2 = 9.0d;
                break;
            case 4:
                d = this.mMultiplier;
                d2 = 6.0d;
                break;
            case 5:
                d = this.mMultiplier;
                d2 = 3.0d;
                break;
            case 6:
                d = this.mMultiplier;
                d2 = 2.0d;
                break;
            case 7:
                d = this.mMultiplier;
                d2 = 1.0d;
                break;
            case 8:
                d = this.mMultiplier;
                d2 = 0.5d;
                break;
            case 9:
                d = this.mMultiplier;
                d2 = 0.25d;
                break;
            case 10:
                d = this.mMultiplier;
                d2 = 0.1d;
                break;
            case 11:
                d = this.mMultiplier;
                d2 = 0.05d;
                break;
            case 12:
                d = this.mMultiplier;
                d2 = 0.025d;
                break;
            case 13:
                d = this.mMultiplier;
                d2 = 0.0125d;
                break;
            case 14:
                d = this.mMultiplier;
                d2 = 0.00625d;
                break;
            case 15:
                d = this.mMultiplier;
                d2 = 0.003125d;
                break;
            case 16:
                d = this.mMultiplier;
                d2 = 0.0015625d;
                break;
            case 17:
                d = this.mMultiplier;
                d2 = 7.8125E-4d;
                break;
            case 18:
                d = this.mMultiplier;
                d2 = 3.90625E-4d;
                break;
            case 19:
                d = this.mMultiplier;
                d2 = 1.953125E-4d;
                break;
            case 20:
                d = this.mMultiplier;
                d2 = 9.765625E-5d;
                break;
            case 21:
                d = this.mMultiplier;
                d2 = 4.8828125E-5d;
                break;
            case 22:
                d = this.mMultiplier;
                d2 = 2.44140625E-5d;
                break;
            case 23:
                d = this.mMultiplier;
                d2 = 1.220703125E-5d;
                break;
            case 24:
                d = this.mMultiplier;
                d2 = 6.103515625E-6d;
                break;
            case 25:
                d = this.mMultiplier;
                d2 = 3.0517578125E-6d;
                break;
            case 26:
                d = this.mMultiplier;
                d2 = 1.52587890625E-6d;
                break;
            case 27:
                d = this.mMultiplier;
                d2 = 7.62939453125E-7d;
                break;
            case 28:
                d = this.mMultiplier;
                d2 = 3.814697265625E-7d;
                break;
            default:
                d = this.mMultiplier;
                d2 = 1.9073486328125E-7d;
                break;
        }
        return d * d2;
    }

    private double computeStartLatitude(double d, double d2) {
        double dRound = Math.round(d / d2) * d2;
        while (dRound > MapView.getTileSystem().getMaxLatitude()) {
            dRound -= d2;
        }
        while (dRound < MapView.getTileSystem().getMinLatitude()) {
            dRound += d2;
        }
        return dRound;
    }

    private String formatCoordinate(double d, boolean z) {
        return this.mDecimalFormatter.format(d) + (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "" : d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? z ? "N" : ExifInterface.LONGITUDE_EAST : z ? ExifInterface.LATITUDE_SOUTH : ExifInterface.LONGITUDE_WEST);
    }
}
