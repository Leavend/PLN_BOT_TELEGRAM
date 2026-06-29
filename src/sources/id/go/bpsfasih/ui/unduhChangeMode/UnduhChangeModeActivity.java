package id.go.bpsfasih.ui.unduhChangeMode;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.domain.models.UnduhChangeModeModel;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UnduhChangeModeActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "mAdapter", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeAdapter;", "mPeriodeId", "", "viewModel", "Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeViewModel;", "initListener", "", "initObserve", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class UnduhChangeModeActivity extends BaseClassActivityNew {
    private UnduhChangeModeAdapter mAdapter;
    private UnduhChangeModeViewModel viewModel;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String mPeriodeId = "";

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

    public final void initListener() {
    }

    /* compiled from: UnduhChangeModeActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/ui/unduhChangeMode/UnduhChangeModeActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "periodeId", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String periodeId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(periodeId, "periodeId");
            Intent intent = new Intent(context, (Class<?>) UnduhChangeModeActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID(), periodeId);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unduh_change_mode);
        setStatusBarColor();
        BaseClassActivityNew.setAppBar$default(this, 0, "Unduh Change Mode", null, null, null, 24, null);
        Intent intent = getIntent();
        String string = null;
        if ((intent != null ? intent.getExtras() : null) != null) {
            Intent intent2 = getIntent();
            if (intent2 != null && (extras = intent2.getExtras()) != null) {
                string = extras.getString(CommonCons.INSTANCE.getKEY_PERIODE_ID());
            }
            this.mPeriodeId = string;
        }
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "this.application");
        String str = this.mPeriodeId;
        Intrinsics.checkNotNull(str);
        this.viewModel = (UnduhChangeModeViewModel) ViewModelProviders.of(this, new UnduhChangeModeViewModelFactory(application, this, str)).get(UnduhChangeModeViewModel.class);
        initView();
        initObserve();
        initListener();
    }

    private final void initView() {
        showProgressBar();
        UnduhChangeModeActivity unduhChangeModeActivity = this;
        UnduhChangeModeActivity unduhChangeModeActivity2 = this;
        UnduhChangeModeViewModel unduhChangeModeViewModel = this.viewModel;
        if (unduhChangeModeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            unduhChangeModeViewModel = null;
        }
        this.mAdapter = new UnduhChangeModeAdapter(unduhChangeModeActivity, unduhChangeModeActivity2, unduhChangeModeViewModel);
        ((RecyclerView) _$_findCachedViewById(R.id.changeModeRecycler)).setAdapter(this.mAdapter);
        ((RecyclerView) _$_findCachedViewById(R.id.changeModeRecycler)).setLayoutManager(new LinearLayoutManager(unduhChangeModeActivity2));
    }

    private final void initObserve() {
        UnduhChangeModeViewModel unduhChangeModeViewModel = this.viewModel;
        UnduhChangeModeViewModel unduhChangeModeViewModel2 = null;
        if (unduhChangeModeViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            unduhChangeModeViewModel = null;
        }
        UnduhChangeModeActivity unduhChangeModeActivity = this;
        unduhChangeModeViewModel.getShowProgressBar().observe(unduhChangeModeActivity, new UnduhChangeModeActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity.initObserve.1
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
                    UnduhChangeModeActivity.this.showProgressBar();
                } else {
                    UnduhChangeModeActivity.this.hideProgressBar();
                }
            }
        }));
        UnduhChangeModeViewModel unduhChangeModeViewModel3 = this.viewModel;
        if (unduhChangeModeViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            unduhChangeModeViewModel3 = null;
        }
        unduhChangeModeViewModel3.getFinishActivity().observe(unduhChangeModeActivity, new UnduhChangeModeActivity$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity.initObserve.2
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
                    UnduhChangeModeActivity.this.finish();
                }
            }
        }));
        UnduhChangeModeViewModel unduhChangeModeViewModel4 = this.viewModel;
        if (unduhChangeModeViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            unduhChangeModeViewModel4 = null;
        }
        unduhChangeModeViewModel4.get_list().observe(unduhChangeModeActivity, new UnduhChangeModeActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends UnduhChangeModeModel>, Unit>() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity.initObserve.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends UnduhChangeModeModel> list) {
                invoke2((List<UnduhChangeModeModel>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<UnduhChangeModeModel> it) {
                UnduhChangeModeAdapter unduhChangeModeAdapter = UnduhChangeModeActivity.this.mAdapter;
                if (unduhChangeModeAdapter != null) {
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    unduhChangeModeAdapter.setData$app_release(it);
                }
            }
        }));
        UnduhChangeModeViewModel unduhChangeModeViewModel5 = this.viewModel;
        if (unduhChangeModeViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            unduhChangeModeViewModel2 = unduhChangeModeViewModel5;
        }
        unduhChangeModeViewModel2.get_listDownloaded().observe(unduhChangeModeActivity, new UnduhChangeModeActivity$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends String>, Unit>() { // from class: id.go.bpsfasih.ui.unduhChangeMode.UnduhChangeModeActivity.initObserve.4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(List<? extends String> list) {
                invoke2((List<String>) list);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(List<String> list) {
                Log.d("FOUR", "list downloaded : " + list);
            }
        }));
    }
}
