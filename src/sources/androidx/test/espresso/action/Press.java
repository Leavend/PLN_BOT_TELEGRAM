package androidx.test.espresso.action;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes5.dex */
public abstract class Press implements PrecisionDescriber {
    public static final Press PINPOINT = new AnonymousClass1("PINPOINT", 0);
    public static final Press FINGER = new AnonymousClass2("FINGER", 1);
    public static final Press THUMB = new AnonymousClass3("THUMB", 2);
    private static final /* synthetic */ Press[] $VALUES = $values();

    /* renamed from: androidx.test.espresso.action.Press$1, reason: invalid class name */
    enum AnonymousClass1 extends Press {
        private AnonymousClass1(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.PrecisionDescriber
        public float[] describePrecision() {
            return new float[]{1.0f, 1.0f};
        }
    }

    /* renamed from: androidx.test.espresso.action.Press$2, reason: invalid class name */
    enum AnonymousClass2 extends Press {
        private AnonymousClass2(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.PrecisionDescriber
        public float[] describePrecision() {
            return new float[]{16.0f, 16.0f};
        }
    }

    /* renamed from: androidx.test.espresso.action.Press$3, reason: invalid class name */
    enum AnonymousClass3 extends Press {
        private AnonymousClass3(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.PrecisionDescriber
        public float[] describePrecision() {
            return new float[]{25.0f, 25.0f};
        }
    }

    private static /* synthetic */ Press[] $values() {
        return new Press[]{PINPOINT, FINGER, THUMB};
    }

    private Press(String str, int i) {
    }

    public static Press valueOf(String str) {
        return (Press) Enum.valueOf(Press.class, str);
    }

    public static Press[] values() {
        return (Press[]) $VALUES.clone();
    }
}
