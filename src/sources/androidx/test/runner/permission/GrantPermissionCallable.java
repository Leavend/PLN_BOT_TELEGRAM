package androidx.test.runner.permission;

import android.content.Context;
import android.util.Log;
import androidx.test.runner.permission.RequestPermissionCallable;

/* loaded from: classes5.dex */
class GrantPermissionCallable extends RequestPermissionCallable {
    private static final String TAG = "GrantPermissionCallable";

    GrantPermissionCallable(ShellCommand shellCommand, Context context, String permission) {
        super(shellCommand, context, permission);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public RequestPermissionCallable.Result call() throws Exception {
        if (isPermissionGranted()) {
            Log.i(TAG, "Permission: " + getPermission() + " is already granted!");
            return RequestPermissionCallable.Result.SUCCESS;
        }
        try {
            getShellCommand().execute();
            if (!isPermissionGranted()) {
                Thread.sleep(1000L);
                if (!isPermissionGranted()) {
                    Log.e(TAG, "Permission: " + getPermission() + " cannot be granted!");
                    return RequestPermissionCallable.Result.FAILURE;
                }
            }
            return RequestPermissionCallable.Result.SUCCESS;
        } catch (Throwable th) {
            if (!isPermissionGranted()) {
                Thread.sleep(1000L);
                if (!isPermissionGranted()) {
                    Log.e(TAG, "Permission: " + getPermission() + " cannot be granted!");
                    return RequestPermissionCallable.Result.FAILURE;
                }
            }
            throw th;
        }
    }
}
