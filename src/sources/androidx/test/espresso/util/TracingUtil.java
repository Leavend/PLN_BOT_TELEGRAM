package androidx.test.espresso.util;

import androidx.test.espresso.core.internal.deps.guava.base.Joiner;
import androidx.test.espresso.core.internal.deps.guava.base.Strings;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public final class TracingUtil {
    private static final String SPAN_NAME_EXCLUDE = "[^0-9A-Za-z._$()\\[\\] /:-]";
    private static final int SPAN_NAME_MAX_LEN = 100;

    private TracingUtil() {
    }

    public static String getClassName(Object obj, String str) {
        String simpleName = obj == null ? null : obj.getClass().getSimpleName();
        if (!Strings.isNullOrEmpty(simpleName)) {
            str = simpleName;
        }
        return Strings.nullToEmpty(str);
    }

    public static String getSpanName(String str, String str2, Object... objArr) {
        String strSanitizeName = sanitizeName(str, SPAN_NAME_EXCLUDE, -1);
        String strSanitizeName2 = sanitizeName(str2, SPAN_NAME_EXCLUDE, -1);
        ArrayList arrayList = new ArrayList();
        if (objArr != null) {
            for (Object obj : objArr) {
                if (obj != null) {
                    String strSanitizeName3 = sanitizeName(obj.toString(), SPAN_NAME_EXCLUDE, -1);
                    if (!strSanitizeName3.isEmpty()) {
                        arrayList.add(strSanitizeName3);
                    }
                }
            }
        }
        if (!strSanitizeName.isEmpty() && !strSanitizeName2.isEmpty()) {
            strSanitizeName = strSanitizeName + ".";
        }
        String str3 = strSanitizeName + strSanitizeName2;
        if (!arrayList.isEmpty()) {
            str3 = str3 + "(" + Joiner.on(", ").join(arrayList) + ")";
        }
        return sanitizeName(str3, null, 100);
    }

    private static String sanitizeName(String str, String str2, int i) {
        if (str == null) {
            return "";
        }
        if (!Strings.isNullOrEmpty(str2)) {
            str = str.replaceAll(str2, "").trim();
        }
        return (i <= 0 || str.length() <= i) ? str : str.substring(0, i).trim();
    }
}
