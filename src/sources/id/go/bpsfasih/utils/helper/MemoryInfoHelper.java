package id.go.bpsfasih.utils.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryInfoHelper.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/utils/helper/MemoryInfoHelper;", "", "()V", "getMemoryInfo", "Lid/go/bpsfasih/domain/models/MemoryInfo;", "context", "Landroid/content/Context;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MemoryInfoHelper {
    public static final int $stable = 0;
    public static final MemoryInfoHelper INSTANCE = new MemoryInfoHelper();

    private MemoryInfoHelper() {
    }

    public final id.go.bpsfasih.domain.models.MemoryInfo getMemoryInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            Object systemService = context.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ((ActivityManager) systemService).getMemoryInfo(memoryInfo);
            Unit unit = Unit.INSTANCE;
            long j = memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            long j2 = memoryInfo.totalMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            long j3 = (memoryInfo.totalMem - memoryInfo.availMem) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            id.go.bpsfasih.domain.models.MemoryInfo memoryInfo2 = new id.go.bpsfasih.domain.models.MemoryInfo();
            memoryInfo2.setMemoryAvail(String.valueOf(j));
            memoryInfo2.setMemoryTotal(String.valueOf(j2));
            memoryInfo2.setMemoryUsage(String.valueOf(j3));
            return memoryInfo2;
        } catch (Exception unused) {
            return null;
        }
    }
}
