package androidx.test.core.view;

import android.os.SystemClock;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MotionEventBuilder {
    private long downTime = 0;
    private long eventTime = SystemClock.uptimeMillis();
    private int action = 0;
    private int actionIndex = -1;
    private List<MotionEvent.PointerProperties> pointerPropertiesList = new ArrayList();
    private List<MotionEvent.PointerCoords> pointerCoordsList = new ArrayList();
    private int metaState = 0;
    private int buttonState = 0;
    private float xPrecision = 0.0f;
    private float yPrecision = 0.0f;
    private int deviceId = 0;
    private int edgeFlags = 0;
    private int source = 0;
    private int flags = 0;

    private MotionEventBuilder() {
    }

    public static MotionEventBuilder newBuilder() {
        return new MotionEventBuilder();
    }

    public MotionEventBuilder setDownTime(long downTime) {
        this.downTime = downTime;
        return this;
    }

    public MotionEventBuilder setEventTime(long eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public MotionEventBuilder setAction(int action) {
        this.action = action;
        return this;
    }

    public MotionEventBuilder setActionIndex(int pointerIndex) {
        checkState(pointerIndex <= 255, "pointerIndex must be less than 0xff");
        this.actionIndex = pointerIndex;
        return this;
    }

    public MotionEventBuilder setMetaState(int metastate) {
        this.metaState = metastate;
        return this;
    }

    public MotionEventBuilder setButtonState(int buttonState) {
        this.buttonState = buttonState;
        return this;
    }

    public MotionEventBuilder setXPrecision(float xPrecision) {
        this.xPrecision = xPrecision;
        return this;
    }

    public MotionEventBuilder setYPrecision(float yPrecision) {
        this.yPrecision = yPrecision;
        return this;
    }

    public MotionEventBuilder setDeviceId(int deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public MotionEventBuilder setEdgeFlags(int edgeFlags) {
        this.edgeFlags = edgeFlags;
        return this;
    }

    public MotionEventBuilder setSource(int source) {
        this.source = source;
        return this;
    }

    public MotionEventBuilder setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public MotionEventBuilder setPointer(float x, float y) {
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = this.pointerPropertiesList.size();
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = x;
        pointerCoords.y = y;
        return setPointer(pointerProperties, pointerCoords);
    }

    public MotionEventBuilder setPointer(MotionEvent.PointerProperties pointerProperties, MotionEvent.PointerCoords pointerCoords) {
        this.pointerPropertiesList.add(pointerProperties);
        this.pointerCoordsList.add(pointerCoords);
        return this;
    }

    public MotionEvent build() {
        if (this.pointerPropertiesList.size() == 0) {
            setPointer(0.0f, 0.0f);
        }
        int i = this.actionIndex;
        if (i != -1) {
            this.action = (i << 8) | this.action;
        }
        long j = this.downTime;
        long j2 = this.eventTime;
        int i2 = this.action;
        int size = this.pointerPropertiesList.size();
        List<MotionEvent.PointerProperties> list = this.pointerPropertiesList;
        MotionEvent.PointerProperties[] pointerPropertiesArr = (MotionEvent.PointerProperties[]) list.toArray(new MotionEvent.PointerProperties[list.size()]);
        List<MotionEvent.PointerCoords> list2 = this.pointerCoordsList;
        return MotionEvent.obtain(j, j2, i2, size, pointerPropertiesArr, (MotionEvent.PointerCoords[]) list2.toArray(new MotionEvent.PointerCoords[list2.size()]), this.metaState, this.buttonState, this.xPrecision, this.yPrecision, this.deviceId, this.edgeFlags, this.source, this.flags);
    }

    private static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }
}
