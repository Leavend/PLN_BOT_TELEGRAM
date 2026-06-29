package com.kdownloader.utils;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.httpclient.DefaultHttpClient;
import com.kdownloader.httpclient.HttpClient;
import com.kdownloader.internal.DownloadRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: Utils.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a\u000e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007\u001a\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0016\u0010\r\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007\u001a\u001e\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"MAX_REDIRECTION", "", "deleteFile", "", "req", "Lcom/kdownloader/internal/DownloadRequest;", "getPath", "", "dirPath", "fileName", "getRedirectedConnectionIfAny", "Lcom/kdownloader/httpclient/HttpClient;", "httpClient0", "getTempPath", "getUniqueId", "url", "isRedirection", "", "code", "renameFileName", "oldPath", "newPath", "library_release"}, k = 2, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UtilsKt {
    private static final int MAX_REDIRECTION = 10;

    private static final boolean isRedirection(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }

    public static final String getPath(String dirPath, String fileName) {
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return dirPath + File.separator + fileName;
    }

    public static final String getTempPath(String dirPath, String fileName) {
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return getPath(dirPath, fileName) + ".temp";
    }

    public static final void renameFileName(String oldPath, String newPath) throws IOException {
        Intrinsics.checkNotNullParameter(oldPath, "oldPath");
        Intrinsics.checkNotNullParameter(newPath, "newPath");
        File file = new File(oldPath);
        try {
            File file2 = new File(newPath);
            if (file2.exists() && !file2.delete()) {
                throw new IOException("Deletion Failed");
            }
            if (!file.renameTo(file2)) {
                throw new IOException("Rename Failed");
            }
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public static final HttpClient getRedirectedConnectionIfAny(HttpClient httpClient0, DownloadRequest req) throws IllegalAccessException, IOException {
        Intrinsics.checkNotNullParameter(httpClient0, "httpClient0");
        Intrinsics.checkNotNullParameter(req, "req");
        int responseCode = httpClient0.getResponseCode();
        String responseHeader = httpClient0.getResponseHeader("Location");
        int i = 0;
        while (isRedirection(responseCode)) {
            if (responseHeader == null) {
                throw new IllegalAccessException("Location is null");
            }
            httpClient0.close();
            req.setUrl$library_release(responseHeader);
            httpClient0 = new DefaultHttpClient().m6560clone();
            httpClient0.connect(req);
            responseCode = httpClient0.getResponseCode();
            responseHeader = httpClient0.getResponseHeader("Location");
            i++;
            if (i >= 10) {
                throw new IllegalAccessException("Max redirection done");
            }
        }
        return httpClient0;
    }

    public static final int getUniqueId(String url, String dirPath, String fileName) throws NoSuchAlgorithmException {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        String str = url + File.separator + dirPath + File.separator + fileName;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
            Charset charsetForName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            byte[] bytes = str.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            byte[] bArrDigest = messageDigest.digest(bytes);
            Intrinsics.checkNotNullExpressionValue(bArrDigest, "{\n        MessageDigest.…(charset(\"UTF-8\")))\n    }");
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                byte b2 = (byte) (b & (-1));
                if (b2 < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(b2));
            }
            return sb.toString().hashCode();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UnsupportedEncodingException", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("NoSuchAlgorithmException", e2);
        }
    }

    public static final void deleteFile(DownloadRequest req) {
        Intrinsics.checkNotNullParameter(req, "req");
        new File(getTempPath(req.getDirPath(), req.getFileName())).delete();
    }
}
