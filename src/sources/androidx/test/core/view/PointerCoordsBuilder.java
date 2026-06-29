package androidx.test.core.view;

import android.view.MotionEvent;

/* loaded from: classes5.dex */
public class PointerCoordsBuilder {
    private float orientation;
    private float toolMajor;
    private float toolMinor;
    private float touchMajor;
    private float touchMinor;
    private float x = 0.0f;
    private float y = 0.0f;
    private float pressure = 1.0f;
    private float size = 1.0f;

    private PointerCoordsBuilder() {
    }

    public static PointerCoordsBuilder newBuilder() {
        return new PointerCoordsBuilder();
    }

    public PointerCoordsBuilder setCoords(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public PointerCoordsBuilder setPressure(float pressure) {
        this.pressure = pressure;
        return this;
    }

    public PointerCoordsBuilder setSize(float size) {
        this.size = size;
        return this;
    }

    public PointerCoordsBuilder setTouch(float touchMajor, float touchMinor) {
        this.touchMajor = touchMajor;
        this.touchMinor = touchMinor;
        return this;
    }

    public PointerCoordsBuilder setTool(float toolMajor, float toolMinor) {
        this.toolMajor = toolMajor;
        this.toolMinor = toolMinor;
        return this;
    }

    public PointerCoordsBuilder setOrientation(float orientation) {
        this.orientation = orientation;
        return this;
    }

    public MotionEvent.PointerCoords build() {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = this.x;
        pointerCoords.y = this.y;
        pointerCoords.pressure = this.pressure;
        pointerCoords.size = this.size;
        pointerCoords.touchMajor = this.touchMajor;
        pointerCoords.touchMinor = this.touchMinor;
        pointerCoords.toolMajor = this.toolMajor;
        pointerCoords.toolMinor = this.toolMinor;
        pointerCoords.orientation = this.orientation;
        return pointerCoords;
    }
}
