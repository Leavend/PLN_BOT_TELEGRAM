package id.go.bpsfasih.data.localserver;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.localserver.Server;
import java.io.PrintWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Server.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.data.localserver.Server$HttpResponseThread$run$1$1$1", f = "Server.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {119, 133}, m = "invokeSuspend", n = {"json", "jsonArrayColumn", "jsonArrayData", "filteredString", "json", "jsonArrayColumn", "jsonArrayData", "filteredString", "data"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* loaded from: classes2.dex */
final class Server$HttpResponseThread$run$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $blokSensusId;
    final /* synthetic */ Ref.IntRef $column;
    final /* synthetic */ Ref.ObjectRef<String> $columnName;
    final /* synthetic */ Ref.ObjectRef<String> $filter;
    final /* synthetic */ Ref.ObjectRef<List<String>> $listFilter;
    final /* synthetic */ PrintWriter $out;
    final /* synthetic */ String $periodeId;
    final /* synthetic */ Ref.ObjectRef<AssignmentRepository> $repo;
    final /* synthetic */ Ref.ObjectRef<String> $sorting;
    final /* synthetic */ String $templateId;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ Server.HttpResponseThread this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Server$HttpResponseThread$run$1$1$1(String str, Ref.ObjectRef<String> objectRef, Ref.IntRef intRef, Server.HttpResponseThread httpResponseThread, Ref.ObjectRef<String> objectRef2, Ref.ObjectRef<List<String>> objectRef3, Ref.ObjectRef<AssignmentRepository> objectRef4, String str2, String str3, Ref.ObjectRef<String> objectRef5, PrintWriter printWriter, Continuation<? super Server$HttpResponseThread$run$1$1$1> continuation) {
        super(2, continuation);
        this.$templateId = str;
        this.$columnName = objectRef;
        this.$column = intRef;
        this.this$0 = httpResponseThread;
        this.$filter = objectRef2;
        this.$listFilter = objectRef3;
        this.$repo = objectRef4;
        this.$blokSensusId = str2;
        this.$periodeId = str3;
        this.$sorting = objectRef5;
        this.$out = printWriter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Server$HttpResponseThread$run$1$1$1(this.$templateId, this.$columnName, this.$column, this.this$0, this.$filter, this.$listFilter, this.$repo, this.$blokSensusId, this.$periodeId, this.$sorting, this.$out, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((Server$HttpResponseThread$run$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0461  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0472  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:212:0x0618  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0626  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x067b  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x06fb  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x072e  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0761  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0794  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x07c7  */
    /* JADX WARN: Removed duplicated region for block: B:367:0x07fa  */
    /* JADX WARN: Removed duplicated region for block: B:386:0x082d  */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0860  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x0893  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x08fa  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x090e  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01e3  */
    /* JADX WARN: Type inference failed for: r0v188, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v195, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v201, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v207, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v213, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v219, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v10, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v27, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v31, types: [T, java.util.List] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r36) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 2354
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.localserver.Server$HttpResponseThread$run$1$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
