package com.google.maps.android.data.kml;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.motion.widget.Key;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.data.Geometry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
class KmlFeatureParser {
    private static final String BOUNDARY_REGEX = "outerBoundaryIs|innerBoundaryIs";
    private static final String COMPASS_REGEX = "north|south|east|west";
    private static final String EXTENDED_DATA = "ExtendedData";
    private static final String GEOMETRY_REGEX = "Point|LineString|Polygon|MultiGeometry";
    private static final int LATITUDE_INDEX = 1;
    private static final int LONGITUDE_INDEX = 0;
    private static final String PROPERTY_REGEX = "name|description|drawOrder|visibility|open|address|phoneNumber";
    private static final String STYLE_TAG = "Style";
    private static final String STYLE_URL_TAG = "styleUrl";

    KmlFeatureParser() {
    }

    static KmlPlacemark createPlacemark(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HashMap map = new HashMap();
        int eventType = xmlPullParser.getEventType();
        Geometry geometryCreateGeometry = null;
        String strNextText = null;
        KmlStyle kmlStyleCreateStyle = null;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("Placemark")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals(STYLE_URL_TAG)) {
                        strNextText = xmlPullParser.nextText();
                    } else if (xmlPullParser.getName().matches(GEOMETRY_REGEX)) {
                        geometryCreateGeometry = createGeometry(xmlPullParser, xmlPullParser.getName());
                    } else if (xmlPullParser.getName().matches(PROPERTY_REGEX)) {
                        map.put(xmlPullParser.getName(), xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(EXTENDED_DATA)) {
                        map.putAll(setExtendedDataProperties(xmlPullParser));
                    } else if (xmlPullParser.getName().equals(STYLE_TAG)) {
                        kmlStyleCreateStyle = KmlStyleParser.createStyle(xmlPullParser);
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPlacemark(geometryCreateGeometry, strNextText, kmlStyleCreateStyle, map);
            }
        }
    }

    static KmlGroundOverlay createGroundOverlay(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, NumberFormatException {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        int eventType = xmlPullParser.getEventType();
        String imageUrl = null;
        float rotation = 0.0f;
        int i = 1;
        float f = 0.0f;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("GroundOverlay")) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().equals("Icon")) {
                        imageUrl = getImageUrl(xmlPullParser);
                    } else if (xmlPullParser.getName().equals("drawOrder")) {
                        f = Float.parseFloat(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals("visibility")) {
                        i = Integer.parseInt(xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().equals(EXTENDED_DATA)) {
                        map.putAll(setExtendedDataProperties(xmlPullParser));
                    } else if (xmlPullParser.getName().equals(Key.ROTATION)) {
                        rotation = getRotation(xmlPullParser);
                    } else if (xmlPullParser.getName().matches(PROPERTY_REGEX) || xmlPullParser.getName().equals(TypedValues.Custom.S_COLOR)) {
                        map.put(xmlPullParser.getName(), xmlPullParser.nextText());
                    } else if (xmlPullParser.getName().matches(COMPASS_REGEX)) {
                        map2.put(xmlPullParser.getName(), Double.valueOf(Double.parseDouble(xmlPullParser.nextText())));
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlGroundOverlay(imageUrl, createLatLngBounds((Double) map2.get("north"), (Double) map2.get("south"), (Double) map2.get("east"), (Double) map2.get("west")), f, i, map, rotation);
            }
        }
    }

    private static float getRotation(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return -Float.parseFloat(xmlPullParser.nextText());
    }

    private static String getImageUrl(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Icon")) {
                return null;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                return xmlPullParser.nextText();
            }
            eventType = xmlPullParser.next();
        }
    }

    private static Geometry createGeometry(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(str)) {
                return null;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Point")) {
                    return createPoint(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("LineString")) {
                    return createLineString(xmlPullParser);
                }
                if (xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                    return createPolygon(xmlPullParser);
                }
                if (xmlPullParser.getName().equals("MultiGeometry")) {
                    return createMultiGeometry(xmlPullParser);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static HashMap<String, String> setExtendedDataProperties(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        HashMap<String, String> map = new HashMap<>();
        int eventType = xmlPullParser.getEventType();
        String attributeValue = null;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(EXTENDED_DATA)) {
                return map;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("Data")) {
                    attributeValue = xmlPullParser.getAttributeValue(null, "name");
                } else if (xmlPullParser.getName().equals("value") && attributeValue != null) {
                    map.put(attributeValue, xmlPullParser.nextText());
                    attributeValue = null;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static KmlPoint createPoint(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        int eventType = xmlPullParser.getEventType();
        LatLng latLngConvertToLatLng = null;
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("Point")) {
                if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                    latLngConvertToLatLng = convertToLatLng(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPoint(latLngConvertToLatLng);
            }
        }
    }

    private static KmlLineString createLineString(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals("LineString")) {
                if (eventType == 2 && xmlPullParser.getName().equals("coordinates")) {
                    arrayList = convertToLatLngArray(xmlPullParser.nextText());
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlLineString(arrayList);
            }
        }
    }

    private static KmlPolygon createPolygon(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Boolean boolValueOf = false;
        ArrayList<LatLng> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType != 3 || !xmlPullParser.getName().equals(KmlPolygon.GEOMETRY_TYPE)) {
                if (eventType == 2) {
                    if (xmlPullParser.getName().matches(BOUNDARY_REGEX)) {
                        boolValueOf = Boolean.valueOf(xmlPullParser.getName().equals("outerBoundaryIs"));
                    } else if (xmlPullParser.getName().equals("coordinates")) {
                        if (boolValueOf.booleanValue()) {
                            arrayList = convertToLatLngArray(xmlPullParser.nextText());
                        } else {
                            arrayList2.add(convertToLatLngArray(xmlPullParser.nextText()));
                        }
                    }
                }
                eventType = xmlPullParser.next();
            } else {
                return new KmlPolygon(arrayList, arrayList2);
            }
        }
    }

    private static KmlMultiGeometry createMultiGeometry(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        ArrayList arrayList = new ArrayList();
        int next = xmlPullParser.next();
        while (true) {
            if (next != 3 || !xmlPullParser.getName().equals("MultiGeometry")) {
                if (next == 2 && xmlPullParser.getName().matches(GEOMETRY_REGEX)) {
                    arrayList.add(createGeometry(xmlPullParser, xmlPullParser.getName()));
                }
                next = xmlPullParser.next();
            } else {
                return new KmlMultiGeometry(arrayList);
            }
        }
    }

    private static ArrayList<LatLng> convertToLatLngArray(String str) {
        ArrayList<LatLng> arrayList = new ArrayList<>();
        for (String str2 : str.trim().split("(\\s+)")) {
            arrayList.add(convertToLatLng(str2));
        }
        return arrayList;
    }

    private static LatLng convertToLatLng(String str) {
        String[] strArrSplit = str.split(",");
        return new LatLng(Double.valueOf(Double.parseDouble(strArrSplit[1])).doubleValue(), Double.valueOf(Double.parseDouble(strArrSplit[0])).doubleValue());
    }

    private static LatLngBounds createLatLngBounds(Double d, Double d2, Double d3, Double d4) {
        return new LatLngBounds(new LatLng(d2.doubleValue(), d4.doubleValue()), new LatLng(d.doubleValue(), d3.doubleValue()));
    }
}
