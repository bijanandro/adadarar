package com.unity3d.player;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
final class B extends FrameLayout {
    private UnityPlayerForActivityOrService a;
    private D0 b;

    public B(Context context, UnityPlayerForActivityOrService unityPlayerForActivityOrService) {
        super(context);
        this.a = unityPlayerForActivityOrService;
        D0 d0 = new D0(unityPlayerForActivityOrService);
        this.b = d0;
        addView(d0);
    }

    public final D0 a() {
        return this.b;
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.b.c()) {
            return false;
        }
        return this.a.injectEvent(motionEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.a.injectEvent(keyEvent);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b.c()) {
            return false;
        }
        return this.a.injectEvent(motionEvent);
    }
}
