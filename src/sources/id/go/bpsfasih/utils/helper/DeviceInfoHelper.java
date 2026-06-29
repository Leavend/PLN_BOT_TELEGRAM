package id.go.bpsfasih.utils.helper;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.domain.models.DeviceInfo;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.time.DurationKt;

/* compiled from: DeviceInfoHelper.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/utils/helper/DeviceInfoHelper;", "", "()V", "getDeviceInfo", "Lid/go/bpsfasih/domain/models/DeviceInfo;", "getDeviceInfoBantuan", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DeviceInfoHelper {
    public static final int $stable = 0;
    public static final DeviceInfoHelper INSTANCE = new DeviceInfoHelper();

    private DeviceInfoHelper() {
    }

    public final DeviceInfo getDeviceInfo() {
        try {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setBrand(Build.BRAND);
            deviceInfo.setHost(Build.HOST);
            deviceInfo.setId(Build.ID);
            deviceInfo.setManufacture(Build.MANUFACTURER);
            deviceInfo.setModel(Build.MODEL);
            deviceInfo.setSerial(Build.SERIAL);
            deviceInfo.setType(Build.TYPE);
            deviceInfo.setUser(Build.USER);
            deviceInfo.setVersionRelease("2.16.3 - 126");
            deviceInfo.setAndroidVersion(Build.VERSION.RELEASE + " - " + Build.VERSION.SDK_INT);
            deviceInfo.setIsRootDevice(DeviceSecurityHelper.INSTANCE.isRooted());
            deviceInfo.setIsEmulator(DeviceSecurityHelper.INSTANCE.isEmulator());
            return deviceInfo;
        } catch (Exception unused) {
            return null;
        }
    }

    public final DeviceInfo getDeviceInfoBantuan() {
        DeviceInfo deviceInfo = getDeviceInfo();
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("deviceinfo_wv_version_storage_used")) {
            try {
                long jSumOfLong = SequencesKt.sumOfLong(SequencesKt.map(FilesKt.walkTopDown(new File(FileHelper.INSTANCE.pathExternalStorage())), new Function1<File, Long>() { // from class: id.go.bpsfasih.utils.helper.DeviceInfoHelper$getDeviceInfoBantuan$size$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Long invoke(File it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        return Long.valueOf(it.length());
                    }
                }));
                if (deviceInfo != null) {
                    deviceInfo.setStorageUsedSize((jSumOfLong / DurationKt.NANOS_IN_MILLIS) + " MB");
                }
                if (Build.VERSION.SDK_INT >= 26 && deviceInfo != null) {
                    PackageInfo currentWebViewPackage = WebView.getCurrentWebViewPackage();
                    deviceInfo.setWebviewVersion(String.valueOf(currentWebViewPackage != null ? currentWebViewPackage.versionName : null));
                }
            } catch (IOException unused) {
                if (deviceInfo != null) {
                    deviceInfo.setStorageUsedSize("Error get storage used | IOException");
                }
            } catch (Exception unused2) {
                if (deviceInfo != null) {
                    deviceInfo.setStorageUsedSize("Error get storage used");
                }
            }
        }
        return deviceInfo;
    }
}
