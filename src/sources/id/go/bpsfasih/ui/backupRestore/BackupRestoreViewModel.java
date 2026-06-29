package id.go.bpsfasih.ui.backupRestore;

import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.AndroidViewModel;
import id.go.bpsfasih.utils.SingleLiveEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackupRestoreViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013R\"\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\t\"\u0004\b\u000e\u0010\u000bR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/ui/backupRestore/BackupRestoreViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "backupDataIsClicked", "Lid/go/bpsfasih/utils/SingleLiveEvent;", "", "getBackupDataIsClicked", "()Lid/go/bpsfasih/utils/SingleLiveEvent;", "setBackupDataIsClicked", "(Lid/go/bpsfasih/utils/SingleLiveEvent;)V", "restoreDataIsClicked", "getRestoreDataIsClicked", "setRestoreDataIsClicked", "shareErrorIsClicked", "getShareErrorIsClicked", "setShareErrorIsClicked", "backupDataClicked", "", "restoreDataClicked", "shareErrorClicked", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BackupRestoreViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private SingleLiveEvent<Boolean> backupDataIsClicked;
    private SingleLiveEvent<Boolean> restoreDataIsClicked;
    private SingleLiveEvent<Boolean> shareErrorIsClicked;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackupRestoreViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.backupDataIsClicked = new SingleLiveEvent<>();
        this.restoreDataIsClicked = new SingleLiveEvent<>();
        this.shareErrorIsClicked = new SingleLiveEvent<>();
    }

    public final SingleLiveEvent<Boolean> getBackupDataIsClicked() {
        return this.backupDataIsClicked;
    }

    public final void setBackupDataIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.backupDataIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getRestoreDataIsClicked() {
        return this.restoreDataIsClicked;
    }

    public final void setRestoreDataIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.restoreDataIsClicked = singleLiveEvent;
    }

    public final SingleLiveEvent<Boolean> getShareErrorIsClicked() {
        return this.shareErrorIsClicked;
    }

    public final void setShareErrorIsClicked(SingleLiveEvent<Boolean> singleLiveEvent) {
        this.shareErrorIsClicked = singleLiveEvent;
    }

    public final void backupDataClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.backupDataIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void restoreDataClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.restoreDataIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }

    public final void shareErrorClicked() {
        SingleLiveEvent<Boolean> singleLiveEvent = this.shareErrorIsClicked;
        if (singleLiveEvent == null) {
            return;
        }
        singleLiveEvent.setValue(true);
    }
}
