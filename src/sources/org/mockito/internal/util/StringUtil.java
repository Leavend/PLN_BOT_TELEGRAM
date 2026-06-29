package org.mockito.internal.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class StringUtil {
    private static final Pattern CAPS = Pattern.compile("([A-Z\\d][^A-Z\\d]*)");

    private StringUtil() {
    }

    public static String removeFirstLine(String str) {
        return str.replaceFirst(".*?\n", "");
    }

    public static String join(Object... objArr) {
        return join("\n", Arrays.asList(objArr));
    }

    public static String join(String str, Collection<?> collection) {
        return join(str, "", collection);
    }

    public static String join(String str, String str2, Collection<?> collection) {
        if (collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str);
        Iterator<?> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(str2).append(it.next()).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }

    public static String decamelizeMatcher(String str) {
        if (str.length() == 0) {
            return "<custom argument matcher>";
        }
        String strDecamelizeClassName = decamelizeClassName(str);
        if (strDecamelizeClassName.length() == 0) {
            return "<" + str + ">";
        }
        return "<" + strDecamelizeClassName + ">";
    }

    private static String decamelizeClassName(String str) {
        Matcher matcher = CAPS.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            if (sb.length() == 0) {
                sb.append(matcher.group());
            } else {
                sb.append(" ");
                sb.append(matcher.group().toLowerCase());
            }
        }
        return sb.toString();
    }
}
