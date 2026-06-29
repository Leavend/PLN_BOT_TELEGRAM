package org.mockito.internal.util.reflection;

import java.lang.reflect.AccessibleObject;

/* loaded from: classes3.dex */
public class AccessibilityChanger {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private Boolean wasAccessible = null;

    public void safelyDisableAccess(AccessibleObject accessibleObject) {
        try {
            accessibleObject.setAccessible(this.wasAccessible.booleanValue());
        } catch (Throwable unused) {
        }
    }

    public void enableAccess(AccessibleObject accessibleObject) throws SecurityException {
        this.wasAccessible = Boolean.valueOf(accessibleObject.isAccessible());
        accessibleObject.setAccessible(true);
    }
}
