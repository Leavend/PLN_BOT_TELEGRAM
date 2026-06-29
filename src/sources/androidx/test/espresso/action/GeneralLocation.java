package androidx.test.espresso.action;

import android.graphics.Rect;
import android.view.View;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes5.dex */
public abstract class GeneralLocation implements CoordinatesProvider {
    public static final GeneralLocation TOP_LEFT = new AnonymousClass1("TOP_LEFT", 0);
    public static final GeneralLocation TOP_CENTER = new AnonymousClass2("TOP_CENTER", 1);
    public static final GeneralLocation TOP_RIGHT = new AnonymousClass3("TOP_RIGHT", 2);
    public static final GeneralLocation CENTER_LEFT = new AnonymousClass4("CENTER_LEFT", 3);
    public static final GeneralLocation CENTER = new AnonymousClass5("CENTER", 4);
    public static final GeneralLocation CENTER_RIGHT = new AnonymousClass6("CENTER_RIGHT", 5);
    public static final GeneralLocation BOTTOM_LEFT = new AnonymousClass7("BOTTOM_LEFT", 6);
    public static final GeneralLocation BOTTOM_CENTER = new AnonymousClass8("BOTTOM_CENTER", 7);
    public static final GeneralLocation BOTTOM_RIGHT = new AnonymousClass9("BOTTOM_RIGHT", 8);
    public static final GeneralLocation VISIBLE_CENTER = new AnonymousClass10("VISIBLE_CENTER", 9);
    private static final /* synthetic */ GeneralLocation[] $VALUES = $values();

    /* renamed from: androidx.test.espresso.action.GeneralLocation$1, reason: invalid class name */
    enum AnonymousClass1 extends GeneralLocation {
        private AnonymousClass1(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.BEGIN, Position.BEGIN);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$10, reason: invalid class name */
    enum AnonymousClass10 extends GeneralLocation {
        private AnonymousClass10(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinatesOfVisiblePart(view, Position.MIDDLE, Position.MIDDLE);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$2, reason: invalid class name */
    enum AnonymousClass2 extends GeneralLocation {
        private AnonymousClass2(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.BEGIN, Position.MIDDLE);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$3, reason: invalid class name */
    enum AnonymousClass3 extends GeneralLocation {
        private AnonymousClass3(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.BEGIN, Position.END);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$4, reason: invalid class name */
    enum AnonymousClass4 extends GeneralLocation {
        private AnonymousClass4(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.MIDDLE, Position.BEGIN);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$5, reason: invalid class name */
    enum AnonymousClass5 extends GeneralLocation {
        private AnonymousClass5(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.MIDDLE, Position.MIDDLE);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$6, reason: invalid class name */
    enum AnonymousClass6 extends GeneralLocation {
        private AnonymousClass6(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.MIDDLE, Position.END);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$7, reason: invalid class name */
    enum AnonymousClass7 extends GeneralLocation {
        private AnonymousClass7(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.END, Position.BEGIN);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$8, reason: invalid class name */
    enum AnonymousClass8 extends GeneralLocation {
        private AnonymousClass8(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.END, Position.MIDDLE);
        }
    }

    /* renamed from: androidx.test.espresso.action.GeneralLocation$9, reason: invalid class name */
    enum AnonymousClass9 extends GeneralLocation {
        private AnonymousClass9(String str, int i) {
            super(str, i);
        }

        @Override // androidx.test.espresso.action.CoordinatesProvider
        public float[] calculateCoordinates(View view) {
            return GeneralLocation.getCoordinates(view, Position.END, Position.END);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    private static abstract class Position {
        public static final Position BEGIN = new AnonymousClass1("BEGIN", 0);
        public static final Position MIDDLE = new AnonymousClass2("MIDDLE", 1);
        public static final Position END = new AnonymousClass3("END", 2);
        private static final /* synthetic */ Position[] $VALUES = $values();

        /* renamed from: androidx.test.espresso.action.GeneralLocation$Position$1, reason: invalid class name */
        enum AnonymousClass1 extends Position {
            private AnonymousClass1(String str, int i) {
                super(str, i);
            }

            @Override // androidx.test.espresso.action.GeneralLocation.Position
            public float getPosition(int i, int i2) {
                return i;
            }
        }

        /* renamed from: androidx.test.espresso.action.GeneralLocation$Position$2, reason: invalid class name */
        enum AnonymousClass2 extends Position {
            private AnonymousClass2(String str, int i) {
                super(str, i);
            }

            @Override // androidx.test.espresso.action.GeneralLocation.Position
            public float getPosition(int i, int i2) {
                return i + ((i2 - 1) / 2.0f);
            }
        }

        /* renamed from: androidx.test.espresso.action.GeneralLocation$Position$3, reason: invalid class name */
        enum AnonymousClass3 extends Position {
            private AnonymousClass3(String str, int i) {
                super(str, i);
            }

            @Override // androidx.test.espresso.action.GeneralLocation.Position
            public float getPosition(int i, int i2) {
                return (i + i2) - 1;
            }
        }

        private static /* synthetic */ Position[] $values() {
            return new Position[]{BEGIN, MIDDLE, END};
        }

        private Position(String str, int i) {
        }

        public static Position valueOf(String str) {
            return (Position) Enum.valueOf(Position.class, str);
        }

        public static Position[] values() {
            return (Position[]) $VALUES.clone();
        }

        abstract float getPosition(int i, int i2);
    }

    private static /* synthetic */ GeneralLocation[] $values() {
        return new GeneralLocation[]{TOP_LEFT, TOP_CENTER, TOP_RIGHT, CENTER_LEFT, CENTER, CENTER_RIGHT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT, VISIBLE_CENTER};
    }

    private GeneralLocation(String str, int i) {
    }

    public static CoordinatesProvider translate(CoordinatesProvider coordinatesProvider, float f, float f2) {
        return new TranslatedCoordinatesProvider(coordinatesProvider, f, f2);
    }

    public static GeneralLocation valueOf(String str) {
        return (GeneralLocation) Enum.valueOf(GeneralLocation.class, str);
    }

    public static GeneralLocation[] values() {
        return (GeneralLocation[]) $VALUES.clone();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float[] getCoordinates(View view, Position position, Position position2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return new float[]{position2.getPosition(iArr[0], view.getWidth()), position.getPosition(iArr[1], view.getHeight())};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static float[] getCoordinatesOfVisiblePart(View view, Position position, Position position2) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        return new float[]{position2.getPosition(iArr[0], rect.width()), position.getPosition(iArr[1], rect.height())};
    }
}
