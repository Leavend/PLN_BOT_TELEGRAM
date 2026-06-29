package org.mockito.internal.creation.bytebuddy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Modifier;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.mockito.Incubating;
import org.mockito.creation.instance.InstantiationException;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.exceptions.base.MockitoInitializationException;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;
import org.mockito.internal.util.concurrent.WeakConcurrentMap;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

@Incubating
/* loaded from: classes3.dex */
public class InlineByteBuddyMockMaker implements ClassCreatingMockMaker {
    private static final Throwable INITIALIZATION_ERROR;
    private static final Instrumentation INSTRUMENTATION;
    private final BytecodeGenerator bytecodeGenerator;
    private final WeakConcurrentMap<Object, MockMethodInterceptor> mocks;

    static {
        Instrumentation instrumentationInstall;
        Instrumentation instrumentation = null;
        try {
            try {
                instrumentationInstall = ByteBuddyAgent.install();
            } catch (IOException e) {
                throw new IllegalStateException(StringUtil.join("Mockito could not self-attach a Java agent to the current VM. This feature is required for inline mocking.", "This error occured due to an I/O error during the creation of this agent: " + e, "", "Potentially, the current VM does not support the instrumentation API correctly"), e);
            }
        } catch (Throwable th) {
            th = th;
        }
        if (!instrumentationInstall.isRetransformClassesSupported()) {
            throw new IllegalStateException(StringUtil.join("Byte Buddy requires retransformation for creating inline mocks. This feature is unavailable on the current VM.", "", "You cannot use this mock maker on this VM"));
        }
        File fileCreateTempFile = File.createTempFile("mockitoboot", ".jar");
        fileCreateTempFile.deleteOnExit();
        JarOutputStream jarOutputStream = new JarOutputStream(new FileOutputStream(fileCreateTempFile));
        try {
            InputStream resourceAsStream = InlineByteBuddyMockMaker.class.getClassLoader().getResourceAsStream("org/mockito/internal/creation/bytebuddy/MockMethodDispatcher.raw");
            if (resourceAsStream == null) {
                throw new IllegalStateException(StringUtil.join("The MockMethodDispatcher class file is not locatable: org/mockito/internal/creation/bytebuddy/MockMethodDispatcher.raw", "", "The class loader responsible for looking up the resource: " + InlineByteBuddyMockMaker.class.getClassLoader()));
            }
            jarOutputStream.putNextEntry(new JarEntry("org/mockito/internal/creation/bytebuddy/MockMethodDispatcher.class"));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = resourceAsStream.read(bArr);
                    if (i == -1) {
                        break;
                    } else {
                        jarOutputStream.write(bArr, 0, i);
                    }
                }
                resourceAsStream.close();
                jarOutputStream.closeEntry();
                jarOutputStream.close();
                instrumentationInstall.appendToBootstrapClassLoaderSearch(new JarFile(fileCreateTempFile));
                try {
                    Class<?> cls = Class.forName("org.mockito.internal.creation.bytebuddy.MockMethodDispatcher");
                    if (cls.getClassLoader() != null) {
                        throw new IllegalStateException(StringUtil.join("The MockMethodDispatcher must not be loaded manually but must be injected into the bootstrap class loader.", "", "The dispatcher class was already loaded by: " + cls.getClassLoader()));
                    }
                    th = null;
                    instrumentation = instrumentationInstall;
                    INSTRUMENTATION = instrumentation;
                    INITIALIZATION_ERROR = th;
                } catch (ClassNotFoundException e2) {
                    throw new IllegalStateException(StringUtil.join("Mockito failed to inject the MockMethodDispatcher class into the bootstrap class loader", "", "It seems like your current VM does not support the instrumentation API correctly."), e2);
                }
            } catch (Throwable th2) {
                resourceAsStream.close();
                throw th2;
            }
        } catch (Throwable th3) {
            jarOutputStream.close();
            throw th3;
        }
    }

    public InlineByteBuddyMockMaker() {
        WeakConcurrentMap.WithInlinedExpunction withInlinedExpunction = new WeakConcurrentMap.WithInlinedExpunction();
        this.mocks = withInlinedExpunction;
        Throwable th = INITIALIZATION_ERROR;
        if (th != null) {
            throw new MockitoInitializationException(StringUtil.join("Could not initialize inline Byte Buddy mock maker. (This mock maker is not supported on Android.)", "", Platform.describe()), th);
        }
        this.bytecodeGenerator = new TypeCachingBytecodeGenerator(new InlineBytecodeGenerator(INSTRUMENTATION, withInlinedExpunction), true);
    }

    @Override // org.mockito.plugins.MockMaker
    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        Class<? extends T> clsCreateMockType = createMockType(mockCreationSettings);
        try {
            T t = (T) Plugins.getInstantiatorProvider().getInstantiator(mockCreationSettings).newInstance(clsCreateMockType);
            MockMethodInterceptor mockMethodInterceptor = new MockMethodInterceptor(mockHandler, mockCreationSettings);
            this.mocks.put(t, mockMethodInterceptor);
            if (t instanceof MockAccess) {
                ((MockAccess) t).setMockitoInterceptor(mockMethodInterceptor);
            }
            return t;
        } catch (InstantiationException e) {
            throw new MockitoException("Unable to create mock instance of type '" + clsCreateMockType.getSimpleName() + "'", e);
        }
    }

    @Override // org.mockito.internal.creation.bytebuddy.ClassCreatingMockMaker
    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        try {
            return this.bytecodeGenerator.mockClass(MockFeatures.withMockFeatures(mockCreationSettings.getTypeToMock(), mockCreationSettings.getExtraInterfaces(), mockCreationSettings.getSerializableMode(), mockCreationSettings.isStripAnnotations()));
        } catch (Exception e) {
            throw prettifyFailure(mockCreationSettings, e);
        }
    }

    private <T> RuntimeException prettifyFailure(MockCreationSettings<T> mockCreationSettings, Exception exc) {
        if (mockCreationSettings.getTypeToMock().isArray()) {
            throw new MockitoException(StringUtil.join("Arrays cannot be mocked: " + mockCreationSettings.getTypeToMock() + ".", ""), exc);
        }
        if (Modifier.isFinal(mockCreationSettings.getTypeToMock().getModifiers())) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Can not mock final classes with the following settings :", " - explicit serialization (e.g. withSettings().serializable())", " - extra interfaces (e.g. withSettings().extraInterfaces(...))", "", "You are seeing this disclaimer because Mockito is configured to create inlined mocks.", "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.", "", "Underlying exception : " + exc), exc);
        }
        if (Modifier.isPrivate(mockCreationSettings.getTypeToMock().getModifiers())) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Most likely it is a private class that is not visible by Mockito", "", "You are seeing this disclaimer because Mockito is configured to create inlined mocks.", "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.", ""), exc);
        }
        Object[] objArr = new Object[11];
        objArr[0] = "Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".";
        objArr[1] = "";
        objArr[2] = "If you're not sure why you're getting this error, please report to the mailing list.";
        objArr[3] = "";
        objArr[4] = Platform.warnForVM("IBM J9 VM", "Early IBM virtual machine are known to have issues with Mockito, please upgrade to an up-to-date version.\n", "Hotspot", Platform.isJava8BelowUpdate45() ? "Java 8 early builds have bugs that were addressed in Java 1.8.0_45, please update your JDK!\n" : "");
        objArr[5] = Platform.describe();
        objArr[6] = "";
        objArr[7] = "You are seeing this disclaimer because Mockito is configured to create inlined mocks.";
        objArr[8] = "You can learn about inline mocks and their limitations under item #39 of the Mockito class javadoc.";
        objArr[9] = "";
        objArr[10] = "Underlying exception : " + exc;
        throw new MockitoException(StringUtil.join(objArr), exc);
    }

    @Override // org.mockito.plugins.MockMaker
    public MockHandler getHandler(Object obj) {
        MockMethodInterceptor mockMethodInterceptor = this.mocks.get(obj);
        if (mockMethodInterceptor == null) {
            return null;
        }
        return mockMethodInterceptor.handler;
    }

    @Override // org.mockito.plugins.MockMaker
    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        MockMethodInterceptor mockMethodInterceptor = new MockMethodInterceptor(mockHandler, mockCreationSettings);
        this.mocks.put(obj, mockMethodInterceptor);
        if (obj instanceof MockAccess) {
            ((MockAccess) obj).setMockitoInterceptor(mockMethodInterceptor);
        }
    }

    @Override // org.mockito.plugins.MockMaker
    public MockMaker.TypeMockability isTypeMockable(final Class<?> cls) {
        return new MockMaker.TypeMockability() { // from class: org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker.1
            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public boolean mockable() {
                return InlineByteBuddyMockMaker.INSTRUMENTATION.isModifiableClass(cls) && !InlineBytecodeGenerator.EXCLUDES.contains(cls);
            }

            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public String nonMockableReason() {
                return mockable() ? "" : cls.isPrimitive() ? "primitive type" : InlineBytecodeGenerator.EXCLUDES.contains(cls) ? "Cannot mock wrapper types, String.class or Class.class" : "VM does not not support modification of given type";
            }
        };
    }
}
