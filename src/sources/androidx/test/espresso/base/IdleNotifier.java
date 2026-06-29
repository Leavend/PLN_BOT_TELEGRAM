package androidx.test.espresso.base;

/* loaded from: classes5.dex */
interface IdleNotifier<CB> {
    void cancelCallback();

    boolean isIdleNow();

    void registerNotificationCallback(CB cb);
}
