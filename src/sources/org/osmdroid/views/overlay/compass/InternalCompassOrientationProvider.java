package org.osmdroid.views.overlay.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/* loaded from: classes3.dex */
public class InternalCompassOrientationProvider implements SensorEventListener, IOrientationProvider {
    private float mAzimuth;
    private IOrientationConsumer mOrientationConsumer;
    private SensorManager mSensorManager;

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public InternalCompassOrientationProvider(Context context) {
        this.mSensorManager = (SensorManager) context.getSystemService("sensor");
    }

    @Override // org.osmdroid.views.overlay.compass.IOrientationProvider
    public boolean startOrientationProvider(IOrientationConsumer iOrientationConsumer) {
        this.mOrientationConsumer = iOrientationConsumer;
        Sensor defaultSensor = this.mSensorManager.getDefaultSensor(3);
        if (defaultSensor != null) {
            return this.mSensorManager.registerListener(this, defaultSensor, 2);
        }
        return false;
    }

    @Override // org.osmdroid.views.overlay.compass.IOrientationProvider
    public void stopOrientationProvider() {
        this.mOrientationConsumer = null;
        this.mSensorManager.unregisterListener(this);
    }

    @Override // org.osmdroid.views.overlay.compass.IOrientationProvider
    public float getLastKnownOrientation() {
        return this.mAzimuth;
    }

    @Override // org.osmdroid.views.overlay.compass.IOrientationProvider
    public void destroy() {
        stopOrientationProvider();
        this.mOrientationConsumer = null;
        this.mSensorManager = null;
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() != 3 || sensorEvent.values == null) {
            return;
        }
        float f = sensorEvent.values[0];
        this.mAzimuth = f;
        IOrientationConsumer iOrientationConsumer = this.mOrientationConsumer;
        if (iOrientationConsumer != null) {
            iOrientationConsumer.onOrientationChanged(f, this);
        }
    }
}
