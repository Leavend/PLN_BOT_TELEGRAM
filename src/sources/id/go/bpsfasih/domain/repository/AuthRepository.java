package id.go.bpsfasih.domain.repository;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.data.remote.dto.LoginSsoTokenResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: AuthRepository.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&J5\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&J-\u0010\r\u001a\u00020\u00032#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&J-\u0010\u000e\u001a\u00020\u00032#\u0010\u0006\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00030\u0007H&¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/domain/repository/AuthRepository;", "", "loginSsoEksternal", "", "code", "", "loginCallback", "Lkotlin/Function1;", "Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "Lkotlin/ParameterName;", "name", "result", "loginSsoInternal", "loginSsoRefreshTokenEksternal", "loginSsoRefreshTokenInternal", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public interface AuthRepository {
    void loginSsoEksternal(String code, Function1<? super LoginSsoTokenResponse, Unit> loginCallback);

    void loginSsoInternal(String code, Function1<? super LoginSsoTokenResponse, Unit> loginCallback);

    void loginSsoRefreshTokenEksternal(Function1<? super LoginSsoTokenResponse, Unit> loginCallback);

    void loginSsoRefreshTokenInternal(Function1<? super LoginSsoTokenResponse, Unit> loginCallback);
}
