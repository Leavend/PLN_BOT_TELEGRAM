package org.osmdroid.views.overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import java.util.Iterator;
import java.util.List;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.TileSystem;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public class FolderOverlay extends Overlay {
    protected String mDescription;
    protected String mName;
    protected OverlayManager mOverlayManager;

    @Deprecated
    public FolderOverlay(Context context) {
        this();
    }

    public FolderOverlay() {
        this.mOverlayManager = new DefaultOverlayManager(null);
        this.mName = "";
        this.mDescription = "";
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public List<Overlay> getItems() {
        return this.mOverlayManager;
    }

    public boolean add(Overlay overlay) {
        boolean zAdd = this.mOverlayManager.add(overlay);
        if (zAdd) {
            recalculateBounds();
        }
        return zAdd;
    }

    private void recalculateBounds() {
        Iterator<Overlay> it = this.mOverlayManager.iterator();
        double dMin = Double.MAX_VALUE;
        double dMin2 = Double.MAX_VALUE;
        double dMax = -1.7976931348623157E308d;
        double dMax2 = -1.7976931348623157E308d;
        while (it.hasNext()) {
            BoundingBox bounds = it.next().getBounds();
            dMin = Math.min(dMin, bounds.getLatSouth());
            dMin2 = Math.min(dMin2, bounds.getLonWest());
            dMax = Math.max(dMax, bounds.getLatNorth());
            dMax2 = Math.max(dMax2, bounds.getLonEast());
        }
        if (dMin == Double.MAX_VALUE) {
            TileSystem tileSystem = MapView.getTileSystem();
            this.mBounds = new BoundingBox(tileSystem.getMaxLatitude(), tileSystem.getMaxLongitude(), tileSystem.getMinLatitude(), tileSystem.getMinLongitude());
        } else {
            this.mBounds = new BoundingBox(dMax, dMax2, dMin, dMin2);
        }
    }

    public boolean remove(Overlay overlay) {
        boolean zRemove = this.mOverlayManager.remove(overlay);
        if (zRemove) {
            recalculateBounds();
        }
        return zRemove;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, Projection projection) {
        this.mOverlayManager.onDraw(canvas, projection);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void draw(Canvas canvas, MapView mapView, boolean z) {
        if (z) {
            return;
        }
        this.mOverlayManager.onDraw(canvas, mapView);
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onSingleTapUp(MotionEvent motionEvent, MapView mapView) {
        if (isEnabled()) {
            return this.mOverlayManager.onSingleTapUp(motionEvent, mapView);
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView) {
        if (isEnabled()) {
            return this.mOverlayManager.onSingleTapConfirmed(motionEvent, mapView);
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onLongPress(MotionEvent motionEvent, MapView mapView) {
        if (isEnabled()) {
            return this.mOverlayManager.onLongPress(motionEvent, mapView);
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
        if (isEnabled()) {
            return this.mOverlayManager.onTouchEvent(motionEvent, mapView);
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public boolean onDoubleTap(MotionEvent motionEvent, MapView mapView) {
        if (isEnabled()) {
            return this.mOverlayManager.onDoubleTap(motionEvent, mapView);
        }
        return false;
    }

    public void closeAllInfoWindows() {
        for (Overlay overlay : this.mOverlayManager) {
            if (overlay instanceof FolderOverlay) {
                ((FolderOverlay) overlay).closeAllInfoWindows();
            } else if (overlay instanceof OverlayWithIW) {
                ((OverlayWithIW) overlay).closeInfoWindow();
            }
        }
    }

    @Override // org.osmdroid.views.overlay.Overlay
    public void onDetach(MapView mapView) {
        OverlayManager overlayManager = this.mOverlayManager;
        if (overlayManager != null) {
            overlayManager.onDetach(mapView);
        }
        this.mOverlayManager = null;
    }
}
