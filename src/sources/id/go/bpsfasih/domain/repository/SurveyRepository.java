package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.data.remote.dto.AllSurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyResponse;
import id.go.bpsfasih.data.remote.dto.SurveyRolesResponse;
import id.go.bpsfasih.data.remote.dto.UserRolesResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: SurveyRepository.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JW\u0010\u0002\u001a\u00020\u00032M\u0010\u0004\u001aI\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0005H&JM\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\f2#\u0010\u0013\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0015¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00030\u0014H&J-\u0010\u0016\u001a\u00020\u00032#\u0010\u0017\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0018¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00030\u0014H&J5\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\f2#\u0010\u0013\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u001b¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00030\u0014H&¨\u0006\u001c"}, d2 = {"Lid/go/bpsfasih/domain/repository/SurveyRepository;", "", "checkSurveys", "", "surveysCallback", "Lkotlin/Function3;", "Lid/go/bpsfasih/data/remote/dto/AllSurveyResponse;", "Lkotlin/ParameterName;", "name", "result", "", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "", "errorMessage", "getListUserByPeriodRoleRegion", "periodeId", "surveyRoleId", "parentUserId", "smallRegionFullCode", "callback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/UserRolesResponse;", "getNewSurvey", "surveyCallback", "Lid/go/bpsfasih/data/remote/dto/SurveyResponse;", "getSurveyRole", "surveyId", "Lid/go/bpsfasih/data/remote/dto/SurveyRolesResponse;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface SurveyRepository {
    void checkSurveys(Function3<? super AllSurveyResponse, ? super Boolean, ? super String, Unit> surveysCallback);

    void getListUserByPeriodRoleRegion(String periodeId, String surveyRoleId, String parentUserId, String smallRegionFullCode, Function1<? super UserRolesResponse, Unit> callback);

    void getNewSurvey(Function1<? super SurveyResponse, Unit> surveyCallback);

    void getSurveyRole(String surveyId, Function1<? super SurveyRolesResponse, Unit> callback);
}
