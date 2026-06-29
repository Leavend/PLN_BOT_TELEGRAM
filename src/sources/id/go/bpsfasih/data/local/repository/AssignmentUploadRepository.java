package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.dao.AssignmentUploadDAO;
import id.go.bpsfasih.data.local.entities.AssignmentUploadEntity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AssignmentUploadRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000bJ\u0010\u0010\r\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0014\u0010\u000e\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\fJ\u0010\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/data/local/repository/AssignmentUploadRepository;", "", "dao", "Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;", "(Lid/go/bpsfasih/data/local/dao/AssignmentUploadDAO;)V", "delete", "", "item", "Lid/go/bpsfasih/data/local/entities/AssignmentUploadEntity;", "deleteAll", "getListUploadByUser", "Landroidx/lifecycle/LiveData;", "", "insert", "insertAll", "listItem", "updateUploadSuccessful", "assigmentId", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentUploadRepository {
    public static final int $stable = 8;
    private final AssignmentUploadDAO dao;

    public AssignmentUploadRepository(AssignmentUploadDAO dao) {
        Intrinsics.checkNotNullParameter(dao, "dao");
        this.dao = dao;
    }

    public final LiveData<List<AssignmentUploadEntity>> getListUploadByUser() {
        FasihApp.INSTANCE.getSession().getUserId();
        return this.dao.getAllItem();
    }

    public final void insert(final AssignmentUploadEntity item) {
        if (item != null) {
            AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentUploadRepository$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentUploadRepository.insert$lambda$0(this.f$0, item);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$0(AssignmentUploadRepository this$0, AssignmentUploadEntity assignmentUploadEntity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dao.insert(assignmentUploadEntity);
    }

    public final void delete(final AssignmentUploadEntity item) {
        Intrinsics.checkNotNullParameter(item, "item");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentUploadRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUploadRepository.delete$lambda$1(this.f$0, item);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$1(AssignmentUploadRepository this$0, AssignmentUploadEntity item) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.dao.deleteItem(item);
    }

    public final void deleteAll() {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentUploadRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUploadRepository.deleteAll$lambda$2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAll$lambda$2(AssignmentUploadRepository this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dao.deleteAll();
    }

    public final void updateUploadSuccessful(final String assigmentId) {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentUploadRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUploadRepository.updateUploadSuccessful$lambda$3(this.f$0, assigmentId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateUploadSuccessful$lambda$3(AssignmentUploadRepository this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AssignmentUploadDAO assignmentUploadDAO = this$0.dao;
        Intrinsics.checkNotNull(str);
        assignmentUploadDAO.isUploadSuccessful(str);
    }

    public final void insertAll(final List<AssignmentUploadEntity> listItem) {
        Intrinsics.checkNotNullParameter(listItem, "listItem");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentUploadRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentUploadRepository.insertAll$lambda$4(this.f$0, listItem);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAll$lambda$4(AssignmentUploadRepository this$0, List listItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(listItem, "$listItem");
        this$0.dao.insertAll(listItem);
    }
}
