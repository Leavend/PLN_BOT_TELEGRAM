package org.mockito.internal.creation.bytebuddy;

import org.mockito.Incubating;
import org.mockito.invocation.MockHandler;
import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

/* loaded from: classes3.dex */
public class ByteBuddyMockMaker implements ClassCreatingMockMaker {
    private ClassCreatingMockMaker defaultByteBuddyMockMaker = new SubclassByteBuddyMockMaker();

    @Override // org.mockito.plugins.MockMaker
    public <T> T createMock(MockCreationSettings<T> mockCreationSettings, MockHandler mockHandler) {
        return (T) this.defaultByteBuddyMockMaker.createMock(mockCreationSettings, mockHandler);
    }

    @Override // org.mockito.internal.creation.bytebuddy.ClassCreatingMockMaker
    public <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings) {
        return this.defaultByteBuddyMockMaker.createMockType(mockCreationSettings);
    }

    @Override // org.mockito.plugins.MockMaker
    public MockHandler getHandler(Object obj) {
        return this.defaultByteBuddyMockMaker.getHandler(obj);
    }

    @Override // org.mockito.plugins.MockMaker
    public void resetMock(Object obj, MockHandler mockHandler, MockCreationSettings mockCreationSettings) {
        this.defaultByteBuddyMockMaker.resetMock(obj, mockHandler, mockCreationSettings);
    }

    @Override // org.mockito.plugins.MockMaker
    @Incubating
    public MockMaker.TypeMockability isTypeMockable(Class<?> cls) {
        return this.defaultByteBuddyMockMaker.isTypeMockable(cls);
    }
}
