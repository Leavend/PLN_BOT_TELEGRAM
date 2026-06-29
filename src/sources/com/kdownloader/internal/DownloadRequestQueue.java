package com.kdownloader.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.kdownloader.Status;
import com.kdownloader.database.DownloadModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadRequestQueue.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000bJ\u000e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/kdownloader/internal/DownloadRequestQueue;", "", "downloader", "Lcom/kdownloader/internal/DownloadDispatchers;", "(Lcom/kdownloader/internal/DownloadDispatchers;)V", "idRequestMap", "Ljava/util/HashMap;", "", "Lcom/kdownloader/internal/DownloadRequest;", "Lkotlin/collections/HashMap;", "cancel", "", DownloadModel.ID, "tag", "", "cancelAll", "enqueue", "request", "pause", "resume", NotificationCompat.CATEGORY_STATUS, "Lcom/kdownloader/Status;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadRequestQueue {
    private final DownloadDispatchers downloader;
    private final HashMap<Integer, DownloadRequest> idRequestMap;

    public DownloadRequestQueue(DownloadDispatchers downloader) {
        Intrinsics.checkNotNullParameter(downloader, "downloader");
        this.downloader = downloader;
        this.idRequestMap = new HashMap<>();
    }

    public final int enqueue(DownloadRequest request) {
        Intrinsics.checkNotNullParameter(request, "request");
        this.idRequestMap.put(Integer.valueOf(request.getDownloadId()), request);
        return this.downloader.enqueue(request);
    }

    public final Status status(int id2) {
        DownloadRequest downloadRequest = this.idRequestMap.get(Integer.valueOf(id2));
        return downloadRequest == null ? Status.UNKNOWN : downloadRequest.getStatus();
    }

    public final void cancel(int id2) {
        DownloadRequest downloadRequest = this.idRequestMap.get(Integer.valueOf(id2));
        if (downloadRequest != null && downloadRequest.getStatus() != Status.CANCELLED) {
            this.downloader.cancel(downloadRequest);
        }
        this.idRequestMap.remove(Integer.valueOf(id2));
    }

    public final void cancel(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Collection<DownloadRequest> collectionValues = this.idRequestMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "idRequestMap.values");
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            if (Intrinsics.areEqual(((DownloadRequest) obj).getTag(), tag)) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            cancel(((DownloadRequest) it.next()).getDownloadId());
        }
    }

    public final void cancelAll() {
        this.idRequestMap.clear();
        this.downloader.cancelAll();
    }

    public final void pause(int id2) {
        DownloadRequest downloadRequest = this.idRequestMap.get(Integer.valueOf(id2));
        if (downloadRequest == null) {
            return;
        }
        downloadRequest.setStatus$library_release(Status.PAUSED);
    }

    public final void resume(int id2) {
        DownloadRequest downloadRequest = this.idRequestMap.get(Integer.valueOf(id2));
        if (downloadRequest == null) {
            return;
        }
        downloadRequest.setStatus$library_release(Status.QUEUED);
        this.downloader.enqueue(downloadRequest);
    }
}
