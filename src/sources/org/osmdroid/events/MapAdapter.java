package org.osmdroid.events;

/* loaded from: classes3.dex */
public abstract class MapAdapter implements MapListener {
    @Override // org.osmdroid.events.MapListener
    public boolean onScroll(ScrollEvent scrollEvent) {
        return false;
    }

    @Override // org.osmdroid.events.MapListener
    public boolean onZoom(ZoomEvent zoomEvent) {
        return false;
    }
}
