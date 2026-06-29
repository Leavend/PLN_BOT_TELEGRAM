package com.kdownloader;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: Constants.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/kdownloader/Constants;", "", "()V", "DEFAULT_CONNECT_TIMEOUT_IN_MILLS", "", "DEFAULT_READ_TIMEOUT_IN_MILLS", "DEFAULT_USER_AGENT", "", "ETAG", "HTTP_PERMANENT_REDIRECT", "HTTP_RANGE_NOT_SATISFIABLE", "HTTP_TEMPORARY_REDIRECT", "RANGE", "USER_AGENT", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final class Constants {
    public static final int DEFAULT_CONNECT_TIMEOUT_IN_MILLS = 20000;
    public static final int DEFAULT_READ_TIMEOUT_IN_MILLS = 20000;
    public static final String DEFAULT_USER_AGENT = "KDownloader";
    public static final String ETAG = "ETag";
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_RANGE_NOT_SATISFIABLE = 416;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    public static final Constants INSTANCE = new Constants();
    public static final String RANGE = "Range";
    public static final String USER_AGENT = "User-Agent";

    private Constants() {
    }
}
