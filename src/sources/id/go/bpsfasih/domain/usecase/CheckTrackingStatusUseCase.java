package id.go.bpsfasih.domain.usecase;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.utils.TrackingPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CheckTrackingStatusUseCase.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase;", "", "trackingPreferences", "Lid/go/bpsfasih/utils/TrackingPreferences;", "(Lid/go/bpsfasih/utils/TrackingPreferences;)V", "invoke", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus;", "context", "Landroid/content/Context;", "currentDate", "", "TrackingStatus", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CheckTrackingStatusUseCase {
    public static final int $stable = 0;
    private final TrackingPreferences trackingPreferences;

    public CheckTrackingStatusUseCase(TrackingPreferences trackingPreferences) {
        Intrinsics.checkNotNullParameter(trackingPreferences, "trackingPreferences");
        this.trackingPreferences = trackingPreferences;
    }

    public final TrackingStatus invoke(Context context, String currentDate) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(currentDate, "currentDate");
        boolean zIsTrackingActive = this.trackingPreferences.isTrackingActive(context);
        String trackingStartDate = this.trackingPreferences.getTrackingStartDate(context);
        if (zIsTrackingActive) {
            return !Intrinsics.areEqual(trackingStartDate, currentDate) ? TrackingStatus.ExpiredDate.INSTANCE : TrackingStatus.Active.INSTANCE;
        }
        return TrackingStatus.NotStarted.INSTANCE;
    }

    /* compiled from: CheckTrackingStatusUseCase.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus;", "", "()V", "Active", "ExpiredDate", "NotStarted", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$Active;", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$ExpiredDate;", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$NotStarted;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static abstract class TrackingStatus {
        public static final int $stable = 0;

        public /* synthetic */ TrackingStatus(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* compiled from: CheckTrackingStatusUseCase.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$NotStarted;", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus;", "()V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class NotStarted extends TrackingStatus {
            public static final int $stable = 0;
            public static final NotStarted INSTANCE = new NotStarted();

            private NotStarted() {
                super(null);
            }
        }

        private TrackingStatus() {
        }

        /* compiled from: CheckTrackingStatusUseCase.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$Active;", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus;", "()V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class Active extends TrackingStatus {
            public static final int $stable = 0;
            public static final Active INSTANCE = new Active();

            private Active() {
                super(null);
            }
        }

        /* compiled from: CheckTrackingStatusUseCase.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus$ExpiredDate;", "Lid/go/bpsfasih/domain/usecase/CheckTrackingStatusUseCase$TrackingStatus;", "()V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
        public static final class ExpiredDate extends TrackingStatus {
            public static final int $stable = 0;
            public static final ExpiredDate INSTANCE = new ExpiredDate();

            private ExpiredDate() {
                super(null);
            }
        }
    }
}
