package androidx.test.espresso.base;

import android.util.Log;
import android.view.View;
import androidx.test.espresso.RootViewException;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.core.internal.deps.guava.base.Throwables;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.internal.util.Checks;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.services.storage.TestStorageException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.Throwable;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class ViewHierarchyExceptionHandler<T extends Throwable & RootViewException> extends DefaultFailureHandler.TypedFailureHandler<T> {
    private static final int MAX_MSG_SIZE = 63488;
    private static final String TAG = "ViewHierarchyExceptionHandler";
    private static final String VIEW_HIERARCHY_CHAR_LIMIT = "view_hierarchy_char_limit";
    private final AtomicInteger failureCount;
    private final PlatformTestStorage testStorage;
    private final Truncater<T> truncater;

    interface Truncater<T> {
        Throwable truncateExceptionMessage(T t, int i, String str);
    }

    public ViewHierarchyExceptionHandler(PlatformTestStorage platformTestStorage, AtomicInteger atomicInteger, Class<T> cls, Truncater<T> truncater) {
        super(cls);
        this.testStorage = (PlatformTestStorage) Checks.checkNotNull(platformTestStorage);
        this.failureCount = atomicInteger;
        this.truncater = truncater;
    }

    private void addOutputFile(String str, String str2) throws IllegalAccessException, IOException, IllegalArgumentException, InvocationTargetException {
        OutputStream outputStreamOpenOutputFile = this.testStorage.openOutputFile(str);
        try {
            outputStreamOpenOutputFile.write(str2.getBytes());
            if (outputStreamOpenOutputFile != null) {
                outputStreamOpenOutputFile.close();
            }
        } catch (Throwable th) {
            if (outputStreamOpenOutputFile != null) {
                try {
                    outputStreamOpenOutputFile.close();
                } catch (Throwable th2) {
                    Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                }
            }
            throw th;
        }
    }

    private String dumpFullViewHierarchyToFile(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String viewHierarchyErrorMessage = HumanReadables.getViewHierarchyErrorMessage(t.getRootView(), null, "", null);
        String str = "view-hierarchy-" + String.valueOf(this.failureCount) + ".txt";
        try {
            addOutputFile(str, viewHierarchyErrorMessage);
            Log.w(TAG, "The complete view hierarchy is available in artifact file '" + str + "'.");
            return str;
        } catch (IOException e) {
            Log.w(TAG, "Failed to save the view hierarchy to file " + str, e);
            return null;
        }
    }

    private int getMsgLen() {
        String inputArg;
        try {
            return (!this.testStorage.getInputArgs().containsKey(VIEW_HIERARCHY_CHAR_LIMIT) || (inputArg = this.testStorage.getInputArg(VIEW_HIERARCHY_CHAR_LIMIT)) == null) ? MAX_MSG_SIZE : Integer.parseInt(inputArg);
        } catch (TestStorageException | NumberFormatException e) {
            Log.e(TAG, "Failed to parse input argument view_hierarchy_char_limit", e);
            return MAX_MSG_SIZE;
        }
    }

    @Override // androidx.test.espresso.base.DefaultFailureHandler.TypedFailureHandler
    public /* bridge */ /* synthetic */ void handleSafely(Object obj, Matcher matcher) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        handleSafely((ViewHierarchyExceptionHandler<T>) obj, (Matcher<View>) matcher);
    }

    public void handleSafely(T t, Matcher<View> matcher) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String strDumpFullViewHierarchyToFile = dumpFullViewHierarchyToFile(t);
        t.setStackTrace(Thread.currentThread().getStackTrace());
        Throwable thTruncateExceptionMessage = this.truncater.truncateExceptionMessage(t, getMsgLen(), strDumpFullViewHierarchyToFile);
        Throwables.throwIfUnchecked(thTruncateExceptionMessage);
        throw new RuntimeException(thTruncateExceptionMessage);
    }
}
