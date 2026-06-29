package org.mockito.internal.util;

import java.io.Serializable;
import org.mockito.mock.MockName;

/* loaded from: classes3.dex */
public class MockNameImpl implements MockName, Serializable {
    private static final long serialVersionUID = 8014974700844306925L;
    private boolean defaultName;
    private final String mockName;

    public MockNameImpl(String str, Class<?> cls) {
        if (str == null) {
            this.mockName = toInstanceName(cls);
            this.defaultName = true;
        } else {
            this.mockName = str;
        }
    }

    public MockNameImpl(String str) {
        this.mockName = str;
    }

    private static String toInstanceName(Class<?> cls) {
        String simpleName = cls.getSimpleName();
        if (simpleName.length() == 0) {
            simpleName = cls.getSuperclass().getSimpleName();
        }
        return simpleName.substring(0, 1).toLowerCase() + simpleName.substring(1);
    }

    @Override // org.mockito.mock.MockName
    public boolean isDefault() {
        return this.defaultName;
    }

    @Override // org.mockito.mock.MockName
    public String toString() {
        return this.mockName;
    }
}
