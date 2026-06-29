package org.mockito.internal.creation.instance;

@Deprecated
/* loaded from: classes3.dex */
public interface Instantiator {
    <T> T newInstance(Class<T> cls) throws InstantiationException;
}
