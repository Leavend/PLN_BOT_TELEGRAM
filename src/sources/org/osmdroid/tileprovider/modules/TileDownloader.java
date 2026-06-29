package org.osmdroid.tileprovider.modules;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import id.go.bpsfasih.data.localserver.Config;
import io.grpc.internal.GrpcUtil;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.HttpHost;
import org.osmdroid.api.IMapView;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.BitmapTileSourceBase;
import org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase;
import org.osmdroid.tileprovider.util.Counters;
import org.osmdroid.tileprovider.util.StreamUtils;
import org.osmdroid.util.MapTileIndex;

/* loaded from: classes3.dex */
public class TileDownloader {
    private boolean compatibilitySocketFactorySet;

    public Drawable downloadTile(long j, IFilesystemCache iFilesystemCache, OnlineTileSourceBase onlineTileSourceBase) throws CantContinueException {
        return downloadTile(j, 0, onlineTileSourceBase.getTileURLString(j), iFilesystemCache, onlineTileSourceBase);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v37, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r1v30, types: [java.io.Closeable, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v52 */
    /* JADX WARN: Type inference failed for: r1v53 */
    /* JADX WARN: Type inference failed for: r1v54 */
    /* JADX WARN: Type inference failed for: r1v55 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v62 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r24v0, types: [org.osmdroid.tileprovider.modules.IFilesystemCache] */
    /* JADX WARN: Type inference failed for: r25v0, types: [org.osmdroid.tileprovider.tilesource.ITileSource, org.osmdroid.tileprovider.tilesource.OnlineTileSourceBase] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34, types: [java.io.ByteArrayOutputStream, java.io.Closeable, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v79 */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r3v25 */
    /* JADX WARN: Type inference failed for: r3v26 */
    /* JADX WARN: Type inference failed for: r3v27 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v33, types: [java.io.BufferedOutputStream, java.io.Closeable, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v36, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v38, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r3v59 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v60 */
    /* JADX WARN: Type inference failed for: r3v61 */
    /* JADX WARN: Type inference failed for: r3v62 */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v12, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v6 */
    public Drawable downloadTile(long j, int i, String str, IFilesystemCache iFilesystemCache, OnlineTileSourceBase onlineTileSourceBase) throws Throwable {
        Throwable th;
        Object obj;
        ByteArrayInputStream byteArrayInputStream;
        ByteArrayInputStream byteArrayInputStream2;
        HttpURLConnection httpURLConnection;
        ByteArrayInputStream byteArrayInputStream3;
        BitmapTileSourceBase.LowMemoryException lowMemoryException;
        Closeable closeable;
        Closeable closeable2;
        HttpURLConnection httpURLConnection2;
        ByteArrayInputStream byteArrayInputStream4;
        IOException iOException;
        Object obj2;
        ByteArrayInputStream byteArrayInputStream5;
        ByteArrayInputStream byteArrayInputStream6;
        HttpURLConnection httpURLConnection3;
        UnknownHostException unknownHostException;
        Object obj3;
        ByteArrayInputStream byteArrayInputStream7;
        ByteArrayInputStream byteArrayInputStream8;
        HttpURLConnection httpURLConnection4;
        FileNotFoundException fileNotFoundException;
        Object obj4;
        ByteArrayInputStream byteArrayInputStream9;
        ByteArrayInputStream byteArrayInputStream10;
        HttpURLConnection httpURLConnection5;
        Throwable th2;
        HttpURLConnection httpURLConnection6;
        Closeable closeable3;
        Closeable closeable4;
        ?? r1;
        Object obj5;
        ByteArrayInputStream byteArrayInputStream11;
        Object obj6;
        ByteArrayInputStream byteArrayInputStream12;
        Object obj7;
        ByteArrayInputStream byteArrayInputStream13;
        Object obj8;
        ByteArrayInputStream byteArrayInputStream14;
        ByteArrayInputStream byteArrayInputStream15;
        ByteArrayInputStream byteArrayInputStream16;
        ByteArrayInputStream byteArrayInputStream17;
        ByteArrayInputStream byteArrayInputStream18;
        ByteArrayInputStream byteArrayInputStream19;
        Object obj9;
        ByteArrayInputStream byteArrayInputStream20;
        Object obj10;
        ByteArrayInputStream byteArrayInputStream21;
        Object obj11;
        ByteArrayInputStream byteArrayInputStream22;
        Object obj12;
        ?? inputStream;
        ?? byteArrayOutputStream;
        ?? bufferedOutputStream;
        ?? r12 = i;
        ?? r2 = str;
        ?? r3 = Config.HTTPS;
        ?? r5 = "Downloading Maptile from url: ";
        HttpURLConnection httpURLConnection7 = null;
        if (r12 > 3) {
            return null;
        }
        String normalizedUserAgent = onlineTileSourceBase.getTileSourcePolicy().normalizesUserAgent() ? Configuration.getInstance().getNormalizedUserAgent() : null;
        if (normalizedUserAgent == null) {
            normalizedUserAgent = Configuration.getInstance().getUserAgentValue();
        }
        if (!onlineTileSourceBase.getTileSourcePolicy().acceptsUserAgent(normalizedUserAgent)) {
            Log.e(IMapView.LOGTAG, "Please configure a relevant user agent; current value is: " + normalizedUserAgent);
            return null;
        }
        try {
            try {
                if (Configuration.getInstance().isDebugMode()) {
                    Log.d(IMapView.LOGTAG, "Downloading Maptile from url: " + r2);
                }
                if (TextUtils.isEmpty(str)) {
                    StreamUtils.closeStream(null);
                    StreamUtils.closeStream(null);
                    StreamUtils.closeStream(null);
                    StreamUtils.closeStream(null);
                    try {
                        httpURLConnection7.disconnect();
                        throw null;
                    } catch (Exception unused) {
                        return null;
                    }
                }
                HttpURLConnection httpURLConnection8 = Configuration.getInstance().getHttpProxy() != null ? (HttpURLConnection) new URL(r2).openConnection(Configuration.getInstance().getHttpProxy()) : (HttpURLConnection) new URL(r2).openConnection();
                try {
                    httpURLConnection8.setUseCaches(true);
                    httpURLConnection8.setRequestProperty(Configuration.getInstance().getUserAgentHttpHeader(), normalizedUserAgent);
                    for (Map.Entry<String, String> entry : Configuration.getInstance().getAdditionalHttpRequestProperties().entrySet()) {
                        httpURLConnection8.setRequestProperty(entry.getKey(), entry.getValue());
                    }
                    httpURLConnection8.connect();
                    if (httpURLConnection8.getResponseCode() != 200) {
                        int responseCode = httpURLConnection8.getResponseCode();
                        if ((responseCode != 301 && responseCode != 302 && responseCode != 307 && responseCode != 308) || !Configuration.getInstance().isMapTileDownloaderFollowRedirects()) {
                            Log.w(IMapView.LOGTAG, "Problem downloading MapTile: " + MapTileIndex.toString(j) + " HTTP response: " + httpURLConnection8.getResponseMessage());
                            if (Configuration.getInstance().isDebugMapTileDownloader()) {
                                Log.d(IMapView.LOGTAG, r2);
                            }
                            Counters.tileDownloadErrors++;
                            StreamUtils.closeStream(httpURLConnection8.getErrorStream());
                            StreamUtils.closeStream(null);
                            StreamUtils.closeStream(null);
                            StreamUtils.closeStream(null);
                            try {
                                httpURLConnection8.disconnect();
                            } catch (Exception unused2) {
                            }
                            return null;
                        }
                        String headerField = httpURLConnection8.getHeaderField("Location");
                        if (headerField != null) {
                            if (headerField.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                                URL url = new URL(r2);
                                int port = url.getPort();
                                boolean zStartsWith = str.toLowerCase().startsWith(Config.HTTPS);
                                if (port == -1) {
                                    port = str.toLowerCase().startsWith(Config.HTTP) ? 80 : GrpcUtil.DEFAULT_PORT_SSL;
                                }
                                headerField = (zStartsWith ? r3 : HttpHost.DEFAULT_SCHEME_NAME) + url.getHost() + ":" + port + headerField;
                            }
                            Log.i(IMapView.LOGTAG, "Http redirect for MapTile: " + MapTileIndex.toString(j) + " HTTP response: " + httpURLConnection8.getResponseMessage() + " to url " + headerField);
                            Drawable drawableDownloadTile = downloadTile(j, r12 + 1, headerField, iFilesystemCache, onlineTileSourceBase);
                            StreamUtils.closeStream(null);
                            StreamUtils.closeStream(null);
                            StreamUtils.closeStream(null);
                            StreamUtils.closeStream(null);
                            try {
                                httpURLConnection8.disconnect();
                            } catch (Exception unused3) {
                            }
                            return drawableDownloadTile;
                        }
                    }
                    String headerField2 = httpURLConnection8.getHeaderField("Content-Type");
                    if (Configuration.getInstance().isDebugMapTileDownloader()) {
                        Log.d(IMapView.LOGTAG, r2 + " success, mime is " + headerField2);
                    }
                    if (headerField2 != null && !headerField2.toLowerCase().contains("image")) {
                        Log.w(IMapView.LOGTAG, r2 + " success, however the mime type does not appear to be an image " + headerField2);
                    }
                    inputStream = httpURLConnection8.getInputStream();
                    try {
                        byteArrayOutputStream = new ByteArrayOutputStream();
                    } catch (FileNotFoundException e) {
                        fileNotFoundException = e;
                        byteArrayInputStream14 = null;
                        obj8 = inputStream;
                        byteArrayInputStream18 = byteArrayInputStream14;
                        obj12 = obj8;
                        byteArrayInputStream22 = byteArrayInputStream14;
                        byteArrayInputStream3 = byteArrayInputStream18;
                        obj4 = obj12;
                        byteArrayInputStream9 = byteArrayInputStream22;
                        byteArrayInputStream10 = byteArrayInputStream18;
                        httpURLConnection5 = httpURLConnection8;
                        Counters.tileDownloadErrors++;
                        Log.w(IMapView.LOGTAG, "Tile not found: " + MapTileIndex.toString(j) + " : " + fileNotFoundException);
                        r12 = obj4;
                        r2 = byteArrayInputStream9;
                        r3 = byteArrayInputStream10;
                        r5 = httpURLConnection5;
                        StreamUtils.closeStream(r12);
                        StreamUtils.closeStream(r3);
                        StreamUtils.closeStream(byteArrayInputStream3);
                        StreamUtils.closeStream(r2);
                        try {
                            r5.disconnect();
                        } catch (Exception unused4) {
                        }
                        return null;
                    } catch (UnknownHostException e2) {
                        unknownHostException = e2;
                        byteArrayInputStream13 = null;
                        obj7 = inputStream;
                        byteArrayInputStream17 = byteArrayInputStream13;
                        obj11 = obj7;
                        byteArrayInputStream21 = byteArrayInputStream13;
                        byteArrayInputStream3 = byteArrayInputStream17;
                        obj3 = obj11;
                        byteArrayInputStream7 = byteArrayInputStream21;
                        byteArrayInputStream8 = byteArrayInputStream17;
                        httpURLConnection4 = httpURLConnection8;
                        Log.w(IMapView.LOGTAG, "UnknownHostException downloading MapTile: " + MapTileIndex.toString(j) + " : " + unknownHostException);
                        Counters.tileDownloadErrors++;
                        r12 = obj3;
                        r2 = byteArrayInputStream7;
                        r3 = byteArrayInputStream8;
                        r5 = httpURLConnection4;
                        StreamUtils.closeStream(r12);
                        StreamUtils.closeStream(r3);
                        StreamUtils.closeStream(byteArrayInputStream3);
                        StreamUtils.closeStream(r2);
                        r5.disconnect();
                        return null;
                    } catch (IOException e3) {
                        iOException = e3;
                        byteArrayInputStream12 = null;
                        obj6 = inputStream;
                        byteArrayInputStream16 = byteArrayInputStream12;
                        obj10 = obj6;
                        byteArrayInputStream20 = byteArrayInputStream12;
                        byteArrayInputStream3 = byteArrayInputStream16;
                        obj2 = obj10;
                        byteArrayInputStream5 = byteArrayInputStream20;
                        byteArrayInputStream6 = byteArrayInputStream16;
                        httpURLConnection3 = httpURLConnection8;
                        Counters.tileDownloadErrors++;
                        Log.w(IMapView.LOGTAG, "IOException downloading MapTile: " + MapTileIndex.toString(j) + " : " + iOException);
                        r12 = obj2;
                        r2 = byteArrayInputStream5;
                        r3 = byteArrayInputStream6;
                        r5 = httpURLConnection3;
                        StreamUtils.closeStream(r12);
                        StreamUtils.closeStream(r3);
                        StreamUtils.closeStream(byteArrayInputStream3);
                        StreamUtils.closeStream(r2);
                        r5.disconnect();
                        return null;
                    } catch (BitmapTileSourceBase.LowMemoryException e4) {
                        e = e4;
                        byteArrayOutputStream = 0;
                        bufferedOutputStream = 0;
                    } catch (Throwable th3) {
                        th = th3;
                        byteArrayInputStream11 = null;
                        obj5 = inputStream;
                        byteArrayInputStream15 = byteArrayInputStream11;
                        obj9 = obj5;
                        byteArrayInputStream19 = byteArrayInputStream11;
                        byteArrayInputStream3 = byteArrayInputStream15;
                        obj = obj9;
                        byteArrayInputStream = byteArrayInputStream19;
                        byteArrayInputStream2 = byteArrayInputStream15;
                        httpURLConnection = httpURLConnection8;
                        Counters.tileDownloadErrors++;
                        Log.e(IMapView.LOGTAG, "Error downloading MapTile: " + MapTileIndex.toString(j), th);
                        r12 = obj;
                        r2 = byteArrayInputStream;
                        r3 = byteArrayInputStream2;
                        r5 = httpURLConnection;
                        StreamUtils.closeStream(r12);
                        StreamUtils.closeStream(r3);
                        StreamUtils.closeStream(byteArrayInputStream3);
                        StreamUtils.closeStream(r2);
                        r5.disconnect();
                        return null;
                    }
                } catch (FileNotFoundException e5) {
                    fileNotFoundException = e5;
                    obj8 = null;
                    byteArrayInputStream14 = null;
                } catch (UnknownHostException e6) {
                    unknownHostException = e6;
                    obj7 = null;
                    byteArrayInputStream13 = null;
                } catch (IOException e7) {
                    iOException = e7;
                    obj6 = null;
                    byteArrayInputStream12 = null;
                } catch (BitmapTileSourceBase.LowMemoryException e8) {
                    lowMemoryException = e8;
                    closeable = null;
                    closeable2 = null;
                    byteArrayInputStream4 = null;
                    httpURLConnection2 = httpURLConnection8;
                } catch (Throwable th4) {
                    th = th4;
                    obj5 = null;
                    byteArrayInputStream11 = null;
                }
                try {
                    bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, 8192);
                    try {
                        long jComputeExpirationTime = onlineTileSourceBase.getTileSourcePolicy().computeExpirationTime(httpURLConnection8, System.currentTimeMillis());
                        StreamUtils.copy(inputStream, bufferedOutputStream);
                        bufferedOutputStream.flush();
                        byteArrayInputStream3 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                        if (iFilesystemCache != 0) {
                            try {
                                byteArrayInputStream4 = byteArrayInputStream3;
                                try {
                                    iFilesystemCache.saveFile(onlineTileSourceBase, j, byteArrayInputStream4, Long.valueOf(jComputeExpirationTime));
                                    byteArrayInputStream4.reset();
                                } catch (FileNotFoundException e9) {
                                    e = e9;
                                    byteArrayInputStream3 = byteArrayInputStream4;
                                    fileNotFoundException = e;
                                    obj4 = inputStream;
                                    byteArrayInputStream9 = byteArrayOutputStream;
                                    byteArrayInputStream10 = bufferedOutputStream;
                                    httpURLConnection5 = httpURLConnection8;
                                    Counters.tileDownloadErrors++;
                                    Log.w(IMapView.LOGTAG, "Tile not found: " + MapTileIndex.toString(j) + " : " + fileNotFoundException);
                                    r12 = obj4;
                                    r2 = byteArrayInputStream9;
                                    r3 = byteArrayInputStream10;
                                    r5 = httpURLConnection5;
                                    StreamUtils.closeStream(r12);
                                    StreamUtils.closeStream(r3);
                                    StreamUtils.closeStream(byteArrayInputStream3);
                                    StreamUtils.closeStream(r2);
                                    r5.disconnect();
                                    return null;
                                } catch (UnknownHostException e10) {
                                    e = e10;
                                    byteArrayInputStream3 = byteArrayInputStream4;
                                    unknownHostException = e;
                                    obj3 = inputStream;
                                    byteArrayInputStream7 = byteArrayOutputStream;
                                    byteArrayInputStream8 = bufferedOutputStream;
                                    httpURLConnection4 = httpURLConnection8;
                                    Log.w(IMapView.LOGTAG, "UnknownHostException downloading MapTile: " + MapTileIndex.toString(j) + " : " + unknownHostException);
                                    Counters.tileDownloadErrors++;
                                    r12 = obj3;
                                    r2 = byteArrayInputStream7;
                                    r3 = byteArrayInputStream8;
                                    r5 = httpURLConnection4;
                                    StreamUtils.closeStream(r12);
                                    StreamUtils.closeStream(r3);
                                    StreamUtils.closeStream(byteArrayInputStream3);
                                    StreamUtils.closeStream(r2);
                                    r5.disconnect();
                                    return null;
                                } catch (IOException e11) {
                                    e = e11;
                                    byteArrayInputStream3 = byteArrayInputStream4;
                                    iOException = e;
                                    obj2 = inputStream;
                                    byteArrayInputStream5 = byteArrayOutputStream;
                                    byteArrayInputStream6 = bufferedOutputStream;
                                    httpURLConnection3 = httpURLConnection8;
                                    Counters.tileDownloadErrors++;
                                    Log.w(IMapView.LOGTAG, "IOException downloading MapTile: " + MapTileIndex.toString(j) + " : " + iOException);
                                    r12 = obj2;
                                    r2 = byteArrayInputStream5;
                                    r3 = byteArrayInputStream6;
                                    r5 = httpURLConnection3;
                                    StreamUtils.closeStream(r12);
                                    StreamUtils.closeStream(r3);
                                    StreamUtils.closeStream(byteArrayInputStream3);
                                    StreamUtils.closeStream(r2);
                                    r5.disconnect();
                                    return null;
                                } catch (BitmapTileSourceBase.LowMemoryException e12) {
                                    e = e12;
                                    httpURLConnection7 = inputStream;
                                    lowMemoryException = e;
                                    closeable = byteArrayOutputStream;
                                    closeable2 = bufferedOutputStream;
                                    httpURLConnection2 = httpURLConnection8;
                                    try {
                                        Counters.countOOM++;
                                        Log.w(IMapView.LOGTAG, "LowMemoryException downloading MapTile: " + MapTileIndex.toString(j) + " : " + lowMemoryException);
                                        throw new CantContinueException(lowMemoryException);
                                    } catch (Throwable th5) {
                                        th2 = th5;
                                        byteArrayInputStream3 = byteArrayInputStream4;
                                        r1 = httpURLConnection7;
                                        closeable4 = closeable;
                                        closeable3 = closeable2;
                                        httpURLConnection6 = httpURLConnection2;
                                        StreamUtils.closeStream(r1);
                                        StreamUtils.closeStream(closeable3);
                                        StreamUtils.closeStream(byteArrayInputStream3);
                                        StreamUtils.closeStream(closeable4);
                                        try {
                                            httpURLConnection6.disconnect();
                                            throw th2;
                                        } catch (Exception unused5) {
                                            throw th2;
                                        }
                                    }
                                } catch (Throwable th6) {
                                    th = th6;
                                    byteArrayInputStream3 = byteArrayInputStream4;
                                    obj = inputStream;
                                    byteArrayInputStream = byteArrayOutputStream;
                                    byteArrayInputStream2 = bufferedOutputStream;
                                    httpURLConnection = httpURLConnection8;
                                    Counters.tileDownloadErrors++;
                                    Log.e(IMapView.LOGTAG, "Error downloading MapTile: " + MapTileIndex.toString(j), th);
                                    r12 = obj;
                                    r2 = byteArrayInputStream;
                                    r3 = byteArrayInputStream2;
                                    r5 = httpURLConnection;
                                    StreamUtils.closeStream(r12);
                                    StreamUtils.closeStream(r3);
                                    StreamUtils.closeStream(byteArrayInputStream3);
                                    StreamUtils.closeStream(r2);
                                    r5.disconnect();
                                    return null;
                                }
                            } catch (FileNotFoundException e13) {
                                e = e13;
                                fileNotFoundException = e;
                                obj4 = inputStream;
                                byteArrayInputStream9 = byteArrayOutputStream;
                                byteArrayInputStream10 = bufferedOutputStream;
                                httpURLConnection5 = httpURLConnection8;
                                Counters.tileDownloadErrors++;
                                Log.w(IMapView.LOGTAG, "Tile not found: " + MapTileIndex.toString(j) + " : " + fileNotFoundException);
                                r12 = obj4;
                                r2 = byteArrayInputStream9;
                                r3 = byteArrayInputStream10;
                                r5 = httpURLConnection5;
                                StreamUtils.closeStream(r12);
                                StreamUtils.closeStream(r3);
                                StreamUtils.closeStream(byteArrayInputStream3);
                                StreamUtils.closeStream(r2);
                                r5.disconnect();
                                return null;
                            } catch (UnknownHostException e14) {
                                e = e14;
                                unknownHostException = e;
                                obj3 = inputStream;
                                byteArrayInputStream7 = byteArrayOutputStream;
                                byteArrayInputStream8 = bufferedOutputStream;
                                httpURLConnection4 = httpURLConnection8;
                                Log.w(IMapView.LOGTAG, "UnknownHostException downloading MapTile: " + MapTileIndex.toString(j) + " : " + unknownHostException);
                                Counters.tileDownloadErrors++;
                                r12 = obj3;
                                r2 = byteArrayInputStream7;
                                r3 = byteArrayInputStream8;
                                r5 = httpURLConnection4;
                                StreamUtils.closeStream(r12);
                                StreamUtils.closeStream(r3);
                                StreamUtils.closeStream(byteArrayInputStream3);
                                StreamUtils.closeStream(r2);
                                r5.disconnect();
                                return null;
                            } catch (IOException e15) {
                                e = e15;
                                iOException = e;
                                obj2 = inputStream;
                                byteArrayInputStream5 = byteArrayOutputStream;
                                byteArrayInputStream6 = bufferedOutputStream;
                                httpURLConnection3 = httpURLConnection8;
                                Counters.tileDownloadErrors++;
                                Log.w(IMapView.LOGTAG, "IOException downloading MapTile: " + MapTileIndex.toString(j) + " : " + iOException);
                                r12 = obj2;
                                r2 = byteArrayInputStream5;
                                r3 = byteArrayInputStream6;
                                r5 = httpURLConnection3;
                                StreamUtils.closeStream(r12);
                                StreamUtils.closeStream(r3);
                                StreamUtils.closeStream(byteArrayInputStream3);
                                StreamUtils.closeStream(r2);
                                r5.disconnect();
                                return null;
                            } catch (BitmapTileSourceBase.LowMemoryException e16) {
                                e = e16;
                                byteArrayInputStream4 = byteArrayInputStream3;
                                httpURLConnection7 = inputStream;
                                lowMemoryException = e;
                                closeable = byteArrayOutputStream;
                                closeable2 = bufferedOutputStream;
                                httpURLConnection2 = httpURLConnection8;
                                Counters.countOOM++;
                                Log.w(IMapView.LOGTAG, "LowMemoryException downloading MapTile: " + MapTileIndex.toString(j) + " : " + lowMemoryException);
                                throw new CantContinueException(lowMemoryException);
                            } catch (Throwable th7) {
                                th = th7;
                                obj = inputStream;
                                byteArrayInputStream = byteArrayOutputStream;
                                byteArrayInputStream2 = bufferedOutputStream;
                                httpURLConnection = httpURLConnection8;
                                Counters.tileDownloadErrors++;
                                Log.e(IMapView.LOGTAG, "Error downloading MapTile: " + MapTileIndex.toString(j), th);
                                r12 = obj;
                                r2 = byteArrayInputStream;
                                r3 = byteArrayInputStream2;
                                r5 = httpURLConnection;
                                StreamUtils.closeStream(r12);
                                StreamUtils.closeStream(r3);
                                StreamUtils.closeStream(byteArrayInputStream3);
                                StreamUtils.closeStream(r2);
                                r5.disconnect();
                                return null;
                            }
                        } else {
                            byteArrayInputStream4 = byteArrayInputStream3;
                        }
                        Drawable drawable = onlineTileSourceBase.getDrawable(byteArrayInputStream4);
                        StreamUtils.closeStream(inputStream);
                        StreamUtils.closeStream(bufferedOutputStream);
                        StreamUtils.closeStream(byteArrayInputStream4);
                        StreamUtils.closeStream(byteArrayOutputStream);
                        try {
                            httpURLConnection8.disconnect();
                        } catch (Exception unused6) {
                        }
                        return drawable;
                    } catch (FileNotFoundException e17) {
                        fileNotFoundException = e17;
                        byteArrayInputStream3 = null;
                        obj4 = inputStream;
                        byteArrayInputStream9 = byteArrayOutputStream;
                        byteArrayInputStream10 = bufferedOutputStream;
                        httpURLConnection5 = httpURLConnection8;
                    } catch (UnknownHostException e18) {
                        unknownHostException = e18;
                        byteArrayInputStream3 = null;
                        obj3 = inputStream;
                        byteArrayInputStream7 = byteArrayOutputStream;
                        byteArrayInputStream8 = bufferedOutputStream;
                        httpURLConnection4 = httpURLConnection8;
                    } catch (IOException e19) {
                        iOException = e19;
                        byteArrayInputStream3 = null;
                        obj2 = inputStream;
                        byteArrayInputStream5 = byteArrayOutputStream;
                        byteArrayInputStream6 = bufferedOutputStream;
                        httpURLConnection3 = httpURLConnection8;
                    } catch (BitmapTileSourceBase.LowMemoryException e20) {
                        e = e20;
                        byteArrayInputStream4 = null;
                    } catch (Throwable th8) {
                        th = th8;
                        byteArrayInputStream3 = null;
                        obj = inputStream;
                        byteArrayInputStream = byteArrayOutputStream;
                        byteArrayInputStream2 = bufferedOutputStream;
                        httpURLConnection = httpURLConnection8;
                    }
                } catch (FileNotFoundException e21) {
                    fileNotFoundException = e21;
                    byteArrayInputStream18 = null;
                    obj12 = inputStream;
                    byteArrayInputStream22 = byteArrayOutputStream;
                    byteArrayInputStream3 = byteArrayInputStream18;
                    obj4 = obj12;
                    byteArrayInputStream9 = byteArrayInputStream22;
                    byteArrayInputStream10 = byteArrayInputStream18;
                    httpURLConnection5 = httpURLConnection8;
                    Counters.tileDownloadErrors++;
                    Log.w(IMapView.LOGTAG, "Tile not found: " + MapTileIndex.toString(j) + " : " + fileNotFoundException);
                    r12 = obj4;
                    r2 = byteArrayInputStream9;
                    r3 = byteArrayInputStream10;
                    r5 = httpURLConnection5;
                    StreamUtils.closeStream(r12);
                    StreamUtils.closeStream(r3);
                    StreamUtils.closeStream(byteArrayInputStream3);
                    StreamUtils.closeStream(r2);
                    r5.disconnect();
                    return null;
                } catch (UnknownHostException e22) {
                    unknownHostException = e22;
                    byteArrayInputStream17 = null;
                    obj11 = inputStream;
                    byteArrayInputStream21 = byteArrayOutputStream;
                    byteArrayInputStream3 = byteArrayInputStream17;
                    obj3 = obj11;
                    byteArrayInputStream7 = byteArrayInputStream21;
                    byteArrayInputStream8 = byteArrayInputStream17;
                    httpURLConnection4 = httpURLConnection8;
                    Log.w(IMapView.LOGTAG, "UnknownHostException downloading MapTile: " + MapTileIndex.toString(j) + " : " + unknownHostException);
                    Counters.tileDownloadErrors++;
                    r12 = obj3;
                    r2 = byteArrayInputStream7;
                    r3 = byteArrayInputStream8;
                    r5 = httpURLConnection4;
                    StreamUtils.closeStream(r12);
                    StreamUtils.closeStream(r3);
                    StreamUtils.closeStream(byteArrayInputStream3);
                    StreamUtils.closeStream(r2);
                    r5.disconnect();
                    return null;
                } catch (IOException e23) {
                    iOException = e23;
                    byteArrayInputStream16 = null;
                    obj10 = inputStream;
                    byteArrayInputStream20 = byteArrayOutputStream;
                    byteArrayInputStream3 = byteArrayInputStream16;
                    obj2 = obj10;
                    byteArrayInputStream5 = byteArrayInputStream20;
                    byteArrayInputStream6 = byteArrayInputStream16;
                    httpURLConnection3 = httpURLConnection8;
                    Counters.tileDownloadErrors++;
                    Log.w(IMapView.LOGTAG, "IOException downloading MapTile: " + MapTileIndex.toString(j) + " : " + iOException);
                    r12 = obj2;
                    r2 = byteArrayInputStream5;
                    r3 = byteArrayInputStream6;
                    r5 = httpURLConnection3;
                    StreamUtils.closeStream(r12);
                    StreamUtils.closeStream(r3);
                    StreamUtils.closeStream(byteArrayInputStream3);
                    StreamUtils.closeStream(r2);
                    r5.disconnect();
                    return null;
                } catch (BitmapTileSourceBase.LowMemoryException e24) {
                    e = e24;
                    bufferedOutputStream = 0;
                    byteArrayOutputStream = byteArrayOutputStream;
                    byteArrayInputStream4 = bufferedOutputStream;
                    httpURLConnection7 = inputStream;
                    lowMemoryException = e;
                    closeable = byteArrayOutputStream;
                    closeable2 = bufferedOutputStream;
                    httpURLConnection2 = httpURLConnection8;
                    Counters.countOOM++;
                    Log.w(IMapView.LOGTAG, "LowMemoryException downloading MapTile: " + MapTileIndex.toString(j) + " : " + lowMemoryException);
                    throw new CantContinueException(lowMemoryException);
                } catch (Throwable th9) {
                    th = th9;
                    byteArrayInputStream15 = null;
                    obj9 = inputStream;
                    byteArrayInputStream19 = byteArrayOutputStream;
                    byteArrayInputStream3 = byteArrayInputStream15;
                    obj = obj9;
                    byteArrayInputStream = byteArrayInputStream19;
                    byteArrayInputStream2 = byteArrayInputStream15;
                    httpURLConnection = httpURLConnection8;
                    Counters.tileDownloadErrors++;
                    Log.e(IMapView.LOGTAG, "Error downloading MapTile: " + MapTileIndex.toString(j), th);
                    r12 = obj;
                    r2 = byteArrayInputStream;
                    r3 = byteArrayInputStream2;
                    r5 = httpURLConnection;
                    StreamUtils.closeStream(r12);
                    StreamUtils.closeStream(r3);
                    StreamUtils.closeStream(byteArrayInputStream3);
                    StreamUtils.closeStream(r2);
                    r5.disconnect();
                    return null;
                }
            } catch (Throwable th10) {
                th2 = th10;
                r1 = r12;
                closeable4 = r2;
                closeable3 = r3;
                httpURLConnection6 = r5;
                StreamUtils.closeStream(r1);
                StreamUtils.closeStream(closeable3);
                StreamUtils.closeStream(byteArrayInputStream3);
                StreamUtils.closeStream(closeable4);
                httpURLConnection6.disconnect();
                throw th2;
            }
        } catch (FileNotFoundException e25) {
            fileNotFoundException = e25;
            obj4 = null;
            byteArrayInputStream9 = null;
            byteArrayInputStream10 = null;
            httpURLConnection5 = null;
            byteArrayInputStream3 = null;
        } catch (UnknownHostException e26) {
            unknownHostException = e26;
            obj3 = null;
            byteArrayInputStream7 = null;
            byteArrayInputStream8 = null;
            httpURLConnection4 = null;
            byteArrayInputStream3 = null;
        } catch (IOException e27) {
            iOException = e27;
            obj2 = null;
            byteArrayInputStream5 = null;
            byteArrayInputStream6 = null;
            httpURLConnection3 = null;
            byteArrayInputStream3 = null;
        } catch (BitmapTileSourceBase.LowMemoryException e28) {
            lowMemoryException = e28;
            closeable = null;
            closeable2 = null;
            httpURLConnection2 = null;
            byteArrayInputStream4 = null;
        } catch (Throwable th11) {
            th = th11;
            obj = null;
            byteArrayInputStream = null;
            byteArrayInputStream2 = null;
            httpURLConnection = null;
            byteArrayInputStream3 = null;
        }
    }

    @Deprecated
    public Long getHttpExpiresTime(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            return Long.valueOf(Configuration.getInstance().getHttpHeaderDateTimeFormat().parse(str).getTime());
        } catch (Exception e) {
            if (!Configuration.getInstance().isDebugMapTileDownloader()) {
                return null;
            }
            Log.d(IMapView.LOGTAG, "Unable to parse expiration tag for tile, server returned " + str, e);
            return null;
        }
    }

    @Deprecated
    public Long getHttpCacheControlDuration(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            for (String str2 : str.split(", ")) {
                if (str2.indexOf("max-age=") == 0) {
                    return Long.valueOf(str2.substring(8));
                }
            }
            return null;
        } catch (Exception e) {
            if (!Configuration.getInstance().isDebugMapTileDownloader()) {
                return null;
            }
            Log.d(IMapView.LOGTAG, "Unable to parse cache control tag for tile, server returned " + str, e);
            return null;
        }
    }

    @Deprecated
    public long computeExpirationTime(String str, String str2, long j) {
        Long expirationOverrideDuration = Configuration.getInstance().getExpirationOverrideDuration();
        if (expirationOverrideDuration != null) {
            return j + expirationOverrideDuration.longValue();
        }
        long expirationExtendedDuration = Configuration.getInstance().getExpirationExtendedDuration();
        Long httpCacheControlDuration = getHttpCacheControlDuration(str2);
        if (httpCacheControlDuration != null) {
            return j + (httpCacheControlDuration.longValue() * 1000) + expirationExtendedDuration;
        }
        Long httpExpiresTime = getHttpExpiresTime(str);
        return httpExpiresTime != null ? httpExpiresTime.longValue() + expirationExtendedDuration : j + 604800000 + expirationExtendedDuration;
    }

    private static class CompatibilitySocketFactory extends SSLSocketFactory {
        SSLSocketFactory sslSocketFactory;

        CompatibilitySocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sslSocketFactory = sSLSocketFactory;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return this.sslSocketFactory.getDefaultCipherSuites();
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return this.sslSocketFactory.getSupportedCipherSuites();
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket() throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket());
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket(socket, str, i, z));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i) throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket(str, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket(str, i, inetAddress, i2));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket(inetAddress, i));
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
            return upgradeTlsAndRemoveSsl((SSLSocket) this.sslSocketFactory.createSocket(inetAddress, i, inetAddress2, i2));
        }

        private SSLSocket upgradeTlsAndRemoveSsl(SSLSocket sSLSocket) {
            String[] supportedProtocols = sSLSocket.getSupportedProtocols();
            String[] enabledProtocols = sSLSocket.getEnabledProtocols();
            if (Arrays.binarySearch(supportedProtocols, "TLSv1.2") >= 0) {
                enabledProtocols = new String[]{"TLSv1.2"};
            } else {
                int iBinarySearch = Arrays.binarySearch(enabledProtocols, "SSLv3");
                if (iBinarySearch >= 0) {
                    int length = enabledProtocols.length - 1;
                    String[] strArr = new String[length];
                    System.arraycopy(enabledProtocols, 0, strArr, 0, iBinarySearch);
                    if (length > iBinarySearch) {
                        System.arraycopy(enabledProtocols, iBinarySearch + 1, strArr, iBinarySearch, length - iBinarySearch);
                    }
                    enabledProtocols = strArr;
                }
            }
            sSLSocket.setEnabledProtocols(enabledProtocols);
            return sSLSocket;
        }
    }
}
