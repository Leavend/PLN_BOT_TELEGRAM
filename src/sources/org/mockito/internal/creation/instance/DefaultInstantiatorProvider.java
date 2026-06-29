package org.mockito.internal.creation.instance;

import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider2;

/* loaded from: classes3.dex */
public class DefaultInstantiatorProvider implements InstantiatorProvider2 {
    private static final org.mockito.creation.instance.Instantiator INSTANCE = new ObjenesisInstantiator();

    @Override // org.mockito.plugins.InstantiatorProvider2
    public org.mockito.creation.instance.Instantiator getInstantiator(MockCreationSettings<?> mockCreationSettings) {
        if (mockCreationSettings != null && mockCreationSettings.getConstructorArgs() != null) {
            return new ConstructorInstantiator(mockCreationSettings.getOuterClassInstance() != null, mockCreationSettings.getConstructorArgs());
        }
        return INSTANCE;
    }
}
