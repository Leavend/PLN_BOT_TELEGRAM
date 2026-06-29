package org.mockito.internal.creation.bytebuddy;

import org.mockito.mock.MockCreationSettings;
import org.mockito.plugins.MockMaker;

/* loaded from: classes3.dex */
interface ClassCreatingMockMaker extends MockMaker {
    <T> Class<? extends T> createMockType(MockCreationSettings<T> mockCreationSettings);
}
