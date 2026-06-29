package id.go.bpsfasih.data.repository;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import com.codekidlabs.storagechooser.StorageChooser;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataStringApproval;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.S3Response;
import id.go.bpsfasih.data.localserver.RetrofitClient;
import id.go.bpsfasih.data.remote.dto.AssignmentAnswerResponse;
import id.go.bpsfasih.data.remote.dto.AssignmentResponse;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import id.go.bpsfasih.data.repository.AssignmentRepositoryImpl;
import id.go.bpsfasih.domain.repository.AssignmentRepository;
import id.go.bpsfasih.ui.formGear.FormGearJavascriptInterface;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.http.cookie.ClientCookie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.HttpException;
import retrofit2.Response;

/* compiled from: AssignmentRepository.kt */
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J^\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\b2\u0006\u0010\n\u001a\u00020\u000b2#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016¢\u0006\u0002\u0010\u0012J\u008a\u0002\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010\u00062\u0006\u0010(\u001a\u00020\u000b2\b\u0010)\u001a\u0004\u0018\u00010\u00062\b\u0010*\u001a\u0004\u0018\u00010\u00062\b\u0010+\u001a\u0004\u0018\u00010\u00062#\u0010,\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010-¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00040\rH\u0016¢\u0006\u0002\u0010/JU\u00100\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u0006032#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00040\rH\u0016J;\u00106\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u0002092!\u0010:\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u00040\rH\u0016J\u008a\u0002\u0010<\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00062\b\u0010\u001a\u001a\u0004\u0018\u00010\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u00062\b\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00062\b\u0010#\u001a\u0004\u0018\u00010\u00062\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010%2\b\u0010'\u001a\u0004\u0018\u00010\u00062\b\u0010)\u001a\u0004\u0018\u00010\u00062\b\u0010*\u001a\u0004\u0018\u00010\u00062\b\u0010+\u001a\u0004\u0018\u00010\u00062#\u0010,\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010-¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00040\rH\u0016¢\u0006\u0002\u0010>JU\u0010?\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u0006032#\u00104\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000105¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(.\u0012\u0004\u0012\u00020\u00040\rH\u0016J;\u0010@\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u0002092!\u0010:\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u00040\rH\u0016J\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002J\u0018\u0010E\u001a\u00020-2\u0006\u0010C\u001a\u00020D2\u0006\u0010F\u001a\u00020\u0006H\u0002JE\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020I2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u00062#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010-¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016J5\u0010K\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010L¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016J5\u0010M\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010L¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016J=\u0010N\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010O\u001a\u00020P2#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010B¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016J3\u0010Q\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rJC\u0010S\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\u0006032#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010B¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016JC\u0010U\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010V\u001a\b\u0012\u0004\u0012\u00020W032#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010X¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\u00040\rH\u0016J\u0014\u0010Z\u001a\u0004\u0018\u00010\u00062\b\u0010[\u001a\u0004\u0018\u00010\u0006H\u0002JU\u0010\\\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u0006032#\u0010]\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010X¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\u00040\rH\u0016J5\u0010^\u001a\u00020\u00042\u0006\u0010.\u001a\u00020W2#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010-¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016JU\u0010_\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u0006032#\u0010]\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010X¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\u00040\rH\u0016J_\u0010`\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00062\b\u00101\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010a\u001a\u00020b2\u0006\u0010c\u001a\u00020\u00062#\u0010]\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010X¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(Y\u0012\u0004\u0012\u00020\u00040\rH\u0016J=\u0010d\u001a\u00020\u00042\u0006\u0010.\u001a\u00020W2\u0006\u0010e\u001a\u00020\u000b2#\u0010\f\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010-¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\u00040\rH\u0016JK\u0010f\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u00062\u0006\u0010g\u001a\u00020b2\u0006\u0010c\u001a\u00020\u00062\u0006\u00108\u001a\u0002092!\u0010:\u001a\u001d\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(;\u0012\u0004\u0012\u00020\u00040\rH\u0016¨\u0006h"}, d2 = {"Lid/go/bpsfasih/data/repository/AssignmentRepositoryImpl;", "Lid/go/bpsfasih/domain/repository/AssignmentRepository;", "()V", "assignBySelection", "", "periodeId", "", "surveyPeriodRoleUserIds", "", "assignmentIds", "replaceUser", "", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "Lkotlin/ParameterName;", "name", "result", "(Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)V", "assignmentEditS3Post", "fileName", "md5", "surveyPeriodeId", "assignmentId", "isNew", "regionId", "data1", "data2", "data3", "data4", "data5", "data6", "data7", "data8", "data9", "data10", "latitude", "", "longitude", "assignmentCopyFromId", "statusApproval", "paradata", ClientCookie.COMMENT_ATTR, FormGearJavascriptInterface.NOTE_FILE, "postCallback", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "assignment", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "assignmentEditS3Presign", "copyFromId", "fileNames", "", "assignmentSubmitS3Callback", "Lid/go/bpsfasih/data/local/models/AssignmentSubmitS3Response;", "assignmentEditS3Upload", "url", StorageChooser.FILE_PICKER, "Ljava/io/File;", "isSuccessCallback", "bool", "assignmentSubmitS3Post", "draftStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "assignmentSubmitS3Presign", "assignmentSubmitS3Upload", "buildDownloadAssignmentErrorResponse", "Lid/go/bpsfasih/data/remote/dto/AssignmentResponse;", "throwable", "", "buildUploadErrorResponse", "fallbackMessage", "changeModeAssignment", "context", "Landroid/content/Context;", "paramChangeMode", "checkButtonApproval", "Lid/go/bpsfasih/data/local/models/BaseResponseDataStringApproval;", "checkButtonEdit", "getAssignment", "page", "", "getAssignmentAnswer", "Lid/go/bpsfasih/data/remote/dto/AssignmentAnswerResponse;", "getAssignmentNotif", "listAssignment", "getPresignS3AnswerAssignment", "listData", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "Lid/go/bpsfasih/data/local/models/S3Response;", "s3response", "parseErrorMessage", "errorBody", "refreshImageS3", "presignPutCallback", "revoke", "submitPresignS3Get", "submitPresignS3Put", "size", "", "contentMd5", "uploadAssignmentApproval", "isApproval", "uploadImageS3", "fileSize", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AssignmentRepositoryImpl implements AssignmentRepository {
    public static final int $stable = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final id.go.bpsfasih.data.remote.dto.AssignmentResponse buildDownloadAssignmentErrorResponse(java.lang.Throwable r9) {
        /*
            Method dump skipped, instructions count: 237
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.buildDownloadAssignmentErrorResponse(java.lang.Throwable):id.go.bpsfasih.data.remote.dto.AssignmentResponse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BaseResponseDataUpload buildUploadErrorResponse(Throwable throwable, String fallbackMessage) {
        Object objM6852constructorimpl;
        String str;
        Response<?> response;
        ResponseBody responseBodyErrorBody;
        HttpException httpException = throwable instanceof HttpException ? (HttpException) throwable : null;
        Integer numValueOf = httpException != null ? Integer.valueOf(httpException.code()) : null;
        try {
            Result.Companion companion = Result.INSTANCE;
            AssignmentRepositoryImpl assignmentRepositoryImpl = this;
            objM6852constructorimpl = Result.m6852constructorimpl((httpException == null || (response = httpException.response()) == null || (responseBodyErrorBody = response.errorBody()) == null) ? null : responseBodyErrorBody.string());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6852constructorimpl = Result.m6852constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m6858isFailureimpl(objM6852constructorimpl)) {
            objM6852constructorimpl = null;
        }
        String str2 = (String) objM6852constructorimpl;
        String str3 = str2;
        if (!(str3 == null || StringsKt.isBlank(str3))) {
            Log.e("AssignmentRepository", "HTTP " + (numValueOf == null ? "-" : numValueOf) + " error body: " + str2);
        }
        String errorMessage = parseErrorMessage(str2);
        if (errorMessage == null) {
            String message = throwable.getMessage();
            if (message == null || !(!StringsKt.isBlank(message))) {
                message = null;
            }
            if (message == null) {
                String str4 = numValueOf != null ? "Terjadi kesalahan (" + numValueOf.intValue() + ")" : null;
                str = str4 == null ? fallbackMessage : str4;
            } else {
                str = message;
            }
        } else {
            str = errorMessage;
        }
        return new BaseResponseDataUpload(false, numValueOf, str, null, 8, null);
    }

    private final String parseErrorMessage(String errorBody) {
        Object objM6852constructorimpl;
        String str = errorBody;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            AssignmentRepositoryImpl assignmentRepositoryImpl = this;
            JSONObject jSONObject = new JSONObject(errorBody);
            String message = jSONObject.optString("message");
            String error = jSONObject.optString(Constants.IPC_BUNDLE_KEY_SEND_ERROR);
            int iOptInt = jSONObject.optInt(NotificationCompat.CATEGORY_STATUS, 0);
            Intrinsics.checkNotNullExpressionValue(message, "message");
            if (!(!StringsKt.isBlank(message))) {
                Intrinsics.checkNotNullExpressionValue(error, "error");
                if ((!StringsKt.isBlank(error)) && iOptInt > 0 && StringsKt.equals(error, "Internal Server Error", true)) {
                    message = "Terjadi kesalahan (" + iOptInt + ")";
                } else if ((!StringsKt.isBlank(error)) && iOptInt > 0) {
                    message = error + " (" + iOptInt + ")";
                } else if (!StringsKt.isBlank(error)) {
                    message = error;
                } else {
                    message = iOptInt > 0 ? "Terjadi kesalahan (" + iOptInt + ")" : null;
                }
            }
            objM6852constructorimpl = Result.m6852constructorimpl(message);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            objM6852constructorimpl = Result.m6852constructorimpl(ResultKt.createFailure(th));
        }
        return (String) (Result.m6858isFailureimpl(objM6852constructorimpl) ? null : objM6852constructorimpl);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void getAssignment(String surveyPeriodeId, int page, final Function1<? super AssignmentResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().getAssignment(surveyPeriodeId, page).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentResponse, Unit> function1 = new Function1<AssignmentResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentResponse assignmentResponse) {
                invoke2(assignmentResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentResponse assignmentResponse) {
                callback.invoke(assignmentResponse);
            }
        };
        Consumer<? super AssignmentResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda12
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignment$lambda$8(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignment.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable error) {
                String message = error.getMessage();
                if (message == null) {
                    message = "Unknown error";
                }
                Log.e("Get Assignment S3 Error", message, error);
                Function1<AssignmentResponse, Unit> function13 = callback;
                AssignmentRepositoryImpl assignmentRepositoryImpl = this;
                Intrinsics.checkNotNullExpressionValue(error, "error");
                function13.invoke(assignmentRepositoryImpl.buildDownloadAssignmentErrorResponse(error));
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda13
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignment$lambda$9(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignment$lambda$8(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignment$lambda$9(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void getAssignmentNotif(String periodeId, List<String> listAssignment, final Function1<? super AssignmentResponse, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(listAssignment, "listAssignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONArray jSONArray = new JSONArray((Collection) listAssignment);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("surveyPeriodId", periodeId);
        jSONObject.put("assignmentIds", jSONArray);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().getAssignmentNotif(requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentResponse, Unit> function1 = new Function1<AssignmentResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignmentNotif.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentResponse assignmentResponse) {
                invoke2(assignmentResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentResponse assignmentResponse) {
                callback.invoke(assignmentResponse);
            }
        };
        Consumer<? super AssignmentResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda34
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignmentNotif$lambda$10(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignmentNotif.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable error) {
                String message = error.getMessage();
                if (message == null) {
                    message = "Unknown error";
                }
                Log.e("Get Assignment S3 Error", message, error);
                Function1<AssignmentResponse, Unit> function13 = callback;
                AssignmentRepositoryImpl assignmentRepositoryImpl = this;
                Intrinsics.checkNotNullExpressionValue(error, "error");
                function13.invoke(assignmentRepositoryImpl.buildDownloadAssignmentErrorResponse(error));
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda35
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignmentNotif$lambda$11(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentNotif$lambda$10(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentNotif$lambda$11(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignBySelection(String periodeId, String[] surveyPeriodRoleUserIds, String[] assignmentIds, boolean replaceUser, final Function1<? super BaseResponse, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(surveyPeriodRoleUserIds, "surveyPeriodRoleUserIds");
        Intrinsics.checkNotNullParameter(assignmentIds, "assignmentIds");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("surveyPeriodRoleUserIds", new JSONArray(surveyPeriodRoleUserIds));
        jSONObject.put("assignmentIds", new JSONArray(assignmentIds));
        jSONObject.put("replaceUser", replaceUser);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jo.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignBySelection(periodeId, requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponse, Unit> function1 = new Function1<BaseResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignBySelection.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponse baseResponse) {
                invoke2(baseResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponse baseResponse) {
                callback.invoke(baseResponse);
            }
        };
        Consumer<? super BaseResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignBySelection$lambda$12(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignBySelection.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                String message = th.getMessage();
                Intrinsics.checkNotNull(message);
                Log.d("USER ROLE ERROR ", message);
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignBySelection$lambda$13(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignBySelection$lambda$12(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignBySelection$lambda$13(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void getPresignS3AnswerAssignment(String periodeId, List<AssignmentEntity> listData, final Function1<? super S3Response, Unit> callback) {
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(listData, "listData");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONArray jSONArray = new JSONArray();
        for (AssignmentEntity assignmentEntity : listData) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("assignmentId", assignmentEntity.getId());
            jSONObject.put("copyFromId", assignmentEntity.getCopyFromId());
            String basePath = assignmentEntity.getBasePath();
            Intrinsics.checkNotNull(basePath);
            jSONObject.put("fileNames", new JSONArray((Collection) CollectionsKt.listOf(CollectionsKt.last(StringsKt.split$default((CharSequence) basePath, new char[]{'/'}, false, 0, 6, (Object) null)))));
            jSONArray.put(jSONObject);
        }
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<S3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().getPresignS3AnswerAssignment(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<S3Response, Unit> function1 = new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getPresignS3AnswerAssignment.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                callback.invoke(s3Response);
            }
        };
        Consumer<? super S3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getPresignS3AnswerAssignment$lambda$15(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getPresignS3AnswerAssignment.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getPresignS3AnswerAssignment$lambda$16(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPresignS3AnswerAssignment$lambda$15(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getPresignS3AnswerAssignment$lambda$16(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void changeModeAssignment(Context context, String assignmentId, String paramChangeMode, final Function1<? super BaseResponseDataUpload, Unit> callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(paramChangeMode, "paramChangeMode");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<BaseResponseDataUpload> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().uploadAssignmentChangeMode(assignmentId, RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/json"), paramChangeMode)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataUpload, Unit> function1 = new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.changeModeAssignment.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                callback.invoke(baseResponseDataUpload);
            }
        };
        Consumer<? super BaseResponseDataUpload> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda25
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.changeModeAssignment$lambda$17(function1, obj);
            }
        };
        final C07512 c07512 = new C07512(context, callback);
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda26
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.changeModeAssignment$lambda$18(c07512, obj);
            }
        });
    }

    /* compiled from: AssignmentRepository.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* renamed from: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$changeModeAssignment$2, reason: invalid class name and case insensitive filesystem */
    static final class C07512 extends Lambda implements Function1<Throwable, Unit> {
        final /* synthetic */ Function1<BaseResponseDataUpload, Unit> $callback;
        final /* synthetic */ Context $context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C07512(Context context, Function1<? super BaseResponseDataUpload, Unit> function1) {
            super(1);
            this.$context = context;
            this.$callback = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(final Throwable th) {
            Context context = this.$context;
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            final Context context2 = this.$context;
            ((Activity) context).runOnUiThread(new Runnable() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$changeModeAssignment$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    AssignmentRepositoryImpl.C07512.invoke$lambda$0(context2, th);
                }
            });
            this.$callback.invoke(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$0(Context context, Throwable th) {
            Intrinsics.checkNotNullParameter(context, "$context");
            Toast.makeText(context, String.valueOf(th.getMessage()), 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void changeModeAssignment$lambda$17(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void changeModeAssignment$lambda$18(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void checkButtonApproval(String assignmentId, final Function1<? super BaseResponseDataStringApproval, Unit> callback) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<BaseResponseDataStringApproval> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().checkButtonApproval(assignmentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataStringApproval, Unit> function1 = new Function1<BaseResponseDataStringApproval, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.checkButtonApproval.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                invoke2(baseResponseDataStringApproval);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                callback.invoke(baseResponseDataStringApproval);
            }
        };
        Consumer<? super BaseResponseDataStringApproval> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda20
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.checkButtonApproval$lambda$19(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.checkButtonApproval.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callback.invoke(null);
            }
        };
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda21
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.checkButtonApproval$lambda$20(function12, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkButtonApproval$lambda$19(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkButtonApproval$lambda$20(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void checkButtonEdit(String assignmentId, final Function1<? super BaseResponseDataStringApproval, Unit> callback) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Observable<BaseResponseDataStringApproval> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().checkButtonEdit(assignmentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataStringApproval, Unit> function1 = new Function1<BaseResponseDataStringApproval, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.checkButtonEdit.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                invoke2(baseResponseDataStringApproval);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataStringApproval baseResponseDataStringApproval) {
                callback.invoke(baseResponseDataStringApproval);
            }
        };
        Consumer<? super BaseResponseDataStringApproval> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda27
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.checkButtonEdit$lambda$21(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.checkButtonEdit.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callback.invoke(null);
            }
        };
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda28
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.checkButtonEdit$lambda$22(function12, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkButtonEdit$lambda$21(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkButtonEdit$lambda$22(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void uploadAssignmentApproval(AssignmentEntity assignment, boolean isApproval, final Function1<? super BaseResponseDataUpload, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignment.getId());
        jSONObject.put(ClientCookie.COMMENT_ATTR, assignment.getComment());
        jSONObject.put("paradata", assignment.getParadata());
        jSONObject.put("statusApproval", isApproval);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "body.toString()");
        Observable<BaseResponseDataUpload> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().uploadAssignmentApproval(companion.create(mediaType, string)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataUpload, Unit> function1 = new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.uploadAssignmentApproval.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                callback.invoke(baseResponseDataUpload);
                Log.d(">> SUCCESS", new Gson().toJson(baseResponseDataUpload));
            }
        };
        Consumer<? super BaseResponseDataUpload> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda23
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.uploadAssignmentApproval$lambda$23(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.uploadAssignmentApproval.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d(">> ERROR 1", new Gson().toJson(String.valueOf(th.getMessage())));
                callback.invoke(null);
            }
        };
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda24
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.uploadAssignmentApproval$lambda$24(function12, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadAssignmentApproval$lambda$23(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadAssignmentApproval$lambda$24(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void revoke(AssignmentEntity assignment, final Function1<? super BaseResponseDataUpload, Unit> callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignment, "assignment");
        Intrinsics.checkNotNullParameter(callback, "callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignment.getId());
        jSONObject.put(ClientCookie.COMMENT_ATTR, assignment.getComment());
        jSONObject.put("paradata", assignment.getParadata());
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "body.toString()");
        Observable<BaseResponseDataUpload> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().revoke(companion.create(mediaType, string)).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataUpload, Unit> function1 = new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.revoke.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                callback.invoke(baseResponseDataUpload);
                Log.d(">> SUCCESS", new Gson().toJson(baseResponseDataUpload));
            }
        };
        Consumer<? super BaseResponseDataUpload> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda31
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.revoke$lambda$25(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.revoke.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                callback.invoke(null);
                Log.d(">> ERROR 1", new Gson().toJson(String.valueOf(th.getMessage())));
            }
        };
        observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda32
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.revoke$lambda$26(function12, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revoke$lambda$25(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void revoke$lambda$26(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentSubmitS3Presign(String assignmentId, String copyFromId, String periodeId, List<String> fileNames, final Function1<? super AssignmentSubmitS3Response, Unit> assignmentSubmitS3Callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(fileNames, "fileNames");
        Intrinsics.checkNotNullParameter(assignmentSubmitS3Callback, "assignmentSubmitS3Callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignmentId);
        if (copyFromId != null) {
            jSONObject.put("copyFromId", copyFromId);
        }
        jSONObject.put("fileNames", new JSONArray((Collection) fileNames));
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentSubmitS3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentSubmitPresigned(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentSubmitS3Response, Unit> function1 = new Function1<AssignmentSubmitS3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Presign.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentSubmitS3Response assignmentSubmitS3Response) {
                invoke2(assignmentSubmitS3Response);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentSubmitS3Response assignmentSubmitS3Response) {
                Log.d(">> presign success", new Gson().toJson(assignmentSubmitS3Response));
                assignmentSubmitS3Callback.invoke(assignmentSubmitS3Response);
            }
        };
        Consumer<? super AssignmentSubmitS3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Presign$lambda$29(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Presign.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d(">> presign error", String.valueOf(th.getMessage()));
                assignmentSubmitS3Callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Presign$lambda$30(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Presign$lambda$29(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Presign$lambda$30(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentSubmitS3Upload(String url, File file, final Function1<? super Boolean, Unit> isSuccessCallback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(isSuccessCallback, "isSuccessCallback");
        RequestBody requestBodyCreate = RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/x-7z-compressed"), file);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<ResponseBody> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentUploadS3(url, requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<ResponseBody, Unit> function1 = new Function1<ResponseBody, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Upload.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ResponseBody responseBody) {
                invoke2(responseBody);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ResponseBody responseBody) {
                isSuccessCallback.invoke(true);
            }
        };
        Consumer<? super ResponseBody> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda38
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Upload$lambda$31(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Upload.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d("FOUR", "assignmentSubmitS3Upload: " + th);
                isSuccessCallback.invoke(false);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda39
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Upload$lambda$32(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Upload$lambda$31(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Upload$lambda$32(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentSubmitS3Post(String fileName, String md5, String surveyPeriodeId, String assignmentId, boolean isNew, boolean draftStatus, String regionId, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10, Float latitude, Float longitude, String assignmentCopyFromId, String paradata, String comment, String note, final Function1<? super BaseResponseDataUpload, Unit> postCallback) {
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(md5, "md5");
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(postCallback, "postCallback");
        Pair[] pairArr = new Pair[25];
        pairArr[0] = TuplesKt.to("filename", fileName);
        pairArr[1] = TuplesKt.to("md5", md5);
        pairArr[2] = TuplesKt.to("surveyPeriodeId", surveyPeriodeId);
        pairArr[3] = TuplesKt.to("assignmentId", assignmentId);
        pairArr[4] = TuplesKt.to("createStatus", String.valueOf(isNew));
        pairArr[5] = TuplesKt.to("draftStatus", String.valueOf(draftStatus));
        pairArr[6] = TuplesKt.to("regionId", regionId);
        pairArr[7] = TuplesKt.to("data1", data1);
        pairArr[8] = TuplesKt.to("data2", data2);
        pairArr[9] = TuplesKt.to("data3", data3);
        pairArr[10] = TuplesKt.to("data4", data4);
        pairArr[11] = TuplesKt.to("data5", data5);
        pairArr[12] = TuplesKt.to("data6", data6);
        pairArr[13] = TuplesKt.to("data7", data7);
        pairArr[14] = TuplesKt.to("data8", data8);
        pairArr[15] = TuplesKt.to("data9", data9);
        pairArr[16] = TuplesKt.to("data10", data10);
        String str = IdManager.DEFAULT_VERSION_NAME;
        if (latitude == null || (string = latitude.toString()) == null) {
            string = IdManager.DEFAULT_VERSION_NAME;
        }
        pairArr[17] = TuplesKt.to("latitude", string);
        if (longitude != null && (string2 = longitude.toString()) != null) {
            str = string2;
        }
        pairArr[18] = TuplesKt.to("longitude", str);
        pairArr[19] = TuplesKt.to("copyFromId", assignmentCopyFromId);
        pairArr[20] = TuplesKt.to("statusApproval", "false");
        pairArr[21] = TuplesKt.to("sourceFrom", "CAPI");
        pairArr[22] = TuplesKt.to("paradata", paradata);
        pairArr[23] = TuplesKt.to(ClientCookie.COMMENT_ATTR, comment);
        pairArr[24] = TuplesKt.to(FormGearJavascriptInterface.NOTE_FILE, note);
        Map<String, String> mapMapOf = MapsKt.mapOf(pairArr);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponseDataUpload> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentSubmitPost(mapMapOf).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataUpload, Unit> function1 = new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Post.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                Log.d(">> submit success", new Gson().toJson(baseResponseDataUpload));
                postCallback.invoke(baseResponseDataUpload);
            }
        };
        Consumer<? super BaseResponseDataUpload> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Post$lambda$33(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentSubmitS3Post.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable ex) {
                AssignmentRepositoryImpl assignmentRepositoryImpl = AssignmentRepositoryImpl.this;
                Intrinsics.checkNotNullExpressionValue(ex, "ex");
                BaseResponseDataUpload baseResponseDataUploadBuildUploadErrorResponse = assignmentRepositoryImpl.buildUploadErrorResponse(ex, "Gagal mengirimkan data ke server");
                Log.d(">> submit error", String.valueOf(baseResponseDataUploadBuildUploadErrorResponse.getMessage()));
                postCallback.invoke(baseResponseDataUploadBuildUploadErrorResponse);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentSubmitS3Post$lambda$34(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Post$lambda$33(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentSubmitS3Post$lambda$34(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentEditS3Presign(String assignmentId, String copyFromId, String periodeId, List<String> fileNames, final Function1<? super AssignmentSubmitS3Response, Unit> assignmentSubmitS3Callback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(fileNames, "fileNames");
        Intrinsics.checkNotNullParameter(assignmentSubmitS3Callback, "assignmentSubmitS3Callback");
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignmentId);
        if (copyFromId != null) {
            jSONObject.put("copyFromId", copyFromId);
        }
        jSONObject.put("fileNames", new JSONArray((Collection) fileNames));
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonObject.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentSubmitS3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentEditPresigned(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentSubmitS3Response, Unit> function1 = new Function1<AssignmentSubmitS3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Presign.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentSubmitS3Response assignmentSubmitS3Response) {
                invoke2(assignmentSubmitS3Response);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentSubmitS3Response assignmentSubmitS3Response) {
                Log.d(">> presign edit success", new Gson().toJson(assignmentSubmitS3Response));
                assignmentSubmitS3Callback.invoke(assignmentSubmitS3Response);
            }
        };
        Consumer<? super AssignmentSubmitS3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda18
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Presign$lambda$37(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Presign.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d(">> presign edit error", String.valueOf(th.getMessage()));
                assignmentSubmitS3Callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda19
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Presign$lambda$38(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Presign$lambda$37(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Presign$lambda$38(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentEditS3Upload(String url, File file, final Function1<? super Boolean, Unit> isSuccessCallback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(isSuccessCallback, "isSuccessCallback");
        RequestBody requestBodyCreate = RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("application/x-7z-compressed"), file);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<ResponseBody> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentUploadS3(url, requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<ResponseBody, Unit> function1 = new Function1<ResponseBody, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Upload.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ResponseBody responseBody) {
                invoke2(responseBody);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ResponseBody responseBody) {
                isSuccessCallback.invoke(true);
            }
        };
        Consumer<? super ResponseBody> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda36
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Upload$lambda$39(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Upload.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d("FOUR", "assignmentEditS3Upload: " + th);
                isSuccessCallback.invoke(false);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda37
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Upload$lambda$40(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Upload$lambda$39(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Upload$lambda$40(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void assignmentEditS3Post(String fileName, String md5, String surveyPeriodeId, String assignmentId, boolean isNew, String regionId, String data1, String data2, String data3, String data4, String data5, String data6, String data7, String data8, String data9, String data10, Float latitude, Float longitude, String assignmentCopyFromId, boolean statusApproval, String paradata, String comment, String note, final Function1<? super BaseResponseDataUpload, Unit> postCallback) {
        String string;
        String string2;
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(md5, "md5");
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(regionId, "regionId");
        Intrinsics.checkNotNullParameter(postCallback, "postCallback");
        Pair[] pairArr = new Pair[25];
        pairArr[0] = TuplesKt.to("filename", fileName);
        pairArr[1] = TuplesKt.to("md5", md5);
        pairArr[2] = TuplesKt.to("surveyPeriodeId", surveyPeriodeId);
        pairArr[3] = TuplesKt.to("assignmentId", assignmentId);
        pairArr[4] = TuplesKt.to("createStatus", String.valueOf(isNew));
        pairArr[5] = TuplesKt.to("draftStatus", "false");
        pairArr[6] = TuplesKt.to("regionId", regionId);
        pairArr[7] = TuplesKt.to("data1", data1);
        pairArr[8] = TuplesKt.to("data2", data2);
        pairArr[9] = TuplesKt.to("data3", data3);
        pairArr[10] = TuplesKt.to("data4", data4);
        pairArr[11] = TuplesKt.to("data5", data5);
        pairArr[12] = TuplesKt.to("data6", data6);
        pairArr[13] = TuplesKt.to("data7", data7);
        pairArr[14] = TuplesKt.to("data8", data8);
        pairArr[15] = TuplesKt.to("data9", data9);
        pairArr[16] = TuplesKt.to("data10", data10);
        String str = IdManager.DEFAULT_VERSION_NAME;
        if (latitude == null || (string = latitude.toString()) == null) {
            string = IdManager.DEFAULT_VERSION_NAME;
        }
        pairArr[17] = TuplesKt.to("latitude", string);
        if (longitude != null && (string2 = longitude.toString()) != null) {
            str = string2;
        }
        pairArr[18] = TuplesKt.to("longitude", str);
        pairArr[19] = TuplesKt.to("copyFromId", assignmentCopyFromId);
        pairArr[20] = TuplesKt.to("statusApproval", String.valueOf(statusApproval));
        pairArr[21] = TuplesKt.to("sourceFrom", "CAPI");
        pairArr[22] = TuplesKt.to("paradata", paradata);
        pairArr[23] = TuplesKt.to(ClientCookie.COMMENT_ATTR, comment);
        pairArr[24] = TuplesKt.to(FormGearJavascriptInterface.NOTE_FILE, note);
        Map<String, String> mapMapOf = MapsKt.mapOf(pairArr);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<BaseResponseDataUpload> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().assignmentEditPost(mapMapOf).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<BaseResponseDataUpload, Unit> function1 = new Function1<BaseResponseDataUpload, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Post.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BaseResponseDataUpload baseResponseDataUpload) {
                invoke2(baseResponseDataUpload);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(BaseResponseDataUpload baseResponseDataUpload) {
                Log.d(">> edit success", new Gson().toJson(baseResponseDataUpload));
                postCallback.invoke(baseResponseDataUpload);
            }
        };
        Consumer<? super BaseResponseDataUpload> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Post$lambda$41(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.assignmentEditS3Post.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable ex) {
                AssignmentRepositoryImpl assignmentRepositoryImpl = AssignmentRepositoryImpl.this;
                Intrinsics.checkNotNullExpressionValue(ex, "ex");
                BaseResponseDataUpload baseResponseDataUploadBuildUploadErrorResponse = assignmentRepositoryImpl.buildUploadErrorResponse(ex, "Gagal mengirimkan edit ke server");
                Log.d(">> edit error", String.valueOf(baseResponseDataUploadBuildUploadErrorResponse.getMessage()));
                postCallback.invoke(baseResponseDataUploadBuildUploadErrorResponse);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.assignmentEditS3Post$lambda$42(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Post$lambda$41(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void assignmentEditS3Post$lambda$42(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void submitPresignS3Get(String assignmentId, String copyFromId, String periodeId, List<String> fileNames, final Function1<? super S3Response, Unit> presignPutCallback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(fileNames, "fileNames");
        Intrinsics.checkNotNullParameter(presignPutCallback, "presignPutCallback");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignmentId);
        jSONObject.put("copyFromId", copyFromId);
        jSONObject.put("fileNames", new JSONArray((Collection) fileNames));
        jSONArray.put(jSONObject);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<S3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().submitPresignedGet(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<S3Response, Unit> function1 = new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.submitPresignS3Get.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                presignPutCallback.invoke(s3Response);
            }
        };
        Consumer<? super S3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda14
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.submitPresignS3Get$lambda$43(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.submitPresignS3Get.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                presignPutCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda15
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.submitPresignS3Get$lambda$44(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitPresignS3Get$lambda$43(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitPresignS3Get$lambda$44(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void submitPresignS3Put(String assignmentId, String copyFromId, String periodeId, String fileName, long size, String contentMd5, final Function1<? super S3Response, Unit> presignPutCallback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        Intrinsics.checkNotNullParameter(contentMd5, "contentMd5");
        Intrinsics.checkNotNullParameter(presignPutCallback, "presignPutCallback");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignmentId);
        jSONObject.put("copyFromId", copyFromId);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("fileName", fileName);
        jSONObject2.put("mimeType", "image/png");
        jSONObject2.put("fileSize", size);
        jSONObject2.put("contentMD5", contentMd5);
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(jSONObject2);
        jSONObject.put("fileNames", jSONArray2);
        jSONArray.put(jSONObject);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<S3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().submitPresignedPut(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<S3Response, Unit> function1 = new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.submitPresignS3Put.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                presignPutCallback.invoke(s3Response);
            }
        };
        Consumer<? super S3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda22
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.submitPresignS3Put$lambda$45(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.submitPresignS3Put.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                presignPutCallback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda33
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.submitPresignS3Put$lambda$46(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitPresignS3Put$lambda$45(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void submitPresignS3Put$lambda$46(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void refreshImageS3(String assignmentId, String copyFromId, String periodeId, List<String> fileNames, final Function1<? super S3Response, Unit> presignPutCallback) throws JSONException {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(periodeId, "periodeId");
        Intrinsics.checkNotNullParameter(fileNames, "fileNames");
        Intrinsics.checkNotNullParameter(presignPutCallback, "presignPutCallback");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("assignmentId", assignmentId);
        jSONObject.put("copyFromId", copyFromId);
        jSONObject.put("fileNames", new JSONArray((Collection) fileNames));
        jSONArray.put(jSONObject);
        RequestBody.Companion companion = RequestBody.INSTANCE;
        MediaType mediaType = MediaType.INSTANCE.parse("application/json");
        String string = jSONArray.toString();
        Intrinsics.checkNotNullExpressionValue(string, "jsonArray.toString()");
        RequestBody requestBodyCreate = companion.create(mediaType, string);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<S3Response> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().refreshPresignedGet(requestBodyCreate, periodeId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<S3Response, Unit> function1 = new Function1<S3Response, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.refreshImageS3.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                presignPutCallback.invoke(s3Response);
            }
        };
        Consumer<? super S3Response> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.refreshImageS3$lambda$47(function1, obj);
            }
        };
        final C07642 c07642 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.refreshImageS3.2
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.refreshImageS3$lambda$48(c07642, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshImageS3$lambda$47(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshImageS3$lambda$48(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    @Override // id.go.bpsfasih.domain.repository.AssignmentRepository
    public void uploadImageS3(String url, long fileSize, String contentMd5, File file, final Function1<? super Boolean, Unit> isSuccessCallback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(contentMd5, "contentMd5");
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(isSuccessCallback, "isSuccessCallback");
        RequestBody requestBodyCreate = RequestBody.INSTANCE.create(MediaType.INSTANCE.parse("image/jpg"), file);
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<ResponseBody> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().uploadImageS3(url, fileSize, contentMd5, requestBodyCreate).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<ResponseBody, Unit> function1 = new Function1<ResponseBody, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.uploadImageS3.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ResponseBody responseBody) {
                invoke2(responseBody);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ResponseBody responseBody) {
                isSuccessCallback.invoke(true);
            }
        };
        Consumer<? super ResponseBody> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda16
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.uploadImageS3$lambda$49(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.uploadImageS3.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Log.d("FOUR", "uploadImageS3: " + th);
                isSuccessCallback.invoke(false);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda17
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.uploadImageS3$lambda$50(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadImageS3$lambda$49(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void uploadImageS3$lambda$50(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public final void getAssignmentAnswer(String assignmentId, final Function1<? super AssignmentAnswerResponse, Unit> callback) {
        Intrinsics.checkNotNullParameter(assignmentId, "assignmentId");
        Intrinsics.checkNotNullParameter(callback, "callback");
        CompositeDisposable myCompositeDisposable = FasihApp.INSTANCE.getMyCompositeDisposable();
        Observable<AssignmentAnswerResponse> observableSubscribeOn = RetrofitClient.INSTANCE.getAssignmentApiService().getAssignmentAnswer(assignmentId).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
        final Function1<AssignmentAnswerResponse, Unit> function1 = new Function1<AssignmentAnswerResponse, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignmentAnswer.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AssignmentAnswerResponse assignmentAnswerResponse) {
                invoke2(assignmentAnswerResponse);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AssignmentAnswerResponse assignmentAnswerResponse) {
                callback.invoke(assignmentAnswerResponse);
            }
        };
        Consumer<? super AssignmentAnswerResponse> consumer = new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda29
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignmentAnswer$lambda$51(function1, obj);
            }
        };
        final Function1<Throwable, Unit> function12 = new Function1<Throwable, Unit>() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl.getAssignmentAnswer.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                String message = th.getMessage();
                Intrinsics.checkNotNull(message);
                Log.d("Error download answer Data", message);
                callback.invoke(null);
            }
        };
        myCompositeDisposable.add(observableSubscribeOn.subscribe(consumer, new Consumer() { // from class: id.go.bpsfasih.data.repository.AssignmentRepositoryImpl$$ExternalSyntheticLambda30
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AssignmentRepositoryImpl.getAssignmentAnswer$lambda$52(function12, obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentAnswer$lambda$51(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getAssignmentAnswer$lambda$52(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }
}
