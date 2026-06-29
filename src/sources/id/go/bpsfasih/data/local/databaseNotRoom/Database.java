package id.go.bpsfasih.data.local.databaseNotRoom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.gms.actions.SearchIntents;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.kdownloader.database.DownloadModel;
import id.go.bpsfasih.domain.models.LookUpModel;
import id.go.bpsfasih.ui.formGear.FormGearActivity;
import id.go.bpsfasih.utils.CrashHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: Database.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 >2\u00020\u0001:\u0001>B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\f\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u000e\u001a\u00020\b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bJ\u000e\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0002J\u0011\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000bH\u0086\u0002JN\u0010\u001d\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b2\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010$2\u0006\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020\rH\u0086\u0002¢\u0006\u0002\u0010(J4\u0010)\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000b2\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010*J$\u0010+\u001a\u00020,2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010-J\u0010\u0010.\u001a\u00020\u000b2\u0006\u0010/\u001a\u00020\u000bH\u0002J\u0016\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u000bJ*\u00100\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b022\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000b02J\u0016\u00100\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020 J\u0010\u00104\u001a\u00020\u00182\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\u001aH\u0016J \u00107\u001a\u00020\b2\u0006\u00106\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\r2\u0006\u00109\u001a\u00020\rH\u0016J\b\u0010:\u001a\u00020\bH\u0002J\u001e\u0010;\u001a\u00020\u00142\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020=2\u0006\u0010\u0015\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006?"}, d2 = {"Lid/go/bpsfasih/data/local/databaseNotRoom/Database;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "DELETE_ALL_TABLE", "", "clearAll", "tableName", "", "countRowTableExists", "", "create", "lookUpModels", "Lid/go/bpsfasih/domain/models/LookUpModel;", "surveyPeriodeId", "", "delete", "", "whereClause", "deleteTable", "existsColumnInTable", "", "inDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "inTable", "columnToCheck", "get", "Landroid/database/Cursor;", SearchIntents.EXTRA_QUERY, "Lorg/json/JSONArray;", "value", "desc", "condition", "", "param", "dataKey", "formType", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Lorg/json/JSONArray;", "getLookUp", "Ljava/util/LinkedHashMap;", "getLookup", "Lcom/google/gson/JsonArray;", "Ljava/util/HashMap;", "getType", "type", "insert", "values", "", "field", "isTableExists", "onCreate", "db", "onUpgrade", "oldVersion", "newVersion", "openDB", "update", "contentValues", "Landroid/content/ContentValues;", "Companion", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Database extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 31;
    private static SQLiteDatabase db;
    private final Context context;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final String DATABASE_NAME = "Survey_database_dynamic";
    private static final String TABLE_LOOKUPID = "look_up_id";

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db2) {
        Intrinsics.checkNotNullParameter(db2, "db");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Database(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 31);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        db = getWritableDatabase();
    }

    public final Context getContext() {
        return this.context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db2, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db2, "db");
        onCreate(db2);
    }

    public final void create(String surveyPeriodeId, int[] lookUpModels) throws SQLException {
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        Intrinsics.checkNotNullParameter(lookUpModels, "lookUpModels");
        try {
            String str = "CREATE TABLE IF NOT EXISTS " + TABLE_LOOKUPID + "( id INTEGER PRIMARY KEY AUTOINCREMENT,surveyPeriodeId INTEGER,value INTEGER)";
            SQLiteDatabase sQLiteDatabase = db;
            Intrinsics.checkNotNull(sQLiteDatabase);
            sQLiteDatabase.execSQL(str);
            insert(lookUpModels, surveyPeriodeId);
        } catch (Exception e) {
            CrashHandler.INSTANCE.sendExeption(e);
            e.printStackTrace();
        }
    }

    public final JsonArray getLookup(String tableName, HashMap<String, String> condition) {
        String str;
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        openDB();
        JsonArray jsonArray = new JsonArray();
        try {
            String str2 = "SELECT * FROM " + tableName + " ";
            HashMap<String, String> map = condition;
            boolean z = true;
            if (!(map == null || map.isEmpty()) && condition != null) {
                for (Map.Entry<String, String> entry : condition.entrySet()) {
                    if (z) {
                        str = ((Object) str2) + "WHERE ";
                        z = false;
                    } else {
                        str = ((Object) str2) + "AND ";
                    }
                    str2 = ((Object) str) + ((Object) entry.getKey()) + " = '" + ((Object) entry.getValue()) + "'";
                }
            }
            SQLiteDatabase sQLiteDatabase = db;
            if (sQLiteDatabase != null) {
                Cursor cursorRawQuery = sQLiteDatabase.rawQuery(str2, null);
                Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "it.rawQuery(query, null)");
                cursorRawQuery.moveToFirst();
                while (!cursorRawQuery.isAfterLast()) {
                    int columnCount = cursorRawQuery.getColumnCount();
                    JsonObject jsonObject = new JsonObject();
                    for (int i = 0; i < columnCount; i++) {
                        if (cursorRawQuery.getColumnName(i) != null) {
                            try {
                                jsonObject.addProperty(cursorRawQuery.getColumnName(i), cursorRawQuery.getString(i));
                            } catch (Exception unused) {
                            }
                        }
                    }
                    jsonArray.add(jsonObject);
                    cursorRawQuery.moveToNext();
                }
            }
        } catch (JsonIOException e) {
            CrashHandler.INSTANCE.sendExeption(e);
            e.printStackTrace();
        }
        return jsonArray;
    }

    public final JSONArray getLookUp(String tableName, String value, String desc, LinkedHashMap<String, String> condition) {
        String str;
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(desc, "desc");
        openDB();
        JSONArray jSONArray = new JSONArray();
        if (isTableExists(tableName)) {
            try {
                str = "";
                SQLiteDatabase sQLiteDatabase = db;
                if (sQLiteDatabase != null) {
                    str = existsColumnInTable(sQLiteDatabase, tableName, value) ? "select distinct " + value : "";
                    if (existsColumnInTable(sQLiteDatabase, tableName, desc)) {
                        str = ((Object) str) + ", " + desc + " from " + tableName;
                    }
                }
                if (condition != null && (!condition.isEmpty())) {
                    if (str.length() > 0) {
                        String str2 = ((Object) str) + " WHERE ";
                        for (Map.Entry<String, String> entry : condition.entrySet()) {
                            SQLiteDatabase sQLiteDatabase2 = db;
                            if (sQLiteDatabase2 != null && existsColumnInTable(sQLiteDatabase2, tableName, entry.getKey())) {
                                str2 = ((Object) str2) + (condition.size() - 1 == 0 ? ((Object) entry.getKey()) + "='" + ((Object) entry.getValue()) + "'" : ((Object) entry.getKey()) + "='" + ((Object) entry.getValue()) + "' AND ");
                            }
                        }
                        str = str2;
                    }
                }
                SQLiteDatabase sQLiteDatabase3 = db;
                if (sQLiteDatabase3 != null) {
                    Cursor cursorRawQuery = sQLiteDatabase3.rawQuery(str, null);
                    Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "it.rawQuery(query, null)");
                    cursorRawQuery.moveToFirst();
                    while (!cursorRawQuery.isAfterLast()) {
                        JSONArray jSONArray2 = new JSONArray();
                        jSONArray2.put(cursorRawQuery.getString(0));
                        jSONArray2.put(cursorRawQuery.getString(1));
                        jSONArray.put(jSONArray2);
                        cursorRawQuery.moveToNext();
                    }
                }
            } catch (JSONException e) {
                CrashHandler.INSTANCE.sendExeption(e);
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    private final boolean existsColumnInTable(SQLiteDatabase inDatabase, String inTable, String columnToCheck) {
        Cursor cursorRawQuery = null;
        try {
            try {
                cursorRawQuery = inDatabase.rawQuery("SELECT * FROM " + inTable + " LIMIT 0", null);
                z = cursorRawQuery.getColumnIndex(columnToCheck) != -1;
            } catch (Exception e) {
                Log.d("column", "When checking whether a column exists in the table, an error occurred: " + e.getMessage());
                if (cursorRawQuery != null) {
                }
            }
            return z;
        } finally {
            if (cursorRawQuery != null) {
                cursorRawQuery.close();
            }
        }
    }

    public final void create(LookUpModel lookUpModels) throws SQLException {
        String tableName;
        if (lookUpModels != null) {
            try {
                tableName = lookUpModels.getTableName();
            } catch (Exception e) {
                CrashHandler.INSTANCE.sendExeption(e);
                e.printStackTrace();
                return;
            }
        } else {
            tableName = null;
        }
        String str = "CREATE TABLE IF NOT EXISTS " + tableName + "(";
        List<String> fields = lookUpModels != null ? lookUpModels.getFields() : null;
        if (fields != null) {
            int i = 0;
            for (String str2 : fields) {
                String str3 = "";
                if (fields.size() - 1 != i && i + 1 <= fields.size()) {
                    str3 = ", ";
                }
                str = ((Object) str) + str2 + " TEXT" + str3;
                i++;
            }
        }
        String str4 = ((Object) str) + ")";
        SQLiteDatabase sQLiteDatabase = db;
        Intrinsics.checkNotNull(sQLiteDatabase);
        sQLiteDatabase.execSQL(str4);
    }

    public final void insert(int[] values, String surveyPeriodeId) {
        Intrinsics.checkNotNullParameter(values, "values");
        Intrinsics.checkNotNullParameter(surveyPeriodeId, "surveyPeriodeId");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor cursorRawQuery = writableDatabase.rawQuery("SELECT * FROM " + TABLE_LOOKUPID, null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "db.rawQuery(\"SELECT * FROM $TABLE_LOOKUPID\", null)");
        int count = cursorRawQuery.getCount();
        cursorRawQuery.close();
        try {
            int length = values.length;
            int i = 0;
            while (i < length) {
                ContentValues contentValues = new ContentValues();
                int i2 = i + 1;
                contentValues.put(DownloadModel.ID, Integer.valueOf(count + i2));
                contentValues.put("surveyPeriodeId", surveyPeriodeId);
                contentValues.put("value", Integer.valueOf(values[i]));
                writableDatabase.insert(TABLE_LOOKUPID, null, contentValues);
                i = i2;
            }
        } catch (Exception e) {
            CrashHandler.INSTANCE.sendExeption(e);
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x019d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void insert(final java.lang.String r19, java.util.List<java.lang.String> r20, java.util.List<java.lang.String> r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 490
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.local.databaseNotRoom.Database.insert(java.lang.String, java.util.List, java.util.List):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void insert$lambda$8(Database this$0, String tableName) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(tableName, "$tableName");
        Toast.makeText(this$0.context, "Error pada id lookup : " + StringsKt.substringAfter$default(tableName, "lookup_id_", (String) null, 2, (Object) null), 1).show();
    }

    public final void insert(String tableName, JSONArray values) throws JSONException, SQLException {
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(values, "values");
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            int length = values.length();
            for (int i = 0; i < length; i++) {
                String str = "INSERT INTO " + tableName + " Values (";
                JSONArray jSONArray = values.getJSONArray(i);
                int length2 = jSONArray.length();
                int i2 = 0;
                while (i2 < length2) {
                    str = (jSONArray.get(i2) instanceof String ? str + "'" + jSONArray.get(i2) + "'" : str + jSONArray.get(i2)) + (jSONArray.length() + (-1) == i2 ? "" : ", ");
                    i2++;
                }
                writableDatabase.execSQL(str + ");");
            }
        } catch (JSONException e) {
            CrashHandler.INSTANCE.sendExeption(e);
            e.printStackTrace();
        }
    }

    public final void DELETE_ALL_TABLE() throws SQLException {
        SQLiteDatabase sQLiteDatabase = db;
        Intrinsics.checkNotNull(sQLiteDatabase);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "db!!.rawQuery(\"SELECT na…HERE type='table'\", null)");
        ArrayList arrayList = new ArrayList();
        while (cursorRawQuery.moveToNext()) {
            arrayList.add(cursorRawQuery.getString(0));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!Intrinsics.areEqual(str, "sqlite_sequence")) {
                SQLiteDatabase sQLiteDatabase2 = db;
                Intrinsics.checkNotNull(sQLiteDatabase2);
                sQLiteDatabase2.execSQL("DROP TABLE IF EXISTS " + str);
            }
        }
    }

    public final void clearAll(String tableName) throws SQLException {
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        openDB();
        SQLiteDatabase sQLiteDatabase = db;
        Intrinsics.checkNotNull(sQLiteDatabase);
        sQLiteDatabase.execSQL("DELETE FROM " + tableName);
    }

    public final boolean isTableExists(String tableName) {
        openDB();
        SQLiteDatabase sQLiteDatabase = db;
        Intrinsics.checkNotNull(sQLiteDatabase);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "db!!.rawQuery(\n         …           null\n        )");
        if (cursorRawQuery.getCount() > 0) {
            cursorRawQuery.close();
            return true;
        }
        cursorRawQuery.close();
        return false;
    }

    public final int countRowTableExists(String tableName) {
        openDB();
        SQLiteDatabase sQLiteDatabase = db;
        Intrinsics.checkNotNull(sQLiteDatabase);
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select COUNT(*) from '" + tableName + "'", null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "db!!.rawQuery(\n         …           null\n        )");
        cursorRawQuery.moveToFirst();
        return cursorRawQuery.getInt(0);
    }

    private final void openDB() {
        SQLiteDatabase sQLiteDatabase = db;
        if (sQLiteDatabase != null) {
            Intrinsics.checkNotNull(sQLiteDatabase);
            if (sQLiteDatabase.isOpen()) {
                return;
            }
        }
        db = getReadableDatabase();
    }

    private final String getType(String type) {
        return (Intrinsics.areEqual(type, TypedValues.Custom.S_FLOAT) || StringsKt.equals(type, "double", true)) ? "FLOAT" : Intrinsics.areEqual(type, FormGearActivity.LON) ? "LONG" : "TEXT";
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0169 A[Catch: JSONException -> 0x01e9, TryCatch #0 {JSONException -> 0x01e9, blocks: (B:3:0x0031, B:6:0x007a, B:10:0x0080, B:12:0x0083, B:14:0x0098, B:16:0x00ad, B:17:0x00b5, B:19:0x00bb, B:24:0x00ce, B:26:0x00de, B:31:0x00fa, B:38:0x0113, B:41:0x0119, B:43:0x011f, B:45:0x012f, B:47:0x0141, B:25:0x00da, B:48:0x0149, B:50:0x0199, B:52:0x01b0, B:55:0x01ba, B:57:0x01bf, B:62:0x01ca, B:64:0x01d6, B:65:0x01dc, B:67:0x01e2, B:68:0x01e5, B:49:0x0169), top: B:73:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b0 A[Catch: JSONException -> 0x01e9, TryCatch #0 {JSONException -> 0x01e9, blocks: (B:3:0x0031, B:6:0x007a, B:10:0x0080, B:12:0x0083, B:14:0x0098, B:16:0x00ad, B:17:0x00b5, B:19:0x00bb, B:24:0x00ce, B:26:0x00de, B:31:0x00fa, B:38:0x0113, B:41:0x0119, B:43:0x011f, B:45:0x012f, B:47:0x0141, B:25:0x00da, B:48:0x0149, B:50:0x0199, B:52:0x01b0, B:55:0x01ba, B:57:0x01bf, B:62:0x01ca, B:64:0x01d6, B:65:0x01dc, B:67:0x01e2, B:68:0x01e5, B:49:0x0169), top: B:73:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01ba A[Catch: JSONException -> 0x01e9, TryCatch #0 {JSONException -> 0x01e9, blocks: (B:3:0x0031, B:6:0x007a, B:10:0x0080, B:12:0x0083, B:14:0x0098, B:16:0x00ad, B:17:0x00b5, B:19:0x00bb, B:24:0x00ce, B:26:0x00de, B:31:0x00fa, B:38:0x0113, B:41:0x0119, B:43:0x011f, B:45:0x012f, B:47:0x0141, B:25:0x00da, B:48:0x0149, B:50:0x0199, B:52:0x01b0, B:55:0x01ba, B:57:0x01bf, B:62:0x01ca, B:64:0x01d6, B:65:0x01dc, B:67:0x01e2, B:68:0x01e5, B:49:0x0169), top: B:73:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01bf A[Catch: JSONException -> 0x01e9, TryCatch #0 {JSONException -> 0x01e9, blocks: (B:3:0x0031, B:6:0x007a, B:10:0x0080, B:12:0x0083, B:14:0x0098, B:16:0x00ad, B:17:0x00b5, B:19:0x00bb, B:24:0x00ce, B:26:0x00de, B:31:0x00fa, B:38:0x0113, B:41:0x0119, B:43:0x011f, B:45:0x012f, B:47:0x0141, B:25:0x00da, B:48:0x0149, B:50:0x0199, B:52:0x01b0, B:55:0x01ba, B:57:0x01bf, B:62:0x01ca, B:64:0x01d6, B:65:0x01dc, B:67:0x01e2, B:68:0x01e5, B:49:0x0169), top: B:73:0x0031 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.json.JSONArray get(java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String[] r21, java.lang.String r22, java.lang.String r23, int r24) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: id.go.bpsfasih.data.local.databaseNotRoom.Database.get(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String, int):org.json.JSONArray");
    }

    public final Cursor get(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        Cursor cursorRawQuery = getWritableDatabase().rawQuery(query, null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "db.rawQuery(query, null)");
        return cursorRawQuery;
    }

    public final long update(String tableName, ContentValues contentValues, String whereClause) {
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(contentValues, "contentValues");
        Intrinsics.checkNotNullParameter(whereClause, "whereClause");
        return getWritableDatabase().update(tableName, contentValues, whereClause, null);
    }

    public final long delete(String tableName, String whereClause) {
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        Intrinsics.checkNotNullParameter(whereClause, "whereClause");
        return getWritableDatabase().delete(tableName, whereClause, null);
    }

    public final void deleteTable(String tableName) throws SQLException {
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        getWritableDatabase().execSQL("DROP TABLE IF EXISTS " + tableName);
    }

    /* compiled from: Database.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lid/go/bpsfasih/data/local/databaseNotRoom/Database$Companion;", "", "()V", "DATABASE_NAME", "", "DATABASE_VERSION", "", "TABLE_LOOKUPID", "getTABLE_LOOKUPID", "()Ljava/lang/String;", "db", "Landroid/database/sqlite/SQLiteDatabase;", "app_release"}, k = 1, mv = {1, 7, 1}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTABLE_LOOKUPID() {
            return Database.TABLE_LOOKUPID;
        }
    }
}
