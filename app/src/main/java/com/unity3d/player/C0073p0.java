package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0073p0 extends Z0 {
    final /* synthetic */ int b;
    final /* synthetic */ C0082s0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0073p0(C0082s0 c0082s0, UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.c = c0082s0;
        this.b = i;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        if (UnityAccessibilityDelegate.onNodeSelected(this.b)) {
            this.c.a.sendEventForVirtualViewId(this.b, 1);
        }
    }
}
