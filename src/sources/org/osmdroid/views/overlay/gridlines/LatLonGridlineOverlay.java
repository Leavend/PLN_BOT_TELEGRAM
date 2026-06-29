package org.osmdroid.views.overlay.gridlines;

import android.content.Context;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

@Deprecated
/* loaded from: classes3.dex */
public class LatLonGridlineOverlay {
    static final DecimalFormat df = new DecimalFormat("#.#####");
    public static int lineColor = ViewCompat.MEASURED_STATE_MASK;
    public static int fontColor = -1;
    public static short fontSizeDp = 24;
    public static int backgroundColor = ViewCompat.MEASURED_STATE_MASK;
    public static float lineWidth = 1.0f;
    public static boolean DEBUG = false;
    public static boolean DEBUG2 = false;
    private static float multiplier = 1.0f;

    private static void applyMarkerAttributes(Marker marker) {
        marker.setTextLabelBackgroundColor(backgroundColor);
        marker.setTextLabelFontSize(fontSizeDp);
        marker.setTextLabelForegroundColor(fontColor);
    }

    public static FolderOverlay getLatLonGrid(Context context, MapView mapView) {
        boolean z;
        double d;
        double d2;
        double d3;
        String str;
        MapView mapView2 = mapView;
        BoundingBox boundingBox = mapView.getBoundingBox();
        int zoomLevel = mapView.getZoomLevel();
        if (DEBUG) {
            System.out.println("######### getLatLonGrid ");
        }
        FolderOverlay folderOverlay = new FolderOverlay();
        if (zoomLevel >= 2) {
            double latNorth = boundingBox.getLatNorth();
            double latSouth = boundingBox.getLatSouth();
            double lonEast = boundingBox.getLonEast();
            double lonWest = boundingBox.getLonWest();
            if (latNorth < latSouth) {
                return folderOverlay;
            }
            if (DEBUG) {
                System.out.println("N " + latNorth + " S " + latSouth + ", 0.0");
            }
            boolean z2 = lonEast < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && lonWest > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            if (DEBUG) {
                System.out.println("delta 0.0");
            }
            double incrementor = getIncrementor(zoomLevel);
            double[] startEndPointsNS = getStartEndPointsNS(latNorth, latSouth, zoomLevel);
            double d4 = startEndPointsNS[0];
            double d5 = startEndPointsNS[1];
            double d6 = d4;
            while (true) {
                z = z2;
                d = latSouth;
                if (d6 > d5) {
                    break;
                }
                double d7 = latNorth;
                Polyline polyline = new Polyline();
                double d8 = incrementor;
                polyline.getOutlinePaint().setStrokeWidth(lineWidth);
                polyline.getOutlinePaint().setColor(lineColor);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new GeoPoint(d6, lonEast));
                arrayList.add(new GeoPoint(d6, lonWest));
                if (DEBUG) {
                    System.out.println("drawing NS " + d6 + "," + lonEast + " to " + d6 + "," + lonWest + ", zoom " + zoomLevel);
                }
                polyline.setPoints(arrayList);
                folderOverlay.add(polyline);
                Marker marker = new Marker(mapView);
                applyMarkerAttributes(marker);
                String str2 = df.format(d6) + (d6 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? "N" : ExifInterface.LATITUDE_SOUTH);
                marker.setTitle(str2);
                marker.setTextIcon(str2);
                marker.setPosition(new GeoPoint(d6, lonWest + d8));
                folderOverlay.add(marker);
                d6 += d8;
                mapView2 = mapView;
                z2 = z;
                latSouth = d;
                latNorth = d7;
                incrementor = d8;
            }
            double d9 = latNorth;
            double d10 = incrementor;
            MapView mapView3 = mapView2;
            double[] startEndPointsWE = getStartEndPointsWE(lonWest, lonEast, zoomLevel);
            double d11 = startEndPointsWE[1];
            double d12 = startEndPointsWE[0];
            double d13 = d11;
            while (d13 <= d12) {
                Polyline polyline2 = new Polyline();
                polyline2.getOutlinePaint().setStrokeWidth(lineWidth);
                polyline2.getOutlinePaint().setColor(lineColor);
                ArrayList arrayList2 = new ArrayList();
                double d14 = d12;
                double d15 = d9;
                arrayList2.add(new GeoPoint(d15, d13));
                double d16 = d11;
                double d17 = d;
                arrayList2.add(new GeoPoint(d17, d13));
                polyline2.setPoints(arrayList2);
                if (DEBUG) {
                    PrintStream printStream = System.err;
                    str = ExifInterface.LONGITUDE_WEST;
                    printStream.println("drawing EW " + d17 + "," + d13 + " to " + d15 + "," + d13 + ", zoom " + zoomLevel);
                } else {
                    str = ExifInterface.LONGITUDE_WEST;
                }
                folderOverlay.add(polyline2);
                Marker marker2 = new Marker(mapView3);
                applyMarkerAttributes(marker2);
                marker2.setRotation(-90.0f);
                String str3 = df.format(d13) + (d13 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? ExifInterface.LONGITUDE_EAST : str);
                marker2.setTitle(str3);
                marker2.setTextIcon(str3);
                marker2.setPosition(new GeoPoint(d17 + d10, d13));
                folderOverlay.add(marker2);
                d13 += d10;
                d = d17;
                d11 = d16;
                d9 = d15;
                d12 = d14;
            }
            double d18 = d12;
            double d19 = d9;
            double d20 = d11;
            double d21 = d;
            if (z) {
                if (DEBUG) {
                    d2 = d18;
                    System.out.println("DATELINE zoom " + zoomLevel + " " + d20 + " " + d2);
                } else {
                    d2 = d18;
                }
                double d22 = d20;
                while (d22 <= 180.0d) {
                    Polyline polyline3 = new Polyline();
                    polyline3.getOutlinePaint().setStrokeWidth(lineWidth);
                    polyline3.getOutlinePaint().setColor(lineColor);
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(new GeoPoint(d19, d22));
                    arrayList3.add(new GeoPoint(d21, d22));
                    polyline3.setPoints(arrayList3);
                    if (DEBUG2) {
                        d3 = d2;
                        System.out.println("DATELINE drawing NS" + d21 + "," + d22 + " to " + d19 + "," + d22 + ", zoom " + zoomLevel);
                    } else {
                        d3 = d2;
                    }
                    folderOverlay.add(polyline3);
                    d22 += d10;
                    d2 = d3;
                }
                double d23 = d2;
                double d24 = -180.0d;
                while (d24 <= d23) {
                    Polyline polyline4 = new Polyline();
                    polyline4.getOutlinePaint().setStrokeWidth(lineWidth);
                    polyline4.getOutlinePaint().setColor(lineColor);
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.add(new GeoPoint(d19, d24));
                    arrayList4.add(new GeoPoint(d21, d24));
                    polyline4.setPoints(arrayList4);
                    if (DEBUG2) {
                        System.out.println("DATELINE drawing EW" + d21 + "," + d24 + " to " + d19 + "," + d24 + ", zoom " + zoomLevel);
                    }
                    folderOverlay.add(polyline4);
                    Marker marker3 = new Marker(mapView);
                    applyMarkerAttributes(marker3);
                    marker3.setRotation(-90.0f);
                    String str4 = df.format(d24) + (d24 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? ExifInterface.LONGITUDE_EAST : ExifInterface.LONGITUDE_WEST);
                    marker3.setTitle(str4);
                    marker3.setTextIcon(str4);
                    marker3.setPosition(new GeoPoint(d21 + d10, d24));
                    folderOverlay.add(marker3);
                    d24 += d10;
                }
                double d25 = d20;
                while (d25 < 180.0d) {
                    Marker marker4 = new Marker(mapView);
                    applyMarkerAttributes(marker4);
                    marker4.setRotation(-90.0f);
                    String str5 = df.format(d25) + (d25 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? ExifInterface.LONGITUDE_EAST : ExifInterface.LONGITUDE_WEST);
                    marker4.setTitle(str5);
                    marker4.setTextIcon(str5);
                    marker4.setPosition(new GeoPoint(d21 + d10, d25));
                    folderOverlay.add(marker4);
                    d25 += d10;
                }
            }
        }
        return folderOverlay;
    }

    private static double[] getStartEndPointsNS(double d, double d2, int i) {
        if (i < 10) {
            double dFloor = Math.floor(d2);
            double incrementor = getIncrementor(i);
            double d3 = -90.0d;
            while (d3 < dFloor) {
                d3 += incrementor;
            }
            double d4 = 90.0d;
            while (d4 > Math.ceil(d)) {
                d4 -= incrementor;
            }
            return new double[]{d3 >= -90.0d ? d3 : -90.0d, d4 <= 90.0d ? d4 : 90.0d};
        }
        double d5 = d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0.0d : -90.0d;
        double d6 = d < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0.0d : 90.0d;
        for (int i2 = 2; i2 <= i; i2++) {
            double incrementor2 = getIncrementor(i2);
            while (d5 < d2 - incrementor2) {
                d5 += incrementor2;
                if (DEBUG) {
                    System.out.println("south " + d5);
                }
            }
            while (d6 > d + incrementor2) {
                d6 -= incrementor2;
                if (DEBUG) {
                    System.out.println("north " + d6);
                }
            }
        }
        return new double[]{d5, d6};
    }

    private static double[] getStartEndPointsWE(double d, double d2, int i) {
        double incrementor = getIncrementor(i);
        if (i < 10) {
            double d3 = 180.0d;
            while (d3 > Math.floor(d)) {
                d3 -= incrementor;
            }
            double dCeil = Math.ceil(d2);
            for (double d4 = -180.0d; d4 < dCeil; d4 += incrementor) {
            }
            return new double[]{dCeil <= 180.0d ? dCeil : 180.0d, d3 >= -180.0d ? d3 : -180.0d};
        }
        double d5 = d > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0.0d : -180.0d;
        double d6 = d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? 0.0d : 180.0d;
        for (int i2 = 2; i2 <= i; i2++) {
            double incrementor2 = getIncrementor(i2);
            while (d6 > d2 + incrementor2) {
                d6 -= incrementor2;
            }
            while (d5 < d - incrementor2) {
                d5 += incrementor2;
                if (DEBUG) {
                    System.out.println("west " + d5);
                }
            }
        }
        if (DEBUG) {
            System.out.println("return EW set as " + d5 + " " + d6);
        }
        return new double[]{d6, d5};
    }

    private static double getIncrementor(int i) {
        double d;
        double d2;
        switch (i) {
            case 0:
            case 1:
                d = multiplier;
                d2 = 30.0d;
                break;
            case 2:
                d = multiplier;
                d2 = 15.0d;
                break;
            case 3:
                d = multiplier;
                d2 = 9.0d;
                break;
            case 4:
                d = multiplier;
                d2 = 6.0d;
                break;
            case 5:
                d = multiplier;
                d2 = 3.0d;
                break;
            case 6:
                d = multiplier;
                d2 = 2.0d;
                break;
            case 7:
                d = multiplier;
                d2 = 1.0d;
                break;
            case 8:
                d = multiplier;
                d2 = 0.5d;
                break;
            case 9:
                d = multiplier;
                d2 = 0.25d;
                break;
            case 10:
                d = multiplier;
                d2 = 0.1d;
                break;
            case 11:
                d = multiplier;
                d2 = 0.05d;
                break;
            case 12:
                d = multiplier;
                d2 = 0.025d;
                break;
            case 13:
                d = multiplier;
                d2 = 0.0125d;
                break;
            case 14:
                d = multiplier;
                d2 = 0.00625d;
                break;
            case 15:
                d = multiplier;
                d2 = 0.003125d;
                break;
            case 16:
                d = multiplier;
                d2 = 0.0015625d;
                break;
            case 17:
                d = multiplier;
                d2 = 7.8125E-4d;
                break;
            case 18:
                d = multiplier;
                d2 = 3.90625E-4d;
                break;
            case 19:
                d = multiplier;
                d2 = 1.953125E-4d;
                break;
            case 20:
                d = multiplier;
                d2 = 9.765625E-5d;
                break;
            case 21:
                d = multiplier;
                d2 = 4.8828125E-5d;
                break;
            default:
                d = multiplier;
                d2 = 2.44140625E-5d;
                break;
        }
        return d * d2;
    }

    public static void setDefaults() {
        lineColor = ViewCompat.MEASURED_STATE_MASK;
        fontColor = -1;
        backgroundColor = ViewCompat.MEASURED_STATE_MASK;
        lineWidth = 1.0f;
        fontSizeDp = (short) 32;
        DEBUG = false;
        DEBUG2 = false;
    }
}
