package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
final class W0 extends Z0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ X0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    W0(X0 x0, boolean z) {
        super(x0.b);
        this.c = x0;
        this.b = z;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityPlayer.permissionResponseToNative(this.c.a, this.b);
    }
}
