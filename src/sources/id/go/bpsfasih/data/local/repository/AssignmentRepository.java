package id.go.bpsfasih.data.local.repository;

import android.os.AsyncTask;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SimpleSQLiteQuery;
import com.google.firebase.analytics.FirebaseAnalytics;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.dao.AssigmentDAO;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.entities.AssignmentSampling;
import id.go.bpsfasih.data.local.models.AssignmentReturnData;
import id.go.bpsfasih.data.local.pojo.AssignmentWilayahPojo;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.http.cookie.ClientCookie;

/* compiled from: AssigmentRepository.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u000e\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018J\u0014\u0010\u0019\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018J\u0014\u0010\u001a\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018J\u001c\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018J\u001b\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u0004\u0018\u00010\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0019\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ'\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010%J\u001f\u0010&\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u001c\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ'\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00072\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*J'\u0010+\u001a\u0004\u0018\u00010\b2\b\u0010,\u001a\u0004\u0018\u00010\u00162\b\u0010\u001c\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*J\u001b\u0010-\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u000e\u0010.\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0016J\u001e\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010)\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0007J2\u00100\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010)\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u00101\u001a\u00020\u00162\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007J\u0019\u00103\u001a\u0002042\u0006\u0010\u001c\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ%\u00105\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010%J\f\u00107\u001a\b\u0012\u0004\u0012\u0002040\u0006J#\u00108\u001a\u0004\u0018\u00010\b2\u0006\u0010)\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010*J$\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00072\u0006\u0010)\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u0016J\"\u0010<\u001a\u00020\u00142\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u0018J\u000e\u0010>\u001a\u00020\u00142\u0006\u0010=\u001a\u00020\bJ\u000e\u0010?\u001a\u00020\u00142\u0006\u0010=\u001a\u00020\bJ\u001a\u0010@\u001a\u00020A2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00162\b\u0010)\u001a\u0004\u0018\u00010\u0016J\u001b\u0010B\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u000e\u0010C\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\bJ\u0010\u0010D\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010EJ\u0016\u0010F\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u0016J\u000e\u0010H\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u0010I\u001a\u00020\u00142\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160\u0007J\u000e\u0010J\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J'\u0010K\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00162\b\u0010L\u001a\u0004\u0018\u00010M2\b\u0010N\u001a\u0004\u0018\u00010M¢\u0006\u0002\u0010OJ\u0016\u0010P\u001a\u00020\u00142\u0006\u0010Q\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010R\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010S\u001a\u00020\u0016J\u0018\u0010T\u001a\u00020\u00142\u0006\u0010U\u001a\u0002042\u0006\u0010V\u001a\u00020\u0016H\u0002J\u0010\u0010W\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010X\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010Y\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016J\u0010\u0010Z\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R&\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006["}, d2 = {"Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "", "assignmentDAO", "Lid/go/bpsfasih/data/local/dao/AssigmentDAO;", "(Lid/go/bpsfasih/data/local/dao/AssigmentDAO;)V", "assignment", "Landroidx/lifecycle/LiveData;", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "getAssignment", "()Landroidx/lifecycle/LiveData;", "setAssignment", "(Landroidx/lifecycle/LiveData;)V", "assignmentSubmitVersionRepository", "Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "assignmentWilayahPojo", "Lid/go/bpsfasih/data/local/pojo/AssignmentWilayahPojo;", "getAssignmentWilayahPojo", "setAssignmentWilayahPojo", "delete", "", "assignmentId", "", "callback", "Lkotlin/Function0;", "deleteAllNew", "deleteAllOld", "deleteByPeriode", "periodeId", "getAssignmentById", "Lid/go/bpsfasih/data/local/pojo/Sync;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssignmentByIdOne", "getAssignmentByIdPrimary", "assignmentIdPrimary", "getAssignmentByIdPrimarys", "assignmentIds", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssignmentByPeriodeId", "getAssignmentByPeriodeIdAndRegionId", "Lid/go/bpsfasih/data/local/entities/AssignmentSampling;", "regionId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAssignmentCopyFromByPeriodeId", "blokSensusId", "getAssignmentOnly", "getAssignmentWilayahPojoByPeriode", "getAssignmentsByPeriodeRegionId", "getAssignmentsSortFilter", "sorting", "filter", "getCountAllAssignmentByPeriodeId", "", "getDetailAssignmentById", "listId", "getJumlahAssignmentLiveData", "getRandomAssignmentPrelist", "getRekapWilayahAssignment", "Lid/go/bpsfasih/domain/models/RekapWilayah;", FirebaseAnalytics.Param.INDEX, "insertAllData", "data", "insertData", "insertDataWithoutReplace", "isAssignmentDone", "", "rollbackPending", "updateAssignment", "updateAssignmentAfterUpload", "Lid/go/bpsfasih/data/local/models/AssignmentReturnData;", "updateComment", ClientCookie.COMMENT_ATTR, "updateDataDownloadedAt", "updateDataDownloadedAtByIds", "updateIsEncrypt", "updateLatLong", FormGearActivity.LAT, "", FormGearActivity.LON, "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;)V", "updateNote", FormGearJavascriptInterface.NOTE_FILE, "updateParadata", "paradata", "updateStatusAssignment", NotificationCompat.CATEGORY_STATUS, "AssignmentId", "updateStatusAssignmentSubmit", "updateStatusAssignmentToApproved", "updateStatusAssignmentToPending", "updateStatusAssignmentToRejected", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentRepository {
    public static final int $stable = 8;
    private LiveData<List<AssignmentEntity>> assignment;
    private final AssigmentDAO assignmentDAO;
    private final AssignmentSubmitVersionRepository assignmentSubmitVersionRepository;
    private LiveData<List<AssignmentWilayahPojo>> assignmentWilayahPojo;

    public AssignmentRepository(AssigmentDAO assignmentDAO) {
        Intrinsics.checkNotNullParameter(assignmentDAO, "assignmentDAO");
        this.assignmentDAO = assignmentDAO;
        this.assignmentSubmitVersionRepository = new AssignmentSubmitVersionRepository(FasihApp.INSTANCE.getAssignmentSubmitVersionDAO());
        this.assignment = assignmentDAO.getAllAssigment(FasihApp.INSTANCE.getSession().getUserId());
        this.assignmentWilayahPojo = assignmentDAO.getAssignmentWilayahPojoByPeriode("", "");
    }

    public final LiveData<List<AssignmentEntity>> getAssignment() {
        return this.assignment;
    }

    public final void setAssignment(LiveData<List<AssignmentEntity>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.assignment = liveData;
    }

    public final LiveData<List<AssignmentWilayahPojo>> getAssignmentWilayahPojo() {
        return this.assignmentWilayahPojo;
    }

    public final void setAssignmentWilayahPojo(LiveData<List<AssignmentWilayahPojo>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.assignmentWilayahPojo = liveData;
    }

    public final LiveData<Integer> getJumlahAssignmentLiveData() {
        return this.assignmentDAO.getJumlahAssignmentLiveData(FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void getAssignmentWilayahPojoByPeriode(String periodeId) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        this.assignmentWilayahPojo = this.assignmentDAO.getAssignmentWilayahPojoByPeriode(periodeId, FasihApp.INSTANCE.getSession().getUserId());
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getDetailAssignmentById$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getDetailAssignmentById$2, reason: invalid class name and case insensitive filesystem */
    static final class C07262 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentEntity>>, Object> {
        final /* synthetic */ List<String> $listId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07262(String str, List<String> list, Continuation<? super C07262> continuation) {
            super(2, continuation);
            this.$userId = str;
            this.$listId = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07262(this.$userId, this.$listId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentEntity>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<AssignmentEntity>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentEntity>> continuation) {
            return ((C07262) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRepository.this.assignmentDAO.getDetailAssignmentById(this.$userId, this.$listId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getDetailAssignmentById(List<String> list, Continuation<? super List<AssignmentEntity>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07262(FasihApp.INSTANCE.getSession().getUserId(), list, null), continuation);
    }

    public final void insertAllData(final List<AssignmentEntity> data, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.insertAllData$lambda$0(this.f$0, data, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertAllData$lambda$0(AssignmentRepository this$0, List data, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentDAO.insertAll(data);
        callback.invoke();
    }

    public final void insertData(final AssignmentEntity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.insertData$lambda$1(this.f$0, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertData$lambda$1(AssignmentRepository this$0, AssignmentEntity data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        this$0.assignmentDAO.insert(data);
    }

    public final void insertDataWithoutReplace(final AssignmentEntity data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.insertDataWithoutReplace$lambda$2(this.f$0, data);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insertDataWithoutReplace$lambda$2(AssignmentRepository this$0, AssignmentEntity data) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        this$0.assignmentDAO.insertWithOutReplace(data);
    }

    public final void updateStatusAssignmentToPending(final String assignmentId) {
        if (assignmentId != null) {
            AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentRepository.updateStatusAssignmentToPending$lambda$3(this.f$0, assignmentId);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateStatusAssignmentToPending$lambda$3(AssignmentRepository this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.assignmentDAO.updateAssignmentToPending(str, FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void updateDataDownloadedAt(final String assignmentId) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateDataDownloadedAt$lambda$4(this.f$0, assignmentId);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDataDownloadedAt$lambda$4(AssignmentRepository this$0, String assignmentId) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentId, "$assignmentId");
        this$0.assignmentDAO.updateDataDownloadedAt(assignmentId, FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void updateDataDownloadedAtByIds(final List<String> assignmentIds) {
        Intrinsics.checkNotNullParameter(assignmentIds, "assignmentIds");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateDataDownloadedAtByIds$lambda$5(this.f$0, assignmentIds);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateDataDownloadedAtByIds$lambda$5(AssignmentRepository this$0, List assignmentIds) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentIds, "$assignmentIds");
        this$0.assignmentDAO.updateDataDownloadedAtByIds(assignmentIds, FasihApp.INSTANCE.getSession().getUserId());
    }

    public final void updateAssignment(AssignmentEntity assignment) {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        this.assignmentDAO.updateItem(assignment);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getRandomAssignmentPrelist$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getRandomAssignmentPrelist$2, reason: invalid class name and case insensitive filesystem */
    static final class C07272 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $regionId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07272(String str, String str2, Continuation<? super C07272> continuation) {
            super(2, continuation);
            this.$regionId = str;
            this.$periodeId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07272(this.$regionId, this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
            return ((C07272) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return AssignmentRepository.this.assignmentDAO.getRandomAssignmentPrelist(FasihApp.INSTANCE.getSession().getUserId(), this.$regionId, this.$periodeId);
        }
    }

    public final Object getRandomAssignmentPrelist(String str, String str2, Continuation<? super AssignmentEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07272(str, str2, null), continuation);
    }

    public final void deleteAllNew(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.deleteAllNew$lambda$6(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAllNew$lambda$6(AssignmentRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentDAO.deleteAllNew(FasihApp.INSTANCE.getSession().getUserId(), CommonCons.INSTANCE.getASSIGNMENT_STATUS_OPEN());
        callback.invoke();
    }

    public final void deleteAllOld(final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.deleteAllOld$lambda$7(this.f$0, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteAllOld$lambda$7(AssignmentRepository this$0, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentDAO.deleteAllOld(FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    public final void deleteByPeriode(final String periodeId, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.deleteByPeriode$lambda$8(this.f$0, periodeId, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void deleteByPeriode$lambda$8(AssignmentRepository this$0, String periodeId, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(periodeId, "$periodeId");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        String userId = FasihApp.INSTANCE.getSession().getUserId();
        this$0.assignmentDAO.deleteByPeriode(userId, periodeId + InternalZipConstants.ZIP_FILE_SEPARATOR + userId, CommonCons.INSTANCE.getASSIGNMENT_STATUS_OPEN());
        callback.invoke();
    }

    public final void delete(final String assignmentId, final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.delete$lambda$9(this.f$0, assignmentId, callback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void delete$lambda$9(AssignmentRepository this$0, String str, Function0 callback) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(callback, "$callback");
        this$0.assignmentDAO.delete(str, FasihApp.INSTANCE.getSession().getUserId());
        callback.invoke();
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/pojo/Sync;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentById$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentById$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Sync>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;
        final /* synthetic */ AssignmentRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(String str, AssignmentRepository assignmentRepository, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.this$0 = assignmentRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$assignmentId, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Sync> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.$assignmentId != null ? this.this$0.assignmentDAO.getAssignmentById(this.$assignmentId) : this.this$0.assignmentDAO.getAssignmentById("1");
        }
    }

    public final Object getAssignmentById(String str, Continuation<? super Sync> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(str, this, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdPrimarys$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdPrimarys$2, reason: invalid class name and case insensitive filesystem */
    static final class C07202 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentEntity>>, Object> {
        final /* synthetic */ List<String> $assignmentIds;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07202(List<String> list, Continuation<? super C07202> continuation) {
            super(2, continuation);
            this.$assignmentIds = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07202(this.$assignmentIds, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentEntity>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<AssignmentEntity>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentEntity>> continuation) {
            return ((C07202) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRepository.this.assignmentDAO.getAssignmentByIdPrimarys(this.$assignmentIds);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssignmentByIdPrimarys(List<String> list, Continuation<? super List<AssignmentEntity>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07202(list, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/pojo/Sync;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdPrimary$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdPrimary$2, reason: invalid class name and case insensitive filesystem */
    static final class C07192 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Sync>, Object> {
        final /* synthetic */ String $assignmentIdPrimary;
        int label;
        final /* synthetic */ AssignmentRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07192(String str, AssignmentRepository assignmentRepository, Continuation<? super C07192> continuation) {
            super(2, continuation);
            this.$assignmentIdPrimary = str;
            this.this$0 = assignmentRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C07192(this.$assignmentIdPrimary, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Sync> continuation) {
            return ((C07192) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.$assignmentIdPrimary != null ? this.this$0.assignmentDAO.getAssignmentByIdPrimary(this.$assignmentIdPrimary) : this.this$0.assignmentDAO.getAssignmentByIdPrimary("1");
        }
    }

    public final Object getAssignmentByIdPrimary(String str, Continuation<? super Sync> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07192(str, this, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdOne$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByIdOne$2, reason: invalid class name and case insensitive filesystem */
    static final class C07182 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07182(String str, Continuation<? super C07182> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07182(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
            return ((C07182) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                AssigmentDAO assigmentDAO = AssignmentRepository.this.assignmentDAO;
                String str = this.$assignmentId;
                Intrinsics.checkNotNull(str);
                return assigmentDAO.getAssignmentByIdOne(str);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssignmentByIdOne(String str, Continuation<? super AssignmentEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07182(str, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentOnly$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentOnly$2, reason: invalid class name and case insensitive filesystem */
    static final class C07242 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
        final /* synthetic */ String $assignmentId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07242(String str, Continuation<? super C07242> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07242(this.$assignmentId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
            return ((C07242) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return AssignmentRepository.this.assignmentDAO.getAssignmentOnlyById(this.$assignmentId, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getAssignmentOnly(String str, Continuation<? super AssignmentEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07242(str, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentCopyFromByPeriodeId$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentCopyFromByPeriodeId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07232 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AssignmentEntity>, Object> {
        final /* synthetic */ String $blokSensusId;
        final /* synthetic */ String $periodeId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07232(String str, String str2, Continuation<? super C07232> continuation) {
            super(2, continuation);
            this.$blokSensusId = str;
            this.$periodeId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07232(this.$blokSensusId, this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super AssignmentEntity> continuation) {
            return ((C07232) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return AssignmentRepository.this.assignmentDAO.getAssignmentCopyFromByPeriodeId(this.$blokSensusId, this.$periodeId, FasihApp.INSTANCE.getSession().getUserId());
        }
    }

    public final Object getAssignmentCopyFromByPeriodeId(String str, String str2, Continuation<? super AssignmentEntity> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07232(str, str2, null), continuation);
    }

    public final List<AssignmentEntity> getAssignmentsByPeriodeRegionId(String regionId, String periodeId) {
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        return this.assignmentDAO.getAssignments(new SimpleSQLiteQuery("SELECT * from data_assignment_entity LEFT JOIN (select survey_time_id, user_id, survey_time_assignment_id from data_survey_time)  ON survey_time_assignment_id = data_assignment_entity.assignment_id WHERE periodeNotPrimary= '" + periodeId + "' AND region_id = '" + regionId + "' AND userIdAssignment = '" + FasihApp.INSTANCE.getSession().getUserId() + "' "));
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public final List<AssignmentEntity> getAssignmentsSortFilter(String regionId, String periodeId, String sorting, List<String> filter) {
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(sorting, "sorting");
        Intrinsics.checkNotNullParameter(filter, "filter");
        String userId = FasihApp.INSTANCE.getSession().getUserId();
        String str = "";
        for (String str2 : filter) {
            int iHashCode = str2.hashCode();
            if (iHashCode != 1823) {
                if (iHashCode != 1824) {
                    switch (iHashCode) {
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE /* 48 */:
                            if (str2.equals("0")) {
                                if (!str.equals("")) {
                                    str = ((Object) str) + " OR ";
                                }
                                str = ((Object) str) + " assignmentStatusId = '0' ";
                                break;
                            } else {
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX /* 49 */:
                            if (str2.equals("1")) {
                                if (!str.equals("")) {
                                    str = ((Object) str) + " OR ";
                                }
                                str = ((Object) str) + " assignmentStatusId = '1' ";
                                break;
                            } else {
                                break;
                            }
                        case 50:
                            if (str2.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                if (!str.equals("")) {
                                    str = ((Object) str) + " OR ";
                                }
                                str = ((Object) str) + " assignmentStatusId = '2' ";
                                break;
                            } else {
                                break;
                            }
                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG /* 51 */:
                            if (str2.equals(ExifInterface.GPS_MEASUREMENT_3D)) {
                                if (!str.equals("")) {
                                    str = ((Object) str) + " OR ";
                                }
                                str = ((Object) str) + " assignmentStatusId = '3' ";
                                break;
                            } else {
                                break;
                            }
                    }
                } else if (str2.equals("99")) {
                    if (!str.equals("")) {
                        str = ((Object) str) + " OR ";
                    }
                    str = ((Object) str) + " pendingStatus = '1' ";
                }
            } else if (str2.equals("98")) {
                if (!str.equals("")) {
                    str = ((Object) str) + " OR ";
                }
                str = ((Object) str) + " assignmentStatusId = '0' AND paradata <> ''";
            }
        }
        String str3 = "SELECT * from data_assignment_entity LEFT JOIN (select survey_time_id, user_id, survey_time_assignment_id from data_survey_time)  ON survey_time_assignment_id = data_assignment_entity.assignment_id WHERE periodeNotPrimary= '" + periodeId + "' AND region_id = '" + regionId + "' AND userIdAssignment = '" + userId + "'";
        String str4 = str;
        if (!(str4 == null || str4.length() == 0)) {
            str3 = str3 + " AND (" + ((Object) str) + ")";
        }
        return this.assignmentDAO.getAssignments(new SimpleSQLiteQuery(str3 + sorting));
    }

    public final boolean isAssignmentDone(String periodeId, String regionId) {
        List<Boolean> listIsRegionDone = this.assignmentDAO.isRegionDone(periodeId, regionId, FasihApp.INSTANCE.getSession().getUserId());
        boolean z = false;
        if (listIsRegionDone != null) {
            Iterator<T> it = listIsRegionDone.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(it.next(), (Object) true)) {
                    z = true;
                }
            }
        }
        return z;
    }

    public final void updateStatusAssignmentToApproved(String assignmentId) {
        if (assignmentId != null) {
            updateStatusAssignment(CommonCons.INSTANCE.getASSIGNMENT_STATUS_APPROVED(), assignmentId);
        }
    }

    public final void updateStatusAssignmentSubmit(String assignmentId) {
        if (assignmentId != null) {
            updateStatusAssignment(CommonCons.INSTANCE.getASSIGNMENT_STATUS_SUBMITED(), assignmentId);
        }
    }

    public final void updateStatusAssignmentToRejected(String assignmentId) {
        if (assignmentId != null) {
            updateStatusAssignment(CommonCons.INSTANCE.getASSIGNMENT_STATUS_REJECTED(), assignmentId);
        }
    }

    private final void updateStatusAssignment(final int status, final String AssignmentId) {
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateStatusAssignment$lambda$15(this.f$0, AssignmentId, status);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateStatusAssignment$lambda$15(AssignmentRepository this$0, String AssignmentId, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(AssignmentId, "$AssignmentId");
        this$0.assignmentDAO.updateStatusAssignment(AssignmentId, i, FasihApp.INSTANCE.getSession().getUserId());
    }

    public final Object rollbackPending(String str, Continuation<? super Unit> continuation) {
        String userId = FasihApp.INSTANCE.getSession().getUserId();
        AssigmentDAO assigmentDAO = this.assignmentDAO;
        Intrinsics.checkNotNull(str);
        assigmentDAO.rollbackPending(userId, str);
        return Unit.INSTANCE;
    }

    public final void updateParadata(final String assignmentIdPrimary, final String paradata) {
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "assignmentIdPrimary");
        Intrinsics.checkNotNullParameter(paradata, "paradata");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateParadata$lambda$16(this.f$0, assignmentIdPrimary, paradata);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateParadata$lambda$16(AssignmentRepository this$0, String assignmentIdPrimary, String paradata) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "$assignmentIdPrimary");
        Intrinsics.checkNotNullParameter(paradata, "$paradata");
        this$0.assignmentDAO.updateParadata(assignmentIdPrimary, paradata);
    }

    public final void updateComment(final String assignmentIdPrimary, final String comment) {
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "assignmentIdPrimary");
        Intrinsics.checkNotNullParameter(comment, "comment");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateComment$lambda$17(this.f$0, assignmentIdPrimary, comment);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateComment$lambda$17(AssignmentRepository this$0, String assignmentIdPrimary, String comment) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "$assignmentIdPrimary");
        Intrinsics.checkNotNullParameter(comment, "$comment");
        this$0.assignmentDAO.updateComment(assignmentIdPrimary, comment);
    }

    public final void updateLatLong(final String assignmentIdPrimary, final Double lat, final Double d) {
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "assignmentIdPrimary");
        AsyncTask.execute(new Runnable() { // from class: id.go.bpsfasih.data.local.repository.AssignmentRepository$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                AssignmentRepository.updateLatLong$lambda$18(this.f$0, assignmentIdPrimary, lat, d);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateLatLong$lambda$18(AssignmentRepository this$0, String assignmentIdPrimary, Double d, Double d2) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(assignmentIdPrimary, "$assignmentIdPrimary");
        this$0.assignmentDAO.updateLatLong(assignmentIdPrimary, d, d2);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getCountAllAssignmentByPeriodeId$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getCountAllAssignmentByPeriodeId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07252 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07252(String str, String str2, Continuation<? super C07252> continuation) {
            super(2, continuation);
            this.$userId = str;
            this.$periodeId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07252(this.$userId, this.$periodeId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
            return ((C07252) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return Boxing.boxInt(AssignmentRepository.this.assignmentDAO.getCountAllAssignmentByPeriodeId(this.$userId, this.$periodeId));
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getCountAllAssignmentByPeriodeId(String str, Continuation<? super Integer> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07252(FasihApp.INSTANCE.getSession().getUserId(), str, null), continuation);
    }

    public final void updateAssignmentAfterUpload(AssignmentReturnData assignment) {
        String id2;
        this.assignmentDAO.updateAssignmentAfterUpload(assignment != null ? assignment.getAssignmentStatusId() : null, assignment != null ? assignment.getAssignmentStatusAlias() : null, assignment != null ? assignment.getAssignmentResponsibility() : null, assignment != null ? assignment.getCurrentUserId() : null, assignment != null ? assignment.getCurrentUserUsername() : null, assignment != null ? assignment.getCurrentUserFullname() : null, assignment != null ? assignment.getCurrentUserSurveyRoleId() : null, assignment != null ? assignment.getCurrentUserSurveyRoleName() : null, assignment != null ? assignment.getCurrentUserSurveyRoleIsPencacah() : null, assignment != null ? assignment.getCurrentUserSurveyRoleCanPullSample() : null, assignment != null ? assignment.getMode() : null, assignment != null ? assignment.getBasePath() : null, assignment != null ? assignment.getSubmitVersionCode() : 0, (assignment != null ? assignment.getId() : null) + InternalZipConstants.ZIP_FILE_SEPARATOR + FasihApp.INSTANCE.getSession().getUserId());
        if (assignment == null || (id2 = assignment.getId()) == null) {
            return;
        }
        this.assignmentSubmitVersionRepository.upsertSync(id2, assignment.getSubmitVersionCode());
    }

    public final void updateNote(String note, String assignmentId) {
        Intrinsics.checkNotNullParameter(note, "note");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.assignmentDAO.updateNote(note, assignmentId);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentSampling;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByPeriodeIdAndRegionId$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByPeriodeIdAndRegionId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07222 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentSampling>>, Object> {
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $regionId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07222(String str, String str2, String str3, Continuation<? super C07222> continuation) {
            super(2, continuation);
            this.$periodeId = str;
            this.$userId = str2;
            this.$regionId = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07222(this.$periodeId, this.$userId, this.$regionId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentSampling>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<AssignmentSampling>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentSampling>> continuation) {
            return ((C07222) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRepository.this.assignmentDAO.getAssignmentIdByPeriodeIdAndRegionId(this.$periodeId, this.$userId, this.$regionId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssignmentByPeriodeIdAndRegionId(String str, String str2, Continuation<? super List<AssignmentSampling>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07222(str, FasihApp.INSTANCE.getSession().getUserId(), str2, null), continuation);
    }

    /* compiled from: AssigmentRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByPeriodeId$2", f = "AssigmentRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.data.local.repository.AssignmentRepository$getAssignmentByPeriodeId$2, reason: invalid class name and case insensitive filesystem */
    static final class C07212 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends AssignmentEntity>>, Object> {
        final /* synthetic */ String $periodeId;
        final /* synthetic */ String $userId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C07212(String str, String str2, Continuation<? super C07212> continuation) {
            super(2, continuation);
            this.$periodeId = str;
            this.$userId = str2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AssignmentRepository.this.new C07212(this.$periodeId, this.$userId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends AssignmentEntity>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<AssignmentEntity>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<AssignmentEntity>> continuation) {
            return ((C07212) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return AssignmentRepository.this.assignmentDAO.getAssignmentByPeriodeId(this.$periodeId + InternalZipConstants.ZIP_FILE_SEPARATOR + this.$userId, this.$userId);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object getAssignmentByPeriodeId(String str, Continuation<? super List<AssignmentEntity>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new C07212(str, FasihApp.INSTANCE.getSession().getUserId(), null), continuation);
    }

    public final void updateIsEncrypt(String assignmentId) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        this.assignmentDAO.updateIsEncrypt(assignmentId);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<id.go.bpsfasih.domain.models.RekapWilayah> getRekapWilayahAssignment(java.lang.String r5, java.lang.String r6, java.lang.String r7) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.local.repository.AssignmentRepository.getRekapWilayahAssignment(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }
}
