package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class J1 implements Runnable {
    final /* synthetic */ K1 a;

    J1(K1 k1) {
        this.a = k1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Q1 q1 = this.a.a.h;
        I1 i1 = q1.f;
        if (i1 != null) {
            q1.a.removeViewFromPlayer(i1);
            q1.i = false;
            q1.f.destroyPlayer();
            q1.f = null;
            P1 p1 = q1.c;
            if (p1 != null) {
                ((R0) p1).a();
            }
        }
        this.a.a.h.a.onResume();
    }
}
