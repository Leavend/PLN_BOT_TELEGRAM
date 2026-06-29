package org.osmdroid.tileprovider.modules;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/* loaded from: classes3.dex */
public class NetworkAvailabliltyCheck implements INetworkAvailablityCheck {
    private final ConnectivityManager mConnectionManager;
    private final boolean mHasNetworkStatePermission;
    private final boolean mIsX86 = "Android-x86".equalsIgnoreCase(Build.BRAND);

    @Override // org.osmdroid.tileprovider.modules.INetworkAvailablityCheck
    @Deprecated
    public boolean getRouteToPathExists(int i) {
        return true;
    }

    public NetworkAvailabliltyCheck(Context context) {
        this.mConnectionManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.mHasNetworkStatePermission = context.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", context.getPackageName()) == 0;
    }

    @Override // org.osmdroid.tileprovider.modules.INetworkAvailablityCheck
    public boolean getNetworkAvailable() {
        if (!this.mHasNetworkStatePermission) {
            return true;
        }
        NetworkInfo activeNetworkInfo = this.mConnectionManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        if (activeNetworkInfo.isConnected()) {
            return true;
        }
        return this.mIsX86 && activeNetworkInfo.getType() == 9;
    }

    @Override // org.osmdroid.tileprovider.modules.INetworkAvailablityCheck
    public boolean getWiFiNetworkAvailable() {
        if (!this.mHasNetworkStatePermission) {
            return true;
        }
        NetworkInfo networkInfo = this.mConnectionManager.getNetworkInfo(1);
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override // org.osmdroid.tileprovider.modules.INetworkAvailablityCheck
    public boolean getCellularDataNetworkAvailable() {
        if (!this.mHasNetworkStatePermission) {
            return true;
        }
        NetworkInfo networkInfo = this.mConnectionManager.getNetworkInfo(0);
        return networkInfo != null && networkInfo.isConnected();
    }
}
