package com.google.maps.android.data.kml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.Marker;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.Renderer;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class KmlRenderer extends Renderer {
    private static final String LOG_TAG = "KmlRenderer";
    private ArrayList<KmlContainer> mContainers;
    private boolean mGroundOverlayImagesDownloaded;
    private final ArrayList<String> mGroundOverlayUrls;
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays;
    private boolean mMarkerIconsDownloaded;

    KmlRenderer(GoogleMap googleMap, Context context) {
        super(googleMap, context);
        this.mGroundOverlayUrls = new ArrayList<>();
        this.mMarkerIconsDownloaded = false;
        this.mGroundOverlayImagesDownloaded = false;
    }

    private static BitmapDescriptor scaleIcon(Bitmap bitmap, Double d) {
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * d.doubleValue()), (int) (bitmap.getHeight() * d.doubleValue()), false));
    }

    private void removePlacemarks(HashMap<? extends Feature, Object> map) {
        removeFeatures(map);
    }

    static boolean getContainerVisibility(KmlContainer kmlContainer, boolean z) {
        return z && (!kmlContainer.hasProperty("visibility") || Integer.parseInt(kmlContainer.getProperty("visibility")) != 0);
    }

    private void removeGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> map) {
        Iterator<GroundOverlay> it = map.values().iterator();
        while (it.hasNext()) {
            it.next().remove();
        }
    }

    private void removeContainers(Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            removePlacemarks(kmlContainer.getPlacemarksHashMap());
            removeGroundOverlays(kmlContainer.getGroundOverlayHashMap());
            removeContainers(kmlContainer.getContainers());
        }
    }

    public void addLayerToMap() {
        setLayerVisibility(true);
        this.mGroundOverlays = getGroundOverlayMap();
        this.mContainers = getContainerList();
        putStyles();
        assignStyleMap(getStyleMaps(), getStylesRenderer());
        addGroundOverlays(this.mGroundOverlays, this.mContainers);
        addContainerGroupToMap(this.mContainers, true);
        addPlacemarksToMap(getAllFeatures());
        if (!this.mGroundOverlayImagesDownloaded) {
            downloadGroundOverlays();
        }
        if (this.mMarkerIconsDownloaded) {
            return;
        }
        downloadMarkerIcons();
    }

    void storeKmlData(HashMap<String, KmlStyle> map, HashMap<String, String> map2, HashMap<KmlPlacemark, Object> map3, ArrayList<KmlContainer> arrayList, HashMap<KmlGroundOverlay, GroundOverlay> map4) {
        storeData(map, map2, map3, arrayList, map4);
    }

    @Override // com.google.maps.android.data.Renderer
    public void setMap(GoogleMap googleMap) {
        removeLayerFromMap();
        super.setMap(googleMap);
        addLayerToMap();
    }

    boolean hasKmlPlacemarks() {
        return hasFeatures();
    }

    Iterable<? extends Feature> getKmlPlacemarks() {
        return getFeatures();
    }

    public boolean hasNestedContainers() {
        return this.mContainers.size() > 0;
    }

    public Iterable<KmlContainer> getNestedContainers() {
        return this.mContainers;
    }

    public Iterable<KmlGroundOverlay> getGroundOverlays() {
        return this.mGroundOverlays.keySet();
    }

    public void removeLayerFromMap() {
        removePlacemarks(getAllFeatures());
        removeGroundOverlays(this.mGroundOverlays);
        if (hasNestedContainers()) {
            removeContainers(getNestedContainers());
        }
        setLayerVisibility(false);
        clearStylesRenderer();
    }

    private void addPlacemarksToMap(HashMap<? extends Feature, Object> map) {
        Iterator<? extends Feature> it = map.keySet().iterator();
        while (it.hasNext()) {
            addFeature(it.next());
        }
    }

    private void addContainerGroupToMap(Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean containerVisibility = getContainerVisibility(kmlContainer, z);
            if (kmlContainer.getStyles() != null) {
                putStyles(kmlContainer.getStyles());
            }
            if (kmlContainer.getStyleMap() != null) {
                super.assignStyleMap(kmlContainer.getStyleMap(), getStylesRenderer());
            }
            addContainerObjectToMap(kmlContainer, containerVisibility);
            if (kmlContainer.hasContainers()) {
                addContainerGroupToMap(kmlContainer.getContainers(), containerVisibility);
            }
        }
    }

    private void addContainerObjectToMap(KmlContainer kmlContainer, boolean z) {
        for (KmlPlacemark kmlPlacemark : kmlContainer.getPlacemarks()) {
            boolean z2 = z && getPlacemarkVisibility(kmlPlacemark);
            if (kmlPlacemark.getGeometry() != null) {
                String id2 = kmlPlacemark.getId();
                Geometry geometry = kmlPlacemark.getGeometry();
                KmlStyle placemarkStyle = getPlacemarkStyle(id2);
                KmlPlacemark kmlPlacemark2 = kmlPlacemark;
                Object objAddKmlPlacemarkToMap = addKmlPlacemarkToMap(kmlPlacemark2, geometry, placemarkStyle, kmlPlacemark2.getInlineStyle(), z2);
                kmlContainer.setPlacemark(kmlPlacemark2, objAddKmlPlacemarkToMap);
                putContainerFeature(objAddKmlPlacemarkToMap, kmlPlacemark);
            }
        }
    }

    private void downloadMarkerIcons() {
        this.mMarkerIconsDownloaded = true;
        Iterator<String> it = getMarkerIconUrls().iterator();
        while (it.hasNext()) {
            new MarkerIconImageDownload(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addIconToMarkers(String str, HashMap<KmlPlacemark, Object> map) {
        for (KmlPlacemark kmlPlacemark : map.keySet()) {
            KmlStyle kmlStyle = getStylesRenderer().get(kmlPlacemark.getId());
            KmlPlacemark kmlPlacemark2 = kmlPlacemark;
            KmlStyle inlineStyle = kmlPlacemark2.getInlineStyle();
            if ("Point".equals(kmlPlacemark.getGeometry().getGeometryType())) {
                boolean z = inlineStyle != null && str.equals(inlineStyle.getIconUrl());
                boolean z2 = kmlStyle != null && str.equals(kmlStyle.getIconUrl());
                if (z) {
                    scaleBitmap(inlineStyle, map, kmlPlacemark2);
                } else if (z2) {
                    scaleBitmap(kmlStyle, map, kmlPlacemark2);
                }
            }
        }
    }

    private void scaleBitmap(KmlStyle kmlStyle, HashMap<KmlPlacemark, Object> map, KmlPlacemark kmlPlacemark) {
        double iconScale = kmlStyle.getIconScale();
        ((Marker) map.get(kmlPlacemark)).setIcon(scaleIcon(getImagesCache().get(kmlStyle.getIconUrl()), Double.valueOf(iconScale)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addContainerGroupIconsToMarkers(String str, Iterable<KmlContainer> iterable) {
        for (KmlContainer kmlContainer : iterable) {
            addIconToMarkers(str, kmlContainer.getPlacemarksHashMap());
            if (kmlContainer.hasContainers()) {
                addContainerGroupIconsToMarkers(str, kmlContainer.getContainers());
            }
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> map, Iterable<KmlContainer> iterable) {
        addGroundOverlays(map);
        for (KmlContainer kmlContainer : iterable) {
            addGroundOverlays(kmlContainer.getGroundOverlayHashMap(), kmlContainer.getContainers());
        }
    }

    private void addGroundOverlays(HashMap<KmlGroundOverlay, GroundOverlay> map) {
        for (KmlGroundOverlay kmlGroundOverlay : map.keySet()) {
            String imageUrl = kmlGroundOverlay.getImageUrl();
            if (imageUrl != null && kmlGroundOverlay.getLatLngBox() != null) {
                if (getImagesCache().get(imageUrl) != null) {
                    addGroundOverlayToMap(imageUrl, this.mGroundOverlays, true);
                } else if (!this.mGroundOverlayUrls.contains(imageUrl)) {
                    this.mGroundOverlayUrls.add(imageUrl);
                }
            }
        }
    }

    private void downloadGroundOverlays() {
        this.mGroundOverlayImagesDownloaded = true;
        Iterator<String> it = this.mGroundOverlayUrls.iterator();
        while (it.hasNext()) {
            new GroundOverlayImageDownload(it.next()).execute(new String[0]);
            it.remove();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addGroundOverlayToMap(String str, HashMap<KmlGroundOverlay, GroundOverlay> map, boolean z) {
        BitmapDescriptor bitmapDescriptorFromBitmap = BitmapDescriptorFactory.fromBitmap(getImagesCache().get(str));
        for (KmlGroundOverlay kmlGroundOverlay : map.keySet()) {
            if (kmlGroundOverlay.getImageUrl().equals(str)) {
                GroundOverlay groundOverlayAttachGroundOverlay = attachGroundOverlay(kmlGroundOverlay.getGroundOverlayOptions().image(bitmapDescriptorFromBitmap));
                if (!z) {
                    groundOverlayAttachGroundOverlay.setVisible(false);
                }
                map.put(kmlGroundOverlay, groundOverlayAttachGroundOverlay);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addGroundOverlayInContainerGroups(String str, Iterable<KmlContainer> iterable, boolean z) {
        for (KmlContainer kmlContainer : iterable) {
            boolean containerVisibility = getContainerVisibility(kmlContainer, z);
            addGroundOverlayToMap(str, kmlContainer.getGroundOverlayHashMap(), containerVisibility);
            if (kmlContainer.hasContainers()) {
                addGroundOverlayInContainerGroups(str, kmlContainer.getContainers(), containerVisibility);
            }
        }
    }

    private class MarkerIconImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mIconUrl;

        public MarkerIconImageDownload(String str) {
            this.mIconUrl = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.mIconUrl).getContent());
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.mIconUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e(KmlRenderer.LOG_TAG, "Image at this URL could not be found " + this.mIconUrl);
                return;
            }
            KmlRenderer.this.putImagesCache(this.mIconUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap()) {
                KmlRenderer kmlRenderer = KmlRenderer.this;
                kmlRenderer.addIconToMarkers(this.mIconUrl, kmlRenderer.getAllFeatures());
                KmlRenderer kmlRenderer2 = KmlRenderer.this;
                kmlRenderer2.addContainerGroupIconsToMarkers(this.mIconUrl, kmlRenderer2.mContainers);
            }
        }
    }

    private class GroundOverlayImageDownload extends AsyncTask<String, Void, Bitmap> {
        private final String mGroundOverlayUrl;

        public GroundOverlayImageDownload(String str) {
            this.mGroundOverlayUrl = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Bitmap doInBackground(String... strArr) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(this.mGroundOverlayUrl).getContent());
            } catch (MalformedURLException unused) {
                return BitmapFactory.decodeFile(this.mGroundOverlayUrl);
            } catch (IOException e) {
                Log.e(KmlRenderer.LOG_TAG, "Image [" + this.mGroundOverlayUrl + "] download issue", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                Log.e(KmlRenderer.LOG_TAG, "Image at this URL could not be found " + this.mGroundOverlayUrl);
                return;
            }
            KmlRenderer.this.putImagesCache(this.mGroundOverlayUrl, bitmap);
            if (KmlRenderer.this.isLayerOnMap()) {
                KmlRenderer kmlRenderer = KmlRenderer.this;
                kmlRenderer.addGroundOverlayToMap(this.mGroundOverlayUrl, kmlRenderer.mGroundOverlays, true);
                KmlRenderer kmlRenderer2 = KmlRenderer.this;
                kmlRenderer2.addGroundOverlayInContainerGroups(this.mGroundOverlayUrl, kmlRenderer2.mContainers, true);
            }
        }
    }
}
