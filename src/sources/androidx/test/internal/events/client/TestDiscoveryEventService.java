package androidx.test.internal.events.client;

import androidx.test.services.events.discovery.TestDiscoveryEvent;

/* loaded from: classes5.dex */
public interface TestDiscoveryEventService {
    void send(TestDiscoveryEvent testDiscoveryEvent) throws TestEventClientException;
}
