package org.mockito.internal.junit;

import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.mock.MockCreationSettings;
import org.mockito.quality.Strictness;

/* loaded from: classes3.dex */
public class StrictStubsRunnerTestListener implements MockitoTestListener {
    private final DefaultStubbingLookupListener stubbingLookupListener = new DefaultStubbingLookupListener(Strictness.STRICT_STUBS);

    @Override // org.mockito.internal.junit.MockitoTestListener
    public void testFinished(TestFinishedEvent testFinishedEvent) {
    }

    @Override // org.mockito.listeners.MockCreationListener
    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
        ((CreationSettings) mockCreationSettings).getStubbingLookupListeners().add(this.stubbingLookupListener);
    }
}
