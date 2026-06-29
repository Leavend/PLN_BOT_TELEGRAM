package id.go.bpsfasih.utils.sync.reqDownload;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.models.PresignedUrlsItem;
import id.go.bpsfasih.data.local.models.S3Entity;
import id.go.bpsfasih.data.local.models.S3Response;
import id.go.bpsfasih.data.localserver.Config;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.domain.models.AnswerBeforeSaveModel;
import id.go.bpsfasih.domain.models.SyncAnswerPartial;
import id.go.bpsfasih.utils.helper.FileHelper;
import id.go.bpsfasih.utils.helper.ZipHelper;
import id.go.bpsfasih.utils.tools.Downloader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: RDAnswerChangeMode.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00128\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\u0002\u0010\rJL\u0010\u0019\u001a\u00020\f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b28\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J6\u0010\u001c\u001a\u00020\f2\u0016\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u0012j\b\u0012\u0004\u0012\u00020\u001f`\u00142\u0006\u0010 \u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\f0!H\u0002J\u001e\u0010\"\u001a\u00020\f2\u0006\u0010 \u001a\u00020\u00062\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\f0!H\u0002J\b\u0010#\u001a\u00020\fH\u0002J\b\u0010$\u001a\u00020\fH\u0002J\b\u0010%\u001a\u00020\fH\u0002R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000RC\u0010\u0004\u001a4\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0012j\n\u0012\u0006\u0012\u0004\u0018\u00010\u0013`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDAnswerChangeMode;", "", "unit", "Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "message", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "(Lid/go/bpsfasih/domain/models/SyncAnswerPartial;Lkotlin/jvm/functions/Function2;)V", "answerPathDownload", "getCallback", "()Lkotlin/jvm/functions/Function2;", "listS3", "Ljava/util/ArrayList;", "Lid/go/bpsfasih/data/local/models/S3Entity;", "Lkotlin/collections/ArrayList;", "paramsAssignmentId", "paramsPeriode", "getUnit", "()Lid/go/bpsfasih/domain/models/SyncAnswerPartial;", "extract", "periodAnswer", "Lid/go/bpsfasih/domain/models/AnswerBeforeSaveModel;", "extractAnswer", "pathInput", "filesZip", "Ljava/io/File;", "tempDirAnswer", "Lkotlin/Function0;", "processExtract", "requestAnswerNfs", "requestAnswerS3", "unduhAnswer", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDAnswerChangeMode {
    public static final int $stable = 8;
    private String answerPathDownload;
    private final Function2<String, Boolean, Unit> callback;
    private ArrayList<S3Entity> listS3;
    private String paramsAssignmentId;
    private String paramsPeriode;
    private final SyncAnswerPartial unit;

    /* JADX WARN: Multi-variable type inference failed */
    public RDAnswerChangeMode(SyncAnswerPartial unit, Function2<? super String, ? super Boolean, Unit> callback) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.unit = unit;
        this.callback = callback;
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

    public final Function2<String, Boolean, Unit> getCallback() {
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
        Downloader.INSTANCE.requestAnswer(str2, strPathTempAnswer, str3, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.requestAnswerNfs.1
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
                    RDAnswerChangeMode rDAnswerChangeMode = RDAnswerChangeMode.this;
                    AnswerBeforeSaveModel answerBeforeSaveModel = new AnswerBeforeSaveModel(strPathTempAnswer, str3, idPeriode, idSurvey, userId);
                    final RDAnswerChangeMode rDAnswerChangeMode2 = RDAnswerChangeMode.this;
                    rDAnswerChangeMode.extract(answerBeforeSaveModel, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.requestAnswerNfs.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(String str5, Boolean bool) {
                            invoke(str5, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(String str5, boolean z2) {
                            rDAnswerChangeMode2.getCallback().invoke(str5, Boolean.valueOf(z2));
                        }
                    });
                    return;
                }
                RDAnswerChangeMode.this.getCallback().invoke(str4, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void extract(AnswerBeforeSaveModel periodAnswer, final Function2<? super String, ? super Boolean, Unit> callback) {
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
                processExtract(strPathTempAnswer, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode$extract$1$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
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
                        callback.invoke("Sukses", false);
                    }
                });
                return;
            } else {
                callback.invoke("Sukses", false);
                return;
            }
        }
        callback.invoke("Sukses", false);
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
        extractAnswer(arrayList, tempDirAnswer, new Function0<Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.processExtract.3
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
        if (filesZip.size() > 0 && filesZip.size() - 1 >= 0) {
            while (true) {
                int i = size - 1;
                objectRef.element = filesZip.get(size);
                if (((File) objectRef.element) != null) {
                    String out = new File(tempDirAnswer).getParent();
                    Log.d("RFM_dir_out_path", out);
                    ZipHelper.Companion companion = ZipHelper.INSTANCE;
                    String path = ((File) objectRef.element).getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "file.path");
                    Intrinsics.checkNotNullExpressionValue(out, "out");
                    companion.extractZip(path, out, new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode$extractAnswer$1$1
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
        new AssignmentRepositoryImpl().getPresignS3AnswerAssignment(this.unit.getIdPeriode(), (List) BuildersKt__BuildersKt.runBlocking$default(null, new RDAnswerChangeMode$requestAnswerS3$listAssignment$1(this, null), 1, null), new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.requestAnswerS3.1
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
                    ArrayList arrayList = RDAnswerChangeMode.this.listS3;
                    List<S3Entity> data = s3Response.getData();
                    Intrinsics.checkNotNull(data);
                    arrayList.addAll(data);
                    RDAnswerChangeMode.this.unduhAnswer();
                    return;
                }
                RDAnswerChangeMode.this.getCallback().invoke("Gagal mengunduh presign url", true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unduhAnswer() {
        S3Entity s3Entity = (S3Entity) CollectionsKt.first((List) this.listS3);
        this.listS3.remove(s3Entity);
        Intrinsics.checkNotNull(s3Entity);
        List<PresignedUrlsItem> presignedUrls = s3Entity.getPresignedUrls();
        Intrinsics.checkNotNull(presignedUrls);
        Object objFirst = CollectionsKt.first((List<? extends Object>) presignedUrls);
        Intrinsics.checkNotNull(objFirst);
        String presignedUrl = ((PresignedUrlsItem) objFirst).getPresignedUrl();
        Intrinsics.checkNotNull(presignedUrl);
        List<PresignedUrlsItem> presignedUrls2 = s3Entity.getPresignedUrls();
        Intrinsics.checkNotNull(presignedUrls2);
        Object objFirst2 = CollectionsKt.first((List<? extends Object>) presignedUrls2);
        Intrinsics.checkNotNull(objFirst2);
        final String fileName = ((PresignedUrlsItem) objFirst2).getFileName();
        Intrinsics.checkNotNull(fileName);
        final String strPathTempAnswer = FileHelper.INSTANCE.pathTempAnswer(this.unit.getIdSurvey(), this.unit.getIdPeriode());
        Downloader.INSTANCE.requestAnswer(presignedUrl, strPathTempAnswer, fileName, new Function2<String, Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.unduhAnswer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool) {
                invoke(str, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(String str, boolean z) {
                if (!z) {
                    RDAnswerChangeMode.this.extractAnswer(strPathTempAnswer + InternalZipConstants.ZIP_FILE_SEPARATOR + fileName);
                    return;
                }
                RDAnswerChangeMode.this.getCallback().invoke(str, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void extractAnswer(final String pathInput) {
        if (StringsKt.endsWith$default(pathInput, ".7z", false, 2, (Object) null)) {
            ZipHelper.INSTANCE.extractZip(pathInput, FileHelper.INSTANCE.pathAnswer(this.unit.getIdSurvey(), this.unit.getIdPeriode()), new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDAnswerChangeMode.extractAnswer.2
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
                    new File(pathInput).delete();
                    if (this.listS3.size() != 0) {
                        this.unduhAnswer();
                    } else {
                        this.getCallback().invoke("Sukses", false);
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
            if (this.listS3.size() == 0) {
                this.callback.invoke("Sukses", false);
            } else {
                unduhAnswer();
            }
        }
    }
}
