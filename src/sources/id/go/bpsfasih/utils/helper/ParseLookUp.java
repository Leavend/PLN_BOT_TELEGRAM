package id.go.bpsfasih.utils.helper;

import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.firebase.messaging.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.data.local.databaseNotRoom.Database;
import id.go.bpsfasih.domain.models.LookUpModel;
import id.go.bpsfasih.utils.CrashHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.cookie.ClientCookie;

/* compiled from: ParseLookUp.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00126\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\u0002\u0010\fJ\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J@\u0010 \u001a\u00020\u000b26\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005H\u0002RA\u0010\u0004\u001a2\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u0006!"}, d2 = {"Lid/go/bpsfasih/utils/helper/ParseLookUp;", "", ClientCookie.PATH_ATTR, "", "callback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", Constants.IPC_BUNDLE_KEY_SEND_ERROR, "message", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "getCallback", "()Lkotlin/jvm/functions/Function2;", "data", "", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "fields", "getFields", "setFields", "lookupModel", "Lid/go/bpsfasih/domain/models/LookUpModel;", "tableName", "getTableName", "()Ljava/lang/String;", "setTableName", "(Ljava/lang/String;)V", "process", "processToDb", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class ParseLookUp {
    public static final int $stable = 8;
    private final Function2<Boolean, String, Unit> callback;
    private List<String> data;
    private List<String> fields;
    private LookUpModel lookupModel;
    private String tableName;

    /* JADX WARN: Multi-variable type inference failed */
    public ParseLookUp(String path, Function2<? super Boolean, ? super String, Unit> callback) throws Throwable {
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
        this.fields = new ArrayList();
        this.data = new ArrayList();
        this.tableName = "";
        process(path);
    }

    public final Function2<Boolean, String, Unit> getCallback() {
        return this.callback;
    }

    public final List<String> getFields() {
        return this.fields;
    }

    public final void setFields(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.fields = list;
    }

    public final List<String> getData() {
        return this.data;
    }

    public final void setData(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    public final String getTableName() {
        return this.tableName;
    }

    public final void setTableName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.tableName = str;
    }

    private final void process(String path) throws Throwable {
        File file = new File(path);
        if (file.exists()) {
            try {
                Reader inputStreamReader = new InputStreamReader(new FileInputStream(file), Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    String text = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                    if (!StringsKt.contains$default((CharSequence) text, (CharSequence) "DROP", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) HttpDelete.METHOD_NAME, false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "INSERT", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "SELECT", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) text, (CharSequence) "UPDATE", false, 2, (Object) null)) {
                        String nameWithoutExtension = FilesKt.getNameWithoutExtension(file);
                        String name = file.getParentFile().getName();
                        Intrinsics.checkNotNullExpressionValue(name, "file.parentFile.name");
                        this.tableName = "lookup_id_" + nameWithoutExtension + "_" + StringsKt.replace$default(name, "-", "_", false, 4, (Object) null);
                        JsonElement jsonElement = new JsonParser().parse(text);
                        JsonObject jsonObject = jsonElement instanceof JsonObject ? (JsonObject) jsonElement : null;
                        if (jsonObject == null) {
                            throw new IllegalStateException("Format lookup tidak valid");
                        }
                        JsonArray asJsonArray = jsonObject.getAsJsonArray("fields");
                        if (asJsonArray == null) {
                            throw new IllegalStateException("Kolom fields pada lookup tidak ditemukan");
                        }
                        JsonArray asJsonArray2 = jsonObject.getAsJsonArray("data");
                        if (asJsonArray2 == null) {
                            throw new IllegalStateException("Kolom data pada lookup tidak ditemukan");
                        }
                        Object objFromJson = new Gson().fromJson((JsonElement) asJsonArray, (Class<Object>) String[].class);
                        Intrinsics.checkNotNullExpressionValue(objFromJson, "Gson().fromJson(\n       …ava\n                    )");
                        this.fields = ArraysKt.toMutableList((Object[]) objFromJson);
                        Object objFromJson2 = new Gson().fromJson((JsonElement) asJsonArray2, (Class<Object>) String[].class);
                        Intrinsics.checkNotNullExpressionValue(objFromJson2, "Gson().fromJson(\n       …ava\n                    )");
                        this.data = ArraysKt.toMutableList((Object[]) objFromJson2);
                        processToDb(new Function2<Boolean, String, Unit>() { // from class: id.go.bpsfasih.utils.helper.ParseLookUp.process.1
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, String str) {
                                invoke(bool.booleanValue(), str);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z, String message) {
                                Intrinsics.checkNotNullParameter(message, "message");
                                ParseLookUp.this.getCallback().invoke(Boolean.valueOf(z), message);
                            }
                        });
                        return;
                    }
                    Log.d("FOUR", "process: tidak ada file indikasi sql injection");
                    this.callback.invoke(true, "File terdapat indikasi sql injection");
                } finally {
                }
            } catch (Exception e) {
                CrashHandler.INSTANCE.sendExeption(e);
                Log.e("ParseLookUp", "Gagal memproses lookup " + file.getPath(), e);
                this.callback.invoke(true, "Ada kesalahan pada data lookup (" + file.getName() + ")");
            }
        } else {
            Log.d("FOUR", "process: tidak ada file " + file.getPath());
            this.callback.invoke(true, "File tidak ditemukan (" + file.getPath() + ")");
        }
    }

    private final void processToDb(Function2<? super Boolean, ? super String, Unit> callback) throws Throwable {
        LookUpModel lookUpModel = new LookUpModel(this.fields, this.data, this.tableName);
        this.lookupModel = lookUpModel;
        try {
            if (FasihApp.INSTANCE.getDatabase().isTableExists(lookUpModel.getTableName())) {
                Database database = FasihApp.INSTANCE.getDatabase();
                String tableName = lookUpModel.getTableName();
                Intrinsics.checkNotNull(tableName);
                database.deleteTable(tableName);
            }
            FasihApp.INSTANCE.getDatabase().create(lookUpModel);
            if (lookUpModel.getData() != null && lookUpModel.getTableName() != null) {
                String tableName2 = lookUpModel.getTableName();
                if (!(tableName2 == null || tableName2.length() == 0)) {
                    List<String> data = lookUpModel.getData();
                    if (!(data == null || data.isEmpty())) {
                        Database database2 = FasihApp.INSTANCE.getDatabase();
                        String tableName3 = lookUpModel.getTableName();
                        Intrinsics.checkNotNull(tableName3);
                        List<String> data2 = lookUpModel.getData();
                        Intrinsics.checkNotNull(data2);
                        List<String> fields = lookUpModel.getFields();
                        Intrinsics.checkNotNull(fields);
                        database2.insert(tableName3, data2, fields);
                    }
                }
            }
            this.lookupModel = null;
            this.data = new ArrayList();
            callback.invoke(false, "Success");
        } catch (Exception e) {
            CrashHandler.INSTANCE.sendExeption(e);
            e.printStackTrace();
            String message = e.getMessage();
            if (message == null) {
                message = "Error proses insert lookup pada database";
            }
            callback.invoke(true, message);
        }
    }
}
