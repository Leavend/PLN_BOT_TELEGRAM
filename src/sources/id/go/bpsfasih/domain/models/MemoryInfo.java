package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: MemoryInfo.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\b\u0010\b\u001a\u0004\u0018\u00010\u0004J\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\f\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\r\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lid/go/bpsfasih/domain/models/MemoryInfo;", "", "()V", "memoryAvail", "", "memoryTotal", "memoryUsage", "getMemoryAvail", "getMemoryTotal", "getMemoryUsage", "setMemoryAvail", "", "setMemoryTotal", "setMemoryUsage", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MemoryInfo {
    public static final int $stable = 8;
    private String memoryAvail;
    private String memoryTotal;
    private String memoryUsage;

    public final String getMemoryUsage() {
        return this.memoryUsage;
    }

    public final void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public final String getMemoryAvail() {
        return this.memoryAvail;
    }

    public final void setMemoryAvail(String memoryAvail) {
        this.memoryAvail = memoryAvail;
    }

    public final String getMemoryTotal() {
        return this.memoryTotal;
    }

    public final void setMemoryTotal(String memoryTotal) {
        this.memoryTotal = memoryTotal;
    }
}
