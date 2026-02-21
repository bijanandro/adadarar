package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class O1 implements Runnable {
    final /* synthetic */ Q1 a;

    O1(Q1 q1) {
        this.a = q1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Q1 q1 = this.a;
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
        this.a.a.onResume();
    }
}
