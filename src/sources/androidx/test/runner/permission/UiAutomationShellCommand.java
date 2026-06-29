package androidx.test.runner.permission;

import android.app.UiAutomation;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.test.InstrumentationRegistry;
import androidx.test.internal.util.Checks;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes5.dex */
class UiAutomationShellCommand extends ShellCommand {
    private static final String TAG = "UiAutomationShellCmd";
    private final PmCommand command;
    private final String permission;
    private final String targetPackage;
    private final UiAutomation uiAutomation = (UiAutomation) Checks.checkNotNull(InstrumentationRegistry.getInstrumentation().getUiAutomation());

    enum PmCommand {
        GRANT_PERMISSION("grant");

        private final String pmCommand;

        PmCommand(String command) {
            this.pmCommand = "pm " + command;
        }

        public String get() {
            return this.pmCommand;
        }
    }

    UiAutomationShellCommand(String targetPackage, String permission, PmCommand pmCommand) {
        this.targetPackage = shellEscape(targetPackage);
        this.permission = shellEscape(permission);
        this.command = pmCommand;
    }

    @Override // androidx.test.runner.permission.ShellCommand
    public void execute() throws Exception {
        executePermissionCommand(commandForPermission());
    }

    protected String commandForPermission() {
        return this.command.get() + " " + this.targetPackage + " " + this.permission;
    }

    private void executePermissionCommand(String cmd) throws Throwable {
        Log.i(TAG, "Requesting permission: " + cmd);
        try {
            awaitTermination(this.uiAutomation.executeShellCommand(cmd), 2L, TimeUnit.SECONDS);
        } catch (TimeoutException unused) {
            Log.e(TAG, "Timeout while executing cmd: " + cmd);
        }
    }

    private static void awaitTermination(ParcelFileDescriptor pfDescriptor, long timeout, TimeUnit unit) throws Throwable {
        long millis = unit.toMillis(timeout);
        long jCurrentTimeMillis = millis > 0 ? System.currentTimeMillis() + millis : 0L;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ParcelFileDescriptor.AutoCloseInputStream(pfDescriptor)));
            do {
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        Log.i(TAG, line);
                    } else {
                        bufferedReader2.close();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } while (jCurrentTimeMillis <= System.currentTimeMillis());
            throw new TimeoutException();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
