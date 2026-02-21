package com.unity3d.player;

import android.window.OnBackInvokedCallback;

/* JADX INFO: renamed from: com.unity3d.player.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0036d implements OnBackInvokedCallback {
    final /* synthetic */ com.unity3d.player.a.e a;

    C0036d(com.unity3d.player.a.e eVar) {
        this.a = eVar;
    }

    public final void onBackInvoked() {
        Runnable runnable = ((F) this.a).a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
