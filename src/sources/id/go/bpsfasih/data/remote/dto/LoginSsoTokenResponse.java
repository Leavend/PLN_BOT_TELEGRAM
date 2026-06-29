package id.go.bpsfasih.data.remote.dto;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginSsoTokenResponse.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J[\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006&"}, d2 = {"Lid/go/bpsfasih/data/remote/dto/LoginSsoTokenResponse;", "", "access_token", "", "expires_in", "", "refresh_expires_in", "refresh_token", "token_type", "session_state", "scope", "errorMessage", "(Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccess_token", "()Ljava/lang/String;", "getErrorMessage", "getExpires_in", "()J", "getRefresh_expires_in", "getRefresh_token", "getScope", "getSession_state", "getToken_type", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class LoginSsoTokenResponse {
    public static final int $stable = 0;
    private final String access_token;
    private final String errorMessage;
    private final long expires_in;
    private final long refresh_expires_in;
    private final String refresh_token;
    private final String scope;
    private final String session_state;
    private final String token_type;

    /* renamed from: component1, reason: from getter */
    public final String getAccess_token() {
        return this.access_token;
    }

    /* renamed from: component2, reason: from getter */
    public final long getExpires_in() {
        return this.expires_in;
    }

    /* renamed from: component3, reason: from getter */
    public final long getRefresh_expires_in() {
        return this.refresh_expires_in;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRefresh_token() {
        return this.refresh_token;
    }

    /* renamed from: component5, reason: from getter */
    public final String getToken_type() {
        return this.token_type;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSession_state() {
        return this.session_state;
    }

    /* renamed from: component7, reason: from getter */
    public final String getScope() {
        return this.scope;
    }

    /* renamed from: component8, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final LoginSsoTokenResponse copy(String access_token, long expires_in, long refresh_expires_in, String refresh_token, String token_type, String session_state, String scope, String errorMessage) {
        Intrinsics.checkNotNullParameter(access_token, "access_token");
        Intrinsics.checkNotNullParameter(refresh_token, "refresh_token");
        Intrinsics.checkNotNullParameter(token_type, "token_type");
        Intrinsics.checkNotNullParameter(session_state, "session_state");
        Intrinsics.checkNotNullParameter(scope, "scope");
        return new LoginSsoTokenResponse(access_token, expires_in, refresh_expires_in, refresh_token, token_type, session_state, scope, errorMessage);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LoginSsoTokenResponse)) {
            return false;
        }
        LoginSsoTokenResponse loginSsoTokenResponse = (LoginSsoTokenResponse) other;
        return Intrinsics.areEqual(this.access_token, loginSsoTokenResponse.access_token) && this.expires_in == loginSsoTokenResponse.expires_in && this.refresh_expires_in == loginSsoTokenResponse.refresh_expires_in && Intrinsics.areEqual(this.refresh_token, loginSsoTokenResponse.refresh_token) && Intrinsics.areEqual(this.token_type, loginSsoTokenResponse.token_type) && Intrinsics.areEqual(this.session_state, loginSsoTokenResponse.session_state) && Intrinsics.areEqual(this.scope, loginSsoTokenResponse.scope) && Intrinsics.areEqual(this.errorMessage, loginSsoTokenResponse.errorMessage);
    }

    public int hashCode() {
        int iHashCode = ((((((((((((this.access_token.hashCode() * 31) + Long.hashCode(this.expires_in)) * 31) + Long.hashCode(this.refresh_expires_in)) * 31) + this.refresh_token.hashCode()) * 31) + this.token_type.hashCode()) * 31) + this.session_state.hashCode()) * 31) + this.scope.hashCode()) * 31;
        String str = this.errorMessage;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        return "LoginSsoTokenResponse(access_token=" + this.access_token + ", expires_in=" + this.expires_in + ", refresh_expires_in=" + this.refresh_expires_in + ", refresh_token=" + this.refresh_token + ", token_type=" + this.token_type + ", session_state=" + this.session_state + ", scope=" + this.scope + ", errorMessage=" + this.errorMessage + ")";
    }

    public LoginSsoTokenResponse(String access_token, long j, long j2, String refresh_token, String token_type, String session_state, String scope, String str) {
        Intrinsics.checkNotNullParameter(access_token, "access_token");
        Intrinsics.checkNotNullParameter(refresh_token, "refresh_token");
        Intrinsics.checkNotNullParameter(token_type, "token_type");
        Intrinsics.checkNotNullParameter(session_state, "session_state");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.access_token = access_token;
        this.expires_in = j;
        this.refresh_expires_in = j2;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.session_state = session_state;
        this.scope = scope;
        this.errorMessage = str;
    }

    public final String getAccess_token() {
        return this.access_token;
    }

    public final long getExpires_in() {
        return this.expires_in;
    }

    public final long getRefresh_expires_in() {
        return this.refresh_expires_in;
    }

    public final String getRefresh_token() {
        return this.refresh_token;
    }

    public final String getToken_type() {
        return this.token_type;
    }

    public final String getSession_state() {
        return this.session_state;
    }

    public final String getScope() {
        return this.scope;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }
}
