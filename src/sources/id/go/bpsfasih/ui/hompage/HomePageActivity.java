package id.go.bpsfasih.ui.hompage;

import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import id.go.bpsfasih.BaseClassActivity;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.localserver.Server;
import id.go.bpsfasih.databinding.ActivityMainmenuBinding;
import id.go.bpsfasih.ui.bantuan.BantuanActivity;
import id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment;
import id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment;
import id.go.bpsfasih.ui.hompage.uploadfragment.UploadProgressFragment;
import id.go.bpsfasih.utils.LiveTrackingHelper;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.CrashlyticHelper;
import id.go.bpsfasih.utils.helper.FirebaseAnalitycHelper;
import id.go.bpsfasih.utils.helper.Network;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HomePageActivity.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\u0012\u0010\u0012\u001a\u00020\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\"\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\r2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0014J/\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0001\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\b\b\u0001\u0010\"\u001a\u00020#H\u0016¢\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\rH\u0014J\"\u0010&\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020\rH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lid/go/bpsfasih/ui/hompage/HomePageActivity;", "Lid/go/bpsfasih/BaseClassActivity;", "Lid/go/bpsfasih/BaseClassActivity$OnPermissionResult;", "()V", "binding", "Lid/go/bpsfasih/databinding/ActivityMainmenuBinding;", "isCheckingDestruct", "", "mOnNavigationItemSelectedListener", "Lcom/google/android/material/bottomnavigation/BottomNavigationView$OnNavigationItemSelectedListener;", "viewModel", "Lid/go/bpsfasih/ui/hompage/HomePageViewModel;", "checkAndShowTrackingDialog", "", "checkDestruct", "checkPermissionStorage", "initObserve", "initServer", "loadFragment", "fragment", "Landroidx/fragment/app/Fragment;", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "permissionResult", "requestWriteExternalStoragePermission", "permission", "setStatusBarColor", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public class HomePageActivity extends BaseClassActivity implements BaseClassActivity.OnPermissionResult {
    public static final int $stable = 8;
    private ActivityMainmenuBinding binding;
    private boolean isCheckingDestruct;
    private HomePageViewModel viewModel;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity$$ExternalSyntheticLambda0
        @Override // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
        public final boolean onNavigationItemSelected(MenuItem menuItem) {
            return HomePageActivity.mOnNavigationItemSelectedListener$lambda$0(this.f$0, menuItem);
        }
    };

    private final void checkDestruct() {
    }

    @Override // id.go.bpsfasih.BaseClassActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // id.go.bpsfasih.BaseClassActivity
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean mOnNavigationItemSelectedListener$lambda$0(HomePageActivity this$0, MenuItem item) {
        PengaturanFragment pengaturanFragmentNewInstance;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "item");
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_beranda) {
            pengaturanFragmentNewInstance = HomeFragment.INSTANCE.newInstance();
        } else if (itemId == R.id.navigation_unggah) {
            pengaturanFragmentNewInstance = UploadProgressFragment.INSTANCE.newInstance();
        } else {
            if (itemId == R.id.navigation_bantuan) {
                BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
                Context applicationContext = this$0.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
                companion.startActivity(applicationContext, null);
            } else if (itemId == R.id.navigation_pengaturan) {
                pengaturanFragmentNewInstance = PengaturanFragment.INSTANCE.newInstance();
            }
            pengaturanFragmentNewInstance = null;
        }
        if ((pengaturanFragmentNewInstance instanceof HomeFragment) || !(pengaturanFragmentNewInstance instanceof UploadProgressFragment)) {
            this$0.setStatusBarColor();
        }
        return this$0.loadFragment(pengaturanFragmentNewInstance);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setThemeNoActionBar();
        setContentView(R.layout.activity_mainmenu);
        this.isCheckingDestruct = true;
        if (Build.VERSION.SDK_INT >= 35) {
            WindowCompat.setDecorFitsSystemWindows(getWindow(), true);
        }
        checkDestruct();
        loadFragment(new HomeFragment());
        this.binding = (ActivityMainmenuBinding) DataBindingUtil.setContentView(this, R.layout.activity_mainmenu);
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        FragmentManager fragmentManager = getFragmentManager();
        Intrinsics.checkNotNullExpressionValue(fragmentManager, "fragmentManager");
        HomePageViewModel homePageViewModel = (HomePageViewModel) ViewModelProviders.of(this, new HomePageViewModelFactory(application, this, fragmentManager)).get(HomePageViewModel.class);
        this.viewModel = homePageViewModel;
        ActivityMainmenuBinding activityMainmenuBinding = this.binding;
        if (activityMainmenuBinding != null) {
            activityMainmenuBinding.setViewModel(homePageViewModel);
        }
        FasihApp.INSTANCE.getSession().setOnboardOff();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) _$_findCachedViewById(R.id.navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this.mOnNavigationItemSelectedListener);
        }
        setStatusBarColor();
        View viewFindViewById = findViewById(R.id.navigation);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.navigation)");
        BottomNavigationView bottomNavigationView2 = (BottomNavigationView) viewFindViewById;
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView2.getMenu().findItem(R.id.navigation_unggah), "navView.menu.findItem(R.id.navigation_unggah)");
        Intrinsics.checkNotNullExpressionValue(bottomNavigationView2.getMenu().findItem(R.id.navigation_pengaturan), "navView.menu.findItem(R.id.navigation_pengaturan)");
        CrashlyticHelper.INSTANCE.init();
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("firebase_analityc_set_user")) {
            FirebaseAnalitycHelper.INSTANCE.init(this);
        }
        initObserve();
        initServer();
        new Thread(new Runnable() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                HomePageActivity.onCreate$lambda$2(this.f$0);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2(final HomePageActivity this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Thread.sleep(2000L);
        this$0.runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                HomePageActivity.onCreate$lambda$2$lambda$1(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$2$lambda$1(HomePageActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ((ShimmerFrameLayout) this$0._$_findCachedViewById(R.id.shimmer_view_container_homepage)).setVisibility(8);
        ((ShimmerFrameLayout) this$0._$_findCachedViewById(R.id.shimmer_view_container_homepage)).stopShimmerAnimation();
    }

    private final void initServer() {
        new Server.HttpServerThread().start();
    }

    private final void setStatusBarColor() {
        View decorView;
        Window window = getWindow();
        if (window == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        decorView.setBackgroundResource(R.color.colorPrimary);
    }

    private final void initObserve() {
        SingleLiveEvent<Boolean> requesting;
        HomePageViewModel homePageViewModel = this.viewModel;
        if (homePageViewModel == null || (requesting = homePageViewModel.getRequesting()) == null) {
            return;
        }
        requesting.observe(this, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity.initObserve.1
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                onChanged(bool.booleanValue());
            }

            public final void onChanged(boolean z) {
                if (z) {
                    HomePageActivity.this.showProgressBar();
                } else {
                    HomePageActivity.this.hideProgressBar();
                }
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        ((ShimmerFrameLayout) _$_findCachedViewById(R.id.shimmer_view_container_homepage)).startShimmerAnimation();
        RemoteConfigHelper.INSTANCE.getAppConfigRemoteConfig(this, true);
        Network network = Network.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        if (network.isOnline(applicationContext)) {
            RemoteConfigHelper.INSTANCE.setUrlsConfig();
        } else {
            Toast.makeText(getApplicationContext(), "Upss...Tidak ada koneksi internet", 0).show();
        }
        checkAndShowTrackingDialog();
    }

    private final void checkAndShowTrackingDialog() {
        try {
            if (!RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow(CommonCons.REMOTE_CONFIG_FEATURE_TRACKING_LOCATION)) {
                LiveTrackingHelper.INSTANCE.stopTrackingPermanent(this);
                return;
            }
            LiveTrackingHelper.INSTANCE.initialize(this);
            String sessionString = FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_ID());
            String str = sessionString;
            if (str == null || str.length() == 0) {
                return;
            }
            LiveTrackingHelper.INSTANCE.checkAndShowDialog(this, sessionString);
            LiveTrackingHelper.INSTANCE.resumeTracking(this);
        } catch (Exception e) {
            Log.e("HomePageActivity", "Error showing tracking dialog: " + e.getMessage());
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != 0) {
            checkPermissionStorage();
        }
    }

    private final void requestWriteExternalStoragePermission(final String permission) {
        HomePageActivity homePageActivity = this;
        if (!ActivityCompat.shouldShowRequestPermissionRationale(homePageActivity, permission)) {
            runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    HomePageActivity.requestWriteExternalStoragePermission$lambda$5(this.f$0, permission);
                }
            });
        } else {
            ActivityCompat.requestPermissions(homePageActivity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestWriteExternalStoragePermission$lambda$5(final HomePageActivity this$0, final String permission) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permission, "$permission");
        this$0.showAlertDialog("Informasi dan Permintaan", "Aplikasi ini membutuhkan izin anda untuk mengakses media tambahan", null, "Ok", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.HomePageActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomePageActivity.requestWriteExternalStoragePermission$lambda$5$lambda$4(permission, this$0, view);
            }
        }, null, null, true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requestWriteExternalStoragePermission$lambda$5$lambda$4(String permission, HomePageActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(permission, "$permission");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (StringsKt.equals(permission, "android.permission.WRITE_EXTERNAL_STORAGE", true)) {
            ActivityCompat.requestPermissions(this$0, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1);
        }
    }

    private final void checkPermissionStorage() {
        if (ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            requestWriteExternalStoragePermission("android.permission.WRITE_EXTERNAL_STORAGE");
        }
    }

    private final boolean loadFragment(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        return true;
    }

    @Override // id.go.bpsfasih.BaseClassActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Bluetooth tidak aktif", 0).show();
    }

    @Override // id.go.bpsfasih.BaseClassActivity.OnPermissionResult
    public void permissionResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "Bluetooth tidak aktif", 0).show();
    }
}
