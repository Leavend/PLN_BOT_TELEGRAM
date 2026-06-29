package id.go.bpsfasih.utils.helper;

import android.app.Activity;
import android.app.Application;
import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.databaseRoom.SurveyRoomDatabase;
import id.go.bpsfasih.data.local.entities.FieldOfficerEntity;
import id.go.bpsfasih.data.local.repository.FieldOfficerRepository;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import id.go.bpsfasih.utils.JWTUtils;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* compiled from: SsoHelper.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(c = "id.go.bpsfasih.utils.helper.SsoHelper$Companion$handleResponseSso$1", f = "SsoHelper.kt", i = {0, 0}, l = {190}, m = "invokeSuspend", n = {"repository", "data"}, s = {"L$0", "L$1"})
/* loaded from: classes2.dex */
final class SsoHelper$Companion$handleResponseSso$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Function1<Boolean, Unit> $callBack;
    final /* synthetic */ LoginSsoTokenResponse $response;
    final /* synthetic */ long $timeNow;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    SsoHelper$Companion$handleResponseSso$1(Activity activity, LoginSsoTokenResponse loginSsoTokenResponse, long j, Function1<? super Boolean, Unit> function1, Continuation<? super SsoHelper$Companion$handleResponseSso$1> continuation) {
        super(2, continuation);
        this.$activity = activity;
        this.$response = loginSsoTokenResponse;
        this.$timeNow = j;
        this.$callBack = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SsoHelper$Companion$handleResponseSso$1(this.$activity, this.$response, this.$timeNow, this.$callBack, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SsoHelper$Companion$handleResponseSso$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Exception {
        FieldOfficerRepository fieldOfficerRepository;
        Object syncedUser;
        FieldOfficerEntity fieldOfficerEntity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SurveyRoomDatabase.Companion companion = SurveyRoomDatabase.INSTANCE;
            Application application = this.$activity.getApplication();
            Intrinsics.checkNotNullExpressionValue(application, "activity.application");
            fieldOfficerRepository = new FieldOfficerRepository(companion.getDatabase(application).fieldOfficerDAO());
            JSONObject data = new JWTUtils().getData(this.$response.getAccess_token());
            String string = data.getString("sub");
            Intrinsics.checkNotNullExpressionValue(string, "profil.getString(\"sub\")");
            FieldOfficerEntity fieldOfficerEntity2 = new FieldOfficerEntity(string, null, data.getString("name"), data.getString("email"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "null", false);
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_TOKEN_TYPE(), this.$response.getToken_type());
            long j = 1000;
            FasihApp.INSTANCE.getSession().createSessionLong(CommonCons.INSTANCE.getSESSION_EXPIRED(), data.getLong("exp") * j);
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_ACCESS_TOKEN(), this.$response.getAccess_token());
            FasihApp.INSTANCE.getSession().createSessionLong(CommonCons.INSTANCE.getSESSION_REFRESH_EXPIRED(), (this.$response.getRefresh_expires_in() * j) + this.$timeNow);
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_ACCESS_TOKEN(), this.$response.getRefresh_token());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_STATE(), this.$response.getSession_state());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_AUTH(), this.$response.getToken_type() + " " + this.$response.getAccess_token());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_ID(), fieldOfficerEntity2.getId());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_NIK(), fieldOfficerEntity2.getNik());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_REGENCY_ID(), fieldOfficerEntity2.getRegencyId());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_USERNAME(), fieldOfficerEntity2.getUsername());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_FIRST_NAME(), fieldOfficerEntity2.getFirstName());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_LAST_NAME(), fieldOfficerEntity2.getLastName());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_PHONE(), fieldOfficerEntity2.getPhoneNumber());
            FasihApp.INSTANCE.getSession().createSessionString(CommonCons.INSTANCE.getSESSION_EMAIL(), fieldOfficerEntity2.getEmail());
            this.L$0 = fieldOfficerRepository;
            this.L$1 = fieldOfficerEntity2;
            this.label = 1;
            syncedUser = fieldOfficerRepository.getSyncedUser(fieldOfficerEntity2.getId(), this);
            if (syncedUser == coroutine_suspended) {
                return coroutine_suspended;
            }
            fieldOfficerEntity = fieldOfficerEntity2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            fieldOfficerEntity = (FieldOfficerEntity) this.L$1;
            fieldOfficerRepository = (FieldOfficerRepository) this.L$0;
            ResultKt.throwOnFailure(obj);
            syncedUser = obj;
        }
        fieldOfficerEntity.setSynced(((Boolean) syncedUser).booleanValue());
        fieldOfficerRepository.insert(fieldOfficerEntity);
        this.$callBack.invoke(Boxing.boxBoolean(true));
        return Unit.INSTANCE;
    }
}
