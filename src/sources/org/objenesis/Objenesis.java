package org.objenesis;

import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes3.dex */
public interface Objenesis {
    <T> ObjectInstantiator<T> getInstantiatorOf(Class<T> cls);

    <T> T newInstance(Class<T> cls);
}
