package com.kdownloader.database;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: NoOpsDbHelper.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0003\u001a\u00020\u0004H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0019\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0007H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J)\u0010\u0013\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0015H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/kdownloader/database/NoOpsDbHelper;", "Lcom/kdownloader/database/DbHelper;", "()V", "empty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "find", "Lcom/kdownloader/database/DownloadModel;", DownloadModel.ID, "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnwantedModels", "", "days", "insert", "model", "(Lcom/kdownloader/database/DownloadModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "remove", "update", "updateProgress", "downloadedBytes", "", "lastModifiedAt", "(IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class NoOpsDbHelper implements DbHelper {
    @Override // com.kdownloader.database.DbHelper
    public Object find(int i, Continuation<? super DownloadModel> continuation) {
        return null;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object getUnwantedModels(int i, Continuation<? super List<DownloadModel>> continuation) {
        return null;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object insert(DownloadModel downloadModel, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object update(DownloadModel downloadModel, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object updateProgress(int i, long j, long j2, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object remove(int i, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object empty(Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }
}
