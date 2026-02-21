package com.unity3d.player;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.Objects;

/* JADX INFO: renamed from: com.unity3d.player.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
final class C0082s0 extends AccessibilityNodeProvider {
    final /* synthetic */ UnityAccessibilityDelegate a;

    C0082s0(UnityAccessibilityDelegate unityAccessibilityDelegate) {
        this.a = unityAccessibilityDelegate;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        if (i != -1) {
            AccessibilityNodeInfo accessibilityNodeInfoObtain = AccessibilityNodeInfo.obtain();
            if (UnityAccessibilityDelegate.populateNodeInfo(accessibilityNodeInfoObtain, i, this.a.b)) {
                return accessibilityNodeInfoObtain;
            }
            return null;
        }
        AccessibilityNodeInfo accessibilityNodeInfoObtain2 = AccessibilityNodeInfo.obtain(this.a.b);
        Object parent = this.a.b.getParent();
        if (parent instanceof View) {
            accessibilityNodeInfoObtain2.setParent((View) parent);
        }
        int[] rootNodeIds = UnityAccessibilityDelegate.getRootNodeIds();
        if (rootNodeIds != null) {
            for (int i2 : rootNodeIds) {
                accessibilityNodeInfoObtain2.addChild(this.a.b, i2);
            }
        }
        return accessibilityNodeInfoObtain2;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public final boolean performAction(int i, int i2, Bundle bundle) {
        if (i2 == 64) {
            UnityAccessibilityDelegate unityAccessibilityDelegate = this.a;
            if (unityAccessibilityDelegate.g == i) {
                return false;
            }
            unityAccessibilityDelegate.b.invalidate();
            UnityAccessibilityDelegate unityAccessibilityDelegate2 = this.a;
            unityAccessibilityDelegate2.g = i;
            unityAccessibilityDelegate2.sendEventForVirtualViewId(i, 32768);
            UnityPlayer unityPlayer = this.a.a;
            Objects.requireNonNull(unityPlayer);
            this.a.a.invokeOnMainThread((Runnable) new C0067n0(unityPlayer, i));
            return true;
        }
        if (i2 == 128) {
            this.a.b.invalidate();
            UnityAccessibilityDelegate unityAccessibilityDelegate3 = this.a;
            if (unityAccessibilityDelegate3.g == i) {
                unityAccessibilityDelegate3.g = -1;
            }
            unityAccessibilityDelegate3.sendEventForVirtualViewId(i, 65536);
            UnityPlayer unityPlayer2 = this.a.a;
            Objects.requireNonNull(unityPlayer2);
            this.a.a.invokeOnMainThread((Runnable) new C0070o0(unityPlayer2, i));
            return true;
        }
        if (i2 == 16) {
            if (!UnityAccessibilityDelegate.isNodeSelectable(i)) {
                return false;
            }
            UnityPlayer unityPlayer3 = this.a.a;
            Objects.requireNonNull(unityPlayer3);
            this.a.a.invokeOnMainThread((Runnable) new C0073p0(this, unityPlayer3, i));
            return true;
        }
        if (i2 == 4096 || i2 == 8192) {
            UnityPlayer unityPlayer4 = this.a.a;
            Objects.requireNonNull(unityPlayer4);
            this.a.a.invokeOnMainThread((Runnable) new C0076q0(this, unityPlayer4, i2, i));
            return true;
        }
        if (i2 != 1048576 || !UnityAccessibilityDelegate.isNodeDismissable(i)) {
            return false;
        }
        UnityPlayer unityPlayer5 = this.a.a;
        Objects.requireNonNull(unityPlayer5);
        this.a.a.invokeOnMainThread((Runnable) new C0079r0(unityPlayer5, i));
        return true;
    }
}
