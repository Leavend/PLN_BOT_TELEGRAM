package id.go.bpsfasih.remoteconfig.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import id.go.bpsfasih.remoteconfig.RemoteConfig;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: RemoteConfigImpl.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lid/go/bpsfasih/remoteconfig/internal/RemoteConfigImpl;", "Lid/go/bpsfasih/remoteconfig/RemoteConfig;", "()V", "firebaseRemoteConfig", "Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "getFirebaseRemoteConfig", "()Lcom/google/firebase/remoteconfig/FirebaseRemoteConfig;", "firebaseRemoteConfig$delegate", "Lkotlin/Lazy;", RemoteConfigComponent.FETCH_FILE_NAME, "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lid/go/bpsfasih/remoteconfig/RemoteConfig$Listener;", "getBoolean", "", DatabaseFileArchive.COLUMN_KEY, "", "defaultValue", "getString", "Companion", "remote-config_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RemoteConfigImpl implements RemoteConfig {
    private static final long ONE_MINUTES_EXPIRATION = TimeUnit.MINUTES.toSeconds(1);

    /* renamed from: firebaseRemoteConfig$delegate, reason: from kotlin metadata */
    private final Lazy firebaseRemoteConfig = LazyKt.lazy(new Function0<FirebaseRemoteConfig>() { // from class: id.go.bpsfasih.remoteconfig.internal.RemoteConfigImpl$firebaseRemoteConfig$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FirebaseRemoteConfig invoke() {
            return FirebaseRemoteConfig.getInstance();
        }
    });

    private final FirebaseRemoteConfig getFirebaseRemoteConfig() {
        return (FirebaseRemoteConfig) this.firebaseRemoteConfig.getValue();
    }

    @Override // id.go.bpsfasih.remoteconfig.RemoteConfig
    public boolean getBoolean(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getBoolean(key, false);
    }

    @Override // id.go.bpsfasih.remoteconfig.RemoteConfig
    public boolean getBoolean(String key, boolean defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        String string = getFirebaseRemoteConfig().getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "firebaseRemoteConfig.getString(key)");
        return string.length() > 0 ? Boolean.parseBoolean(string) : defaultValue;
    }

    @Override // id.go.bpsfasih.remoteconfig.RemoteConfig
    public String getString(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return getString(key, "");
    }

    @Override // id.go.bpsfasih.remoteconfig.RemoteConfig
    public String getString(String key, String defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
        String string = getFirebaseRemoteConfig().getString(key);
        Intrinsics.checkNotNullExpressionValue(string, "firebaseRemoteConfig.getString(key)");
        return string.length() > 0 ? string : defaultValue;
    }

    @Override // id.go.bpsfasih.remoteconfig.RemoteConfig
    public void fetch(final RemoteConfig.Listener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettingsBuild = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(ONE_MINUTES_EXPIRATION).build();
        Intrinsics.checkNotNullExpressionValue(firebaseRemoteConfigSettingsBuild, "Builder()\n            .s…ION)\n            .build()");
        getFirebaseRemoteConfig().setConfigSettingsAsync(firebaseRemoteConfigSettingsBuild);
        getFirebaseRemoteConfig().fetchAndActivate().addOnCompleteListener(new OnCompleteListener() { // from class: id.go.bpsfasih.remoteconfig.internal.RemoteConfigImpl$$ExternalSyntheticLambda0
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                RemoteConfigImpl.fetch$lambda$0(listener, this, task);
            }
        }).addOnFailureListener(new OnFailureListener() { // from class: id.go.bpsfasih.remoteconfig.internal.RemoteConfigImpl$$ExternalSyntheticLambda1
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                RemoteConfigImpl.fetch$lambda$1(listener, exc);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetch$lambda$0(RemoteConfig.Listener listener, RemoteConfigImpl this$0, Task it) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onCompleted(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void fetch$lambda$1(RemoteConfig.Listener listener, Exception it) {
        Intrinsics.checkNotNullParameter(listener, "$listener");
        Intrinsics.checkNotNullParameter(it, "it");
        listener.onError(it);
    }
}
