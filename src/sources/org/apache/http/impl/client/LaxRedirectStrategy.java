package org.apache.http.impl.client;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;

/* loaded from: classes3.dex */
public class LaxRedirectStrategy extends DefaultRedirectStrategy {
    private static final String[] REDIRECT_METHODS = {HttpGet.METHOD_NAME, "POST", HttpHead.METHOD_NAME};

    @Override // org.apache.http.impl.client.DefaultRedirectStrategy
    protected boolean isRedirectable(String str) {
        for (String str2 : REDIRECT_METHODS) {
            if (str2.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
