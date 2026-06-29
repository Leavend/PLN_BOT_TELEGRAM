package androidx.test.espresso;

import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import java.util.Iterator;

/* loaded from: classes5.dex */
public interface UiController {

    /* renamed from: androidx.test.espresso.UiController$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static boolean $default$injectMotionEventSequence(UiController uiController, Iterable iterable) throws InjectEventSecurityException {
            Log.w("UIC", "Using default injectMotionEventSeq() - this may not inject a sequence properly. If wrapping UIController please override this method and delegate.");
            Iterator it = iterable.iterator();
            boolean zInjectMotionEvent = true;
            while (it.hasNext()) {
                MotionEvent motionEvent = (MotionEvent) it.next();
                if (motionEvent.getEventTime() - SystemClock.uptimeMillis() > 10) {
                    uiController.loopMainThreadForAtLeast(10L);
                }
                zInjectMotionEvent &= uiController.injectMotionEvent(motionEvent);
            }
            return zInjectMotionEvent;
        }
    }

    boolean injectKeyEvent(KeyEvent keyEvent) throws InjectEventSecurityException;

    boolean injectMotionEvent(MotionEvent motionEvent) throws InjectEventSecurityException;

    boolean injectMotionEventSequence(Iterable<MotionEvent> iterable) throws InjectEventSecurityException;

    boolean injectString(String str) throws InjectEventSecurityException;

    void loopMainThreadForAtLeast(long j);

    void loopMainThreadUntilIdle();
}
