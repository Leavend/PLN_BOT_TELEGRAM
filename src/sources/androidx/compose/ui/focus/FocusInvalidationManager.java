package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeKind;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: FocusInvalidationManager.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0018\u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\tJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\rJ%\u0010\u000f\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110\b2\u0006\u0010\u0010\u001a\u0002H\u0011H\u0002¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0002\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/focus/FocusInvalidationManager;", "", "onRequestApplyChangesListener", "Lkotlin/Function1;", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function1;)V", "focusEventNodes", "", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "focusPropertiesNodes", "Landroidx/compose/ui/focus/FocusPropertiesModifierNode;", "focusTargetNodes", "Landroidx/compose/ui/focus/FocusTargetNode;", "invalidateNodes", "scheduleInvalidation", "node", ExifInterface.GPS_DIRECTION_TRUE, "(Ljava/util/Set;Ljava/lang/Object;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes5.dex */
public final class FocusInvalidationManager {
    public static final int $stable = 8;
    private final Function1<Function0<Unit>, Unit> onRequestApplyChangesListener;
    private Set<FocusTargetNode> focusTargetNodes = new LinkedHashSet();
    private Set<FocusEventModifierNode> focusEventNodes = new LinkedHashSet();
    private Set<FocusPropertiesModifierNode> focusPropertiesNodes = new LinkedHashSet();
    private final Function0<Unit> invalidateNodes = new Function0<Unit>() { // from class: androidx.compose.ui.focus.FocusInvalidationManager$invalidateNodes$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            int i;
            FocusStateImpl focusState;
            FocusStateImpl focusState2;
            Set set = this.this$0.focusPropertiesNodes;
            FocusInvalidationManager focusInvalidationManager = this.this$0;
            Iterator it = set.iterator();
            while (true) {
                int i2 = 1024;
                int i3 = 1;
                if (!it.hasNext()) {
                    this.this$0.focusPropertiesNodes.clear();
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Set<FocusEventModifierNode> set2 = this.this$0.focusEventNodes;
                    FocusInvalidationManager focusInvalidationManager2 = this.this$0;
                    for (FocusEventModifierNode focusEventModifierNode : set2) {
                        if (focusEventModifierNode.getNode().getIsAttached()) {
                            FocusEventModifierNode focusEventModifierNode2 = focusEventModifierNode;
                            int iM4891constructorimpl = NodeKind.m4891constructorimpl(i2);
                            Modifier.Node node = focusEventModifierNode2.getNode();
                            int i4 = 0;
                            int i5 = i3;
                            FocusTargetNode focusTargetNode = null;
                            MutableVector mutableVector = null;
                            while (node != null) {
                                if (node instanceof FocusTargetNode) {
                                    FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
                                    if (focusTargetNode != null) {
                                        i4 = i3;
                                    }
                                    if (focusInvalidationManager2.focusTargetNodes.contains(focusTargetNode2)) {
                                        linkedHashSet.add(focusTargetNode2);
                                        i5 = 0;
                                    }
                                    focusTargetNode = focusTargetNode2;
                                } else if (((node.getKindSet() & iM4891constructorimpl) != 0 ? i3 : 0) != 0 && (node instanceof DelegatingNode)) {
                                    Modifier.Node delegate = ((DelegatingNode) node).getDelegate();
                                    int i6 = 0;
                                    while (delegate != null) {
                                        if (((delegate.getKindSet() & iM4891constructorimpl) != 0 ? i3 : 0) != 0) {
                                            i6++;
                                            if (i6 == i3) {
                                                node = delegate;
                                            } else {
                                                if (mutableVector == null) {
                                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                }
                                                if (node != null) {
                                                    if (mutableVector != null) {
                                                        Boolean.valueOf(mutableVector.add(node));
                                                    }
                                                    node = null;
                                                }
                                                if (mutableVector != null) {
                                                    Boolean.valueOf(mutableVector.add(delegate));
                                                }
                                            }
                                        }
                                        delegate = delegate.getChild();
                                        i3 = 1;
                                    }
                                    int i7 = i3;
                                    if (i6 == i7) {
                                        i3 = i7;
                                    }
                                }
                                node = DelegatableNodeKt.pop(mutableVector);
                                i3 = 1;
                            }
                            if (!focusEventModifierNode2.getNode().getIsAttached()) {
                                throw new IllegalStateException("visitChildren called on an unattached node".toString());
                            }
                            MutableVector mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                            Modifier.Node child = focusEventModifierNode2.getNode().getChild();
                            if (child == null) {
                                DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, focusEventModifierNode2.getNode());
                            } else {
                                mutableVector2.add(child);
                            }
                            while (mutableVector2.isNotEmpty()) {
                                Modifier.Node nodePop = (Modifier.Node) mutableVector2.removeAt(mutableVector2.getSize() - 1);
                                if ((nodePop.getAggregateChildKindSet() & iM4891constructorimpl) == 0) {
                                    DelegatableNodeKt.addLayoutNodeChildren(mutableVector2, nodePop);
                                } else {
                                    while (nodePop != null) {
                                        if ((nodePop.getKindSet() & iM4891constructorimpl) != 0) {
                                            MutableVector mutableVector3 = null;
                                            while (nodePop != null) {
                                                if (nodePop instanceof FocusTargetNode) {
                                                    FocusTargetNode focusTargetNode3 = (FocusTargetNode) nodePop;
                                                    if (focusTargetNode != null) {
                                                        i4 = 1;
                                                    }
                                                    if (focusInvalidationManager2.focusTargetNodes.contains(focusTargetNode3)) {
                                                        linkedHashSet.add(focusTargetNode3);
                                                        i5 = 0;
                                                    }
                                                    focusTargetNode = focusTargetNode3;
                                                } else if (((nodePop.getKindSet() & iM4891constructorimpl) != 0) && (nodePop instanceof DelegatingNode)) {
                                                    int i8 = 0;
                                                    for (Modifier.Node delegate2 = ((DelegatingNode) nodePop).getDelegate(); delegate2 != null; delegate2 = delegate2.getChild()) {
                                                        if ((delegate2.getKindSet() & iM4891constructorimpl) != 0) {
                                                            i8++;
                                                            if (i8 == 1) {
                                                                nodePop = delegate2;
                                                            } else {
                                                                if (mutableVector3 == null) {
                                                                    mutableVector3 = new MutableVector(new Modifier.Node[16], 0);
                                                                }
                                                                if (nodePop != null) {
                                                                    if (mutableVector3 != null) {
                                                                        Boolean.valueOf(mutableVector3.add(nodePop));
                                                                    }
                                                                    nodePop = null;
                                                                }
                                                                if (mutableVector3 != null) {
                                                                    Boolean.valueOf(mutableVector3.add(delegate2));
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if (i8 != 1) {
                                                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                                                    }
                                                }
                                                nodePop = DelegatableNodeKt.pop(mutableVector3);
                                            }
                                        } else {
                                            nodePop = nodePop.getChild();
                                        }
                                    }
                                }
                            }
                            i = 1;
                            if (i5 != 0) {
                                if (i4 != 0) {
                                    focusState2 = FocusEventModifierNodeKt.getFocusState(focusEventModifierNode);
                                } else {
                                    if (focusTargetNode == null || (focusState = focusTargetNode.getFocusState()) == null) {
                                        focusState = FocusStateImpl.Inactive;
                                    }
                                    focusState2 = focusState;
                                }
                                focusEventModifierNode.onFocusEvent(focusState2);
                            }
                        } else {
                            focusEventModifierNode.onFocusEvent(FocusStateImpl.Inactive);
                            i = i3;
                        }
                        i3 = i;
                        i2 = 1024;
                    }
                    this.this$0.focusEventNodes.clear();
                    for (FocusTargetNode focusTargetNode4 : this.this$0.focusTargetNodes) {
                        if (focusTargetNode4.getIsAttached()) {
                            FocusStateImpl focusState3 = focusTargetNode4.getFocusState();
                            focusTargetNode4.invalidateFocus$ui_release();
                            if (focusState3 != focusTargetNode4.getFocusState() || linkedHashSet.contains(focusTargetNode4)) {
                                FocusEventModifierNodeKt.refreshFocusEventNodes(focusTargetNode4);
                            }
                        }
                    }
                    this.this$0.focusTargetNodes.clear();
                    linkedHashSet.clear();
                    if (!this.this$0.focusPropertiesNodes.isEmpty()) {
                        throw new IllegalStateException("Unprocessed FocusProperties nodes".toString());
                    }
                    if (!this.this$0.focusEventNodes.isEmpty()) {
                        throw new IllegalStateException("Unprocessed FocusEvent nodes".toString());
                    }
                    if (!this.this$0.focusTargetNodes.isEmpty()) {
                        throw new IllegalStateException("Unprocessed FocusTarget nodes".toString());
                    }
                    return;
                }
                FocusPropertiesModifierNode focusPropertiesModifierNode = (FocusPropertiesModifierNode) it.next();
                if (focusPropertiesModifierNode.getNode().getIsAttached()) {
                    FocusPropertiesModifierNode focusPropertiesModifierNode2 = focusPropertiesModifierNode;
                    int iM4891constructorimpl2 = NodeKind.m4891constructorimpl(1024);
                    Modifier.Node node2 = focusPropertiesModifierNode2.getNode();
                    MutableVector mutableVector4 = null;
                    while (node2 != null) {
                        if (node2 instanceof FocusTargetNode) {
                            focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node2);
                        } else if (((node2.getKindSet() & iM4891constructorimpl2) != 0) && (node2 instanceof DelegatingNode)) {
                            int i9 = 0;
                            for (Modifier.Node delegate3 = ((DelegatingNode) node2).getDelegate(); delegate3 != null; delegate3 = delegate3.getChild()) {
                                if ((delegate3.getKindSet() & iM4891constructorimpl2) != 0) {
                                    i9++;
                                    if (i9 == 1) {
                                        node2 = delegate3;
                                    } else {
                                        if (mutableVector4 == null) {
                                            mutableVector4 = new MutableVector(new Modifier.Node[16], 0);
                                        }
                                        if (node2 != null) {
                                            if (mutableVector4 != null) {
                                                Boolean.valueOf(mutableVector4.add(node2));
                                            }
                                            node2 = null;
                                        }
                                        if (mutableVector4 != null) {
                                            Boolean.valueOf(mutableVector4.add(delegate3));
                                        }
                                    }
                                }
                            }
                            if (i9 == 1) {
                            }
                        }
                        node2 = DelegatableNodeKt.pop(mutableVector4);
                    }
                    if (!focusPropertiesModifierNode2.getNode().getIsAttached()) {
                        throw new IllegalStateException("visitChildren called on an unattached node".toString());
                    }
                    MutableVector mutableVector5 = new MutableVector(new Modifier.Node[16], 0);
                    Modifier.Node child2 = focusPropertiesModifierNode2.getNode().getChild();
                    if (child2 == null) {
                        DelegatableNodeKt.addLayoutNodeChildren(mutableVector5, focusPropertiesModifierNode2.getNode());
                    } else {
                        mutableVector5.add(child2);
                    }
                    while (mutableVector5.isNotEmpty()) {
                        Modifier.Node nodePop2 = (Modifier.Node) mutableVector5.removeAt(mutableVector5.getSize() - 1);
                        if ((nodePop2.getAggregateChildKindSet() & iM4891constructorimpl2) == 0) {
                            DelegatableNodeKt.addLayoutNodeChildren(mutableVector5, nodePop2);
                        } else {
                            while (true) {
                                if (nodePop2 == null) {
                                    break;
                                }
                                if ((nodePop2.getKindSet() & iM4891constructorimpl2) != 0) {
                                    MutableVector mutableVector6 = null;
                                    while (nodePop2 != null) {
                                        if (nodePop2 instanceof FocusTargetNode) {
                                            focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) nodePop2);
                                        } else if (((nodePop2.getKindSet() & iM4891constructorimpl2) != 0) && (nodePop2 instanceof DelegatingNode)) {
                                            int i10 = 0;
                                            for (Modifier.Node delegate4 = ((DelegatingNode) nodePop2).getDelegate(); delegate4 != null; delegate4 = delegate4.getChild()) {
                                                if ((delegate4.getKindSet() & iM4891constructorimpl2) != 0) {
                                                    i10++;
                                                    if (i10 == 1) {
                                                        nodePop2 = delegate4;
                                                    } else {
                                                        if (mutableVector6 == null) {
                                                            mutableVector6 = new MutableVector(new Modifier.Node[16], 0);
                                                        }
                                                        if (nodePop2 != null) {
                                                            if (mutableVector6 != null) {
                                                                Boolean.valueOf(mutableVector6.add(nodePop2));
                                                            }
                                                            nodePop2 = null;
                                                        }
                                                        if (mutableVector6 != null) {
                                                            Boolean.valueOf(mutableVector6.add(delegate4));
                                                        }
                                                    }
                                                }
                                            }
                                            if (i10 == 1) {
                                            }
                                        }
                                        nodePop2 = DelegatableNodeKt.pop(mutableVector6);
                                    }
                                } else {
                                    nodePop2 = nodePop2.getChild();
                                }
                            }
                        }
                    }
                }
            }
        }
    };

    /* JADX WARN: Multi-variable type inference failed */
    public FocusInvalidationManager(Function1<? super Function0<Unit>, Unit> function1) {
        this.onRequestApplyChangesListener = function1;
    }

    public final void scheduleInvalidation(FocusTargetNode node) {
        scheduleInvalidation(this.focusTargetNodes, node);
    }

    public final void scheduleInvalidation(FocusEventModifierNode node) {
        scheduleInvalidation(this.focusEventNodes, node);
    }

    public final void scheduleInvalidation(FocusPropertiesModifierNode node) {
        scheduleInvalidation(this.focusPropertiesNodes, node);
    }

    private final <T> void scheduleInvalidation(Set<T> set, T t) {
        if (set.add(t) && this.focusTargetNodes.size() + this.focusEventNodes.size() + this.focusPropertiesNodes.size() == 1) {
            this.onRequestApplyChangesListener.invoke(this.invalidateNodes);
        }
    }
}
