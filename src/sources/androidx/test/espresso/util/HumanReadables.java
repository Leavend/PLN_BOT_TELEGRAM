package androidx.test.espresso.util;

import android.content.res.Resources;
import android.database.Cursor;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Checkable;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.test.espresso.core.internal.deps.guava.base.Function;
import androidx.test.espresso.core.internal.deps.guava.base.Joiner;
import androidx.test.espresso.core.internal.deps.guava.base.MoreObjects;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.core.internal.deps.guava.base.Strings;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.util.TreeIterables;
import com.kdownloader.database.DownloadModel;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.language.Soundex;
import org.bouncycastle.i18n.TextBundle;

/* loaded from: classes5.dex */
public final class HumanReadables {
    private static final String JAVA_ID = "\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*";
    private static final Pattern OBJECT_HASH_CODE_PATTERN = Pattern.compile("\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*(\\.\\p{javaJavaIdentifierStart}\\p{javaJavaIdentifierPart}*)*@([0-9A-Fa-f]+)");

    private HumanReadables() {
    }

    public static String describe(Cursor cursor) {
        if (cursor.isBeforeFirst()) {
            return "Cursor positioned before first element.";
        }
        if (cursor.isAfterLast()) {
            return "Cursor positioned after last element.";
        }
        StringBuilder sbAppend = new StringBuilder("Row ").append(cursor.getPosition()).append(": {");
        String[] columnNames = cursor.getColumnNames();
        for (int i = 0; i < columnNames.length; i++) {
            sbAppend.append(columnNames[i]).append(":");
            int type = cursor.getType(i);
            if (type == 0) {
                sbAppend.append("null");
            } else if (type == 1) {
                sbAppend.append(cursor.getLong(i));
            } else if (type == 2) {
                sbAppend.append(cursor.getDouble(i));
                sbAppend.append("f");
            } else if (type == 3) {
                sbAppend.append("\"").append(cursor.getString(i)).append("\"");
            } else if (type != 4) {
                sbAppend.append("\"").append(cursor.getString(i)).append("\"");
            } else {
                byte[] blob = cursor.getBlob(i);
                sbAppend.append("[");
                for (int i2 = 0; i2 < 5 && i2 < blob.length; i2++) {
                    sbAppend.append((int) blob[i2]);
                    sbAppend.append(",");
                }
                if (blob.length > 5) {
                    sbAppend.append("... (").append(blob.length - 5).append(" more elements)");
                }
                sbAppend.append("]");
            }
            sbAppend.append(", ");
        }
        sbAppend.append("}");
        return sbAppend.toString();
    }

    public static String getViewHierarchyErrorMessage(View view, List<View> list, String str, String str2) {
        return getViewHierarchyErrorMessage(view, list, str, str2, Integer.MAX_VALUE);
    }

    private static void innerDescribe(ViewGroup viewGroup, MoreObjects.ToStringHelper toStringHelper) {
        toStringHelper.add("child-count", viewGroup.getChildCount());
    }

    private static boolean isViewIdGenerated(int i) {
        return ((-16777216) & i) == 0 && (i & ViewCompat.MEASURED_SIZE_MASK) != 0;
    }

    public static String getViewHierarchyErrorMessage(View view, final List<View> list, String str, final String str2, int i) {
        Preconditions.checkArgument(list == null || str2 != null);
        StringBuilder sb = new StringBuilder(str);
        if (str2 != null) {
            sb.append(String.format(Locale.ROOT, "\nProblem views are marked with '%s' below.", str2));
        }
        sb.append("\n\nView Hierarchy:\n").append(Joiner.on("\n|\n").join(Iterables.transform(TreeIterables.depthFirstViewTraversalWithDistance(view), new Function<TreeIterables.ViewAndDistance, String>() { // from class: androidx.test.espresso.util.HumanReadables.1
            @Override // androidx.test.espresso.core.internal.deps.guava.base.Function
            public String apply(TreeIterables.ViewAndDistance viewAndDistance) {
                List list2 = list;
                String str3 = "+%s%s ";
                if (list2 != null && list2.contains(viewAndDistance.getView())) {
                    str3 = "+%s%s " + str2;
                }
                return String.format(Locale.ROOT, str3, Strings.padStart(">", viewAndDistance.getDistanceFromRoot() + 1, Soundex.SILENT_MARKER), HumanReadables.describe(viewAndDistance.getView()));
            }
        })));
        if (i < Integer.MAX_VALUE && sb.length() + 12 > i) {
            sb.delete(Math.max(0, i - 12), sb.length());
            sb.append(" [truncated]");
        }
        return sb.toString();
    }

    private static void innerDescribe(Checkable checkable, MoreObjects.ToStringHelper toStringHelper) {
        toStringHelper.add("is-checked", checkable.isChecked());
    }

    private static String replaceHashCodes(Object obj) {
        if (obj == null) {
            return null;
        }
        String string = obj.toString();
        Matcher matcher = OBJECT_HASH_CODE_PATTERN.matcher(string);
        if (!matcher.find()) {
            return string;
        }
        return string.substring(0, matcher.start(2)) + "YYYYYY" + string.substring(matcher.end(2));
    }

    private static void innerDescribe(TextView textView, MoreObjects.ToStringHelper toStringHelper) {
        if (textView.getText() != null) {
            toStringHelper.add(TextBundle.TEXT_ENTRY, textView.getText());
        }
        if (textView.getError() != null) {
            toStringHelper.add("error-text", textView.getError());
        }
        if (textView.getHint() != null) {
            toStringHelper.add("hint", textView.getHint());
        }
        toStringHelper.add("input-type", textView.getInputType());
        toStringHelper.add("ime-target", textView.isInputMethodTarget());
        toStringHelper.add("has-links", textView.getUrls().length > 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String describe(View view) {
        if (view == 0) {
            return "null";
        }
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(view).add(DownloadModel.ID, view.getId());
        if (view.getId() != -1 && view.getResources() != null && !isViewIdGenerated(view.getId())) {
            try {
                toStringHelperAdd.add("res-name", view.getResources().getResourceEntryName(view.getId()));
            } catch (Resources.NotFoundException unused) {
            }
        }
        if (view.getContentDescription() != null) {
            toStringHelperAdd.add("desc", view.getContentDescription());
        }
        int visibility = view.getVisibility();
        if (visibility == 0) {
            toStringHelperAdd.add("visibility", "VISIBLE");
        } else if (visibility == 4) {
            toStringHelperAdd.add("visibility", "INVISIBLE");
        } else if (visibility == 8) {
            toStringHelperAdd.add("visibility", "GONE");
        } else {
            toStringHelperAdd.add("visibility", view.getVisibility());
        }
        toStringHelperAdd.add("width", view.getWidth()).add("height", view.getHeight()).add("has-focus", view.hasFocus()).add("has-focusable", view.hasFocusable()).add("has-window-focus", view.hasWindowFocus()).add("is-clickable", view.isClickable()).add("is-enabled", view.isEnabled()).add("is-focused", view.isFocused()).add("is-focusable", view.isFocusable()).add("is-layout-requested", view.isLayoutRequested()).add("is-selected", view.isSelected()).add("layout-params", replaceHashCodes(view.getLayoutParams())).add("tag", view.getTag());
        if (view.getRootView() != null) {
            toStringHelperAdd.add("root-is-layout-requested", view.getRootView().isLayoutRequested());
        }
        EditorInfo editorInfo = new EditorInfo();
        boolean z = view.onCreateInputConnection(editorInfo) != null;
        toStringHelperAdd.add("has-input-connection", z);
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            editorInfo.dump(new StringBuilderPrinter(sb), "");
            sb.append("]");
            toStringHelperAdd.add("editor-info", sb.toString().replace("\n", " "));
        }
        toStringHelperAdd.add("x", view.getX()).add("y", view.getY());
        if (view instanceof TextView) {
            innerDescribe((TextView) view, toStringHelperAdd);
        }
        if (view instanceof Checkable) {
            innerDescribe((Checkable) view, toStringHelperAdd);
        }
        if (view instanceof ViewGroup) {
            innerDescribe((ViewGroup) view, toStringHelperAdd);
        }
        return toStringHelperAdd.toString();
    }
}
