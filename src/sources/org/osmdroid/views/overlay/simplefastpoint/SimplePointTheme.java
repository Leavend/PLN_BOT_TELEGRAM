package org.osmdroid.views.overlay.simplefastpoint;

import java.util.Iterator;
import java.util.List;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay;

/* loaded from: classes3.dex */
public final class SimplePointTheme implements SimpleFastPointOverlay.PointAdapter {
    private boolean mLabelled;
    private final List<IGeoPoint> mPoints;
    private boolean mStyled;

    public SimplePointTheme(List<IGeoPoint> list) {
        this(list, list.size() != 0 && (list.get(0) instanceof LabelledGeoPoint), list.size() != 0 && (list.get(0) instanceof StyledLabelledGeoPoint));
    }

    public SimplePointTheme(List<IGeoPoint> list, boolean z) {
        this(list, z, false);
    }

    public SimplePointTheme(List<IGeoPoint> list, boolean z, boolean z2) {
        this.mPoints = list;
        this.mLabelled = z;
        this.mStyled = z2;
    }

    @Override // org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay.PointAdapter
    public int size() {
        return this.mPoints.size();
    }

    @Override // org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay.PointAdapter
    public IGeoPoint get(int i) {
        return this.mPoints.get(i);
    }

    @Override // org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay.PointAdapter
    public boolean isLabelled() {
        return this.mLabelled;
    }

    @Override // org.osmdroid.views.overlay.simplefastpoint.SimpleFastPointOverlay.PointAdapter
    public boolean isStyled() {
        return this.mStyled;
    }

    @Override // java.lang.Iterable
    public Iterator<IGeoPoint> iterator() {
        return this.mPoints.iterator();
    }
}
