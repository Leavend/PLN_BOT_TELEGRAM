package com.wangjie.rapidfloatingactionbutton.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

/* loaded from: classes2.dex */
public class RFABImageUtil {
    public static final String TAG = "RFABImageUtil";

    public static Drawable getResourceDrawableBounded(Context context, int i, int i2) throws Resources.NotFoundException {
        Drawable drawable = null;
        try {
            drawable = context.getResources().getDrawable(i, null);
            drawable.setBounds(0, 0, i2, i2);
            return drawable;
        } catch (Exception e) {
            Log.e(TAG, "", e);
            return drawable;
        }
    }
}
