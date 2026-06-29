package id.go.bpsfasih.ui.tarikSampelOffline;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import id.go.bpsfasih.data.CommonCons;
import id.go.bpsfasih.data.local.entities.AssignmentEntity;
import id.go.bpsfasih.utils.CrashHandler;
import id.go.bpsfasih.utils.Directory;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.HttpUrl;

/* compiled from: TarikSampleOfflineViewModel.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lid/go/bpsfasih/ui/tarikSampelOffline/TarikSampleOfflineViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "insertAssignmentJson", "", "assignmentEntity", "Lid/go/bpsfasih/data/local/entities/AssignmentEntity;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class TarikSampleOfflineViewModel extends ViewModel {
    public static final int $stable = 0;

    public final void insertAssignmentJson(AssignmentEntity assignmentEntity) {
        Intrinsics.checkNotNullParameter(assignmentEntity, "assignmentEntity");
        String str = Directory.INSTANCE.getABSOLUTEPATHENV() + File.separator + "assignment_listing" + CommonCons.INSTANCE.getEXTENSION_JSON();
        try {
            File file = new File(Directory.INSTANCE.getABSOLUTEPATHENV(), "");
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(str);
            if (!file2.exists()) {
                try {
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                        outputStreamWriter.write(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                        outputStreamWriter.flush();
                        outputStreamWriter.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e) {
                        CrashHandler.INSTANCE.sendExeption(e);
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                }
                file2.createNewFile();
            }
            Object objFromJson = new Gson().fromJson(FilesKt.readText$default(file2, null, 1, null), (Class<Object>) AssignmentEntity[].class);
            Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(jsonCont…nmentEntity>::class.java)");
            List mutableList = ArraysKt.toMutableList((Object[]) objFromJson);
            mutableList.add(assignmentEntity);
            String jsonString = new Gson().toJson(mutableList);
            FileHelper.Companion companion = FileHelper.INSTANCE;
            String absolutepathenv = Directory.INSTANCE.getABSOLUTEPATHENV();
            Intrinsics.checkNotNullExpressionValue(jsonString, "jsonString");
            companion.writeFile(absolutepathenv, "assignment_listing.json", jsonString);
        } catch (Exception unused) {
        }
    }
}
