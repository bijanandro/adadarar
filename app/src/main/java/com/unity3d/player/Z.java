package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class Z implements Runnable {
    final /* synthetic */ C0031b0 a;

    Z(C0031b0 c0031b0) {
        this.a = c0031b0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.c.requestFocus();
        this.a.e();
    }
}
