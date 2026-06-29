package org.objenesis.instantiator.gcj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;

/* loaded from: classes3.dex */
public abstract class GCJInstantiatorBase<T> implements ObjectInstantiator<T> {
    static ObjectInputStream dummyStream;
    static Method newObjectMethod;
    protected final Class<T> type;

    private static class DummyStream extends ObjectInputStream {
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public abstract T newInstance();

    private static void initialize() throws NoSuchMethodException, SecurityException {
        if (newObjectMethod == null) {
            try {
                Method declaredMethod = ObjectInputStream.class.getDeclaredMethod("newObject", Class.class, Class.class);
                newObjectMethod = declaredMethod;
                declaredMethod.setAccessible(true);
                dummyStream = new DummyStream();
            } catch (IOException e) {
                throw new ObjenesisException(e);
            } catch (NoSuchMethodException e2) {
                throw new ObjenesisException(e2);
            } catch (RuntimeException e3) {
                throw new ObjenesisException(e3);
            }
        }
    }

    public GCJInstantiatorBase(Class<T> cls) throws NoSuchMethodException, SecurityException {
        this.type = cls;
        initialize();
    }
}
