package org.mockito.internal.creation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.mockito.MockSettings;
import org.mockito.internal.creation.settings.CreationSettings;
import org.mockito.internal.debugging.VerboseMockInvocationLogger;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.util.Checks;
import org.mockito.internal.util.MockCreationValidator;
import org.mockito.internal.util.MockNameImpl;
import org.mockito.internal.util.collections.Sets;
import org.mockito.listeners.InvocationListener;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.mock.MockCreationSettings;
import org.mockito.mock.MockName;
import org.mockito.mock.SerializableMode;
import org.mockito.stubbing.Answer;

/* loaded from: classes3.dex */
public class MockSettingsImpl<T> extends CreationSettings<T> implements MockSettings, MockCreationSettings<T> {
    private static final long serialVersionUID = 4475297236197939569L;
    private Object[] constructorArgs;
    private Object outerClassInstance;
    private boolean useConstructor;

    @Override // org.mockito.MockSettings
    public MockSettings serializable() {
        return serializable(SerializableMode.BASIC);
    }

    @Override // org.mockito.MockSettings
    public MockSettings serializable(SerializableMode serializableMode) {
        this.serializableMode = serializableMode;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings extraInterfaces(Class<?>... clsArr) {
        if (clsArr == null || clsArr.length == 0) {
            throw Reporter.extraInterfacesRequiresAtLeastOneInterface();
        }
        for (Class<?> cls : clsArr) {
            if (cls == null) {
                throw Reporter.extraInterfacesDoesNotAcceptNullParameters();
            }
            if (!cls.isInterface()) {
                throw Reporter.extraInterfacesAcceptsOnlyInterfaces(cls);
            }
        }
        this.extraInterfaces = Sets.newSet(clsArr);
        return this;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public MockName getMockName() {
        return this.mockName;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Set<Class<?>> getExtraInterfaces() {
        return this.extraInterfaces;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Object getSpiedInstance() {
        return this.spiedInstance;
    }

    @Override // org.mockito.MockSettings
    public MockSettings name(String str) {
        this.name = str;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings spiedInstance(Object obj) {
        this.spiedInstance = obj;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings defaultAnswer(Answer answer) {
        this.defaultAnswer = answer;
        if (answer != null) {
            return this;
        }
        throw Reporter.defaultAnswerDoesNotAcceptNullParameter();
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Answer<Object> getDefaultAnswer() {
        return this.defaultAnswer;
    }

    @Override // org.mockito.MockSettings
    public MockSettingsImpl<T> stubOnly() {
        this.stubOnly = true;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings useConstructor(Object... objArr) {
        Checks.checkNotNull(objArr, "constructorArgs", "If you need to pass null, please cast it to the right type, e.g.: useConstructor((String) null)");
        this.useConstructor = true;
        this.constructorArgs = objArr;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings outerInstance(Object obj) {
        this.outerClassInstance = obj;
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings withoutAnnotations() {
        this.stripAnnotations = true;
        return this;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public boolean isUsingConstructor() {
        return this.useConstructor;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Object getOuterClassInstance() {
        return this.outerClassInstance;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Object[] getConstructorArgs() {
        if (this.outerClassInstance == null) {
            return this.constructorArgs;
        }
        ArrayList arrayList = new ArrayList(this.constructorArgs.length + 1);
        arrayList.add(this.outerClassInstance);
        arrayList.addAll(Arrays.asList(this.constructorArgs));
        return arrayList.toArray(new Object[this.constructorArgs.length + 1]);
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public boolean isStubOnly() {
        return this.stubOnly;
    }

    @Override // org.mockito.MockSettings
    public MockSettings verboseLogging() {
        if (!invocationListenersContainsType(VerboseMockInvocationLogger.class)) {
            invocationListeners(new VerboseMockInvocationLogger());
        }
        return this;
    }

    @Override // org.mockito.MockSettings
    public MockSettings invocationListeners(InvocationListener... invocationListenerArr) {
        if (invocationListenerArr == null || invocationListenerArr.length == 0) {
            throw Reporter.invocationListenersRequiresAtLeastOneListener();
        }
        addListeners(invocationListenerArr, this.invocationListeners, "invocationListeners");
        return this;
    }

    private static <T> void addListeners(T[] tArr, List<T> list, String str) {
        if (tArr == null) {
            throw Reporter.methodDoesNotAcceptParameter(str, "null vararg array.");
        }
        for (T t : tArr) {
            if (t == null) {
                throw Reporter.methodDoesNotAcceptParameter(str, "null listeners.");
            }
            list.add(t);
        }
    }

    @Override // org.mockito.MockSettings
    public MockSettings verificationStartedListeners(VerificationStartedListener... verificationStartedListenerArr) {
        addListeners(verificationStartedListenerArr, this.verificationStartedListeners, "verificationStartedListeners");
        return this;
    }

    private boolean invocationListenersContainsType(Class<?> cls) {
        Iterator<InvocationListener> it = this.invocationListeners.iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public List<InvocationListener> getInvocationListeners() {
        return this.invocationListeners;
    }

    public boolean hasInvocationListeners() {
        return !this.invocationListeners.isEmpty();
    }

    @Override // org.mockito.internal.creation.settings.CreationSettings, org.mockito.mock.MockCreationSettings
    public Class<T> getTypeToMock() {
        return this.typeToMock;
    }

    @Override // org.mockito.MockSettings
    public <T> MockCreationSettings<T> build(Class<T> cls) {
        return validatedSettings(cls, this);
    }

    private static <T> CreationSettings<T> validatedSettings(Class<T> cls, CreationSettings<T> creationSettings) {
        MockCreationValidator mockCreationValidator = new MockCreationValidator();
        mockCreationValidator.validateType(cls);
        mockCreationValidator.validateExtraInterfaces(cls, creationSettings.getExtraInterfaces());
        mockCreationValidator.validateMockedType(cls, creationSettings.getSpiedInstance());
        mockCreationValidator.validateConstructorUse(creationSettings.isUsingConstructor(), creationSettings.getSerializableMode());
        CreationSettings<T> creationSettings2 = new CreationSettings<>(creationSettings);
        creationSettings2.setMockName(new MockNameImpl(creationSettings.getName(), cls));
        creationSettings2.setTypeToMock(cls);
        creationSettings2.setExtraInterfaces(prepareExtraInterfaces(creationSettings));
        return creationSettings2;
    }

    private static Set<Class<?>> prepareExtraInterfaces(CreationSettings creationSettings) {
        HashSet hashSet = new HashSet(creationSettings.getExtraInterfaces());
        if (creationSettings.isSerializable()) {
            hashSet.add(Serializable.class);
        }
        return hashSet;
    }
}
