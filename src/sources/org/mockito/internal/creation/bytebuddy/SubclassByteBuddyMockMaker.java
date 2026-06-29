package org.mockito.internal.creation.bytebuddy;

import java.lang.reflect.Modifier;
import org.mockito.creation.instance.InstantiationException;
import org.mockito.creation.instance.Instantiator;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.configuration.plugins.Plugins;
import org.mockito.internal.util.Platform;
import org.mockito.internal.util.StringUtil;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

/* loaded from: classes3.dex */
public class SubclassByteBuddyMockMaker implements ClassCreatingMockMaker {
    private final BytecodeGenerator cachingMockBytecodeGenerator;

    public SubclassByteBuddyMockMaker() {
        this(new SubclassInjectionLoader());
    }

    public SubclassByteBuddyMockMaker(SubclassLoader subclassLoader) {
        this.cachingMockBytecodeGenerator = new TypeCachingBytecodeGenerator(new SubclassBytecodeGenerator(subclassLoader), false);
    }

    @Override // org.mockito.plugins.MockMaker
    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        Object objNewInstance;
        Class<? extends T> clsCreateMockType = createMockType(mockCreationSettings);
        Instantiator instantiator = Plugins.getInstantiatorProvider().getInstantiator(mockCreationSettings);
        try {
            try {
                objNewInstance = instantiator.newInstance(clsCreateMockType);
            } catch (ClassCastException e) {
                e = e;
                objNewInstance = null;
            }
            try {
                ((MockAccess) objNewInstance).setMockitoInterceptor(new MockMethodInterceptor(mockHandler, mockCreationSettings));
                return (T) ensureMockIsAssignableToMockedType(mockCreationSettings, objNewInstance);
            } catch (ClassCastException e2) {
                e = e2;
                throw new MockitoException(StringUtil.join("ClassCastException occurred while creating the mockito mock :", "  class to mock : " + describeClass((Class<?>) mockCreationSettings.getTypeToMock()), "  created class : " + describeClass((Class<?>) clsCreateMockType), "  proxy instance class : " + describeClass(objNewInstance), "  instance creation by : " + instantiator.getClass().getSimpleName(), "", "You might experience classloading issues, please ask the mockito mailing-list.", ""), e);
            }
        } catch (InstantiationException e3) {
            throw new MockitoException("Unable to create mock instance of type '" + clsCreateMockType.getSuperclass().getSimpleName() + "'", e3);
        }
    }

    @Override // org.mockito.internal.creation.bytebuddy.ClassCreatingMockMaker
    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        try {
            return this.cachingMockBytecodeGenerator.mockClass(MockFeatures.withMockFeatures(mockCreationSettings.getTypeToMock(), mockCreationSettings.getExtraInterfaces(), mockCreationSettings.getSerializableMode(), mockCreationSettings.isStripAnnotations()));
        } catch (Exception e) {
            throw prettifyFailure(mockCreationSettings, e);
        }
    }

    private static <T> T ensureMockIsAssignableToMockedType(MockCreationSettings<T> mockCreationSettings, T t) {
        return mockCreationSettings.getTypeToMock().cast(t);
    }

    private <T> RuntimeException prettifyFailure(MockCreationSettings<T> mockCreationSettings, Exception exc) {
        if (mockCreationSettings.getTypeToMock().isArray()) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock arrays: " + mockCreationSettings.getTypeToMock() + ".", ""), exc);
        }
        if (Modifier.isPrivate(mockCreationSettings.getTypeToMock().getModifiers())) {
            throw new MockitoException(StringUtil.join("Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".", "Most likely it is due to mocking a private class that is not visible to Mockito", ""), exc);
        }
        Object[] objArr = new Object[9];
        objArr[0] = "Mockito cannot mock this class: " + mockCreationSettings.getTypeToMock() + ".";
        objArr[1] = "";
        objArr[2] = "Mockito can only mock non-private & non-final classes.";
        objArr[3] = "If you're not sure why you're getting this error, please report to the mailing list.";
        objArr[4] = "";
        objArr[5] = Platform.warnForVM("IBM J9 VM", "Early IBM virtual machine are known to have issues with Mockito, please upgrade to an up-to-date version.\n", "Hotspot", Platform.isJava8BelowUpdate45() ? "Java 8 early builds have bugs that were addressed in Java 1.8.0_45, please update your JDK!\n" : "");
        objArr[6] = Platform.describe();
        objArr[7] = "";
        objArr[8] = "Underlying exception : " + exc;
        throw new MockitoException(StringUtil.join(objArr), exc);
    }

    private static String describeClass(Class<?> cls) {
        return cls == null ? "null" : "'" + cls.getCanonicalName() + "', loaded by classloader : '" + cls.getClassLoader() + "'";
    }

    private static String describeClass(Object obj) {
        return obj == null ? "null" : describeClass(obj.getClass());
    }

    @Override // org.mockito.plugins.MockMaker
    public MockHandler getHandler(Object obj) {
        if (obj instanceof MockAccess) {
            return ((MockAccess) obj).getMockitoInterceptor().getMockHandler();
        }
        return null;
    }

    @Override // org.mockito.plugins.MockMaker
    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        ((MockAccess) obj).setMockitoInterceptor(new MockMethodInterceptor(mockHandler, mockCreationSettings));
    }

    @Override // org.mockito.plugins.MockMaker
    public MockMaker.TypeMockability isTypeMockable(final Class<?> cls) {
        return new MockMaker.TypeMockability() { // from class: org.mockito.internal.creation.bytebuddy.SubclassByteBuddyMockMaker.1
            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public boolean mockable() {
                return (cls.isPrimitive() || Modifier.isFinal(cls.getModifiers())) ? false : true;
            }

            @Override // org.mockito.plugins.MockMaker.TypeMockability
            public String nonMockableReason() {
                return mockable() ? "" : cls.isPrimitive() ? "primitive type" : Modifier.isFinal(cls.getModifiers()) ? "final class" : StringUtil.join("not handled type");
            }
        };
    }
}
