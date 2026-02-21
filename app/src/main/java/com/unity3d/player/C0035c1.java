package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.c1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0035c1 implements K {
    final /* synthetic */ RunnableC0038d1 a;

    C0035c1(RunnableC0038d1 runnableC0038d1) {
        this.a = runnableC0038d1;
    }

    public final void a() {
        RunnableC0038d1 runnableC0038d1 = this.a;
        runnableC0038d1.a = true;
        if (runnableC0038d1.b) {
            runnableC0038d1.c.release();
        }
    }
}
