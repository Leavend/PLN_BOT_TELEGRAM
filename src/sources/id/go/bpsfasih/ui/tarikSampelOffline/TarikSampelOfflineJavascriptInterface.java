package id.go.bpsfasih.ui.tarikSampelOffline;

import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentOfflineSamplingResult;
import id.go.bpsfasih.data.local.models.Target;
import id.go.bpsfasih.data.local.repository.SamplingRegionRepository;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.HttpUrl;

/* compiled from: TarikSampelOfflineJavascriptInterface.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0095\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0007H\u0007J\b\u0010\u001c\u001a\u0004\u0018\u00010\u0007J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010 \u001a\u0004\u0018\u00010\u0007H\u0007J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0007H\u0007J\u0010\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\u0007H\u0007R\u0011\u0010\u0016\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampelOfflineJavascriptInterface;", "", "webView", "Landroid/webkit/WebView;", "activity", "Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampelOfflineActivity;", "periodeId", "", "regionFullcode", "regionId", "assignmentList", "principals", "samplingId", "script", "sourceSchema", "targetSchema", "source", TypedValues.AttributesType.S_TARGET, "samplingRegionId", "viewModel", "Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;", "(Landroid/webkit/WebView;Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampelOfflineActivity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;)V", "PATH_DATA", "getPATH_DATA", "()Ljava/lang/String;", "getAssignments", "getFullcode", "getPrincipal", "getPrincipalsTarget", "getRegionFullcode", "getScript", "getSourceSchema", "getTargetSchema", "insertAssignmentJson", "", "assignmentEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "result", "assignmentResults", "showError", "message", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampelOfflineJavascriptInterface {
    public static final String TAG_HANDLER = "Android";
    private final String PATH_DATA;
    private TarikSampelOfflineActivity activity;
    private String assignmentList;
    private String periodeId;
    private String principals;
    private String regionFullcode;
    private String regionId;
    private String samplingId;
    private String samplingRegionId;
    private String script;
    private String source;
    private String sourceSchema;
    private String target;
    private String targetSchema;
    private TarikSampleOfflineViewModel viewModel;
    private WebView webView;
    public static final int $stable = 8;

    @JavascriptInterface
    public final String getFullcode() {
        return "";
    }

    public final String getPrincipalsTarget() {
        return "";
    }

    public TarikSampelOfflineJavascriptInterface(WebView webView, TarikSampelOfflineActivity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, TarikSampleOfflineViewModel viewModel) {
        Intrinsics.checkNotNullParameter(webView, "webView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.PATH_DATA = Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + "assignment_listing" + CommonCons.INSTANCE.getEXTENSION_JSON();
        this.webView = webView;
        this.activity = activity;
        this.periodeId = str;
        this.regionFullcode = str2;
        this.regionId = str3;
        this.assignmentList = str4;
        this.principals = str5;
        this.samplingId = str6;
        this.script = str7;
        this.sourceSchema = str8;
        this.targetSchema = str9;
        this.source = str10;
        this.target = str11;
        this.samplingRegionId = str12;
        this.viewModel = viewModel;
    }

    public final String getPATH_DATA() {
        return this.PATH_DATA;
    }

    @JavascriptInterface
    public final String getRegionFullcode() {
        return this.regionFullcode;
    }

    @JavascriptInterface
    /* renamed from: getAssignments, reason: from getter */
    public final String getAssignmentList() {
        return this.assignmentList;
    }

    @JavascriptInterface
    /* renamed from: getPrincipal, reason: from getter */
    public final String getPrincipals() {
        return this.principals;
    }

    @JavascriptInterface
    public final String getScript() {
        return this.script;
    }

    @JavascriptInterface
    public final String getSourceSchema() {
        return this.sourceSchema;
    }

    @JavascriptInterface
    public final String getTargetSchema() {
        return this.targetSchema;
    }

    @JavascriptInterface
    public final void result(String assignmentResults) throws JsonSyntaxException {
        Intrinsics.checkNotNullParameter(assignmentResults, "assignmentResults");
        Target target = (Target) new Gson().fromJson(this.target, Target.class);
        Object objFromJson = new Gson().fromJson(assignmentResults, new TypeToken<List<? extends AssignmentOfflineSamplingResult>>() { // from class: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineJavascriptInterface$result$listAssignmentType$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(assignme…ults, listAssignmentType)");
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.activity), null, null, new AnonymousClass1((List) objFromJson, this, target, null), 3, null);
    }

    /* compiled from: TarikSampelOfflineJavascriptInterface.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineJavascriptInterface$result$1", f = "TarikSampelOfflineJavascriptInterface.kt", i = {0}, l = {116}, m = "invokeSuspend", n = {"item"}, s = {"L$3"})
    /* renamed from: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineJavascriptInterface$result$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<AssignmentOfflineSamplingResult> $listAssignment;
        final /* synthetic */ Target $target;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        final /* synthetic */ TarikSampelOfflineJavascriptInterface this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(List<AssignmentOfflineSamplingResult> list, TarikSampelOfflineJavascriptInterface tarikSampelOfflineJavascriptInterface, Target target, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$listAssignment = list;
            this.this$0 = tarikSampelOfflineJavascriptInterface;
            this.$target = target;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$1(View view) {
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$listAssignment, this.this$0, this.$target, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:11:0x0048  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x008f  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x0228  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0255  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x006c -> B:15:0x0074). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r96) throws java.lang.Throwable {
            /*
                Method dump skipped, instructions count: 679
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.ui.tarikSampelOffline.TarikSampelOfflineJavascriptInterface.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invokeSuspend$lambda$2(TarikSampelOfflineJavascriptInterface tarikSampelOfflineJavascriptInterface, View view) {
            SamplingRegionRepository samplingRegionRepository = DataSurvey.SamplingRegion.INSTANCE.getSamplingRegionRepository();
            String str = tarikSampelOfflineJavascriptInterface.samplingRegionId;
            Intrinsics.checkNotNull(str);
            samplingRegionRepository.updateIsDone(str);
            tarikSampelOfflineJavascriptInterface.activity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertAssignmentJson(AssignmentEntity assignmentEntity) throws IOException {
        try {
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(this.PATH_DATA);
            if (!file2.exists()) {
                try {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e2) {
                    CrashHandler.INSTANCE.sendExeption(e2);
                    e2.printStackTrace();
                }
                file2.createNewFile();
            }
            Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) AssignmentEntity[].class);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…nmentEntity>::class.java)");
            List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
            mutableList.add(assignmentEntity);
            String jsonString = new Gson().toJson(mutableList);
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
            Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
            companion.writeFile(absolutepathenv, "assignment_listing.json", jsonString);
        } catch (Exception unused) {
        }
    }

    @JavascriptInterface
    public final void showError(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Log.d("FOUR", "result: " + message);
    }
}
