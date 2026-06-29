package androidx.test.espresso.base;

import android.view.View;
import androidx.test.espresso.base.DefaultFailureHandler;
import junit.framework.AssertionFailedError;
import org.hamcrest.Matcher;

/* loaded from: classes5.dex */
class AssertionErrorHandler extends DefaultFailureHandler.TypedFailureHandler<Throwable> {

    static final class AssertionFailedWithCauseError extends AssertionFailedError {
        public AssertionFailedWithCauseError(String str, Throwable th) {
            super(str);
            initCause(th);
        }
    }

    public AssertionErrorHandler(Class<?>... clsArr) {
        super(clsArr);
    }

    @Override // androidx.test.espresso.base.DefaultFailureHandler.TypedFailureHandler
    public /* bridge */ /* synthetic */ void handleSafely(Throwable th, Matcher matcher) {
        handleSafely2(th, (Matcher<View>) matcher);
    }

    /* renamed from: handleSafely, reason: avoid collision after fix types in other method */
    public void handleSafely2(Throwable th, Matcher<View> matcher) {
        AssertionFailedWithCauseError assertionFailedWithCauseError = new AssertionFailedWithCauseError(th.getMessage(), th);
        assertionFailedWithCauseError.setStackTrace(Thread.currentThread().getStackTrace());
        throw assertionFailedWithCauseError;
    }
}
