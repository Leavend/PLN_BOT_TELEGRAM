package id.go.bpsfasih.ui.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.ui.login.LoginActivity;
import id.go.bpsfasih.ui.onboarding.OnBoardingActivity;
import id.go.bpsfasih.ui.theme.ThemeKt;
import id.go.bpsfasih.utils.Session;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SplashActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\b\u0010\n\u001a\u00020\u0004H\u0002¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/ui/splash/SplashActivity;", "Landroidx/activity/ComponentActivity;", "()V", "checkLogin", "", "navigateToLogin", "navigateToOnBoarding", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupFullscreen", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SplashActivity extends ComponentActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

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
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(223858538, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivity.onCreate.1
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
                        ComposerKt.traceEventStart(223858538, i, -1, "id.go.bpsfasih.ui.splash.SplashActivity.onCreate.<anonymous> (SplashActivity.kt:59)");
                    }
                    final SplashActivity splashActivity = SplashActivity.this;
                    ThemeKt.FasihTheme(false, ComposableLambdaKt.composableLambda(composer, 966502346, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivity.onCreate.1.1
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
                                    ComposerKt.traceEventStart(966502346, i2, -1, "id.go.bpsfasih.ui.splash.SplashActivity.onCreate.<anonymous>.<anonymous> (SplashActivity.kt:60)");
                                }
                                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                long background = MaterialTheme.INSTANCE.getColorScheme(composer2, 8).getBackground();
                                final SplashActivity splashActivity2 = splashActivity;
                                SurfaceKt.m1982SurfaceT9BRK9s(modifierFillMaxSize$default, null, background, 0L, 0.0f, 0.0f, null, ComposableLambdaKt.composableLambda(composer2, -2133201691, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivity.onCreate.1.1.1
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
                                                ComposerKt.traceEventStart(-2133201691, i3, -1, "id.go.bpsfasih.ui.splash.SplashActivity.onCreate.<anonymous>.<anonymous>.<anonymous> (SplashActivity.kt:64)");
                                            }
                                            final SplashActivity splashActivity3 = splashActivity2;
                                            SplashActivityKt.ElegantAnimatedSplashScreen(new Function0<Unit>() { // from class: id.go.bpsfasih.ui.splash.SplashActivity.onCreate.1.1.1.1
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
                                                    splashActivity3.checkLogin();
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
                                }), composer2, 12582918, 122);
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
        if (Build.VERSION.SDK_INT < 30) {
            getWindow().setFlags(1024, 1024);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkLogin() {
        String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_DEVICE_ID());
        if (sessionString == null || sessionString.length() == 0) {
            Session session = FasihApp.INSTANCE.getSession();
            String session_device_id = CommonCons.INSTANCE.getSESSION_DEVICE_ID();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            String lowerCase = string.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            session.createSessionString(session_device_id, lowerCase);
            navigateToOnBoarding();
            return;
        }
        if (FasihApp.INSTANCE.getSession().is_login()) {
            navigateToLogin();
        } else {
            navigateToOnBoarding();
        }
    }

    private final void navigateToLogin() {
        startActivity(new Intent(this, (Class<?>) LoginActivity.class));
        finish();
    }

    private final void navigateToOnBoarding() {
        startActivity(new Intent(this, (Class<?>) OnBoardingActivity.class));
        finish();
    }
}
