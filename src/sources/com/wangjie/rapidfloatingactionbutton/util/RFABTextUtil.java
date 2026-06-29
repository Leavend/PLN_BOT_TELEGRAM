package com.wangjie.rapidfloatingactionbutton.util;

import android.content.Context;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes2.dex */
public class RFABTextUtil {
    public static final String TAG = "RFABTextUtil";

    public static float getScaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2sp(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int sp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] objArr) {
        return objArr == null || objArr.length <= 0;
    }

    public static boolean isEmpty(int[] iArr) {
        return iArr == null || iArr.length <= 0;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() <= 0;
    }

    public static boolean isBlank(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().trim().length() <= 0;
    }

    public static boolean isLeast(Object[] objArr, int i) {
        return objArr != null && objArr.length >= i;
    }

    public static boolean isLeast(int[] iArr, int i) {
        return iArr != null && iArr.length >= i;
    }

    public static boolean isEquals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static String trim(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return charSequence.toString().trim();
    }

    public static String pickFirstNotNull(CharSequence... charSequenceArr) {
        if (isEmpty(charSequenceArr)) {
            return null;
        }
        for (CharSequence charSequence : charSequenceArr) {
            if (charSequence != null) {
                return charSequence.toString();
            }
        }
        return null;
    }

    @SafeVarargs
    public static <T> T pickFirstNotNull(Class<T> cls, T... tArr) {
        if (isEmpty(tArr)) {
            return null;
        }
        for (T t : tArr) {
            if (t != null) {
                return t;
            }
        }
        return null;
    }
}
