package com.unity3d.player;

import android.view.Surface;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
final class s1 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ Surface b;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ UnityPlayerForActivityOrService d;

    s1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, Surface surface, Semaphore semaphore) {
        this.d = unityPlayerForActivityOrService;
        this.a = i;
        this.b = surface;
        this.c = semaphore;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.d.nativeRecreateGfxState(this.a, this.b);
        this.c.release();
    }
}
