package androidx.test.espresso.base;

import android.view.KeyEvent;
import android.view.MotionEvent;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.UiController;
import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.platform.ui.UiController;

/* loaded from: classes5.dex */
public class UiControllerModule {

    private static class EspressoUiControllerAdapter implements InterruptableUiController {
        private final UiController platformUiController;

        private EspressoUiControllerAdapter(UiController uiController) {
            this.platformUiController = uiController;
        }

        @Override // androidx.test.espresso.UiController
        public boolean injectKeyEvent(KeyEvent keyEvent) throws InjectEventSecurityException {
            try {
                return this.platformUiController.injectKeyEvent(keyEvent);
            } catch (androidx.test.platform.ui.InjectEventSecurityException e) {
                throw new InjectEventSecurityException(e);
            }
        }

        @Override // androidx.test.espresso.UiController
        public boolean injectMotionEvent(MotionEvent motionEvent) throws InjectEventSecurityException {
            try {
                return this.platformUiController.injectMotionEvent(motionEvent);
            } catch (androidx.test.platform.ui.InjectEventSecurityException e) {
                throw new InjectEventSecurityException(e);
            }
        }

        @Override // androidx.test.espresso.UiController
        public /* synthetic */ boolean injectMotionEventSequence(Iterable iterable) {
            return UiController.CC.$default$injectMotionEventSequence(this, iterable);
        }

        @Override // androidx.test.espresso.UiController
        public boolean injectString(String str) throws InjectEventSecurityException {
            try {
                return this.platformUiController.injectString(str);
            } catch (androidx.test.platform.ui.InjectEventSecurityException e) {
                throw new InjectEventSecurityException(e);
            }
        }

        @Override // androidx.test.espresso.base.InterruptableUiController
        public void interruptEspressoTasks() {
        }

        @Override // androidx.test.espresso.UiController
        public void loopMainThreadForAtLeast(long j) {
            this.platformUiController.loopMainThreadForAtLeast(j);
        }

        @Override // androidx.test.espresso.UiController
        public void loopMainThreadUntilIdle() {
            this.platformUiController.loopMainThreadUntilIdle();
        }
    }

    public androidx.test.espresso.UiController provideUiController(UiControllerImpl uiControllerImpl) {
        androidx.test.platform.ui.UiController uiController = (androidx.test.platform.ui.UiController) ServiceLoaderWrapper.loadSingleServiceOrNull(androidx.test.platform.ui.UiController.class);
        return uiController == null ? uiControllerImpl : new EspressoUiControllerAdapter(uiController);
    }
}
