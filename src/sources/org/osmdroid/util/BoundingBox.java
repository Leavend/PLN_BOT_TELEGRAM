package org.osmdroid.util;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.List;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.config.Configuration;
import org.osmdroid.views.MapView;

/* loaded from: classes3.dex */
public class BoundingBox implements Parcelable, Serializable {
    public static final Parcelable.Creator<BoundingBox> CREATOR = new Parcelable.Creator<BoundingBox>() { // from class: org.osmdroid.util.BoundingBox.1
        @Override // android.os.Parcelable.Creator
        public BoundingBox createFromParcel(Parcel parcel) {
            return BoundingBox.readFromParcel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BoundingBox[] newArray(int i) {
            return new BoundingBox[i];
        }
    };
    static final long serialVersionUID = 2;
    private double mLatNorth;
    private double mLatSouth;
    private double mLonEast;
    private double mLonWest;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BoundingBox(double d, double d2, double d3, double d4) {
        set(d, d2, d3, d4);
    }

    public BoundingBox() {
    }

    public void set(double d, double d2, double d3, double d4) {
        this.mLatNorth = d;
        this.mLonEast = d2;
        this.mLatSouth = d3;
        this.mLonWest = d4;
        if (Configuration.getInstance().isEnforceTileSystemBounds()) {
            TileSystem tileSystem = MapView.getTileSystem();
            if (!tileSystem.isValidLatitude(d)) {
                throw new IllegalArgumentException("north must be in " + tileSystem.toStringLatitudeSpan());
            }
            if (!tileSystem.isValidLatitude(d3)) {
                throw new IllegalArgumentException("south must be in " + tileSystem.toStringLatitudeSpan());
            }
            if (!tileSystem.isValidLongitude(d4)) {
                throw new IllegalArgumentException("west must be in " + tileSystem.toStringLongitudeSpan());
            }
            if (!tileSystem.isValidLongitude(d2)) {
                throw new IllegalArgumentException("east must be in " + tileSystem.toStringLongitudeSpan());
            }
        }
    }

    public BoundingBox clone() {
        return new BoundingBox(this.mLatNorth, this.mLonEast, this.mLatSouth, this.mLonWest);
    }

    public BoundingBox concat(BoundingBox boundingBox) {
        return new BoundingBox(Math.max(this.mLatNorth, boundingBox.getLatNorth()), Math.max(this.mLonEast, boundingBox.getLonEast()), Math.min(this.mLatSouth, boundingBox.getLatSouth()), Math.min(this.mLonWest, boundingBox.getLonWest()));
    }

    @Deprecated
    public GeoPoint getCenter() {
        return new GeoPoint((this.mLatNorth + this.mLatSouth) / 2.0d, (this.mLonEast + this.mLonWest) / 2.0d);
    }

    public GeoPoint getCenterWithDateLine() {
        return new GeoPoint(getCenterLatitude(), getCenterLongitude());
    }

    public double getDiagonalLengthInMeters() {
        return new GeoPoint(this.mLatNorth, this.mLonWest).distanceToAsDouble(new GeoPoint(this.mLatSouth, this.mLonEast));
    }

    public double getLatNorth() {
        return this.mLatNorth;
    }

    public double getLatSouth() {
        return this.mLatSouth;
    }

    public double getCenterLatitude() {
        return (this.mLatNorth + this.mLatSouth) / 2.0d;
    }

    public double getCenterLongitude() {
        return getCenterLongitude(this.mLonWest, this.mLonEast);
    }

    public static double getCenterLongitude(double d, double d2) {
        double d3 = (d2 + d) / 2.0d;
        if (d2 < d) {
            d3 += 180.0d;
        }
        return MapView.getTileSystem().cleanLongitude(d3);
    }

    public double getActualNorth() {
        return Math.max(this.mLatNorth, this.mLatSouth);
    }

    public double getActualSouth() {
        return Math.min(this.mLatNorth, this.mLatSouth);
    }

    public double getLonEast() {
        return this.mLonEast;
    }

    public double getLonWest() {
        return this.mLonWest;
    }

    public double getLatitudeSpan() {
        return Math.abs(this.mLatNorth - this.mLatSouth);
    }

    @Deprecated
    public double getLongitudeSpan() {
        return Math.abs(this.mLonEast - this.mLonWest);
    }

    public void setLatNorth(double d) {
        this.mLatNorth = d;
    }

    public void setLatSouth(double d) {
        this.mLatSouth = d;
    }

    public void setLonEast(double d) {
        this.mLonEast = d;
    }

    public void setLonWest(double d) {
        this.mLonWest = d;
    }

    public double getLongitudeSpanWithDateLine() {
        double d = this.mLonEast;
        double d2 = this.mLonWest;
        return d > d2 ? d - d2 : (d - d2) + 360.0d;
    }

    public PointF getRelativePositionOfGeoPointInBoundingBoxWithLinearInterpolation(double d, double d2, PointF pointF) {
        if (pointF == null) {
            pointF = new PointF();
        }
        pointF.set(1.0f - ((float) ((this.mLonEast - d2) / getLongitudeSpan())), (float) ((this.mLatNorth - d) / getLatitudeSpan()));
        return pointF;
    }

    public PointF getRelativePositionOfGeoPointInBoundingBoxWithExactGudermannInterpolation(double d, double d2, PointF pointF) {
        if (pointF == null) {
            pointF = new PointF();
        }
        pointF.set(1.0f - ((float) ((this.mLonEast - d2) / getLongitudeSpan())), (float) ((MyMath.gudermannInverse(this.mLatNorth) - MyMath.gudermannInverse(d)) / (MyMath.gudermannInverse(this.mLatNorth) - MyMath.gudermannInverse(this.mLatSouth))));
        return pointF;
    }

    public GeoPoint getGeoPointOfRelativePositionWithLinearInterpolation(float f, float f2) {
        TileSystem tileSystem = MapView.getTileSystem();
        return new GeoPoint(tileSystem.cleanLatitude(this.mLatNorth - (getLatitudeSpan() * f2)), tileSystem.cleanLongitude(this.mLonWest + (getLongitudeSpan() * f)));
    }

    public GeoPoint getGeoPointOfRelativePositionWithExactGudermannInterpolation(float f, float f2) {
        TileSystem tileSystem = MapView.getTileSystem();
        double dGudermannInverse = MyMath.gudermannInverse(this.mLatNorth);
        double dGudermannInverse2 = MyMath.gudermannInverse(this.mLatSouth);
        return new GeoPoint(tileSystem.cleanLatitude(MyMath.gudermann(dGudermannInverse2 + ((1.0f - f2) * (dGudermannInverse - dGudermannInverse2)))), tileSystem.cleanLongitude(this.mLonWest + (getLongitudeSpan() * f)));
    }

    public BoundingBox increaseByScale(float f) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("pBoundingboxPaddingRelativeScale must be positive");
        }
        TileSystem tileSystem = MapView.getTileSystem();
        double centerLatitude = getCenterLatitude();
        double d = f;
        double latitudeSpan = (getLatitudeSpan() / 2.0d) * d;
        double dCleanLatitude = tileSystem.cleanLatitude(centerLatitude + latitudeSpan);
        double dCleanLatitude2 = tileSystem.cleanLatitude(centerLatitude - latitudeSpan);
        double centerLongitude = getCenterLongitude();
        double longitudeSpanWithDateLine = (getLongitudeSpanWithDateLine() / 2.0d) * d;
        return new BoundingBox(dCleanLatitude, tileSystem.cleanLongitude(centerLongitude + longitudeSpanWithDateLine), dCleanLatitude2, tileSystem.cleanLongitude(centerLongitude - longitudeSpanWithDateLine));
    }

    public String toString() {
        return new StringBuffer("N:").append(this.mLatNorth).append("; E:").append(this.mLonEast).append("; S:").append(this.mLatSouth).append("; W:").append(this.mLonWest).toString();
    }

    public GeoPoint bringToBoundingBox(double d, double d2) {
        return new GeoPoint(Math.max(this.mLatSouth, Math.min(this.mLatNorth, d)), Math.max(this.mLonWest, Math.min(this.mLonEast, d2)));
    }

    public static BoundingBox fromGeoPoints(List<? extends IGeoPoint> list) {
        double dMin = Double.MAX_VALUE;
        double dMin2 = Double.MAX_VALUE;
        double dMax = -1.7976931348623157E308d;
        double dMax2 = -1.7976931348623157E308d;
        for (IGeoPoint iGeoPoint : list) {
            double latitude = iGeoPoint.getLatitude();
            double longitude = iGeoPoint.getLongitude();
            dMin = Math.min(dMin, latitude);
            dMin2 = Math.min(dMin2, longitude);
            dMax = Math.max(dMax, latitude);
            dMax2 = Math.max(dMax2, longitude);
        }
        return new BoundingBox(dMax, dMax2, dMin, dMin2);
    }

    public boolean contains(IGeoPoint iGeoPoint) {
        return contains(iGeoPoint.getLatitude(), iGeoPoint.getLongitude());
    }

    public boolean contains(double d, double d2) {
        double d3 = this.mLatNorth;
        double d4 = this.mLatSouth;
        boolean z = d3 < d4 || (d < d3 && d > d4);
        double d5 = this.mLonEast;
        double d6 = this.mLonWest;
        return z && ((d5 > d6 ? 1 : (d5 == d6 ? 0 : -1)) >= 0 ? !((d2 > d5 ? 1 : (d2 == d5 ? 0 : -1)) >= 0 || (d2 > d6 ? 1 : (d2 == d6 ? 0 : -1)) <= 0) : !((d2 > d5 ? 1 : (d2 == d5 ? 0 : -1)) > 0 || (d2 > d6 ? 1 : (d2 == d6 ? 0 : -1)) < 0));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mLatNorth);
        parcel.writeDouble(this.mLonEast);
        parcel.writeDouble(this.mLatSouth);
        parcel.writeDouble(this.mLonWest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BoundingBox readFromParcel(Parcel parcel) {
        return new BoundingBox(parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble());
    }

    @Deprecated
    public int getLatitudeSpanE6() {
        return (int) (getLatitudeSpan() * 1000000.0d);
    }

    @Deprecated
    public int getLongitudeSpanE6() {
        return (int) (getLongitudeSpan() * 1000000.0d);
    }

    public boolean overlaps(BoundingBox boundingBox, double d) {
        if (d < 3.0d) {
            return true;
        }
        double d2 = boundingBox.mLatSouth;
        double d3 = this.mLatNorth;
        boolean z = d2 <= d3 && d2 >= this.mLatSouth;
        double d4 = this.mLonWest;
        double d5 = boundingBox.mLonWest;
        boolean z2 = d4 >= d5 && d4 <= boundingBox.mLonEast;
        double d6 = this.mLonEast;
        if (d6 >= d5 && d4 <= boundingBox.mLonEast) {
            z2 = true;
        }
        if (d4 <= d5 && d6 >= boundingBox.mLonEast && d3 >= boundingBox.mLatNorth && this.mLatSouth <= d2) {
            return true;
        }
        if (d3 >= d2 && d3 <= this.mLatSouth) {
            z = true;
        }
        double d7 = this.mLatSouth;
        if (d7 >= d2 && d7 <= d7) {
            z = true;
        }
        if (d4 > d6) {
            double d8 = boundingBox.mLonEast;
            if (d6 <= d8 && d5 >= d4) {
                z2 = true;
            }
            if (d4 >= d8 && d6 <= d8) {
                z2 = (d8 <= d6 || d5 <= d6) ? d8 >= d4 || d5 >= d4 : false;
            }
            if (d4 >= d8 && d6 >= d8) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public static BoundingBox fromGeoPointsSafe(List<GeoPoint> list) {
        try {
            return fromGeoPoints(list);
        } catch (IllegalArgumentException unused) {
            TileSystem tileSystem = MapView.getTileSystem();
            return new BoundingBox(tileSystem.getMaxLatitude(), tileSystem.getMaxLongitude(), tileSystem.getMinLatitude(), tileSystem.getMinLongitude());
        }
    }
}
