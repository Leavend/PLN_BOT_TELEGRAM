package id.go.bpsfasih.utils;

import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.codec.language.Soundex;

/* compiled from: FileUtil.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lid/go/bpsfasih/utils/FileUtil;", "", "()V", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class FileUtil {
    public static final int $stable = 0;
    private static final String DEFAULT_FILE_EXTENSION = ".jpg";
    private static final String DEFAULT_FILE_NAME = "attachment.jpg";
    private static final String DEFAULT_TEMP_FILE_PREFIX = "attachment";
    private static final int MIN_TEMP_FILE_PREFIX_LENGTH = 3;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int EOF = -1;
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    /* compiled from: FileUtil.kt */
    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J \u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0002\u0010\u001fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lid/go/bpsfasih/utils/FileUtil$Companion;", "", "()V", "DEFAULT_BUFFER_SIZE", "", "DEFAULT_FILE_EXTENSION", "", "DEFAULT_FILE_NAME", "DEFAULT_TEMP_FILE_PREFIX", "EOF", "MIN_TEMP_FILE_PREFIX_LENGTH", "copy", "", "input", "Ljava/io/InputStream;", "output", "Ljava/io/OutputStream;", "ensureValidPrefix", "prefix", "from", "Ljava/io/File;", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "getFileName", "resolveSuffix", "suffix", "splitFileName", "", "fileName", "(Ljava/lang/String;)[Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final File from(Context context, Uri uri) throws IOException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(uri, "uri");
            InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
            if (inputStreamOpenInputStream == null) {
                return null;
            }
            String[] strArrSplitFileName = splitFileName(getFileName(context, uri));
            File fileCreateTempFile = File.createTempFile(ensureValidPrefix(strArrSplitFileName[0]), resolveSuffix(context, uri, strArrSplitFileName[1]), context.getCacheDir());
            fileCreateTempFile.deleteOnExit();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(fileCreateTempFile);
                try {
                    FileUtil.INSTANCE.copy(inputStreamOpenInputStream, fileOutputStream);
                    CloseableKt.closeFinally(fileOutputStream, null);
                    return fileCreateTempFile;
                } finally {
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } finally {
                inputStreamOpenInputStream.close();
            }
        }

        private final String[] splitFileName(String fileName) {
            String strSubstring;
            String string = fileName != null ? StringsKt.trim((CharSequence) fileName).toString() : null;
            String str = string;
            String str2 = str == null || str.length() == 0 ? null : string;
            if (str2 == null) {
                str2 = FileUtil.DEFAULT_FILE_NAME;
            }
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) str2, ".", 0, false, 6, (Object) null);
            if (iLastIndexOf$default != -1) {
                String strSubstring2 = str2.substring(0, iLastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
                strSubstring = str2.substring(iLastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
                str2 = strSubstring2;
            } else {
                strSubstring = "";
            }
            String[] strArr = new String[2];
            strArr[0] = str2;
            strArr[1] = strSubstring != null ? strSubstring : "";
            return strArr;
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private final java.lang.String getFileName(android.content.Context r10, android.net.Uri r11) {
            /*
                r9 = this;
                java.lang.String r0 = r11.getScheme()
                java.lang.String r1 = "content"
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                r1 = -1
                r2 = 0
                if (r0 == 0) goto L49
                android.content.ContentResolver r3 = r10.getContentResolver()
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r4 = r11
                android.database.Cursor r10 = r3.query(r4, r5, r6, r7, r8)
                if (r10 == 0) goto L42
                boolean r0 = r10.moveToFirst()     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
                if (r0 == 0) goto L42
                java.lang.String r0 = "_display_name"
                int r0 = r10.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
                if (r0 == r1) goto L42
                java.lang.String r0 = r10.getString(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Exception -> L32
                goto L43
            L30:
                r11 = move-exception
                goto L3c
            L32:
                r0 = move-exception
                r0.printStackTrace()     // Catch: java.lang.Throwable -> L30
                if (r10 == 0) goto L49
                r10.close()
                goto L49
            L3c:
                if (r10 == 0) goto L41
                r10.close()
            L41:
                throw r11
            L42:
                r0 = r2
            L43:
                if (r10 == 0) goto L4a
                r10.close()
                goto L4a
            L49:
                r0 = r2
            L4a:
                if (r0 != 0) goto L7f
                java.lang.String r10 = r11.getLastPathSegment()
                if (r10 != 0) goto L56
                java.lang.String r10 = r11.getPath()
            L56:
                r0 = r10
                if (r0 == 0) goto L6c
                r3 = r0
                java.lang.CharSequence r3 = (java.lang.CharSequence) r3
                java.lang.String r4 = java.io.File.separator
                java.lang.String r10 = "separator"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r10)
                r5 = 0
                r6 = 0
                r7 = 6
                r8 = 0
                int r10 = kotlin.text.StringsKt.lastIndexOf$default(r3, r4, r5, r6, r7, r8)
                goto L6d
            L6c:
                r10 = r1
            L6d:
                if (r10 == r1) goto L7f
                if (r0 == 0) goto L7e
                int r10 = r10 + 1
                java.lang.String r10 = r0.substring(r10)
                java.lang.String r11 = "this as java.lang.String).substring(startIndex)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r11)
                r0 = r10
                goto L7f
            L7e:
                r0 = r2
            L7f:
                if (r0 == 0) goto L8d
                r10 = r0
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10
                boolean r10 = kotlin.text.StringsKt.isBlank(r10)
                if (r10 != 0) goto L8b
                r2 = r0
            L8b:
                if (r2 != 0) goto L8f
            L8d:
                java.lang.String r2 = "attachment.jpg"
            L8f:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.utils.FileUtil.Companion.getFileName(android.content.Context, android.net.Uri):java.lang.String");
        }

        private final String ensureValidPrefix(String prefix) {
            String strTrim = StringsKt.trim(new Regex("[^A-Za-z0-9._-]").replace(StringsKt.trim((CharSequence) prefix).toString(), "_"), '_', '.', Soundex.SILENT_MARKER);
            if (strTrim.length() >= 3) {
                return strTrim;
            }
            return strTrim.length() > 0 ? StringsKt.padEnd(strTrim, 3, '_') : FileUtil.DEFAULT_TEMP_FILE_PREFIX;
        }

        private final String resolveSuffix(Context context, Uri uri, String suffix) {
            if (StringsKt.startsWith$default(suffix, ".", false, 2, (Object) null) && suffix.length() > 1) {
                return suffix;
            }
            String extensionFromMimeType = MimeTypeMap.getSingleton().getExtensionFromMimeType(context.getContentResolver().getType(uri));
            String str = extensionFromMimeType;
            return !(str == null || StringsKt.isBlank(str)) ? "." + extensionFromMimeType : FileUtil.DEFAULT_FILE_EXTENSION;
        }

        private final long copy(InputStream input, OutputStream output) throws IOException {
            byte[] bArr = new byte[FileUtil.DEFAULT_BUFFER_SIZE];
            long j = 0;
            while (true) {
                int i = FileUtil.EOF;
                int i2 = input.read(bArr);
                Unit unit = Unit.INSTANCE;
                if (i == i2) {
                    return j;
                }
                output.write(bArr, 0, i2);
                j += i2;
            }
        }
    }
}
