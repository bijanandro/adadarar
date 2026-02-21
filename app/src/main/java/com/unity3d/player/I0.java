package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class I0 implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ UnityPlayer b;

    I0(UnityPlayer unityPlayer, boolean z) {
        this.b = unityPlayer;
        this.a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        UnityPlayer unityPlayer = this.b;
        R1.a(unityPlayer.mActivity, unityPlayer.getFrameLayout(), this.a, DisplayCutoutSupport.getDisplayCutoutSupport(this.b.mActivity));
        this.b.dispatchFrameLayoutPadding();
    }
}
