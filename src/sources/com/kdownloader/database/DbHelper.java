package com.kdownloader.database;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: DbHelper.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ!\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000fJ)\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0017"}, d2 = {"Lcom/kdownloader/database/DbHelper;", "", "empty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "find", "Lcom/kdownloader/database/DownloadModel;", DownloadModel.ID, "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnwantedModels", "", "days", "insert", "model", "(Lcom/kdownloader/database/DownloadModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "remove", "update", "updateProgress", "downloadedBytes", "", "lastModifiedAt", "(IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface DbHelper {
    Object empty(Continuation<? super Unit> continuation);

    Object find(int i, Continuation<? super DownloadModel> continuation);

    Object getUnwantedModels(int i, Continuation<? super List<DownloadModel>> continuation);

    Object insert(DownloadModel downloadModel, Continuation<? super Unit> continuation);

    Object remove(int i, Continuation<? super Unit> continuation);

    Object update(DownloadModel downloadModel, Continuation<? super Unit> continuation);

    Object updateProgress(int i, long j, long j2, Continuation<? super Unit> continuation);
}
