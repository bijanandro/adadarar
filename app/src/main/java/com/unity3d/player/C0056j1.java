package com.unity3d.player;

/* JADX INFO: renamed from: com.unity3d.player.j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0056j1 extends Z0 {
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ UnityPlayerForActivityOrService d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0056j1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i, int i2) {
        super(unityPlayerForActivityOrService);
        this.d = unityPlayerForActivityOrService;
        this.b = i;
        this.c = i2;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        this.d.nativeSetInputSelection(this.b, this.c);
    }
}
