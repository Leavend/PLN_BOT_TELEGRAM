package org.mockito.internal.creation.bytebuddy;

/* loaded from: classes3.dex */
public interface BytecodeGenerator {
    <T> Class<? extends T> mockClass(MockFeatures<T> mockFeatures);
}
