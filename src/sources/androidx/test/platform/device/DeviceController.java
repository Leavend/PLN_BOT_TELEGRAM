package androidx.test.platform.device;

/* loaded from: classes5.dex */
public interface DeviceController {

    public enum ScreenOrientation {
        PORTRAIT,
        LANDSCAPE
    }

    void setDeviceMode(int deviceMode);

    void setScreenOrientation(int screenOrientation);
}
