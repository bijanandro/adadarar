package com.unity3d.player;

import android.view.KeyEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
final class V implements TextView.OnEditorActionListener {
    final /* synthetic */ W a;

    V(W w) {
        this.a = w;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == 6) {
            W w = this.a;
            w.a(w.a(), false);
        }
        return false;
    }
}
