package androidx.test.internal.runner;

import android.text.TextUtils;
import androidx.test.internal.runner.RunnerArgs;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
class ClassesArgTokenizer {
    private static final String TAG = "ClassesArgTokenizer";

    private ClassesArgTokenizer() {
    }

    private static abstract class TokenizerState {
        protected final String input;
        protected int pos;
        protected final int startTokenPos;
        protected final List<RunnerArgs.TestArg> testArgs;

        abstract TokenizerState parse();

        protected TokenizerState(List<RunnerArgs.TestArg> testArgs, String input, int pos) {
            this.testArgs = testArgs;
            this.input = input;
            this.pos = pos;
            this.startTokenPos = pos;
        }
    }

    private static class ClassTokenizerState extends TokenizerState {
        private ClassTokenizerState(List<RunnerArgs.TestArg> testArgs, String input, int pos) {
            super(testArgs, input, pos);
        }

        @Override // androidx.test.internal.runner.ClassesArgTokenizer.TokenizerState
        TokenizerState parse() {
            while (this.pos < this.input.length()) {
                if (this.input.charAt(this.pos) == '#') {
                    return new MethodTokenizerState(this.testArgs, this.input, this.pos + 1, this.input.substring(this.startTokenPos, this.pos)).parse();
                }
                if (this.input.charAt(this.pos) == ',') {
                    this.testArgs.add(new RunnerArgs.TestArg(this.input.substring(this.startTokenPos, this.pos)));
                    return new ClassTokenizerState(this.testArgs, this.input, this.pos + 1);
                }
                this.pos++;
            }
            if (this.pos <= this.startTokenPos) {
                return null;
            }
            this.testArgs.add(new RunnerArgs.TestArg(this.input.substring(this.startTokenPos, this.pos)));
            return null;
        }
    }

    private static class MethodTokenizerState extends TokenizerState {
        private final String className;

        protected MethodTokenizerState(List<RunnerArgs.TestArg> testArgs, String input, int pos, String className) {
            super(testArgs, input, pos);
            this.className = className;
        }

        @Override // androidx.test.internal.runner.ClassesArgTokenizer.TokenizerState
        TokenizerState parse() {
            while (true) {
                if (this.pos < this.input.length()) {
                    if (this.input.charAt(this.pos) == ',') {
                        this.testArgs.add(new RunnerArgs.TestArg(this.className, this.input.substring(this.startTokenPos, this.pos)));
                        return new ClassTokenizerState(this.testArgs, this.input, this.pos + 1).parse();
                    }
                    if (this.input.charAt(this.pos) == '[') {
                        this.pos = this.input.indexOf(93, this.pos);
                        if (this.pos <= 0) {
                            throw new IllegalStateException("Could not find closing param ] in input " + this.input);
                        }
                    }
                    if (this.input.charAt(this.pos) == '(') {
                        this.pos = this.input.indexOf(41, this.pos);
                        if (this.pos <= 0) {
                            throw new IllegalStateException("Could not find closing param ) in input " + this.input);
                        }
                    }
                    this.pos++;
                } else {
                    if (this.pos > this.startTokenPos) {
                        this.testArgs.add(new RunnerArgs.TestArg(this.className, this.input.substring(this.startTokenPos, this.pos)));
                    }
                    return null;
                }
            }
        }
    }

    static List<RunnerArgs.TestArg> parse(String input) {
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(input)) {
            for (TokenizerState classTokenizerState = new ClassTokenizerState(arrayList, input, 0); classTokenizerState != null; classTokenizerState = classTokenizerState.parse()) {
            }
        }
        return arrayList;
    }
}
