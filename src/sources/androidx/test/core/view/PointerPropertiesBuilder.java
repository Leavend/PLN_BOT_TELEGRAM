package androidx.test.core.view;

import android.view.MotionEvent;

/* loaded from: classes5.dex */
public class PointerPropertiesBuilder {

    /* renamed from: id, reason: collision with root package name */
    private int f81id;
    private int toolType;

    private PointerPropertiesBuilder() {
    }

    public PointerPropertiesBuilder setId(int id2) {
        this.f81id = id2;
        return this;
    }

    public PointerPropertiesBuilder setToolType(int toolType) {
        this.toolType = toolType;
        return this;
    }

    public MotionEvent.PointerProperties build() {
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = this.f81id;
        pointerProperties.toolType = this.toolType;
        return pointerProperties;
    }

    public static PointerPropertiesBuilder newBuilder() {
        return new PointerPropertiesBuilder();
    }
}
