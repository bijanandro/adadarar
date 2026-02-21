package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class v1 implements Runnable {
    final /* synthetic */ UnityPlayerForActivityOrService a;

    v1(UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        this.a = unityPlayerForActivityOrService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        D0 view = this.a.getView();
        if (view != null) {
            view.b();
        }
    }
}
