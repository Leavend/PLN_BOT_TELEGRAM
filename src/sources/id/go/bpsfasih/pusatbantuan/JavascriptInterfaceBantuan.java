package id.go.bpsfasih.pusatbantuan;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import androidx.autofill.HintConstants;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavascriptInterfaceBantuan.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u000eJ\n\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0007H\u0007J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0007H\u0007J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lid/go/bpsfasih/pusatbantuan/JavascriptInterfaceBantuan;", "", "_webView", "Landroid/webkit/WebView;", "activity", "Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity;", HintConstants.AUTOFILL_HINT_USERNAME, "", "userId", "token", "assignmentId", "deviceInfo", "firebaseToken", "surveys", "(Landroid/webkit/WebView;Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActivity", "()Lid/go/bpsfasih/pusatbantuan/PusatBantuanActivity;", "assignmetId", "getAssignmentId", "getDeviceInfo", "getFirebaseToken", "getSurveys", "getToken", "getUserId", "getUsername", "sendFile", "", "ticketId", "Companion", "pusatbantuan_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class JavascriptInterfaceBantuan {
    public static final String TAG_HANDLER = "Android";
    private final PusatBantuanActivity activity;
    private String assignmetId;
    private String deviceInfo;
    private String firebaseToken;
    private String surveys;
    private String token;
    private String userId;
    private String username;

    public JavascriptInterfaceBantuan(WebView _webView, PusatBantuanActivity activity, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(_webView, "_webView");
        Intrinsics.checkNotNullParameter(activity, "activity");
        this.activity = activity;
        this.username = str;
        this.userId = str2;
        this.token = str3;
        this.deviceInfo = str5;
        this.assignmetId = str4;
        this.firebaseToken = str6;
        this.surveys = str7;
    }

    public final PusatBantuanActivity getActivity() {
        return this.activity;
    }

    @JavascriptInterface
    public final String getUsername() {
        return this.username;
    }

    @JavascriptInterface
    public final String getUserId() {
        return this.userId;
    }

    @JavascriptInterface
    public final String getToken() {
        return this.token;
    }

    @JavascriptInterface
    public final String getDeviceInfo() {
        return this.deviceInfo;
    }

    @JavascriptInterface
    public final String getFirebaseToken() {
        return this.firebaseToken;
    }

    @JavascriptInterface
    /* renamed from: getAssignmentId, reason: from getter */
    public final String getAssignmetId() {
        return this.assignmetId;
    }

    @JavascriptInterface
    public final String getSurveys() {
        return this.surveys;
    }

    @JavascriptInterface
    public final void sendFile(String ticketId, String assignmentId) {
        Intrinsics.checkNotNullParameter(ticketId, "ticketId");
        this.activity.sendFile(ticketId, assignmentId);
    }
}
