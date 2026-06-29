package com.wangjie.rapidfloatingactionbutton.util;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;

/* loaded from: classes2.dex */
public class RFABViewUtil {
    public static <T extends View> T obtainView(View view, int i) {
        SparseArray sparseArray = (SparseArray) view.getTag();
        if (sparseArray == null) {
            sparseArray = new SparseArray();
            view.setTag(sparseArray);
        }
        T t = (T) sparseArray.get(i);
        if (t != null) {
            return t;
        }
        T t2 = (T) view.findViewById(i);
        sparseArray.put(i, t2);
        return t2;
    }

    public static void setBackgroundDrawable(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    public static void changeBrightness(ImageView imageView, float f) {
        imageView.setColorFilter(getBrightnessMatrixColorFilter(f));
    }

    public static void changeBrightness(Drawable drawable, float f) {
        drawable.setColorFilter(getBrightnessMatrixColorFilter(f));
    }

    private static ColorMatrixColorFilter getBrightnessMatrixColorFilter(float f) {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{1.0f, 0.0f, 0.0f, 0.0f, f, 0.0f, 1.0f, 0.0f, 0.0f, f, 0.0f, 0.0f, 1.0f, 0.0f, f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
        return new ColorMatrixColorFilter(colorMatrix);
    }

    public static ColorStateList createColorStateList(int i, int i2) {
        return createColorStateList(i, i2, -7829368);
    }

    public static ColorStateList createColorStateList(int i, int i2, int i3) {
        return createColorStateList(i, i2, i2, i2, i3);
    }

    public static ColorStateList createColorStateList(int i, int i2, int i3, int i4, int i5) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed, R.attr.state_enabled}, new int[]{R.attr.state_focused, R.attr.state_enabled}, new int[]{R.attr.state_checked, R.attr.state_enabled}, new int[]{R.attr.state_enabled}, new int[0]}, new int[]{i2, i3, i4, i, i5});
    }
}
