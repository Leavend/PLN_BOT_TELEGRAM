package id.go.bpsfasih.ui.memoryInfo;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.utils.helper.MemoryInfo;
import id.go.bpsfasih.utils.helper.StorageInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryInfoViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\rR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\r¨\u0006\u0018"}, d2 = {"Lid/go/bpsfasih/ui/memoryInfo/MemoryInfoViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "memoryAvail", "Landroidx/databinding/ObservableField;", "", "getMemoryAvail", "()Landroidx/databinding/ObservableField;", "memoryTotal", "getMemoryTotal", "memoryUsage", "getMemoryUsage", "storageAvail", "getStorageAvail", "storageTotal", "getStorageTotal", "storageUsage", "getStorageUsage", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MemoryInfoViewModel extends AndroidViewModel {
    public static final int $stable = 8;
    private final Context context;
    private final ObservableField<String> memoryAvail;
    private final ObservableField<String> memoryTotal;
    private final ObservableField<String> memoryUsage;
    private final ObservableField<String> storageAvail;
    private final ObservableField<String> storageTotal;
    private final ObservableField<String> storageUsage;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MemoryInfoViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.context = FasihApp.INSTANCE.getContext();
        this.storageUsage = new ObservableField<>(StorageInfo.INSTANCE.getInfo().getUsage() + " MB");
        this.storageAvail = new ObservableField<>(StorageInfo.INSTANCE.getInfo().getAvailable() + " MB");
        this.storageTotal = new ObservableField<>(StorageInfo.INSTANCE.getInfo().getTotal() + " MB");
        Application application2 = application;
        this.memoryUsage = new ObservableField<>(MemoryInfo.INSTANCE.getInfo(application2).getUsage() + " MB");
        this.memoryAvail = new ObservableField<>(MemoryInfo.INSTANCE.getInfo(application2).getAvailable() + " MB");
        this.memoryTotal = new ObservableField<>(MemoryInfo.INSTANCE.getInfo(application2).getTotal() + " MB");
    }

    public final Context getContext() {
        return this.context;
    }

    public final ObservableField<String> getStorageUsage() {
        return this.storageUsage;
    }

    public final ObservableField<String> getStorageAvail() {
        return this.storageAvail;
    }

    public final ObservableField<String> getStorageTotal() {
        return this.storageTotal;
    }

    public final ObservableField<String> getMemoryUsage() {
        return this.memoryUsage;
    }

    public final ObservableField<String> getMemoryAvail() {
        return this.memoryAvail;
    }

    public final ObservableField<String> getMemoryTotal() {
        return this.memoryTotal;
    }
}
