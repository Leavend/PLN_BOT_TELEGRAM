package id.go.bpsfasih.utils.helper;

import android.os.SystemClock;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThrottledOnClicklistener.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B(\u0012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0002\u0010\tJ\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/utils/helper/ThrottledOnClicklistener;", "Landroid/view/View$OnClickListener;", "onClicks", "Lkotlin/Function1;", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "", "(Lkotlin/jvm/functions/Function1;)V", "lastClickMap", "", "", "onClick", "clickedView", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ThrottledOnClicklistener implements View.OnClickListener {
    public static final int $stable = 8;
    private final Map<View, Long> lastClickMap;
    private final Function1<View, Unit> onClicks;

    /* JADX WARN: Multi-variable type inference failed */
    public ThrottledOnClicklistener(Function1<? super View, Unit> onClicks) {
        Intrinsics.checkNotNullParameter(onClicks, "onClicks");
        this.onClicks = onClicks;
        this.lastClickMap = new WeakHashMap();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View clickedView) {
        Intrinsics.checkNotNullParameter(clickedView, "clickedView");
        Long l = this.lastClickMap.get(clickedView);
        long jUptimeMillis = SystemClock.uptimeMillis();
        this.lastClickMap.put(clickedView, Long.valueOf(jUptimeMillis));
        if (l == null || jUptimeMillis - l.longValue() > 1000) {
            this.onClicks.invoke(clickedView);
        }
    }
}
