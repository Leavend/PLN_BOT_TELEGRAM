package com.dewakoding.androiddatatable;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.dewakoding.androiddatatable.data.Column;
import com.dewakoding.androiddatatable.data.OrderBy;
import com.dewakoding.androiddatatable.listener.OnClickListener;
import com.dewakoding.androiddatatable.listener.OnWebViewComponentClickListener;
import com.dewakoding.androiddatatable.util.JavascriptInterface;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DataTableView.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\nJ_\u0010\u001c\u001a\u00020\u00182\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"2\b\b\u0002\u0010$\u001a\u00020%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010)\u001a\u0004\u0018\u00010%¢\u0006\u0002\u0010*R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/dewakoding/androiddatatable/DataTableView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "clickListener", "Lcom/dewakoding/androiddatatable/listener/OnWebViewComponentClickListener;", "jsi", "Lcom/dewakoding/androiddatatable/util/JavascriptInterface;", "getJsi$androiddatatable_release", "()Lcom/dewakoding/androiddatatable/util/JavascriptInterface;", "setJsi$androiddatatable_release", "(Lcom/dewakoding/androiddatatable/util/JavascriptInterface;)V", "webView", "Landroid/webkit/WebView;", "convertStreamToString", "", "inputStream", "Ljava/io/InputStream;", "onRowButtonClicked", "", "dataStr", "setOnClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setTable", "columns", "Ljava/util/ArrayList;", "Lcom/dewakoding/androiddatatable/data/Column;", "Lkotlin/collections/ArrayList;", "listData", "", "", "isActionButtonShow", "", "orderBy", "Lcom/dewakoding/androiddatatable/data/OrderBy;", "pageLength", "isSearchingEnable", "(Ljava/util/ArrayList;Ljava/util/List;ZLcom/dewakoding/androiddatatable/data/OrderBy;Ljava/lang/Integer;Ljava/lang/Boolean;)V", "androiddatatable_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public class DataTableView extends LinearLayout {
    private OnWebViewComponentClickListener clickListener;
    private JavascriptInterface jsi;
    private final WebView webView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DataTableView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DataTableView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ DataTableView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataTableView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setOrientation(1);
        this.webView = new WebView(context);
    }

    /* renamed from: getJsi$androiddatatable_release, reason: from getter */
    public final JavascriptInterface getJsi() {
        return this.jsi;
    }

    public final void setJsi$androiddatatable_release(JavascriptInterface javascriptInterface) {
        this.jsi = javascriptInterface;
    }

    public static /* synthetic */ void setTable$default(DataTableView dataTableView, ArrayList arrayList, List list, boolean z, OrderBy orderBy, Integer num, Boolean bool, int i, Object obj) throws Resources.NotFoundException, IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setTable");
        }
        if ((i & 4) != 0) {
            z = false;
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            orderBy = null;
        }
        OrderBy orderBy2 = orderBy;
        if ((i & 16) != 0) {
            num = 10;
        }
        Integer num2 = num;
        if ((i & 32) != 0) {
            bool = true;
        }
        dataTableView.setTable(arrayList, list, z2, orderBy2, num2, bool);
    }

    public final void setTable(ArrayList<Column> columns, List<? extends Object> listData, boolean isActionButtonShow, OrderBy orderBy, Integer pageLength, Boolean isSearchingEnable) throws Resources.NotFoundException, IOException {
        Intrinsics.checkNotNullParameter(columns, "columns");
        Intrinsics.checkNotNullParameter(listData, "listData");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext()");
        String json = new Gson().toJson(columns);
        Intrinsics.checkNotNullExpressionValue(json, "Gson().toJson(columns)");
        String json2 = new Gson().toJson(listData);
        Intrinsics.checkNotNullExpressionValue(json2, "Gson().toJson(listData)");
        JavascriptInterface javascriptInterface = new JavascriptInterface(context, json, json2, isActionButtonShow, orderBy, pageLength, isSearchingEnable, new OnClickListener() { // from class: com.dewakoding.androiddatatable.DataTableView.setTable.1
            @Override // com.dewakoding.androiddatatable.listener.OnClickListener
            public void onClick(String str) {
                Intrinsics.checkNotNullParameter(str, "str");
                DataTableView.this.onRowButtonClicked(str);
            }
        });
        this.jsi = javascriptInterface;
        WebView webView = this.webView;
        Intrinsics.checkNotNull(javascriptInterface);
        webView.addJavascriptInterface(javascriptInterface, "Android");
        this.webView.setWebChromeClient(new WebChromeClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        int i = R.raw.datatable2023;
        InputStream inputStreamOpenRawResource = getResources().openRawResource(i);
        Intrinsics.checkNotNullExpressionValue(inputStreamOpenRawResource, "resources.openRawResource(rawResourceId)");
        String strConvertStreamToString = convertStreamToString(inputStreamOpenRawResource);
        Uri.parse("file:///android_res/raw/" + i);
        this.webView.loadDataWithBaseURL("file:///android_res/raw/", strConvertStreamToString, "text/html", "UTF-8", null);
        addView(this.webView);
    }

    private final String convertStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line).append("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Throwable th) {
                    try {
                        inputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    throw th;
                }
            } catch (IOException e3) {
                e3.printStackTrace();
                inputStream.close();
            }
        }
        inputStream.close();
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "stringBuilder.toString()");
        return string;
    }

    public final void setOnClickListener(OnWebViewComponentClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.clickListener = listener;
    }

    public final void onRowButtonClicked(String dataStr) {
        Intrinsics.checkNotNullParameter(dataStr, "dataStr");
        OnWebViewComponentClickListener onWebViewComponentClickListener = this.clickListener;
        if (onWebViewComponentClickListener != null) {
            onWebViewComponentClickListener.onRowClicked(dataStr);
        }
    }
}
