package org.osmdroid.tileprovider.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import org.osmdroid.api.IMapView;

/* loaded from: classes3.dex */
public class ManifestUtil {
    public static String retrieveKey(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData == null) {
                Log.i(IMapView.LOGTAG, "Key %s not found in manifest" + str);
                return "";
            }
            String string = applicationInfo.metaData.getString(str);
            if (string == null) {
                Log.i(IMapView.LOGTAG, "Key %s not found in manifest" + str);
                return "";
            }
            return string.trim();
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(IMapView.LOGTAG, "Key %s not found in manifest" + str);
            return "";
        }
    }
}
