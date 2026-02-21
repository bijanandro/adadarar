package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0096z0 implements Runnable {
    final /* synthetic */ A0 a;

    RunnableC0096z0(A0 a0) {
        this.a = a0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.a.getView().releasePointerCapture();
    }
}
