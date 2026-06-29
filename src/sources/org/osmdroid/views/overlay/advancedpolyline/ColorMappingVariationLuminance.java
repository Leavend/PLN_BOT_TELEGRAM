package org.osmdroid.views.overlay.advancedpolyline;

/* loaded from: classes3.dex */
public class ColorMappingVariationLuminance extends ColorMappingVariation {
    private float mHue;
    private float mSaturation;

    public ColorMappingVariationLuminance(float f, float f2, float f3, float f4, float f5, float f6) {
        float fConstrain = ColorHelper.constrain(f3, 0.0f, 1.0f);
        float fConstrain2 = ColorHelper.constrain(f4, 0.0f, 1.0f);
        this.mHue = ColorHelper.constrain(f5, 0.0f, 360.0f);
        this.mSaturation = ColorHelper.constrain(f6, 0.0f, 1.0f);
        init(f, f2, fConstrain, fConstrain2);
    }

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMappingVariation
    protected float getHue(float f) {
        return this.mHue;
    }

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMappingVariation
    protected float getSaturation(float f) {
        return this.mSaturation;
    }

    @Override // org.osmdroid.views.overlay.advancedpolyline.ColorMappingVariation
    protected float getLuminance(float f) {
        return mapScalar(f);
    }
}
