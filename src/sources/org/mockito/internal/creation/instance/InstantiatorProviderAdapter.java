package org.mockito.internal.creation.instance;

import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.InstantiatorProvider;
import org.mockito.plugins.InstantiatorProvider2;

/* loaded from: classes3.dex */
public class InstantiatorProviderAdapter implements InstantiatorProvider2 {
    private final InstantiatorProvider provider;

    public InstantiatorProviderAdapter(InstantiatorProvider instantiatorProvider) {
        this.provider = instantiatorProvider;
    }

    @Override // org.mockito.plugins.InstantiatorProvider2
    public org.mockito.creation.instance.Instantiator getInstantiator(final MockCreationSettings<?> mockCreationSettings) {
        return new org.mockito.creation.instance.Instantiator() { // from class: org.mockito.internal.creation.instance.InstantiatorProviderAdapter.1
            @Override // org.mockito.creation.instance.Instantiator
            public <T> T newInstance(Class<T> cls) throws org.mockito.creation.instance.InstantiationException {
                try {
                    return (T) InstantiatorProviderAdapter.this.provider.getInstantiator(mockCreationSettings).newInstance(cls);
                } catch (InstantiationException e) {
                    throw new org.mockito.creation.instance.InstantiationException(e.getMessage(), e.getCause());
                }
            }
        };
    }
}
