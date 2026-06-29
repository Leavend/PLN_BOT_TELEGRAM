package androidx.test.espresso.action;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.core.os.EnvironmentCompat;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import java.util.Locale;

/* loaded from: classes5.dex */
public final class MotionEvents {
    static final int MAX_CLICK_ATTEMPTS = 3;
    private static final String TAG = "MotionEvents";

    public static class DownResultHolder {
        public final MotionEvent down;
        public final boolean longPress;

        DownResultHolder(MotionEvent motionEvent, boolean z) {
            this.down = motionEvent;
            this.longPress = z;
        }
    }

    private MotionEvents() {
    }

    private static MotionEvent.PointerProperties[] getPointerProperties(int i) {
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        MotionEvent.PointerProperties[] pointerPropertiesArr = {pointerProperties};
        pointerProperties.clear();
        pointerPropertiesArr[0].id = 0;
        pointerPropertiesArr[0].toolType = i;
        return pointerPropertiesArr;
    }

    private static int mapInputDeviceToToolType(int i) {
        if (i == 4098) {
            return 1;
        }
        if (i != 8194) {
            return i != 16386 ? 0 : 2;
        }
        return 3;
    }

    public static MotionEvent obtainDownEvent(float[] fArr, float[] fArr2) {
        return obtainDownEvent(fArr, fArr2, 0, 1);
    }

    @Deprecated
    public static MotionEvent obtainMovement(long j, long j2, float[] fArr) {
        float f = fArr[1];
        return MotionEvent.obtain(j, j2, 2, f, f, 0);
    }

    public static MotionEvent obtainUpEvent(MotionEvent motionEvent, long j, float[] fArr) {
        Preconditions.checkNotNull(motionEvent);
        Preconditions.checkNotNull(fArr);
        return obtain(motionEvent, j, 1, fArr);
    }

    public static void sendCancel(UiController uiController, MotionEvent motionEvent) {
        Preconditions.checkNotNull(uiController);
        Preconditions.checkNotNull(motionEvent);
        MotionEvent motionEventObtain = null;
        try {
            try {
                motionEventObtain = obtain(motionEvent, SystemClock.uptimeMillis(), 3, new float[]{motionEvent.getX(), motionEvent.getY()});
                if (!uiController.injectMotionEvent(motionEventObtain)) {
                    Log.e(TAG, String.format(Locale.ROOT, "Injection of cancel event failed (corresponding down event: %s)", motionEvent));
                } else if (motionEventObtain != null) {
                    motionEventObtain.recycle();
                }
            } catch (InjectEventSecurityException e) {
                throw new PerformException.Builder().withActionDescription(String.format(Locale.ROOT, "inject cancel event (corresponding down event: %s)", motionEvent)).withViewDescription(EnvironmentCompat.MEDIA_UNKNOWN).withCause(e).build();
            }
        } finally {
            if (motionEventObtain != null) {
                motionEventObtain.recycle();
            }
        }
    }

    public static DownResultHolder sendDown(UiController uiController, float[] fArr, float[] fArr2) {
        return sendDown(uiController, fArr, fArr2, 0, 1);
    }

    public static boolean sendMovement(UiController uiController, MotionEvent motionEvent, float[] fArr) {
        Preconditions.checkNotNull(uiController);
        Preconditions.checkNotNull(motionEvent);
        Preconditions.checkNotNull(fArr);
        MotionEvent motionEventObtainMovement = null;
        try {
            try {
                motionEventObtainMovement = obtainMovement(motionEvent, fArr);
                if (!uiController.injectMotionEvent(motionEventObtainMovement)) {
                    Log.e(TAG, String.format(Locale.ROOT, "Injection of motion event failed (corresponding down event: %s)", motionEvent));
                }
                if (motionEventObtainMovement == null) {
                    return true;
                }
                motionEventObtainMovement.recycle();
                return true;
            } catch (InjectEventSecurityException e) {
                throw new PerformException.Builder().withActionDescription(String.format(Locale.ROOT, "inject motion event (corresponding down event: %s)", motionEvent)).withViewDescription(EnvironmentCompat.MEDIA_UNKNOWN).withCause(e).build();
            }
        } finally {
            if (motionEventObtainMovement != null) {
                motionEventObtainMovement.recycle();
            }
        }
    }

    public static boolean sendUp(UiController uiController, MotionEvent motionEvent) {
        return sendUp(uiController, motionEvent, new float[]{motionEvent.getX(), motionEvent.getY()});
    }

    private static MotionEvent obtain(long j, long j2, int i, float[] fArr, float f, float f2, int i2, int i3) {
        return obtain(j, j2, i, fArr, f, f2, i2, mapInputDeviceToToolType(i2), i3);
    }

    public static MotionEvent obtainDownEvent(float[] fArr, float[] fArr2, int i, int i2) {
        Preconditions.checkNotNull(fArr);
        Preconditions.checkNotNull(fArr2);
        long jUptimeMillis = SystemClock.uptimeMillis();
        return obtain(jUptimeMillis, jUptimeMillis, 0, fArr, fArr2[0], fArr2[1], i, i2);
    }

    public static DownResultHolder sendDown(UiController uiController, float[] fArr, float[] fArr2, int i, int i2) {
        boolean z;
        Preconditions.checkNotNull(uiController);
        Preconditions.checkNotNull(fArr);
        Preconditions.checkNotNull(fArr2);
        for (int i3 = 0; i3 < 3; i3++) {
            try {
                MotionEvent motionEventObtainDownEvent = obtainDownEvent(fArr, fArr2, i, i2);
                long downTime = motionEventObtainDownEvent.getDownTime();
                long tapTimeout = (ViewConfiguration.getTapTimeout() / 2) + downTime;
                boolean zInjectMotionEvent = uiController.injectMotionEvent(motionEventObtainDownEvent);
                while (true) {
                    long jUptimeMillis = tapTimeout - SystemClock.uptimeMillis();
                    if (jUptimeMillis <= 10) {
                        break;
                    }
                    uiController.loopMainThreadForAtLeast(jUptimeMillis / 4);
                }
                if (SystemClock.uptimeMillis() > downTime + ViewConfiguration.getLongPressTimeout()) {
                    Log.w(TAG, "Overslept and turned a tap into a long press");
                    z = true;
                } else {
                    z = false;
                }
                if (zInjectMotionEvent) {
                    return new DownResultHolder(motionEventObtainDownEvent, z);
                }
                motionEventObtainDownEvent.recycle();
            } catch (InjectEventSecurityException e) {
                throw new PerformException.Builder().withActionDescription("Send down motion event").withViewDescription(EnvironmentCompat.MEDIA_UNKNOWN).withCause(e).build();
            }
        }
        throw new PerformException.Builder().withActionDescription(String.format(Locale.ROOT, "click (after %s attempts)", 3)).withViewDescription(EnvironmentCompat.MEDIA_UNKNOWN).build();
    }

    public static boolean sendUp(UiController uiController, MotionEvent motionEvent, float[] fArr) {
        Preconditions.checkNotNull(uiController);
        Preconditions.checkNotNull(motionEvent);
        Preconditions.checkNotNull(fArr);
        MotionEvent motionEventObtainUpEvent = null;
        try {
            try {
                motionEventObtainUpEvent = obtainUpEvent(motionEvent, fArr);
                if (!uiController.injectMotionEvent(motionEventObtainUpEvent)) {
                    Log.e(TAG, String.format(Locale.ROOT, "Injection of up event failed (corresponding down event: %s)", motionEvent));
                }
                if (motionEventObtainUpEvent == null) {
                    return true;
                }
                motionEventObtainUpEvent.recycle();
                return true;
            } catch (InjectEventSecurityException e) {
                throw new PerformException.Builder().withActionDescription(String.format(Locale.ROOT, "inject up event (corresponding down event: %s)", motionEvent)).withViewDescription(EnvironmentCompat.MEDIA_UNKNOWN).withCause(e).build();
            }
        } finally {
            if (motionEventObtainUpEvent != null) {
                motionEventObtainUpEvent.recycle();
            }
        }
    }

    private static MotionEvent obtain(long j, long j2, int i, float[] fArr, float f, float f2, int i2, int i3, int i4) {
        MotionEvent.PointerCoords[] pointerCoordsArr = {new MotionEvent.PointerCoords()};
        MotionEvent.PointerProperties[] pointerProperties = getPointerProperties(i3);
        pointerCoordsArr[0].clear();
        pointerCoordsArr[0].x = fArr[0];
        pointerCoordsArr[0].y = fArr[1];
        pointerCoordsArr[0].pressure = 0.0f;
        pointerCoordsArr[0].size = 1.0f;
        return MotionEvent.obtain(j, j2, i, 1, pointerProperties, pointerCoordsArr, 0, i4, f, f2, 0, 0, i2, 0);
    }

    @Deprecated
    public static MotionEvent obtainMovement(long j, float[] fArr) {
        return MotionEvent.obtain(j, SystemClock.uptimeMillis(), 2, fArr[0], fArr[1], 0);
    }

    public static MotionEvent obtainMovement(MotionEvent motionEvent, long j, float[] fArr) {
        Preconditions.checkNotNull(motionEvent);
        Preconditions.checkNotNull(fArr);
        return obtain(motionEvent, j, 2, fArr);
    }

    public static MotionEvent obtainUpEvent(MotionEvent motionEvent, float[] fArr) {
        return obtainUpEvent(motionEvent, SystemClock.uptimeMillis(), fArr);
    }

    public static MotionEvent obtainMovement(MotionEvent motionEvent, float[] fArr) {
        return obtainMovement(motionEvent, SystemClock.uptimeMillis(), fArr);
    }

    private static MotionEvent obtain(MotionEvent motionEvent, long j, int i, float[] fArr) {
        return obtain(motionEvent.getDownTime(), j, i, fArr, motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getSource(), motionEvent.getToolType(0), motionEvent.getButtonState());
    }
}
