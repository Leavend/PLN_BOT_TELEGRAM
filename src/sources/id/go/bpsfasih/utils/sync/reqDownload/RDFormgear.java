package id.go.bpsfasih.utils.sync.reqDownload;

import androidx.constraintlayout.widget.ConstraintLayout;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.tools.Downloader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import net.lingala.zip4j.util.InternalZipConstants;

/* compiled from: RDFormgear.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00126\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000RA\u0010\u0005\u001a2\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\f0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lid/go/bpsfasih/utils/sync/reqDownload/RDFormgear;", "", "url", "", "formgearId", "myCallback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "result", "message", "", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "mFormgearId", "getMyCallback", "()Lkotlin/jvm/functions/Function2;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class RDFormgear {
    public static final int $stable = 0;
    private final String mFormgearId;
    private final Function2<Boolean, String, Unit> myCallback;

    /* JADX WARN: Multi-variable type inference failed */
    public RDFormgear(String url, final String formgearId, Function2<? super Boolean, ? super String, Unit> myCallback) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(formgearId, "formgearId");
        Intrinsics.checkNotNullParameter(myCallback, "myCallback");
        this.myCallback = myCallback;
        this.mFormgearId = formgearId;
        Downloader.INSTANCE.requestFormgear(url, new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.sync.reqDownload.RDFormgear.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) throws IOException {
                invoke(bool.booleanValue(), str);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, String message) throws IOException {
                Intrinsics.checkNotNullParameter(message, "message");
                if (z) {
                    File file = new File(Directory.INSTANCE.getABSOLUTEPATH() + (formgearId.equals("1") ? Directory.INSTANCE.getFORMENGINE_FILE_TEMP() : "fasih-form"));
                    File file2 = new File(Directory.INSTANCE.getFORMENGINE_PATH() + InternalZipConstants.ZIP_FILE_SEPARATOR + this.mFormgearId);
                    if (!file2.exists()) {
                        file2.mkdirs();
                    }
                    if (file.isDirectory()) {
                        File[] fileArrListFiles = file.listFiles();
                        if (fileArrListFiles != null) {
                            for (File file3 : fileArrListFiles) {
                                if (file3 != null) {
                                    Intrinsics.checkNotNullExpressionValue(file3, "file");
                                    File file4 = new File(file2, file3.getName());
                                    if (!file3.renameTo(file4)) {
                                        try {
                                            Files.copy(file3.toPath(), file4.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                            file3.delete();
                                        } catch (IOException unused) {
                                        }
                                    }
                                }
                            }
                        }
                        FilesKt.deleteRecursively(file);
                        file.delete();
                    }
                }
                this.getMyCallback().invoke(Boolean.valueOf(z), message);
            }
        });
    }

    public final Function2<Boolean, String, Unit> getMyCallback() {
        return this.myCallback;
    }
}
