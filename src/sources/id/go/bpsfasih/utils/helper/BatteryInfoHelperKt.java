package id.go.bpsfasih.utils.helper;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BatteryInfoHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"getPerformanceMetricsJson", "", "Landroid/content/Context;", "app_release"}, k = 2, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class BatteryInfoHelperKt {
    public static final String getPerformanceMetricsJson(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return BatteryInfoHelper.INSTANCE.getPerformanceMetricsJson(context);
    }
}
