package com.unity3d.player;

import android.content.DialogInterface;

/* JADX INFO: renamed from: com.unity3d.player.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class DialogInterfaceOnCancelListenerC0040e0 implements DialogInterface.OnCancelListener {
    final /* synthetic */ C0046g0 a;

    DialogInterfaceOnCancelListenerC0040e0(C0046g0 c0046g0) {
        this.a = c0046g0;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        J j = this.a.f;
        if (j != null) {
            ((x1) j).a();
        }
    }
}
