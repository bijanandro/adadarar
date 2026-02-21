package com.unity3d.player;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
final class L implements Runnable {
    final /* synthetic */ M a;

    L(M m) {
        this.a = m;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.a.b.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(ViewCompat.MEASURED_STATE_MASK), new BitmapDrawable(this.a.b.getResources(), this.a.b.a)}));
    }
}
