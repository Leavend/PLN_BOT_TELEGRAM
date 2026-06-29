package id.go.bpsfasih.ui.tarikSampelOffline;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.gms.actions.SearchIntents;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.BaseClassActivityNew;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.databinding.ActivityTarikSampelOfflineBinding;
import id.go.bpsfasih.utils.helper.AssignmentEncryptionHelper;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: TarikSampelOfflineActivity.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010'\u001a\u0004\u0018\u00010\u0004J\b\u0010(\u001a\u0004\u0018\u00010\u0004J\u001b\u0010)\u001a\u0004\u0018\u00010\u00042\u0006\u0010*\u001a\u00020\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u0004\u0018\u00010\u00042\u0006\u0010-\u001a\u00020\u0004J\u0010\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\u0004J\b\u00101\u001a\u000202H\u0003J\u0012\u00103\u001a\u0002022\b\u00104\u001a\u0004\u0018\u000105H\u0014R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000f\"\u0004\b\u001a\u0010\u0011R\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u000f\"\u0004\b\u001d\u0010\u0011R\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u000f\"\u0004\b \u0010\u0011R\u001a\u0010!\u001a\u00020\"X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&\u0082\u0002\u0004\n\u0002\b\u0019¨\u00066"}, d2 = {"Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampelOfflineActivity;", "Lid/go/bpsfasih/BaseClassActivityNew;", "()V", "assignmentList", "", "binding", "Lid/go/bpsfasih/databinding/ActivityTarikSampelOfflineBinding;", "getBinding", "()Lid/go/bpsfasih/databinding/ActivityTarikSampelOfflineBinding;", "setBinding", "(Lid/go/bpsfasih/databinding/ActivityTarikSampelOfflineBinding;)V", "jsi", "Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampelOfflineJavascriptInterface;", "mode", "getMode", "()Ljava/lang/String;", "setMode", "(Ljava/lang/String;)V", "periodeId", "getPeriodeId", "setPeriodeId", "regionFullCode", "getRegionFullCode", "setRegionFullCode", "regionId", "getRegionId", "setRegionId", "samplingRegionId", "getSamplingRegionId", "setSamplingRegionId", "surveyId", "getSurveyId", "setSurveyId", "viewModel", "Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;", "getViewModel", "()Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;", "setViewModel", "(Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;)V", "getHtml", "getJs", "getPrincipalTarget", "periodeTarget", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getResponse", DownloadModel.ID, "isJsonFormat", "", "str", "loadContent", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampelOfflineActivity extends BaseClassActivityNew {
    public static final int $stable = 8;
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    private String assignmentList;
    public ActivityTarikSampelOfflineBinding binding;
    private TarikSampelOfflineJavascriptInterface jsi;
    private String mode;
    private String periodeId;
    private String regionFullCode;
    private String regionId;
    private String samplingRegionId;
    private String surveyId;
    public TarikSampleOfflineViewModel viewModel;

    /* compiled from: TarikSampelOfflineActivity.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity", f = "TarikSampelOfflineActivity.kt", i = {}, l = {175, 179, 183}, m = "getPrincipalTarget", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$1, reason: invalid class name */
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TarikSampelOfflineActivity.this.getPrincipalTarget(null, this);
        }
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

    public final TarikSampleOfflineViewModel getViewModel() {
        TarikSampleOfflineViewModel tarikSampleOfflineViewModel = this.viewModel;
        if (tarikSampleOfflineViewModel != null) {
            return tarikSampleOfflineViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        return null;
    }

    public final void setViewModel(TarikSampleOfflineViewModel tarikSampleOfflineViewModel) {
        Intrinsics.checkNotNullParameter(tarikSampleOfflineViewModel, "<set-?>");
        this.viewModel = tarikSampleOfflineViewModel;
    }

    public final ActivityTarikSampelOfflineBinding getBinding() {
        ActivityTarikSampelOfflineBinding activityTarikSampelOfflineBinding = this.binding;
        if (activityTarikSampelOfflineBinding != null) {
            return activityTarikSampelOfflineBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(ActivityTarikSampelOfflineBinding activityTarikSampelOfflineBinding) {
        Intrinsics.checkNotNullParameter(activityTarikSampelOfflineBinding, "<set-?>");
        this.binding = activityTarikSampelOfflineBinding;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public final void setRegionId(String str) {
        this.regionId = str;
    }

    public final String getRegionFullCode() {
        return this.regionFullCode;
    }

    public final void setRegionFullCode(String str) {
        this.regionFullCode = str;
    }

    public final String getSurveyId() {
        return this.surveyId;
    }

    public final void setSurveyId(String str) {
        this.surveyId = str;
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final void setPeriodeId(String str) {
        this.periodeId = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        this.mode = str;
    }

    public final String getSamplingRegionId() {
        return this.samplingRegionId;
    }

    public final void setSamplingRegionId(String str) {
        this.samplingRegionId = str;
    }

    @Override // id.go.bpsfasih.BaseClassActivityNew, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityTarikSampelOfflineBinding activityTarikSampelOfflineBindingInflate = ActivityTarikSampelOfflineBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityTarikSampelOfflineBindingInflate, "inflate(layoutInflater)");
        setBinding(activityTarikSampelOfflineBindingInflate);
        this.regionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_ID());
        this.regionFullCode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_REGION_FULL_CODE());
        this.surveyId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SURVEY_ID());
        this.periodeId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_PERIODE_ID());
        this.mode = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_MODE());
        this.samplingRegionId = getIntent().getStringExtra(CommonCons.INSTANCE.getKEY_SAMPLING_REGION_ID());
        setViewModel(new TarikSampleOfflineViewModel());
        setContentView(getBinding().getRoot());
        loadContent();
    }

    private final void loadContent() {
        getBinding().wvMain.getSettings().setJavaScriptEnabled(true);
        getBinding().wvMain.getSettings().setAllowFileAccess(true);
        getBinding().wvMain.getSettings().setAllowFileAccessFromFileURLs(true);
        getBinding().wvMain.clearCache(true);
        getBinding().wvMain.setWebViewClient(new WebViewClient() { // from class: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.loadContent.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        getBinding().wvMain.setWebChromeClient(new WebChromeClient() { // from class: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.loadContent.2
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

    /* compiled from: TarikSampelOfflineActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$loadContent$3", f = "TarikSampelOfflineActivity.kt", i = {1, 2, 2}, l = {79, 84, 92}, m = "invokeSuspend", n = {SearchIntents.EXTRA_QUERY, SearchIntents.EXTRA_QUERY, "sampling"}, s = {"L$0", "L$0", "L$1"})
    /* renamed from: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$loadContent$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return TarikSampelOfflineActivity.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x008c A[Catch: Exception -> 0x003b, LOOP:0: B:26:0x0086->B:28:0x008c, LOOP_END, TryCatch #0 {Exception -> 0x003b, blocks: (B:8:0x001c, B:33:0x00b5, B:13:0x002f, B:25:0x007b, B:26:0x0086, B:28:0x008c, B:29:0x009e, B:14:0x0035, B:22:0x005c, B:19:0x0041), top: B:38:0x000c }] */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00b3 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00b4  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 370
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final String getResponse(String id2) {
        String str = "{}";
        Intrinsics.checkNotNullParameter(id2, "id");
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String str2 = this.surveyId;
        if (str2 == null) {
            str2 = "";
        }
        String str3 = this.periodeId;
        String strPathAnswerAssignment = companion.pathAnswerAssignment(str2, str3 != null ? str3 : "", id2);
        String string = new File(new StringBuilder().append(strPathAnswerAssignment).append("/data.json").toString()).exists() ? FileHelper.INSTANCE.readFile(strPathAnswerAssignment + "/data.json").toString() : "{}";
        if (isJsonFormat(string)) {
            return string;
        }
        try {
            AssignmentEncryptionHelper assignmentEncryptionHelper = AssignmentEncryptionHelper.INSTANCE;
            Intrinsics.checkNotNull(string);
            String strDecrypt = assignmentEncryptionHelper.decrypt(string, AssignmentEncryptionHelper.INSTANCE.getWrappedDataKey(this.regionId, this.periodeId));
            if (isJsonFormat(strDecrypt)) {
                str = strDecrypt;
            }
        } catch (Exception unused) {
        }
        return str;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0089 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object getPrincipalTarget(java.lang.String r8, kotlin.coroutines.Continuation<? super java.lang.String> r9) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r9 instanceof id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r9
            id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$1 r0 = (id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L19
        L14:
            id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$1 r0 = new id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$1
            r0.<init>(r9)
        L19:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L41
            if (r2 == r5) goto L3d
            if (r2 == r4) goto L39
            if (r2 != r3) goto L31
            kotlin.ResultKt.throwOnFailure(r9)
            goto L8a
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L72
        L3d:
            kotlin.ResultKt.throwOnFailure(r9)
            goto L5a
        L41:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$periode$1 r2 = new id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$periode$1
            r2.<init>(r8, r6)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r5
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r2, r0)
            if (r9 != r1) goto L5a
            return r1
        L5a:
            id.go.bpsfasih.data.local.entities.PeriodeEntityNew r9 = (id.go.bpsfasih.data.local.entities.PeriodeEntityNew) r9
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$template$1 r2 = new id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$template$1
            r2.<init>(r9, r6)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r4
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r9 != r1) goto L72
            return r1
        L72:
            id.go.bpsfasih.data.local.entities.TemplateValidationEntity r9 = (id.go.bpsfasih.data.local.entities.TemplateValidationEntity) r9
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$data$1 r2 = new id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity$getPrincipalTarget$data$1
            r2.<init>(r9, r6)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.label = r3
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r9 != r1) goto L8a
            return r1
        L8a:
            id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity r9 = (id.go.bpsfasih.data.local.entities.CustomDataTemplateEntity) r9
            com.google.gson.Gson r8 = new com.google.gson.Gson
            r8.<init>()
            java.lang.String r8 = r8.toJson(r9)
            java.lang.String r8 = r8.toString()
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineActivity.getPrincipalTarget(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String getHtml() throws IOException {
        InputStream inputStreamOpen = getAssets().open("sampling.html");
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assets.open(\"sampling.html\")");
        Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            return text;
        } finally {
        }
    }

    public final String getJs() throws IOException {
        InputStream inputStreamOpen = getAssets().open("sampling-runner.js");
        Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "assets.open(\"sampling-runner.js\")");
        Reader inputStreamReader = new InputStreamReader(inputStreamOpen, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String text = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            Log.d(">>> umd", text);
            return text;
        } finally {
        }
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
