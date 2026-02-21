package com.unity3d.player;

import android.app.Activity;
import android.app.Dialog;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;

/* JADX INFO: loaded from: classes.dex */
final class H extends G {
    private OnBackInvokedCallback d;
    private OnBackInvokedDispatcher e;
    private int f;

    private H(OnBackInvokedDispatcher onBackInvokedDispatcher, int i, Runnable runnable) {
        super(runnable);
        this.d = null;
        this.f = i;
        this.e = onBackInvokedDispatcher;
    }

    public static G a(Object obj, int i, Runnable runnable) {
        G h = (PlatformSupport.TIRAMISU_SUPPORT && ((obj instanceof Activity) || (obj instanceof Dialog))) ? new H(AbstractC0039e.a(obj), i, runnable) : new G(runnable);
        h.registerOnBackPressedCallback();
        return h;
    }

    @Override // com.unity3d.player.G
    protected void registerOnBackPressedCallback() {
        if (this.a != null) {
            return;
        }
        super.registerOnBackPressedCallback();
        if (PlatformSupport.TIRAMISU_SUPPORT) {
            C0036d c0036d = new C0036d(this.a);
            this.d = c0036d;
            AbstractC0039e.a(this.e, this.f, c0036d);
        }
    }

    @Override // com.unity3d.player.G
    protected void unregisterOnBackPressedCallback() {
        if (this.a != null) {
            if (PlatformSupport.TIRAMISU_SUPPORT) {
                AbstractC0039e.a(this.e, this.d);
                this.d = null;
            }
            super.unregisterOnBackPressedCallback();
        }
    }
}
