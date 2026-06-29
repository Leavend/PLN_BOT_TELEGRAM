package org.osmdroid.util;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

/* loaded from: classes3.dex */
public class SegmentIntersection {
    public static boolean intersection(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        if (parallelSideEffect(d, d2, d3, d4, d5, d6, d7, d8, pointL) || divisionByZeroSideEffect(d, d2, d3, d4, d5, d6, d7, d8, pointL)) {
            return true;
        }
        double d9 = d - d3;
        double d10 = d6 - d8;
        double d11 = d2 - d4;
        double d12 = d5 - d7;
        double d13 = (d9 * d10) - (d11 * d12);
        if (d13 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            return false;
        }
        double d14 = (d * d4) - (d2 * d3);
        double d15 = (d5 * d8) - (d6 * d7);
        return check(d, d2, d3, d4, d5, d6, d7, d8, pointL, ((d12 * d14) - (d9 * d15)) / d13, ((d10 * d14) - (d11 * d15)) / d13);
    }

    private static boolean parallelSideEffect(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        if (d == d3) {
            return parallelSideEffectSameX(d, d2, d3, d4, d5, d6, d7, d8, pointL);
        }
        if (d5 == d7) {
            return parallelSideEffectSameX(d5, d6, d7, d8, d, d2, d3, d4, pointL);
        }
        double d9 = (d4 - d2) / (d3 - d);
        double d10 = (d8 - d6) / (d7 - d5);
        if (d9 == d10 && d2 - (d9 * d) == d6 - (d10 * d5)) {
            return check(d, d2, d3, d4, d5, d6, d7, d8, pointL, middle(d, d3, d5, d7), middle(d2, d4, d6, d8));
        }
        return false;
    }

    private static double middle(double d, double d2, double d3, double d4) {
        return (Math.min(Math.max(d, d2), Math.max(d3, d4)) + Math.max(Math.min(d, d2), Math.min(d3, d4))) / 2.0d;
    }

    private static boolean check(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL, double d9, double d10) {
        if (d9 < Math.min(d, d3) || d9 > Math.max(d, d3) || d9 < Math.min(d5, d7) || d9 > Math.max(d5, d7) || d10 < Math.min(d2, d4) || d10 > Math.max(d2, d4) || d10 < Math.min(d6, d8) || d10 > Math.max(d6, d8)) {
            return false;
        }
        if (pointL == null) {
            return true;
        }
        pointL.x = Math.round(d9);
        pointL.y = Math.round(d10);
        return true;
    }

    private static boolean parallelSideEffectSameX(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        if (d == d3 && d5 == d7 && d == d5) {
            return check(d, d2, d3, d4, d5, d6, d7, d8, pointL, d, middle(d2, d4, d6, d8));
        }
        return false;
    }

    private static boolean divisionByZeroSideEffect(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        return divisionByZeroSideEffectX(d, d2, d3, d4, d5, d6, d7, d8, pointL) || divisionByZeroSideEffectX(d5, d6, d7, d8, d, d2, d3, d4, pointL) || divisionByZeroSideEffectY(d, d2, d3, d4, d5, d6, d7, d8, pointL) || divisionByZeroSideEffectY(d5, d6, d7, d8, d, d2, d3, d4, pointL);
    }

    private static boolean divisionByZeroSideEffectX(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        if (d == d3 && d5 != d7) {
            return check(d, d2, d3, d4, d5, d6, d7, d8, pointL, d, (((d - d5) / (d7 - d5)) * (d8 - d6)) + d6);
        }
        return false;
    }

    private static boolean divisionByZeroSideEffectY(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, PointL pointL) {
        if (d2 == d4 && d6 != d8) {
            return check(d, d2, d3, d4, d5, d6, d7, d8, pointL, (((d2 - d6) / (d8 - d6)) * (d7 - d5)) + d5, d2);
        }
        return false;
    }
}
