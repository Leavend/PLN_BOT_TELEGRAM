package com.kdownloader;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DownloaderConfig.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, d2 = {"Lcom/kdownloader/DownloaderConfig;", "", "databaseEnabled", "", "connectTimeOut", "", "readTimeOut", "(ZII)V", "getConnectTimeOut", "()I", "setConnectTimeOut", "(I)V", "getDatabaseEnabled", "()Z", "setDatabaseEnabled", "(Z)V", "getReadTimeOut", "setReadTimeOut", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "toString", "", "library_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes2.dex */
public final /* data */ class DownloaderConfig {
    private int connectTimeOut;
    private boolean databaseEnabled;
    private int readTimeOut;

    public DownloaderConfig() {
        this(false, 0, 0, 7, null);
    }

    public static /* synthetic */ DownloaderConfig copy$default(DownloaderConfig downloaderConfig, boolean z, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = downloaderConfig.databaseEnabled;
        }
        if ((i3 & 2) != 0) {
            i = downloaderConfig.connectTimeOut;
        }
        if ((i3 & 4) != 0) {
            i2 = downloaderConfig.readTimeOut;
        }
        return downloaderConfig.copy(z, i, i2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getDatabaseEnabled() {
        return this.databaseEnabled;
    }

    /* renamed from: component2, reason: from getter */
    public final int getConnectTimeOut() {
        return this.connectTimeOut;
    }

    /* renamed from: component3, reason: from getter */
    public final int getReadTimeOut() {
        return this.readTimeOut;
    }

    public final DownloaderConfig copy(boolean databaseEnabled, int connectTimeOut, int readTimeOut) {
        return new DownloaderConfig(databaseEnabled, connectTimeOut, readTimeOut);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DownloaderConfig)) {
            return false;
        }
        DownloaderConfig downloaderConfig = (DownloaderConfig) other;
        return this.databaseEnabled == downloaderConfig.databaseEnabled && this.connectTimeOut == downloaderConfig.connectTimeOut && this.readTimeOut == downloaderConfig.readTimeOut;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.databaseEnabled;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((r0 * 31) + Integer.hashCode(this.connectTimeOut)) * 31) + Integer.hashCode(this.readTimeOut);
    }

    public String toString() {
        return "DownloaderConfig(databaseEnabled=" + this.databaseEnabled + ", connectTimeOut=" + this.connectTimeOut + ", readTimeOut=" + this.readTimeOut + ')';
    }

    public DownloaderConfig(boolean z, int i, int i2) {
        this.databaseEnabled = z;
        this.connectTimeOut = i;
        this.readTimeOut = i2;
    }

    public /* synthetic */ DownloaderConfig(boolean z, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? 20000 : i, (i3 & 4) != 0 ? 20000 : i2);
    }

    public final boolean getDatabaseEnabled() {
        return this.databaseEnabled;
    }

    public final void setDatabaseEnabled(boolean z) {
        this.databaseEnabled = z;
    }

    public final int getConnectTimeOut() {
        return this.connectTimeOut;
    }

    public final void setConnectTimeOut(int i) {
        this.connectTimeOut = i;
    }

    public final int getReadTimeOut() {
        return this.readTimeOut;
    }

    public final void setReadTimeOut(int i) {
        this.readTimeOut = i;
    }
}
