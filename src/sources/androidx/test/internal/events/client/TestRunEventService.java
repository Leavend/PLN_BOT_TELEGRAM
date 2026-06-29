package androidx.test.internal.events.client;

import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes5.dex */
public interface TestRunEventService {
    void send(TestRunEvent event) throws TestEventClientException;
}
