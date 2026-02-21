package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.r1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0080r1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    RunnableC0080r1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.nativeSendSurfaceChangedEvent();
    }
}
