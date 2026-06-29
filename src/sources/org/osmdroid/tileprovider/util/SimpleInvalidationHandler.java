package org.osmdroid.tileprovider.util;

import android.os.Handler;
import android.os.Message;
import android.view.View;

/* loaded from: classes3.dex */
public class SimpleInvalidationHandler extends Handler {
    private View mView;

    public SimpleInvalidationHandler(View view) {
        this.mView = view;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        View view;
        if (message.what == 0 && (view = this.mView) != null) {
            view.invalidate();
        }
    }

    public void destroy() {
        this.mView = null;
    }
}
