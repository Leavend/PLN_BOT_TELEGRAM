package org.mockito.internal.matchers.text;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public class ValuePrinter {
    private ValuePrinter() {
    }

    public static String print(final Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof String) {
            return "\"" + obj.toString() + Typography.quote;
        }
        if (obj instanceof Character) {
            return printChar(((Character) obj).charValue());
        }
        if (obj instanceof Long) {
            return obj + "L";
        }
        if (obj instanceof Double) {
            return obj + "d";
        }
        if (obj instanceof Float) {
            return obj + "f";
        }
        if (obj instanceof Short) {
            return "(short) " + obj;
        }
        if (obj instanceof Byte) {
            return String.format("(byte) 0x%02X", (Byte) obj);
        }
        if (obj instanceof Map) {
            return printMap((Map) obj);
        }
        if (obj.getClass().isArray()) {
            return printValues("[", ", ", "]", new Iterator<Object>() { // from class: org.mockito.internal.matchers.text.ValuePrinter.1
                private int currentIndex = 0;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.currentIndex < Array.getLength(obj);
                }

                @Override // java.util.Iterator
                public Object next() {
                    Object obj2 = obj;
                    int i = this.currentIndex;
                    this.currentIndex = i + 1;
                    return Array.get(obj2, i);
                }

                @Override // java.util.Iterator
                public void remove() {
                    throw new UnsupportedOperationException("cannot remove items from an array");
                }
            });
        }
        if (obj instanceof FormattedText) {
            return ((FormattedText) obj).getText();
        }
        return descriptionOf(obj);
    }

    private static String printMap(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<?, ?> next = it.next();
            sb.append(print(next.getKey())).append(" = ").append(print(next.getValue()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        return "{" + sb.toString() + "}";
    }

    public static String printValues(String str, String str2, String str3, Iterator<?> it) {
        if (str == null) {
            str = "(";
        }
        if (str2 == null) {
            str2 = ",";
        }
        if (str3 == null) {
            str3 = ")";
        }
        StringBuilder sb = new StringBuilder(str);
        while (it.hasNext()) {
            sb.append(print(it.next()));
            if (it.hasNext()) {
                sb.append(str2);
            }
        }
        return sb.append(str3).toString();
    }

    private static String printChar(char c) {
        StringBuilder sb = new StringBuilder("'");
        if (c == '\t') {
            sb.append("\\t");
        } else if (c == '\n') {
            sb.append("\\n");
        } else if (c == '\r') {
            sb.append("\\r");
        } else if (c == '\"') {
            sb.append("\\\"");
        } else {
            sb.append(c);
        }
        sb.append('\'');
        return sb.toString();
    }

    private static String descriptionOf(Object obj) {
        try {
            return String.valueOf(obj);
        } catch (Exception unused) {
            return obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
        }
    }
}
