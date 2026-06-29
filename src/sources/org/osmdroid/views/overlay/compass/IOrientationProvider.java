package org.osmdroid.views.overlay.compass;

/* loaded from: classes3.dex */
public interface IOrientationProvider {
    void destroy();

    float getLastKnownOrientation();

    boolean startOrientationProvider(IOrientationConsumer iOrientationConsumer);

    void stopOrientationProvider();
}
