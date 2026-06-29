package androidx.test.internal.platform.reflect;

/* loaded from: classes5.dex */
public class ReflectionException extends Exception {
    ReflectionException(Exception cause) {
        super("Reflection access failed", cause);
    }
}
