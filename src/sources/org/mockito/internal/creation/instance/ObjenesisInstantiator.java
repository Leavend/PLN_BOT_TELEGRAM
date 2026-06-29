package org.mockito.internal.creation.instance;

import org.mockito.internal.configuration.GlobalConfiguration;
import org.objenesis.ObjenesisStd;

/* loaded from: classes3.dex */
class ObjenesisInstantiator implements org.mockito.creation.instance.Instantiator {
    private final ObjenesisStd objenesis = new ObjenesisStd(new GlobalConfiguration().enableClassCache());

    ObjenesisInstantiator() {
    }

    @Override // org.mockito.creation.instance.Instantiator
    public <T> T newInstance(Class<T> cls) {
        return (T) this.objenesis.newInstance(cls);
    }
}
