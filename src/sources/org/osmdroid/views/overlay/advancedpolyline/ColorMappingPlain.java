package org.osmdroid.views.overlay.advancedpolyline;

/* loaded from: classes3.dex */
public class ColorMappingPlain implements ColorMapping {
    private final int mColorPlain;

    public ColorMappingPlain(int i) {
        this.mColorPlain = i;
    }

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMapping
    public int getColorForIndex(int i) {
        return this.mColorPlain;
    }
}
