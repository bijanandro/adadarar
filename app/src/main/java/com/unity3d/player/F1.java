package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class F1 implements Runnable {
    final /* synthetic */ I1 a;

    F1(I1 i1) {
        this.a = i1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.destroyPlayer();
        this.a.a(3);
    }
}
