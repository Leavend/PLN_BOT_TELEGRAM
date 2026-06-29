package androidx.test.espresso.base;

import android.view.View;
import androidx.test.espresso.EspressoException;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.core.internal.deps.guava.base.Throwables;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class EspressoExceptionHandler extends DefaultFailureHandler.TypedFailureHandler<Throwable> {
    public EspressoExceptionHandler(Class<EspressoException> cls) {
        super(cls);
    }

    @Override // androidx.test.espresso.base.DefaultFailureHandler.TypedFailureHandler
    public /* bridge */ /* synthetic */ void handleSafely(Throwable th, Matcher matcher) {
        handleSafely2(th, (Matcher<View>) matcher);
    }

    /* renamed from: handleSafely, reason: avoid collision after fix types in other method */
    public void handleSafely2(Throwable th, Matcher<View> matcher) {
        th.setStackTrace(Thread.currentThread().getStackTrace());
        Throwables.throwIfUnchecked(th);
        throw new RuntimeException(th);
    }
}
