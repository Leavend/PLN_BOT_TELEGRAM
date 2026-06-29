package androidx.test.orchestrator.callback;

import android.content.Context;
import androidx.test.internal.events.client.TestDiscoveryEventService;
import androidx.test.internal.events.client.TestEventServiceConnection;
import androidx.test.internal.events.client.TestRunEventService;
import androidx.test.services.events.discovery.TestDiscoveryEvent;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes5.dex */
public class NoOpOrchestratorConnection implements TestEventServiceConnection, TestRunEventService, TestDiscoveryEventService {
    @Override // androidx.test.internal.events.client.TestEventServiceConnection
    public void connect(Context context) {
    }

    @Override // androidx.test.internal.events.client.TestDiscoveryEventService
    public void send(TestDiscoveryEvent testDiscoveryEvent) {
    }

    @Override // androidx.test.internal.events.client.TestRunEventService
    public void send(TestRunEvent event) {
    }
}
