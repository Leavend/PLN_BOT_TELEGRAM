package org.mockito.internal.creation.bytebuddy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.bytebuddy.dynamic.loading.ClassInjector;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.mockito.codegen.InjectionBase;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;

/* loaded from: classes3.dex */
class SubclassInjectionLoader implements SubclassLoader {
    private static final String ERROR_MESSAGE = StringUtil.join("The current JVM does not support any class injection mechanism.", "", "Currently, Mockito supports injection via neither by method handle lookups or using sun.misc.Unsafe", "Neither seems to be available on your current JVM.");
    private final SubclassLoader loader;

    SubclassInjectionLoader() {
        if (!Boolean.getBoolean("org.mockito.internal.simulateJava11") && ClassInjector.UsingReflection.isAvailable()) {
            this.loader = new WithReflection();
        } else {
            if (ClassInjector.UsingLookup.isAvailable()) {
                this.loader = tryLookup();
                return;
            }
            throw new MockitoException(StringUtil.join(ERROR_MESSAGE, "", Platform.describe()));
        }
    }

    private static SubclassLoader tryLookup() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("java.lang.invoke.MethodHandles");
            Object objInvoke = cls.getMethod("lookup", new Class[0]).invoke(null, new Object[0]);
            Method method = cls.getMethod("privateLookupIn", Class.class, Class.forName("java.lang.invoke.MethodHandles$Lookup"));
            return new WithLookup(objInvoke, method.invoke(null, InjectionBase.class, objInvoke), method);
        } catch (Exception e) {
            throw new MockitoException(StringUtil.join(ERROR_MESSAGE, "", Platform.describe()), e);
        }
    }

    private static class WithReflection implements SubclassLoader {
        private WithReflection() {
        }

        @Override // org.mockito.internal.creation.bytebuddy.SubclassLoader
        public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
            ClassLoadingStrategy.Default r2 = ClassLoadingStrategy.Default.INJECTION;
            if (z) {
                cls = InjectionBase.class;
            }
            return r2.with(cls.getProtectionDomain());
        }
    }

    private static class WithLookup implements SubclassLoader {
        private final Object codegenLookup;
        private final Object lookup;
        private final Method privateLookupIn;

        WithLookup(Object obj, Object obj2, Method method) {
            this.lookup = obj;
            this.codegenLookup = obj2;
            this.privateLookupIn = method;
        }

        @Override // org.mockito.internal.creation.bytebuddy.SubclassLoader
        public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
            if (z) {
                return ClassLoadingStrategy.UsingLookup.of(this.codegenLookup);
            }
            if (classLoader != cls.getClassLoader()) {
                return ClassLoadingStrategy.Default.WRAPPER.with(cls.getProtectionDomain());
            }
            try {
                try {
                    return ClassLoadingStrategy.UsingLookup.of(this.privateLookupIn.invoke(null, cls, this.lookup));
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof IllegalAccessException) {
                        return ClassLoadingStrategy.Default.WRAPPER.with(cls.getProtectionDomain());
                    }
                    throw e.getCause();
                }
            } catch (Throwable th) {
                throw new MockitoException(StringUtil.join("The Java module system prevents Mockito from defining a mock class in the same package as " + cls, "", "To overcome this, you must open and export the mocked type to Mockito.", "Remember that you can also do so programmatically if the mocked class is defined by the same module as your test code", th));
            }
        }
    }

    @Override // org.mockito.internal.creation.bytebuddy.SubclassLoader
    public ClassLoadingStrategy<ClassLoader> resolveStrategy(Class<?> cls, ClassLoader classLoader, boolean z) {
        return this.loader.resolveStrategy(cls, classLoader, z);
    }
}
