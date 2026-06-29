package id.go.bpsfasih.ui.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import id.go.bpsfasih.databinding.ActivityLoginBinding;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import id.go.bpsfasih.ui.login.LoginActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.utils.helper.ServiceHelper;
import id.go.bpsfasih.utils.services.AppServices;
import id.go.bpsfasih.web_view.ui.WebViewActivity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* compiled from: LoginActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u001f\u0010\u000f\u001a\u00020\u000e2\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011H\u0002¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\"\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00062\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u000eH\u0014J+\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00062\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0002\u0010#J\u0012\u0010$\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\u0012\u0010'\u001a\u00020\u000e2\b\u0010%\u001a\u0004\u0018\u00010&H\u0002J\b\u0010(\u001a\u00020\u000eH\u0002J\b\u0010)\u001a\u00020\u000eH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006*"}, d2 = {"Lid/go/bpsfasih/ui/login/LoginActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "binding", "Lid/go/bpsfasih/databinding/ActivityLoginBinding;", FirebaseAnalytics.Param.INDEX, "", "viewModel", "Lid/go/bpsfasih/ui/login/LoginViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/login/LoginViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/login/LoginViewModel;)V", "checkFile", "", "checkPermission", "request", "", "", "([Ljava/lang/String;)V", "initListener", "initObservables", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openChrome", "uri", "Landroid/net/Uri;", "openWebView", "permitted", "startService", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class LoginActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private ActivityLoginBinding binding;
    private int index;
    public LoginViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openChrome$lambda$3(View view) {
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew
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

    public final LoginViewModel getViewModel() {
        LoginViewModel loginViewModel = this.viewModel;
        if (loginViewModel != null) {
            return loginViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(LoginViewModel loginViewModel) {
        Intrinsics.checkNotNullParameter(loginViewModel, "<set-?>");
        this.viewModel = loginViewModel;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModel((LoginViewModel) new ViewModelProvider(this).get(LoginViewModel.class));
        getViewModel().checkToken();
        ActivityLoginBinding activityLoginBinding = (ActivityLoginBinding) DataBindingUtil.setContentView(this, R.layout.activity_login);
        this.binding = activityLoginBinding;
        if (activityLoginBinding != null) {
            activityLoginBinding.setViewModel(getViewModel());
        }
        BaseClassActivityNew.setAppBar$default(this, 0, "Login", null, null, null, 24, null);
        setStatusBarColor();
        if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV, false, 2, null)) {
            ((TextView) _$_findCachedViewById(R.id.modeLogin_tv)).setText("DEVELOPMENT");
            ((TextView) _$_findCachedViewById(R.id.modeLogin_tv)).setVisibility(0);
            ((TextView) _$_findCachedViewById(R.id.deskripsi_tv)).setText("Silahkan masukkan username dan password anda. Pastikan anda telah menggunakan VPN BPS ketika login.");
            ((ImageView) _$_findCachedViewById(R.id.icon)).setImageResource(R.drawable.icon_login_dev);
        } else if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD, false, 2, null)) {
            ((TextView) _$_findCachedViewById(R.id.modeLogin_tv)).setVisibility(8);
            ((TextView) _$_findCachedViewById(R.id.deskripsi_tv)).setText("Silakan pilih metode Login");
            ((ImageView) _$_findCachedViewById(R.id.icon)).setImageResource(R.drawable.icon_login_prod);
        }
        if (getIntent() != null) {
            LoginViewModel viewModel = getViewModel();
            Intent intent = getIntent();
            viewModel.authenticate(intent != null ? intent.getData() : null);
        }
        initListener();
        initObservables();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        hideProgressBar();
        super.onDestroy();
    }

    private final void initListener() {
        ((Button) _$_findCachedViewById(R.id.loginEksternalButton)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initListener$lambda$0(this.f$0, view);
            }
        });
        ((Button) _$_findCachedViewById(R.id.loginInternalButton)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initListener$lambda$1(this.f$0, view);
            }
        });
        ((ImageView) _$_findCachedViewById(R.id.icon)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.initListener$lambda$2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openWebView(this$0.getViewModel().urlLoginSsoEksternal());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openWebView(this$0.getViewModel().urlLoginSsoInternal());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(final LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.index + 1;
        this$0.index = i;
        if (i == 10) {
            this$0.index = 0;
            this$0.showAlertDialogKodeVerifikasiAdmin(new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initListener$3$1
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) throws Exception {
                    invoke(bool.booleanValue(), str);
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z, String accessToken) throws Exception {
                    Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                    if (z) {
                        return;
                    }
                    this.this$0.getViewModel().handleResponseSso$app_release(new LoginSsoTokenResponse(accessToken, 86272L, 18000L, "", "bearer", "", "", null), System.currentTimeMillis());
                }
            });
        }
    }

    private final void openWebView(Uri uri) {
        try {
            Network network = Network.INSTANCE;
            Context applicationContext = getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
            if (network.isOnline(applicationContext) && RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("login_webview")) {
                Intent intent = new Intent(this, (Class<?>) WebViewActivity.class);
                intent.putExtra("URL", String.valueOf(uri));
                startActivityForResult(intent, 8);
            } else {
                openChrome(uri);
            }
        } catch (Exception unused) {
            openChrome(uri);
        }
    }

    private final void openChrome(Uri uri) {
        try {
            try {
                Intent intent = new Intent();
                intent.setPackage("com.android.chrome");
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                intent.addFlags(268435456);
                startActivity(intent);
            } catch (Exception unused) {
                startActivity(new Intent("android.intent.action.VIEW", uri).addCategory("android.intent.category.BROWSABLE"));
            }
        } catch (Exception unused2) {
            BaseClassActivityNew.showAlertDialog$default(this, "Perhatian", "Anda harus mendownload Google Chrome di Play Store terlebih dahulu, dan menjadikannya sebagai default browser", null, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.openChrome$lambda$3(view);
                }
            }, null, null, false, false, 128, null);
        }
    }

    private final void initObservables() {
        MutableLiveData<Boolean> progressDialog = getViewModel().getProgressDialog();
        if (progressDialog != null) {
            progressDialog.observe(this, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.login.LoginActivity.initObservables.1
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Boolean it) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    if (it.booleanValue()) {
                        LoginActivity.this.showProgressBar();
                    } else {
                        LoginActivity.this.hideProgressBar();
                    }
                }
            });
        }
        SingleLiveEvent<Boolean> userLogin = getViewModel().getUserLogin();
        if (userLogin != null) {
            userLogin.observe(this, new AnonymousClass2());
        }
        SingleLiveEvent<Boolean> offlineLogin = getViewModel().getOfflineLogin();
        if (offlineLogin != null) {
            offlineLogin.observe(this, new AnonymousClass3());
        }
        getViewModel().getErrorLogin().observe(this, new AnonymousClass4());
    }

    /* compiled from: LoginActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.login.LoginActivity$initObservables$2, reason: invalid class name */
    static final class AnonymousClass2 implements Observer<Boolean> {
        AnonymousClass2() {
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
            onChanged(bool.booleanValue());
        }

        public final void onChanged(boolean z) {
            if (z) {
                LoginActivity.checkPermission$default(LoginActivity.this, null, 1, null);
                return;
            }
            final LoginActivity loginActivity = LoginActivity.this;
            LoginActivity loginActivity2 = loginActivity;
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initObservables$2$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.AnonymousClass2.onChanged$lambda$0(loginActivity, view);
                }
            };
            final LoginActivity loginActivity3 = LoginActivity.this;
            BaseClassActivityNew.showAlertDialog$default(loginActivity2, "Perhatian", "Fasih akan menyimpan lokasi Anda untuk keperluan fitur monitoring", null, "Setuju", onClickListener, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initObservables$2$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.AnonymousClass2.onChanged$lambda$1(loginActivity3, view);
                }
            }, false, false, 128, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$0(LoginActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ActivityLoginBinding activityLoginBinding = this$0.binding;
            Button button = activityLoginBinding != null ? activityLoginBinding.loginInternalButton : null;
            if (button != null) {
                button.setEnabled(true);
            }
            ActivityLoginBinding activityLoginBinding2 = this$0.binding;
            Button button2 = activityLoginBinding2 != null ? activityLoginBinding2.loginEksternalButton : null;
            if (button2 == null) {
                return;
            }
            button2.setEnabled(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$1(LoginActivity this$0, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.finishAffinity();
        }
    }

    /* compiled from: LoginActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", ""}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.login.LoginActivity$initObservables$3, reason: invalid class name */
    static final class AnonymousClass3 implements Observer<Boolean> {
        AnonymousClass3() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$0(View view) {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$1(View view) {
        }

        @Override // androidx.lifecycle.Observer
        public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
            onChanged(bool.booleanValue());
        }

        public final void onChanged(boolean z) {
            LoginActivity loginActivity = LoginActivity.this;
            BaseClassActivityNew.showAlertDialog$default(loginActivity, loginActivity.getResources().getString(R.string.warningTitleAlert), LoginActivity.this.getResources().getString(R.string.offlineMode), null, "Ok", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initObservables$3$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.AnonymousClass3.onChanged$lambda$0(view);
                }
            }, "Cancel", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initObservables$3$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.AnonymousClass3.onChanged$lambda$1(view);
                }
            }, false, false, 384, null);
        }
    }

    /* compiled from: LoginActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "message", ""}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.ui.login.LoginActivity$initObservables$4, reason: invalid class name */
    static final class AnonymousClass4 implements Observer<String> {
        AnonymousClass4() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onChanged$lambda$0(View view) {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            LoginActivity loginActivity = LoginActivity.this;
            BaseClassActivityNew.showAlertDialog$default(loginActivity, loginActivity.getResources().getString(R.string.warningTitleAlert), message, null, "Ok", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$initObservables$4$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LoginActivity.AnonymousClass4.onChanged$lambda$0(view);
                }
            }, null, null, false, false, 384, null);
            LoginActivity.this.hideProgressBar();
        }
    }

    static /* synthetic */ void checkPermission$default(LoginActivity loginActivity, String[] strArr, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = null;
        }
        loginActivity.checkPermission(strArr);
    }

    private final void checkPermission(String[] request) {
        if (request == null) {
            if (Build.VERSION.SDK_INT >= 33) {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VIDEO", "android.permission.CAMERA", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.POST_NOTIFICATIONS"}, 1);
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.CAMERA", "android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 1);
                return;
            }
        }
        LoginActivity loginActivity = this;
        if (!ActivityCompat.shouldShowRequestPermissionRationale(loginActivity, request[0])) {
            Intent intent = new Intent();
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", getPackageName(), null));
            startActivityForResult(intent, 6);
            return;
        }
        ActivityCompat.requestPermissions(loginActivity, request, 1);
    }

    private final void permitted() {
        startService();
        new Thread(new Runnable() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                LoginActivity.permitted$lambda$4(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void permitted$lambda$4(LoginActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkFile();
    }

    private final void startService() {
        Intent intent = new Intent(this, (Class<?>) AppServices.class);
        if (!ServiceHelper.INSTANCE.isMyServiceRunning(AppServices.class)) {
            if (Build.VERSION.SDK_INT >= 26) {
                startService(intent);
                return;
            } else {
                startService(intent);
                return;
            }
        }
        stopService(intent);
        startService(intent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r3v6, types: [T, java.lang.Object[]] */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new String[0];
            int length = permissions.length - 1;
            if (length >= 0) {
                int i = 0;
                while (true) {
                    if (grantResults[i] == -1) {
                        List mutableList = ArraysKt.toMutableList((Object[]) objectRef.element);
                        mutableList.add(permissions[i]);
                        objectRef.element = mutableList.toArray(new String[0]);
                    }
                    if (i == length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
            if (((Object[]) objectRef.element).length > 0) {
                BaseClassActivityNew.showAlertDialog$default(this, "Perhatian", "Harap menyetujui semua perizinan yang diberikanuntuk bisa melanjutkan aplikasi", null, "Izinkan", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        LoginActivity.onRequestPermissionsResult$lambda$5(this.f$0, objectRef, view);
                    }
                }, "Tutup", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.login.LoginActivity$$ExternalSyntheticLambda2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        LoginActivity.onRequestPermissionsResult$lambda$6(this.f$0, view);
                    }
                }, false, false, 384, null);
            } else {
                permitted();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void onRequestPermissionsResult$lambda$5(LoginActivity this$0, Ref.ObjectRef request, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(request, "$request");
        this$0.checkPermission((String[]) request.element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onRequestPermissionsResult$lambda$6(LoginActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.hideProgressBar();
    }

    private final void checkFile() {
        Intent intent = new Intent(this, (Class<?>) HomePageActivity.class);
        intent.setFlags(268468224);
        startActivity(intent);
        finish();
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4) {
            if (resultCode == -1) {
                checkFile();
                return;
            } else {
                finishAffinity();
                return;
            }
        }
        if (requestCode == 6) {
            checkPermission$default(this, null, 1, null);
            return;
        }
        if (requestCode == 8 && resultCode == -1 && data != null) {
            String stringExtra = data.getStringExtra("uri");
            Intrinsics.checkNotNull(stringExtra);
            if (StringsKt.contains$default((CharSequence) stringExtra, (CharSequence) "https://sso.bps.go.id/auth/realms/eksternal/broker/google/login", false, 2, (Object) null)) {
                openChrome(getViewModel().urlLoginSsoEksternal());
            } else {
                getViewModel().authenticate(Uri.parse(stringExtra));
            }
        }
    }
}
