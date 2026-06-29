package com.kdownloader.httpclient;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.internal.DownloadRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DefaultHttpClient.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\t\u001a\u00020\u0001H\u0016J\b\u0010\n\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u001a\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00130\u0011H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0012H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/kdownloader/httpclient/DefaultHttpClient;", "Lcom/kdownloader/httpclient/HttpClient;", "()V", "connection", "Ljava/net/URLConnection;", "addHeaders", "", "req", "Lcom/kdownloader/internal/DownloadRequest;", "clone", "close", "connect", "getContentLength", "", "getErrorStream", "Ljava/io/InputStream;", "getHeaderFields", "", "", "", "getInputStream", "getResponseCode", "", "getResponseHeader", "name", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DefaultHttpClient implements HttpClient {
    private URLConnection connection;

    @Override // com.kdownloader.httpclient.HttpClient
    public void close() {
    }

    @Override // com.kdownloader.httpclient.HttpClient
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public HttpClient m6560clone() {
        return new DefaultHttpClient();
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public void connect(DownloadRequest req) throws IOException {
        Intrinsics.checkNotNullParameter(req, "req");
        String str = String.format(Locale.ENGLISH, "bytes=%d-", Long.valueOf(req.getDownloadedBytes()));
        Intrinsics.checkNotNullExpressionValue(str, "format(\n            Loca…downloadedBytes\n        )");
        URLConnection uRLConnectionOpenConnection = new URL(req.getUrl()).openConnection();
        this.connection = uRLConnectionOpenConnection;
        if (uRLConnectionOpenConnection != null) {
            uRLConnectionOpenConnection.setReadTimeout(req.getReadTimeOut());
            uRLConnectionOpenConnection.setConnectTimeout(req.getConnectTimeOut());
            uRLConnectionOpenConnection.addRequestProperty("Range", str);
            uRLConnectionOpenConnection.addRequestProperty("User-Agent", req.getUserAgent());
            addHeaders(req);
            uRLConnectionOpenConnection.connect();
        }
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public int getResponseCode() throws IOException {
        URLConnection uRLConnection = this.connection;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            return 0;
        }
        Intrinsics.checkNotNull(uRLConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        return ((HttpURLConnection) uRLConnection).getResponseCode();
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public InputStream getInputStream() throws IOException {
        URLConnection uRLConnection = this.connection;
        if (uRLConnection != null) {
            return uRLConnection.getInputStream();
        }
        return null;
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public long getContentLength() {
        URLConnection uRLConnection = this.connection;
        String headerField = uRLConnection != null ? uRLConnection.getHeaderField("Content-Length") : null;
        if (headerField != null) {
            return Long.parseLong(headerField);
        }
        return -1L;
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public String getResponseHeader(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        URLConnection uRLConnection = this.connection;
        String headerField = uRLConnection != null ? uRLConnection.getHeaderField(name) : null;
        return headerField == null ? "" : headerField;
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public Map<String, List<String>> getHeaderFields() {
        URLConnection uRLConnection = this.connection;
        Map<String, List<String>> headerFields = uRLConnection != null ? uRLConnection.getHeaderFields() : null;
        return headerFields == null ? MapsKt.emptyMap() : headerFields;
    }

    @Override // com.kdownloader.httpclient.HttpClient
    public InputStream getErrorStream() {
        URLConnection uRLConnection = this.connection;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            return null;
        }
        Intrinsics.checkNotNull(uRLConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
        return ((HttpURLConnection) uRLConnection).getErrorStream();
    }

    private final void addHeaders(DownloadRequest req) {
        HashMap<String, List<String>> headers$library_release = req.getHeaders$library_release();
        if (headers$library_release != null) {
            Set<Map.Entry<String, List<String>>> setEntrySet = headers$library_release.entrySet();
            Intrinsics.checkNotNullExpressionValue(setEntrySet, "headers.entries");
            for (Map.Entry<String, List<String>> entry : setEntrySet) {
                String key = entry.getKey();
                for (String str : entry.getValue()) {
                    URLConnection uRLConnection = this.connection;
                    if (uRLConnection != null) {
                        uRLConnection.addRequestProperty(key, str);
                    }
                }
            }
        }
    }
}
