package com.unity3d.player;

import android.graphics.Rect;

/* JADX INFO: renamed from: com.unity3d.player.k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0059k1 extends Z0 {
    final /* synthetic */ Rect b;
    final /* synthetic */ UnityPlayerForActivityOrService c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0059k1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, Rect rect) {
        super(unityPlayerForActivityOrService);
        this.c = unityPlayerForActivityOrService;
        this.b = rect;
    }

    @Override // com.unity3d.player.Z0
    public final void a() {
        UnityPlayerForActivityOrService unityPlayerForActivityOrService = this.c;
        Rect rect = this.b;
        unityPlayerForActivityOrService.nativeSetInputArea(rect.left, rect.top, rect.right, rect.bottom);
    }
}
