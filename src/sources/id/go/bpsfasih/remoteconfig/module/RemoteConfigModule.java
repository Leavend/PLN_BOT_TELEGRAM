package id.go.bpsfasih.remoteconfig.module;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.remoteconfig.RemoteConfig;
import id.go.bpsfasih.remoteconfig.internal.RemoteConfigImpl;
import kotlin.Metadata;

/* compiled from: RemoteConfigModule.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/remoteconfig/module/RemoteConfigModule;", "", "()V", "get", "Lid/go/bpsfasih/remoteconfig/RemoteConfig;", "remote-config_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RemoteConfigModule {
    public static final RemoteConfigModule INSTANCE = new RemoteConfigModule();

    private RemoteConfigModule() {
    }

    public final RemoteConfig get() {
        return new RemoteConfigImpl();
    }
}
