package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class M1 implements Runnable {
    final /* synthetic */ Q1 a;

    M1(Q1 q1) {
        this.a = q1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.a.onPause();
    }
}
