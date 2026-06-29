package androidx.compose.ui.semantics;

import androidx.compose.ui.node.LayoutNode;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

/* compiled from: SemanticsOwner.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Landroidx/compose/ui/semantics/SemanticsOwner;", "", "rootNode", "Landroidx/compose/ui/node/LayoutNode;", "(Landroidx/compose/ui/node/LayoutNode;)V", "rootSemanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "getRootSemanticsNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "unmergedRootSemanticsNode", "getUnmergedRootSemanticsNode", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class SemanticsOwner {
    public static final int $stable = 8;
    private final LayoutNode rootNode;

    public SemanticsOwner(LayoutNode layoutNode) {
        this.rootNode = layoutNode;
    }

    public final SemanticsNode getRootSemanticsNode() {
        return SemanticsNodeKt.SemanticsNode(this.rootNode, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0086 A[LOOP:0: B:5:0x0019->B:44:0x0086, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008b A[EDGE_INSN: B:49:0x008b->B:45:0x008b BREAK  A[LOOP:0: B:5:0x0019->B:44:0x0086], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.semantics.SemanticsNode getUnmergedRootSemanticsNode() {
        /*
            r10 = this;
            androidx.compose.ui.node.LayoutNode r0 = r10.rootNode
            androidx.compose.ui.node.NodeChain r0 = r0.getNodes()
            r1 = 8
            int r1 = androidx.compose.ui.node.NodeKind.m4891constructorimpl(r1)
            int r2 = androidx.compose.ui.node.NodeChain.access$getAggregateChildKindSet(r0)
            r2 = r2 & r1
            r3 = 0
            r4 = 0
            if (r2 == 0) goto L8b
            androidx.compose.ui.Modifier$Node r0 = r0.getHead()
        L19:
            if (r0 == 0) goto L8b
            int r2 = r0.getKindSet()
            r2 = r2 & r1
            if (r2 == 0) goto L7f
            r2 = r0
            r5 = r3
        L24:
            if (r2 == 0) goto L7f
            boolean r6 = r2 instanceof androidx.compose.ui.node.SemanticsModifierNode
            if (r6 == 0) goto L2d
            r3 = r2
            goto L8b
        L2d:
            int r6 = r2.getKindSet()
            r6 = r6 & r1
            r7 = 1
            if (r6 == 0) goto L37
            r6 = r7
            goto L38
        L37:
            r6 = r4
        L38:
            if (r6 == 0) goto L7a
            boolean r6 = r2 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto L7a
            r6 = r2
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.getDelegate()
            r8 = r4
        L46:
            if (r6 == 0) goto L77
            int r9 = r6.getKindSet()
            r9 = r9 & r1
            if (r9 == 0) goto L51
            r9 = r7
            goto L52
        L51:
            r9 = r4
        L52:
            if (r9 == 0) goto L72
            int r8 = r8 + 1
            if (r8 != r7) goto L5a
            r2 = r6
            goto L72
        L5a:
            if (r5 != 0) goto L65
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            r9 = 16
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r9]
            r5.<init>(r9, r4)
        L65:
            if (r2 == 0) goto L6d
            if (r5 == 0) goto L6c
            r5.add(r2)
        L6c:
            r2 = r3
        L6d:
            if (r5 == 0) goto L72
            r5.add(r6)
        L72:
            androidx.compose.ui.Modifier$Node r6 = r6.getChild()
            goto L46
        L77:
            if (r8 != r7) goto L7a
            goto L24
        L7a:
            androidx.compose.ui.Modifier$Node r2 = androidx.compose.ui.node.DelegatableNodeKt.access$pop(r5)
            goto L24
        L7f:
            int r2 = r0.getAggregateChildKindSet()
            r2 = r2 & r1
            if (r2 == 0) goto L8b
            androidx.compose.ui.Modifier$Node r0 = r0.getChild()
            goto L19
        L8b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            androidx.compose.ui.node.SemanticsModifierNode r3 = (androidx.compose.ui.node.SemanticsModifierNode) r3
            androidx.compose.ui.Modifier$Node r0 = r3.getNode()
            androidx.compose.ui.node.LayoutNode r1 = r10.rootNode
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = new androidx.compose.ui.semantics.SemanticsConfiguration
            r2.<init>()
            androidx.compose.ui.semantics.SemanticsNode r3 = new androidx.compose.ui.semantics.SemanticsNode
            r3.<init>(r0, r4, r1, r2)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.semantics.SemanticsOwner.getUnmergedRootSemanticsNode():androidx.compose.ui.semantics.SemanticsNode");
    }
}
