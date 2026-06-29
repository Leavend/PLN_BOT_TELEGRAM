package org.mockito.creation.instance;

/* loaded from: classes3.dex */
public interface Instantiator {
    <T> T newInstance(Class<T> cls) throws InstantiationException;
}
