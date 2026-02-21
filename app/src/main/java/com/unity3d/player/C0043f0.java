package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.widget.EditText;

/* JADX INFO: renamed from: com.unity3d.player.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0043f0 extends EditText {
    final /* synthetic */ W a;
    final /* synthetic */ C0046g0 b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0043f0(C0046g0 c0046g0, Context context, W w) {
        super(context);
        this.b = c0046g0;
        this.a = w;
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (keyEvent.getAction() == 1) {
                this.b.h.onBackPressed();
            }
            return true;
        }
        if (i == 84) {
            return true;
        }
        if (i == 66 && keyEvent.getAction() == 0 && (getInputType() & 131072) == 0) {
            W w = this.a;
            w.a(w.a(), false);
            return true;
        }
        if (i != 111 || keyEvent.getAction() != 0) {
            return super.onKeyPreIme(i, keyEvent);
        }
        W w2 = this.a;
        w2.a(w2.a(), true);
        return true;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            requestFocus();
            this.a.e();
        }
    }
}
