package com.dewakoding.androiddatatable.util;

import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.dewakoding.androiddatatable.data.OrderBy;
import com.dewakoding.androiddatatable.listener.OnClickListener;
import com.google.gson.Gson;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JavascriptInterface.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0005H\u0007J\b\u0010\u001d\u001a\u00020\u0005H\u0007J\b\u0010\u001e\u001a\u00020\u0005H\u0007J\n\u0010\u001f\u001a\u0004\u0018\u00010\u0005H\u0007J\n\u0010 \u001a\u0004\u0018\u00010\u0005H\u0007J\b\u0010\u0007\u001a\u00020\bH\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0007R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006\""}, d2 = {"Lcom/dewakoding/androiddatatable/util/JavascriptInterface;", "", "context", "Landroid/content/Context;", "column", "", "data", "isActionButtonShow", "", "orderBy", "Lcom/dewakoding/androiddatatable/data/OrderBy;", "pageLength", "", "isSearchingEnable", "onClickListener", "Lcom/dewakoding/androiddatatable/listener/OnClickListener;", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;ZLcom/dewakoding/androiddatatable/data/OrderBy;Ljava/lang/Integer;Ljava/lang/Boolean;Lcom/dewakoding/androiddatatable/listener/OnClickListener;)V", "mColumn", "mData", "mIsActionButtonShow", "mIsSearchingEnable", "Ljava/lang/Boolean;", "mOnClickListener", "mOrderBy", "mPageLength", "Ljava/lang/Integer;", "action", "", "dataStr", "getColumn", "getData", "getOrderBy", "getPageLength", "Companion", "androiddatatable_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class JavascriptInterface {
    public static final String TAG_HANDLER = "Android";
    private String mColumn;
    private String mData;
    private boolean mIsActionButtonShow;
    private Boolean mIsSearchingEnable;
    private OnClickListener mOnClickListener;
    private OrderBy mOrderBy;
    private Integer mPageLength;

    public JavascriptInterface(Context context, String column, String data, boolean z, OrderBy orderBy, Integer num, Boolean bool, OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(column, "column");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        this.mColumn = column;
        this.mData = data;
        this.mIsActionButtonShow = z;
        this.mOrderBy = orderBy;
        this.mPageLength = num;
        this.mIsSearchingEnable = bool;
        this.mOnClickListener = onClickListener;
    }

    @android.webkit.JavascriptInterface
    /* renamed from: getColumn, reason: from getter */
    public final String getMColumn() {
        return this.mColumn;
    }

    @android.webkit.JavascriptInterface
    /* renamed from: getData, reason: from getter */
    public final String getMData() {
        return this.mData;
    }

    @android.webkit.JavascriptInterface
    public final void action(String dataStr) {
        Intrinsics.checkNotNullParameter(dataStr, "dataStr");
        this.mOnClickListener.onClick(dataStr);
    }

    @android.webkit.JavascriptInterface
    /* renamed from: isActionButtonShow, reason: from getter */
    public final boolean getMIsActionButtonShow() {
        return this.mIsActionButtonShow;
    }

    @android.webkit.JavascriptInterface
    public final String getOrderBy() {
        ArrayList arrayList = new ArrayList();
        OrderBy orderBy = this.mOrderBy;
        if (orderBy != null) {
            Intrinsics.checkNotNull(orderBy);
            arrayList.add(Integer.valueOf(orderBy.getColumnId()));
            OrderBy orderBy2 = this.mOrderBy;
            Intrinsics.checkNotNull(orderBy2);
            arrayList.add(orderBy2.getType());
        }
        return new Gson().toJson(arrayList);
    }

    @android.webkit.JavascriptInterface
    public final String getPageLength() {
        return String.valueOf(this.mPageLength);
    }

    @android.webkit.JavascriptInterface
    public final String isSearchingEnable() {
        return String.valueOf(this.mIsSearchingEnable);
    }
}
