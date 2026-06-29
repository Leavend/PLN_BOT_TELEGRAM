package androidx.test.core.app;

import androidx.test.internal.platform.ServiceLoaderWrapper;
import androidx.test.internal.platform.os.ControlledLooper;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes5.dex */
public final /* synthetic */ class ActivityScenario$$ExternalSyntheticLambda1 implements ServiceLoaderWrapper.Factory {
    public static final /* synthetic */ ActivityScenario$$ExternalSyntheticLambda1 INSTANCE = new ActivityScenario$$ExternalSyntheticLambda1();

    private /* synthetic */ ActivityScenario$$ExternalSyntheticLambda1() {
    }

    @Override // androidx.test.internal.platform.ServiceLoaderWrapper.Factory
    public final Object create() {
        return ControlledLooper.NO_OP_CONTROLLED_LOOPER;
    }
}
