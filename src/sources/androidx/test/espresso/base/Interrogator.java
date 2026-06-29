package androidx.test.espresso.base;

import android.os.Binder;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.os.SystemClock;
import android.util.Log;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Throwables;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
final class Interrogator {
    private static final int LOOKAHEAD_MILLIS = 15;
    private static final String TAG = "Interrogator";
    private static final ThreadLocal<Boolean> interrogating = new ThreadLocal<Boolean>() { // from class: androidx.test.espresso.base.Interrogator.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Boolean initialValue() {
            return Boolean.FALSE;
        }
    };
    private static final Field messageQueueHeadField;
    private static final Method messageQueueNextMethod;
    private static final Method recycleUncheckedMethod;

    interface InterrogationHandler<R> extends QueueInterrogationHandler<R> {
        boolean beforeTaskDispatch();

        String getMessage();

        void quitting();

        void setMessage(Message message);
    }

    interface QueueInterrogationHandler<R> {
        boolean barrierUp();

        R get();

        boolean queueEmpty();

        boolean taskDueLong();

        boolean taskDueSoon();
    }

    static {
        Method declaredMethod;
        try {
            Method declaredMethod2 = MessageQueue.class.getDeclaredMethod("next", new Class[0]);
            messageQueueNextMethod = declaredMethod2;
            declaredMethod2.setAccessible(true);
            Field declaredField = MessageQueue.class.getDeclaredField("mMessages");
            messageQueueHeadField = declaredField;
            declaredField.setAccessible(true);
            try {
                declaredMethod = Message.class.getDeclaredMethod("recycleUnchecked", new Class[0]);
                try {
                    declaredMethod.setAccessible(true);
                } catch (NoSuchMethodException unused) {
                }
            } catch (NoSuchMethodException unused2) {
                declaredMethod = null;
            }
            recycleUncheckedMethod = declaredMethod;
        } catch (IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | SecurityException e) {
            Log.e(TAG, "Could not initialize interrogator!", e);
            throw new RuntimeException("Could not initialize interrogator!", e);
        }
    }

    Interrogator() {
    }

    private static void checkSanity() {
        Preconditions.checkState(Looper.myLooper() != null, "Calling non-looper thread!");
        Preconditions.checkState(Boolean.FALSE.equals(interrogating.get()), "Already interrogating!");
    }

    private static Message getNextMessage() {
        try {
            return (Message) messageQueueNextMethod.invoke(Looper.myQueue(), new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException e) {
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    private static boolean interrogateQueueState(MessageQueue messageQueue, QueueInterrogationHandler<?> queueInterrogationHandler) {
        synchronized (messageQueue) {
            try {
                try {
                    Message message = (Message) messageQueueHeadField.get(messageQueue);
                    if (message == null) {
                        return queueInterrogationHandler.queueEmpty();
                    }
                    if (message.getTarget() == null) {
                        if (Log.isLoggable(TAG, 3)) {
                            Log.d(TAG, "barrier is up");
                        }
                        return queueInterrogationHandler.barrierUp();
                    }
                    long when = message.getWhen();
                    long jUptimeMillis = SystemClock.uptimeMillis() + 15;
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "headWhen: " + when + " nowFuz: " + jUptimeMillis + " due long: " + (jUptimeMillis < when));
                    }
                    if (jUptimeMillis > when) {
                        return queueInterrogationHandler.taskDueSoon();
                    }
                    return queueInterrogationHandler.taskDueLong();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static <R> R loopAndInterrogate(InterrogationHandler<R> interrogationHandler) {
        checkSanity();
        interrogating.set(Boolean.TRUE);
        MessageQueue messageQueueMyQueue = Looper.myQueue();
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            long jClearCallingIdentity2 = Binder.clearCallingIdentity();
            boolean zInterrogateQueueState = true;
            while (zInterrogateQueueState) {
                zInterrogateQueueState = interrogateQueueState(messageQueueMyQueue, interrogationHandler);
                if (zInterrogateQueueState) {
                    Message nextMessage = getNextMessage();
                    if (nextMessage == null) {
                        interrogationHandler.quitting();
                        return interrogationHandler.get();
                    }
                    boolean zBeforeTaskDispatch = interrogationHandler.beforeTaskDispatch();
                    interrogationHandler.setMessage(nextMessage);
                    nextMessage.getTarget().dispatchMessage(nextMessage);
                    long jClearCallingIdentity3 = Binder.clearCallingIdentity();
                    if (jClearCallingIdentity3 != jClearCallingIdentity2) {
                        Log.wtf(TAG, "Thread identity changed from 0x" + Long.toHexString(jClearCallingIdentity2) + " to 0x" + Long.toHexString(jClearCallingIdentity3) + " while dispatching to " + nextMessage.getTarget().getClass().getName() + " " + String.valueOf(nextMessage.getCallback()) + " what=" + nextMessage.what);
                    }
                    recycle(nextMessage);
                    zInterrogateQueueState = zBeforeTaskDispatch;
                }
            }
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            interrogating.set(Boolean.FALSE);
            return interrogationHandler.get();
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
            interrogating.set(Boolean.FALSE);
        }
    }

    static <R> R peekAtQueueState(MessageQueue messageQueue, QueueInterrogationHandler<R> queueInterrogationHandler) {
        Preconditions.checkNotNull(messageQueue);
        Preconditions.checkNotNull(queueInterrogationHandler);
        Preconditions.checkState(!interrogateQueueState(messageQueue, queueInterrogationHandler), "It is expected that %s would stop interrogation after a single peak at the queue.", queueInterrogationHandler);
        return queueInterrogationHandler.get();
    }

    private static void recycle(Message message) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method method = recycleUncheckedMethod;
        if (method == null) {
            message.recycle();
            return;
        }
        try {
            method.invoke(message, new Object[0]);
        } catch (IllegalAccessException e) {
            e = e;
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e2) {
            e = e2;
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        } catch (SecurityException e3) {
            e = e3;
            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        } catch (InvocationTargetException e4) {
            if (e4.getCause() == null) {
                throw new RuntimeException(e4);
            }
            Throwables.throwIfUnchecked(e4.getCause());
            throw new RuntimeException(e4.getCause());
        }
    }
}
