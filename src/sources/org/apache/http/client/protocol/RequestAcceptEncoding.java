package org.apache.http.client.protocol;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

/* loaded from: classes3.dex */
public class RequestAcceptEncoding implements HttpRequestInterceptor {
    @Override // org.apache.http.HttpRequestInterceptor
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        if (httpRequest.containsHeader("Accept-Encoding")) {
            return;
        }
        httpRequest.addHeader("Accept-Encoding", "gzip,deflate");
    }
}
