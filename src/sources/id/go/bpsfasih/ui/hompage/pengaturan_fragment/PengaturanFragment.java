package id.go.bpsfasih.ui.hompage.pengaturan_fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import id.go.bpsfasih.BaseClassActivity;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.CustomProgressBar;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.FragmentPengaturanBinding;
import id.go.bpsfasih.presentation.locationtracking.LiveTrackingActivity;
import id.go.bpsfasih.ui.backupRestore.BackupRestoreActivity;
import id.go.bpsfasih.ui.bantuan.BantuanActivity;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import id.go.bpsfasih.ui.onboarding.OnBoardingActivity;
import id.go.bpsfasih.ui.sistem.SistemActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.CrashlyticHelper;
import id.go.bpsfasih.utils.helper.FirebaseAnalitycHelper;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import id.go.bpsfasih.web_view.ui.WebViewActivity;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: PengaturanFragment.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\u0006\u0010\u000e\u001a\u00020\nJ&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0017\u001a\u00020\nH\u0016J\u001a\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u001a\u001a\u00020\nH\u0002J\u0006\u0010\u001b\u001a\u00020\nJ\b\u0010\u001c\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lid/go/bpsfasih/ui/hompage/pengaturan_fragment/PengaturanFragment;", "Landroidx/fragment/app/Fragment;", "()V", "binding", "Lid/go/bpsfasih/databinding/FragmentPengaturanBinding;", "customProgressBar", "Lid/go/bpsfasih/CustomProgressBar;", "viewModel", "Lid/go/bpsfasih/ui/hompage/pengaturan_fragment/PengaturanFragmentViewModel;", "hideProgressBar", "", "initObservables", "initView", "logout", "logoutKeycloak", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", MemoryUtil.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onViewCreated", "view", "showDialogLogout", "showProgressBar", "updateLiveTrackingVisibility", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class PengaturanFragment extends Fragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private FragmentPengaturanBinding binding;
    private CustomProgressBar customProgressBar;
    private PengaturanFragmentViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @JvmStatic
    public static final PengaturanFragment newInstance() {
        return INSTANCE.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    public View _$_findCachedViewById(int i) {
        View viewFindViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View view2 = getView();
        if (view2 == null || (viewFindViewById = view2.findViewById(i)) == null) {
            return null;
        }
        map.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.fragment_pengaturan, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(inflater, R.layo…aturan, container, false)");
        this.binding = (FragmentPengaturanBinding) viewDataBindingInflate;
        this.viewModel = (PengaturanFragmentViewModel) ViewModelProviders.of(this).get(PengaturanFragmentViewModel.class);
        FragmentPengaturanBinding fragmentPengaturanBinding = this.binding;
        FragmentPengaturanBinding fragmentPengaturanBinding2 = null;
        if (fragmentPengaturanBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPengaturanBinding = null;
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel = this.viewModel;
        if (pengaturanFragmentViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel = null;
        }
        fragmentPengaturanBinding.setViewModel(pengaturanFragmentViewModel);
        FragmentPengaturanBinding fragmentPengaturanBinding3 = this.binding;
        if (fragmentPengaturanBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPengaturanBinding2 = fragmentPengaturanBinding3;
        }
        return fragmentPengaturanBinding2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initObservables();
        initView();
    }

    private final void initView() {
        updateLiveTrackingVisibility();
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_activity);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((TextView) bottomSheetDialog.findViewById(R.id.halaman_tv)).setText("Pengaturan");
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_formgear_tv)).setText(FormEngineHelper.INSTANCE.getFormEngineVersion(CommonCons.FORMGEAR_ID_DUMMY));
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_aplikasi_tv)).setText(BuildConfig.VERSION_NAME);
        if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV, false, 2, null)) {
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PengaturanFragment.initView$lambda$0(bottomSheetDialog, view);
                }
            });
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Development");
        } else if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD, false, 2, null)) {
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PengaturanFragment.initView$lambda$1(bottomSheetDialog, view);
                }
            });
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Production");
        }
        ((Button) bottomSheetDialog.findViewById(R.id.laporkan_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PengaturanFragment.initView$lambda$2(this.f$0, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PengaturanFragment.initView$lambda$3(bottomSheetDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$0(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$1(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(PengaturanFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        companion.startActivity(contextRequireContext, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private final void updateLiveTrackingVisibility() {
        boolean featuresRemoteConfigIsShow = RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow(CommonCons.REMOTE_CONFIG_FEATURE_TRACKING_LOCATION);
        FragmentPengaturanBinding fragmentPengaturanBinding = this.binding;
        if (fragmentPengaturanBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPengaturanBinding = null;
        }
        fragmentPengaturanBinding.cv8.setVisibility(featuresRemoteConfigIsShow ? 0 : 8);
    }

    private final void initObservables() {
        PengaturanFragmentViewModel pengaturanFragmentViewModel = this.viewModel;
        PengaturanFragmentViewModel pengaturanFragmentViewModel2 = null;
        if (pengaturanFragmentViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel = null;
        }
        SingleLiveEvent<Boolean> logoutIsClicked = pengaturanFragmentViewModel.getLogoutIsClicked();
        if (logoutIsClicked != null) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            logoutIsClicked.observe(viewLifecycleOwner, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.1
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        PengaturanFragment.this.showDialogLogout();
                    }
                }
            });
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel3 = this.viewModel;
        if (pengaturanFragmentViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel3 = null;
        }
        SingleLiveEvent<Boolean> kebijakanPrivasiIsClicked = pengaturanFragmentViewModel3.getKebijakanPrivasiIsClicked();
        if (kebijakanPrivasiIsClicked != null) {
            LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "viewLifecycleOwner");
            kebijakanPrivasiIsClicked.observe(viewLifecycleOwner2, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.2
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        String urlRemoteConfig = FasihApp.INSTANCE.getSession().getUrlRemoteConfig(CommonCons.URL_REMOTE_CONFIG_KEBIJAKAN_PRIVASI);
                        String str = urlRemoteConfig;
                        if (str == null || str.length() == 0) {
                            Toast.makeText(PengaturanFragment.this.getActivity(), "Alamat URL tidak ditemukan", 1).show();
                            return;
                        }
                        Intent intent = new Intent(PengaturanFragment.this.getActivity(), (Class<?>) WebViewActivity.class);
                        intent.putExtra("URL", urlRemoteConfig);
                        PengaturanFragment.this.startActivity(intent);
                    }
                }
            });
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel4 = this.viewModel;
        if (pengaturanFragmentViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel4 = null;
        }
        SingleLiveEvent<Boolean> sistemIsClicked = pengaturanFragmentViewModel4.getSistemIsClicked();
        if (sistemIsClicked != null) {
            LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "viewLifecycleOwner");
            sistemIsClicked.observe(viewLifecycleOwner3, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.3
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        PengaturanFragment.this.startActivity(new Intent(PengaturanFragment.this.getActivity(), (Class<?>) SistemActivity.class));
                    }
                }
            });
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel5 = this.viewModel;
        if (pengaturanFragmentViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel5 = null;
        }
        SingleLiveEvent<Boolean> backupRestoreIsClicked = pengaturanFragmentViewModel5.getBackupRestoreIsClicked();
        if (backupRestoreIsClicked != null) {
            LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "viewLifecycleOwner");
            backupRestoreIsClicked.observe(viewLifecycleOwner4, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.4
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        PengaturanFragment.this.startActivity(new Intent(PengaturanFragment.this.getActivity(), (Class<?>) BackupRestoreActivity.class));
                    }
                }
            });
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel6 = this.viewModel;
        if (pengaturanFragmentViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            pengaturanFragmentViewModel6 = null;
        }
        SingleLiveEvent<Boolean> faqIsClicked = pengaturanFragmentViewModel6.getFaqIsClicked();
        if (faqIsClicked != null) {
            LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner5, "viewLifecycleOwner");
            faqIsClicked.observe(viewLifecycleOwner5, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.5
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    String urlRemoteConfig = FasihApp.INSTANCE.getSession().getUrlRemoteConfig(CommonCons.URL_REMOTE_CONFIG_CHANGELOG_APPLICATION);
                    String str = urlRemoteConfig;
                    if (str == null || str.length() == 0) {
                        Toast.makeText(PengaturanFragment.this.getContext(), "Alamat URL tidak ditemukan", 1).show();
                        return;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(urlRemoteConfig));
                    PengaturanFragment.this.startActivity(intent);
                }
            });
        }
        PengaturanFragmentViewModel pengaturanFragmentViewModel7 = this.viewModel;
        if (pengaturanFragmentViewModel7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            pengaturanFragmentViewModel2 = pengaturanFragmentViewModel7;
        }
        SingleLiveEvent<Boolean> liveTrackingIsClicked = pengaturanFragmentViewModel2.getLiveTrackingIsClicked();
        if (liveTrackingIsClicked != null) {
            LifecycleOwner viewLifecycleOwner6 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner6, "viewLifecycleOwner");
            liveTrackingIsClicked.observe(viewLifecycleOwner6, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.initObservables.6
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        PengaturanFragment.this.startActivity(new Intent(PengaturanFragment.this.getActivity(), (Class<?>) LiveTrackingActivity.class));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showDialogLogout() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity());
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.hompage.HomePageActivity");
        ((HomePageActivity) activity).showAlertDialogKodeVerifikasi(bottomSheetDialog, "Logout", "Apakah yakin logout ?", "Untuk melakukan aksi berikut, tolong ketik kode verifikasi dengan benar", "Logout", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PengaturanFragment.showDialogLogout$lambda$4(this.f$0, view);
            }
        }, "Batal", new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PengaturanFragment.showDialogLogout$lambda$5(bottomSheetDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogLogout$lambda$4(PengaturanFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showDialogLogout$lambda$5(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        dialog.dismiss();
    }

    private final void logout() {
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.BaseClassActivity");
        ((BaseClassActivity) activity).showProgressBar();
        CrashlyticHelper.INSTANCE.clearUserId();
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("firebase_analityc_set_user")) {
            FirebaseAnalitycHelper.Companion companion = FirebaseAnalitycHelper.INSTANCE;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            companion.clearUser(contextRequireContext);
        }
        logoutKeycloak();
        FasihApp.INSTANCE.getSession().logout(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.hompage.pengaturan_fragment.PengaturanFragment.logout.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                Intent intent = new Intent(PengaturanFragment.this.getActivity(), (Class<?>) OnBoardingActivity.class);
                FragmentActivity activity2 = PengaturanFragment.this.getActivity();
                if (activity2 != null) {
                    activity2.startActivity(intent);
                }
                FragmentActivity activity3 = PengaturanFragment.this.getActivity();
                if (activity3 != null) {
                    activity3.finish();
                }
            }
        });
    }

    public final void logoutKeycloak() {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://sso.bps.go.id/auth/realms/eksternal/protocol/openid-connect/logout").buildUpon().appendQueryParameter("redirect_uri", "fasih://id.go.bpsfasih").build());
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.addFlags(268435456);
        startActivity(intent);
    }

    public final void showProgressBar() {
        if (this.customProgressBar == null) {
            Context applicationContext = requireActivity().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireActivity().applicationContext");
            this.customProgressBar = new CustomProgressBar(applicationContext, null, 2, null);
        }
        if (this.customProgressBar != null) {
            Context applicationContext2 = requireActivity().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "requireActivity().applicationContext");
            new CustomProgressBar(applicationContext2, null, 2, null);
        }
        CustomProgressBar customProgressBar = this.customProgressBar;
        if (customProgressBar != null) {
            customProgressBar.showLoading();
        }
    }

    public final void hideProgressBar() {
        if (this.customProgressBar == null) {
            Context applicationContext = requireActivity().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "requireActivity().applicationContext");
            this.customProgressBar = new CustomProgressBar(applicationContext, null, 2, null);
        }
        CustomProgressBar customProgressBar = this.customProgressBar;
        if (customProgressBar == null || customProgressBar == null) {
            return;
        }
        customProgressBar.dismiss();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updateLiveTrackingVisibility();
    }

    /* compiled from: PengaturanFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/ui/hompage/pengaturan_fragment/PengaturanFragment$Companion;", "", "()V", "newInstance", "Lid/go/bpsfasih/ui/hompage/pengaturan_fragment/PengaturanFragment;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final PengaturanFragment newInstance() {
            PengaturanFragment pengaturanFragment = new PengaturanFragment();
            pengaturanFragment.setArguments(new Bundle());
            return pengaturanFragment;
        }
    }
}
