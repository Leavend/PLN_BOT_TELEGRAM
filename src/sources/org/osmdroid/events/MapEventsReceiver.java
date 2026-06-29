package org.osmdroid.events;

import org.osmdroid.util.GeoPoint;

/* loaded from: classes3.dex */
public interface MapEventsReceiver {
    boolean longPressHelper(GeoPoint geoPoint);

    boolean singleTapConfirmedHelper(GeoPoint geoPoint);
}
