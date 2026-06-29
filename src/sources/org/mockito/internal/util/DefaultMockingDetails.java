package org.mockito.internal.util;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;
import org.mockito.MockingDetails;
import org.mockito.exceptions.misusing.NotAMockException;
import org.mockito.internal.debugging.InvocationsPrinter;
import org.mockito.internal.stubbing.InvocationContainerImpl;
import org.mockito.internal.stubbing.StubbingComparator;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.stubbing.Stubbing;

/* loaded from: classes3.dex */
public class DefaultMockingDetails implements MockingDetails {
    private final Object toInspect;

    public DefaultMockingDetails(Object obj) {
        this.toInspect = obj;
    }

    @Override // org.mockito.MockingDetails
    public boolean isMock() {
        return MockUtil.isMock(this.toInspect);
    }

    @Override // org.mockito.MockingDetails
    public boolean isSpy() {
        return MockUtil.isSpy(this.toInspect);
    }

    @Override // org.mockito.MockingDetails
    public Collection<Invocation> getInvocations() {
        return getInvocationContainer().getInvocations();
    }

    private InvocationContainerImpl getInvocationContainer() {
        assertGoodMock();
        return MockUtil.getInvocationContainer(this.toInspect);
    }

    @Override // org.mockito.MockingDetails
    public MockCreationSettings<?> getMockCreationSettings() {
        return mockHandler().getMockSettings();
    }

    @Override // org.mockito.MockingDetails
    public Collection<Stubbing> getStubbings() {
        List<Stubbing> stubbedInvocations = getInvocationContainer().getStubbedInvocations();
        TreeSet treeSet = new TreeSet(new StubbingComparator());
        treeSet.addAll(stubbedInvocations);
        return treeSet;
    }

    @Override // org.mockito.MockingDetails
    public String printInvocations() {
        assertGoodMock();
        return new InvocationsPrinter().printInvocations(this.toInspect);
    }

    @Override // org.mockito.MockingDetails
    public MockHandler getMockHandler() {
        return mockHandler();
    }

    @Override // org.mockito.MockingDetails
    public Object getMock() {
        return this.toInspect;
    }

    private MockHandler<Object> mockHandler() {
        assertGoodMock();
        return MockUtil.getMockHandler(this.toInspect);
    }

    private void assertGoodMock() {
        if (this.toInspect == null) {
            throw new NotAMockException("Argument passed to Mockito.mockingDetails() should be a mock, but is null!");
        }
        if (!isMock()) {
            throw new NotAMockException("Argument passed to Mockito.mockingDetails() should be a mock, but is an instance of " + this.toInspect.getClass() + "!");
        }
    }
}
