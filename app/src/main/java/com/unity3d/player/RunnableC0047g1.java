package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.g1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0047g1 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    RunnableC0047g1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        this.b = unityPlayerForActivityOrService;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        W w = this.b.mSoftInput;
        if (w != null) {
            w.a(this.a);
        }
    }
}
