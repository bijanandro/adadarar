package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

/* JADX INFO: renamed from: com.unity3d.player.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0028a0 extends EditText {
    final /* synthetic */ W a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0028a0(Context context, W w) {
        super(context);
        this.a = w;
    }

    @Override // android.widget.TextView
    public final void onEditorAction(int i) {
        if (i == 6) {
            W w = this.a;
            w.a(w.a(), false);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent.getAction() == 1) {
                W w = this.a;
                w.a(w.a(), false);
            }
            return true;
        }
        if (i == 84) {
            return true;
        }
        if (i != 66 || keyEvent.getAction() != 0 || (getInputType() & 131072) != 0) {
            return super.onKeyPreIme(i, keyEvent);
        }
        W w2 = this.a;
        w2.a(w2.a(), false);
        return true;
    }

    @Override // android.widget.TextView
    protected void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        this.a.b.reportSoftInputSelection(i, i2 - i);
    }
}
