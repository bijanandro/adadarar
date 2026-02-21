package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class u1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    u1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.nativeResume();
    }
}
