package androidx.test.espresso.internal.data;

import android.graphics.Rect;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.internal.data.model.ActionData;
import androidx.test.espresso.internal.data.model.ScreenData;
import androidx.test.espresso.internal.data.model.TestArtifact;
import androidx.test.espresso.internal.data.model.TestFlow;
import androidx.test.espresso.internal.data.model.ViewData;
import androidx.test.internal.platform.util.TestOutputEmitter;
import androidx.test.internal.util.Checks;
import androidx.test.platform.io.PlatformTestStorage;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.text.Typography;

/* loaded from: classes5.dex */
public class TestFlowVisualizer {
    private static final String LOG_TAG = "TestFlowVisualizer";
    private static final String TEST_FLOW_ARG = "enable_testflow_gallery";
    private static TestFlowVisualizer testFlowVisualizer;
    private int actionIndex;
    private Boolean enabled;
    private final PlatformTestStorage platformTestStorage;
    private final TestFlow testFlow;

    TestFlowVisualizer(PlatformTestStorage platformTestStorage) {
        this(platformTestStorage, new TestFlow());
    }

    private Rect adjustViewCoords(View view) {
        float[] fArrCalculateCoordinates = GeneralLocation.TOP_LEFT.calculateCoordinates(view);
        float[] fArrCalculateCoordinates2 = GeneralLocation.BOTTOM_RIGHT.calculateCoordinates(view);
        fArrCalculateCoordinates2[1] = Math.min(fArrCalculateCoordinates2[1], 800.0f);
        return new Rect((int) fArrCalculateCoordinates[0], (int) fArrCalculateCoordinates[1], (int) fArrCalculateCoordinates2[0], (int) fArrCalculateCoordinates2[1]);
    }

    private void beginActionOutput(PrintStream printStream) {
        printStream.append("<div class=\"action\"><div style=\"position:relative; display:inline-block;\">");
    }

    private void displayActionData(ActionData actionData, PrintStream printStream) {
        if (actionData.getName() != null) {
            printStream.append((CharSequence) String.format(Locale.getDefault(), "<p>Classname: %s</p>", actionData.getName()));
        }
        if (actionData.getDesc() != null) {
            printStream.append((CharSequence) String.format(Locale.getDefault(), "<p>Description: %s</p>", actionData.getDesc()));
        }
        if (actionData.getConstraints() != null) {
            printStream.append((CharSequence) String.format(Locale.getDefault(), "<p>Constraints: %s</p>", actionData.getConstraints().replace(Typography.less, '(').replace(Typography.greater, ')')));
        }
        printStream.append("</div>");
    }

    private void displayScreenshot(String str, PrintStream printStream) {
        printStream.append("<div style=\"width:480px; display: inline-block\">");
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "<img src=\"./%s\" />\n", str));
        printStream.append("</div>");
    }

    private void displayViewData(ViewData viewData, PrintStream printStream) {
        Rect viewBox = viewData.getViewBox();
        Rect visibleViewBox = viewData.getVisibleViewBox();
        int i = viewBox.left;
        int i2 = viewBox.right;
        int i3 = viewBox.top;
        int i4 = viewBox.bottom;
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "<div style=\"border:3px solid rgba(255, 0, 0, .5); width:%d; height:%d", Integer.valueOf(visibleViewBox.right - visibleViewBox.left), Integer.valueOf(visibleViewBox.bottom - (visibleViewBox.top + 3))));
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "px; position:absolute; top:%dpx; left: %dpx; z-index:10;\"></div>", Integer.valueOf(visibleViewBox.top - 3), Integer.valueOf(visibleViewBox.left - 3)));
        int i5 = i2 - i;
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "<div style=\"border:3px solid rgba(0, 0, 255, .5); width:%s; height:%s", Integer.valueOf(i5), Integer.valueOf(i4 - (i3 + 3))));
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "; position:absolute; top:%spx; left: %spx; z-index:9;\"></div>", Integer.valueOf(i3 - 3), Integer.valueOf(i - 3)));
        printStream.append("<div class=\"action-item\">");
        printStream.append("<div style=\"border:3px solid rgba(255, 0, 0, .5);\">Visible View</div>");
        printStream.append("<div style=\"border:3px solid rgba(0, 0, 255, .5);\">Actual View</div>");
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "<p>%s</p>", viewData.getDesc()));
        printStream.append((CharSequence) String.format("View: %s<br />", viewBox));
        Locale locale = Locale.ENGLISH;
        visibleViewBox.getClass();
        printStream.append((CharSequence) String.format(locale, "<p>Visible portion: %s</p>", visibleViewBox));
        printStream.append((CharSequence) String.format(Locale.ENGLISH, "This view is %s%% visible.", Float.valueOf(Math.max(Math.min((visibleViewBox.bottom - visibleViewBox.top) / (i4 - i3), 1.0f) * Math.min((visibleViewBox.right - visibleViewBox.left) / i5, 1.0f) * 100.0f, 0.0f))));
    }

    private void endActionOutput(PrintStream printStream) {
        printStream.append("</div></div>");
    }

    public static TestFlowVisualizer getInstance(PlatformTestStorage platformTestStorage) {
        TestFlowVisualizer testFlowVisualizer2 = testFlowVisualizer;
        if (testFlowVisualizer2 == null) {
            testFlowVisualizer = new TestFlowVisualizer(platformTestStorage);
        } else if (testFlowVisualizer2.platformTestStorage != platformTestStorage) {
            throw new IllegalStateException("getInstance called with different instance of PlatformTestStorage.");
        }
        return testFlowVisualizer;
    }

    private void setStyling(PrintStream printStream) {
        printStream.append("<style>\n.action-item {\ndisplay:inline-block;\nwidth:450px;\n");
        printStream.append("margin-left:10px;\nmargin-right:10px;\n}\n</style>");
    }

    public void afterActionGenerateTestArtifact(int i) {
        TestOutputEmitter.takeScreenshot("screenshot-after-" + i + ".png");
    }

    public void afterActionRecordData(ActionData actionData) {
        Checks.checkState(Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called off the main application thread (on: %s)", Thread.currentThread().getName());
        Checks.checkNotNull(actionData, "Requires ActionData to store in graph.");
        ScreenData tail = this.testFlow.getTail();
        ScreenData screenData = new ScreenData();
        actionData.source = tail;
        actionData.dest = screenData;
        this.testFlow.addScreen(screenData, actionData);
    }

    public void beforeActionGenerateTestArtifact(int i) {
        TestOutputEmitter.takeScreenshot("screenshot-before-" + i + ".png");
    }

    public void beforeActionRecordData(ActionData actionData, View view) {
        Checks.checkState(Thread.currentThread().equals(Looper.getMainLooper().getThread()), "Method cannot be called off the main application thread (on: %s)", Thread.currentThread().getName());
        Checks.checkNotNull(actionData, "Requires actionData to store in graph.");
        Checks.checkNotNull(view, "Requires View to analyze.");
        if (actionData.getIndex() == null) {
            throw new IllegalStateException("ActionData must have a distinguishing index.");
        }
        if (this.testFlow.getEdge(actionData.getIndex().intValue()) != null) {
            throw new IllegalStateException("Currently appending to existing ActionData objects is not supported.");
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        ScreenData screenData = new ScreenData();
        screenData.addViewData(new ViewData(view.toString(), adjustViewCoords(view), rect));
        this.testFlow.addScreen(screenData);
    }

    public int getLastActionIndex() {
        return this.actionIndex;
    }

    public int getLastActionIndexAndIncrement() {
        int i = this.actionIndex;
        this.actionIndex = i + 1;
        return i;
    }

    public boolean isEnabled() {
        if (this.enabled == null) {
            this.enabled = Boolean.valueOf(this.platformTestStorage.getInputArgs().containsKey(TEST_FLOW_ARG) && Boolean.parseBoolean(this.platformTestStorage.getInputArg(TEST_FLOW_ARG)));
        }
        return this.enabled.booleanValue();
    }

    public void visualize() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            PrintStream printStream = new PrintStream(this.platformTestStorage.openOutputFile("output_gallery.html"));
            try {
                ScreenData head = this.testFlow.getHead();
                if (head == null) {
                    Log.d(LOG_TAG, "Exiting process 'visualize()', TestFlow graph is empty.");
                    printStream.close();
                    return;
                }
                this.testFlow.resetTraversal();
                setStyling(printStream);
                int i = 0;
                while (!head.getActions().isEmpty() && head.getActionIndex() < head.getActions().size()) {
                    beginActionOutput(printStream);
                    String str = "screenshot-before-" + i + ".png";
                    head.addArtifact(new TestArtifact(str, ".png"));
                    displayScreenshot(str, printStream);
                    if (head.getActions().isEmpty()) {
                        printStream.close();
                        return;
                    }
                    ActionData actionData = head.getActions().get(head.getActionIndex());
                    List<ViewData> views = head.getViews();
                    if (actionData.getDesc() != null) {
                        if (actionData.getDesc().contains("scroll") || head.getViews().isEmpty()) {
                            printStream.append("<div class=\"action-item\">");
                        } else {
                            Iterator<ViewData> it = views.iterator();
                            while (it.hasNext()) {
                                displayViewData(it.next(), printStream);
                            }
                        }
                        displayActionData(actionData, printStream);
                    } else if (!views.isEmpty()) {
                        Iterator<ViewData> it2 = views.iterator();
                        while (it2.hasNext()) {
                            displayViewData(it2.next(), printStream);
                        }
                    }
                    ScreenData dest = actionData.getDest();
                    head.setActionIndex(head.getActionIndex() + 1);
                    String str2 = "screenshot-after-" + i + ".png";
                    head.addArtifact(new TestArtifact(str2, ".png"));
                    displayScreenshot(str2, printStream);
                    if (!dest.getActions().isEmpty() && dest.getActions().get(dest.getActionIndex()) != null) {
                        head = dest.getActions().get(dest.getActionIndex()).getDest();
                        dest.setActionIndex(dest.getActionIndex() + 1);
                    }
                    endActionOutput(printStream);
                    i++;
                }
                printStream.close();
            } finally {
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Exception thrown while trying to display TestFlow.", e);
        }
    }

    TestFlowVisualizer(PlatformTestStorage platformTestStorage, TestFlow testFlow) {
        this.actionIndex = 0;
        this.platformTestStorage = (PlatformTestStorage) Checks.checkNotNull(platformTestStorage);
        this.testFlow = (TestFlow) Checks.checkNotNull(testFlow);
    }
}
