package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0062l1 extends Z0 {
    final /* synthetic */ boolean b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0062l1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, boolean z) {
        super(unityPlayerForActivityOrService);
        this.c = unityPlayerForActivityOrService;
        this.b = z;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        this.c.nativeSetKeyboardIsVisible(this.b);
    }
}
