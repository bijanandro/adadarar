package com.unity3d.player;

import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: com.unity3d.player.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class ViewTreeObserverOnGlobalLayoutListenerC0037d0 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ C0046g0 a;

    ViewTreeObserverOnGlobalLayoutListenerC0037d0(C0046g0 c0046g0) {
        this.a = c0046g0;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.a.reportSoftInputArea();
        this.a.h.b();
    }
}
