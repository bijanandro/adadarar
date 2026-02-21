package com.unity3d.player;

import android.widget.EditText;

/* JADX INFO: renamed from: com.unity3d.player.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0041e1 implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    RunnableC0041e1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, String str) {
        this.b = unityPlayerForActivityOrService;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        EditText editText;
        W w = this.b.mSoftInput;
        if (w == null || (str = this.a) == null || (editText = w.c) == null) {
            return;
        }
        editText.setText(str);
        w.c.setSelection(str.length());
    }
}
