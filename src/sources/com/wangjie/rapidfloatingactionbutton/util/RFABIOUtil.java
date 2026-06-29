package com.wangjie.rapidfloatingactionbutton.util;

import android.graphics.Bitmap;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes2.dex */
public class RFABIOUtil {
    public static final String TAG = "RFABIOUtil";

    public static void closeIO(Closeable... closeableArr) throws IOException {
        if (closeableArr == null || closeableArr.length <= 0) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    Log.e(TAG, "close IO ERROR...", e);
                }
            }
        }
    }

    public static void recycleBitmap(Bitmap... bitmapArr) {
        if (RFABTextUtil.isEmpty(bitmapArr)) {
            return;
        }
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }
    }
}
