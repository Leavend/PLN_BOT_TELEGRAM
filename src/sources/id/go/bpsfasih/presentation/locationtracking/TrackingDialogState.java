package id.go.bpsfasih.presentation.locationtracking;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationTrackingUiState.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lid/go/bpsfasih/presentation/locationtracking/TrackingDialogState;", "", "shouldShow", "", "currentDate", "", "(ZLjava/lang/String;)V", "getCurrentDate", "()Ljava/lang/String;", "getShouldShow", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class TrackingDialogState {
    public static final int $stable = 0;
    private final String currentDate;
    private final boolean shouldShow;

    /* JADX WARN: Multi-variable type inference failed */
    public TrackingDialogState() {
        this(false, null, 3, 0 == true ? 1 : 0);
    }

    public static /* synthetic */ TrackingDialogState copy$default(TrackingDialogState trackingDialogState, boolean z, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = trackingDialogState.shouldShow;
        }
        if ((i & 2) != 0) {
            str = trackingDialogState.currentDate;
        }
        return trackingDialogState.copy(z, str);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getShouldShow() {
        return this.shouldShow;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCurrentDate() {
        return this.currentDate;
    }

    public final TrackingDialogState copy(boolean shouldShow, String currentDate) {
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        return new TrackingDialogState(shouldShow, currentDate);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackingDialogState)) {
            return false;
        }
        TrackingDialogState trackingDialogState = (TrackingDialogState) other;
        return this.shouldShow == trackingDialogState.shouldShow && Intrinsics.areEqual(this.currentDate, trackingDialogState.currentDate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    public int hashCode() {
        boolean z = this.shouldShow;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (r0 * 31) + this.currentDate.hashCode();
    }

    public String toString() {
        return "TrackingDialogState(shouldShow=" + this.shouldShow + ", currentDate=" + this.currentDate + ")";
    }

    public TrackingDialogState(boolean z, String currentDate) {
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        this.shouldShow = z;
        this.currentDate = currentDate;
    }

    public /* synthetic */ TrackingDialogState(boolean z, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? "" : str);
    }

    public final boolean getShouldShow() {
        return this.shouldShow;
    }

    public final String getCurrentDate() {
        return this.currentDate;
    }
}
