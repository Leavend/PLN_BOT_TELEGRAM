package me.dm7.barcodescanner.core;

import android.hardware.Camera;
import java.util.List;
import kotlinx.coroutines.DebugKt;

/* loaded from: classes3.dex */
public class CameraUtils {
    public static Camera getCameraInstance() {
        return getCameraInstance(getDefaultCameraId());
    }

    public static int getDefaultCameraId() {
        int numberOfCameras = Camera.getNumberOfCameras();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i = -1;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            int i4 = i;
            i = i3;
            if (i >= numberOfCameras) {
                return i4;
            }
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 0) {
                return i;
            }
            i2 = i + 1;
        }
    }

    public static Camera getCameraInstance(int i) {
        Camera cameraOpen;
        try {
            if (i == -1) {
                cameraOpen = Camera.open();
            } else {
                cameraOpen = Camera.open(i);
            }
            return cameraOpen;
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean isFlashSupported(Camera camera) {
        List<String> supportedFlashModes;
        if (camera != null) {
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getFlashMode() != null && (supportedFlashModes = parameters.getSupportedFlashModes()) != null && !supportedFlashModes.isEmpty() && (supportedFlashModes.size() != 1 || !supportedFlashModes.get(0).equals(DebugKt.DEBUG_PROPERTY_VALUE_OFF))) {
                return true;
            }
        }
        return false;
    }
}
