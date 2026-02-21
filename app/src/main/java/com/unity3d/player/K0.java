package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class K0 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ UnityPlayer b;

    K0(UnityPlayer unityPlayer, int i) {
        this.b = unityPlayer;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            UnityPlayer unityPlayer = this.b;
            AbstractC0033c.a(unityPlayer.mActivity, unityPlayer.getFrameLayout(), this.a);
        } catch (Exception e) {
            D.Log(6, "Exception when opening Activity Indicator " + e);
        }
    }
}
