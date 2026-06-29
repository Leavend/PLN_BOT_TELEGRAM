package id.go.bpsfasih.ui.downloadFormEngine;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.ActivityFormgearDownloadBinding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DownloadFormEngineActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0003J\b\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0016H\u0002J\u0012\u0010\u0019\u001a\u00020\u00162\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0015R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/ui/downloadFormEngine/DownloadFormEngineActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "binding", "Lid/go/bpsfasih/databinding/ActivityFormgearDownloadBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityFormgearDownloadBinding;", "binding$delegate", "Lkotlin/Lazy;", "surveyId", "", "getSurveyId", "()Ljava/lang/String;", "setSurveyId", "(Ljava/lang/String;)V", "viewModel", "Lid/go/bpsfasih/ui/downloadFormEngine/DownloadFormEngineViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/downloadFormEngine/DownloadFormEngineViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/downloadFormEngine/DownloadFormEngineViewModel;)V", "initListener", "", "initObserver", "initView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class DownloadFormEngineActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<ActivityFormgearDownloadBinding>() { // from class: id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity$binding$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityFormgearDownloadBinding invoke() {
            return ActivityFormgearDownloadBinding.inflate(this.this$0.getLayoutInflater());
        }
    });
    private String surveyId = "";
    public DownloadFormEngineViewModel viewModel;

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

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityFormgearDownloadBinding getBinding() {
        return (ActivityFormgearDownloadBinding) this.binding.getValue();
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final DownloadFormEngineViewModel getViewModel() {
        DownloadFormEngineViewModel downloadFormEngineViewModel = this.viewModel;
        if (downloadFormEngineViewModel != null) {
            return downloadFormEngineViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(DownloadFormEngineViewModel downloadFormEngineViewModel) {
        Intrinsics.checkNotNullParameter(downloadFormEngineViewModel, "<set-?>");
        this.viewModel = downloadFormEngineViewModel;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBinding().getRoot());
        this.surveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        String str = this.surveyId;
        Intrinsics.checkNotNull(str);
        setViewModel(new DownloadFormEngineViewModel(this, str));
        setStatusBarColor();
        initView();
        initObserver();
        initListener();
    }

    private final void initView() {
        getBinding().icon.setImageResource(R.drawable.icon_warning_page_3);
    }

    private final void initObserver() {
        getViewModel().getDescription().observe(this, new DownloadFormEngineActivity$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity.initObserver.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String str) {
                DownloadFormEngineActivity.this.getBinding().keterangan.setText(str);
            }
        }));
    }

    private final void initListener() {
        getBinding().unduhEngine.setOnClickListener(new View.OnClickListener() { // from class: id.go.bpsfasih.ui.downloadFormEngine.DownloadFormEngineActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DownloadFormEngineActivity.initListener$lambda$0(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(DownloadFormEngineActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().downloadFormEngine();
    }
}
