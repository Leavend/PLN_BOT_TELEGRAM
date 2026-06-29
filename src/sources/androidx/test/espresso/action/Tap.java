package androidx.test.espresso.action;

import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.test.espresso.UiController;
import androidx.test.espresso.action.MotionEvents;
import androidx.test.espresso.action.Tapper;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import java.lang.reflect.InvocationTargetException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes5.dex */
public abstract class Tap implements Tapper {
    private static final int DOUBLE_TAP_MIN_TIMEOUT;
    public static final Tap SINGLE;
    public static final Tap LONG = new AnonymousClass2("LONG", 1);
    public static final Tap DOUBLE = new AnonymousClass3("DOUBLE", 2);
    private static final /* synthetic */ Tap[] $VALUES = $values();
    private static final String TAG = "Tap";

    /* renamed from: androidx.test.espresso.action.Tap$2, reason: invalid class name */
    enum AnonymousClass2 extends Tap {
        private AnonymousClass2(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2) {
            return sendTap(uiController, fArr, fArr2, 0, 0);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2, int i, int i2) {
            Preconditions.checkNotNull(uiController);
            Preconditions.checkNotNull(fArr);
            Preconditions.checkNotNull(fArr2);
            MotionEvent motionEvent = MotionEvents.sendDown(uiController, fArr, fArr2, i, i2).down;
            try {
                uiController.loopMainThreadForAtLeast((long) (ViewConfiguration.getLongPressTimeout() * 1.5f));
                if (MotionEvents.sendUp(uiController, motionEvent)) {
                    motionEvent.recycle();
                    return Tapper.Status.SUCCESS;
                }
                MotionEvents.sendCancel(uiController, motionEvent);
                return Tapper.Status.FAILURE;
            } finally {
                motionEvent.recycle();
            }
        }
    }

    /* renamed from: androidx.test.espresso.action.Tap$3, reason: invalid class name */
    enum AnonymousClass3 extends Tap {
        private AnonymousClass3(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2) {
            return sendTap(uiController, fArr, fArr2, 0, 0);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2, int i, int i2) {
            Preconditions.checkNotNull(uiController);
            Preconditions.checkNotNull(fArr);
            Preconditions.checkNotNull(fArr2);
            Tapper.Status statusSendSingleTap = Tap.sendSingleTap(uiController, fArr, fArr2, i, i2);
            if (statusSendSingleTap == Tapper.Status.FAILURE) {
                return Tapper.Status.FAILURE;
            }
            if (Tap.DOUBLE_TAP_MIN_TIMEOUT > 0) {
                uiController.loopMainThreadForAtLeast(Tap.DOUBLE_TAP_MIN_TIMEOUT);
            }
            Tapper.Status statusSendSingleTap2 = Tap.sendSingleTap(uiController, fArr, fArr2, i, i2);
            return statusSendSingleTap2 == Tapper.Status.FAILURE ? Tapper.Status.FAILURE : (statusSendSingleTap2 == Tapper.Status.WARNING || statusSendSingleTap == Tapper.Status.WARNING) ? Tapper.Status.WARNING : Tapper.Status.SUCCESS;
        }
    }

    private static /* synthetic */ Tap[] $values() {
        return new Tap[]{SINGLE, LONG, DOUBLE};
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int iIntValue = 0;
        SINGLE = new AnonymousClass1("SINGLE", iIntValue);
        try {
            iIntValue = ((Integer) ViewConfiguration.class.getDeclaredMethod("getDoubleTapMinTime", new Class[0]).invoke(null, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.w(TAG, "Unable to query double tap min time!", e);
        } catch (NoSuchMethodException e2) {
            Log.w(TAG, "Expected to find getDoubleTapMinTime", e2);
        } catch (InvocationTargetException e3) {
            Log.w(TAG, "Unable to query double tap min time!", e3);
        }
        DOUBLE_TAP_MIN_TIMEOUT = iIntValue;
    }

    private Tap(String str, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Tapper.Status sendSingleTap(UiController uiController, float[] fArr, float[] fArr2, int i, int i2) {
        Preconditions.checkNotNull(uiController);
        Preconditions.checkNotNull(fArr);
        Preconditions.checkNotNull(fArr2);
        MotionEvents.DownResultHolder downResultHolderSendDown = MotionEvents.sendDown(uiController, fArr, fArr2, i, i2);
        try {
            if (MotionEvents.sendUp(uiController, downResultHolderSendDown.down)) {
                downResultHolderSendDown.down.recycle();
                return downResultHolderSendDown.longPress ? Tapper.Status.WARNING : Tapper.Status.SUCCESS;
            }
            Log.d(TAG, "Injection of up event as part of the click failed. Send cancel event.");
            MotionEvents.sendCancel(uiController, downResultHolderSendDown.down);
            return Tapper.Status.FAILURE;
        } finally {
            downResultHolderSendDown.down.recycle();
        }
    }

    public static Tap valueOf(String str) {
        return (Tap) Enum.valueOf(Tap.class, str);
    }

    public static Tap[] values() {
        return (Tap[]) $VALUES.clone();
    }

    /* renamed from: androidx.test.espresso.action.Tap$1, reason: invalid class name */
    enum AnonymousClass1 extends Tap {
        private AnonymousClass1(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2) {
            return sendTap(uiController, fArr, fArr2, 0, 0);
        }

        @Override // androidx.test.espresso.action.Tapper
        public Tapper.Status sendTap(UiController uiController, float[] fArr, float[] fArr2, int i, int i2) {
            Tapper.Status statusSendSingleTap = Tap.sendSingleTap(uiController, fArr, fArr2, i, i2);
            if (Tapper.Status.SUCCESS == statusSendSingleTap) {
                uiController.loopMainThreadForAtLeast((long) (ViewConfiguration.getTapTimeout() * 1.5f));
            }
            return statusSendSingleTap;
        }
    }
}
