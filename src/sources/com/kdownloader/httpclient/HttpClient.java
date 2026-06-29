package com.kdownloader.httpclient;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.internal.DownloadRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;

/* compiled from: HttpClient.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0000H&J\b\u0010\u0003\u001a\u00020\u0004H&J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\n\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u001a\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000f0\rH&J\n\u0010\u0010\u001a\u0004\u0018\u00010\u000bH&J\b\u0010\u0011\u001a\u00020\u0012H&J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000eH&¨\u0006\u0015"}, d2 = {"Lcom/kdownloader/httpclient/HttpClient;", "", "clone", "close", "", "connect", "req", "Lcom/kdownloader/internal/DownloadRequest;", "getContentLength", "", "getErrorStream", "Ljava/io/InputStream;", "getHeaderFields", "", "", "", "getInputStream", "getResponseCode", "", "getResponseHeader", "name", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface HttpClient extends Cloneable {
    HttpClient clone();

    void close();

    void connect(DownloadRequest req) throws IOException;

    long getContentLength();

    InputStream getErrorStream();

    Map<String, List<String>> getHeaderFields();

    InputStream getInputStream() throws IOException;

    int getResponseCode() throws IOException;

    String getResponseHeader(String name);
}
