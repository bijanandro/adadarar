package com.unity3d.player;

import java.util.concurrent.Semaphore;

/* JADX INFO: renamed from: com.unity3d.player.d1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0038d1 implements Runnable {
    boolean a = false;
    boolean b = false;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ UnityPlayerForActivityOrService d;
    final /* synthetic */ UnityPlayerForActivityOrService e;

    RunnableC0038d1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Semaphore semaphore, UnityPlayerForActivityOrService unityPlayerForActivityOrService2) {
        this.e = unityPlayerForActivityOrService;
        this.c = semaphore;
        this.d = unityPlayerForActivityOrService2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.e.mSoftInput != null) {
            this.d.setOnHandleFocusListener(new C0032b1(this));
            UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.e;
            unityPlayerForActivityOrService.mSoftInput.g = new C0035c1(this);
            unityPlayerForActivityOrService.dismissSoftInput();
        }
    }
}
