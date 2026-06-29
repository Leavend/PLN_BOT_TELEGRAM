package com.wangjie.rapidfloatingactionbutton.util;

import android.R;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;

/* loaded from: classes2.dex */
public class RFABShape {
    public static ShapeDrawable generateCornerShapeDrawable(int i, int i2) {
        return generateCornerShapeDrawable(i, i2, i2, i2, i2);
    }

    public static ShapeDrawable generateCornerShapeDrawable(int i, int i2, int i3, int i4, int i5) {
        float f = i2;
        float f2 = i3;
        float f3 = i4;
        float f4 = i5;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f, f, f2, f2, f3, f3, f4, f4}, null, null));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
        return shapeDrawable;
    }

    public static ShapeDrawable generateCornerStrokeDrawable(int i, float f, int i2) {
        return generateCornerStrokeDrawable(i, f, i2, i2, i2, i2);
    }

    public static ShapeDrawable generateCornerStrokeDrawable(int i, float f, int i2, int i3, int i4, int i5) {
        float f2 = i2;
        float f3 = i3;
        float f4 = i4;
        float f5 = i5;
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{f2, f2, f3, f3, f4, f4, f5, f5}, null, null));
        shapeDrawable.getPaint().setColor(i);
        shapeDrawable.getPaint().setStyle(Paint.Style.STROKE);
        shapeDrawable.getPaint().setAntiAlias(true);
        shapeDrawable.getPaint().setStrokeWidth(f);
        return shapeDrawable;
    }

    public static StateListDrawable selectorClickSimple(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed, R.attr.state_enabled}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    public static StateListDrawable selectorClickColorCornerSimple(int i, int i2, int i3) {
        return selectorClickSimple(generateCornerShapeDrawable(i, i3), generateCornerShapeDrawable(i2, i3));
    }

    public static ShapeDrawable generateBackgroundDrawable(int i) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(i);
        paint.setStyle(Paint.Style.FILL);
        return shapeDrawable;
    }

    public static Drawable selectorCornerRippleCompat(int i, int i2) {
        return selectorCornerRippleCompat(i, i2, 0);
    }

    public static Drawable selectorCornerRippleCompat(int i, int i2, int i3) {
        return new RippleDrawable(RFABViewUtil.createColorStateList(i, i2), generateCornerShapeDrawable(i, i3), null);
    }
}
