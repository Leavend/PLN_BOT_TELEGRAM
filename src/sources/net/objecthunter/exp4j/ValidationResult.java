package net.objecthunter.exp4j;

import java.util.List;

/* loaded from: classes3.dex */
public class ValidationResult {
    public static final ValidationResult SUCCESS = new ValidationResult(true, null);
    private final List<String> errors;
    private final boolean valid;

    public ValidationResult(boolean z, List<String> list) {
        this.valid = z;
        this.errors = list;
    }

    public boolean isValid() {
        return this.valid;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
