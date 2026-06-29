package okhttp3.internal.http;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import okhttp3.Interceptor;

/* compiled from: CallServerInterceptor.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes3.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e0 A[Catch: IOException -> 0x0188, TryCatch #1 {IOException -> 0x0188, blocks: (B:39:0x00ab, B:41:0x00b4, B:42:0x00b8, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x0130, B:56:0x013e, B:63:0x0154, B:66:0x0163, B:67:0x0187, B:58:0x0149, B:53:0x0120), top: B:77:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0120 A[Catch: IOException -> 0x0188, TryCatch #1 {IOException -> 0x0188, blocks: (B:39:0x00ab, B:41:0x00b4, B:42:0x00b8, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x0130, B:56:0x013e, B:63:0x0154, B:66:0x0163, B:67:0x0187, B:58:0x0149, B:53:0x0120), top: B:77:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0149 A[Catch: IOException -> 0x0188, TryCatch #1 {IOException -> 0x0188, blocks: (B:39:0x00ab, B:41:0x00b4, B:42:0x00b8, B:44:0x00e0, B:46:0x00e9, B:47:0x00ec, B:48:0x0110, B:52:0x011b, B:54:0x0130, B:56:0x013e, B:63:0x0154, B:66:0x0163, B:67:0x0187, B:58:0x0149, B:53:0x0120), top: B:77:0x00ab }] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ab A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v21, types: [okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    @Override // okhttp3.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okhttp3.Response intercept(okhttp3.Interceptor.Chain r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 407
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.CallServerInterceptor.intercept(okhttp3.Interceptor$Chain):okhttp3.Response");
    }
}
