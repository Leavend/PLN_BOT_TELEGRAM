package androidx.test.services.events.discovery;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.ErrorInfo;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.discovery.TestDiscoveryEvent;

/* loaded from: classes5.dex */
public class TestDiscoveryErrorEvent extends TestDiscoveryEvent {
    public final ErrorInfo error;
    public final TimeStamp timeStamp;

    public TestDiscoveryErrorEvent(ErrorInfo error, TimeStamp timeStamp) {
        this.error = (ErrorInfo) Checks.checkNotNull(error, "error cannot be null");
        this.timeStamp = (TimeStamp) Checks.checkNotNull(timeStamp, "timeStamp cannot be null");
    }

    TestDiscoveryErrorEvent(Parcel source) {
        this.error = new ErrorInfo(source);
        this.timeStamp = new TimeStamp(source);
    }

    @Override // androidx.test.services.events.discovery.TestDiscoveryEvent
    TestDiscoveryEvent.EventType instanceType() {
        return TestDiscoveryEvent.EventType.ERROR;
    }

    @Override // androidx.test.services.events.discovery.TestDiscoveryEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.error.writeToParcel(parcel, i);
        this.timeStamp.writeToParcel(parcel, i);
    }
}
