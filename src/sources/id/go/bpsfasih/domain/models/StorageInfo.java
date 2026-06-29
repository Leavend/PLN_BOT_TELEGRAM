package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: StorageInfo.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\f\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\r\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/domain/models/StorageInfo;", "", "()V", "storageAvail", "", "storageTotal", "storageUsage", "getStorageAvail", "getStorageTotal", "getStorageUsage", "setStorageAvail", "", "setStorageTotal", "setStorageUsage", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class StorageInfo {
    public static final int $stable = 8;
    private String storageAvail;
    private String storageTotal;
    private String storageUsage;

    public final String getStorageUsage() {
        return this.storageUsage;
    }

    public final void setStorageUsage(String storageUsage) {
        this.storageUsage = storageUsage;
    }

    public final String getStorageAvail() {
        return this.storageAvail;
    }

    public final void setStorageAvail(String storageAvail) {
        this.storageAvail = storageAvail;
    }

    public final String getStorageTotal() {
        return this.storageTotal;
    }

    public final void setStorageTotal(String storageTotal) {
        this.storageTotal = storageTotal;
    }
}
