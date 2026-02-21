package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class X implements Runnable {
    final /* synthetic */ Y a;

    X(Y y) {
        this.a = y;
    }

    @Override // java.lang.Runnable
    public final void run() {
        W w = this.a.d;
        w.a(w.a(), true);
    }
}
