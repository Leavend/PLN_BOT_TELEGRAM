package com.hzy.lib7z;

/* loaded from: classes2.dex */
public interface IExtractCallback {
    void onError(int i, String str);

    void onGetFileNum(int i);

    void onProgress(String str, long j);

    void onStart();

    void onSucceed();
}
