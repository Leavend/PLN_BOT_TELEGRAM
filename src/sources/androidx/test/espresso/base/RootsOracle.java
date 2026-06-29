package androidx.test.espresso.base;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import androidx.test.espresso.Root;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.collect.Lists;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes5.dex */
public final class RootsOracle implements ActiveRootLister {
    private static final String GET_DEFAULT_IMPL = "getDefault";
    private static final String GET_GLOBAL_INSTANCE = "getInstance";
    private static final String TAG = "RootsOracle";
    private static final String VIEWS_FIELD = "mViews";
    private static final String WINDOW_MANAGER_GLOBAL_CLAZZ = "android.view.WindowManagerGlobal";
    private static final String WINDOW_MANAGER_IMPL_CLAZZ = "android.view.WindowManagerImpl";
    private static final String WINDOW_PARAMS_FIELD = "mParams";
    private boolean initialized;
    private final Looper mainLooper;
    private Field paramsField;
    private Field viewsField;
    private Object windowManagerObj;

    RootsOracle(Looper looper) {
        this.mainLooper = looper;
    }

    private void initialize() throws NoSuchFieldException, ClassNotFoundException {
        this.initialized = true;
        try {
            Class<?> cls = Class.forName(WINDOW_MANAGER_GLOBAL_CLAZZ);
            this.windowManagerObj = cls.getMethod(GET_GLOBAL_INSTANCE, new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField(VIEWS_FIELD);
            this.viewsField = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = cls.getDeclaredField(WINDOW_PARAMS_FIELD);
            this.paramsField = declaredField2;
            declaredField2.setAccessible(true);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, String.format(Locale.ROOT, "could not find class: %s", WINDOW_MANAGER_GLOBAL_CLAZZ), e);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, String.format(Locale.ROOT, "reflective setup failed using obj: %s method: %s field: %s", WINDOW_MANAGER_GLOBAL_CLAZZ, GET_GLOBAL_INSTANCE, VIEWS_FIELD), e2);
        } catch (NoSuchFieldException e3) {
            Log.e(TAG, String.format(Locale.ROOT, "could not find field: %s or %s on %s", WINDOW_PARAMS_FIELD, VIEWS_FIELD, WINDOW_MANAGER_GLOBAL_CLAZZ), e3);
        } catch (NoSuchMethodException e4) {
            Log.e(TAG, String.format(Locale.ROOT, "could not find method: %s on %s", GET_GLOBAL_INSTANCE, WINDOW_MANAGER_GLOBAL_CLAZZ), e4);
        } catch (RuntimeException e5) {
            Log.e(TAG, String.format(Locale.ROOT, "reflective setup failed using obj: %s method: %s field: %s", WINDOW_MANAGER_GLOBAL_CLAZZ, GET_GLOBAL_INSTANCE, VIEWS_FIELD), e5);
        } catch (InvocationTargetException e6) {
            Log.e(TAG, String.format(Locale.ROOT, "could not invoke: %s on %s", GET_GLOBAL_INSTANCE, WINDOW_MANAGER_GLOBAL_CLAZZ), e6.getCause());
        }
    }

    @Override // androidx.test.espresso.base.ActiveRootLister
    public List<Root> listActiveRoots() throws NoSuchFieldException, ClassNotFoundException {
        Preconditions.checkState(this.mainLooper.equals(Looper.myLooper()), "must be called on main thread.");
        if (!this.initialized) {
            initialize();
        }
        if (this.windowManagerObj == null) {
            Log.w(TAG, "No reflective access to windowmanager object.");
            return Lists.newArrayList();
        }
        if (this.viewsField == null) {
            Log.w(TAG, "No reflective access to mViews");
            return Lists.newArrayList();
        }
        if (this.paramsField == null) {
            Log.w(TAG, "No reflective access to mParams");
            return Lists.newArrayList();
        }
        try {
            List list = (List) this.viewsField.get(this.windowManagerObj);
            List list2 = (List) this.paramsField.get(this.windowManagerObj);
            ArrayList arrayListNewArrayList = Lists.newArrayList();
            int size = list.size();
            while (true) {
                size--;
                if (size < 0) {
                    return arrayListNewArrayList;
                }
                arrayListNewArrayList.add(new Root.Builder().withDecorView((View) list.get(size)).withWindowLayoutParams((WindowManager.LayoutParams) list2.get(size)).build());
            }
        } catch (IllegalAccessException e) {
            Log.w(TAG, String.format(Locale.ROOT, "Reflective access to %s or %s on %s failed.", this.viewsField, this.paramsField, this.windowManagerObj), e);
            return Lists.newArrayList();
        } catch (RuntimeException e2) {
            Log.w(TAG, String.format(Locale.ROOT, "Reflective access to %s or %s on %s failed.", this.viewsField, this.paramsField, this.windowManagerObj), e2);
            return Lists.newArrayList();
        }
    }
}
