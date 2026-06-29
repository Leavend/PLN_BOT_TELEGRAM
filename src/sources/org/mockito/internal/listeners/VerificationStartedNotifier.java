package org.mockito.internal.listeners;

import java.util.Iterator;
import java.util.List;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.internal.exceptions.Reporter;
import org.mockito.internal.matchers.text.ValuePrinter;
import org.mockito.listeners.VerificationStartedEvent;
import org.mockito.listeners.VerificationStartedListener;
import org.mockito.mock.MockCreationSettings;

/* loaded from: classes3.dex */
public class VerificationStartedNotifier {
    public static Object notifyVerificationStarted(List<VerificationStartedListener> list, MockingDetails mockingDetails) {
        if (list.isEmpty()) {
            return mockingDetails.getMock();
        }
        Event event = new Event(mockingDetails);
        Iterator<VerificationStartedListener> it = list.iterator();
        while (it.hasNext()) {
            it.next().onVerificationStarted(event);
        }
        return event.getMock();
    }

    static class Event implements VerificationStartedEvent {
        private Object mock;
        private final MockingDetails originalMockingDetails;

        public Event(MockingDetails mockingDetails) {
            this.originalMockingDetails = mockingDetails;
            this.mock = mockingDetails.getMock();
        }

        @Override // org.mockito.listeners.VerificationStartedEvent
        public void setMock(Object obj) {
            if (obj == null) {
                throw Reporter.methodDoesNotAcceptParameter("VerificationStartedEvent.setMock", "null parameter.");
            }
            if (!Mockito.mockingDetails(obj).isMock()) {
                throw Reporter.methodDoesNotAcceptParameter("VerificationStartedEvent.setMock", "parameter which is not a Mockito mock.\n  Received parameter: " + ValuePrinter.print(obj) + ".\n ");
            }
            VerificationStartedNotifier.assertCompatibleTypes(obj, this.originalMockingDetails.getMockCreationSettings());
            this.mock = obj;
        }

        @Override // org.mockito.listeners.VerificationStartedEvent
        public Object getMock() {
            return this.mock;
        }
    }

    static void assertCompatibleTypes(Object obj, MockCreationSettings mockCreationSettings) {
        Class typeToMock = mockCreationSettings.getTypeToMock();
        if (!typeToMock.isInstance(obj)) {
            throw Reporter.methodDoesNotAcceptParameter("VerificationStartedEvent.setMock", "parameter which is not the same type as the original mock.\n  Required type: " + typeToMock.getName() + "\n  Received parameter: " + ValuePrinter.print(obj) + ".\n ");
        }
        for (Class<?> cls : mockCreationSettings.getExtraInterfaces()) {
            if (!cls.isInstance(obj)) {
                throw Reporter.methodDoesNotAcceptParameter("VerificationStartedEvent.setMock", "parameter which does not implement all extra interfaces of the original mock.\n  Required type: " + typeToMock.getName() + "\n  Required extra interface: " + cls.getName() + "\n  Received parameter: " + ValuePrinter.print(obj) + ".\n ");
            }
        }
    }
}
