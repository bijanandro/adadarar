package com.unity3d.player;

import android.text.InputFilter;
import android.widget.EditText;

/* JADX INFO: renamed from: com.unity3d.player.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC0044f1 implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ UnityPlayerForActivityOrService b;

    RunnableC0044f1(UnityPlayerForActivityOrService unityPlayerForActivityOrService, int i) {
        this.b = unityPlayerForActivityOrService;
        this.a = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        W w = this.b.mSoftInput;
        if (w != null) {
            int i = this.a;
            EditText editText = w.c;
            if (editText != null) {
                if (i > 0) {
                    editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
                } else {
                    editText.setFilters(new InputFilter[0]);
                }
            }
        }
    }
}
