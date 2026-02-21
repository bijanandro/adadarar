package com.unity3d.player;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: renamed from: com.unity3d.player.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnHoverListenerC0092x0 implements View.OnHoverListener {
    final /* synthetic */ UnityAccessibilityDelegate a;

    protected ViewOnHoverListenerC0092x0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
    }

    @Override // android.view.View.OnHoverListener
    public final boolean onHover(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 7 || action == 9) {
            int iHitTest = UnityAccessibilityDelegate.hitTest(motionEvent.getX(), motionEvent.getY());
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            int i = unityAccessibilityDelegate.h;
            if (i == iHitTest) {
                return true;
            }
            unityAccessibilityDelegate.h = iHitTest;
            if (iHitTest != -1) {
                unityAccessibilityDelegate.sendEventForVirtualViewId(iHitTest, 128);
            }
            if (i == -1) {
                return true;
            }
            unityAccessibilityDelegate.sendEventForVirtualViewId(i, 256);
            return true;
        }
        if (action != 10) {
            Log.i("a11y", "hover unknown" + motionEvent.toString());
            return true;
        }
        UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
        int i2 = unityAccessibilityDelegate2.h;
        if (i2 == -1) {
            return true;
        }
        unityAccessibilityDelegate2.h = -1;
        if (i2 == -1) {
            return true;
        }
        unityAccessibilityDelegate2.sendEventForVirtualViewId(i2, 256);
        return true;
    }
}
