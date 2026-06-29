package com.hzy.libp7zip;

/* loaded from: classes2.dex */
public class P7ZipApi {
    public static native int executeCommand(String str);

    public static native String get7zVersionInfo();

    static {
        System.loadLibrary("p7zip");
    }
}
