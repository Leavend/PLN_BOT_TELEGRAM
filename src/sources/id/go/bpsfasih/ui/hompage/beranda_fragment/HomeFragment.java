package id.go.bpsfasih.ui.hompage.beranda_fragment;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.net.MailTo;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.codekidlabs.storagechooser.utils.MemoryUtil;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import id.go.bpsfasih.BuildConfig;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.pojo.SurveyPojo;
import id.go.bpsfasih.databinding.FragmentHomeBinding;
import id.go.bpsfasih.ui.bantuan.BantuanActivity;
import id.go.bpsfasih.ui.hompage.HomePageActivity;
import id.go.bpsfasih.ui.recap.RecapActivity;
import id.go.bpsfasih.utils.SingleLiveEvent;
import id.go.bpsfasih.utils.helper.FormEngineHelper;
import id.go.bpsfasih.utils.helper.RemoteConfigHelper;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: HomeFragment.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentUpdateListingNewAdapter;", "binding", "Lid/go/bpsfasih/databinding/FragmentHomeBinding;", "viewModel", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragmentViewModel;", "initObservable", "", "initView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", MemoryUtil.CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class HomeFragment extends Fragment {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private HomeFragmentUpdateListingNewAdapter adapter;
    private FragmentHomeBinding binding;
    private HomeFragmentViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @JvmStatic
    public static final HomeFragment newInstance() {
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
        FragmentActivity activity = getActivity();
        Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type id.go.bpsfasih.ui.hompage.HomePageActivity");
        this.viewModel = new HomeFragmentViewModel((HomePageActivity) activity);
        FragmentHomeBinding fragmentHomeBinding = (FragmentHomeBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        this.binding = fragmentHomeBinding;
        if (fragmentHomeBinding != null) {
            HomeFragmentViewModel homeFragmentViewModel = this.viewModel;
            if (homeFragmentViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                homeFragmentViewModel = null;
            }
            fragmentHomeBinding.setViewModel(homeFragmentViewModel);
        }
        FragmentHomeBinding fragmentHomeBinding2 = this.binding;
        Intrinsics.checkNotNull(fragmentHomeBinding2);
        return fragmentHomeBinding2.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Context context = getContext();
        HomeFragmentViewModel homeFragmentViewModel = this.viewModel;
        if (homeFragmentViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            homeFragmentViewModel = null;
        }
        this.adapter = new HomeFragmentUpdateListingNewAdapter(context, homeFragmentViewModel, this);
        ((RecyclerView) _$_findCachedViewById(R.id.list_survei)).setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        ((RecyclerView) _$_findCachedViewById(R.id.list_survei)).setAdapter(this.adapter);
        initView();
        initObservable();
        if (RemoteConfigHelper.INSTANCE.getFeaturesRemoteConfigIsShow("recap")) {
            ((CardView) _$_findCachedViewById(R.id.rekapTahunan_card)).setVisibility(0);
            ((CardView) _$_findCachedViewById(R.id.rekapTahunan_card)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    HomeFragment.onViewCreated$lambda$0(this.f$0, view2);
                }
            });
        }
        getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.startActivity(new Intent(this$0.getContext(), (Class<?>) RecapActivity.class));
    }

    private final void initView() {
        ((TextView) _$_findCachedViewById(R.id.haloUser_tv)).setText("Halo, " + FasihApp.INSTANCE.getSession().getSessionString(CommonCons.INSTANCE.getSESSION_USERNAME()) + "!");
        ((TextView) _$_findCachedViewById(R.id.lihatSemua_tv)).setPaintFlags(8);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireActivity());
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_base_activity);
        Window window = bottomSheetDialog.getWindow();
        Intrinsics.checkNotNull(window);
        window.getDecorView().findViewById(com.google.android.material.R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        ((TextView) bottomSheetDialog.findViewById(R.id.halaman_tv)).setText("Beranda");
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_formgear_tv)).setText(FormEngineHelper.INSTANCE.getFormEngineVersion(CommonCons.FORMGEAR_ID_DUMMY));
        ((TextView) bottomSheetDialog.findViewById(R.id.versi_aplikasi_tv)).setText(BuildConfig.VERSION_NAME);
        if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_DEV, false, 2, null)) {
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(0);
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.initView$lambda$2(bottomSheetDialog, view);
                }
            });
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Development");
        } else if (StringsKt.equals$default(FasihApp.INSTANCE.getSession().getMode(), CommonCons.MODE_PROD, false, 2, null)) {
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setVisibility(0);
            ((ImageView) _$_findCachedViewById(R.id.modeProd_iv)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HomeFragment.initView$lambda$3(bottomSheetDialog, view);
                }
            });
            ((Button) _$_findCachedViewById(R.id.modeDev_tv)).setVisibility(8);
            ((TextView) bottomSheetDialog.findViewById(R.id.modeBottomSheer_tv)).setText("Production");
        }
        ((Button) bottomSheetDialog.findViewById(R.id.laporkan_b)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.initView$lambda$4(this.f$0, view);
            }
        });
        ((ImageView) bottomSheetDialog.findViewById(R.id.tutup)).setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.initView$lambda$5(bottomSheetDialog, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$2(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$3(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$4(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BantuanActivity.Companion companion = BantuanActivity.INSTANCE;
        Context contextRequireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        companion.startActivity(contextRequireContext, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initView$lambda$5(BottomSheetDialog dialog, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    private final void initObservable() {
        HomeFragmentViewModel homeFragmentViewModel = this.viewModel;
        HomeFragmentViewModel homeFragmentViewModel2 = null;
        if (homeFragmentViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            homeFragmentViewModel = null;
        }
        SingleLiveEvent<Boolean> emailIsClicked = homeFragmentViewModel.getEmailIsClicked();
        if (emailIsClicked != null) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            emailIsClicked.observe(viewLifecycleOwner, new Observer<Boolean>() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment.initObservable.1
                @Override // androidx.lifecycle.Observer
                public /* bridge */ /* synthetic */ void onChanged(Boolean bool) {
                    onChanged(bool.booleanValue());
                }

                public final void onChanged(boolean z) {
                    if (z) {
                        Intent intent = new Intent("android.intent.action.SENDTO");
                        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                        intent.setFlags(268435456);
                        intent.putExtra("android.intent.extra.EMAIL", new String[]{"halosis@bps.go.id"});
                        intent.putExtra("android.intent.extra.SUBJECT", "Permasalahan CAPI BPS");
                        HomeFragment.this.startActivity(Intent.createChooser(intent, "Email via..."));
                    }
                }
            });
        }
        HomeFragmentViewModel homeFragmentViewModel3 = this.viewModel;
        if (homeFragmentViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            homeFragmentViewModel3 = null;
        }
        homeFragmentViewModel3.getSurveysLiveData().observe(getViewLifecycleOwner(), new Observer<List<? extends SurveyPojo>>() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment.initObservable.2
            @Override // androidx.lifecycle.Observer
            public /* bridge */ /* synthetic */ void onChanged(List<? extends SurveyPojo> list) {
                onChanged2((List<SurveyPojo>) list);
            }

            /* renamed from: onChanged, reason: avoid collision after fix types in other method */
            public final void onChanged2(List<SurveyPojo> list) {
                if (list != null && list.size() > 0) {
                    ((TextView) HomeFragment.this._$_findCachedViewById(R.id.emptyPinData_tv)).setVisibility(8);
                    ((RecyclerView) HomeFragment.this._$_findCachedViewById(R.id.list_survei)).setVisibility(0);
                    HomeFragmentViewModel homeFragmentViewModel4 = HomeFragment.this.viewModel;
                    if (homeFragmentViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        homeFragmentViewModel4 = null;
                    }
                    ObservableBoolean surveyExist = homeFragmentViewModel4.getSurveyExist();
                    if (surveyExist != null) {
                        surveyExist.set(true);
                    }
                    HomeFragmentUpdateListingNewAdapter homeFragmentUpdateListingNewAdapter = HomeFragment.this.adapter;
                    if (homeFragmentUpdateListingNewAdapter != null) {
                        homeFragmentUpdateListingNewAdapter.setSurveys$app_release(list);
                        return;
                    }
                    return;
                }
                ((TextView) HomeFragment.this._$_findCachedViewById(R.id.emptyPinData_tv)).setVisibility(0);
                ((RecyclerView) HomeFragment.this._$_findCachedViewById(R.id.list_survei)).setVisibility(8);
            }
        });
        HomeFragmentViewModel homeFragmentViewModel4 = this.viewModel;
        if (homeFragmentViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            homeFragmentViewModel4 = null;
        }
        homeFragmentViewModel4.getJumlahSurvey().observe(getViewLifecycleOwner(), new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment.initObservable.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer it) {
                HomeFragment homeFragment = HomeFragment.this;
                Intrinsics.checkNotNullExpressionValue(it, "it");
                if (it.intValue() > 0) {
                    ((Button) homeFragment._$_findCachedViewById(R.id.syncSurvey_b)).setVisibility(0);
                    ((LinearLayout) homeFragment._$_findCachedViewById(R.id.notEmptyData_ll)).setVisibility(0);
                    ((LinearLayout) homeFragment._$_findCachedViewById(R.id.emptyData_ll)).setVisibility(8);
                    HomeFragmentViewModel homeFragmentViewModel5 = homeFragment.viewModel;
                    if (homeFragmentViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        homeFragmentViewModel5 = null;
                    }
                    ObservableBoolean surveyExist = homeFragmentViewModel5.getSurveyExist();
                    if (surveyExist != null) {
                        surveyExist.set(true);
                    }
                } else {
                    ((Button) homeFragment._$_findCachedViewById(R.id.syncSurvey_b)).setVisibility(8);
                    ((LinearLayout) homeFragment._$_findCachedViewById(R.id.notEmptyData_ll)).setVisibility(8);
                    ((LinearLayout) homeFragment._$_findCachedViewById(R.id.emptyData_ll)).setVisibility(0);
                }
                ((TextView) homeFragment._$_findCachedViewById(R.id.jml_survei_tv)).setText(String.valueOf(it));
            }
        }));
        HomeFragmentViewModel homeFragmentViewModel5 = this.viewModel;
        if (homeFragmentViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            homeFragmentViewModel5 = null;
        }
        homeFragmentViewModel5.getJumlahPeriode().observe(getViewLifecycleOwner(), new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment.initObservable.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                ((TextView) HomeFragment.this._$_findCachedViewById(R.id.jml_periode_tv)).setText(String.valueOf(num));
            }
        }));
        HomeFragmentViewModel homeFragmentViewModel6 = this.viewModel;
        if (homeFragmentViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            homeFragmentViewModel2 = homeFragmentViewModel6;
        }
        homeFragmentViewModel2.getJumlahAssignment().observe(getViewLifecycleOwner(), new HomeFragment$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: id.go.bpsfasih.ui.hompage.beranda_fragment.HomeFragment.initObservable.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke2(num);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Integer num) {
                ((TextView) HomeFragment.this._$_findCachedViewById(R.id.jml_assignment_tv)).setText(String.valueOf(num));
            }
        }));
    }

    /* compiled from: HomeFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment$Companion;", "", "()V", "newInstance", "Lid/go/bpsfasih/ui/hompage/beranda_fragment/HomeFragment;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final HomeFragment newInstance() {
            HomeFragment homeFragment = new HomeFragment();
            homeFragment.setArguments(new Bundle());
            return homeFragment;
        }
    }
}
