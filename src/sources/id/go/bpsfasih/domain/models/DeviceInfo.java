package id.go.bpsfasih.domain.models;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.kdownloader.database.DownloadModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: DeviceInfo.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u000f\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0019\u001a\u00020\tJ\u0006\u0010\u001a\u001a\u00020\tJ\b\u0010\u001b\u001a\u0004\u0018\u00010\u0004J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0004J\b\u0010\u001d\u001a\u0004\u0018\u00010\u0004J\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004J\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004J\b\u0010 \u001a\u0004\u0018\u00010\u0004J\u0006\u0010!\u001a\u00020\u0012J\b\u0010\"\u001a\u0004\u0018\u00010\u0004J\b\u0010#\u001a\u0004\u0018\u00010\u0004J\u000e\u0010$\u001a\u00020%2\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010&\u001a\u00020%2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010'\u001a\u00020%2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u0010\u0010(\u001a\u00020%2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u000e\u0010)\u001a\u00020%2\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010*\u001a\u00020%2\u0006\u0010\n\u001a\u00020\tJ\u0010\u0010+\u001a\u00020%2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004J\u0010\u0010,\u001a\u00020%2\b\u0010\f\u001a\u0004\u0018\u00010\u0004J\u0010\u0010-\u001a\u00020%2\b\u0010\r\u001a\u0004\u0018\u00010\u0004J\u000e\u0010.\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u0004J\u0010\u0010/\u001a\u00020%2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0010\u00100\u001a\u00020%2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004J\u000e\u00101\u001a\u00020%2\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u00102\u001a\u00020%2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004J\u000e\u00103\u001a\u00020%2\u0006\u0010\u0014\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lid/go/bpsfasih/domain/models/DeviceInfo;", "", "()V", "androidVersion", "", "brand", "host", DownloadModel.ID, "isEmulator", "", "isRootDevice", "manufacture", "model", "serial", "storageUsedSize", "type", "user", ClientCookie.VERSION_ATTR, "", "versionRelease", "webviewVersion", "getAndroidVersion", "getBrand", "getHost", "getId", "getIsEmulator", "getIsRootDevice", "getManufacture", "getModel", "getSerial", "getStorageUsedSize", "getType", "getUser", "getVersion", "getVersionRelease", "getWebviewVersion", "setAndroidVersion", "", "setBrand", "setHost", "setId", "setIsEmulator", "setIsRootDevice", "setManufacture", "setModel", "setSerial", "setStorageUsedSize", "setType", "setUser", "setVersion", "setVersionRelease", "setWebviewVersion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DeviceInfo {
    public static final int $stable = 8;
    private String androidVersion;
    private String brand;
    private String host;
    private String id;
    private boolean isEmulator;
    private boolean isRootDevice;
    private String manufacture;
    private String model;
    private String serial;
    private String storageUsedSize;
    private String type;
    private String user;
    private int version;
    private String versionRelease;
    private String webviewVersion;

    public final void setAndroidVersion(String androidVersion) {
        Intrinsics.checkNotNullParameter(androidVersion, "androidVersion");
        this.androidVersion = androidVersion;
    }

    public final String getAndroidVersion() {
        return this.androidVersion;
    }

    public final String getBrand() {
        return this.brand;
    }

    public final void setBrand(String brand) {
        this.brand = brand;
    }

    public final String getSerial() {
        return this.serial;
    }

    public final void setSerial(String serial) {
        this.serial = serial;
    }

    public final String getModel() {
        return this.model;
    }

    public final void setModel(String model) {
        this.model = model;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String id2) {
        this.id = id2;
    }

    public final String getManufacture() {
        return this.manufacture;
    }

    public final void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String type) {
        this.type = type;
    }

    public final String getUser() {
        return this.user;
    }

    public final void setUser(String user) {
        this.user = user;
    }

    public final int getVersion() {
        return this.version;
    }

    public final void setVersion(int version) {
        this.version = version;
    }

    public final String getVersionRelease() {
        return this.versionRelease;
    }

    public final void setVersionRelease(String versionRelease) {
        this.versionRelease = versionRelease;
    }

    public final String getHost() {
        return this.host;
    }

    public final void setHost(String host) {
        this.host = host;
    }

    public final String getWebviewVersion() {
        return this.webviewVersion;
    }

    public final void setWebviewVersion(String webviewVersion) {
        Intrinsics.checkNotNullParameter(webviewVersion, "webviewVersion");
        this.webviewVersion = webviewVersion;
    }

    public final String getStorageUsedSize() {
        return this.storageUsedSize;
    }

    public final void setStorageUsedSize(String storageUsedSize) {
        Intrinsics.checkNotNullParameter(storageUsedSize, "storageUsedSize");
        this.storageUsedSize = storageUsedSize;
    }

    public final boolean getIsEmulator() {
        return this.isEmulator;
    }

    public final void setIsEmulator(boolean isEmulator) {
        this.isEmulator = isEmulator;
    }

    public final boolean getIsRootDevice() {
        return this.isRootDevice;
    }

    public final void setIsRootDevice(boolean isRootDevice) {
        this.isRootDevice = isRootDevice;
    }
}
