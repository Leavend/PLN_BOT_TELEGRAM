package id.go.bpsfasih.utils.sync.reqDownload;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.remote.dto.AssignmentResponse;
import id.go.bpsfasih.data.remote.dto.DataAssignmentResponse;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.utils.sync.reqDownload.RDAssignment;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RDAssignment.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001cB0\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\u0002\u0010\u000bJ!\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00032\b\u0010\u0019\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\nH\u0002R,\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignment;", "", "periodeId", "", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignment$DownloadResult;", "Lkotlin/ParameterName;", "name", "result", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getCallback", "()Lkotlin/jvm/functions/Function1;", "page", "", "Ljava/lang/Integer;", "getPeriodeId", "()Ljava/lang/String;", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlin/collections/ArrayList;", "total", "formatErrorMessage", "message", "errorCode", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "request", "DownloadResult", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDAssignment {
    public static final int $stable = 8;
    private final Function1<DownloadResult, Unit> callback;
    private Integer page;
    private final String periodeId;
    private ArrayList<AssignmentEntity> result;
    private Integer total;

    /* JADX WARN: Multi-variable type inference failed */
    public RDAssignment(String periodeId, Function1<? super DownloadResult, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.periodeId = periodeId;
        this.callback = callback;
        this.result = new ArrayList<>();
        this.page = 0;
        this.total = 0;
        request();
    }

    public final String getPeriodeId() {
        return this.periodeId;
    }

    public final Function1<DownloadResult, Unit> getCallback() {
        return this.callback;
    }

    /* compiled from: RDAssignment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0006HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAssignment$DownloadResult;", "", "assignments", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "errorMessage", "", "(Ljava/util/List;Ljava/lang/String;)V", "getAssignments", "()Ljava/util/List;", "getErrorMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final /* data */ class DownloadResult {
        public static final int $stable = 8;
        private final List<AssignmentEntity> assignments;
        private final String errorMessage;

        /* JADX WARN: Multi-variable type inference failed */
        public DownloadResult() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DownloadResult copy$default(DownloadResult downloadResult, List list, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                list = downloadResult.assignments;
            }
            if ((i & 2) != 0) {
                str = downloadResult.errorMessage;
            }
            return downloadResult.copy(list, str);
        }

        public final List<AssignmentEntity> component1() {
            return this.assignments;
        }

        /* renamed from: component2, reason: from getter */
        public final String getErrorMessage() {
            return this.errorMessage;
        }

        public final DownloadResult copy(List<AssignmentEntity> assignments, String errorMessage) {
            return new DownloadResult(assignments, errorMessage);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DownloadResult)) {
                return false;
            }
            DownloadResult downloadResult = (DownloadResult) other;
            return Intrinsics.areEqual(this.assignments, downloadResult.assignments) && Intrinsics.areEqual(this.errorMessage, downloadResult.errorMessage);
        }

        public int hashCode() {
            List<AssignmentEntity> list = this.assignments;
            int iHashCode = (list == null ? 0 : list.hashCode()) * 31;
            String str = this.errorMessage;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "DownloadResult(assignments=" + this.assignments + ", errorMessage=" + this.errorMessage + ")";
        }

        public DownloadResult(List<AssignmentEntity> list, String str) {
            this.assignments = list;
            this.errorMessage = str;
        }

        public /* synthetic */ DownloadResult(List list, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : list, (i & 2) != 0 ? null : str);
        }

        public final List<AssignmentEntity> getAssignments() {
            return this.assignments;
        }

        public final String getErrorMessage() {
            return this.errorMessage;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatErrorMessage(String message, Integer errorCode) {
        if (message == null) {
            message = "Terdapat kesalahan ketika mendownload daftar assignment (1)";
        }
        return (StringsKt.contains((CharSequence) message, (CharSequence) "Error code :", true) || errorCode == null) ? message : message + " (Error code : " + errorCode + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void request() {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                RDAssignment.request$lambda$0(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void request$lambda$0(final RDAssignment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AssignmentRepositoryImpl assignmentRepositoryImpl = new AssignmentRepositoryImpl();
        String str = this$0.periodeId;
        Integer num = this$0.page;
        Intrinsics.checkNotNull(num);
        assignmentRepositoryImpl.getAssignment(str, num.intValue(), new Function1<AssignmentResponse, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAssignment$request$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentResponse assignmentResponse) {
                invoke2(assignmentResponse);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentResponse assignmentResponse) {
                String str2 = null;
                DataAssignmentResponse data = assignmentResponse != null ? assignmentResponse.getData() : null;
                int i = 1;
                if (!(assignmentResponse != null ? Intrinsics.areEqual((Object) assignmentResponse.getSuccess(), (Object) false) : false) && data != null) {
                    this.this$0.total = Integer.valueOf(data.getTotal());
                    this.this$0.result.addAll(data.getContent());
                    Integer num2 = this.this$0.total;
                    Intrinsics.checkNotNull(num2);
                    if (num2.intValue() > this.this$0.result.size()) {
                        RDAssignment rDAssignment = this.this$0;
                        Integer num3 = rDAssignment.page;
                        Intrinsics.checkNotNull(num3);
                        rDAssignment.page = Integer.valueOf(num3.intValue() + 1);
                        this.this$0.request();
                        return;
                    }
                    this.this$0.getCallback().invoke(new RDAssignment.DownloadResult(this.this$0.result, str2, 2, null == true ? 1 : 0));
                    return;
                }
                this.this$0.getCallback().invoke(new RDAssignment.DownloadResult(null == true ? 1 : 0, this.this$0.formatErrorMessage(assignmentResponse != null ? assignmentResponse.getMessage() : null, assignmentResponse != null ? assignmentResponse.getErrorCode() : null), i, null == true ? 1 : 0));
            }
        });
    }
}
