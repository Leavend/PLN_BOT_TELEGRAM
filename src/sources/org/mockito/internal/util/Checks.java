package org.mockito.internal.util;

import java.util.Iterator;

/* loaded from: classes3.dex */
public class Checks {
    public static <T> T checkNotNull(T t, String str) {
        return (T) checkNotNull(t, str, null);
    }

    public static <T> T checkNotNull(T t, String str, String str2) {
        if (t != null) {
            return t;
        }
        String str3 = str + " should not be null";
        if (str2 != null) {
            str3 = str3 + ". " + str2;
        }
        throw new IllegalArgumentException(str3);
    }

    public static <T extends Iterable<?>> T checkItemsNotNull(T t, String str) {
        checkNotNull(t, str);
        Iterator it = t.iterator();
        while (it.hasNext()) {
            checkNotNull(it.next(), "item in " + str);
        }
        return t;
    }
}
