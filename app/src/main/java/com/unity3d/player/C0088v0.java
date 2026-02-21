package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0088v0 extends Z0 {
    final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0088v0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityAccessibilityDelegate.sendClosedCaptioningChangedNotification(this.b);
    }
}
