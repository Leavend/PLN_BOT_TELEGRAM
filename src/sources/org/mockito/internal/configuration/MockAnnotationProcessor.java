package org.mockito.internal.configuration;

import java.lang.reflect.Field;
import org.mockito.Mock;
import org.mockito.MockSettings;
import org.mockito.Mockito;

/* loaded from: classes3.dex */
public class MockAnnotationProcessor implements FieldAnnotationProcessor<Mock> {
    @Override // org.mockito.internal.configuration.FieldAnnotationProcessor
    public Object process(Mock mock, Field field) {
        MockSettings mockSettingsWithSettings = Mockito.withSettings();
        if (mock.extraInterfaces().length > 0) {
            mockSettingsWithSettings.extraInterfaces(mock.extraInterfaces());
        }
        if ("".equals(mock.name())) {
            mockSettingsWithSettings.name(field.getName());
        } else {
            mockSettingsWithSettings.name(mock.name());
        }
        if (mock.serializable()) {
            mockSettingsWithSettings.serializable();
        }
        if (mock.stubOnly()) {
            mockSettingsWithSettings.stubOnly();
        }
        mockSettingsWithSettings.defaultAnswer(mock.answer());
        return Mockito.mock(field.getType(), mockSettingsWithSettings);
    }
}
