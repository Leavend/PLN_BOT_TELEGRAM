package net.objecthunter.exp4j;

import java.util.EmptyStackException;

/* loaded from: classes3.dex */
class ArrayStack {
    private double[] data;
    private int idx;

    ArrayStack() {
        this(5);
    }

    ArrayStack(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Stack's capacity must be positive");
        }
        this.data = new double[i];
        this.idx = -1;
    }

    void push(double d) {
        int i = this.idx + 1;
        double[] dArr = this.data;
        if (i == dArr.length) {
            double[] dArr2 = new double[((int) (dArr.length * 1.2d)) + 1];
            System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
            this.data = dArr2;
        }
        double[] dArr3 = this.data;
        int i2 = this.idx + 1;
        this.idx = i2;
        dArr3[i2] = d;
    }

    double peek() {
        int i = this.idx;
        if (i == -1) {
            throw new EmptyStackException();
        }
        return this.data[i];
    }

    double pop() {
        int i = this.idx;
        if (i == -1) {
            throw new EmptyStackException();
        }
        double[] dArr = this.data;
        this.idx = i - 1;
        return dArr[i];
    }

    boolean isEmpty() {
        return this.idx == -1;
    }

    int size() {
        return this.idx + 1;
    }
}
