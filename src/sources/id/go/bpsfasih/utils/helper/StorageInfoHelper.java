package id.go.bpsfasih.utils.helper;

import android.os.Environment;
import android.os.StatFs;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.time.DurationKt;

/* compiled from: StorageInfoHelper.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/utils/helper/StorageInfoHelper;", "", "()V", "getStorageInfo", "Lid/go/bpsfasih/domain/models/StorageInfo;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class StorageInfoHelper {
    public static final int $stable = 0;
    public static final StorageInfoHelper INSTANCE = new StorageInfoHelper();

    private StorageInfoHelper() {
    }

    public final id.go.bpsfasih.domain.models.StorageInfo getStorageInfo() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            long availableBytes = statFs.getAvailableBytes();
            long j = DurationKt.NANOS_IN_MILLIS;
            long j2 = availableBytes / j;
            long totalBytes = statFs.getTotalBytes() / j;
            id.go.bpsfasih.domain.models.StorageInfo storageInfo = new id.go.bpsfasih.domain.models.StorageInfo();
            storageInfo.setStorageAvail(String.valueOf(j2));
            storageInfo.setStorageTotal(String.valueOf(totalBytes));
            storageInfo.setStorageUsage(String.valueOf(totalBytes - j2));
            return storageInfo;
        } catch (Exception unused) {
            return null;
        }
    }
}
