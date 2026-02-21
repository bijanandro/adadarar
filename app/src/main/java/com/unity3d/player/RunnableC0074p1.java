package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* JADX INFO: renamed from: com.unity3d.player.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0074p1 implements Runnable {
    final /* synthetic */ Semaphore a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    RunnableC0074p1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore) {
        this.b = unityPlayerForActivityOrService;
        this.a = semaphore;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.b.nativePause()) {
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.b;
            unityPlayerForActivityOrService.mQuitting = true;
            unityPlayerForActivityOrService.shutdown();
            this.b.queueDestroy();
        }
        this.a.release();
    }
}
