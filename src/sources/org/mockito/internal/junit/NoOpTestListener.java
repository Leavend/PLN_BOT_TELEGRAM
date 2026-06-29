package org.mockito.internal.junit;

import org.mockito.mock.MockCreationSettings;

/* loaded from: classes3.dex */
public class NoOpTestListener implements MockitoTestListener {
    @Override // org.mockito.listeners.MockCreationListener
    public void onMockCreated(Object obj, MockCreationSettings mockCreationSettings) {
    }

    @Override // org.mockito.internal.junit.MockitoTestListener
    public void testFinished(TestFinishedEvent testFinishedEvent) {
    }
}
