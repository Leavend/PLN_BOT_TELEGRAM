package androidx.test.espresso.base;

import android.view.View;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.core.internal.deps.guava.base.Throwables;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class ThrowableHandler implements FailureHandler {
    ThrowableHandler() {
    }

    @Override // androidx.test.espresso.FailureHandler
    public void handle(Throwable th, Matcher<View> matcher) {
        Throwables.throwIfUnchecked(th);
        throw new RuntimeException(th);
    }
}
