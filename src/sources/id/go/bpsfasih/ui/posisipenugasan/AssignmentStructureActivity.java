package id.go.bpsfasih.ui.posisipenugasan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.R;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.AssignmentStructureBinding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: AssignmentStructureActivity.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0003J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/ui/posisipenugasan/AssignmentStructureActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "binding", "Lid/go/bpsfasih/databinding/AssignmentStructureBinding;", "jsi", "Lid/go/bpsfasih/ui/posisipenugasan/JavaScriptInterfaceAssignmentStructure;", "mAssignmentId", "", "loadContent", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentStructureActivity extends BaseClassActivityNew {
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private AssignmentStructureBinding binding;
    private JavaScriptInterfaceAssignmentStructure jsi;
    private String mAssignmentId;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

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

    /* compiled from: AssignmentStructureActivity.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lid/go/bpsfasih/ui/posisipenugasan/AssignmentStructureActivity$Companion;", "", "()V", "startActivity", "", "context", "Landroid/content/Context;", "assignmentId", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void startActivity(Context context, String assignmentId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
            Intent intent = new Intent(context, (Class<?>) AssignmentStructureActivity.class);
            intent.putExtra(CommonCons.INSTANCE.getKEY_ASSIGNMENT_ID(), assignmentId);
            context.startActivity(intent);
        }
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.assignment_structure);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(this, R.l…out.assignment_structure)");
        this.binding = (AssignmentStructureBinding) contentView;
        this.mAssignmentId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_ASSIGNMENT_ID());
        BaseClassActivityNew.setAppBar$default(this, 0, "Posisi Penugasan", null, null, null, 24, null);
        String str = this.mAssignmentId;
        Intrinsics.checkNotNull(str);
        loadContent(str);
    }

    /* compiled from: AssignmentStructureActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity$loadContent$1", f = "AssignmentStructureActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity$loadContent$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mAssignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$mAssignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentStructureActivity.this.new AnonymousClass1(this.$mAssignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).getSettings().setJavaScriptEnabled(true);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccess(true);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).getSettings().setAllowFileAccessFromFileURLs(true);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).clearCache(true);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).clearCache(true);
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).loadUrl("file:///android_asset/assignment_struktur.html");
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity.loadContent.1.1
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            AssignmentStructureActivity assignmentStructureActivity = AssignmentStructureActivity.this;
            AssignmentStructureActivity assignmentStructureActivity2 = new AssignmentStructureActivity();
            WebView wv_main = (WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main);
            Intrinsics.checkNotNullExpressionValue(wv_main, "wv_main");
            assignmentStructureActivity.jsi = new JavaScriptInterfaceAssignmentStructure(assignmentStructureActivity2, wv_main, "");
            WebView webView = (WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main);
            JavaScriptInterfaceAssignmentStructure javaScriptInterfaceAssignmentStructure = AssignmentStructureActivity.this.jsi;
            if (javaScriptInterfaceAssignmentStructure == null) {
                Intrinsics.throwUninitializedPropertyAccessException("jsi");
                javaScriptInterfaceAssignmentStructure = null;
            }
            webView.addJavascriptInterface(javaScriptInterfaceAssignmentStructure, "Android");
            ((WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main)).setWebChromeClient(new WebChromeClient() { // from class: id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity.loadContent.1.2
                @Override // android.webkit.WebChromeClient
                public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                    Intrinsics.checkNotNullParameter(origin, "origin");
                    Intrinsics.checkNotNullParameter(callback, "callback");
                    callback.invoke(origin, true, false);
                }
            });
            WebView webView2 = (WebView) AssignmentStructureActivity.this._$_findCachedViewById(R.id.wv_main);
            final AssignmentStructureActivity assignmentStructureActivity3 = AssignmentStructureActivity.this;
            final String str = this.$mAssignmentId;
            webView2.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.posisipenugasan.AssignmentStructureActivity.loadContent.1.3
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView view, String url) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    super.onPageFinished(view, url);
                    JavaScriptInterfaceAssignmentStructure javaScriptInterfaceAssignmentStructure2 = assignmentStructureActivity3.jsi;
                    if (javaScriptInterfaceAssignmentStructure2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("jsi");
                        javaScriptInterfaceAssignmentStructure2 = null;
                    }
                    javaScriptInterfaceAssignmentStructure2.execute(str);
                }
            });
            return Unit.INSTANCE;
        }
    }

    private final void loadContent(String mAssignmentId) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(mAssignmentId, null), 2, null);
    }
}
