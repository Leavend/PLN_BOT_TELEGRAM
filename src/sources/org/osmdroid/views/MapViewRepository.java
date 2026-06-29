package org.osmdroid.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.osmdroid.library.R;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;

/* loaded from: classes3.dex */
public class MapViewRepository {
    private Drawable mDefaultMarkerIcon;
    private MarkerInfoWindow mDefaultMarkerInfoWindow;
    private BasicInfoWindow mDefaultPolygonInfoWindow;
    private BasicInfoWindow mDefaultPolylineInfoWindow;
    private final Set<InfoWindow> mInfoWindowList = new HashSet();
    private MapView mMapView;

    public MapViewRepository(MapView mapView) {
        this.mMapView = mapView;
    }

    public void add(InfoWindow infoWindow) {
        this.mInfoWindowList.add(infoWindow);
    }

    public void onDetach() {
        synchronized (this.mInfoWindowList) {
            Iterator<InfoWindow> it = this.mInfoWindowList.iterator();
            while (it.hasNext()) {
                it.next().onDetach();
            }
            this.mInfoWindowList.clear();
        }
        this.mMapView = null;
        this.mDefaultMarkerInfoWindow = null;
        this.mDefaultPolylineInfoWindow = null;
        this.mDefaultPolygonInfoWindow = null;
        this.mDefaultMarkerIcon = null;
    }

    public MarkerInfoWindow getDefaultMarkerInfoWindow() {
        if (this.mDefaultMarkerInfoWindow == null) {
            this.mDefaultMarkerInfoWindow = new MarkerInfoWindow(R.layout.bonuspack_bubble, this.mMapView);
        }
        return this.mDefaultMarkerInfoWindow;
    }

    public BasicInfoWindow getDefaultPolylineInfoWindow() {
        if (this.mDefaultPolylineInfoWindow == null) {
            this.mDefaultPolylineInfoWindow = new BasicInfoWindow(R.layout.bonuspack_bubble, this.mMapView);
        }
        return this.mDefaultPolylineInfoWindow;
    }

    public BasicInfoWindow getDefaultPolygonInfoWindow() {
        if (this.mDefaultPolygonInfoWindow == null) {
            this.mDefaultPolygonInfoWindow = new BasicInfoWindow(R.layout.bonuspack_bubble, this.mMapView);
        }
        return this.mDefaultPolygonInfoWindow;
    }

    public Drawable getDefaultMarkerIcon() {
        MapView mapView;
        Context context;
        if (this.mDefaultMarkerIcon == null && (mapView = this.mMapView) != null && (context = mapView.getContext()) != null) {
            this.mDefaultMarkerIcon = context.getResources().getDrawable(R.drawable.marker_default);
        }
        return this.mDefaultMarkerIcon;
    }
}
