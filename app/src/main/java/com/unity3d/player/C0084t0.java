package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0084t0 extends Z0 {
    final /* synthetic */ boolean b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0084t0(UnityPlayer unityPlayer, boolean z) {
        super(unityPlayer);
        this.b = z;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityAccessibilityDelegate.sendScreenReaderStatusChangedNotification(this.b);
    }
}
