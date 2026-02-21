package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0064m0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ UnityAccessibilityDelegate c;

    RunnableC0064m0(UnityAccessibilityDelegate unityAccessibilityDelegate, int i, int i2) {
        this.c = unityAccessibilityDelegate;
        this.a = i;
        this.b = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.c.sendEventForVirtualViewId(this.a, this.b);
    }
}
