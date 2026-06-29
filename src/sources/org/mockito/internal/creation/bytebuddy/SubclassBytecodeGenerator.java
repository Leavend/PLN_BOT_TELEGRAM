package org.mockito.internal.creation.bytebuddy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.ModifierContributor;
import net.bytebuddy.description.modifier.SynchronizationState;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.Transformer;
import net.bytebuddy.dynamic.loading.MultipleParentClassLoader;
import net.bytebuddy.dynamic.scaffold.TypeValidation;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.attribute.MethodAttributeAppender;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.creation.bytebuddy.ByteBuddyCrossClassLoaderSerializationSupport;
import org.mockito.internal.creation.bytebuddy.MockMethodInterceptor;
import org.mockito.internal.util.StringUtil;
import org.mockito.mock.SerializableMode;

/* loaded from: classes3.dex */
class SubclassBytecodeGenerator implements BytecodeGenerator {
    private static final String CODEGEN_PACKAGE = "org.mockito.codegen.";
    private final ByteBuddy byteBuddy;
    private final Implementation dispatcher;
    private final Implementation equals;
    private final Implementation hashCode;
    private final SubclassLoader loader;
    private final ElementMatcher<? super MethodDescription> matcher;
    private final Random random;
    private final Implementation readReplace;
    private final Implementation writeReplace;

    public SubclassBytecodeGenerator() {
        this(new SubclassInjectionLoader());
    }

    public SubclassBytecodeGenerator(SubclassLoader subclassLoader) {
        this(subclassLoader, null, ElementMatchers.any());
    }

    public SubclassBytecodeGenerator(Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this(new SubclassInjectionLoader(), implementation, elementMatcher);
    }

    protected SubclassBytecodeGenerator(SubclassLoader subclassLoader, Implementation implementation, ElementMatcher<? super MethodDescription> elementMatcher) {
        this.dispatcher = MethodDelegation.to(MockMethodInterceptor.DispatcherDefaultingToRealMethod.class);
        this.hashCode = MethodDelegation.to(MockMethodInterceptor.ForHashCode.class);
        this.equals = MethodDelegation.to(MockMethodInterceptor.ForEquals.class);
        this.writeReplace = MethodDelegation.to(MockMethodInterceptor.ForWriteReplace.class);
        this.loader = subclassLoader;
        this.readReplace = implementation;
        this.matcher = elementMatcher;
        this.byteBuddy = new ByteBuddy().with(TypeValidation.DISABLED);
        this.random = new Random();
    }

    @Override // org.mockito.internal.creation.bytebuddy.BytecodeGenerator
    public <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures) {
        MethodAttributeAppender.NoOp noOp;
        String strNameFor = nameFor(mockFeatures.mockedType);
        DynamicType.Builder.MethodDefinition methodDefinitionTransform = this.byteBuddy.subclass(mockFeatures.mockedType).name(strNameFor).ignoreAlso(isGroovyMethod()).annotateType(mockFeatures.stripAnnotations ? new Annotation[0] : mockFeatures.mockedType.getAnnotations()).implement(new ArrayList(mockFeatures.interfaces)).method(this.matcher).intercept(this.dispatcher).transform(Transformer.ForMethod.withModifiers(new ModifierContributor.ForMethod[]{SynchronizationState.PLAIN}));
        if (mockFeatures.stripAnnotations) {
            noOp = MethodAttributeAppender.NoOp.INSTANCE;
        } else {
            noOp = MethodAttributeAppender.ForInstrumentedMethod.INCLUDING_RECEIVER;
        }
        DynamicType.Builder.MethodDefinition.ReceiverTypeDefinition receiverTypeDefinitionIntercept = methodDefinitionTransform.attribute((MethodAttributeAppender.Factory) noOp).method(ElementMatchers.isHashCode()).intercept(this.hashCode).method(ElementMatchers.isEquals()).intercept(this.equals).serialVersionUid(42L).defineField("mockitoInterceptor", MockMethodInterceptor.class, new ModifierContributor.ForField[]{Visibility.PRIVATE}).implement(new Type[]{MockAccess.class}).intercept(FieldAccessor.ofBeanProperty());
        if (mockFeatures.serializableMode == SerializableMode.ACROSS_CLASSLOADERS) {
            receiverTypeDefinitionIntercept = receiverTypeDefinitionIntercept.implement(new Type[]{ByteBuddyCrossClassLoaderSerializationSupport.CrossClassLoaderSerializableMock.class}).intercept(this.writeReplace);
        }
        if (this.readReplace != null) {
            receiverTypeDefinitionIntercept = receiverTypeDefinitionIntercept.defineMethod("readObject", Void.TYPE, new ModifierContributor.ForMethod[]{Visibility.PRIVATE}).withParameters(new Type[]{ObjectInputStream.class}).throwing(new Type[]{ClassNotFoundException.class, IOException.class}).intercept(this.readReplace);
        }
        ClassLoader classLoaderBuild = new MultipleParentClassLoader.Builder().append(new Class[]{mockFeatures.mockedType}).append(mockFeatures.interfaces).append(new ClassLoader[]{Thread.currentThread().getContextClassLoader()}).append(new Class[]{MockAccess.class, MockMethodInterceptor.DispatcherDefaultingToRealMethod.class}).append(new Class[]{MockMethodInterceptor.class, MockMethodInterceptor.ForHashCode.class, MockMethodInterceptor.ForEquals.class}).build(MockMethodInterceptor.class.getClassLoader());
        if (classLoaderBuild != mockFeatures.mockedType.getClassLoader()) {
            assertVisibility(mockFeatures.mockedType);
            Iterator<Class<?>> it = mockFeatures.interfaces.iterator();
            while (it.hasNext()) {
                assertVisibility(it.next());
            }
            receiverTypeDefinitionIntercept = receiverTypeDefinitionIntercept.ignoreAlso(ElementMatchers.isPackagePrivate().or(ElementMatchers.returns(ElementMatchers.isPackagePrivate())).or(ElementMatchers.hasParameters(ElementMatchers.whereAny(ElementMatchers.hasType(ElementMatchers.isPackagePrivate())))));
        }
        return receiverTypeDefinitionIntercept.make().load(classLoaderBuild, this.loader.resolveStrategy(mockFeatures.mockedType, classLoaderBuild, strNameFor.startsWith(CODEGEN_PACKAGE))).getLoaded();
    }

    private static ElementMatcher<MethodDescription> isGroovyMethod() {
        return ElementMatchers.isDeclaredBy(ElementMatchers.named("groovy.lang.GroovyObjectSupport"));
    }

    private String nameFor(Class<?> cls) {
        String name = cls.getName();
        if (isComingFromJDK(cls) || isComingFromSignedJar(cls) || isComingFromSealedPackage(cls)) {
            name = CODEGEN_PACKAGE + cls.getSimpleName();
        }
        return String.format("%s$%s$%d", name, "MockitoMock", Integer.valueOf(Math.abs(this.random.nextInt())));
    }

    private boolean isComingFromJDK(Class<?> cls) {
        return (cls.getPackage() != null && "Java Runtime Environment".equalsIgnoreCase(cls.getPackage().getImplementationTitle())) || cls.getName().startsWith("java.") || cls.getName().startsWith("javax.");
    }

    private boolean isComingFromSealedPackage(Class<?> cls) {
        return cls.getPackage() != null && cls.getPackage().isSealed();
    }

    private boolean isComingFromSignedJar(Class<?> cls) {
        return cls.getSigners() != null;
    }

    private static void assertVisibility(Class<?> cls) {
        if (!Modifier.isPublic(cls.getModifiers())) {
            throw new MockitoException(StringUtil.join("Cannot create mock for " + cls, "", "The type is not public and its mock class is loaded by a different class loader.", "This can have multiple reasons:", " - You are mocking a class with additional interfaces of another class loader", " - Mockito is loaded by a different class loader than the mocked type (e.g. with OSGi)", " - The thread's context class loader is different than the mock's class loader"));
        }
    }
}
