package androidx.test.espresso;

import android.util.Log;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.platform.io.PlatformTestStorage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public final class GraphHolder {
    private static final String TAG = "GraphHolder";
    private static final AtomicReference<GraphHolder> instance = new AtomicReference<>(null);
    private final BaseLayerComponent component;

    private GraphHolder(BaseLayerComponent baseLayerComponent) {
        this.component = (BaseLayerComponent) Preconditions.checkNotNull(baseLayerComponent);
    }

    private static void addUsageToOutputProperties(Map<String, Serializable> map, PlatformTestStorage platformTestStorage) {
        try {
            platformTestStorage.addOutputProperties(map);
        } catch (RuntimeException unused) {
            Log.w(TAG, "Failed to add the output properties. This could happen when running on Robolectric or an automotive emulator with API 30. Ignore for now.");
        }
    }

    static BaseLayerComponent baseLayer() {
        AtomicReference<GraphHolder> atomicReference = instance;
        GraphHolder graphHolder = atomicReference.get();
        if (graphHolder != null) {
            return graphHolder.component;
        }
        GraphHolder graphHolder2 = new GraphHolder(DaggerBaseLayerComponent.create());
        if (!GraphHolder$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, null, graphHolder2)) {
            return atomicReference.get().component;
        }
        HashMap map = new HashMap();
        map.put("Espresso", "1");
        addUsageToOutputProperties(map, graphHolder2.component.testStorage());
        return graphHolder2.component;
    }
}
