package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0076q0 extends Z0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ C0082s0 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0076q0(C0082s0 c0082s0, UnityPlayer unityPlayer, int i, int i2) {
        super(unityPlayer);
        this.d = c0082s0;
        this.b = i;
        this.c = i2;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        if (this.b == 4096) {
            UnityAccessibilityDelegate.onNodeIncremented(this.c);
        } else {
            UnityAccessibilityDelegate.onNodeDecremented(this.c);
        }
        this.d.a.sendEventForVirtualViewId(this.c, 4);
    }
}
