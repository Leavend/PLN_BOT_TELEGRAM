package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Point;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;

/* loaded from: classes3.dex */
public class DefaultOverlayManager extends AbstractList<Overlay> implements OverlayManager {
    private final CopyOnWriteArrayList<Overlay> mOverlayList;
    private TilesOverlay mTilesOverlay;

    public DefaultOverlayManager(TilesOverlay tilesOverlay) {
        setTilesOverlay(tilesOverlay);
        this.mOverlayList = new CopyOnWriteArrayList<>();
    }

    @Override // java.util.AbstractList, java.util.List, org.osmdroid.views.overlay.OverlayManager
    public Overlay get(int i) {
        return this.mOverlayList.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List, org.osmdroid.views.overlay.OverlayManager
    public int size() {
        return this.mOverlayList.size();
    }

    @Override // java.util.AbstractList, java.util.List, org.osmdroid.views.overlay.OverlayManager
    public void add(int i, Overlay overlay) {
        if (overlay == null) {
            Log.e(IMapView.LOGTAG, "Attempt to add a null overlay to the collection. This is probably a bug and should be reported!", new Exception());
        } else {
            this.mOverlayList.add(i, overlay);
        }
    }

    @Override // java.util.AbstractList, java.util.List, org.osmdroid.views.overlay.OverlayManager
    public Overlay remove(int i) {
        return this.mOverlayList.remove(i);
    }

    @Override // java.util.AbstractList, java.util.List, org.osmdroid.views.overlay.OverlayManager
    public Overlay set(int i, Overlay overlay) {
        if (overlay == null) {
            Log.e(IMapView.LOGTAG, "Attempt to set a null overlay to the collection. This is probably a bug and should be reported!", new Exception());
            return null;
        }
        return this.mOverlayList.set(i, overlay);
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public TilesOverlay getTilesOverlay() {
        return this.mTilesOverlay;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void setTilesOverlay(TilesOverlay tilesOverlay) {
        this.mTilesOverlay = tilesOverlay;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public Iterable<Overlay> overlaysReversed() {
        return new Iterable<Overlay>() { // from class: org.osmdroid.views.overlay.DefaultOverlayManager.1
            private ListIterator<Overlay> bulletProofReverseListIterator() {
                while (true) {
                    try {
                        return DefaultOverlayManager.this.mOverlayList.listIterator(DefaultOverlayManager.this.mOverlayList.size());
                    } catch (IndexOutOfBoundsException unused) {
                    }
                }
            }

            @Override // java.lang.Iterable
            public Iterator<Overlay> iterator() {
                final ListIterator<Overlay> listIteratorBulletProofReverseListIterator = bulletProofReverseListIterator();
                return new Iterator<Overlay>() { // from class: org.osmdroid.views.overlay.DefaultOverlayManager.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return listIteratorBulletProofReverseListIterator.hasPrevious();
                    }

                    @Override // java.util.Iterator
                    public Overlay next() {
                        return (Overlay) listIteratorBulletProofReverseListIterator.previous();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        listIteratorBulletProofReverseListIterator.remove();
                    }
                };
            }
        };
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public List<Overlay> overlays() {
        return this.mOverlayList;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onDraw(Canvas canvas, MapView mapView) {
        onDrawHelper(canvas, mapView, mapView.getProjection());
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onDraw(Canvas canvas, Projection projection) {
        onDrawHelper(canvas, null, projection);
    }

    private void onDrawHelper(Canvas canvas, MapView mapView, Projection projection) {
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        if (tilesOverlay != null) {
            tilesOverlay.protectDisplayedTilesForCache(canvas, projection);
        }
        Iterator<Overlay> it = this.mOverlayList.iterator();
        while (it.hasNext()) {
            Overlay next = it.next();
            if (next != null && next.isEnabled() && (next instanceof TilesOverlay)) {
                ((TilesOverlay) next).protectDisplayedTilesForCache(canvas, projection);
            }
        }
        TilesOverlay tilesOverlay2 = this.mTilesOverlay;
        if (tilesOverlay2 != null && tilesOverlay2.isEnabled()) {
            if (mapView != null) {
                this.mTilesOverlay.draw(canvas, mapView, false);
            } else {
                this.mTilesOverlay.draw(canvas, projection);
            }
        }
        Iterator<Overlay> it2 = this.mOverlayList.iterator();
        while (it2.hasNext()) {
            Overlay next2 = it2.next();
            if (next2 != null && next2.isEnabled()) {
                if (mapView != null) {
                    next2.draw(canvas, mapView, false);
                } else {
                    next2.draw(canvas, projection);
                }
            }
        }
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onDetach(MapView mapView) {
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        if (tilesOverlay != null) {
            tilesOverlay.onDetach(mapView);
        }
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            it.next().onDetach(mapView);
        }
        clear();
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onPause() {
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        if (tilesOverlay != null) {
            tilesOverlay.onPause();
        }
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onResume() {
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        if (tilesOverlay != null) {
            tilesOverlay.onResume();
        }
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onKeyDown(int i, KeyEvent keyEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onKeyDown(i, keyEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onKeyUp(int i, KeyEvent keyEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onKeyUp(i, keyEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onTouchEvent(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onTouchEvent(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onTrackballEvent(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onTrackballEvent(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onSnapToItem(int i, int i2, Point point, IMapView iMapView) {
        for (Object obj : overlaysReversed()) {
            if ((obj instanceof Overlay.Snappable) && ((Overlay.Snappable) obj).onSnapToItem(i, i2, point, iMapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onDoubleTap(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onDoubleTap(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onDoubleTapEvent(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onDoubleTapEvent(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onSingleTapConfirmed(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onDown(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onDown(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onFling(motionEvent, motionEvent2, f, f2, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onLongPress(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onLongPress(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onScroll(motionEvent, motionEvent2, f, f2, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void onShowPress(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            it.next().onShowPress(motionEvent, mapView);
        }
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onSingleTapUp(MotionEvent motionEvent, MapView mapView) {
        Iterator<Overlay> it = overlaysReversed().iterator();
        while (it.hasNext()) {
            if (it.next().onSingleTapUp(motionEvent, mapView)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public void setOptionsMenusEnabled(boolean z) {
        Iterator<Overlay> it = this.mOverlayList.iterator();
        while (it.hasNext()) {
            Object obj = (Overlay) it.next();
            if (obj instanceof IOverlayMenuProvider) {
                IOverlayMenuProvider iOverlayMenuProvider = (IOverlayMenuProvider) obj;
                if (iOverlayMenuProvider.isOptionsMenuEnabled()) {
                    iOverlayMenuProvider.setOptionsMenuEnabled(z);
                }
            }
        }
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onCreateOptionsMenu(Menu menu, int i, MapView mapView) {
        boolean zOnCreateOptionsMenu = true;
        for (Object obj : overlaysReversed()) {
            if (obj instanceof IOverlayMenuProvider) {
                IOverlayMenuProvider iOverlayMenuProvider = (IOverlayMenuProvider) obj;
                if (iOverlayMenuProvider.isOptionsMenuEnabled()) {
                    zOnCreateOptionsMenu &= iOverlayMenuProvider.onCreateOptionsMenu(menu, i, mapView);
                }
            }
        }
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        return (tilesOverlay == null || !tilesOverlay.isOptionsMenuEnabled()) ? zOnCreateOptionsMenu : zOnCreateOptionsMenu & this.mTilesOverlay.onCreateOptionsMenu(menu, i, mapView);
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onPrepareOptionsMenu(Menu menu, int i, MapView mapView) {
        for (Object obj : overlaysReversed()) {
            if (obj instanceof IOverlayMenuProvider) {
                IOverlayMenuProvider iOverlayMenuProvider = (IOverlayMenuProvider) obj;
                if (iOverlayMenuProvider.isOptionsMenuEnabled()) {
                    iOverlayMenuProvider.onPrepareOptionsMenu(menu, i, mapView);
                }
            }
        }
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        if (tilesOverlay == null || !tilesOverlay.isOptionsMenuEnabled()) {
            return true;
        }
        this.mTilesOverlay.onPrepareOptionsMenu(menu, i, mapView);
        return true;
    }

    @Override // org.osmdroid.views.overlay.OverlayManager
    public boolean onOptionsItemSelected(MenuItem menuItem, int i, MapView mapView) {
        for (Object obj : overlaysReversed()) {
            if (obj instanceof IOverlayMenuProvider) {
                IOverlayMenuProvider iOverlayMenuProvider = (IOverlayMenuProvider) obj;
                if (iOverlayMenuProvider.isOptionsMenuEnabled() && iOverlayMenuProvider.onOptionsItemSelected(menuItem, i, mapView)) {
                    return true;
                }
            }
        }
        TilesOverlay tilesOverlay = this.mTilesOverlay;
        return tilesOverlay != null && tilesOverlay.isOptionsMenuEnabled() && this.mTilesOverlay.onOptionsItemSelected(menuItem, i, mapView);
    }
}
