package androidx.test.espresso.base;

import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.test.core.app.DeviceCapture;
import androidx.test.core.graphics.BitmapStorage;
import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.EspressoException;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.base.ViewHierarchyExceptionHandler;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.internal.inject.TargetContext;
import androidx.test.internal.platform.util.TestOutputEmitter;
import androidx.test.platform.io.PlatformTestStorage;
import androidx.test.platform.io.PlatformTestStorageRegistry;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import junit.framework.AssertionFailedError;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
public final class DefaultFailureHandler implements FailureHandler {
    private static final AtomicInteger failureCount = new AtomicInteger(0);
    private final List<FailureHandler> handlers;
    private final PlatformTestStorage testStorage;

    static abstract class TypedFailureHandler<T> implements FailureHandler {
        private final List<Class<?>> acceptedTypes;

        public TypedFailureHandler(Class<?>... clsArr) {
            this.acceptedTypes = (List) Preconditions.checkNotNull(Arrays.asList(clsArr));
        }

        abstract void handleSafely(T t, Matcher<View> matcher);

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.test.espresso.FailureHandler
        public void handle(Throwable th, Matcher<View> matcher) {
            if (th != 0) {
                Iterator<Class<?>> it = this.acceptedTypes.iterator();
                while (it.hasNext()) {
                    if (it.next().isInstance(th)) {
                        handleSafely(th, matcher);
                        return;
                    }
                }
            }
        }
    }

    public DefaultFailureHandler(@TargetContext Context context) {
        this(context, PlatformTestStorageRegistry.getInstance());
    }

    static ViewHierarchyExceptionHandler.Truncater<AmbiguousViewMatcherException> getAmbiguousViewMatcherExceptionTruncater() {
        return new ViewHierarchyExceptionHandler.Truncater() { // from class: androidx.test.espresso.base.DefaultFailureHandler$$ExternalSyntheticLambda0
            @Override // androidx.test.espresso.base.ViewHierarchyExceptionHandler.Truncater
            public final Throwable truncateExceptionMessage(Object obj, int i, String str) {
                return new AmbiguousViewMatcherException.Builder().from((AmbiguousViewMatcherException) obj).withMaxMsgLen(i).withViewHierarchyFile(str).build();
            }
        };
    }

    static ViewHierarchyExceptionHandler.Truncater<NoMatchingViewException> getNoMatchingViewExceptionTruncater() {
        return new ViewHierarchyExceptionHandler.Truncater() { // from class: androidx.test.espresso.base.DefaultFailureHandler$$ExternalSyntheticLambda1
            @Override // androidx.test.espresso.base.ViewHierarchyExceptionHandler.Truncater
            public final Throwable truncateExceptionMessage(Object obj, int i, String str) {
                return new NoMatchingViewException.Builder().from((NoMatchingViewException) obj).withMaxMsgLen(i).withViewHierarchyFile(str).build();
            }
        };
    }

    private void takeScreenshot(String str) {
        try {
            if (DeviceCapture.canTakeScreenshot()) {
                BitmapStorage.writeToTestStorage(DeviceCapture.takeScreenshotNoSync(), this.testStorage, str);
            } else {
                TestOutputEmitter.takeScreenshot(str + ".png");
            }
        } catch (IOException | Error | RuntimeException e) {
            Log.w("DefaultFailureHandler", "Failed to take screenshot", e);
        }
    }

    @Override // androidx.test.espresso.FailureHandler
    public void handle(Throwable th, Matcher<View> matcher) {
        int iIncrementAndGet = failureCount.incrementAndGet();
        TestOutputEmitter.captureWindowHierarchy("explore-window-hierarchy-" + iIncrementAndGet + ".xml");
        takeScreenshot("view-op-error-" + iIncrementAndGet);
        Iterator<FailureHandler> it = this.handlers.iterator();
        while (it.hasNext()) {
            it.next().handle(th, matcher);
        }
    }

    DefaultFailureHandler(@TargetContext Context context, PlatformTestStorage platformTestStorage) {
        ArrayList arrayList = new ArrayList();
        this.handlers = arrayList;
        this.testStorage = platformTestStorage;
        AtomicInteger atomicInteger = failureCount;
        arrayList.add(new ViewHierarchyExceptionHandler(platformTestStorage, atomicInteger, NoMatchingViewException.class, getNoMatchingViewExceptionTruncater()));
        arrayList.add(new ViewHierarchyExceptionHandler(platformTestStorage, atomicInteger, AmbiguousViewMatcherException.class, getAmbiguousViewMatcherExceptionTruncater()));
        arrayList.add(new PerformExceptionHandler((Context) Preconditions.checkNotNull(context), PerformException.class));
        arrayList.add(new AssertionErrorHandler(AssertionFailedError.class, AssertionError.class));
        arrayList.add(new EspressoExceptionHandler(EspressoException.class));
        arrayList.add(new ThrowableHandler());
    }
}
