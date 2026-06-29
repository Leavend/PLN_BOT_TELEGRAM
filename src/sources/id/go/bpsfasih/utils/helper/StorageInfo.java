package id.go.bpsfasih.utils.helper;

import android.os.Environment;
import android.os.StatFs;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.models.StorageModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.time.DurationKt;

/* compiled from: StorageInfoHelper.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/helper/StorageInfo;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class StorageInfo {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: StorageInfoHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/utils/helper/StorageInfo$Companion;", "", "()V", "getInfo", "Lid/go/bpsfasih/domain/models/StorageModel;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StorageModel getInfo() {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long availableBytes = statFs.getAvailableBytes();
            long j = DurationKt.NANOS_IN_MILLIS;
            long j2 = availableBytes / j;
            long totalBytes = statFs.getTotalBytes() / j;
            return new StorageModel(j2, totalBytes, totalBytes - j2, (long) (j2 / 0.5d), j2 / 10, j2 / 15);
        }
    }
}
