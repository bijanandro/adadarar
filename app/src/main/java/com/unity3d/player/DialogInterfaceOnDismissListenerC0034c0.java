package com.unity3d.player;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.unity3d.player.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class DialogInterfaceOnDismissListenerC0034c0 implements DialogInterface.OnDismissListener {
    final /* synthetic */ C0046g0 a;

    DialogInterfaceOnDismissListenerC0034c0(C0046g0 c0046g0) {
        this.a = c0046g0;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        this.a.invokeOnClose();
    }
}
