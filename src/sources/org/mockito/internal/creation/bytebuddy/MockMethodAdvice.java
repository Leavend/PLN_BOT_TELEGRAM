package org.mockito.internal.creation.bytebuddy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import net.bytebuddy.implementation.bind.annotation.Argument;
import net.bytebuddy.implementation.bind.annotation.This;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.debugging.LocationImpl;
import org.mockito.internal.exceptions.stacktrace.ConditionalStackTraceFilter;
import org.mockito.internal.invocation.RealMethod;
import org.mockito.internal.invocation.SerializableMethod;
import org.mockito.internal.invocation.mockref.MockReference;
import org.mockito.internal.invocation.mockref.MockWeakReference;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;

/* loaded from: classes3.dex */
public class MockMethodAdvice extends MockMethodDispatcher {
    private final String identifier;
    final WeakConcurrentMap<Object, MockMethodInterceptor> interceptors;
    private final SelfCallInfo selfCallInfo = new SelfCallInfo();
    private final MethodGraph.Compiler compiler = MethodGraph.Compiler.Default.forJavaHierarchy();
    private final WeakConcurrentMap<Class<?>, SoftReference<MethodGraph>> graphs = new WeakConcurrentMap.WithInlinedExpunction();

    @Retention(RetentionPolicy.RUNTIME)
    @interface Identifier {
    }

    public MockMethodAdvice(WeakConcurrentMap<Object, MockMethodInterceptor> weakConcurrentMap, String str) {
        this.interceptors = weakConcurrentMap;
        this.identifier = str;
    }

    @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
    private static Callable<?> enter(@Identifier String str, @Advice.This Object obj, @Advice.Origin Method method, @Advice.AllArguments Object[] objArr) throws Throwable {
        MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
        if (mockMethodDispatcher == null || !mockMethodDispatcher.isMocked(obj) || mockMethodDispatcher.isOverridden(obj, method)) {
            return null;
        }
        return mockMethodDispatcher.handle(obj, method, objArr);
    }

    @Advice.OnMethodExit
    private static void exit(@Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object obj, @Advice.Enter Callable<?> callable) throws Exception {
        if (callable != null) {
            callable.call();
        }
    }

    static Throwable hideRecursiveCall(Throwable th, int i, Class<?> cls) {
        try {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int i2 = 0;
            do {
                i2++;
            } while (!stackTrace[(stackTrace.length - i) - i2].getClassName().equals(cls.getName()));
            int length = (stackTrace.length - i) - i2;
            StackTraceElement[] stackTraceElementArr = new StackTraceElement[stackTrace.length - i2];
            System.arraycopy(stackTrace, 0, stackTraceElementArr, 0, length);
            System.arraycopy(stackTrace, i2 + length, stackTraceElementArr, length, i);
            th.setStackTrace(stackTraceElementArr);
        } catch (RuntimeException unused) {
        }
        return th;
    }

    public Callable<?> handle(Object obj, Method method, Object[] objArr) throws Throwable {
        RealMethod realMethodCall;
        MockMethodInterceptor mockMethodInterceptor = this.interceptors.get(obj);
        if (mockMethodInterceptor == null) {
            return null;
        }
        if (obj instanceof Serializable) {
            realMethodCall = new SerializableRealMethodCall(this.identifier, method, obj, objArr);
        } else {
            realMethodCall = new RealMethodCall(this.selfCallInfo, method, obj, objArr);
        }
        Throwable th = new Throwable();
        th.setStackTrace(skipInlineMethodElement(th.getStackTrace()));
        return new ReturnValueWrapper(mockMethodInterceptor.doIntercept(obj, method, objArr, realMethodCall, new LocationImpl(th)));
    }

    public boolean isMock(Object obj) {
        return obj != this.interceptors.target && this.interceptors.containsKey(obj);
    }

    public boolean isMocked(Object obj) {
        return this.selfCallInfo.checkSuperCall(obj) && isMock(obj);
    }

    public boolean isOverridden(Object obj, Method method) {
        SoftReference<MethodGraph> softReference = this.graphs.get(obj.getClass());
        MethodGraph methodGraphCompile = softReference == null ? null : softReference.get();
        if (methodGraphCompile == null) {
            methodGraphCompile = this.compiler.compile(new TypeDescription.ForLoadedType(obj.getClass()));
            this.graphs.put(obj.getClass(), new SoftReference<>(methodGraphCompile));
        }
        MethodGraph.Node nodeLocate = methodGraphCompile.locate(new MethodDescription.ForLoadedMethod(method).asSignatureToken());
        return (nodeLocate.getSort().isResolved() && nodeLocate.getRepresentative().asDefined().getDeclaringType().represents(method.getDeclaringClass())) ? false : true;
    }

    private static class RealMethodCall implements RealMethod {
        private final Object[] arguments;
        private final MockWeakReference<Object> instanceRef;
        private final Method origin;
        private final SelfCallInfo selfCallInfo;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return true;
        }

        private RealMethodCall(SelfCallInfo selfCallInfo, Method method, Object obj, Object[] objArr) {
            this.selfCallInfo = selfCallInfo;
            this.origin = method;
            this.instanceRef = new MockWeakReference<>(obj);
            this.arguments = objArr;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() throws Throwable {
            if (!Modifier.isPublic(this.origin.getDeclaringClass().getModifiers() & this.origin.getModifiers())) {
                this.origin.setAccessible(true);
            }
            this.selfCallInfo.set(this.instanceRef.get());
            return MockMethodAdvice.tryInvoke(this.origin, this.instanceRef.get(), this.arguments);
        }
    }

    private static class SerializableRealMethodCall implements RealMethod {
        private final Object[] arguments;
        private final String identifier;
        private final MockReference<Object> instanceRef;
        private final SerializableMethod origin;

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return true;
        }

        private SerializableRealMethodCall(String str, Method method, Object obj, Object[] objArr) {
            this.origin = new SerializableMethod(method);
            this.identifier = str;
            this.instanceRef = new MockWeakReference(obj);
            this.arguments = objArr;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() throws Throwable {
            Method javaMethod = this.origin.getJavaMethod();
            if (!Modifier.isPublic(javaMethod.getDeclaringClass().getModifiers() & javaMethod.getModifiers())) {
                javaMethod.setAccessible(true);
            }
            MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(this.identifier, this.instanceRef.get());
            if (!(mockMethodDispatcher instanceof MockMethodAdvice)) {
                throw new MockitoException("Unexpected dispatcher for advice-based super call");
            }
            MockMethodAdvice mockMethodAdvice = (MockMethodAdvice) mockMethodDispatcher;
            Object objReplace = mockMethodAdvice.selfCallInfo.replace(this.instanceRef.get());
            try {
                return MockMethodAdvice.tryInvoke(javaMethod, this.instanceRef.get(), this.arguments);
            } finally {
                mockMethodAdvice.selfCallInfo.set(objReplace);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object tryInvoke(Method method, Object obj, Object[] objArr) throws Throwable {
        try {
            return method.invoke(obj, objArr);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            new ConditionalStackTraceFilter().filter(hideRecursiveCall(cause, new Throwable().getStackTrace().length, method.getDeclaringClass()));
            throw cause;
        }
    }

    private static StackTraceElement[] skipInlineMethodElement(StackTraceElement[] stackTraceElementArr) {
        ArrayList arrayList = new ArrayList(stackTraceElementArr.length);
        int i = 0;
        while (i < stackTraceElementArr.length) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            arrayList.add(stackTraceElement);
            if (stackTraceElement.getClassName().equals(MockMethodAdvice.class.getName()) && stackTraceElement.getMethodName().equals("handle")) {
                i++;
            }
            i++;
        }
        return (StackTraceElement[]) arrayList.toArray(new StackTraceElement[arrayList.size()]);
    }

    private static class ReturnValueWrapper implements Callable<Object> {
        private final Object returned;

        private ReturnValueWrapper(Object obj) {
            this.returned = obj;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            return this.returned;
        }
    }

    private static class SelfCallInfo extends ThreadLocal<Object> {
        private SelfCallInfo() {
        }

        Object replace(Object obj) {
            Object obj2 = get();
            set(obj);
            return obj2;
        }

        boolean checkSuperCall(Object obj) {
            if (obj != get()) {
                return true;
            }
            set(null);
            return false;
        }
    }

    static class ForHashCode {
        ForHashCode() {
        }

        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        private static boolean enter(@Identifier String str, @Advice.This Object obj) {
            MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
            return mockMethodDispatcher != null && mockMethodDispatcher.isMock(obj);
        }

        @Advice.OnMethodExit
        private static void enter(@Advice.This Object obj, @Advice.Return(readOnly = false) int i, @Advice.Enter boolean z) {
            if (z) {
                System.identityHashCode(obj);
            }
        }
    }

    static class ForEquals {
        @Advice.OnMethodExit
        private static void enter(@Advice.This Object obj, @Advice.Argument(0) Object obj2, @Advice.Return(readOnly = false) boolean z, @Advice.Enter boolean z2) {
        }

        ForEquals() {
        }

        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        private static boolean enter(@Identifier String str, @Advice.This Object obj) {
            MockMethodDispatcher mockMethodDispatcher = MockMethodDispatcher.get(str, obj);
            return mockMethodDispatcher != null && mockMethodDispatcher.isMock(obj);
        }
    }

    public static class ForReadObject {
        public static void doReadObject(@Identifier String str, @This MockAccess mockAccess, @Argument(0) ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            MockMethodAdvice mockMethodAdvice = (MockMethodAdvice) MockMethodDispatcher.get(str, mockAccess);
            if (mockMethodAdvice != null) {
                mockMethodAdvice.interceptors.put(mockAccess, mockAccess.getMockitoInterceptor());
            }
        }
    }
}
