package com.unity3d.player;

import android.view.WindowInsets;

/* JADX INFO: renamed from: com.unity3d.player.a1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0029a1 extends Z0 {
    final /* synthetic */ WindowInsets b;
    final /* synthetic */ ViewOnApplyWindowInsetsListenerC0068n1 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0029a1(ViewOnApplyWindowInsetsListenerC0068n1 viewOnApplyWindowInsetsListenerC0068n1, WindowInsets windowInsets) {
        super(viewOnApplyWindowInsetsListenerC0068n1.a);
        this.c = viewOnApplyWindowInsetsListenerC0068n1;
        this.b = windowInsets;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        this.c.a.nativeOnApplyWindowInsets(this.b);
    }
}
