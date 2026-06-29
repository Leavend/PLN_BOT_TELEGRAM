package org.osmdroid.tileprovider.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;

/* loaded from: classes3.dex */
public class CloudmadeUtil {
    private static final String CLOUDMADE_ID = "CLOUDMADE_ID";
    private static final String CLOUDMADE_KEY = "CLOUDMADE_KEY";
    private static final String CLOUDMADE_TOKEN = "CLOUDMADE_TOKEN";
    public static boolean DEBUGMODE = false;
    private static String mAndroidId = "android_id";
    private static String mKey = "";
    private static SharedPreferences.Editor mPreferenceEditor = null;
    private static String mToken = "";

    public static void retrieveCloudmadeKey(Context context) {
        mAndroidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        mKey = ManifestUtil.retrieveKey(context, CLOUDMADE_KEY);
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mPreferenceEditor = defaultSharedPreferences.edit();
        if (defaultSharedPreferences.getString(CLOUDMADE_ID, "").equals(mAndroidId)) {
            String string = defaultSharedPreferences.getString(CLOUDMADE_TOKEN, "");
            mToken = string;
            if (string.length() > 0) {
                mPreferenceEditor = null;
                return;
            }
            return;
        }
        mPreferenceEditor.putString(CLOUDMADE_ID, mAndroidId);
        mPreferenceEditor.commit();
    }

    public static String getCloudmadeKey() {
        return mKey;
    }

    public static void setCloudmadeKey(String str) {
        mKey = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0133 A[EXC_TOP_SPLITTER, PHI: r3
      0x0133: PHI (r3v8 ??) = (r3v7 ??), (r3v24 ??) binds: [B:62:0x016a, B:44:0x0131] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x017a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0162 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[Catch: all -> 0x0180, SYNTHETIC, TRY_ENTER, TryCatch #0 {, blocks: (B:6:0x000d, B:8:0x0015, B:41:0x0129, B:43:0x012e, B:45:0x0133, B:66:0x0170, B:68:0x0175, B:70:0x017a, B:71:0x017d, B:59:0x0162, B:61:0x0167, B:72:0x017e), top: B:87:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0167 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0175 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0170 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v25, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.io.InputStreamReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getCloudmadeToken() {
        /*
            Method dump skipped, instructions count: 390
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.osmdroid.tileprovider.util.CloudmadeUtil.getCloudmadeToken():java.lang.String");
    }
}
