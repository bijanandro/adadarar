package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class L0 implements Runnable {
    L0() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            AbstractC0033c.a();
        } catch (Exception e) {
            D.Log(6, "Exception when hiding Activity Indicator " + e);
        }
    }
}
