package id.go.bpsfasih.ui.miniDashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.gson.Gson;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.databinding.ActivityMiniDashboardBinding;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.Kripto;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: MiniDashboardActivity.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0012\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004J\u0010\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0004J\b\u0010\u0018\u001a\u00020\u0019H\u0003J\u0012\u0010\u001a\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/ui/miniDashboard/MiniDashboardActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "assignmentList", "", "binding", "Lid/go/bpsfasih/databinding/ActivityMiniDashboardBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityMiniDashboardBinding;", "binding$delegate", "Lkotlin/Lazy;", "jsi", "Lid/go/bpsfasih/ui/miniDashboard/MiniDashboardJavascriptInterface;", "periodeId", "getPeriodeId", "()Ljava/lang/String;", "setPeriodeId", "(Ljava/lang/String;)V", "getResponse", DownloadModel.ID, "surveyId", "isJsonFormat", "", "str", "loadContent", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MiniDashboardActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    private String assignmentList;
    private MiniDashboardJavascriptInterface jsi;
    private String periodeId;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();

    /* renamed from: binding$delegate, reason: from kotlin metadata */
    private final Lazy binding = LazyKt.lazy(new Function0<ActivityMiniDashboardBinding>() { // from class: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity$binding$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityMiniDashboardBinding invoke() {
            return ActivityMiniDashboardBinding.inflate(this.this$0.getLayoutInflater());
        }
    });

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
    public final ActivityMiniDashboardBinding getBinding() {
        return (ActivityMiniDashboardBinding) this.binding.getValue();
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final void setPeriodeId(String str) {
        this.periodeId = str;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.periodeId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        setContentView(getBinding().getRoot());
        loadContent();
    }

    private final void loadContent() {
        getBinding().wvMain.getSettings().setJavaScriptEnabled(true);
        getBinding().wvMain.getSettings().setAllowFileAccess(true);
        getBinding().wvMain.getSettings().setAllowFileAccessFromFileURLs(true);
        getBinding().wvMain.clearCache(true);
        getBinding().wvMain.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity.loadContent.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        getBinding().wvMain.setWebChromeClient(new WebChromeClient() { // from class: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity.loadContent.2
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = new Object[3];
                objArr[0] = consoleMessage != null ? consoleMessage.message() : null;
                objArr[1] = consoleMessage != null ? Integer.valueOf(consoleMessage.lineNumber()) : null;
                objArr[2] = consoleMessage != null ? consoleMessage.sourceId() : null;
                String str = String.format("%s %s:%d", Arrays.copyOf(objArr, 3));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                Log.d("WebView", str);
                return true;
            }
        });
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new AnonymousClass3(null), 3, null);
    }

    /* compiled from: MiniDashboardActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity$loadContent$3", f = "MiniDashboardActivity.kt", i = {}, l = {ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity$loadContent$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MiniDashboardActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = BuildersKt.withContext(Dispatchers.getIO(), new MiniDashboardActivity$loadContent$3$query$1(MiniDashboardActivity.this, null), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            List<AssignmentEntity> list = (List) obj;
            MiniDashboardActivity miniDashboardActivity = MiniDashboardActivity.this;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (AssignmentEntity assignmentEntity : list) {
                arrayList.add(MapsKt.mapOf(TuplesKt.to(DownloadModel.ID, assignmentEntity.getId()), TuplesKt.to("data_json", miniDashboardActivity.getResponse(assignmentEntity.getId(), assignmentEntity.getSurveyIdNotPrimary(), assignmentEntity.getPeriodeNotPrimary()))));
            }
            MiniDashboardActivity.this.assignmentList = new Gson().toJson(arrayList);
            MiniDashboardActivity miniDashboardActivity2 = MiniDashboardActivity.this;
            WebView webView = MiniDashboardActivity.this.getBinding().wvMain;
            Intrinsics.checkNotNullExpressionValue(webView, "binding.wvMain");
            MiniDashboardActivity miniDashboardActivity3 = MiniDashboardActivity.this;
            String str = miniDashboardActivity3.assignmentList;
            Intrinsics.checkNotNull(str);
            miniDashboardActivity2.jsi = new MiniDashboardJavascriptInterface(webView, miniDashboardActivity3, str, "");
            Log.d(">>>>", MiniDashboardActivity.this.assignmentList);
            WebView webView2 = MiniDashboardActivity.this.getBinding().wvMain;
            MiniDashboardJavascriptInterface miniDashboardJavascriptInterface = MiniDashboardActivity.this.jsi;
            Intrinsics.checkNotNull(miniDashboardJavascriptInterface);
            webView2.addJavascriptInterface(miniDashboardJavascriptInterface, "Android");
            MiniDashboardActivity.this.getBinding().wvMain.setWebChromeClient(new WebChromeClient() { // from class: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity.loadContent.3.1
                @Override // android.webkit.WebChromeClient
                public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                    Intrinsics.checkNotNullParameter(origin, "origin");
                    Intrinsics.checkNotNullParameter(callback, "callback");
                    callback.invoke(origin, true, false);
                }
            });
            MiniDashboardActivity.this.getBinding().wvMain.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.miniDashboard.MiniDashboardActivity.loadContent.3.2
                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView view, String url) {
                    Intrinsics.checkNotNullParameter(view, "view");
                    Intrinsics.checkNotNullParameter(url, "url");
                    super.onPageFinished(view, url);
                }
            });
            MiniDashboardActivity.this.getBinding().wvMain.loadUrl("file:///android_asset/dashboard.html");
            return Unit.INSTANCE;
        }
    }

    public final String getResponse(String id2, String surveyId, String periodeId) {
        String string = "{}";
        Intrinsics.checkNotNullParameter(id2, "id");
        Intrinsics.checkNotNullParameter(surveyId, "surveyId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        String strPathAnswerAssignment = FileHelper.INSTANCE.pathAnswerAssignment(surveyId, periodeId, id2);
        try {
            if (new File(strPathAnswerAssignment + "/data.json").exists()) {
                string = FileHelper.INSTANCE.readFile(strPathAnswerAssignment + "/data.json").toString();
            }
        } catch (IOException unused) {
        }
        if (isJsonFormat(string)) {
            return string;
        }
        Kripto kripto = new Kripto();
        String encryption_secret_key = CommonCons.INSTANCE.getENCRYPTION_SECRET_KEY();
        Intrinsics.checkNotNull(string);
        return String.valueOf(kripto.decrypt(encryption_secret_key, string));
    }

    public final boolean isJsonFormat(String str) {
        try {
            try {
                new JSONObject(str);
                return true;
            } catch (JSONException unused) {
                new JSONArray(str);
                return true;
            }
        } catch (JSONException unused2) {
            return false;
        }
    }
}
