package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.helper.ParseLookUp;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: SyncLookUp.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001BM\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003\u00126\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\u0002\u0010\rJ\n\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0006\u0010\u0017\u001a\u00020\fRA\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\"\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013¨\u0006\u0018"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/SyncLookUp;", "", "pathList", "", "", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "message", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "getCallback", "()Lkotlin/jvm/functions/Function2;", "getPathList", "()Ljava/util/List;", "setPathList", "(Ljava/util/List;)V", "getPath", "insertLookUp", "filesLookup", "start", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class SyncLookUp {
    public static final int $stable = 8;
    private final Function2<Boolean, String, Unit> callback;
    private List<String> pathList;

    /* JADX WARN: Multi-variable type inference failed */
    public SyncLookUp(List<String> pathList, Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkNotNullParameter(pathList, "pathList");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        this.pathList = CollectionsKt.toMutableList((Collection) pathList);
        start();
    }

    public final Function2<Boolean, String, Unit> getCallback() {
        return this.callback;
    }

    public final List<String> getPathList() {
        return this.pathList;
    }

    public final void setPathList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.pathList = list;
    }

    public final void start() {
        if (this.pathList.size() > 0) {
            String path = getPath();
            if (path != null) {
                insertLookUp(path);
                return;
            }
            return;
        }
        this.callback.invoke(false, "Success");
    }

    private final String getPath() {
        String str = (String) CollectionsKt.first((List) this.pathList);
        this.pathList.remove(0);
        return str;
    }

    /* compiled from: SyncLookUp.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.SyncLookUp$insertLookUp$1", f = "SyncLookUp.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.sync.reqDownload.SyncLookUp$insertLookUp$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $filesLookup;
        int label;
        final /* synthetic */ SyncLookUp this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, SyncLookUp syncLookUp, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$filesLookup = str;
            this.this$0 = syncLookUp;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$filesLookup, this.this$0, continuation);
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
            if (this.$filesLookup.length() == 0) {
                this.this$0.start();
            } else {
                try {
                    String str = this.$filesLookup;
                    final SyncLookUp syncLookUp = this.this$0;
                    new ParseLookUp(str, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.SyncLookUp.insertLookUp.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str2) {
                            invoke(bool.booleanValue(), str2);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z, String message) {
                            Intrinsics.checkNotNullParameter(message, "message");
                            if (!z) {
                                syncLookUp.start();
                            } else {
                                syncLookUp.getCallback().invoke(Boolean.valueOf(z), message);
                            }
                        }
                    });
                } catch (Exception e) {
                    CrashHandler.INSTANCE.sendExeption(e);
                    this.this$0.getCallback().invoke(Boxing.boxBoolean(true), "Ada kesalahan pada data lookup");
                }
            }
            return Unit.INSTANCE;
        }
    }

    private final void insertLookUp(String filesLookup) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass1(filesLookup, this, null), 2, null);
    }
}
