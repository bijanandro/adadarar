package com.unity3d.player;

import com.google.android.gms.tasks.OnSuccessListener;

/* JADX INFO: renamed from: com.unity3d.player.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0054j implements OnSuccessListener {
    private IAssetPackManagerMobileDataConfirmationCallback a;
    private UnityPlayer b;

    public C0054j(UnityPlayer unityPlayer, IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback) {
        this.b = unityPlayer;
        this.a = iAssetPackManagerMobileDataConfirmationCallback;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        Integer num = (Integer) obj;
        IAssetPackManagerMobileDataConfirmationCallback iAssetPackManagerMobileDataConfirmationCallback = this.a;
        if (iAssetPackManagerMobileDataConfirmationCallback != null) {
            this.b.invokeOnMainThread(new RunnableC0051i(iAssetPackManagerMobileDataConfirmationCallback, num.intValue() == -1));
        }
    }
}
