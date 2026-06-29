package com.codekidlabs.storagechooser.utils;

import android.content.Context;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.codekidlabs.storagechooser.R;

/* loaded from: classes5.dex */
public class ResourceUtil {
    private Context context;

    public ResourceUtil(Context context) {
        this.context = context;
    }

    public int getColor(int i) {
        return ContextCompat.getColor(this.context, i);
    }

    public int getAppliedAlpha(int i) {
        return ColorUtils.setAlphaComponent(i, 50);
    }

    public int getPrimaryColorWithAlpha() {
        return getAppliedAlpha(getColor(R.color.colorPrimary));
    }
}
