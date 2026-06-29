package org.mockito.internal.invocation;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.exceptions.VerificationAwareInvocation;
import org.mockito.internal.invocation.mockref.MockReference;
import org.mockito.internal.reporting.PrintSettings;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.Location;
import org.mockito.invocation.StubInfo;

/* loaded from: classes3.dex */
public class InterceptedInvocation implements Invocation, VerificationAwareInvocation {
    public static final RealMethod NO_OP = new RealMethod() { // from class: org.mockito.internal.invocation.InterceptedInvocation.1
        @Override // org.mockito.internal.invocation.RealMethod
        public Object invoke() throws Throwable {
            return null;
        }

        @Override // org.mockito.internal.invocation.RealMethod
        public boolean isInvokable() {
            return false;
        }
    };
    private static final long serialVersionUID = 475027563923510472L;
    private final Object[] arguments;
    private boolean isIgnoredForVerification;
    private final Location location;
    private final MockReference<Object> mockRef;
    private final MockitoMethod mockitoMethod;
    private final Object[] rawArguments;
    private final RealMethod realMethod;
    private final int sequenceNumber;
    private StubInfo stubInfo;
    private boolean verified;

    public int hashCode() {
        return 1;
    }

    public InterceptedInvocation(MockReference<Object> mockReference, MockitoMethod mockitoMethod, Object[] objArr, RealMethod realMethod, Location location, int i) {
        this.mockRef = mockReference;
        this.mockitoMethod = mockitoMethod;
        this.arguments = ArgumentsProcessor.expandArgs(mockitoMethod, objArr);
        this.rawArguments = objArr;
        this.realMethod = realMethod;
        this.location = location;
        this.sequenceNumber = i;
    }

    @Override // org.mockito.invocation.Invocation, org.mockito.internal.exceptions.VerificationAwareInvocation
    public boolean isVerified() {
        return this.verified || this.isIgnoredForVerification;
    }

    @Override // org.mockito.invocation.Invocation
    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    @Override // org.mockito.invocation.Invocation, org.mockito.invocation.DescribedInvocation
    public Location getLocation() {
        return this.location;
    }

    @Override // org.mockito.invocation.Invocation
    public Object[] getRawArguments() {
        return this.rawArguments;
    }

    @Override // org.mockito.invocation.Invocation
    public Class<?> getRawReturnType() {
        return this.mockitoMethod.getReturnType();
    }

    @Override // org.mockito.invocation.Invocation
    public void markVerified() {
        this.verified = true;
    }

    @Override // org.mockito.invocation.Invocation
    public StubInfo stubInfo() {
        return this.stubInfo;
    }

    @Override // org.mockito.invocation.Invocation
    public void markStubbed(StubInfo stubInfo) {
        this.stubInfo = stubInfo;
    }

    @Override // org.mockito.invocation.Invocation
    public boolean isIgnoredForVerification() {
        return this.isIgnoredForVerification;
    }

    @Override // org.mockito.invocation.Invocation
    public void ignoreForVerification() {
        this.isIgnoredForVerification = true;
    }

    @Override // org.mockito.invocation.InvocationOnMock
    public Object getMock() {
        return this.mockRef.get();
    }

    @Override // org.mockito.invocation.InvocationOnMock
    public Method getMethod() {
        return this.mockitoMethod.getJavaMethod();
    }

    @Override // org.mockito.invocation.InvocationOnMock
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override // org.mockito.invocation.InvocationOnMock
    public <T> T getArgument(int i) {
        return (T) this.arguments[i];
    }

    @Override // org.mockito.invocation.InvocationOnMock
    public Object callRealMethod() throws Throwable {
        if (!this.realMethod.isInvokable()) {
            throw Reporter.cannotCallAbstractRealMethod();
        }
        return this.realMethod.invoke();
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        InterceptedInvocation interceptedInvocation = (InterceptedInvocation) obj;
        return this.mockRef.get().equals(interceptedInvocation.mockRef.get()) && this.mockitoMethod.equals(interceptedInvocation.mockitoMethod) && equalArguments(interceptedInvocation.arguments);
    }

    private boolean equalArguments(Object[] objArr) {
        return Arrays.equals(objArr, this.arguments);
    }

    @Override // org.mockito.invocation.DescribedInvocation
    public String toString() {
        return new PrintSettings().print(ArgumentsProcessor.argumentsToMatchers(getArguments()), this);
    }
}
