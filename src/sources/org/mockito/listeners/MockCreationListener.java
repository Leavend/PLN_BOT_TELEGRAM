package org.mockito.listeners;

import org.mockito.mock.MockCreationSettings;

/* loaded from: classes3.dex */
public interface MockCreationListener extends MockitoListener {
    void onMockCreated(Object obj, MockCreationSettings mockCreationSettings);
}
