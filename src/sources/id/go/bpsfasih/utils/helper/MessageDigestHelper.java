package id.go.bpsfasih.utils.helper;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.codekidlabs.storagechooser.StorageChooser;
import kotlin.Metadata;

/* compiled from: MessageDigestHelper.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/utils/helper/MessageDigestHelper;", "", "()V", "getMD5", "", StorageChooser.FILE_PICKER, "Ljava/io/File;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class MessageDigestHelper {
    public static final int $stable = 0;
    public static final MessageDigestHelper INSTANCE = new MessageDigestHelper();

    private MessageDigestHelper() {
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String getMD5(java.io.File r4) throws java.lang.Throwable {
        /*
            r3 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L29
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L27 java.io.IOException -> L29
            r4 = r1
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            byte[] r4 = org.apache.commons.codec.digest.DigestUtils.md5(r4)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            char[] r4 = org.apache.commons.codec.binary.Hex.encodeHex(r4)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            java.lang.String r2 = "encodeHex(md)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            java.lang.String r2 = new java.lang.String     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            r2.<init>(r4)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L34
            r1.close()
            r0 = r2
            goto L33
        L25:
            r4 = move-exception
            goto L2b
        L27:
            r4 = move-exception
            goto L36
        L29:
            r4 = move-exception
            r1 = r0
        L2b:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L33
            r1.close()
        L33:
            return r0
        L34:
            r4 = move-exception
            r0 = r1
        L36:
            if (r0 == 0) goto L3b
            r0.close()
        L3b:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.helper.MessageDigestHelper.getMD5(java.io.File):java.lang.String");
    }
}
