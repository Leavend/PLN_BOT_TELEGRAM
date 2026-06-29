package com.google.maps.android.data;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.Polyline;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.geojson.GeoJsonRenderer;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlRenderer;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes2.dex */
public abstract class Layer {
    private Renderer mRenderer;

    public interface OnFeatureClickListener {
        void onFeatureClick(Feature feature);
    }

    protected void addKMLToMap() throws XmlPullParserException, IOException {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a KmlRenderer");
    }

    protected void addGeoJsonToMap() throws NumberFormatException {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).addLayerToMap();
            return;
        }
        throw new UnsupportedOperationException("Stored renderer is not a GeoJsonRenderer");
    }

    public void removeLayerFromMap() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof GeoJsonRenderer) {
            ((GeoJsonRenderer) renderer).removeLayerFromMap();
        } else if (renderer instanceof KmlRenderer) {
            ((KmlRenderer) renderer).removeLayerFromMap();
        }
    }

    public void setOnFeatureClickListener(final OnFeatureClickListener onFeatureClickListener) {
        GoogleMap map = getMap();
        map.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() { // from class: com.google.maps.android.data.Layer.1
            @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
            public void onPolygonClick(Polygon polygon) {
                if (Layer.this.getFeature(polygon) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polygon));
                } else {
                    if (Layer.this.getContainerFeature(polygon) != null) {
                        onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polygon));
                        return;
                    }
                    OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                    Layer layer = Layer.this;
                    onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(polygon)));
                }
            }
        });
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: com.google.maps.android.data.Layer.2
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                if (Layer.this.getFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(marker));
                    return false;
                }
                if (Layer.this.getContainerFeature(marker) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(marker));
                    return false;
                }
                OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                Layer layer = Layer.this;
                onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(marker)));
                return false;
            }
        });
        map.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() { // from class: com.google.maps.android.data.Layer.3
            @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
            public void onPolylineClick(Polyline polyline) {
                if (Layer.this.getFeature(polyline) != null) {
                    onFeatureClickListener.onFeatureClick(Layer.this.getFeature(polyline));
                } else {
                    if (Layer.this.getContainerFeature(polyline) != null) {
                        onFeatureClickListener.onFeatureClick(Layer.this.getContainerFeature(polyline));
                        return;
                    }
                    OnFeatureClickListener onFeatureClickListener2 = onFeatureClickListener;
                    Layer layer = Layer.this;
                    onFeatureClickListener2.onFeatureClick(layer.getFeature(layer.multiObjectHandler(polyline)));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<?> multiObjectHandler(Object obj) {
        for (Object obj2 : this.mRenderer.getValues()) {
            if (obj2.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) obj2;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    protected void storeRenderer(Renderer renderer) {
        this.mRenderer = renderer;
    }

    public Iterable<? extends Feature> getFeatures() {
        return this.mRenderer.getFeatures();
    }

    public Feature getFeature(Object obj) {
        return this.mRenderer.getFeature(obj);
    }

    public Feature getContainerFeature(Object obj) {
        return this.mRenderer.getContainerFeature(obj);
    }

    protected boolean hasFeatures() {
        return this.mRenderer.hasFeatures();
    }

    protected boolean hasContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).hasNestedContainers();
        }
        return false;
    }

    protected Iterable<KmlContainer> getContainers() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getNestedContainers();
        }
        return null;
    }

    protected Iterable<KmlGroundOverlay> getGroundOverlays() {
        Renderer renderer = this.mRenderer;
        if (renderer instanceof KmlRenderer) {
            return ((KmlRenderer) renderer).getGroundOverlays();
        }
        return null;
    }

    public GoogleMap getMap() {
        return this.mRenderer.getMap();
    }

    public void setMap(GoogleMap googleMap) {
        this.mRenderer.setMap(googleMap);
    }

    public boolean isLayerOnMap() {
        return this.mRenderer.isLayerOnMap();
    }

    protected void addFeature(Feature feature) throws NumberFormatException {
        this.mRenderer.addFeature(feature);
    }

    protected void removeFeature(Feature feature) {
        this.mRenderer.removeFeature(feature);
    }

    public GeoJsonPointStyle getDefaultPointStyle() {
        return this.mRenderer.getDefaultPointStyle();
    }

    public GeoJsonLineStringStyle getDefaultLineStringStyle() {
        return this.mRenderer.getDefaultLineStringStyle();
    }

    public GeoJsonPolygonStyle getDefaultPolygonStyle() {
        return this.mRenderer.getDefaultPolygonStyle();
    }
}
