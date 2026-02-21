package com.unity3d.player;

import android.view.accessibility.AccessibilityManager;
import java.util.Objects;

/* JADX INFO: renamed from: com.unity3d.player.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class AccessibilityManagerAccessibilityStateChangeListenerC0086u0 implements AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    AccessibilityManagerAccessibilityStateChangeListenerC0086u0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
        unityAccessibilityDelegate.c.addAccessibilityStateChangeListener(this);
        unityAccessibilityDelegate.c.addTouchExplorationStateChangeListener(this);
        if (unityAccessibilityDelegate.c.isEnabled()) {
            onAccessibilityStateChanged(true);
        }
    }

    protected void cleanup() {
        this.a.c.removeAccessibilityStateChangeListener(this);
        this.a.c.removeTouchExplorationStateChangeListener(this);
    }

    @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    public final void onAccessibilityStateChanged(boolean z) {
        if (!z) {
            this.a.b.setAccessibilityDelegate(null);
            this.a.b.setWillNotDraw(true);
            onTouchExplorationStateChanged(false);
        } else {
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            unityAccessibilityDelegate.b.setAccessibilityDelegate(unityAccessibilityDelegate);
            this.a.b.setWillNotDraw(false);
            onTouchExplorationStateChanged(this.a.c.isTouchExplorationEnabled());
        }
    }

    @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    public final void onTouchExplorationStateChanged(boolean z) {
        boolean z2 = this.a.c.isEnabled() && z;
        UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
        if (z2) {
            unityAccessibilityDelegate.b.setOnHoverListener(new ViewOnHoverListenerC0092x0(unityAccessibilityDelegate));
        } else {
            unityAccessibilityDelegate.b.setOnHoverListener(null);
        }
        UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
        if (unityAccessibilityDelegate2.i == z2) {
            return;
        }
        unityAccessibilityDelegate2.i = z2;
        UnityPlayer unityPlayer = unityAccessibilityDelegate2.a;
        Objects.requireNonNull(unityPlayer);
        this.a.a.invokeOnMainThread((Runnable) new C0084t0(unityPlayer, z2));
    }
}
