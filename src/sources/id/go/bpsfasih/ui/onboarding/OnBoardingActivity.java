package id.go.bpsfasih.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.ui.login.LoginActivity;
import id.go.bpsfasih.ui.theme.ThemeKt;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OnBoardingActivity.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\t\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0015J\b\u0010\f\u001a\u00020\u0007H\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002J\b\u0010\u000e\u001a\u00020\u0007H\u0002J\b\u0010\u000f\u001a\u00020\u0007H\u0002J\b\u0010\u0010\u001a\u00020\u0007H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/onboarding/OnBoardingActivity;", "Landroidx/activity/ComponentActivity;", "()V", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "initializeApp", "", "navigateToLogin", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openPrivacyPolicy", "setupDevMode", "setupFullscreen", "setupPermissions", "setupProdMode", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class OnBoardingActivity extends ComponentActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ActivityResultLauncher<String> requestPermissionLauncher;

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        if (viewFindViewById == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFullscreen();
        setupPermissions();
        initializeApp();
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(816152758, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 11) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(816152758, i, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.<anonymous> (OnBoardingActivity.kt:81)");
                    }
                    final OnBoardingActivity onBoardingActivity = OnBoardingActivity.this;
                    ThemeKt.FasihTheme(false, ComposableLambdaKt.composableLambda(composer, 252168470, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i2) {
                            if ((i2 & 11) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(252168470, i2, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.<anonymous>.<anonymous> (OnBoardingActivity.kt:82)");
                                }
                                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                long jM3451getWhite0d7_KjU = Color.INSTANCE.m3451getWhite0d7_KjU();
                                final OnBoardingActivity onBoardingActivity2 = onBoardingActivity;
                                SurfaceKt.m1982SurfaceT9BRK9s(modifierFillMaxSize$default, null, jM3451getWhite0d7_KjU, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda(composer2, -505922127, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.1.1.1
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                        invoke(composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer composer3, int i3) {
                                        if ((i3 & 11) != 2 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-505922127, i3, -1, "id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.<anonymous>.<anonymous>.<anonymous> (OnBoardingActivity.kt:86)");
                                            }
                                            final OnBoardingActivity onBoardingActivity3 = onBoardingActivity2;
                                            Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.1.1.1.1
                                                {
                                                    super(1);
                                                }

                                                @Override // kotlin.jvm.functions.Function1
                                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                                    invoke(bool.booleanValue());
                                                    return Unit.INSTANCE;
                                                }

                                                public final void invoke(boolean z) {
                                                    if (z) {
                                                        onBoardingActivity3.setupDevMode();
                                                    } else {
                                                        onBoardingActivity3.setupProdMode();
                                                    }
                                                    onBoardingActivity3.navigateToLogin();
                                                }
                                            };
                                            final OnBoardingActivity onBoardingActivity4 = onBoardingActivity2;
                                            OnBoardingActivityKt.CleanOnBoardingScreen(function1, new Function0<Unit>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.onCreate.1.1.1.2
                                                {
                                                    super(0);
                                                }

                                                @Override // kotlin.jvm.functions.Function0
                                                public /* bridge */ /* synthetic */ Unit invoke() {
                                                    invoke2();
                                                    return Unit.INSTANCE;
                                                }

                                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                public final void invoke2() {
                                                    onBoardingActivity4.openPrivacyPolicy();
                                                }
                                            }, composer3, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer3.skipToGroupEnd();
                                    }
                                }), composer2, 12583302, 122);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }), composer, 48, 1);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 1, null);
    }

    private final void setupFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
        windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.systemBars());
        windowInsetsControllerCompat.setSystemBarsBehavior(2);
    }

    private final void setupPermissions() {
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() { // from class: id.go.bpsfasih.ui.onboarding.OnBoardingActivity.setupPermissions.1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Boolean bool) {
                if (bool.booleanValue()) {
                    return;
                }
                Toast.makeText(OnBoardingActivity.this, R.string.onboarding_permission_notification_message, 1).show();
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "private fun setupPermiss…        }\n        }\n    }");
        this.requestPermissionLauncher = activityResultLauncherRegisterForActivityResult;
        if (Build.VERSION.SDK_INT < 33 || ContextCompat.checkSelfPermission(this, "android.permission.POST_NOTIFICATIONS") == 0) {
            return;
        }
        ActivityResultLauncher<String> activityResultLauncher = this.requestPermissionLauncher;
        if (activityResultLauncher == null) {
            Intrinsics.throwUninitializedPropertyAccessException("requestPermissionLauncher");
            activityResultLauncher = null;
        }
        activityResultLauncher.launch("android.permission.POST_NOTIFICATIONS");
    }

    private final void initializeApp() {
        Network network = Network.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        if (network.isOnline(applicationContext)) {
            RemoteConfigHelper.INSTANCE.setUrlsConfig();
        } else {
            Toast.makeText(getApplicationContext(), "Upss...Tidak ada koneksi internet", 0).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupProdMode() {
        FasihApp.INSTANCE.getSession().setMode(CommonCons.MODE_PROD);
        FasihApp.INSTANCE.getSession().setUrl(Config.BASE_URL_DEFAULT);
        RetrofitClient.INSTANCE.setMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupDevMode() {
        FasihApp.INSTANCE.getSession().setMode(CommonCons.MODE_DEV);
        FasihApp.INSTANCE.getSession().setUrl(Config.BASE_URL_DEFAULT_DEVELOPMENT);
        RetrofitClient.INSTANCE.setMode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void navigateToLogin() {
        startActivity(new Intent(getApplicationContext(), (Class<?>) LoginActivity.class));
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openPrivacyPolicy() {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(FasihApp.INSTANCE.getSession().getUrlRemoteConfig(CommonCons.URL_REMOTE_CONFIG_KEBIJAKAN_PRIVASI))));
    }
}
