package com.kdownloader.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import com.kdownloader.Status;
import com.kdownloader.utils.UtilsKt;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: DownloadRequest.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002@AB\u0097\u0001\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00122\u0010\u0007\u001a.\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t\u0018\u00010\bj\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t\u0018\u0001`\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0003¢\u0006\u0002\u0010\u0014J\u0006\u0010>\u001a\u00020?R\u001a\u0010\u0012\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u000b\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u0014\u0010\u000e\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR@\u0010\u0007\u001a.\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t\u0018\u00010\bj\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\t\u0018\u0001`\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u0080.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010\u0011\u001a\u00020\rX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R\u001a\u0010\u000f\u001a\u00020\u0010X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001aR\u001a\u00106\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u001f\"\u0004\b8\u0010!R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001a\"\u0004\b:\u0010;R\u001a\u0010\u0013\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u001a\"\u0004\b=\u0010;¨\u0006B"}, d2 = {"Lcom/kdownloader/internal/DownloadRequest;", "", "url", "", "tag", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/kdownloader/internal/DownloadRequest$Listener;", "headers", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "dirPath", "downloadId", "", "fileName", NotificationCompat.CATEGORY_STATUS, "Lcom/kdownloader/Status;", "readTimeOut", "connectTimeOut", "userAgent", "(Ljava/lang/String;Ljava/lang/String;Lcom/kdownloader/internal/DownloadRequest$Listener;Ljava/util/HashMap;Ljava/lang/String;ILjava/lang/String;Lcom/kdownloader/Status;IILjava/lang/String;)V", "getConnectTimeOut$library_release", "()I", "setConnectTimeOut$library_release", "(I)V", "getDirPath$library_release", "()Ljava/lang/String;", "getDownloadId$library_release", "downloadedBytes", "", "getDownloadedBytes", "()J", "setDownloadedBytes", "(J)V", "getFileName$library_release", "getHeaders$library_release", "()Ljava/util/HashMap;", "job", "Lkotlinx/coroutines/Job;", "getJob$library_release", "()Lkotlinx/coroutines/Job;", "setJob$library_release", "(Lkotlinx/coroutines/Job;)V", "getListener$library_release", "()Lcom/kdownloader/internal/DownloadRequest$Listener;", "setListener$library_release", "(Lcom/kdownloader/internal/DownloadRequest$Listener;)V", "getReadTimeOut$library_release", "setReadTimeOut$library_release", "getStatus$library_release", "()Lcom/kdownloader/Status;", "setStatus$library_release", "(Lcom/kdownloader/Status;)V", "getTag$library_release", "totalBytes", "getTotalBytes", "setTotalBytes", "getUrl$library_release", "setUrl$library_release", "(Ljava/lang/String;)V", "getUserAgent$library_release", "setUserAgent$library_release", "reset", "", "Builder", "Listener", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadRequest {
    private int connectTimeOut;
    private final String dirPath;
    private final int downloadId;
    private long downloadedBytes;
    private final String fileName;
    private final HashMap<String, List<String>> headers;
    public Job job;
    private Listener listener;
    private int readTimeOut;
    private Status status;
    private final String tag;
    private long totalBytes;
    private String url;
    private String userAgent;

    /* compiled from: DownloadRequest.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\f"}, d2 = {"Lcom/kdownloader/internal/DownloadRequest$Listener;", "", "onCompleted", "", "onError", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "onPause", "onProgress", "value", "", "onStart", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public interface Listener {
        void onCompleted();

        void onError(String error);

        void onPause();

        void onProgress(int value);

        void onStart();
    }

    private DownloadRequest(String str, String str2, Listener listener, HashMap<String, List<String>> map, String str3, int i, String str4, Status status, int i2, int i3, String str5) {
        this.url = str;
        this.tag = str2;
        this.listener = listener;
        this.headers = map;
        this.dirPath = str3;
        this.downloadId = i;
        this.fileName = str4;
        this.status = status;
        this.readTimeOut = i2;
        this.connectTimeOut = i3;
        this.userAgent = str5;
    }

    /* renamed from: getUrl$library_release, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final void setUrl$library_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.url = str;
    }

    /* renamed from: getTag$library_release, reason: from getter */
    public final String getTag() {
        return this.tag;
    }

    /* renamed from: getListener$library_release, reason: from getter */
    public final Listener getListener() {
        return this.listener;
    }

    public final void setListener$library_release(Listener listener) {
        this.listener = listener;
    }

    public final HashMap<String, List<String>> getHeaders$library_release() {
        return this.headers;
    }

    /* renamed from: getDirPath$library_release, reason: from getter */
    public final String getDirPath() {
        return this.dirPath;
    }

    /* renamed from: getDownloadId$library_release, reason: from getter */
    public final int getDownloadId() {
        return this.downloadId;
    }

    /* renamed from: getFileName$library_release, reason: from getter */
    public final String getFileName() {
        return this.fileName;
    }

    /* synthetic */ DownloadRequest(String str, String str2, Listener listener, HashMap map, String str3, int i, String str4, Status status, int i2, int i3, String str5, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, listener, map, str3, i, str4, (i4 & 128) != 0 ? Status.UNKNOWN : status, (i4 & 256) != 0 ? 0 : i2, (i4 & 512) != 0 ? 0 : i3, (i4 & 1024) != 0 ? com.kdownloader.Constants.DEFAULT_USER_AGENT : str5);
    }

    /* renamed from: getStatus$library_release, reason: from getter */
    public final Status getStatus() {
        return this.status;
    }

    public final void setStatus$library_release(Status status) {
        Intrinsics.checkNotNullParameter(status, "<set-?>");
        this.status = status;
    }

    /* renamed from: getReadTimeOut$library_release, reason: from getter */
    public final int getReadTimeOut() {
        return this.readTimeOut;
    }

    public final void setReadTimeOut$library_release(int i) {
        this.readTimeOut = i;
    }

    /* renamed from: getConnectTimeOut$library_release, reason: from getter */
    public final int getConnectTimeOut() {
        return this.connectTimeOut;
    }

    public final void setConnectTimeOut$library_release(int i) {
        this.connectTimeOut = i;
    }

    /* renamed from: getUserAgent$library_release, reason: from getter */
    public final String getUserAgent() {
        return this.userAgent;
    }

    public final void setUserAgent$library_release(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userAgent = str;
    }

    public final long getTotalBytes() {
        return this.totalBytes;
    }

    public final void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public final long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public final void setDownloadedBytes(long j) {
        this.downloadedBytes = j;
    }

    public final Job getJob$library_release() {
        Job job = this.job;
        if (job != null) {
            return job;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        return null;
    }

    public final void setJob$library_release(Job job) {
        Intrinsics.checkNotNullParameter(job, "<set-?>");
        this.job = job;
    }

    /* compiled from: DownloadRequest.kt */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001a\u00020\u0013J\t\u0010\u0014\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÂ\u0003J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bJ'\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J6\u0010\t\u001a\u00020\u00002.\u0010\t\u001a*\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\nj\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b`\fJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0003J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0003R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R:\u0010\t\u001a.\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0018\u00010\nj\u0016\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/kdownloader/internal/DownloadRequest$Builder;", "", "url", "", "dirPath", "fileName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "connectTimeOut", "", "headers", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/kdownloader/internal/DownloadRequest$Listener;", "readTimeOut", "tag", "userAgent", "build", "Lcom/kdownloader/internal/DownloadRequest;", "component1", "component2", "component3", "connectTimeout", "timeout", "copy", "equals", "", "other", "hashCode", "readTimeout", "toString", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class Builder {
        private int connectTimeOut;
        private final String dirPath;
        private final String fileName;
        private HashMap<String, List<String>> headers;
        private Listener listener;
        private int readTimeOut;
        private String tag;
        private final String url;
        private String userAgent;

        /* renamed from: component1, reason: from getter */
        private final String getUrl() {
            return this.url;
        }

        /* renamed from: component2, reason: from getter */
        private final String getDirPath() {
            return this.dirPath;
        }

        /* renamed from: component3, reason: from getter */
        private final String getFileName() {
            return this.fileName;
        }

        public static /* synthetic */ Builder copy$default(Builder builder, String str, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = builder.url;
            }
            if ((i & 2) != 0) {
                str2 = builder.dirPath;
            }
            if ((i & 4) != 0) {
                str3 = builder.fileName;
            }
            return builder.copy(str, str2, str3);
        }

        public final Builder copy(String url, String dirPath, String fileName) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(dirPath, "dirPath");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            return new Builder(url, dirPath, fileName);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Builder)) {
                return false;
            }
            Builder builder = (Builder) other;
            return Intrinsics.areEqual(this.url, builder.url) && Intrinsics.areEqual(this.dirPath, builder.dirPath) && Intrinsics.areEqual(this.fileName, builder.fileName);
        }

        public int hashCode() {
            return (((this.url.hashCode() * 31) + this.dirPath.hashCode()) * 31) + this.fileName.hashCode();
        }

        public String toString() {
            return "Builder(url=" + this.url + ", dirPath=" + this.dirPath + ", fileName=" + this.fileName + ')';
        }

        public Builder(String url, String dirPath, String fileName) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(dirPath, "dirPath");
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            this.url = url;
            this.dirPath = dirPath;
            this.fileName = fileName;
            this.readTimeOut = 20000;
            this.connectTimeOut = 20000;
            this.userAgent = com.kdownloader.Constants.DEFAULT_USER_AGENT;
        }

        public final Builder tag(String tag) {
            Intrinsics.checkNotNullParameter(tag, "tag");
            this.tag = tag;
            return this;
        }

        public final Builder headers(HashMap<String, List<String>> headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.headers = headers;
            return this;
        }

        public final Builder readTimeout(int timeout) {
            this.readTimeOut = timeout;
            return this;
        }

        public final Builder connectTimeout(int timeout) {
            this.connectTimeOut = timeout;
            return this;
        }

        public final Builder userAgent(String userAgent) {
            Intrinsics.checkNotNullParameter(userAgent, "userAgent");
            this.userAgent = userAgent;
            return this;
        }

        public final DownloadRequest build() {
            String str = this.url;
            String str2 = this.tag;
            Listener listener = this.listener;
            HashMap<String, List<String>> map = this.headers;
            String str3 = this.dirPath;
            return new DownloadRequest(str, str2, listener, map, str3, UtilsKt.getUniqueId(str, str3, this.fileName), this.fileName, null, this.readTimeOut, this.connectTimeOut, this.userAgent, 128, null);
        }
    }

    public final void reset() {
        this.downloadedBytes = 0L;
        this.totalBytes = 0L;
        this.status = Status.UNKNOWN;
    }
}
