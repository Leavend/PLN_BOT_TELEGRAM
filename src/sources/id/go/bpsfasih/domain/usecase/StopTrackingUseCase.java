package id.go.bpsfasih.domain.usecase;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.utils.TrackingPreferences;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StopTrackingUseCase.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J'\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0086\u0002ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/domain/usecase/StopTrackingUseCase;", "", "trackingPreferences", "Lid/go/bpsfasih/utils/TrackingPreferences;", "(Lid/go/bpsfasih/utils/TrackingPreferences;)V", "invoke", "Lkotlin/Result;", "", "context", "Landroid/content/Context;", "invoke-IoAF18A", "(Landroid/content/Context;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class StopTrackingUseCase {
    public static final int $stable = 0;
    private final TrackingPreferences trackingPreferences;

    public StopTrackingUseCase(TrackingPreferences trackingPreferences) {
        Intrinsics.checkNotNullParameter(trackingPreferences, "trackingPreferences");
        this.trackingPreferences = trackingPreferences;
    }

    /* renamed from: invoke-IoAF18A, reason: not valid java name */
    public final Object m6679invokeIoAF18A(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            this.trackingPreferences.setTrackingActive(context, false);
            this.trackingPreferences.endTrackingSession(context);
            Result.Companion companion = Result.INSTANCE;
            return Result.m6852constructorimpl(Unit.INSTANCE);
        } catch (Exception e) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m6852constructorimpl(ResultKt.createFailure(e));
        }
    }
}
