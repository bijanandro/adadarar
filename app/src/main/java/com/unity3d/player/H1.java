package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
public final class H1 implements Runnable {
    private I1 a;
    private boolean b = false;

    public H1(I1 i1) {
        this.a = i1;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
        if (this.b) {
            return;
        }
        int i = I1.A;
        this.a.cancelOnPrepare();
    }
}
