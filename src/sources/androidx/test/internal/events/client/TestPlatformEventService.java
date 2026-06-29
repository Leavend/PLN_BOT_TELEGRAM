package androidx.test.internal.events.client;

import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes5.dex */
public interface TestPlatformEventService {
    void send(TestPlatformEvent event) throws TestEventClientException;
}
