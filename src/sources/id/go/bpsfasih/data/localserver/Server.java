package id.go.bpsfasih.data.localserver;

import android.net.Uri;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.FasihApp;
import id.go.bpsfasih.utils.dbHelper.DataSurvey;
import id.go.bpsfasih.utils.helper.FileHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import net.lingala.zip4j.util.InternalZipConstants;
import okhttp3.HttpUrl;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.tileprovider.modules.DatabaseFileArchive;

/* compiled from: Server.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lid/go/bpsfasih/data/localserver/Server;", "", "()V", "Companion", "HttpResponseThread", "HttpServerThread", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Server {
    public static final int $stable = 0;
    public static final int HttpServerPORT = 3310;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static Object reqObject = new Object();

    /* compiled from: Server.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lid/go/bpsfasih/data/localserver/Server$Companion;", "", "()V", "HttpServerPORT", "", "reqObject", "Ljava/lang/Object;", "getReqObject", "()Ljava/lang/Object;", "setReqObject", "(Ljava/lang/Object;)V", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Object getReqObject() {
            return Server.reqObject;
        }

        public final void setReqObject(Object obj) {
            Intrinsics.checkNotNullParameter(obj, "<set-?>");
            Server.reqObject = obj;
        }
    }

    /* compiled from: Server.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lid/go/bpsfasih/data/localserver/Server$HttpServerThread;", "Ljava/lang/Thread;", "()V", "run", "", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class HttpServerThread extends Thread {
        public static final int $stable = 0;

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            try {
                while (true) {
                    Socket socketAccept = new ServerSocket(Server.HttpServerPORT).accept();
                    Log.d("server", "# from " + socketAccept.getInetAddress() + ":" + socketAccept.getPort() + "\n");
                    new HttpResponseThread(socketAccept).run();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: Server.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u000f"}, d2 = {"Lid/go/bpsfasih/data/localserver/Server$HttpResponseThread;", "Ljava/lang/Thread;", "socket", "Ljava/net/Socket;", "(Ljava/net/Socket;)V", "getSocket", "()Ljava/net/Socket;", "setSocket", "run", "", "setColumn", "Lorg/json/JSONObject;", MessageBundle.TITLE_ENTRY, "", "data", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class HttpResponseThread extends Thread {
        public static final int $stable = 8;
        private Socket socket;

        public final Socket getSocket() {
            return this.socket;
        }

        public final void setSocket(Socket socket) {
            this.socket = socket;
        }

        public HttpResponseThread(Socket socket) {
            this.socket = socket;
        }

        /* JADX WARN: Type inference failed for: r0v27, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r3v15, types: [T, java.lang.String] */
        /* JADX WARN: Type inference failed for: r3v16, types: [T, java.util.List] */
        /* JADX WARN: Type inference failed for: r3v18, types: [T, id.go.bpsfasih.data.local.repository.AssignmentRepository] */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() throws IOException {
            Socket socket = this.socket;
            if (socket != null) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                    String line = new BufferedReader(new InputStreamReader(inputStream)).readLine();
                    printWriter.println("HTTP/1.0 200 OK");
                    printWriter.println("Content-Type: application/json; charset=utf-8");
                    printWriter.println("Server: MINISERVER");
                    printWriter.println("Access-Control-Allow-Methods: GET, PUT, POST, DELETE, OPTIONS");
                    printWriter.println("Access-Control-Max-Age: 1000");
                    printWriter.println(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN);
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("");
                    synchronized (Server.INSTANCE.getReqObject()) {
                        if (line != null) {
                            if (StringsKt.contains$default((CharSequence) line, (CharSequence) "get-list-assignments", false, 2, (Object) null)) {
                                String strSubstringBefore$default = StringsKt.substringBefore$default(StringsKt.substringAfter$default(line, "get-list-assignments/", (String) null, 2, (Object) null), " HTTP/1.1", (String) null, 2, (Object) null);
                                if (StringsKt.split$default((CharSequence) strSubstringBefore$default, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).size() == 5) {
                                    String str = (String) StringsKt.split$default((CharSequence) strSubstringBefore$default, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).get(1);
                                    String str2 = (String) StringsKt.split$default((CharSequence) strSubstringBefore$default, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).get(2);
                                    String str3 = (String) StringsKt.split$default((CharSequence) strSubstringBefore$default, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).get(3);
                                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                                    objectRef.element = URLDecoder.decode((String) StringsKt.split$default((CharSequence) strSubstringBefore$default, new String[]{InternalZipConstants.ZIP_FILE_SEPARATOR}, false, 0, 6, (Object) null).get(4), "UTF-8");
                                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                    objectRef2.element = CollectionsKt.emptyList();
                                    Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                                    objectRef3.element = DataSurvey.Assignment.INSTANCE.getAssignmentRepository();
                                    String dataSort = FileHelper.INSTANCE.readDataSort(str);
                                    Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
                                    objectRef4.element = "";
                                    Ref.IntRef intRef = new Ref.IntRef();
                                    intRef.element = -1;
                                    Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
                                    if (dataSort != null) {
                                        JsonObject asJsonObject = new JsonParser().parse(dataSort).getAsJsonObject();
                                        intRef.element = asJsonObject.get("column").getAsInt() + 1;
                                        int asInt = asJsonObject.get("secara").getAsInt();
                                        int asInt2 = asJsonObject.get("sebagai").getAsInt();
                                        if (intRef.element > 0) {
                                            objectRef4.element = "ORDER BY " + (asInt2 == 0 ? new StringBuilder("data").append(intRef.element) : new StringBuilder("CAST(data").append(intRef.element).append(" AS int)")).toString() + " " + (asInt == 0 ? "ASC" : "DESC");
                                        }
                                    }
                                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new Server$HttpResponseThread$run$1$1$1(str3, objectRef5, intRef, this, objectRef, objectRef2, objectRef3, str2, str, objectRef4, printWriter, null), 3, null);
                                }
                            } else if (StringsKt.contains$default((CharSequence) line, (CharSequence) "lookup", false, 2, (Object) null)) {
                                Log.d("FASIH FORM", line);
                                Uri uri = Uri.parse(StringsKt.substringBefore$default(line, "c=", (String) null, 2, (Object) null));
                                Intrinsics.checkNotNullExpressionValue(uri, "parse(line.substringBefore(\"c=\"))");
                                String queryParameter = uri.getQueryParameter(DownloadModel.ID);
                                String queryParameter2 = uri.getQueryParameter("v");
                                String strSubstringBefore$default2 = StringsKt.substringBefore$default(StringsKt.substringAfter$default(line, "c=", (String) null, 2, (Object) null), " HTTP", (String) null, 2, (Object) null);
                                new JsonArray();
                                JsonObject jsonObject = new JsonObject();
                                new JsonArray();
                                HashMap<String, String> map = new HashMap<>();
                                try {
                                    if (!strSubstringBefore$default2.equals(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI)) {
                                        JsonArray asJsonArray = new JsonParser().parse(URLDecoder.decode(strSubstringBefore$default2, "UTF-8")).getAsJsonArray();
                                        Intrinsics.checkNotNullExpressionValue(asJsonArray, "JsonParser().parse(URLDe…ng, \"UTF-8\")).asJsonArray");
                                        for (JsonElement jsonElement : asJsonArray) {
                                            map.put(jsonElement.getAsJsonObject().get(DatabaseFileArchive.COLUMN_KEY).getAsString(), jsonElement.getAsJsonObject().get("value").getAsString());
                                        }
                                    }
                                    try {
                                        JsonArray lookup = FasihApp.INSTANCE.getDatabase().getLookup("lookup_id_" + queryParameter2 + "_" + (queryParameter != null ? StringsKt.replace$default(queryParameter, "-", "_", false, 4, (Object) null) : null), map);
                                        jsonObject.add(FirebaseAnalytics.Param.SUCCESS, new JsonParser().parse("true"));
                                        jsonObject.add("data", lookup);
                                        printWriter.println(jsonObject);
                                    } catch (Exception e) {
                                        String message = e.getMessage();
                                        Intrinsics.checkNotNull(message);
                                        Log.d("LOOKUP_ERROR", message);
                                    }
                                } catch (IOException e2) {
                                    String message2 = e2.getMessage();
                                    Intrinsics.checkNotNull(message2);
                                    Log.d("LOOKUP_ERROR", message2);
                                    jsonObject.add(FirebaseAnalytics.Param.SUCCESS, new JsonParser().parse("false"));
                                    printWriter.println(jsonObject);
                                }
                                printWriter.close();
                            }
                            Unit unit = Unit.INSTANCE;
                        } else {
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                } catch (IOException e3) {
                    e3.printStackTrace();
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final JSONObject setColumn(String title, String data) throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(MessageBundle.TITLE_ENTRY, title);
            jSONObject.put("data", data);
            return jSONObject;
        }
    }
}
