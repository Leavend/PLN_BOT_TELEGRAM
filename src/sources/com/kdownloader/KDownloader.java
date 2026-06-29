package com.kdownloader;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.kdownloader.database.AppDbHelper;
import com.kdownloader.database.DbHelper;
import com.kdownloader.database.DownloadModel;
import com.kdownloader.database.NoOpsDbHelper;
import com.kdownloader.internal.DownloadDispatchers;
import com.kdownloader.internal.DownloadRequest;
import com.kdownloader.internal.DownloadRequestQueue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: KDownloader.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 -2\u00020\u0001:\u0001-B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\fJ\u000e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u000eJ\u008e\u0001\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u000e\b\u0006\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00182#\b\u0006\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\f0\u001a2\u000e\b\u0006\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u00182#\b\u0006\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b( \u0012\u0004\u0012\u00020\f0\u001a2\u000e\b\u0006\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u0018H\u0086\bø\u0001\u0000J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020#J\u001e\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010'\u001a\u00020\u00102\u0006\u0010(\u001a\u00020\u0010J\u000e\u0010)\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010*\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010+\u001a\u00020,2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006."}, d2 = {"Lcom/kdownloader/KDownloader;", "", "dbHelper", "Lcom/kdownloader/database/DbHelper;", "config", "Lcom/kdownloader/DownloaderConfig;", "(Lcom/kdownloader/database/DbHelper;Lcom/kdownloader/DownloaderConfig;)V", "downloader", "Lcom/kdownloader/internal/DownloadDispatchers;", "reqQueue", "Lcom/kdownloader/internal/DownloadRequestQueue;", "cancel", "", DownloadModel.ID, "", "tag", "", "cancelAll", "cleanUp", "days", "enqueue", "req", "Lcom/kdownloader/internal/DownloadRequest;", "onStart", "Lkotlin/Function0;", "onProgress", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "value", "onPause", "onError", com.google.firebase.messaging.Constants.IPC_BUNDLE_KEY_SEND_ERROR, "onCompleted", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/kdownloader/internal/DownloadRequest$Listener;", "newRequestBuilder", "Lcom/kdownloader/internal/DownloadRequest$Builder;", "url", "dirPath", "fileName", "pause", "resume", NotificationCompat.CATEGORY_STATUS, "Lcom/kdownloader/Status;", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class KDownloader {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final DownloaderConfig config;
    private final DownloadDispatchers downloader;
    private final DownloadRequestQueue reqQueue;

    public /* synthetic */ KDownloader(DbHelper dbHelper, DownloaderConfig downloaderConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(dbHelper, downloaderConfig);
    }

    private KDownloader(DbHelper dbHelper, DownloaderConfig downloaderConfig) {
        this.config = downloaderConfig;
        DownloadDispatchers downloadDispatchers = new DownloadDispatchers(dbHelper);
        this.downloader = downloadDispatchers;
        this.reqQueue = new DownloadRequestQueue(downloadDispatchers);
    }

    /* compiled from: KDownloader.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/kdownloader/KDownloader$Companion;", "", "()V", "create", "Lcom/kdownloader/KDownloader;", "context", "Landroid/content/Context;", "config", "Lcom/kdownloader/DownloaderConfig;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ KDownloader create$default(Companion companion, Context context, DownloaderConfig downloaderConfig, int i, Object obj) {
            if ((i & 2) != 0) {
                downloaderConfig = new DownloaderConfig(true, 0, 0, 6, null);
            }
            return companion.create(context, downloaderConfig);
        }

        public final KDownloader create(Context context, DownloaderConfig config) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(config, "config");
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (config.getDatabaseEnabled()) {
                return new KDownloader(new AppDbHelper(context), config, defaultConstructorMarker);
            }
            return new KDownloader(new NoOpsDbHelper(), config, defaultConstructorMarker);
        }
    }

    public final DownloadRequest.Builder newRequestBuilder(String url, String dirPath, String fileName) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(dirPath, "dirPath");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        return new DownloadRequest.Builder(url, dirPath, fileName).readTimeout(this.config.getReadTimeOut()).connectTimeout(this.config.getConnectTimeOut());
    }

    public final int enqueue(DownloadRequest req, DownloadRequest.Listener listener) {
        Intrinsics.checkNotNullParameter(req, "req");
        Intrinsics.checkNotNullParameter(listener, "listener");
        req.setListener$library_release(listener);
        return this.reqQueue.enqueue(req);
    }

    public static /* synthetic */ int enqueue$default(KDownloader kDownloader, DownloadRequest req, Function0 onStart, Function1 function1, Function0 function0, Function1 function12, Function0 function02, int i, Object obj) {
        if ((i & 2) != 0) {
            onStart = new Function0<Unit>() { // from class: com.kdownloader.KDownloader.enqueue.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            function1 = new Function1<Integer, Unit>() { // from class: com.kdownloader.KDownloader.enqueue.2
                public final void invoke(int i2) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                    invoke(num.intValue());
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 onProgress = function1;
        if ((i & 8) != 0) {
            function0 = new Function0<Unit>() { // from class: com.kdownloader.KDownloader.enqueue.3
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 onPause = function0;
        if ((i & 16) != 0) {
            function12 = new Function1<String, Unit>() { // from class: com.kdownloader.KDownloader.enqueue.4
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String str) {
                    Intrinsics.checkNotNullParameter(str, "<anonymous parameter 0>");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
        }
        Function1 onError = function12;
        if ((i & 32) != 0) {
            function02 = new Function0<Unit>() { // from class: com.kdownloader.KDownloader.enqueue.5
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        Function0 onCompleted = function02;
        Intrinsics.checkNotNullParameter(req, "req");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        Intrinsics.checkNotNullParameter(onPause, "onPause");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onCompleted, "onCompleted");
        return kDownloader.enqueue(req, new AnonymousClass6(onStart, onProgress, onPause, onError, onCompleted));
    }

    /* compiled from: KDownloader.kt */
    @Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016¨\u0006\f"}, d2 = {"com/kdownloader/KDownloader$enqueue$6", "Lcom/kdownloader/internal/DownloadRequest$Listener;", "onCompleted", "", "onError", com.google.firebase.messaging.Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "onPause", "onProgress", "value", "", "onStart", "library_release"}, k = 1, mv = {1, 8, 0}, xi = 176)
    /* renamed from: com.kdownloader.KDownloader$enqueue$6, reason: invalid class name */
    public static final class AnonymousClass6 implements DownloadRequest.Listener {
        final /* synthetic */ Function0<Unit> $onCompleted;
        final /* synthetic */ Function1<String, Unit> $onError;
        final /* synthetic */ Function0<Unit> $onPause;
        final /* synthetic */ Function1<Integer, Unit> $onProgress;
        final /* synthetic */ Function0<Unit> $onStart;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(Function0<Unit> function0, Function1<? super Integer, Unit> function1, Function0<Unit> function02, Function1<? super String, Unit> function12, Function0<Unit> function03) {
            this.$onStart = function0;
            this.$onProgress = function1;
            this.$onPause = function02;
            this.$onError = function12;
            this.$onCompleted = function03;
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onStart() {
            this.$onStart.invoke();
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onProgress(int value) {
            this.$onProgress.invoke(Integer.valueOf(value));
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onPause() {
            this.$onPause.invoke();
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onError(String error) {
            Intrinsics.checkNotNullParameter(error, "error");
            this.$onError.invoke(error);
        }

        @Override // com.kdownloader.internal.DownloadRequest.Listener
        public void onCompleted() {
            this.$onCompleted.invoke();
        }
    }

    public final int enqueue(DownloadRequest req, Function0<Unit> onStart, Function1<? super Integer, Unit> onProgress, Function0<Unit> onPause, Function1<? super String, Unit> onError, Function0<Unit> onCompleted) {
        Intrinsics.checkNotNullParameter(req, "req");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onProgress, "onProgress");
        Intrinsics.checkNotNullParameter(onPause, "onPause");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onCompleted, "onCompleted");
        return enqueue(req, new AnonymousClass6(onStart, onProgress, onPause, onError, onCompleted));
    }

    public final Status status(int id2) {
        return this.reqQueue.status(id2);
    }

    public final void cancel(int id2) {
        this.reqQueue.cancel(id2);
    }

    public final void cancel(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.reqQueue.cancel(tag);
    }

    public final void cancelAll() {
        this.reqQueue.cancelAll();
    }

    public final void pause(int id2) {
        this.reqQueue.pause(id2);
    }

    public final void resume(int id2) {
        this.reqQueue.resume(id2);
    }

    public final void cleanUp(int days) {
        this.downloader.cleanup(days);
    }
}
