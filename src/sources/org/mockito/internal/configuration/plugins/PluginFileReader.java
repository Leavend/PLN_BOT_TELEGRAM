package org.mockito.internal.configuration.plugins;

import java.io.InputStream;
import java.util.Iterator;
import org.mockito.internal.util.io.IOUtil;

/* loaded from: classes3.dex */
class PluginFileReader {
    PluginFileReader() {
    }

    String readPluginClass(InputStream inputStream) {
        Iterator<String> it = IOUtil.readLines(inputStream).iterator();
        while (it.hasNext()) {
            String strStripCommentAndWhitespace = stripCommentAndWhitespace(it.next());
            if (strStripCommentAndWhitespace.length() > 0) {
                return strStripCommentAndWhitespace;
            }
        }
        return null;
    }

    private static String stripCommentAndWhitespace(String str) {
        int iIndexOf = str.indexOf(35);
        if (iIndexOf != -1) {
            str = str.substring(0, iIndexOf);
        }
        return str.trim();
    }
}
