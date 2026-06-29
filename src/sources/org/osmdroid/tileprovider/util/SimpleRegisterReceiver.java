package org.osmdroid.tileprovider.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import org.osmdroid.tileprovider.IRegisterReceiver;

/* loaded from: classes3.dex */
public class SimpleRegisterReceiver implements IRegisterReceiver {
    private Context mContext;

    public SimpleRegisterReceiver(Context context) {
        this.mContext = context;
    }

    @Override // org.osmdroid.tileprovider.IRegisterReceiver
    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return this.mContext.registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override // org.osmdroid.tileprovider.IRegisterReceiver
    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {
        this.mContext.unregisterReceiver(broadcastReceiver);
    }

    @Override // org.osmdroid.tileprovider.IRegisterReceiver
    public void destroy() {
        this.mContext = null;
    }
}
