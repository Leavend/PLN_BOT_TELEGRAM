package androidx.test.espresso.remote;

import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes5.dex */
public class RemoteInteractionRegistry {
    private static final AtomicReference<RemoteInteraction> sInstance = new AtomicReference<>(new NoopRemoteInteraction());

    private RemoteInteractionRegistry() {
    }

    public static RemoteInteraction getInstance() {
        return sInstance.get();
    }

    public static void registerInstance(RemoteInteraction remoteInteraction) {
        if (remoteInteraction == null) {
            sInstance.set(new NoopRemoteInteraction());
        } else {
            sInstance.set(remoteInteraction);
        }
    }
}
