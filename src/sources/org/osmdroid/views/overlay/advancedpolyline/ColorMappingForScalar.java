package org.osmdroid.views.overlay.advancedpolyline;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class ColorMappingForScalar implements ColorMapping {
    private final List<Integer> mColors = new ArrayList();

    protected abstract int computeColor(float f);

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMapping
    public int getColorForIndex(int i) {
        return this.mColors.get(i).intValue();
    }

    public void add(float f) {
        this.mColors.add(Integer.valueOf(computeColor(f)));
    }

    protected void set(int i, float f) {
        this.mColors.set(i, Integer.valueOf(computeColor(f)));
    }
}
