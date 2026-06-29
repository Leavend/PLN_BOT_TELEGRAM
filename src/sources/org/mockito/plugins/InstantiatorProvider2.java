package org.mockito.plugins;

import org.mockito.creation.instance.Instantiator;
import org.mockito.mock.MockCreationSettings;

/* loaded from: classes3.dex */
public interface InstantiatorProvider2 {
    Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings);
}
