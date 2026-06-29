package androidx.test.espresso.action;

import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import androidx.test.espresso.InjectEventSecurityException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.remote.annotation.RemoteMsgConstructor;
import androidx.test.espresso.remote.annotation.RemoteMsgField;
import androidx.test.espresso.util.HumanReadables;
import java.util.Locale;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

/* loaded from: classes5.dex */
public final class TypeTextAction implements ViewAction {
    private static final String TAG = "TypeTextAction";
    final GeneralClickAction clickAction;

    @RemoteMsgField(order = 0)
    final String stringToBeTyped;

    @RemoteMsgField(order = 1)
    final boolean tapToFocus;

    public TypeTextAction(String str) {
        this(str, true, defaultClickAction());
    }

    private static GeneralClickAction defaultClickAction() {
        return new GeneralClickAction(Tap.SINGLE, GeneralLocation.CENTER, Press.FINGER, 0, 1);
    }

    @Override // androidx.test.espresso.ViewAction
    public Matcher<View> getConstraints() {
        Matcher matcherAllOf = Matchers.allOf(ViewMatchers.isDisplayed());
        if (!this.tapToFocus) {
            matcherAllOf = Matchers.allOf(matcherAllOf, ViewMatchers.hasFocus());
        }
        return Matchers.allOf(matcherAllOf, Matchers.anyOf(ViewMatchers.supportsInputMethods(), ViewMatchers.isAssignableFrom(SearchView.class)));
    }

    @Override // androidx.test.espresso.ViewAction
    public String getDescription() {
        return String.format(Locale.ROOT, "type text(%s)", this.stringToBeTyped);
    }

    @Override // androidx.test.espresso.ViewAction
    public void perform(UiController uiController, View view) {
        if (this.stringToBeTyped.length() == 0) {
            Log.w(TAG, "Supplied string is empty resulting in no-op (nothing is typed).");
            return;
        }
        if (this.tapToFocus) {
            GeneralClickAction generalClickAction = this.clickAction;
            if (generalClickAction == null) {
                defaultClickAction().perform(uiController, view);
            } else {
                generalClickAction.perform(uiController, view);
            }
            uiController.loopMainThreadUntilIdle();
        }
        try {
            if (uiController.injectString(this.stringToBeTyped)) {
                return;
            }
            Log.e(TAG, "Failed to type text: " + this.stringToBeTyped);
            throw new PerformException.Builder().withActionDescription(getDescription()).withViewDescription(HumanReadables.describe(view)).withCause(new RuntimeException("Failed to type text: " + this.stringToBeTyped)).build();
        } catch (InjectEventSecurityException e) {
            Log.e(TAG, "Failed to type text: " + this.stringToBeTyped);
            throw new PerformException.Builder().withActionDescription(getDescription()).withViewDescription(HumanReadables.describe(view)).withCause(e).build();
        }
    }

    @RemoteMsgConstructor
    public TypeTextAction(String str, boolean z) {
        this(str, z, null);
    }

    public TypeTextAction(String str, boolean z, GeneralClickAction generalClickAction) {
        Preconditions.checkNotNull(str);
        this.stringToBeTyped = str;
        this.tapToFocus = z;
        this.clickAction = generalClickAction;
    }
}
