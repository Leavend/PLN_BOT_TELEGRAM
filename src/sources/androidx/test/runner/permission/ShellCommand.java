package androidx.test.runner.permission;

/* loaded from: classes5.dex */
public abstract class ShellCommand {
    private static final String SAFE_PUNCTUATION = "@%-_+:,./";

    protected abstract void execute() throws Exception;

    static String shellEscape(String word) {
        int length = word.length();
        if (length == 0) {
            return "''";
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = word.charAt(i);
            if (!Character.isLetterOrDigit(cCharAt) && SAFE_PUNCTUATION.indexOf(cCharAt) == -1) {
                return "'" + word.replace("'", "'\\''") + "'";
            }
        }
        return word;
    }
}
