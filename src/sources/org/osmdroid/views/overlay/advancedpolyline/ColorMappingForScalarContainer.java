package org.osmdroid.views.overlay.advancedpolyline;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class ColorMappingForScalarContainer {
    private final ColorMappingForScalar mInnerMapping;
    private final List<Float> mScalars = new ArrayList();
    private float mScalarMin = Float.MAX_VALUE;
    private float mScalarMax = Float.MIN_VALUE;

    public ColorMappingForScalarContainer(ColorMappingForScalar colorMappingForScalar) {
        this.mInnerMapping = colorMappingForScalar;
    }

    public ColorMappingForScalar getMappingForScalar() {
        return this.mInnerMapping;
    }

    public int size() {
        return this.mScalars.size();
    }

    public float getScalarMin() {
        return this.mScalarMin;
    }

    public float getScalarMax() {
        return this.mScalarMax;
    }

    public void add(float f) {
        this.mInnerMapping.add(f);
        this.mScalars.add(Float.valueOf(f));
        if (this.mScalarMin > f) {
            this.mScalarMin = f;
        }
        if (this.mScalarMax < f) {
            this.mScalarMax = f;
        }
    }

    public void refresh() {
        Iterator<Float> it = this.mScalars.iterator();
        int i = 0;
        while (it.hasNext()) {
            this.mInnerMapping.set(i, it.next().floatValue());
            i++;
        }
    }
}
