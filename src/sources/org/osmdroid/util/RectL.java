package org.osmdroid.util;

import android.graphics.Rect;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes3.dex */
public class RectL {
    public long bottom;
    public long left;
    public long right;
    public long top;

    public RectL() {
    }

    public RectL(long j, long j2, long j3, long j4) {
        set(j, j2, j3, j4);
    }

    public RectL(RectL rectL) {
        set(rectL);
    }

    public void set(long j, long j2, long j3, long j4) {
        this.left = j;
        this.top = j2;
        this.right = j3;
        this.bottom = j4;
    }

    public void set(RectL rectL) {
        this.left = rectL.left;
        this.top = rectL.top;
        this.right = rectL.right;
        this.bottom = rectL.bottom;
    }

    public void union(long j, long j2) {
        if (j < this.left) {
            this.left = j;
        } else if (j > this.right) {
            this.right = j;
        }
        if (j2 < this.top) {
            this.top = j2;
        } else if (j2 > this.bottom) {
            this.bottom = j2;
        }
    }

    public static boolean intersects(RectL rectL, RectL rectL2) {
        return rectL.left < rectL2.right && rectL2.left < rectL.right && rectL.top < rectL2.bottom && rectL2.top < rectL.bottom;
    }

    public boolean contains(long j, long j2) {
        long j3 = this.left;
        long j4 = this.right;
        if (j3 < j4) {
            long j5 = this.top;
            long j6 = this.bottom;
            if (j5 < j6 && j >= j3 && j < j4 && j2 >= j5 && j2 < j6) {
                return true;
            }
        }
        return false;
    }

    public void inset(long j, long j2) {
        this.left += j;
        this.top += j2;
        this.right -= j;
        this.bottom -= j2;
    }

    public final long width() {
        return this.right - this.left;
    }

    public final long height() {
        return this.bottom - this.top;
    }

    public String toString() {
        return "RectL(" + this.left + ", " + this.top + " - " + this.right + ", " + this.bottom + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RectL rectL = (RectL) obj;
        return this.left == rectL.left && this.top == rectL.top && this.right == rectL.right && this.bottom == rectL.bottom;
    }

    public int hashCode() {
        return (int) (((((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom) % 2147483647L);
    }

    public static RectL getBounds(RectL rectL, long j, long j2, double d, RectL rectL2) {
        RectL rectL3 = rectL2 != null ? rectL2 : new RectL();
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            rectL3.top = rectL.top;
            rectL3.left = rectL.left;
            rectL3.bottom = rectL.bottom;
            rectL3.right = rectL.right;
            return rectL3;
        }
        double d2 = (3.141592653589793d * d) / 180.0d;
        double dCos = Math.cos(d2);
        double dSin = Math.sin(d2);
        long j3 = rectL.left;
        long j4 = rectL.top;
        long rotatedX = getRotatedX(j3, j4, j, j2, dCos, dSin);
        long rotatedY = getRotatedY(j3, j4, j, j2, dCos, dSin);
        rectL3.bottom = rotatedY;
        rectL3.top = rotatedY;
        rectL3.right = rotatedX;
        rectL3.left = rotatedX;
        long j5 = rectL.right;
        long j6 = rectL.top;
        long rotatedX2 = getRotatedX(j5, j6, j, j2, dCos, dSin);
        long rotatedY2 = getRotatedY(j5, j6, j, j2, dCos, dSin);
        if (rectL3.top > rotatedY2) {
            rectL3.top = rotatedY2;
        }
        if (rectL3.bottom < rotatedY2) {
            rectL3.bottom = rotatedY2;
        }
        if (rectL3.left > rotatedX2) {
            rectL3.left = rotatedX2;
        }
        if (rectL3.right < rotatedX2) {
            rectL3.right = rotatedX2;
        }
        long j7 = rectL.right;
        long j8 = rectL.bottom;
        long rotatedX3 = getRotatedX(j7, j8, j, j2, dCos, dSin);
        long rotatedY3 = getRotatedY(j7, j8, j, j2, dCos, dSin);
        if (rectL3.top > rotatedY3) {
            rectL3.top = rotatedY3;
        }
        if (rectL3.bottom < rotatedY3) {
            rectL3.bottom = rotatedY3;
        }
        if (rectL3.left > rotatedX3) {
            rectL3.left = rotatedX3;
        }
        if (rectL3.right < rotatedX3) {
            rectL3.right = rotatedX3;
        }
        long j9 = rectL.left;
        long j10 = rectL.bottom;
        long rotatedX4 = getRotatedX(j9, j10, j, j2, dCos, dSin);
        long rotatedY4 = getRotatedY(j9, j10, j, j2, dCos, dSin);
        if (rectL3.top > rotatedY4) {
            rectL3.top = rotatedY4;
        }
        if (rectL3.bottom < rotatedY4) {
            rectL3.bottom = rotatedY4;
        }
        if (rectL3.left > rotatedX4) {
            rectL3.left = rotatedX4;
        }
        if (rectL3.right < rotatedX4) {
            rectL3.right = rotatedX4;
        }
        return rectL3;
    }

    public static Rect getBounds(Rect rect, int i, int i2, double d, Rect rect2) {
        Rect rect3 = rect2 != null ? rect2 : new Rect();
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            rect3.top = rect.top;
            rect3.left = rect.left;
            rect3.bottom = rect.bottom;
            rect3.right = rect.right;
            return rect3;
        }
        double d2 = (3.141592653589793d * d) / 180.0d;
        double dCos = Math.cos(d2);
        double dSin = Math.sin(d2);
        long j = rect.left;
        long j2 = rect.top;
        long j3 = i;
        long j4 = i2;
        int rotatedX = (int) getRotatedX(j, j2, j3, j4, dCos, dSin);
        int rotatedY = (int) getRotatedY(j, j2, j3, j4, dCos, dSin);
        rect3.bottom = rotatedY;
        rect3.top = rotatedY;
        rect3.right = rotatedX;
        rect3.left = rotatedX;
        long j5 = rect.right;
        long j6 = rect.top;
        int rotatedX2 = (int) getRotatedX(j5, j6, j3, j4, dCos, dSin);
        int rotatedY2 = (int) getRotatedY(j5, j6, j3, j4, dCos, dSin);
        if (rect3.top > rotatedY2) {
            rect3.top = rotatedY2;
        }
        if (rect3.bottom < rotatedY2) {
            rect3.bottom = rotatedY2;
        }
        if (rect3.left > rotatedX2) {
            rect3.left = rotatedX2;
        }
        if (rect3.right < rotatedX2) {
            rect3.right = rotatedX2;
        }
        long j7 = rect.right;
        long j8 = rect.bottom;
        int rotatedX3 = (int) getRotatedX(j7, j8, j3, j4, dCos, dSin);
        int rotatedY3 = (int) getRotatedY(j7, j8, j3, j4, dCos, dSin);
        if (rect3.top > rotatedY3) {
            rect3.top = rotatedY3;
        }
        if (rect3.bottom < rotatedY3) {
            rect3.bottom = rotatedY3;
        }
        if (rect3.left > rotatedX3) {
            rect3.left = rotatedX3;
        }
        if (rect3.right < rotatedX3) {
            rect3.right = rotatedX3;
        }
        long j9 = rect.left;
        long j10 = rect.bottom;
        int rotatedX4 = (int) getRotatedX(j9, j10, j3, j4, dCos, dSin);
        int rotatedY4 = (int) getRotatedY(j9, j10, j3, j4, dCos, dSin);
        if (rect3.top > rotatedY4) {
            rect3.top = rotatedY4;
        }
        if (rect3.bottom < rotatedY4) {
            rect3.bottom = rotatedY4;
        }
        if (rect3.left > rotatedX4) {
            rect3.left = rotatedX4;
        }
        if (rect3.right < rotatedX4) {
            rect3.right = rotatedX4;
        }
        return rect3;
    }

    public static long getRotatedX(long j, long j2, double d, long j3, long j4) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return j;
        }
        double d2 = (3.141592653589793d * d) / 180.0d;
        return getRotatedX(j, j2, j3, j4, Math.cos(d2), Math.sin(d2));
    }

    public static long getRotatedY(long j, long j2, double d, long j3, long j4) {
        if (d == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return j2;
        }
        double d2 = (3.141592653589793d * d) / 180.0d;
        return getRotatedY(j, j2, j3, j4, Math.cos(d2), Math.sin(d2));
    }

    public static long getRotatedX(long j, long j2, long j3, long j4, double d, double d2) {
        return j3 + Math.round(((j - j3) * d) - ((j2 - j4) * d2));
    }

    public static long getRotatedY(long j, long j2, long j3, long j4, double d, double d2) {
        return j4 + Math.round(((j - j3) * d2) + ((j2 - j4) * d));
    }

    public void offset(long j, long j2) {
        this.left += j;
        this.top += j2;
        this.right += j;
        this.bottom += j2;
    }

    public void union(long j, long j2, long j3, long j4) {
        long j5 = j4;
        if (j >= j3 || j2 >= j5) {
            return;
        }
        long j6 = this.left;
        long j7 = this.right;
        if (j6 < j7) {
            long j8 = this.top;
            long j9 = this.bottom;
            if (j8 < j9) {
                if (j6 > j) {
                    this.left = j;
                }
                if (j8 > j2) {
                    this.top = j2;
                }
                if (j7 < j3) {
                    this.right = j3;
                }
                if (j9 < j4) {
                    this.bottom = j4;
                    return;
                }
                return;
            }
            j5 = j4;
        }
        this.left = j;
        this.top = j2;
        this.right = j3;
        this.bottom = j5;
    }

    public void union(RectL rectL) {
        union(rectL.left, rectL.top, rectL.right, rectL.bottom);
    }

    public long centerX() {
        return (this.left + this.right) / 2;
    }

    public long centerY() {
        return (this.top + this.bottom) / 2;
    }
}
