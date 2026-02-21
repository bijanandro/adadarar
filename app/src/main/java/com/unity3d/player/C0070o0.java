package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0070o0 extends Z0 {
    final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0070o0(UnityPlayer unityPlayer, int i) {
        super(unityPlayer);
        this.b = i;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityAccessibilityDelegate.onNodeFocusChanged(this.b, false);
    }
}
