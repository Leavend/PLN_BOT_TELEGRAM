package com.kdownloader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AppDbHelper.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00102\u0006\u0010\u0011\u001a\u00020\rH\u0097@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J)\u0010\u0017\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001d"}, d2 = {"Lcom/kdownloader/database/AppDbHelper;", "Lcom/kdownloader/database/DbHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "db", "Landroid/database/sqlite/SQLiteDatabase;", "empty", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "find", "Lcom/kdownloader/database/DownloadModel;", DownloadModel.ID, "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnwantedModels", "", "days", "insert", "model", "(Lcom/kdownloader/database/DownloadModel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "remove", "update", "updateProgress", "downloadedBytes", "", "lastModifiedAt", "(IJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class AppDbHelper implements DbHelper {
    public static final String TABLE_NAME = "downloads";
    private SQLiteDatabase db;

    public AppDbHelper(Context context) {
        SQLiteDatabase writableDatabase = new DatabaseOpenHelper(context).getWritableDatabase();
        Intrinsics.checkNotNullExpressionValue(writableDatabase, "databaseOpenHelper.writableDatabase");
        this.db = writableDatabase;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object find(int i, Continuation<? super DownloadModel> continuation) {
        DownloadModel downloadModel = null;
        Cursor cursorRawQuery = this.db.rawQuery("SELECT * FROM downloads WHERE id = " + i, null);
        if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
            String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("url"));
            Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(cursor.…Index(DownloadModel.URL))");
            String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(DownloadModel.ETAG));
            Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(cursor.…ndex(DownloadModel.ETAG))");
            String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(DownloadModel.DIR_PATH));
            Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(cursor.…(DownloadModel.DIR_PATH))");
            String string4 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(DownloadModel.FILE_NAME));
            Intrinsics.checkNotNullExpressionValue(string4, "cursor.getString(cursor.…DownloadModel.FILE_NAME))");
            downloadModel = new DownloadModel(i, string, string2, string3, string4, cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(DownloadModel.TOTAL_BYTES)), cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(DownloadModel.DOWNLOADED_BYTES)), cursorRawQuery.getLong(cursorRawQuery.getColumnIndex(DownloadModel.LAST_MODIFIED_AT)));
        }
        cursorRawQuery.close();
        return downloadModel;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object insert(DownloadModel downloadModel, Continuation<? super Unit> continuation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DownloadModel.ID, Boxing.boxInt(downloadModel.getId()));
        contentValues.put("url", downloadModel.getUrl());
        contentValues.put(DownloadModel.ETAG, downloadModel.getETag());
        contentValues.put(DownloadModel.DIR_PATH, downloadModel.getDirPath());
        contentValues.put(DownloadModel.FILE_NAME, downloadModel.getFileName());
        contentValues.put(DownloadModel.TOTAL_BYTES, Boxing.boxLong(downloadModel.getTotalBytes()));
        contentValues.put(DownloadModel.DOWNLOADED_BYTES, Boxing.boxLong(downloadModel.getDownloadedBytes()));
        contentValues.put(DownloadModel.LAST_MODIFIED_AT, Boxing.boxLong(downloadModel.getLastModifiedAt()));
        this.db.insert(TABLE_NAME, null, contentValues);
        return Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object update(DownloadModel downloadModel, Continuation<? super Unit> continuation) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("url", downloadModel.getUrl());
            contentValues.put(DownloadModel.ETAG, downloadModel.getETag());
            contentValues.put(DownloadModel.DIR_PATH, downloadModel.getDirPath());
            contentValues.put(DownloadModel.FILE_NAME, downloadModel.getFileName());
            contentValues.put(DownloadModel.TOTAL_BYTES, Boxing.boxLong(downloadModel.getTotalBytes()));
            contentValues.put(DownloadModel.DOWNLOADED_BYTES, Boxing.boxLong(downloadModel.getDownloadedBytes()));
            contentValues.put(DownloadModel.LAST_MODIFIED_AT, Boxing.boxLong(downloadModel.getLastModifiedAt()));
            this.db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf(downloadModel.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    /* compiled from: AppDbHelper.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(c = "com.kdownloader.database.AppDbHelper$updateProgress$2", f = "AppDbHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.kdownloader.database.AppDbHelper$updateProgress$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
        final /* synthetic */ long $downloadedBytes;
        final /* synthetic */ int $id;
        final /* synthetic */ long $lastModifiedAt;
        int label;
        final /* synthetic */ AppDbHelper this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(long j, long j2, AppDbHelper appDbHelper, int i, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$downloadedBytes = j;
            this.$lastModifiedAt = j2;
            this.this$0 = appDbHelper;
            this.$id = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$downloadedBytes, this.$lastModifiedAt, this.this$0, this.$id, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Object> continuation) {
            return invoke2(coroutineScope, (Continuation<Object>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<Object> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DownloadModel.DOWNLOADED_BYTES, Boxing.boxLong(this.$downloadedBytes));
                contentValues.put(DownloadModel.LAST_MODIFIED_AT, Boxing.boxLong(this.$lastModifiedAt));
                return Boxing.boxInt(this.this$0.db.update(AppDbHelper.TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf(this.$id)}));
            } catch (Exception e) {
                e.printStackTrace();
                return Unit.INSTANCE;
            }
        }
    }

    @Override // com.kdownloader.database.DbHelper
    public Object updateProgress(int i, long j, long j2, Continuation<? super Unit> continuation) throws Throwable {
        Object objWithContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(j, j2, this, i, null), continuation);
        return objWithContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objWithContext : Unit.INSTANCE;
    }

    @Override // com.kdownloader.database.DbHelper
    public Object remove(int i, Continuation<? super Unit> continuation) throws SQLException {
        try {
            this.db.execSQL("DELETE FROM downloads WHERE id = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00d2 A[PHI: r4
      0x00d2: PHI (r4v3 android.database.Cursor) = (r4v2 android.database.Cursor), (r4v5 android.database.Cursor) binds: [B:22:0x00e4, B:11:0x00d0] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00ea  */
    @Override // com.kdownloader.database.DbHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object getUnwantedModels(int r20, kotlin.coroutines.Continuation<? super java.util.List<com.kdownloader.database.DownloadModel>> r21) throws java.lang.Throwable {
        /*
            r19 = this;
            java.lang.String r0 = "SELECT * FROM downloads WHERE last_modified_at <= "
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.List r1 = (java.util.List) r1
            int r2 = r20 * 24
            int r2 = r2 * 60
            int r2 = r2 * 60
            long r2 = (long) r2
            r4 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 * r4
            r4 = 0
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lda java.lang.Exception -> Lde
            long r5 = r5 - r2
            r2 = r19
            android.database.sqlite.SQLiteDatabase r3 = r2.db     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r7.<init>(r0)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.StringBuilder r0 = r7.append(r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            android.database.Cursor r4 = r3.rawQuery(r0, r4)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r4 == 0) goto Ld0
            boolean r0 = r4.moveToFirst()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r0 == 0) goto Ld0
        L36:
            com.kdownloader.database.DownloadModel r0 = new com.kdownloader.database.DownloadModel     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r13 = 0
            r15 = 0
            r17 = 255(0xff, float:3.57E-43)
            r18 = 0
            r5 = r0
            r5.<init>(r6, r7, r8, r9, r10, r11, r13, r15, r17, r18)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "id"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            int r3 = r4.getInt(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setId(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "url"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r5 = "cursor.getString(cursor.…Index(DownloadModel.URL))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setUrl(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "etag"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r5 = "cursor.getString(cursor.…ndex(DownloadModel.ETAG))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setETag(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "dir_path"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r5 = "cursor.getString(cursor.…(DownloadModel.DIR_PATH))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setDirPath(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "file_name"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = r4.getString(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r5 = "cursor.getString(cursor.…DownloadModel.FILE_NAME))"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setFileName(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "total_bytes"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r5 = r4.getLong(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setTotalBytes(r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "downloaded_bytes"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r5 = r4.getLong(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setDownloadedBytes(r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            java.lang.String r3 = "last_modified_at"
            int r3 = r4.getColumnIndex(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            long r5 = r4.getLong(r3)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r0.setLastModifiedAt(r5)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            r1.add(r0)     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            boolean r0 = r4.moveToNext()     // Catch: java.lang.Throwable -> Ld6 java.lang.Exception -> Ld8
            if (r0 != 0) goto L36
        Ld0:
            if (r4 == 0) goto Le7
        Ld2:
            r4.close()
            goto Le7
        Ld6:
            r0 = move-exception
            goto Le8
        Ld8:
            r0 = move-exception
            goto Le1
        Lda:
            r0 = move-exception
            r2 = r19
            goto Le8
        Lde:
            r0 = move-exception
            r2 = r19
        Le1:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Ld6
            if (r4 == 0) goto Le7
            goto Ld2
        Le7:
            return r1
        Le8:
            if (r4 == 0) goto Led
            r4.close()
        Led:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kdownloader.database.AppDbHelper.getUnwantedModels(int, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.kdownloader.database.DbHelper
    public Object empty(Continuation<? super Unit> continuation) throws SQLException {
        try {
            this.db.execSQL("DELETE * FROM downloads");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unit.INSTANCE;
    }
}
