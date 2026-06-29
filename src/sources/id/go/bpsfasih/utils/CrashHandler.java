package id.go.bpsfasih.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.CommonCons;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.ClientCookie;

/* compiled from: CrashHandler.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/utils/CrashHandler;", "Ljava/lang/Thread$UncaughtExceptionHandler;", "myContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "uncaughtException", "", "thread", "Ljava/lang/Thread;", "exception", "", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String LINE_SEPARATOR = "\n";
    private final Context myContext;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public CrashHandler(Context context) {
        this.myContext = context;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable exception) throws IOException {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(exception, "exception");
        StringWriter stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        StringBuilder sb = new StringBuilder("************ CAUSE OF ERROR ************\n\n");
        sb.append(stringWriter.toString());
        sb.append("\n************ DEVICE INFORMATION ***********\nBrand: ");
        sb.append(Build.BRAND);
        sb.append("\nDevice: ");
        sb.append(Build.DEVICE);
        sb.append("\nModel: ");
        sb.append(Build.MODEL);
        sb.append("\nId: ");
        sb.append(Build.ID);
        sb.append("\nProduct: ");
        sb.append(Build.PRODUCT);
        sb.append("\n\n************ FIRMWARE ************\nSDK: ");
        sb.append(Build.VERSION.SDK);
        sb.append("\nRelease: ");
        sb.append(Build.VERSION.RELEASE);
        sb.append("\nIncremental: ");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append("\n\n************ APP ************\nCapi Version: ");
        try {
            Context context = this.myContext;
            if (context != null) {
                sb.append(context.getPackageManager().getPackageInfo(this.myContext.getPackageName(), 0).versionName);
            }
        } catch (PackageManager.NameNotFoundException e) {
            INSTANCE.sendExeption(e);
            e.printStackTrace();
            sb.append("Error");
        }
        sb.append(LINE_SEPARATOR);
        Log.e(Constants.IPC_BUNDLE_KEY_SEND_ERROR, sb.toString());
        Companion companion = INSTANCE;
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "errorReport.toString()");
        companion.writeToFile(string, System.currentTimeMillis() + CommonCons.INSTANCE.getEXTENSION_LOG(), Directory.INSTANCE.getABSOLUTE_PATH_ERROR(), new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.CrashHandler.uncaughtException.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
                    Log.d("hanaapt", "sukses file create");
                }
            }
        });
    }

    /* compiled from: CrashHandler.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0002J\u0016\u0010\n\u001a\u00020\u00062\u000e\u0010\u000b\u001a\n\u0018\u00010\bj\u0004\u0018\u0001`\tJC\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042!\u0010\u0010\u001a\u001d\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00060\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lid/go/bpsfasih/utils/CrashHandler$Companion;", "", "()V", "LINE_SEPARATOR", "", "printError", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "sendExeption", "e", "writeToFile", "sources", "fileName", ClientCookie.PATH_ATTR, "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "result", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final void printError(Exception exception) throws IOException {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            StringBuilder sb = new StringBuilder("************ CAUSE OF ERROR ************\n\n");
            sb.append(stringWriter.toString());
            sb.append("\n************ DEVICE INFORMATION ***********\nBrand: ");
            sb.append(Build.BRAND);
            sb.append("\nDevice: ");
            sb.append(Build.DEVICE);
            sb.append("\nModel: ");
            sb.append(Build.MODEL);
            sb.append("\nId: ");
            sb.append(Build.ID);
            sb.append("\nProduct: ");
            sb.append(Build.PRODUCT);
            sb.append("\n\n************ FIRMWARE ************\nSDK: ");
            sb.append(Build.VERSION.SDK);
            sb.append("\nRelease: ");
            sb.append(Build.VERSION.RELEASE);
            sb.append("\nIncremental: ");
            sb.append(Build.VERSION.INCREMENTAL);
            sb.append("\n\n************ APP ************\nCapi Version: ");
            try {
                sb.append(FasihApp.INSTANCE.getContext().getPackageManager().getPackageInfo(FasihApp.INSTANCE.getContext().getPackageName(), 0).versionName);
            } catch (PackageManager.NameNotFoundException e) {
                sendExeption(e);
                e.printStackTrace();
                sb.append("Error");
            }
            sb.append(CrashHandler.LINE_SEPARATOR);
            Log.e("error_handler", sb.toString());
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "errorReport.toString()");
            writeToFile(string, "CH_" + System.currentTimeMillis() + CommonCons.INSTANCE.getEXTENSION_LOG(), Directory.INSTANCE.getABSOLUTE_PATH_ERROR(), new Function1<Boolean, Unit>() { // from class: id.go.bpsfasih.utils.CrashHandler$Companion$printError$1
                public final void invoke(boolean z) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }
            });
        }

        public final void sendExeption(Exception e) {
            if (e != null) {
                printError(e);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void writeToFile(String sources, String fileName, String path, Function1<? super Boolean, Unit> callback) throws IOException {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file.getAbsolutePath(), fileName);
            try {
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
            } catch (Exception e) {
                sendExeption(e);
                e.printStackTrace();
                callback.invoke(false);
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(sources);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.flush();
                fileOutputStream.close();
                callback.invoke(true);
            } catch (FileNotFoundException e2) {
                sendExeption(e2);
                callback.invoke(false);
                e2.printStackTrace();
            } catch (IOException e3) {
                sendExeption(e3);
                e3.printStackTrace();
                callback.invoke(false);
            }
        }
    }
}
