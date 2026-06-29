package org.osmdroid.views.overlay;

import android.graphics.Canvas;
import android.graphics.Point;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import java.util.List;
import org.osmdroid.api.IMapView;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;

/* loaded from: classes3.dex */
public interface OverlayManager extends List<Overlay> {
    @Override // java.util.List, org.osmdroid.views.overlay.OverlayManager
    void add(int i, Overlay overlay);

    @Override // java.util.List, org.osmdroid.views.overlay.OverlayManager
    Overlay get(int i);

    TilesOverlay getTilesOverlay();

    boolean onCreateOptionsMenu(Menu menu, int i, MapView mapView);

    void onDetach(MapView mapView);

    boolean onDoubleTap(MotionEvent motionEvent, MapView mapView);

    boolean onDoubleTapEvent(MotionEvent motionEvent, MapView mapView);

    boolean onDown(MotionEvent motionEvent, MapView mapView);

    void onDraw(Canvas canvas, MapView mapView);

    void onDraw(Canvas canvas, Projection projection);

    boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView);

    boolean onKeyDown(int i, KeyEvent keyEvent, MapView mapView);

    boolean onKeyUp(int i, KeyEvent keyEvent, MapView mapView);

    boolean onLongPress(MotionEvent motionEvent, MapView mapView);

    boolean onOptionsItemSelected(MenuItem menuItem, int i, MapView mapView);

    void onPause();

    boolean onPrepareOptionsMenu(Menu menu, int i, MapView mapView);

    void onResume();

    boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2, MapView mapView);

    void onShowPress(MotionEvent motionEvent, MapView mapView);

    boolean onSingleTapConfirmed(MotionEvent motionEvent, MapView mapView);

    boolean onSingleTapUp(MotionEvent motionEvent, MapView mapView);

    boolean onSnapToItem(int i, int i2, Point point, IMapView iMapView);

    boolean onTouchEvent(MotionEvent motionEvent, MapView mapView);

    boolean onTrackballEvent(MotionEvent motionEvent, MapView mapView);

    List<Overlay> overlays();

    Iterable<Overlay> overlaysReversed();

    @Override // java.util.List, org.osmdroid.views.overlay.OverlayManager
    Overlay remove(int i);

    @Override // java.util.List, org.osmdroid.views.overlay.OverlayManager
    Overlay set(int i, Overlay overlay);

    void setOptionsMenusEnabled(boolean z);

    void setTilesOverlay(TilesOverlay tilesOverlay);

    @Override // java.util.Collection, java.util.List, org.osmdroid.views.overlay.OverlayManager
    int size();
}
