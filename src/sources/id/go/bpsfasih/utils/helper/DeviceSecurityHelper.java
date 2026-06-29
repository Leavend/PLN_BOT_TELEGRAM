package id.go.bpsfasih.utils.helper;

import android.os.Build;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.os.EnvironmentCompat;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceSecurityHelper.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJS\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b\u0010J\u0006\u0010\u0011\u001a\u00020\bJ+\u0010\u0011\u001a\u00020\b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0014H\u0000¢\u0006\u0002\b\u0015R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/utils/helper/DeviceSecurityHelper;", "", "()V", "ROOT_PATHS", "", "", "[Ljava/lang/String;", "isEmulator", "", "fingerprint", "model", "manufacturer", "brand", "device", "product", "hardware", "isEmulator$app_release", "isRooted", "tags", "fileExists", "Lkotlin/Function1;", "isRooted$app_release", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DeviceSecurityHelper {
    public static final DeviceSecurityHelper INSTANCE = new DeviceSecurityHelper();
    private static final String[] ROOT_PATHS = {"/system/app/Superuser.apk", "/system/app/SuperSU.apk", "/system/app/Magisk.apk", "/system/app/MagiskManager.apk", "/system/bin/su", "/system/xbin/su", "/sbin/su", "/system/su", "/system/bin/.ext/.su", "/system/usr/we-need-root/su", "/system/xbin/daemonsu"};
    public static final int $stable = 8;

    private DeviceSecurityHelper() {
    }

    public final boolean isEmulator() {
        return isEmulator$app_release(Build.FINGERPRINT, Build.MODEL, Build.MANUFACTURER, Build.BRAND, Build.DEVICE, Build.PRODUCT, Build.HARDWARE);
    }

    public final boolean isRooted() {
        return isRooted$app_release(Build.TAGS, new Function1<String, Boolean>() { // from class: id.go.bpsfasih.utils.helper.DeviceSecurityHelper.isRooted.1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(String path) {
                Intrinsics.checkNotNullParameter(path, "path");
                return Boolean.valueOf(new File(path).exists());
            }
        });
    }

    public final boolean isEmulator$app_release(String fingerprint, String model, String manufacturer, String brand, String device, String product, String hardware) {
        if (fingerprint == null) {
            fingerprint = "";
        }
        if (model == null) {
            model = "";
        }
        if (manufacturer == null) {
            manufacturer = "";
        }
        if (brand == null) {
            brand = "";
        }
        if (device == null) {
            device = "";
        }
        if (product == null) {
            product = "";
        }
        if (hardware == null) {
            hardware = "";
        }
        if (StringsKt.startsWith(fingerprint, "generic", true) || StringsKt.startsWith(fingerprint, EnvironmentCompat.MEDIA_UNKNOWN, true)) {
            return true;
        }
        String str = model;
        if (StringsKt.contains((CharSequence) str, (CharSequence) "google_sdk", true) || StringsKt.contains((CharSequence) str, (CharSequence) "emulator", true) || StringsKt.contains((CharSequence) str, (CharSequence) "android sdk built for x86", true) || StringsKt.contains((CharSequence) str, (CharSequence) "android sdk built for x86_64", true) || StringsKt.contains((CharSequence) manufacturer, (CharSequence) "genymotion", true)) {
            return true;
        }
        if (StringsKt.startsWith(brand, "generic", true) && StringsKt.startsWith(device, "generic", true)) {
            return true;
        }
        String str2 = product;
        if (StringsKt.contains((CharSequence) str2, (CharSequence) "google_sdk", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "sdk_gphone", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "sdk", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "sdk_x86", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "vbox86p", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "emulator", true) || StringsKt.contains((CharSequence) str2, (CharSequence) "simulator", true)) {
            return true;
        }
        String str3 = hardware;
        return StringsKt.contains((CharSequence) str3, (CharSequence) "goldfish", true) || StringsKt.contains((CharSequence) str3, (CharSequence) "ranchu", true) || StringsKt.contains((CharSequence) str3, (CharSequence) "vbox86", true);
    }

    public final boolean isRooted$app_release(String tags, Function1<? super String, Boolean> fileExists) {
        Intrinsics.checkNotNullParameter(fileExists, "fileExists");
        if (tags != null && StringsKt.contains$default((CharSequence) tags, (CharSequence) "test-keys", false, 2, (Object) null)) {
            return true;
        }
        for (String str : ROOT_PATHS) {
            if (fileExists.invoke(str).booleanValue()) {
                return true;
            }
        }
        return false;
    }
}
