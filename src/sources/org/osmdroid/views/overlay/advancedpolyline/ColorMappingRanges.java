package org.osmdroid.views.overlay.advancedpolyline;

import java.util.Map;
import java.util.SortedMap;

/* loaded from: classes3.dex */
public class ColorMappingRanges extends ColorMappingForScalar {
    private final SortedMap<Float, Integer> mColorRanges;
    private final boolean mStrictComparison;

    public ColorMappingRanges(SortedMap<Float, Integer> sortedMap, boolean z) {
        this.mColorRanges = sortedMap;
        this.mStrictComparison = z;
    }

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMappingForScalar
    protected int computeColor(float f) {
        int i = 0;
        for (Map.Entry<Float, Integer> entry : this.mColorRanges.entrySet()) {
            if (this.mStrictComparison) {
                if (f < entry.getKey().floatValue()) {
                    return entry.getValue().intValue();
                }
            } else if (f <= entry.getKey().floatValue()) {
                return entry.getValue().intValue();
            }
            i++;
        }
        if (i != this.mColorRanges.size()) {
            return 0;
        }
        SortedMap<Float, Integer> sortedMap = this.mColorRanges;
        return sortedMap.get(sortedMap.lastKey()).intValue();
    }
}
