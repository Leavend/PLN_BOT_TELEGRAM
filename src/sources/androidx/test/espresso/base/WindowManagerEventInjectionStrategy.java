package androidx.test.espresso.base;

import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
final class WindowManagerEventInjectionStrategy implements EventInjectionStrategy {
    private static final String TAG = "WindowManagerEventInjectionStrategy";
    private boolean initComplete;
    private Method injectInputKeyEventMethod;
    private Method injectInputMotionEventMethod;
    private Object wmInstance;

    WindowManagerEventInjectionStrategy() {
        Preconditions.checkState(false, "Unsupported API level.");
    }

    void initialize() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this.initComplete) {
            return;
        }
        try {
            Log.d(TAG, "Trying to create injection strategy.");
            Class<?> cls = Class.forName("android.os.ServiceManager");
            Method declaredMethod = cls.getDeclaredMethod("getService", String.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(cls, "window");
            Class<?> cls2 = Class.forName("android.view.IWindowManager$Stub");
            Method declaredMethod2 = cls2.getDeclaredMethod("asInterface", IBinder.class);
            declaredMethod2.setAccessible(true);
            Object objInvoke2 = declaredMethod2.invoke(cls2, objInvoke);
            this.wmInstance = objInvoke2;
            Method declaredMethod3 = objInvoke2.getClass().getDeclaredMethod("injectPointerEvent", MotionEvent.class, Boolean.TYPE);
            this.injectInputMotionEventMethod = declaredMethod3;
            declaredMethod3.setAccessible(true);
            Method declaredMethod4 = this.wmInstance.getClass().getDeclaredMethod("injectKeyEvent", KeyEvent.class, Boolean.TYPE);
            this.injectInputKeyEventMethod = declaredMethod4;
            declaredMethod4.setAccessible(true);
            this.initComplete = true;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (IllegalArgumentException e3) {
            throw e3;
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException(e4);
        } catch (SecurityException e5) {
            throw e5;
        } catch (InvocationTargetException e6) {
            throw new RuntimeException(e6);
        }
    }

    @Override // androidx.test.espresso.base.EventInjectionStrategy
    public boolean injectKeyEvent(KeyEvent keyEvent) throws InjectEventSecurityException {
        try {
            return ((Boolean) this.injectInputKeyEventMethod.invoke(this.wmInstance, keyEvent, true)).booleanValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (SecurityException e3) {
            throw new InjectEventSecurityException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof SecurityException) {
                throw new InjectEventSecurityException(cause);
            }
            throw new RuntimeException(e4);
        }
    }

    @Override // androidx.test.espresso.base.EventInjectionStrategy
    public boolean injectMotionEvent(MotionEvent motionEvent, boolean z) throws InjectEventSecurityException {
        try {
            return ((Boolean) this.injectInputMotionEventMethod.invoke(this.wmInstance, motionEvent, Boolean.valueOf(z))).booleanValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (SecurityException e3) {
            throw new InjectEventSecurityException(e3);
        } catch (InvocationTargetException e4) {
            Throwable cause = e4.getCause();
            if (cause instanceof SecurityException) {
                throw new InjectEventSecurityException(cause);
            }
            throw new RuntimeException(e4);
        }
    }
}
