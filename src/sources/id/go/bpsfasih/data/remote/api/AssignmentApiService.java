package id.go.bpsfasih.data.remote.api;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.local.models.AssignmentSubmitS3Response;
import id.go.bpsfasih.data.local.models.BaseResponseDataStringApproval;
import id.go.bpsfasih.data.local.models.BaseResponseDataUpload;
import id.go.bpsfasih.data.local.models.S3Response;
import id.go.bpsfasih.data.remote.dto.AssignmentAnswerResponse;
import id.go.bpsfasih.data.remote.dto.AssignmentResponse;
import id.go.bpsfasih.data.remote.dto.BaseResponse;
import io.reactivex.Observable;
import java.util.Map;
import kotlin.Metadata;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/* compiled from: AssignmentApiService.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'J&\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0016\b\u0001\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fH'J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0016\b\u0001\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\fH'J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\"\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\bH'J\u0018\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u0006H'J\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u0006H'J\"\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u000f\u001a\u00020\u00062\b\b\u0001\u0010\u001b\u001a\u00020\u001cH'J\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\u0017\u001a\u00020\u0006H'J\u0018\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'J\"\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\"\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\u0018\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'J\"\u0010$\u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\"\u0010%\u001a\b\u0012\u0004\u0012\u00020!0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000f\u001a\u00020\u0006H'J\u0018\u0010&\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH'J\"\u0010'\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\b\b\u0001\u0010(\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\bH'J6\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u00062\b\b\u0001\u0010*\u001a\u00020+2\b\b\u0001\u0010,\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\bH'¨\u0006-"}, d2 = {"Lid/go/bpsfasih/data/remote/api/AssignmentApiService;", "", "assignBySelection", "Lio/reactivex/Observable;", "Lid/go/bpsfasih/data/remote/dto/BaseResponse;", "surveyPeriodId", "", "param", "Lokhttp3/RequestBody;", "assignmentEditPost", "Lid/go/bpsfasih/data/local/models/BaseResponseDataUpload;", "requestBody", "", "assignmentEditPresigned", "Lid/go/bpsfasih/data/local/models/AssignmentSubmitS3Response;", "surveyPeriodeId", "assignmentSubmitPost", "assignmentSubmitPresigned", "assignmentUploadS3", "Lokhttp3/ResponseBody;", "urlWithQuery", "checkButtonApproval", "Lid/go/bpsfasih/data/local/models/BaseResponseDataStringApproval;", "assignmentId", "checkButtonEdit", "getAssignment", "Lid/go/bpsfasih/data/remote/dto/AssignmentResponse;", "page", "", "getAssignmentAnswer", "Lid/go/bpsfasih/data/remote/dto/AssignmentAnswerResponse;", "getAssignmentNotif", "getPresignS3AnswerAssignment", "Lid/go/bpsfasih/data/local/models/S3Response;", "refreshPresignedGet", "revoke", "submitPresignedGet", "submitPresignedPut", "uploadAssignmentApproval", "uploadAssignmentChangeMode", "surveyPeriodeRoleId", "uploadImageS3", "contentlength", "", "contentMd5", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AssignmentApiService {
    @POST("/mobile/assignment-general/api/mobile/assign-by-selection/{surveyPeriodId}")
    Observable<BaseResponse> assignBySelection(@Path("surveyPeriodId") String surveyPeriodId, @Body RequestBody param);

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/assignment-submit-2/api/assignment/s3/edit")
    Observable<BaseResponseDataUpload> assignmentEditPost(@Body Map<String, String> requestBody);

    @POST("/mobile/assignment-submit-2/api/assignment/s3/edit/presign-url")
    Observable<AssignmentSubmitS3Response> assignmentEditPresigned(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @Headers({"Content-Type: application/json"})
    @POST("/mobile/assignment-submit-2/api/assignment/s3/submit")
    Observable<BaseResponseDataUpload> assignmentSubmitPost(@Body Map<String, String> requestBody);

    @POST("/mobile/assignment-submit-2/api/assignment/s3/presign-url")
    Observable<AssignmentSubmitS3Response> assignmentSubmitPresigned(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @Headers({"Content-Type: application/x-7z-compressed", "No-Authentication: true"})
    @PUT("")
    Observable<ResponseBody> assignmentUploadS3(@Url String urlWithQuery, @Body RequestBody requestBody);

    @Headers({"Content-type: application/json"})
    @POST("/mobile/assignment-approval/api/v2/get-button-approval")
    Observable<BaseResponseDataStringApproval> checkButtonApproval(@Query("assignmentId") String assignmentId);

    @Headers({"Content-type: application/json"})
    @POST("/mobile/assignment-approval/api/v2/get-button-edit")
    Observable<BaseResponseDataStringApproval> checkButtonEdit(@Query("assignmentId") String assignmentId);

    @GET("/mobile/assignment-sync/api/mobile/s3/assignment/datatable")
    Observable<AssignmentResponse> getAssignment(@Query("surveyPeriodId") String surveyPeriodeId, @Query("page") int page);

    @GET("/mobile/assignment-sync/api/mobile/assignment/{assignmentId}/answer")
    Observable<AssignmentAnswerResponse> getAssignmentAnswer(@Path("assignmentId") String assignmentId);

    @POST("/mobile/assignment-sync/api/mobile/s3/assignment/datatable/partial")
    Observable<AssignmentResponse> getAssignmentNotif(@Body RequestBody param);

    @POST("/mobile/assignment-sync/api/mobile/s3/assignment/presign-url?")
    Observable<S3Response> getPresignS3AnswerAssignment(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @POST("/mobile/assignment-sync/api/image/presigned-url-get")
    Observable<S3Response> refreshPresignedGet(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @Headers({"Content-type: application/json"})
    @POST("/mobile/assignment-approval/api/v2/revoke-approval")
    Observable<BaseResponseDataUpload> revoke(@Body RequestBody param);

    @POST("/mobile/assignment-submit-2/api/image/presigned-url-get")
    Observable<S3Response> submitPresignedGet(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @POST("/mobile/assignment-submit-2/api/image/v2/presigned-url-put")
    Observable<S3Response> submitPresignedPut(@Body RequestBody param, @Query("surveyPeriodId") String surveyPeriodeId);

    @Headers({"Content-type: application/json"})
    @POST("/mobile/assignment-approval/api/v2/approval")
    Observable<BaseResponseDataUpload> uploadAssignmentApproval(@Body RequestBody param);

    @POST("/mobile/assignment-submit-2/api/assignment/{userId}/change-mode")
    Observable<BaseResponseDataUpload> uploadAssignmentChangeMode(@Path("userId") String surveyPeriodeRoleId, @Body RequestBody param);

    @Headers({"Content-Type: image/png"})
    @PUT("")
    Observable<ResponseBody> uploadImageS3(@Url String urlWithQuery, @Header("Content-Length") long contentlength, @Header("Content-MD5") String contentMd5, @Body RequestBody requestBody);
}
