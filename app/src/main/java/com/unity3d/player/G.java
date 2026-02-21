package com.unity3d.player;

/* JADX INFO: loaded from: classes.dex */
class G {
    protected Runnable b;
    protected com.unity3d.player.a.e a = null;
    protected boolean c = true;

    protected G(Runnable runnable) {
        this.b = runnable;
    }

    protected void registerOnBackPressedCallback() {
        if (this.a != null) {
            return;
        }
        this.a = new F(this.b);
    }

    protected void unregisterOnBackPressedCallback() {
        this.a = null;
    }
}
