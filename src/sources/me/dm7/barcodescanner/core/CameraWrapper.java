package me.dm7.barcodescanner.core;

import android.hardware.Camera;

/* loaded from: classes3.dex */
public class CameraWrapper {
    public final Camera mCamera;
    public final int mCameraId;

    private CameraWrapper(Camera camera, int i) {
        if (camera == null) {
            throw new NullPointerException("Camera cannot be null");
        }
        this.mCamera = camera;
        this.mCameraId = i;
    }

    public static CameraWrapper getWrapper(Camera camera, int i) {
        if (camera == null) {
            return null;
        }
        return new CameraWrapper(camera, i);
    }
}
