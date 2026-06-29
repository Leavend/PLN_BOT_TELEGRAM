package id.go.bpsfasih.ui.posisipenugasan;

import android.webkit.WebView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.gson.Gson;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: JavaScriptInterfaceAssignmentStructure.kt */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/ui/posisipenugasan/JavaScriptInterfaceAssignmentStructure;", "", "activity", "Lid/go/bpsfasih/ui/posisipenugasan/AssignmentStructureActivity;", "_webView", "Landroid/webkit/WebView;", "assignmentId", "", "(Lid/go/bpsfasih/ui/posisipenugasan/AssignmentStructureActivity;Landroid/webkit/WebView;Ljava/lang/String;)V", "mWebView", "execute", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JavaScriptInterfaceAssignmentStructure {
    public static final int $stable = 8;
    private AssignmentStructureActivity activity;
    private WebView mWebView;

    public JavaScriptInterfaceAssignmentStructure(AssignmentStructureActivity activity, WebView _webView, String assignmentId) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(_webView, "_webView");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.mWebView = _webView;
        this.activity = activity;
    }

    /* compiled from: JavaScriptInterfaceAssignmentStructure.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.ui.posisipenugasan.JavaScriptInterfaceAssignmentStructure$execute$1", f = "JavaScriptInterfaceAssignmentStructure.kt", i = {}, l = {22}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.ui.posisipenugasan.JavaScriptInterfaceAssignmentStructure$execute$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;
        final /* synthetic */ JavaScriptInterfaceAssignmentStructure this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, JavaScriptInterfaceAssignmentStructure javaScriptInterfaceAssignmentStructure, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.this$0 = javaScriptInterfaceAssignmentStructure;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$assignmentId, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DataSurvey.Assignment.INSTANCE.getAssignmentRepository().getAssignmentById(this.$assignmentId, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            Gson gson = new Gson();
            AssignmentEntity assignment = ((Sync) obj).getAssignment();
            String str = "javascript:init('" + gson.toJson(assignment != null ? assignment.getAssignmentResponsibility() : null) + "')";
            WebView webView = this.this$0.mWebView;
            if (webView != null) {
                webView.evaluateJavascript(str, null);
            }
            return Unit.INSTANCE;
        }
    }

    public final void execute(String assignmentId) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(assignmentId, this, null), 2, null);
    }
}
