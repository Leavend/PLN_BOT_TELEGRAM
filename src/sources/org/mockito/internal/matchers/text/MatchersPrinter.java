package org.mockito.internal.matchers.text;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.mockito.ArgumentMatcher;
import org.mockito.internal.matchers.ContainsExtraTypeInfo;
import org.mockito.internal.reporting.PrintSettings;

/* loaded from: classes3.dex */
public class MatchersPrinter {
    public String getArgumentsLine(List<ArgumentMatcher> list, PrintSettings printSettings) {
        return ValuePrinter.printValues("(", ", ", ");", applyPrintSettings(list, printSettings));
    }

    public String getArgumentsBlock(List<ArgumentMatcher> list, PrintSettings printSettings) {
        return ValuePrinter.printValues("(\n    ", ",\n    ", "\n);", applyPrintSettings(list, printSettings));
    }

    private Iterator<FormattedText> applyPrintSettings(List<ArgumentMatcher> list, PrintSettings printSettings) {
        LinkedList linkedList = new LinkedList();
        int i = 0;
        for (ArgumentMatcher argumentMatcher : list) {
            if ((argumentMatcher instanceof ContainsExtraTypeInfo) && printSettings.extraTypeInfoFor(i)) {
                linkedList.add(new FormattedText(((ContainsExtraTypeInfo) argumentMatcher).toStringWithType()));
            } else {
                linkedList.add(new FormattedText(MatcherToString.toString(argumentMatcher)));
            }
            i++;
        }
        return linkedList.iterator();
    }
}
