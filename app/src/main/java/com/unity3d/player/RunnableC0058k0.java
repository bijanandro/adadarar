package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* JADX INFO: renamed from: com.unity3d.player.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0058k0 implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ UnityAccessibilityDelegate b;

    RunnableC0058k0(UnityAccessibilityDelegate unityAccessibilityDelegate, Semaphore semaphore) {
        this.b = unityAccessibilityDelegate;
        this.a = semaphore;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.b;
            if (unityAccessibilityDelegate.c != null) {
                unityAccessibilityDelegate.d = new AccessibilityManagerAccessibilityStateChangeListenerC0086u0(unityAccessibilityDelegate);
            }
            UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.b;
            if (unityAccessibilityDelegate2.e != null) {
                unityAccessibilityDelegate2.f = new C0090w0(this.b);
            }
        } finally {
            this.a.release();
        }
    }
}
