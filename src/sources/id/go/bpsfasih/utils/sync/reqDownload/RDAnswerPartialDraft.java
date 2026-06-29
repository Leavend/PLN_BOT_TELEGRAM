package id.go.bpsfasih.utils.sync.reqDownload;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.models.S3Entity;
import id.go.bpsfasih.data.local.models.S3Response;
import id.go.bpsfasih.data.local.pojo.Sync;
import id.go.bpsfasih.data.local.repository.AssignmentRepository;
import id.go.bpsfasih.data.local.repository.AssignmentSubmitVersionRepository;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.domain.models.AnswerBeforeSaveModel;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.tools.Downloader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: RDAnswerPartialDraft.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\\\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012M\u0010\u0004\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0005¢\u0006\u0002\u0010\u000fJa\u0010\"\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010$2M\u0010\u0004\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0005H\u0002J\"\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\b\b\u0002\u0010(\u001a\u00020\nH\u0002J6\u0010%\u001a\u00020\u000e2\u0016\u0010)\u001a\u0012\u0012\u0004\u0012\u00020*0\u001aj\b\u0012\u0004\u0012\u00020*`\u001c2\u0006\u0010+\u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000e0,H\u0002J\u0018\u0010-\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u0018H\u0002J\u0016\u0010/\u001a\u00020\u000e2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000601H\u0002J\u001e\u00102\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u000e0,H\u0002J\b\u00103\u001a\u00020\u000eH\u0002J\b\u00104\u001a\u00020\u000eH\u0002J\b\u00105\u001a\u00020\u000eH\u0002R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000RX\u0010\u0004\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0019\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001aj\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b`\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00066"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAnswerPartialDraft;", "", "unit", "Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "callback", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "progress", "", "(Lid/go/bpsfasih/domain/models/SyncAnswerPartial;Lkotlin/jvm/functions/Function3;)V", "answerPathDownload", "assignmentSubmitVersionRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentSubmitVersionRepository;", "assignmentaRepo", "Lid/go/bpsfasih/data/local/repository/AssignmentRepository;", "getCallback", "()Lkotlin/jvm/functions/Function3;", "countS3", "", "listS3", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/data/local/models/S3Entity;", "Lkotlin/collections/ArrayList;", "paramsAssignmentId", "paramsPeriode", "sumSuccess", "getUnit", "()Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "extract", "periodAnswer", "Lid/go/bpsfasih/domain/models/AnswerBeforeSaveModel;", "extractAnswer", "assignmentId", "pathInput", "isSingleDownload", "filesZip", "Ljava/io/File;", "tempDirAnswer", "Lkotlin/Function0;", "persistSubmitVersion", "submitVersionCode", "persistSubmitVersions", "assignmentIds", "", "processExtract", "requestAnswerNfs", "requestAnswerS3", "unduhAnswer", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDAnswerPartialDraft {
    public static final int $stable = 8;
    private String answerPathDownload;
    private final AssignmentSubmitVersionRepository assignmentSubmitVersionRepo;
    private final AssignmentRepository assignmentaRepo;
    private final Function3<String, Boolean, Double, Unit> callback;
    private int countS3;
    private ArrayList<S3Entity> listS3;
    private String paramsAssignmentId;
    private String paramsPeriode;
    private double sumSuccess;
    private final SyncAnswerPartial unit;

    /* JADX WARN: Multi-variable type inference failed */
    public RDAnswerPartialDraft(SyncAnswerPartial unit, Function3<? super String, ? super Boolean, ? super Double, Unit> callback) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.unit = unit;
        this.callback = callback;
        this.assignmentaRepo = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
        this.assignmentSubmitVersionRepo = DataSurvey.AssignmentSubmitVersion.INSTANCE.getAssignmentSubmitVersionRepository();
        this.paramsPeriode = "surveyPeriodeId=";
        this.paramsAssignmentId = "assignmentIds=";
        this.answerPathDownload = new Config().BASE_URL() + "/mobile/assignment-sync/api/mobile/assignment/partial/download-uploaded-files?";
        this.listS3 = new ArrayList<>();
        if (unit.getListAssignmentIdNfs() != null) {
            requestAnswerNfs();
        } else {
            requestAnswerS3();
        }
    }

    public final SyncAnswerPartial getUnit() {
        return this.unit;
    }

    public final Function3<String, Boolean, Double, Unit> getCallback() {
        return this.callback;
    }

    private final void requestAnswerNfs() {
        final String userId = FasihApp.INSTANCE.getSession().getUserId();
        final String idPeriode = this.unit.getIdPeriode();
        final String idSurvey = this.unit.getIdSurvey();
        List<String> listAssignmentIdNfs = this.unit.getListAssignmentIdNfs();
        Intrinsics.checkNotNull(listAssignmentIdNfs);
        int size = listAssignmentIdNfs.size();
        String str = "";
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                str = str + ", ";
            }
            List<String> listAssignmentIdNfs2 = this.unit.getListAssignmentIdNfs();
            Intrinsics.checkNotNull(listAssignmentIdNfs2);
            str = str + ((Object) listAssignmentIdNfs2.get(i));
        }
        String str2 = this.answerPathDownload + this.paramsPeriode + idPeriode + "&" + this.paramsAssignmentId + str;
        final String str3 = "answer_" + idPeriode + "_" + this.unit.getFilenameNfs() + "_" + CommonCons.INSTANCE.getEXTENSION_ZIP();
        final String strPathTempAnswer = FileHelper.INSTANCE.pathTempAnswer(idSurvey, idPeriode);
        Downloader.INSTANCE.requestAnswer(str2, strPathTempAnswer, str3, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.requestAnswerNfs.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str4, Boolean bool) {
                invoke(str4, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str4, boolean z) {
                if (!z) {
                    RDAnswerPartialDraft rDAnswerPartialDraft = RDAnswerPartialDraft.this;
                    AnswerBeforeSaveModel answerBeforeSaveModel = new AnswerBeforeSaveModel(strPathTempAnswer, str3, idPeriode, idSurvey, userId);
                    final RDAnswerPartialDraft rDAnswerPartialDraft2 = RDAnswerPartialDraft.this;
                    rDAnswerPartialDraft.extract(answerBeforeSaveModel, new Function3<String, Boolean, Double, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.requestAnswerNfs.1.1
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(String str5, Boolean bool, Double d) {
                            invoke(str5, bool.booleanValue(), d.doubleValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str5, boolean z2, double d) {
                            rDAnswerPartialDraft2.getCallback().invoke(str5, Boolean.valueOf(z2), Double.valueOf(d));
                        }
                    });
                    return;
                }
                RDAnswerPartialDraft.this.getCallback().invoke(str4, true, Double.valueOf(-100.0d));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void extract(AnswerBeforeSaveModel periodAnswer, final Function3<? super String, ? super Boolean, ? super Double, Unit> callback) {
        if (periodAnswer == null || periodAnswer.getPath() == null || periodAnswer.getFilename() == null) {
            return;
        }
        FileHelper.Companion companion = FileHelper.INSTANCE;
        String surveyId = periodAnswer.getSurveyId();
        if (surveyId == null) {
            surveyId = "";
        }
        String periodeId = periodAnswer.getPeriodeId();
        if (periodeId == null) {
            periodeId = "";
        }
        String strPathTempAnswer = companion.pathTempAnswer(surveyId, periodeId);
        FileHelper.Companion companion2 = FileHelper.INSTANCE;
        String surveyId2 = periodAnswer.getSurveyId();
        if (surveyId2 == null) {
            surveyId2 = "";
        }
        String periodeId2 = periodAnswer.getPeriodeId();
        if (new File(companion2.pathTempAnswerZip(surveyId2, periodeId2 != null ? periodeId2 : "", periodAnswer.getFilename())).exists()) {
            boolean zUnZip$default = ZipHelper.Companion.unZip$default(ZipHelper.INSTANCE, strPathTempAnswer, periodAnswer.getFilename(), null, 4, null);
            Log.d("unzip_answer", String.valueOf(zUnZip$default));
            if (zUnZip$default) {
                processExtract(strPathTempAnswer, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$extract$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() throws InterruptedException {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() throws InterruptedException {
                        List<String> listAssignmentIdNfs = this.this$0.getUnit().getListAssignmentIdNfs();
                        if (listAssignmentIdNfs != null) {
                            RDAnswerPartialDraft rDAnswerPartialDraft = this.this$0;
                            rDAnswerPartialDraft.assignmentaRepo.updateDataDownloadedAtByIds(listAssignmentIdNfs);
                            rDAnswerPartialDraft.persistSubmitVersions(listAssignmentIdNfs);
                        }
                        callback.invoke("Sukses", false, Double.valueOf(100.0d));
                    }
                });
                return;
            } else {
                callback.invoke("Sukses", false, Double.valueOf(100.0d));
                return;
            }
        }
        callback.invoke("Sukses", false, Double.valueOf(100.0d));
    }

    private final void processExtract(String tempDirAnswer, final Function0<Unit> callback) {
        File file = new File(tempDirAnswer);
        ArrayList<File> arrayList = new ArrayList<>();
        File[] fileArrListFiles = file.listFiles();
        Intrinsics.checkNotNullExpressionValue(fileArrListFiles, "tempAnswer.listFiles()");
        ArrayList arrayList2 = new ArrayList();
        for (File file2 : fileArrListFiles) {
            File f = file2;
            Intrinsics.checkNotNullExpressionValue(f, "f");
            String upperCase = ("." + FilesKt.getExtension(f)).toUpperCase();
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase()");
            if (StringsKt.equals(upperCase, CommonCons.INSTANCE.getEXTENSION_7ZIP(), true)) {
                arrayList2.add(file2);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add((File) it.next());
        }
        extractAnswer(arrayList, tempDirAnswer, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.processExtract.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                callback.invoke();
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.Object] */
    private final void extractAnswer(final ArrayList<File> filesZip, String tempDirAnswer, Function0<Unit> callback) {
        int size;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (filesZip.size() > 0 && (size = filesZip.size() - 1) >= 0) {
            while (true) {
                int i = size - 1;
                objectRef.element = filesZip.get(size);
                if (((File) objectRef.element) != null) {
                    String out = new File(tempDirAnswer).getParent();
                    Log.d("RFM_dir_out_path", out);
                    Sync sync = (Sync) BuildersKt__BuildersKt.runBlocking$default(null, new RDAnswerPartialDraft$extractAnswer$1$assignmentPojo$1(this, objectRef, null), 1, null);
                    if (sync != null) {
                        AssignmentEntity assignment = sync.getAssignment();
                        Intrinsics.checkNotNull(assignment);
                        Integer assignmentStatusId = assignment.getAssignmentStatusId();
                        int assignment_status_open = CommonCons.INSTANCE.getASSIGNMENT_STATUS_OPEN();
                        if (assignmentStatusId != null && assignmentStatusId.intValue() == assignment_status_open && !assignment.getPendingStatus()) {
                            filesZip.remove(objectRef.element);
                            ((File) objectRef.element).delete();
                        } else {
                            ZipHelper.Companion companion = ZipHelper.INSTANCE;
                            String path = ((File) objectRef.element).getPath();
                            Intrinsics.checkNotNullExpressionValue(path, "file.path");
                            Intrinsics.checkNotNullExpressionValue(out, "out");
                            companion.extractZip(path, out, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$extractAnswer$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                                    invoke(bool.booleanValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(boolean z) {
                                    filesZip.remove(objectRef.element);
                                    objectRef.element.delete();
                                }
                            });
                        }
                    } else {
                        Log.d("FOUR", "extractAnswer: Error assignment tidak ditemukan");
                    }
                }
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        callback.invoke();
    }

    private final void requestAnswerS3() {
        new AssignmentRepositoryImpl().getPresignS3AnswerAssignment(this.unit.getIdPeriode(), (List) BuildersKt__BuildersKt.runBlocking$default(null, new RDAnswerPartialDraft$requestAnswerS3$listAssignment$1(this, null), 1, null), new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.requestAnswerS3.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(S3Response s3Response) {
                invoke2(s3Response);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(S3Response s3Response) {
                Log.d("FOUR", "requestAnswerS3: " + s3Response);
                if (s3Response != null) {
                    ArrayList arrayList = RDAnswerPartialDraft.this.listS3;
                    List<S3Entity> data = s3Response.getData();
                    Intrinsics.checkNotNull(data);
                    arrayList.addAll(data);
                    RDAnswerPartialDraft.this.sumSuccess = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    RDAnswerPartialDraft rDAnswerPartialDraft = RDAnswerPartialDraft.this;
                    rDAnswerPartialDraft.countS3 = rDAnswerPartialDraft.listS3.size();
                    RDAnswerPartialDraft.this.unduhAnswer();
                    return;
                }
                RDAnswerPartialDraft.this.getCallback().invoke("Gagal mengunduh presign url", true, Double.valueOf(-100.0d));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, java.lang.Object] */
    public final void unduhAnswer() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CollectionsKt.first((List) this.listS3);
        this.listS3.remove(objectRef.element);
        T t = objectRef.element;
        Intrinsics.checkNotNull(t);
        List<PresignedUrlsItem> presignedUrls = ((S3Entity) t).getPresignedUrls();
        Intrinsics.checkNotNull(presignedUrls);
        Object objFirst = CollectionsKt.first((List<? extends Object>) presignedUrls);
        Intrinsics.checkNotNull(objFirst);
        String presignedUrl = ((PresignedUrlsItem) objFirst).getPresignedUrl();
        Intrinsics.checkNotNull(presignedUrl);
        List<PresignedUrlsItem> presignedUrls2 = ((S3Entity) objectRef.element).getPresignedUrls();
        Intrinsics.checkNotNull(presignedUrls2);
        Object objFirst2 = CollectionsKt.first((List<? extends Object>) presignedUrls2);
        Intrinsics.checkNotNull(objFirst2);
        final String fileName = ((PresignedUrlsItem) objFirst2).getFileName();
        Intrinsics.checkNotNull(fileName);
        final String strPathTempAnswer = FileHelper.INSTANCE.pathTempAnswer(this.unit.getIdSurvey(), this.unit.getIdPeriode());
        Downloader.INSTANCE.requestAnswer(presignedUrl, strPathTempAnswer, fileName, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.unduhAnswer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) throws InterruptedException {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z) throws InterruptedException {
                if (!z) {
                    RDAnswerPartialDraft rDAnswerPartialDraft = RDAnswerPartialDraft.this;
                    String assignmentId = objectRef.element.getAssignmentId();
                    Intrinsics.checkNotNull(assignmentId);
                    RDAnswerPartialDraft.extractAnswer$default(rDAnswerPartialDraft, assignmentId, strPathTempAnswer + InternalZipConstants.ZIP_FILE_SEPARATOR + fileName, false, 4, null);
                    return;
                }
                RDAnswerPartialDraft.this.getCallback().invoke(str, true, Double.valueOf(-100.0d));
            }
        });
    }

    static /* synthetic */ void extractAnswer$default(RDAnswerPartialDraft rDAnswerPartialDraft, String str, String str2, boolean z, int i, Object obj) throws InterruptedException {
        if ((i & 4) != 0) {
            z = false;
        }
        rDAnswerPartialDraft.extractAnswer(str, str2, z);
    }

    private final void extractAnswer(final String assignmentId, final String pathInput, boolean isSingleDownload) throws InterruptedException {
        final AssignmentEntity assignmentEntity = (AssignmentEntity) BuildersKt__BuildersKt.runBlocking$default(null, new RDAnswerPartialDraft$extractAnswer$assignment$1(this, assignmentId, null), 1, null);
        if (StringsKt.endsWith$default(pathInput, ".7z", false, 2, (Object) null)) {
            ZipHelper.INSTANCE.extractZip(pathInput, FileHelper.INSTANCE.pathAnswer(this.unit.getIdSurvey(), this.unit.getIdPeriode()), new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft.extractAnswer.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) throws InterruptedException {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) throws InterruptedException {
                    Log.d(">>>> 2", assignmentId + " | " + pathInput);
                    new File(pathInput).delete();
                    this.assignmentaRepo.updateDataDownloadedAt(assignmentId);
                    RDAnswerPartialDraft rDAnswerPartialDraft = this;
                    String str = assignmentId;
                    AssignmentEntity assignmentEntity2 = assignmentEntity;
                    rDAnswerPartialDraft.persistSubmitVersion(str, assignmentEntity2 != null ? assignmentEntity2.getSubmitVersionCode() : 0);
                    Function3<String, Boolean, Double, Unit> callback = this.getCallback();
                    RDAnswerPartialDraft rDAnswerPartialDraft2 = this;
                    rDAnswerPartialDraft2.sumSuccess += 1.0d;
                    callback.invoke("Sukses", false, Double.valueOf((rDAnswerPartialDraft2.sumSuccess / this.countS3) * 100));
                    if (this.listS3.size() != 0) {
                        this.unduhAnswer();
                    }
                }
            });
            return;
        }
        if (StringsKt.endsWith$default(pathInput, ".zip", false, 2, (Object) null)) {
            File file = new File(pathInput);
            String parent = file.getParentFile().getParent();
            String name = file.getName();
            Intrinsics.checkNotNullExpressionValue(name, "oldFile.name");
            File file2 = new File(parent + InternalZipConstants.ZIP_FILE_SEPARATOR + CollectionsKt.first(StringsKt.split$default((CharSequence) name, new String[]{"_"}, false, 0, 6, (Object) null)));
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2.getPath() + InternalZipConstants.ZIP_FILE_SEPARATOR + file.getName());
            file.renameTo(file3);
            ZipHelper.Companion companion = ZipHelper.INSTANCE;
            String parent2 = file3.getParent();
            Intrinsics.checkNotNullExpressionValue(parent2, "assignmentFile.parent");
            String name2 = file3.getName();
            Intrinsics.checkNotNullExpressionValue(name2, "assignmentFile.name");
            companion.unZip(parent2, name2, false);
            this.assignmentaRepo.updateDataDownloadedAt(assignmentId);
            persistSubmitVersion(assignmentId, assignmentEntity != null ? assignmentEntity.getSubmitVersionCode() : 0);
            Function3<String, Boolean, Double, Unit> function3 = this.callback;
            double d = this.sumSuccess + 1.0d;
            this.sumSuccess = d;
            function3.invoke("Sukses", false, Double.valueOf((d / this.countS3) * 100));
            if (this.listS3.size() != 0) {
                unduhAnswer();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void persistSubmitVersions(List<String> assignmentIds) throws InterruptedException {
        for (AssignmentEntity assignmentEntity : (List) BuildersKt__BuildersKt.runBlocking$default(null, new RDAnswerPartialDraft$persistSubmitVersions$assignments$1(this, assignmentIds, null), 1, null)) {
            persistSubmitVersion(assignmentEntity.getId(), assignmentEntity.getSubmitVersionCode());
        }
    }

    /* compiled from: RDAnswerPartialDraft.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$persistSubmitVersion$1", f = "RDAnswerPartialDraft.kt", i = {}, l = {278}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerPartialDraft$persistSubmitVersion$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $assignmentId;
        final /* synthetic */ int $submitVersionCode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$assignmentId = str;
            this.$submitVersionCode = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RDAnswerPartialDraft.this.new AnonymousClass1(this.$assignmentId, this.$submitVersionCode, continuation);
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
                if (RDAnswerPartialDraft.this.assignmentSubmitVersionRepo.upsert(this.$assignmentId, this.$submitVersionCode, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void persistSubmitVersion(String assignmentId, int submitVersionCode) throws InterruptedException {
        BuildersKt__BuildersKt.runBlocking$default(null, new AnonymousClass1(assignmentId, submitVersionCode, null), 1, null);
    }
}
