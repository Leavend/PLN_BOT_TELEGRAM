package org.mockito.internal.junit;

import org.mockito.listeners.MockCreationListener;

/* loaded from: classes3.dex */
public interface MockitoTestListener extends MockCreationListener {
    void testFinished(TestFinishedEvent testFinishedEvent);
}
