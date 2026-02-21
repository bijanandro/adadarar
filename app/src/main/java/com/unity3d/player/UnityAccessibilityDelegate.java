package com.unity3d.player;

import android.content.res.Configuration;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.accessibility.CaptioningManager;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes.dex */
class UnityAccessibilityDelegate extends View.AccessibilityDelegate {
    private final UnityPlayer a;
    private final SurfaceView b;
    private AccessibilityManager c;
    private AccessibilityManagerAccessibilityStateChangeListenerC0086u0 d;
    private CaptioningManager e;
    private C0090w0 f;
    private int g = -1;
    private int h = -1;
    private boolean i = false;
    private float j = 1.0f;
    private AccessibilityNodeProvider k = new C0082s0(this);

    UnityAccessibilityDelegate(UnityPlayer unityPlayer) {
        this.a = unityPlayer;
        this.b = unityPlayer.getSurfaceView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native int[] getRootNodeIds();

    /* JADX INFO: Access modifiers changed from: private */
    public static native int hitTest(float f, float f2);

    protected static UnityAccessibilityDelegate init(UnityPlayer unityPlayer) {
        UnityAccessibilityDelegate unityAccessibilityDelegate = new UnityAccessibilityDelegate(unityPlayer);
        unityAccessibilityDelegate.c = (AccessibilityManager) unityAccessibilityDelegate.a.getContext().getSystemService("accessibility");
        CaptioningManager captioningManager = (CaptioningManager) unityAccessibilityDelegate.a.getContext().getSystemService("captioning");
        unityAccessibilityDelegate.e = captioningManager;
        if (unityAccessibilityDelegate.c != null || captioningManager != null) {
            Semaphore semaphore = new Semaphore(0);
            unityAccessibilityDelegate.a.runOnUiThread(new RunnableC0058k0(unityAccessibilityDelegate, semaphore));
            try {
                semaphore.acquire();
            } catch (InterruptedException unused) {
            }
        }
        unityAccessibilityDelegate.j = unityAccessibilityDelegate.a.getContext().getResources().getConfiguration().fontScale;
        unityAccessibilityDelegate.a.setAccessibilityDelegate(unityAccessibilityDelegate);
        return unityAccessibilityDelegate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean isNodeDismissable(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean isNodeSelectable(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void onNodeDecremented(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean onNodeDismissed(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void onNodeFocusChanged(int i, boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void onNodeIncremented(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean onNodeSelected(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native boolean populateNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo, int i, View view);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void sendClosedCaptioningChangedNotification(boolean z);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void sendElementFocusedNotification(int i);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void sendFontScaleChangedNotification(float f);

    /* JADX INFO: Access modifiers changed from: private */
    public static native void sendScreenReaderStatusChangedNotification(boolean z);

    public final void a(Configuration configuration) {
        float f = configuration.fontScale;
        if (f != this.j) {
            this.j = f;
            UnityPlayer unityPlayer = this.a;
            Objects.requireNonNull(unityPlayer);
            this.a.invokeOnMainThread((Runnable) new C0061l0(unityPlayer, configuration));
        }
    }

    protected void cleanup() {
        AccessibilityManagerAccessibilityStateChangeListenerC0086u0 accessibilityManagerAccessibilityStateChangeListenerC0086u0 = this.d;
        if (accessibilityManagerAccessibilityStateChangeListenerC0086u0 != null) {
            accessibilityManagerAccessibilityStateChangeListenerC0086u0.cleanup();
        }
        C0090w0 c0090w0 = this.f;
        if (c0090w0 != null) {
            c0090w0.cleanup();
        }
        this.a.setAccessibilityDelegate(null);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        return this.k;
    }

    protected int getFocusedNodeId() {
        return this.g;
    }

    protected boolean sendAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        ViewGroup viewGroup;
        if (accessibilityEvent == null || (viewGroup = (ViewGroup) this.b.getParent()) == null) {
            return false;
        }
        return viewGroup.requestSendAccessibilityEvent(this.b, accessibilityEvent);
    }

    protected boolean sendAnnouncementForVirtualViewId(int i, String str) {
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(16384);
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.setSource(this.b, i);
        accessibilityEventObtain.getText().add(str);
        return sendAccessibilityEvent(accessibilityEventObtain);
    }

    protected boolean sendEventForVirtualViewId(int i, int i2) {
        if (!this.c.isEnabled()) {
            return false;
        }
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i2);
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.setSource(this.b, i);
        if (i2 == 2048) {
            accessibilityEventObtain.setContentChangeTypes(1);
        }
        return sendAccessibilityEvent(accessibilityEventObtain);
    }

    protected boolean sendEventForVirtualViewIdFromNative(int i, int i2) {
        this.a.runOnUiThread(new RunnableC0064m0(this, i, i2));
        return true;
    }
}
