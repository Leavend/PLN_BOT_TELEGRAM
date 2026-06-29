package id.go.bpsfasih.presentation.locationtracking;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LiveTrackingActivity.kt */
@Metadata(d1 = {"\u00007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u000eH\u0014J\b\u0010\u0012\u001a\u00020\u000eH\u0014J\b\u0010\u0013\u001a\u00020\u000eH\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002R\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005R(\u0010\u0006\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\t \n*\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingActivity;", "Landroidx/activity/ComponentActivity;", "()V", "gpsStatusReceiver", "id/go/bpsfasih/presentation/locationtracking/LiveTrackingActivity$gpsStatusReceiver$1", "Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingActivity$gpsStatusReceiver$1;", "locationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "kotlin.jvm.PlatformType", "viewModel", "Lid/go/bpsfasih/presentation/locationtracking/LiveTrackingViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStart", "onStop", "openAppSettings", "openLocationSettings", "requestLocationPermissionIfNeeded", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LiveTrackingActivity extends ComponentActivity {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final LiveTrackingActivity$gpsStatusReceiver$1 gpsStatusReceiver;
    private final ActivityResultLauncher<String[]> locationPermissionLauncher;
    private LiveTrackingViewModel viewModel;

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

    /* JADX WARN: Type inference failed for: r0v5, types: [id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity$gpsStatusReceiver$1] */
    public LiveTrackingActivity() {
        ActivityResultLauncher<String[]> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity$locationPermissionLauncher$1
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Map<String, Boolean> map) {
                boolean z = Intrinsics.areEqual((Object) map.get("android.permission.ACCESS_FINE_LOCATION"), (Object) true) || Intrinsics.areEqual((Object) map.get("android.permission.ACCESS_COARSE_LOCATION"), (Object) true);
                LiveTrackingViewModel liveTrackingViewModel = this.this$0.viewModel;
                LiveTrackingViewModel liveTrackingViewModel2 = null;
                if (liveTrackingViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveTrackingViewModel = null;
                }
                liveTrackingViewModel.refreshTrackingStatusFromDevice();
                if (z) {
                    LiveTrackingViewModel liveTrackingViewModel3 = this.this$0.viewModel;
                    if (liveTrackingViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    } else {
                        liveTrackingViewModel2 = liveTrackingViewModel3;
                    }
                    liveTrackingViewModel2.setTrackingEnabled(true);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…led(true)\n        }\n    }");
        this.locationPermissionLauncher = activityResultLauncherRegisterForActivityResult;
        this.gpsStatusReceiver = new BroadcastReceiver() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity$gpsStatusReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LiveTrackingViewModel liveTrackingViewModel = this.this$0.viewModel;
                if (liveTrackingViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    liveTrackingViewModel = null;
                }
                liveTrackingViewModel.refreshTrackingStatusFromDevice();
            }
        };
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = (LiveTrackingViewModel) new ViewModelProvider(this).get(LiveTrackingViewModel.class);
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(-2104311797, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1
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
                        ComposerKt.traceEventStart(-2104311797, i, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.<anonymous> (LiveTrackingActivity.kt:77)");
                    }
                    final LiveTrackingActivity liveTrackingActivity = LiveTrackingActivity.this;
                    MaterialThemeKt.MaterialTheme(null, null, null, ComposableLambdaKt.composableLambda(composer, 651612895, true, new Function2<Composer, Integer, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1.1
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
                                    ComposerKt.traceEventStart(651612895, i2, -1, "id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.<anonymous>.<anonymous> (LiveTrackingActivity.kt:78)");
                                }
                                LiveTrackingViewModel liveTrackingViewModel = liveTrackingActivity.viewModel;
                                if (liveTrackingViewModel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    liveTrackingViewModel = null;
                                }
                                final LiveTrackingActivity liveTrackingActivity2 = liveTrackingActivity;
                                Function0<Unit> function0 = new Function0<Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1.1.1
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
                                        liveTrackingActivity2.finish();
                                    }
                                };
                                final LiveTrackingActivity liveTrackingActivity3 = liveTrackingActivity;
                                Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1.1.2
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
                                            liveTrackingActivity3.requestLocationPermissionIfNeeded();
                                            return;
                                        }
                                        LiveTrackingViewModel liveTrackingViewModel2 = liveTrackingActivity3.viewModel;
                                        if (liveTrackingViewModel2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                            liveTrackingViewModel2 = null;
                                        }
                                        liveTrackingViewModel2.setTrackingEnabled(false);
                                    }
                                };
                                final LiveTrackingActivity liveTrackingActivity4 = liveTrackingActivity;
                                Function0<Unit> function02 = new Function0<Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1.1.3
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
                                        liveTrackingActivity4.openLocationSettings();
                                    }
                                };
                                final LiveTrackingActivity liveTrackingActivity5 = liveTrackingActivity;
                                LiveTrackingActivityKt.LiveTrackingStatusScreen(liveTrackingViewModel, function0, function1, function02, new Function0<Unit>() { // from class: id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity.onCreate.1.1.4
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
                                        liveTrackingActivity5.openAppSettings();
                                    }
                                }, composer2, 8);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }), composer, 3072, 7);
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

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter("android.location.PROVIDERS_CHANGED");
        if (Build.VERSION.SDK_INT >= 33) {
            registerReceiver(this.gpsStatusReceiver, intentFilter, 4);
        } else {
            ContextCompat.registerReceiver(this, this.gpsStatusReceiver, intentFilter, 4);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        LiveTrackingViewModel liveTrackingViewModel = this.viewModel;
        if (liveTrackingViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            liveTrackingViewModel = null;
        }
        liveTrackingViewModel.refreshTrackingStatusFromDevice();
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            Result.Companion companion = Result.INSTANCE;
            LiveTrackingActivity liveTrackingActivity = this;
            unregisterReceiver(this.gpsStatusReceiver);
            Result.m6852constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m6852constructorimpl(ResultKt.createFailure(th));
        }
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestLocationPermissionIfNeeded() {
        LiveTrackingActivity liveTrackingActivity = this;
        boolean z = ContextCompat.checkSelfPermission(liveTrackingActivity, "android.permission.ACCESS_FINE_LOCATION") == 0;
        boolean z2 = ContextCompat.checkSelfPermission(liveTrackingActivity, "android.permission.ACCESS_COARSE_LOCATION") == 0;
        if (z || z2) {
            LiveTrackingViewModel liveTrackingViewModel = this.viewModel;
            if (liveTrackingViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                liveTrackingViewModel = null;
            }
            liveTrackingViewModel.setTrackingEnabled(true);
            return;
        }
        this.locationPermissionLauncher.launch(new String[]{"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openLocationSettings() {
        startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void openAppSettings() {
        startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", getPackageName(), null)));
    }
}
